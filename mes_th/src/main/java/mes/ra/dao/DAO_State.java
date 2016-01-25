package mes.ra.dao;

import mes.ra.bean.State;

public interface DAO_State {
	/**
	 * ����state
	 *  ���
	 * @param state
	 * @return ����state��sql���
	 */
	String saveState(State state);

	/**
	 * ͨ����Ų�ѯstate
	 *  ���
	 * @param id
	 * @return ��ѯstate��sql��� <br>
	 *         int_id ��� <br>
	 *         Str_statename ״̬��<br>
	 *         Str_style ��ʽ<br>
	 *         Int_delete ɾ����ʶ<br>
	 *         Str_styledesc ��ʽ����<br>
	 */
	String getStateById(int id);

	/**
	 * ͨ�����ɾ��gather
	 *  ���
	 * @param id
	 * @return ɾ��state��sql���
	 */
	String delStateById(int id);

	/**
	 * ����state����ͨ����id����
	 *  ���
	 * @param state
	 * @return ����state��sql���
	 */
	String updateState(State state);

	/**
	 * ��ѯȫ��state����
	 *  ���
	 * @return ��ѯȫ��state��sql���<br>
	 *         int_id ��� <br>
	 *         Str_statename ״̬��<br>
	 *         Str_style ��ʽ<br>
	 *         Int_delete ɾ����ʶ<br>
	 *         Str_styledesc ��ʽ����<br>
	 */
	String getAllState();

    /**
	 * �����ѯȫ��state����
	 * ���
	 * @return �����ѯȫ��state��sql���<br>
	 *         int_id ��� <br>
	 *         Str_statename ״̬��<br>
	 *         Str_style ��ʽ<br>
	 *         Int_delete ɾ����ʶ<br>
	 *         Str_styledesc ��ʽ����<br>
	 */
	public String getAllStateDESC();
	
	/**
	 * ͨ�����Ʋ�ѯstate
	 *  ���
	 * @param stateName
	 * @return ��ѯ�ض������sql���
	 */
	String getStateByName(String StateName);

	/**
	 * ���produceunit�����Ƿ��д�״̬�ŵĹ���
	 *  ���
	 * @param id
	 * @return ��������
	 */
	String checkProduceUnitById(int id);
	
	/**
	 * ���state�����Ƿ��д�״̬�ŵĹ���
	 *  ���
	 * @param id
	 * @return ��������
	 */
	String checkStateById1(int id);
	/**
	 * ���state�����Ƿ��д�״̬�ŵĹ���
	 *  ���
	 * @param id
	 * @return ��������
	 */
	String checkStateById2(int id);

	/**
	 * ��֤״̬����Ϊname��״̬�Ƿ��Ѿ�����
	 *  ���
	 * @param statename
	 * @return ˳��Ÿ���
	 */
	String checkStateByName(String StateName);


	
	
	
	
	
	/**
	 * �鿴���е�״̬װ����ϵ
	 * 
	 * л����
	 */
	String getAllStateConversion();
	
	/**
	 *�����ɼ���״̬����
	 * 
	 * л����
	 */
	String saveGatherStateRule(int int_gatherid,int Stateconversionid,int defaultgo);
	/**
	 *ͨ���ɼ����id����ѯ��ɼ�����йص�״̬��id��
	 * 
	 * л����
	 */
	String getstateIdbygatherid(int int_gatherid);
	/**
	 * ͨ���ɼ����id�鿴״̬װ����ϵ
	 * 
	 * л����
	 */
	String getgatherStateRule(int int_gatherid);
	/**
	 * ͨ���ɼ����id�鿴Ĭ��״̬װ����ϵ��
	 * 
	 * л����
	 */
	String getconversionidBy(int int_gatherid);
	/**
	 * ��ɾ���ɼ���Ĺ���
	 * 
	 * @param int_gatherid �ɼ����id
	 *  л����
	 * @return ��ѯ��ŵ�sql���
	 */
	  String delgatherstaterule(int int_gatherid);
	
}
