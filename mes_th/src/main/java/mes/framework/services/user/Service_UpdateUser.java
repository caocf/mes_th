package mes.framework.services.user;

import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import mes.framework.*;
import mes.framework.dao.*;

public class Service_UpdateUser extends DefService implements IService {

	private Connection con = null;

	private String usrno = null;

	private String usrname = null;

	private String password = null;

	private String employeeid = null;

	private String state = null;

	private String note = null;

	private String roleno = null;

	private String default_roleno = null;

	private String old_default_roleno = null;

	private String lastupdateuser = null;

	private String lastupdatetime = null;

	private String enabled = null;

	private String oldRoleno = null;

	public Service_UpdateUser() {
		super();
	}

	private final Log log = LogFactory.getLog(Service_UpdateUser.class);

	public ExecuteResult doService(IMessage message, String soa_processid) {
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		if (!initFordo(message, soa_processid)) {
			log.error(processInfo + ",�޸��û�,��ʼ����������ʧ��");
			return ExecuteResult.fail;
		}

		try {
			Statement stmt = null;
			ResultSet rs = null;
			try {
				IDAO_UserManager dao = DAOFactory_UserManager
						.getInstance(DataBaseType.getDataBaseType(con));
				if (dao == null) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.UNKNOWNDATABASETYPE, "", this
									.getId(), soa_processid, new Date(), null));
					log.error(processInfo + ",�޸��û�,�������ݿ����,δ֪�����ݿ�����");
					return ExecuteResult.fail;
				}
				log.debug(processInfo + ",�޸��û�,�������ݿ�ɹ�");
				stmt = con.createStatement();
				String sql_check = dao.getSQL_QueryCountForUserNo(usrno);
				log.debug("ȡ���û�id�Ƿ��ظ���sql���: sql_check = " + sql_check);
				rs = stmt.executeQuery(sql_check);
				int count = 0;
				if (rs.next()) {
					count = rs.getInt(1);
				}
				if (count == 0) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.UNKNOWN, "û��Ҫ���µ����ݣ�", this
									.getId(), soa_processid, new Date(), null));
					log
							.error(processInfo + ",�޸��û�ʱ,û��Ҫ���µ�����  count = "
									+ count);
					return ExecuteResult.fail;
				}

				/*
				 * ��ѯ���в�������Map��
				 */
				Map<String, String> data = new HashMap<String, String>();
				String rs_usrno = null;
				String rs_usrname = null;
				String rs_password = null;
				String rs_employeeid = null;
				String rs_state = null;
				String rs_lastupdateuser = null;
				String rs_lastupdatetime = null;
				String rs_note = null;
				String rs_enabled = null;
				String map_sql_select = dao
						.getSQL_QueryUserInfoForUserID(usrno);
				log.debug(processInfo + "��ѯ���û����в�����sql���: map_sql_select = "
						+ map_sql_select);
				rs = stmt.executeQuery(map_sql_select);
				while (rs.next()) {
					rs_usrno = rs.getString(1);
					rs_usrname = rs.getString(2);
					rs_password = rs.getString(3);
					rs_employeeid = rs.getString(5);
					rs_state = rs.getString(6);
					rs_lastupdateuser = rs.getString(7);
					rs_lastupdatetime = rs.getString(8);
					rs_note = rs.getString(9);
					rs_enabled = rs.getString(10);
				}
				String rs_debug = "rs_usrno = " + rs_usrno + "\n"
						+ "rs_usrname = " + rs_usrname + "\n"
						+ "rs_password = " + rs_password + "\n"
						+ "rs_employeeid = " + rs_employeeid + "\n"
						+ "rs_state = " + rs_state + "\n"
						+ "rs_lastupdateuser = " + rs_lastupdateuser + "\n"
						+ "rs_lastupdatetime = " + rs_lastupdatetime + "\n"
						+ "rs_note = " + rs_note + "\n" + "rs_enabled = "
						+ rs_enabled;
				log.debug(processInfo + "��ѯ��������ϢΪ: rs_debug = " + rs_debug);
				data.put("rs_usrno", rs_usrno);
				data.put("rs_usrname", rs_usrname);
				data.put("rs_password", rs_password);
				data.put("rs_oldRoleno", oldRoleno);
				data.put("rs_old_default_roleno", old_default_roleno);
				data.put("rs_employeeid", rs_employeeid);
				data.put("rs_state", rs_state);
				data.put("rs_lastupdateuser", rs_lastupdateuser);
				data.put("rs_lastupdatetime", rs_lastupdatetime);
				data.put("rs_note", rs_note);
				data.put("rs_enabled", rs_enabled);
				message.setOtherParameter(this.getClass().getName(), data);

				String sql_update;
				// password be changed
				if (!password.equals("********")) {
					sql_update = dao.getSQL_UpdateUser(usrno, usrname,
							password, employeeid, state, lastupdateuser,
							lastupdatetime, note, enabled);
				} else// password not changed
				{
					sql_update = dao.getSQL_UpdateUser(usrno, usrname,
							employeeid, state, lastupdateuser, lastupdatetime,
							note, enabled);
				}
				log.debug("�����û�id�������û���Ϣ�����������sql���: sql_update = "
						+ sql_update);
				stmt.executeUpdate(sql_update);
				String sql_deleteDataUserRole = "";
				String sql_insertDataUserRole = "";

				String[] role_new = roleno.split(":");
				sql_deleteDataUserRole = dao
						.getSQL_DeleteDataUserRole(new Integer(usrno));
				log.debug("���ݽ�ɫ�ţ�ɾ��ԭ�н�ɫ�ŵ�sql���:sql_deleteDataUserRole = "
						+ sql_deleteDataUserRole);
				stmt.executeUpdate(sql_deleteDataUserRole);
				for (String ch : role_new) {
					if (ch != null && !ch.equals("")) {
						if (default_roleno.equals(ch)) {
							sql_insertDataUserRole = dao
									.getSQL_insertDataUserRole(new Integer(
											usrno), new Integer(ch), "0");
						} else {
							sql_insertDataUserRole = dao
									.getSQL_insertDataUserRole(new Integer(
											usrno), new Integer(ch), "1");
						}
						log
								.debug("�����½�ɫ��data_user_role���sql���: sql_insertDataUserRole = "
										+ sql_insertDataUserRole);
						stmt.executeUpdate(sql_insertDataUserRole);
					}
				}

			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ����" + sqle,
						this.getId(), soa_processid, new Date(), sqle));
				log.fatal(processInfo + ",�޸��û�,���ݿ�����쳣" + sqle.toString());
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
			log.fatal(processInfo + ",�޸��û�,δ֪�쳣" + e.toString());
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
			String map_usrno = map.get("rs_usrno");
			String map_usrname = map.get("rs_usrname");
			String map_password = map.get("rs_password");
			String map_oldRoleno = map.get("rs_oldRoleno").trim();
			String map_employeeid = map.get("rs_employeeid").trim();
			String map_old_default_roleno = map.get("rs_old_default_roleno");
			String map_state = map.get("rs_state");
			String map_lastupdateuser = map.get("rs_lastupdateuser");
			String map_lastupdatetime = map.get("rs_lastupdatetime");
			String map_note = map.get("rs_note");
			String map_enabled = map.get("rs_enabled");
			String map_debug = "map_usrno = " + map_usrno + "\n"
					+ "map_usrname = " + map_usrname + "\n" + "map_password = "
					+ map_password + "\n" + "map_oldRoleno = " + map_oldRoleno
					+ "\n" + "map_employeeid = " + map_employeeid + "\n"
					+ "map_state = " + map_state + "\n"
					+ "map_lastupdateuser = " + map_lastupdateuser + "\n"
					+ "map_lastupdatetime = " + map_lastupdatetime + "\n"
					+ "map_note = " + map_note + "\n" + "map_enabled = "
					+ map_enabled;
			log.debug(processInfo + "�޸��û����˲���Map���յ�������ϢΪ: map_debug = "
					+ map_debug);
			try {
				Statement stmt = null;
				try {
					IDAO_UserManager dao = DAOFactory_UserManager
							.getInstance(DataBaseType.getDataBaseType(con));
					if (dao == null) {
						message.addServiceException(new ServiceException(
								ServiceExceptionType.UNKNOWNDATABASETYPE, "",
								this.getId(), soa_processid, new Date(), null));
						log.error(processInfo + ",�޸��û����˲���,�������ݿ����,δ֪�����ݿ�����");
						return ExecuteResult.fail;
					}
					log.debug(processInfo + ",�޸��û����˲���,�������ݿ�ɹ�");
					stmt = con.createStatement();
					String undo_sql_update;
					// password be changed
					if (!password.equals("********")) {
						undo_sql_update = dao.getSQL_UpdateUser(map_usrno,
								map_usrname, map_password, map_employeeid,
								map_state, map_lastupdateuser,
								map_lastupdatetime, map_note, map_enabled);
					} else// password not changed
					{
						undo_sql_update = dao.getSQL_UpdateUser(map_usrno,
								map_usrname, map_employeeid, map_state,
								map_lastupdateuser, map_lastupdatetime,
								map_note, map_enabled);
					}
					log.debug("�޸��û����˲�����sql���: undo_sql_update = "
							+ undo_sql_update);
					stmt.executeUpdate(undo_sql_update);

					String sql_deleteDataUserRole = "";
					String sql_insertDataUserRole = "";

					String[] role_old = map_oldRoleno.split(":");
					sql_deleteDataUserRole = dao
							.getSQL_DeleteDataUserRole(new Integer(map_usrno));
					log.debug("���ݽ�ɫ�ţ�ɾ���½�ɫ�ŵ�sql���:sql_deleteDataUserRole = "
							+ sql_deleteDataUserRole);
					stmt.executeUpdate(sql_deleteDataUserRole);
					for (String ch : role_old) {
						if (ch != null && !ch.equals("")) {
							if (map_old_default_roleno.equals(ch)) {
								sql_insertDataUserRole = dao
										.getSQL_insertDataUserRole(new Integer(
												usrno), new Integer(ch), "0");
							} else {
								sql_insertDataUserRole = dao
										.getSQL_insertDataUserRole(new Integer(
												usrno), new Integer(ch), "1");
							}
							log
									.debug("����ԭ�н�ɫ��data_user_role���sql���: sql_insertDataUserRole = "
											+ sql_insertDataUserRole);
							stmt.executeUpdate(sql_insertDataUserRole);
						}
					}

				} catch (SQLException sqle) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.DATABASEERROR, "���ݿ����" + sqle,
							this.getId(), soa_processid, new Date(), sqle));
					log.fatal(processInfo + ",�޸��û�,���ݿ�����쳣" + sqle.toString());
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
				log.fatal(processInfo + ",�޸��û�,δ֪�쳣" + e.toString());
				return ExecuteResult.fail;
			}
		}
		return ExecuteResult.sucess;
	}

	private boolean initFordo(IMessage message, String soa_processid) {

		con = null;
		usrno = null;
		usrname = null;
		password = null;
		roleno = null;
		employeeid = null;
		state = null;
		lastupdateuser = null;
		lastupdatetime = null;
		note = null;
		enabled = null;
		oldRoleno = null;

		con = (Connection) message.getOtherParameter("con");
		usrno = message.getUserParameterValue("usrno");
		usrname = message.getUserParameterValue("usrname");
		password = message.getUserParameterValue("password");
		roleno = message.getUserParameterValue("roleno").trim();
		oldRoleno = message.getUserParameterValue("oldRoleno").trim();
		default_roleno = message.getUserParameterValue("default_roleno");
		old_default_roleno = message
				.getUserParameterValue("old_default_roleno");
		employeeid = message.getUserParameterValue("employeeid");
		state = message.getUserParameterValue("state");
		lastupdateuser = message.getUserParameterValue("lastupdateuser");
		lastupdatetime = message.getUserParameterValue("lastupdatetime");
		note = message.getUserParameterValue("note");
		enabled = message.getUserParameterValue("enabled");
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		String debug_UpdateUser = "usrno = " + usrno + "\n" + "usrname = "
				+ usrname + "\n" + "password = " + password + "\n"
				+ "roleno = " + roleno + "\n" + "oldRoleno = " + oldRoleno
				+ "\n" + "default_roleno = " + default_roleno + "\n"
				+ "old_default_roleno = " + old_default_roleno + "\n"
				+ "employeeid = " + employeeid + "\n" + "state = " + state
				+ "\n\r" + "lastupdateuser = " + lastupdateuser + "\n"
				+ "lastupdatetime = " + lastupdatetime + "\n" + "note = "
				+ note + "\n" + "enabled = " + enabled + "\n";
		log.debug(processInfo + ",�޸��û�ʱ�û��ύ�Ĳ���: " + debug_UpdateUser);

		if (usrno == null || usrname == null || password == null
				|| roleno == null || employeeid == null || state == null
				|| lastupdateuser == null || lastupdatetime == null
				|| note == null || enabled == null) {
			message
					.addServiceException(new ServiceException(
							ServiceExceptionType.PARAMETERLOST, "����û�ʱ����Ϊ��",
							this.getId(), soa_processid, new java.util.Date(),
							null));
			log.error(processInfo + ",�޸��û�,ȱ���������");
			return false;
		}
		return true;
	}
}
