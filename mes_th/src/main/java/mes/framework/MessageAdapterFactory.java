package mes.framework;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Map;

import com.qm.mes.th.helper.Conn_MES;

import mes.framework.dao.DAOFactory_Core;
import mes.framework.dao.IDAO_Core;

/**
 * ��Ϣ����������
 * 
 * @author �Ź��� 2007-6-21
 */
public final class MessageAdapterFactory {
	private static Map<String, IMessageAdapter> mamap = new Hashtable<String, IMessageAdapter>();

	/**
	 * �������е�������
	 * 
	 * @param con
	 * @throws SQLException
	 */
	public static void loadAllMessageAdapter(Connection con) throws SQLException {
		IDAO_Core ma = DAOFactory_Core.getInstance(DataBaseType.getDataBaseType(con));
		loadMessageAdapter(ma.getSQL_QueryAllAdapterInfos(), con);
	}

	/**
	 * ������Ϊ��Ϣ�����<br>
	 * ͨ������id�ͷ���������ҵ���<br>
	 * �Բ���messageΪ����Դ����Ϣ�������
	 * 
	 * @param processid
	 *            ����id
	 * @param serviceName
	 *            �������
	 * @param message
	 *            ��Ϣ����
	 * @return ����������������ֱ�ӷ�����Ϣ����
	 */
	public static IMessage getMessage(String processid, String serviceName, IMessage message) {
		return getMessage(processid, serviceName, message, true);
	}

	private static IMessage getMessage(String processid, String serviceName, IMessage message, boolean b) {
		if (b) {
			IMessageAdapter adapter = mamap.get(processid + "." + serviceName);
			if (adapter != null) {
				adapter.setSource(message);
				return adapter;
			}
		}

		Connection con = null;
		try {
			con = new Conn_MES().getConn();
			IDAO_Core ma = DAOFactory_Core.getInstance(DataBaseType.getDataBaseType(con));

			IMessageAdapter message2 = loadMessageAdapter(ma.getSQL_QueryAdepterInfo(processid, serviceName), con);
			if (message2 == null) {
				message2 = new DefMessageAdapter(processid, serviceName);
			}
			message2.setSource(message);
			return message2;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException sqle) {
			}
		}
		return message;
	}

	private static IMessageAdapter loadMessageAdapter(String sql, Connection con) throws SQLException {
		Statement st_base = con.createStatement();
		ResultSet set = st_base.executeQuery(sql);
		DefMessageAdapter temp = null;
		// ���µĲ�����Ȼ���ã����иĽ��Ŀռ�
		while (set.next()) {
			String temp_sname = set.getString("cialiasname");
			String temp_pid = set.getString("nprocessid");

			if (temp == null)
				temp = new DefMessageAdapter(temp_pid, temp_sname);

			if (!temp.getProcessid().equals(temp_pid) || !temp.getServiceName().equals(temp_sname))
				temp = new DefMessageAdapter(temp_pid, temp_sname);

			String targetServiceName = set.getString("COALIASNAME");
			// ������������������
			if (targetServiceName == null || targetServiceName.trim().equals("")) {
				temp.setAdapterName(set.getString("ciparameter"), set.getString("COPARAMETER"));
			} else {
				temp.setAdapterName(set.getString("ciparameter"),
						targetServiceName + "." + set.getString("COPARAMETER"));
			}
			mamap.put(temp.getProcessid() + "." + temp.getServiceName(), temp);
		}
		st_base.close();
		return temp;
	}

}
