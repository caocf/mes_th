package th.fx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.Conn_MES;
import th.fx.bean.COrderEntity;

/**
 * ���õ���ӡ��̨�������
 * 
 * @author Ajaxfan
 */
public class ConfigOrderHandler {
	/** ���� */
	private static final String ch = " 1";

	/** ҳ��������� */
	private String jspRq;

	/** ��С��ӡ������� */
	private int minPartCount = 9999;

	/**
	 * ���캯��
	 * 
	 * @param jspRq
	 */
	public ConfigOrderHandler(String jspRq) {
		this.jspRq = jspRq;
	}

	/**
	 * ���з��񣬴�������
	 */
	public synchronized List<COrderEntity> execute() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		// Ҫ��ӡ��ʵ��������
		List<COrderEntity> list = new ArrayList<COrderEntity>();

		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT MAX(id) id, iPrintGroupId, cDescrip, nPerTimeCount,");
		sb.append(" nTFASSCount, cfactory, cCarType, ceiling(CAST(nPerTimeCount as float)/nTFASSCount) cPrintRadio,");
		sb.append(" cAuto, npage, cLastVin, cvinrule");
		sb.append(" FROM printset");
		sb.append(" GROUP BY iPrintGroupId, cDescrip,nPerTimeCount,");
		sb.append(" nTFASSCount, cfactory, cCarType, cPrintRadio, cAuto, npage, cLastVin, cvinrule");

