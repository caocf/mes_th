package mes.ra.util;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import jxl.Workbook;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.WritableSheet;
import mes.ra.bean.State;
import mes.ra.dao.DAO_ProduceUnit;
import mes.ra.dao.DAO_ProduceUnitForOracle;
import mes.ra.factory.StateFactory;
public class InstructionExport {
	
	public static synchronized void createExcelFile(ResultSet rs,
			OutputStream os,Connection con) throws Exception {
	//Method 2����WritableWorkbookֱ��д�뵽�����
	    jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(os);
	    //����Excel������
	    WritableSheet sheet = wwb.createSheet("����ָ��", 0);
	    Statement stat1=null;
		try{
	    HashMap<Integer, String> m = new   HashMap<Integer, String>(); 
	    HashMap<Integer, String> m1 = new   HashMap<Integer, String>();
		DAO_ProduceUnit dao_produceunit =new DAO_ProduceUnitForOracle() ;
		// �õ����е�������Ԫ����Ϣ
	    String producesql=dao_produceunit.getAllProduceUnit();
	     stat1=con.createStatement();
	    ResultSet Unit = stat1.executeQuery(producesql);
	    while(Unit.next()){
	    m.put(Unit.getInt("int_id"),(String)Unit.getString("str_name"));
	    }
	    StateFactory state=new StateFactory();
		List<State> liststate = new ArrayList<State>();
		liststate=state.getAllState(con);
		Iterator<State> iterstate=liststate.iterator();
		while(iterstate.hasNext()){
		         State stat=new State();
		         stat=(State)iterstate.next();
		         m1.put(stat.getId(), stat.getStateName());
		    }
	    ResultSetMetaData metaData = rs.getMetaData();
		int cols=metaData.getColumnCount(), row=0;
            cols=15;
		if(true){
			sheet.addCell(new Label(0, row, "������Ԫ"));
			sheet.addCell(new Label(3, row, "��������"));
			sheet.addCell(new Label(13, row, "�汾��"));
			sheet.addCell(new Label(4, row, "ָ��˳���"));
			sheet.addCell(new Label(11, row, "�ƻ�����"));
			sheet.addCell(new Label(12, row, "�ƻ�˳���"));
			sheet.addCell(new Label(5, row, "��Ʒ����ʶ"));
			sheet.addCell(new Label(6, row, "��Ʒ����"));
			sheet.addCell(new Label(7, row, "��Ʒ��ʶ"));
			sheet.addCell(new Label(2, row, "���"));
			sheet.addCell(new Label(1, row, "����"));
			sheet.addCell(new Label(9, row, "Ԥ�ƿ�ʼʱ��"));
			sheet.addCell(new Label(10, row, "Ԥ�ƽ���ʱ��"));
			sheet.addCell(new Label(8, row, "ָ������"));
			sheet.addCell(new Label(14, row, "ָ��״̬"));
			row++;
		}
       boolean f=true;
		while(rs.next()){
			for(int i=1;i<=cols;i++){
				f=true;
				if(i==1){//������Ԫ
				int t=rs.getInt(i);
				String name=m.get(t);
				sheet.addCell(new Label(i-1,row,name));
				f=false;
				}
			    if(i==4){//��������
			    //	sheet.addCell(new Label(i-1,row,(new SimpleDateFormat("yyyy-MM-dd"))
			    //			.format(rs.getDate(i))));
			    	
			    	DateTime dt = new DateTime(i-1,row,new SimpleDateFormat("yyyy-MM-dd")
			    			.parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date(rs.getDate(i).getTime()+1* 24 * 60 * 60 * 1000))));
			    	sheet.addCell(dt);
			    	
			    	f=false;
			    }
                if(i==12){//�ƻ�����
			    	sheet.addCell(new Label(i - 1, row, rs.getDate(i)==null?"":(new SimpleDateFormat("yyyy-MM-dd"))
			    			.format(rs.getDate(i))));
			    	f=false;
			    } 
				if(i==15){//ָ��״̬
					int t1= rs.getInt(i);
					String name1=m1.get(t1);
				sheet.addCell(new Label(i - 1, row, name1));
				f=false;
				}
				if(f){
				sheet.addCell(new Label(i - 1, row, rs.getString(i)==null?"":rs.getString(i)));
				}
			}
			row++;
		}
		
	}catch(Exception e){
		e.getStackTrace();
	}finally{
		if (rs != null)
			rs.close();
		if(stat1!=null)
		    stat1.close();
		if(con!=null)
			con=null;
		wwb.write();
		wwb.close();
	}
	
	
}

   
}
