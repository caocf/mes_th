package com.qm.mes.pm.dao;

import com.qm.mes.pm.bean.ProLineItem;
import com.qm.mes.pm.bean.ProduceUnitLine;

/**
 * ������Ԫ��������DAO�ӿ���
 * 
 * @author YuanPeng
 *
 */
public interface DAO_ProduceUnitLine {

	/**
	 * ����������Ԫ�������õ�sql���
	 * 
	 * @param ProduceUnitLine װ��ָ��������
	 * @param CreateUID �����û�ID
	 * @return
	 */
	public String saveProduceUnitLine(ProduceUnitLine ProduceUnitLine);
	
	/**
	 * ͨ����Ų��������Ԫ�������õ�sql���
	 * 
	 * @param id װ��ָ�������
	 * @return ͨ����Ų��������Ԫ�������ö���
	 */
	public String getProduceUnitLineById(int id);
	
	/**
	 * ͨ�����ɾ��������Ԫ�������õ�sql���
	 * 
	 * @param id װ��ָ�������
	 * @return
	 */
	public String delProduceUnitLineById(int id);
	
	
	/**
	 * �����ѯ����������Ԫ��������
	 * 
	 * @return �����ѯ����������Ԫ�������õ��б�
	 */
	public String getAllProduceUnitLinesByDESC();
	
	/**
	 * �����ѯ����������Ԫ��������
	 * 
	 * @return �����ѯ����������Ԫ�������õ��б�
	 */
	public String getAllProduceUnitLines();
	
	/**
	 * ͨ��������Ԫ������������ѯ���
	 * 
	 * @param name
	 * @return ͨ��������Ԫ������������ѯ�����
	 */
	public String getProduceUnitLineIdByName(String name);
	
	/**
	 * ͨ��������Ԫ������������ѯ������Ԫ������������
	 * 
	 * @param name ������Ԫ���������� 
	 * @return ������Ԫ������������
	 */
	public String getProduceUnitLineCountByName(String name);
	
	/**
	 * ����������Ԫ��������
	 * 
	 * @param ProduceUnitLine ������Ԫ�������ö���
	 * @return 
	 */
	public String updateProduceUnitLine(ProduceUnitLine ProduceUnitLine);
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * ����������Ԫ�������ú�
	 * 
	 * @param ProLineItem ������Ԫ�������ݶ���
	 * @return
	 */
	public String saveProLineItem(ProLineItem ProLineItem);
	
	/**
	 * ͨ��������Ԫ�������úŲ��������Ԫ���������б��sql���
	 * 
	 * @param id ������Ԫ�������ú�
	 * @return ͨ��������Ԫ�������úŲ��������Ԫ���������б�
	 */
	public String getProLineItemByProduceUnitLineId(int id);
	
	/**
	 * ͨ��������Ԫ�������ú�ɾ��������Ԫ�������ݵ�sql���
	 * 
	 * @param id ������Ԫ�����������
	 * @return
	 */
	public String delProLineItemByProduceUnitLineId(int id);
}
