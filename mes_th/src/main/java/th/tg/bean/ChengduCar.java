package th.tg.bean;


/**
 * �ɶ���
 * 
 * @author YuanPeng
 *
 */
public class ChengduCar {

	/**
	 * KIN��
	 */
	private String cCarNo;
	/**
	 * VIN
	 */
	private String cVinCode;
	/**
	 * ��װ����ʱ��
	 */
	private String dWBegin;
	public String getCCarNo() {
		return cCarNo;
	}
	public void setCCarNo(String carNo) {
		cCarNo = carNo;
	}
	public String getCVinCode() {
		return cVinCode;
	}
	public void setCVinCode(String vinCode) {
		cVinCode = vinCode;
	}
	public String getDWBegin() {
		return dWBegin;
	}
	public void setDWBegin(String begin) {
		dWBegin = begin;
	}
}
