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
 * ɾ�����̶��� ������������Ҫ��otherparameter�з���һ��Connection������Ϊcon��<br>
 * ��Ҫ���û������к���processidֵ <br>
 * ���̶���ִ�� doService() <br>
 * ���̶����ʼ�� initFordo() <br>
 * ���̶������ undoService() <br>
 * ����������
 * 
 * @author ������ 2007-12-25
 * 
 */

public class Service_DeleteProcessStatement extends DefService implements
		IService {

	private Connection con = null;

	private String processid = null;

	public Service_DeleteProcessStatement() {
		super();
	}

	private final Log log = LogFactory
			.getLog(Service_DeleteProcessStatement.class);

	public ExecuteResult doService(IMessage message, String soa_processid) {
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		if (!initFordo(message, soa_processid)) {
			log.error(processInfo + ",ɾ�����̶����ʼ������ʧ��");
			return ExecuteResult.fail;
		}

		try {
			Statement stmt = null;
			ResultSet rs = null;
			ResultSet result = null;
			try {
				IDAO_Core dao = DAOFactory_Core.getInstance(DataBaseType
						.getDataBaseType(con));
				if (dao == null) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.UNKNOWNDATABASETYPE, "daoΪ��",
							this.getId(), soa_processid, new Date(), null));
					log.error(processInfo + ",ɾ�����̶���ʱ,���ݿ���ش���,δ֪�����ݿ�����");
					return ExecuteResult.fail;
				}
				log.debug(processInfo + ",ɾ�����̶���,���ݿ���سɹ�");
				stmt = con.createStatement();
				String sql_check = dao
						.getSQL_QueryCountProcessStatementForProcessid(processid);
				log.debug("������̶�����Ƿ����ظ����ݵ�sql���: sql_check = " + sql_check);
				rs = stmt.executeQuery(sql_check);
				int count = 0;
				if (rs.next()) {
					count = rs.getInt(1);
					rs.close();
				}
				if (count == 0) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.UNKNOWN, "Ҫɾ�������ݲ�����", this
									.getId(), soa_processid, new Date(), null));
					log.error(processInfo + ",ɾ�����̶���ʱ,Ҫɾ�������ݲ����� count = "
							+ count);
					return ExecuteResult.fail;
				} else {
					Map<String, String> data = new HashMap<String, String>();
					String map_processid = null;
					String map_processname = null;
					String map_description = null;
					String map_namespace = null;
					String sql_select = dao.getSQL_QueryAllProcessStatementIds(
							processid, "ById");
					log.debug(processInfo + "��ѯ�����̺ŵ�������Ϣ��sql���: sql_select = "
							+ sql_select);
					result = stmt.executeQuery(sql_select);
					while (result.next()) {
						map_processid = result.getString(1);
						map_processname = result.getString(2);
						map_description = result.getString(3);
						map_namespace = result.getString(4);
					}
					String debug_result_map = "select_map_processid = "
							+ map_processid + "\n"
							+ "select_map_processname = " + map_processname
							+ "\n" + "select_map_description = "
							+ map_description + "\n"
							+ "select_map_namespace = " + map_namespace;
					log.debug(processInfo + " , " + this.getClass().getName()
							+ "��ѯ�����̺ŵ�������Ϣ��: debug_result_map = "
							+ debug_result_map);
					data.put("map_processid", map_processid);
					data.put("map_processname", map_processname);
					data.put("map_description", map_description);
					data.put("map_namespace", map_namespace);
					message.setOtherParameter(this.getClass().getName(), data);
					/*
					 * ɾ�����̶���
					 */
					String sql_delete = dao
							.getSQL_DeleteProcessStatement(processid);
					log.debug("ɾ�����̶����sql���: sql_delete = " + sql_delete);
					stmt.executeUpdate(sql_delete);
				}

			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ����" + sqle,
						this.getId(), soa_processid, new Date(), null));
				log.fatal(processInfo + ",ɾ�����̶���,���ݿ��쳣" + sqle.toString());
				return ExecuteResult.fail;
			} finally {
				if (result != null)
					result.close();
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			}
		} catch (Exception e) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
					soa_processid, new java.util.Date(), e));
			log.fatal(processInfo + ",ɾ�����̶���,δ֪�쳣" + e.toString());
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
	}

	private boolean initFordo(IMessage message, String soa_processid) {

		con = null;
		// ���̺�
		processid = null;
		con = (Connection) message.getOtherParameter("con");
		processid = message.getUserParameterValue("processid");
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();

		String debug_DeleteProcessStatement = "processid = " + processid + "\n";
		log.debug(processInfo + ",ɾ�����̶���ʱ�û��ύ�Ĳ���:"
				+ debug_DeleteProcessStatement);

		if (processid == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
					soa_processid, new java.util.Date(), null));
			log.error(processInfo + ",ɾ�����̶���ʱ,ȱ���������");
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
			String map_processname = map.get("map_processname");
			String map_description = map.get("map_description");
			String map_namespace = map.get("map_namespace");
			String debug_map = "map_processid = " + map_processid + "\n"
					+ "map_processname = " + map_processname + "\n"
					+ "map_description = " + map_description + "\n"
					+ "map_namespace = " + map_namespace;
			log.debug(processInfo + "ɾ�����̶����л��˲���Map���յ���ֵ: debug_map = "
					+ debug_map);
			try {
				Statement st = null;
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
										+ ",ɾ�����̶�����˲���ʱ,���ݿ���ش���,δ֪�����ݿ�����");
						return ExecuteResult.fail;
					}
					log.debug(processInfo + ",ɾ�����̶�����˲���,���ݿ���سɹ�");
					st = con.createStatement();
					String undo_sql_insert = dao.getSQL_InsertProcessStatement(
							map_processid, map_processname, map_description,
							map_namespace);
					log.debug(processInfo + " , " + this.getClass().getName()
							+ "ɾ�����̶�����˲�����sql���: undo_sql_insert = "
							+ undo_sql_insert);
					st.executeUpdate(undo_sql_insert);
				} catch (SQLException sqle) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.DATABASEERROR, "���ݿ����" + sqle,
							this.getId(), soa_processid, new Date(), sqle));
					log.fatal(processInfo + ",ɾ�����̶�����˲���,���ݿ��쳣" + sqle.toString());
					return ExecuteResult.fail;
				} finally {
					if (st != null)
						st.close();
				}
			} catch (Exception e) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.UNKNOWN, e.toString(), this
								.getId(), soa_processid, new java.util.Date(),
						e));
				log.fatal(processInfo + ",ɾ�����̶�����˲���,δ֪�쳣" + e.toString());
				return ExecuteResult.fail;
			}
		}
		return ExecuteResult.sucess;
	}
}
