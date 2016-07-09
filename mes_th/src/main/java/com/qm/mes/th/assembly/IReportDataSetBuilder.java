package com.qm.mes.th.assembly;

import java.util.List;

import th.pz.bean.JConfigure;

public interface IReportDataSetBuilder {
	public void buildQueryExpression();
	
	/**
	 * ����ҵ�����ݼ���
	 */
	public void buildBusinessDataSet();

	/**
	 * @return �������ݼ���
	 */
	public List<JConfigure> getDataSet();
}
