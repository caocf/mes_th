package mes.os.dao;
import mes.os.bean.*;

 /**
  * 
  * @author л���� 2009-5-13
  *
  */
public interface DAO_MPSPlan {
	 /** 
	  * �������ƻ�  л���� 
	  */ 
   String  saveMPSPlan(MPSPlan mpsPlan);
   /** 
	  *  �õ����е����ƻ� л���� 
	  */ 
   String getALLMPSPlan();
   /** 
	  *  �滻��ɾ�����ƻ����ڵ����ƻ� л���� 
	  */  
   String deleteMPSPlanbystartDate(String startDate);
   /** 
	  *   �鿴ĳһ�����ƻ�ͨ�����ƻ��� л����
	  */ 
   String getMPSPlanbyid(int id);
   /** 
	  *   ͨ���ƻ����ڵõ�һ�����ϵ����ƻ� л����
	  */ 
   String getMPSPlanbystartDate(String  dat_startDate);
   /** 
	*   �������ƻ� ͨ��idֵ л����
	*/ 
   String updateMPSPlanbyid(MPSPlan mpsplan);
	/** 
	*    ɾ�����ƻ� ͨ��idֵ л����
	*/
   String deleteMPSPlanbyid(int int_id);
}
