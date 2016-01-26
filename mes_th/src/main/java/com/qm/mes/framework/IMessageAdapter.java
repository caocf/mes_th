package com.qm.mes.framework;

/**
 * ��Ϣ������
 * 
 * @author �Ź��� 2007-6-21
 */
public interface IMessageAdapter extends IMessage {
	/**
	 * @param key
	 *            ������������
	 * @return ���������name
	 */
	public String getAdapterName(String key);

	/**
	 * @return ��������id
	 */
	String getProcessid();

	/**
	 * @return �������̱���
	 */
	String getServiceName();

	/**
	 * @param m
	 *            ����������������Դ
	 */
	void setSource(IMessage m);

}
