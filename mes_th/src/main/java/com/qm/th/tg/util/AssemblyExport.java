package com.qm.th.tg.util;

import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.Number;

/**
 * ��װ����
 * 
 * @author YuanPeng
 *
 */
public class AssemblyExport {
	public static synchronized void createExcelFile(ResultSet rs,
			OutputStream os,String type,Connection con) throws Exception {
		jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(os);
		//����Excel������
		WritableSheet sheet = null;
		if(type.equals("1")){
			sheet = wwb.createSheet("��װ��ѯ����", 0);
		}else{
			sheet = wwb.createSheet("��װͳ�Ƶ���", 0);
		}
		 ResultSetMetaData metaData = rs.getMetaData();
		int cols = metaData.getColumnCount(), row = 0;
			if (true) {
				if(type.equals("1")){
		            cols=6;
					sheet.addCell(new Label(0, row, "˳���"));
					sheet.addCell(new Label(1, row, "VIN"));
					sheet.addCell(new Label(2, row, "������"));
					sheet.addCell(new Label(3, row, "��װ����ʱ��"));
					sheet.addCell(new Label(4, row, "��װ����ʱ��"));
					sheet.addCell(new Label(5, row, "CP6����ʱ��"));
					sheet.setColumnView(0, 8); // �����еĿ��
					sheet.setColumnView(1, 20); // �����еĿ��
					sheet.setColumnView(2, 13); // �����еĿ��
					sheet.setColumnView(3, 23); // �����еĿ��
					sheet.setColumnView(4, 23); // �����еĿ��
					sheet.setColumnView(5, 23); // �����еĿ��
				}else{
		            cols=2;
					sheet.addCell(new Label(0, row, "�����"));
					sheet.addCell(new Label(1, row, "����"));
					sheet.setColumnView(0, 20); // �����еĿ��
					sheet.setColumnView(1, 8); // �����еĿ��
				}
					row++;
			}
			while (rs.next()) {
				for (int i=0;i<cols;i++){
					if(type.equals("1")){
						switch(i){
						case 0://˳���
		    				sheet.addCell(new Number(i, row,rs.getInt("cSEQNo_A")));
		    				break;
						case 1://VIN
							sheet.addCell(new Label(i,row,rs.getString("cVinCode")));
							break;
						case 2://������
							sheet.addCell(new Number(i,row,rs.getInt("cCarNo")));
							break;
						case 3://��װ����ʱ��
							sheet.addCell(new Label(i,row,rs.getString("dWBegin")));
							break;
						case 4://��װ����ʱ��
							sheet.addCell(new Label(i,row,rs.getString("dABegin")));
							break;
						case 5://CP6����ʱ��
							sheet.addCell(new Label(i,row,rs.getString("dCp6Begin")));
							break;
						}
					}else{
						switch(i){
						case 0://˳���
							sheet.addCell(new Label(i, row,rs.getString("seq")));
		    				break;
						case 1://����
							sheet.addCell(new Number(i,row,rs.getInt("num")));
							break;
						}
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
