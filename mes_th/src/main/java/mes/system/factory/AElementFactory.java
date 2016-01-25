package mes.system.factory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import mes.framework.DataBaseType;
import mes.system.dao.DAOFactoryAdapter;
import mes.system.dao.IDAO_Element;
import mes.system.elements.IElement;

/**
 * Ԫ�ع���<br>
 * �����������е�Ԫ�ض��Ǵ�IElement�ӿڵ������ͣ�<br>
 * ����������ʵ�ֹ��ܵ�ʱ��Ҳ����漰����IElement�Ĳ�����<br>
 * ���ｫ��Щ�������в�д�ɳ����๩����������ʹ�ã��ﵽ���õ�Ч����
 * 
 * @author �Ź��� 2008-3-6
 */
abstract class AElementFactory<element extends IElement> implements
		IElementFactory<element> {

	public boolean deleteElement(element e, Connection con) throws SQLException {
		return deleteElement(e.getName(), con);
	}

	public boolean deleteElement(int id, Connection con) throws SQLException {
		// ����������͵�DAO����
		IDAO_Element dao = (IDAO_Element) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), IDAO_Element.class);
		if (doUpdateStatement(dao.getSQL_deleteElement(id), con) == 0)
			return false;// Ŀ��Ԫ�ز����ڡ�
		return true;
	}

	public boolean deleteElement(String name, Connection con)
			throws SQLException {
		// ����������͵�DAO����
		IDAO_Element dao = (IDAO_Element) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), IDAO_Element.class);
		if (doUpdateStatement(dao.getSQL_deleteElement(name), con) == 0)
			return false;// Ŀ��Ԫ�ز����ڡ�
		return true;
	}

	public element queryElement(int id, Connection con) throws SQLException {
		return null;
	}

	public element queryElement(String name, Connection con)
			throws SQLException {
		element element = createElement();
		IDAO_Element dao = (IDAO_Element) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), IDAO_Element.class);
		String sql = dao.getSQL_queryElement(name);
		Statement st = con.createStatement();
		ResultSet set = st.executeQuery(sql);
		if (set.next()) {
			element.setId(set.getInt("id"));
			element.setName(set.getString("name"));
			element.setDiscard(set.getInt("discard") == 1 ? true : false);
			element.setDescription(set.getString("description"));
			element.setUpdateDateTime(set.getDate("updateDateTime"));
			element.setUpdateUserId(set.getInt("updateUserId"));
			element.setVersion(set.getInt("version"));
		}
		st.close();
		return element;
	}

	public final boolean save(element obj, Connection con) throws SQLException {
		IDAO_Element dao = (IDAO_Element) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), IDAO_Element.class);
		return doTrans(obj, dao.getSQL_innerElement(obj), con,true);
	}

	/**
	 * ���������������<b><font color="ff0000">����</font></b>����õ����ڴ洢Ԫ�������Ϣ�ķ�����
	 * <p>
	 * <b><font color="ff0000">����һ��Ҫע���ͷ���Դ���мǡ�</font></b>
	 * @param e
	 *            Ҫ�洢��Ԫ��
	 * @param con
	 *            ���ݿ�����
	 * @return ���ط�������״̬
	 * @throws SQLException
	 *@deprecated <font color="ff0000">�˷����������ⲿ���ã���ʹ��save������</font> 
	 */
	abstract boolean saveElement(element e, Connection con)
			throws SQLException;

	/**
	 * @deprecated <font color="ff0000">�˷����������ⲿ���ã���ʹ��update������</font>
	 */
	abstract boolean updateElement(element e, Connection con)
			throws SQLException;

	protected void initObjectFromResultSet(element element, ResultSet set)
			throws SQLException {
		element.setId(set.getInt("id"));
		element.setName(set.getString("name"));
		element.setDescription(set.getString("description"));
		element.setDiscard(set.getInt("discard") == 1 ? true : false);
		element.setUpdateDateTime(set.getDate("updatedatetime"));
		element.setUpdateUserId(set.getInt("updateuserid"));
		element.setVersion(set.getInt("version"));
	}

	protected int doUpdateStatement(String sql, Connection con)
			throws SQLException {
		Statement st = con.createStatement();
		int i = st.executeUpdate(sql);
		st.close();
		return i;
	}

	public final boolean update(element obj, Connection con)
			throws SQLException {
		IDAO_Element dao = (IDAO_Element) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), IDAO_Element.class);
		return doTrans(obj, dao.getSQL_updateElement(obj), con,false);
	}

	@SuppressWarnings("deprecation")
	private synchronized boolean doTrans(element obj, String sql, Connection con,
			boolean isSave) throws SQLException {
		boolean isReleaseLock = false;
		try {
			if (con.getAutoCommit()) { // ����ǰ����û������������ƣ����ֶ�����
				isReleaseLock = true;
				con.setAutoCommit(false);
			}// �����ǶԱ�Ĳ���
			doUpdateStatement(sql, con);
			if (isSave)
				saveElement(obj, con);
			else
				updateElement(obj, con);
			if (isReleaseLock)// ����������ͷ������
				con.commit();// �ύ����
			return true;
		} catch (SQLException e) {
			con.rollback();
			throw e;
		} finally {
			if (isReleaseLock)
				try {
					con.setAutoCommit(true);
				} catch (Exception e) {
				}
		}
	}
}
