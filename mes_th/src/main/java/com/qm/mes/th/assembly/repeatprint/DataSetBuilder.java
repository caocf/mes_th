package com.qm.mes.th.assembly.repeatprint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.qm.mes.th.assembly.IReportDataSetBuilder;
import com.qm.mes.th.assembly.entities.ReportBaseInfo;

import th.pz.bean.JConfigure;
import th.pz.bean.PrintSet;

/**
 * ���ݼ���������
 * 
 * @author Ajaxfan
 */
class DataSetBuilder implements IReportDataSetBuilder {
    private Connection conn;
    private PrintSet printSet;
    private ReportBaseInfo reportBaseInfo;
    private List<JConfigure> list;
    private String queryExpression;

    /**
     * 
     * @param conn
     * @param printSet
     * @param reportBaseInfo
     */
    public DataSetBuilder(Connection conn, PrintSet printSet, ReportBaseInfo reportBaseInfo) {
        this.conn = conn;
        this.printSet = printSet;
        this.reportBaseInfo = reportBaseInfo;

        list = new ArrayList<JConfigure>();
    }

    /**
     * ��ѯ���ʽ
     */
    public void buildQueryExpression() {
        queryExpression = "SELECT * FROM print_data WHERE iCarNo = ? AND cRemark = ? AND iPrintGroupId = ? ORDER BY inum";
    }

    /**
     * �������ݼ���
     */
    public void buildBusinessDataSet() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(queryExpression);
            stmt.setInt(1, reportBaseInfo.getCarno());
            stmt.setString(2, reportBaseInfo.getDabegin());
            stmt.setInt(3, printSet.getId());

            rs = stmt.executeQuery();

            // ������ݼ���

            while (rs.next()) {
                JConfigure obj = new JConfigure();

                obj.setIndex(rs.getInt("inum"));// ����
                obj.setCQADNo(rs.getString("cTFAss"));// ��������
                obj.setCSEQNo_A(rs.getString("cSEQNo"));// ��װ˳���
                obj.setCVinCode(rs.getString("cVinCode"));// VIN��
                obj.setCCarNo(rs.getString("cKinNo"));// kin��
                obj.setCCarType(printSet.getCCode());// ����
                obj.setChassisNumber(rs.getString("cPageNo"));
                obj.setTfassId(rs.getInt("ITFASSNameId"));
                obj.setJs(rs.getInt("iCarNo"));
                obj.setPrintSetId(printSet.getId());

                list.add(obj);
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

    /*
     * ���ݼ���
     */
    public List<JConfigure> getDataSet() {
        return list;
    }
}
