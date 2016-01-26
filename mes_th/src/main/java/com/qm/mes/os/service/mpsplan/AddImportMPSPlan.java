package com.qm.mes.os.service.mpsplan;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
/**
 * ���ܣ��������ƻ�
 * @author л���� 2009-5-13
 *
 */
public class AddImportMPSPlan  extends AdapterService  {
	
	/**
	 * log  ��־  
	 */
	private final Log log = LogFactory.getLog(AddImportMPSPlan.class);
	/**
	 * con <font color=red> ���ݿ�����</font>
	 */
	private Connection con = null;
	/**
	 * factory <font color=red>���ƻ�����</font>
	 */
	private MPSPlanFactory factory= new MPSPlanFactory();
	/**
	 * user �û���id
	 */
    private String user=null;
     /**
      *  in ������1
      */
	private InputStream in=null;
	/**
	 *  in2 ������2
	 */
	private InputStream in2=null;
	/**
	 * stmt   �����ݿⷢ��sql���
	 */
	private Statement stmt=null;
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
	public boolean checkParameter(IMessage message, String processid) {
		      con = (Connection) message.getOtherParameter("con");
		      user=message.getUserParameterValue("user");
	          in=(InputStream)message.getOtherParameter("in");
	          in2=(InputStream)message.getOtherParameter("in2");
	    try {
	    	  //����������
	          jxl.Workbook rwb = Workbook.getWorkbook(in);
	          //��ȡ��һ��Sheet��
	    	  Sheet  sheet = rwb.getSheet(0);
	    	  //Ϊ��ֻ��ȡexcel�еĵڶ�������
	    	  int num=1;
	    	  //�ӵڶ��п�ʼ�������ݵ��� excel������0��ʼ����һ�зŵ�������
	    	  int row=1;
			  //��ȡSheet��������������������
			  int columns=sheet.getColumns();
			  //����ȡSheet��������������������
			  int rownum=sheet.getRows();
			   //��excel�еڶ������ݡ�
			  List<String> list_excel = new ArrayList<String>();
			   //�ж������ֶ��Ƿ�Ϊ�պ������Ƿ�ƥ��
			   //regex Ϊ��������������ʽ
			  String  regex="0*[1-9]{1}[0-9]*";
			  while(row<rownum){
				  for(int i=0;i<columns;i++){
			           Cell cel=sheet.getCell(i,row);
			           String strc= cel.getContents().trim();
			           if(num==1){
			           list_excel.add(strc);
				        }
			           
			           switch(i){
			               case 0://�ƻ�����
			        	       if(cel.getType()!=CellType.DATE){
			        	    	   addException("��"+(row+1)+"��\"�ƻ�����\"��ʽ����", message, processid);
			        	    	   return gobackfalse();
				            	 }
			                     break;
			               case 1: //mps��λ   
			            	   if(strc.equals("")||strc==null){
			            		   addException( "��"+(row+1)+"��\"MPS��λ\"Ϊ��", message, processid);
			            		   return gobackfalse();
					             } 
			            	     break;
			               case 2: //������
			            	   if(strc.equals("")||strc==null){
			            		   addException("��"+(row+1)+"��\"������\"Ϊ��", message, processid);
			            		   return gobackfalse();
					              }
			            	      break;
			               case 3: //�ƻ�����
			            	   if(!strc.matches(regex)){
			            		   addException("��"+(row+1)+"��\"�ƻ�����\"ӦΪ������", message, processid);
			            		   return gobackfalse();
				            	  }
			            	   break;
			               case 4://Ԥ�ƿ��
			            	   if(!strc.matches(regex)){
			            		   addException("��"+(row+1)+"��\"Ԥ�ƿ��\"ӦΪ����", message, processid);
			            		   return gobackfalse();
				            	  }
			            	   break;
			               case 5: //�ƻ�������
			            	   if(strc.equals("")||strc==null){
			            		   addException("��"+(row+1)+"��\"�ƻ���������\"Ϊ��", message, processid);
			            		   return gobackfalse();
					              }
			            	   break;
			               case 6: //�汾
			            	   if(strc.equals("")||strc==null){
			            		   addException("��"+(row+1)+"��\"�汾\"Ϊ��", message, processid);
			            		   return gobackfalse();
					              }
			            	   break;
			               case 7: //��ͬ��
			            	   if(strc.equals("")||strc==null){
			            		   addException("��"+(row+1)+"��\"��ͬ��\"Ϊ��", message, processid);
			            		   return gobackfalse();
					              }
			            	   break;
			                 }
					     }
					   num++;
					   row++;
				   }// ƥ�����
			   
			   //���յ�һ�е����������Ľ��жԱȣ����ƻ������Ƿ����Ҫ��    
			   row=2;
			   while(row<rownum){
				   for(int i=0;i<columns;i++){
			             Cell cel=sheet.getCell(i,row);
			             String strc= cel.getContents().trim();
			             if(i==0&&(!list_excel.get(0).trim().equals(strc.trim()))){
			            	 addException("��2����"+(row+1)+"�мƻ����ڲ����", message, processid);
			            	 return gobackfalse();
			             }
			         }  
				   row++;
			   } 
			   //�滻��ͬ�ļƻ����ڵ����ƻ�
			   String [] date=new String [3];
			   date =list_excel.get(0).split("/");
	           String startDate= date[2]+"-"+date[1]+"-"+date[0];
	           log.debug("�ƻ�����:"+startDate);
	           //ɾ�����ƻ�ͨ���ƻ�����
	           factory.deleteMPSPlanbystartDate(startDate, con);
	           in.close();
	           list_excel.clear() ;   
		} catch (Exception sqle) {
			sqle.getStackTrace();
			 return gobackfalse();
		}
		return true;
	}
	
	
	
	

