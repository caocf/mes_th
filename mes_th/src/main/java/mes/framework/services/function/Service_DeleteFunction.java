package mes.framework.services.function;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import mes.framework.DataBaseType;
import mes.framework.DefService;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.IProcess;
import mes.framework.IService;
import mes.framework.ProcessFactory;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import mes.framework.dao.DAOFactory_UserManager;
import mes.framework.dao.IDAO_UserManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ����ɾ�������� ������������Ҫ��otherparameter�з���һ��Connection������Ϊcon��<br>
 * ��Ҫ���û�������������к���functionid. <br>
 * ִ�� doService() <br>
 * ��ʼ�� initFordo() <br>
 * ���� undoService() <br>
 * ����������
 * 
 * @author ������ 2008-01-02
 */
public class Service_DeleteFunction extends DefService implements IService {

	// ���ݿ�����
	private Connection con = null;

	// ���ܺ�
	private String functionid = null;

	// �û�ID
	private String userid = null;

	private IDAO_UserManager dao = null;

	public Service_DeleteFunction() {
		super();
	}

	private final Log log = LogFactory.getLog(Service_DeleteFunction.class);

	private Vector<String> v_delsql = new Vector<String>();

	private void delfunction(Function f) throws Exception {
		String sql_del = "";
		if (f.getNodeType().equals("3")) {
			sql_del = dao.getSQL_DeleteFunction(Integer.parseInt(f
					.getFunctionID()));
			log.debug("����ڵ�����Ϊ3(Ҷ��)ʱ,ɾ�����ܵ�sql���: sql_del = " + sql_del);
			v_delsql.addElement(sql_del);
			return;
		} else if (f.getNodeType().equals("2")) {
			sql_del = dao.getSQL_DeleteFunction(Integer.parseInt(f
					.getFunctionID()));
			log.debug("����ڵ�����Ϊ2(�ڵ�)ʱ,ɾ�����ܵ�sql���: sql_del = " + sql_del);
			v_delsql.addElement(sql_del);
			Statement stmt = null;
			ResultSet rs = null;
			try {
				String sql_select = dao
						.getSQL_QueryFunctionForUpfunctionid(Integer.valueOf(f
								.getFunctionID()));
				log.debug("��ȡ�ڵ�����Ϊ2(�ڵ�)ʱ,�Ƿ�����ӹ��ܵ�sql���: sql_select = "
						+ sql_select);
				stmt = con.createStatement();
				rs = stmt.executeQuery(sql_select);
				while (rs.next()) {
					Function f_sub = new Function();
					f_sub.setFunctionID(rs.getString(1));
					f_sub.setNodeType(rs.getString(2));
					delfunction(f_sub);
				}
			} catch (Exception e) {
				log.fatal("ɾ��������delfunction(Function f)�����׳��쳣" + e.toString());
				throw e;
			} finally {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			}

		} else {
			throw new Exception("�ڵ���������");
		}
	}

