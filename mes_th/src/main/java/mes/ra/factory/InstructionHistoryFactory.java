package mes.ra.factory;
/**
 * author : л����
 */
import java.sql.*;
import java.text.ParseException;

import mes.framework.DataBaseType;
import mes.ra.bean.Instruction;
import mes.ra.dao.DAO_InstructionHistory;
import mes.system.dao.DAOFactoryAdapter;
import java.util.*;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InstructionHistoryFactory {
	private final Log log = LogFactory.getLog(InstructionHistoryFactory.class);
	/**
	 * * ����ָ��汾���ݵ�sql���
	 * л����
	 * @param instruction
	 * @param str_versioncode
	 * @param con
	 * @throws SQLException
	 */

	 public void saveInstruction (Instruction instruction,String str_versioncode,Connection con) throws SQLException{
	        DAO_InstructionHistory dao_Instructionhistory = (DAO_InstructionHistory)DAOFactoryAdapter.getInstance(
	                DataBaseType.getDataBaseType(con),DAO_InstructionHistory.class);
	        Statement stmt = con.createStatement();
	        log.debug("����ָ��汾���ݵ�sql���"+dao_Instructionhistory.saveVersionHistory(instruction, str_versioncode));
	      
	        stmt.execute(dao_Instructionhistory.saveVersionHistory(instruction, str_versioncode));
	      
	        if(stmt != null){
	            stmt.close();
	        }
	    }
	 /**
	  * ͨ��ָ��汾��ŵõ�ָ��汾��¼��
	  * л����
	  * @param int_id
	  * @param con
	  * @return
	  * @throws SQLException
	  */
     public  List<Instruction> getInstructionHistory(int int_id,Connection con) throws SQLException{
	        DAO_InstructionHistory dao_Instructionhistory = (DAO_InstructionHistory)DAOFactoryAdapter.getInstance(
	                DataBaseType.getDataBaseType(con),DAO_InstructionHistory.class);
	        Statement stmt = con.createStatement();
	        log.debug("ͨ��ָ��汾��ŵõ�ָ��汾��¼��"+dao_Instructionhistory.getInstructionHistory(int_id));
	        List<Instruction> list = new ArrayList<Instruction>();
	        ResultSet rs=stmt.executeQuery(dao_Instructionhistory.getInstructionHistory(int_id));
	        while(rs.next()){
			 	Instruction ins1 = new Instruction();
		   			ins1.setProdunitid(rs.getInt("INT_PRODUNITID"));
		            ins1.setProduceDate(rs.getDate("DAT_PRODUCEDATE"));
		            ins1.setVersionCode(rs.getString("STR_VERSIONCODE"));
		            ins1.setInstructionOrder(rs.getInt("INT_INSTRUCTORDER"));
		            ins1.setPlanDate(rs.getDate("DAT_PLANDATE"));
		            ins1.setPlanOrder(rs.getInt("INT_PLANORDER"));
		            ins1.setProduceType(rs.getString("STR_PRODUCETYPE"));
		            ins1.setProduceName(rs.getString("STR_PRODUCENAME"));
		            ins1.setProduceMarker(rs.getString("STR_PRODUCEMARKER"));
		            ins1.setWorkOrder(rs.getString("STR_WORKORDER"));
		            ins1.setWorkTeam(rs.getString("STR_WORKTEAM"));
		            ins1.setPlanBegin(rs.getDate("TIM_PLANBEGIN"));
		            ins1.setPlanFinish(rs.getDate("TIM_PLANFINISH"));
		            ins1.setCount(rs.getInt("INT_COUNT"));
		            ins1.setInstructStateID(rs.getInt("INT_INSTRUCTSTATEID"));
		  		list.add(ins1);
		  	}
	        if(stmt!=null){
	        	stmt.close();
	        }
	        return list;
	        
	        
     }
 
	 

	 /**
	  * ͨ��������Ԫ���������ڲ�ѯָ��汾��¼��
	  * л����
	  * @param int_produnitid
	  * @param Dat_produceDate
	  * @param con
	  * @return
	  * @throws SQLException
	  */

	 public List<Instruction>  getInstructionhistorybyproduceunitdate(int  int_produnitid,String Dat_produceDate,Connection con)throws SQLException{
	        DAO_InstructionHistory dao_Instructionhistory = (DAO_InstructionHistory)DAOFactoryAdapter.getInstance(
	                DataBaseType.getDataBaseType(con),DAO_InstructionHistory.class);
	        Statement stmt = con.createStatement();
	        List<Instruction> list = new ArrayList<Instruction>();
	        log.debug("ͨ��������Ԫ���������ڲ�ѯָ��汾��¼��"+dao_Instructionhistory.getInstructionhistorybyproduceunitdate(int_produnitid, Dat_produceDate));
	        ResultSet rs=stmt.executeQuery(dao_Instructionhistory.getInstructionhistorybyproduceunitdate(int_produnitid, Dat_produceDate));
	        while(rs.next()){
	       	 Instruction instruction = new Instruction();
	            instruction.setId(rs.getInt("INT_ID"));
	            instruction.setProdunitid(rs.getInt("INT_PRODUNITID"));
	            instruction.setProduceDate(rs.getDate("DAT_PRODUCEDATE"));
	            instruction.setVersionCode(rs.getString("STR_VERSIONCODE"));
	            instruction.setInstructionOrder(rs.getInt("INT_INSTRUCTORDER"));
	            instruction.setPlanDate(rs.getDate("DAT_PLANDATE"));
	            instruction.setPlanOrder(rs.getInt("INT_PLANORDER"));
	            instruction.setProduceType(rs.getString("STR_PRODUCETYPE"));
	            instruction.setProduceName(rs.getString("STR_PRODUCENAME"));
	            instruction.setProduceMarker(rs.getString("STR_PRODUCEMARKER"));
	            instruction.setWorkOrder(rs.getString("STR_WORKORDER"));
	            instruction.setWorkTeam(rs.getString("STR_WORKTEAM"));
	            instruction.setPlanBegin(rs.getTime("TIM_PLANBEGIN"));
	            instruction.setPlanFinish(rs.getTime("TIM_PLANFINISH"));
	            instruction.setCount(rs.getInt("INT_COUNT"));
	            instruction.setInstructStateID(rs.getInt("INT_INSTRUCTSTATEID"));
	            list.add(instruction);
	        }
			if (stmt != null) {
				stmt.close();
			}
	        
	        return list;
	 }
	 /**
	  * * л����
	  *   ͨ���汾��ɾ��ָ����ʷ��
	  * @param code
	  * @param con
	  * @throws SQLException
	  */
	 public void deleteInstructionhistoryBycode(String code,Connection con)throws SQLException{
	        DAO_InstructionHistory dao_Instructionhistory = (DAO_InstructionHistory)DAOFactoryAdapter.getInstance(
	                DataBaseType.getDataBaseType(con),DAO_InstructionHistory.class);
	        Statement stmt = con.createStatement();
	        log.debug("ͨ���汾��ɾ��ָ����ʷ��"+dao_Instructionhistory.delVersionHistory(code));
	        stmt.executeUpdate(dao_Instructionhistory.delVersionHistory(code));
	    	if (stmt != null) {
				stmt.close();
			}
	 }
	 /**
	  *  * �������İ汾��
	  * л����
	  * @param int_produnitid
	  * @param str_date
	  * @param workOrder
	  * @param con
	  * @return
	  * @throws SQLException
	  */
	
	 public   String  checkcodebyproduceunitanddateWorkorder(int int_produnitid,String str_date,String workOrder,Connection con)throws SQLException{
	        DAO_InstructionHistory dao_Instructionhistory = (DAO_InstructionHistory)DAOFactoryAdapter.getInstance(
	                DataBaseType.getDataBaseType(con),DAO_InstructionHistory.class);
	        Statement stmt = con.createStatement();
	        log.debug("�������İ汾��"+dao_Instructionhistory.checkcodebyproduceunitanddateWorkorder(int_produnitid, str_date,workOrder));
	       ResultSet rs=stmt.executeQuery(dao_Instructionhistory.checkcodebyproduceunitanddateWorkorder(int_produnitid, str_date,workOrder));
		   String versioncode="";
	       if(rs.next()){
			   versioncode=rs.getString("Str_versionCode");
		   }
			if (stmt != null) {
				stmt.close();
			}

	       return versioncode;
	      
	 }
	 /**
	  *  * л����
      * ͨ���汾�ŵ�ָ����ʷ���в������ݵļ���
	  * @param versioncode
	  * @param con
	  * @return
	  * @throws SQLException
	  */
    
     public List<Instruction>  getInstructionbyversioncode(String versioncode,Connection con)throws SQLException{
	        DAO_InstructionHistory dao_Instructionhistory = (DAO_InstructionHistory)DAOFactoryAdapter.getInstance(
	                DataBaseType.getDataBaseType(con),DAO_InstructionHistory.class);
	        Statement stmt = con.createStatement();
	        List<Instruction> list = new ArrayList<Instruction>();
	        log.debug("ͨ���汾�ŵ�ָ����ʷ���в������ݵļ���"+dao_Instructionhistory.getInstructionbyversioncode(versioncode));
	        ResultSet rs=stmt.executeQuery(dao_Instructionhistory.getInstructionbyversioncode(versioncode));
	        while(rs.next()){
	       	 Instruction instruction = new Instruction();
	            instruction.setId(rs.getInt("INT_ID"));
	            instruction.setProdunitid(rs.getInt("INT_PRODUNITID"));
	            instruction.setProduceDate(rs.getDate("DAT_PRODUCEDATE"));
	            instruction.setVersionCode(rs.getString("STR_VERSIONCODE"));
	            instruction.setInstructionOrder(rs.getInt("INT_INSTRUCTORDER"));
	            instruction.setPlanDate(rs.getDate("DAT_PLANDATE"));
	            instruction.setPlanOrder(rs.getInt("INT_PLANORDER"));
	            instruction.setProduceType(rs.getString("STR_PRODUCETYPE"));
	            instruction.setProduceName(rs.getString("STR_PRODUCENAME"));
	            instruction.setProduceMarker(rs.getString("STR_PRODUCEMARKER"));
	            instruction.setWorkOrder(rs.getString("STR_WORKORDER"));
	            instruction.setWorkTeam(rs.getString("STR_WORKTEAM"));
	            instruction.setPlanBegin(rs.getTime("TIM_PLANBEGIN"));
	            instruction.setPlanFinish(rs.getTime("TIM_PLANFINISH"));
	            instruction.setCount(rs.getInt("INT_COUNT"));
	            instruction.setInstructStateID(rs.getInt("INT_INSTRUCTSTATEID"));
	            list.add(instruction);
	        }
			if (stmt != null) {
				stmt.close();
			}
	       
	        return list;
	 }
     /**
      * л���� �鿴ָ����ʷ�����Ƿ�ʹ��������Ԫ
      * �����ж��Ƿ����ɾ��������Ԫ
      * @param produceid
      * @param con
      * @return
      * @throws SQLException
      * @throws ParseException
      */
     public boolean checkproduceandproducedate(int produceid,Connection con)throws SQLException ,ParseException{
         
    	 Calendar calendar=Calendar.getInstance();
         calendar.setTime(new Date());
         int hour=calendar.get(Calendar.HOUR_OF_DAY);
         String year=String.valueOf(calendar.get(Calendar.YEAR));
         String month=String.valueOf(calendar.get(Calendar.MONDAY)+1);
          if(calendar.get(Calendar.MONDAY)+1<10){
          	month="0"+month;
          }
         
         String day=null;
         String str_date=null;
         if(hour>=8)
         {
      	   day=String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
      	   if(calendar.get(Calendar.DAY_OF_MONTH)<10){
      		   day="0"+day;
      	   }
      	   str_date=year+"-"+month+"-"+day;
         }
         else
         { 
      	   day=String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)-1);
      	   str_date=year+"-"+month+"-"+day;
         }
         DAO_InstructionHistory dao_Instructionhistory = (DAO_InstructionHistory)DAOFactoryAdapter.getInstance(
	                DataBaseType.getDataBaseType(con),DAO_InstructionHistory.class);
	        Statement stmt = con.createStatement();
	        log.debug("�鿴ָ����ʷ�����Ƿ�ʹ��������Ԫ"+dao_Instructionhistory.getInstructionhistorybyproduceunitdate(produceid, str_date));
	      ResultSet rs=stmt.executeQuery(dao_Instructionhistory.getInstructionhistorybyproduceunitdate(produceid, str_date));
	      boolean f=false;
			if(rs.next()){
				f=true;
			}

			if (stmt != null) {
				stmt.close();
			}
	      return f;

     }
 
}
