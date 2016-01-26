package com.qm.mes.ra.factory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.*;
import java.text.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.DataBaseType;
import com.qm.mes.ra.bean.Instruction;
import com.qm.mes.ra.dao.DAO_Instruction;
import com.qm.mes.system.dao.DAOFactoryAdapter;

/**
 *
 * @author YuanPeng
 */
public class InstructionFactory {
	private final Log log = LogFactory.getLog(InstructionFactory.class);
	
	 /**
     * ����ָ��
     * Ԭ��
     * @param instruction
     *          ָ�����
     * @param con
     *          ���Ӷ���
     * @throws java.sql.SQLException
     */
    public void saveInstruction (Instruction instruction,Connection con) throws SQLException{
        DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
                DataBaseType.getDataBaseType(con),DAO_Instruction.class);
        Statement stmt = con.createStatement();
        log.debug("����ָ��"+dao_Instruction.saveInstruction(instruction));
        stmt.execute(dao_Instruction.saveInstruction(instruction));
        if(stmt != null){
            stmt.close();
        }
    }

    /**
     * ͨ��ID��ѯָ��
     * Ԭ��
     * @param id
     *          ָ�����к�
     * @param con
     *          ���Ӷ���
     * @return ͨ��ID��ѯ����ָ�����
     * @throws java.sql.SQLException
     */
    public Instruction getInstructionById (int id,Connection con) throws SQLException, ParseException{
        Instruction instruction = new Instruction();
        DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
                DataBaseType.getDataBaseType(con),DAO_Instruction.class);
        Statement stmt = con.createStatement();
        log.debug("ͨ��ID��ѯָ��"+dao_Instruction.getInstructionById(id));
        ResultSet rs = stmt.executeQuery(dao_Instruction.getInstructionById(id));
        if(rs.next()){
            instruction.setId(rs.getInt("INT_ID"));
            instruction.setProdunitid(rs.getInt("INT_PRODUNITID"));
            instruction.setProduceDate(rs.getDate("DAT_PRODUCEDATE"));
            instruction.setVersionCode(rs.getString("STR_VERSIONCODE"));
            instruction.setInstructionOrder(rs.getInt("INT_INSTRUCTORDER"));
            instruction.setPlanDate(rs.getString("DAT_PLANDATE")==null?null:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("DAT_PLANDATE")));
            instruction.setPlanOrder(rs.getInt("INT_PLANORDER"));
            instruction.setProduceType(rs.getString("STR_PRODUCETYPE"));
            instruction.setProduceName(rs.getString("STR_PRODUCENAME"));
            instruction.setProduceMarker(rs.getString("STR_PRODUCEMARKER"));
            instruction.setWorkOrder(rs.getString("STR_WORKORDER"));
            instruction.setWorkTeam(rs.getString("STR_WORKTEAM"));
            instruction.setPlanBegin(rs.getString("TIM_PLANBEGIN")==null?null:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("TIM_PLANBEGIN")));
            instruction.setPlanFinish(rs.getString("TIM_PLANFINISH")==null?null:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("TIM_PLANFINISH")));
            instruction.setCount(rs.getInt("INT_COUNT"));
            instruction.setInstructStateID(rs.getInt("INT_INSTRUCTSTATEID"));
            instruction.setIssuance(rs.getInt("INT_ISSUANCE"));
            instruction.setStaError(rs.getInt("INT_STAERROR"));
            instruction.setDelete(rs.getInt("INT_DELETE"));
            instruction.setEdit(rs.getInt("INT_EDIT"));
        }
        if(stmt != null)
            stmt.close();
        return instruction;
    }

    /**
     * ��ѯ��Int_instructOrder��Ķ���
     * Ԭ��
     * @param Int_instructOrder
     *                  ָ��˳���
     * @param con
     *          ���Ӷ���
     * @throws java.sql.SQLException
     * return Instruction
     *              ���ر�Int_instructOrder��Ķ���
     */
    public List<Instruction> OrderPlus (int ProduceUnitID, String str_date,String workOrder,int Int_instructOrder,Connection con) throws SQLException, ParseException{
    	List<Instruction> list = new ArrayList<Instruction>();
        DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
                DataBaseType.getDataBaseType(con),DAO_Instruction.class);
        Statement stmt = con.createStatement();
        log.debug("��ѯ��Int_instructOrder��Ķ���"+dao_Instruction.getByOrderPlus(ProduceUnitID,str_date,workOrder,Int_instructOrder));
        ResultSet rs = stmt.executeQuery(dao_Instruction.getByOrderPlus(ProduceUnitID,str_date,workOrder,Int_instructOrder));
        while(rs.next()){
            Instruction instruction = new Instruction();
            instruction.setId(rs.getInt("INT_ID"));
            instruction.setProdunitid(rs.getInt("INT_PRODUNITID"));
            instruction.setProduceDate(rs.getDate("DAT_PRODUCEDATE"));
            instruction.setVersionCode(rs.getString("STR_VERSIONCODE"));
            instruction.setInstructionOrder(rs.getInt("INT_INSTRUCTORDER"));
            instruction.setPlanDate(rs.getString("DAT_PLANDATE")==null?null:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("DAT_PLANDATE")));
            instruction.setPlanOrder(rs.getInt("INT_PLANORDER"));
            instruction.setProduceType(rs.getString("STR_PRODUCETYPE"));
            instruction.setProduceName(rs.getString("STR_PRODUCENAME"));
            instruction.setProduceMarker(rs.getString("STR_PRODUCEMARKER"));
            instruction.setWorkOrder(rs.getString("STR_WORKORDER"));
            instruction.setWorkTeam(rs.getString("STR_WORKTEAM"));
            instruction.setPlanBegin(rs.getString("TIM_PLANBEGIN")==null?null:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("TIM_PLANBEGIN")));
            instruction.setPlanFinish(rs.getString("TIM_PLANFINISH")==null?null:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("TIM_PLANFINISH")));
            instruction.setCount(rs.getInt("INT_COUNT"));
            instruction.setInstructStateID(rs.getInt("INT_INSTRUCTSTATEID"));
            instruction.setIssuance(rs.getInt("INT_ISSUANCE"));
            instruction.setStaError(rs.getInt("INT_STAERROR"));
            instruction.setDelete(rs.getInt("INT_DELETE"));
            instruction.setEdit(rs.getInt("INT_EDIT"));
            list.add(instruction);
        }
        if (stmt != null) {
			stmt.close();
		}
        return list;
    }

   /**
    * ͨ��������Ԫ�š��������ڡ�״̬�š�������������ѯ�����Ա��༭����ҵָ��
    * Ԭ��
    * @param ProduceUnitID
    *           ������Ԫ��
    * @param str_date
    *           ��������
    * @param stateid
    *           ״̬��
    * @param LockNum
    *       ��������
    * @param con
    *       ���Ӷ���
    * @return ��ҵָ��ID(List<Integer>)
    * @throws java.sql.SQLException
    */
   public List<Integer> getInstructionsByUnlock(int ProduceUnitID,String str_date,String workOrder, int stateid,int LockNum,Connection con)throws SQLException{
       int i=0;
       List<Integer> list = new ArrayList<Integer>();
       DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
               DataBaseType.getDataBaseType(con),DAO_Instruction.class);
		Statement stmt = con.createStatement();
		log.debug(" ͨ��������Ԫ�š��������ڡ���Ρ�״̬�š�������������ѯ�����Ա��༭����ҵָ��"+dao_Instruction.getInstructionByProUnitProDateStateOrder(ProduceUnitID,str_date,workOrder,stateid));
		ResultSet rs  = stmt.executeQuery(dao_Instruction.getInstructionByProUnitProDateStateOrder(ProduceUnitID,str_date,workOrder,stateid));
        while(rs.next()){
            if(++i > LockNum){
                list.add(rs.getInt(1));
            }
        }
        if (stmt != null) {
			stmt.close();
		}
        return list;
   }

   /**
     * ͨ��ID�޸�ָ��༭״̬
     * Ԭ��
     * @param id
     *          ָ�����к�
     * @param con
     *          ���Ӷ���
     * @return ͨ��ID�޸�ָ��༭״̬
     * @throws java.sql.SQLException
     */
    public void editInstructionById (int id,Connection con) throws SQLException{
        DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
                DataBaseType.getDataBaseType(con),DAO_Instruction.class);
        Statement stmt = con.createStatement();
        log.debug(" ͨ��ID�޸�ָ��༭״̬"+dao_Instruction.editInstructionById(id));
        stmt.execute(dao_Instruction.editInstructionById(id));
        if(stmt != null)
            stmt.close();
    }

    /**
     * ͨ��ID�޸�ָ��ȡ���༭״̬
     * Ԭ��
     * @param id
     *          ָ�����к�
     * @param con
     *          ���Ӷ���
     * @return ͨ��ID�޸�ָ��༭״̬
     * @throws java.sql.SQLException
     */
    public void uneditInstructionById (int id,Connection con) throws SQLException{
        DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
                DataBaseType.getDataBaseType(con),DAO_Instruction.class);
        Statement stmt = con.createStatement();
        log.debug(" ͨ��ID�޸�ָ��ȡ���༭״̬"+dao_Instruction.uneditInstructionById(id));
        stmt.execute(dao_Instruction.uneditInstructionById(id));
        if(stmt != null)
            stmt.close();
    }
    
    /**
     * ͨ��������Ԫ�š��������ڡ�˳��Ŵ����׸��Ǳ༭��������int_delete�ֶ�ֵΪ1
     *  Ԭ��
     * @param ProduceUnitID ������Ԫ��
     * @param str_date ��������
     * @param UnlockStartOrder
     * @return 
     */
    public void DeleteInstructionByProUnitDateworkOrderUnlockOrder (int ProduceUnitID, String str_date,String workOrder, int UnlockStartOrder, Connection con) throws SQLException{
        DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
                DataBaseType.getDataBaseType(con),DAO_Instruction.class);
        Statement stmt = con.createStatement();
        log.debug(" ͨ��������Ԫ�š��������ڡ���Ρ�˳��Ŵ����׸��Ǳ༭��������int_delete�ֶ�ֵΪ1"+dao_Instruction.DeleteInstructionByProUnitDateworkOrderUnlockOrder(ProduceUnitID, str_date,workOrder, UnlockStartOrder));
        stmt.execute(dao_Instruction.DeleteInstructionByProUnitDateworkOrderUnlockOrder(ProduceUnitID, str_date,workOrder, UnlockStartOrder));
        if(stmt != null)
            stmt.close();
    }
    
    /**
     * ͨ��������Ԫ�š��������ڲ�ѯ��¼����
     *  Ԭ��
     * @param ProduceUnitID
     * 			������Ԫ��
     * @param str_date
     * 			��������
     * @return ����ͨ��������Ԫ�š��������ڲ�ѯ��¼������
     */
    public int getCountByProUnitDateOrder(int ProduceUnitID, String str_date, String str_workOrder,Connection con){
    	int count = 0;
    	try {
    	DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
			DataBaseType.getDataBaseType(con),DAO_Instruction.class);
		
 		Statement stmt = con.createStatement();
 		log.debug(" ͨ��������Ԫ�š��������ڡ���Σ�˳��Ŵ����׸��Ǳ༭��������int_delete�ֶ�ֵΪ1"+dao_Instruction.getCountByProUnitDateOrder(ProduceUnitID, str_date,str_workOrder));
 		ResultSet rs  = stmt.executeQuery(dao_Instruction.getCountByProUnitDateOrder(ProduceUnitID, str_date,str_workOrder));
         if(rs.next()){
             count = rs.getInt(1);
         }
         if (stmt != null) {
  			stmt.close();
  		}
         
    	} catch (SQLException e) {
			e.printStackTrace();
		}
         return count;
    }

    /**
	    * ͨ��������Ԫ�š��������ڡ�״̬�š�������������ѯ�������Ա��༭����ҵָ��
	    * Ԭ��
	    * @param ProduceUnitID
	    *           ������Ԫ��
	    * @param str_date
	    *           ��������
	    * @param stateid
	    *           ״̬��
	    * @param LockNum
	    *       ��������
	    * @param con
	    *       ���Ӷ���
	    * @return ��ҵָ��ID(List<Integer>)
	    * @throws java.sql.SQLException
	    */
	   public List<Integer> getInstructionsBylock(int ProduceUnitID,String str_date,String workOrder,int stateid,int LockNum,Connection con)throws SQLException{
	       int i=0;
	       List<Integer> list = new ArrayList<Integer>();
	       DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
	               DataBaseType.getDataBaseType(con),DAO_Instruction.class);
			Statement stmt = con.createStatement();
			log.debug("ͨ��������Ԫ�š��������ڡ�״̬�š�������������ѯ�������Ա��༭����ҵָ��"+dao_Instruction.getInstructionByProUnitProDateStateOrder(ProduceUnitID,str_date,workOrder,stateid));
			ResultSet rs  = stmt.executeQuery(dao_Instruction.getInstructionByProUnitProDateStateOrder(ProduceUnitID,str_date,workOrder,stateid));
	        while(rs.next()){
	            if(++i <= LockNum){
	                list.add(rs.getInt(1));
	            }
	        }
	        if (stmt != null) {
				stmt.close();
			}
	        return list;
	   }

	   
	   /**
	     * ͨ��������Ԫ�š��������ڡ�˳��Ŵ����׸��Ǳ༭��������int_delete�ֶ�ֵΪ1
	     *  Ԭ��
	     * @param ProduceUnitID
	     * @param str_date
	     * @param UnlockStartOrder
	     * @return 
	     */
	    public void issuanceInstruction (int id, Connection con) throws SQLException{
	        DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
	                DataBaseType.getDataBaseType(con),DAO_Instruction.class);
	        Statement stmt = con.createStatement();
	        log.debug("ͨ��������Ԫ�š��������ڡ�˳��Ŵ����׸��Ǳ༭��������int_delete�ֶ�ֵΪ1"+dao_Instruction.issuanceInstruction(id));
	        stmt.execute(dao_Instruction.issuanceInstruction(id));
	        if(stmt != null)
	            stmt.close();
	    }
	    
	    /**
	     * ͨ��������Ԫ�š��������ڡ���Ʒ��ʶ��ѯ��¼����
	     *  Ԭ��
	     * @param ProduceUnitID
	     * 			������Ԫ��
	     * @param str_date
	     * 			��������
	     * @param marker
	     * 			��Ʒ��ʶ
	     * @return ����ͨ��������Ԫ�š��������ڡ���β�ѯ��¼������
	     */
	    public int getCountByProUnitDateWorkorderMarker(int ProduceUnitID, String str_date,String workOrder,String marker, Connection con){
	    	int count = 0;
	    	try {
	    	DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con),DAO_Instruction.class);
			
	 		Statement stmt = con.createStatement();
	 		 log.debug("ͨ��������Ԫ�š��������ڡ���Ρ���Ʒ��ʶ��ѯ��¼����"+dao_Instruction.getCountByProUnitDateWorkorderMarker(ProduceUnitID, str_date,workOrder,marker));
	 		ResultSet rs  = stmt.executeQuery(dao_Instruction.getCountByProUnitDateWorkorderMarker(ProduceUnitID, str_date,workOrder,marker));
	         if(rs.next()){
	             count = rs.getInt(1);
	         }
	         if (stmt != null) {
	  			stmt.close();
	  		}
	         
	    	} catch (SQLException e) {
				e.printStackTrace();
			}
	         return count;
	    }
	    
	    /**
	     * ͨ��������Ԫ�š��������ڲ�ѯ��¼����
	     *  Ԭ��
	     * @param ProduceUnitID
	     * 			������Ԫ��
	     * @param str_date
	     * 			��������
	     * @return ����ͨ��������Ԫ�š��������ڲ�ѯ��¼������
	     */
	    public int getCountByProUnitDateworkOrder(int ProduceUnitID, String str_date, String workOrder,Connection con){
	    	int count = 0;
	    	try {
	    	DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con),DAO_Instruction.class);
			
	 		Statement stmt = con.createStatement();
	 		log.debug(" ͨ��������Ԫ�š��������ڡ�˳��Ŵ����׸��Ǳ༭��������int_delete�ֶ�ֵΪ1"+dao_Instruction.getCountByProUnitDateOrder(ProduceUnitID, str_date,workOrder));
	 		ResultSet rs  = stmt.executeQuery(dao_Instruction.getCountByProUnitDateOrder(ProduceUnitID, str_date,workOrder));
	         if(rs.next()){
	             count = rs.getInt(1);
	         }
	         if (stmt != null) {
	  			stmt.close();
	  		}
	    	} catch (SQLException e) {
				e.printStackTrace();
			}
	         return count;
	    }
	    
	    /**
	     * ��ѯ��������Ԫ���������ڣ����״̬�ŵ�ָ�����
	     * 
	     * @param ProduceUnitID ������Ԫ��
	     * @param str_date	��������
	     * @param list_state	״̬�б�
	     * @param con	���Ӷ���
	     * @return list<Instruction>ָ���б�
	     * @throws SQLException
	     * @throws ParseException
	     */
	    public List<Instruction> getCountByProUnitDateState (int ProduceUnitID,List<Integer> list_state,Connection con) throws SQLException, ParseException{
	    	List<Instruction> list_instruction = new ArrayList<Instruction>();
	        DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
	                DataBaseType.getDataBaseType(con),DAO_Instruction.class);
	        Statement stmt = con.createStatement();
	        log.debug("��ѯ��������Ԫ���������ڣ����״̬�ŵ�ָ�����"+dao_Instruction.getCountByProUnitDateState(ProduceUnitID,list_state));
	        ResultSet rs = stmt.executeQuery(dao_Instruction.getCountByProUnitDateState(ProduceUnitID,list_state));
	        while(rs.next()){
	        	Instruction instruction = new Instruction();
	        	instruction.setId(rs.getInt("INT_ID"));
	            instruction.setProdunitid(rs.getInt("INT_PRODUNITID"));
	            instruction.setProduceDate(rs.getDate("DAT_PRODUCEDATE"));
	            instruction.setVersionCode(rs.getString("STR_VERSIONCODE"));
	            instruction.setInstructionOrder(rs.getInt("INT_INSTRUCTORDER"));
	            instruction.setPlanDate(rs.getString("DAT_PLANDATE")==null?null:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("DAT_PLANDATE")));
	            instruction.setPlanOrder(rs.getInt("INT_PLANORDER"));
	            instruction.setProduceType(rs.getString("STR_PRODUCETYPE"));
	            instruction.setProduceName(rs.getString("STR_PRODUCENAME"));
	            instruction.setProduceMarker(rs.getString("STR_PRODUCEMARKER"));
	            instruction.setWorkOrder(rs.getString("STR_WORKORDER"));
	            instruction.setWorkTeam(rs.getString("STR_WORKTEAM"));
	            instruction.setPlanBegin(rs.getString("TIM_PLANBEGIN")==null?null:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("TIM_PLANBEGIN")));
	            instruction.setPlanFinish(rs.getString("TIM_PLANFINISH")==null?null:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("TIM_PLANFINISH")));
	            instruction.setCount(rs.getInt("INT_COUNT"));
	            instruction.setInstructStateID(rs.getInt("INT_INSTRUCTSTATEID"));
	            instruction.setIssuance(rs.getInt("INT_ISSUANCE"));
	            instruction.setStaError(rs.getInt("INT_STAERROR"));
	            instruction.setDelete(rs.getInt("INT_DELETE"));
	            instruction.setEdit(rs.getInt("INT_EDIT"));
	            list_instruction.add(instruction);
	        }
	        if(stmt != null)
	            stmt.close();
	        return list_instruction;
	    }

	    /**
	     * �޸�ָ��״̬
	     * л����
	     * @param structStateID ״̬id
	     *  @param staError ״̬��ת���쳣��ʶ
	     *  @param producemarker ��Ʒ��ʶ
	     * @return  
	     */
	    public void updateInstructState(int structStateID,int staError,String producemarker,int produnitid,Connection con)	throws SQLException {
			DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
	                DataBaseType.getDataBaseType(con),DAO_Instruction.class); 
			Statement stmt = con.createStatement();
			log.debug("�޸�ָ��״̬"+dao_Instruction.updateInstructState(structStateID, staError, producemarker,produnitid ));
			stmt.executeUpdate(dao_Instruction.updateInstructState(structStateID, staError, producemarker,produnitid ));

			if (stmt != null) {
				stmt.close();
				
			}
	    }
	    
	    /**
	     * �鿴�����ϵ�ǰָ��״̬
	     * л����
	     * @param str_produceMarker ��Ʒ��ʶ
	     * @param produnitid
	     * @param con
	     * @return
	     * @throws SQLException
	     */
	   public int checkstr_produceMarker(String str_produceMarker,int produnitid,Connection con)throws SQLException {
	    	DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
	                DataBaseType.getDataBaseType(con),DAO_Instruction.class); 
			Statement stmt = con.createStatement();
			log.debug("�鿴�����ϵ�ǰָ��״̬"+dao_Instruction.checkstr_produceMarker(str_produceMarker,produnitid));
			ResultSet rs=stmt.executeQuery(dao_Instruction.checkstr_produceMarker(str_produceMarker,produnitid));
			int Int_instrucsttateid=0;
			if(rs.next())
				Int_instrucsttateid=rs.getInt(1);
			if (stmt != null) {
				stmt.close();
			}
			return Int_instrucsttateid;
	    }
	    /**
	     * ָ��״̬�����쳣ʱҪ������ֵ�����
	     * л����
	     * 
	     *  @param producemarker ��Ʒ��ʶ
	     *   @param userId�û�id
	     *    @param Int_instructionStateID״̬id
	     *     @param  �ɼ���id
	     * @return  �������Ӧ��ָ��
	     */
	    public void savet_ra_Instructionexception(int Int_instructionStateID,int userId,String produceMarker,int INT_GATHERID,Connection con)throws SQLException {
	    	DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
	                DataBaseType.getDataBaseType(con),DAO_Instruction.class); 
			Statement stmt = con.createStatement();
			log.debug("ָ��״̬�����쳣ʱҪ������ֵ�����"+dao_Instruction.savet_ra_Instructionexception(Int_instructionStateID, userId, produceMarker, INT_GATHERID));
			stmt.executeUpdate(dao_Instruction.savet_ra_Instructionexception(Int_instructionStateID, userId, produceMarker, INT_GATHERID));

			if (stmt != null) {
				stmt.close();
			}
	    	
	    }
	    /**
	     * * �鿴��Ʒ��ʶ���ҳ�������������ֵ��ָ����п��Ƿ���ڡ�
	     * л����
	     * @param producemarker
	     * @param con
	     * @return
	     * @throws SQLException
	     */

	    public boolean checkmaterielValue(String producemarker,Connection con) throws SQLException {
	    
	    	DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
	                DataBaseType.getDataBaseType(con),DAO_Instruction.class); 
			Statement stmt = con.createStatement();
			log.debug("�鿴��Ʒ��ʶ���ҳ�������������ֵ��ָ����п��Ƿ����"+dao_Instruction.checkmaterielValue(producemarker));
			ResultSet rs=stmt.executeQuery(dao_Instruction.checkmaterielValue(producemarker));
              boolean f=false;
              if(rs.next()){
            	  f=true;
              }
  			if (stmt != null) {
  				stmt.close();
  			}
  	      
			return f;
	       }
	    /**
	     * ��������ʱ���������Ԫ�鿴��ص�ָ�
	     * л����
	     * @param int_produnitid
	     * @param str_date
	     * @param workOrder
	     * @param con
	     * @return �������Ӧ��ָ��
	     * @throws SQLException
	     * @throws ParseException
	     */
	     
	    
	   public  List<Instruction> getInstructionByProduceUnitProduceDateWorkorder(int int_produnitid,String str_date,String workOrder,Connection con) throws SQLException ,ParseException{
	      
	       List<Instruction> list = new ArrayList<Instruction>();
	       DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
	               DataBaseType.getDataBaseType(con),DAO_Instruction.class);
	    
			Statement stmt = con.createStatement();
			log.debug("��������ʱ���������Ԫ�Ͱ�β鿴��ص�ָ��"+dao_Instruction.getInstructionByProduceUnitProduceDateWorkorder(int_produnitid, str_date,workOrder));
			ResultSet rs = stmt.executeQuery(dao_Instruction.getInstructionByProduceUnitProduceDateWorkorder(int_produnitid, str_date,workOrder));
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
	            instruction.setPlanBegin(rs.getDate("TIM_PLANBEGIN"));
	            instruction.setPlanFinish(rs.getDate("TIM_PLANFINISH"));
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
	    * ����ָ��汾
	    * �������µİ汾ʱ����
	    * ����ָ����еİ汾�ֶ�
	    * л����
	    * @param instuction
	    * @param str_versioncode
	    * @param con
	    * @throws SQLException
	    * @throws ParseException
	    */
	   
	   public void updateInstructionVersioncode(Instruction instuction,String str_versioncode,Connection con)throws SQLException ,ParseException{
	       DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
	               DataBaseType.getDataBaseType(con),DAO_Instruction.class);
			Statement stmt = con.createStatement();
			log.debug("����ָ��汾"+dao_Instruction.updateInstructionVersioncode(instuction, str_versioncode));
			stmt.executeUpdate(dao_Instruction.updateInstructionVersioncode(instuction, str_versioncode));
			if (stmt != null) {
				stmt.close();
			}
	   }
	   /**
	    *  * �˶��Ƿ���Խ��а汾����
	    * л����
	    * ����������Ԫ���������ڼ�������Ԫ��δ����ָ��״̬
	    * @param int_produnitid
	    * @param str_date
	    * @param workOrder
	    * @param con
	    * @return
	    * @throws SQLException
	    * @throws ParseException
	    */
	   public boolean  checksaveVersion(int int_produnitid,String str_date,String workOrder,Connection con)throws SQLException ,ParseException{
	       DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
	               DataBaseType.getDataBaseType(con),DAO_Instruction.class);
			Statement stmt = con.createStatement();
			log.debug("�˶��Ƿ���Խ��а汾����"+dao_Instruction.checksaveVersion(int_produnitid, str_date,workOrder));
			ResultSet rs=	stmt.executeQuery(dao_Instruction.checksaveVersion(int_produnitid, str_date,workOrder));
			boolean f=false;
			if(rs.next()){
				f=true;
			}
			  if (stmt != null) {
					stmt.close();	
				}
				return f;
	   }

	   /**
	    * * ��������ʱ���������Ԫ�ͻָ�״̬�鿴��ص�ָ����а汾�ָ�
	    * л����
	    * @param int_produnitid
	    * @param str_date
	    * @param int_stateid
	    * @param con
	    * @return
	    * @throws SQLException
	    * @throws ParseException
	    */

	   public boolean  comebackVersion(int int_produnitid,String str_date,int int_stateid,Connection con)throws SQLException ,ParseException{
	       DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
	               DataBaseType.getDataBaseType(con),DAO_Instruction.class);
			Statement stmt = con.createStatement();
			log.debug("��������ʱ���������Ԫ�ͻָ�״̬�鿴��ص�ָ����а汾�ָ�"+dao_Instruction.comebackVersion(int_produnitid, str_date, int_stateid));
			ResultSet rs=stmt.executeQuery(dao_Instruction.comebackVersion(int_produnitid, str_date, int_stateid));
			 int n=0;
				if(rs.next()){
					n++;
				}
				if (stmt != null) {
					stmt.close();	
				}
		     
				if(n!=0){
					return false;
				}
				else
					return true;
	   }
	   /**
	    * ��������ʱ���������Ԫɾ��ָ��
	    * л����
	    * @param int_produnitid
	    * @param str_date
	    * @param workOrder
	    * @param con
	    * @throws SQLException
	    * @throws ParseException
	    */

	   public void deleteInstructionByProduceUnitProduceDateWorkorder(int int_produnitid,String str_date,String workOrder,Connection con)throws SQLException ,ParseException{
	       DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
	               DataBaseType.getDataBaseType(con),DAO_Instruction.class);
			Statement stmt = con.createStatement();
			log.debug("��������ʱ���������Ԫ�Ͱ��ɾ��ָ��"+dao_Instruction.deleteInstructionByProduceUnitProduceDateWorkorder(int_produnitid, str_date,workOrder));
			stmt.executeUpdate(dao_Instruction.deleteInstructionByProduceUnitProduceDateWorkorder(int_produnitid, str_date,workOrder));
			if (stmt != null) {
				stmt.close();
			}
	   }
	   /**
	    * ����ָ������˳���
	    * ����ָ��׷��
	    * л����
	    * @param int_produnitid
	    * @param str_date
	    * @param workOrder
	    * @param con
	    * @return
	    * @throws SQLException
	    * @throws ParseException
	    */
	 
	   public int getInstructionMaxOrder(int int_produnitid,String str_date,String workOrder,Connection con)throws SQLException ,ParseException{
	       DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
	               DataBaseType.getDataBaseType(con),DAO_Instruction.class);
			Statement stmt = con.createStatement();
			log.debug("����ָ������˳���"+dao_Instruction.getInstructionMaxOrder(int_produnitid, str_date, workOrder));
			ResultSet rs=	stmt.executeQuery(dao_Instruction.getInstructionMaxOrder(int_produnitid, str_date, workOrder));
			 int n=1;
			if(rs.next()){
				n=rs.getInt(1);
			}
			if (stmt != null) {
				stmt.close();
			}
				return n;
	   }
	   /**
	    * л����
	    * �鿴ָ������Ƿ����������Ԫ���������ڵ�ָ��
	    * @param int_produnitid
	    * @param str_date
	    * @param con
	    * @return
	    * @throws SQLException
	    * @throws ParseException
	    */
	   public boolean checkByProduceUnitProduceDateWorkorder(int int_produnitid,String str_date,String workOrder,Connection con) throws SQLException ,ParseException{
	      
	  
	       DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
	               DataBaseType.getDataBaseType(con),DAO_Instruction.class);
	    
			Statement stmt = con.createStatement();
			log.debug("�鿴ָ������Ƿ����������Ԫ���������ڡ���ε�ָ��"+dao_Instruction.getInstructionByProduceUnitProduceDateWorkorder(int_produnitid, str_date,workOrder));
			ResultSet rs = stmt.executeQuery(dao_Instruction.getInstructionByProduceUnitProduceDateWorkorder(int_produnitid, str_date,workOrder));
			boolean f=false;
			if(rs.next()){
				f=true;
			}
			if (stmt != null) {
				stmt.close();
			}
	        return f;
	   }
	   /**
	    * * �鿴������Ԫ���������ڵ�������ֵ
	    * ����ָ���ʱ�ж�
	    * л����
	    * @param produceid
	    * @param str_date
	    * @param con
	    * @return
	    * @throws SQLException
	    * @throws ParseException
	    */

	   public  List<Instruction> getStr_produceMarkerbyproduceUnitandproduceDate(int produceid,String str_date,Connection con) throws SQLException ,ParseException{
	       List<Instruction> list = new ArrayList<Instruction>();
	       DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
	               DataBaseType.getDataBaseType(con),DAO_Instruction.class);
			Statement stmt = con.createStatement();
			log.debug("�鿴������Ԫ���������ڵ�������ֵ"+dao_Instruction.getStr_produceMarkerbyproduceUnitandproduceDate(produceid, str_date));
			ResultSet rs = stmt.executeQuery(dao_Instruction.getStr_produceMarkerbyproduceUnitandproduceDate(produceid, str_date));
	        while(rs.next()){
	        	 Instruction instruction = new Instruction();
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
		         instruction.setPlanBegin(rs.getDate("TIM_PLANBEGIN"));
		         instruction.setPlanFinish(rs.getDate("TIM_PLANFINISH"));
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
	 public boolean checkproduceandproducedateWorkorder(int produceid,String workOrder,Connection con)throws SQLException ,ParseException{
	     
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
	 
	     DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
	             DataBaseType.getDataBaseType(con),DAO_Instruction.class);
			Statement stmt = con.createStatement();
			log.debug("�鿴ָ����ʷ�����Ƿ�ʹ��������Ԫ"+dao_Instruction.getInstructionByProduceUnitProduceDateWorkorder(produceid, str_date,workOrder));
			ResultSet rs = stmt.executeQuery(dao_Instruction.getInstructionByProduceUnitProduceDateWorkorder(produceid, str_date,workOrder));
			boolean f=false;
			if(rs.next()){
				f=true;
			}
			if (stmt != null) {
				stmt.close();
			}
	      return f;
	 } 
	  /**
	    * л���� �鿴ָ������Ƿ�ʹ��������Ԫ
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
	 
	     DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
	             DataBaseType.getDataBaseType(con),DAO_Instruction.class);
			Statement stmt = con.createStatement();
			log.debug("�鿴ָ����ʷ�����Ƿ�ʹ��������Ԫ"+dao_Instruction.getInstructionByProduceUnitProduceDate(produceid, str_date));
			ResultSet rs = stmt.executeQuery(dao_Instruction.getInstructionByProduceUnitProduceDate(produceid, str_date));
			boolean f=false;
			if(rs.next()){
				f=true;
			}
			if (stmt != null) {
				stmt.close();
			}
	    
	      return f;
	 } 
	 /**
	  * 
	  *  л����
	  *  �鿴ָ����е����������Ԫ���������ڵ�ָ��˳���
	  * @param int_produnitid
	  * @param str_date
	  * @param con
	  * @return
	  * @throws SQLException
	  * @throws ParseException
	  */
	 public  List<String > getallInstructionorder(int int_produnitid,String str_date,String workOrder,Connection con) throws SQLException ,ParseException{
	     List<String> list = new ArrayList<String>();
	     DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
	             DataBaseType.getDataBaseType(con),DAO_Instruction.class);
			Statement stmt = con.createStatement();
			log.debug("�鿴ָ����е����������Ԫ���������ڵ�ָ��˳���"+dao_Instruction.checkAllInstructionByProdunitidDateWorkorder(int_produnitid, str_date,workOrder));
			ResultSet rs = stmt.executeQuery(dao_Instruction.checkAllInstructionByProdunitidDateWorkorder(int_produnitid, str_date,workOrder));
		    int n=0;
	      while(rs.next()){
	    	  n=rs.getInt("int_instructorder");
	    	  list.add(String.valueOf(n));
	      }
		if (stmt != null) {
			stmt.close();
		}
	      return list;
	   }
	 /**
	  *  * л����
	  * ������Ԫ������ʱ��
	  * ͨ�������ϵ�ֵ�鿴ָ���Ƿ��ڱ༭״̬��
	  * @param producemarker
	  * @param int_produnitid
	  * @param con
	  * @return
	  * @throws SQLException
	  * @throws ParseException
	  */
	  public  boolean checkinstructionedit(String producemarker,int int_produnitid,Connection con) throws SQLException ,ParseException{
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
		 
		     DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
		             DataBaseType.getDataBaseType(con),DAO_Instruction.class);
		  
				Statement stmt = con.createStatement();
				log.debug("������Ԫ������ʱ��"+dao_Instruction.checkinstructionedit(producemarker, int_produnitid, str_date));
				ResultSet rs = stmt.executeQuery(dao_Instruction.checkinstructionedit(producemarker, int_produnitid, str_date));
				boolean f=false;
				if(rs.next()){
					if(rs.getInt("int_edit")==1){
					f=true;
					}
				}
				if (stmt != null) {
					stmt.close();
				}
		   
		      return f;
	  }
	  
	  /**
		  * �鿴ָ����е����������Ԫ��ָ�����
		  * 
		  * @param int_produnitid
		  * @param con
		  * @return
		  * @throws SQLException
		  * @throws ParseException
		  */
		 public  List<Instruction > getInstructionsbyproduceUnit(int int_produnitid,Connection con) throws SQLException ,ParseException{
		     List<Instruction> list = new ArrayList<Instruction>();
		     DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
		             DataBaseType.getDataBaseType(con),DAO_Instruction.class);
				Statement stmt = con.createStatement();
				log.debug("�鿴ָ����е����������Ԫ��ָ�����"+dao_Instruction.getInstructionsbyproduceUnit(int_produnitid));
				ResultSet rs = stmt.executeQuery(dao_Instruction.getInstructionsbyproduceUnit(int_produnitid));
			    while(rs.next()){
		        	 Instruction instruction = new Instruction();
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
			         instruction.setPlanBegin(rs.getDate("TIM_PLANBEGIN"));
			         instruction.setPlanFinish(rs.getDate("TIM_PLANFINISH"));
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
		  * �鿴ָ����еĸ�������Ԫ�Ͳ�ͬ�������ڵ�ָ�����
		  *  
		  * @param int_produnitid
		  * @param str_date
		  * @param con
		  * @return
		  * @throws SQLException
		  * @throws ParseException
		  */
		 public  List<Instruction > getInstructionsByProdunitOtherProdDate(int int_produnitid,String str_date,Connection con) throws SQLException ,ParseException{
		     List<Instruction> list = new ArrayList<Instruction>();
		     DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
		             DataBaseType.getDataBaseType(con),DAO_Instruction.class);
				Statement stmt = con.createStatement();
				log.debug("�鿴ָ����еĸ�������Ԫ�Ͳ�ͬ�������ڵ�ָ�����"+dao_Instruction.getInstructionsByProdunitOtherProdDate(int_produnitid, str_date));
				ResultSet rs = stmt.executeQuery(dao_Instruction.getInstructionsByProdunitOtherProdDate(int_produnitid, str_date));
		      while(rs.next()){
		        	 Instruction instruction = new Instruction();
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
			         instruction.setPlanBegin(rs.getDate("TIM_PLANBEGIN"));
			         instruction.setPlanFinish(rs.getDate("TIM_PLANFINISH"));
			         instruction.setCount(rs.getInt("INT_COUNT"));
			         instruction.setInstructStateID(rs.getInt("INT_INSTRUCTSTATEID"));
			         list.add(instruction);
		      }
			if (stmt != null) {
				stmt.close();
			}
		      return list;
		   }

		 /**�õ�ָ��������е�ָ�� л����
		  * 
		  * @param con
		  * @return
		  * @throws SQLException
		  * @throws ParseException
		  */
		public List<Instruction > getallInstruction(Connection con) throws SQLException ,ParseException{
			List<Instruction> list = new ArrayList<Instruction>();
			DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
			DataBaseType.getDataBaseType(con),DAO_Instruction.class);
			Statement stmt = con.createStatement();
			log.debug("�õ�ָ��������е�ָ�� "+dao_Instruction.getallInstruction());
			ResultSet rs = stmt.executeQuery(dao_Instruction.getallInstruction());
			while(rs.next()){
				Instruction instruction = new Instruction();
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
				instruction.setPlanBegin(rs.getDate("TIM_PLANBEGIN"));
				instruction.setPlanFinish(rs.getDate("TIM_PLANFINISH"));
				instruction.setCount(rs.getInt("INT_COUNT"));
				instruction.setInstructStateID(rs.getInt("INT_INSTRUCTSTATEID"));
				list.add(instruction);
			}
			if (stmt != null) {
				stmt.close();
			}
			return list; 
							 
		}
		
		/** ����ָ�������ϵ�ָ�� л����
		* 
		* @param materile
		* @param produceid
		* @param con
		* @return
		* @throws SQLException
		* @throws ParseException
		*/
		public Instruction getInstructionbymaterile( String materile,int produceid,Connection con) throws SQLException ,ParseException{
			Instruction instruction = new Instruction();
			DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
			DataBaseType.getDataBaseType(con),DAO_Instruction.class);
			Statement stmt = con.createStatement();
			log.debug("����ָ�������ϵ�ָ��  "+dao_Instruction.getInstructionbymaterile(materile,produceid));
			ResultSet rs = stmt.executeQuery(dao_Instruction.getInstructionbymaterile(materile,produceid));
			if(rs.next()){
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
				instruction.setPlanBegin(rs.getDate("TIM_PLANBEGIN"));
				instruction.setPlanFinish(rs.getDate("TIM_PLANFINISH"));
				instruction.setCount(rs.getInt("INT_COUNT"));
				instruction.setInstructStateID(rs.getInt("INT_INSTRUCTSTATEID"));
					           
			}
			if (stmt != null) {
				stmt.close();
			}
			return instruction; 
		}
		/** ����ָ������Ȳ嵥ʱ�õ��ƻ���vinʱ����
		* @param instruction
		* @param con
		* @throws SQLException
		*/
		public void updateInstruction(Instruction instruction,Connection con) throws SQLException {	
			  DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
			             DataBaseType.getDataBaseType(con),DAO_Instruction.class);
					Statement stmt = con.createStatement();
					stmt.executeUpdate(dao_Instruction.updateInstruction(instruction));

					if (stmt != null) {
						stmt.close();
					}
		}
				  
	
		/** ������
		 * ͨ��������Ԫ����Σ�����ɾ��ָ���
		 * @param int_produnitid
		 * @param str_date
		 * @param workOrder
		 * @param con
		 * @throws SQLException 
		 */
		public void deleteAllInstructionByProdunitidDateWorkorder(int int_produnitid,String str_date,String workOrder,Connection con) throws SQLException {
			DAO_Instruction dao_instruction = (DAO_Instruction) DAOFactoryAdapter.getInstance(
					DataBaseType.getDataBaseType(con),DAO_Instruction.class);
			Statement stmt = con.createStatement();
			stmt.execute(dao_instruction.deleteInstructionByProduceUnitProduceDateWorkorder(int_produnitid,str_date,workOrder));
			log.debug("ͨ��ͨ��������Ԫ���������ڣ����ɾ��ָ���SQL��䣺"+dao_instruction.deleteInstructionByProduceUnitProduceDateWorkorder(int_produnitid,str_date,workOrder));
			if (stmt != null) {
				stmt.close();
			}
		}
		
		 /**
		  * * �˶�ָ������Ƿ���ָ������ɾ��
		    * ������
		    * ����������Ԫ���������ڼ����
		  * @param int_produnitid
		  * @param str_date
		  * @param workOrder
		  * @param con
		  * @return
		  * @throws SQLException
		  * @throws ParseException
		  */
		   public boolean  checkAllInstructionByProdunitidDateWorkorder(int int_produnitid,String str_date,String workOrder,Connection con)throws SQLException ,ParseException{
		       DAO_Instruction dao_Instruction = (DAO_Instruction)DAOFactoryAdapter.getInstance(
		               DataBaseType.getDataBaseType(con),DAO_Instruction.class);
				Statement stmt = con.createStatement();
				log.debug("�˶�ָ������Ƿ���ָ������ɾ��"+dao_Instruction.checkAllInstructionByProdunitidDateWorkorder(int_produnitid, str_date,workOrder));
				ResultSet rs=stmt.executeQuery(dao_Instruction.checkAllInstructionByProdunitidDateWorkorder(int_produnitid, str_date,workOrder));
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
