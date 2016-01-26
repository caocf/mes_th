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
 * ������̶��� ������������Ҫ��otherparameter�з���һ��Connection������Ϊcon��<br>
 * ��Ҫ���û������к���processid,processname,description,namespaceֵ����ʹ�ǿ��ַ����� <br>
 * ���̶���ִ�� doService() <br>
 * ���̶����ʼ�� initFordo() <br>
 * ���̶������ undoService() �����̶�����Ӳ������ֲ�ͨ��ع�<br>
 * ����������
 * 
 * @author ������ 2007-12-25
 * 
 */
public class Service_AddProcessStatement extends DefService implements IService {

	private Connection con = null;

	private String processid = null;

	private String processname = null;

	private String description = null;

	private String namespace = null;

	public Service_AddProcessStatement() {
		super();
	}

	private final Log log = LogFactory
			.getLog(Service_AddProcessStatement.class);

	public synchronized ExecuteResult doService(IMessage message,
			String soa_processid) {
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		if (!initFordo(message, soa_processid)) {
			log.error(processInfo + ",������̶���ʱ,��ʼ������ʧ��");
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
					log.error(processInfo + ",������̶���ʱ,���ݿ���ش���,δ֪�����ݿ�����");
					return ExecuteResult.fail;
				}
				log.debug(processInfo + ",������̶���,���ݿ���سɹ�");
				stmt = con.createStatement();
				String sql_maxid = dao.getSQL_QueryNextProcessid();
				log.debug("�������̶�������������̺ŵ�sql���: sql_maxid = " + sql_maxid);
				rs = stmt.executeQuery(sql_maxid);
				processid = "0";
				if (rs.next()) {
					processid = String.valueOf(rs.getInt(1));
				}

				String sql_check = dao
						.getSQL_QueryCountProcessStatementForProcessid(processid);
				log.debug("������̶�����Ƿ����ظ����ݵ�sql���: sql_check = " + sql_check);
				rs = stmt.executeQuery(sql_check);
				int count = 0;//
				if (rs.next()) {
					count = rs.getInt(1);
				}
				if (count > 0) //
				{
					message.addServiceException(new ServiceException(
							ServiceExceptionType.UNKNOWN, "�����Ѿ�����", this
									.getId(), soa_processid, new Date(), null));
					log.error(processInfo + ",������̶���,�����Ѿ����� count = " + count);
					return ExecuteResult.fail;
				}
				// �������̺ŷ���Map��
				Map<String, String> data = new HashMap<String, String>();
				data.put("map_processid", processid);
				message.setOtherParameter(this.getClass().getName(), data);
				/*
				 * ������̶���
				 */
				String sql_insert = dao.getSQL_InsertProcessStatement(
						processid, processname, description, namespace);
				log.debug("�������̶����sql���: sql_insert = " + sql_insert);
				stmt.executeUpdate(sql_insert);

			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ��쳣" + sqle,
						this.getId(), soa_processid, new Date(), sqle));
				log.fatal(processInfo + ",������̶���ʱ,���ݿ��쳣" + sqle.toString());
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
			log.fatal(processInfo + ",������̶���ʱ,δ֪�쳣" + e.toString());
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
	}

	private boolean initFordo(IMessage message, String soa_processid) {
		con = null;
		// ���̺�
		processid = null;
		// ������
		processname = null;
		// ҵ������
		description = null;
		// �����ռ�
		namespace = null;
		con = (Connection) message.getOtherParameter("con");
		processid = message.getUserParameterValue("processid");
		processname = message.getUserParameterValue("processname");
		description = message.getUserParameterValue("description");
		namespace = message.getUserParameterValue("namespace");
		// namespace = namespace == null ? "" : namespace;

		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();

		String debug_AddProcessStatement = "processid = " + processid + "\n"
				+ "processname = " + processname + "\n" + "description = "
				+ description + "\n" + "namespace = " + namespace;
		log.debug(processInfo + ",������̶����û��ύ�Ĳ���: " + debug_AddProcessStatement);

		if (processid == null || processname == null || description == null
				|| namespace == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "", this.getId(),
					soa_processid, new java.util.Date(), null));
			log.error(processInfo + "������̶���ʱ,ȱ���������");
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
			String debug_map = "map_processid = " + map_processid;
			log.debug(processInfo + "������̶�����˲���Map���յ�ֵ: debug_map = "
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
						log.error(processInfo + "������̶�����˲������ݿ����ʧ��");
					}
					log.debug(processInfo + "������̶�����˲������ݿ���سɹ�");
					String undo_sql_delete = dao
							.getSQL_DeleteProcessStatement(map_processid);
					log.debug(processInfo + " , " + this.getClass().getName()
							+ "������̶�����˲�����sql���: undo_sql_delete = "
							+ undo_sql_delete);
					stmt = con.createStatement();
					stmt.executeUpdate(undo_sql_delete);
				} catch (SQLException sqle) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.DATABASEERROR, "���ݿ��쳣" + sqle,
							this.getId(), soa_processid, new Date(), sqle));
					log.fatal(processInfo + ",������̶������ʱ,���ݿ��쳣"
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
				log.fatal(processInfo + ",������̶������ʱ,δ֪�쳣" + e.toString());
				return ExecuteResult.fail;
			}
		}
		return ExecuteResult.sucess;
	}

}
