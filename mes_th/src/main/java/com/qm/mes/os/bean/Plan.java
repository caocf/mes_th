package com.qm.mes.os.bean;
import java.util.*;
 /**
  * 
  * @author  л���� 2009-5-13
  *
  */
public class Plan {
	/**
	  *��ҵ�ƻ����
	  */
	private  int id ; 
	/**
	  *�ƻ�����
	  */
	private Date planDate; 
	/**
	  *��������
	  */
	private Date produceDate;  
	/**
	  *������
	  */
	private  String orderFormId;  
	/**
	  *�ƻ����κ�
	  */
	private  int planGroupId; 
	/**
	  *��Ʒ����ʶ���糵���룩
	  */
	private  String produceType;  
	/**
	  *��Ʒ����
	  */
	private String produceName ;
	/**
	  *��Ʒ��ʶ������̺š����κţ�
	  */
	private String produceMarker ; 
	/**
	  *������Ԫ
	  */
	private int produnitid; 
	/**
	  *����
	  */
	private String workTeam; 
	/**
	  *���
	  */
	private String workOrder;  
	/**
	  *����
	  */
	private int amount; 
	/**
	  *�汾��
	  */
	private String versioncode;  
	/**
	  *����
	  */
	private int upload;  
	/**
	  *�ƻ�˳���
	  */
	private int planOrder;
	/**
	  *����
	  */
	private String description;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPlanOrder() {
		return planOrder;
	}
	public void setPlanOrder(int planOrder) {
		this.planOrder = planOrder;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getPlanDate() {
		return planDate;
	}
	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}
	public Date getProduceDate() {
		return produceDate;
	}
	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}
	public String getOrderFormId() {
		return orderFormId;
	}
	public void setOrderFormId(String orderFormId) {
		this.orderFormId = orderFormId;
	}
	public int getPlanGroupId() {
		return planGroupId;
	}
	public void setPlanGroupId(int planGroupId) {
		this.planGroupId = planGroupId;
	}
	
	public String getProduceType() {
		return produceType;
	}
	public void setProduceType(String produceType) {
		this.produceType = produceType;
	}

	public String getProduceName() {
		return produceName;
	}
	public void setProduceName(String produceName) {
		this.produceName = produceName;
	}
	public String getProduceMarker() {
		return produceMarker;
	}
	public void setProduceMarker(String produceMarker) {
		this.produceMarker = produceMarker;
	}
	public int getProdunitid() {
		return produnitid;
	}
	public void setProdunitid(int produnitid) {
		this.produnitid = produnitid;
	}
	public String getWorkTeam() {
		return workTeam;
	}
	public void setWorkTeam(String workTeam) {
		this.workTeam = workTeam;
	}
	public String getWorkOrder() {
		return workOrder;
	}
	public void setWorkOrder(String workOrder) {
		this.workOrder = workOrder;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getVersioncode() {
		return versioncode;
	}
	public void setVersioncode(String versioncode) {
		this.versioncode = versioncode;
	}
	public int getUpload() {
		return upload;
	}
	public void setUpload(int upload) {
		this.upload = upload;
	}


}
