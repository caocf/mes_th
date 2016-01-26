/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.qm.mes.ra.dao;

import java.util.List;

import com.qm.mes.ra.bean.Instruction;

/**
 *
 * @author YuanPeng
 */
public interface DAO_Instruction {

	  /**
     * ͨ����Ų��ָ���sql���
     *Ԭ��
     * @param id
     * @return T_RA_INSTRUCTION�и�ID�������ֶ�
     */
    String getInstructionById(int id);

    /**Ԭ��
     * ��ָ����и��²�����sql���
     *
     * @param instruction
     * @return ����T_RA_INSTRUCTION�и�ID�ļ�¼
     */
    String updateInstruction(Instruction instruction);

    /**Ԭ��
     * ���ұȸ�˳���С��˳���ָ��
     * 
     *@param ProduceUnitID
     *		������Ԫ
     *@param str_date
     *		��������
     * @param Int_instructOrder
     *                      ָ��˳��
     * @return �ȸ�˳���С��˳���ָ��
     */
   
    public String getByOrderMinus(int ProduceUnitID, String str_date,String workOrder,int Int_instructOrder);

    /**Ԭ��
     * ���ұȸ�˳��Ŵ��˳���ָ��
     *
     *@param ProduceUnitID
     *		������Ԫ
     *@param str_date
     *		��������
     * @param Int_instructOrder
     *                      ָ��˳��
     * @return �ȸ�˳���С��˳���ָ��
     */
    public String getByOrderPlus(int ProduceUnitID, String str_date,String workOrder,int Int_instructOrder);

   /**Ԭ��
   * ͨ��������Ԫ�š��������ڡ�״̬�Ų�ѯ��ҵָ��
   *
   * @param Int_produnitid
   *            ������Ԫ��
   * @param str_date
   *            ��������
   * @param stateid
   *            ״̬��
   * @return ͨ��������Ԫ�š��������ڡ�״̬�Ų�ѯ����ҵָ��
   */
    public String getInstructionByProUnitProDateStateOrder(int Int_produnitid,String str_date,String workOrder,int stateid);

    /**Ԭ��
     * ͨ����ҵָ����Ÿ���Int_editΪ1
     *
     * @param id
     *      ��ҵָ�����
     * @return ͨ����ҵָ����Ÿ���Int_editΪ1
     */
    public String editInstructionById(int id);

    /**Ԭ��
     * ͨ����ҵָ����Ÿ���Int_editΪ0
     *
     * @param id
     *      ��ҵָ�����
     * @return ͨ����ҵָ����Ÿ���Int_editΪ0
     */
    public String uneditInstructionById(int id);

/** Ԭ��
    * ����ָ��
    *
    * @param instruction 
    * @return
    */
public String saveInstruction(Instruction instruction);

/**
* ͨ��������Ԫ�š��������ڡ�˳��Ŵ����׸��Ǳ༭��������int_delete�ֶ�ֵΪ1
*  Ԭ��
* @param ProduceUnitID
* @param str_date
* @param UnlockStartOrder
* @return
*/
public String DeleteInstructionByProUnitDateworkOrderUnlockOrder(int ProduceUnitID, String str_date, String workOrder,int UnlockStartOrder);
/**
* ͨ��������Ԫ�š��������ڲ�ѯ��¼����
*  Ԭ��
* @param ProduceUnitID
* 			������Ԫ��
* @param str_date
* 			��������
* @return ����ͨ��������Ԫ�š��������ڲ�ѯ��¼������
*/
public String getCountByProUnitDateOrder(int ProduceUnitID, String str_date,String str_workOrder);

/**
* ��ָ��ID��ָ�����Ϊ�ѷ���״̬
* 
* @param id
* 		��ҵָ�����
* @return
*/
public String issuanceInstruction(int id );

/**
 * ͨ��������Ԫ�ź��������ڲ�ѯδ��ɾ����ָ��
 * 
 * @param produnitid
 * 			������Ԫ��
 * @param str_date
 * 			��������
 * @return ָ���
 */
	public String getInstructionsByProdunitDateOrder(int  produnitid,String str_date,String str_workorder);
    
	/**
	 * �жϸ�������Ԫ���������ڡ���Ʒ��ʶ��ָ������
	 * 
	 * @param produnitid
	 * @param str_date
	 * @param marker
	 * @return
	 */
	public String getCountByProUnitDateWorkorderMarker(int  produnitid,String str_date,String workOrder,String marker);
	