	public ExecuteResult doAdapterService(IMessage message, String processid)
	throws SQLException, Exception {
try {
	try {
		      //����Workbook����, ֻ��Workbook���� ֱ�Ӵӱ����ļ�����Workbook ������������Workbook
              jxl.Workbook rwb = Workbook.getWorkbook(in2);
    	      Sheet  sheet = rwb.getSheet(0);   //��ȡ��һ��Sheet��
    	      int row=1;
		      int columns=sheet.getColumns(); //��ȡSheet��������������������
		      int rownum=sheet.getRows();  //����ȡSheet��������������������
			  List<String> list_excel = new ArrayList<String>();  // ���excel��һ�е�����
			  int  userId = Integer.parseInt(user);
	   	      IDAO_UserManager dao_UserManager = DAOFactory_UserManager.getInstance(DataBaseType.getDataBaseType(con));
	   	      String sql = dao_UserManager.getSQL_SelectUserById(userId);
	   	      stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	   	      ResultSet rs = stmt.executeQuery(sql);
              log.debug("ͨ��ID��ѯ�û�: " + dao_UserManager.getSQL_SelectUserById(userId));
	   	      if(rs.next())
	   	        	user = rs.getString("CUSRNAME");
	   	      else
	   	        	user = null;
		     
		      while(row<rownum){
	        	     MPSPlan mpsplan=new MPSPlan();
	                //��ȡ��һ�е�����
		             for(int i=0;i<columns;i++){
	                 Cell cel=sheet.getCell(i,row);
	                 String strc= cel.getContents().trim();
	                 list_excel.add(strc);
	                  }
		             String [] date=new String [3];
		             date =list_excel.get(0).split("/");
	                 String startDate= date[2]+"-"+date[1]+"-"+date[0];
                     mpsplan.setStartDate((new SimpleDateFormat("yyyy-MM-dd")).parse(startDate));
                     mpsplan.setMpsUnit(list_excel.get(1));
                     mpsplan.setMaterielName(list_excel.get(2));
                     mpsplan.setPlanAmount(Integer.parseInt(list_excel.get(3)));
                     mpsplan.setIntendStorage(Integer.parseInt(list_excel.get(4)));
                     mpsplan.setPlanType(list_excel.get(5));
                     mpsplan.setVersion(list_excel.get(6));
                     mpsplan.setUserName(user);
                     mpsplan.setContractCode(list_excel.get(7));
                     factory.saveMPSPlan(mpsplan, con);
	                 row++;
	                 list_excel.clear();
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
          con = null;
          in=null;
	      in2=null;
          user=null;
          if(stmt!=null)
    	   stmt.close();
    }
}
	    
