package mes.pm.dao;

import mes.pm.bean.ExceptionCause;
import mes.pm.bean.ExceptionType;
/**
 * @author Xujia
 *
 */
public class DAO_ExceptionForOracle implements DAO_Exception {
	/**
	 * �����쳣���͵�sql���
	 * @param exType
	 */  
	public String saveExceptionType (ExceptionType exType){
		 String sql = "insert into t_pm_exceptiontype(int_id,int_state,int_sysdefault,str_name) "
				+ "values(seq_PM_EXCEPTIONTYPE.nextval,"
				+ exType.getState()
				+",0,'"+exType.getName()+"')";	   
		return sql;
	}	
	/**
	 * ͨ����Ų���쳣���͵�sql���
	 * @param id
	 * @return
	 */
	public String getExceptionTypeById(int id){
		String sql = "select * from t_pm_exceptiontype"
			+ " where int_id = " + id + "";
	return sql;
	}
	/**
	 * ͨ�����ɾ���쳣���͵�sql���
	 * @param id
	 * @return
	 */
	public String delExceptionTypeById(int id){
		String sql = "delete from  t_pm_exceptiontype where int_id=" + id;
		return sql;
	}
	/**
	 * ��ѯ��ȫ���쳣���͵�sql���
	 * @return
	 */
	public String getAllExceptionType (){
		String sql = "select *"
			+ " from t_pm_exceptiontype";
	
	return sql;
	}
	/**
	 * ͨ�����Ʋ���쳣���͵�sql���
	 * @param name
	 * @return
	 */
	public String getExceptionTypeByName(String name){
		String sql = "select* from t_pm_exceptiontype"
			+ " where str_name='" + name +"'order by int_id";
	
	return sql;
	}
	/**
	 * �����쳣ԭ���sql���
	 * @param exCause
	 * @return
	 */  
	public 	String saveExceptionCause (ExceptionCause exCause){
		 String sql = "insert into t_pm_exceptioncause(int_id,str_name,int_state) "
				+ "values(seq_PM_EXCEPTIONCAUSE.nextval,'"
				+ exCause.getName()
				+"',"+exCause.getState()+")";	   
		return sql;
	}
	/**
	 * ͨ����Ų���쳣ԭ���sql���
	 * @param id
	 * @return
	 */
	public String getExceptionCauseById(int id){
		String sql = "select * from t_pm_exceptioncause"
			+ " where int_id = " + id + "";
	return sql;
	}
	/**
	 * ͨ�����ɾ���쳣ԭ���sql���
	 * @param id
	 * @return
	 */
	public String delExceptionCauseById(int id){
		String sql = "delete from  t_pm_exceptioncause where int_id=" + id;
		return sql;
	}
	/**
	 * ��ѯ��ȫ���쳣ԭ���sql���
	 * @return
	 */
	public String getAllExceptionCause(){
		String sql = "select *"
			+ " from t_pm_exceptioncause";
	
	return sql;
	}
	/**
	 * ����ExceptionType����ͨ����id����
	 *  ���
	 * @param ExceptionType
	 * @return ����ExceptionType��sql���
	 */
	public String updateExceptionType(ExceptionType exceptionType) {  
		String sql = "update t_pm_exceptiontype set int_state ="
			+exceptionType.getState() 
			+ ",str_name= '" +exceptionType.getName() + "' where int_id = "
			+ exceptionType.getId();
	return sql;
	}
	/**
	 * ����ExceptionCause����ͨ����id����
	 *  ���
	 * @param ExceptionCause
	 * @return ����ExceptionCause��sql���
	 */
	public String updateExceptionCause(ExceptionCause exceptionCause) {
		String sql = "update t_pm_exceptioncause set int_state ="
			+exceptionCause.getState() 
			+ ",str_name= '" +exceptionCause.getName() + "' where int_id = "
			+ exceptionCause.getId();
	return sql;
	}
	/**
	 * ���ExceptionRecord�����Ƿ����쳣���ͺ�
	 *  ���
	 * @param id
	 * @return ��������
	 */
	public String checkExceptionTypeById(int id){
		String sql = "select count(*) from  t_pm_exceptiontype a, t_pm_exceptionrecord b"
			+ " where a.int_id=" + id
			+ " and a.int_id = b.int_exceptiontype";
	return sql;
	}
	/**
	 * ���ExceptionRecord�����Ƿ����쳣ԭ���
	 *  ���
	 * @param id
	 * @return ��������
	 */
	public String checkExceptionCauseById(int id){
		String sql = "select count(*) from  t_pm_exceptioncause a, t_pm_exceptionrecord b"	
		+ " where a.int_id=" + id
		+ " and a.int_id = b.int_exceptioncause";
        return sql;
	}

}
