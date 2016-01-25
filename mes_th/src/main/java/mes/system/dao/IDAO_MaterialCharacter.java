package mes.system.dao;

import mes.system.elements.IMaterialCharacter;

public interface IDAO_MaterialCharacter {

	String getSQL_innerElement(IMaterialCharacter e);

	String getSQL_queryElement(String name);

	String getSQL_queryElement(int id);

	/**
	 * @param name
	 *            ����������
	 * @return id ����������<br>
	 *         name ����������<br>
	 *         element_id Ԫ�غ�
	 */
	String getSQL_queryElementAll(String name);

	/**
	 * @return id ����������<br>
	 *         name ����������<br>
	 *         element_id Ԫ�غ�
	 */
	String getSQL_queryElementAll();

	/**
	 * 
	 * @param id
	 *            Ԫ�غ�<br>
	 * @return id Ԫ�غ� <br>
	 *         name ����������
	 */
	String getSQL_queryCharactersById(int id);

}
