package mes.ra.bean;
import java.util.Date;
public class Version {
	/**
	 * ���
	 */
	private int id;
	/**
	 * ����ʱ��
	 */
	private Date versionDatime;
	/**
	 * �汾�����û�
	 */
	private String user;
	/**
	 * ������Ԫ
	 */
	private int produnitid;
	/**
	 *�汾��
	 */
	private String versionCode;
	/**
	 * ɾ����ʶ
	 */
	private int Int_delete;
	/**
	 * ��ע
	 */
	private String description;
	
	public int getInt_delete() {
		return Int_delete;
	}
	public void setInt_delete(int int_delete) {
		Int_delete = int_delete;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getVersionDatime() {
		return versionDatime;
	}
	public void setVersionDatime(Date versionDatime) {
		this.versionDatime = versionDatime;
	}
	public String getUser() {
		return user;
	}
	public int getProdunitid() {
		return produnitid;
	}
	public void setProdunitid(int produnitid) {
		this.produnitid = produnitid;
	}
	public void setUser(String user) {
		this.user = user;
	}

	public String getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
