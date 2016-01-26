package com.qm.mes.framework;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * ��Ϣ������������Ϣ���а�װ<br>
 * ���Service������<br>
 * ��service�ṩͨ��ӳ������Ĳ�����Ϣ�� �����service������ع��ܡ�
 * 
 * @author �Ź��� 2007-6-7
 */
class DefMessageAdapter implements IMessageAdapter {

	/**
	 * �����������ֺ���Ϣ�����ֵĶ�Ӧ��ϵ
	 */
	private Map<String, String> adapterNames = new Hashtable<String, String>();

	/**
	 * �����������̱�ʶ
	 */
	private String processid = "";

	/**
	 * ��������Ӧ�������еķ���ı���
	 */
	private String serviceName = "";

	/**
	 * Դ��Ϣ����
	 */
	private IMessage source = null;

	public DefMessageAdapter(String processid, String serviceName) {
		super();
		this.processid = processid;
		this.serviceName = serviceName;
	}

	public String getAdapterName(String key) {
		return adapterNames.get(key);
	}

	public String getProcessid() {
		return processid;
	}

	void setAdapterName(String key, String value) {
		adapterNames.put(key, value);
	}

	public void setSource(IMessage m) {
		source = m;
	}

	public void addServiceException(ServiceException se) {
		source.addServiceException(se);
	}

	public List<ServiceException> getServiceException() {
		return source.getServiceException();
	}

	/**
	 * @deprecated ��Է����ṩ�����û������Ĺ��ܡ�
	 */
	public void setUserParameter(String key, String value) {
	}

	public String getUserParameterValue(String key) {
		String sname = this.getAdapterName(key);
		Object o = null;
		if (sname != null)// ����ɹ�
			o = source.getValue(sname);
		else
			o = source.getValue(key);
		if (o != null && o instanceof String)
			return (String) o;
		return null;
	}

	public void setOutputParameter(String key, String value) {
		// �������������ʱ��ӷ��������ǰ׺
		source.setOutputParameter(serviceName + "." + key, value);
	}

	public String getOutputParameterValue(String key) {
		return getUserParameterValue(key);
	}

	public void setOtherParameter(String key, Object value) {
		// �����Լ��������������Ӧ��Ӧ���������򣺷������.����
		source.setOtherParameter(serviceName + "." + key, value);
	}

	public Object getOtherParameter(String key) {
		// otherParameter ��û��ӳ������ġ�
		// ���Ƿ����Լ��������ô��Ϊ��serviceName + "." + key��
		// ���������û��������ô��Ϊ��key��
		String skey = serviceName + "." + key;
		return source.getValue(skey) != null ? source.getValue(skey) : source
				.getValue(key);
	}

	public Object getValue(String key) {
		return source.getValue(key);
	}

	/**
	 * @return the serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}
}
