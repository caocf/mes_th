package mes.pm.bean;

/**
 * Bomʵ��Bean
 * 
 * @author YuanPeng
 *
 */
public class Bom implements java.io.Serializable {
	/**
	 * ���֣����л��汾
	 */
	private static final long serialVersionUID = 7052467200620035657L;
	/**
	 * Bom���
	 */
	private int id;
	/**
	 * �����ʾ
	 */
	private String component;
	/**
	 * �ϼ���ű�ʾ
	 */
	private int parentid;
	/**
	 * ����
	 */
	private String discription;
	/**
	 * ����
	 */
	private String name;
	/**
	 * ������
	 */
	private int code;
	/**
	 * ����
	 */
	private int count;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public int getParentid() {
		return parentid;
	}
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
