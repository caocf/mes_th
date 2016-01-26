package com.qm.mes.os.service.plan;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import java.sql.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.AdapterService;
import com.qm.mes.framework.DataBaseType;
import com.qm.mes.framework.ExecuteResult;
import com.qm.mes.framework.IMessage;
import com.qm.mes.framework.ServiceException;
import com.qm.mes.framework.ServiceExceptionType;
import com.qm.mes.framework.dao.DAOFactory_UserManager;
import com.qm.mes.framework.dao.IDAO_UserManager;
import com.qm.mes.os.bean.*;
import com.qm.mes.os.factory.*;
import com.qm.mes.ra.bean.ProduceUnit;
import com.qm.mes.ra.factory.ProduceUnitFactory;
import com.qm.mes.tg.bean.MaterielRule;
import com.qm.mes.tg.factory.MaterielRuleFactory;


/**���ܣ�������ҵ�ƻ�
 * 
 * @author л����
 * 
 */

public class AddImportPlan extends AdapterService {

	/**
	 * log ��־
	 */
	private final Log log = LogFactory.getLog(AddImportPlan.class);
	
	/**
	 * con ���ݿ�����
	 */
	private Connection con = null;

	/**
	 * stmt �����ݿⷢ��sql���
	 */
	private Statement stmt = null;
	
	/**
	 * factory // �ƻ�����
	 */
	private PlanFactory factory = new PlanFactory();
	
	/**
	 * unitfactory // ������Ԫ����
	 */
	private ProduceUnitFactory unitfactory = new ProduceUnitFactory();
	
	/**
	 * versionfactory // �ƻ��汾����
	 */
	private VersionFactory versionfactory = new VersionFactory();
	
	/**
	 * workschedlefactory // ����ʱ�̱���
	 */
	private WorkSchedleFactory workschedlefactory = new WorkSchedleFactory();
	
	/**
	 * sum // ����ҳ�� ѡ�еĸ������ύ��һ����
	 */
	private String sum = null;
	
	/**
	 * code // ���İ汾��ͨ��������Ԫ�������ںͰ�εõ�
	 */
	private String code = null;
	
	/**
	 * replace // �滻replace 
	 */
	private String replace = null;
	
	/**
	 * automakeplanorder // �Զ�����˳���
	 */
	private String automakeplanorder = null;
	
	/**
	 * automakeversioncode // �Զ����ɰ汾��
	 */
	private String automakeversioncode = null;
	
	/**
	 * in // ������1
	 */
	private InputStream in = null;
	
	/**
	 * in2 // ������2
	 */
	private InputStream in2 = null;
	
	/**
	 * user �û�id
	 */
	private String user = null;
	
	/**
	 * produceid // ������Ԫ��idֵ
	 */
	private int produceid = 0;
	/**
	 *  �������� Ϊ�ַ�����ʽ��2009-06-24 
	 */
	private String produceDate=null;
	/**
	 *workOrder ���  
	 */
	private String workOrder=null;
	
	/**
	 * maxnum // ��׷���Զ�����˳���ʱ�� ��ʶ����˳���
	 */
	private int maxnum = 0;

	
	 /**
	 * sqlplanall �ƻ��������мƻ���Ԥ����
	 */
	private  PreparedStatement  sqlplannow=null;
	
	
	
    /**
     * @param message //�����,�滻������˳��ţ����ɰ汾��ֵ.
     */

