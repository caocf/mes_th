package th.tg.bean;

/**
 * �ܳ���
 * 
 * @author YuanPeng
 *
 */
public class Part {

	/**
	 * ���
	 */
	private int id;
	/**
	 * ����
	 */
	private String name;
	/**
	 * ��
	 */
	private String code;
	/**
	 * ����
	 */
	private int num;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
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
}
