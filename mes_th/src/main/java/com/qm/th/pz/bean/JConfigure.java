package com.qm.th.pz.bean;

public class JConfigure {
	private String cSEQNo_A; // ��װ˳���
	private String cVinCode; // vin��
	private String cQADNo; // ����ܳɺ�
	private String cCarType; // ����
	private int index;
	String cCarNo;
	private int tfassId;
	private String tfass;
	private int printSetId;

	public String getCSEQNo_A() {
		return cSEQNo_A;
	}

	public void setCSEQNo_A(String no_A) {
		cSEQNo_A = no_A;
	}

	public String getCVinCode() {
		return cVinCode;
	}

	public void setCVinCode(String vinCode) {
		cVinCode = vinCode;
	}

	public String getCQADNo() {
		return cQADNo;
	}

	public void setCQADNo(String no) {
		cQADNo = no;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getCCarType() {
		return cCarType;
	}

	public void setCCarType(String carType) {
		cCarType = carType;
	}

	public String getCCarNo() {
		return cCarNo;
	}

	public void setCCarNo(String carNo) {
		cCarNo = carNo;
	}

	public int getTfassId() {
		return tfassId;
	}

	public void setTfassId(int tfassId) {
		this.tfassId = tfassId;
	}

	public String getTfass() {
		return tfass;
	}

	public void setTfass(String tfass) {
		this.tfass = tfass;
	}

	public int getPrintSetId() {
		return printSetId;
	}

	public void setPrintSetId(int printSetId) {
		this.printSetId = printSetId;
	}

}
