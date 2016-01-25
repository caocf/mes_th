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
import mes.tg.bean.Gather;
import mes.tg.dao.IDAO_Gather;
import mes.tg.dao.IDAO_GatherItem;

public class GatherFactory {
	
	//��־
	private final Log log = LogFactory.getLog(GatherFactory.class);
	
	/**
	 * ����gather
	 * @param gather
	 * @param con
	 * @throws SQLException
	 */
	public void saveGather(Gather gather,Connection con) throws SQLException{
		IDAO_Gather dao_Gather = (IDAO_Gather) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), IDAO_Gather.class);
		Statement stmt = con.createStatement();
		log.debug("�����ɼ���SQL��䣺"+dao_Gather.saveGather(gather));
		stmt.execute(dao_Gather.saveGather(gather));
		if(stmt!=null){
			stmt.close();
			stmt=null;
		}
	}
	
	/**
	 * ͨ����Ų�ѯgather
	 * @param id
	 * @param con
	 * @return
	 * @throws SQLException 
	 */
	public Gather getGatherById(int id,Connection con) throws SQLException{
		Gather g = null;
		IDAO_Gather dao_Gather = (IDAO_Gather) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), IDAO_Gather.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ���ɼ���Ż�ȡ�ɼ���SQL��䣺"+dao_Gather.getGatherById(id));
		ResultSet rs = stmt.executeQuery(dao_Gather.getGatherById(id));
		log.debug("ͨ���ɼ���Ż�ȡ�ɼ����б�---");
		while(rs.next()){
			g = new Gather();
			g.setId(rs.getInt("int_id"));
			g.setName(rs.getString("str_name"));
			g.setDesc(rs.getString("str_desc"));
			g.setProdunitId(rs.getInt("int_produnitid"));
			g.setMaterielruleId(rs.getInt("int_materielruleid"));
			g.setStartgo(rs.getInt("startgo"));
			g.setCompel(rs.getInt("compel"));
			log.debug("�ɼ�������"+rs.getInt("int_id")+"���ɼ�����"+rs.getString("str_name")+"���ɼ���������"
					+rs.getString("str_desc")+"��������Ԫ�ţ�"+rs.getInt("int_produnitid")+"����������֤����"
					+rs.getInt("int_materielruleid")+"���Ƿ�����״̬������֤��"+rs.getInt("startgo")+
					"���Ƿ�ǿ������״̬������֤��"+rs.getInt("compel"));
		}
		if(rs!=null){
			rs.close();
			rs=null;
		}
		if(stmt!=null){
			stmt.close();
			stmt=null;
		}
		return g;
	}
	
	/**
	 * ͨ���ɼ�������ѯgather
	 * @param id
	 * @param con
	 * @return
	 * @throws SQLException 
	 */
	public Gather getGatherByName(String str_name,Connection con) throws SQLException{
		Gather g = null;
		IDAO_Gather dao_Gather = (IDAO_Gather) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), IDAO_Gather.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ���ɼ�������ȡ�ɼ���SQL��䣺"+dao_Gather.getGatherByName(str_name));
		ResultSet rs = stmt.executeQuery(dao_Gather.getGatherByName(str_name));
		log.debug("ͨ���ɼ�������ȡ�ɼ����б�---");
		while(rs.next()){
			g = new Gather();
			g.setId(rs.getInt("int_id"));
			g.setName(rs.getString("str_name"));
			g.setDesc(rs.getString("str_desc"));
			g.setProdunitId(rs.getInt("int_produnitid"));
			g.setMaterielruleId(rs.getInt("int_materielruleid"));
			log.debug("�ɼ�������"+rs.getInt("int_id")+"���ɼ�����"+rs.getString("str_name")+"���ɼ���������"
					+rs.getString("str_desc")+"��������Ԫ�ţ�"+rs.getInt("int_produnitid")+"����������֤����"
					+rs.getInt("int_materielruleid"));
		}
		if(rs!=null){
			rs.close();
			rs=null;
		}
		if(stmt!=null){
			stmt.close();
			stmt=null;
		}
		return g;
	}
	
	/**
	 * ͨ�����ɾ��gather��ע�⼶��ɾ��GatherItem��ԭ����ɾ�������ǲ��ṩ���û���
	 * @param id
	 * @param con
	 * @throws SQLException 
	 */
	public void delGatherById(int id,Connection con) throws SQLException{
		IDAO_Gather dao_Gather = (IDAO_Gather) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), IDAO_Gather.class);
		IDAO_GatherItem dao_GatherItem = (IDAO_GatherItem) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), IDAO_GatherItem.class);
		Statement stmt = null;
		try {
			stmt= con.createStatement();

			log.debug("ͨ���ɼ����ɾ���ɼ���SQL��䣺"+dao_Gather.delGatherById(id));
			//ɾ��Gather
			stmt.execute(dao_Gather.delGatherById(id));
				con.commit();
			
		}catch(SQLException e){
			con.rollback();
			throw e;
		}finally{
			if(stmt!=null){
				stmt.close();
				stmt=null;
			}
		}
	}
	
	/**
	 * ����gather����
	 * @param gather
	 * @param con
	 * @throws SQLException 
	 */
	public void updateGather(Gather gather,Connection con) throws SQLException{
		IDAO_Gather dao_Gather = (IDAO_Gather) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), IDAO_Gather.class);
		Statement stmt = con.createStatement();
		log.debug("���²ɼ���SQL��䣺"+dao_Gather.updateGather(gather));
		stmt.execute(dao_Gather.updateGather(gather));
		if(stmt!=null){
			stmt.close();
			stmt=null;
		}
	}
	
	/**
	 * ��ѯȫ��gather����
	 * @param con
	 * @return
	 * @throws SQLException 
	 */
	public List<Gather> getAllGather(Connection con) throws SQLException{
		List<Gather> list = new ArrayList<Gather>();
		IDAO_Gather dao_Gather = (IDAO_Gather) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), IDAO_Gather.class);
		Statement stmt = con.createStatement();
		log.debug("��ѯ���вɼ���SQL��䣺"+dao_Gather.getAllGather());
		ResultSet rs = stmt.executeQuery(dao_Gather.getAllGather());
		while(rs.next()){
			Gather g = new Gather();
			g.setId(rs.getInt("int_id"));
			g.setName(rs.getString("str_name"));
			g.setDesc(rs.getString("str_desc"));
			g.setProdunitId(rs.getInt("int_produnitid"));
			g.setMaterielruleId(rs.getInt("int_materielruleid"));
			log.debug("�ɼ�������"+rs.getInt("int_id")+"���ɼ�����"+rs.getString("str_name")+"���ɼ���������"
					+rs.getString("str_desc")+"��������Ԫ�ţ�"+rs.getInt("int_produnitid")+"����������֤����"
					+rs.getInt("int_materielruleid"));
			list.add(g);
		}
		if(rs!=null){
			rs.close();
			rs=null;
		}
		if(stmt!=null){
			stmt.close();
			stmt=null;
		}
		return list;
	}
	
	/**
	 * ���������Ԫ���Ƿ���ڴ������ϱ�ʶ�����
	 * @param gatherid
	 * @param materialId
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public int checkGatherItemByMaterialId(int gatherid, int materialId,
			Connection con) throws SQLException {
		IDAO_Gather dao_Gather = (IDAO_Gather) DAOFactoryAdapter
		.getInstance(DataBaseType.getDataBaseType(con),
				IDAO_Gather.class);
		Statement stmt = con.createStatement();
		log.debug("���������Ԫ�д��ڴ������ϱ�ʶ����ŵĸ���SQL��䣺"+dao_Gather
				.checkGatherItemCountByMaterialId(gatherid, materialId));
		int count = 0;
		ResultSet rs = stmt.executeQuery(dao_Gather
				.checkGatherItemCountByMaterialId(gatherid, materialId));
		if (rs.next()) {
			count = rs.getInt(1);
			log.debug("���������Ԫ�д��ڴ������ϱ�ʶ����ŵĸ���:"+count);
		}
		if(stmt!=null){
			stmt.close();
			stmt=null;
		}
		return count;
	}
	
}
