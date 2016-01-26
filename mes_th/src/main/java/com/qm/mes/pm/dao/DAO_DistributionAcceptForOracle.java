package com.qm.mes.pm.dao;

import com.qm.mes.pm.bean.DistributionAccept;

public class DAO_DistributionAcceptForOracle implements DAO_DistributionAccept {

	/**
	 * ��������ȷ�ϵ���sql���
	 * 
	 * @param distributionAccept ����ȷ�ϵ�����
	 * @param CreateUID �����û�ID
	 * @return
	 */
	public String saveDistributionAccept(DistributionAccept distributionAccept){
		String sql = "insert into t_pm_DistributionAccept(int_id,Int_DisDocId,Int_State,Dat_requestDate," +
				"Str_materiel) " +
				"values(seq_pm_DistributionAccept.nextval,"+distributionAccept.getDisDocId()+",0,sysdate,'"
				+distributionAccept.getMateriel()+"')";
		return sql;
	}
	
	/**
	 * ͨ��ID��������ȷ�ϵ�
	 * 
	 * param id ���
	 * @param userid �û�id
	 * return  
	 */
	public String transactDistributionAccept(int id,int userid){
		String sql = "update t_pm_DistributionAccept set Int_State=1,Dat_responseDate=sysdate,Int_responseUID=" +
				+userid+" where int_id="+id;
		return sql;
	}
	
	/**
	 * ͨ����Ų������ȷ�ϵ���sql���
	 * 
	 * @param id ����ȷ�ϵ����
	 * @return ͨ����Ų������ȷ�ϵ�����
	 */
	public String getDistributionAcceptById(int id){
		String sql = "select * from t_pm_DistributionAccept where int_id ="+id;
		return sql;
	}
	
	/**
	 * �����ѯ��������ȷ�ϵ�
	 * 
	 * @return �����ѯ��������ȷ�ϵ����б�
	 */
	public String getAllDistributionAcceptsByDESC(){
		String sql = "select * from t_pm_DistributionAccept order by int_id desc";
		System.out.println(sql);
		return sql;
	}
	
	/**
	 * ͨ������ָʾ����Ӧ������Ԫ��ѯ����ȷ�ϵ�
	 * 
	 * @return ͨ������ָʾ����Ӧ������Ԫ��ѯ����ȷ�ϵ����б�
	 */
	public String getDistributionAcceptsByresponseProUnit(int responseProUnit){
		String sql = "select da.int_id,Int_DisDocId,Int_State,Dat_requestDate,Dat_responseDate,Int_responseUID,da.Str_materiel" +
				",dd.INT_TARGET from t_pm_DistributionAccept da,t_pm_DistributionDoc dd where Int_response="+responseProUnit+
				" and Int_DisDocId=dd.int_id order by da.Int_State";
		return sql;
	}
}
