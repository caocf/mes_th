package com.qm.mes.pm.bean;

/**
 * ������Ԫ��������
 * 
 * @author YuanPeng
 *
 */
public class ProLineItem {
	/**
	 * ���
	 */
	private int id;
	/**
	 * ������Ԫ��
	 */
	private int produceUnitId;
	/**
	 * ������Ԫ��
	 */
	private String prodUnitName;
	/**
	 * ˳���
	 */
	private int Order;
	/**
	 * ������Ԫ�������ú�
	 */
	private int LineId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProduceUnitId() {
		return produceUnitId;
	}
	public void setProduceUnitId(int produceUnitId) {
		this.produceUnitId = produceUnitId;
	}
	public int getOrder() {
		return Order;
	}
	public void setOrder(int order) {
		Order = order;
	}
	public int getLineId() {
		return LineId;
	}
	public void setLineId(int lineId) {
		LineId = lineId;
	}
	public String getProdUnitName() {
		return prodUnitName;
	}
	public void setProdUnitName(String prodUnitName) {
		this.prodUnitName = prodUnitName;
	}
}
