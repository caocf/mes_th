package com.qm.mes.tg.bean;

public class GatherItem {
	private int id;//���
	private int order;//˳��
	//private String name;//�������ƣ�������е������ظ�ȥ��
	//private String desc;//��������
	private int materielruleId;//������֤����id
	private int gatherId;//�ɼ������
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getDesc() {
//		return desc;
//	}
//	public void setDesc(String desc) {
//		this.desc = desc;
//	}
	public int getGatherId() {
		return gatherId;
	}
	public void setGatherId(int gatherId) {
		this.gatherId = gatherId;
	}
	public int getMaterielruleId() {
		return materielruleId;
	}
	public void setMaterielruleId(int materielruleId) {
		this.materielruleId = materielruleId;
	}
}
