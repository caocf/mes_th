package com.qm.mes.ra.service.state;

import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.Date;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.AdapterService;
import com.qm.mes.framework.ExecuteResult;
import com.qm.mes.framework.IMessage;
import com.qm.mes.framework.ServiceException;
import com.qm.mes.framework.ServiceExceptionType;
import com.qm.mes.ra.bean.ConversionState;
import com.qm.mes.ra.factory.StateManager;

/**
 * �޸�״̬ת����Ϣ
 * 
 * @author xujia
 * 
 */
public class UpdateConversionState extends AdapterService { 
	/**
	 * �������
	 */
	private Connection con = null; 
	/**
	 * ԭ״̬
	 */
	private String fromstate = null; 
	/**
	 * ��ת״̬
	 */
	private String tostate = null;
	/**
	 * ת������
	 */
	private String string_desc = null;
	/**
	 * ���
	 */
	String from_id = null;

	String to_id = null;

	private String int_id = null;

	private ResultSet rs1 = null;

	private String sql1 = "";

	private final Log log = LogFactory.getLog(UpdateConversionState.class);
	StateManager Manager = new StateManager();

	@Override
	public boolean checkParameter(IMessage message, String processid) {

		con = (Connection) message.getOtherParameter("con");

		Statement stmt = null;

		try {

			fromstate = message.getUserParameterValue("fromstate");
			tostate = message.getUserParameterValue("tostate");
			string_desc = message.getUserParameterValue("string_desc");
			int_id = message.getUserParameterValue("int_id");
			from_id = message.getUserParameterValue("from_id");
			to_id = message.getUserParameterValue("to_id");
			boolean fff = Integer.parseInt(fromstate) != Integer.parseInt(from_id);
			boolean fff1 = Integer.parseInt(tostate) != Integer.parseInt(to_id);
			boolean fff2 = fff || fff1;
			if (!fff2) {
				// û�иı�ֱ���ύ
				return true;
			}
			stmt = con.createStatement(); // ��ʼ��
			sql1 = "select int_fromstate, int_tostate from t_ra_stateconversion where int_fromstate="
					+ Integer.parseInt(fromstate)
					+ " and int_tostate="
					+ Integer.parseInt(tostate);

			// ���log��Ϣ
			String debug = "״̬����fromstate:" + fromstate + "  ��ʽ����tostate:"
					+ tostate + "  ������string_desc:" + string_desc + "\n";
			log.info("���״̬ת���Ĳ���: " + debug);

			// У���Ƿ���Ϣ�ظ�
			rs1 = stmt.executeQuery(sql1);
			if (rs1.next()) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.PARAMETERLOST, "����ת�����ظ��������¶���",
						this.getId(), processid, new java.util.Date(), null));
				return false;

			}
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {

			if ((Integer.parseInt(fromstate)) == (Integer.parseInt(tostate))) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.UNKNOWN, "��ͬ״̬֮�䲻��ת����������ѡ��", this
								.getId(), processid, new Date(), null));
                log.fatal("��ͬ״̬֮�䲻��ת��");
				return ExecuteResult.fail;
			} else {

				ConversionState state = new ConversionState();
				state.setFromstate(Integer.parseInt(fromstate));
				state.setTostate(Integer.parseInt(tostate));
				state.setDesc(string_desc);
				state.setId(Integer.parseInt(int_id));
				Manager.updateConversionState(state, con);
				log.debug("���������ɹ�!");

			}
		} catch (Exception e) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
					processid, new java.util.Date(), e));
			log.error("���״̬�������ʱ,δ֪�쳣" + e.toString());
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
	}

	@Override
	public void relesase() throws SQLException {
		fromstate = null;
		tostate = null;
		string_desc = null;

	}

}
