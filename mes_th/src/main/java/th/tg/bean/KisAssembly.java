package th.tg.bean;

/**
 * ��װKIS��ѯ
 * 
 * @author YuanPeng
 *
 */
public class KisAssembly {
	
	/**
	 * ˳���
	 */
	private String seq;
	/**
	 * ����
	 */
	private String cartype;
	/**
	 * VIN
	 */
	private String vin;
	/**
	 * KIN
	 */
	private String kin;
	/**
	 * ��װ����ʱ��
	 */
	private String dABegin;
	/**
	 * CP6ʱ��
	 */
	private String dCp6Begin;
	
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
	public String getCartype() {
		return cartype;
	}
	public void setCartype(String cartype) {
		this.cartype = cartype;
	}
	
}
