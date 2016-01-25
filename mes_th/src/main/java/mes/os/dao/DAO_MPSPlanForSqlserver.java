package mes.os.dao;

import java.text.SimpleDateFormat;

import mes.os.bean.MPSPlan;

/**
 * 
 * @author л���� 2009-5-13 
 *
 */
public class DAO_MPSPlanForSqlserver extends DAO_MPSPlanForOracle{
	/** 
	 * �������ƻ� л���� 
	 */
	public  String saveMPSPlan(MPSPlan mpsPlan){
		 String a=mpsPlan.getStartDate()==null||mpsPlan.getStartDate().toString().equals("null")?"":(""+(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(mpsPlan.getStartDate())+"");
		String sql="insert into t_os_MPSPlan values("
			+"convert(datetime,'"+a+"',20)" + ",'" 
			+mpsPlan.getMpsUnit()+"','"
			+mpsPlan.getMaterielName()+"',"
			+mpsPlan.getPlanAmount()+","
			+mpsPlan.getIntendStorage()+",'"
			+mpsPlan.getPlanType()+"','"
			+mpsPlan.getVersion()+"','"
			+mpsPlan.getUserName()+"','"
			+mpsPlan.getContractCode()+"','" 
			+   "','" 
			+   "','" 
			+   "'" +")";
		
		return sql;
	}
	
	 /** 
		 *�õ����е����ƻ� л���� 
		 */ 
	 public   String getALLMPSPlan(){
		 String sql="select int_id,convert(varchar,dat_startDate,20) as dat_startDate,str_mpsUnit," 
		 		+"str_materielName,int_planAmount,int_intendStorage,str_planType,str_version ,str_user,str_contractcode from t_os_MPSPlan order by int_id desc";
		 
		 return sql;
		 
	 }
	/** 
	* �滻��ɾ�����ƻ����ڵ����ƻ� л����
	*/
	public String deleteMPSPlanbystartDate(String dat_startDate){
		 String sql="delete from t_os_MPSPlan where convert(varchar,dat_startDate,20)='"+dat_startDate+"'";
		 
		 return sql;
		
	}
	
	/** 
		* ͨ���ƻ����ڵõ�һ�����ϵ����ƻ� л����
	*/
	public    String getMPSPlanbystartDate(String  dat_startDate){
		String sql="select * from t_os_MPSPlan where convert(varchar,dat_startDate,20)='"+dat_startDate+"' ";
		return sql;
	}
	/** 
	* �������ƻ� ͨ��idֵ л���� 
	*/
	public    String updateMPSPlanbyid(MPSPlan mpsplan){
		String sql="update t_os_MPSPlan set "
			+"dat_startDate=convert(datetime,'"+(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(mpsplan.getStartDate())+"',20),"
			+"str_mpsUnit='"+mpsplan.getMpsUnit()+"',"
			+"str_materielName='"+mpsplan.getMaterielName()+"',"
			+"int_planAmount="+mpsplan.getPlanAmount()+","
			+"int_intendStorage="+mpsplan.getIntendStorage()+","
			+"str_planType='"+mpsplan.getPlanType()+"',"
			+"str_version='"+mpsplan.getVersion()+"',"
			+"str_contractcode='"+mpsplan.getContractCode()+"'"
			+" where int_id= "+mpsplan.getId()+"";
		
		return sql;
	}
	
}
