package mes.framework.services.service;

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
 * ������ӷ�������Ϣ ������������Ҫ��otherparameter�з���һ��Connection������Ϊcon��<br>
 * ��Ҫ���û�������������к���servicename,classname,servicedesc,namespaceֵ����ʹ�ǿ��ַ����� <br>
 * ����ִ�� doService() <br>
 * �����ʼ�� initFordo() <br>
 * ������� undoService() �������Ӳ������ֲ�ͨ��ع�<br>
 * ����������
 * 
 * @author ������ 2007-12-24
 */
public class Service_InsertService extends DefService implements IService {

	// ���ݿ�����
	private Connection con = null;

	// ������
	private String servicename = null;

	// ��Ӧ����
	private String classname = null;

	// �����ҵ������
	private String servicedesc = null;

	// �����ռ�
	private String namespace = null;

	public Service_InsertService() {
		super();
	}

	private final Log log = LogFactory.getLog(Service_InsertService.class);

	public synchronized ExecuteResult doService(IMessage message,
			String soa_processid) {
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		// ��ʼ���������в�������ʧ�ܣ�����ִ�н��
		if (!initFordo(message, soa_processid)) {
			log.error(processInfo + " , ��ӷ���,��ʼ����������ʧ��");
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
					log.error(processInfo + " , ��ӷ���ʱ,�������ݿ����,δ֪�����ݿ�����");
					return ExecuteResult.fail;
				}
				log.debug(processInfo + " , ��ӷ���,�������ݿ�ɹ�");
				stmt = con.createStatement();
				// ���ɷ����
				String sql_create = dao.getSQL_QueryNextServiceId();
				log.debug(" ���ɷ���ŵ�sql���: sql_create = " + sql_create);
				rs = stmt.executeQuery(sql_create);
				int sid = 0;// �����
				if (rs.next()) {
					sid = rs.getInt(1);
				}
				log.debug(" ���ɷ����: sid = " + sid);

				// ���˷���ŷ��뵽Map��
				Map<String, String> data = new HashMap<String, String>();
				data.put("map_serviceid", String.valueOf(sid));
				message.setOtherParameter(this.getClass().getName(), data);
				// ��ӷ���
				String sql_insert = dao.getSQL_InsertService(sid, servicename,
						classname, servicedesc, namespace);
				log.debug(" ��ӷ����sql���: sql_insert = " + sql_insert);
				stmt.executeUpdate(sql_insert);
			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), soa_processid, new Date(), sqle));
				log.fatal(processInfo + " , ��ӷ���ʱ,���ݿ�����쳣" + sqle.toString());
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
			log.fatal(processInfo + " , ��ӷ���,δ֪�쳣" + e.toString());

			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
	}

	private boolean initFordo(IMessage message, String soa_processid) {
		// ���ݿ�����
		con = null;
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
		servicename = message.getUserParameterValue("servicename");
		classname = message.getUserParameterValue("classname");
		servicedesc = message.getUserParameterValue("servicedesc");
		namespace = message.getUserParameterValue("namespace");

		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();

		String debug_Service_InsertService = "�û��ύ�Ĳ���: \n "
				+ "������: servicename = " + servicename + "\n"
				+ "��Ӧ����: classname = " + classname + "\n"
				+ "�����ҵ������: servicedesc = " + servicedesc + "\n"
				+ "�����ռ�: namespace = " + namespace + "\n";
		log.debug(processInfo + " , ��ӷ���: " + debug_Service_InsertService);

		if (servicename == null || classname == null || servicedesc == null
				|| namespace == null || con == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "ȱ���������", this.getId(),
					soa_processid, new java.util.Date(), null));
			log.error(processInfo + " , ��ӷ���,ȱ���������");

			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public synchronized ExecuteResult undoService(IMessage message,
			String soa_processid) {
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		Statement stmt = null;
		Object obj = message.getOtherParameter(this.getClass().getName());
		if (obj instanceof Map) {
			Map<String, String> map = (Map<String, String>) obj;
			String map_serviceid = map.get("map_serviceid");

			String debug_map = "map_serviceid = " + map_serviceid + "\n";
			log.debug(processInfo + " , " + this.getClass().getName()
					+ " ,���˲���Map��ֵ:    debug_map = " + debug_map);
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
					// ����ӵķ�����ɾ����ȥ
					String sql_del = dao.getSQL_DeleteService(Integer
							.parseInt(map_serviceid));
					log.debug(processInfo + " , " + this.getClass().getName()
							+ " ,���˲�����sql���:  undo_sql_del = " + sql_del);
					stmt = con.createStatement();
					stmt.executeUpdate(sql_del);
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
		return super.undoService(message, soa_processid);
	}
}
