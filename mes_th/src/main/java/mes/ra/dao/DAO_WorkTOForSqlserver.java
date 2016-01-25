package mes.ra.dao;

import mes.ra.bean.WorkTO;

public class DAO_WorkTOForSqlserver extends  DAO_WorkTOForOracle{
	/** 
	 * ���������μ�¼��
	 * ������
	 */
	public String saveWorkTO(WorkTO  workto){
		String sql = "insert into t_ra_workto(int_id,int_produnitid,str_workOrder)"
			+ "values(seq_ra_WorkTO.nextval"	
			+ ","+ workto.getProdunitid()+""
			+ ",'"+ workto.getWorkOrder()+ "')";
			
		return sql;
	}
	
	/**ͨ��ID��ð����μ�¼��
	 * author : ������
	 */
	public String getWorkTOById(int id){
		String sql="select * from t_ra_workto where int_id="+id;
		return sql;
	}

	/**������а����μ�¼��
	 * author : ������
	 */
	public String getAllWorkTO(){
		String sql = "select wo.int_id,wo.int_produnitid,wo.str_workorder,pu.str_name " +
				"from t_ra_workto wo,t_ra_produceUnit pu where pu.int_id=wo.int_produnitid order by wo.int_id desc";
	    return sql;
	}

	/**�޸İ����μ�¼��
	 * author : ������
	 */
	public String updateWorkTO(WorkTO  workto){
		String sql=" update t_ra_workto set int_produnitid="+workto.getProdunitid()+"," 
		  
		   +"str_workOrder='"+workto.getWorkOrder()+"' where int_id="+workto.getId()+" ";
		return sql;
	
	}
	
	/**ͨ��IDɾ�������μ�¼��
	 * author : ������
	 */
	public String deleteWorkTOById(int id){
		String sql="delete from t_ra_workto where int_id=" + id;
		return sql;
	}
	
	/**��ð����μ�¼����������Ԫ��
	 * author : ������
	 */
	public String getprodunitid(){
		String sql="select int_produnitid from t_ra_workto";
		return sql;

	}
	

	
	/**��ð����μ�¼���а��
	 * author : ������
	 */
	public String getworkOrder(){
		String sql="select str_workOrder from t_ra_workto";
		return sql;
	
	}
	
	/**��ȥ��ID��ѯ��ð����μ�¼����������Ԫ�����ڸ����ж�
	 * author : ������
	 */
	public String getprodunitidById(int id){
		String sql="select int_produnitid from t_ra_workto where not int_id="+id;
		return sql;

	}
	
	
	
	/**��ȥ��ID��ѯ��ð����μ�¼���а�����ڸ����ж�
	 * author : ������
	 */
	public String getworkOrderById(int id){
		String sql="select str_workOrder from t_ra_workto where not int_id="+id;
		return sql;
	
	}
	/**ͨ��������Ԫ��id����ȡ��������Ϣ 
	 * author :л����
	 */
	public String getworkOrderByprodunitid(int id){
		String sql="select Str_workOrder from t_ra_workto where int_produnitid="+id+" ";
		return sql;
	}
	
	
}








