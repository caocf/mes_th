package com.qm.mes.pm.dao;

/**
 * Bom  DAO�ӿ���
 * 
 * @author YuanPeng
 *
 */
public interface DAO_Bom {
	
	/**
	 * ͨ��BOM��Ų�ѯBOM����
	 * 
	 * @param id	BOM���
	 * @return	BOM����
	 */
	public String getBomById(int id);
	
	/**
	 * ͨ�������ʾ��ѯBOM�����б�
	 * 
	 * @param component �����ʾ
	 * @return  BOM�����б�
	 */
	public String getBomsBycomponent(String component);
	
	/**
	 *  ͨ���ϼ���ű�ʾ��ѯBOM����
	 * 
	 * @param parentid �ϼ���ű�ʾ
	 * @return BOM����
	 */
	public String getBomsByParentId(int parentid);
	
	/**
	 * 	�����ѯ����Bom
	 * 
	 * @return  �����ѯ����Bom�б�  
	 */
	public String getAllBomsByDESC();
	
	/**
	 * ͨ�������ʾ��ѯ�¼�BOM����
	 * 
	 * @param Component	�����ʾ
	 * @return ͨ�������ʾ��ѯ�¼�BOM�����б�
	 */
	public String getBomByComponent(String Component);
	
	/**
	 * ��ѯ����BOM�����ʶ�������ʶ����
	 * 
	 * @return ��ѯ����BOM�����ʶ�������ʶ�����б�
	 */
	public String getBomsGroupByComponent();
	
}
