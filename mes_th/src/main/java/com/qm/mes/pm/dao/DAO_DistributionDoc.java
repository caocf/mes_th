package com.qm.mes.pm.dao;

import com.qm.mes.pm.bean.DistriItem;
import com.qm.mes.pm.bean.DistributionDoc;

/**
 * ����ָʾ��DAO�ӿ���
 * 
 * @author YuanPeng
 *
 */
public interface DAO_DistributionDoc {

	/**
	 * ��������ָʾ����sql���
	 * 
	 * @param distributionDoc ����ָ��������
	 * @return
	 */
	public String saveDistributionDoc(DistributionDoc distributionDoc);
	
	/**
	 * ͨ����Ų������ָʾ����sql���
	 * 
	 * @param id ����ָʾ�����
	 * @return ͨ����Ų��װ��ָʾ������
	 */
	public String getDistributionDocById(int id);
	
	/**
	 * ͨ�����ɾ������ָʾ����sql���
	 * 
	 * @param id ����ָʾ�����
	 * @return
	 */
	public String delDistributionDocById(int id);
	
	/**
	 * ͨ�����ϱ�ʾ�������ָʾ����sql���
	 * 
	 * @param materiel ���ϱ�ʾ 
	 * @return ͨ�����ϱ�ʾ�������ָʾ������
	 */
	public String getDistributionDocByMateriel(String materiel);
	
	/**
	 * �����ѯ��������ָʾ��
	 * 
	 * @return �����ѯ��������ָʾ�����б�
	 */
	public String getAllDistributionDocsByDESC();
	
	/**
	 * �����ѯ��������ָʾ��
	 * 
	 * @return �����ѯ��������ָʾ�����б�
	 */
	public String getAllDistributionDocs();
	
	/**
	 * ͨ������ָʾ������ѯ���
	 * 
	 * @param name
	 * @return ͨ������ָʾ������ѯ�����
	 */
	public String getDistributionDocIdByName(String name);
	
	/**
	 * ͨ������ָʾ������ѯ����ָʾ������
	 * 
	 * @param name ����ָʾ���� 
	 * @return ����ָʾ������
	 */
	public String getDistributionDocCountByName(String name);
	
	/**
	 * ��������ָʾ��
	 * 
	 * @param distributionDoc ����ָʾ������
	 * @return 
	 */
	public String updateDistributionDoc(DistributionDoc distributionDoc);
	
	/**
	 * ͨ������������Ԫ�Ų�ѯ����ָʾ��
	 * 
	 * @param requestProUnitId ����������Ԫ��
	 * @return ͨ������������Ԫ�Ų�ѯ����ָʾ���б�
	 */
	public String getDistributionDocsByRequestProUnitId(int requestProUnitId);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * ��������ָʾ����
	 * 
	 * @param distriItem �������������
	 * @return
	 */
	public String saveDistriItem(DistriItem distriItem);
	
	/**
	 * ͨ������ָʾ���Ų�������������б��sql���
	 * 
	 * @param id ����ָʾ����
	 * @return ͨ������ָʾ���Ų�������������б�
	 */
	public String getDistriItemByDistributionDocId(int id);
	
	/**
	 * ͨ������ָʾ����ɾ�������������sql���
	 * 
	 * @param id ����ָʾ�����
	 * @return
	 */
	public String delDistriItemByDistributionDocId(int id);
}
