package com.qm.mes.tg.dao;

public interface IDAO_QueryUnit {
	/**
	 * ����·����ѯ
	 * 
	 * @param materielvalue
	 *            �����ϱ�ʶ����ֵ <br>
	 * @return str_materielValue �����ϱ�ʶֵ<br>
	 *         dat_Date ����ʱ��<br>
	 *         str_date �ض���ʽʱ��<br>
	 *         STR_MATERIELNAME ���ϱ�ʶ������<br>
	 *         INT_GATHERID �ɼ����<br>
	 *         INT_USERID ��¼�û���
	 */
	String QueryProducePathByMaterielValue(String materielvalue);

	/**
	 * ������Ԫ�깤��ѯ
	 * 
	 * @param produnitId
	 *            ������Ԫid(���԰���������ѯ������Ĭ���ǵ����깤)���в�ѯ <br>
	 * @param strDate
	 *            ��ʼ����
	 * @param endDate
	 *            ��ֹ����
	 * @return str_materielValue �����ϱ�ʶֵ<br>
	 *         dat_Date ����ʱ��
	 */
	String QueryProduceUnitComplete(int produnitId, String strDate,
			String endDate);

	/**
	 * ��Ʒ��ϵ��ѯ<br>
	 * ����ѯ�����״��ʾ�������������������������Ʒ�������ϣ����ݹ�������ʾ�� <br>
	 * 
	 * @param materielvalue
	 *            �����ϱ�ʶ����� <br>
	 * @return gr.str_materielValue �����ϱ�ʶ����ֵ<br>
	 *         pr.str_materielValue �����ϱ�ʶ����ֵ<br>
	 *         gr.DAT_DATE ����ʱ��<br>
	 *         str_date ���ϸ�ʽ�Ĺ���ʱ��<br>
	 *         gr.int_gatherid �ɼ���id<br>
	 *         ga.str_name �ɼ�����<br>
	 *         pr.str_materielname �����ϱ�ʶֵ�����ϱ�ʶ������<br>
	 */
	String QueryProductRecordByMaterielValue(String materielvalue);

	/**
	 * ��Ʒ��ϵ��ѯ�ǵ������ϱ�ʶ����ŵ���ϸ����<br>
	 * ����ѯ�����״��ʾ�������������������������Ʒ�������ϣ����ݹ�������ʾ�� <br>
	 * 
	 * @param materielvalue
	 *            �����ϱ�ʶ����� <br>
	 * @return str_materielValue �����ϱ�ʶ����ֵ<br>
	 *         DAT_DATE ����ʱ��<br>
	 *         str_date ���ϸ�ʽ�Ĺ���ʱ��<br>
	 *         int_gatherid �ɼ���id<br>
	 *         ga.str_name �ɼ�����<br>
	 *         gr.str_materielname �����ϱ�ʶֵ�����ϱ�ʶ������<br>
	 * 
	 */
	String QueryProductRecord_MaterialValue(String materialvalue);

	/**
	 * ���߲�Ʒ��ѯ<br>
	 * �������ͨ����ʼ�ɼ����ûͨ����ֹ�ɼ���������ϵ���Ϣ��<br>
	 * ����ѯ���������ڡ��������ʾ�������߲ɼ��㲢û�о�����ֹ�ɼ���������ϵ���Ϣ��
	 * 
	 * @param date
	 *            ���� <br>
	 * @param gatherid
	 *            �ɼ���id
	 * @return gr.INT_ID �����¼id<br>
	 *         gr.INT_GATHERID �ɼ���id<br>
	 *         gr.STR_MATERIELVALUE �����ϱ�ʶֵ<br>
	 *         gr.INT_USERID �û�<br>
	 *         gr.DAT_DATE ����ʱ��<br>
	 *         gr.STR_MATERIELNAME ���ϱ�ʶ������<br>
	 *         ga.str_name �ɼ�����<br>
	 * 
	 */
	String QueryOnlineProduct(String date, int gatherid);

	/**
	 * ��ѯ�ӿ�ʼ���ڵ���������֮�������
	 * 
	 * @param date1
	 *            ��ʼ����<br>
	 * @param date2
	 *            ��������<br>
	 * @return
	 * @deprecated
	 */
	String QueryDays(String date1, String date2);

	/**
	 * ����·����ѯ
	 * 
	 * @return str_materielValue �����ϱ�ʶֵ<br>
	 *         dat_Date ����ʱ��<br>
	 *         str_date �ض���ʽʱ��<br>
	 *         STR_MATERIELNAME ���ϱ�ʶ������<br>
	 *         INT_GATHERID �ɼ����<br>
	 *         INT_USERID ��¼�û���
	 * @deprecated ҳ���ϲ��ô˷�����
	 */
	String QueryAllProducePath();
	/**
	  * ���
	  * ������Ԫ�깤��ѯ����������ĩ״̬	 
	  * @return sql
	  * @prama ������Ԫ
	  */
    String QueryAllstate(int pruduceid);
    /**
	  * ���
	  * ��ָ���õ�����������Ԫ��ָ��״̬����ֹ���ڵ���Ϣ
	  * @return sql
	  * @prama ������Ԫ��״̬������
	  */
    String QueryAllinstruction(int produnitId, String wh,String strDate,String endDate);

}
