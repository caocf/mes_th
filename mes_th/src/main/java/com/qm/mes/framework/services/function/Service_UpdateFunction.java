package com.qm.mes.framework.services.function;

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
import com.qm.mes.framework.dao.DAOFactory_UserManager;
import com.qm.mes.framework.dao.IDAO_UserManager;

/**
 * ���񣺸��¹��� ������������Ҫ��otherparameter�з���һ��Connection������Ϊcon��<br>
 * ��Ҫ���û�������������к���functionid,functionname,url,state,note,safemarkcode,nodetype,rank,userid
 * ֵ����ʹ�ǿ��ַ����� <br>
 * ִ�� doService() <br>
 * ��ʼ�� initFordo() <br>
 * ���� undoService() <br>
 * ����������
 * 
 * @author ������ 2008-01-02
 */
public class Service_UpdateFunction extends DefService implements IService {

	// ���ݿ�����
	private Connection con = null;

	// ���ܺ�
	private String functionid = null;

	// ��������
	private String functionname = null;

	// �ļ�url
	private String url = null;

	// ״̬
	private String state = null;

	// ��ע
	private String note = null;

	// ���ʰ�ȫ���
	private String safemarkcode = null;

	// �ڵ�����
	private String nodetype = null;

	// ����
	private String rank = null;

	// �û�ID
	private String userid = null;

	// ����˳���
	private String flo_Order = null;

	// �����ܺ�
	private String upfunctionid = null;

	public Service_UpdateFunction() {
		super();
	}

	private final Log log = LogFactory.getLog(Service_UpdateFunction.class);

