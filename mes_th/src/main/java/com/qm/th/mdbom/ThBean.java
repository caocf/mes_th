package com.qm.th.mdbom;

/**
 * ���BOM��Ϣ
 * 
 * @author gaohf
 * @version 1.0.1
 * @date 2009-9-24
 */
public class ThBean {
	/** ���ݿ��ʶ */
	private int id;
	/** �������� */
	private String ctfass;
	/** ���������� */
	private String nTFASSNum;
	/** ����������� */
	private String cVWMainPart;
	/** ������������� */
	private String cVWMainPartQuan;
	/** ��������������� */
	private String cVWMainPartType;
	/** ����������� */
	private String cVWSubPart;
	/** ������������� */
	private String nVWSubPartQuan;
	/** ��������������� */
	private String nVWSubPartType;
	/** ���������� */
	private String cTFASSName;
	/** �������������� */
	private String cTFASSTypeNo;
	/** ������ */
	private String cQADNo;
	/** �Ƿ���ȳ��� */
	private String cIsTempCar;
	/** ��ע */
	private String cRemark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCtfass() {
		return ctfass;
	}

	public void setCtfass(String ctfass) {
		this.ctfass = ctfass;
	}

	public String getNTFASSNum() {
		return nTFASSNum;
	}

	public void setNTFASSNum(String num) {
		nTFASSNum = num;
	}

	public String getCVWMainPart() {
		return cVWMainPart;
	}

	public void setCVWMainPart(String mainPart) {
		cVWMainPart = mainPart;
	}

	public String getCVWMainPartQuan() {
		return cVWMainPartQuan;
	}

	public void setCVWMainPartQuan(String mainPartQuan) {
		cVWMainPartQuan = mainPartQuan;
	}

	public String getCVWMainPartType() {
		return cVWMainPartType;
	}

	public void setCVWMainPartType(String mainPartType) {
		cVWMainPartType = mainPartType;
	}

	public String getCVWSubPart() {
		return cVWSubPart;
	}

	public void setCVWSubPart(String subPart) {
		cVWSubPart = subPart;
	}

	public String getNVWSubPartQuan() {
		return nVWSubPartQuan;
	}

	public void setNVWSubPartQuan(String subPartQuan) {
		nVWSubPartQuan = subPartQuan;
	}

	public String getNVWSubPartType() {
		return nVWSubPartType;
	}

	public void setNVWSubPartType(String subPartType) {
		nVWSubPartType = subPartType;
	}

	public String getCTFASSName() {
		return cTFASSName;
	}

	public void setCTFASSName(String name) {
		cTFASSName = name;
	}

	public String getCTFASSTypeNo() {
		return cTFASSTypeNo;
	}

	public void setCTFASSTypeNo(String typeNo) {
		cTFASSTypeNo = typeNo;
	}

	public String getCQADNo() {
		return cQADNo;
	}

	public void setCQADNo(String no) {
		cQADNo = no;
	}

	public String getCIsTempCar() {
		return cIsTempCar;
	}

	public void setCIsTempCar(String isTempCar) {
		cIsTempCar = isTempCar;
	}

	public String getCRemark() {
		return cRemark;
	}

	public void setCRemark(String remark) {
		cRemark = remark;
	}
}
