package helper.excel.impl;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import helper.excel.inters.IExcelExporter;

/**
 * �ļ��ĵ�������
 */
public class ExcelExporterImpl implements IExcelExporter {
    /** ģ���ļ�·�� */
    private static final String FILE_PAHT = "/helper/excel/resources/template.xls";

    /**
     * ����ģ��Excel�ļ�
     * @param out
     */
    @Override
    public synchronized void export(OutputStream out) throws IOException {
        new POIFSFileSystem(this.getClass().getResourceAsStream(FILE_PAHT)).writeFilesystem(out);
    }
}
