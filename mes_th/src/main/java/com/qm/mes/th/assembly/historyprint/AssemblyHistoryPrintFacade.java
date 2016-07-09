package com.qm.mes.th.assembly.historyprint;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.qm.mes.th.assembly.IAssemblyPrintFacade;
import com.qm.mes.th.assembly.IReportOrder;
import com.qm.mes.th.assembly.entities.RequestParam;
import com.qm.mes.th.assembly.report.JasperPrintCollectionCreator;

import common.Conn_MES;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * �¹汨��
 * 
 * @author Ajaxfan
 */
public class AssemblyHistoryPrintFacade implements IAssemblyPrintFacade {

    /**
     * ��ӡ����
     * 
     * @param conn
     * @param requestParam
     */
    @Override
    public List<JasperPrint> assemblyPrint(RequestParam requestParam) {
        List<JasperPrint> list = new ArrayList<JasperPrint>();
        Connection conn = null;

        try {
            // �������ݿ����Ӷ���
            conn = new Conn_MES().getConn();
            // ��������ģʽ
            conn.setAutoCommit(false);

            // ���ɶ������󣨶����洢��Ҫ���ɱ����������������Ϣ��
            List<IReportOrder> orderList = new ReportOrderCreator().createReportOrders(conn, requestParam);

            // ���յı�����󼯺�
            if (orderList.size() > 0) {
                list.addAll(new JasperPrintCollectionCreator()
                        .getJasperPrintCollection(orderList.get(0).getPrintSet().getCCode(), orderList));
            }
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    conn = null;
                }
            }
        }
        return list;
    }
}
