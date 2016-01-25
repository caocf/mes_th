package mes.system.dao;

import mes.system.elements.IMaterialidentify;

public interface IDAO_MaterialIdentify {

	String getSQL_queryElement(int id);

	String getSQL_queryElement(String name);

	String getSQL_innerElement(IMaterialidentify materialidentify);

	String getSQL_UpdateElement(IMaterialidentify materialidentify);

	/**
	 * @param name
	 *            ���ϱ�ʶ��
	 * @return id ���ϱ�ʶ��<br>
	 *         name ���ϱ�ʶ��<br>
	 *         element_id Ԫ�غ�
	 */
	String getSQL_queryElementAll(String name);

	/**
	 * @return id ���ϱ�ʶ��<br>
	 *         name ���ϱ�ʶ��<br>
	 *         element_id Ԫ�غ�
	 */
	String getSQL_queryElementAll();

	/**
	 * 
	 * @param id
	 *            Ԫ�غ�<br>
	 * @return id Ԫ�غ� <br>
	 *         name ���ϱ�ʶ��
	 */
	String getSQL_queryIdentifyById(int id);

}
