package mes.os.bean;

public class WorkSchedle {
	/**
	  * ���
	  */
	private int id;  
	/**
	  * ������Ԫ��
	  */
	private int produnitid;  
	/**
	  *���
	  */
	private String workOrder;  
	/**
	  *����ʱ��
	  */
	private String workSchedle;  
	/**
	  *��ǰ��
	  */
	private String advanceTime;  
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
	public String getWorkSchedle() {
		return workSchedle;
	}
	public void setWorkSchedle(String workSchedle) {
		this.workSchedle = workSchedle;
	}
	public String getAdvanceTime() {
		return advanceTime;
	}
	public void setAdvanceTime(String advanceTime) {
		this.advanceTime = advanceTime;
	}
	
}
