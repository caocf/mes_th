package com.qm.mes.framework;

/**
 * ����
 * 
 * @author �Ź��� 2007-6-5
 */
public interface IProcess extends IElement {

	/**
	 * ִ������
	 * 
	 * @param message
	 *            �û��ṩ�Ĳ���
	 * @return ��������ִ��״̬
	 */
	public ExecuteResult doProcess(IMessage message);

	/**
	 * ������������ϵͳ�������û���ӵ�˳��ִ�з��񣬶������ٴ�����
	 * 
	 * @param pi
	 *            ���������
	 */
	void addProcessItem(IProcessItem pi);



}
