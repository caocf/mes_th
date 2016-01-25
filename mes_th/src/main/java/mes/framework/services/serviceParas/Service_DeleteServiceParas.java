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
 * ����ɾ�������� ������������Ҫ��otherparameter�з���һ��Connection������Ϊcon��<br>
 * ��Ҫ���û�������������к���serviceid��paratype,paraname. <br>
 * ����ִ�� doService() <br>
 * �����ʼ�� initFordo() <br>
 * ������� undoService()<br>
 * ����������
 * 
 * @author ������ 2007-12-25
 */
public class Service_DeleteServiceParas extends DefService implements IService {

	// ���ݿ�����
	private Connection con = null;

	// �����
	private String serviceid = null;

	// ��������
	private String paratype = null;

	// ������
	private String paraname = null;

	public Service_DeleteServiceParas() {
		super();
	}

	private final Log log = LogFactory.getLog(Service_DeleteServiceParas.class);

	public ExecuteResult doService(IMessage message, String soa_processid) {
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();

		// ��ʼ���������в�������ʧ�ܣ�����ִ�н��
		if (!initFordo(message, soa_processid)) {
			log.error(processInfo + ",ɾ���������ʱ,��ʼ����������ʧ��");
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
					log.error(processInfo + ",ɾ���������ʱ,�������ݿ����,δ֪�����ݿ�����");
					return ExecuteResult.fail;
				}
				log.debug(processInfo + ",ɾ���������ʱ,�������ݿ�ɹ�!");
				stmt = con.createStatement();
				int sid = Integer.parseInt(serviceid);
				/*
				 * ���˲����������
				 */
				Map<String, String> data = new HashMap<String, String>();
				String map_paraname = null;
				String map_paratype = null;
				String map_paradesc = null;
				String sql_selectPara = dao.getSQL_QueryServicePara(sid,
						paratype, paraname);
				log.debug(processInfo + "��ѯ�˷���ŵ�ԭ�в���,���뵽Map�е�sql���:"
						+ sql_selectPara);
				rs = stmt.executeQuery(sql_selectPara);
				while (rs.next()) {
					map_paraname = rs.getString(1);
					map_paratype = rs.getString(2);
					map_paradesc = rs.getString(3);
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
				message.setOtherParameter(this.getClass().getName(), data);
				/*
				 * ɾ������
				 */
				String sql_del = dao.getSQL_DeleteServicePara(sid, paratype,
						paraname);
				log.debug("ɾ��������sql���: sql_del = " + sql_del);
				stmt.executeUpdate(sql_del);

			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), soa_processid, new Date(), sqle));
				log.fatal(processInfo + ",ɾ���������,���ݿ�����쳣" + sqle.toString());
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
			log.fatal(processInfo + ",ɾ���������,δ֪�쳣" + e.toString());
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
		paratype = message.getUserParameterValue("paratype");
		paraname = message.getUserParameterValue("paraname");
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();

		String debug_DeleteServicePara = "���̺�: serviceid = " + serviceid + "\n";
		log.debug(processInfo + ",ɾ���������ʱ�û��ύ�Ĳ���: " + debug_DeleteServicePara);

		if (serviceid == null || paratype == null || paraname == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "ȱ���������", this.getId(),
					soa_processid, new java.util.Date(), null));
			log.error(processInfo + ",ɾ���������,ȱ���������");
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
			String map_paraname = map.get("map_paraname");
			String map_paratype = map.get("map_paratype");
			String map_paradesc = map.get("map_paradesc");
			String debug_undo = "map_serviceid = " + map_serviceid + "\n"
					+ "map_paraname = " + map_paraname + "\n"
					+ "map_paratype = " + map_paratype + "\n"
					+ "map_paradesc = " + map_paradesc;
			log
					.debug(processInfo + "���˲����н��յ���mapֵ: debug_undo = "
							+ debug_undo);
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
						log.error(processInfo + ",ɾ����������Ļ��˲����������ݿ�ʱ,δ֪�����ݿ�����");
						return ExecuteResult.fail;
					}
					log.debug(processInfo + "ɾ����������Ļ��˲����������ݿ�ɹ�");
					//ɾ������������˲����Ĳ���
					String undo_sql_insert = dao.getSQL_InsertServicePara(
							map_serviceid, map_paraname, map_paratype,
							map_paradesc);
					log.debug(processInfo + " , " + this.getClass().getName()
							+ "ɾ����������Ļ��˲�����sql���: undo_sql_insert = "
							+ undo_sql_insert);
					stmt = con.createStatement();
					stmt.executeUpdate(undo_sql_insert);
				} catch (SQLException sqle) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
									.getId(), soa_processid, new Date(), sqle));
					log.fatal("ɾ����������Ļ��˲��������ݿ�����쳣" + sqle.toString());
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
