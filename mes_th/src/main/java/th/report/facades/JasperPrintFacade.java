package th.report.facades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.Conn_MES;
import net.sf.jasperreports.engine.JasperPrint;
import th.report.api.IJasperPrintFacade;
import th.report.creators.MultiColumnJasperPrintCreator;
import th.report.creators.MultiPageJasperPrintCreator;
import th.report.creators.SingleJasperPrintCreator;
import th.report.entities.RequestParam;

/**
 * ������ӡ����
 * 
 * @author Ajaxfan
 */
public class JasperPrintFacade implements IJasperPrintFacade {
    /**
     * ��������
     */
    public List<JasperPrint> createReports(RequestParam requestParam) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<JasperPrint> list = new ArrayList<JasperPrint>();

        try {
            conn = new Conn_MES().getConn();
            stmt = conn.prepareStatement("SELECT cCode FROM printSet WHERE iPrintGroupId = ?");
            stmt.setString(1, requestParam.getGroupId());
            rs = stmt.executeQuery();

            if (rs.next()) {// ����Ҫ��ӡ�����°��������д�ӡ��Ŀ
                list.addAll(createJasperPrintByType(conn, rs.getString("cCode"), requestParam));
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

    /**
     * �����������ɱ�����
     * 
     * @param conn
     * @param code
     * @param requestParam
     * @return
     */
    private List<JasperPrint> createJasperPrintByType(Connection conn, String code, RequestParam requestParam) {
        if ("0".equals(code)) {
            return new SingleJasperPrintCreator().createJasperPrints(conn, requestParam);
        } else if ("1".equals(code) || "2".equals(code)) {
            return new MultiPageJasperPrintCreator().createJasperPrints(conn, requestParam);
        }
        return new MultiColumnJasperPrintCreator().createJasperPrints(conn, requestParam);
    }
}
