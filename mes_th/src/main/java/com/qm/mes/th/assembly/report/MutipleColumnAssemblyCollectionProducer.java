package com.qm.mes.th.assembly.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qm.mes.th.assembly.IReportCollectionProducer;
import com.qm.mes.th.assembly.IReportOrder;
import com.qm.mes.th.assembly.helper.JasperTemplateLoader;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * �����ʽ�ı���
 * 
 * @author Ajaxfan
 */
class MutipleColumnAssemblyCollectionProducer implements IReportCollectionProducer {

    /**
     * ���ӵĶ�ҳ������Ҫ�����ݼ��ϵĲ��
     * 
     * @param orderList
     * @return
     */
    @Override
    public List<JasperPrint> product(List<IReportOrder> orderList) {
        List<JasperPrint> list = new ArrayList<JasperPrint>();
        Map<String, Object> parameters = new HashMap<String, Object>();

        try {
            JasperReport jasperReport = null;
            for (int i = 0, index = 1; i < orderList.size(); i++, index++) {
                IReportOrder order = orderList.get(i);
                
                // ������ÿһ�е�����
                parameters.put(order.getPrintSet().getCCode(), order.getDatas());
                parameters.put("mc" + index, order.getPrintSet().getCCarTypeDesc());
                parameters.put("id" + index, order.getPrintSet().getId());
                parameters.put("tm" + index, order.getReportBaseInfo().getChassisNumber());

                // ����ͨ������
                if (jasperReport == null) {
                    parameters.put("js", String.valueOf(order.getReportBaseInfo().getCarno()));
                    parameters.put("zrq", order.getRequestParam().getRequestDate());
                    parameters.put("ch", order.getReportBaseInfo().getChassisNumber());
                    parameters.put("SUBREPORT_DIR", JasperTemplateLoader.BASE_PATH);

                    jasperReport = (JasperReport) JRLoader
                            .loadObject(JasperTemplateLoader.load(order.getPrintSet().getCPrintMD()));
                }
            }
            list.add(JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
