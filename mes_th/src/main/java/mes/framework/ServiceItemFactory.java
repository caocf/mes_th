package mes.framework;

/**
 * �������<br>
 * 
 * @author �Ź��� 2007-6-21
 */
public final class ServiceItemFactory {

	/**
	 * ͨ����������������ֺ��������һ��������ӿڵ�ʵ��
	 * 
	 * @param name
	 *            ����
	 * @param descr
	 *            ����
	 * @return �½�����ʵ��
	 */
	public static IServiceItem createParameterItem(String name, String descr) {
		return new DefServiceItem(name, descr);
	}

}
