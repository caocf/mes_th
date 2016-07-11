package th.report.facades;

import java.sql.Connection;

import th.pz.bean.PrintSet;
import th.report.api.IReportBaseInfoBuilder;
import th.report.api.IReportBaseInfoFacade;
import th.report.builders.ReportBaseInfoBuilder;
import th.report.entities.ReportBaseInfo;
import th.report.entities.RequestParam;

/**
 * ������Ϣ������
 * 
 * @author Ajaxfan
 */
public class ReportBaseInfoFacade implements IReportBaseInfoFacade {
	/**
	 * ��ȡ���������Ϣ
	 */
	public ReportBaseInfo obtainBaseInfo(Connection conn, RequestParam requestParam, PrintSet printSet) {
		IReportBaseInfoBuilder builder = new ReportBaseInfoBuilder(conn, printSet, requestParam);
		builder.buildDAInfo();
		builder.buildMaxCarNo();
		builder.buildTFassId();
		builder.buildMaxPageNo();
		builder.buildChassisNumber();
		builder.buildVinAndCarTypePair();

		return builder.getReportBaseInfo();
	}
}
