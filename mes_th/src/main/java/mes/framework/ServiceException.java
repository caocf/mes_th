package mes.framework;

import java.util.Date;

/**
 * �����쳣�࣬���ڴ洢�������й����г��ֵ��쳣������쳣������Ϣ��
 * 
 * @author �Ź��� 2007-6-21
 */
public final class ServiceException extends java.lang.Throwable {

	private static final long serialVersionUID = -1688501331104688624L;

	/**
	 * �쳣��������Ϣ
	 */
	private String descr = null;

	/**
	 * �쳣����
	 */
	private ServiceExceptionType type = ServiceExceptionType.UNKNOWN;

	/**
	 * �쳣����
	 */
	private Exception source = null;

	/**
	 * ��Ӧ����
	 */
	private String es = null;

	/**
	 * ��Ӧ����
	 */
	private String sp = null;

	/**
	 * ����ʱ��
	 */
	private Date date = null;

	public ServiceException(ServiceExceptionType set, String descr, String es,
			String sp, Date d, Exception s) {

		source = s;
		this.descr = descr;
		this.type = set;
		this.es = es;
		this.sp = sp;
		this.date = d;
	}

	/**
	 * ���ش�������
	 * 
	 * @return ���ش�������
	 */
	public ServiceExceptionType getServiceExceptionType() {
		return type;
	}

	/**
	 * ���ش������
	 * 
	 * @return ���ش������
	 */
	public Exception getSource() {
		return source;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	public String getEs() {
		return es;
	}

	public String getSp() {
		return sp;
	}

	/**
	 * @return the type
	 */
	public ServiceExceptionType getType() {
		return type;
	}

	/**
	 * @return the descr
	 */
	public String getDescr() {
		return descr == null ? "" : descr;
	}

	/**
	 * @param descr
	 *            the descr to set
	 */
	public void setDescr(String descr) {
		this.descr = descr;
	}

}
