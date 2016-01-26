package com.qm.mes.tg.dao;

import com.qm.mes.tg.bean.MaterielRule;

public interface IDAO_MaterielRule {

	/**
	 * ͨ���ɼ�����ţ�����òɼ��������������֤�����б�
	 * 
	 * @param id
	 *            �ɼ������ <br>
	 * @return 1��int_gatherid �ɼ����<br>
	 *         2��int_materielruleid �����ϱ�ʶֵ(������)<br>
	 *         3��int_order �ɼ�˳���<br>
	 *         4��int_id ���ϱ�ʶ�����<br>
	 *         5��str_name ���ϱ�ʶ������<br>
	 *         6��str_validateclass ���ϱ�ʶ������֤�ַ��� <br>
	 *         7��desc ��֤�ַ�����������Ϣ
	 */
	String getAttributeByGid(int id);

	/**
	 * ͨ���ɼ�����ţ�����òɼ��������ϱ�ʶ����
	 * 
	 * @param id
	 *            �ɼ������
	 * @return 1��int_id ���ϱ�ʶ��<br>
	 *         2��str_name ���ϱ�ʶ��<br>
	 *         3��str_validateclass ��֤�ַ��� <br>
	 *         4��desc ��֤�ַ�����������Ϣ
	 */
	String gerMainRuleByGid(int id);

	/**
	 * ������ϱ�ʶ����
	 * 
	 * @param materielRule
	 *            ʵ�����
	 * @return ����materielRule��sql���
	 */
	String saveMainRule(MaterielRule materielRule);

	/**
	 * �޸����ϱ�ʶ����
	 * 
	 * @param materielRule
	 *            ʵ�����
	 * @return �޸�materielRule��sql���
	 */
	String updateMainRule(MaterielRule materielRule);

	/**
	 * ɾ�����ϱ�ʶ����
	 * 
	 * @param id
	 *            ���ϱ�ʶid
	 * @return ɾ��materielRule��sql���
	 */
	String deleteMainRule(int id);

	/**
	 * ��ѯ�������ϱ�ʶ����
	 * 
	 * @param id
	 *            ���ϱ�ʶid
	 * @return 1��int_id ���ϱ�ʶ��<br>
	 *         2��str_name ���ϱ�ʶ��<br>
	 *         3��str_validateclass ��֤�ַ��� <br>
	 *         4��desc ��֤�ַ�����������Ϣ
	 */
	String findMainRule(int id);

	/**
	 * ��ѯ���ϱ�ʶ����
	 * 
	 * @return 1��int_id ���ϱ�ʶ��<br>
	 *         2��str_name ���ϱ�ʶ��<br>
	 *         3��str_validateclass ��֤�ַ��� <br>
	 *         4��desc ��֤�ַ�����������Ϣ
	 */
	String selectMainRule();

    /**
     * ͨ�������ѯ���ϱ�ʶ����
	 *
	 * @return 1��int_id ���ϱ�ʶ��<br>
	 *         2��str_name ���ϱ�ʶ��<br>
	 *         3��str_validateclass ��֤�ַ��� <br>
	 *         4��desc ��֤�ַ�����������Ϣ
     */
    String selectMainRuleDESC();

	/**
	 * ��ѯ�������ϱ�ʶ����
	 * 
	 * @param name
	 *            ���ϱ�ʶname
	 * @return 1��int_id ���ϱ�ʶ��<br>
	 *         2��str_name ���ϱ�ʶ��<br>
	 *         3��str_validateclass ��֤�ַ��� <br>
	 *         4��desc ��֤�ַ�����������Ϣ
	 */
	String findMainRuleByName(String name);

	/**
	 * ���ɼ���gather�����Ƿ��д����ϱ�ʶ����ŵĹ���
	 * 
	 * @param id
	 *            ���ϱ�ʶ����id <br>
	 * @return �����ĸ���
	 */
	String checkGather(int id);

	/**
	 * ���ɼ���gatherItem�����Ƿ��д����ϱ�ʶ����ŵĹ���
	 * 
	 * @param id
	 *            ���ϱ�ʶ����id <br>
	 * @return �����ĸ���
	 */
	String checkGatherItem(int id);
	
}
