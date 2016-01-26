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
 * ������ӷ�������Ϣ ������������Ҫ��otherparameter�з���һ��Connection������Ϊcon��<br>
 * ��Ҫ���û�������������к���id,namespace,descֵ����ʹ�ǿ��ַ����� <br>
 * ִ�� doService() <br>
 * ��ʼ�� initFordo() <br>
 * ���� undoService() <br>
 * ����������
 * 
 * @author ������ 2008-01-02
 */
public class Service_InsertNameSpace extends DefService implements IService {

	// ���ݿ�����
	private Connection con = null;

	// �����ռ�����
	private String namespace = null;

	// ����
	private String desc = null;

	public Service_InsertNameSpace() {
		super();
	}

	private final Log log = LogFactory.getLog(Service_InsertNameSpace.class);

	public synchronized ExecuteResult doService(IMessage message,
			String soa_processid) {
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		// ��ʼ���������в�������ʧ�ܣ�����ִ�н��
		if (!initFordo(message, soa_processid)) {
			log.error(processInfo + ",��������ռ�,��ʼ����������ʧ��!");
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
					log.error(processInfo + ",��������ռ�,�������ݿ����,δ֪�����ݿ�����");
					return ExecuteResult.fail;
				}
				log.debug(processInfo + ",��������ռ�,�������ݿ�ɹ�");
				stmt = con.createStatement();
				// ���������ռ��
				String sql_create = dao.getSQL_QueryNextNameSpaceId();
				log.debug("���������ռ�ŵ�sql���: sql_create = " + sql_create);
				rs = stmt.executeQuery(sql_create);
				int id = 0;
				if (rs.next()) {
					id = rs.getInt(1);
				}
				// �������ռ�ŷ��뵽Map��
				Map<String, String> data = new HashMap<String, String>();
				data.put("map_id", String.valueOf(id));
				message.setOtherParameter(this.getClass().getName(), data);
				/*
				 * ��������ռ�
				 */
				String sql_insert = dao.getSQL_InsertNameSpace(id, namespace,
						desc);
				log.debug("��������ռ��sql���: sql_insert = " + sql_insert);
				stmt.executeUpdate(sql_insert);

			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), soa_processid, new Date(), sqle));
				log.fatal(processInfo + ",��������ռ�ʱ,���ݿ�����쳣" + sqle.toString());
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
			log.fatal(processInfo + ",��������ռ�ʱ,δ֪�쳣" + e.toString());
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
	}

	@SuppressWarnings("unchecked")
	public ExecuteResult undoService(IMessage message, String soa_processid) {
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		Object obj = message.getOtherParameter(this.getClass().getName());
		if (obj instanceof Map) {
			Map<String, String> map = (Map<String, String>) obj;
			int map_id = Integer.parseInt(map.get("map_id"));
			log.debug(processInfo + " , ��������ռ�Map��ȡ��: map_id = " + map_id);
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
						log.error(processInfo + ",��������ռ���˲���,�������ݿ����,δ֪�����ݿ�����");
						return ExecuteResult.fail;
					}
					log.debug(processInfo + ",��������ռ���˲���,�������ݿ�ɹ�");
					stmt = con.createStatement();
					String sql_delete = dao.getSQL_DeleteNameSpace(map_id);
					log.debug(processInfo + " ,��������ռ���˲�����sql���: sql_delete = "
							+ sql_delete);
					stmt.executeUpdate(sql_delete);
				} catch (SQLException sqle) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.DATABASEERROR,
							"��������ռ���˲������ݿ��쳣", this.getId(), soa_processid,
							new Date(), sqle));
					log.fatal(processInfo + ",��������ռ���˲���ʱ,���ݿ�����쳣"
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
				log.fatal(processInfo + ",��������ռ���˲���ʱ,δ֪�쳣" + e.toString());
				return ExecuteResult.fail;
			}
		}
		return ExecuteResult.sucess;
	}

	private boolean initFordo(IMessage message, String soa_processid) {
		// ���ݿ�����
		con = null;
		// �����ռ�����
		namespace = null;
		// ����
		desc = null;

		// ��ȡ�÷�������������ֵ
		con = (Connection) message.getOtherParameter("con");
		namespace = message.getUserParameterValue("namespace");
		desc = message.getUserParameterValue("desc");
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();

		String debug_Service_InsertNameSpace = "�����ռ�����: namespace = "
				+ namespace + "\n" + "����: desc = " + desc + "\n";
		log.debug(processInfo + ",��������ռ�ʱ�û��ύ�Ĳ���: "
				+ debug_Service_InsertNameSpace);

		if (namespace == null || desc == null || con == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "ȱ���������", this.getId(),
					soa_processid, new java.util.Date(), null));
			log.error(processInfo + ",��������ռ�ʱ,ȱ���������");
			return false;
		}
		return true;
	}
}
