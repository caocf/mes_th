package com.qm.th.beans;

/**
 * ����KIN��
 * 
 * @author GaoHF
 */
/**
 * 
 * 
 * @author GaoHF
 */
public class SpecKin {
	/** Ψһ��ʶF */
	private String id;
	/** KIN�� */
	private String kin;
	/** VIN�� */
	private String vin;
	/** ��װ����ʱ�� */
	private String dwbegin;
	/** ��װ����ʱ�� */
	private String dabegin;

	public String getKin() {
		return kin;
	}

	public void setKin(String kin) {
		this.kin = kin;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getDwbegin() {
		return dwbegin;
	}

	public void setDwbegin(String dwbegin) {
		if (dwbegin != null)
			dwbegin = dwbegin.replaceAll("\\.[0-9]+$", "");
		this.dwbegin = dwbegin;
	}

	public String getDabegin() {
		return dabegin;
	}

	public void setDabegin(String dabegin) {
		if (dabegin != null)
			dabegin = dabegin.replaceAll("\\.[0-9]+$", "");
		this.dabegin = dabegin;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
