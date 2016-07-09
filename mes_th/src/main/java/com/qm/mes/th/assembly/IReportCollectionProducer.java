package com.qm.mes.th.assembly;

import java.util.List;

import net.sf.jasperreports.engine.JasperPrint;

/**
 * ��ӡ����Ĵ�������
 * 
 * @author Ajaxfan
 */
public interface IReportCollectionProducer {
    /**
     * ������ӡ������
     * 
     * @param orderList
     * @return
     */
    public List<JasperPrint> product(List<IReportOrder> orderList);
}