		try {
			conn = new Conn_MES().getConn();
			stmt = conn.prepareStatement(sb.toString());

			rs = stmt.executeQuery();

			// �������ݼ���
			while (rs.next()) {
				// ���õ���Ŀ
				COrderEntity entity = new COrderEntity();

				entity.setPrintSetId(rs.getString("id"));// ��ӡ����Id
				entity.setGroupId(rs.getString("iPrintGroupId"));// ���
				entity.setDescript(rs.getString("cDescrip"));// ������Ϣ
				entity.setPerTimeCount(rs.getInt("nPerTimeCount"));// ÿ�δ�ӡ��
				entity.setTFassCount(rs.getInt("nTFASSCount"));// ÿ�������
				entity.setFactoryNo(rs.getString("cfactory"));// ����
				entity.setCarType(rs.getString("cCarType"));// ����
				entity.setPrintRadio(rs.getInt("cPrintRadio"));// ��ӡ����
				// �Ƿ��Զ���ӡ 1: �Զ���ӡ 0: ���Զ���ӡ
				entity.setAuto(rs.getString("cAuto"));
				entity.setPages(rs.getInt("npage"));// ��ӡ����
				entity.setLastVin(rs.getString("cLastVin"));// ����vin��
				entity.setCvinrule(rs.getString("cvinrule"));// vin����

				// ���������Ϣ
				handleCardata(conn, entity);

				// ������õ�
				list.add(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					rs = null;
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					stmt = null;
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					conn = null;
				}
			}
		}
		return list;
	}

	/**
	 * ��ѯ����ӡ��Vin���Ӧ�ĳ�����Ϣ����װ����ʱ�䣬��װ˳���)
	 * 
	 * @param conn
	 */
	private void handleCardata(Connection conn, COrderEntity entity) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.prepareStatement(
					"SELECT dabegin, cseqno_a FROM cardata WHERE cvincode = ? AND dabegin is not null");
			stmt.setString(1, entity.getLastVin());

			rs = stmt.executeQuery();

			if (rs.next()) {
				entity.setDabegin(rs.getString("dabegin"));// ��װ����ʱ��
				entity.setSeq_a(rs.getString("cseqno_a"));// ��װ˳���
			}
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					rs = null;
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					stmt = null;
				}
			}
		}
		// ��ӡ��¼��
		handlePrintSet(conn, entity);
	}

	/**
	 * �Ӵ�ӡ��¼���ж�ȡ����ӡ���ݵĻ���������Ϣ
	 * 
	 * @throws Exception
	 */
	private void handlePrintSet(Connection conn, COrderEntity entity) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		// hmvin���vin��
		Map<String, String> hmVin = new HashMap<String, String>();

		try {
			stmt = conn.prepareStatement("SELECT ctype, clastvin FROM printSetVin WHERE printid = ?");
			stmt.setString(1, entity.getPrintSetId());

			rs = stmt.executeQuery();

			while (rs.next()) {
				String ctype = rs.getString("ctype");

				if (ctype != null && !ctype.equals("")) {
					hmVin.put(ctype, rs.getString("clastvin"));
				}
			}
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					rs = null;
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					stmt = null;
				}
			}
		}
		// �ж�vin�Ƿ�����
		handleVinSeries(conn, entity, hmVin);
	}

	/**
	 * ���Vin�Ƿ�����
	 * 
	 * @param conn
	 * @param entity
	 * @throws Exception
	 */
	private void handleVinSeries(Connection conn, COrderEntity entity, Map<String, String> hmVin) throws Exception {
		// Vin���������
		int perTimeRow = entity.getPerTimeCount();

		// ���ò���
		entity.setPerTimeRow(perTimeRow);

		// ��ѯ���
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT").append(" c.cVinCode, c.dabegin");
		sql.append(" FROM carData c");
		sql.append(" WHERE ((dabegin = ? AND c.cSEQNo_A > ?)").append(" OR (dabegin > ?))");

		// ����
		String carType = entity.getCarType();

		if (carType != null && !carType.equals("")) {
			carType = "'" + carType + "'";
			carType = carType.replace(",", "','");

			sql.append("AND SUBSTRING(c.ccarno, 6, 1) IN (" + carType + ")");
		}

		// ��������
		String factoryNo = entity.getFactoryNo();

		if (factoryNo != null && !factoryNo.equals("")) {
			factoryNo = "'" + factoryNo + "'";
			factoryNo = factoryNo.replace(",", "','");

			sql.append(" AND (SUBSTRING(c.cSEQNo_A, 1, 2) IN(" + factoryNo + ")) ");
		}

		// VIN����
		String cVinRule = entity.getCvinrule();

		if (cVinRule != null && !cVinRule.equals("")) {
			cVinRule = "'" + cVinRule + "'";
			cVinRule = cVinRule.replace(",", "','");

			sql.append(" AND (SUBSTRING(c.cVinCode,7,2) IN(" + cVinRule + ")) ");
		}
		// ����װ����ʱ�����װ˳�������
		sql.append(" order by c.dabegin, c.cSEQNo_A");

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setString(1, entity.getDabegin());// ��װ����ʱ��
			stmt.setString(2, entity.getSeq_a());// ��װ˳���
			stmt.setString(3, entity.getDabegin());// ��װ����ʱ��

			rs = stmt.executeQuery();

			for (int i = 0; i < perTimeRow && rs.next(); i++) {
				String vinCode = rs.getString("cVinCode");// Vin��
				String vinType = vinCode.substring(6, 8);// Vin����
				String tempVin = hmVin.get(vinType);// ͨ��Vin���ͱ�־������������ӡ��Vin��

				// �����ǰ���Ͷ�Ӧ��Vin��¼�����ڣ��򽫵�ǰ��Vin�����ó���
				if (tempVin != null && !"".equals(tempVin)) {
					int oldVinLst = Integer.valueOf(tempVin.substring(11)); // vin����λ
					int newVinLst = Integer.valueOf(vinCode.substring(11)); // cardata��vin��6λ

					// ��������������кţ���ô������Ľ��Ӧ�õ���1
					if ((newVinLst - oldVinLst) != 1) {
						entity.setContinue(false);

						break;
					}
				}
				hmVin.put(vinType, vinCode);
			}
			rs.last();
			entity.setPartCount(rs.getRow());

			if (rs.getRow() > 0) {
				rs.absolute(rs.getRow() - 1);
				entity.setDabegin(timeDiff(rs.getTimestamp("dabegin")));
			}
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					rs = null;
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					stmt = null;
				}
			}
		}
		// ��¼��ӡ����
		handlePritdata(conn, entity);
	}

	/**
	 * ��ѯ��ӡ�ĳ������Kin��
	 * 
	 * @param conn
	 * @param entity
	 * @throws Exception
	 */
	private void handlePritdata(Connection conn, COrderEntity entity) throws Exception {
		int partCount = entity.getPartCount();// ��ǰҪ��ӡ���ּ�����

		// ���ô�ӡ����
		entity.setMinPartCount(minPartCount > partCount ? partCount : minPartCount);

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.prepareStatement(
					"SELECT ISNULL(MAX(iCarNo), 0) FROM print_Data WHERE cremark = ? AND iPrintGroupId = ?");
			stmt.setString(1, jspRq);
			stmt.setString(2, entity.getPrintSetId());

			rs = stmt.executeQuery();

			if (rs.next()) {
				entity.setMaxCarno(rs.getString(1));
			}
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					rs = null;
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					stmt = null;
				}
			}
		}
		// ��֯�Զ���ӡ�Ľű����ʽ
		orgnizeOpenappExpression(entity);
	}

	/**
	 * ��֯�Զ���ӡ�Ľű����ʽ
	 * 
	 * @param entity
	 */
	private void orgnizeOpenappExpression(COrderEntity entity) {
		// ��ǰ���õ�ÿ���ӡ����
		int perTimeRow = entity.getPerTimeRow();
		// ��С��ӡ����
		int minPartCount = entity.getMinPartCount();

		// �ж��Զ���ӡ��־��ֻ������Ϊ�Զ���ӡ��ʱ������Ż����������
		if (entity.getAuto().equals("1") && minPartCount >= perTimeRow) {
			StringBuilder openApp = new StringBuilder();
			openApp.append("openApp(").append(entity.getGroupId()).append(",");
			openApp.append(entity.getPrintRadio()).append(",").append(ch).append(",");
			openApp.append(entity.getPages()).append(",").append(minPartCount).append(",");
			openApp.append(perTimeRow).append(",").append(entity.isContinue() ? "1" : "0").append(");");

			// �����Զ���ӡ�ĵ�ȡ��
			entity.setOpenApp(openApp.toString());
		}
	}

	private String timeDiff(Timestamp lasttime) {
		Calendar cal = java.util.GregorianCalendar.getInstance();
		long diff = cal.getTimeInMillis() - lasttime.getTime();

		Integer ss = 1000;
		Integer mi = ss * 60;
		Integer hh = mi * 60;
		Integer dd = hh * 24;

		Long day = diff / dd;
		Long hour = (diff - day * dd) / hh;
		Long minute = (diff - day * dd - hour * hh) / mi;
		Long second = (diff - day * dd - hour * hh - minute * mi) / ss;

		StringBuffer sb = new StringBuffer();
		if (day > 0) {
			sb.append(day + "��");
		}
		if (hour > 0) {
			sb.append(hour + "Сʱ");
		}
		if (minute > 0) {
			sb.append(minute + "��");
		}
		if (second > 0) {
			sb.append(second + "��");
		}
		return sb.toString();
	}
}
