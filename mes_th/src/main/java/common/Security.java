package common;

/**
 * ������
 * 
 * @author Administrator
 */
public class Security {
	/**
	 * ȥ���ַ����еĵ�����
	 * 
	 * @param s
	 * @return
	 */
	public static String clearSingleQuotationMarksFlaw(String s) {
		return s.replaceAll("'", "''");
	}
}
