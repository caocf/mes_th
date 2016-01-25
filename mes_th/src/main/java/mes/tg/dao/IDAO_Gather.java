package mes.tg.dao;

import mes.tg.bean.Gather;

public interface IDAO_Gather {

	/**
	 * ����gather
	 * 
	 * @param gather
	 *            ʵ�����
	 * @return ����gather��sql���
	 */
	String saveGather(Gather gather);

	/**
	 * ͨ����Ų�ѯgather
	 * 
	 * @param id
	 *            ���
	 * @return ��ѯ��ŵ�sql��� <br>
	 *         int_id �ɼ���� <br>
	 *         str_name �ɼ�����<br>
	 *         str_desc ������Ϣ<br>
	 *         int_produnitid ������Ԫ��<br>
	 *         int_materielruleid ���Ϻ�
	 */
	String getGatherById(int id);

	/**
	 * ͨ�����ɾ��gather
	 * 
	 * @param id
	 *            ���
	 * @return ɾ����sql���
	 */
	String delGatherById(int id);

	/**
	 * ����gather����ͨ����id����
	 * 
	 * @param gather
	 *            ʵ�����
	 * @return ���µ�sql���
	 */
	String updateGather(Gather gather);

	/**
	 * ��ѯȫ��gather����
	 * 
	 * @return ��ѯȫ����sql���<br>
	 *         int_id �ɼ���� <br>
	 *         str_name �ɼ�����<br>
	 *         str_desc ������Ϣ<br>
	 *         int_produnitid ������Ԫ��<br>
	 *         int_materielruleid ���Ϻ�
	 */
	String getAllGather();
	
    /**
	 * �����ѯȫ��gather����
	 *
	 * @return ��ѯȫ����sql���<br>
	 *         int_id �ɼ���� <br>
	 *         str_name �ɼ�����<br>
	 *         str_desc ������Ϣ<br>
	 *         int_produnitid ������Ԫ��<br>
	 *         int_materielruleid ���Ϻ�
	 */
	String getAllGatherDESC();

	/**
	 * ͨ����Ų�ѯgather
	 * 
	 * @param str_name
	 *            �ɼ�����
	 * @return ��ѯ��ŵ�sql���
	 */
	String getGatherByName(String str_name);
	
	/**
	 * ���ɼ�������gather�����Ƿ����д����ϱ�ʶ�����
	 * 
	 * @param materialId
	 *            �����ϱ�ʶ����� <br>
	 * @param gatherid
	 *            �ɼ���� <br>
	 * @return ˳��Ÿ���
	 */
	String checkGatherItemCountByMaterialId(int gatherid, int materialId);

	//--------------------------------------------������Ŀ���----------------------------------------------------------------------
	
	/**
	 * ��Ӳɼ���������Ĺ�ϵ
	 * @param g
	 * @param q
	 * @param order
	 * @return
	 */
	String saveGather_Q(int g,int q,int order);
	
    /**
     * ɾ���ɼ����������ϵ
     * @param g
     * @return
     */
    String delGather_Q(int g);
} 
