package mes.ra.dao;

import mes.ra.bean.ConversionState;
public class DAO_ConversionStateForSqlserver extends DAO_ConversionStateForOracle{
	/**
	 * ����Conversionstate
	 * 
	 * @param conversionstate
	 * @return ����conversionstate��sql���
	 */
	public String saveConversionState(ConversionState ConversionState) {
		String sql = "insert into t_ra_stateconversion(int_fromstate,int_tostate,str_desc) "
			+ "values('"
			+ ConversionState.getFromstate()
			+ "','" + ConversionState.getTostate()+ "','" + ConversionState.getDesc() + "')";
	return sql;
	}
}
