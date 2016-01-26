package com.qm.mes.framework;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


/**
 * ��Ϣ��Ĭ��ʵ�֡�
 * 
 * @author �Ź��� 2007-6-7
 */
class DefMessage implements IMessage {
	/**
	 * ���������Ϣ��
	 */
	private Map<String, String> outputParameter = new Hashtable<String, String>();

	/**
	 * ϵͳ������
	 */
	private Map<String, Object> otherParameter = new Hashtable<String, Object>();

	/**
	 * �û�������
	 */
	private Map<String, String> userParameter = new Hashtable<String, String>();

	private final String preUser = "user.";

	private final String preOutput = "output.";

	private final String preOther = "other.";

	/**
	 * �쳣��¼��
	 */
	private List<ServiceException> serviceException = new ArrayList<ServiceException>();

	public Map<String, String> getOutputParameter() {
		return new Hashtable<String, String>(outputParameter);
	}

	public Map<String, String> getUserParameter() {
		return new Hashtable<String, String>(userParameter);
	}

	public Object getValue(String key) {
		if (userParameter.containsKey(preUser + key))
			return userParameter.get(preUser + key);

		if (outputParameter.containsKey(preOutput + key))
			return outputParameter.get(preOutput + key);

		if (otherParameter.containsKey(preOther + key))
			return otherParameter.get(preOther + key);

		return null;
	}

	public Map<String, Object> getOtherParameter() {
		return new Hashtable<String, Object>(otherParameter);
	}

	public void setOutputParameter(String key, String value) {
		outputParameter.put(preOutput + key, value);
	}

	public void setUserParameter(String key, String value) {
		userParameter.put(preUser + key, value);
	}

	public List<ServiceException> getServiceException() {
		return new ArrayList<ServiceException>(serviceException);
	}

	public void addServiceException(ServiceException se) {
		serviceException.add(se);
	}

	public void setOtherParameter(String key, Object value) {
		otherParameter.put(preOther + key, value);
	}

	public Object getOtherParameter(String key) {
		return this.otherParameter.get(preOther + key);
	}

	public String getOutputParameterValue(String key) {
		return this.outputParameter.get(preOutput + key);
	}

	public String getUserParameterValue(String key) {
		return this.userParameter.get(preUser + key);
	}
}
