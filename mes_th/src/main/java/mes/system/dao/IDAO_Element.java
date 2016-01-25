package mes.system.dao;

import mes.system.elements.IElement;

public interface IDAO_Element {

	/**
	 * ����Ԫ�ػ�����Ϣ��
	 * <p>
	 * discard����ֵ�á�0����<br>
	 * version���汾�š�1��<br>
	 * updateDatetime��ȡϵͳʱ��
	 * 
	 * @param name
	 * @param description
	 * @param updateUserId
	 * @return
	 */
	public String getSQL_innerElement(String name, String description,
			int updateUserId);

	public String getSQL_innerElement(IElement element);

	public String getSQL_updateElement(IElement element);

	/**
	 * ����Ԫ�ػ�����Ϣ��
	 * <p>
	 * discard����ֵ��ɾ������ʱ���޸ģ�<br>
	 * version���汾���Զ����£��������޸�<br>
	 * updateDatetime��ȡϵͳʱ��
	 * 
	 * @param name
	 *            ����name��ʶҪ�޸ĵĶ���
	 * @param description
	 * @param updateUserId
	 * @return ����Ԫ�ػ�����Ϣʹ�õ�SQL���
	 */
	public String getSQL_updateElement(String name, String description,
			int updateUserId);

	/**
	 * ɾ��Ԫ�ء���������ֵ�á�1��
	 * 
	 * @param id
	 *            Ŀ��Ԫ��id
	 * @return ����ɾ��Ŀ���������͵�sql���
	 */
	public String getSQL_deleteElement(int id);

	/**
	 * ɾ��Ԫ�ء���������ֵ�á�1��
	 * 
	 * @param name
	 *            Ԫ����
	 * @return ����ɾ��Ŀ���������͵�sql���
	 */
	public String getSQL_deleteElement(String name);

	/**
	 * ͨ��nameֵ����Ԫ��
	 * 
	 * @param name
	 * @return ����sql���
	 */
	public String getSQL_queryElement(String name);
	
	public String getSQL_queryElementCount(String name);
}
