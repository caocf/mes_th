package com.qm.mes.os.factory;

import java.util.*;
import java.sql.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.DataBaseType;
import com.qm.mes.os.bean.Plan;
import com.qm.mes.os.dao.DAO_Plan;
import com.qm.mes.system.dao.DAOFactoryAdapter;
/**
 * 
 * @author л����2009-5-15
 *  �ƻ�����
 */


public class PlanFactory {
	private final Log log = LogFactory.getLog(PlanFactory.class);
	
	 /**������ҵ�ƻ�  л����
	 * @param plan
	 * @param con
	 * @throws SQLException
	 */
	public  void savePlan(Plan plan,Connection con)throws SQLException{
		 DAO_Plan dao_plan = (DAO_Plan) DAOFactoryAdapter.getInstance(
					DataBaseType.getDataBaseType(con), DAO_Plan.class);
			Statement stmt = con.createStatement();
			//log.debug("������ҵ�ƻ�"+dao_plan.savePlan(plan));
			stmt.executeUpdate(dao_plan.savePlan(plan));
			if(stmt!=null)
			{
				stmt.close();
			}
			
	 }

	/**����������Ԫ���������ڣ��Ͱ�β�����ҵ�ƻ� л����  ����ҵ�ƻ�����ʱ���û�ȡ�汾��
	* @param produceDate
	* @param produnitid
	* @param workOrder
	* @param con
	* @return
	* @throws SQLException
	*/
	public List<Plan> getPlanbyProdateProidWorder(String produceDate,int produnitid,String workOrder,Connection con)throws SQLException{
		DAO_Plan dao_plan = (DAO_Plan) DAOFactoryAdapter.getInstance(
						DataBaseType.getDataBaseType(con), DAO_Plan.class);
		Statement stmt = con.createStatement();
		log.debug("����������Ԫ���������ڣ��Ͱ�β�����ҵ�ƻ�"+dao_plan.getPlanbyProdateProidWorder(produceDate, produnitid,workOrder));
		ResultSet rs=stmt.executeQuery(dao_plan.getPlanbyProdateProidWorder(produceDate, produnitid,workOrder));
		List<Plan> planlist=new ArrayList<Plan>();
		while(rs.next()){
			Plan plan =new Plan();
			     plan.setId(rs.getInt("int_id"));
	             plan.setPlanDate(rs.getDate("dat_planDate"));
	             plan.setProduceDate(rs.getDate("dat_produceDate"));
	             plan.setOrderFormId(rs.getString("str_orderFormId"));
	             plan.setPlanGroupId(rs.getInt("int_planGroupId"));
	             plan.setProduceType(rs.getString("str_produceType"));
	             plan.setProduceName(rs.getString("str_produceName"));
	             plan.setProduceMarker(rs.getString("str_produceMarker"));
	             plan.setProdunitid(rs.getInt("int_produnitid"));
	             plan.setWorkTeam(rs.getString("str_workTeam"));
	             plan.setWorkOrder(rs.getString("str_workOrder"));
	             plan.setAmount(rs.getInt("int_count"));
	             plan.setVersioncode(rs.getString("str_versioncode"));
	             plan.setUpload(rs.getInt("int_upload"));
	             plan.setPlanOrder(rs.getInt("int_planOrder"));
	             plan.setDescription(rs.getString("str_description"));
	             planlist.add(plan);
		}
		if(stmt!=null)
		{
			stmt.close();
		}
		
		return planlist;
	}
		 
