package helper.excel.inters;

import java.io.InputStream;

/**
 * Excel�ļ������ӿ��ļ�
 */
public interface IExcelParser {
	public String parseExcel(InputStream in) throws Exception;
}
