package mes.framework;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Map;

import common.Conn_MES;
import mes.framework.dao.DAOFactory_Core;
import mes.framework.dao.IDAO_Core;

/**
 * ���̹���
 * 
 * @author �Ź��� 2007-6-21
 */
public final class ProcessFactory {
	private static Map<String, IProcess> pmap = new Hashtable<String, IProcess>();

	/**
	 * ����ָ��id������
	 * 
	 * @param processid
	 *            ����id
	 * @return ����id�����ڵĻ�����null
	 */
	public static IProcess getInstance(String processid) {
		return getInstance(processid, true);
	}

	private static IProcess getInstance(String processid, boolean b) {
		if (b) {
			IProcess process = pmap.get(processid);
			if (process != null)
				return process;
		}
		Connection con = null;
		try {
			con = (new Conn_MES()).getConn();
			IDAO_Core daoprocess = DAOFactory_Core.getInstance(DataBaseType
					.getDataBaseType(con));

			return loadProcess(daoprocess.getSQL_QueryProcess(processid), con,
					daoprocess);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return null;
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException sqle) {
			}
		}
	}

	private static IProcess loadProcess(String sql, Connection con,
			IDAO_Core daoprocess) throws SQLException {
		Statement st_base = con.createStatement();
		Statement st_item = con.createStatement();
		ResultSet set = st_base.executeQuery(sql);
		DefProcess temp = null;
		while (set.next()) {
			temp = new DefProcess();
			temp.setId(set.getString("NPROCESSID"));
			temp.setName(set.getString("CPROCESSNAME"));
			temp.setDescr(set.getString("CDESCRIPTION"));
			temp.setNameSpace(set.getString("NNAMESPACEID"));

			ResultSet set_item = st_item.executeQuery(daoprocess
					.getSQL_QueryProcessItem(temp.getId()));
			// Ϊÿһ�������������
			while (set_item.next())
				temp.addProcessItem(new DefProcessItem(set_item
						.getString("NSERVERID"), set_item
						.getString("CALIASNAME"), set_item.getInt("NSID"),
						ExceptionDispose.getInstance(set_item
								.getString("cdescription"))));
			pmap.put(temp.getId(), temp);
		}
		st_item.close();
		st_base.close();
		return temp;
	}

	/**
	 * Ӧ�����ݿ����Ӽ�����������
	 * 
	 * @param con
	 *            ���ݿ�����
	 * @throws SQLException
	 */
	static void loadAllProcess(Connection con) throws SQLException {
		IDAO_Core daoprocess = DAOFactory_Core.getInstance(DataBaseType
				.getDataBaseType(con));

		loadProcess(daoprocess.getSQL_QueryAllProcess(), con, daoprocess);
	}
}
