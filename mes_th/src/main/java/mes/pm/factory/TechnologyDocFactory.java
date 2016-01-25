package mes.pm.factory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import mes.framework.DataBaseType;
import mes.pm.bean.TechDocItem;
import mes.pm.bean.TechItemFile;
import mes.pm.bean.TechnologyDoc;
import mes.pm.dao.DAO_TechnologyDoc;
import mes.system.dao.DAOFactoryAdapter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ���ղ���˵���鹤����
 * 
 * @author YuanPeng 
 *
 */
public class TechnologyDocFactory {
	private final Log log = LogFactory.getLog(TechnologyDocFactory.class);// ��־

	/**
	 * �������ղ���˵����
	 * 
	 * @param technologyDoc
	 *            ���ղ���˵�������
	 * @param con
	 *            ���Ӷ���
	 * @throws SQLException
	 *             SQL�쳣
	 */
	public void saveTechnologyDoc(TechnologyDoc technologyDoc, Connection con)
			throws SQLException {
		DAO_TechnologyDoc dao_TechnologyDoc = (DAO_TechnologyDoc) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_TechnologyDoc.class);
		Statement stmt = con.createStatement();
		log.debug("�������ղ���˵�������SQL��" + dao_TechnologyDoc.saveTechnologyDoc(technologyDoc));
		stmt.execute(dao_TechnologyDoc.saveTechnologyDoc(technologyDoc));
		if (stmt != null) {
			stmt.close();
		}
	}

	/**
	 * ͨ��ID��ѯ���ղ���˵����
	 * 
	 * @param id
	 *            ���ղ���˵�������к�
	 * @param con
	 *            ���Ӷ���
	 * @return ͨ��ID��ѯ����ָ�����
	 * @throws java.sql.SQLException
	 */
	public TechnologyDoc getTechnologyDocById(int id, Connection con)
			throws SQLException, ParseException {
		TechnologyDoc technologyDoc = new TechnologyDoc();
		DAO_TechnologyDoc dao_TechnologyDoc = (DAO_TechnologyDoc) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_TechnologyDoc.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ��ID��ѯ���ղ���˵����SQL��" + dao_TechnologyDoc.getTechnologyDocById(id));
		ResultSet rs = stmt
				.executeQuery(dao_TechnologyDoc.getTechnologyDocById(id));
		if (rs.next()) {
			technologyDoc.setId(rs.getInt("int_id"));
			technologyDoc.setName(rs.getString("str_name"));
			technologyDoc.setMateriel(rs.getString("STR_MATERIEL"));
			technologyDoc.setDescription(rs.getString("Str_description"));
			technologyDoc.setCreateDate(rs.getString("Dat_createDate") == null ? null
							: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
									.parse(rs.getString("Dat_createDate")));
			technologyDoc.setUpDate(rs.getString("Dat_upDate") == null ? null
					: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs
							.getString("Dat_upDate")));
			technologyDoc.setCreateUID(rs.getInt("Int_CreateUID"));
			technologyDoc.setUpdateUID(rs.getInt("Int_UpdateUID"));
		}
		if (stmt != null)
			stmt.close();
		return technologyDoc;
	}

	/**
	 * ͨ�����ղ���˵�����ɾ����װ��ָʾ��
	 * 
	 * @param id
	 *            ���ղ���˵�����
	 * @param con
	 *            ���Ӷ���
	 * @throws SQLException
	 *             SQL�쳣
	 */
	public void delTechnologyDocById(int id, Connection con) throws SQLException {
		DAO_TechnologyDoc dao_TechnologyDoc = (DAO_TechnologyDoc) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_TechnologyDoc.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ�����ղ���˵�����ɾ���ù��ղ���˵����SQL��"
				+ dao_TechnologyDoc.delTechnologyDocById(id));
		stmt.execute(dao_TechnologyDoc.delTechnologyDocById(id));
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}
	}

	/**
	 * �����ѯ���ղ���˵����
	 * 
	 * @param con
	 *            ���Ӷ���
	 * @return �����ѯ���ղ���˵�����б�
	 * @throws SQLException
	 *             SQL�쳣
	 * @throws ParseException
	 *             ����ת���쳣
	 */
	public List<TechnologyDoc> getAllTechnologyDocsByDESC(Connection con)throws SQLException, ParseException{
		List<TechnologyDoc> list_TechnologyDoc = new ArrayList<TechnologyDoc>();
		DAO_TechnologyDoc dao_TechnologyDoc = (DAO_TechnologyDoc) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_TechnologyDoc.class);
		Statement stmt = con.createStatement();
		log.debug("�����ѯ���ղ���˵����SQL��" + dao_TechnologyDoc.getAllTechnologyDocsByDESC());
		ResultSet rs = stmt
				.executeQuery(dao_TechnologyDoc.getAllTechnologyDocsByDESC());
		while (rs.next()) {
			TechnologyDoc technologyDoc = new TechnologyDoc();
			technologyDoc.setId(rs.getInt("int_id"));
			technologyDoc.setName(rs.getString("str_name"));
			technologyDoc.setMateriel(rs.getString("STR_MATERIEL"));
			technologyDoc.setDescription(rs.getString("Str_description"));
			technologyDoc.setCreateDate(rs.getString("Dat_createDate") == null ? null
							: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
									.parse(rs.getString("Dat_createDate")));
			technologyDoc.setUpDate(rs.getString("Dat_upDate") == null ? null
					: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs
							.getString("Dat_upDate")));
			technologyDoc.setCreateUID(rs.getInt("Int_CreateUID"));
			technologyDoc.setUpdateUID(rs.getInt("Int_UpdateUID"));
			list_TechnologyDoc.add(technologyDoc);
		}
		if (stmt != null)
			stmt.close();
		return list_TechnologyDoc;
	}
	
	/**
	 * ͨ�����ղ���˵��������ѯ���ղ���˵�����
	 * 
	 * @param name	���ղ���˵������
	 * @param con	���Ӷ���
	 * @return ���ղ���˵�����
	 * @throws SQLException SQL�쳣
	 */
	public int getTechnologyDocIdByName(String name ,Connection con)throws SQLException{
		int id=0;
		DAO_TechnologyDoc dao_TechnologyDoc = (DAO_TechnologyDoc) DAOFactoryAdapter
			.getInstance(DataBaseType.getDataBaseType(con),DAO_TechnologyDoc.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ��װ��ָʾ������ѯ���SQL��" + dao_TechnologyDoc.getTechnologyDocIdByName(name));
		ResultSet rs = stmt.executeQuery(dao_TechnologyDoc.getTechnologyDocIdByName(name));
		if (rs.next()) {
			id = rs.getInt("int_id");
		}
		if (stmt != null)
			stmt.close();
		return id;
	}
	
	/**
	 * ͨ�����ղ���˵��������ѯ���ղ���˵��������
	 * 
	 * @param name ���ղ���˵������
	 * @param con ���Ӷ���
	 * @return ���ղ���˵��������
	 * @throws SQLException SQL�쳣
	 */
	public int getTechnologyDocCountByName(String name ,Connection con)throws SQLException{
		int count=0;
		DAO_TechnologyDoc dao_TechnologyDoc = (DAO_TechnologyDoc) DAOFactoryAdapter
			.getInstance(DataBaseType.getDataBaseType(con),DAO_TechnologyDoc.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ�����ղ���˵��������ѯ���ղ���˵��������SQL��" + 
				dao_TechnologyDoc.getTechnologyDocCountByName(name));
		ResultSet rs = stmt.executeQuery(dao_TechnologyDoc.getTechnologyDocCountByName(name));
		if (rs.next()) {
			count = rs.getInt(1);
		}
		if (stmt != null)
			stmt.close();
		return count;
	}
	
	/**
	 * ���¹��ղ���˵����
	 * 
	 * @param assembleDoc ���ղ���˵�������
	 * @param con ���Ӷ���
	 * @throws SQLException SQL�쳣
	 */
	public void updateTechnologyDoc(TechnologyDoc technologyDoc, Connection con)
			throws SQLException {
		DAO_TechnologyDoc dao_TechnologyDoc = (DAO_TechnologyDoc) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),DAO_TechnologyDoc.class);
		Statement stmt = con.createStatement();
		log.debug("���¹��ղ���˵����SQL��"
				+ dao_TechnologyDoc.updateTechnologyDoc(technologyDoc));
		stmt.execute(dao_TechnologyDoc.updateTechnologyDoc(technologyDoc));
		if (stmt != null) {
			stmt.close();
		}
	}
	
	/**
	 * ͨ����Ʒ����ʾ��ѯ���ղ���˵��������
	 * 
	 * @param materiel	��������ʾ
	 * @param con ���Ӷ���
	 * @return ͨ����Ʒ����ʾ��ѯ���ղ���˵��������
	 * @throws SQLException SQL�쳣
	 */
	public int getTechnologyDocCountByMateriel(String materiel,Connection con)throws SQLException{
		int count=0;
		DAO_TechnologyDoc dao_TechnologyDoc = (DAO_TechnologyDoc) DAOFactoryAdapter
			.getInstance(DataBaseType.getDataBaseType(con),DAO_TechnologyDoc.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ����������ʾ��ѯ���ղ���˵��������SQL��" + 
				dao_TechnologyDoc.getTechnologyDocCountByMateriel(materiel));
		ResultSet rs = stmt.executeQuery(dao_TechnologyDoc.getTechnologyDocCountByMateriel(materiel));
		if (rs.next()) {
			count = rs.getInt(1);
		}
		if (stmt != null)
			stmt.close();
		return count;
	}
	
	
	
	

	
	
	
	
	
	
	
	/**
	 * �������ղ�����
	 * 
	 * @param techDocItem ���ղ��������
	 * @param con ���Ӷ���
	 * @throws SQLException  SQL�쳣
	 */
	public void saveTechDocItem(TechDocItem techDocItem,Connection con)throws SQLException{
		DAO_TechnologyDoc dao_TechnologyDoc = (DAO_TechnologyDoc) DAOFactoryAdapter
			.getInstance(DataBaseType.getDataBaseType(con),DAO_TechnologyDoc.class);
		Statement stmt = con.createStatement();
		log.debug("���湤�ղ�����SQL��" + dao_TechnologyDoc.saveTechDocItem(techDocItem));
		stmt.execute(dao_TechnologyDoc.saveTechDocItem(techDocItem));
			if (stmt != null) {
				stmt.close();
			}
	}
	
	/**
	 * ͨ�����ղ���˵����Ų�ѯ���ղ�����
	 * 
	 * @param id ���ղ���˵�����
	 * @param con ���Ӷ���
	 * @return ͨ�����ղ���˵����Ų�ѯ���ղ������б�
	 * @throws SQLException SQL�쳣
	 */
	public List<TechDocItem> getTechDocItemByTechnologyDocId(int id,Connection con)throws SQLException{
		List<TechDocItem> list_TechDocItem = new ArrayList<TechDocItem>();
		DAO_TechnologyDoc dao_AssembleDoc = (DAO_TechnologyDoc) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_TechnologyDoc.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ�����ղ���˵����Ų�ѯ���ղ������б�SQL��" + dao_AssembleDoc.getTechDocItemByTechnologyDocId(id));
		ResultSet rs = stmt
				.executeQuery(dao_AssembleDoc.getTechDocItemByTechnologyDocId(id));
		while (rs.next()) {
			TechDocItem techDocItem = new TechDocItem();
			techDocItem.setId(rs.getInt("int_id"));
			techDocItem.setTechDocId(rs.getInt("Int_TechDocId"));
			techDocItem.setProduceUnitId(rs.getInt("Int_produceUnit"));
			techDocItem.setContent(rs.getString("Str_Content"));
			list_TechDocItem.add(techDocItem);
		}
		if (stmt != null)
			stmt.close();
		return list_TechDocItem;
	}
	
	/**
	 * ͨ�����ղ���˵�����ɾ�����ղ�����
	 * 
	 * @param id ���ղ���˵�����
	 * @param con ���Ӷ���
	 * @throws SQLException SQL�쳣
	 */
	public void delTechDocItemByTechnologyDocId(int id,Connection con)throws SQLException{
		DAO_TechnologyDoc dao_TechnologyDoc = (DAO_TechnologyDoc) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_TechnologyDoc.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ�����ղ���˵�����ɾ�����ղ�����SQL��" + dao_TechnologyDoc.delTechDocItemByTechnologyDocId(id));
		stmt.execute(dao_TechnologyDoc.delTechDocItemByTechnologyDocId(id));
			if (stmt != null) {
				stmt.close();
			}
	}
	
	
	/**
	 * ��ѯ���ղ�����������
	 * 
	 * @param con ���Ӷ���
	 * @return ���ղ�����������
	 */
	public int getTechDocItemMaxId(Connection con)throws SQLException{
		int id=0;
		DAO_TechnologyDoc dao_TechnologyDoc = (DAO_TechnologyDoc) DAOFactoryAdapter
			.getInstance(DataBaseType.getDataBaseType(con),DAO_TechnologyDoc.class);
		Statement stmt = con.createStatement();
		log.debug("��ѯ���ղ�����������SQL��" + dao_TechnologyDoc.getTechDocItemMaxId());
		ResultSet rs = stmt.executeQuery(dao_TechnologyDoc.getTechDocItemMaxId());
		if (rs.next()) {
			id = rs.getInt(1);
		}
		if (stmt != null)
			stmt.close();
		return id;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * ͨ�����ղ�������Ų�ѯ���ղ������ļ�
	 * 
	 * @param tech ���ղ��������
	 * @param con ���Ӷ���
	 * @return ͨ�����ղ�������Ų�ѯ���ղ������ļ�
	 * @throws SQLException
	 */
	public TechItemFile getTechItemFileByTechDocItemId(int tech,Connection con)throws SQLException{
		TechItemFile techItemFile = null;
		DAO_TechnologyDoc dao_TechnologyDoc = (DAO_TechnologyDoc) DAOFactoryAdapter
		.getInstance(DataBaseType.getDataBaseType(con),DAO_TechnologyDoc.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ�����ղ�������Ų�ѯ���ղ������ļ��б�SQL��" + dao_TechnologyDoc.getTechItemFileByTechDocItemId(tech));
		ResultSet rs = stmt.executeQuery(dao_TechnologyDoc.getTechItemFileByTechDocItemId(tech));
		if(rs.next()){
			techItemFile = new TechItemFile();
			techItemFile.setId(rs.getInt("int_id"));
			techItemFile.setTechDocItemId(rs.getInt("INT_TECHITEMID"));
			techItemFile.setPathName(rs.getString("STR_PATHNAME"));
		}
		if (stmt != null)
			stmt.close();
		return techItemFile;
	}
	
	/**
	 * �������ղ������ļ�
	 * 
	 * @param tif ���ղ������ļ�����
	 * @param con ���Ӷ���
	 * @throws SQLException SQL�쳣
	 */
	public void saveTechItemFile(TechItemFile tif,Connection con)throws SQLException{
		DAO_TechnologyDoc dao_TechnologyDoc = (DAO_TechnologyDoc) DAOFactoryAdapter
		.getInstance(DataBaseType.getDataBaseType(con),DAO_TechnologyDoc.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ�����ղ�������Ų�ѯ���ղ������ļ��б�SQL��" + dao_TechnologyDoc.saveTechItemFile(tif));
		stmt.execute(dao_TechnologyDoc.saveTechItemFile(tif));
		if (stmt != null)
			stmt.close();
	}
	
	/**
	 * ͨ�����ղ�����ɾ�����ղ������ļ�
	 * 
	 * @param techDocItemId	���ղ�����
	 * @param con ���Ӷ���
	 * @throws SQLException sql�쳣
	 */
	public void delTechItemFile(int techDocItemId,Connection con)throws SQLException{
		DAO_TechnologyDoc dao_TechnologyDoc = (DAO_TechnologyDoc) DAOFactoryAdapter
		.getInstance(DataBaseType.getDataBaseType(con),DAO_TechnologyDoc.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ�����ղ�����ɾ�����ղ������ļ�SQL��" + dao_TechnologyDoc.delTechItemFile(techDocItemId));
		stmt.execute(dao_TechnologyDoc.delTechItemFile(techDocItemId));
		if (stmt != null)
			stmt.close();
	}
	
	/**
	 * ͨ�����ղ���˵�������ɾ�����ղ������ļ�
	 * 
	 * @param techDocId ���ղ���˵�������
	 * @param con ���Ӷ���
	 * @throws SQLException sql�쳣
	 */
	public void delTechItemFileByTechDoc(int techDocId,Connection con)throws SQLException{
		DAO_TechnologyDoc dao_TechnologyDoc = (DAO_TechnologyDoc) DAOFactoryAdapter
			.getInstance(DataBaseType.getDataBaseType(con),DAO_TechnologyDoc.class);
		Statement stmt = con.createStatement();
		log.debug("�����ղ���˵�������ɾ�����ղ������ļ�SQL��" + dao_TechnologyDoc.delTechItemFileByTechDoc(techDocId));
		stmt.execute(dao_TechnologyDoc.delTechItemFileByTechDoc(techDocId));
		if (stmt != null)
			stmt.close();
	}
	
	/**
	 * ͨ���ɹ��ղ�������Ÿ����¹��ղ������ļ�
	 * 
	 * @param oldItemId	�ɹ��ղ�����
	 * @param con ���Ӷ���
	 * @throws SQLException sql�쳣
	 */
	public void updateTechItemId(int oldItemId,Connection con)throws SQLException{
		DAO_TechnologyDoc dao_TechnologyDoc = (DAO_TechnologyDoc) DAOFactoryAdapter
		.getInstance(DataBaseType.getDataBaseType(con),DAO_TechnologyDoc.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ���ɹ��ղ�������Ÿ����¹��ղ�����SQL��" + dao_TechnologyDoc.updateTechItemId(oldItemId));
		stmt.execute(dao_TechnologyDoc.updateTechItemId(oldItemId));
		if (stmt != null)
			stmt.close();
	}
}