package com.qm.mes.th.assembly;

import java.util.List;

import net.sf.jasperreports.engine.JasperPrint;

/**
 * �������ɹ��ߵĹ�����
 * 
 * @author Ajaxfan
 */
public interface IReportCollectionCreator {
    public List<JasperPrint> getJasperPrintCollection(String code, List<IReportOrder> orderList);
}
