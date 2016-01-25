package th.servlet;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qm.mes.th.helper.Conn_MES;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRRewindableDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import th.fx.bean.PrintOrder;
import th.pz.bean.JConfigure;

/*
 * ��ӡ˵�������ļ�����������ʷ��ӡ��
 * ��ӡprint_data�е�����
 * ��ӡ��Ϣȡ��print_data��
 */
public class RepeatPrint extends HttpServlet {
	/**
	 * �ظ���ӡ�߼���������
	 */
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// ������Ӧͷ
		response.setContentType("application/octet-stream");

		// ��ÿͻ����������
		String groupId = request.getParameter("groupid");// ���ID
		String rq = request.getParameter("rq");// ����
		String ch = request.getParameter("ch");// ����
		String js = request.getParameter("js");// �ܺ�

		// ����Ҫ��ӡ�ı���
		JasperPrint jasperPrint = handle(groupId, rq, ch, js);

		// ��ͻ����������
		ServletOutputStream ouputStream = response.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(ouputStream);
		oos.writeObject(jasperPrint);

		oos.flush();
		oos.close();

		ouputStream.flush();
		ouputStream.close();
	}

	/**
	 * ��ӡ����
	 * 
	 * @param groupId
	 * @param rq
	 * @param ch
	 * @param js
	 */
	private JasperPrint handle(String groupId, String rq, String ch, String js) {
		Connection conn = null;

		try {
			conn = new Conn_MES().getConn();
			// չ����ӡ��
			List<PrintOrder> orders = getPrintOrders(conn, groupId, rq, js);

			// ���ɴ�Ӧ�㱨��
			return getJasperPrint(conn, js, rq, ch, orders);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					conn = null;
				}
			}
		}
		return null;
	}

	/**
	 * չ����ӡ������
	 * 
	 * @param conn
	 *            ���ݿ�����
	 * @param groupId
	 *            ��ӡ��ID
	 * @throws Exception
	 *             �쳣
	 */
	private List<PrintOrder> getPrintOrders(Connection conn, String groupId, String rq, String js) throws Exception {
		List<PrintOrder> list = new ArrayList<PrintOrder>();

		PreparedStatement stmt = null;
		ResultSet rs = null;

		// ��ѯ���
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT MAX(id) maxId, MIN(id) minId, cCarTypeDesc, cPrintMd, ccode ");
		sb.append("FROM printset ");
		sb.append("WHERE iPrintGroupId = ? ");
		sb.append("GROUP BY cCarTypeDesc, cPrintMd, ccode, iPrintGroupId ");
		sb.append("ORDER BY maxId");

		try {
			// ��ѯ�����ڴ�ӡ���õ�����
			stmt = conn.prepareStatement(sb.toString());
			stmt.setString(1, groupId);
			rs = stmt.executeQuery();

			// ����չ��������õ�
			while (rs.next()) {
				PrintOrder order = new PrintOrder();
				order.setBeginId(rs.getInt("minId"));
				order.setEndId(rs.getInt("maxId"));
				order.setPrintMd(rs.getString("cPrintMd"));
				order.setcCode(rs.getString("ccode"));
				order.setPrintTitle(rs.getString("cCarTypeDesc"));

				// ����һ�����õ����б���
				list.add(fillItems(conn, rq, js, order));
			}
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					rs = null;
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					stmt = null;
				}
			}
		}
		return list;
	}

	/**
	 * ��ӡ������Ŀ
	 * 
	 * @param conn
	 * @param printSetId
	 * @param rq
	 * @return
	 */
	private PrintOrder fillItems(Connection conn, String rq, String js, PrintOrder order) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT cseqno, cvincode, cTFAss, cKinNo, inum, cpageNo ");
		sb.append("FROM print_data ");
		sb.append("WHERE iCarNo=? AND cRemark=? AND (iPrintGroupId=? OR iPrintGroupId=?) ");
		sb.append("ORDER BY inum, iPrintGroupId");

		try {
			// ��ѯ�����ڴ�ӡ���õ�����
			stmt = conn.prepareStatement(sb.toString());
			stmt.setString(1, js);
			stmt.setString(2, rq);
			stmt.setInt(3, order.getBeginId());
			stmt.setInt(4, order.getEndId());
			rs = stmt.executeQuery();

			while (rs.next()) {
				JConfigure jc = new JConfigure();
				jc.setCSEQNo_A(rs.getString("cseqno"));
				jc.setCVinCode(rs.getString("cvincode"));
				jc.setCQADNo(rs.getString("cTFAss"));
				jc.setCCarNo(rs.getString("cKinNo"));
				jc.setIndex(rs.getInt("inum"));

				order.setPageNo(rs.getString("cpageNo"));
				order.addItem(jc);
			}
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					rs = null;
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					stmt = null;
				}
			}
		}
		return order;
	}

	/**
	 * ���ɴ�ӡ�������
	 * 
	 * @param order
	 * @return
	 */
	private JasperPrint getJasperPrint(Connection conn, String js, String rq, String ch, List<PrintOrder> orders)
			throws Exception {
		// Servlet�����Ķ��󣬿���ͨ��������������������Ӧ�ó���������Ϣ
		ServletContext context = this.getServletConfig().getServletContext();
		// �����ļ���·��
		File reportFolder = new File(context.getRealPath("ireport"));
		// �������
		JasperPrint jasperPrint = null;

		// Ҫ��������Ĳ���
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("REPORT_CONNECTION", conn);
		parameters.put("js", js);
		parameters.put("zrq", rq);
		parameters.put("ch", ch);
		parameters.put("x_sub", reportFolder.getPath() + "\\");
		parameters.put("xdir", reportFolder.getPath() + "\\");
		parameters.put("SUBREPORT_DIR", reportFolder.getPath() + "\\");

		// ��Ҫ��ӡ�����õ���Ϊ1��ʱ�򣬱�ʾ��һ�ŵ�����ֻ���ӡΨһ�����
		if (orders.size() == 1) {
			// ���õ���Ϣ����
			PrintOrder order = orders.get(0);
			// �����ļ�ģ��λ��
			File reportFile = new File(context.getRealPath(order.getPrintMd()));

			// ���Բ���
			parameters.put("id", order.getBeginId());
			parameters.put("mc", order.getPrintTitle());
			parameters.put("tm", order.getPageNo());

			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getPath());
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jasperPrintDataSource(order));
		} else {
			File reportFile = null;

			// һ�ű�������Ҫ��ӡ�����ͬ�ļ�
			for (int i = 0, index = 1; i < orders.size(); i++, index++) {
				// ���õ���Ϣ����
				PrintOrder order = orders.get(i);
				// ��ñ���ģ���ļ�λ��
				if (reportFile == null) {
					reportFile = new File(context.getRealPath(order.getPrintMd()));
				}
				parameters.put(order.getcCode(), new JRBeanCollectionDataSource(order.getItems()));
				parameters.put("id" + index, order.getBeginId());
				parameters.put("mc" + index, order.getPrintTitle());
				parameters.put("tm" + index, order.getPageNo());
			}
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getPath());
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
		}
		return jasperPrint;
	}

	/**
	 * ��������Դ
	 * 
	 * @param order
	 * @return
	 */
	private JRRewindableDataSource jasperPrintDataSource(PrintOrder order) {
		if (order.getItems().size() > 0) {
			return new JRBeanCollectionDataSource(order.getItems());
		}
		return new JREmptyDataSource();
	}
}
