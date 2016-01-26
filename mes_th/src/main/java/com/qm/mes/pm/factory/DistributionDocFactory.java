package com.qm.mes.pm.factory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.DataBaseType;
import com.qm.mes.pm.bean.DistriItem;
import com.qm.mes.pm.bean.DistributionDoc;
import com.qm.mes.pm.dao.DAO_DistributionDoc;
import com.qm.mes.system.dao.DAOFactoryAdapter;

/**
 * ����ָʾ��������
 * 
 * @author YuanPeng
 *
 */
public class DistributionDocFactory {
	
	private final Log log = LogFactory.getLog(DistributionDocFactory.class);// ��־

	/**
	 * ��������ָʾ��
	 * 
	 * @param DistributionDoc
	 *            ����ָʾ������
	 * @param con
	 *            ���Ӷ���
	 * @throws SQLException
	 *             SQL�쳣
	 */
	public void saveDistributionDoc(DistributionDoc DistributionDoc, Connection con)
			throws SQLException {
		DAO_DistributionDoc dao_DistributionDoc = (DAO_DistributionDoc) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_DistributionDoc.class);
		Statement stmt = con.createStatement();
		log.debug("��������ָʾ��SQL��" + dao_DistributionDoc.saveDistributionDoc(DistributionDoc));
		stmt.execute(dao_DistributionDoc.saveDistributionDoc(DistributionDoc));
		if (stmt != null) {
			stmt.close();
		}
	}

	/**
	 * ͨ��ID��ѯ����ָʾ��
	 * 
	 * @param id
	 *            ����ָʾ�����к�
	 * @param con
	 *            ���Ӷ���
	 * @return ͨ��ID��ѯ��������ָʾ������
	 * @throws java.sql.SQLException
	 */
	public DistributionDoc getDistributionDocById(int id, Connection con)
			throws SQLException, ParseException {
		DistributionDoc DistributionDoc = new DistributionDoc();
		DAO_DistributionDoc dao_DistributionDoc = (DAO_DistributionDoc) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_DistributionDoc.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ��ID��ѯ����ָʾ��SQL��" + dao_DistributionDoc.getDistributionDocById(id));
		ResultSet rs = stmt
				.executeQuery(dao_DistributionDoc.getDistributionDocById(id));
		if (rs.next()) {
			DistributionDoc.setId(rs.getInt("int_id"));
			DistributionDoc.setName(rs.getString("str_name"));
			DistributionDoc.setMaterielType(rs.getString("Str_materiel"));
			DistributionDoc.setDescription(rs.getString("Str_description"));
			DistributionDoc.setCreateDate(rs.getString("Dat_createDate") == null ? null
							: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
									.parse(rs.getString("Dat_createDate")));
			DistributionDoc.setUpDate(rs.getString("Dat_upDate") == null ? null
					: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs
							.getString("Dat_upDate")));
			DistributionDoc.setCreateUID(rs.getInt("Int_CreateUID"));
			DistributionDoc.setUpdateUID(rs.getInt("Int_UpdateUID"));
			DistributionDoc.setRequest_proUnit(rs.getInt("Int_request"));
			DistributionDoc.setResponse_proUnit(rs.getInt("Int_response"));
			DistributionDoc.setTarget_proUnit(rs.getInt("Int_target"));
			DistributionDoc.setBomId(rs.getInt("Int_bomid"));
		}
		if (stmt != null)
			stmt.close();
		return DistributionDoc;
	}

	/**
	 * ͨ������ָʾ����ɾ��������ָʾ��
	 * 
	 * @param id
	 *            ����ָʾ����
	 * @param con
	 *            ���Ӷ���
	 * @throws SQLException
	 *             SQL�쳣
	 */
	public void delDistributionDocById(int id, Connection con) throws SQLException {
		DAO_DistributionDoc dao_DistributionDoc = (DAO_DistributionDoc) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_DistributionDoc.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ������ָʾ����ɾ��������ָʾ��SQL��"
				+ dao_DistributionDoc.delDistributionDocById(id));
		stmt.execute(dao_DistributionDoc.delDistributionDocById(id));
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}
	}

	/**
	 * ͨ��ID��ѯ����ָʾ��
	 * 
	 * @param materiel
	 *            ��������
	 * @param con
	 *            ���Ӷ���
	 * @return ͨ��ID��ѯ����ָʾ������
	 * @throws SQLException
	 *             SQL�쳣
	 * @throws ParseException
	 *             ����ת���쳣
	 */
	public DistributionDoc getDistributionDocByMateriel(String materiel, Connection con)
			throws SQLException, ParseException {
		DistributionDoc DistributionDoc = new DistributionDoc();
		DAO_DistributionDoc dao_DistributionDoc = (DAO_DistributionDoc) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_DistributionDoc.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ��ID��ѯ����ָʾ��SQL��" + dao_DistributionDoc.getDistributionDocByMateriel(materiel));
		ResultSet rs = stmt
				.executeQuery(dao_DistributionDoc.getDistributionDocByMateriel(materiel));
		if (rs.next()) {
			DistributionDoc.setId(rs.getInt("int_id"));
			DistributionDoc.setName(rs.getString("str_name"));
			DistributionDoc.setMaterielType(rs.getString("Str_materiel"));
			DistributionDoc.setDescription(rs.getString("Str_description"));
			DistributionDoc.setCreateDate(rs.getString("Dat_createDate") == null ? null
							: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
									.parse(rs.getString("Dat_createDate")));
			DistributionDoc.setUpDate(rs.getString("Dat_upDate") == null ? null
					: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs
							.getString("Dat_upDate")));
			DistributionDoc.setCreateUID(rs.getInt("Int_CreateUID"));
			DistributionDoc.setUpdateUID(rs.getInt("Int_UpdateUID"));
			DistributionDoc.setRequest_proUnit(rs.getInt("Int_request"));
			DistributionDoc.setResponse_proUnit(rs.getInt("Int_response"));
			DistributionDoc.setTarget_proUnit(rs.getInt("Int_target"));
			DistributionDoc.setBomId(rs.getInt("Int_bomid"));
		}
		if (stmt != null)
			stmt.close();
		return DistributionDoc;
	}
	
	/**
	 * �����ѯ����ָʾ��
	 * 
	 * @param con
	 *            ���Ӷ���
	 * @return �����ѯ����ָʾ���б�
	 * @throws SQLException
	 *             SQL�쳣
	 * @throws ParseException
	 *             ����ת���쳣
	 */
	public List<DistributionDoc> getAllDistributionDocsByDESC(Connection con)throws SQLException, ParseException{
		List<DistributionDoc> list_DistributionDoc = new ArrayList<DistributionDoc>();
		DAO_DistributionDoc dao_DistributionDoc = (DAO_DistributionDoc) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_DistributionDoc.class);
		Statement stmt = con.createStatement();
		log.debug("�����ѯ����ָʾ��SQL��" + dao_DistributionDoc.getAllDistributionDocsByDESC());
		ResultSet rs = stmt
				.executeQuery(dao_DistributionDoc.getAllDistributionDocsByDESC());
		while (rs.next()) {
			DistributionDoc DistributionDoc = new DistributionDoc();
			DistributionDoc.setId(rs.getInt("int_id"));
			DistributionDoc.setName(rs.getString("str_name"));
			DistributionDoc.setMaterielType(rs.getString("Str_materiel"));
			DistributionDoc.setDescription(rs.getString("Str_description"));
			DistributionDoc.setCreateDate(rs.getString("Dat_createDate") == null ? null
							: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
									.parse(rs.getString("Dat_createDate")));
			DistributionDoc.setUpDate(rs.getString("Dat_upDate") == null ? null
					: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs
							.getString("Dat_upDate")));
			DistributionDoc.setCreateUID(rs.getInt("Int_CreateUID"));
			DistributionDoc.setUpdateUID(rs.getInt("Int_UpdateUID"));
			DistributionDoc.setRequest_proUnit(rs.getInt("Int_request"));
			DistributionDoc.setResponse_proUnit(rs.getInt("Int_response"));
			DistributionDoc.setTarget_proUnit(rs.getInt("Int_target"));
			DistributionDoc.setBomId(rs.getInt("Int_bomid"));
			list_DistributionDoc.add(DistributionDoc);
		}
		if (stmt != null)
			stmt.close();
		return list_DistributionDoc;
	}
	
	/**
	 * ͨ������ָʾ������ѯ����ָʾ����
	 * 
	 * @param name	����ָʾ��
	 * @param con	���Ӷ���
	 * @return ����ָʾ����
	 * @throws SQLException SQL�쳣
	 */
	public int getDistributionDocIdByName(String name ,Connection con)throws SQLException{
		int id=0;
		DAO_DistributionDoc dao_DistributionDoc = (DAO_DistributionDoc) DAOFactoryAdapter
			.getInstance(DataBaseType.getDataBaseType(con),DAO_DistributionDoc.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ������ָʾ������ѯ���SQL��" + dao_DistributionDoc.getDistributionDocIdByName(name));
		ResultSet rs = stmt.executeQuery(dao_DistributionDoc.getDistributionDocIdByName(name));
		if (rs.next()) {
			id = rs.getInt("int_id");
		}
		if (stmt != null)
			stmt.close();
		return id;
	}
	
	/**
	 * ͨ������ָʾ������ѯ����ָʾ������
	 * 
	 * @param name ����ָʾ����
	 * @param con ���Ӷ���
	 * @return ����ָʾ������
	 * @throws SQLException SQL�쳣
	 */
	public int getDistributionDocCountByName(String name ,Connection con)throws SQLException{
		int count=0;
		DAO_DistributionDoc dao_DistributionDoc = (DAO_DistributionDoc) DAOFactoryAdapter
			.getInstance(DataBaseType.getDataBaseType(con),DAO_DistributionDoc.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ������ָʾ������ѯ����ָʾ������SQL��" + dao_DistributionDoc.getDistributionDocCountByName(name));
		ResultSet rs = stmt.executeQuery(dao_DistributionDoc.getDistributionDocCountByName(name));
		if (rs.next()) {
			count = rs.getInt(1);
		}
		if (stmt != null)
			stmt.close();
		return count;
	}
	
	/**
	 * ��������ָʾ��
	 * 
	 * @param DistributionDoc ����ָʾ������
	 * @param con ���Ӷ���
	 * @throws SQLException SQL�쳣
	 */
	public void updateDistributionDoc(DistributionDoc DistributionDoc, Connection con)
			throws SQLException {
		DAO_DistributionDoc dao_DistributionDoc = (DAO_DistributionDoc) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),DAO_DistributionDoc.class);
		Statement stmt = con.createStatement();
		log.debug("��������ָʾ��SQL��"
				+ dao_DistributionDoc.updateDistributionDoc(DistributionDoc));
		stmt.execute(dao_DistributionDoc.updateDistributionDoc(DistributionDoc));
		if (stmt != null) {
			stmt.close();
		}
	}
	
	/**
	 * ͨ������������Ԫ�Ų�ѯ����ָʾ��
	 * 
	 * @param requestProUnitId ����������Ԫ��
	 * @return ͨ������������Ԫ�Ų�ѯ����ָʾ���б�
	 */
	public List<DistributionDoc> getDistributionDocsByRequestProUnitId(int requestProUnitId, Connection con)
			throws SQLException, ParseException {
		List<DistributionDoc> list_DistributionDoc = new ArrayList<DistributionDoc>();
		DAO_DistributionDoc dao_DistributionDoc = (DAO_DistributionDoc) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),DAO_DistributionDoc.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ������������Ԫ�Ų�ѯ����ָʾ��SQL��"
				+ dao_DistributionDoc.getDistributionDocsByRequestProUnitId(requestProUnitId));
		ResultSet rs = stmt
			.executeQuery(dao_DistributionDoc.getDistributionDocsByRequestProUnitId(requestProUnitId));
		while (rs.next()) {
			DistributionDoc DistributionDoc = new DistributionDoc();
			DistributionDoc.setId(rs.getInt("int_id"));
			DistributionDoc.setName(rs.getString("str_name"));
			DistributionDoc.setMaterielType(rs.getString("Str_materiel"));
			DistributionDoc.setDescription(rs.getString("Str_description"));
			DistributionDoc.setCreateDate(rs.getString("Dat_createDate") == null ? null
							: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
									.parse(rs.getString("Dat_createDate")));
			DistributionDoc.setUpDate(rs.getString("Dat_upDate") == null ? null
					: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs
							.getString("Dat_upDate")));
			DistributionDoc.setCreateUID(rs.getInt("Int_CreateUID"));
			DistributionDoc.setUpdateUID(rs.getInt("Int_UpdateUID"));
			DistributionDoc.setRequest_proUnit(rs.getInt("Int_request"));
			DistributionDoc.setResponse_proUnit(rs.getInt("Int_response"));
			DistributionDoc.setTarget_proUnit(rs.getInt("Int_target"));
			DistributionDoc.setBomId(rs.getInt("Int_bomid"));
			list_DistributionDoc.add(DistributionDoc);
		}
		if (stmt != null) {
			stmt.close();
		}
		return list_DistributionDoc;
	}
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	/**
	 * ����װ��ָʾ��
	 * 
	 * @param assDocItem װ��ָʾ�����
	 * @param con ���Ӷ���
	 * @throws SQLException  SQL�쳣
	 */
	public void saveDistriItem(DistriItem DistriItem,Connection con)throws SQLException{
		DAO_DistributionDoc dao_DistributionDoc = (DAO_DistributionDoc) DAOFactoryAdapter
			.getInstance(DataBaseType.getDataBaseType(con),DAO_DistributionDoc.class);
		Statement stmt = con.createStatement();
		log.debug("����װ��ָʾ��SQL��" + dao_DistributionDoc.saveDistriItem(DistriItem));
		stmt.execute(dao_DistributionDoc.saveDistriItem(DistriItem));
			if (stmt != null) {
				stmt.close();
			}
	}
	
	/**
	 * ͨ������ָʾ���Ų�ѯװ��ָʾ��
	 * 
	 * @param id ����ָʾ����
	 * @param con ���Ӷ���
	 * @return ͨ������ָʾ���Ų�ѯװ��ָʾ���б�
	 * @throws SQLException SQL�쳣
	 */
	public List<DistriItem> getDistriItemByDistributionDocId(int id,Connection con)throws SQLException{
		List<DistriItem> list_DistriItem = new ArrayList<DistriItem>();
		DAO_DistributionDoc dao_DistributionDoc = (DAO_DistributionDoc) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_DistributionDoc.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ������ָʾ���Ų�ѯװ��ָʾ���б�SQL��" + dao_DistributionDoc.getDistriItemByDistributionDocId(id));
		ResultSet rs = stmt
				.executeQuery(dao_DistributionDoc.getDistriItemByDistributionDocId(id));
		while (rs.next()) {
			DistriItem DistriItem = new DistriItem();
			DistriItem.setId(rs.getInt("int_id"));
			DistriItem.setDistributionDocId(rs.getInt("Int_DisDoc"));
			DistriItem.setName(rs.getString("str_name"));
			DistriItem.setCount(rs.getInt("Int_count"));
			DistriItem.setMatitem(rs.getString("Str_matitem"));
			DistriItem.setDescription(rs.getString("Str_description"));
			list_DistriItem.add(DistriItem);
		}
		if (stmt != null)
			stmt.close();
		return list_DistriItem;
	}
	
	/**
	 * ͨ������ָʾ����ɾ��װ��ָʾ��
	 * 
	 * @param id ����ָʾ����
	 * @param con ���Ӷ���
	 * @throws SQLException SQL�쳣
	 */
	public void delDistriItemByDistributionDocId(int id,Connection con)throws SQLException{
		DAO_DistributionDoc dao_DistributionDoc = (DAO_DistributionDoc) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_DistributionDoc.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ������ָʾ����ɾ��������SQL��" + dao_DistributionDoc.delDistriItemByDistributionDocId(id));
		stmt.execute(dao_DistributionDoc.delDistriItemByDistributionDocId(id));
			if (stmt != null) {
				stmt.close();
			}
	}
}
