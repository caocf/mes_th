package com.qm.mes.pm.bean;

import java.util.Date;
/**
 * �쳣�����쳣��¼��Ϣ
 * @author Xujia
 *
 */
public class ExceptionRecord {
	/**
	 *  ���
	 */
	private int id; 
	/**
	 * �ϱ��û�
	 */
	private int userId; 
	/**
	 * �û�����
	 */
	private String userName;
	/**
	 * ������Ԫ
	 */
	private int produceUnitId; 
	/**
	 * ������Ԫ��
	 */
	private String prodUnitName;
	/**
	 * �ر��û�
	 */
	private int closeuser;
	/**
	 * �ϱ�ʱ��
	 */
	private Date start; 
	/**
	 * String���ϱ�ʱ��
	 */
	private String str_start;
	/**
	 *  �ر�ʱ��
	 */
	private Date close;
	/**
	 * �ر�ʱ��
	 */
	private String str_end;
	/**
	 * �쳣������
	 */
	private String description;  
	/**
	 *  �쳣�ָ�������
	 */
	private String rediscription; 
	/**
	 *  �쳣����(Ԥ��/��)
	 */
	private int exceptionTypeId;
	/**
	 *  �쳣��������
	 */
	private String excepTypeName;
	/**
	 *  �쳣ԭ��
	 */
	private int exceptionCauseId; 
	/**
	 *  �쳣ԭ������
	 */
	private String excepCauseName;
	/**
	 *   ״̬��ʶ
	 */
	private int state;         
	/**
	 *   �豸��
	 */
	private int deviceid;        
	/**
	 *   �豸���ͺ�
	 */
	private int devicetypeid;    
	/**
	 *  �رա��ϱ��쳣ʱ�s��
	 */
	private long timelang;		
	
	
	/**
	 * @return close
	 */
	public Date getClose() {
		return close;
	}
	/**
	 * @param close Ҫ���õ� close
	 */
	public void setClose(Date close) {
		this.close = close;
	}
	/**
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description Ҫ���õ� description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return deviceid
	 */
	public int getDeviceid() {
		return deviceid;
	}
	/**
	 * @param deviceid Ҫ���õ� deviceid
	 */
	public void setDeviceid(int deviceid) {
		this.deviceid = deviceid;
	}
	/**
	 * @return devicetypeid
	 */
	public int getDevicetypeid() {
		return devicetypeid;
	}
	/**
	 * @param devicetypeid Ҫ���õ� devicetypeid
	 */
	public void setDevicetypeid(int devicetypeid) {
		this.devicetypeid = devicetypeid;
	}
	/**
	 * @return exceptionCauseId
	 */
	public int getExceptionCauseId() {
		return exceptionCauseId;
	}
	/**
	 * @param exceptionCauseId Ҫ���õ� exceptionCauseId
	 */
	public void setExceptionCauseId(int exceptionCauseId) {
		this.exceptionCauseId = exceptionCauseId;
	}
	/**
	 * @return exceptionTypeId
	 */
	public int getExceptionTypeId() {
		return exceptionTypeId;
	}
	/**
	 * @param exceptionTypeId Ҫ���õ� exceptionTypeId
	 */
	public void setExceptionTypeId(int exceptionTypeId) {
		this.exceptionTypeId = exceptionTypeId;
	}
	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id Ҫ���õ� id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return produceUnitId
	 */
	public int getProduceUnitId() {
		return produceUnitId;
	}
	/**
	 * @param produceUnitId Ҫ���õ� produceUnitId
	 */
	public void setProduceUnitId(int produceUnitId) {
		this.produceUnitId = produceUnitId;
	}
	/**
	 * @return rediscription
	 */
	public String getRediscription() {
		return rediscription;
	}
	/**
	 * @param rediscription Ҫ���õ� rediscription
	 */
	public void setRediscription(String rediscription) {
		this.rediscription = rediscription;
	}
	/**
	 * @return start
	 */
	public Date getStart() {
		return start;
	}
	/**
	 * @param start Ҫ���õ� start
	 */
	public void setStart(Date start) {
		this.start = start;
	}
	/**
	 * @return state
	 */
	public int getState() {
		return state;
	}
	/**
	 * @param state Ҫ���õ� state
	 */
	public void setState(int state) {
		this.state = state;
	}
	
	/**
	 * @return closeuser
	 */
	public int getCloseuser() {
		return closeuser;
	}
	/**
	 * @param closeuser Ҫ���õ� closeuser
	 */
	public void setCloseuser(int closeuser) {
		this.closeuser = closeuser;
	}
	/**
	 * @return userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId Ҫ���õ� userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getProdUnitName() {
		return prodUnitName;
	}
	public void setProdUnitName(String prodUnitName) {
		this.prodUnitName = prodUnitName;
	}
	public String getExcepTypeName() {
		return excepTypeName;
	}
	public void setExcepTypeName(String excepTypeName) {
		this.excepTypeName = excepTypeName;
	}
	public String getExcepCauseName() {
		return excepCauseName;
	}
	public void setExcepCauseName(String excepCauseName) {
		this.excepCauseName = excepCauseName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStr_start() {
		return str_start;
	}
	public void setStr_start(String str_start) {
		this.str_start = str_start;
	}
	public String getStr_end() {
		return str_end;
	}
	public void setStr_end(String str_end) {
		this.str_end = str_end;
	}
	public long getTimelang() {
		return timelang;
	}
	public void setTimelang(long timelang) {
		this.timelang = timelang;
	}
	
	

}
