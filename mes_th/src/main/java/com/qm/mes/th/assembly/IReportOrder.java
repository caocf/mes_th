package com.qm.mes.th.assembly;

import java.util.List;

import com.qm.mes.th.assembly.entities.ReportBaseInfo;
import com.qm.mes.th.assembly.entities.RequestParam;

import th.pz.bean.JConfigure;
import th.pz.bean.PrintSet;

/**
 * ��Ϊ�ӿ�Э��ʹ�ã�û�о����ʵ��
 * 
 * @author Ajaxfan
 */
public interface IReportOrder {
    public PrintSet getPrintSet();

    public RequestParam getRequestParam();

    public ReportBaseInfo getReportBaseInfo();

    public List<JConfigure> getDatas();
}
