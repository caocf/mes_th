package mes.framework.services.adapter;

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
import mes.framework.MessageAdapterFactory;
import mes.framework.ProcessFactory;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import mes.framework.dao.DAOFactory_Core;
import mes.framework.dao.IDAO_Core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ���񣺸��������� ������������Ҫ��otherparameter�з���һ��Connection������Ϊcon��<br>
 * ��Ҫ���û�������������к���processid,i_serveralias,i_parameter,source,o_serveralias��o_parameterֵ����ʹ�ǿ��ַ�����
 * <br>
 * ������ִ�� doService() <br>
 * ��������ʼ�� initFordo() <br>
 * ���������� undoService()<br>
 * ����������
 * 
 * @author ������ 2007-12-25
 */
public class Service_UpdateAdapter extends DefService implements IService {

	// ���ݿ�����
	private Connection con = null;

	// ���̺�
	private String processid = null;

	// ����˷������
	private String i_serveralias = null;

	// ����˵Ĳ�����
	private String i_parameter = null;

	// ������Դ
	private String source = null;

	// ����˵ķ������
	private String o_serveralias = null;

	// ����˵Ĳ�����
	private String o_parameter = null;

	public Service_UpdateAdapter() {
		super();
	}

	// ����log4j��־
	private final Log log = LogFactory.getLog(Service_UpdateAdapter.class);

