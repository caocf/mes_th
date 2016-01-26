package com.qm.th.tg.dao;

import com.qm.th.tg.bean.SearchSet;

/**
 * ��ѯ����
 * 
 * @author YuanPeng
 *
 */
public class DAO_SearchSet {

	/**
	 * ������ѯ����
	 * 
	 * @param ss	��ѯ���ö���
	 * @return
	 */
	public String saveSearchSet(SearchSet ss){
		String sql = "insert into searchSet(cSearchName,cWA,cFactory,cDscFactory,cCarType,cDscCarType,cRemark)" +
			" values('"+ss.getCsearchName()+"','"+ss.getCwa()+"','"+ss.getCfactory()+"','"+ss.getCdscFactory()
			+"','"+ss.getCcarType()+"','"+ss.getCdscCarType()+"','"+ss.getCremark()+"')";
		return sql;
	}

	/**
	 * ��ѯ���в�ѯ����
	 * 
	 * @return	List<SearchSet>  ��ѯ�����б�
	 */
	public String getAllSearchSets(){
		String sql = "select * from searchSet";
		return sql;
	}

	/**
	 * ͨ����Ų��Ҳ�ѯ����
	 * @param id	���
	 * @return	SearchSet  ��ѯ����
	 */
	public String getSearchSetById(int id){
		String sql = "select * from searchSet where id = " + id;
		return sql;
	}
	
	/**
	 * ͨ�����ɾ����ѯ����
	 * 
	 * @param id	���
	 * @return
	 */
	public String delSearchSetById(int id){
		String sql = "delete searchSet where id = " + id;
		return sql;
	}
	
	/**
	 * ͨ����Ÿ��²�ѯ����
	 * 
	 * @param id	���
	 * @param ss	��ѯ���ö���
	 * @return
	 */
	public String updateSearchSetById(int id,SearchSet ss){
		String sql = "update searchSet set " +
			"cSearchName = '" + ss.getCsearchName() +
			"',cWA = '" + ss.getCwa() +
			"',cFactory = '" + ss.getCfactory() +
			"',cDscFactory = '" + ss.getCdscFactory() +
			"',cCarType = '" + ss.getCcarType() +
			"',cDscCarType = '" + ss.getCdscCarType() +
			"',cRemark = '" + ss.getCremark() + 
			"' where id = " + id;
		return sql;
	}
}
