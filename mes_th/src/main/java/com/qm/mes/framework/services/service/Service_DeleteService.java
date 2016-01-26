package com.qm.mes.framework.services.service;

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
 * ����ɾ����������Ϣ ������������Ҫ��otherparameter�з���һ��Connection������Ϊcon��<br>
 * ��Ҫ���û�������������к���serviceid. <br>
 * ����ִ�� doService() <br>
 * �����ʼ�� initFordo() <br>
 * ������� undoService() �����ɾ���������ֲ�ͨ��ع�<br>
 * ����������
 * 
 * @author ������ 2007-12-20
 */
public class Service_DeleteService extends DefService implements IService {

	// ���ݿ�����
	private Connection con = null;

	// �����
	private String serviceid = null;

	public Service_DeleteService() {
		super();
	}

	private final Log log = LogFactory.getLog(Service_DeleteService.class);

	public ExecuteResult doService(IMessage message, String soa_processid) {
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();

		// ��ʼ���������в�������ʧ�ܣ�����ִ�н��
		if (!initFordo(message, soa_processid)) {
			log.error(processInfo + " , ɾ������ʱ,��ʼ����������ʧ��!");
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
							ServiceExceptionType.UNKNOWNDATABASETYPE,
							"δ֪�����ݿ�����", this.getId(), soa_processid,
							new Date(), null));
					log.error(processInfo + " , ɾ������ʱ,�������ݿ����,δ֪�����ݿ�����");
					return ExecuteResult.fail;
				}
				log.debug(processInfo + " , ɾ������,�������ݿ�ɹ�");
				stmt = con.createStatement();
				int sid = Integer.parseInt(serviceid);
				int count = 0;
				// �鿴Ҫɾ���ķ����Ƿ��ѱ�ĳһ����Ӧ�ã���Ӧ�ã�����ɾ���÷���
				String sql_check = dao.getSQL_QueryCountServiceIsUsed(sid);
			
				log.debug("�鿴Ҫɾ���ķ����Ƿ��ѱ�ĳһ����Ӧ�ã���Ӧ�ã�����ɾ���÷����sql���: sql_check = "
						+ sql_check);
				rs = stmt.executeQuery(sql_check);
				if (rs.next()) {
					count = rs.getInt(1);
				}
				if (count > 0)// �����ѱ�����ʹ��
				{
					message.addServiceException(new ServiceException(
							ServiceExceptionType.UNKNOWN, " �÷����ѱ�ĳ����ʹ�ã�����ɾ��!",
							this.getId(), soa_processid, new Date(), null));
					log.error(processInfo
							+ " , ɾ������ʱ,�÷����ѱ�ĳ����ʹ��,����ɾ��! count = " + count);
					return ExecuteResult.fail;
				}
				// ��ѯ�˷���ŵ�������Ϣ,���뵽Map��
				Map<String, String> data = new HashMap<String, String>();
				int map_serviceid = 0;
				String map_cservername = null;
				String map_cclassname = null;
				String map_cdescription = null;
				int map_nnamespaceid = 0;
				String sql_selectService = dao.getSQL_QueryAllServices(String
						.valueOf(sid), "ById");
				log.debug("��ѯ�˷���ŵ�������Ϣ,���뵽Map�е�sql���: sql_selectService = "
						+ sql_selectService);
				result = stmt.executeQuery(sql_selectService);
				while (result.next()) {
					map_serviceid = result.getInt(1);
					map_cservername = result.getString(2);
					map_cclassname = result.getString(3);
					map_cdescription = result.getString(4);
					map_nnamespaceid = result.getInt(5);
				}
				String debug_map = "result_map_serviceid = " + map_serviceid
						+ "\n" + "result_map_cservername = " + map_cservername
						+ "\n" + "result_map_cclassname = " + map_cclassname
						+ "\n" + "reslut_map_cdescription = "
						+ map_cdescription + "\n"
						+ "result_map_nnamespaceid = " + map_nnamespaceid
						+ "\n";
				log.debug(processInfo + " , " + this.getClass().getName()
						+ " ,���˲���Map��ֵ:    debug_map = " + debug_map);
				data.put("map_serviceid", String.valueOf(map_serviceid));
				data.put("map_cservername", map_cservername);
				data.put("map_cclassname", map_cclassname);
				data.put("map_cdescription", map_cdescription);
				data.put("map_nnamespaceid", String.valueOf(map_nnamespaceid));
				message.setOtherParameter(this.getClass().getName(), data);
				// ɾ���������
				String sql_del = dao.getSQL_DeleteService(sid);
				log.debug(" ɾ�������sql���: sql_del = " + sql_del);
				stmt.executeUpdate(sql_del);
			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), soa_processid, new Date(), sqle));
				log.fatal(processInfo + " , ɾ������,���ݿ�����쳣" + sqle.toString());
				return ExecuteResult.fail;
			} finally {
				if(result!=null)
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
			log.fatal(processInfo + " , ɾ������,δ֪�쳣" + e.toString());
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
	}

	private boolean initFordo(IMessage message, String soa_processid) {
		// ���ݿ�����
		con = null;
		// ���̺�
		serviceid = null;

		// ��ȡ�÷�������������ֵ
		con = (Connection) message.getOtherParameter("con");
		serviceid = message.getUserParameterValue("serviceid");

		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();

		String debug_Service_DeleteService = "�û��ύ�Ĳ���: ���̺�: serviceid = "
				+ serviceid + "\n";
		log.debug(processInfo + " , ɾ������ " + debug_Service_DeleteService);

		if (serviceid == null || con == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "ȱ���������", this.getId(),
					soa_processid, new java.util.Date(), null));
			log.error(processInfo + " , ɾ������,ȱ���������");
			return false;
		}
		return true;
	}

	//
	@SuppressWarnings("unchecked")
	public ExecuteResult undoService(IMessage message, String soa_processid) {
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		// message��Object����
		Object obj = message.getOtherParameter(this.getClass().getName());
		Statement stmt = null;
		// ���Map�е�key��ObjectΪtrue
		log.trace(obj);
		if (obj instanceof Map) {
			Map<String, String> map = (Map<String, String>) obj;
			String map_serviceid = (String) map.get("map_serviceid");
			String map_cservername = (String) map.get("map_cservername");
			String map_cclassname = (String) map.get("map_cclassname");
			String map_cdescription = (String) map.get("map_cdescription");
			String map_nnamespaceid = (String) map.get("map_nnamespaceid");
			String debug_map = "map_serviceid = " + map_serviceid + "\n"
					+ "map_cservername = " + map_cservername + "\n"
					+ "map_cclassname = " + map_cclassname + "\n"
					+ "map_cdescription = " + map_cdescription + "\n"
					+ "map_nnamespaceid = " + map_nnamespaceid + "\n";
			log.debug(processInfo + " , " + this.getClass().getName()
					+ " ,ɾ������Ļ��˲���Map��ֵ:    debug_map = " + debug_map);
			try {
				try {
					IDAO_Core dao = DAOFactory_Core.getInstance(DataBaseType
							.getDataBaseType(con));
					if (dao == null) {
						message.addServiceException(new ServiceException(
								ServiceExceptionType.UNKNOWNDATABASETYPE,
								"δ֪�����ݿ�����", this.getId(), soa_processid,
								new Date(), null));
						log.error(processInfo + " , "
								+ this.getClass().getName()
								+ " ,���˲���,�������ݿ����,δ֪�����ݿ�����");
						return ExecuteResult.fail;
					}
					log.debug(processInfo + " , " + this.getClass().getName()
							+ " ,���˲������ݿ���سɹ�");
					// ��ɾ���ķ�����������ӽ���
					String sql_insert = dao.getSQL_InsertService(Integer
							.parseInt(map_serviceid), map_cservername,
							map_cclassname, map_cdescription, map_nnamespaceid);
					log.debug(processInfo + " , " + this.getClass().getName()
							+ " ,���˲�����sql���:  undo_sql_insert = " + sql_insert);
					stmt = con.createStatement();
					stmt.executeUpdate(sql_insert);
				} catch (SQLException sqle) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
									.getId(), soa_processid, new Date(), sqle));
					log.fatal(processInfo + " , " + this.getClass().getName()
							+ " ,���˲������ݿ�����쳣" + sqle.toString());
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
				log.fatal(processInfo + " , " + this.getClass().getName()
						+ " ,���˲������ݿ�����쳣" + e.toString());
				return ExecuteResult.fail;
			}
		}
		return ExecuteResult.sucess;
	}
}
