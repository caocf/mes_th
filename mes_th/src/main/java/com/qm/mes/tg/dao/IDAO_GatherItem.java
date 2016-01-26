package com.qm.mes.tg.dao;

import com.qm.mes.tg.bean.GatherItem;

public interface IDAO_GatherItem {

	/**
	 * ����GatherItem����
	 * 
	 * @param item
	 *            ʵ�����
	 * @return ����GatherItem�����sql���
	 */
	String saveGatherItem(GatherItem item);

	/**
	 * ͨ�����ɾ��GatherItem����
	 * 
	 * @param id
	 *            ���
	 * @return ɾ����sql���
	 */
	String delGatherItemById(int id);

	/**
	 * ͨ�����ȡ��GatherItem����
	 * 
	 * @param id
	 *            ���
	 * @return ȡ�ö����sql���
	 */
	String getGatherItemById(int id);

	/**
	 * ͨ��Gather���ȡ��GatherItem�б�
	 * 
	 * @param id
	 *            Gather���
	 * @return ȡ�ö����б��sql���
	 */
	String getGatherItemByGid(int id);

	/**
	 * ͨ��Gather��ţ�ɾ��������GatherItem
	 * 
	 * @param id
	 *            Gather���
	 * @return ͨ��idɾ����sql���
	 */
	String delGatherItemByGid(int id);

	/**
	 * ����GatherItem����
	 * 
	 * @param item
	 *            ʵ�����
	 * @return ����ʵ������sql���
	 */
	String updateGatherItem(GatherItem item);

	/**
	 * ���ɼ�������gatherItem�����Ƿ����д�˳���
	 * 
	 * @param order
	 *            ˳��� <br>
	 * @param gatherid
	 *            �ɼ���� <br>
	 * @return ˳��Ÿ���
	 */
	String checkGatherItemCountByorder(int gatherid, int order);

	/**
	 * ���ɼ�������gatherItem�����Ƿ����д����ϱ�ʶ�����
	 * 
	 * @param materialId
	 *            �����ϱ�ʶ����� <br>
	 * @param gatherid
	 *            �ɼ���� <br>
	 * @return ˳��Ÿ���
	 */
	String checkGatherItemCountBySubMaterialId(int gatherid, int materialId);

}
