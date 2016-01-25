package mes.pm.dao;

import mes.pm.bean.DataRule;
import mes.pm.bean.DataRuleArg;


/**
 * @author Xujia
 *
 */
public class DAO_DataRuleForOracle implements DAO_DataRule {

	/**
	 * �������ݹ����sql���
	 * @param dataRule
	 * @return
	 */
	public String saveDataRule (DataRule dataRule){
		 String sql = "insert into t_pm_datarule(int_id,str_name,str_code,string_rule,str_description) "
			+ "values(seq_PM_DATARULE.nextval,'"
			+ dataRule.getName()
			+"','"+dataRule.getCode()+"','"+dataRule.getRule()+"','"+dataRule.getDescription()+"')";
   
	return sql;
	}
	/**
	 * �������������sql���
	 * @param dataRule
	 * @return
	 */
	public String saveDataRuleArg(DataRuleArg dataRuleArg){
		 String sql = "insert into t_pm_dataruleargs(int_id,str_argname,str_description,int_dataruleid) "
				+ "values(seq_PM_DATARULEARGS.nextval,'"
				+ dataRuleArg.getName()
				+"','"+dataRuleArg.getDescription()+"',"+dataRuleArg.getInt_dataruleid()+")";
	   
		return sql;		
	}
	
	/**
	 * ͨ����Ų�����ݹ����sql���
	 * @param id
	 * @return
	 */
	public String getDataRuleById(int id){
		String sql = "select * from t_pm_datarule "
			+ "where int_id = " + id + " order by int_id";
	return sql;
		
	}
	/**
	 * ͨ����Ų�����ݹ��������sql���
	 * @param id
	 * @return
	 */
	public String getDataRuleArgsById(int id){
		String sql = "select * from t_pm_dataruleargs "
			+ "where int_dataruleid = " + id + " order by int_id";
	return sql;
		
	}
	/**
	 * ͨ�����ɾ�����ݹ����sql���
	 * @param id
	 * @return
	 */
	public String delDataRuleById(int id){
		String sql = "delete from  t_pm_datarule where int_id=" + id;
		return sql;
		
	}
	/**
	 * ͨ�����ɾ�����ݹ��������sql���
	 * @param id
	 * @return
	 */	
	public String delDataRuleArgsById(int id){
		String sql="delete from t_pm_dataruleargs where int_dataruleid="+id;
		return sql;
	}
	/**
	 * ��ѯ��ȫ�����ݹ����sql���
	 * @return
	 */
	public String getAllDataRule(){
		String sql = "select *"
			+ " from t_pm_datarule";
	
	return sql;
		
	}
	/**
	 * ͨ�����Ʋ�����ݹ����sql���
	 * @param name
	 * @return
	 */
	public String getDataRuleByName(String name){
		String sql = "select* from t_pm_datarule"
			+ " where str_name='" + name +"'order by int_id";
	
	return sql;
		
	}
	/**
	 * ����dataRule����ͨ����id����
	 *  ���
	 * @param dataRule
	 * @return ����dataRule��sql���
	 */
	public String updateDataRule(DataRule dataRule) {		
		String sql = "update t_pm_datarule set str_name ='"
				+dataRule.getName() + "' , str_code = '"+ dataRule.getCode()
				+ "',string_rule ='" + dataRule.getRule()
				+ "', str_description= '" +dataRule.getDescription() + "' where int_id = "
				+ dataRule.getId();
		return sql;
	}


	
}
