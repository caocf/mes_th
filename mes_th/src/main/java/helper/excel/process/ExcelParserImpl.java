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
import helper.excel.entities.FATHBean;
import helper.excel.inters.IDataPersistenceService;
import helper.excel.persistence.BoraPersistenceServcie;
import helper.excel.persistence.CA3PersistenceServcie;
import helper.excel.persistence.Q5PersistenceServcie;
import helper.excel.persistence.SihuanPersistenceServcie;

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
	 * ���ݴ���ӳ���
	 */
	private static Map<String, IDataPersistenceService> stores;

	/**
	 * ��ʼ�����ݴ���ӳ���
	 */
	static {
		if (stores == null) {
			stores = new HashMap<String, IDataPersistenceService>();

			// ��ҵ����Ҫ�������账���ĸ�sheet��ÿ��sheet���Լ��Ĵ������
			stores.put("CA3", new CA3PersistenceServcie());
			stores.put("Q5", new Q5PersistenceServcie());
			stores.put("BORA", new BoraPersistenceServcie());
			stores.put("SiHuan", new SihuanPersistenceServcie());
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
				List<FATHBean> list = new ArrayList<FATHBean>();

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