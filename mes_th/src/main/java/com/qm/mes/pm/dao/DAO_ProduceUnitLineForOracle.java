package com.qm.mes.pm.dao;

import com.qm.mes.pm.bean.ProLineItem;
import com.qm.mes.pm.bean.ProduceUnitLine;

public class DAO_ProduceUnitLineForOracle implements DAO_ProduceUnitLine{

	/**
	 * ����������Ԫ�������õ�sql���
	 * 
	 * @param produceUnitLine ������Ԫ�������ö���
	 * @return
	 */
	public String saveProduceUnitLine(ProduceUnitLine ProduceUnitLine){
		String sql = "insert into t_pm_ProduceUnitLine(int_id,Str_Name,str_Description,int_delete) " +
				"values(seq_pm_ProduceUnitLine.nextval,'"+ProduceUnitLine.getName()+"','"+
				ProduceUnitLine.getDescription()+"',0)";
		return sql;
	}
	
	/**
	 * ͨ����Ų��������Ԫ�������õ�sql���
	 * 
	 * @param id ������Ԫ�����������
	 * @return ͨ����Ų��������Ԫ�������ö���
	 */
	public String getProduceUnitLineById(int id){
		String sql = "select * from t_pm_ProduceUnitLine where int_id ="+id+" and int_delete<>1";
		return sql;
	}
	
	/**
	 * ͨ�����ɾ��������Ԫ�������õ�sql���
	 * 
	 * @param id ������Ԫ�����������
	 * @return
	 */
	public String delProduceUnitLineById(int id){
		String sql = "update t_pm_ProduceUnitLine set int_delete=1 where int_id ="+id;
		return sql;
	}
	
	/**
	 * �����ѯ����������Ԫ��������
	 * 
	 * @return �����ѯ����������Ԫ�������õ��б�
	 */
	public String getAllProduceUnitLinesByDESC(){
		String sql = "select * from t_pm_ProduceUnitLine where int_delete<>1 order by int_id desc";
		return sql;
	}
	
	/**
	 * �����ѯ����������Ԫ��������
	 * 
	 * @return �����ѯ����������Ԫ�������õ��б�
	 */
	public String getAllProduceUnitLines(){
		String sql = "select * from t_pm_ProduceUnitLine where int_delete<>1";
		return sql;
	}
	
	/**
	 * ͨ��������Ԫ������������ѯ���
	 * 
	 * @param name
	 * @return ͨ��������Ԫ������������ѯ�����
	 */
	public String getProduceUnitLineIdByName(String name){
		String sql = "select int_id from t_pm_ProduceUnitLine where str_name='"+name+"' and int_delete<>1";
		return sql;
	}
	
	/**
	 * ͨ��������Ԫ������������ѯ������Ԫ������������
	 * 
	 * @param name ������Ԫ���������� 
	 * @return ������Ԫ������������
	 */
	public String getProduceUnitLineCountByName(String name){
		String sql = "select count(*) from t_pm_ProduceUnitLine where str_name='"+name+"' and int_delete<>1";
		return sql;
	}
	
	/**
	 * ����������Ԫ��������
	 * 
	 * @param ProduceUnitLine ������Ԫ�������ö���
	 * @return 
	 */
	public String updateProduceUnitLine(ProduceUnitLine ProduceUnitLine){
		String sql = "update t_pm_ProduceUnitLine set str_name='"+ProduceUnitLine.getName()+
		"',Str_description='"+ProduceUnitLine.getDescription()+"' where int_id="+ProduceUnitLine.getId();
		return sql;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * ����������Ԫ�������ú�
	 * 
	 * @param ProLineItem ������Ԫ�������ݶ���
	 * @return
	 */
	public String saveProLineItem(ProLineItem ProLineItem){
		String sql = "insert into t_pm_ProLineItem(int_id,Int_produceUnitId,Int_Order,Int_LineId)" +
				"values(seq_pm_ProLineItem.nextval,"+ProLineItem.getProduceUnitId()+
				","+ProLineItem.getOrder()+","+ProLineItem.getLineId()+")";
		return sql;		
	}
	
	/**
	 * ͨ��������Ԫ�������úŲ��������Ԫ���������б��sql���
	 * 
	 * @param id ������Ԫ�������ú�
	 * @return ͨ��������Ԫ�������úŲ��������Ԫ���������б�
	 */
	public String getProLineItemByProduceUnitLineId(int id){
		String sql = "select * from t_pm_ProLineItem where Int_LineId ="+id+" order by Int_Order";
		return sql;
	}
	
	/**
	 * ͨ��������Ԫ�������ú�ɾ��������Ԫ�������ݵ�sql���
	 * 
	 * @param id ������Ԫ�����������
	 * @return
	 */
	public String delProLineItemByProduceUnitLineId(int id){
		String sql = "delete from t_pm_ProLineItem where Int_LineId ="+id;
		return sql;
	}

}
