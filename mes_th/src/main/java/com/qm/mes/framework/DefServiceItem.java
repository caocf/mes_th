package com.qm.mes.framework;

/**
 * ������
 * 
 * @author �Ź��� 2007-6-7
 */
class DefServiceItem implements IServiceItem {

	/**
	 * ��
	 */
	private String name;

	/**
	 * ����
	 */
	private String descr;

	public DefServiceItem(String name, String descr) {
		super();
		this.name = name;
		this.descr = descr;
	}

	/**
	 * @return the descr
	 */
	public String getDescr() {
		return descr;
	}

	/**
	 * @param descr
	 *            the descr to set
	 */
	public void setDescr(String descr) {
		this.descr = descr;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
