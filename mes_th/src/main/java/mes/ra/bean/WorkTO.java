package mes.ra.bean;

public class WorkTO {
	/**
	 * ���
	 */
	private int id;  
	/**
	 * ������Ԫ��
	 */
	private int produnitid;  
	/**
	 * ���
	 */
	private String workOrder; 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProdunitid() {
		return produnitid;
	}

	public void setProdunitid(int produnitid) {
		this.produnitid = produnitid;
	}

	public String getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(String workOrder) {
		this.workOrder = workOrder;
	}
}