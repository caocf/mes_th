package mes.ra.dao;
import mes.ra.bean.*;

public interface DAO_InstructionHistory {
	/**
	 * 
	 * ����ָ��汾���ݵ�sql���
	 * л����
	*/
	String saveVersionHistory(Instruction instruction,String str_versioncode);
	/**
	 * 
	 * ͨ���汾�Ų��ָ��汾���ݵ�sql���
	 * л����
	*/
	String getVersionHistory(String versionCode);
	/**
	 * 
	 * ͨ���汾��ɾ����Ӧ�İ汾���ݵ�sql���
	 * л����
	*/
	String delVersionHistory(String versionCode);
	
	/**
	 * 
	 * ͨ��ָ��汾��ŵõ�ָ��汾��¼��
	 * л����
	*/
	String getInstructionHistory(int int_id);
	

	/**
	 * 
	 * ͨ��������Ԫ���������ڲ�ѯָ��汾��¼��
	 * л����
	*/
	String getInstructionhistorybyproduceunitdate(int int_produnitid,String Dat_produceDate);
	 /**
	  * �������İ汾��
	  * л����
	  */
	
	     String checkcodebyproduceunitanddateWorkorder(int int_produnitid,String str_date,String workOrder);
	     /**
	      * л����
	      * ͨ���汾�ŵ�ָ����ʷ���в������ݵļ���
	      */
	     String getInstructionbyversioncode(String versioncode);
}
