package helper.excel;

import java.io.BufferedInputStream;
import java.io.InputStream;

import helper.excel.impl.DataPersistenceServcie;
import helper.excel.impl.ExcelParserImpl;
import helper.excel.inters.IDataPersistenceService;
import helper.excel.inters.IExcelParser;

/**
 * Excel ����������
 *
 * @author AjaxFan
 */
public final class ExcelHelper {
	private static IExcelParser excelParser = new ExcelParserImpl();
	private static IDataPersistenceService dataStore = new DataPersistenceServcie();

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
	public static int parse(InputStream in) throws Exception {
		return dataStore.storeData(excelParser.parseExcel(new BufferedInputStream(in)));
	}
}