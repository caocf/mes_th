package th.report.creators;

import java.sql.Connection;
import java.util.List;

import net.sf.jasperreports.engine.JasperPrint;
import th.report.entities.RequestParam;

/**
 * ��ҳ��ӡ����
 * 
 * @author Ajaxfan
 */
public class MultiPageJasperPrintCreator  extends SimpleCreator {

	public List<JasperPrint> createJasperPrints(Connection conn, RequestParam requestParam) {
		return null;
	}
}
