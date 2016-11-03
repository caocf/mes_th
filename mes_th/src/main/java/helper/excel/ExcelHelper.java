package helper.excel;

import java.io.BufferedInputStream;
import java.io.InputStream;

import helper.excel.inters.IExcelParser;
import helper.excel.process.ExcelParserImpl;

/**
 * Excel ����������
 *
 * @author AjaxFan
 */
public final class ExcelHelper {
	private static IExcelParser excelParser = new ExcelParserImpl();

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
		return excelParser.parseExcel(new BufferedInputStream(in));
	}
}