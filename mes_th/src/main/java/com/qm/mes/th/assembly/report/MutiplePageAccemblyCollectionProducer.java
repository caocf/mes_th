package com.qm.mes.th.assembly.report;

import java.util.ArrayList;
import java.util.Collections;
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
import th.pz.bean.JConfigure;

/**
 * �����򵥱���
 * 
 * @author Ajaxfan
 */
class MutiplePageAccemblyCollectionProducer implements IReportCollectionProducer {
    /**
     * ��ӡ�������������
     * 
     * @param orderList
     * @return
     */
    @Override
    public List<JasperPrint> product(List<IReportOrder> orderList) {
        List<JasperPrint> list = new ArrayList<JasperPrint>();

        try {
            for (IReportOrder order : orderList) {
                list.add(createMainJasperPrint(order));
                list.addAll(createTraceJasperPrints(order));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public JasperPrint createMainJasperPrint(IReportOrder order) throws Exception {
        // �������
        Map<String, Object> parameters = new HashMap<String, Object>();

        // ����datasource;
        parameters.put("js", String.valueOf(order.getReportBaseInfo().getCarno()));
        parameters.put("zrq", order.getRequestParam().getRequestDate());
        parameters.put("ch", order.getReportBaseInfo().getCarno());
        parameters.put("tm", order.getReportBaseInfo().getChassisNumber());
        parameters.put("mc", order.getPrintSet().getCTFASSName());
        parameters.put("id", order.getPrintSet().getId());
        parameters.put("SUBREPORT_DIR", JasperTemplateLoader.BASE_PATH);

        // ���ݲ��
        parameters.put("dataSource", getSubList(order.getDatas(), 1, 3));
        parameters.put("dataSource1", getSubList(order.getDatas(), 2, 3));
        parameters.put("dataSource2", getSubList(order.getDatas(), 3, 3));

        return createJasperPrints(order.getPrintSet().getCPrintMD(), parameters);
    }

    /**
     * ׷�ݱ�
     * 
     * @param reportBaseInfo
     * @param printSet
     * @param requestParam
     * @param dataset
     * @return
     * @throws Exception
     */
    private List<JasperPrint> createTraceJasperPrints(IReportOrder order) throws Exception {
        // Ҫ��ӡ�ı�����
        List<JasperPrint> list = new ArrayList<JasperPrint>();

        // ֻ�������ض������������ܴ�ӡ׷�ݵ�
        if ("1".equals(order.getPrintSet().getCCode())) {
            // �������
            Map<String, Object> parameters = new HashMap<String, Object>();

            parameters.put("js", String.valueOf(order.getReportBaseInfo().getCarno()));
            parameters.put("zrq", order.getRequestParam().getRequestDate());
            parameters.put("mc", order.getPrintSet().getCTFASSName());
            parameters.put("SUBREPORT_DIR", JasperTemplateLoader.BASE_PATH);

            for (int i = 0, n = 1; i < 2; i++) {// ׷�ݵ�ÿ�����һ�����ŵ���
                for (int j = 1; j <= 2; j++) {// ÿ�����Ӵ�ӡ��������
                    parameters.put("dataSource" + j, getSubList(order.getDatas(), n++, 4));
                }
                // ��Ϊ׷�ݵ���һ�������������ֿ����ŵ��Ӵ�ӡ��������Ҫ�����ݲ�֣��������ɴ�ӡ����
                JasperPrint print = createJasperPrints("new_qnfxpzs.jasper", parameters);
                // ׷�ݵ������ظ���ӡ
                print.setProperty("repeat", "false");

                list.add(print);
            }
        }
        return list;
    }

    /**
     * ��ȡ�����ݼ���
     * 
     * @param configures
     * @param percent
     *            �ڼ���
     * @param all
     *            �ܷ���
     * @return
     */
    private List<JConfigure> getSubList(List<JConfigure> configures, int percent, int all) {
        int size = configures.size();
        int every = size / (all == 0 ? 1 : all);

        if (size >= every * percent) {
            return configures.subList(percent * every - every, percent * every);
        }
        return Collections.emptyList();
    }

    /**
     * @param conn
     * @param printSet
     * @param reportBaseInfo
     * @param dataset
     * @param requestParam
     * @return
     */
    private JasperPrint createJasperPrints(String printMd, Map<String, Object> parameters) throws Exception {
        return JasperFillManager.fillReport((JasperReport) JRLoader.loadObject(JasperTemplateLoader.load(printMd)),
                parameters, new JREmptyDataSource());
    }
}
