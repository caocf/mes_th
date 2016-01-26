package com.qm.mes.system.factory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.qm.mes.framework.DataBaseType;
import com.qm.mes.system.dao.DAOFactoryAdapter;
import com.qm.mes.system.dao.IDAO_MaterialType;
import com.qm.mes.system.elements.IMaterialType;

/**
 * �������͹����������ṩ����������͵Ļ�������
 * 
 * @author �Ź��� 2008-3-6
 */
public class IMaterialTypeFactory extends AElementFactory<IMaterialType> {
	IMaterialTypeFactory() {
	}

	public IMaterialType createElement() {
		return new DefMaterialType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see system.factory.AElementFactory#save(system.elements.IElement,
	 *      java.sql.Connection)
	 */
	boolean saveElement(IMaterialType materialtype,
			Connection con) throws SQLException {
		/**
		 * name��element������� <br>
		 * 1 ��������Ϣ����element���� <br>
		 * 2 ͨ��nameֵ��ȡelement���ж����idֵ <br>
		 * 3 ��������Ϣ�������ͱ��С�
		 */
		IMaterialType type = super.queryElement(materialtype.getName(), con);
		materialtype.setId(type.getId());
		IDAO_MaterialType dao = (IDAO_MaterialType) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						IDAO_MaterialType.class);
		doUpdateStatement(dao.getSQL_innerElement(materialtype), con);
		return true;
	}

	boolean updateElement(IMaterialType materialtype,
			Connection con) throws SQLException {
			IDAO_MaterialType dao = (IDAO_MaterialType) DAOFactoryAdapter
					.getInstance(DataBaseType.getDataBaseType(con),
							IDAO_MaterialType.class);
			doUpdateStatement(dao.getSQL_UpdateElement(materialtype), con);
			return true;
	}

	public IMaterialType queryElement(int id, Connection con)
			throws SQLException {
		IDAO_MaterialType dao = (IDAO_MaterialType) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						IDAO_MaterialType.class);
		return queryElement2(dao.getSQL_queryElement(id), con);
	}

	public IMaterialType queryElement(String name, Connection con)
			throws SQLException {
		IDAO_MaterialType dao = (IDAO_MaterialType) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						IDAO_MaterialType.class);
		return queryElement2(dao.getSQL_queryElement(name), con);
	}

	private IMaterialType queryElement2(String sql, Connection con)
			throws SQLException {
		Statement st = con.createStatement();
		ResultSet set = st.executeQuery(sql);
		IMaterialType type = null;

		if (set.next()) {
			type = createElement();
			initObjectFromResultSet(type, set);
			type.setParentId(set.getInt("parent_id"));
		}
		st.close();
		return type;
	}
}
