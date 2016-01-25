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
 * ���񣺷��ط������Ƿ���ڸò�����<br>
 * ������������Ҫ��otherparameter�з���һ��Connection������Ϊcon��<br>
 * ��Ҫ���û�������������к���processid,serveralias parameter��ʹ�ǿ��ַ����� <br>
 * ����������
 * 
 * @author ���� 2007-6-22
 */
public class Service_ExistInputParameterForServer extends DefService implements
		IService {

	// ���ݿ�����
	private Connection con = null;

	// ���̺�
	private String processid = null;

	// ������
	private String parameter = null;

	// �������
	private String serveralias = null;

	public Service_ExistInputParameterForServer() {
		super();
	}

	private final Log log = LogFactory
			.getLog(Service_ExistInputParameterForServer.class);

	private boolean initFordo(IMessage message, String soa_processid) {
		// ���ݿ�����
		con = null;
		// ������
		parameter = null;
		// �������
		serveralias = null;
		// ���̺�
		processid = null;

		// ��ȡ�÷�������������ֵ
		con = (Connection) message.getOtherParameter("con");

		parameter = message.getUserParameterValue("parameter");
		serveralias = message.getUserParameterValue("serveralias");
		processid = message.getUserParameterValue("processid");
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		String debug_Service_EIPFS = "������: parameter = " + parameter + "\n"
				+ "�������: serveralias = " + serveralias + "\n"
				+ "���̺�: processid = " + processid + "\n";
		log.debug(processInfo + ",��ѯ�����Ƿ���ڲ�����ʱ�û��ύ�Ĳ���: " + debug_Service_EIPFS);

		if (parameter == null || serveralias == null || processid == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "ȱ���������", this.getId(),
					soa_processid, new java.util.Date(), null));
			log.error(processInfo + ",�����Ƿ���ڲ�������,ȱ���������");
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
			log.error(processInfo + ",�����Ƿ���ڲ�������,��ʼ������ʧ��");
			return ExecuteResult.fail;
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
					log.error(processInfo + ",�����Ƿ���ڲ�������,�������ݿ����,δ֪�����ݿ�����");
					return ExecuteResult.fail;
				}
				log.debug(processInfo + ",�����Ƿ���ڲ�������,�������ݿ�ɹ�");
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
						parameter, "I");
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
							+ ",���ط������Ƿ���ڸò������࣬����������޸�������� count = " + count);
					return ExecuteResult.fail;
				}
			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), soa_processid, new Date(), null));
				log.fatal(processInfo + ",���ط������Ƿ���ڸò�������,���ݿ�����쳣"
						+ sqle.toString());
				return ExecuteResult.fail;
			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();

			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
					soa_processid, new java.util.Date(), e));
			log.fatal(processInfo + ",���ط������Ƿ���ڸò�������,����δ֪�쳣" + e.toString());
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
	}

}