    public void apartParameter(IMessage message){
    	switch (Integer.parseInt(sum)) {
		 case 1:
			  replace="0";
			  automakeplanorder="0";
			  automakeversioncode="0";
			  break;
		 case 2:
			  replace=message.getUserParameterValue("num"+1);
			  if(Integer.parseInt(replace)==1){
				 replace="1";
				 automakeplanorder="0";
				 automakeversioncode="0";
				 break;
			  } 
			  if(Integer.parseInt(replace)==2){
				replace="0";
				automakeplanorder="1";
				automakeversioncode="0";
				break;
			  }
			  if(Integer.parseInt(replace)==3){
                replace="0";
				automakeplanorder="0";
				automakeversioncode="1";
				break;
			  }
		 case 3:
			  replace=message.getUserParameterValue("num"+1);
			  automakeplanorder=message.getUserParameterValue("num"+2);
			  if(Integer.parseInt(replace)==1){
				if(Integer.parseInt(automakeplanorder)==2){
					replace="1";
					automakeplanorder="1";
					automakeversioncode="0";

				  }
				else
				  {
					replace="1";
					automakeplanorder="0";
					automakeversioncode ="1";
				  }
			  }
			  else{
				replace="0";
			    automakeplanorder="1";
				automakeversioncode="1";
			  }
			  break;
		 case 4:
			 replace="1";
			 automakeplanorder="1";
			 automakeversioncode="1";
			 break;
		}
    }
/**
 * 
 * @param msn �쳣��Ϣ
 * @param message ��Ϣ����
 * @param processid ���̺�
 */
 public void addException(String msn,IMessage message,String processid){
	 message.addServiceException(new ServiceException(
				ServiceExceptionType.PARAMETERLOST, 
						msn, this
						.getId(), processid,
				new java.util.Date(), null));
 }
 /**
  * 
  * @return false
  */
 public boolean gobackfalse(){
	 return false;
 }
    /**�ж�excel�е������Ƿ�Ϊ�գ��Ƿ���ϸ�ʽ
     * @param colum excel�еڼ���
     * @param strc ��ǰ��Ԫ���е�����
     * @param row ��
     * @param regex������ʽ
     * @param list_excel_order���excel�е�˳���
     * @param cel��Ԫ�����
     * @param message��Ϣ����
     * @param processid���̺�
     * @return  
     */
    public boolean checkmessage(int colum,String strc,int row,String regex, List<String>  list_excel_order,Cell cel,IMessage message,String processid){
		  try{
		switch(colum){
					       case 0:// ������Ԫ��
						        if(strc.equals("")||strc==null){
						        addException("��"+(row+1)+"��\"������Ԫ��\"Ϊ��", message, processid);
						        return gobackfalse();
						         }
						        if(!unitfactory.checkProduceUnitByName(strc, con)){
							    addException("��"+(row+1) + "��\"������Ԫ\"������", message, processid);
							    return gobackfalse();
						        }
						        break;
					       case 1:// ����
						        if(strc.equals("")||strc==null){
						        addException("��"+(row+1)+"��\"����\"Ϊ��", message, processid);
						        return gobackfalse();
						        }
						        break;
					       case 2:// ���
						       if(strc.equals("")||strc==null){
						    	addException("��"+(row+1)+"��\"���\"Ϊ��", message, processid);
						    	return gobackfalse();
						        }
						        break;
					       case 3:// ��������
						       if(cel.getType()!=CellType.DATE){
						        addException("��"+(row+1)+"��\"��������\"��ʽ����", message, processid);
						        return gobackfalse();
						        }
						        break;
					       case 4:// �ƻ�˳���
						        if(Integer.parseInt(automakeplanorder)==0){
							       if(!strc.matches(regex)){
							    	  addException("��"+(row+1)+"��\"�ƻ�˳���\"ӦΪ������", message, processid);
							    	  return gobackfalse();
							    }
							   else{
								list_excel_order.add(strc);
							     }
						       }
						       break;
					       case 5:// ��Ʒ����ʶ
						        if(strc.equals("")||strc==null){
						        addException("��"+(row+1)+"��\"��Ʒ����ʶ\"Ϊ��", message, processid);
						        return gobackfalse(); 
						        }
						        break;
					       case 6:// ��Ʒ����
						        if(strc.equals("")||strc==null){
						        addException("��"+(row+1)+"��\"��Ʒ����\"Ϊ��", message, processid);
						        return gobackfalse();
						        }
						        break;
					       case 7:// ��Ʒ��ʶ
						        if(strc.equals("")||strc==null){
						        addException("��"+(row+1)+"��\"��Ʒ��ʶ\"Ϊ��", message, processid);
						        return gobackfalse();
						        }
						        break;
					       case 8:// ����
						        if(!strc.matches(regex)){
						        addException("��"+(row+1)+"��\"����\"ӦΪ������", message, processid);
						        return gobackfalse();
						       }
						       break;
					       case 9:// �ƻ����κ�
						        if(!strc.matches(regex)){
						        addException("��"+(row+1)+"��\"�ƻ����κ�\"ӦΪ������", message, processid);
						        return gobackfalse();
						        }
						        break;
					       case 10:// �ƻ�����
						        if(cel.getType()!=CellType.DATE) {
						        addException("��"+(row+1)+"��\"�ƻ�����\"��ʽ����", message, processid);
						        return gobackfalse();
						        }
						       break;
					    }
		  }catch(Exception e){
			  e.getStackTrace();
			  return false;
		  }
		    return true;
		
	}

