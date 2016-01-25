package mes.framework;

/**
 * ��ҵ���� ϵͳ��ԭ��ҵ��<br>
 * ��Ӧһ��ʵ������������ִ�з�����
 * <p>
 * 
 * @author �Ź��� 2007-6-4
 */
public interface IService extends IElement {

	public String getClassName();

	void setClassName(String classname);

	/**
	 * ִ������
	 * 
	 * @param message
	 *            ��������Ϣ����
	 * @param processid
	 *            ����id
	 * @return ����ִ��״̬
	 */
	public ExecuteResult doService(IMessage message, String processid);

	/**
	 * ���˲���
	 * 
	 * @param message
	 *            ��������Ϣ����
	 * @param processid
	 *            ����id
	 * @return ����ִ��״̬
	 */
	public ExecuteResult undoService(IMessage message, String processid);

	/**
	 * ��undoService��doService
	 * 
	 * @param message
	 * @return ִ��ʧ�ܷ���false��
	 */
	public ExecuteResult redoService(IMessage message, String processid);

}
