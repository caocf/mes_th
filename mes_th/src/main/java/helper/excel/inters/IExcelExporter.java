package helper.excel.inters;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Excel �ļ������ӿ�
 */
public interface IExcelExporter {
	/**
	 * �����ļ�
	 *
	 * @param out
	 *            ����ļ���
	 */
	public void export(OutputStream out) throws IOException;
}
