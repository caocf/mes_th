package helper.excel.base;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import helper.excel.inters.IExcelParser;

/**
 * ExcelParser�ĳ������
 *
 * @author Administrator
 */
public abstract class BaseExcelParser implements IExcelParser {
    /** Excel 2003ͷǩ����Ϣ */
    private static final long _signature = 0xE11AB1A1E011CFD0L;

    /** Userful Offset */
    private static final int _signature_offset = 0;

    /**
     * ��õ�Ԫ���ֵ
     *
     * @param cell
     *            ��Ԫ�����
     * @return
     */
    protected final String getCellValue(Cell cell) {
        if (cell != null) {
            switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                return String.valueOf((long)cell.getNumericCellValue());
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_BLANK:
            case Cell.CELL_TYPE_BOOLEAN:
            case Cell.CELL_TYPE_ERROR:
            case Cell.CELL_TYPE_FORMULA:
            }
        }
        return null;
    }

    /**
     * ����Excel�ļ�����
     *
     * @param in
     * @return
     */
    protected final Workbook createWorkbook(InputStream in) throws IOException {
        // �ж��ļ���ͷ��Ϣ��У���ļ��Ƿ�ΪExcel 2003��ʽ
        if (isExcel2003HeaderBlock(in)) {
            return new HSSFWorkbook(in, false);
        }
        return new XSSFWorkbook(in);
    }

    /**
     * ����ļ�ͷ������ǩ����Ϣ
     *
     * @param in �ļ�������
     * @return
     * @throws IOException
     */
    private boolean isExcel2003HeaderBlock(InputStream in) throws IOException {
        // У���ļ�ǩ��
        return LittleEndian.getLong(readFirst512(in), _signature_offset) == _signature;
    }

    /**
     * �ж��ļ�ͷ��Ϣ������ļ��Ƿ�Ϊ2003��ʽ
     *
     * @param stream
     * @return
     * @throws IOException
     */
    private byte[] readFirst512(InputStream stream) throws IOException {
        // ������ǰ��512�ֽڵ�ͷ��Ϣ
        byte[] data = new byte[512];
        // ������markλ�ã��Ա��������ع�
        stream.mark(0);
        // ��ȡͷ��Ϣ
        IOUtils.readFully(stream, data);
        // �ع�IO��
        stream.reset();

        return data;
    }
}