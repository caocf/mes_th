package com.qm.mes.ra.dao;
import com.qm.mes.ra.bean.*;
/**
 * 
 * @author л����
 *
 */
public interface DAO_ProduceUnit {
	
	/** ����������Ԫ
	 * л����
	 * param produceUnit;
	 */
	String saveProduceUnit(ProduceUnit produceUnit);
	/**
	 * л����
	 * �õ����е�������Ԫ����Ϣ
	 */
	String getAllProduceUnit();
	/**
	 * л����
	 * �õ�id�ŵ�������Ԫ����Ϣ
	 */
   String getProduceUnitById(int id);
   /**
    * 
    * л����
    * ����������Ԫ��Ϣ
    * param  produceunit
    */
   String updateProduceUnit(ProduceUnit produceunit);
   /**
    * 
    * ͨ��idɾ��ָ����������Ԫ
    * �߼�ɾ��
    * л����
    */
   String deleteProduceUnitById(int id);
   /**
    * ͨ�����ֲ�ѯ��ص�������Ԫ
    * 
    * л����
    */
   String getProduceUnitByName(String name);
 /**
  * ͨ��������Ԫ��id�鵽������Ԫ�ĳ�ʼָ��״̬
  * л����
  */
    String getInstructionstateIdByproduceunitid(int id);
    /**
	 * л����
	 * �õ����е�������Ԫ����Ϣ
	 * ��ѯ���������ɾ����������Ԫ��
	 */
	String getAllProduceUnitDESC();
	
	/**
	   * ͨ�����ϱ�ʶ����Ų�ѯ������Ԫ����
	   * 
	   * @param materielurleid	���ϱ�ʶ�����
	   * @return	������Ԫ����
	   */
	  public String countProduceUnitByMateritelRuleID(int materielurleid);
	  
	  /**
	   * �����ѯ����������Ԫ����ź�����
	   * 
	   * @return	�����ѯ����������Ԫ����ź����Ƶļ���
	   */
	  public String getAllProdUnitIdNamesByDesc();
	  
	  //--------------------------------------------------������Ŀ���---------------------------------------------------------------
	  /**
	   * ���ݸ�������Ԫ������������Ԫ
	   * @param id
	   * @return
	   */
	  String getCunitByid(int id);
	  
	  /** ɾ����������Ԫ��Ϣ
	  * @param id
	  * @return
	  */
	  String delCunitByid(int id);
	  
	  /** �����������Ԫ��Ϣ
	  * @param id
	  * @param CunitId
	  * @return
	  */
	 String addCunit(int id, String CunitId);
}
