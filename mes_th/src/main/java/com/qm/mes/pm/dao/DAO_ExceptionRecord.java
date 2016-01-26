package com.qm.mes.pm.dao;

import com.qm.mes.pm.bean.ExceptionRecord;
/**
 * @author Xujia
 *
 */
public interface DAO_ExceptionRecord {
	/**
	 * �����쳣��¼��sql���
	 * @param exRecord
	 * @return
	 */
	String saveExceptionRecord (ExceptionRecord exRecord);
	
	/**
	 * ͨ����Ų���쳣��¼��sql���
	 * @param id
	 * @return
	 */
	String getExceptionRecordById(int id);
	
	/**
	 * ͨ�����ɾ���쳣��¼��sql���
	 * @param id
	 * @return
	 */
	String delExceptionRecordById(int id);
	/**
	 * ��ѯ��ȫ���쳣��¼��sql���
	 * @return
	 */
	String getAllExceptionRecord ();
	/**
	 * ͨ�����ӱ���豸��Ϣ
	 * @return
	 */
	String getDeviceByDevice_Unit(int unitid);
	/**
	 * ͨ���豸����ȡ������
	 * @param devicename
	 * @return
	 */
	String getdevicetypeBydevicename(String devicename);
	
	/**
	 * �ر��쳣��¼
	 * @param id
	 * @return
	 */
	String colseExceptionRecord(ExceptionRecord exRecord);
	/**
	 * �����쳣��¼
	 * @param id
	 * @return  
	 */
	String updateExceptionRecord(ExceptionRecord exRecord);
	
	/**
	 * ͨ����ʼ������ʱ���ѯ�쳣��¼�б�
	 * 
	 * @param str_starttime	��ʼʱ��
	 * @param str_endtime	����ʱ��
	 * @return	ͨ����ʼ������ʱ���ѯ�쳣��¼�б�
	 */
	public String getExcepRecordsByStartTimeEndTime(String str_starttime,String str_endtime);
}
