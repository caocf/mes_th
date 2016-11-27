package helper.excel.process;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import helper.excel.base.BaseExcelParser;
import helper.excel.entities.SpecialKinBean;
import helper.excel.inters.IDataPersistenceService;
import helper.excel.persistence.SpecialKinPersistence;

/**
 * ����Excel�ļ�
 *
 * @author AjaxFan
 */
public class SpecialKinExcelParserImpl extends BaseExcelParser {
	/**
	 * �ƻ�ģ��ı���������
	 *
	 * �����ʽ: Kin�š��Ƿ��ء���ע
	 */
	private String[] TEMPLATE_HEADERS = { "KIN\u53f7", "\u662f\u5426\u76d1\u63a7", "\u5907\u6ce8"};

	/**
	 * ���ݴ���ӳ���
	 */
	private static Map<String, IDataPersistenceService<SpecialKinBean>> stores;

	/**
	 * ��ʼ�����ݴ���ӳ���
	 */
	static {
		if (stores == null) {
			stores = new HashMap<String, IDataPersistenceService<SpecialKinBean>>();

			// ��ҵ����Ҫ�������账���ĸ�sheet��ÿ��sheet���Լ��Ĵ������
			stores.put("spec_kincode", new SpecialKinPersistence());
		}
	}

	/**
	 * ����Excel�ļ���������һ�������б�
	 *
	 * @param in
	 *            �ļ���
	 */
	public String parseExcel(InputStream in) throws Exception {
		Workbook workbook = createWorkbook(in);
		List<String> messages = new ArrayList<String>();

		// �������ݼ���
		for (String key : stores.keySet()) {
			Sheet sheet = workbook.getSheet(key);

			// �������ʽ
			if (sheet != null && checkExcelHeader(sheet)) {
				List<SpecialKinBean> list = new ArrayList<SpecialKinBean>();

				// �Ƴ������������� - ������
				sheet.removeRow(sheet.getRow(sheet.getFirstRowNum()));

				// �����������е��ж���ȡ��ÿ�е�������Ч��Ԫ��
				for (Iterator<Row> rowite = sheet.rowIterator(); rowite.hasNext();) {
					list.add(createPlanBeanByExcelRow(rowite.next()));
				}
				// ��¼��������ݽ��
				messages.add(key + ": " + stores.get(key).storeData(list));
			}
		}
		return java.util.Arrays.toString(messages.toArray());
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
	private SpecialKinBean createPlanBeanByExcelRow(Row row) {
		// �������ݶ���
		SpecialKinBean p = new SpecialKinBean();

		int i = 0;

		// ������ݶ���
		p.setKincode(getCellValue(row.getCell(i++)));
		p.setEnabled(getCellValue(row.getCell(i++)));
		p.setRemark(getCellValue(row.getCell(i++)));

		return p;
	}
}