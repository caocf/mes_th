package com.qm.mes.pm.dao;

import com.qm.mes.pm.bean.DistriItem;
import com.qm.mes.pm.bean.DistributionDoc;

public class DAO_DistributionDocForOracle implements DAO_DistributionDoc {

	/**
	 * ��������ָʾ����sql���
	 * 
	 * @param distributionDoc ����ָʾ������+
	 * 
	 * @return
	 */
	public String saveDistributionDoc(DistributionDoc distributionDoc){
		String sql = "insert into t_pm_DistributionDoc(int_id,Str_Name,Str_description,Dat_createDate" +
				",Dat_upDate,Int_CreateUID,Int_UpdateUID,Int_delete,Int_request,Int_response,Int_target " +
				",Str_materiel,Int_bomid) values(seq_pm_DistributionDoc.nextval,'"+distributionDoc.getName()+"','"+
				distributionDoc.getDescription()+"',sysdate,sysdate,"+distributionDoc.getCreateUID()+","+
				distributionDoc.getCreateUID()+",0,"+distributionDoc.getRequest_proUnit()+","+
				distributionDoc.getResponse_proUnit()+","+distributionDoc.getTarget_proUnit()+",'"+
				distributionDoc.getMaterielType()+"',"+distributionDoc.getBomId()+")";
		return sql;
	}
	
	/**
	 * ͨ����Ų������ָʾ����sql���
	 * 
	 * @param id ����ָʾ�����
	 * @return ͨ����Ų������ָʾ������
	 */
	public String getDistributionDocById(int id){
		String sql = "select * from t_pm_DistributionDoc where int_id ="+id;
		return sql;
	}
	
	/**
	 * ͨ�����ɾ������ָʾ����sql���
	 * 
	 * @param id ����ָʾ�����
	 * @return
	 */
	public String delDistributionDocById(int id){
		String sql = "update t_pm_DistributionDoc set int_delete=1 where int_id ="+id;
		return sql;
	}
	
	/**
	 * ͨ�����ϱ�ʾ�������ָʾ����sql���
	 * 
	 * @param materiel ���ϱ�ʾ 
	 * @return ͨ�����ϱ�ʾ�������ָʾ������
	 */
	public String getDistributionDocByMateriel(String materiel){
		String sql = "select * from t_pm_DistributionDoc where Str_materiel ='"+materiel+"' and int_delete<>1";
		return sql;
	}
	
	/**
	 * �����ѯ��������ָʾ��
	 * 
	 * @return �����ѯ��������ָʾ�����б�
	 */
	public String getAllDistributionDocsByDESC(){
		String sql = "select * from t_pm_DistributionDoc where int_delete<>1 order by int_id desc";
		System.out.println(sql);
		return sql;
	}
	
	/**
	 * �����ѯ��������ָʾ��
	 * 
	 * @return �����ѯ��������ָʾ�����б�
	 */
	public String getAllDistributionDocs(){
		String sql = "select tpd.INT_ID as tpd_id,tpd.STR_NAME as tpd_name,tpd.STR_DESCRIPTION,tpd.STR_MATERIEL,"+
			"(select trp.str_name from T_RA_PRODUCEUNIT trp where INT_REQUEST = trp.int_id ) req_proUnitName,"+
			"(select trp.str_name from T_RA_PRODUCEUNIT trp where INT_RESPONSE = trp.int_id ) res_proUnitName,"+
			"(select trp.str_name from T_RA_PRODUCEUNIT trp where INT_TARGET = trp.int_id ) tar_proUnitName,"+
			"to_char(tpd.DAT_CREATEDATE,'yyyy-mm-dd hh24:mi:ss') as createdate,"+
			"to_char(tpd.DAT_UPDATE,'yyyy-mm-dd hh24:mi:ss') as updatedate,"+
			"(select cusrname from data_user where tpd.int_createuid = nusrno ) createname,"+
			"(select cusrname from data_user where tpd.int_updateuid = nusrno ) updatename "+
			"from T_PM_DISTRIBUTIONDOC tpd "+
			"where tpd.int_delete=0 order by tpd_id asc";
		return sql;
	}
	
	/**
	 * ͨ������ָʾ������ѯ���
	 * 
	 * @param name
	 * @return ͨ������ָʾ������ѯ�����
	 */
	public String getDistributionDocIdByName(String name){
		String sql = "select int_id from t_pm_DistributionDoc where str_name='"+name+"' and int_delete<>1";
		return sql;
	}
	
	/**
	 * ͨ������ָʾ������ѯ����ָʾ������
	 * 
	 * @param name ����ָʾ���� 
	 * @return ����ָʾ������
	 */
	public String getDistributionDocCountByName(String name){
		String sql = "select count(*) from t_pm_DistributionDoc where str_name='"+name+"' and int_delete<>1";
		return sql;
	}
	
	/**
	 * ��������ָʾ��
	 * 
	 * @param distributionDoc ����ָʾ������
	 * @return 
	 */
	public String updateDistributionDoc(DistributionDoc distributionDoc){
		String sql = "update t_pm_DistributionDoc set str_name='"+distributionDoc.getName()+"', Str_description='"+
			distributionDoc.getDescription()+"', Dat_upDate=sysdate"+", Int_UpdateUID= "+
			distributionDoc.getUpdateUID()+",Int_delete=0,Int_request="+distributionDoc.getRequest_proUnit()+
			",Int_response="+distributionDoc.getResponse_proUnit()+",Int_target="+distributionDoc.getTarget_proUnit()
			+",Str_materiel='"+distributionDoc.getMaterielType()+"',Int_bomid="+distributionDoc.getBomId()+" where int_id="+distributionDoc.getId();
		return sql;
	}
	
	/**
	 * ͨ������������Ԫ�Ų�ѯ����ָʾ��
	 * 
	 * @param requestProUnitId ����������Ԫ��
	 * @return ͨ������������Ԫ�Ų�ѯ����ָʾ���б�
	 */
	public String getDistributionDocsByRequestProUnitId(int requestProUnitId){
		String sql ="select * from t_pm_DistributionDoc where Int_request="+requestProUnitId+" and int_delete<>1";
		return sql;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * ��������ָʾ����
	 * 
	 * @param distriItem �������������
	 * @return
	 */
	public String saveDistriItem(DistriItem distriItem){
		String sql = "insert into t_pm_DistriItem(int_id,Str_Name,Str_matitem,Int_count,Int_DisDoc,Str_description)" +
				"values(seq_pm_distriItem.nextval,'"+distriItem.getName()+"','"+distriItem.getMatitem()+"',"+
				distriItem.getCount()+","+distriItem.getDistributionDocId()+",'"+distriItem.getDescription()+"')";
		return sql;		
	}
	
	/**
	 * ͨ������ָʾ���Ų�������������б��sql���
	 * 
	 * @param id ����ָʾ����
	 * @return ͨ������ָʾ���Ų�������������б�
	 */
	public String getDistriItemByDistributionDocId(int id){
		String sql = "select * from t_pm_DistriItem where Int_DisDoc ="+id;
		return sql;
	}
	
	/**
	 * ͨ������ָʾ����ɾ�������������sql���
	 * 
	 * @param id ����ָʾ�����
	 * @return
	 */
	public String delDistriItemByDistributionDocId(int id){
		String sql = "delete from t_pm_DistriItem where Int_DisDoc ="+id;
		return sql;
	}

}
