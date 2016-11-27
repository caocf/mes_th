package helper.excel;

import java.io.BufferedInputStream;
import java.io.InputStream;

import helper.excel.inters.IExcelParser;
import helper.excel.process.IEFITExcelParserImpl;
import helper.excel.process.SpecialKinExcelParserImpl;

/**
 * Excel ����������
 *
 * @author AjaxFan
 */
public final class ExcelHelper {
	private static IExcelParser iefitExcelParser = new IEFITExcelParserImpl();
	private static IExcelParser specKincodeExcelParser = new SpecialKinExcelParserImpl();

	/**
	 * �������ǲ��ɱ�ʵ������
	 */
	private ExcelHelper() {
	}

	/**
	 * ���ݽ������־û�
	 *
	 * @param in
	 * @return
	 */
	public static String parse(InputStream in) throws Exception {
		return iefitExcelParser.parseExcel(new BufferedInputStream(in));
	}
	
	/**
	 * ���ݽ������־û�
	 *
	 * @param in
	 * @return
	 */
	public static String parse(InputStream in, String type) throws Exception {
		return specKincodeExcelParser.parseExcel(new BufferedInputStream(in));
	}
}