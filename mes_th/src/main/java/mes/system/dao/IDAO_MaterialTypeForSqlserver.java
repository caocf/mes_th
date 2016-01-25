package mes.system.dao;

import mes.system.elements.IMaterialType;

public class IDAO_MaterialTypeForSqlserver extends DAO_MaterialType {

	public String getSQL_innerElement(IMaterialType materialtype) {
		// Sqlserver���ݿ�֧�������У�����id�в���Ҫ������sql����С�
		return "insert into MaterialType(element_id,parent_id)values("
				+ materialtype.getId() + "," + materialtype.getParentId() + ")";
	}

}
