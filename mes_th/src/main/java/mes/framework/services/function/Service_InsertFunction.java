package mes.framework.services.function;

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
import mes.framework.dao.DAOFactory_UserManager;
import mes.framework.dao.IDAO_UserManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ������ӷ�������Ϣ ������������Ҫ��otherparameter�з���һ��Connection������Ϊcon��<br>
 * ��Ҫ���û�������������к���functionname,url,upfunctionid,state,note,safemarkcode,nodetype,rank,userid,enableֵ����ʹ�ǿ��ַ�����
 * <br>
 * ִ�� doService() <br>
 * ��ʼ�� initFordo() <br>
 * ���� undoService() <br>
 * ����������
 * 
 * @author ������ 2008-01-02
 */
public class Service_InsertFunction extends DefService implements IService {

	// ���ݿ�����
	private Connection con = null;

	// ��������
	private String functionname = null;

	// �ļ�url
	private String url = null;

	// �����ܺ�
	private String upfunctionid = null;

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

	// ϵͳ��ʶ
	private String enable = null;

	// ����˳��
	private String flo_Order = null;

	public Service_InsertFunction() {
		super();
	}

	private final Log log = LogFactory.getLog(Service_InsertFunction.class);

	public synchronized ExecuteResult doService(IMessage message,
			String soa_processid) {
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		// ��ʼ���������в�������ʧ�ܣ�����ִ�н��
		if (!initFordo(message, soa_processid)) {
			log.error(processInfo + ",��ӹ���,��ʼ����������ʧ��");
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
					log.error(processInfo + ",��ӹ���,�������ݿ����,δ֪�����ݿ�����");
					return ExecuteResult.fail;
				}
				log.debug(processInfo + ",��ӹ���,�������ݿ�ɹ�");
				int uid = 0;// �û�ID
				int upfid = 0;// �����ܺ�
				uid = Integer.parseInt(userid);
				upfid = Integer.parseInt(upfunctionid);

				stmt = con.createStatement();
				// ���ɹ��ܺ�
				String sql_create = dao.getSQL_QueryNextFuntionId();
				log.debug("���ɹ��ܺŵ�sql���: sql_create = " + sql_create);
				rs = stmt.executeQuery(sql_create);
				int fid = 0;// ���ܺ�
				if (rs.next()) {
					fid = rs.getInt(1);
				}
				int length = 0;
				// ��ȡȨ�޴�����
				String sql_get = dao.getSQL_QueryPowerStringLength();
				log.debug("��ȡϵͳ��Ȩ�޴����ȵ�sql���: sql_get = " + sql_get);
				rs = stmt.executeQuery(sql_get);
				if (rs.next()) {
					length = rs.getInt(1);
				}
				String appendString = "";
				if (length < fid) {
					for (int i = length + 1; i <= fid; i++) {
						appendString = appendString + "0";
					}
				}
				log.debug(processInfo + ",��ӹ��ܵĹ��ܴ�Ϊ: appendString = "
						+ appendString);
				// ��ӹ��ܻ��˲��������������Map��
				Map<String, String> data = new HashMap<String, String>();
				data.put("map_userid", String.valueOf(uid));
				data.put("map_functionid", String.valueOf(fid));
				data.put("map_appendString", appendString);
				message.setOtherParameter(this.getClass().getName(), data);

				// ��ӹ���
				String sql_insert = dao.getSQL_InsertFunction(fid,
						functionname, nodetype, url, upfid, rank, state, uid,
						safemarkcode, note, enable, Float.valueOf(flo_Order));
				log.debug("��ӹ��ܵ�sql���: sql_insert = " + sql_insert);
				// ����Ȩ�޴�
				String sql_update = dao
						.getSQL_UpdatePowerStringWhenAdd(appendString);
				log.debug("��ӹ��ܺ����Ȩ�޴���sql���: sql_update = " + sql_update);
				con.setAutoCommit(false);
				stmt.executeUpdate(sql_insert);
				stmt.executeUpdate(sql_update);
				con.commit();
				con.setAutoCommit(true);

			} catch (SQLException sqle) {
				con.rollback();
				con.setAutoCommit(true);
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), soa_processid, new Date(), sqle));
				log.fatal(processInfo + ",��ӹ���,���ݿ�����쳣" + sqle.toString());
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
			log.fatal(processInfo + ",��ӹ���,δ֪�쳣" + e.toString());
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
			int map_uid = Integer.parseInt(map.get("map_userid"));
			int map_functionid = Integer.parseInt(map.get("map_functionid"));
			String map_appendString = map.get("map_appendString");
			String map_debug = "map_uid = " + map_uid + "\n"
					+ "map_functionid = " + map_functionid + "\n"
					+ "map_appendString = " + map_appendString;
			log.debug(processInfo + "��ӹ��ܻ��˲���Map���յ���ֵ: map_debug = "
					+ map_debug);
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
						log.error(processInfo + ",��ӹ��ܻ��˲���,�������ݿ����,δ֪�����ݿ�����");
						return ExecuteResult.fail;
					}
					log.debug(processInfo + ",��ӹ��ܻ��˲���,�������ݿ�ɹ�");
					stmt = con.createStatement();
					String sql_delete = dao
							.getSQL_DeleteFunction(map_functionid);
					log.debug(processInfo + ",��ӹ��ܻ��˲�����sql���: sql_delete = "
							+ sql_delete);
					String sql_update = dao.getSQL_UpdatePowerStringWhenDelete(
							map_uid, String.valueOf(map_functionid));
					log.debug(processInfo
							+ ",��ӹ��ܻ��˲���ʱ����Ȩ�޴���sql���: sql_update = "
							+ sql_update);
					con.setAutoCommit(false);
					stmt.executeUpdate(sql_delete);
					stmt.executeUpdate(sql_update);
					con.commit();
					con.setAutoCommit(true);
				} catch (SQLException sqle) {
					con.rollback();
					con.setAutoCommit(true);
					message.addServiceException(new ServiceException(
							ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
									.getId(), soa_processid, new Date(), sqle));
					log.fatal(processInfo + ",��ӹ��ܻ��˲���,���ݿ�����쳣"
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
				log.fatal(processInfo + ",��ӹ��ܻ��˲���,δ֪�쳣" + e.toString());
				return ExecuteResult.fail;
			}
		}
		return ExecuteResult.sucess;
	}

	private boolean initFordo(IMessage message, String soa_processid) {
		// ���ݿ�����
		con = null;
		// ��������
		functionname = null;
		// �ļ�url
		url = null;
		// �����ܺ�
		upfunctionid = null;
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
		// ����Ա
		userid = null;
		// ϵͳ��ʶ
		enable = null;
		// ����˳��
		flo_Order = null;

		// ��ȡ�÷�������������ֵ
		con = (Connection) message.getOtherParameter("con");
		functionname = message.getUserParameterValue("functionname");
		nodetype = message.getUserParameterValue("nodetype");
		url = message.getUserParameterValue("url");
		upfunctionid = message.getUserParameterValue("upfunctionid");
		safemarkcode = message.getUserParameterValue("safemarkcode");
		note = message.getUserParameterValue("note");
		state = message.getUserParameterValue("state");
		rank = message.getUserParameterValue("rank");
		userid = message.getUserParameterValue("userid");
		enable = message.getUserParameterValue("enable");
		flo_Order = message.getUserParameterValue("flo_Order");
		
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(soa_processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		String debug_Service_InsertFunction = "������: functionname = "
				+ functionname + "\n" + "�ļ�url: url = " + url + "\n"
				+ "�����ܺ�: upfunctionid = " + upfunctionid + "\n"
				+ "״̬: state = " + state + "\n" + "��ע: note = " + note + "\n"
				+ "���ʰ�ȫ���: safemarkcode = " + safemarkcode + "\n"
				+ "�ڵ�����: nodetype = " + nodetype + "\n" + "����: rank = " + rank
				+ "\n" + "����Ա: userid = " + userid + "\n" + "ϵͳ��ʶ: enable = "
				+ enable + "\n" + "����˳��: flo_Order = " + flo_Order + "\n";
		log.debug(processInfo + ",��ӹ���ʱ�û��ύ�Ĳ���: "
				+ debug_Service_InsertFunction);

		if (functionname == null || nodetype == null || url == null
				|| upfunctionid == null || safemarkcode == null || note == null
				|| state == null || rank == null || userid == null
				|| enable == null || con == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "ȱ���������", this.getId(),
					soa_processid, new java.util.Date(), null));
			log.error(processInfo + ",��ӹ���,ȱ���������");
			return false;
		}
		return true;
	}
}
