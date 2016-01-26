package com.qm.mes.pm.dao;


import com.qm.mes.pm.bean.ExceptionCause;
import com.qm.mes.pm.bean.ExceptionType;

/**
 * @author Xujia
 *
 */
public interface DAO_Exception {
	/**
	 * �����쳣���͵�sql���
	 * @param exType
	 * @return
	 */
	String saveExceptionType (ExceptionType exType);
	/**
	 * ͨ����Ų���쳣���͵�sql���
	 * @param id
	 * @return
	 */
	String getExceptionTypeById(int id);
	/**
	 * ͨ�����ɾ���쳣���͵�sql���
	 * @param id
	 * @return
	 */
	String delExceptionTypeById(int id);
	/**
	 * ��ѯ��ȫ���쳣���͵�sql���
	 * @return
	 */
	String getAllExceptionType ();
	/**
	 * ͨ�����Ʋ���쳣���͵�sql���
	 * @param name
	 * @return
	 */
	String getExceptionTypeByName(String name);
	/**
	 * �����쳣ԭ���sql���
	 * @param exCause
	 * @return
	 */
	String saveExceptionCause (ExceptionCause exCause);
	/**
	 * ͨ����Ų���쳣ԭ���sql���
	 * @param id
	 * @return
	 */
	String getExceptionCauseById(int id);
	/**
	 * ͨ�����ɾ���쳣ԭ���sql���
	 * @param id
	 * @return
	 */
	String delExceptionCauseById(int id);
	/**
	 * ��ѯ��ȫ���쳣ԭ���sql���
	 * @return
	 */
	String getAllExceptionCause();
	/**
	 * ����ExceptionType����ͨ����id����
	 *  ���
	 * @param ExceptionType
	 * @return ����ExceptionType��sql���
	 */
	String updateExceptionType(ExceptionType exceptionType) ;
	/**
	 * ����ExceptionCause����ͨ����id����
	 *  ���
	 * @param ExceptionCause
	 * @return ����ExceptionCause��sql���
	 */
	String updateExceptionCause(ExceptionCause exceptionCause) ;
	/**
	 * ���ExceptionRecord�����Ƿ����쳣���ͺ�
	 *  ���
	 * @param id
	 * @return ��������
	 */
	String checkExceptionTypeById(int id);
	/**
	 * ���ExceptionRecord�����Ƿ����쳣ԭ���
	 *  ���
	 * @param id
	 * @return ��������
	 */
	String checkExceptionCauseById(int id);

}
