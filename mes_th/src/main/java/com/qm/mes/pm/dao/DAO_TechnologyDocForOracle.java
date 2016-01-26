package com.qm.mes.pm.dao;

import com.qm.mes.pm.bean.TechDocItem;
import com.qm.mes.pm.bean.TechItemFile;
import com.qm.mes.pm.bean.TechnologyDoc;

public class DAO_TechnologyDocForOracle implements DAO_TechnologyDoc {

	/**
	 * �������ղ���˵�����sql���
	 * 
	 * @param technologyDoc ���ղ���˵�������
	 * @param CreateUID �����û�ID
	 * @return
	 */
	public String saveTechnologyDoc(TechnologyDoc technologyDoc){
		String sql = "insert into t_pm_TechnologyDoc(int_id,Str_Name,STR_MATERIEL,Str_Description,Dat_createDate," +
				"Dat_upDate,Int_CreateUID,INT_UPDATEUID,Int_delete) " +
				"values(seq_pm_TechnologyDoc.nextval,'"+technologyDoc.getName()+"','"+technologyDoc.getMateriel()
				+"','"+technologyDoc.getDescription()+"',sysdate,sysdate,"
				+technologyDoc.getCreateUID()+","+technologyDoc.getCreateUID()+",0)";
		return sql;
	}
	
	/**
	 * ͨ����Ų�����ղ���˵�����sql���
	 * 
	 * @param id ���ղ���˵�������
	 * @return ͨ����Ź��ղ���˵�������
	 */
	public String getTechnologyDocById(int id){
		String sql = "select * from t_pm_TechnologyDoc where int_id ="+id;
		return sql;
	}
	
	/**
	 * ͨ�����ɾ�����ղ���˵�����sql���
	 * 
	 * @param id ���ղ���˵�������
	 * @return
	 */
	public String delTechnologyDocById(int id){
		String sql = "update t_pm_TechnologyDoc set Int_delete = 1 where int_id ="+id;
		return sql;
	}
	
	/**
	 * �����ѯ���й��ղ���˵����
	 * 
	 * @return �����ѯ���й��ղ���˵������б�
	 */
	public String getAllTechnologyDocsByDESC(){
		String sql = "select * from t_pm_TechnologyDoc where int_delete<>1 order by int_id desc";
		return sql;
	}
	
	/**
	 * �����ѯ���й��ղ���˵����
	 * 
	 * @return �����ѯ���й��ղ���˵������б�
	 */
	public String getAllTechnologyDocs(){
		String sql = "select * from "+
			"(select tpt.INT_ID as tpt_id,tpt.STR_NAME as tpt_name,tpt.STR_MATERIEL as STR_MATERIEL,tpt.STR_DESCRIPTION,"+
			"to_char(tpt.DAT_CREATEDATE,'yyyy-mm-dd hh24:mi:ss') as createdate,"+
			"to_char(tpt.DAT_UPDATE,'yyyy-mm-dd hh24:mi:ss') as updatedate,"+
			"(select cusrname from data_user where tpt.int_createuid = nusrno ) createname,"+
			"(select cusrname from data_user where tpt.int_updateuid = nusrno ) updatename "+ 
			"from T_PM_TECHNOLOGYDOC tpt where tpt.int_delete=0 order by tpt_id asc) a order by tpt_id asc";
		return sql;
	}
	
	/**
	 * ͨ�����ղ���˵��������ѯ���
	 * 
	 * @param name
	 * @return ͨ�����ղ���˵��������ѯ�����
	 */
	public String getTechnologyDocIdByName(String name){
		String sql = "select int_id from t_pm_TechnologyDoc where str_name='"+name+"' and int_delete<>1";
		return sql;
	}
	
	/**
	 * ͨ�����ղ���˵��������ѯװ��ָʾ������
	 * 
	 * @param name ���ղ���˵������ 
	 * @return ���ղ���˵��������
	 */
	public String getTechnologyDocCountByName(String name){
		String sql = "select count(*) from t_pm_TechnologyDoc where str_name='"+name+"' and int_delete<>1";
		return sql;
	}
	
	/**
	 * ���¹��ղ���˵����
	 * 
	 * @param technologyDoc ���ղ���˵�������
	 * @return 
	 */
	public String updateTechnologyDoc(TechnologyDoc technologyDoc){
		String sql = "update t_pm_TechnologyDoc set str_name='"+technologyDoc.getName()+"',STR_MATERIEL='"+
		technologyDoc.getMateriel()+
		"', Str_Description='"+technologyDoc.getDescription()+"', Dat_upDate=sysdate"+
		", Int_UpdateUID= "+technologyDoc.getUpdateUID()+" where int_id="+technologyDoc.getId();
		return sql;
	}
	
