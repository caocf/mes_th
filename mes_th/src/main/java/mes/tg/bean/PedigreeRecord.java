package mes.tg.bean;

public class PedigreeRecord {
	private int id;//���
	private int gatherRecordId;//��������
	private String materielName;//��������
	private String materielValue;//����ֵ
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGatherRecordId() {
		return gatherRecordId;
	}
	public void setGatherRecordId(int gatherRecordId) {
		this.gatherRecordId = gatherRecordId;
	}
	public String getMaterielValue() {
		return materielValue;
	}
	public void setMaterielValue(String materielValue) {
		this.materielValue = materielValue;
	}
	public String getMaterielName() {
		return materielName;
	}
	public void setMaterielName(String materielName) {
		this.materielName = materielName;
	}
}
