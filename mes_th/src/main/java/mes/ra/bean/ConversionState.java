package mes.ra.bean;


public class ConversionState {
	/**
	 * ���
	 */
	private int id;
	/**
	 * ԭʼ״̬
	 */
	private int fromstate;
	/**
	 * ��ת״̬
	 */
	private int tostate;
	/**
	 * ������Ϣ
	 */
	private String desc;
	
	/**
	 * @return desc
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @param desc Ҫ���õ� desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * @return fromstate
	 */
	public int getFromstate() {
		return fromstate;
	}
	/**
	 * @param fromstate Ҫ���õ� fromstate
	 */
	public void setFromstate(int fromstate) {
		this.fromstate = fromstate;
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
	 * @return tostate
	 */
	public int getTostate() {
		return tostate;
	}
	/**
	 * @param tostate Ҫ���õ� tostate
	 */
	public void setTostate(int tostate) {
		this.tostate = tostate;
	}
	
}
