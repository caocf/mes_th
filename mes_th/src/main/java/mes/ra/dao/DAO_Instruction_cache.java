/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mes.ra.dao;

import mes.ra.bean.Instruction;

/**
 *
 * @author YuanPeng
 */
public interface DAO_Instruction_cache {
    /**
    * ����ָ���sql���
    *
    * @param instruction
    * @return
    */
    public String saveInstructionCache(Instruction instruction);

    /**
     * ͨ����Ų��ָ���sql���
     *
     * @param id
     * @return T_RA_INSTRUCTION�и�ID�������ֶ�
     */
    String getInstructionCacheById(int id);

    /**
     * ͨ�����ɾ��ָ���sql���
     *
     *@param Int_produnitid
     *@param str_date
     *@param workOrder
     *@param order
     *@return ɾ��T_RA_INSTRUCTION�и�order�ļ�¼
     */
    String delInstructionCacheByProduceUnitDateWorkorderOrder(int Int_produnitid, String str_date,String workOrder,int order);

    /**
     * ��ָ����и��²�����sql���
     *
     * @param instruction
     * @return ����T_RA_INSTRUCTION�и�ID�ļ�¼
     */
    String updateInstructionCache(Instruction instruction);

     /**
     * ��ѯT_RA_INSTRUCTION�м�¼����
     * @return T_RA_INSTRUCTION�м�¼����
     */
    public String getInstructionCacheCount();

    /**
     * ��ѯT_RA_INSTRUCTION�����м�¼
     *
     * @return ����T_RA_INSTRUCTION�����м�¼
     */
    public String getAllInstructionCache();
    
    /**
     * ��ѯT_RA_INSTRUCTION�����м�¼��˳������
     *
     * @return ����T_RA_INSTRUCTION�����м�¼��˳������
     */
    public String getAllInstructionCacheByOrder();

    /**
     * ���ҵ�ǰ���ں�������Ԫ�ıȸ�˳���С��˳���ָ��
     * 
     * @param Int_produnitid
     * 			������ԪID
     * @param str_date
     * 			��������
     * * @param workOrder
     * 			���
     * @param Int_instructOrder
     * 			��ҵָ��˳���
     * @return ��ҵָ�����
     */
    public String getByOrderMinus(int Int_produnitid, String str_date,String workOrder,int Int_instructOrder);

    /**
     * ���ҵ�ǰ���ں�������Ԫ�ıȸ�˳��Ŵ��˳���ָ��
     * 
     * @param Int_produnitid
     * 			������ԪID
     * @param str_date
     * 			��������
     * @param workOrder
     * 			���
     * @param Int_instructOrder
     * 			��ҵָ��˳���
     * @return ��ҵָ�����
     */
    public String getByOrderPlus(int Int_produnitid, String str_date,String workOrder,int Int_instructOrder);

    /**
     * ͨ��������Ԫ�š��������ڡ�ָ��˳��ŷ���ָ���sql���
     *
     * @param Int_produnitid
     * 			������Ԫ��
     * @param str_date
     * 		��������
     * @param workOrder
     * 			���
     * @param order
     * 		ָ��˳���
     * @return ����T_RA_INSTRUCTION�и�������Ԫ�š��������ڡ�ָ��˳��ŵļ�¼
     */
    public String IssuanceByProduceUnitDateWorkorderOrder(int Int_produnitid, String str_date,String workOrder,int order);
    
    /**
     * ��������ʱ���������Ԫ��β鿴��ص�ָ�
     *  @param  Str_produceUnit,String str_date,workOrder
     * @return  �������Ӧ��ָ��
     */
  public  String getInstructionCacheByProduceUnitProduceDateOrder(int Int_produnitid,String str_date,String workOrder);

  /**
   * ɾ��ָ����ʱ������������
   *
   * @return
   */
  public String DeleteInstructionCache();
   
  /**
   * ͨ��������Ԫ�š��������ڲ�ѯ��¼����
   * 
   * @param ProduceUnitID
   * 			������Ԫ��
   * @param str_date
   * 			��������
   * @param workOrder
   * 			���
   * @return ����ͨ��������Ԫ�š��������ڲ�ѯ��¼������
   */
   public String getCountByProUnitDateOrder(int ProduceUnitID, String str_date,String workOrder);
   
   /**
    * ɾ����������Ԫ�ð��������ʱָ��
    * 
    * @param ProduceUnitID
    * 			������Ԫ��
    * @return 
    */
   public String DeleteInstructionCacheByProdUnitIdproducedateWorkorder(int ProduceUnitID,String str_date,String workOrder);
   
