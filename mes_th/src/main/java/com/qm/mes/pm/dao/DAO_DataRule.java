package com.qm.mes.pm.dao;

import com.qm.mes.pm.bean.DataRule;
import com.qm.mes.pm.bean.DataRuleArg;


/**
 * @author Xujia
 *
 */
public interface DAO_DataRule {
	/**
	 * �������ݹ����sql���
	 * @param dataRule
	 * @return
	 */
	String saveDataRule(DataRule dataRule);
	/**
	 * ͨ����Ų�����ݹ����sql���
	 * @param id
	 * @return
	 */
	String getDataRuleById(int id);
	/**
	 * ͨ����Ų�����ݹ��������sql���
	 * @param id
	 * @return
	 */
	String getDataRuleArgsById(int id);
	/**
	 * ͨ�����ɾ�����ݹ����sql���
	 * @param id
	 * @return
	 */	
	String delDataRuleById(int id);
	/**
	 * ͨ�����ɾ�����ݹ��������sql���
	 * @param id
	 * @return
	 */	
	String delDataRuleArgsById(int id);
	/**
	 * ��ѯ��ȫ�����ݹ����sql���
	 * @return
	 */
	String getAllDataRule();
	/**
	 * ͨ�����Ʋ�����ݹ����sql���
	 * @param name
	 * @return
	 */
	String getDataRuleByName(String name);
	/**
	 * ����dataRule����ͨ����id����
	 *  ���
	 * @param dataRule
	 * @return ����dataRule��sql���
	 */
	String updateDataRule(DataRule dataRule); 
	/**
	 * �������������sql���
	 * @param dataRule
	 * @return
	 */
	String saveDataRuleArg(DataRuleArg dataRuleArg);
	
}
