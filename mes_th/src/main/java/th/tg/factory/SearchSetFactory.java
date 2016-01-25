package th.tg.factory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import th.tg.bean.SearchSet;
import th.tg.dao.DAO_SearchSet;

/**
 * ��ѯ���ù���
 * 
 * @author YuanPeng
 *
 */
public class SearchSetFactory {

	//��־
	private final Log log = LogFactory.getLog(SearchSetFactory.class);
	
	/**
	 * ������ѯ���ö���
	 * 
	 * @param ss	��ѯ���ö���
	 * @param con	���Ӷ���
	 * @throws SQLException	SQL�쳣
	 */
	public void saveSearchSet(SearchSet ss,Connection con)throws SQLException{
		DAO_SearchSet dao = new DAO_SearchSet();
		Statement stmt = con.createStatement();
		log.debug("������ѯ����"+dao.saveSearchSet(ss));
		stmt.execute(dao.saveSearchSet(ss));
		if(stmt != null){
			stmt.close();
			stmt = null;
		}
	}
	
	/**
	 * ��ѯ���в�ѯ����
	 * 
	 * @param con	���Ӷ���
	 * @return	List<SearchSet>	��ѯ�����б�
	 * @throws SQLException	SQL�쳣
	 */
	public List<SearchSet> getAllSearchSets(Connection con)throws SQLException{
		List<SearchSet> list = new ArrayList<SearchSet>();
		DAO_SearchSet dao = new DAO_SearchSet();
		Statement stmt = con.createStatement();
		log.debug("��ѯ���в�ѯ����"+dao.getAllSearchSets());
		ResultSet rs = stmt.executeQuery(dao.getAllSearchSets());
		while(rs.next()){
			SearchSet ss = new SearchSet();
			ss.setCsearchName(rs.getString("cSearchName"));
			ss.setCwa(rs.getString("cWA"));
			ss.setCfactory(rs.getString("cFactory"));
			ss.setCdscFactory(rs.getString("cDscFactory"));
			ss.setCcarType(rs.getString("cCarType"));
			ss.setCdscCarType(rs.getString("cDscCarType"));
			ss.setCremark(rs.getString("cRemark"));
			list.add(ss);
		}
		if(stmt != null){
			stmt.close();
			stmt = null;
		}
		return list;
	}
	
	/**
	 * ͨ����Ų�ѯ��ѯ����
	 * 
	 * @param id	���
	 * @param con	���Ӷ���
	 * @return	SearchSet	��ѯ�����б�
	 * @throws SQLException	SQL�쳣
	 */
	public SearchSet getSearchSetById(int id,Connection con)throws SQLException{
		SearchSet ss = new SearchSet();
		DAO_SearchSet dao = new DAO_SearchSet();
		Statement stmt = con.createStatement();
		log.debug("ͨ����Ų�ѯ��ѯ����"+dao.getSearchSetById(id));
		ResultSet rs = stmt.executeQuery(dao.getSearchSetById(id));
		while(rs.next()){
			ss.setCsearchName(rs.getString("cSearchName"));
			ss.setCwa(rs.getString("cWA"));
			ss.setCfactory(rs.getString("cFactory"));
			ss.setCdscFactory(rs.getString("cDscFactory"));
			ss.setCcarType(rs.getString("cCarType"));
			ss.setCdscCarType(rs.getString("cDscCarType"));
			ss.setCremark(rs.getString("cRemark"));
		}
		if(stmt != null){
			stmt.close();
			stmt = null;
		}
		return ss;
	}
	
	/**
	 * ͨ�����ɾ����ѯ����
	 * 
	 * @param id	���
	 * @param con	���Ӷ���
	 * @throws SQLException	SQL�쳣
	 */
	public void delSearchSetById(int id,Connection con)throws SQLException{
		DAO_SearchSet dao = new DAO_SearchSet();
		Statement stmt = con.createStatement();
		log.debug("ͨ�����ɾ����ѯ����"+dao.delSearchSetById(id));
		stmt.execute(dao.delSearchSetById(id));
		if(stmt != null){
			stmt.close();
			stmt = null;
		}
	}
	
	/**
	 * ͨ����Ÿ��²�ѯ����
	 * 
	 * @param id	���
	 * @param ss	��ѯ���ö���
	 * @param con	���Ӷ���
	 * @throws SQLException	SQL�쳣
	 */
	public void updateSearchSetById(int id,SearchSet ss,Connection con)throws SQLException{
		DAO_SearchSet dao = new DAO_SearchSet();
		Statement stmt = con.createStatement();
		log.debug("ͨ����Ÿ��²�ѯ����"+dao.updateSearchSetById(id,ss));
		stmt.execute(dao.updateSearchSetById(id,ss));
		if(stmt != null){
			stmt.close();
			stmt = null;
		}
	}
}
