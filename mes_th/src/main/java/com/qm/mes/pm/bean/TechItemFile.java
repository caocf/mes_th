package com.qm.mes.pm.bean;
/**
 * ���ղ������ļ�
 * 
 * @author YuanPeng
 *
 */
public class TechItemFile {
	/**
	 * ���
	 */
	private int id;
	/**
	 * ���ղ��������
	 */
	private int techDocItemId;
	/**
	 * ·��+����
	 */
	private String pathName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTechDocItemId() {
		return techDocItemId;
	}
	public void setTechDocItemId(int techDocItemId) {
		this.techDocItemId = techDocItemId;
	}
	public String getPathName() {
		return pathName;
	}
	public void setPathName(String pathName) {
		this.pathName = pathName;
	}
	
}
