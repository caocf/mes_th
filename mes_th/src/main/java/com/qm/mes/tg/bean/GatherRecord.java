package com.qm.mes.tg.bean;

import java.util.Date;

public class GatherRecord {
	private int id;// ���
	private int gatherId;// �ɼ������
	private String materielName;// ����������
	private String materielValue;// ������ֵ
	private int userId;// �û�id
	private Date date;// ����ʱ��

	private String str; // һ���м���
	/**
	 * modify by yld
	 */
	private String datestr;// ���ڶ�����ַ�����ֵ

	public String getDatestr() {
		return datestr;
	}

	public void setDatestr(String datestr) {
		this.datestr = datestr;
	}

	/**
	 * end of modify by yld
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGatherId() {
		return gatherId;
	}

	public void setGatherId(int gatherId) {
		this.gatherId = gatherId;
	}

	public String getMaterielValue() {
		return materielValue;
	}

	public void setMaterielValue(String materielValue) {
		this.materielValue = materielValue;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMaterielName() {
		return materielName;
	}

	public void setMaterielName(String materielname) {
		this.materielName = materielname;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
}
