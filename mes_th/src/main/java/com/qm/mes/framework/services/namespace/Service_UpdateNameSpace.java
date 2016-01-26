package com.qm.mes.framework.services.namespace;

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
 * ���񣺸��������ռ���Ϣ ������������Ҫ��otherparameter�з���һ��Connection������Ϊcon��<br>
 * ��Ҫ���û�������������к���namespace,descֵ����ʹ�ǿ��ַ����� <br>
 * ִ�� doService() <br>
 * ��ʼ�� initFordo() <br>
 * ���� undoService() <br>
 * ����������
 * 
 * @author ������ 2008-01-02
 */
public class Service_UpdateNameSpace extends DefService implements IService {

	// ���ݿ�����
	private Connection con = null;

	// �����ռ��
	private String id = null;

	// �������ռ�
	private String namespace = null;

	// ����
	private String desc = null;

	public Service_UpdateNameSpace() {
		super();
	}

	private final Log log = LogFactory.getLog(Service_UpdateNameSpace.class);

	public ExecuteResult doService(IMessage message, String soa_processid) {
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		// ��ʼ���������в�������ʧ�ܣ�����ִ�н��
		if (!initFordo(message, soa_processid)) {
			log.error(processInfo + ",�޸������ռ�,��ʼ����������ʧ��");
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
					log.error(processInfo + ",�޸������ռ�,�������ݿ����,δ֪�����ݿ�����");
					return ExecuteResult.fail;
				}
				log.debug(processInfo + ",�޸������ռ�,�������ݿ�ɹ�");
				// ��֤�����ռ������Ƿ����
				int nid = Integer.parseInt(id);
				String sql_check = dao.getSQL_QueryCountNameSpaceForNameSpace(
						nid, namespace);
				log.debug("��֤�����ռ������Ƿ���ڵ�sql���: sql_check = " + sql_check);
				stmt = con.createStatement();
				rs = stmt.executeQuery(sql_check);

				int count = 0;
				if (rs.next()) {
					count = rs.getInt(1);
				}
				if (count > 0)// �����ռ����Ѵ���
				{
					message.addServiceException(new ServiceException(
							ServiceExceptionType.UNKNOWN,
							"�������ռ����Ѵ��ڣ��������µ������ռ�����!", this.getId(),
							soa_processid, new Date(), null));
					log.error(processInfo
							+ ",�޸������ռ�ʱ,�������ռ����Ѵ��ڣ��������µ������ռ�����  count = "
							+ count);
					return ExecuteResult.fail;
				}
				// ��ѯ�������ռ�ŵ�������Ϣ,���뵽Map��
				Map<String, String> data = new HashMap<String, String>();
				String map_cnamespace = null;
				String map_cdescription = null;
				String map_sql_select = dao
						.getSQL_QueryNameSpaceForNameSpace(nid);
				log.debug(processInfo
						+ ",ɾ�������ռ�,��ѯ�������ռ�ŵ�������Ϣ��sql���: map_sql_select = "
						+ map_sql_select);
				rs = stmt.executeQuery(map_sql_select);
				while (rs.next()) {
					map_cnamespace = rs.getString(1);
					map_cdescription = rs.getString(2);
				}
				String rs_debug = "map_cnamespace = " + map_cnamespace + "\n"
						+ "map_cdescription = " + map_cdescription;
				log.debug(processInfo + "ɾ���������˲����������: rs_debug = " + rs_debug);
				data.put("map_id", String.valueOf(nid));
				data.put("map_cnamespace", map_cnamespace);
				data.put("map_cdescription", map_cdescription);
				message.setOtherParameter(this.getClass().getName(), data);
				/*
				 * �޸������ռ�
				 */
				String sql_update = dao.getSQL_UpdateNameSpace(nid, namespace,
						desc);
				log.debug("���������ռ��sql���: sql_update = " + sql_update);
				stmt.executeUpdate(sql_update);

			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), soa_processid, new Date(), sqle));
				log.fatal(processInfo + ",�޸������ռ�,���ݿ�����쳣" + sqle.toString());
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
			log.fatal(processInfo + ",�޸������ռ�,δ֪�쳣" + e.toString());
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ExecuteResult undoService(IMessage message, String soa_processid) {
		IProcess process=ProcessFactory.getInstance(soa_processid);
		String processInfo=process.getNameSpace()+"."+process.getName();
		Object obj = message.getOtherParameter(this.getClass().getName());
		if (obj instanceof Map) {
			Map<String, String> map = (Map<String, String>) obj;
			int map_id = Integer.parseInt(map.get("map_id"));
			String map_cnamespace = map.get("map_cnamespace");
			String map_cdescription = map.get("map_cdescription");
			String map_debug = "map_id = " + map_id + "\n"
					+ "map_cnamespace = " + map_cnamespace + "\n"
					+ "map_cdescription = " + map_cdescription;
			log.debug(processInfo + "�޸������ռ���˲���Map��ȡ��ֵ: map_debug = "
					+ map_debug);
			try {
				Statement stmt = null;
				try {
					IDAO_Core dao = DAOFactory_Core.getInstance(DataBaseType
							.getDataBaseType(con));
					if (dao == null) {
						message.addServiceException(new ServiceException(
								ServiceExceptionType.UNKNOWNDATABASETYPE,
								"δ֪�����ݿ�����", this.getId(), soa_processid,
								new Date(), null));
						log.error(processInfo + ",�޸������ռ���˲���,�������ݿ����,δ֪�����ݿ�����");
						return ExecuteResult.fail;
					}
					log.debug(processInfo + ",�޸������ռ���˲���,�������ݿ�ɹ�");
					stmt = con.createStatement();
					String sql_update = dao.getSQL_UpdateNameSpace(map_id,
							map_cnamespace, map_cdescription);
					log.debug(processInfo
							+ "�޸������ռ���˲�����sql���: map_sql_update = "
							+ sql_update);
					stmt.executeUpdate(sql_update);
				} catch (SQLException sqle) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
									.getId(), soa_processid, new Date(), sqle));
					log.fatal(processInfo + ",�޸������ռ���˲���,���ݿ�����쳣"
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
				log.fatal(processInfo + ",�޸������ռ���˲���,δ֪�쳣" + e.toString());
				return ExecuteResult.fail;
			}
		}
		return ExecuteResult.sucess;
	}

	private boolean initFordo(IMessage message, String soa_processid) {
		// ���ݿ�����
		con = null;
		// �����ռ��
		id = null;
		// �������ռ�
		namespace = null;
		// ����
		desc = null;

		// ��ȡ�÷�������������ֵ
		con = (Connection) message.getOtherParameter("con");
		id = message.getUserParameterValue("id");
		namespace = message.getUserParameterValue("namespace");
		desc = message.getUserParameterValue("desc");
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		String debug_UpdateNameSpace = "�����ռ��: id = " + id + "\n"
				+ "�������ռ�: namespace = " + namespace + "\n" + "����: desc = "
				+ desc + "\n";
		log.debug(processInfo + ",�޸������ռ�ʱ�û��ύ�Ĳ���: " + debug_UpdateNameSpace);

		if (id == null || namespace == null || desc == null || con == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "ȱ���������", this.getId(),
					soa_processid, new java.util.Date(), null));
			log.error(processInfo + ",�޸������ռ�,ȱ���������");
			return false;
		}
		return true;
	}
}
