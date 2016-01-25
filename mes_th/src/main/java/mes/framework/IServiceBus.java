package mes.framework;

/**
 * ��ҵ��������<br>
 * ������&ִ�����̺ͷ��񣬲��������Ϣ������־<br>
 * �����û������̵�����<br>
 * �������̶Է��������<br>
 * ��ط���ִ�е�״̬������������������̡�
 * 
 * @author �Ź��� 2007-6-4
 */
public interface IServiceBus {
	/**
	 * �������̵���������ִ��һ������
	 * 
	 * @param serviceid
	 *            ����id
	 * @param processid
	 *            ����id
	 * @param message
	 *            ��Ϣ���������ṩ���������Ķ���
	 * @return ���ط�������״̬
	 */
	public ExecuteResult doService(String serviceid, String processid,
			IMessage message);

	/**
	 * ���� �û�������ִ������
	 * 
	 * @param processid
	 *            ����ID
	 * @param message
	 *            ��Ϣ�����û��ṩ
	 * @return ��������ִ�н��
	 */
	public ExecuteResult doProcess(String processid, IMessage message);
}
