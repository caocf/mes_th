package mes.system.factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import mes.framework.DataBaseType;
import mes.system.dao.DAOFactoryAdapter;
import mes.system.dao.IDAO_Material;
import mes.system.elements.IMaterial;

/**
 * ά�����ϻ�����Ϣ����Ĺ�����
 * 
 * @author �Ź��� 2008-3-5
 */
public class IMaterialFactory extends AElementFactory<IMaterial> {
	IMaterialFactory() {
	}

	public IMaterial createElement() {
		return new DefMaterial();
	}

	boolean saveElement(IMaterial material, Connection con) throws SQLException {
		IMaterial type = super.queryElement(material.getName(), con);
		material.setId(type.getId());// Ԫ�ش洢�����ͨ��Ԫ�ص�name�ҵ�idֵ
		IDAO_Material dao = (IDAO_Material) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), IDAO_Material.class);
		doUpdateStatement(dao.getSQL_innerElement(material), con);
		// �洢��������id
		saveSubInfo(con.prepareStatement(dao
				.getSQLTemplete_innerCharacter(material)), material
				.getCharacterIds());
		// �洢���ϱ�ʾid
		saveSubInfo(con.prepareStatement(dao
				.getSQLTemplete_innerIdentify(material)), material
				.getIdentifyIds());
		return true;
	}

	private void saveSubInfo(PreparedStatement pst, Iterator<Integer> ids)
			throws SQLException {
		while (ids.hasNext()) {
			pst.setInt(1, ids.next());
			pst.executeUpdate();
		}
		pst.close();
	}

	boolean updateElement(IMaterial material, Connection con)
			throws SQLException {
		IDAO_Material dao = (IDAO_Material) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), IDAO_Material.class);
		doUpdateStatement(dao.getSQL_UpdateElement(material), con);
		doUpdateStatement(dao.getSQL_deleteCharacters(material.getId()), con);
		doUpdateStatement(dao.getSQL_deleteIdentifys(material.getId()), con);
		// �洢��������id
		saveSubInfo(con.prepareStatement(dao
				.getSQLTemplete_innerCharacter(material)), material
				.getCharacterIds());
		// �洢���ϱ�ʾid
		saveSubInfo(con.prepareStatement(dao
				.getSQLTemplete_innerIdentify(material)), material
				.getIdentifyIds());
		return true;
	}

	public IMaterial queryElement(int id, Connection con) throws SQLException {
		IDAO_Material dao = (IDAO_Material) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), IDAO_Material.class);
		return queryElement2(dao.getSQL_queryElement(id), dao, con);
	}

	public IMaterial queryElement(String name, Connection con)
			throws SQLException {
		IDAO_Material dao = (IDAO_Material) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), IDAO_Material.class);
		return queryElement2(dao.getSQL_queryElement(name), dao, con);
	}

	private IMaterial queryElement2(String sql, IDAO_Material dao,
			Connection con) throws SQLException {
		Statement st = con.createStatement();
		ResultSet set = st.executeQuery(sql);
		IMaterial material = null;

		if (set.next()) {
			material = createElement();
			// ��������Ϣ
			initObjectFromResultSet(material, set);
			// ������������
			material.setMaterialTypeId(set.getInt("materialtype_id"));
			// ��ѯ�������������Id
			set = st.executeQuery(dao.getSQL_queryCharacters(material.getId()));
			while (set.next())
				material.addCharacterId(set.getInt("MATERIALCHARACTER_ID"));
			// ��ѯ��������ϱ�ʶId
			set = st.executeQuery(dao.getSQL_queryIdentifys(material.getId()));
			while (set.next())
				material.addIdentifyId(set.getInt("MATERIALIdentify_ID"));
		}
		st.close();
		return material;
	}

}