    /**  �˶�������Ԫ����κ����������Ƿ���ȡ�
     * @param rownum ��
     * @param columns ��
     * @param list_excel ��ŵ�һ�е�����
     * @param sheet ��һ�ű�
     * @param message ��Ϣ����
     * @param processid���̺�
     * @return
     */
    public boolean checkproduceidworkorderproducedate(int rownum,int columns,List<String> list_excel,Sheet sheet,IMessage message,String processid){
    	int row=2;
		while(row<rownum){
			for (int i=0;i<columns;i++){
				Cell cel=sheet.getCell(i, row);
				String strc=cel.getContents().trim();
				switch(i){
				case 0: // ��������Ԫֵ
					  if(!list_excel.get(0).trim().equals(strc.trim())){
						addException("��2����"+(row+1)+"��������Ԫֵ�����", message, processid);
						return gobackfalse();
					  }
					break;
				case 2:// �а��
					  if(!list_excel.get(2).trim().equals(strc.trim())){
						 addException("��2����"+(row+1)+"�а�β����", message, processid);
						 return gobackfalse();
					   }
					  break;
				 case 3:// ����������
					  if(!list_excel.get(3).trim().equals(strc.trim())){
						 addException("��2����"+(row+1)+"���������ڲ����", message, processid);
						 return gobackfalse();
				       }
					   break;
				}
			}
			row++;
		} 
	   return true;
    }
    /** �˶�������ֵ�Ƿ����Ҫ��
     * @param rownum �еĸ���
     * @param size   �ƻ�����
     * @param sheet  ��
     * @param validate ���Ϲ���
     * @param message ��Ϣ����
     * @param processid���̺�
     * @return
     */
    public boolean checkmateriel(int rownum,int size,Sheet sheet,String validate ,IMessage message,String processid){
		   try{
		    ResultSet rs=null;
			String sqlplanstring="select * from t_os_plan where str_versioncode =(select max(str_versioncode)  from t_os_plan where to_char(dat_produceDate,'yyyy-MM-dd')=?  and int_produnitid=? and str_workOrder=? and str_versioncode!='temp') " 
					+" and str_producemarker=? ";
            sqlplannow=con.prepareStatement(sqlplanstring); 
       
			for (int j=1;j<rownum;j++){
				 Cell cel=sheet.getCell(7, j);
				 String strc1 = cel.getContents().trim();
				 if(!strc1.matches(validate)){
					addException("��"+(j+1)+"��\"��Ʒ��ʶ\"�����������Ϲ���", message, processid);
					return gobackfalse();
				   }
				for(int k =j+1;k<rownum;k++){ // ��excel�еĲ�Ʒ��ʶ���ݽ��бȽ�
					Cell ce2=sheet.getCell(7, k);
					String strc2=ce2.getContents().trim();
					if(strc1.equals(strc2)){
					addException("��"+(k)+"��\"��Ʒ��ʶ\"���"+(j)+"�����", message, processid);
					return gobackfalse();
					}
				  }
				 // ׷��ʱ�ж���ҵ�ƻ����в鿴�Ƿ�����ͬ��������  �����滻��ʱ֮ǰû���κ����ݣ�������Ԫ����κ��������ڣ�
				 if(Integer.parseInt(replace)==0){
					 sqlplannow.setString(1,produceDate);
					 sqlplannow.setInt(2,produceid);
					 sqlplannow.setString(3,workOrder);
					 sqlplannow.setString(4,strc1);
					 rs=sqlplannow.executeQuery();
					 if(rs.next()){
						addException("��"+(j+1)+"��\"��Ʒ��ʶ\"����Ҫ׷�ӵİ汾���Ѿ���ʹ����.���ʵ����", message, processid);
						return gobackfalse();
					 }
				  }
				
			}
		   }catch(Exception e){
			   e.getStackTrace();
			   return false;
		   }
           return true;
		
	}
    public boolean checkParameter(IMessage message, String processid) {
		      con=(Connection) message.getOtherParameter("con");
		      user=message.getUserParameterValue("user");
		      sum=message.getUserParameterValue("sum");
		      in=(InputStream) message.getOtherParameter("in");
		      in2=(InputStream) message.getOtherParameter("in2");
		      String debug="user:"+user+" sum:"+sum+" in:"+in+"  in2:"+in2+ "\n";
              log.info("����ƻ��Ĳ���: " + debug);
		      apartParameter(message);
		      try {
			        jxl.Workbook rwb=Workbook.getWorkbook(in); // excel �Ǵ�0��0�п�ʼ
			        Sheet sheet=rwb.getSheet(0);// ��ȡ��һ��Sheet��
			        int num=1; // ֻ��excel�еĵ�2�е����ݼ�����
			        int row=1; // �кŵ�0��Ϊ�б������Դ�1��ʼ
			        int columns=sheet.getColumns();// ��ȡSheet��������������������
			        int rownum=sheet.getRows(); // ��ȡSheet��������������������
			        List<String> list_excel=new ArrayList<String>(); // ��excel�еڶ������ݡ�
			        List<Plan> list_plan=new ArrayList<Plan>(); // ����ҵ�ƻ�����׷��ʱ�ж�������ֵ
			        List<String> list_excel_order=new ArrayList<String>();  // ��excel�еļƻ�˳���
			        List<Integer> list_plan_order=new ArrayList<Integer>(); // ����ҵ�ƻ����е�order
			        String regex="0*[1-9]{1}[0-9]*";//������ʽ
			        if(columns!=12){
						   addException("���ļ����Ǽƻ��ļ�", message, processid);
						   return gobackfalse();
					   }
			        // ��1���ж������ֶ��Ƿ�Ϊ�պ������Ƿ�ƥ��
			        while(row<rownum){
				       for(int i=0;i<columns;i++){
					      Cell cel=sheet.getCell(i, row);
					      String strc=cel.getContents().trim();
					      if(num==1){ //ֻ��excel�еĵ�2�е����ݼ�����
						     list_excel.add(strc);
					       }
					      if(!checkmessage(i, strc, row, regex,list_excel_order, cel, message, processid)){
					    	return false;
					      }
				      }
				      num++;
				      row++;
			       } // ��1�� �ж������ֶ��Ƿ�Ϊ�պ������Ƿ�ƥ�� ����
			      //(2)���Զ�����˳���ʱ�ж�excel����ҵ�ƻ�˳����Ƿ�����ȵ� list_excel_order��excel�е���ҵ�ƻ�˳���
			       if(Integer.parseInt(automakeplanorder)==0){
				         for(int i=0;i<list_excel_order.size();i++){
					       for(int j=i+1;j<list_excel_order.size();j++){
						     if(list_excel_order.get(i).equals(list_excel_order.get(j))){
						    	 addException("��"+(i+2)+"�����"+(j+2)+"��\"��ҵ�ƻ���˳���\"���", message, processid);
						    	 return gobackfalse();
						       }
					        }
				          }
			           }// (2) ����
			       //(3) �ж�������Ԫ ���� ��κ����������Ƿ���Ⱥʹ��ڡ�
			       if(!checkproduceidworkorderproducedate(rownum,columns, list_excel, sheet, message, processid)){
			    	  return false;
			       }
			       // ��ȡ������ʱ��
			       String[] date = new String[3];
			       date = list_excel.get(3).split("/");
			       produceDate = date[2]+"-"+date[1]+"-"+date[0];
			       //�����Ϣ
			       workOrder=list_excel.get(2);
			       // ��ȡ������Ԫ��id�����Ϲ���
			       ProduceUnit unit=new ProduceUnit();
			       unit=unitfactory.getProduceUnitByName(list_excel.get(0).trim(),con);
			       produceid = unit.getInt_id();
			       MaterielRuleFactory materiel = new MaterielRuleFactory();
			       MaterielRule rule=new MaterielRule();
			       rule=materiel.findMaterielRule(unit.getInt_materielRuleid(), con);
			       // ���Ϲ��� ������ʽ
			       String validate=rule.getValidate();
			       // �˶��Ƿ����������Ԫ��εĹ���ʱ�̱�
			       boolean ishasworkschedle=workschedlefactory.checkworkOrderProduce(produceid,workOrder, con);
			       if(!ishasworkschedle){
			    	   addException("û�и�������Ԫ��ι���ʱ�̱�", message, processid);
			    	   return gobackfalse();
			        }
	                // �鿴��������Ԫ,�������ڵİ�μƻ��Ƿ��ڱ༭.
			       list_plan =factory.getPlanbyProdateProidWorder(produceDate,produceid, workOrder, con);
                   if(list_plan.size()!=0&&list_plan.get(0).getVersioncode().equals("temp")){
                	   addException("��������Ԫ,�������ڵİ�����ڱ༭.", message, processid);
                	   return gobackfalse();
			        }	
			          // �ж��Ƿ������ʵ����
			       long time=0;
			         time=workschedlefactory.getworkschedleadtime(produceid,produceDate, list_excel.get(2), con);
			       if(time==0){
			    	   addException("��������Ԫ,�������ڵİ�μƻ��Ѿ�������ʵ������.", message, processid);
			    	   return gobackfalse();
			        }
			        // �������˳���
			      list_plan_order=factory.getPlanOrderbyProdateProidWorder(produceDate, produceid, list_excel.get(2), con);
			      if(list_plan_order.size()!=0){
				  maxnum=list_plan_order.get(list_plan_order.size()-1);
			      }
			      //׷�Ӳ��Զ�������ҵ�ƻ�˳����ж� list3�д�ŵ�����ҵ�ƻ����е�˳���
			      if(Integer.parseInt(replace)==0){
				    if(Integer.parseInt(automakeplanorder)==0){
					  for(int i=0;i<list_excel_order.size();i++){
						for(int j=0;j<list_plan_order.size();j++){
							if(Integer.parseInt(list_excel_order.get(i))==list_plan_order.get(j)){
								addException("��"+(i+2)+"��\"�ƻ�˳���\"��ϵͳ���Ѿ�����", message, processid);
								return gobackfalse();
						      }
						   }
					   }
				    }
			      } 
			      //�˶�������ֵ�Ƿ����Ҫ��
			    if(!checkmateriel(rownum, list_plan.size(), sheet, validate , message,processid))
			    	return false;
			    in.close(); 
			    list_excel.clear();
		} catch (Exception sqle) {

			sqle.printStackTrace();
			return false;
		}finally{
			try{
			  if(sqlplannow!=null)
				  sqlplannow.close();
			  
			}
			catch(Exception e){
				e.printStackTrace();
				return false;
			}
		}

		return true;
	}
   
