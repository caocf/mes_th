package com.qm.mes.pm.bean;

import java.util.Date;
import java.util.List;
/**
 * ʵ��Bean���ڷ�װ����ָʾ����ʵ����Ϣ
 * 
 * @author YuanPeng
 *
 */
public class DistributionDoc {
	/**
	 * װ����ָʾ�����
	 */
	private int id ; 
	/**
	 * ����
	 */
	private String name; 
	/**
	 * ���������������Ԫ
	 */
	private int request_proUnit; 
	/**
	 * ����������Ԫ��
	 */
	private String req_proUnitName;
	/**
	 * ��Ӧ�����������Ԫ
	 */
	private int response_proUnit; 
	/**
	 * ��Ӧ������Ԫ��
	 */
	private String res_proUnitName;
	/**
	 * ��Ҫ�������ϵ�������Ԫ
	 */
	private int target_proUnit; 
	/**
	 * ��������������Ԫ��
	 */
	private String tar_proUnitName;
	/**
	 * ����ʱ��
	 */
	private Date createDate;
	/**
	 * ����ʱ��
	 */
	private Date upDate;
	/**
	 * �����û�ID
	 */
	private int CreateUID;
	/**
	 * �����û���
	 */
	private String createUName;
	/**
	 * �����û�ID
	 */
	private int UpdateUID;
	/**
	 * �����û���
	 */
	private String updateUName;
	/**
	 * ��ӦBOM�����ʾ
	 */
	private String materielType; 
	/**
	 * ������Ϣ����
	 */
	private List<DistriItem> DistriItems; 
	/**
	 * ����������Ϣ
	 */
	private String description; 
	/**
	 * BOM���
	 */
	private int bomId;
	
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
	public int getRequest_proUnit() {
		return request_proUnit;
	}
	public void setRequest_proUnit(int request_proUnit) {
		this.request_proUnit = request_proUnit;
	}
	public int getResponse_proUnit() {
		return response_proUnit;
	}
	public void setResponse_proUnit(int response_proUnit) {
		this.response_proUnit = response_proUnit;
	}
	public int getTarget_proUnit() {
		return target_proUnit;
	}
	public void setTarget_proUnit(int target_proUnit) {
		this.target_proUnit = target_proUnit;
	}
	public String getMaterielType() {
		return materielType;
	}
	public void setMaterielType(String materielType) {
		this.materielType = materielType;
	}
	public List<DistriItem> getDistriItems() {
		return DistriItems;
	}
	public void setDistriItems(List<DistriItem> distriItems) {
		DistriItems = distriItems;
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
	public int getBomId() {
		return bomId;
	}
	public void setBomId(int bomId) {
		this.bomId = bomId;
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
	public String getReq_proUnitName() {
		return req_proUnitName;
	}
	public void setReq_proUnitName(String req_proUnitName) {
		this.req_proUnitName = req_proUnitName;
	}
	public String getRes_proUnitName() {
		return res_proUnitName;
	}
	public void setRes_proUnitName(String res_proUnitName) {
		this.res_proUnitName = res_proUnitName;
	}
	public String getTar_proUnitName() {
		return tar_proUnitName;
	}
	public void setTar_proUnitName(String tar_proUnitName) {
		this.tar_proUnitName = tar_proUnitName;
	}

}
