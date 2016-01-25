package mes.framework;

/**
 * �쳣����ʽ���������Լ���Ϊ�Լ��Ĺ���
 * 
 * @author �Ź��� 2007-6-21
 */
public enum ExceptionDispose {
	/**
	 * ����
	 */
	ignore,
	/**
	 * �˳�
	 */
	exit,
	/**
	 * �ع�
	 */
	rollback;
	/**
	 * ͨ��������ַ������ض�Ӧ�Ĵ���ʽ
	 * 
	 * @param string
	 * @return Ĭ��Ϊ���˳���
	 */
	public static ExceptionDispose getInstance(String string) {
		if (string.toLowerCase().equals("rollback"))
			return rollback;
		if (string.toLowerCase().equals("iqnore"))
			return ignore;
		if (string.toLowerCase().equals("exit"))
			return exit;
		return exit;
	}
}
