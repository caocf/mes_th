package helper.excel.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import helper.excel.base.BaseExcelParser;
import helper.excel.entities.FATHBean;

/**
 * ����Excel�ļ�
 *
 * @author AjaxFan
 */
public class ExcelParserImpl extends BaseExcelParser {
	/**
	 * �ƻ�ģ��ı���������
	 *
	 * �����ʽ: ����˳��, �ƻ����, �ƻ����κ�, ������Ԫ, ������, ���Ϻ�, ������ˮ��ʼ, ������ˮ����, ����, ���, ����, ��ע
	 */
	private String[] TEMPLATE_HEADERS = { "id", "Status", "Seq", "CP5A Date", "CP5A Time", "Model", "KNR", "Color",
	        "ColorDesc", "Type", "Chassi" };

	/**
	 * ����Excel�ļ���������һ�������б�
	 *
	 * @param in
	 *            �ļ���
	 */
	public List<FATHBean> parseExcel(InputStream in) throws Exception {
		// ��������б�
		List<FATHBean> list = new ArrayList<FATHBean>();

		// ����ĵ��еĵ�һ��������
		Sheet sheet = createWorkbook(in).getSheetAt(0);

		// �ж��ļ��ĸ�ʽ
		if (!checkExcelHeader(sheet)) {
			return list;
		}

		// �Ƴ������������� - ������
		sheet.removeRow(sheet.getRow(sheet.getFirstRowNum()));

		// �����������е��ж���ȡ��ÿ�е�������Ч��Ԫ��
		for (Iterator<Row> rowite = sheet.rowIterator(); rowite.hasNext();) {
			// ����һ���м�¼����
			list.add(createPlanBeanByExcelRow(rowite.next()));
		}
		return list;
	}

	/**
	 * ͨ��Excel���ͷ���жϱ�������Ƿ����Ҫ��
	 *
	 * @param sheet
	 * @return
	 */
	private boolean checkExcelHeader(Sheet sheet) {
		// �ļ��ı�����
		Row row = sheet.getRow(sheet.getFirstRowNum());
		// ����һ�в���Ϊ��
		if (row == null) {
			return false;
		}

		// �Ͷ����ģ���ʽ���жԱȣ�ֻ�з���ģ��Ҫ����ĵ����ܱ���ȷ����
		for (int i = 0; i < TEMPLATE_HEADERS.length; i++) {
			if (!TEMPLATE_HEADERS[i].equals(getCellValue(row.getCell(i)))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * ����FATHBean����
	 *
	 * @param row
	 * @return
	 */
	private FATHBean createPlanBeanByExcelRow(Row row) {
		// �������ݶ���
		FATHBean p = new FATHBean();

		// ������ݶ���
		p.setId(getCellValue(row.getCell(0)));
		p.setStatus(getCellValue(row.getCell(1)));
		p.setSeq(getCellValue(row.getCell(2)));
		p.setCp5adate(org.apache.poi.ss.usermodel.DateUtil.getJavaDate(row.getCell(3).getNumericCellValue()));
		p.setCp5atime(org.apache.poi.ss.usermodel.DateUtil.getJavaDate(row.getCell(4).getNumericCellValue()));
		p.setModel(getCellValue(row.getCell(5)));
		p.setKnr(getCellValue(row.getCell(6)));
		p.setColor(getCellValue(row.getCell(7)));
		p.setColorDesc(getCellValue(row.getCell(8)));
		p.setType(getCellValue(row.getCell(9)));
		p.setChassi(getCellValue(row.getCell(10)));

		return p;
	}
}