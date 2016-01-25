package mes.framework.services.adapter;

import java.sql.*;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import mes.framework.*;
import mes.framework.dao.*;

/**
 * ���񣺷����������Ƿ���ڸ÷������ ������������Ҫ��otherparameter�з���һ��Connection������Ϊcon��<br>
 * ��Ҫ���û�������������к���processid,serveralias����ʹ�ǿ��ַ����� <br>
 * ����������
 * 
 * @author ���� 2007-6-22
 */
public class Service_ExistServerForProcess extends DefService implements
		IService {

	// ���ݿ�����
	private Connection con = null;

	// ���̺�
	private String processid = null;

	// �������
	private String serveralias = null;

	public Service_ExistServerForProcess() {
		super();
	}

	private final Log log = LogFactory
			.getLog(Service_ExistServerForProcess.class);

	private boolean initFordo(IMessage message, String soa_processid) {
		// ���ݿ�����
		con = null;
		// ���̺�
		processid = null;
		// �������
		serveralias = null;

		// ��ȡ�÷�������������ֵ
		con = (Connection) message.getOtherParameter("con");

		processid = message.getUserParameterValue("processid");
		serveralias = message.getUserParameterValue("serveralias");
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		String debug_Service_ESFP = "���̺�: processid = " + processid + "\n"
				+ "�������: serveralias = " + serveralias + "\n";
		log.debug(processInfo + ",�������Ƿ���ڸ÷���������û��ύ�Ĳ���: " + debug_Service_ESFP);

		if (processid == null || serveralias == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "ȱ���������", this.getId(),
					soa_processid, new java.util.Date(), null));
			log.error(processInfo + ",�������Ƿ���ڸ÷��������,��ʼ������ʱ,ȱ���������");
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
			log.error(processInfo + ",�������Ƿ���ڸ÷��������,��ʼ����������ʧ��");
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
					log.error(processInfo + ",�������Ƿ���ڸ÷��������,�������ݿ����,δ֪�����ݿ�����");
					return ExecuteResult.fail;
				}
				log.debug(processInfo + ",�������Ƿ���ڸ÷��������,�������ݿ�ɹ�");
				stmt = con.createStatement();
				String sql_check = dao.getSQL_QueryCountProcessServerInfo(
						processid, serveralias);
				log.debug("�������̺�,������������̷�����л�ȡ��¼����sql���: sql_check = "
						+ sql_check);
				rs = stmt.executeQuery(sql_check);
				int count = 0;
				if (rs.next()) {
					count = rs.getInt(1);
				}
				if (count <= 0) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.UNKNOWNDATABASETYPE,
							"�������в����ڸ÷������", this.getId(), soa_processid,
							new Date(), null));
					log.error(processInfo
							+ ",�������Ƿ���ڸ÷��������,�������в����ڸ÷������ count = " + count);
					return ExecuteResult.fail;
				}

			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), soa_processid, new Date(), null));
				log.fatal(processInfo + ",�������Ƿ���ڸ÷��������,�������ݿ����,���ݿ�����쳣"
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
			log.fatal(processInfo + ",�������Ƿ���ڸ÷��������,δ֪�쳣" + e.toString());
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
	}

}
