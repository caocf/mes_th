package com.qm.mes.pm.bean;

import java.util.List;
/**
 * ʵ��Bean���ڷ�װ�豸��ʵ����Ϣ
 * @author Xujia
 *
 */
public class Device {

	/**
	 *  ���
	 */
	private int id;  
	/**
	 *  ����
	 */
	private String name; 
	/**
	 *  ��ţ��û����Ը�����豸������	
	 */
	private String code ; 
	/**
	 *  ��ǰ�豸����������豸������	
	 */
	private List<Integer> types = null; 
	/**
	 * ��ǰ�豸�Ĺ�������
	 */
	String description;  
	
	
	/**
	 * @return code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code Ҫ���õ� code
	 */
	public void setCode(String code) {
		this.code = code;
	}
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
	/**
	 * @return types
	 */
	public List<Integer> getTypes() {
		return types;
	}
	/**
	 * @param types Ҫ���õ� types
	 */
	public void setTypes(List<Integer> types) {
		this.types = types;
	}
	
	
	

}
