package com.qm.mes.framework;

/**
 * ��Ϣ����
 * 
 * @author �Ź��� 2007-6-21
 */
public final class MessageFactory {
	public static IMessage createInstance() {
		return new DefMessage();
	}
}
