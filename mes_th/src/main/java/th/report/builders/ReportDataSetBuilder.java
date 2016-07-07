package th.report.builders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import th.pz.bean.JConfigure;
import th.pz.bean.PrintSet;
import th.report.api.IReportDataSetBuilder;
import th.report.entities.ReportBaseInfo;

/**
 * ���ݼ���������
 * 
 * @author Ajaxfan
 */
public class ReportDataSetBuilder implements IReportDataSetBuilder {
    private Connection conn;
    private PrintSet printSet;
    private ReportBaseInfo reportBaseInfo;
    private List<JConfigure> list;
    private String queryExpression;

    /** ϵͳ��־���� */
    private Logger logger = Logger.getLogger(ReportDataSetBuilder.class);

    /**
     * 
     * @param conn
     * @param printSet
     * @param reportBaseInfo
     */
    public ReportDataSetBuilder(Connection conn, PrintSet printSet, ReportBaseInfo reportBaseInfo) {
        this.conn = conn;
        this.printSet = printSet;
        this.reportBaseInfo = reportBaseInfo;

        list = new ArrayList<JConfigure>();
    }

    /**
     * ��ѯ���ʽ
     */
    public void buildQueryExpression() {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT top ");
        sb.append(printSet.getNTFASSCount());
        sb.append(" c.cSEQNo_A, c.cVinCode, c.cCarType, cQADNo, sc.ITFASSNameId, sc.iTFASSNum, c.cCarNo, ks.ccode");
        sb.append(" FROM carData c LEFT JOIN carData_D sc");
        sb.append(" ON c.ccarno = sc.icarid AND itfassnameid = ");
        sb.append(reportBaseInfo.getTfassId());
        sb.append(" LEFT JOIN TFASSName t ON sc.itfassnameid = t.id ");
        sb.append(" LEFT JOIN kinset ks ON substring(c.ccarno,6,1) = ks.csubkin");
        sb.append(" WHERE ((dabegin = '");
        sb.append(reportBaseInfo.getDabegin());
        sb.append("' AND c.cSEQNo_A>'");
        sb.append(reportBaseInfo.getDaseqa());
        sb.append("') or (dabegin> '");
        sb.append(reportBaseInfo.getDabegin());
        sb.append("'))");

        if (printSet.getCCarType() != null && !printSet.getCCarType().equals("")) {
            String carType = "'" + printSet.getCCarType() + "'";
            carType = carType.replace(",", "','");
            sb.append(" AND substring(c.ccarno,6,1) in (" + carType + ")");
        }

        if (printSet.getCFactory() != null && !printSet.getCFactory().equals("")) {
            String factoryNo = "'" + printSet.getCFactory() + "'";
            factoryNo = factoryNo.replace(",", "','");
            sb.append(" AND (subString(c.cSEQNo_A,1,2) in(" + factoryNo + ")) ");
        }
        if (printSet.getCVinRule() != null && !"".equals(printSet.getCVinRule().trim())) {
            String cvinRule = "'" + printSet.getCVinRule() + "'";
            cvinRule = cvinRule.replace(",", "','");
            sb.append(" and (subString(c.cVinCode,7,2) in(" + cvinRule + ")) ");
        }
        sb.append(" ORDER BY c.dabegin, c.cSEQNo_A");

        logger.debug(sb.toString());

        queryExpression = sb.toString();
    }

    /**
     * �������ݼ���
     */
    public void buildBusinessDataSet() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(queryExpression);
            rs = stmt.executeQuery();

            // ������ݼ���
            for (int i = 1; list.size() < printSet.getNTFASSCount(); i++) {
                JConfigure obj = new JConfigure(i);

                if (rs.next()) {
                    obj.setCQADNo(rs.getString("cQADNo"));// ��������
                    obj.setCSEQNo_A(rs.getString("cSEQNo_A"));// ��װ˳���
                    obj.setCVinCode(rs.getString("cVinCode"));// VIN��
                    obj.setCCarNo(rs.getString("cCarNo"));// kin��
                    obj.setCCarType(rs.getString("ccode"));// ����
                    obj.setChassisNumber(reportBaseInfo.getChassisNumber());
                    obj.setTfassId(rs.getInt("ITFASSNameId"));
                    obj.setJs(reportBaseInfo.getCarno());
                    obj.setPrintSetId(printSet.getIPrintGroupId());
                }
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

    /**
     * ��������Vin��
     * 
     * @param obj
     * @return
     */
    private String getLastVin(String curVin) {
        // ���ݲ����ʱ��VIN����Ϊ��
        if (curVin == null || curVin.trim().length() == 0) {
            return null;
        }

        // ��ȡ������Ϣ
        String cartype = curVin.substring(6, 8);
        // ����ϴδ�ӡ��Vin��
        String lastVin = reportBaseInfo.getVinByCarType(cartype);
        // �������µ�VIN��
        reportBaseInfo.putVinMap2CarType(cartype, curVin);

        return lastVin;
    }

    /**
     * �ж�Vin�Ƿ�����
     * 
     * @param list2
     * 
     * @param objs
     * @return
     */
    private List<JConfigure> autoAdjust(List<JConfigure> configures) {
        for (int i = 0; i < configures.size(); i++) {// ��������Ƿ�������������֯���ݼ���
            JConfigure configure = configures.get(i);
            String lastVin = getLastVin(configure.getCVinCode());

            if (lastVin != null && lastVin.trim().length() > 11) {// ȷ������
                int lastSerial = Integer.valueOf(lastVin.substring(11));// ��һ�δ�ӡ���ݵ���ˮ��
                int curSerial = Integer.valueOf(configure.getCVinCode().substring(11));// ���δ�ӡ����ˮ��

                if (curSerial - lastSerial != 1) {// ���������������Ҫ������ǰ�����հ�
                    configures.add(configure.getIndex() - 1, new JConfigure(configure.getIndex()));
                    configure.setIndex(configure.getIndex() + 1);// �������������ǰԪ�ص������Զ���1
                    configures.remove(configures.size() - 1);
                }
            }
        }
        return configures;
    }

    /*
     * ���ݼ���
     */
    public List<JConfigure> getDataSet() {
        return autoAdjust(list);
    }
}
