package com.qm.mes.ra.dao;

import com.qm.mes.ra.bean.WorkTO;

public interface DAO_WorkTO {
	
	/**���������μ�¼��
	 * author : ������
	 */
	String saveWorkTO(WorkTO workto);
	
	/**ͨ��ID��ð����μ�¼��
	 * author : ������
	 */
	String getWorkTOById(int id);
	
	/**������а����μ�¼��
	 * author : ������
	 */
	String getAllWorkTO();
	
	/**�޸İ����μ�¼��
	 * author : ������
	 */
	String updateWorkTO(WorkTO  workto);
	
	/**ͨ��IDɾ�������μ�¼��
	 * author : ������
	 */
	String deleteWorkTOById(int id);

	/**��ð����μ�¼����������Ԫ��
	 * author : ������
	 */
	
	String getprodunitid();
	
	
	
	/**��ð����μ�¼���а��
	 * author : ������
	 */
	String getworkOrder();
	
	/**��ȥ��ID��ѯ��ð����μ�¼����������Ԫ�����ڸ����ж�
	 * author : ������
	 */
	String getprodunitidById(int id);
	
	
	
	/**��ȥ��ID��ѯ��ð����μ�¼���а�����ڸ����ж�
	 * author : ������
	 */
	String getworkOrderById(int id);
	
	/**ͨ��������Ԫ��id����ȡ��������Ϣ л����
	 * @param id
	 * @return
	 */
	String getworkOrderByprodunitid(int id);
	
	
	
}








