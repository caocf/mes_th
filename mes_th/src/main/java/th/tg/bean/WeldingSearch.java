package th.tg.bean;

/**
 * ��װ��ѯ
 * 
 * @author YuanPeng
 *
 */
public class WeldingSearch {

	/**
	 * ˳���
	 */
	private String sequence;
	/**
	 * KIN��
	 */
	private String kin;
	/**
	 * �����
	 */
	private String part_no;
	/**
	 * ����
	 */
	private int num;
	public String getPart_no() {
		return part_no;
	}
	public void setPart_no(String part_no) {
		this.part_no = part_no;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getKin() {
		return kin;
	}
	public void setKin(String kin) {
		this.kin = kin;
	}
}
