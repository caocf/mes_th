package com.qm.mes.th.assembly.entities;

import java.util.ArrayList;
import java.util.List;

import com.qm.mes.th.assembly.IReportOrder;

import th.pz.bean.JConfigure;
import th.pz.bean.PrintSet;

/**
 * װ������������
 * 
 * @author Ajaxfan
 */
public class ReportOrder implements IReportOrder {
    /** ����Ĵ�ӡ������Ϣ */
    private PrintSet printSet;
    /** �����ӡ�ı�����Ϣ */
    private RequestParam requestParam;
    /** ����Ļ�����Ϣ */
    private ReportBaseInfo reportBaseInfo;

    /** �������ݼ��� */
    private List<JConfigure> datas = new ArrayList<JConfigure>();

    public PrintSet getPrintSet() {
        return printSet;
    }

    public void setPrintSet(PrintSet printSet) {
        this.printSet = printSet;
    }

    public RequestParam getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(RequestParam requestParam) {
        this.requestParam = requestParam;
    }

    public ReportBaseInfo getReportBaseInfo() {
        return reportBaseInfo;
    }

    public void setReportBaseInfo(ReportBaseInfo reportBaseInfo) {
        this.reportBaseInfo = reportBaseInfo;
    }

    public void add(JConfigure configure) {
        datas.add(configure);
    }

    public void addAll(List<JConfigure> configures) {
        datas.addAll(configures);
    }

    public List<JConfigure> getDatas() {
        return datas;
    }
}
