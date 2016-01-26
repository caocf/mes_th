package com.qm.th.tg.util;

import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;

public class ErrExport {
	public static synchronized void createExcelFile(ResultSet rs,
			OutputStream os,String type,Connection con) throws Exception {
		jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(os);
		//����Excel������
		WritableSheet sheet = null;
			sheet = wwb.createSheet("δƥ��ɢ����Ϣͳ��", 0);
		 ResultSetMetaData metaData = rs.getMetaData();
		int cols = metaData.getColumnCount(), row = 0;
			if (true) {
		        cols=3;
				sheet.addCell(new Label(0, row, "δƥ��ɢ��"));
				sheet.addCell(new Label(1, row, "ɢ������"));
				sheet.addCell(new Label(2, row, "����"));
				sheet.setColumnView(0, 25); // �����еĿ��
				sheet.setColumnView(1, 25); // �����еĿ��
				sheet.setColumnView(2, 8); // �����еĿ��
				row++;
			}
			while (rs.next()) {
				for (int i=0;i<cols;i++){
					switch(i){
					case 0://����
						sheet.addCell(new Label(i, row,rs.getString("cpartno")));
		   				break;
					case 1://δƥ��ɢ��
						sheet.addCell(new Label(i,row,rs.getString("cpartname")));
						break;
					case 2://ɢ������
						sheet.addCell(new Number(i,row,rs.getInt("nNum")));
						break;
					}
			  }
				row++;
			}
		
		if(con!=null){
			con=null;
		}
		wwb.write();
		wwb.close();
	}
}
