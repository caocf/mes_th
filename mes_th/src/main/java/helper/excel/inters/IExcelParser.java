package helper.excel.inters;

import java.io.InputStream;
import java.util.List;

import helper.excel.entities.FATHBean;

/**
 * Excel�ļ������ӿ��ļ�
 */
public interface IExcelParser {
	public List<FATHBean> parseExcel(InputStream in) throws Exception;
}