	public ExecuteResult doService(IMessage message, String soa_processid) {
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		// ��ʼ���������в�������ʧ�ܣ�����ִ�н��
		if (!initFordo(message, soa_processid)) {
			log.error(processInfo + ",�޸Ĺ���,��ʼ����������ʧ��");
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
							ServiceExceptionType.UNKNOWNDATABASETYPE,
							"δ֪�����ݿ�����", this.getId(), soa_processid,
							new Date(), null));
					log.error(processInfo + ",�޸Ĺ���,�������ݿ����,δ֪�����ݿ�����");
					return ExecuteResult.fail;
				}
				log.debug(processInfo + ",�޸Ĺ���,�������ݿ�ɹ�");
				stmt = con.createStatement();
				String sql_update = "";
				int fid = Integer.parseInt(functionid);
				int uid = Integer.parseInt(userid);
				if (nodetype.equals("3")) {// ���¹���(���ڵ�ΪҶ�ڵ�ʱ)
					sql_update = dao.getSQL_UpdateFunctionWhenLeaf(fid,
							functionname, url, state, note, safemarkcode, uid,
							rank, Float.valueOf(flo_Order),Integer.parseInt(upfunctionid));
					log.debug("���¹���(���ڵ�ΪҶ�ڵ�ʱ)��sql���: sql_update = "
							+ sql_update);
				} else {
					// ���¹���(���ڵ�Ϊ��Ҷ�ڵ�ʱ)
					sql_update = dao.getSQL_UpdateFunctionWhenNotLeaf(fid,
							functionname, note, uid, rank, Float
									.valueOf(flo_Order),new Integer(upfunctionid));
					log.debug("���¹���(���ڵ�Ϊ��Ҷ�ڵ�ʱ)��sql���: sql_update = "
							+ sql_update);
				}

				/*
				 * ��ѯ�˹��ܺŵ�������Ϣ����Map��
				 */
				Map<String, String> data = new HashMap<String, String>();
				String map_functionid = null;
				String map_functionname = null;
				String map_url = null;
				String map_rank = null;
				String map_nodetype = null;
				String map_state = null;
				String map_safemarkcode = null;
				String map_note = null;
				String map_userid = null;
				String map_flo_Order = null;
				String map_upfunctionid = null;
				String map_sql_select = dao
						.getSQL_QueryFuncitonInfoForFunctionID(this.functionid);
				log.debug(processInfo
						+ ",�޸Ĺ��ܻ��˲�����������в�����sql���: map_sql_select = "
						+ map_sql_select);
				rs = stmt.executeQuery(map_sql_select);
				while (rs.next()) {
					map_functionid = rs.getString(1);
					map_functionname = rs.getString(2);
					map_url = rs.getString(3);
					map_rank = rs.getString(5);
					map_nodetype = rs.getString(6);
					map_state = rs.getString(7);
					map_safemarkcode = rs.getString(8);
					map_note = rs.getString(9);
					map_userid = rs.getString(11);
					map_flo_Order = rs.getString(12);
					map_upfunctionid = rs.getString(4);
				}
				String map_debug = "map_functionid = " + map_functionid + "\n"
						+ "map_functionname = " + map_functionname + "\n"
						+ "map_url = " + map_url + "\n" + "map_rank = "
						+ map_rank + "\n" + "map_nodetype = " + map_nodetype
						+ "\n" + "map_state = " + map_state + "\n"
						+ "map_safemarkcode = " + map_safemarkcode + "\n"
						+ "map_note = " + map_note + "\n" + "map_userid = "
						+ map_userid + "\n" + "map_flo_Order = "
						+ map_flo_Order;
				log.debug(processInfo + ",�޸Ĺ��ܻ��˲�����ѯ�������в���Ϊ: map_debug = "
						+ map_debug);
				data.put("map_functionid", map_functionid);
				data.put("map_functionname", map_functionname);
				data.put("map_url", map_url);
				data.put("map_rank", map_rank);
				data.put("map_nodetype", map_nodetype);
				data.put("map_state", map_state);
				data.put("map_safemarkcode", map_safemarkcode);
				data.put("map_note", map_note);
				data.put("map_userid", map_userid);
				data.put("map_flo_Order", map_flo_Order);
				data.put("map_upfunctionid", map_upfunctionid);
				message.setOtherParameter(this.getClass().getName(), data);

				stmt.executeUpdate(sql_update);

			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), soa_processid, new Date(), sqle));
				log.fatal(processInfo + ",�޸Ĺ���,���ݿ�����쳣" + sqle.toString());
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
			log.fatal(processInfo + ",�޸Ĺ���,δ֪�쳣" + e.toString());
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
			String map_rank = map.get("map_rank");
			String map_nodetype = map.get("map_nodetype");
			String map_state = map.get("map_state");
			String map_safemarkcode = map.get("map_safemarkcode");
			String map_note = map.get("map_note");
			String map_userid = map.get("map_userid");
			String map_flo_Order = map.get("map_flo_Order");
			String map_upfunctionid = map.get("map_upfunctionid");
			String debug_map = "map_functionid = " + map_functionid + "\n"
					+ "map_functionname = " + map_functionname + "\n"
					+ "map_url = " + map_url + "\n" + "map_rank = " + map_rank
					+ "\n" + "map_nodetype = " + map_nodetype + "\n"
					+ "map_state = " + map_state + "\n" + "map_safemarkcode = "
					+ map_safemarkcode + "\n" + "map_note = " + map_note + "\n"
					+ "map_userid = " + map_userid;
			log.debug(processInfo + ",�޸Ĺ��ܻ��˲�����ѯ�������в���Ϊ: map_debug = "
					+ debug_map);
			try {
				Statement stmt = null;
				try {
					IDAO_UserManager dao = DAOFactory_UserManager
							.getInstance(DataBaseType.getDataBaseType(con));
					if (dao == null) {
						message.addServiceException(new ServiceException(
								ServiceExceptionType.UNKNOWNDATABASETYPE,
								"δ֪�����ݿ�����", this.getId(), soa_processid,
								new Date(), null));
						log.error(processInfo + ",�޸Ĺ��ܻ��˲���,�������ݿ����,δ֪�����ݿ�����");
						return ExecuteResult.fail;
					}
					log.debug(processInfo + ",�޸Ĺ��ܻ��˲���,�������ݿ�ɹ�");
					stmt = con.createStatement();
					String sql_update = "";
					if (nodetype.equals("3")) {// ���¹���(���ڵ�ΪҶ�ڵ�ʱ)
						sql_update = dao.getSQL_UpdateFunctionWhenLeaf(Integer
								.parseInt(map_functionid), map_functionname,
								map_url, map_state, map_note, map_safemarkcode,
								Integer.parseInt(map_userid), rank, Float
										.valueOf(map_flo_Order),Integer.valueOf(map_upfunctionid));
						log
								.debug("���¹��ܻ��˲���(���ڵ�ΪҶ�ڵ�ʱ)��sql���: undo_sql_update = "
										+ sql_update);
					} else {
						// ���¹���(���ڵ�Ϊ��Ҷ�ڵ�ʱ)
						sql_update = dao.getSQL_UpdateFunctionWhenNotLeaf(
								Integer.parseInt(map_functionid),
								map_functionname, map_note, Integer
										.parseInt(map_userid), map_rank, Float
										.valueOf(map_flo_Order),Integer.valueOf(map_upfunctionid));
						log
								.debug("���¹��ܻ��˲���(���ڵ�Ϊ��Ҷ�ڵ�ʱ)��sql���: undo_sql_update = "
										+ sql_update);
					}
					stmt.executeUpdate(sql_update);
				} catch (SQLException sqle) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
									.getId(), soa_processid, new Date(), sqle));
					log.fatal(processInfo + ",�޸Ĺ��ܻ��˲���,���ݿ�����쳣"
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
				log.fatal(processInfo + ",�޸Ĺ��ܻ��˲���,δ֪�쳣" + e.toString());
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
		// ��������
		functionname = null;
		// �ļ�url
		url = null;
		// ״̬
		state = null;
		// ��ע
		note = null;
		// ���ʰ�ȫ���
		safemarkcode = null;
		// �ڵ�����
		nodetype = null;
		// ����
		rank = null;
		// �û�ID
		userid = null;
		// ����˳��
		flo_Order = null;
		// �����ܺ�
		upfunctionid = null;

		// ��ȡ�÷�������������ֵ
		con = (Connection) message.getOtherParameter("con");
		functionid = message.getUserParameterValue("functionid");
		functionname = message.getUserParameterValue("functionname");
		url = message.getUserParameterValue("url");
		state = message.getUserParameterValue("state");
		note = message.getUserParameterValue("note");
		safemarkcode = message.getUserParameterValue("safemarkcode");
		nodetype = message.getUserParameterValue("nodetype");
		rank = message.getUserParameterValue("rank");
		userid = message.getUserParameterValue("userid");
		flo_Order = message.getUserParameterValue("flo_Order");
		upfunctionid = message.getUserParameterValue("upnodeid");
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		String debug_Service_UpdateFunction = "���ܺ�: functionid = " + functionid
				+ "\n" + "��������: functionname = " + functionname + "\n"
				+ "�ļ�url: url = " + url + "\n" + "״̬: state = " + state + "\n"
				+ "��ע: state = " + state + "\n" + "���ʰ�ȫ���: safemarkcode = "
				+ safemarkcode + "\n" + "�ڵ�����: nodetype = " + nodetype + "\n"
				+ "����: rank = " + rank + "\n" + "�û�ID: userid = " + userid
				+ "\n" + "����˳��: flo_Order = " + flo_Order + "\n"
				+ "�����ܺ�: upfunctionid = " + upfunctionid + "\n";
		log.debug(processInfo + ",�޸Ĺ���ʱ�û��ύ�Ĳ���: "
				+ debug_Service_UpdateFunction);

		if (functionid == null || functionname == null || url == null
				|| state == null || note == null || safemarkcode == null
				|| nodetype == null || rank == null || userid == null
				|| upfunctionid == null || con == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "ȱ���������", this.getId(),
					soa_processid, new java.util.Date(), null));
			log.error(processInfo + ",�޸Ĺ���,ȱ���������");
			return false;
		}
		return true;
	}
}
