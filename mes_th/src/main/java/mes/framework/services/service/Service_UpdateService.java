package mes.framework.services.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

/**
 * ���񣺸��·�������Ϣ ������������Ҫ��otherparameter�з���һ��Connection������Ϊcon��<br>
 * ��Ҫ���û�������������к���serviceid,servicename,classname,servicedesc,namespaceֵ����ʹ�ǿ��ַ�����
 * <br>
 * ����ִ�� doService() <br>
 * �����ʼ�� initFordo() <br>
 * ������� undoService() ������޸Ĳ������ֲ�ͨ��ع�<br>
 * ����������
 * 
 * @author ������ 2007-12-24
 */
public class Service_UpdateService extends DefService implements IService {

	// ���ݿ�����
	private Connection con = null;

	// �����
	private String serviceid = null;

	// ������
	private String servicename = null;

	// ��Ӧ����
	private String classname = null;

	// �����ҵ������
	private String servicedesc = null;

	// �����ռ�
	private String namespace = null;

	public Service_UpdateService() {
		super();
	}

	private final Log log = LogFactory.getLog(Service_UpdateService.class);

	public ExecuteResult doService(IMessage message, String soa_processid) {
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();

		// ��ʼ���������в�������ʧ�ܣ�����ִ�н��
		if (!initFordo(message, soa_processid)) {
			log.error(processInfo + " , �޸ķ���,��ʼ����������ʧ��");
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
					log.error(processInfo + " , �޸ķ���ʱ,�������ݿ����,δ֪�����ݿ�����");
					return ExecuteResult.fail;
				}
				log.debug(processInfo + " , �޸ķ���,�������ݿ�ɹ�");
				stmt = con.createStatement();
				int sid = Integer.parseInt(serviceid);
				int ns = Integer.parseInt(namespace);

				// ��ѯ�˷���ŵ�������Ϣ,���뵽Map��
				Map<String, String> data = new HashMap<String, String>();
				int map_serviceid = 0;
				String map_cservername = null;
				String map_cclassname = null;
				String map_cdescription = null;
				int map_nnamespaceid = 0;
				String sql_selectService = dao.getSQL_QueryAllServices(String
						.valueOf(sid), "ById");
				result = stmt.executeQuery(sql_selectService);
				while (result.next()) {
					map_serviceid = result.getInt(1);
					map_cservername = result.getString(2);
					map_cclassname = result.getString(3);
					map_cdescription = result.getString(4);
					map_nnamespaceid = result.getInt(5);
				}
				data.put("map_serviceid", String.valueOf(map_serviceid));
				data.put("map_cservername", map_cservername);
				data.put("map_cclassname", map_cclassname);
				data.put("map_cdescription", map_cdescription);
				data.put("map_nnamespaceid", String.valueOf(map_nnamespaceid));
				message.setOtherParameter(this.getClass().getName(), data);
				// �޸ķ������
				String sql_update = dao.getSQL_UpdateService(sid, servicename,
						classname, servicedesc, ns);
				log.debug("�޸ķ����sql���: sql_update = " + sql_update);
				stmt.executeUpdate(sql_update);
				
			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), soa_processid, new Date(), sqle));
				log.fatal(processInfo + " , �޸ķ���,���ݿ�����쳣" + sqle.toString());
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
			log.fatal(processInfo + " , �޸ķ���,δ֪�쳣" + e.toString());
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
	}

	private boolean initFordo(IMessage message, String soa_processid) {
		// ���ݿ�����
		con = null;
		// �����
		serviceid = null;
		// ������
		servicename = null;
		// ��Ӧ����
		classname = null;
		// �����ҵ������
		servicedesc = null;
		// �����ռ�
		namespace = null;

		// ��ȡ�÷�������������ֵ
		con = (Connection) message.getOtherParameter("con");
		serviceid = message.getUserParameterValue("serviceid");
		servicename = message.getUserParameterValue("servicename");
		classname = message.getUserParameterValue("classname");
		servicedesc = message.getUserParameterValue("servicedesc");
		namespace = message.getUserParameterValue("namespace");

		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();

		String debug_Service_UpdateService = "�û��ύ�Ĳ���: �����: serviceid = "
				+ serviceid + "\n" + "������: servicename = " + servicename + "\n"
				+ "��Ӧ����: classname = " + classname + "\n"
				+ "�����ҵ������: servicedesc = " + servicedesc + "\n"
				+ "�����ռ�: namespace = " + namespace + "\n";
		log.debug(processInfo + " , �޸ķ���" + debug_Service_UpdateService);

		if (serviceid == null || servicename == null || classname == null
				|| servicedesc == null || namespace == null || con == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "ȱ���������", this.getId(),
					soa_processid, new java.util.Date(), null));
			log.error(processInfo + " , �޸ķ���,ȱ���������");
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public ExecuteResult undoService(IMessage message, String soa_processid) {
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
 
		Object obj = message.getOtherParameter(this.getClass().getName());
		if (obj instanceof Map) {
			Map<String, String> map = (Map<String, String>)obj;
			String map_serviceid = map.get("map_serviceid");
			String map_cservername = map.get("map_cservername");
			String map_cclassname = map.get("map_cclassname");
			String map_cdescription = map.get("map_cdescription");
			String map_nnamespaceid = map.get("map_nnamespaceid");
			String debug_map = "map_serviceid = " + map_serviceid + "\n"
					+ "map_cservername = " + map_cservername + "\n"
					+ "map_cclassname = " + map_cclassname + "\n"
					+ "map_cdescription = " + map_cdescription + "\n"
					+ "map_nnamespaceid = " + map_nnamespaceid + "\n";
			log.debug(processInfo + " , " + this.getClass().getName()
					+ " ,���˲���Map��ֵ:    debug_map = " + debug_map);
			try {
				Statement st = null;
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
					// ���޸ĵķ����������޸Ļ���
					String sql_update = dao.getSQL_UpdateService(Integer
							.parseInt(map_serviceid), map_cservername,
							map_cclassname, map_cdescription, Integer
									.parseInt(map_nnamespaceid));
					log
							.debug("�޸ķ�����˲�����sql���: undo_sql_update = "
									+ sql_update);
					st = con.createStatement();
					st.executeUpdate(sql_update);
				} catch (SQLException sqle) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
									.getId(), soa_processid, new Date(), sqle));
					log.fatal(processInfo + " , " + this.getClass().getName()
							+ " ,���˲������ݿ�����쳣" + sqle.toString());
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
				log.fatal(processInfo + " , " + this.getClass().getName()
						+ " ,���˲������ݿ�����쳣" + e.toString());
				return ExecuteResult.fail;
			}
		}
		return super.undoService(message, soa_processid);
	}
}
