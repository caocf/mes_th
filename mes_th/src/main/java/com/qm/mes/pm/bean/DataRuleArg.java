package com.qm.mes.pm.bean;
/**
 * ʵ��Bean���ڹ��������ʵ����Ϣ
 * @author Xujia
 *
 */
public class DataRuleArg {

	/**
	 * ���
	 */
	private int id;
	/**
	 * �����
	 */
	private int int_dataruleid;
	/**
	 * ����
	 */
	private String name; 
	/**
	 * ��ǰ����������
	 */
	private String description; 
	
	
	/**
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description Ҫ���õ� description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id Ҫ���õ� id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return int_dateruleid
	 */
	public int getInt_dataruleid() {
		return int_dataruleid;
	}
	/**
	 * @param int_dateruleid Ҫ���õ� int_dateruleid
	 */
	public void setInt_dataruleid(int int_dataruleid) {
		this.int_dataruleid = int_dataruleid;
	}
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name Ҫ���õ� name
	 */
	public void setName(String name) {
		this.name = name;
	}

	

}
