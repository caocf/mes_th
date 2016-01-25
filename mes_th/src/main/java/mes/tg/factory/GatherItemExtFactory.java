/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mes.tg.factory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import mes.tg.bean.GatherItemExt;
import mes.tg.dao.IDAO_GatherItemExt;
import mes.system.dao.DAOFactoryAdapter;
import mes.framework.DataBaseType;

/**
 * GatherItemExt������
 *
 * @author YuanPeng
 */
public class GatherItemExtFactory {

	//��־
	private final Log log = LogFactory.getLog(GatherItemExtFactory.class);
	
    /**
	 * ����GatherItemExt����
	 *
	 * @param gatherItemExt
	 * @param con
	 * @throws SQLException
	 */
	public void saveGatherItemExt(GatherItemExt gatherItemExt, Connection con)
			throws SQLException {
		IDAO_GatherItemExt dao_GatherItemExt = (IDAO_GatherItemExt) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						IDAO_GatherItemExt.class);
		Statement stmt = con.createStatement();
		log.debug("�����ɼ�����չ����SQL��䣺"+dao_GatherItemExt.saveGatherItemExt(gatherItemExt));
		stmt.execute(dao_GatherItemExt.saveGatherItemExt(gatherItemExt));
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}
	}

    /**
	 * ͨ�����ɾ��GatherItemExt����
	 *
	 * @param id
     *          ��ţ�Ψһ��
	 * @param con
     *          ���ݿ����Ӷ���
	 * @throws SQLException
     *                     SQL�쳣
	 */
	public void delGatherItemExtById(int id, Connection con) throws SQLException {
		IDAO_GatherItemExt dao_GatherItemExt = (IDAO_GatherItemExt) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						IDAO_GatherItemExt.class);
		Statement stmt = con.createStatement();
		log.debug("ɾ���ɼ�����չ����SQL��䣺"+dao_GatherItemExt.delGatherItemExtById(id));
		stmt.execute(dao_GatherItemExt.delGatherItemExtById(id));
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}
	}

    /**
     * ͨ���ɼ���IDɾ����Ӧ����չ����
     *
     * @param gather_id
     * @return
     */
    public void delGatherItemExtByGatherId(int gather_id, Connection con) throws SQLException {
		IDAO_GatherItemExt dao_GatherItemExt = (IDAO_GatherItemExt) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						IDAO_GatherItemExt.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ���ɼ���IDɾ����Ӧ�Ĳɼ�����չ����SQL��䣺"+dao_GatherItemExt.delGatherItemExtByGatherId(gather_id));
		stmt.execute(dao_GatherItemExt.delGatherItemExtByGatherId(gather_id));
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}
	}

    /**
	 * ͨ�����ȡ��GatherItemExt����
	 *
	 * @param id
     *          ��ţ�Ψһ��
	 * @param con
     *          ���ݿ����Ӷ���
	 * @return GatherItemExt
     *                  ͨ��ID��ѯ�õ���GatherItemExt����
	 * @throws SQLException
	 */
	public GatherItemExt getGatherItemExtById(int id, Connection con)
			throws SQLException {
		IDAO_GatherItemExt dao_GatherItemExt = (IDAO_GatherItemExt) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						IDAO_GatherItemExt.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ���ɼ�����չ���ԺŲ�ѯ�ɼ�����չ���Զ���SQL��䣺"+dao_GatherItemExt.getGatherItemExtById(id));
		ResultSet rs = stmt.executeQuery(dao_GatherItemExt.getGatherItemExtById(id));
		GatherItemExt gatherItemExt = null;
		if (rs.next()) {
			gatherItemExt = new GatherItemExt();
			gatherItemExt.setId(rs.getInt("int_id"));
			gatherItemExt.setGatherId(rs.getInt("int_gather_id"));
			gatherItemExt.setOrder(rs.getInt("int_order"));
			gatherItemExt.setName(rs.getString("str_name"));
			log.debug("�ɼ�����չ���Ժţ�"+rs.getInt("int_id")+"���ɼ���ţ�"+rs.getInt("int_gather_id")+
					"���ɼ�����չ����˳��ţ�"+rs.getInt("int_order")+"���ɼ�����չ��������"+rs.getString("str_name"));
		}
        if (stmt != null) {
			stmt.close();
			stmt = null;
		}
        if(rs != null){
            rs.close();
            rs = null;
        }
        return gatherItemExt;
	}

    /**
	 * ͨ����չ��������ѯGatherItemExt
	 * @param str_name
	 * @param con
	 * @return GatherItemExt
	 * @throws SQLException
	 */
	public GatherItemExt getGatherItemExtByName(String str_name,Connection con) throws SQLException{
		GatherItemExt g = null;
		IDAO_GatherItemExt dao_GatherItemExt = (IDAO_GatherItemExt) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), IDAO_GatherItemExt.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ����չ��������ѯ�ɼ�����չ����SQL��䣺"+dao_GatherItemExt.getGatherItemExtByName(str_name));
		ResultSet rs = stmt.executeQuery(dao_GatherItemExt.getGatherItemExtByName(str_name));
		if(rs.next()){
			g = new GatherItemExt();
			g.setId(rs.getInt("int_id"));
			g.setName(rs.getString("str_name"));
			g.setGatherId(rs.getInt("int_gather_id"));
			g.setOrder(rs.getInt("int_order"));
			log.debug("�ɼ�����չ���Ժţ�"+rs.getInt("int_id")+"���ɼ���ţ�"+rs.getInt("int_gather_id")+
					"���ɼ�����չ����˳��ţ�"+rs.getInt("int_order")+"���ɼ�����չ��������"+rs.getString("str_name"));
		}
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}
        if(rs != null){
            rs.close();
            rs = null;
        }
		return g;
	}

    /**
	 * ����GatherItemExt����
	 *
	 * @param gatherItemExt
     *                  gatherItemExt����
	 * @param con
     *          ���ݿ����Ӷ���
	 * @throws SQLException
     *                  SQL�쳣
	 */
	public void updateGatherItemExt(GatherItemExt gatherItemExt, Connection con)
			throws SQLException {
		IDAO_GatherItemExt dao_GatherItemExt = (IDAO_GatherItemExt) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						IDAO_GatherItemExt.class);
		Statement stmt = con.createStatement();
		log.debug("���²ɼ�����չ���Զ���SQL��䣺"+dao_GatherItemExt.updateGatherItemExt(gatherItemExt));
		stmt.execute(dao_GatherItemExt.updateGatherItemExt(gatherItemExt));
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}
	}

    /**
	 * ��ѯGatherItemExt�������м�¼
	 *
	 * @param con
     *          ���ݿ����Ӷ���
	 * @return List<GatherItemExt>
     *                  GatherItemExt��ʽ��List
	 * @throws SQLException
     *                  SQL�쳣
	 */
	public List<GatherItemExt> getAllGatherItemExt( Connection con)
			throws SQLException {
		IDAO_GatherItemExt dao_GatherItemExt = (IDAO_GatherItemExt) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						IDAO_GatherItemExt.class);
		List<GatherItemExt> list = new ArrayList<GatherItemExt>();
		Statement stmt = con.createStatement();
		log.debug("��ѯ���вɼ�����չ���Լ�¼SQL��䣺"+dao_GatherItemExt.getAllGatherItemExt());
		ResultSet rs = stmt.executeQuery(dao_GatherItemExt.getAllGatherItemExt());
		log.debug("���вɼ�����չ�����б�--");
		while (rs.next()) {
			GatherItemExt gatherItemExt = new GatherItemExt();
			gatherItemExt.setId(rs.getInt("int_id"));
			gatherItemExt.setGatherId(rs.getInt("int_gatherid"));
			gatherItemExt.setOrder(rs.getInt("int_order"));
			gatherItemExt.setName(rs.getString("str_name"));
			list.add(gatherItemExt);
			log.debug("�ɼ�����չ���Ժţ�"+rs.getInt("int_id")+"���ɼ���ţ�"+rs.getInt("int_gather_id")+
					"���ɼ�����չ����˳��ţ�"+rs.getInt("int_order")+"���ɼ�����չ��������"+rs.getString("str_name"));
		}
        if (stmt != null) {
			stmt.close();
			stmt = null;
		}
        if(rs != null){
            rs.close();
            rs = null;
        }
		return list;
	}

    /**
     * ͨ���ɼ�����Ų�ѯ�õ�GatherItemExt����
     *
     * @param int_gather_id
     * @param con
     * @return List<GatherItemExt>
     * @throws java.sql.SQLException
     */
    public List<GatherItemExt> getGatherItemExtByGatherId(int int_gather_id,Connection con)
			throws SQLException {
		IDAO_GatherItemExt dao_GatherItemExt = (IDAO_GatherItemExt) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						IDAO_GatherItemExt.class);
		List<GatherItemExt> list = new ArrayList<GatherItemExt>();
		Statement stmt = con.createStatement();
		log.debug("ͨ���ɼ�����Ų�ѯ�õ��ɼ�����չ���Լ���SQL��䣺"+dao_GatherItemExt.getGatherItemExtByGatherId(int_gather_id));
		ResultSet rs = stmt.executeQuery(dao_GatherItemExt.getGatherItemExtByGatherId(int_gather_id));
		log.debug("ͨ���ɼ�����Ų�ѯ�õ��ɼ�����չ�����б�--");
		while (rs.next()) {
			GatherItemExt gatherItemExt = new GatherItemExt();
			gatherItemExt.setId(rs.getInt("int_id"));
			gatherItemExt.setGatherId(rs.getInt("int_gather_id"));
			gatherItemExt.setOrder(rs.getInt("int_order"));
			gatherItemExt.setName(rs.getString("str_name"));
			list.add(gatherItemExt);
			log.debug("�ɼ�����չ���Ժţ�"+rs.getInt("int_id")+"���ɼ���ţ�"+rs.getInt("int_gather_id")+
					"���ɼ�����չ����˳��ţ�"+rs.getInt("int_order")+"���ɼ�����չ��������"+rs.getString("str_name"));
		}
        if (stmt != null) {
			stmt.close();
			stmt = null;
		}
        if(rs != null){
            rs.close();
            rs = null;
        }
		return list;
	}


}
