package mes.ra.dao;

import mes.ra.bean.ConversionState;

public interface DAO_ConversionState {
	/**
	 * ��ѯ״̬ת����Ϣ
	 * 
	 * @return sql���<br>
	 *         int_id ��� <br>
	 *         INT_FROMSTATE ԭ״̬��<br>
	 *         INT_TOSTATE ��ת״̬��<br>
	 *         STR_DESC ������Ϣ<br>
	 */
	String getAllConversionState();
	/**
	 * ����Conversionstate
	 * 
	 * @param conversionstate
	 * @return ����conversionstate��sql���
	 */
	String saveConversionState(ConversionState ConversionState);

	/**
	 * ͨ����Ų�ѯconversionstate
	 * 
	 * @param id
	 * @return ��ѯconversionstate��sql��� <br>
	 *         int_id ��� <br>
	 *         INT_FROMSTATE ԭ״̬��<br>
	 *         INT_TOSTATE ��ת״̬��<br>
	 *         STR_DESC ������Ϣ<br>
	 */
	String getConversionStateById(int id);

	/**
	 * ͨ�����ɾ��conversion
	 * 
	 * @param id
	 * @return ɾ��conversionstate��sql���
	 */
	String delConversionStateById(int id);

	/**
	 * ����conversionstate����ͨ����id
	 * 
	 * @param conversionstate
	 * @return ����conversionstate��sql���
	 */
	String updateConversionState(ConversionState ConversionState);
	/**
	 * ͨ��������ѯconversionstate
	 * 
	 * @param str_desc
	 * @return ��ѯ�ض������sql���
	 */
	 String getConversionStateByDesc(String str_desc);

	 /**
	  * ͨ��������ԪID��ѯ״̬����
	  * 
	  * @param ProduceUnitId ������Ԫ��
	  * @return
	  */
	 public String getConversionStateByProdUnitId(int ProduceUnitId);


}
