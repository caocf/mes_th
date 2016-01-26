package com.qm.mes.ra.dao;

import com.qm.mes.ra.bean.ConversionState;
public class DAO_ConversionStateForOracle implements DAO_ConversionState{
	/**
	 * ��ѯ״̬ת����Ϣ
	 * 
	 * @return sql���<br>
	 *         int_id ��� <br>
	 *         INT_FROMSTATE ԭ״̬��<br>
	 *         INT_TOSTATE ��ת״̬��<br>
	 *         STR_DESC ������Ϣ<br>
	 */
	public String getAllConversionState() {
		String sql = "select sc.*,fs.str_statename as fs_str_statename,ts.str_statename as ts_statename"
                    +" from t_ra_stateconversion sc inner join t_ra_state fs on sc.int_fromstate = fs.int_id"
                    +" inner join t_ra_state ts on sc.int_tostate = ts.int_id ";
						return sql;
	}
	/**
	 * ����Conversionstate
	 * 
	 * @param conversionstate
	 * @return ����conversionstate��sql���
	 */
	public String saveConversionState(ConversionState ConversionState) {
		String sql = "insert into t_ra_stateconversion(int_id,int_fromstate,int_tostate,str_desc) "
			+ "values(seq_ra_StateConversion.nextval,'"
			+ ConversionState.getFromstate()
			+ "','" + ConversionState.getTostate()+ "','" + ConversionState.getDesc() + "')";
	return sql;
	}

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
	public String getConversionStateById(int id) {
		String sql = "select int_id,int_fromstate,int_tostate,str_desc from t_ra_stateconversion"
			+ " where int_id = " + id;
		
	return sql;
	}

	/**
	 * ͨ�����ɾ��conversion
	 * 
	 * @param id
	 * @return ɾ��conversionstate��sql���
	 */
	public String delConversionStateById(int id) {
		String sql = "delete from t_ra_stateconversion where int_id="+ id;
		//System.out.println(sql);
		return sql;
	}
	
	/**
	 * ����conversionstate����ͨ����id
	 * 
	 * @param conversionstate
	 * @return ����conversionstate��sql���
	 */
	public String updateConversionState(ConversionState ConversionState) {
		String sql = "update t_ra_stateconversion set int_fromstate ='"
			+ConversionState.getFromstate()+ "',int_tostate ='"+ConversionState.getTostate()
			+"',str_desc ='"+ ConversionState.getDesc() + "' where int_id ="
			+ConversionState.getId();
		//System.out.println(sql);
	return sql;
		}
	/**
	 * ͨ�����Ʋ�ѯconversionstate
	 * 
	 * @param str_desc
	 * @return ��ѯ�ض������sql���
	 */
	 public String getConversionStateByDesc(String str_desc) {
	
		String sql = "select int_id,int_fromstate,int_tostate,str_desc from t_ra_stateconversion"
				+ " where str_desc='" + str_desc +"'order by int_id asc";
		return sql;
	}
	 
	 /**
	  * ͨ��������ԪID��ѯ״̬����
	  * 
	  * @param ProduceUnitId ������Ԫ��
	  * @return
	  */
	 public String getConversionStateByProdUnitId(int ProduceUnitId){
		 String sql = "select s.int_id,s.int_fromstate,s.int_tostate "+
		 "from t_ra_stateconversion s,t_ra_gatherstaterule r,t_tg_gather g "+ 
		 "where g.INT_PRODUNITID="+ProduceUnitId+" and r.int_gatherid=g.int_id and r.int_stateconversionid=s.int_id";
		
		 return sql;
	 }
}
