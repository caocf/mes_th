package com.qm.mes.os.dao;
import com.qm.mes.os.bean.*;
public interface DAO_WorkSchedle {

	/**��������ʱ�̱�
	 * author : ������
	 */
	String saveWorkSchedle(WorkSchedle  workSchedle);
	
	/**������й���ʱ�̱�
	 * author : ������
	 */
	String getAllWorkSchedle();
	
	/**ͨ��IDɾ������ʱ�̱�
	 * author : ������
	 */
	String deleteWorkSchedleById(int id);
	
	/**ͨ��ID��ù���ʱ�̱�
	 * author : ������
	 */
	String getWorkSchedleById(int id);
	
	/**�޸Ĺ���ʱ�̱�
	 * author : ������
	 */
	String updateWorkSchedle(WorkSchedle  workSchedle);
	
	/**��ù���ʱ�̱���������Ԫ��
	 * author : ������
	 */
	String getprodunitid();
	/**��ù���ʱ�̱��а��
	 * author : ������
	 */
	String getworkOrder();
	
	/**��ȥ��ID��ѯ��ù���ʱ�̱���������Ԫ�����ڸ����ж�
	 * author : ������
	 */
	String getprodunitidById(int id);
	
	/**��ȥ��ID��ѯ��ù���ʱ�̱��а�����ڸ����ж�
	 * author : ������
	 */
	String getworkOrderById(int id);
	
	/**���������ǰ��
	 * author : ������
	 */
	String getAllAdvanceTime();
	
	/**ͨ��ID��ѯ������Ԫ��������ж�ɾ������
	 * author : ������
	 */
	String getProdunitidOrderById(int id);
	
	 /**ͨ��������Ԫ��β�ѯ����ʱ�̱�����ɾ��������ж�
	 * author : ������
	 */
	String getSchedleByProdunitidOrder(int Produnitid,String Order);
	
	/**
	 *ͨ��������Ԫ��id����ȡ�����Ϣ л����
	 */
	String getworkOrderByprodunitid(int id);
	//
	/**
	 *ͨ��������Ԫ�������ѯ����ʱ�����ǰ��  л����
	 */
	String getworkschedleadtime(int produnitid,String workorder);
	/**ͨ��������Ԫ��ѯ����ʱ��
	 * author : ������
	 */
	String getWorkSchedelByProuunitid(int str_produceunit);
	
	/**����IDͨ��������Ԫ��ѯ����ʱ��
	 * author : ������
	 */
	String getWorkSchedelByProuunitidAndID(int str_produceunit,int int_id);
	

	
}
