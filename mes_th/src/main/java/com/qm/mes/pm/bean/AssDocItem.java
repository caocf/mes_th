package com.qm.mes.pm.bean;

/**
 * װ���Ӽ���Ϣ
 * @author YuanPeng
 *
 */
public class AssDocItem {
	/**
	 * װ��ָʾ�����
	 */
	private int id;
	/**
	 * װ��ָʾ����
	 */
	private int AssDocId;
	/**
	 * ����
	 */
	private String name; 
	/**
	 * �Ӽ���ʾ
	 */
	private String code; 
	/**
	 * �Ӽ�����
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAssDocId() {
		return AssDocId;
	}
	public void setAssDocId(int assDocId) {
		AssDocId = assDocId;
	}

}
