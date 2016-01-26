package com.qm.mes.os.util;

import java.io.OutputStream;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import java.text.SimpleDateFormat;


import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;


public class MPSPlanExport {
	public static synchronized void createExcelFile(ResultSet rs,
			OutputStream os) throws Exception {
		//Method 2����WritableWorkbookֱ��д�뵽�����
	jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(os);
	//����Excel������
	WritableSheet sheet = wwb.createSheet("����ҵ�ƻ�", 0);
	
	    ResultSetMetaData metaData = rs.getMetaData();
		int cols = metaData.getColumnCount(), row = 0;
            cols=10;
		if (true) {
			
		   sheet.addCell(new Label(0, row, "���ƻ����"));
			sheet.addCell(new Label(1, row, "�ƻ�����"));
			sheet.addCell(new Label(2, row, "MPS��λ"));
			sheet.addCell(new Label(3, row, "������"));
			sheet.addCell(new Label(4, row, "�ƻ�����"));
			sheet.addCell(new Label(5, row, "Ԥ�ƿ��"));
			sheet.addCell(new Label(6, row, "�ƻ�������"));
			sheet.addCell(new Label(7, row, "�汾"));
			sheet.addCell(new Label(8, row, "�ƶ���"));
			sheet.addCell(new Label(9, row, "��ͬ��"));
	
			/**for (int i = 1; i <= cols; i++) {
				sheet.addCell(new Label(i - 1, row, metaData.getColumnName(i)));
			}
			
			*/
			row++;
		}
       boolean f=true;
		while (rs.next()) {
			for (int i = 1; i <= cols; i++) {
				f=true;
			
			    if(i==2){
			    	
			    	sheet.addCell(new Label(i - 1, row, (new SimpleDateFormat("yyyy-MM-dd"))
			    			.format(rs.getDate(i))));
			    	f=false;
			    }
             
				if(f){
				sheet.addCell(new Label(i - 1, row, rs.getString(i)));
				
				}
			}
			row++;
		}
		
	   
	if (rs != null)
		rs.close();
	wwb.write();
	wwb.close();
}

}
