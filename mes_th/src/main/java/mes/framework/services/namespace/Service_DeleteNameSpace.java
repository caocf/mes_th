package mes.framework.services.namespace;

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
 * ����ɾ�������ռ䶨����Ϣ ������������Ҫ��otherparameter�з���һ��Connection������Ϊcon��<br>
 * ��Ҫ���û�������������к���namespace. <br>
 * ִ�� doService() <br>
 * ��ʼ�� initFordo() <br>
 * ���� undoService() <br>
 * ����������
 * 
 * @author ������ 2008-01-02
 */
public class Service_DeleteNameSpace extends DefService implements IService {

	// ���ݿ�����
	private Connection con = null;

	// �����ռ��
	private String id = null;

	public Service_DeleteNameSpace() {
		super();
	}

	private final Log log = LogFactory.getLog(Service_DeleteNameSpace.class);

	public ExecuteResult doService(IMessage message, String soa_processid) {
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		// ��ʼ���������в�������ʧ�ܣ�����ִ�н��
		if (!initFordo(message, soa_processid)) {
			log.error(processInfo + ",ɾ�������ռ�,��ʼ����������ʧ��!");
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
					log.error(processInfo + ",ɾ�������ռ�,�������ݿ����,δ֪�����ݿ�����");
					return ExecuteResult.fail;
				}
				log.debug(processInfo + ",ɾ�������ռ�,�������ݿ�ɹ�");
				int nid = Integer.parseInt(id);
				stmt = con.createStatement();
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
				 * ɾ�������ռ�
				 */
				String sql_del = dao.getSQL_DeleteNameSpace(nid);
				log.debug("ɾ�������ռ��sql���: sql_del = " + sql_del);
				stmt.executeUpdate(sql_del);

			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), soa_processid, new Date(), sqle));
				log.fatal(processInfo + ",ɾ�������ռ�,���ݿ�����쳣" + sqle.toString());
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
			log.fatal(processInfo + ",ɾ�������ռ�,δ֪�쳣" + e.toString());
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ExecuteResult undoService(IMessage message, String soa_processid) {
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		Object obj = message.getOtherParameter(this.getClass().getName());
		if (obj instanceof Map) {
			Map<String, String> map = (Map<String, String>) obj;
			int map_id = Integer.parseInt(map.get("map_id"));
			String map_cnamespace = map.get("map_cnamespace");
			String map_cdescription = map.get("map_cdescription");
			String map_debug = "map_id = " + map_id + "\n"
					+ "map_cnamespace = " + map_cnamespace + "\n"
					+ "map_cdescription = " + map_cdescription;
			log.debug(processInfo + "ɾ�������ռ���˲���Map��ȡ��ֵ: map_debug = "
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
						log.error(processInfo + ",ɾ�������ռ���˲���,�������ݿ����,δ֪�����ݿ�����");
						return ExecuteResult.fail;
					}
					log.debug(processInfo + ",ɾ�������ռ���˲���,�������ݿ�ɹ�");
					stmt = con.createStatement();
					String sql_insert = dao.getSQL_InsertNameSpace(map_id,
							map_cnamespace, map_cdescription);
					log.debug(processInfo
							+ "ɾ�������ռ���˲�����sql���: map_sql_insert = "
							+ sql_insert);
					stmt.executeUpdate(sql_insert);
				} catch (SQLException sqle) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
									.getId(), soa_processid, new Date(), sqle));
					log.fatal(processInfo + ",ɾ�������ռ���˲���,���ݿ�����쳣"
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
				log.fatal(processInfo + ",ɾ�������ռ���˲���,δ֪�쳣" + e.toString());
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

		// ��ȡ�÷�������������ֵ
		con = (Connection) message.getOtherParameter("con");
		id = message.getUserParameterValue("id");
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();

		String debug_Service_DeleteNameSpace = "�����ռ��: id = " + id + "\n";
		log.debug(processInfo + ",ɾ�������ռ�ʱ�û��ύ�Ĳ���:"
				+ debug_Service_DeleteNameSpace);

		if (id == null || con == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "ȱ���������", this.getId(),
					soa_processid, new java.util.Date(), null));
			log.error(processInfo + ",ɾ�������ռ�,ȱ���������");
			return false;
		}
		return true;
	}
}
