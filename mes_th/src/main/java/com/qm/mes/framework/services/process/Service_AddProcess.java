/**
 * create by chenpeng
 * function: add a process 
 * process: insert a record into table process_services
 */
package com.qm.mes.framework.services.process;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.DataBaseType;
import com.qm.mes.framework.DefService;
import com.qm.mes.framework.ExecuteResult;
import com.qm.mes.framework.IMessage;
import com.qm.mes.framework.IProcess;
import com.qm.mes.framework.IService;
import com.qm.mes.framework.ProcessFactory;
import com.qm.mes.framework.ServiceException;
import com.qm.mes.framework.ServiceExceptionType;
import com.qm.mes.framework.dao.DAOFactory_Core;
import com.qm.mes.framework.dao.IDAO_Core;

/**
 * ������̷��� ������������Ҫ��otherparameter�з���һ��Connection������Ϊcon��<br>
 * ��Ҫ���û������к���processid,sid,serverid,aliasname,actidֵ����ʹ�ǿ��ַ����� <br>
 * ���̷���ִ�� doService() <br>
 * ���̷����ʼ�� initFordo() <br>
 * ���̷������ undoService() �����̷�����Ӳ������ֲ�ͨ��ع�<br>
 * ����������
 * 
 * @author ������ 2007-12-25
 * 
 */
public class Service_AddProcess extends DefService implements IService {

	private Connection con = null;

	private String processid = null;

	private String sid = null;

	private String serverid = null;

	private String aliasname = null;

	private String actid = null;

	public Service_AddProcess() {
		super();
	}

	private final Log log = LogFactory.getLog(Service_AddProcess.class);

	public synchronized ExecuteResult doService(IMessage message,
			String soa_processid) {
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		if (!initFordo(message, soa_processid)) {
			log.error(processInfo + ",������̷����ʼ������ʧ��!");
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
							ServiceExceptionType.UNKNOWNDATABASETYPE, "daoΪ��",
							this.getId(), soa_processid, new Date(), null));
					log.error(processInfo + ",������̷���ʱ,���ݿ���ش���,δ֪�����ݿ�����");
					return ExecuteResult.fail;
				}
				log.debug(processInfo + ",������̷���ʱ,���ݿ���سɹ�" + "\n");
				stmt = con.createStatement();
				String sql_check = dao
						.getSQL_QueryCountProcessServerForProcessidAndSid(
								processid, sid);
				log.debug("������̷�����Ƿ����ظ����ݵ�sql���: sql_check = " + sql_check);
				rs = stmt.executeQuery(sql_check);
				int count = 0;
				if (rs.next()) {
					count = rs.getInt(1);
				}
				if (count > 0) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.UNKNOWN, "�����Ѿ�����", this
									.getId(), soa_processid, new Date(), null));
					log.error(processInfo + ",������̷���ʱ,�����Ѿ����� count = " + count);
					return ExecuteResult.fail;
				}
				// �����̺�,���кŷ���Map��
				Map<String, String> data = new HashMap<String, String>();
				data.put("map_processid", processid);
				data.put("map_sid", sid);
				message.setOtherParameter(this.getClass().getName(), data);
				/*
				 * ������̷���
				 */
				String sql_insert = dao.getSQL_InsertProcess(processid, sid,
						serverid, aliasname, actid);
				log.debug("�������̷����sql���: sql_insert = " + sql_insert);
				stmt.executeUpdate(sql_insert);

			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ����" + sqle,
						this.getId(), soa_processid, new Date(), sqle));
				log.fatal(processInfo + ",������̷���ʱ,���ݿ��쳣" + sqle.toString());
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
			log.fatal(processInfo + ",������̷���ʱ,δ֪�쳣" + e.toString());
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
	}

	private boolean initFordo(IMessage message, String soa_processid) {
		con = null;
		// ���̺�
		processid = null;
		// ���к�
		sid = null;
		// �����
		serverid = null;
		// �������
		aliasname = null;
		// �쳣���ͺ�
		actid = null;
		con = (Connection) message.getOtherParameter("con");
		processid = message.getUserParameterValue("processid");
		sid = message.getUserParameterValue("sid");
		serverid = message.getUserParameterValue("serverid");
		aliasname = message.getUserParameterValue("aliasname");
		actid = message.getUserParameterValue("actid");

		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();

		String debug_AddProcess = "���̺�: processid = " + processid + "\n"
				+ "���к�:sid = " + sid + "\n" + "�����: serverid = " + serverid
				+ "\n" + " �������:aliasname = " + aliasname + "\n"
				+ "�쳣���ͺ�:actid = " + actid + "\n";
		log.debug(processInfo + ",������̷���ʱ�û��ύ�Ĳ���: " + debug_AddProcess);

		if (processid == null || sid == null || serverid == null
				|| aliasname == null || actid == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "", this.getId(),
					soa_processid, new java.util.Date(), null));
			log.error(processInfo + ",������̷���ʱ,ȱ���������!");
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public ExecuteResult undoService(IMessage message, String soa_processid) {
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		Object obj = message.getOtherParameter(this.getClass().getName());
		if (obj instanceof Map) {
			Map<String, String> map = (Map<String, String>) obj;
			String map_processid = map.get("map_processid");
			String map_sid = map.get("map_sid");
			String debug_map = "map_processid = " + map_processid + "\n"
					+ "map_sid = " + map_sid;
			log
					.debug(processInfo + "������̷�����˲������յ���ֵ: debug_map = "
							+ debug_map);
			try {
				Statement stmt = null;
				try {
					IDAO_Core dao = DAOFactory_Core.getInstance(DataBaseType
							.getDataBaseType(con));
					if (dao == null) {
						message.addServiceException(new ServiceException(
								ServiceExceptionType.UNKNOWNDATABASETYPE,
								"daoΪ��", this.getId(), soa_processid,
								new Date(), null));
						log
								.error(processInfo
										+ ",������̷�����˲���ʱ,���ݿ���ش���,δ֪�����ݿ�����");
						return ExecuteResult.fail;
					}
					log.debug(processInfo + ",������̷�����˲���ʱ,���ݿ���سɹ�" + "\n");
					String undo_sql_delete = dao.getSQL_DeleteProcess(
							map_processid, map_sid);
					log.debug(processInfo + " , " + this.getClass().getName()
							+ "������̷�����˲�����sql���: undo_sql_delete  = "
							+ undo_sql_delete);
					stmt = con.createStatement();
					stmt.executeUpdate(undo_sql_delete);
				} catch (SQLException sqle) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.DATABASEERROR, "���ݿ����" + sqle,
							this.getId(), soa_processid, new Date(), sqle));
					log.fatal(processInfo + ",������̷�����˲���ʱ,���ݿ��쳣"
							+ sqle.toString());
					return ExecuteResult.fail;
				} finally {
					if (stmt != null)
						stmt.close();
				}
			} catch (Exception e) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.UNKNOWN, e.toString(), this
								.getId(), soa_processid, new java.util.Date(),
						e));
				log.fatal(processInfo + ",������̷�����˲���ʱ,δ֪�쳣" + e.toString());
				return ExecuteResult.fail;
			}
		}
		return ExecuteResult.sucess;
	}
}