	/**
	* ͨ��������Ԫ�š��������ڲ�ѯ��¼
	* Ԭ��
	* @param ProduceUnitID
	* 			������Ԫ��
	* @param str_date
	* 			��������
	* @param List<Integer> list_state ״̬����
	* @return ����ͨ��������Ԫ�š��������ڲ�ѯ��¼
	*/
	public String getCountByProUnitDateState(int ProduceUnitID,List<Integer> list_state);
   
    
    
    
    /**
     * �޸�ָ��״̬
     * л����
     * @param structStateID ״̬id
     *  @param staError ״̬��ת���쳣��ʶ
     *  @param producemarker ��Ʒ��ʶ
     *  produnitid ������Ԫ��id
     * @return  �����ڸ�������Ԫ���Ӧ��ָ��
     */
    String updateInstructState(int structStateID,int staError,String producemarker,int produnitid);
    /**
     * �鿴����ָ��״̬
     * л����
     * 
     *  @param producemarker ��Ʒ��ʶ
     *  produnitid ������Ԫ��id
     * @return  �������Ӧ��ָ��
     */
    String checkstr_produceMarker(String str_produceMarker,int produnitid);
    /**
     * ָ��״̬�����쳣ʱҪ������ֵ�����
     * л����
     * 
     *  @param producemarker ��Ʒ��ʶ
     *   @param userId�û�id
     *    @param Int_instructionStateID״̬id
     *     @param produceUnit������Ԫ
     * @return  �������Ӧ��ָ��
     */
    String savet_ra_Instructionexception(int Int_instructionStateID,int userId,String produceMarker,int INT_GATHERID);
    /**
     * �鿴��Ʒ��ʶ���ҳ�������������ֵ��ָ����п��Ƿ���ڡ�
     * л����
     * 
     *  @param producemarker ��Ʒ��ʶ
     * @return  �������Ӧ��ָ��
     */
    
    String checkmaterielValue(String producemarker);
    /**
     * ��������ʱ���������Ԫ�Ͱ�β鿴��ص�ָ�
     * л����
     * 
     *  @param  int_produnitid,String str_date
     * @return  �������Ӧ��ָ��
     */
    
    
    String getInstructionByProduceUnitProduceDateWorkorder(int int_produnitid,String str_date,String workOrder);
    String getInstructionByProduceUnitProduceDate(int int_produnitid,String str_date);
    /**
     * ����ָ��汾
     * л����
     * 
     * 
     *
     */
    
    String updateInstructionVersioncode(Instruction instuction,String str_versioncode);
    /**
     * �˶��Ƿ���Խ��а汾����
     * л����
     * 
     *  
     *
     */
    String checksaveVersion(int int_produnitid,String str_date,String workOrder);
    /**
     * ��������ʱ���������Ԫ�ͻָ�״̬�鿴��ص�ָ����а汾�ָ�
     * л����
     * 
     *  
     *
     */
    String comebackVersion(int int_produnitid,String str_date,int int_stateid);
    
    /**
     * ��������ʱ���������Ԫɾ��ָ��
     * л����
     * 
     * 
     *
     */
    String deleteInstructionByProduceUnitProduceDateWorkorder(int int_produnitid,String str_date,String workOrder);
    
    
    /**
     * ͨ���ɼ���id��ϵͳʱ���ѯ��ص�ָ�
     * л����
     */
    String getGatherwork(int gatherid);
 
    /**
     * ����ָ������˳���
     * ����ָ��׷��
     * л����
     */
    String getInstructionMaxOrder(int int_produnitid,String str_date,String workOrder);
    /**
     * ͨ���ӿ�ʼʱ�䵽����ʱ�估������Ԫ��ѯ��ص�ָ��
     * л����
     */
   String getInstructionByStartAndEndProduceunitOrder(int produceid,String starttime,String endtime,String workorder);
   /**
    * �鿴������Ԫ���������ڵ�������ֵ
    * ����ָ���ʱ�ж�
    * л����
    */
   String getStr_produceMarkerbyproduceUnitandproduceDate(int produceid,String str_date);
  /**
   *  л����
   *  �˶������ϱ�ʶ����
   *  ָ���ʱ��
   */
   String checkinstructionmateriel(int produnitid);
   /**
    * л����
    * ������Ԫ������ʱ��
    * ͨ�������ϵ�ֵ�鿴ָ���Ƿ��ڱ༭״̬��
    */
   String checkinstructionedit(String producemarker ,int produnitid,String str_date);
   
   /**
    * �鿴������Ԫ���������ڵ�������ֵ
    * ����ָ���ʱ�ж�
    * 
    */
   public  String getInstructionsbyproduceUnit(int produceid);
   
   /**
    * ��ѯ��ǰ������Ԫ���ǵ�ǰ�������ڵ�ָ�����
    * 
    * @param produnitid
    * @param str_date
    * @return
    */
   public String getInstructionsByProdunitOtherProdDate(int produnitid,String str_date);

    /**�õ�ָ��������е�ָ�� л����
     * @return
     */
    String getallInstruction();
    
   /**����ָ�������ϵ�ָ�� л����
    * @param materile
    * @return
    */
    public String getInstructionbymaterile(String materile,int produceid);
   /**
    * ���
    * �������
    * Ϊ�˼����������ݿ�
    */
   public String export(int produceid,String starttime,String endtime,String workorder);
   
    
   /**
    * л����˶�������Ԫ�Ƿ����ɾ������
    */
   public String checkproducedelete(int produceid,String date);
   
   /**
    * �˶�ָ������Ƿ���ָ������ɾ��
    * ������
    * ����������Ԫ���������ڼ����
    *  @param  instuction
    *
    */
   public String checkAllInstructionByProdunitidDateWorkorder(int int_produnitid,String str_date,String workOrder);
   
}

