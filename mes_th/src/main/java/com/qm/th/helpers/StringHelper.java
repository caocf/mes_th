package com.qm.th.helpers;

/**
 * ������
 * 
 * @author Administrator
 */
public class StringHelper {
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