package com.qm.mes.os.bean;
import java.util.Date;

public class UpLoadRecord {
	/**
	  * ���
	  */
	private int id;
	/**
	  * ��������ʱ��
	  */
	private Date upLoadDatime;
	/**
	  * �����ƻ��û���
	  */
	private String userName;
	/**
	  * �汾��
	  */
	private String versionCode;
	/**
	  * ����/ȡ������
	  */
	private int upload;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getUpLoadDatime() {
		return upLoadDatime;
	}
	public void setUpLoadDatime(Date upLoadDatime) {
		this.upLoadDatime = upLoadDatime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	public int getUpload() {
		return upload;
	}
	public void setUpload(int upload) {
		this.upload = upload;
	}
	
}
