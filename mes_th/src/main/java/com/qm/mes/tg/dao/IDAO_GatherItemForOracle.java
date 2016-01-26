package com.qm.mes.tg.dao;

import com.qm.mes.tg.bean.GatherItem;

public class IDAO_GatherItemForOracle implements IDAO_GatherItem {
	/**
	 * ͨ��Gather��ţ�ɾ��������GatherItem
	 * 
	 * @param id
	 *            Gather���
	 * @return ͨ��idɾ����sql���
	 */
	public String delGatherItemByGid(int id) {
		String sql = "delete from t_tg_GatherItem where int_gatherid = " + id;
		return sql;
	}

	/**
	 * ͨ�����ɾ��GatherItem����
	 * 
	 * @param id
	 *            ���
	 * @return ɾ����sql���
	 */
	public String delGatherItemById(int id) {
		String sql = "delete from t_tg_GatherItem where int_id = " + id;
		return sql;
	}

	/**
	 * ͨ��Gather���ȡ��GatherItem�б�
	 * 
	 * @param id
	 *            Gather���
	 * @return ȡ�ö����б��sql���
	 */
	public String getGatherItemByGid(int id) {
		String sql = "select int_id,int_gatherid,int_order,int_materielruleid from t_tg_GatherItem where int_gatherid = "
				+ id + " order by int_order";
		return sql;
	}

	/**
	 * ͨ�����ȡ��GatherItem����
	 * 
	 * @param id
	 *            ���
	 * @return ȡ�ö����sql���
	 */
	public String getGatherItemById(int id) {
		String sql = "select int_id,int_gatherid,int_order,int_materielruleid from t_tg_GatherItem where int_id = "
				+ id + " order by int_order";
		return sql;
	}

	/**
	 * ����GatherItem����
	 * 
	 * @param item
	 *            ʵ�����
	 * @return ����GatherItem�����sql���
	 */
	public String saveGatherItem(GatherItem item) {
		String sql = "insert into t_tg_GatherItem(int_id,int_gatherid,int_order,int_materielruleid) "
				+ "values(seq_tg_GatherItem.nextval,"
				+ item.getGatherId()
				+ "," + item.getOrder() + "," + item.getMaterielruleId() + ")";
		return sql;
	}

	/**
	 * ����GatherItem����
	 * 
	 * @param item
	 *            ʵ�����
	 * @return ����ʵ������sql���
	 */
	public String updateGatherItem(GatherItem item) {
		String sql = "update t_tg_GatherItem set int_gahterid = "
				+ item.getGatherId() + " , int_order = " + item.getOrder()
				+ " , int_materielrulid = " + item.getMaterielruleId()
				+ "where int_id = " + item.getId();
		return sql;
	}

	public String checkGatherItemCountByorder(int gatherid, int order) {
		String sql = "select count(*) from  t_tg_GatherItem "
				+ " where int_order=" + order + " and int_gatherid=" + gatherid
				+ "";
		return sql;
	}
	
	public String checkGatherItemCountBySubMaterialId(int gatherid, int materialId) {
		String sql = "select count(*) from  t_tg_GatherItem "
				+ " where INT_MATERIELRULEID=" + materialId + " and int_gatherid=" + gatherid
				+ "";
		return sql;
	}

}
