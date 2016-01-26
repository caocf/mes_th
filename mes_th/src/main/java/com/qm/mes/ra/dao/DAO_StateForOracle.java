package com.qm.mes.ra.dao;

import com.qm.mes.ra.bean.State;


public class DAO_StateForOracle implements DAO_State {
	/**
	 * ����state
	 * ���
	 * @param state
	 * @return ����state��sql���
	 */
	public String saveState(State state) {
		String sql = "insert into t_ra_state(int_id,str_statename,str_style,int_delete,str_styledesc) "
				+ "values(seq_ra_State.nextval,'"
				+state.getStateName()
				+"','"+state.getStyle()+"',"+state.getDelete()+",'"+state.getStyledesc()+"')";
	   
		return sql;
	}

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
	public String getStateById(int id) {
		String sql = "select int_id,str_statename,str_style,int_delete,str_styledesc from t_ra_state "
				+ "where int_id = " + id + " order by int_id";
		return sql;
	}

	/**
	 * ͨ�����ɾ��gather
	 *  ���
	 * @param id
	 * @return ɾ��state��sql���
	 */
	public String delStateById(int id) {
		String sql = "delete from t_ra_State where int_id=" + id;
		return sql;
	}

	/**
	 * ����state����ͨ����id����
	 *  ���
	 * @param state
	 * @return ����state��sql���
	 */
	public String updateState(State state) {
		String sql = "update t_ra_state set str_statename ='"
				+state.getStateName() + "' , str_style = '"+ state.getStyle()
				+ "',str_styledesc ='" + state.getStyledesc()
				+ "', int_delete = " +state.getDelete() + " where int_id = "
				+ state.getId();
		return sql;
	}

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
	public String getAllState() {
		String sql = "select int_id,str_statename,str_style,int_delete,str_styledesc"
				+ " from t_ra_state ";
		
		return sql;
	}

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
	public String getAllStateDESC() {
		String sql = "select int_id,str_statename,str_style,int_delete,str_styledesc  "
				+ "from t_ra_state where int_delete <> 1 order by int_id DEsc";
		return sql;
	}

	/**
	 * ͨ�����Ʋ�ѯstate
	 *  ���
	 * @param stateName
	 * @return ��ѯ�ض������sql���
	 */
	public String getStateByName(String stateName) {
		String sql = "select int_id,str_statename,str_style,int_delete,str_styledesc from t_ra_state"
				+ " where str_statename='" + stateName +"'order by int_id";
		
		return sql;
	}

	/**
	 * ��֤��Ԫ�Ƿ��Ѿ�����
	 *  ���
	 * @param id
	 * @return ˳��Ÿ���
	 */
	public String checkProduceUnitById(int id) {
		String sql = "select count(*) from  t_ra_state a, t_ra_produceunit b"
			+ " where a.int_id=" + id
			+ " and a.int_id = b.int_instructstateid and b.int_delete=0";
	return sql;

	}
	/**
	 * ��֤״̬�Ƿ��Ѿ�����
	 *  ���
	 * @param id
	 * @return ˳��Ÿ���
	 */
	public String checkStateById1(int id) {
		String sql = "select count(*) from  t_ra_state a, t_ra_stateconversion b"
			+ " where a.int_id=" + id
			+ " and a.int_id = b.int_fromstate";
	return sql;

	}
	/**
	 * ���state�����Ƿ��д�״̬�ŵĹ���
	 *  ���
	 * @param id
	 * @return ��������
	 */
	public String checkStateById2(int id){
		String sql = "select count(*) from  t_ra_state a, t_ra_stateconversion b"
			+ " where a.int_id=" + id
			+ " and a.int_id = b.int_tostate";
	return sql;

	}

	/**
	 * ��֤״̬����Ϊname��״̬�Ƿ��Ѿ�����
	 *  ���
	 * @param statename
	 * @return ˳��Ÿ���
	 */
	public String checkStateByName(String StateName) {
		String sql = "select count(*) from  t_ra_state " + " where str_name="
				+ StateName ;
		return sql;

	}

	
	
	
	
	
	
	/**
	 * �鿴���е�״̬װ����ϵ
	 * 
	 * л����
	 */
	public String   getAllStateConversion(){
		String sql="select a.int_id ,a.int_Fromstate,a.int_tostate ,b.STR_STATENAME as from1,c.STR_STATENAME as to1  from t_ra_stateconversion a"
			+" left join t_ra_state b on b.int_id = a.INT_FROMSTATE"
            +" left join t_ra_state c on c.int_id = a.INT_toSTATE  order by a.int_id asc";
		return sql;
	}
	/**
	 * ͨ���ɼ����id�鿴״̬װ����ϵ
	 * 
	 * л����
	 */
	public String getgatherStateRule(int int_gatherid){
		String sql="select a.int_id ,a.int_Fromstate,a.int_tostate ,b.STR_STATENAME as from1,c.STR_STATENAME as to1  from t_ra_stateconversion a"
			+" left join t_ra_state b on b.int_id = a.INT_FROMSTATE"
			+" left join t_ra_state c on c.int_id = a.INT_toSTATE "
		    +"where a.int_id in ( select  int_stateconversionid  from t_ra_gatherstaterule where  int_gatherid="+int_gatherid
		    +" )";
		
		return sql;
		
	}
	
	/**
	 *����ɼ���״̬����
	 * 
	 * л����
	 */
	public String saveGatherStateRule(int int_gatherid,int Stateconversionid,int defaultgo){
		String sql="insert into t_ra_gatherstaterule(int_id,int_gatherid,int_stateconversionid,defaultexcute)"
			+" values(seq_ra_gatherstaterule.nextval,"
			+ int_gatherid 
			+","
			+ Stateconversionid 
			+","
			+ defaultgo
			+")";
		
		return sql;
		
		
		
	}
	/**
	 *ͨ���ɼ����id����ѯ��ɼ�����йص�״̬��id��
	 * 
	 * л����
	 */
	public String getstateIdbygatherid(int int_gatherid){
		String sql="select sc.int_fromstate ,sc.int_tostate,gsr.defaultexcute,g.startgo,g.compel from t_ra_gatherstaterule gsr,t_ra_stateconversion sc,t_tg_gather g"
			+" where gsr.int_stateconversionid=sc.int_id and gsr.int_gatherid=g.int_id and gsr.int_gatherid="+int_gatherid;
		
		
		return sql;
	}
	/**
	 * ͨ���ɼ����id�鿴Ĭ��״̬װ����ϵ��
	 * 
	 * л����
	 */
	public String getconversionidBy(int int_gatherid){
		String sql="select int_stateconversionid from t_ra_gatherstaterule where defaultexcute=1 and int_gatherid="+int_gatherid;
		return sql;
	}
	/**
	 * ��ɾ���ɼ���Ĺ���
	 * 
	 * @param int_gatherid �ɼ����id
	 *  л����
	 * @return ��ѯ��ŵ�sql���
	 */
	public  String delgatherstaterule(int int_gatherid){
		String sql="delete t_ra_gatherstaterule where int_gatherid="+int_gatherid;
		return sql;
	}
}
