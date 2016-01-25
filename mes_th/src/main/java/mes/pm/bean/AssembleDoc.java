package mes.pm.bean;

import java.util.Date;
import java.util.List;
/**
 * װ��ָʾ��
 * 
 * @author YuanPeng
 *
 */
public class AssembleDoc {
	/**
	 * װ��ָʾ�����
	 */
	private int id;
	/**
	 * ����
	 */
	private String name; 
	/**
	 * �������ͱ�ʾ
	 */
	private String materiel; 
	/**
	 * װ���Ӽ�����
	 */
	private List<AssDocItem> items; 
	/**
	 * ����
	 */
	private String description; 
	/**
	 * ����ʱ��
	 */
	private Date createDate;
	/**
	 * ����޸�ʱ��
	 */
	private Date upDate;
	/**
	 * �����û�ID
	 */
	private int CreateUID;
	/**
	 * �����û���
	 */
	private String createUName;
	/**����޸��û�ID
	 * 
	 */
	private int UpdateUID;//
	/**
	 * ����޸��û���
	 */
	private String updateUName;//
	/**
	 * BOM���
	 */
	private int bomId;//
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMateriel() {
		return materiel;
	}
	public void setMateriel(String materiel) {
		this.materiel = materiel;
	}
	public List<AssDocItem> getItems() {
		return items;
	}
	public void setItems(List<AssDocItem> items) {
		this.items = items;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpDate() {
		return upDate;
	}
	public void setUpDate(Date upDate) {
		this.upDate = upDate;
	}
	public int getCreateUID() {
		return CreateUID;
	}
	public void setCreateUID(int createUID) {
		CreateUID = createUID;
	}
	public int getUpdateUID() {
		return UpdateUID;
	}
	public void setUpdateUID(int updateUID) {
		UpdateUID = updateUID;
	}
	public int getBomId() {
		return bomId;
	}
	public void setBomId(int bomId) {
		this.bomId = bomId;
	}
	public String getCreateUName() {
		return createUName;
	}
	public void setCreateUName(String createUName) {
		this.createUName = createUName;
	}
	public String getUpdateUName() {
		return updateUName;
	}
	public void setUpdateUName(String updateUName) {
		this.updateUName = updateUName;
	}

}