	/**�����������ļƻ�����ز���
	 * @param upload ������ʶ
	 * @param str_versioncode Ϊ��ȡ�İ汾�ż�1�����ɵİ汾��
	 * @param plan �ƻ�����
	 */
	public void doonce(int upload,String str_versioncode,Plan plan){
		try{
			List<Plan> planbycode=new ArrayList<Plan>();
		if(Integer.parseInt(replace)==0){
			// ׷�Ӳ����ɰ汾 ʲô��������������Ƿ���ʱ��Ҫ��׷�Ӳ����ɰ汾���׷�����ɰ汾
			if(Integer.parseInt(automakeversioncode)==1||upload==1){
				planbycode=factory.getPlanbyversioncord(code,con);
				Iterator<Plan> iter=planbycode.iterator();
				while(iter.hasNext()){
					Plan plan1=(Plan)iter.next();
					plan1.setVersioncode(str_versioncode);
					factory.savePlan(plan1, con);
				  }
			  }
		   }
		else{// �滻�����ɰ汾,�����滻���ɰ汾��Ҫ��ԭ���İ汾ɾ������ ͬʱ���汾����ɾ����¼
			if(code!=null){
				factory.deletePlanbyversioncode(code, con);
				if(Integer.parseInt(automakeversioncode)==1||upload==1){
				versionfactory.deleteVersionbyversioncode(code, con);
				}
			}
		}
		if(upload==1||Integer.parseInt(automakeversioncode)==1){
			Version version=new Version();
			version.setVersionCode(plan.getVersioncode());
			version.setUser(user);
			versionfactory.saveVersion(version, con);
		}
		}catch(Exception e){
			e.getStackTrace();
		}
	}
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {
			try {//��ȡ�û�����
				int userId=Integer.parseInt(user);
				IDAO_UserManager dao_UserManager = DAOFactory_UserManager
						.getInstance(DataBaseType.getDataBaseType(con));
				 
				String sql=dao_UserManager.getSQL_SelectUserById(userId);
				stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(sql);
				if(rs.next())
					user=rs.getString("CUSRNAME");
				else
					user=null;
				// ����Workbook����, ֻ��Workbook���� ֱ�Ӵӱ����ļ�����Workbook ������������Workbook
				jxl.Workbook rwb=Workbook.getWorkbook(in2);
				Sheet sheet=rwb.getSheet(0);// ��ȡ��һ��Sheet��
				int row=1;//�кŵ�0��Ϊ�б������Դ�1��ʼ
				int columns=sheet.getColumns();// ��ȡSheet��������������������
				int rownum=sheet.getRows();// ��ȡSheet��������������������
				List<String> list_excel=new ArrayList<String>();// ���excel��һ�е�����
				List<Plan> listmaxplan=new ArrayList<Plan>();// ��ȡ���İ汾�żƻ���Ϣ
				String first="01";
				int excuteonetime = 1;// ��ϵͳֻȡһ�ΰ汾��
				String str_versioncode = null;// ��ȡ���İ汾���ϼ�1���ɵ��°汾��
				int upload=0;// ������ʶ
				int firstnum=1;//��doonce����ִֻ��һ��
				int num=1;//�ƻ�˳���
				while(row<rownum){
					Plan plan=new Plan();
					// ��ȡ��һ�е�����
					for (int i=0;i<columns;i++){
						Cell cel=sheet.getCell(i,row);
						String strc=cel.getContents().trim();
						list_excel.add(strc);
					}
					// ��ȡ�ƻ�����
					String[] plandate=new String[3];
					plandate=list_excel.get(10).split("/");
					String planDate=plandate[2]+"-"+plandate[1]+ "-"+plandate[0];
					// ��ϵͳֻȡһ�ΰ汾��
					if(excuteonetime==1){
                       listmaxplan=factory.getmaxPlanexcepttemp(produceDate,produceid, workOrder, con);
						if(listmaxplan.size()!=0){
							Plan plannew=new Plan();
							plannew=listmaxplan.get(0);//��ȡһ���ƻ�����
							upload=plannew.getUpload();// ��ȡ������ʶ
							code=plannew.getVersioncode();//��ȡ�汾��
							if(!code.equals("")&&code!= null){
								int leng=code.length();
								String code1=code.substring(leng-2,leng-1);// ʮλ
								String code2 = code.substring(leng-1,leng);// ��λ
								int gewei=Integer.parseInt(code2)+1;
								if(gewei<=9){
									first = code1 + String.valueOf(gewei);
								}else{
									int shiwei = Integer.parseInt(code1) + 1;
									first = String.valueOf(shiwei) + "0";
								}
							 }
						  }
						String str_name=list_excel.get(0).trim();//������Ԫ��
						String[] name=new String[3];
						name=produceDate.split("-");//��������ȥ��"-"
						String namess=name[0]+name[1]+name[2];
						str_versioncode=namess+str_name+workOrder+first;//��ȡ�汾��
						excuteonetime++;
					}
					if(upload==1){// ����ҵ�ƻ��Ѿ�����ʱ���������°汾
						plan.setVersioncode(str_versioncode);
					 }else{
						if(!first.equals("01")){//�����ɰ汾
							if(Integer.parseInt(automakeversioncode)==0){
									plan.setVersioncode(code);//���ð汾��
								}//���ɰ汾
								else {
									plan.setVersioncode(str_versioncode);//���ð汾��
								}
						}else {
							plan.setVersioncode(str_versioncode);//���ð汾��
						}
					}
					plan.setProdunitid(produceid);//����������Ԫ
					plan.setWorkTeam(list_excel.get(1));//���ð���
					plan.setWorkOrder(workOrder);//���ð��
					plan.setPlanDate((new SimpleDateFormat("yyyy-MM-dd")).parse(planDate));//���üƻ�����
					plan.setProduceDate((new SimpleDateFormat("yyyy-MM-dd")).parse(produceDate));//������������
					plan.setOrderFormId(" ");//���ö�����
					plan.setProduceType(list_excel.get(5));//���ò�Ʒ����ʶ
					plan.setProduceName(list_excel.get(6));//���ò�Ʒ����
					plan.setProduceMarker(list_excel.get(7));//���ò�Ʒ��ʶ
					plan.setAmount(Integer.parseInt(list_excel.get(8)));//��������
					plan.setPlanGroupId(Integer.parseInt(list_excel.get(9)));//���üƻ����κ�
					if(Integer.parseInt(replace)==1){//���ѡ�����滻���Զ����ɼƻ�˳���ʱ
						if(Integer.parseInt(automakeplanorder)==1){
							plan.setPlanOrder(num);//���üƻ�˳���
							num++;
						} else {
							plan.setPlanOrder(Integer.parseInt(list_excel.get(4)));//���üƻ�˳���
						}
					}
					else{	// ���ѡ����׷�ӵ��Զ����ɼƻ�˳���ʱ
						if(Integer.parseInt(automakeplanorder)==1){
							maxnum=maxnum+1;
							plan.setPlanOrder(maxnum);//���üƻ�˳���
						}else{
							plan.setPlanOrder(Integer.parseInt(list_excel.get(4)));//���üƻ�˳���
						}
					}
					plan.setUpload(0);//���÷�����ʶ
					plan.setDescription(list_excel.get(11));//����������Ϣ
					if(firstnum==1){
						 doonce(upload,str_versioncode,plan);
						firstnum++;
					}
					factory.savePlan(plan, con);//����ƻ���¼
					row++;//�м�һ
					list_excel.clear();//���excel��һ������
				 }

				in2.close();
			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), processid, new Date(), sqle));
				return ExecuteResult.fail;
			}
		} catch (Exception e) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
					processid, new java.util.Date(), e));
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
	}

	@Override
	public void relesase() throws SQLException {
		con=null;
		sum=null;
		code=null;
		replace=null;
		automakeplanorder=null;
		in=null;
		in2=null;
		automakeversioncode=null;
		user=null;
		produceid=0;
		workOrder=null;
		produceDate=null;
		maxnum=0;
		if(stmt!= null)
		  stmt.close();
	}

}
