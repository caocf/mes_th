package mes.ra.service.instruction;
/**���� ָ���
 * author : л����
 * 2009-3-7
 */

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import jxl.CellType;
import mes.framework.AdapterService;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import mes.os.factory.WorkSchedleFactory;
import mes.ra.bean.Instruction;
import mes.ra.bean.ProduceUnit;
import mes.ra.bean.Version;
import mes.ra.factory.*;
import mes.framework.dao.*;

import mes.framework.DataBaseType;

import mes.tg.factory.*;

import mes.tg.bean.*;


public class AddImportInstruction  extends AdapterService {
	/**
	 * con ���ݿ����� 
	 */
	private Connection con = null;
    /**
     * sum ҳ��ѡ���ı���ĸ��������ϴ���ť
     */
    private String sum=null;
    /**
	 * in ������1
	 */
	private InputStream in=null;
	/**
	 * in2������2
	 */
	private InputStream in2=null;
	/**
	 * unitfactory ������Ԫ����
	 */
	private ProduceUnitFactory unitfactory=new ProduceUnitFactory();
	/**
	 * instructionFactory //ָ���
	 */
	private  InstructionFactory instructionFactory=new InstructionFactory();
	/**
	 * historyfactory //ָ����ʷ����
	 */
	private  InstructionHistoryFactory historyfactory=new InstructionHistoryFactory();
	/**
	 * materielfactory //���Ϲ��򹤳�
	 */
	private  MaterielRuleFactory materielfactory=new  MaterielRuleFactory();
	/**
	 * workschedlefactory ����ʱ�̱���
	 */
	private WorkSchedleFactory workschedlefactory = new WorkSchedleFactory();
	/**
	 * versioncode0 //��ɾ����ͬʱָ��İ汾�Ų���
	 */
	private String versioncode0=null;
	
