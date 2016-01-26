package com.qm.mes.pm.dao;

import com.qm.mes.pm.bean.TechDocItem;
import com.qm.mes.pm.bean.TechItemFile;
import com.qm.mes.pm.bean.TechnologyDoc;


/**
 * ���ղ���˵����DAO�ӿ���
 * 
 * @author YuanPeng
 *
 */
public interface DAO_TechnologyDoc {
	
	/**
	 * �������ղ���˵�����sql���
	 * 
	 * @param technogyDoc ���ղ���˵�������
	 * @return
	 */
	public String saveTechnologyDoc(TechnologyDoc technogyDoc);
	
	/**
	 * ͨ����Ų�����ղ���˵�����sql���
	 * 
	 * @param id ���ղ���˵�������
	 * @return ͨ����Ų�����ղ���˵�������
	 */
	public String getTechnologyDocById(int id);
	
	/**
	 * ͨ�����ɾ�����ղ���˵�����sql���
	 * 
	 * @param id ���ղ���˵�������
	 * @return
	 */
	public String delTechnologyDocById(int id);
	
	
	/**
	 * �����ѯ���й��ղ���˵����
	 * 
	 * @return �����ѯ���й��ղ���˵������б�
	 */
	public String getAllTechnologyDocsByDESC();
	
	/**
	 * ��ѯ���й��ղ���˵����
	 * 
	 * @return �����ѯ���й��ղ���˵������б�
	 */
	public String getAllTechnologyDocs();
	
	/**
	 * ͨ�����ղ���˵��������ѯ���
	 * 
	 * @param name
	 * @return ͨ�����ղ���˵��������ѯ�����
	 */
	public String getTechnologyDocIdByName(String name);
	
	/**
	 * ͨ�����ղ���˵��������ѯװ��ָʾ������
	 * 
	 * @param name ���ղ���˵������ 
	 * @return ���ղ���˵��������
	 */
	public String getTechnologyDocCountByName(String name);
	
	/**
	 * ���¹��ղ���˵����
	 * 
	 * @param technologyDoc ���ղ���˵����
	 * @return 
	 */
	public String updateTechnologyDoc(TechnologyDoc technologyDoc);
	
	/**
	 * ͨ����Ʒ����ʾ��ѯ���ղ���˵��������
	 * 
	 * @param materiel ��Ʒ����ʾ
	 * @return ���ղ���˵��������
	 */
	public String getTechnologyDocCountByMateriel(String materiel);
	
	
	
	
	
	
	
	
	
	
	/**
	 * �������ղ�����
	 * 
	 * @param techDocItem ���ղ�����
	 * @return
	 */
	public String saveTechDocItem(TechDocItem techDocItem);
	
	/**
	 * ͨ�����ղ���˵����Ų�����ղ������б��sql���
	 * 
	 * @param id ���ղ���˵�����
	 * @return ͨ�����ղ���˵����Ų�����ղ������б�
	 */
	public String getTechDocItemByTechnologyDocId(int id);
	
	/**
	 * ͨ�����ղ���˵�����ɾ�����ղ������sql���
	 * 
	 * @param id ���ղ���˵�����
	 * @return
	 */
	public String delTechDocItemByTechnologyDocId(int id);
	
	/**
	 * ��ѯ���ղ�����������
	 * 
	 * @return ���ղ�����������
	 */
	public String getTechDocItemMaxId();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * ͨ�����ղ�������Ų�ѯ���ղ������ļ�
	 * 
	 * @param techDocItemId ���ղ��������
	 * @return ���ղ������ļ�
	 */
	public String getTechItemFileByTechDocItemId(int techDocItemId);
	
	/**
	 * �������ղ������ļ�
	 * 
	 * @param tif ���ղ������ļ�����
	 * @return 
	 */
	public String saveTechItemFile(TechItemFile tif);
	
	/**
	 * ͨ�����ղ�����ɾ�����ղ������ļ�
	 * 
	 * @param techDocItemId	���ղ��������
	 * @return
	 */
	public String delTechItemFile(int techDocItemId);
	
	/**
	 * ͨ�����ղ���˵�������ɾ�����ղ������ļ�
	 * 
	 * @param techDocId  ���ղ���˵�������
	 * @return
	 */
	public String delTechItemFileByTechDoc(int techDocId);
	
	/**
	 * ͨ���ɹ��ղ�������Ÿ����¹��ղ�����
	 * 
	 * @param oldItemId �ɹ��ղ�����
	 * @return
	 */
	public String updateTechItemId(int oldItemId);
}