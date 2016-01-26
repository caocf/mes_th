package com.qm.mes.os.dao;
import com.qm.mes.os.bean.WorkSchedle;

public class DAO_WorkSchedleForOracle implements DAO_WorkSchedle{
	
	/** ��������ʱ�̱�
	 * ������
	 */
	public String saveWorkSchedle(WorkSchedle  workSchedle){
		String sql = "insert into t_os_workSchedle(int_id,int_produnitid,str_workOrder,str_workSchedle,str_advanceTime)"
			+ "values(seq_os_WorkSchedle.nextval"	
			+ ","+ workSchedle.getProdunitid()+""
			+ ",'"+ workSchedle.getWorkOrder()+ "'"
			+ ",'"+ workSchedle.getWorkSchedle()+ "'"
			+ ",'"+ workSchedle.getAdvanceTime()+"')";
			
		return sql;
	}
	
	/**������й���ʱ�̱�
	 * author : ������
	 */
	public String getAllWorkSchedle(){
		
		String sql = "select wo.int_id,wo.int_produnitid,wo.str_workorder,wo.str_workschedle,wo.str_advancetime,pu.str_name" +
				" from t_os_workschedle wo,t_ra_produceUnit pu where pu.int_id=wo.int_produnitid order by wo.int_id desc";
		
	    return sql;
	}
	
	/**ͨ��IDɾ������ʱ�̱�
	 * author : ������
	 */
	public String deleteWorkSchedleById(int id){
		String sql="delete from T_OS_WORKSCHEDLE where int_id=" + id;
		return sql;
	}
	
	/**ͨ��ID��ù���ʱ�̱�
	 * author : ������
	 */
	public String getWorkSchedleById(int id){
		String sql="select * from T_OS_WORKSCHEDLE where int_id="+id;
		return sql;
	}
	
	/**�޸Ĺ���ʱ�̱�
	 * author : ������
	 */
	public String updateWorkSchedle(WorkSchedle  workSchedle){
		String sql=" update T_OS_WORKSCHEDLE set int_produnitid="+workSchedle.getProdunitid()+"," 
		  
		   +"str_workOrder='"+workSchedle.getWorkOrder()+"',"
		   +"str_workSchedle='"+workSchedle.getWorkSchedle()+"',"
		   	+"str_advanceTime='"+workSchedle.getAdvanceTime()+"' where int_id="+workSchedle.getId()+" ";
		 
		   return sql;
	
	}
	
	
	/**��ù���ʱ�̱���������Ԫ��
	 * author : ������
	 */
	public String getprodunitid(){
		String sql="select int_produnitid from T_OS_WORKSCHEDLE";
		return sql;

	}
	
	/**��ù���ʱ�̱��а��
	 * author : ������
	 */
	public String getworkOrder(){
		String sql="select str_workOrder from T_OS_WORKSCHEDLE";
		return sql;
	
	}
	
	/**��ȥ��ID��ѯ��ù���ʱ�̱���������Ԫ�����ڸ����ж�
	 * author : ������
	 */
	public String getprodunitidById(int id){
		String sql="select int_produnitid from T_OS_WORKSCHEDLE where not int_id="+id;
		return sql;

	}
	
	/**��ȥ��ID��ѯ��ù���ʱ�̱��а�����ڸ����ж�
	 * author : ������
	 */
	public String getworkOrderById(int id){
		String sql="select str_workOrder from T_OS_WORKSCHEDLE where not int_id="+id;
		return sql;
	
	}
	
	/**��ѯ������ţ���ǰ��
	 * author : ������
	 */
	public String getAllAdvanceTime(){
		String sql="select int_id,str_advanceTime from T_OS_WORKSCHEDLE";
		return sql;
	
	}
	
	/**ͨ��ID��ѯ������Ԫ��������ж�ɾ������
	 * author : ������
	 */
	public String getProdunitidOrderById(int id){
		String sql="select int_produnitid,Str_workOrder from T_OS_WORKSCHEDLE where int_id="+id;
		return sql;
	}
	
	/**ͨ��������Ԫ��β�ѯ����ʱ�̱�����ɾ��������ж�
	 * author : ������
	 */
	public String getSchedleByProdunitidOrder(int Produnitid,String Order){
		String sql="select * from T_OS_WORKSCHEDLE where int_produnitid="+Produnitid+" and str_workOrder='"+Order+"'";
		return sql;
	}
	
	/**ͨ��������Ԫ��id����ȡ�����Ϣ 
	 * author : л����
	 */
	public String getworkOrderByprodunitid(int id){
		String sql="select Str_workOrder  from t_os_workschedle where int_produnitid="+id+" ";
		return sql;
	}
	 /**ͨ��������Ԫ�������ѯ����ʱ�����ǰ�� 
		 * author :  л����
		 */
	public String getworkschedleadtime(int produnitid,String workorder){
		String sql="select * from t_os_workSchedle where int_produnitid="+produnitid+" "
		+"  and str_workOrder='"+workorder+"'";
		
		return sql;
	}
	/**ͨ��������Ԫ��ѯ����ʱ��
	 * author : ������
	 */
	public String getWorkSchedelByProuunitid(int str_produceunit){
		String sql="select str_workschedle from t_os_workSchedle where int_produnitid="+str_produceunit;
		return sql;
		
	}
	
	/**����IDͨ��������Ԫ��ѯ����ʱ��
	 * author : ������
	 */
	public String getWorkSchedelByProuunitidAndID(int str_produceunit,int int_id){
		String sql="select str_workschedle from t_os_workSchedle where int_produnitid="+str_produceunit+" and not int_id="+int_id;
		return sql;
		
	}
	
	
	
	
}
