package mes.os.bean;
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
	  * �汾������
	  */
	private String user;
	/**
	  * �汾��
	  */
	private String versionCode;
	/**
	 * 
	 * @return
	 */
	private String description;
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
