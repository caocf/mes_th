package mes.framework.services.serviceParas;

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
 * ���񣺸��·������ ������������Ҫ��otherparameter�з���һ��Connection������Ϊcon��<br>
 * ��Ҫ���û�������������к���serviceid,paraname,setparaname,paratype,setparatype,paradescֵ����ʹ�ǿ��ַ�����
 * <br>
 * ����ִ�� doService() <br>
 * �����ʼ�� initFordo() <br>
 * ������� undoService() <br>
 * ����������
 * 
 * @author ������0 2007-12-25
 */
public class Service_UpdateServiceParas extends DefService implements IService {

	// ���ݿ�����
	private Connection con = null;

	// �����
	private String serviceid = null;

	// ԭ������
	private String setparaname = null;

	// ԭ��������
	private String setparatype = null;

	// ���º�Ĳ�����
	private String paraname = null;

	// ���º�Ĳ�������
	private String paratype = null;

	// ��������
	private String paradesc = null;

	public Service_UpdateServiceParas() {
		super();
	}

	private final Log log = LogFactory.getLog(Service_UpdateServiceParas.class);

	public ExecuteResult doService(IMessage message, String soa_processid) {
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();

		// ��ʼ���������в�������ʧ�ܣ�����ִ�н��
		if (!initFordo(message, soa_processid)) {
			log.error(processInfo + ",�޸ķ������ʱ,��ʼ����������ʧ��");
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
					log.error(processInfo + ",�޸ķ������ʱ,�������ݿ����,δ֪�����ݿ�����");
					return ExecuteResult.fail;
				}
				log.debug(processInfo + ",�޸ķ������,�������ݿ�ɹ�");
				stmt = con.createStatement();
				int sid = Integer.parseInt(serviceid);
				if (!paraname.equals(setparaname)) {
					String sql_check = dao
							.getSQL_QueryCountServiceParaIsUnique(sid,
									paraname, paratype);
					log.debug("��֤ͬһ����ͬ���͵Ĳ����Ƿ�Ψһ��sql���: sql_check = "
							+ sql_check);
					rs = stmt.executeQuery(sql_check);
					int count = 0;
					if (rs.next()) {
						count = rs.getInt(1);
					}
					if (count > 0)// ͬһ����ͬ���͵Ĳ�����Ψһ
					{
						message.addServiceException(new ServiceException(
								ServiceExceptionType.UNKNOWN, "�ò����Ѵ���!", this
										.getId(), soa_processid, new Date(),
								null));
						log.error(processInfo + ",�޸ķ������ʱ,�ò����Ѵ���,count = "
								+ count);
						return ExecuteResult.fail;
					}
				}
				/*
				 * ���˲����������
				 */
				Map<String, String> data = new HashMap<String, String>();
				String map_paraname = null;
				String map_paratype = null;
				String map_paradesc = null;
				String sql_selectPara = dao.getSQL_QueryServicePara(sid,
						setparatype, setparaname);
				log.debug(processInfo + "��ѯ�˷���ŵ�ԭ�в���,���뵽Map�е�sql���:"
						+ sql_selectPara);
				result = stmt.executeQuery(sql_selectPara);
				while (result.next()) {
					map_paraname = result.getString(1);
					map_paratype = result.getString(2);
					map_paradesc = result.getString(3);
				}
				String debug_map = "result_map_paraname = " + map_paraname
						+ "\n" + "result_map_paratype = " + map_paratype + "\n"
						+ "result_map_paradesc = " + map_paradesc;
				log.debug(processInfo + " , " + this.getClass().getName()
						+ " ,���˲���Map��ֵ:    debug_map = " + debug_map);
				// �������,��������,����������Map��
				data.put("map_serviceid", String.valueOf(sid));
				data.put("map_paratype", map_paratype);
				data.put("map_paraname", map_paraname);
				data.put("map_paradesc", map_paradesc);
				data.put("map_setparatype", paratype);
				data.put("map_setparaname", paraname);
				message.setOtherParameter(this.getClass().getName(), data);
				/*
				 * ���²���
				 */
				String sql_update = dao.getSQL_UpdateServicePara(sid, paratype,
						setparatype, paraname, setparaname, paradesc);
				log.debug("���²�����sql���: sql_update = " + sql_update);
				stmt.executeUpdate(sql_update);
			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), soa_processid, new Date(), sqle));
				log.fatal(processInfo + ",�޸ķ������,���ݿ�����쳣" + sqle.toString());
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
			log.fatal(processInfo + "�޸ķ������,δ֪�쳣" + e.toString());
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
	}

	private boolean initFordo(IMessage message, String soa_processid) {
		// ���ݿ�����
		con = null;
		// �����
		serviceid = null;
		// Ŀ�������
		paraname = null;
		// ���º�Ĳ�����
		setparaname = null;
		// ���º�Ĳ�������
		paratype = null;
		// Ŀ���������
		setparatype = null;
		// ��������
		paradesc = null;

		// ��ȡ�÷�������������ֵ
		con = (Connection) message.getOtherParameter("con");
		serviceid = message.getUserParameterValue("serviceid");
		paraname = message.getUserParameterValue("paraname");
		setparaname = message.getUserParameterValue("setparaname");
		paratype = message.getUserParameterValue("paratype");
		setparatype = message.getUserParameterValue("setparatype");
		paradesc = message.getUserParameterValue("paradesc");
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();

		String debug_UpdateServicePara = "�����: serviceid = " + serviceid + "\n"
				+ "Ŀ�������: paraname = " + paraname + "\n"
				+ "���º�Ĳ�������setparaname = " + setparaname + "\n"
				+ "���º�Ĳ������ͣ�paratype = " + paratype + "\n"
				+ "Ŀ��������ͣ�setparatype = " + setparatype + "\n"
				+ "����������paradesc = " + paradesc + "\n";
		log.debug(processInfo + ",�޸ķ������ʱ�û��ύ�Ĳ���: " + debug_UpdateServicePara);

		if (serviceid == null || paraname == null || paratype == null
				|| paradesc == null || setparatype == null
				|| setparaname == null || con == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "ȱ���������", this.getId(),
					soa_processid, new java.util.Date(), null));
			log.error(processInfo + ",�޸ķ������ʱ,ȱ���������");
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
			int map_serviceid = Integer.parseInt(map.get("map_serviceid"));
			String map_paratype = map.get("map_paratype");
			String map_paraname = map.get("map_paraname");
			String map_paradesc = map.get("map_paradesc");
			String map_setparatype = map.get("map_setparatype");
			String map_setparaname = map.get("map_setparaname");
			String debug_map = "map_serviceid = " + map_serviceid + "\n"
					+ "map_paratype = " + map_paratype + "\n"
					+ "map_paraname = " + map_paraname + "\n"
					+ "map_paradesc = " + map_paradesc + "\n"
					+ "map_setparatype = " + map_setparatype + "\n"
					+ "map_setparaname = " + map_setparaname;
			log.debug(processInfo + " , " + this.getClass().getName()
					+ "�޸ķ�������Ļ��˲������յĲ���: debug_map = " + debug_map);
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
						log.error(processInfo + ",�޸ķ�������Ļ��˲����������ݿ�ʱ,δ֪�����ݿ�����");
						return ExecuteResult.fail;
					}
					log.debug(processInfo + "�޸ķ�������Ļ��˲����������ݿ�ɹ�");
					String undo_sql_update = dao.getSQL_UpdateServicePara(
							map_serviceid, map_paratype, map_setparatype,
							map_paraname, map_setparaname, map_paradesc);
					log.debug(processInfo
							+ "�޸ķ���������˲�����sql���: undo_sql_update = "
							+ undo_sql_update);
					stmt = con.createStatement();
					stmt.executeUpdate(undo_sql_update);
				} catch (SQLException sqle) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
									.getId(), soa_processid, new Date(), sqle));
					log.fatal("�޸ķ�������Ļ��˲��������ݿ�����쳣" + sqle.toString());
					return ExecuteResult.fail;
				} finally {
					if (stmt != null)
						stmt.close();
				}
			} catch (Exception e) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.UNKNOWN, e.toString(), this
								.getId(), soa_processid, new Date(), null));
				log.fatal("ɾ����������Ļ����г���δ֪�쳣" + e.toString());
				return ExecuteResult.fail;
			}
		}
		return ExecuteResult.sucess;
	}
}
