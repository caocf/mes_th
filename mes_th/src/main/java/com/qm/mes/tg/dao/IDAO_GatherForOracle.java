package com.qm.mes.tg.dao;

import com.qm.mes.tg.bean.Gather;

public class IDAO_GatherForOracle implements IDAO_Gather {
	/**
	 * ͨ�����ɾ��gather
	 * 
	 * @param id
	 *            ���
	 * @return ɾ����sql���
	 */
	public String delGatherById(int id) {
		String sql = "delete from t_tg_Gather where int_id=" + id;
		return sql;
	}

	/**
	 * ��ѯȫ��gather����
	 * 
	 * @return ��ѯȫ����sql���
	 */
	public String getAllGather() {
		String sql="select g.*,p.str_name as producename,r.str_name as rulename"
    +" from t_tg_Gather g inner join t_ra_produceunit p on g.int_produnitid=p.int_id"
    +" inner join t_tg_materielrule r on g.int_materielruleid=r.int_id   ";
		return sql;
	}

    /**
	 * �����ѯȫ��gather����
	 *
	 * @return ��ѯȫ����sql���
	 */
	public String getAllGatherDESC() {
		String sql = "select int_id,str_name,str_desc,int_produnitid,int_materielruleid "
				+ "from t_tg_Gather order by int_id desc";
		return sql;
	}

	/**
	 * ͨ����Ų�ѯgather
	 * 
	 * @param id
	 *            ���
	 * @return ��ѯ��ŵ�sql���
	 */
	public String getGatherById(int id) {
		String sql = "select int_id,str_name,str_desc,int_produnitid,int_materielruleid,startgo,compel from t_tg_Gather "
				+ "where int_id = " + id + " order by int_id";
		return sql;
	}

	/**
	 * ͨ����Ų�ѯgather
	 * 
	 * @param str_name
	 *            �ɼ�����
	 * @return ��ѯ��ŵ�sql���
	 */
	public String getGatherByName(String str_name) {
		String sql = "select int_id,str_name,str_desc,int_produnitid,int_materielruleid from t_tg_Gather "
				+ "where str_name = '" + str_name + "' order by int_id";
		return sql;
	}

	/**
	 * ����gather
	 * л�����޸�������startgo,compel�����ֶ�
	 * @param gather
	 *            ʵ�����
	 * @return ����gather��sql���
	 */
	public String saveGather(Gather gather) {
		String sql = "insert into t_tg_Gather(int_id,str_name,str_desc,int_produnitid,int_materielruleid,startgo,compel) "
				+ "values(seq_tg_Gather.nextval,'"
				+ gather.getName()
				+ "','"
				+ gather.getDesc()
				+ "',"
				+ gather.getProdunitId()
				+ ","
				+ gather.getMaterielruleId() 
				+","
				+ gather.getStartgo()
		        +","
		        +gather.getCompel()
				+ ")";
		
		return sql;
	}

	/**
	 * ����gather����ͨ����id����
	 * 
	 * @param gather
	 *            ʵ�����
	 * @return ���µ�sql���
	 * л�����޸�������startgo,compel�����ֶ�
	 */
	public String updateGather(Gather gather) {
		String sql = "update t_tg_Gather set str_name = '" + gather.getName()
				+ "' , str_desc = '" + gather.getDesc()
				+ "' , int_produnitid = " + gather.getProdunitId()
				+ " , int_materielruleid = " + gather.getMaterielruleId()
				+",startgo="+gather.getStartgo()
				+",compel="+gather.getCompel()
				+ " where int_id = " + gather.getId();
		return sql;
	}

	public String checkGatherItemCountByMaterialId(int gatherid, int materialId) {
		String sql = "select count(*) from  t_tg_Gather "
				+ " where INT_MATERIELRULEID=" + materialId + " and int_id="
				+ gatherid + "";
		return sql;
	}
	
	//--------------------------------------------------������Ŀ���---------------------------------------------------------------
	
	public String saveGather_Q(int g,int q,int order){
		 String sql = "insert into T_QM_R_GATHER_QUALITYSTATES(int_id,INT_GATHER_ID,INT_QUALITYSTATES_ID,int_order) "
				+ "values(seq_PM_DEVICE_UNIT.nextval,"
				+g+","+q+","+order+")";	   
		return sql;
	}
	
	public String delGather_Q(int g){
		String sql = "delete from T_QM_R_GATHER_QUALITYSTATES where int_gather_id=" + g;
		return sql;		
	}
}
