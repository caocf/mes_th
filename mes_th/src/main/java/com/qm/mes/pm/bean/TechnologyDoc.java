package com.qm.mes.pm.bean;

import java.util.Date;
import java.util.List;
/**
 * ʵ��Bean���ڹ���˵�����ʵ����Ϣ
 * 
 * @author YuanPeng
 *
 */
public class TechnologyDoc {
	/**
	 * ���
	 */
	private int id;
	/**
	 * ����
	 */
	private String name; 
	/**
	 * ��Ʒ����ʾ
	 */
	private String materiel;
	/**
	 * ��ϸ����
	 */
	private List<TechDocItem> contents; 
	/**
	 * ����˵����ļ�Ҫ����
	 */
	private String description; 
	/**
	 * �����û����
	 */
	private int CreateUID;
	/**
	 * �����û���
	 */
	private String createUName;
	/**
	 * �������û����
	 */
	private int UpdateUID;
	/**
	 * �������û���
	 */
	private String updateUName;
	/**
	 * ��������
	 */
	private Date createDate;
	/**
	 * ����������
	 */
	private Date upDate;
	
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
	public List<TechDocItem> getContents() {
		return contents;
	}
	public void setContents(List<TechDocItem> contents) {
		this.contents = contents;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCreateUID() {
		return CreateUID;
	}
	public void setCreateUID(int createUID) {
		CreateUID = createUID;
	}
	public int getUpdateUID() {
		return UpdateUID;
	}
	public void setUpdateUID(int updateUID) {
		UpdateUID = updateUID;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpDate() {
		return upDate;
	}
	public void setUpDate(Date upDate) {
		this.upDate = upDate;
	}
	public String getCreateUName() {
		return createUName;
	}
	public void setCreateUName(String createUName) {
		this.createUName = createUName;
	}
	public String getUpdateUName() {
		return updateUName;
	}
	public void setUpdateUName(String updateUName) {
		this.updateUName = updateUName;
	}
	public String getMateriel() {
		return materiel;
	}
	public void setMateriel(String materiel) {
		this.materiel = materiel;
	}

	
}
