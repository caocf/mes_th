package com.qm.mes.os.dao;
import com.qm.mes.os.bean.*;
/**
 * 
 * @author  л����
 *
 */

public interface DAO_Plan {
	/** 
	* ������ҵ�ƻ�  л����
      */
	 String savePlan(Plan plan);
     /** 
  	* ����������Ԫ���������ڣ��Ͱ�β�����ҵ�ƻ� ���İ汾��Ϣ л����
      */
	 String getPlanbyProdateProidWorder(String produceDate,int produnitid,String workOrder );
    /** 
    * ͨ���汾�ŵõ���ҵ�ƻ�  л����
      */
	 String getPlanbyversioncord(String versioncode);
      /** 
      * ɾ��ָ���汾�ŵ���ҵ�ƻ�  л����
       */
	  String deletePlanbyversioncode(String versioncode);
      /** 
      * ͨ���汾int_id  �õ��汾�ƻ���Ϣ  л����
      */
	  String getPlanbyversionid(int int_id);
      /** 
      * ��ѯһ��ʱ���ڵ���ҵ�ƻ� л����
      */
	  String getplanbybystarttimeandendtimeproduceunitoverendtime(int produnitid,String workOrder,String overtime,String endtime);
      /** 
      * �޸�ָ��idֵ�ļƻ�  л����
      */
	  String updatePlanbyid(Plan plan);
      /** 
      * �õ�ָ��id�ļƻ�   л����
      */
	  String getplanbyid(int id );
      /** 
      * ɾ��������Ԫ���������ڣ��Ͱ��  ˳��ŵ���ҵ�ƻ� л����
      */ 
	  String deleteplanbyPlanOrder(String produceDate,int produnitid,String workOrder,int planorder);
      /** 
      * ����ָ��˳�򷽷�  л����
      */ 
	  String updatePlanOrder(String produceDate,int produnitid,String workOrder,int planorder);
       /** 
       * ���ƻ������� �ƻ�˳��ͨ���ƻ���id  �ͻ�����˳���л����
       */
	  String down_or_upPlanOrderbyplanid(int id,int nextorder);
	
      /** 
      * �����༭�ƻ� л����
      */ 
	  String disfrockplan(String produceDate,int produnitid,String workOrder);
      /** 
      * �ύ�༭�ƻ� л����
      */ 
	  String submitplan(String produceDate,int produnitid,String workOrder,String versioncode);
      /** 
      *��ѯ�ύ�İ汾�� л����
      */
	  String getversioncodewhensubmit(String produceDate,int produnitid,String workOrder);
      /** 
      * ������ҵʱֻ�ܿ������İ汾���Ұ汾�Ų�Ϊtemp л����
      */ 
	  String getmaxPlanexcepttemp(String produceDate,int produnitid,String workOrder);
      /** 
       * ������ҵ�ƻ� л����
       */
	  String uploadplan(String produceDate,int produnitid,String workOrder);
      /** 
       * ȡ����ҵ�ƻ����� л����
      */
    String  canceluploadplan(String produceDate,int produnitid,String workOrder);
     /** 
      * ��ѯ��ͬ��������������Ԫ��ε����汾��Ϣ л����
     */
    String geteverydaymaxversioncodeplan();
     /** 
      * �滻ʱ���˶Ա�������ݵ���ҵ�ƻ�  л����
      */
    String geteverydaymaxversioncodeplanexceptnow(String produceDate,int produnitid,String workOrder);
     /** 
      * ��ѯ��ͬ��������������Ԫ��ε����汾��Ϣ ���Ƿ���Ҫ���ҵ�������ֵ л����
      */
    String getmaterielfromeditplan(String produceDate,int produnitid,String workOrder,String materiel);
      /** 
     * �༭ҳ����Ҽ�¼ �汾����temp  л����
      */ 
    String getplancontainstemp(String produceDate,int produnitid,String workOrder);
     /** 
     * �˶��Ƿ����order�ļƻ�˳��� л����
     */
    String checkplanorder(String produceDate,int produnitid,String workOrder,int order);

    /**ͨ��id��ѯ������Ԫ������ڼ�鹤��ʱ�̱�ɾ������
	 * author : ������
	 */
	String getAllPlanByProdunitidOrder(int Produnitid,String Order);
	/** 
	* �ƻ�����ָ��ʱ û�����ļƻ�����Ա��������л����
      */
	String getmaxPlanexcepttempupload(String produceDate,int produnitid,String workOrder);
	/**
	 * @author л����
	 * �ڼƻ�����ʱ����������֤�����������汾��������Ԫ�������Ƿ��ظ���
	 *
	 */
	String getplanbymaterielproid_upload_andmaxversion(String materiel,int produnitid);

}
