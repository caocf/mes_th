package th.tg.util;

import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.Number;

/**
 * ��װ����
 * 
 * @author YuanPeng
 *
 */
public class WeldingExport {
	public static synchronized void createExcelFile(ResultSet rs,ResultSet rs_part,
			OutputStream os,String partnum,String type,Connection con) throws Exception {//�������壨���� 10�� ��ɫ��
		WritableFont blueFont = new jxl.write.WritableFont(
			      WritableFont.createFont("����"), 10, WritableFont.NO_BOLD);
		blueFont.setColour(Colour.BLUE);
		//������ʽ
		jxl.write.WritableCellFormat blueFormat = new jxl.write.WritableCellFormat(
				blueFont);
		//�������壨���� 10�� ��ɫ��
		WritableFont greenFont = new jxl.write.WritableFont(
			      WritableFont.createFont("����"), 10, WritableFont.NO_BOLD);
		blueFont.setColour(Colour.GREEN);
		//������ʽ
		jxl.write.WritableCellFormat greenFormat = new jxl.write.WritableCellFormat(
				greenFont);
		jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(os);
		//����Excel������
		WritableSheet sheet = wwb.createSheet("��װ��ѯ����", 0);
		 ResultSetMetaData metaData = rs.getMetaData();
		int cols = metaData.getColumnCount(), row = 0;
			if (true) {
				if(type.equals("1")){
					int count = 2;
					sheet.addCell(new Label(0, row, "˳���"));
					sheet.addCell(new Label(1, row, "KIN"));
					sheet.setColumnView(0, 8); // �����еĿ��
					sheet.setColumnView(1, 14); // �����еĿ��
					while(rs_part.next()){
						sheet.setColumnView(count, 20); // �����еĿ��
						sheet.addCell(new Label(count++, row, rs_part.getString("name")));
						sheet.setColumnView(count, 13); // �����еĿ��
						sheet.addCell(new Label(count++, row, rs_part.getString("name")+"����"));
					}
					sheet.addCell(new Label(cols-2, row, "��װ����ʱ��"));
					sheet.setColumnView(cols-2, 22); // �����еĿ��
				}else{
					sheet.addCell(new Label(0, row, "�����"));
					sheet.addCell(new Label(1, row, "����"));
					sheet.setColumnView(0, 18); // �����еĿ��
					sheet.setColumnView(1, 8); // �����еĿ��
				}
				row++;
			}
			rs_part.beforeFirst();
			if(type.equals("1")){
				while (rs.next()) {
					int count2 = 2;
					sheet.addCell(new Number(0, row,rs.getInt("seq")));
					sheet.addCell(new Number(1, row,rs.getInt("kin")));
					while(rs_part.next()){
						sheet.addCell(new Label(count2++, row,rs.getString(rs_part.getString("name"))));
						
						sheet.addCell(new Number(count2++, row,rs.getInt(rs_part.getString("name")+"����")));
					}
					rs_part.beforeFirst();
					sheet.addCell(new Label(cols-2, row,rs.getString("dwbegin")));
					row++;
				}
				int c =1;
				rs_part.afterLast();
				cols = sheet.getColumns();//����
				int rows = sheet.getRows();//����
			for(c=1;c<=Integer.parseInt(partnum);c++){
				int col = c*2+1;
				String curname = "";
				int length = 0;
				int n= 0;
				String value = "";
				for(int i=1;i<rows;i++){
					 
					Cell cell = sheet.getCell(col-1,i);
					value = cell.getContents();
					//��һ�β���
					if(curname == ""){
						curname = value;
					}
					//����ȵ�ʱ��
					if(!curname.equals(value)){
						int length_total = length;
						n++;
						curname = value;
						//ѭ������ÿһ���Ѿ��洢�ĵ�Ԫ�����
						while(length>0){
							//���������������ݼ��١�
							if(length_total == length)
								for(int k=1;k<=length;k++)
									sheet.addCell(new Number(col,i-k,length));
							length--;
						}
					}
					//����������
					if(curname.equals(value)){
						length++;
						
					}
				}
				int length_total = length;
				n++;
				curname = value;
				//ѭ������ÿһ���Ѿ��洢�ĵ�Ԫ�����
				while(length>0){
					//���������������ݼ��١�
					if(length_total == length)
						for(int k=1;k<=length;k++)
							sheet.addCell(new Number(col,rows-k,length));
					length--;
				}
			}
				
				
			}else{
				while (rs.next()) {
					sheet.addCell(new Label(0, row,rs.getString("max_no")));
					sheet.addCell(new Number(1, row,rs.getInt("sum_num")));
					row++;
				}
			}
		if(con!=null){
			con=null;
		}
		wwb.write();
		wwb.close();
	}

}