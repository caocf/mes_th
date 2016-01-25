package mes.framework.services.process;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * �޸����̶��� ������������Ҫ��otherparameter�з���һ��Connection������Ϊcon��<br>
 * ��Ҫ���û������к���processid,processname,description,namespaceֵ����ʹ�ǿ��ַ����� <br>
 * ���̶���ִ�� doService() <br>
 * ���̶����ʼ�� initFordo() <br>
 * ���̶������ undoService() <br>
 * ����������
 * 
 * @author ������ 2007-12-25
 * 
 */
public class Service_UpdateProcessStatement extends DefService implements
		IService {

	private Connection con = null;

	private String processid = null;

	private String processname = null;

	private String description = null;

	private String namespace = null;

	public Service_UpdateProcessStatement() {
		super();
	}

	private final Log log = LogFactory
			.getLog(Service_UpdateProcessStatement.class);

	public ExecuteResult doService(IMessage message, String soa_processid) {
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		if (!initFordo(message, soa_processid)) {
			log.error(processInfo + ",�޸����̶����ʼ������ʧ��");
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
					log.error(processInfo + ",�޸����̶���,���ݿ���ش���,δ֪�����ݿ�����");
					return ExecuteResult.fail;
				}
				log.debug(processInfo + ",�޸����̶���,���ݿ���سɹ�");
				stmt = con.createStatement();
				// String
				// sql_check=dao.getSQL_CheckUniqueProcessStatement(processid);
				// rs=stmt.executeQuery(sql_check);
				// int count=0;
				// if(rs.next())
				// {
				// count=rs.getInt(1);
				// }
				// if(count>0)
				// {
				// message.addServiceException(new ServiceException(
				// ServiceExceptionType.UNKNOWN, "",
				// this.getId(), soa_processid, new Date(), null));
				// return ExecuteResult.fail;
				// }
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
						+ map_processid + "\n" + "select_map_processname = "
						+ map_processname + "\n" + "select_map_description = "
						+ map_description + "\n" + "select_map_namespace = "
						+ map_namespace;
				log.debug(processInfo + " , " + this.getClass().getName()
						+ "��ѯ�����̺ŵ�������Ϣ��: debug_result_map = "
						+ debug_result_map);
				data.put("map_processid", map_processid);
				data.put("map_processname", map_processname);
				data.put("map_description", map_description);
				data.put("map_namespace", map_namespace);
				message.setOtherParameter(this.getClass().getName(), data);
				/*
				 * �޸����̶���
				 */
				String sql_update = dao.getSQL_UpdateProcessStatement(
						processid, processname, description, namespace);
				log.debug("�������̶�����sql���: sql_update = " + sql_update);
				stmt.executeUpdate(sql_update);
			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ����", this
								.getId(), soa_processid, new Date(), sqle));
				log.fatal(processInfo + ",�޸����̶���,���ݿ��쳣" + sqle.toString());
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
			log.fatal(processInfo + ",�޸����̶���,δ֪�쳣" + e.toString());
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
		namespace = namespace == null ? "" : namespace;
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		String debug_UpdateProcessStatement = "processid = " + processid + "\n"
				+ "processname = " + processname + "\n" + "description = "
				+ description + "\n" + "namespace = " + namespace + "\n";
		log.debug(processInfo + ",�޸����̶���ʱ�û��ύ�Ĳ���: "
				+ debug_UpdateProcessStatement);

		if (processid == null || processname == null || description == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "����Ϊ��", this.getId(),
					soa_processid, new java.util.Date(), null));
			log.error(processInfo + ",�޸����̶���,ȱ���������");
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
										+ ",�޸����̶�����˲���ʱ,���ݿ���ش���,δ֪�����ݿ�����");
						return ExecuteResult.fail;
					}
					log.debug(processInfo + ",�޸����̶�����˲���,���ݿ���سɹ�");
					st = con.createStatement();
					String undo_sql_update = dao.getSQL_UpdateProcessStatement(
							map_processid, map_processname, map_description,
							map_namespace);
					log.debug(processInfo + " , " + this.getClass().getName()
							+ "�޸����̶�����˲�����sql���: undo_sql_update = "
							+ undo_sql_update);
					st.executeUpdate(undo_sql_update);
				} catch (SQLException sqle) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.DATABASEERROR, "���ݿ����" + sqle,
							this.getId(), soa_processid, new Date(), sqle));
					log.fatal(processInfo + ",�޸����̶�����˲���,���ݿ��쳣"
							+ sqle.toString());
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
				log.fatal(processInfo + ",�޸����̶�����˲���,δ֪�쳣" + e.toString());
				return ExecuteResult.fail;
			}
		}
		return ExecuteResult.sucess;
	}
}
