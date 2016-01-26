package com.qm.mes.framework;

import java.util.List;

/**
 * ��Ϣ������������<br>
 * ������<br>
 * �û��ύ�������������ҵ��ؼ�������<br>
 * �������������������������������<br>
 * ����������Ϣ���û���Ϣ�����ӳ����ȡ�<br>
 * �쳣�б���¼����ִ�й����г��ֵ��쳣��
 * 
 * @author �Ź��� 2007-6-4
 */
public interface IMessage {
	/**
	 * ���ĳһ��������ֵ��ͬʱ��������Ϣ�ֲ��ҡ�<br>
	 * ˳���ǣ�ϵͳ�������û��ύ���������������
	 * 
	 * @param key
	 * @return
	 */
	public String getUserParameterValue(String key);

	public String getOutputParameterValue(String key);

	public Object getOtherParameter(String key);

	/**
	 * �����û��ύ����
	 * 
	 * @param key
	 *            ������
	 * @param value
	 *            ֵ
	 */
	public void setUserParameter(String key, String value);

	/**
	 * ���ü���������
	 * 
	 * @param key
	 *            ������
	 * @param value
	 *            ֵ
	 */
	public void setOutputParameter(String key, String value);

	/**
	 * �����쳣�б�
	 * 
	 * @return
	 */
	public List<ServiceException> getServiceException();

	/**
	 * ���һ���쳣����
	 * 
	 * @param se
	 */
	void addServiceException(ServiceException se);

	/**
	 * �������������û���չ
	 * 
	 * @return
	 */
	// Map<String, Object> getOtherParameter();
	void setOtherParameter(String key, Object value);

	/**
	 * ������ֵMap�в��� key ��ֵ��
	 * 
	 * @param key
	 *            ������
	 * @return �Ҳ�������null��
	 */
	Object getValue(String key);
}
