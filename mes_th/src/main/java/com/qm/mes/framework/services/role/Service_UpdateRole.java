package com.qm.mes.framework.services.role;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.AdapterService;
import com.qm.mes.framework.DataBaseType;
import com.qm.mes.framework.ExecuteResult;
import com.qm.mes.framework.IMessage;
import com.qm.mes.framework.IProcess;
import com.qm.mes.framework.ProcessFactory;
import com.qm.mes.framework.ServiceException;
import com.qm.mes.framework.ServiceExceptionType;
import com.qm.mes.framework.dao.DAOFactory_UserManager;
import com.qm.mes.framework.dao.IDAO_UserManager;

public class Service_UpdateRole extends AdapterService {
	private Connection con = null;

	private String rolename = null;

	private String rank = null;

	private String powerstring = null;

	private String welcomepage = null;

	private String userid = null;

	private String note = null;

	private String roleno = null;

	private final Log log = LogFactory.getLog(Service_UpdateRole.class);

	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");

		roleno = message.getUserParameterValue("roleno");
		rolename = message.getUserParameterValue("rolename");
		rank = message.getUserParameterValue("rank");
		powerstring = message.getUserParameterValue("powerstring");
		welcomepage = message.getUserParameterValue("welcomepage");
		userid = message.getUserParameterValue("userid");
		note = message.getUserParameterValue("note");
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		String debug_UpdateRole = "rolename = " + rolename + "\n" + "rank = "
				+ rank + "\n" + "powerstring = " + powerstring + "\n"
				+ "welcomepage = " + welcomepage + "\n" + "userid = " + userid
				+ "\n" + "note = " + note + "\n";
		log.debug(processInfo + ",�޸Ľ�ɫ" + debug_UpdateRole);

		if (roleno == null || rolename == null || rank == null
				|| powerstring == null || welcomepage == null || userid == null
				|| note == null || con == null) {
			log.error(processInfo + ",�޸Ľ�ɫ,ȱ���������");
			return false;
		}
		return true;
	}

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		IDAO_UserManager daor = DAOFactory_UserManager.getInstance(DataBaseType
				.getDataBaseType(con));
		Statement st = con.createStatement();
		
//		 ���˲����������
		Map<String, String> data = new HashMap<String, String>();
		String map_rolename = null;
		String map_rank = null;
		String map_powerstring = null;
		String map_welcomepage = null;
		String map_userid = null;
		String map_note = null;
		String sql_selectRole = daor.getSQL_QueryRoleForRoleNo(roleno);
		log.debug(processInfo + "" + sql_selectRole);
		ResultSet rs = st.executeQuery(sql_selectRole);
		while (rs.next()) {
			map_rolename = rs.getString(2);
			map_rank = rs.getString(3);
			map_powerstring = rs.getString(4);
			map_welcomepage = rs.getString(5);
			map_userid = rs.getString(6);
			map_note = rs.getString(8);
		}
		String rs_debug = "rs_rolename = " + map_rolename + "\n" + "rs_rank = "
				+ map_rank + "\n" + "rs_powerstring = " + map_powerstring
				+ "\n" + "rs_welcomepage = " + map_welcomepage + "\n"
				+ "rs_userid = " + map_userid + "\n" + "rs_note = " + map_note;
		log.debug(processInfo + "�˽�ɫ�ŷ��ص�������Ϣ: rs_debug = " + rs_debug);
		data.put("map_roleno", roleno);
		data.put("map_rolename", map_rolename);
		data.put("map_rank", map_rank);
		data.put("map_powerstring", map_powerstring);
		data.put("map_welcomepage", map_welcomepage);
		data.put("map_userid", map_userid);
		data.put("map_note", map_note);
		message.setOtherParameter(this.getClass().getName(), data);
		
		log.debug(processInfo
				+ ",���½�ɫ�����ݽ�ɫ�Ÿ���������ɫ��Ϣ��sql���: sql = "
				+ daor.getSQL_UpdateRole(roleno, rolename, rank, powerstring,
						welcomepage, userid, note));
		synchronized (this) {
			st.executeUpdate(daor.getSQL_UpdateRole(roleno, rolename, rank,
					powerstring, welcomepage, userid, note));
		}
		st.close();
		return ExecuteResult.sucess;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ExecuteResult undoAdapterService(IMessage message, String soa_processid) {
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		Object obj = message.getOtherParameter(this.getClass().getName());
		if (obj instanceof Map) {
			Map<String, String> map = (Map<String, String>) obj;
			String map_roleno = map.get("map_roleno");
			String map_rolename = map.get("map_rolename");
			String map_rank = map.get("map_rank");
			String map_powerstring = map.get("map_powerstring");
			String map_welcomepage = map.get("map_welcomepage");
			String map_userid = map.get("map_userid");
			String map_note = map.get("map_note");
			String map_debug = "map_roleno = " + map_roleno + "\n"
					+ "map_rolename = " + map_rolename + "\n" + "rs_rank = "
					+ map_rank + "\n" + "map_powerstring = " + map_powerstring
					+ "\n" + "map_welcomepage = " + map_welcomepage + "\n"
					+ "map_userid = " + map_userid + "\n" + "map_note = "
					+ map_note;
			log.debug(processInfo + "�˽�ɫ�ŷ��ص�������Ϣ: map_debug = " + map_debug);
			try {
				IDAO_UserManager daor = DAOFactory_UserManager
						.getInstance(DataBaseType.getDataBaseType(con));
				Statement st = con.createStatement();
				synchronized (this) {
				String undo_sql_update = daor.getSQL_UpdateRole(map_roleno, map_rolename, map_rank, map_powerstring,
						map_welcomepage, map_userid, map_note);
				log.debug("�޸Ľ�ɫ��Ϣ���˲�����sql���: undo_sql_update = " + undo_sql_update);
				st.executeUpdate(undo_sql_update);
				}
				st.close();
			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "�ύ����", this
								.getId(), soa_processid, new Date(), sqle));
				log.fatal(processInfo + ",�޸Ľ�ɫ���˲����ύ����" + sqle.toString());
			}
		}
		return ExecuteResult.sucess;
	}

	@Override
	public void relesase() throws SQLException {
		roleno = null;
		rolename = null;
		rank = null;
		powerstring = null;
		welcomepage = null;
		userid = null;
		note = null;
		con = null;
	}

}
