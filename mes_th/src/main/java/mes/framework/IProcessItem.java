package mes.framework;

/**
 * ������
 * 
 * @author �Ź��� 2007-6-21
 */
public interface IProcessItem {

	/**
	 * @return ����������Դ�����쳣����ʽ
	 */
	ExceptionDispose getExceptionDisposeType();

	/**
	 * @return ���ض�Ӧ�ķ������
	 */
	String getServiceName();

	/**
	 * @return ���ض�Ӧ�ķ���id
	 */
	String getServicdId();

	/**
	 * @return ����˳��ֵ
	 */
	int getSort();

}
