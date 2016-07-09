package com.qm.mes.th.assembly.report;

import java.util.List;

import com.qm.mes.th.assembly.IReportCollectionCreator;
import com.qm.mes.th.assembly.IReportOrder;

import net.sf.jasperreports.engine.JasperPrint;

/**
 * ��ӡ������
 * 
 * @author Ajaxfan
 */
public class JasperPrintCollectionCreator implements IReportCollectionCreator {
    /**
     * ������Ҫ�ı������ͣ�������������ָ���������߽�������
     * 
     * @param collectionType
     *            ��Ҫ�ı���������
     * @param orderList
     *            ������Ϣ
     * @return
     */
    @Override
    public List<JasperPrint> getJasperPrintCollection(String code, List<IReportOrder> orderList) {
        if ("0".equals(code)) {// �򵥱�������������ֻ����һ��ҳ�ڣ�
            return new SimpleAccemblyCollectionProducer().product(orderList);
        } else if ("1".equals(code) || "2".equals(orderList)) {// ��ҳ��������ӡ��Ϊ���õ���׷�ݵ���
            return new MutiplePageAccemblyCollectionProducer().product(orderList);
        }
        // ���б�����һ���������ж�������Ϣ��
        return new MutipleColumnAssemblyCollectionProducer().product(orderList);
    }
}
