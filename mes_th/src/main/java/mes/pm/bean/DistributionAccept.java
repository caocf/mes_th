package mes.pm.bean;

import java.util.Date;

/**
 * ʵ��Bean���ڷ�װ������Ϣ��ȷ�ϵ�
 * 
 * @author YuanPeng
 *
 */
public class DistributionAccept {
	/**
	 * ����ȷ�ϵ����
	 */
	private int id;
	/**
	 * ���͵�ID
	 */
	private int DisDocId;
	/**
	 * ״̬��δ����/�Ѵ���
	 */
	private int state; 
	/**
	 * ��Ӧ�û�ID
	 */
	private int responseUID; 
	/**
	 * ���͵����ϱ�ʾ
	 */
	private String materiel; 
	/**
	 * ����ʱ��
	 */
	private Date requestDate;
	/**
	 * ��Ӧʱ��
	 */
	private Date responseDate;
	/**
	 * ��Ӧ������Ԫ��
	 */
	private int targetProUnit;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getDisDocId() {
		return DisDocId;
	}
	public void setDisDocId(int disDocId) {
		DisDocId = disDocId;
	}
	public int getResponseUID() {
		return responseUID;
	}
	public void setResponseUID(int responseUID) {
		this.responseUID = responseUID;
	}
	public String getMateriel() {
		return materiel;
	}
	public void setMateriel(String materiel) {
		this.materiel = materiel;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public Date getResponseDate() {
		return responseDate;
	}
	public void setResponseDate(Date responseDate) {
		this.responseDate = responseDate;
	}
	public int getTargetProUnit() {
		return targetProUnit;
	}
	public void setTargetProUnit(int targetProUnit) {
		this.targetProUnit = targetProUnit;
	}

}
