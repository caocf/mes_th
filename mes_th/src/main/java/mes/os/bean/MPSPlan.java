package mes.os.bean;
import java.util.*;
/**
 * 
 * @author  л���� 2009-5-13
 *
 */
public class MPSPlan {
	/**
	  * ���
	  */
	private  int id ; 
	/**
	  * ��ʼ����
	  */
	private  Date startDate; 
	/**
	  * MPS��λ���ա��ܡ��¡�Ѯ������
	  */
	private  String mpsUnit ;  
	/**
	  * ������
	  */
	private String materielName; 
	/**
	  *�ƻ�����
	  */
	private  int planAmount; 
	/**
	  *Ԥ�ƿ��
	  */
	private int intendStorage;  
	/**
	  *�ƻ������ͣ����󡢼ƻ���Ԥ�⣩
	  */
	private String planType; 
	/**
	  *�汾
	  */
	private String version; 
	/**
	  *�ƶ���
	  */
	private String userName; 
	/**
	  *��ͬ��
	  */
	private String contractCode; 
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getMpsUnit() {
		return mpsUnit;
	}
	public void setMpsUnit(String mpsUnit) {
		this.mpsUnit = mpsUnit;
	}
	public String getMaterielName() {
		return materielName;
	}
	public void setMaterielName(String materielName) {
		this.materielName = materielName;
	}
	public int getPlanAmount() {
		return planAmount;
	}
	public void setPlanAmount(int planAmount) {
		this.planAmount = planAmount;
	}
	public int getIntendStorage() {
		return intendStorage;
	}
	public void setIntendStorage(int intendStorage) {
		this.intendStorage = intendStorage;
	}
	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}


}
