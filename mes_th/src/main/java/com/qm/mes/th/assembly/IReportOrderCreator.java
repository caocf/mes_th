package com.qm.mes.th.assembly;

import java.sql.Connection;
import java.util.List;

import com.qm.mes.th.assembly.entities.RequestParam;

/**
 * ���������ɹ���
 * @author Ajaxfan
 */
public interface IReportOrderCreator {
    public List<IReportOrder> createReportOrders(Connection conn, RequestParam requestParam);
}