	public ExecuteResult doService(IMessage message, String soa_processid) {
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		// ��ʼ���������в�������ʧ�ܣ�����ִ�н��
		if (!initFordo(message, soa_processid)) {
			log.error(processInfo + ",ɾ��������,��ʼ���������в���ʧ��");
			return ExecuteResult.fail;
		}

		try {
			Statement stmt = null;
			ResultSet rs = null;
			try {
				dao = DAOFactory_UserManager.getInstance(DataBaseType
						.getDataBaseType(con));
				if (dao == null) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.UNKNOWNDATABASETYPE,
							"δ֪�����ݿ�����", this.getId(), soa_processid,
							new Date(), null));
					log.error(processInfo + ",ɾ��������,�������ݿ����,δ֪�����ݿ�����");
					return ExecuteResult.fail;
				}
				log.debug(processInfo + ",ɾ��������,�������ݿ⹦��");
				Function fn = new Function();
				fn.setFunctionID(this.functionid);
				String sql_getNodeType = dao
						.getSQL_QueryNodeTypeForFunctionId(Integer
								.parseInt(this.functionid));
				log.debug("��ȡ�ڵ����͵�sql���: sql_getNodeType = " + sql_getNodeType);
				log.trace(" this.functionid = " + this.functionid);
				stmt = con.createStatement();
				rs = stmt.executeQuery(sql_getNodeType);
				String nodetype = null;
				if (rs.next()) {
					nodetype = rs.getString(1);
				}
				fn.setNodeType(nodetype);
				log.debug(processInfo + ",ɾ��������,��õĽڵ�����: nodetype = "
						+ nodetype);
				v_delsql.removeAllElements();
				this.delfunction(fn);
				con.setAutoCommit(false);
				stmt = con.createStatement();
				/*
				 * ��ѯ�˹��ܺŵ�������Ϣ����Map��
				 */
				Map<String, String> data = new HashMap<String, String>();
				String map_functionid = null;
				String map_functionname = null;
				String map_url = null;
				String map_upfunctionid = null;
				String map_rank = null;
				String map_nodetype = null;
				String map_state = null;
				String map_safemarkcode = null;
				String map_note = null;
				String map_cenabled = null;
				String map_userid = null;
				String map_flo_Order = null;
				String map_sql_select = dao
						.getSQL_QueryFuncitonInfoForFunctionID(this.functionid);
				log.debug(processInfo
						+ ",ɾ�����ܻ��˲�����������в�����sql���: map_sql_select = "
						+ map_sql_select);
				rs = stmt.executeQuery(map_sql_select);
				while (rs.next()) {
					map_functionid = rs.getString(1);
					map_functionname = rs.getString(2);
					map_url = rs.getString(3);
					map_upfunctionid = rs.getString(4);
					map_rank = rs.getString(5);
					map_nodetype = rs.getString(6);
					map_state = rs.getString(7);
					map_safemarkcode = rs.getString(8);
					map_note = rs.getString(9);
					map_cenabled = rs.getString(10);
					map_userid = rs.getString(11);
					map_flo_Order = rs.getString(12);
				}
				String map_debug = "map_functionid = " + map_functionid + "\n"
						+ "map_functionname = " + map_functionname + "\n"
						+ "map_url = " + map_url + "\n" + "map_upfunctionid = "
						+ map_upfunctionid + "\n" + "map_rank = " + map_rank
						+ "\n" + "map_nodetype = " + map_nodetype + "\n"
						+ "map_state = " + map_state + "\n"
						+ "map_safemarkcode = " + map_safemarkcode + "\n"
						+ "map_note = " + map_note + "\n" + "map_cenabled = "
						+ map_cenabled + "\n" + "map_userid = " + map_userid
						+ "\n" + "map_flo_Order = " + map_flo_Order;
				log.debug(processInfo + ",ɾ�����ܻ��˲�����ѯ�������в���Ϊ: map_debug = "
						+ map_debug);
				int length = 0;
				// ��ȡȨ�޴�����
				String sql_get = dao.getSQL_QueryPowerStringLength();
				log.debug("��ȡϵͳ��Ȩ�޴����ȵ�sql���: sql_get = " + sql_get);
				rs = stmt.executeQuery(sql_get);
				if (rs.next()) {
					length = rs.getInt(1);
				}
				String appendString = "";
				if (length < Integer.parseInt(map_functionid)) {
					for (int i = length + 1; i <= Integer
							.parseInt(map_functionid); i++) {
						appendString = appendString + "0";
					}
				}
				log.debug(processInfo + ",��ӹ��ܵĹ��ܴ�Ϊ: appendString = "
						+ appendString);
				data.put("map_functionid", map_functionid);
				data.put("map_functionname", map_functionname);
				data.put("map_url", map_url);
				data.put("map_upfunctionid", map_upfunctionid);
				data.put("map_rank", map_rank);
				data.put("map_nodetype", map_nodetype);
				data.put("map_state", map_state);
				data.put("map_safemarkcode", map_safemarkcode);
				data.put("map_note", map_note);
				data.put("map_cenabled", map_cenabled);
				data.put("map_userid", map_userid);
				data.put("map_flo_Order", map_flo_Order);
				data.put("appendString", appendString);
				message.setOtherParameter(this.getClass().getName(), data);
				/*
				 * ɾ������
				 */
				// System.out.println("begin");
				for (int i = 0; i < v_delsql.size(); i++) {
					stmt.executeUpdate((String) v_delsql.elementAt(i));
					// System.out.println((String)v_delsql.elementAt(i));
				}

