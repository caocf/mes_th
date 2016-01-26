package com.qm.th.bs.bom;

import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;

/**
 * Excel Sheet
 * 
 * @author Gaohf
 * @date 2009-10-12
 */
public class ThSheet {
    private Sheet sheet = null;
    boolean found = false;

    ThSheet(String sheetName, Workbook w) {
        this.sheet = w.getSheet(sheetName);
        found = this.sheet != null;
    }

    /**
     * ȡ��һ�еĽ����
     * 
     * @param colName
     */
    public List<String> getColumnContents(String colName) throws Exception {
        Cell cell = getColumn(colName);
        
        if (cell.getType().equals(CellType.EMPTY))
            throw new Exception("�� "+  colName + " ������!");

        List<String> list = new ArrayList<String>();
        Cell[] cells = this.getColumn(cell.getColumn());

        for (int i = 0; i < cells.length; i++) {
            Cell ce = cells[i];
            list.add(ce.getContents());
        }

        return list;
    }

    /**
     * ���һ�е����ݼ�
     * 
     * @param index
     * @return
     */
    public List<String> getRowContents(int index) {

        List<String> list = new ArrayList<String>();
        Cell[] cells = this.getRow(index);

        for (Cell ce : cells) {
            list.add(ce.getContents());
        }

        return list;
    }

    /**
     * ��Ч����
     */
    public int getRows() {
        return sheet.getRows();
    }

    /**
     * ��Ч����
     */
    public int getCols() {
        return sheet.getColumns();
    }

    /**
     * ���һ����Ԫ��
     * 
     * @param colIndex
     * @param rowIndex
     * @return
     */
    public Cell getCell(int colIndex, int rowIndex) {
        return sheet.getCell(colIndex, rowIndex);
    }

    /**
     * ���һ�����е�Ԫ���������
     * 
     * @param rowIndex
     * @return
     */
    public Cell[] getRow(int rowIndex) {
        return sheet.getRow(rowIndex);
    }

    /**
     * ���һ�����е�Ԫ������
     * 
     * @param colIndex
     * @return
     */
    public Cell[] getColumn(int colIndex) {
        return sheet.getColumn(colIndex);
    }

    /**
     * ���������
     * 
     * @param colName
     * @return
     */
    public int getColumnIndex(String colName) {
        Cell cell = getColumn(colName);
        return cell == null ? -1 : cell.getColumn();
    }

    /**
     * ���ӵ��ָ���Ƶ���ͷ��Ԫ��
     * 
     * @param colName
     * @return
     */
    private Cell getColumn(String colName) {
        Cell cell = null;
        for (int i = 0; i < this.getCols(); i++) {
            cell = this.getCell(i, 0);
            if (cell.getContents().trim().equalsIgnoreCase(colName)) {
                break;
            }
        }
        return cell;
    }
}
