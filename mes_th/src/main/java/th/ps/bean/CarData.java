package th.ps.bean;

/**
 * ��������ά��
 * 
 * @author Gaohf
 * @date 2010-4-19
 */
public class CarData {
	/** ���� */
	private Integer id;

	/** ��װ˳��� */
	private String cseqno;

	/** ��װ˳��� */
	private String cseqno_a;

	/** VIN�� */
	private String cvincode;

	/** KIN�� */
	private String ccarno;

	/** �������� */
	private String ccartype;

	/** ��װ����ʱ�� */
	private String dwbegin;

	/** ��װ����ʱ�� */
	private String dabegin;

	/** CP6����ʱ�� */
	private String dcp6begin;

	/** ��װ���ߴ��� */
	private Integer wuptime;

	/** ��ע */
	private String cremark;

	/** ��Чʱ�� */
	private String dtodate;

	/** ��װ���ߴ��� */
	private Integer auptime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCseqno() {
		return cseqno;
	}

	public void setCseqno(String cseqno) {
		this.cseqno = cseqno;
	}

	public String getCseqno_a() {
		return cseqno_a;
	}

	public void setCseqno_a(String cseqno_a) {
		this.cseqno_a = cseqno_a;
	}

	public String getCvincode() {
		return cvincode;
	}

	public void setCvincode(String cvincode) {
		this.cvincode = cvincode;
	}

	public String getCcarno() {
		return ccarno;
	}

	public void setCcarno(String ccarno) {
		this.ccarno = ccarno;
	}

	public String getCcartype() {
		return ccartype;
	}

	public void setCcartype(String ccartype) {
		this.ccartype = ccartype;
	}

	public String getDwbegin() {
		return dwbegin;
	}

	public void setDwbegin(String dwbegin) {
		this.dwbegin = dwbegin;
	}

	public String getDabegin() {
		return dabegin;
	}

	public void setDabegin(String dabegin) {
		this.dabegin = dabegin;
	}

	public String getDcp6begin() {
		return dcp6begin;
	}

	public void setDcp6begin(String dcp6begin) {
		this.dcp6begin = dcp6begin;
	}

	public Integer getWuptime() {
		return wuptime;
	}

	public void setWuptime(Integer wuptime) {
		this.wuptime = wuptime;
	}

	public Integer getAuptime() {
		return auptime;
	}

	public void setAuptime(Integer auptime) {
		this.auptime = auptime;
	}

	public String getCremark() {
		return cremark;
	}

	public void setCremark(String cremark) {
		this.cremark = cremark;
	}

	public String getDtodate() {
		return dtodate;
	}

	public void setDtodate(String dtodate) {
		this.dtodate = dtodate;
	}
}