	/**ͨ���汾�ŵõ���ҵ�ƻ�  л����
	* @param versioncode
	* @param con
	* @return
	* @throws SQLException
	*/
	public List<Plan> getPlanbyversioncord(String versioncode,Connection con)throws SQLException{
		DAO_Plan dao_plan = (DAO_Plan) DAOFactoryAdapter.getInstance(
						DataBaseType.getDataBaseType(con), DAO_Plan.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ���汾�ŵõ���ҵ�ƻ�"+dao_plan.getPlanbyversioncord(versioncode));
		ResultSet rs=stmt.executeQuery(dao_plan.getPlanbyversioncord(versioncode));
		List<Plan> planlist=new ArrayList<Plan>();
		while(rs.next()){
			Plan plan =new Plan();
			     plan.setId(rs.getInt("int_id"));
	             plan.setPlanDate(rs.getDate("dat_planDate"));
	             plan.setProduceDate(rs.getDate("dat_produceDate"));
	             plan.setOrderFormId(rs.getString("str_orderFormId"));
	             plan.setPlanGroupId(rs.getInt("int_planGroupId"));
	             plan.setProduceType(rs.getString("str_produceType"));
	             plan.setProduceName(rs.getString("str_produceName"));
	             plan.setProduceMarker(rs.getString("str_produceMarker"));
	             plan.setProdunitid(rs.getInt("int_produnitid"));
	             plan.setWorkTeam(rs.getString("str_workTeam"));
	             plan.setWorkOrder(rs.getString("str_workOrder"));
	             plan.setAmount(rs.getInt("int_count"));
	             plan.setVersioncode(rs.getString("str_versioncode"));
	             plan.setUpload(rs.getInt("int_upload"));
	             plan.setPlanOrder(rs.getInt("int_planOrder"));
	             plan.setDescription(rs.getString("str_description"));
	             planlist.add(plan);
		}
		if(stmt!=null)
		{
			stmt.close();
		}
		
		return planlist;
	}
		
	/**ɾ��ָ���汾�ŵ���ҵ�ƻ� л����
	* @param versioncode
	* @param con
	* @throws SQLException
	*/
	public  void deletePlanbyversioncode(String versioncode,Connection con)throws SQLException{
		DAO_Plan dao_plan = (DAO_Plan) DAOFactoryAdapter.getInstance(
						DataBaseType.getDataBaseType(con), DAO_Plan.class);
		Statement stmt = con.createStatement();
		log.debug("ɾ��ָ���汾�ŵ���ҵ�ƻ�"+dao_plan.deletePlanbyversioncode(versioncode));
		stmt.executeUpdate(dao_plan.deletePlanbyversioncode(versioncode));
		if(stmt!=null)
		{
			stmt.close();
		}
		
	}
		
	/**��ȡ��ǰ��������������Ԫ�Ͱ�ε����汾�ƻ�˳��Ű��ս�������
	* @param produceDate
	* @param produnitid
	* @param workOrder
	* @param con
	* @return
	* @throws SQLException
	*/
	public List<Integer> getPlanOrderbyProdateProidWorder(String produceDate,int produnitid,String workOrder,Connection con)throws SQLException{
		DAO_Plan dao_plan = (DAO_Plan) DAOFactoryAdapter.getInstance(
						DataBaseType.getDataBaseType(con), DAO_Plan.class);
		Statement stmt = con.createStatement();
		List<Integer> list=new ArrayList<Integer>();
		log.debug("��ȡ��ǰ��������������Ԫ�Ͱ�ε����汾�ƻ�˳��Ű��ս�������"+dao_plan.getmaxPlanexcepttemp(produceDate,produnitid,workOrder));
		ResultSet rs=stmt.executeQuery(dao_plan.getmaxPlanexcepttemp(produceDate,produnitid,workOrder));
		while(rs.next()){
			list.add(rs.getInt("int_planOrder"));
		}
		if(stmt!=null)
		{
			stmt.close();
		}
		
		return list;
	}
	
	/**ͨ���汾int_id�õ��汾�ƻ���Ϣ ���ռƻ�˳����������� л����
	* @param int_id
	* @param con
	* @return
	* @throws SQLException
	*/
	public   List<Plan> getPlanbyversionid(int int_id,Connection con)throws SQLException{
		DAO_Plan dao_plan = (DAO_Plan) DAOFactoryAdapter.getInstance(
						DataBaseType.getDataBaseType(con), DAO_Plan.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ���汾int_id�õ��汾�ƻ���Ϣ ���ռƻ�˳�����������"+dao_plan.getPlanbyversionid(int_id));
		ResultSet rs=stmt.executeQuery(dao_plan.getPlanbyversionid(int_id));
		List<Plan> planlist=new ArrayList<Plan>();
		while(rs.next()){
			Plan plan =new Plan();
			     plan.setId(rs.getInt("int_id"));
	             plan.setPlanDate(rs.getDate("dat_planDate"));
	             plan.setProduceDate(rs.getDate("dat_produceDate"));
	             plan.setOrderFormId(rs.getString("str_orderFormId"));
	             plan.setPlanGroupId(rs.getInt("int_planGroupId"));
	             plan.setProduceType(rs.getString("str_produceType"));
	             plan.setProduceName(rs.getString("str_produceName"));
	             plan.setProduceMarker(rs.getString("str_produceMarker"));
	             plan.setProdunitid(rs.getInt("int_produnitid"));
	             plan.setWorkTeam(rs.getString("str_workTeam"));
	             plan.setWorkOrder(rs.getString("str_workOrder"));
	             plan.setAmount(rs.getInt("int_count"));
	             plan.setVersioncode(rs.getString("str_versioncode"));
	             plan.setUpload(rs.getInt("int_upload"));
	             plan.setPlanOrder(rs.getInt("int_planOrder"));
	             plan.setDescription(rs.getString("str_description"));
	             planlist.add(plan);
		}
		if(stmt!=null)
		{
			stmt.close();
		}
		
		return planlist;
	}
		
	/**�õ�ָ��id�ļƻ�   л����
	* @param id
	* @param con
	* @return
	* @throws SQLException
	*/
	public   Plan getplanbyid(int id ,Connection con)throws SQLException{
		DAO_Plan dao_plan = (DAO_Plan) DAOFactoryAdapter.getInstance(
						DataBaseType.getDataBaseType(con), DAO_Plan.class);
		Statement stmt = con.createStatement();
		log.debug("�õ�ָ��id�ļƻ�"+dao_plan.getplanbyid(id));
		ResultSet rs=stmt.executeQuery(dao_plan.getplanbyid(id));
		Plan plan =new Plan();
		if(rs.next()){
			plan.setId(rs.getInt("int_id"));
	        plan.setPlanDate(rs.getDate("dat_planDate"));
	        plan.setProduceDate(rs.getDate("dat_produceDate"));
	        plan.setOrderFormId(rs.getString("str_orderFormId"));
	        plan.setPlanGroupId(rs.getInt("int_planGroupId"));
	        plan.setProduceType(rs.getString("str_produceType"));
	        plan.setProduceName(rs.getString("str_produceName"));
	        plan.setProduceMarker(rs.getString("str_produceMarker"));
	        plan.setProdunitid(rs.getInt("int_produnitid"));
	        plan.setWorkTeam(rs.getString("str_workTeam"));
	        plan.setWorkOrder(rs.getString("str_workOrder"));
	        plan.setAmount(rs.getInt("int_count"));
	        plan.setVersioncode(rs.getString("str_versioncode"));
	        plan.setUpload(rs.getInt("int_upload"));
	        plan.setPlanOrder(rs.getInt("int_planOrder"));
	        plan.setDescription(rs.getString("str_description"));
		}
		if(stmt!=null)
		{
			stmt.close();
		}
		
		return plan;
	}
		 //
	
	/**�޸�ָ��idֵ�ļƻ�  л����
	* @param plan
	* @param con
	* @throws SQLException
	*/
	public   void updatePlanbyid(Plan plan,Connection con)throws SQLException{
		DAO_Plan dao_plan = (DAO_Plan) DAOFactoryAdapter.getInstance(
						DataBaseType.getDataBaseType(con), DAO_Plan.class);
		Statement stmt = con.createStatement();
		log.debug("�޸�ָ��idֵ�ļƻ�"+dao_plan.updatePlanbyid(plan));
		stmt.executeUpdate(dao_plan.updatePlanbyid(plan));
		if(stmt!=null)
		{
			stmt.close();
		}
		
	}
   
	/**ɾ��������Ԫ���������ڣ�����Ͱ��,˳��ŵ���ҵ�ƻ� л����
	 * @param produceDate
	 * @param produnitid
	 * @param workOrder
	 * @param planorder
	 * @param con
	 * @throws SQLException
	 */
	public   void deleteplanbyPlanOrder(String produceDate,int produnitid,String workOrder,int planorder,Connection con)throws SQLException{
		DAO_Plan dao_plan = (DAO_Plan) DAOFactoryAdapter.getInstance(
					DataBaseType.getDataBaseType(con), DAO_Plan.class);
		Statement stmt = con.createStatement();
		log.debug("ɾ��������Ԫ���������ڣ�����Ͱ��,˳��ŵ���ҵ�ƻ�"+dao_plan.deleteplanbyPlanOrder(produceDate, produnitid,workOrder, planorder));
		stmt.executeUpdate(dao_plan.deleteplanbyPlanOrder(produceDate, produnitid,workOrder, planorder));
		if(stmt!=null)
		{
			stmt.close();	
		}
		
	}

	 /**����ָ��˳�򷽷�  л����
	 * @param produceDate
	 * @param produnitid
	 * @param workOrder
	 * @param planorder
	 * @param con
	 * @throws SQLException
	 */
	public void updatePlanOrder(String produceDate,int produnitid,String workOrder,int planorder ,Connection con)throws SQLException{
		DAO_Plan dao_plan = (DAO_Plan) DAOFactoryAdapter.getInstance(
					DataBaseType.getDataBaseType(con), DAO_Plan.class);
		Statement stmt = con.createStatement();
		log.debug("����ָ��˳�򷽷�"+dao_plan.updatePlanOrder(produceDate, produnitid, workOrder, planorder));
		stmt.executeUpdate(dao_plan.updatePlanOrder(produceDate, produnitid, workOrder, planorder));
		if(stmt!=null)
		{
			stmt.close();
		}
		
	}
   
	/**���ƻ������� �ƻ�˳��ͨ���ƻ���id  �ͻ�����˳���л����
	* @param id
	* @param nextorder
	* @param con
	* @throws SQLException
	*/
	public   void   down_or_upPlanOrderbyplanid(int id,int nextorder,Connection con)throws SQLException{
		DAO_Plan dao_plan = (DAO_Plan) DAOFactoryAdapter.getInstance(
						DataBaseType.getDataBaseType(con), DAO_Plan.class);
		Statement stmt = con.createStatement();
		log.debug("���ƻ������� �ƻ�˳��ͨ���ƻ���id  �ͻ�����˳���"+dao_plan.down_or_upPlanOrderbyplanid(id,nextorder));
        stmt.executeUpdate(dao_plan.down_or_upPlanOrderbyplanid(id,nextorder));
  		if(stmt!=null)
  		{
  			stmt.close();
  		}
  		
	}
		
	/**�����༭�ƻ� л����
	* @param produceDate
	* @param produnitid
	* @param workOrder
	* @param con
	* @throws SQLException
	*/
	public  void disfrockplan(String produceDate,int produnitid,String workOrder,Connection con)throws SQLException{
		DAO_Plan dao_plan = (DAO_Plan) DAOFactoryAdapter.getInstance(
						DataBaseType.getDataBaseType(con), DAO_Plan.class);
		Statement stmt = con.createStatement();
		log.debug("�����༭�ƻ�"+dao_plan.disfrockplan(produceDate, produnitid,  workOrder));
        stmt.executeUpdate(dao_plan.disfrockplan(produceDate, produnitid,  workOrder));
        if(stmt!=null)
		{
			stmt.close();
		}
		
	}

	/**�ύ�༭�ƻ� л����
	* @param produceDate
	* @param produnitid
	* @param workOrder
	* @param versioncode
	* @param con
	* @throws SQLException
	*/
	public  void  submitplan(String produceDate,int produnitid,String workOrder,String versioncode,Connection con)throws SQLException{
		DAO_Plan dao_plan = (DAO_Plan) DAOFactoryAdapter.getInstance(
						DataBaseType.getDataBaseType(con), DAO_Plan.class);
		Statement stmt = con.createStatement();
		log.debug("�ύ�༭�ƻ�"+dao_plan.submitplan(produceDate, produnitid, workOrder, versioncode));
        stmt.executeUpdate(dao_plan.submitplan(produceDate, produnitid, workOrder, versioncode));
        if(stmt!=null)
		{
			stmt.close();
		}
		
	}

	/**��ѯ�ύ�İ汾�� л����
	* @param produceDate
	* @param produnitid
	* @param workOrder
	* @param con
	* @return
	* @throws SQLException
	*/
	public String getversioncodewhensubmit(String produceDate,int produnitid,String workOrder,Connection con)throws SQLException{
		DAO_Plan dao_plan = (DAO_Plan) DAOFactoryAdapter.getInstance(
						DataBaseType.getDataBaseType(con), DAO_Plan.class);
		Statement stmt = con.createStatement();
		log.debug("��ѯ�ύ�İ汾��"+dao_plan.getversioncodewhensubmit(produceDate, produnitid,  workOrder));
        ResultSet rs=  stmt.executeQuery(dao_plan.getversioncodewhensubmit(produceDate, produnitid,  workOrder));
        String versioncode="";
        if(rs.next()){
        	versioncode=rs.getString("str_versioncode");
        }
        if(stmt!=null)
		{
			stmt.close();
		}
		
		return versioncode;
	}
	 
	/**������ҵ�ƻ� л����
	* @param produceDate
	* @param produnitid
	* @param workOrder
	* @param con
	* @throws SQLException
	*/
	public void uploadplan(String produceDate,int produnitid,String workOrder,Connection con)throws SQLException{
		DAO_Plan dao_plan = (DAO_Plan) DAOFactoryAdapter.getInstance(
						DataBaseType.getDataBaseType(con), DAO_Plan.class);
		Statement stmt = con.createStatement();
		log.debug("������ҵ�ƻ�"+dao_plan.uploadplan(produceDate, produnitid, workOrder));
		stmt.executeUpdate(dao_plan.uploadplan(produceDate, produnitid, workOrder));
        if(stmt!=null)
		{
			stmt.close();
		}
		
	}
	 
	/**ȡ����ҵ�ƻ����� л����
	* @param produceDate
	* @param produnitid
	* @param workOrder
	* @param con
	* @throws SQLException
	*/
	public void canceluploadplan(String produceDate,int produnitid,String workOrder,Connection con)throws SQLException{
		DAO_Plan dao_plan = (DAO_Plan) DAOFactoryAdapter.getInstance(
							DataBaseType.getDataBaseType(con), DAO_Plan.class);
		Statement stmt = con.createStatement();
		log.debug("ȡ����ҵ�ƻ�����"+dao_plan.canceluploadplan(produceDate, produnitid,  workOrder));
	    stmt.executeUpdate(dao_plan.canceluploadplan(produceDate, produnitid,  workOrder)); 
	    if(stmt!=null)
		{
			stmt.close();	
		}
		
	}
	  
	/**������ҵʱֻ�ܿ������İ汾���Ұ汾�Ų�Ϊtemp л����
	* @param produceDate
	* @param produnitid
	* @param workOrder
	* @param con
	* @return
	* @throws SQLException
	*/
	public  List<Plan> getmaxPlanexcepttemp(String produceDate,int produnitid,String workOrder,Connection con)throws SQLException{
		DAO_Plan dao_plan = (DAO_Plan) DAOFactoryAdapter.getInstance(
							DataBaseType.getDataBaseType(con), DAO_Plan.class);
		Statement stmt = con.createStatement();
		log.debug("������ҵʱֻ�ܿ������İ汾���Ұ汾�Ų�Ϊtemp "+dao_plan.getmaxPlanexcepttemp(produceDate, produnitid, workOrder));
	    ResultSet rs= stmt.executeQuery(dao_plan.getmaxPlanexcepttemp(produceDate, produnitid, workOrder)); 
	    List<Plan> planlist=new ArrayList<Plan>();
		while(rs.next()){
			Plan plan =new Plan();
			plan.setId(rs.getInt("int_id"));
	        plan.setPlanDate(rs.getDate("dat_planDate"));
	        plan.setProduceDate(rs.getDate("dat_produceDate"));
	        plan.setOrderFormId(rs.getString("str_orderFormId"));
	        plan.setPlanGroupId(rs.getInt("int_planGroupId"));
	        plan.setProduceType(rs.getString("str_produceType"));
	        plan.setProduceName(rs.getString("str_produceName"));
	        plan.setProduceMarker(rs.getString("str_produceMarker"));
	        plan.setProdunitid(rs.getInt("int_produnitid"));
	        plan.setWorkTeam(rs.getString("str_workTeam"));
	        plan.setWorkOrder(rs.getString("str_workOrder"));
	        plan.setAmount(rs.getInt("int_count"));
	        plan.setVersioncode(rs.getString("str_versioncode"));
	        plan.setUpload(rs.getInt("int_upload"));
	        plan.setPlanOrder(rs.getInt("int_planOrder"));
	        plan.setDescription(rs.getString("str_description"));
	        planlist.add(plan);
		}

	    if(stmt!=null)
		{
			stmt.close();	
		}
		
	    return planlist;
	}

	/**��ѯ��ͬ��������������Ԫ�����ε����汾��Ϣ л����
	* @param con
	* @return
	* @throws SQLException
	*/
	public List<Plan> geteverydaymaxversioncodeplan(Connection con)throws SQLException{
		DAO_Plan dao_plan = (DAO_Plan) DAOFactoryAdapter.getInstance(
							DataBaseType.getDataBaseType(con), DAO_Plan.class);
		Statement stmt = con.createStatement();
		log.debug("��ѯ��ͬ��������������Ԫ�����ε����汾��Ϣ"+dao_plan.geteverydaymaxversioncodeplan());		 
		ResultSet rs=stmt.executeQuery(dao_plan.geteverydaymaxversioncodeplan());
		List<Plan> planlist=new ArrayList<Plan>();
		while(rs.next()){
			Plan plan =new Plan();
			plan.setId(rs.getInt("int_id"));
		    plan.setPlanDate(rs.getDate("dat_planDate"));
		    plan.setProduceDate(rs.getDate("dat_produceDate"));
		    plan.setOrderFormId(rs.getString("str_orderFormId"));
		    plan.setPlanGroupId(rs.getInt("int_planGroupId"));
		    plan.setProduceType(rs.getString("str_produceType"));
		    plan.setProduceName(rs.getString("str_produceName"));
		    plan.setProduceMarker(rs.getString("str_produceMarker"));
		    plan.setProdunitid(rs.getInt("int_produnitid"));
		    plan.setWorkTeam(rs.getString("str_workTeam"));
		    plan.setWorkOrder(rs.getString("str_workOrder"));
		    plan.setAmount(rs.getInt("int_count"));
		    plan.setVersioncode(rs.getString("str_versioncode"));
		    plan.setUpload(rs.getInt("int_upload"));
		    plan.setPlanOrder(rs.getInt("int_planOrder"));
		    plan.setDescription(rs.getString("str_description"));
		    planlist.add(plan);
		}
		if(stmt!=null)
		{
			stmt.close();
		}
		
		return planlist;
	}

	/**�滻ʱ���˶Ա�������ݵ���ҵ�ƻ�  л����
	* @param produceDate
	* @param produnitid
	* @param workOrder
	* @param con
	* @return
	* @throws SQLException
	*/
	public List<Plan>  geteverydaymaxversioncodeplanexceptnow(String produceDate,int produnitid,String workOrder,Connection con)throws SQLException{
		DAO_Plan dao_plan = (DAO_Plan) DAOFactoryAdapter.getInstance(
								DataBaseType.getDataBaseType(con), DAO_Plan.class);
		Statement stmt = con.createStatement();
		log.debug("�滻ʱ���˶Ա�������ݵ���ҵ�ƻ�"+dao_plan.geteverydaymaxversioncodeplanexceptnow(produceDate, produnitid,  workOrder));			 
		ResultSet rs=stmt.executeQuery(dao_plan.geteverydaymaxversioncodeplanexceptnow(produceDate, produnitid,  workOrder));			
		List<Plan> planlist=new ArrayList<Plan>();
		while(rs.next()){
			Plan plan =new Plan();
			plan.setId(rs.getInt("int_id"));
			plan.setPlanDate(rs.getDate("dat_planDate"));
			plan.setProduceDate(rs.getDate("dat_produceDate"));
			plan.setOrderFormId(rs.getString("str_orderFormId"));
			plan.setPlanGroupId(rs.getInt("int_planGroupId"));
			plan.setProduceType(rs.getString("str_produceType"));
			plan.setProduceName(rs.getString("str_produceName"));
			plan.setProduceMarker(rs.getString("str_produceMarker"));
			plan.setProdunitid(rs.getInt("int_produnitid"));
			plan.setWorkTeam(rs.getString("str_workTeam"));
			plan.setWorkOrder(rs.getString("str_workOrder"));
			plan.setAmount(rs.getInt("int_count"));
			plan.setVersioncode(rs.getString("str_versioncode"));
			plan.setUpload(rs.getInt("int_upload"));
			plan.setPlanOrder(rs.getInt("int_planOrder"));
			plan.setDescription(rs.getString("str_description"));
			planlist.add(plan);
		}
		if(stmt!=null)
		{
			stmt.close();
		}
		
		return planlist;
	}
	
	/**��ѯ��ͬ��������������Ԫ�����ε����汾��Ϣ ���Ƿ���Ҫ���ҵ�������ֵ л����
	 * @param materiel
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public Plan getmaterielfromeditplan(String produceDate,int produnitid,String workOrder,String materiel,Connection con)throws SQLException{
		DAO_Plan dao_plan = (DAO_Plan) DAOFactoryAdapter.getInstance(
								DataBaseType.getDataBaseType(con), DAO_Plan.class);
		Statement stmt = con.createStatement();
		log.debug("��ѯ��ͬ��������������Ԫ�����ε����汾��Ϣ ���Ƿ���Ҫ���ҵ�������ֵ"+dao_plan.getmaterielfromeditplan(produceDate,produnitid,workOrder,materiel));			 
		ResultSet rs=stmt.executeQuery(dao_plan.getmaterielfromeditplan(produceDate,produnitid,workOrder,materiel));
		Plan plan=new Plan();
		if(rs.next()){		 
			plan.setId(rs.getInt("int_id"));
			plan.setPlanDate(rs.getDate("dat_planDate"));
			plan.setProduceDate(rs.getDate("dat_produceDate"));
			plan.setOrderFormId(rs.getString("str_orderFormId"));
			plan.setPlanGroupId(rs.getInt("int_planGroupId"));
			plan.setProduceType(rs.getString("str_produceType"));
			plan.setProduceName(rs.getString("str_produceName"));
			plan.setProduceMarker(rs.getString("str_produceMarker"));
			plan.setProdunitid(rs.getInt("int_produnitid"));
			plan.setWorkTeam(rs.getString("str_workTeam"));
			plan.setWorkOrder(rs.getString("str_workOrder"));
			plan.setAmount(rs.getInt("int_count"));
			plan.setVersioncode(rs.getString("str_versioncode"));
			plan.setUpload(rs.getInt("int_upload"));
			plan.setPlanOrder(rs.getInt("int_planOrder"));
			plan.setDescription(rs.getString("str_description"));

		}
		if(stmt!=null)
		{
			stmt.close();
		}
		
		return plan;
	}

	/**�༭ҳ����Ҽ�¼ �汾����temp  л����
	* @param produceDate
	* @param produnitid
	* @param workOrder
	* @param con
	* @return
	* @throws SQLException
	*/
	public  List<Plan> getplancontainstemp(String produceDate,int produnitid,String workOrder,Connection con)throws SQLException{
		DAO_Plan dao_plan = (DAO_Plan) DAOFactoryAdapter.getInstance(
								DataBaseType.getDataBaseType(con), DAO_Plan.class);
		Statement stmt = con.createStatement();
		log.debug("�༭ҳ����Ҽ�¼ �汾����temp"+dao_plan.getplancontainstemp(produceDate, produnitid, workOrder));			 
		ResultSet rs=stmt.executeQuery(dao_plan.getplancontainstemp(produceDate, produnitid, workOrder));		
		List<Plan> planlist=new ArrayList<Plan>();
		while(rs.next()){
			Plan plan =new Plan();
			plan.setId(rs.getInt("int_id"));
			plan.setPlanDate(rs.getDate("dat_planDate"));
			plan.setProduceDate(rs.getDate("dat_produceDate"));
			plan.setOrderFormId(rs.getString("str_orderFormId"));
			plan.setPlanGroupId(rs.getInt("int_planGroupId"));
			plan.setProduceType(rs.getString("str_produceType"));
			plan.setProduceName(rs.getString("str_produceName"));
			plan.setProduceMarker(rs.getString("str_produceMarker"));
			plan.setProdunitid(rs.getInt("int_produnitid"));
			plan.setWorkTeam(rs.getString("str_workTeam"));
			plan.setWorkOrder(rs.getString("str_workOrder"));
			plan.setAmount(rs.getInt("int_count"));
			plan.setVersioncode(rs.getString("str_versioncode"));
			plan.setUpload(rs.getInt("int_upload"));
			plan.setPlanOrder(rs.getInt("int_planOrder"));
			plan.setDescription(rs.getString("str_description"));
			planlist.add(plan);
		}
		if(stmt!=null)
		{
			stmt.close();
		}
		
		return planlist;
	}

	/**�˶��Ƿ����order�ļƻ�˳��� л����  �ƻ����ĺ����ҳ��
	* @param produceDate
	* @param produnitid
	* @param workOrder
	* @param order
	* @param con
	* @return
	* @throws SQLException
	*/
	public boolean checkplanorder(String produceDate,int produnitid,String workOrder,int order,Connection con)throws SQLException{
		DAO_Plan dao_plan = (DAO_Plan) DAOFactoryAdapter.getInstance(
								DataBaseType.getDataBaseType(con), DAO_Plan.class);
		Statement stmt = con.createStatement();
		boolean f=false;
		log.debug("�˶��Ƿ����order�ļƻ�˳���"+dao_plan.checkplanorder(produceDate, produnitid,workOrder,order));
		ResultSet rs=stmt.executeQuery(dao_plan.checkplanorder(produceDate, produnitid,workOrder,order));
		if(rs.next()){
			f=true;
		}
		if(stmt!=null)
		{
			stmt.close();		
		}
		
		return f;
	}
	
	/**ͨ��������Ԫ��β�ѯ��ҵ�ƻ�����ɾ������ʱ�̱����ж�
	* author : ������
	 * @param Produnitid
	 * @param Order
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public boolean getAllPlanByProdunitidOrder(int Produnitid,String Order,Connection con)throws SQLException{
		DAO_Plan dao_plan = (DAO_Plan) DAOFactoryAdapter.getInstance(
							DataBaseType.getDataBaseType(con), DAO_Plan.class);
		Statement stmt = con.createStatement();
		boolean f=false;
		log.debug("ͨ��������Ԫ��β�ѯ��ҵ�ƻ�����ɾ������ʱ�̱����ж�"+dao_plan.getAllPlanByProdunitidOrder(Produnitid, Order));
		ResultSet rs=stmt.executeQuery(dao_plan.getAllPlanByProdunitidOrder(Produnitid, Order));
		if(rs.next()){
			f=true;
		}
		if(stmt!=null){
			stmt.close();		
		}
		
		return f;
	}
			    		
	/**�ƻ�����ָ��ʱ û�����ļƻ�����Ա��������л����
	 * @param produceDate
	 * @param produnitid
	 * @param workOrder
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public List<Plan> getmaxPlanexcepttempupload(String produceDate,int produnitid,String workOrder,Connection con)throws SQLException{	
		DAO_Plan dao_plan = (DAO_Plan) DAOFactoryAdapter.getInstance(
						DataBaseType.getDataBaseType(con), DAO_Plan.class);
		Statement stmt = con.createStatement();
		log.debug("�ƻ�����ָ��ʱ û�����ļƻ�����Ա������"+dao_plan.getmaxPlanexcepttempupload(produceDate, produnitid, workOrder));
		ResultSet rs= stmt.executeQuery(dao_plan.getmaxPlanexcepttempupload(produceDate, produnitid, workOrder));
		List<Plan> planlist=new ArrayList<Plan>();
		while(rs.next()){
			Plan plan =new Plan();
			plan.setId(rs.getInt("int_id"));
			plan.setPlanDate(rs.getDate("dat_planDate"));
			plan.setProduceDate(rs.getDate("dat_produceDate"));
			plan.setOrderFormId(rs.getString("str_orderFormId"));
			plan.setPlanGroupId(rs.getInt("int_planGroupId"));
			plan.setProduceType(rs.getString("str_produceType"));
			plan.setProduceName(rs.getString("str_produceName"));
			plan.setProduceMarker(rs.getString("str_produceMarker"));
			plan.setProdunitid(rs.getInt("int_produnitid"));
			plan.setWorkTeam(rs.getString("str_workTeam"));
			plan.setWorkOrder(rs.getString("str_workOrder"));
			plan.setAmount(rs.getInt("int_count"));
			plan.setVersioncode(rs.getString("str_versioncode"));
			plan.setUpload(rs.getInt("int_upload"));
          	plan.setPlanOrder(rs.getInt("int_planOrder"));
          	plan.setDescription(rs.getString("str_description"));
          	planlist.add(plan);
		}
        if(stmt!=null)
		{
        	stmt.close();
		}
		
        return planlist;
	}
	
	
				 
}
			  
			  

