package th.tg.bean;

/**
 * ��װ��ѯ��
 * 
 * @author YuanPeng
 *
 */
public class AssemblySearch {

	/**
	 * ��װ˳���
	 */
	private String seq_W;
	/**
	 * ˳���
	 */
	private String seq;
	/**
	 * VIN(���̺�)
	 */
	private String vin;
	/**
	 * KIN
	 */
	private String kin;
	/**
	 * ��װ����ʱ��
	 */
	private String dWBegin;
	/**
	 * ��װ����ʱ��
	 */
	private String dABegin;
	/**
	 * CP6����ʱ��
	 */
	private String dCp6Begin;
	/**
	 * ��װ��������
	 */
	private String cfilename_w;
	/**
	 * ��װ��������
	 */
	private String cfilename_a;
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getKin() {
		return kin;
	}
	public void setKin(String kin) {
		this.kin = kin;
	}
	public String getDWBegin() {
		return dWBegin;
	}
	public void setDWBegin(String begin) {
		dWBegin = begin;
	}
	public String getDABegin() {
		return dABegin;
	}
	public void setDABegin(String begin) {
		dABegin = begin;
	}
	public String getDCp6Begin() {
		return dCp6Begin;
	}
	public void setDCp6Begin(String cp6Begin) {
		dCp6Begin = cp6Begin;
	}
	public String getSeq_W() {
		return seq_W;
	}
	public void setSeq_W(String seq_W) {
		this.seq_W = seq_W;
	}
	public String getCfilename_w() {
		return cfilename_w;
	}
	public void setCfilename_w(String cfilename_w) {
		this.cfilename_w = cfilename_w;
	}
	public String getCfilename_a() {
		return cfilename_a;
	}
	public void setCfilename_a(String cfilename_a) {
		this.cfilename_a = cfilename_a;
	}
}
