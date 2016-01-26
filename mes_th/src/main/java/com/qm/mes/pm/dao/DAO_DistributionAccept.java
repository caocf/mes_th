package com.qm.mes.pm.dao;

import com.qm.mes.pm.bean.DistributionAccept;

/**
 * ����ȷ�ϵ�DAO�ӿ���
 * 
 * @author YuanPeng
 *
 */
public interface DAO_DistributionAccept {

	/**
	 * ��������ȷ�ϵ���sql���
	 * 
	 * @param distributionAccept װ��ָ��������
	 * @param CreateUID �����û�ID
	 * @return
	 */
	public String saveDistributionAccept(DistributionAccept distributionAccept);
	
	/**
	 * ͨ��ID��������ȷ�ϵ�
	 * 
	 * param id ���
	 * @param userid �û�ID
	 * return  
	 */
	public String transactDistributionAccept(int id,int userid);
	
	/**
	 * ͨ����Ų������ȷ�ϵ���sql���
	 * 
	 * @param id ����ȷ�ϵ����
	 * @return ͨ����Ų������ȷ�ϵ�����
	 */
	public String getDistributionAcceptById(int id);
	
	/**
	 * �����ѯ��������ȷ�ϵ�
	 * 
	 * @return �����ѯ��������ȷ�ϵ����б�
	 */
	public String getAllDistributionAcceptsByDESC();
	
	/**
	 * ͨ������ָʾ����Ӧ������Ԫ��ѯ����ȷ�ϵ�
	 * 
	 * @return ͨ������ָʾ����Ӧ������Ԫ��ѯ����ȷ�ϵ����б�
	 */
	public String getDistributionAcceptsByresponseProUnit(int responseProUnit);
}
