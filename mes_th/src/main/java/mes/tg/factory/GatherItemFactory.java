package mes.tg.factory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import mes.framework.DataBaseType;
import mes.system.dao.DAOFactoryAdapter;
import mes.tg.bean.GatherItem;
import mes.tg.dao.IDAO_GatherItem;

public class GatherItemFactory {
	
	//��־
	private final Log log = LogFactory.getLog(GatherItemFactory.class);

	/**
	 * ����GatherItem����
	 * 
	 * @param item
	 * @param con
	 * @throws SQLException
	 */
	public void saveGatherItem(GatherItem item, Connection con)
			throws SQLException {
		IDAO_GatherItem dao_GatherItem = (IDAO_GatherItem) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						IDAO_GatherItem.class);
		Statement stmt = con.createStatement();
		log.debug("�����ɼ�������SQL��䣺"+dao_GatherItem.saveGatherItem(item));
		stmt.execute(dao_GatherItem.saveGatherItem(item));
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}
	}

	/**
	 * ͨ�����ɾ��GatherItem����
	 * 
	 * @param id
	 * @param con
	 * @throws SQLException
	 */
	public void delGatherItemById(int id, Connection con) throws SQLException {
		IDAO_GatherItem dao_GatherItem = (IDAO_GatherItem) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						IDAO_GatherItem.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ���ɼ������Ժ�ɾ���ɼ�������SQL��䣺"+dao_GatherItem.delGatherItemById(id));
		stmt.execute(dao_GatherItem.delGatherItemById(id));
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}
	}

	/**
	 * ͨ�����ȡ��GatherItem����
	 * 
	 * @param id
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public GatherItem getGatherItemById(int id, Connection con)
			throws SQLException {
		IDAO_GatherItem dao_GatherItem = (IDAO_GatherItem) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						IDAO_GatherItem.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ���ɼ������ԺŲ�ѯ�ɼ�������SQL��䣺"+dao_GatherItem.getGatherItemById(id));
		ResultSet rs = stmt.executeQuery(dao_GatherItem.getGatherItemById(id));
		GatherItem gi = null;
		if (rs.next()) {
			gi = new GatherItem();
			gi.setId(rs.getInt("int_id"));
			gi.setGatherId(rs.getInt("int_gatherid"));
			gi.setOrder(rs.getInt("int_order"));
			gi.setMaterielruleId(rs.getInt("int_materielruleid"));
			log.debug("�ɼ������Ժţ�"+rs.getInt("int_id")+"���ɼ���ţ�"+rs.getInt("int_gatherid")+"���ɼ�������˳��ţ�"
					+rs.getInt("int_order")+"���ɼ����������ϱ�ʶ����ţ�"+rs.getInt("int_materielruleid"));
		}
		if(stmt!=null){
			stmt.close();
			stmt=null;
		}
		return gi;
	}

	/**
	 * ͨ��Gather���ȡ��GatherItem�б�
	 * 
	 * @param id
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public List<GatherItem> getGatherItemByGid(int id, Connection con)
			throws SQLException {
		IDAO_GatherItem dao_GatherItem = (IDAO_GatherItem) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						IDAO_GatherItem.class);
		List<GatherItem> list = new ArrayList<GatherItem>();
		Statement stmt = con.createStatement();
		log.debug("ͨ���ɼ���Ų�ѯ�ɼ�������SQL��䣺"+dao_GatherItem.getGatherItemByGid(id));
		ResultSet rs = stmt.executeQuery(dao_GatherItem.getGatherItemByGid(id));
		log.debug("ͨ���ɼ���Ų�ѯ�ɼ��������б�--");
		while (rs.next()) {
			GatherItem gi = new GatherItem();
			gi.setId(rs.getInt("int_id"));
			gi.setGatherId(rs.getInt("int_gatherid"));
			gi.setOrder(rs.getInt("int_order"));
			gi.setMaterielruleId(rs.getInt("int_materielruleid"));
			log.debug("�ɼ������Ժţ�"+rs.getInt("int_id")+"���ɼ���ţ�"+rs.getInt("int_gatherid")+"���ɼ�������˳��ţ�"
					+rs.getInt("int_order")+"���ɼ����������ϱ�ʶ����ţ�"+rs.getInt("int_materielruleid"));
			list.add(gi);
		}
		if(stmt!=null){
			stmt.close();
			stmt=null;
		}
		return list;
	}

	/**
	 * ͨ��Gather��ţ�ɾ��������GatherItem
	 * 
	 * @param id
	 * @param con
	 * @throws SQLException
	 */
	public void delGatherItemByGid(int id, Connection con) throws SQLException {
		IDAO_GatherItem dao_GatherItem = (IDAO_GatherItem) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						IDAO_GatherItem.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ���ɼ����ɾ���ɼ�������SQL��䣺"+dao_GatherItem.delGatherItemByGid(id));
		stmt.execute(dao_GatherItem.delGatherItemByGid(id));
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}
	}

	/**
	 * ����GatherItem����
	 * 
	 * @param item
	 * @param con
	 * @throws SQLException
	 */
	public void updateGatherItem(GatherItem item, Connection con)
			throws SQLException {
		IDAO_GatherItem dao_GatherItem = (IDAO_GatherItem) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						IDAO_GatherItem.class);
		Statement stmt = con.createStatement();
		log.debug("���²ɼ�������SQL��䣺"+dao_GatherItem.saveGatherItem(item));
		stmt.execute(dao_GatherItem.updateGatherItem(item));
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}
	}

	/**
	 * ���������Ԫ���Ƿ��Ѿ����ڴ�˳���
	 * @param gatherid
	 * @param order
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public int checkGatherItemByOrder(int gatherid, int order, Connection con)
			throws SQLException {
		IDAO_GatherItem dao_GatherItem = (IDAO_GatherItem) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						IDAO_GatherItem.class);
		Statement stmt = con.createStatement();
		int count = 0;
		log.debug("��ѯ�òɼ���š���˳��Ųɼ������Ը���SQL��䣺"+
				dao_GatherItem.checkGatherItemCountByorder(gatherid, order));
		ResultSet rs = stmt.executeQuery(dao_GatherItem
				.checkGatherItemCountByorder(gatherid, order));
		if (rs.next()) {
			count = rs.getInt(1);
			log.debug("��ѯ�òɼ���š���˳��Ųɼ������Ը���Ϊ��"+count);
		}
		if(stmt!=null){
			stmt.close();
			stmt=null;
		}
		return count;
	}
	
	/**
	 * ���������Ԫ���Ƿ���ڴ������ϱ�ʶ�����
	 * @param gatherid
	 * @param materialId
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public int checkGatherItemBySubMaterialId(int gatherid, int materialId,
			Connection con) throws SQLException {
		IDAO_GatherItem dao_GatherItem = (IDAO_GatherItem) DAOFactoryAdapter
		.getInstance(DataBaseType.getDataBaseType(con),
				IDAO_GatherItem.class);
		Statement stmt = con.createStatement();
		int count = 0;
		log.debug("��ѯ�òɼ���š������ϱ�ʶ����ŵĲɼ������Ը���SQL��䣺"+
				dao_GatherItem.checkGatherItemCountBySubMaterialId(gatherid, materialId));
		ResultSet rs = stmt.executeQuery(dao_GatherItem
				.checkGatherItemCountBySubMaterialId(gatherid, materialId));
		if (rs.next()) {
			count = rs.getInt(1);
			log.debug("��ѯ�òɼ���š���˳��Ųɼ������Ը���Ϊ��"+count);
		}
		if(stmt!=null){
			stmt.close();
			stmt=null;
		}
		return count;
	}

}
