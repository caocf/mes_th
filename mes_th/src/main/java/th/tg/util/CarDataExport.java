package th.tg.util;

import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;

public class CarDataExport {
	public static synchronized void createExcelFile(ResultSet rs,
			OutputStream os,String type,Connection con) throws Exception {
		jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(os);
		//����Excel������
		WritableSheet sheet = null;
			sheet = wwb.createSheet("QAD�����ܳ���Ϣͳ��", 0);
		 ResultSetMetaData metaData = rs.getMetaData();
		int cols = metaData.getColumnCount(), row = 0;
			if (true) {
		            cols=3;
					sheet.addCell(new Label(0, row, "QAD�ܳɺ�"));
					sheet.addCell(new Label(1, row, "����"));
					sheet.addCell(new Label(2, row, "����"));
					sheet.setColumnView(0, 25); // �����еĿ��
					sheet.setColumnView(1, 8); // �����еĿ��
					sheet.setColumnView(2, 8); // �����еĿ��
					row++;
			}
			while (rs.next()) {
				for (int i=0;i<cols;i++){
					switch(i){
					case 0://QAD�ܳɺ�
						sheet.addCell(new Label(i, row,rs.getString("cTfass")));
	    				break;
					case 1://����
						sheet.addCell(new Number(i,row,rs.getInt("nNum")));
						break;
					case 2://����
						sheet.addCell(new Label(i,row,rs.getString("ccartype")));
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
