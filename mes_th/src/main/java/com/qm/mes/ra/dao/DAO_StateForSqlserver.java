package com.qm.mes.ra.dao;

import com.qm.mes.ra.bean.State;


public class DAO_StateForSqlserver extends  DAO_StateForOracle {
	/**
	 * ����state
	 * ���
	 * @param state
	 * @return ����state��sql���
	 */
	public String saveState(State state) {
		String sql = "insert into t_ra_state(str_statename,str_style,int_delete,str_styledesc) "
				+ "values('"
				+state.getStateName()
				+"','"+state.getStyle()+"',"+state.getDelete()+",'"+state.getStyledesc()+"')";
	   
		return sql;
	}
	/**
	 *����ɼ���״̬����
	 * 
	 * л����
	 */
	public String saveGatherStateRule(int int_gatherid,int Stateconversionid,int defaultgo){
		String sql="insert into t_ra_gatherstaterule(int_gatherid,int_stateconversionid,defaultexcute)"
			+" values("
			+ int_gatherid 
			+","
			+ Stateconversionid 
			+","
			+ defaultgo
			+")";
		
		return sql;
}
}