	/**
	 * replace_sign //�滻
	 */
	private String replace_sign=null;
	/**
	 * autoOrder_sign //�Զ�����˳��� 
	 */
	private String autoOrder_sign=null;
	/**
	 * autoVersion_sign //�Զ����ɰ汾��
	 */
	private String autoVersion_sign=null;
	/**
	 * userid  �û���id
	 */
	private String userid=null;
	/**
	 *  sqlplanall �ƻ�Ԥ�������
	 */
    PreparedStatement sqlplanall=null;
    /**
     *   sqlinstruction ָ��Ԥ�������
     */
    PreparedStatement sqlinstruction=null;
    /**
     *sqlinstructionexceptreplace ָ��Ԥ������˵�ǰ���滻��ָ�����ݡ�
     */
    PreparedStatement sqlinstructionexceptreplace=null;
	 /**
	 * produceid	//������Ԫ��idֵ 
	 */
	private  int produceid=0;
	/**
	 * produceDate �������� ��2009-06-23
	 */
	private   String produceDate=null;
	/**
	 * workOrder ���
	 */
	private   String workOrder=null;
	/**
	 * log //��־
	 */
	private final Log log = LogFactory.getLog(AddImportInstruction.class);
	 /**  �õ�����ҳ�渴ѡ��ֵ
	 * @param message
	 */
	public void apartParameter(IMessage message){
		//�ж��Ƿ��滻ָ��Ƿ�����˳��ţ��Ƿ����ɰ汾��
		switch(Integer.parseInt(sum)){
		   case 1:
			    replace_sign="0"; //�滻
	    	    autoOrder_sign="0"; //�Զ�����˳���
	    	    autoVersion_sign="0"; //�Զ����ɰ汾��
	    	    break;
		   case 2:
			    replace_sign=message.getUserParameterValue("name"+1);
		    	if(Integer.parseInt(replace_sign)==1){
		    		replace_sign="1"; //�滻
		    		autoOrder_sign="0"; //�Զ�����˳���
		    		autoVersion_sign="0"; //�Զ����ɰ汾��
		    		break;
		    	 }
		    	if(Integer.parseInt(replace_sign)==2){
		    		replace_sign="0"; //�滻
		    		autoOrder_sign="1"; //�Զ�����˳���
		    		autoVersion_sign="0"; //�Զ����ɰ汾��
		    		break;
		    	 }
		    	if(Integer.parseInt(replace_sign)==3){
		    		replace_sign="0"; //�滻
		    		autoOrder_sign="0"; //�Զ�����˳���
		    		autoVersion_sign="1"; //�Զ����ɰ汾��
		    		break;
		    	 }
		   case 3:
			    replace_sign=message.getUserParameterValue("name"+1);
    	        autoOrder_sign=message.getUserParameterValue("name"+2);
   		        if(Integer.parseInt(replace_sign)==1){
   			        if(Integer.parseInt(autoOrder_sign)==2){
   				      replace_sign="1"; //�滻
   	    		      autoOrder_sign="1"; //�Զ�����˳���
   	    		      autoVersion_sign="0"; //�Զ����ɰ汾��
   	    		      break;
   			          }
   			       else{
   				      replace_sign="1"; //�滻
   	    		      autoOrder_sign="0"; //�Զ�����˳���
   	    		      autoVersion_sign="1"; //�Զ����ɰ汾��
   	    		      break;
   			          }
	    	      }
   		       else{
   			       replace_sign="0"; //�滻
   			       autoOrder_sign="1"; //�Զ�����˳���
   			       autoVersion_sign="1"; //�Զ����ɰ汾��
   			       break;
   		           }
		   case 4:
			    replace_sign="1"; //�滻
	   		    autoOrder_sign="1"; //�Զ�����˳���
	   		    autoVersion_sign="1"; //�Զ����ɰ汾��
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
	/** �˶�excel�е������Ƿ�Ϊ��
	 * @param colum ��
	 * @param strc ֵ
	 * @param row  ��
	 * @param regex ������ʽ
	 * @param list_excel_instructionorder ��ָ��˳���
	 * @param cel ��Ԫ��
	 * @param message ��Ϣ����
	 * @param processid���̺�
	 * @return
	 */
	public boolean checkmessage(int colum,String strc,int row,String regex, List<String>  list_excel_instructionorder,Cell cel,IMessage message,String processid){
	   try{
		  switch(colum){
               case 0: //������Ԫ��
     	            if(strc.equals("")||strc==null){
     	            	addException("��"+(row+1)+"��\"������Ԫ��\"Ϊ��", message, processid);
     	            	return gobackfalse();
			         }
			       if(unitfactory.checkProduceUnitByName(strc, con)==false) {
			    	    addException("��"+(row+1)+"��\"������Ԫ\"������", message, processid);
			    	    return  gobackfalse();
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
		       case 4://ָ��˳���
			        if(Integer.parseInt(autoOrder_sign)==0){
					   if(!strc.matches(regex)){
						  addException("��"+(row+1)+"��\"ָ��˳���\"ӦΪ������", message, processid);
     			         log.fatal("��"+(row+1)+"��\"ָ��˳��Ż�Ϊ������\"Ϊ��");
     			         return gobackfalse();
					   }
     		            else{
     			           list_excel_instructionorder.add(strc);
     		              }
     	             }
			         break;
		        case 5://��Ʒ����ʶ
			        if(strc.equals("")||strc==null){
			        	 addException("��"+(row+1)+"��\"��Ʒ����ʶ\"Ϊ��", message, processid);
			        	  return gobackfalse();
			         }
			         break;
		        case 6:// ��Ʒ����
			         if(strc.equals("")||strc==null){
			        	 addException("��"+(row+1)+"��\"��Ʒ����\"Ϊ��", message, processid);
			        	 return  gobackfalse();
			         }
			         break;
		        case 7:// ��Ʒ��ʶ
		             if(strc.equals("")||strc==null){
		            	 addException("��"+(row+1)+"��\"��Ʒ��ʶ\"Ϊ��", message, processid);
		            	 return  gobackfalse();
			          }
			          break;
		        case 8://����
			         if(!strc.matches(regex)){
			        	 addException("��"+(row+1)+"��\"����\"ӦΪ������", message, processid);
			        	 return  gobackfalse();
			           }
			           break;
		         case 9:
			           break;
		         case 10:
			           break;
               }
		 }catch(Exception e){
			 e.getStackTrace();
			 addException("�쳣", message, processid);
			 return  gobackfalse();
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
			for(int i=0;i<columns;i++){
		         Cell cel=sheet.getCell(i,row);
		         String strc= cel.getContents().trim();
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
							return	gobackfalse();
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
     * @param sheet  ��
     * @param validate ���Ϲ���
     * @param message ��Ϣ����
     * @param processid���̺�
     * @return
     */
	public boolean checkmateriel(int rownum,Sheet sheet,String validate ,IMessage message,String processid){
		 
		   ResultSet rs=null;
		   String sqlinstructionstring="select * from t_ra_instruction  where str_produceMarker=? and int_produnitid="+produceid+" and int_delete=0 ";
		   String sqlinstructionexceptreplacestring ="select * from t_ra_instruction where  str_produceMarker=? and int_produnitid="+produceid+" and int_delete=0 and "
		            +"str_versioncode in ("
                    +"select max(str_versioncode) from t_ra_instruction  where int_delete=0 and "
		            +"not(to_char(dat_produceDate,'yyyy-MM-dd')='"+produceDate+"'  and int_produnitid="+produceid+" and str_workOrder='"+workOrder+"' "
		            +")  group by dat_produceDate,int_produnitid,str_workOrder) ";

		   try{
		         sqlinstruction=con.prepareStatement(sqlinstructionstring);
		         sqlinstructionexceptreplace=con.prepareStatement(sqlinstructionexceptreplacestring);
		          //�ж�������ֵ�Ƿ���ȣ���ȱ��� rownumΪ����
		         for(int j=1;j<rownum;j++){
			           Cell cel=sheet.getCell(7,j);
	                   String strc1= cel.getContents().trim();
	                   if(!strc1.matches(validate)){
	                	   addException("��"+(j+1)+"��\"��Ʒ��ʶ\"�����������Ϲ���", message, processid);
	            	       log.fatal("��"+(j+1)+"��\"��Ʒ��ʶ\"�����������Ϲ���");
	            	       return  gobackfalse();
	                     }
			           for(int k=j+1;k<rownum;k++){ //��excel�еĲ�Ʒ��ʶ���ݽ��бȽ�
		                    Cell ce2=sheet.getCell(7, k);
		                    String strc2=ce2.getContents().trim();
		                    if(strc1.equals(strc2)){
		                    	addException("��"+(j+1)+"��\"��Ʒ��ʶ\"�뱾�����������", message, processid);
		        	            log.fatal("��"+(j+1)+"��\"��Ʒ��ʶ\"�뱾�����������");
		        	            return  gobackfalse();
		                      }
			            }
			            //׷��ʱ�жϵ�ָ����в鿴�Ƿ�����ͬ��������
			          if(Integer.parseInt(replace_sign)==0){
				           sqlinstruction.setString(1, strc1);
					       rs=sqlinstruction.executeQuery();
					       if(rs.next()){
					    	   addException("��"+(j+1)+"��\"��Ʒ��ʶ\"��ָ��ϵͳ���Ѿ���ʹ����.���ʵ����", message, processid);
					    	   return  gobackfalse();
					        }
			             }
			         else{
				            sqlinstructionexceptreplace.setString(1, strc1);
					        rs=sqlinstructionexceptreplace.executeQuery();
					        if(rs.next()){
					        	addException("��"+(j+1)+"��\"��Ʒ��ʶ\"��ָ��ϵͳ���Ѿ���ʹ����.���ʵ����", message, processid);
					        	return  gobackfalse();
					         }
			             }
			  
			  
		        }
		   }catch(Exception e){
			   e.getStackTrace();
			   addException("�쳣", message, processid);
			   return  gobackfalse();
		   }
		return true;   
	 }

	/** ��ѡ���滻ʱ��ɾ��ϵͳ�е�ָ��
	 * @param message
	 * @param processid
	 * @return
	 */
	public boolean replace_signinstruction(IMessage message,String processid){
		try{
		    if(Integer.parseInt(replace_sign)==1){
       	           if(!instructionFactory.checksaveVersion(produceid, produceDate,workOrder ,con)){
       		        //�滻���ɰ汾��ʱ�Ȱ�ָ����е����ݷŵ�ָ����ʷ������ɾ���˱�
       		              if(Integer.parseInt(autoVersion_sign)==1){
       			               //ָ����ʷ����
       				           InstructionHistoryFactory historyfactory=new InstructionHistoryFactory();
       				           List<Instruction> listinstruction = new ArrayList<Instruction>();
       				           //ͨ��������Ԫ�ţ��������ڣ���λ��ָ����Ϣ
                  		       listinstruction= instructionFactory.getInstructionByProduceUnitProduceDateWorkorder(produceid, produceDate,workOrder, con);
                  	           Iterator<Instruction> iter=listinstruction.iterator();
                  		       while(iter.hasNext()){
                  			     Instruction instruction=new Instruction();
                  			     instruction=(Instruction)iter.next();
                  		         versioncode0=instruction.getVersionCode();
                  		         log.debug("������ʷָ��汾�ţ�"+versioncode0);
                  			     historyfactory.saveInstruction(instruction, instruction.getVersionCode(), con);
                  			     log.info("������ʷָ��ɹ�");
                  		       }
                  		       instructionFactory.deleteInstructionByProduceUnitProduceDateWorkorder(produceid,produceDate,workOrder, con);
       			               log.info("ɾ����������Ԫ���������ڵ�ָ��ɹ�");
       		              }
       		             else
       		              {
       			              instructionFactory.deleteInstructionByProduceUnitProduceDateWorkorder(produceid,produceDate,workOrder, con);
       			              log.info("ɾ����������Ԫ���������ڵ�ָ��ɹ�");
       		              }
       	            }
       	          else
       	            {
       	        	 addException("�����Ѿ���ʼ������ָ�������ڱ༭״̬", message, processid);
         	         log.fatal("�����Ѿ���ʼ������ָ�������ڱ༭״̬");
         	        return gobackfalse();
       	            }
             }
		}catch(Exception e){
			e.getStackTrace();
			addException("�쳣", message, processid);
			return  gobackfalse();
		}
		return true;
	}
	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con=(Connection) message.getOtherParameter("con");
		userid=message.getUserParameterValue("userid");
		sum=message.getUserParameterValue("sum");
	    in=(InputStream)message.getOtherParameter("in");
	    in2=(InputStream)message.getOtherParameter("in2");
	    apartParameter(message);
	  
	    try {
	           jxl.Workbook rwb=Workbook.getWorkbook(in);
	    	   Sheet  sheet=rwb.getSheet(0); //��ȡ��һ��Sheet��
	    	   int row=1;//�صڶ��п�ʼȡ���ݡ���һ��Ϊ����
			   int columns=sheet.getColumns(); //��ȡSheet��������������������
			   int rownum=sheet.getRows();//����ȡSheet��������������������
			   List<String> list_excel = new ArrayList<String>();//��excel�еڶ������ݡ�
			   List<String> list_excel_instructionorder = new ArrayList<String>(); // ��excel�е�ָ��˳���
			   List<String> list_instructionorder = new ArrayList<String>(); // ��ָ����е�order
			   String regex ="0*[1-9]{1}[0-9]*"; //������ʽ
			   int num=1; //ֻ���excle�еĵڶ�������
			  
			   if(columns!=11){
				   addException("���ļ�����ָ���ļ�", message, processid);
				   return gobackfalse();
			   }
			   // �鿴������Ҫ�ֶ��Ƿ�Ϊ��
			   while(row<rownum){
			     for(int i=0;i<columns;i++){
		             Cell cel=sheet.getCell(i,row);
		             String strc= cel.getContents().trim();
					 if(num==1){
						 list_excel.add(strc);
				       }
					 if(!checkmessage(i,strc,row,regex,list_excel_instructionorder,cel, message,processid))
		                 return false;
		           }
			       num++;
			       row++;
	            }
			   //�ж�ָ��˳����Ƿ�����ȵ� list_excel_instructionorder ��excel�е�ָ��˳���
			   if(Integer.parseInt(autoOrder_sign)==0){
				   for(int i=0;i<list_excel_instructionorder.size();i++){
					   for(int j=i+1;j<list_excel_instructionorder.size();j++){
						   if(list_excel_instructionorder.get(i).equals(list_excel_instructionorder.get(j))){
							   addException("��"+(i+2)+"�����"+(j+2)+"��\"ָ��˳���\"���", message, processid);
						       log.fatal("��"+(i+2)+"�����"+(j+2)+"��\"ָ��˳���\"���");
						       return gobackfalse();
						   }
					   }
				   }
         	    }
			   //��ȡ������ʱ��
			   String [] date=new String [3];
			   date=list_excel.get(3).split("/");
			   produceDate=date[2]+"-"+date[1]+"-"+date[0];
			   //�����Ϣ
			   workOrder=list_excel.get(2);
			   //��ȡ������Ԫ��id
			   ProduceUnit unit=new ProduceUnit();
			   unit=unitfactory.getProduceUnitByName(list_excel.get(0).trim(),con);
			   produceid=unit.getInt_id();
			   //��ȡ���Ϲ���
			   MaterielRule rule=new MaterielRule();
			   rule=materielfactory.findMaterielRule(unit.getInt_materielRuleid(),con);
			   String validate=rule.getValidate();
			   // �˶��Ƿ����������Ԫ��εĹ���ʱ�̱�
			   boolean ishasworkschedle=workschedlefactory.checkworkOrderProduce(produceid,workOrder,con);
			   if(!ishasworkschedle){
				   addException("û�и�������Ԫ�����Ϣ", message, processid);
				   return   gobackfalse();
				}
			   //׷�Ӳ��Զ�����ָ��˳����ж� list3�д�ŵ���ָ����е�˳���
			   if(Integer.parseInt(replace_sign)==0){
				  if(Integer.parseInt(autoOrder_sign)==0){
				     list_instructionorder=instructionFactory.getallInstructionorder(produceid, produceDate,workOrder,con);
				     for(int i=0;i<list_excel_instructionorder.size();i++){
					      for(int j=0;j<list_instructionorder.size();j++){
						     if(list_excel_instructionorder.get(i).equals(list_instructionorder.get(j))){
						    	 addException("��"+(i+2)+"��\"ָ��˳���\"��ϵͳ���Ѿ�����", message, processid);
						    	 return  gobackfalse();
						       }
					       }
				       }
				  }
				   
			   }
			   //�ж�������Ԫ ���� ��κ����������Ƿ���Ⱥʹ��ڡ�
		       if(!checkproduceidworkorderproducedate(rownum,columns, list_excel, sheet, message, processid)){
		    	  return false;
		       }
		       //�˶�������ֵ�Ƿ����Ҫ��
			   if(!checkmateriel(rownum,sheet,validate,message,processid))
			    	return false;
	            //��ѡ���滻ʱ��ɾ��ϵͳ�е�ָ�������Ԫ���������ڣ�
	           if(!replace_signinstruction(message, processid))
	        	   return false;
		       in.close();
		       list_excel.clear();
		       list_excel_instructionorder.clear();
		       list_instructionorder.clear();
		} catch (Exception sqle) {
			sqle.toString();
			addException("�쳣", message, processid);
			 return gobackfalse();
		}
	return true;
  }
	/**�����������ɰ汾��
	 * @param first ��ʼ�汾�ź���λ��
	 * @param instruction ָ�����
	 * @param str_versioncode ָ����ʷ��汾��+1
	 * @param str_versioncode1ָ����ʷ��汾��+2
	 * @return
	 */
	public String getversioncode(String first,Instruction instruction,String str_versioncode,String str_versioncode1){
		 String versioncode=null;  
		if(!first.equals("01")){
               if(Integer.parseInt(autoVersion_sign)==1){
        	         // �滻ʱ���ɰ汾
        	         if(Integer.parseInt(replace_sign)==1){
        		         instruction.setVersionCode(str_versioncode);
        		         versioncode=str_versioncode;
        		         log.debug("�滻��ָ��汾��:"+str_versioncode);
        	           }
        	          // ׷��ʱ���ɰ汾
        	        else{
        		         instruction.setVersionCode(str_versioncode1);
        		         versioncode=str_versioncode1;
        		         log.debug("׷�ӵ�ָ��汾��:"+str_versioncode1);
        	           }
                 }
               else
               {     //�������ɰ汾ʱ�������滻����׷�Ӷ���һ���汾��
            	        instruction.setVersionCode(str_versioncode);
        		        versioncode=str_versioncode;
               }
           }
          else
           {
    	       instruction.setVersionCode(str_versioncode1);
           }
		return versioncode;
	}
	/**��ָ��������Ͻ��мƻ���Ϣ����ӣ���������������£�
	 * @param instruction
	 * @param materiel
	 */
	public void  addinstructionplanmessage(Instruction instruction,String materiel){
		 
		 //������صļƻ���Ϣ
		String  sqlplanstring="select p.*,to_char(p.Dat_produceDate,'yyyy-MM-dd') as producedate from t_os_plan p where str_versioncode in(select max(str_versioncode) from t_os_plan  where int_upload=1  and int_produnitid="+produceid+" group by int_produnitid,Dat_produceDate,str_workOrder"
			            +") and  str_producemarker=? ";
	try{
		  ResultSet rsprepare=null;
          sqlplanall=con.prepareStatement(sqlplanstring);
          sqlplanall.setString(1,materiel);
          rsprepare=sqlplanall.executeQuery();
          //���汾�ҹ�����ʵ�����Ĳ��ܵ���ȫָ��ļƻ���Ϣ
          if(rsprepare.next()){
       	       long locked= workschedlefactory.getworkschedleadtime(rsprepare.getInt("int_produnitid"),rsprepare.getString("producedate"),rsprepare.getString("str_workorder"),con);
       	       if(locked==0){
       		   //�������ͬ�������ϲ�����ʵ������ȫ��صļƻ���Ϣ
       		   instruction.setPlanDate(rsprepare.getDate("dat_planDate")); 
           	   instruction.setPlanOrder(rsprepare.getInt("int_planOrder"));
       	      }
          }
        else{
       	      instruction.setPlanDate(null); 
       	      instruction.setPlanOrder(0);
         }
	}catch(Exception e){
			e.getStackTrace();
	}
		
 }

	/**���ɰ汾��Ϣ
	 * @param first
	 * @param str_versioncode
	 */
	public void  makeversion(String first,String str_versioncode){
		 Statement stmt=null;
		try{
	                		InstructionVersionFactory factory3=new  InstructionVersionFactory();
	                		Version version=new Version();
		                	version.setInt_delete(0);
		                	if(first.equals("01")){
		                		  version.setVersionCode(str_versioncode);
		                		  log.debug("����һ������ָ��汾��Ϊ��"+str_versioncode);
		                	 }
		                  else{
		                		  
		                		  if(Integer.parseInt(replace_sign)==1){
		                			  version.setVersionCode(versioncode0);
			                		  log.debug("ָ��汾�ţ�"+versioncode0);
		                		   }
		                		  else{
		                		      version.setVersionCode(str_versioncode);  
		                		      log.debug("ָ��汾�ţ�"+str_versioncode);
		                		  }
		                	  }
		       		       
		       		        version.setProdunitid(produceid); 
	                		log.debug("������Ԫ�ţ�"+produceid);
		       		        String username=null;
		       		        int  userId = Integer.parseInt(userid);
		    	            IDAO_UserManager dao_UserManager = DAOFactory_UserManager.getInstance(DataBaseType.getDataBaseType(con));
		    	            String sql = dao_UserManager.getSQL_SelectUserById(userId);
		    	            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		    	            log.debug("ͨ���û�ID��ѯ�û�SQL��䣺"+sql);
		    	            ResultSet rs = stmt.executeQuery(sql);
		    	            if(rs.next()){
		    	        	   username = rs.getString("CUSRNAME");
		    	        	   log.debug("�û�����"+username);
		    	            }
		    	           else{
		    	        	   username = null;
		    	        	  log.debug("�û�������ֵ");
		    	            }
		       		       version.setUser(username);
		       		       if(versioncode0!=null||first.equals("01")){
		       		       factory3.saveVersion(version, con);
		       		       }
		       		       log.info("�����汾�ɹ�");
		       		      
		             
		}catch(Exception e){
			e.getStackTrace();
		}finally{
			try{
			 if(stmt!=null)
    		   stmt.close();
			}catch(Exception s){
				s.printStackTrace();
			}
		}
	    }
	
	/** ׷��ʱ���ɰ汾��ָ���е����ݴ�ŵ�ָ����ʷ����Ȼ�����ָ��汾��
	 * @param str_versioncode ָ����ʷ��İ汾��+1
	 * @param str_versioncode1ָ����ʷ��İ汾��+2
	 */
	public void  addreplace_signautoVersion_sign(String str_versioncode,String str_versioncode1){
	 try{
		 List<Instruction> listinstruction = new ArrayList<Instruction>();
  		 listinstruction=instructionFactory.getInstructionByProduceUnitProduceDateWorkorder(produceid,produceDate,workOrder,con);
  	     Iterator<Instruction> iter=listinstruction.iterator();
  		 while(iter.hasNext()){
  			  Instruction instruction=new Instruction();
  			  instruction=(Instruction)iter.next();
  			  historyfactory.saveInstruction(instruction, str_versioncode, con);
  			  log.info("������ʷ�汾�ɹ�");
  			  instructionFactory.updateInstructionVersioncode(instruction, str_versioncode1, con);
  			  log.debug("����ָ��汾��--�汾��Ϊ��"+str_versioncode1);
  		 }
	   }catch(Exception e){
			 e.getStackTrace();
	  }
  }
	public ExecuteResult doAdapterService(IMessage message, String processid)
	throws SQLException, Exception {
try {
	try {
              jxl.Workbook rwb = Workbook.getWorkbook(in2);//����Workbook����, ֻ��Workbook���� ֱ�Ӵӱ����ļ�����Workbook ������������Workbook
    	      Sheet  sheet = rwb.getSheet(0); //��ȡ��һ��Sheet��
    	      int row=1; // ��excel��һ�п�ʼȥ���ݣ�0��Ϊ����
		      int columns=sheet.getColumns(); //��ȡSheet��������������������
		      int c=sheet.getRows(); //����ȡSheet��������������������
		      int mmm=1; //��ϵͳֻȡһ�ΰ汾��
		      int num=1; //�Լ�����˳���
		      int banben=1; //��ϵͳֻ����һ���汾
		      String str_versioncode=null; //  ָ����ʷ���еİ汾��+1
		      String str_versioncode1=null; //  ָ����еİ汾��+2
		      String versioncode=null;//   �汾��
		      String first="01"; //��ʼ�汾�ŵ������λֵ
		      List<String> list_excel = new ArrayList<String>();   // ���excel��һ�е�����
		      int maxnum=instructionFactory.getInstructionMaxOrder(produceid,produceDate,workOrder,con);
	          while(row<c){
	        	   Instruction instruction=new Instruction();
	               //��ȡ��һ�е�����
		           for(int i=0;i<columns;i++){
	                    Cell cel=sheet.getCell(i,row);
	                    String strc= cel.getContents().trim();
	                    list_excel.add(strc);
	                }
	               String second=null;
	               if(mmm==1){
		                String code=historyfactory.checkcodebyproduceunitanddateWorkorder(produceid, produceDate,list_excel.get(2), con);
		                log.debug("ָ��汾�ţ�"+code);
		                if(!code.equals("")&&code!=null&&!code.equals("null"))
		                 {
				             int leng=code.length();
	  	                     String code1=code.substring(leng-2,leng-1);//ʮλ
			    	         String code2=code.substring(leng-1,leng);  //	��λ
			                 int gewei=Integer.parseInt(code2)+1;
			    	         if(gewei<=9){
			    	 	        first=code1+String.valueOf(gewei);
			    	          }
			               else{
			    	           int shiwei=Integer.parseInt(code1)+1;
			    	           first=String.valueOf(shiwei)+"0";
			    	          }
	                    }
		               if(Integer.parseInt(first)+1<=9){
    		               second="0"+String.valueOf((Integer.parseInt(first)+1));
    	                }
    	              else{
    		               second=String.valueOf((Integer.parseInt(first)+1));
    	                }
			          ProduceUnit produceunit=new ProduceUnit();
			          // ͨ��������Ԫid��ȡ������Ԫ��ͬʱ���ɰ汾��
	                  produceunit=unitfactory.getProduceUnitbyId(produceid, con);
			          String str_name=produceunit.getStr_name();
			          String [] date=new String [3];
			          date=produceDate.split("-");
		              String namess=date[0]+date[1]+date[2];
		              str_versioncode=namess+str_name+list_excel.get(2)+first;
		              str_versioncode1=namess+str_name+list_excel.get(2)+second;
	                  mmm++;
	               }
	              instruction.setProdunitid(produceid);  //������Ԫ
	              instruction.setProduceDate((new SimpleDateFormat("yyyy-MM-dd")).parse(produceDate));//��������
	              log.debug("�������ڣ�"+instruction.getProduceDate());
	              //�����������ɰ汾��
	              if(mmm==2){
	            	  versioncode=getversioncode(first,instruction,str_versioncode,str_versioncode1);
	              }
	              else{
	            	  instruction.setVersionCode(versioncode);
	              }
	              //���ѡ�����滻���Զ�����ָ��˳���ʱ
	             if(Integer.parseInt(replace_sign)==1){
	            	  if(Integer.parseInt(autoOrder_sign)==1){
	 	            	 instruction.setInstructionOrder(num);
	 	            	 log.debug("ָ��˳���:"+num);
	 	            	 num++;
	 	              }
	            	  else{
	            		 instruction.setInstructionOrder(Integer.parseInt(list_excel.get(4)));
		 	             log.debug("ָ��˳���:"+list_excel.get(4));
	            	  }
	             }
	              //���ѡ����׷�ӵ��Զ�����ָ��˳���ʱ
	             if(Integer.parseInt(replace_sign)==0){
	            	      if(Integer.parseInt(autoOrder_sign)==1){
	            	    	  maxnum++;
	          	              instruction.setInstructionOrder(maxnum);
	          	              log.debug("ָ��˳���:"+maxnum);
		 	               }
		            	  else{
		            		  instruction.setInstructionOrder(Integer.parseInt(list_excel.get(4)));
		            		  log.debug("ָ��˳���:"+list_excel.get(4));
		            	   }
	              }
	             //��ָ��������Ͻ��мƻ���Ϣ����ӣ���������������£�
	             addinstructionplanmessage(instruction,list_excel.get(7));
	             instruction.setProduceType(list_excel.get(5).trim());
	             instruction.setProduceName(list_excel.get(6).trim());
	             instruction.setProduceMarker(list_excel.get(7).trim());
	             instruction.setWorkOrder(list_excel.get(2).trim());
	             instruction.setWorkTeam(list_excel.get(1).trim());
	             log.debug("��Ʒ���ͣ�"+list_excel.get(5)+"��Ʒ����"+list_excel.get(6)+"����Ʒ��ʶ��"+
	        		   list_excel.get(7)+"����Σ�"+list_excel.get(2)+"�����飺"+list_excel.get(1));
	             if(list_excel.get(9)!=null&&!list_excel.get(9).equals(""))
	              {
	            	  String [] u=new String [3];
		              u=list_excel.get(9).split("/");
		              String begintime= u[2]+"-"+u[1]+"-"+u[0];
	                  instruction.setPlanBegin((new SimpleDateFormat("yyyy-MM-dd")).parse(begintime));
	                  log.debug("��ʼʱ�䣺"+instruction.getPlanBegin());
	              }
	            else{
	            	  instruction.setPlanBegin(null);
		              log.debug("��ʼʱ�丳��ֵ");
	            }
	            if(list_excel.get(10)!=null&&!list_excel.get(10).equals(""))
	            {
	            	  String [] u=new String [3];
		              u=list_excel.get(10).split("/");
		              String endtime= u[2]+"-"+u[1]+"-"+u[0];
	                  instruction.setPlanFinish((new SimpleDateFormat("yyyy-MM-dd")).parse(endtime));
	                  log.debug("����ʱ�䣺"+instruction.getPlanFinish());
	            }
	            else{
	            	  instruction.setPlanFinish(null);
	                  log.debug("����ʱ�丳��ֵ");
	            }
	            instruction.setCount(Integer.parseInt(list_excel.get(8)));
	            log.debug("������"+list_excel.get(10));
	            int instructionstateid=unitfactory.getInstructionstateIdByproduceunitid(produceid,con);
	            instruction.setInstructStateID(instructionstateid);
	            instruction.setIssuance(0);
	            instruction.setStaError(0);
	            instruction.setDelete(0);
	            instruction.setEdit(0);
	            log.debug("ָ��״̬��:"+instructionstateid+"���Ƿ񷢲������Ƿ���󣺷��Ƿ�ɾ�������Ƿ����ڱ༭����");
	             //��ָ������������
	            instructionFactory.saveInstruction(instruction,con);
	            log.info("����ָ��ɹ�");
	            if(first.equals("01")){
	            historyfactory.saveInstruction(instruction, str_versioncode, con);
	            log.debug("����ָ��--�汾��Ϊ��"+str_versioncode);
	             }    
	            //���ɰ汾
	           if(Integer.parseInt(autoVersion_sign)==1||first.equals("01")){
	               if(banben==1){
	        	      makeversion(first,str_versioncode);	
	        	      log.debug("�����汾�ɹ�");
		       	      banben++;
	               }
	           }
	           list_excel.clear(); 
	   		   row++;
	   		   
	         
	         }
	         //׷��ʱ���ɰ汾��ָ���е����ݴ�ŵ�ָ����ʷ����Ȼ�����ָ��汾��
	         if(!first.equals("01")){
         	    if(Integer.parseInt(replace_sign)==0){
         		     if(Integer.parseInt(autoVersion_sign)==1){
         			  addreplace_signautoVersion_sign(str_versioncode,str_versioncode1);
         		   }
         	     }
		      
	          }
    	  in2.close();
		
		
	} catch (SQLException sqle) {
		message.addServiceException(new ServiceException(
				ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
						.getId(), processid, new Date(), sqle));
		log.error("���ݿ��쳣");
		return ExecuteResult.fail;
	}
} catch (Exception e) {
	message.addServiceException(new ServiceException(
			ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
			processid, new java.util.Date(), e));
	log.error("δ֪�쳣");
	return ExecuteResult.fail;
}
  
return ExecuteResult.sucess;
}

@Override
public void relesase() throws SQLException {
	   if(con!=null){
	    con=null;
	    }
	    in=null;
		in2=null;
		produceid=0;
		workOrder=null;
		produceDate=null;
	    replace_sign=null;
		autoOrder_sign=null;
	    autoVersion_sign=null;
	    userid=null;
	    try{
	    	if(sqlinstruction!=null){
	         sqlinstruction.close();
	    	}
	    	if(sqlplanall!=null){
	    	 sqlplanall.close();
	    	}
		   if(sqlinstructionexceptreplace!=null){
			 sqlinstructionexceptreplace.close();
		   }
	    
	    }catch(Exception e){
	    	e.getStackTrace();
	    }

   }
}