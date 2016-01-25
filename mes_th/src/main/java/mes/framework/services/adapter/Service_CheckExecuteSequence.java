package mes.framework.services.adapter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import mes.framework.DataBaseType;
import mes.framework.DefService;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.IProcess;
import mes.framework.IService;
import mes.framework.ProcessFactory;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import mes.framework.dao.DAOFactory_Core;
import mes.framework.dao.IDAO_Core;

/**
 * ���񣺼����������Ƿ����������֮ǰ ������������Ҫ��otherparameter�з���һ��Connection������Ϊcon��<br>
 * ��Ҫ���û�������������к���processid,i_serveralias, sourceid,o_serveralias ��ʹ�ǿ��ַ����� <br>
 * ����������
 * 
 * @author ���� 2007-6-22
 */
public class Service_CheckExecuteSequence extends DefService implements
		IService {

	// ���ݿ�����
	private Connection con = null;

	// ���̺�
	private String processid = null;

	// ����˷������
	private String i_serveralias = null;

	// ����˷������
	private String o_serveralias = null;

	// ������Դ
	private String sourceid = null;

	public Service_CheckExecuteSequence() {
		super();
	}

	private final Log log = LogFactory
			.getLog(Service_CheckExecuteSequence.class);

	private boolean initFordo(IMessage message, String soa_processid) {

		// ���ݿ�����
		con = null;
		// ���̺�
		processid = null;
		// ����˷������
		i_serveralias = null;
		// ����˷������
		o_serveralias = null;
		// ������Դ
		sourceid = null;

		// ��ȡ�÷�������������ֵ
		con = (Connection) message.getOtherParameter("con");

		processid = message.getUserParameterValue("processid");
		i_serveralias = message.getUserParameterValue("i_serveralias");
		o_serveralias = message.getUserParameterValue("o_serveralias");
		sourceid = message.getUserParameterValue("sourceid");
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		String debug_Service_CES = "���̺�: processid = " + processid + "\n\r"
				+ "����˷������: i_serveralias = " + i_serveralias + "\n\r"
				+ "����˷������: o_serveralias = " + o_serveralias + "\n\r"
				+ "������Դ: sourceid = " + sourceid + "\n\r" + "���ݿ�����: con = "
				+ con + "\n\r";
		log.debug(processInfo + ",������ִ��˳��ʱ�û��ύ�Ĳ���: " + debug_Service_CES);

		if (processid == null || i_serveralias == null || o_serveralias == null
				|| sourceid == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "ȱ���������", this.getId(),
					soa_processid, new java.util.Date(), null));
			log.error(processInfo + ",������ִ��˳����,ȱ���������");
			return false;
		}
		return true;
	}

	public synchronized ExecuteResult doService(IMessage message,
			String soa_processid) {
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		// ��ʼ���������в�������ʧ�ܣ�����ִ�н��
		if (!initFordo(message, soa_processid)) {
			log.error(processInfo + ",������ִ��˳����,��ʼ���������в���ʧ��");
			return ExecuteResult.fail;
		}

		if (sourceid.trim().equals("1")) {
			return ExecuteResult.sucess;
		} else {
			log.error(processInfo + ",������ִ��˳����,������Դ��Ϊ 1");
		}

		try {
			Statement stmt = null;
			ResultSet rs = null;
			try {
				IDAO_Core dao = DAOFactory_Core.getInstance(DataBaseType
						.getDataBaseType(con));
				if (dao == null) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.UNKNOWNDATABASETYPE,
							"δ֪�����ݿ�����", this.getId(), soa_processid,
							new Date(), null));
					log.error(processInfo + ",������ִ��˳����,�������ݿ����,δ֪�����ݿ�����");
					return ExecuteResult.fail;
				}
				log.debug(processInfo + ",������ִ��˳����,�������ݿ�ɹ�");
				stmt = con.createStatement();

				String i_sid = "";
				String o_sid = "";

				String sql_getSID = dao.getSQL_QuerySID(processid,
						i_serveralias);
				log.debug("�������̺�,�������(����)��ȡ����˳��ŵ�sql���: sql_getSID = "
						+ sql_getSID);
				rs = stmt.executeQuery(sql_getSID);
				if (rs.next()) {
					i_sid = rs.getString(1);
				}

				String sql_getSID2 = dao.getSQL_QuerySID(processid,
						o_serveralias);
				log.debug("�������̺�,�������(���)��ȡ����˳��ŵ�sql���: sql_getSID2 = "
						+ sql_getSID2);
				rs = stmt.executeQuery(sql_getSID2);
				if (rs.next()) {
					o_sid = rs.getString(1);
				}

				if (Integer.parseInt(i_sid) <= Integer.parseInt(o_sid)) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.UNKNOWN, "����˷��������ͺ���������",
							this.getId(), soa_processid, new Date(), null));
					log.error(processInfo + ",������ִ��˳����,����˷��������ͺ���������");
					return ExecuteResult.fail;
				}

			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), soa_processid, new Date(), null));
				log.fatal(processInfo + ",������ִ��˳����,���ݿ�����쳣: "
						+ sqle.toString());
				return ExecuteResult.fail;
			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			}
		} catch (Exception e) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
					soa_processid, new java.util.Date(), e));
			log.fatal(processInfo + ",������ִ��˳����,δ֪�쳣: " + e.toString());
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
	}

}
