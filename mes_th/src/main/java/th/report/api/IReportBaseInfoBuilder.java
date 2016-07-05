package th.report.api;

import th.report.entities.ReportBaseInfo;

/**
 * ���������Ϣ����
 * 
 * @author Ajaxfan
 */
public interface IReportBaseInfoBuilder {
	/**
	 * ��װ��Ϣ
	 */
	public void buildDAInfo();

	/**
	 * �����Ӻ�
	 */
	public void buildMaxCarNo();

	/**
	 * ��������
	 */
	public void buildTFassId();

	/**
	 * ���Ӻ�
	 */
	public void buildMaxPageNo();

	/**
	 * ���̺�
	 */
	public void buildChassisNumber();

	/**
	 * VIN�복�͵Ķ��չ�ϵ
	 */
	public void buildVinAndCarTypePair();

	/**
	 * @return ���������Ϣ
	 */
	public ReportBaseInfo getReportBaseInfo();
}