	public ExecuteResult doService(IMessage message, String soa_processid) {
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		// ��ʼ���������в�������ʧ�ܣ�����ִ�н��
		if (!initFordo(message, soa_processid)) {
			log.error(processInfo + ",�޸�������,��ʼ���������в���ʧ��");
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
					log.error(processInfo + ",�޸���������,���ݿ�������ʹ���,δ֪�����ݿ�����");
					return ExecuteResult.fail;
				}
				log.debug(processInfo + ",�޸���������,���ݿ���سɹ�");
				stmt = con.createStatement();

				Map<String, String> data = new HashMap<String, String>();
				String map_processid = null;
				String map_i_serveralias = null;
				String map_i_parameter = null;
				String map_source = null;
				String map_o_serveralias = null;
				String map_o_parameter = null;
				String data_sql_select = dao.getSQL_QueryAdepterInfo(processid);
				log.debug(processInfo + "��ѯ�����̺ŵ�������Ϣ��sql���: data_sql_select = "
						+ data_sql_select);
				rs = stmt.executeQuery(data_sql_select);
				while (rs.next()) {
					map_processid = rs.getString(1);
					map_i_serveralias = rs.getString(2);
					map_i_parameter = rs.getString(3);
					map_source = rs.getString(4);
					map_o_serveralias = rs.getString(5);
					map_o_parameter = rs.getString(6);
				}
				String debug_result_map = "select_map_processid = "
						+ map_processid + "\n" + "select_map_i_serveralias = "
						+ map_i_serveralias + "\n"
						+ "select_map_i_parameter = " + map_i_parameter + "\n"
						+ "select_map_source = " + map_source + "\n"
						+ "select_map_o_serveralias = " + map_o_serveralias
						+ "\n" + "select_map_o_parameter = " + map_o_parameter;
				log.debug(processInfo + " , " + this.getClass().getName()
						+ "��ѯ�����̺ŵ�������Ϣ��: debug_result_map = "
						+ debug_result_map);
				data.put("map_processid", map_processid);
				data.put("map_i_serveralias", map_i_serveralias);
				data.put("map_i_parameter", map_i_parameter);
				data.put("map_source", map_source);
				data.put("map_o_serveralias", map_o_serveralias);
				data.put("map_o_parameter", map_o_parameter);
				message.setOtherParameter(this.getClass().getName(), data);
				/*
				 * �޸�������
				 */
				String sql_update = dao.getSQL_UpdateAdapterInfo(processid,
						i_serveralias, i_parameter, source, o_serveralias,
						o_parameter);
				log.debug("�������̺�,����������,���������,����������Դ��,����������,�����������sql���: "
						+ "sql_update = " + sql_update);
				stmt.executeUpdate(sql_update);
				/**
				 * ������������
				 */
				MessageAdapterFactory.loadAllMessageAdapter(con);
			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), soa_processid, new Date(), sqle));
				log.fatal(processInfo + ",�޸�������_���ݿ�����쳣:" + sqle.toString());
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
			log.fatal(processInfo + ",�޸�������ʱ����δ֪�쳣" + e.toString());
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
	}

	private boolean initFordo(IMessage message, String soa_processid) {
		// ���ݿ�����
		con = null;
		// ���̺�
		processid = null;
		// ����˷������
		i_serveralias = null;
		// ����˵Ĳ�����
		i_parameter = null;
		// ������Դ
		source = null;
		// ����˵ķ������
		o_serveralias = null;
		// ����˵Ĳ�����
		o_parameter = null;

		// ��ȡ�÷�������������ֵ
		con = (Connection) message.getOtherParameter("con");

		processid = message.getUserParameterValue("processid");
		i_serveralias = message.getUserParameterValue("i_serveralias");
		i_parameter = message.getUserParameterValue("i_parameter");
		source = message.getUserParameterValue("source");
		o_serveralias = message.getUserParameterValue("o_serveralias");
		o_parameter = message.getUserParameterValue("o_parameter");
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		String debug_Service_UpdateAdapter = "���̺�: processid = " + processid
				+ "\n\r" + "����˷������: i_serveralias = " + i_serveralias + "\n\r"
				+ "����˵Ĳ�����: i_parameter = " + i_parameter + "\n\r"
				+ "������Դ: source = " + source + "\n\r"
				+ "����˵ķ������: o_serveralias = " + o_serveralias + "\n\r"
				+ "����˵Ĳ�����: o_parameter = " + o_parameter + "\n\r"
				+ "���ݿ�����: con = " + con + "\n\r";

		log
				.debug(processInfo + ",�޸�������: " + debug_Service_UpdateAdapter
						+ "\n");

		if (processid == null || i_serveralias == null || i_parameter == null
				|| source == null || o_serveralias == null
				|| o_parameter == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "ȱ���������", this.getId(),
					soa_processid, new java.util.Date(), null));
			log.error(processInfo + ",�޸�������������,ȱ���������" + "\n");

			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ExecuteResult undoService(IMessage message, String soa_processid) {
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		Object obj = message.getOtherParameter(this.getClass().getName());
		if (obj instanceof Map) {
			Map<String, String> map = (Map<String, String>) obj;
			String map_processid = map.get("map_processid");
			String map_i_serveralias = map.get("map_i_serveralias");
			String map_i_parameter = map.get("map_i_parameter");
			String map_source = map.get("map_source");
			String map_o_serveralias = map.get("map_o_serveralias");
			String map_o_parameter = map.get("map_o_parameter");
			String debug_map = "map_processid = " + map_processid + "\n"
					+ "map_i_serveralias = " + map_i_serveralias + "\n"
					+ "map_i_parameter = " + map_i_parameter + "\n"
					+ "map_source = " + map_source + "\n"
					+ "map_o_serveralias = " + map_o_serveralias + "\n"
					+ "map_o_parameter = " + map_o_parameter;
			log.debug(processInfo + " , " + this.getClass().getName()
					+ "�޸��������л��˲���Map���յ���ֵ: debug_map = " + debug_map);
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
										+ ",�޸����������˲���ʱ,���ݿ���ش���,δ֪�����ݿ�����");
						return ExecuteResult.fail;
					}
					log.debug(processInfo + ",�޸����̷�����˲���,���ݿ���سɹ�");
					stmt = con.createStatement();
					String undo_sql_update = dao.getSQL_UpdateAdapterInfo(
							map_processid, map_i_serveralias, map_i_parameter,
							map_source, map_o_serveralias,map_o_parameter);
					log.debug(processInfo + " , " + this.getClass().getName()
							+ "�޸����������˲�����sql���: undo_sql_update = "
							+ undo_sql_update);
					stmt.executeUpdate(undo_sql_update);
				} catch (SQLException sqle) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.DATABASEERROR, "���ݿ����" + sqle,
							this.getId(), soa_processid, new Date(), sqle));
					log.fatal(processInfo + ",�޸����������˲���,���ݿ��쳣"
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
				log.fatal(processInfo + ",�޸����������˲���,δ֪�쳣" + e.toString());
				return ExecuteResult.fail;
			}
		}
		return ExecuteResult.sucess;
	}
}
