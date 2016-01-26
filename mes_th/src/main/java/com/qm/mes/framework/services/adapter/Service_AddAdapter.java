package com.qm.mes.framework.services.adapter;

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
 * ������������� ������������Ҫ��otherparameter�з���һ��Connection������Ϊcon��<br>
 * ��Ҫ���û�������������к���processid,i_serveralias,i_parameter,source,o_serveralias��o_parameterֵ����ʹ�ǿ��ַ�����
 * <br>
 * ������ִ�� doService() <br>
 * ��������ʼ�� initFordo() <br>
 * ���������� undoService()<br>
 * ����������
 * 
 * @author ������ 2007-12-25
 */
public class Service_AddAdapter extends DefService implements IService {

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

	public Service_AddAdapter() {
		super();
	}

	// ����Log4j��־
	private final Log log = LogFactory.getLog(Service_AddAdapter.class);

	public synchronized ExecuteResult doService(IMessage message,
			String soa_processid) {
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		// ��ʼ���������в�������ʧ�ܣ�����ִ�н��
		if (!initFordo(message, soa_processid)) {
			log.error(processInfo + ",���������,��ʼ���������в���ʧ��");
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
					log.error(processInfo + ",���ݿ�������ʹ���,δ֪�����ݿ�����");
					return ExecuteResult.fail;
				}
				log.debug(processInfo + ",���ݿ���سɹ�");
				stmt = con.createStatement();
				String sql_check = dao.getSQL_QueryCountAdeptInfo(processid,
						i_serveralias, i_parameter);
				log.debug("�������̺�,����������,�������,����������Ϣ���л�ȡ��¼����sql���: sql_check = "
						+ sql_check);
				rs = stmt.executeQuery(sql_check);
				int count = 0;
				if (rs.next()) {
					count = rs.getInt(1);
				}
				if (count > 0) // ��Ϣ�ظ�
				{
					message.addServiceException(new ServiceException(
							ServiceExceptionType.UNKNOWN, "����������Ϣ�Ѵ���!", this
									.getId(), soa_processid, new Date(), null));
					log.error(processInfo + ",����������Ϣ�Ѿ����� count = " + count);
					return ExecuteResult.fail;
				}
				Map<String, String> data = new HashMap<String, String>();
				data.put("map_processid", processid);
				data.put("map_i_serveralias", i_serveralias);
				data.put("map_i_parameter", i_parameter);
				message.setOtherParameter(this.getClass().getName(), data);
				String sql_insert = dao.getSQL_InsertAdapterInfo(processid,
						i_serveralias, i_parameter, source, o_serveralias,
						o_parameter);
				log.debug("�����������Ϣ��sql���: sql_insert = " + sql_insert);
				stmt.executeUpdate(sql_insert);

			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), soa_processid, new Date(), sqle));
				log.fatal(processInfo + ",���������_���ݿ�����쳣:" + sqle.toString());
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
			log.fatal(processInfo + ",���������ʱ����δ֪�쳣" + e.toString());
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
		String debug_Service_AddAdapter = "���̺�: processid = " + processid
				+ "\n" + "����˷������: i_serveralias = " + i_serveralias + "\n"
				+ "����˵Ĳ�����: i_parameter = " + i_parameter + "\n"
				+ "������Դ: source = " + source + "\n"
				+ "����˵ķ������: o_serveralias = " + o_serveralias + "\n"
				+ "����˵Ĳ�����: o_parameter = " + o_parameter + "\n";

		log.debug(processInfo + ",���������ʱ�û��ύ�Ĳ���: " + debug_Service_AddAdapter);

		if (processid == null || i_serveralias == null || i_parameter == null
				|| source == null || o_serveralias == null
				|| o_parameter == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "ȱ���������", this.getId(),
					soa_processid, new java.util.Date(), null));
			log.error(processInfo + ",���������������,ȱ���������");
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
			String debug_map = "map_processid = " + map_processid + "\n"
					+ "map_i_serveralias = " + map_i_serveralias + "\n"
					+ "map_i_parameter = " + map_i_parameter;
			log.debug(processInfo + "������������˲������յ���ֵ: debug_map = " + debug_map);
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
						log.error(processInfo + ",������������˲���ʱ,���ݿ���ش���,δ֪�����ݿ�����");
						return ExecuteResult.fail;
					}
					log.debug(processInfo + ",������������˲���ʱ,���ݿ���سɹ�" + "\n");
					String undo_sql_delete = dao.getSQL_DeleteAdapterInfo(
							map_processid, map_i_serveralias, map_i_parameter);
					log.debug(processInfo + " , " + this.getClass().getName()
							+ "������������˲�����sql���: undo_sql_delete  = "
							+ undo_sql_delete);
					stmt = con.createStatement();
					stmt.executeUpdate(undo_sql_delete);
				} catch (SQLException sqle) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.DATABASEERROR, "���ݿ����" + sqle,
							this.getId(), soa_processid, new Date(), sqle));
					log.fatal(processInfo + ",������������˲���ʱ,���ݿ��쳣"
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
