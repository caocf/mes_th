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
 * �޸����̷��� ������������Ҫ��otherparameter�з���һ��Connection������Ϊcon��<br>
 * ��Ҫ���û������к���processid,sid,serverid,aliasname,actidֵ����ʹ�ǿ��ַ����� <br>
 * ���̷���ִ�� doService() <br>
 * ���̷����ʼ�� initFordo() <br>
 * ���̷������ undoService() <br>
 * ����������
 * 
 * @author ������ 2007-12-25
 * 
 */
public class Service_UpdateProcess extends DefService implements IService {

	private Connection con = null;

	private String processid = null;

	private String sid = null;

	private String serverid = null;

	private String aliasname = null;

	private String actid = null;

	private final Log log = LogFactory.getLog(Service_UpdateProcess.class);

	public ExecuteResult doService(IMessage message, String soa_processid) {
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		if (!initFordo(message, soa_processid)) {
			log.error(processInfo + ",�޸����̷����ʼ������ʧ��!");
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
							ServiceExceptionType.UNKNOWNDATABASETYPE, "dao����",
							this.getId(), soa_processid, new Date(), null));
					log.error(processInfo + ",�޸����̷���ʱ,���ݿ���ش���,δ֪�����ݿ�����");
					return ExecuteResult.fail;
				}
				log.debug(processInfo + ",�޸����̷���,���ݿ���سɹ�");
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
				if (count != 1) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.UNKNOWN, "û��Ҫ���ĵ����ݣ�", this
									.getId(), soa_processid, new Date(), null));
					log.error(processInfo + ",�޸����̷���ʱ,û��Ҫ���ĵ����� count = "
							+ count);
					return ExecuteResult.fail;
				}

				Map<String, String> data = new HashMap<String, String>();
				String map_processid = null;
				String map_sid = null;
				String map_serverid = null;
				String map_aliasname = null;
				String map_actid = null;
				String data_sql_select = dao
						.getSQL_QueryProcessServerInfoForProcessidAndSid(
								processid, sid);
				log.debug(processInfo
						+ "��ѯ�����̺�,���кŵ�������Ϣ��sql���:"+"\n"+" data_sql_select = "
						+ data_sql_select);
				rs = stmt.executeQuery(data_sql_select);
				while (rs.next()) {
					map_processid = rs.getString(1);
					map_sid = rs.getString(2);
					map_serverid = rs.getString(3);
					map_aliasname = rs.getString(4);
					map_actid = rs.getString(5);
				}
				String debug_result_map = "select_map_processid = "
						+ map_processid + "\n" + "select_map_sid = " + map_sid
						+ "\n" + "select_map_serverid = " + map_serverid + "\n"
						+ "select_map_aliasname = " + map_aliasname + "\n"
						+ "select_map_actid = " + map_actid;
				log.debug(processInfo + " , " + this.getClass().getName()
						+ "��ѯ�����̺�,���кŵ�������Ϣ��: debug_result_map = "+"\n"
						+ debug_result_map);
				data.put("map_processid", map_processid);
				data.put("map_sid", map_sid);
				data.put("map_serverid", map_serverid);
				data.put("map_aliasname", map_aliasname);
				data.put("map_actid", map_actid);
				message.setOtherParameter(this.getClass().getName(), data);

				String sql_update = dao.getSQL_UpdateProcess(processid, sid,
						serverid, aliasname, actid);
				log.debug("�������̷����sql���: sql_update = " + sql_update);
				stmt.executeUpdate(sql_update);

			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "sql���ִ�г���", this
								.getId(), soa_processid, new Date(), sqle));
				log.fatal(processInfo + ",�޸����̷���,���ݿ��쳣" + sqle.toString());
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
			log.fatal(processInfo + ",�޸����̷���,δ֪�쳣" + e.toString());
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
	}

	public Service_UpdateProcess() {
		super();
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
		String debug_UpdateProcess = "processid = " + processid + "\n"
				+ "sid = " + sid + "\n" + "serverid = " + serverid + "\n"
				+ "aliasname = " + aliasname + "\n" + "actid = " + actid + "\n";
		log.debug(processInfo + ",�޸����̷���ʱ�û��ύ�Ĳ���: " + debug_UpdateProcess);

		if (processid == null || sid == null || serverid == null
				|| aliasname == null || actid == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "��������Ϊ��", this.getId(),
					soa_processid, new java.util.Date(), null));
			log.error(processInfo + ",�޸����̷���ʱ,ȱ���������");
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
			String map_serverid = map.get("map_serverid");
			String map_aliasname = map.get("map_aliasname");
			String map_actid = map.get("map_actid");
			String debug_map = "map_processid = " + map_processid + "\n"
					+ "map_sid = " + map_sid + "\n" + "map_serverid = "
					+ map_serverid + "\n" + "map_aliasname = " + map_aliasname
					+ "\n" + "map_actid = " + map_actid;
			log.debug(processInfo + " , " + this.getClass().getName()
					+ "�޸����̷����л��˲���Map���յ���ֵ: debug_map = "+"\n" + debug_map);
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
										+ ",�޸����̷�����˲���ʱ,���ݿ���ش���,δ֪�����ݿ�����");
						return ExecuteResult.fail;
					}
					log.debug(processInfo + ",�޸����̷�����˲���,���ݿ���سɹ�");
					stmt = con.createStatement();
					String undo_sql_update = dao.getSQL_UpdateProcess(
							map_processid, map_sid, map_serverid,
							map_aliasname, map_actid);
					log.debug(processInfo + " , " + this.getClass().getName()
							+ "�޸����̷�����˲�����sql���: "+"\n"+"undo_sql_update = "
							+ undo_sql_update);
					stmt.executeUpdate(undo_sql_update);
				} catch (SQLException sqle) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.DATABASEERROR, "���ݿ����" + sqle,
							this.getId(), soa_processid, new Date(), sqle));
					log.fatal(processInfo + ",�޸����̷�����˲���,���ݿ��쳣"
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
				log.fatal(processInfo + ",ɾ�����̷�����˲���,δ֪�쳣" + e.toString());
				return ExecuteResult.fail;
			}
		}
		return ExecuteResult.sucess;
	}
}
