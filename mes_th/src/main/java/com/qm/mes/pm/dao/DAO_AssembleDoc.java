package com.qm.mes.pm.dao;

import com.qm.mes.pm.bean.AssDocItem;
import com.qm.mes.pm.bean.AssembleDoc;

/**
 * װ��ָʾ��DAO�ӿ���
 * 
 * @author YuanPeng
 *
 */
public interface DAO_AssembleDoc {
	
	/**
	 * ����װ��ָʾ����sql���
	 * 
	 * @param assembleDoc װ��ָ��������
	 * @param CreateUID �����û�ID
	 * @return
	 */
	public String saveAssembleDoc(AssembleDoc assembleDoc);
	
	/**
	 * ͨ����Ų��װ��ָʾ����sql���
	 * 
	 * @param id װ��ָ�������
	 * @return ͨ����Ų��װ��ָʾ������
	 */
	public String getAssembleDocById(int id);
	
	/**
	 * ͨ�����ɾ��װ��ָʾ����sql���
	 * 
	 * @param id װ��ָ�������
	 * @return
	 */
	public String delAssembleDocById(int id);
	
	/**
	 * ͨ�����ϱ�ʾ���װ��ָʾ����sql���
	 * 
	 * @param materiel ���ϱ�ʾ 
	 * @return ͨ�����ϱ�ʾ���װ��ָʾ������
	 */
	public String getAssembleDocByMateriel(String materiel);
	
	/**
	 * �����ѯ����װ��ָʾ��
	 * 
	 * @return �����ѯ����װ��ָʾ�����б�
	 */
	public String getAllAssembleDocsByDESC();
	
	/**
	 * �����ѯ����װ��ָʾ��
	 * 
	 * @return �����ѯ����װ��ָʾ�����б�
	 */
	public String getAllAssembleDocs();
	
	/**
	 * ͨ��װ��ָʾ������ѯ���
	 * 
	 * @param name
	 * @return ͨ��װ��ָʾ������ѯ�����
	 */
	public String getAssembleDocIdByName(String name);
	
	/**
	 * ͨ��װ��ָʾ������ѯװ��ָʾ������
	 * 
	 * @param name װ��ָʾ���� 
	 * @return װ��ָʾ������
	 */
	public String getAssembleDocCountByName(String name);
	
	/**
	 * ����װ��ָʾ��
	 * 
	 * @param assembleDoc װ��ָʾ������
	 * @return 
	 */
	public String updateAssembleDoc(AssembleDoc assembleDoc);
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * ����װ��ָʾ����
	 * 
	 * @param assDocItem װ��ָʾ������
	 * @return
	 */
	public String saveAssDocItem(AssDocItem assDocItem);
	
	/**
	 * ͨ��װ��ָʾ���Ų��װ��ָʾ���б��sql���
	 * 
	 * @param id װ��ָʾ����
	 * @return ͨ��װ��ָʾ���Ų��װ��ָʾ���б�
	 */
	public String getAssDocItemByAssembleDocId(int id);
	
	/**
	 * ͨ��װ��ָʾ����ɾ��װ��ָʾ���sql���
	 * 
	 * @param id װ��ָʾ�����
	 * @return
	 */
	public String delAssDocItemByAssembleDocId(int id);
}
