package com.qm.mes.system.dao;

import com.qm.mes.system.elements.IMaterialType;

/**
 * �������͵�SQL������
 * 
 * @author �Ź��� 2008-3-6
 */
public interface IDAO_MaterialType {

	String getSQL_innerElement(IMaterialType materialtype);

	String getSQL_queryElement(String name);

	String getSQL_UpdateElement(IMaterialType materialtype);

	String getSQL_queryElement(int id);

	/**
	 * @param name
	 *            ����������
	 * @return id �������ͺ�<br>
	 *         name ����������<br>
	 *         element_id Ԫ�غ�
	 */
	String getSQL_queryElementAll(String name);

	/**
	 * @return id �������ͺ�<br>
	 *         name ����������<br>
	 *         element_id Ԫ�غ�
	 */
	String getSQL_queryElementAll();

	/**
	 * @param name
	 *            ����������
	 * @return id �������ͺ� <br>
	 *         name ���������� <br>
	 *         element_id Ԫ�غ�
	 */
	String getSQL_queryElementOtherType(int id);

	/**
	 * @param id
	 *            �������ͺ� <br>
	 *            parent_id ���������ͺ�<br>
	 * @return id �������ͺ� <br>
	 *         name ���������� <br>
	 *         element_id Ԫ�غ�
	 */
	//String getSQL_queryElementNotOwner(int id, int parent_id);

	/**
	 * @param name
	 *            ���������� <br>
	 * @return count ����
	 */
	String getSQL_queryElementCount(String name);

}
