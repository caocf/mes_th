package com.qm.mes.pm.factory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.DataBaseType;
import com.qm.mes.pm.bean.ProLineItem;
import com.qm.mes.pm.bean.ProduceUnitLine;
import com.qm.mes.pm.dao.DAO_ProduceUnitLine;
import com.qm.mes.system.dao.DAOFactoryAdapter;


/**
 * ������Ԫ�������ù�����
 * 
 * @author YuanPeng
 *
 */
public class ProduceUnitLineFactory {
	private final Log log = LogFactory.getLog(ProduceUnitLineFactory.class);// ��־

	/**
	 * ����������Ԫ��������
	 * 
	 * @param ProduceUnitLine
	 *            ������Ԫ�������ö���
	 * @param con
	 *            ���Ӷ���
	 * @throws SQLException
	 *             SQL�쳣
	 */
	public void saveProduceUnitLine(ProduceUnitLine ProduceUnitLine, Connection con)
			throws SQLException {
		DAO_ProduceUnitLine dao_ProduceUnitLine = (DAO_ProduceUnitLine) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_ProduceUnitLine.class);
		Statement stmt = con.createStatement();
		log.debug("����������Ԫ��������SQL��" + dao_ProduceUnitLine.saveProduceUnitLine(ProduceUnitLine));
		stmt.execute(dao_ProduceUnitLine.saveProduceUnitLine(ProduceUnitLine));
		if (stmt != null) {
			stmt.close();
		}
	}

	/**
	 * ͨ��ID��ѯ������Ԫ��������
	 * 
	 * @param id
	 *            ������Ԫ�����������к�
	 * @param con
	 *            ���Ӷ���
	 * @return ͨ��ID��ѯ����������Ԫ�������ö���
	 * @throws java.sql.SQLException
	 */
	public ProduceUnitLine getProduceUnitLineById(int id, Connection con)
			throws SQLException, ParseException {
		ProduceUnitLine ProduceUnitLine = new ProduceUnitLine();
		DAO_ProduceUnitLine dao_ProduceUnitLine = (DAO_ProduceUnitLine) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_ProduceUnitLine.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ��ID��ѯ������Ԫ��������SQL��" + dao_ProduceUnitLine.getProduceUnitLineById(id));
		ResultSet rs = stmt
				.executeQuery(dao_ProduceUnitLine.getProduceUnitLineById(id));
		if (rs.next()) {
			ProduceUnitLine.setId(rs.getInt("int_id"));
			ProduceUnitLine.setName(rs.getString("str_name"));
			ProduceUnitLine.setDescription(rs.getString("Str_description"));
		}
		if (stmt != null)
			stmt.close();
		return ProduceUnitLine;
	}

	/**
	 * ͨ��������Ԫ�������ú�ɾ����������Ԫ��������
	 * 
	 * @param id
	 *            ������Ԫ�������ú�
	 * @param con
	 *            ���Ӷ���
	 * @throws SQLException
	 *             SQL�쳣
	 */
	public void delProduceUnitLineById(int id, Connection con) throws SQLException {
		DAO_ProduceUnitLine dao_ProduceUnitLine = (DAO_ProduceUnitLine) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_ProduceUnitLine.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ��������Ԫ�������ú�ɾ����������Ԫ��������SQL��"
				+ dao_ProduceUnitLine.delProduceUnitLineById(id));
		stmt.execute(dao_ProduceUnitLine.delProduceUnitLineById(id));
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}
	}
	
	/**
	 * �����ѯ������Ԫ��������
	 * 
	 * @param con
	 *            ���Ӷ���
	 * @return �����ѯ������Ԫ���������б�
	 * @throws SQLException
	 *             SQL�쳣
	 * @throws ParseException
	 *             ����ת���쳣
	 */
	public List<ProduceUnitLine> getAllProduceUnitLinesByDESC(Connection con)throws SQLException, ParseException{
		List<ProduceUnitLine> list_ProduceUnitLine = new ArrayList<ProduceUnitLine>();
		DAO_ProduceUnitLine dao_ProduceUnitLine = (DAO_ProduceUnitLine) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_ProduceUnitLine.class);
		Statement stmt = con.createStatement();
		log.debug("�����ѯ������Ԫ��������SQL��" + dao_ProduceUnitLine.getAllProduceUnitLinesByDESC());
		ResultSet rs = stmt
				.executeQuery(dao_ProduceUnitLine.getAllProduceUnitLinesByDESC());
		while (rs.next()) {
			ProduceUnitLine ProduceUnitLine = new ProduceUnitLine();
			ProduceUnitLine.setId(rs.getInt("int_id"));
			ProduceUnitLine.setName(rs.getString("str_name"));
			ProduceUnitLine.setDescription(rs.getString("Str_description"));
			list_ProduceUnitLine.add(ProduceUnitLine);
		}
		if (stmt != null)
			stmt.close();
		return list_ProduceUnitLine;
	}
	
	/**
	 * ͨ��������Ԫ������������ѯ������Ԫ�������ú�
	 * 
	 * @param name	������Ԫ��������
	 * @param con	���Ӷ���
	 * @return ������Ԫ�������ú�
	 * @throws SQLException SQL�쳣
	 */
	public int getProduceUnitLineIdByName(String name ,Connection con)throws SQLException{
		int id=0;
		DAO_ProduceUnitLine dao_ProduceUnitLine = (DAO_ProduceUnitLine) DAOFactoryAdapter
			.getInstance(DataBaseType.getDataBaseType(con),DAO_ProduceUnitLine.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ��������Ԫ������������ѯ���SQL��" + dao_ProduceUnitLine.getProduceUnitLineIdByName(name));
		ResultSet rs = stmt.executeQuery(dao_ProduceUnitLine.getProduceUnitLineIdByName(name));
		if (rs.next()) {
			id = rs.getInt("int_id");
		}
		if (stmt != null)
			stmt.close();
		return id;
	}
	
	/**
	 * ͨ��������Ԫ������������ѯ������Ԫ������������
	 * 
	 * @param name ������Ԫ����������
	 * @param con ���Ӷ���
	 * @return ������Ԫ������������
	 * @throws SQLException SQL�쳣
	 */
	public int getProduceUnitLineCountByName(String name ,Connection con)throws SQLException{
		int count=0;
		DAO_ProduceUnitLine dao_ProduceUnitLine = (DAO_ProduceUnitLine) DAOFactoryAdapter
			.getInstance(DataBaseType.getDataBaseType(con),DAO_ProduceUnitLine.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ��������Ԫ������������ѯ������Ԫ������������SQL��" + dao_ProduceUnitLine.getProduceUnitLineCountByName(name));
		ResultSet rs = stmt.executeQuery(dao_ProduceUnitLine.getProduceUnitLineCountByName(name));
		if (rs.next()) {
			count = rs.getInt(1);
		}
		if (stmt != null)
			stmt.close();
		return count;
	}
	
	/**
	 * ����������Ԫ��������
	 * 
	 * @param ProduceUnitLine ������Ԫ�������ö���
	 * @param con ���Ӷ���
	 * @throws SQLException SQL�쳣
	 */
	public void updateProduceUnitLine(ProduceUnitLine ProduceUnitLine, Connection con)
			throws SQLException {
		DAO_ProduceUnitLine dao_ProduceUnitLine = (DAO_ProduceUnitLine) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),DAO_ProduceUnitLine.class);
		Statement stmt = con.createStatement();
		log.debug("����������Ԫ��������SQL��"
				+ dao_ProduceUnitLine.updateProduceUnitLine(ProduceUnitLine));
		stmt.execute(dao_ProduceUnitLine.updateProduceUnitLine(ProduceUnitLine));
		if (stmt != null) {
			stmt.close();
		}
	}
	
	
	

	
	
	
	
	
	
	
	/**
	 * ����װ������Ԫ��������
	 * 
	 * @param ProLineItem װ������Ԫ�������ݶ���
	 * @param con ���Ӷ���
	 * @throws SQLException  SQL�쳣
	 */
	public void saveProLineItem(ProLineItem ProLineItem,Connection con)throws SQLException{
		DAO_ProduceUnitLine dao_ProduceUnitLine = (DAO_ProduceUnitLine) DAOFactoryAdapter
			.getInstance(DataBaseType.getDataBaseType(con),DAO_ProduceUnitLine.class);
		Statement stmt = con.createStatement();
		log.debug("����װ������Ԫ��������SQL��" + dao_ProduceUnitLine.saveProLineItem(ProLineItem));
		stmt.execute(dao_ProduceUnitLine.saveProLineItem(ProLineItem));
			if (stmt != null) {
				stmt.close();
			}
	}
	
	/**
	 * ͨ��������Ԫ�������úŲ�ѯװ������Ԫ��������
	 * 
	 * @param id ������Ԫ�������ú�
	 * @param con ���Ӷ���
	 * @return ͨ��������Ԫ�������úŲ�ѯװ������Ԫ���������б�
	 * @throws SQLException SQL�쳣
	 */
	public List<ProLineItem> getProLineItemByProduceUnitLineId(int id,Connection con)throws SQLException{
		List<ProLineItem> list_ProLineItem = new ArrayList<ProLineItem>();
		DAO_ProduceUnitLine dao_ProduceUnitLine = (DAO_ProduceUnitLine) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_ProduceUnitLine.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ��������Ԫ�������úŲ�ѯװ������Ԫ���������б�SQL��" + dao_ProduceUnitLine.getProLineItemByProduceUnitLineId(id));
		ResultSet rs = stmt
				.executeQuery(dao_ProduceUnitLine.getProLineItemByProduceUnitLineId(id));
		while (rs.next()) {
			ProLineItem ProLineItem = new ProLineItem();
			ProLineItem.setId(rs.getInt("int_id"));
			ProLineItem.setProduceUnitId(rs.getInt("Int_produceUnitId"));
			ProLineItem.setOrder(rs.getInt("Int_Order"));
			ProLineItem.setLineId(rs.getInt("Int_LineId"));
			list_ProLineItem.add(ProLineItem);
		}
		if (stmt != null)
			stmt.close();
		return list_ProLineItem;
	}
	
	/**
	 * ͨ��������Ԫ�������ú�ɾ��װ������Ԫ��������
	 * 
	 * @param id ������Ԫ�������ú�
	 * @param con ���Ӷ���
	 * @throws SQLException SQL�쳣
	 */
	public void delProLineItemByProduceUnitLineId(int id,Connection con)throws SQLException{
		DAO_ProduceUnitLine dao_ProduceUnitLine = (DAO_ProduceUnitLine) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_ProduceUnitLine.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ��������Ԫ�������ú�ɾ��װ������Ԫ��������SQL��" + dao_ProduceUnitLine.delProLineItemByProduceUnitLineId(id));
		stmt.execute(dao_ProduceUnitLine.delProLineItemByProduceUnitLineId(id));
			if (stmt != null) {
				stmt.close();
			}
	}
}