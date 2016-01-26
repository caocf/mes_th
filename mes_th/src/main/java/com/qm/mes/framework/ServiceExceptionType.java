package com.qm.mes.framework;

public enum ServiceExceptionType {
	/**
	 * ����ʧ
	 */
	SERVICELOST,
	/**
	 * ���ݿ����
	 */
	DATABASEERROR,
	
	/**
	 * ��������
	 */
	PARAMETERERROR,
	/**
	 * ȱ�ٹؼ�����
	 */
	PARAMETERLOST,
	/**
	 * δ֪���ݿ�����
	 */
	UNKNOWNDATABASETYPE,
	/**
	 * δ֪����
	 */
	UNKNOWN;

	public String toString() {
		switch (this) {
		case SERVICELOST:
			return "����ʧ";
		case DATABASEERROR:
			return "���ݿ����";
		case PARAMETERLOST:
			return "ȱ�ٹؼ�����";
		case UNKNOWN:
			return "δ֪����";
		case UNKNOWNDATABASETYPE:
			return "δ֪���ݿ�����";
		case PARAMETERERROR:
			return "��������";
		default:
			return "";
		}
	}
}
