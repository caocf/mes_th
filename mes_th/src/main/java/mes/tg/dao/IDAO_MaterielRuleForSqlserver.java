package mes.tg.dao;

import mes.tg.bean.MaterielRule;
/**
 *����SQLSERVER�����ݿ����
 *
 * @author Xjia
 */

public class IDAO_MaterielRuleForSqlserver extends IDAO_MaterielRuleForOracle {
	
	public String saveMainRule(MaterielRule materielRule) {
		String sql = "insert into t_tg_MaterielRule(str_name,str_validateclass,str_desc) "
				+ "values('"
				+ materielRule.getName()
				+ "','"
				+ materielRule.getValidate()
				+ "','" + materielRule.getDesc() + "')";
		return sql;
	}

}
