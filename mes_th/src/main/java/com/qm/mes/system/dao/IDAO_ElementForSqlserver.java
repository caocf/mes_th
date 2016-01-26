package com.qm.mes.system.dao;

import com.qm.mes.system.elements.IElement;

public class IDAO_ElementForSqlserver extends DAO_Element {

	public String getSQL_innerElement(String name, String description,
			int updateUserId) {
		// Sqlserver���ݿ�֧�������У�����id�в���Ҫ������sql����С�
		return "insert into Element(name,description,discard,updateDateTime,updateUserId,version)values('"
				+ name
				+ "','"
				+ description
				+ "',0,getdate(),"
				+ updateUserId
				+ ",1)";
	}

	public String getSQL_innerElement(IElement element) {
		return getSQL_innerElement(element.getName(), element.getDescription(),
				element.getUpdateUserId());
	}

}
