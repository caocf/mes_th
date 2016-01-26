package com.qm.mes.framework.services.serviceParas;

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
 * ������ӷ������ ������������Ҫ��otherparameter�з���һ��Connection������Ϊcon��<br>
 * ��Ҫ���û�������������к���serviceid,paraname,paratype,paradescֵ����ʹ�ǿ��ַ����� <br>
 * ����ִ�� doService() <br>
 * �����ʼ�� initFordo() <br>
 * ������� undoService()<br>
 * ����������
 * 
 * @author ������ 2007-12-25
 */
public class Service_InsertServiceParas extends DefService implements IService {

	// ���ݿ�����
	private Connection con = null;

	// �����
	private String serviceid = null;

	// ������
	private String paraname = null;

	// ��������
	private String paratype = null;

	// ��������
	private String paradesc = null;

	public Service_InsertServiceParas() {
		super();
	}

	private final Log log = LogFactory.getLog(Service_InsertServiceParas.class);

	public synchronized ExecuteResult doService(IMessage message,
			String soa_processid) {
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		// ��ʼ���������в�������ʧ�ܣ�����ִ�н��
		if (!initFordo(message, soa_processid)) {
			log.error(processInfo + ",��ӷ������,��ʼ����������ʧ��");
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
					log.error(processInfo + ",��ӷ������,�������ݿ����,δ֪�����ݿ�����");
					return ExecuteResult.fail;
				}
				log.debug(processInfo + ",��ӷ������,�������ݿ�ɹ�");
				stmt = con.createStatement();
				int sid = Integer.parseInt(serviceid);
				// ��֤�����Ƿ�Ψһ
				String sql_check = dao.getSQL_QueryCountServiceParaIsUnique(
						sid, paraname, paratype);
				log.debug("��֤�����Ƿ�Ψһ��sql���: sql_check = " + sql_check);
				rs = stmt.executeQuery(sql_check);
				int count = 0;
				if (rs.next()) {
					count = rs.getInt(1);
				}
				if (count > 0)// ͬһ�����ͬ���͵Ĳ�����Ψһ
				{
					message.addServiceException(new ServiceException(
							ServiceExceptionType.UNKNOWN, "�ò����Ѵ���!", this
									.getId(), soa_processid, new Date(), null));
					log.error(processInfo + ",��֤����,ͬһ�����ͬ���͵Ĳ�����Ψһ count = "
							+ count);
					return ExecuteResult.fail;
				}
				// �������,������,�������ͷ���Map��
				Map<String, String> data = new HashMap<String, String>();
				data.put("map_serviceid", String.valueOf(sid));
				data.put("map_paraname", paraname);
				data.put("map_paratype", paratype);
				message.setOtherParameter(this.getClass().getName(), data);
				// ��Ӳ���
				String sql_insert = dao.getSQL_InsertServicePara(sid, paraname,
						paratype, paradesc);
				log.debug("��Ӳ�����sql���: sql_insert = " + sql_insert);
				stmt.executeUpdate(sql_insert);

			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), soa_processid, new Date(), sqle));
				log.fatal(processInfo + ",��ӷ������ʱ,���ݿ�����쳣" + sqle.toString());
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
			log.fatal(processInfo + ",��ӷ������ʱ,����δ֪�쳣" + e.toString());
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
		paraname = null;
		// ��������
		paratype = null;
		// ��������
		paradesc = null;

		// ��ȡ�÷�������������ֵ
		con = (Connection) message.getOtherParameter("con");
		serviceid = message.getUserParameterValue("serviceid");
		paraname = message.getUserParameterValue("paraname");
		paratype = message.getUserParameterValue("paratype");
		paradesc = message.getUserParameterValue("paradesc");

		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();

		String debug_InsertServicePara = "�û��ύ�Ĳ���: �����: serviceid = "
				+ serviceid + "\n" + "������: paraname = " + paraname + "\n"
				+ "��������: paratype = " + paratype + "\n" + "��������: paradesc = "
				+ paradesc + "\n";
		log.debug(processInfo + ",��ӷ������ " + debug_InsertServicePara);

		if (serviceid == null || paraname == null || paratype == null
				|| paradesc == null || con == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "ȱ���������", this.getId(),
					soa_processid, new java.util.Date(), null));
			log.error(processInfo + ",��ӷ������,ȱ���������");
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
			String debug_map = "map_serviceid = " + map_serviceid + "\n"
					+ "map_paraname = " + map_paraname + "\n"
					+ "map_paratype = " + map_paratype;
			log.debug(processInfo + "��ӷ��������undoService����: "
					+ this.getClass().getName() + "  debug_map = " + debug_map);
			try {
				Statement stmt = null;
				try {
					IDAO_Core dao = DAOFactory_Core.getInstance(DataBaseType
							.getDataBaseType(con));
					if (dao == null) {
						message.addServiceException(new ServiceException(
								ServiceExceptionType.UNKNOWNDATABASETYPE,
								"δ֪���ݿ�����", this.getId(), soa_processid,
								new Date(), null));
						log.error(processInfo + ",���˲����������ݿ����,δ֪�����ݿ�����");
						return ExecuteResult.fail;
					}
					log.debug(processInfo + ",���˲����������ݿ�ɹ�");
					String sql_delete = dao.getSQL_DeleteServicePara(
							map_serviceid, map_paratype, map_paraname);
					log.debug(this.getClass().getName()
							+ "��ӷ�������Ļ��˲�����sql���: undo_sql_delete = "
							+ sql_delete);
					stmt = con.createStatement();
					stmt.executeUpdate(sql_delete);
				} catch (SQLException sqle) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
									.getId(), soa_processid, new Date(), sqle));
					log.fatal(processInfo + ",��ӷ���������˲���ʱ,���ݿ�����쳣"
							+ sqle.toString());
					return ExecuteResult.fail;
				} finally {
					if (stmt != null)
						stmt.close();
				}
			} catch (Exception e) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.UNKNOWN, e.toString(), this
								.getId(), soa_processid, new Date(), null));
				log.fatal(processInfo + "��ӷ���������˲���ʱ,����δ֪�쳣" + e.toString());
				return ExecuteResult.fail;
			}
		}
		return ExecuteResult.sucess;
	}
}
