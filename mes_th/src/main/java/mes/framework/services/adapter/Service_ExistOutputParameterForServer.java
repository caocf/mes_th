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
 * ���񣺷��ط������Ƿ���ڸ���������� ������������Ҫ��otherparameter�з���һ��Connection������Ϊcon��<br>
 * ��Ҫ���û�������������к���processid,serveralias parameter��sourceid ��ʹ�ǿ��ַ����� <br>
 * ����������
 * 
 * @author ���� 2007-6-22
 */
public class Service_ExistOutputParameterForServer extends DefService implements
		IService {

	// ���ݿ�����
	private Connection con = null;

	// ���̺�
	private String processid = null;

	// ������
	private String parameter = null;

	// �������
	private String serveralias = null;

	// ������Դ
	private String sourceid = null;

	public Service_ExistOutputParameterForServer() {
		super();
	}

	private final Log log = LogFactory
			.getLog(Service_ExistOutputParameterForServer.class);

	private boolean initFordo(IMessage message, String soa_processid) {
		// ���ݿ�����
		con = null;
		// ������
		parameter = null;
		// �������
		serveralias = null;
		// ������Դ
		sourceid = null;
		// ���̺�
		processid = null;

		// ��ȡ�÷�������������ֵ
		con = (Connection) message.getOtherParameter("con");

		parameter = message.getUserParameterValue("parameter");

		serveralias = message.getUserParameterValue("serveralias");
		sourceid = message.getUserParameterValue("sourceid");
		processid = message.getUserParameterValue("processid");
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		String debug_Service_EOPFS = "������: parameter = " + parameter + "\n"
				+ "�������: serveralias = " + serveralias + "\n"
				+ "������Դ: sourceid = " + sourceid + "\n" + "���̺�: processid = "
				+ processid + "\n";
		log.debug(processInfo + ",��ѯ�����Ƿ���ڸ�������������û��ύ�Ĳ���: "
				+ debug_Service_EOPFS);

		if (parameter == null || serveralias == null || processid == null
				|| sourceid == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "ȱ���������", this.getId(),
					soa_processid, new java.util.Date(), null));
			log.error(processInfo + ",�������Ƿ���ڸ������������,��ʼ��ʱȱ���������");
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
			log.error(processInfo + ",�������Ƿ���ڸ������������,��ʼ���������в���ʧ��");
			return ExecuteResult.fail;
		}
		if (sourceid.trim().equals("1")) {
			return ExecuteResult.sucess;
		} else {
			log.error(processInfo + ",�������Ƿ���ڸ������������,����Ų��� 1");
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
					log.error(processInfo
							+ ",�������Ƿ���ڸ������������,�������ݿ���ִ���,δ֪�����ݿ�����");
					return ExecuteResult.fail;
				}
				log.debug(processInfo + ",�������Ƿ���ڸ������������,�������ݿ�ɹ�");
				stmt = con.createStatement();

				String sql_getServerID = dao
						.getSQL_QueryProcessServerForProcessIDServerAlias(
								processid, serveralias);
				log.debug("�������̺źͷ��������ѯ���̷�����Ϣ��sql���: sql_getServerID = "
						+ sql_getServerID);
				String serverid = "";
				rs = stmt.executeQuery(sql_getServerID);
				if (rs.next()) {
					serverid = rs.getString(3);
				}

				String sql_check = dao.getSQL_QueryCountParameterInfo(serverid,
						parameter, "O");
				log.debug("���ݷ����,�������������ʹӷ���������л�ȡ��¼����sql���: sql_check = "
						+ sql_check);
				int count = 0;
				rs = stmt.executeQuery(sql_check);
				if (rs.next()) {
					count = rs.getInt(1);
				}
				if (count <= 0) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.UNKNOWN, "����˷������޸����������", this
									.getId(), soa_processid, new Date(), null));
					log.error(processInfo
							+ ",�������Ƿ���ڸ������������,����˷������޸�������� count = " + count);
					return ExecuteResult.fail;
				}
			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), soa_processid, new Date(), null));
				log.fatal(processInfo + ",�������Ƿ���ڸ������������,�������ݿ�����쳣"
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
			log.fatal(processInfo + ",�������Ƿ���ڸ������������,����δ֪�쳣" + e.toString());
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
	}

}