   /**
    * ͨ��������Ԫ�š��������ڡ�ָ��˳��Ų�ѯָ��
    * 
    * @param ProduceUnitID
    * 			������Ԫ��
    * @param str_date
    * 			��������
    * @param workOrder
    * 			���
    * @param Int_instructOrder
    * 				ָ��˳���
    * @return ��ҵָ�����
    */
   public String getInstructionByProdUnitDateWorkorderOrder(int ProduceUnitID,String str_date,String workOrder,int Int_instructOrder);

   /**
    * ���������ڡ�������Ԫ�����ڸ�ָ��˳��ŵ�ָ��˳��ż�1
    * 
    *  @param  ProduceUnitID
    *  		������Ԫ��
    *  @param  str_date
    *  		�������ں�
    *  @param  workOrder
    *  		���
    *  @param  order
    *  		ָ��˳���
    * @return  ����sql
    */
    public  String MinusInstructionOrder(int ProduceUnitID,String str_date,String workOrder,int order);
   
    /**
     * ������������Ԫ���������ڵ�����ָ��
     * 
     * @param Int_produnitid
     * @param str_date
     * @param workOrder
     * @return
     */
    public String IssuanceAllByProduceUnitDateWorkorder(int Int_produnitid, String str_date,String workOrder);
   
    /**
     * ��ѯ��ʱ�����˳���
     * 
     * @param int_produnitid
     * @param str_date
     * @param workOrder
     * @return
     */
    public String getInstructionMaxOrder(int int_produnitid,String str_date,String workOrder);
    
    /**
 	 * �жϸ�������Ԫ���������ڡ���Ʒ��ʶ��ָ������
 	 * 
 	 * @param produnitid
 	 * @param str_date
 	 * @param workOrder
 	 * @param marker
 	 * @return
 	 */
	public String getCountByProUnitDateWorkorderMarker(int  produnitid,String str_date,String workOrder,String marker);
    
	/**
 	 * ��ѯ��������Ԫ���������ڡ���Ʒ��ʶ��ָ��
 	 * 
 	 * @param produnitid
 	 * @param str_date
 	 * @param workOrder
 	 * @param marker
 	 * @return
 	 */
 	public String getInstructionByProUnitDateWorkorderMarker(int  produnitid,String str_date,String workOrder,String marker);
    

    /**
    * ���ָ��ָ��Ŷ�Ӧ��id
    * ���
    *  @param  int_order,str_date,ProduceUnitID,workOrder
    * @return  ����sql
    */
    public  String selectInstructionCacheid(int int_order,String str_date,int ProduceUnitID,String workOrder);   

    
    /**
     * ���ָ��ָ��ŵ�����
     * ���
     *
     *  @param  int_order,str_date,ProduceUnitID,workOrder
     * @return  ����sql
     */
     public  String selectInstructionCacheNum(int int_order,String str_date,int ProduceUnitID,String workOrder);
   	 /**
      * ����ָ��ָ��ŵ�����
      * ���
      *
      *  @param  int_count,int_order
      * @return  ����sql
      */
      public  String updateInstructionCacheNum(int int_count,int int_order,String str_date,int ProduceUnitID,String workOrder);

      /**
       * ͨ�����ɾ��ָ���sql���
       * ���
       * @param order
       * @return ɾ��T_RA_INSTRUCTION�и�order�ļ�¼������ɾ����
       */
      String deleteInstructionCacheByOrder(int order,String str_date,int ProduceUnitID,String workOrder);
      /**
       * ����ָ��ָ���
       * ���
       *
       *  @param  order
       * @return  ����sql
       */
       public  String updateInstructionCacheOrder(int order,String str_date,int ProduceUnitID,String workOrder);
       /**
        * �������Ҫ���ָ��
        * ���
        *
        *  @param  order,order1,count,str
        * @return  ����sql
        */
        public  String insertInstructionCache(int order1,String str,int count,int order);
        
        /**
         * ͨ��������Ԫ��ѯ��Ʒ��ʶ
         * 
         * @param ProduceUnitID
         * @return
         */
        public String selectproducemarker(int ProduceUnitID);

        
        /***
         *  л�����ѯ�༭�ռ����������ϵ�ֵ ��������Ԫ
         */
        public String getInstructionbymateriel(String materiel,int int_produnitid);
}
