package com.qm.mes.pm.bean;
/**
 * 
 * ʵ��Bean���ڷ�װ������Ϣ�����ʵ����Ϣ
 * 
 * @author YuanPeng
 *
 */
public class DistriItem {
	/**
	 * ����ָʾ�����
	 */
	private int id; 
	/**
	 * ����
	 */
	private String name; 
	/**
	 * ���ϱ�ʾ
	 */
	private String matitem; 
	/**
	 * ����
	 */
	private int count; 
	/**
	 * ����ָʾ����
	 */
	private int DistributionDocId;
	/**
	 * ��ǰ�豸�Ĺ�������
	 */
	private String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMatitem() {
		return matitem;
	}
	public void setMatitem(String matitem) {
		this.matitem = matitem;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDistributionDocId() {
		return DistributionDocId;
	}
	public void setDistributionDocId(int distributionDocId) {
		DistributionDocId = distributionDocId;
	}

}
