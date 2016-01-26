package com.qm.mes.os.factory;
import java.sql.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.DataBaseType;
import com.qm.mes.os.bean.*;
import com.qm.mes.os.dao.*;
import com.qm.mes.system.dao.DAOFactoryAdapter;
/**
 * 
 * @author л���� 2009-5-13
 *
 */

public class MPSPlanFactory {
	private final Log log = LogFactory.getLog(MPSPlanFactory.class);  
   /**�������ƻ� л���� 
 * @param mpsplan
 * @param con
 * @throws SQLException
 */
	public void	saveMPSPlan(MPSPlan mpsplan,Connection con)throws SQLException{
		DAO_MPSPlan dao_mpsplan = (DAO_MPSPlan) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_MPSPlan.class);
		Statement stmt = con.createStatement();
		log.debug("�������ƻ�"+dao_mpsplan.saveMPSPlan(mpsplan));
		stmt.executeUpdate(dao_mpsplan.saveMPSPlan(mpsplan));
		if(stmt!=null)
		{
			stmt.close();
		}
		
   }
  
   /**ɾ�����滻��ָ���ƻ����ڵ����ƻ�
 * @param startDate
 * @param con
 * @throws SQLException
 */
	public void deleteMPSPlanbystartDate(String startDate,Connection con)throws SQLException{
	  
	   DAO_MPSPlan dao_mpsplan = (DAO_MPSPlan) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_MPSPlan.class);
		Statement stmt = con.createStatement();
		log.debug("ɾ�����滻��ָ���ƻ����ڵ����ƻ�"+dao_mpsplan.deleteMPSPlanbystartDate(startDate));
		stmt.executeUpdate(dao_mpsplan.deleteMPSPlanbystartDate(startDate));
		if(stmt!=null)
		{
			stmt.close();
		}
		
   }
 
	 /**�鿴ĳһ�����ƻ�ͨ�����ƻ��� л����
	 * @param id
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public  MPSPlan getMPSPlanbyid(int id,Connection con)throws SQLException{
			DAO_MPSPlan dao_mpsplan = (DAO_MPSPlan) DAOFactoryAdapter.getInstance(
					DataBaseType.getDataBaseType(con), DAO_MPSPlan.class);
			Statement stmt = con.createStatement();
			log.debug("�鿴ĳһ�����ƻ�ͨ�����ƻ���"+dao_mpsplan.getMPSPlanbyid(id));
			ResultSet rs=stmt.executeQuery(dao_mpsplan.getMPSPlanbyid(id));
			MPSPlan mpsplan=new MPSPlan();
			while(rs.next()){
				  mpsplan.setId(rs.getInt("int_id"));
                  mpsplan.setStartDate(rs.getDate("dat_startDate"));
                  mpsplan.setMpsUnit(rs.getString("str_mpsUnit"));
                  mpsplan.setMaterielName(rs.getString("str_materielName"));
                  mpsplan.setPlanAmount(rs.getInt("int_planAmount"));
                  mpsplan.setIntendStorage(rs.getInt("int_intendStorage"));
                  mpsplan.setPlanType(rs.getString("str_planType"));
                  mpsplan.setVersion(rs.getString("str_version"));
                  mpsplan.setUserName(rs.getString("str_user"));
                  mpsplan.setContractCode(rs.getString("str_contractcode"));
			}
			if(stmt!=null)
			{
				stmt.close();
			}
			
		return mpsplan;
	 }
	 
	 /**�������ƻ� ͨ��idֵ л����
	 * @param mpsplan
	 * @param con
	 * @throws SQLException
	 */
	public   void updateMPSPlanbyid(MPSPlan mpsplan ,Connection con)throws SQLException{
			DAO_MPSPlan dao_mpsplan = (DAO_MPSPlan) DAOFactoryAdapter.getInstance(
					DataBaseType.getDataBaseType(con), DAO_MPSPlan.class);
			Statement stmt = con.createStatement();
			log.debug("�������ƻ� ͨ��idֵ"+dao_mpsplan.updateMPSPlanbyid(mpsplan));
			stmt.executeUpdate(dao_mpsplan.updateMPSPlanbyid(mpsplan));
			if(stmt!=null)
			{
				stmt.close();
			}
			
			
	 }
	
		/**ɾ�����ƻ� ͨ��idֵ л����
		 * @param int_id
		 * @param con
		 * @throws SQLException
		 */
		public  void  deleteMPSPlanbyid(int int_id,Connection con)throws SQLException{
			DAO_MPSPlan dao_mpsplan = (DAO_MPSPlan) DAOFactoryAdapter.getInstance(
					DataBaseType.getDataBaseType(con), DAO_MPSPlan.class);
			Statement stmt = con.createStatement();
			log.debug("ɾ�����ƻ� ͨ��idֵ"+dao_mpsplan.deleteMPSPlanbyid(int_id));
			stmt.executeUpdate(dao_mpsplan.deleteMPSPlanbyid(int_id));
			if(stmt!=null)
			{
				stmt.close();
			}
			
		}
}
