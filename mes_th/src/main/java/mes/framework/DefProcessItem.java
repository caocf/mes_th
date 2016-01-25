package mes.framework;

/**
 * ������
 * 
 * @author �Ź��� 2007-6-21
 */
class DefProcessItem implements IProcessItem {
	/**
	 * �쳣����ʽ
	 */
	private ExceptionDispose exceptionDisposeType;

	/**
	 * �������
	 */
	private String serviceName = "";

	/**
	 * ����id
	 */
	private String servicdId = "";

	/**
	 * ˳��ֵ
	 */
	private int sort = 0;

	/**
	 * ���캯��
	 * 
	 * @param servicdId
	 *            ����id
	 * @param serviceName
	 *            �������
	 * @param sort
	 *            ˳��ֵ
	 * @param exceptionDisposeType
	 *            �쳣����ʽ
	 */
	DefProcessItem(String servicdId, String serviceName, int sort,
			ExceptionDispose exceptionDisposeType) {
		super();
		this.servicdId = servicdId;
		this.serviceName = serviceName;
		this.sort = sort;
		this.exceptionDisposeType = exceptionDisposeType;
	}

	/**
	 * @return the exceptionDisposeType
	 */
	public ExceptionDispose getExceptionDisposeType() {
		return exceptionDisposeType;
	}

	public void setExceptionDisposeType(ExceptionDispose exceptionDisposeType) {
		this.exceptionDisposeType = exceptionDisposeType;
	}

	/**
	 * @return the serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * @param serviceName
	 *            the serviceName to set
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/**
	 * @return the servicdId
	 */
	public String getServicdId() {
		return servicdId;
	}

	/**
	 * @param servicdId
	 *            the servicdId to set
	 */
	public void setServicdId(String servicdId) {
		this.servicdId = servicdId;
	}

	/**
	 * @return the sort
	 */
	public int getSort() {
		return sort;
	}

	/**
	 * @param sort
	 *            the sort to set
	 */
	public void setSort(int sort) {
		this.sort = sort;
	}

	
}
