package th.report.entities;

/**
 * �����������
 * 
 * @author Ajaxfan
 */
public class RequestParam {
	/** ��� */
	private String groupId;
	/** ���� */
	private String requestDate;
	/** ���̺� */
	private String chassisNumber;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	public String getChassisNumber() {
		return chassisNumber;
	}

	public void setChassisNumber(String chassisNumber) {
		this.chassisNumber = chassisNumber;
	}
}
