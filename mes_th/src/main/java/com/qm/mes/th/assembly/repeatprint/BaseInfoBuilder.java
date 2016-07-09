package com.qm.mes.th.assembly.repeatprint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.qm.mes.th.assembly.IReportBaseInfoBuilder;
import com.qm.mes.th.assembly.entities.ReportBaseInfo;
import com.qm.mes.th.assembly.entities.RequestParam;

import th.pz.bean.PrintSet;

/**
 * ���������Ϣ
 * 
 * @author Ajaxfan
 */
class BaseInfoBuilder implements IReportBaseInfoBuilder {
    private Connection conn;
    private PrintSet printSet;
    private RequestParam requestParam;
    private ReportBaseInfo reportBaseInfo;

    /**
     * ���캯��
     * 
     * @param conn
     * @param printSet
     * @param requestParam
     */
    public BaseInfoBuilder(Connection conn, PrintSet printSet, RequestParam requestParam) {
        this.conn = conn;
        this.printSet = printSet;
        this.requestParam = requestParam;

        this.reportBaseInfo = new ReportBaseInfo();
    }

    /**
     * ������װ��Ϣ
     */
    public void buildDAInfo() {
        reportBaseInfo.setDabegin(requestParam.getRequestDate());
    }

    /**
     * �����ܺ���Ϣ
     */
    public void buildMaxCarNo() {
        reportBaseInfo.setCarno(Integer.valueOf(requestParam.getJs()));
    }

    /**
     * �������ID
     */
    public void buildTFassId() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("SELECT id FROM tfassname WHERE ctfassname = ?");
            stmt.setString(1, printSet.getCTFASSName());

            rs = stmt.executeQuery();
            if (rs.next()) {
                reportBaseInfo.setTfassId(rs.getString("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    rs = null;
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    stmt = null;
                }
            }
        }
    }

    /**
     * ��������
     */
    public void buildMaxPageNo() {
        // Ҳ�ò���Ҫ����
    }

    /**
     * �������̺�
     */
    public void buildChassisNumber() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(
                    "SELECT MAX(cpageNo) FROM print_data WHERE iPrintGroupId = ? AND cremark = ? AND iCarno = ?");
            stmt.setInt(1, printSet.getId());
            stmt.setString(2, requestParam.getRequestDate());
            stmt.setString(3, requestParam.getJs());

            rs = stmt.executeQuery();
            if (rs.next()) {
                reportBaseInfo.setChassisNumber(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    rs = null;
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    stmt = null;
                }
            }
        }
    }

    /**
     * ����Vin�ͳ��͵Ķ�Ӧ��ϵ
     */
    public void buildVinAndCarTypePair() {
        // ��������
    }

    /**
     */
    public ReportBaseInfo getReportBaseInfo() {
        return reportBaseInfo;
    }
}