				// System.out.println("end");
				// ����Ȩ�޴�
				int uid = Integer.parseInt(userid);
				String sql_update = dao.getSQL_UpdatePowerStringWhenDelete(uid,
						functionid);
				log.debug(processInfo + ",ɾ������ʱ����Ȩ�޴���sql���: sql_update = "
						+ sql_update);
				stmt.executeUpdate(sql_update);
				con.commit();
				con.setAutoCommit(true);

			} catch (SQLException sqle) {
				con.rollback();
				con.setAutoCommit(true);
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), soa_processid, new Date(), sqle));
				log.fatal(processInfo + ",ɾ������,���ݿ�����쳣" + sqle.toString());
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
			log.fatal(processInfo + ",ɾ������,δ֪�쳣" + e.toString());

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
			String map_functionid = map.get("map_functionid");
			String map_functionname = map.get("map_functionname");
			String map_url = map.get("map_url");
			String map_upfunctionid = map.get("map_upfunctionid");
			String map_rank = map.get("map_rank");
			String map_nodetype = map.get("map_nodetype");
			String map_state = map.get("map_state");
			String map_safemarkcode = map.get("map_safemarkcode");
			String map_note = map.get("map_note");
			String map_cenabled = map.get("map_cenabled");
			String map_userid = map.get("map_userid");
			String map_flo_Order = map.get("map_flo_Order");
			String appendString = map.get("appendString");
			String debug_map = "map_functionid = " + map_functionid + "\n"
					+ "map_functionname = " + map_functionname + "\n"
					+ "map_url = " + map_url + "\n" + "map_upfunctionid = "
					+ map_upfunctionid + "\n" + "map_rank = " + map_rank + "\n"
					+ "map_nodetype = " + map_nodetype + "\n" + "map_state = "
					+ map_state + "\n" + "map_safemarkcode = "
					+ map_safemarkcode + "\n" + "map_note = " + map_note + "\n"
					+ "map_cenabled = " + map_cenabled + "\n" + "map_userid = "
					+ map_userid + "\n" + "map_flo_Order = " + map_flo_Order
					+ "\n" + "appendString = " + appendString;
			log.debug(processInfo + ",ɾ�����ܻ��˲�����ѯ�������в���Ϊ: map_debug = "
					+ debug_map);
			try {
				Statement stmt = null;
				try {
					dao = DAOFactory_UserManager.getInstance(DataBaseType
							.getDataBaseType(con));
					if (dao == null) {
						message.addServiceException(new ServiceException(
								ServiceExceptionType.UNKNOWNDATABASETYPE,
								"δ֪�����ݿ�����", this.getId(), soa_processid,
								new Date(), null));
						log.error(processInfo + ",ɾ�����ܻ��˲���,�������ݿ����,δ֪�����ݿ�����");
						return ExecuteResult.fail;
					}
					log.debug(processInfo + ",ɾ�����ܻ��˲���,�������ݿ⹦��");
					stmt = con.createStatement();
					String sql_insert = dao.getSQL_InsertFunction(Integer
							.parseInt(map_functionid), map_functionname,
							map_nodetype, map_url, Integer
									.parseInt(map_upfunctionid), map_rank,
							map_state, Integer.parseInt(map_userid),
							map_safemarkcode, map_note, map_cenabled,Float.valueOf(map_flo_Order));
					log.debug(processInfo
							+ ",ɾ�����ܻ��˲�����sql���: undo_sql_insert = "
							+ sql_insert);
					String sql_updateString = dao
							.getSQL_UpdatePowerStringWhenAdd(appendString);
					log.debug(processInfo
							+ ",ɾ�����ܻ��˲�����sql���: undo_sql_updateString = "
							+ sql_updateString);
					con.setAutoCommit(false);
					stmt.executeUpdate(sql_insert);
					stmt.executeUpdate(sql_updateString);
					con.commit();
					con.setAutoCommit(true);
				} catch (SQLException sqle) {
					con.rollback();
					con.setAutoCommit(true);
					message.addServiceException(new ServiceException(
							ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
									.getId(), soa_processid, new Date(), sqle));
					log.fatal(processInfo + ",ɾ�����ܻ��˲���,���ݿ�����쳣"
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
				log.fatal(processInfo + ",ɾ�����ܻ��˲���,δ֪�쳣" + e.toString());
				return ExecuteResult.fail;
			}
		}
		return ExecuteResult.sucess;
	}

	private boolean initFordo(IMessage message, String soa_processid) {
		// ���ݿ�����
		con = null;
		// ���ܺ�
		functionid = null;
		// �û�ID
		userid = null;

		// ��ȡ�÷�������������ֵ
		con = (Connection) message.getOtherParameter("con");
		functionid = message.getUserParameterValue("functionid");
		userid = message.getUserParameterValue("userid");
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		String debug_Service_DeleteFunction = "���ܺ�: functionid = " + functionid
				+ "\n" + "�û�ID: userid = " + userid + "\n";
		log.debug(processInfo + ",ɾ������ʱ�û��ύ�Ĳ���: "
				+ debug_Service_DeleteFunction);

		if (functionid == null || userid == null || con == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "ȱ���������", this.getId(),
					soa_processid, new java.util.Date(), null));
			log.error(processInfo + ",ɾ������,��ʼ��������,ȱ���������");
			return false;
		}
		return true;
	}
}