	/**
	 * ͨ����Ʒ����ʾ��ѯ���ղ���˵��������
	 * 
	 * @param materiel ��Ʒ����ʾ
	 * @return ���ղ���˵��������
	 */
	public String getTechnologyDocCountByMateriel(String materiel){
		String sql = "select count(*) from t_pm_TechnologyDoc where STR_MATERIEL='"+materiel+"' and int_delete<>1";
		return sql;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * �������ղ�����
	 * 
	 * @param techDocItem ���ղ��������
	 * @return
	 */
	public String saveTechDocItem(TechDocItem techDocItem){
		String sql = "insert into t_pm_TechDocItem(int_id,Int_produceUnit,Str_Content,Int_delete,Int_TechDocId)" +
				"values(seq_pm_TechDocItem.nextval,"+techDocItem.getProduceUnitId()+",'"+techDocItem.getContent()
				+"',0,"+techDocItem.getTechDocId()+")";
		return sql;		
	}
	
	/**
	 * ͨ�����ղ���˵����Ų�����ղ���������б��sql���
	 * 
	 * @param id ���ղ���˵�����
	 * @return ͨ�����ղ���˵����Ų�����ղ���������б�
	 */
	public String getTechDocItemByTechnologyDocId(int id){
		String sql = "select * from t_pm_TechDocItem where Int_TechDocId ="+id+" and int_delete<>1";
		return sql;
	}
	
	/**
	 * ͨ�����ղ���˵�����ɾ�����ղ������sql���
	 * 
	 * @param id ���ղ���˵�������
	 * @return
	 */
	public String delTechDocItemByTechnologyDocId(int id){
		String sql = "update t_pm_TechDocItem set Int_delete=1 where Int_TechDocId ="+id;
		return sql;
	}
	
	/**
	 * ��ѯ���ղ�����������
	 * 
	 * @return ���ղ�����������
	 */
	public String getTechDocItemMaxId(){
		String sql = "select max(int_id) from t_pm_TechDocItem";
		return sql;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * ͨ�����ղ�������Ų�ѯ���ղ������ļ�
	 * 
	 * @param techDocItemId ���ղ��������
	 * @return ���ղ������ļ�
	 */
	public String getTechItemFileByTechDocItemId(int techDocItemId){
		String sql = "select int_ID,int_TECHITEMID,str_PATHNAME from T_PM_TECHDOCITEMFILE where int_TECHITEMID="+techDocItemId;
		return sql;
	}
	
	/**
	 * �������ղ������ļ�
	 * 
	 * @param tif ���ղ������ļ�����
	 * @return 
	 */
	public String saveTechItemFile(TechItemFile tif){
		String sql = "insert into T_PM_TECHDOCITEMFILE(int_ID,int_TECHITEMID,str_PATHNAME) values(SEQ_PM_TECHDOCITEMFILE.nextval," +
		tif.getTechDocItemId()+",'"+tif.getPathName()+"')";
		return sql;
	}
	
	/**
	 * ͨ�����ղ�����ɾ�����ղ������ļ�
	 * 
	 * @param techDocItemId	���ղ��������
	 * @return
	 */
	public String delTechItemFile(int techDocItemId){
		String sql = "delete from T_PM_TECHDOCITEMFILE where int_TECHITEMID="+techDocItemId;
		return sql;
	}
	
	/**
	 * ͨ�����ղ���˵�������ɾ�����ղ�����
	 * 
	 * @param techDocId  ���ղ���˵�������
	 * @return
	 */
	public String delTechItemFileByTechDoc(int techDocId){
		String sql = "delete from T_PM_TECHDOCITEMFILE where int_TECHITEMID in(select INT_ID from T_PM_TECHDOCITEM where INT_TECHDOCID="+techDocId+")";
		return sql;
	}
	
	/**
	 * ͨ���ɹ��ղ�������Ÿ����¹��ղ������ļ�
	 * 
	 * @param oldItemId �ɹ��ղ�����
	 * @return
	 */
	public String updateTechItemId(int oldItemId){
		String sql = "update T_PM_TECHDOCITEMFILE set int_TECHITEMID=(select max(int_id) from T_PM_TECHDOCITEM) where int_TECHITEMID="+oldItemId;
		return sql;
	}

}