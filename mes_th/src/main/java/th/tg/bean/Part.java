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

	private String pageno;
	private String traceone;
	private String tracetwo;
	private String proddate;

	public String getPageno() {
		return pageno;
	}

	public void setPageno(String pageno) {
		this.pageno = pageno;
	}

	public String getTraceone() {
		return traceone;
	}

	public void setTraceone(String traceone) {
		this.traceone = traceone;
	}

	public String getTracetwo() {
		return tracetwo;
	}

	public void setTracetwo(String tracetwo) {
		this.tracetwo = tracetwo;
	}

	public String getProddate() {
		return proddate;
	}

	public void setProddate(String proddate) {
		this.proddate = proddate;
	}

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
