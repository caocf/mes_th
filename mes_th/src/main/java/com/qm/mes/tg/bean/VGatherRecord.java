/*
 * create by cp 
 * view gahterRecord
 * 20080725
 */
package com.qm.mes.tg.bean;

import java.util.Date;

public class VGatherRecord {

	private int id;//���
	//private int gatherId;//�ɼ������
	private String gatherIdName;//�ɼ�������
	private String materielName;//����������
	private String materielValue;//������ֵ
	private int userId;//�û�id
	private Date date;//����ʱ��
	private String datestr;//���ڶ�����ַ�����ֵ

	 public String getDatestr() {
	  return datestr;
	 }
	 public void setDatestr(String datestr) {
	  this.datestr = datestr;
	 }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	/*
	public int getGatherId() {
		return gatherId;
	}
	public void setGatherId(int gatherId) {
		this.gatherId = gatherId;
	}
	*/

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
	public String getGatherIdName() {
		return gatherIdName;
	}
	public void setGatherIdName(String gatherIdName) {
		this.gatherIdName = gatherIdName;
	}
}
