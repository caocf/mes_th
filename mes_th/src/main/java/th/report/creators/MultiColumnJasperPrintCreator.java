package th.report.creators;

import java.sql.Connection;
import java.util.List;

import net.sf.jasperreports.engine.JasperPrint;
import th.report.entities.RequestParam;

/**
 * ���б����ӡ
 * 
 * @author Ajaxfan
 */
public class MultiColumnJasperPrintCreator extends SimpleCreator {

	public List<JasperPrint> createJasperPrints(Connection conn, RequestParam requestParam) {
		return null;
	}
}
