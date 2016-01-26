package com.qm.mes.ra.dao;
import com.qm.mes.ra.bean.*;
/**
 * author : л����
 */
public interface DAO_InstructionVersion {
	  
	/**����ָ��汾
	 * author : л����
	 */
	String saveVersion(Version version);
	/**ͨ���汾�ŵõ��汾
	 * author : л����
	 */
	
	String getVersion(String versionCode);

	/**ɾ��ָ��汾
	 * author : л����
	 */
	String delVersionById(int id);
	/**��ѯ�涨�������ڵ�ָ��汾����
	 * author : л����
	 */
	String getVersionsByDate(String str_date);
	/**
	 * ͨ��������Ԫ��ѯָ��汾����
	 * author : л����
	 */
	String getVersionsByProduceUnit(String produceUnit);
	/**
	 * ͨ��������Ԫ���������ڲ����Ӧ�İ汾����
	 * author : л����
	 */
	
	String getVersionsByDateAndUnit(String date,String unit);
	
	/**
	 * ������а汾����
	 * author : л����
	 */
	
	String getAllVersions();
	/**
	 * ������а汾����
	 * author : л����
	 */
	String getVersionsCount();
	/**
	 * ͨ���汾�Ų�ѯ
	 * author : л����
	 */
	String getVersionByid(int int_id);
	/**
	 * л����
	 * ͨ��ģ����ѯ�汾�ŷ�����Ӧ�Ľ��
	 */
   String getVersionbylike(String versioncode);
   /**
	 * @author ���ݰ汾�Ÿİ汾��ע��Ϣ
	 *
	 */
	String upversiondescriptionbyvcode(String versioncode,String description);
	
}
