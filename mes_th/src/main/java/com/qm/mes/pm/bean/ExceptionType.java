package com.qm.mes.pm.bean;

/**
 * �쳣����
 * @author Xujia
 *
 */
public class ExceptionType {

	/**
	 *  �쳣�������
	 */
	private int id;
	/**
	 * ����
	 */
	private String name; 
	/**
	 * ״̬��ͣ��/���ã�
	 */
	private int state; 
	/**
	 * ϵͳ���ñ�ʾ�����õĲ�����ɾ����
	 */
	private int sysdefault; 
	
	
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
	 * @return state
	 */
	public int getState() {
		return state;
	}
	/**
	 * @param state Ҫ���õ� state
	 */
	public void setState(int state) {
		this.state = state;
	}
	/**
	 * @return sysdefault
	 */
	public int getSysdefault() {
		return sysdefault;
	}
	/**
	 * @param sysdefault Ҫ���õ� sysdefault
	 */
	public void setSysdefault(int sysdefault) {
		this.sysdefault = sysdefault;
	}
	
	

}
