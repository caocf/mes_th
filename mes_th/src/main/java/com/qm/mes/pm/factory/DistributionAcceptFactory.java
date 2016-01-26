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
import com.qm.mes.pm.bean.DistributionAccept;
import com.qm.mes.pm.dao.DAO_DistributionAccept;
import com.qm.mes.system.dao.DAOFactoryAdapter;

/**
 * ����ȷ�ϵ�������
 * 
 * @author YuanPeng
 *
 */
public class DistributionAcceptFactory {
	private final Log log = LogFactory.getLog(DistributionAcceptFactory.class);// ��־
	
	/**
	 * ��������ȷ�ϵ�
	 * 
	 * @param distributionAccept ����ȷ�ϵ�����
	 * @param con	���Ӷ���
	 * @throws SQLException	SQL�쳣
	 */
	public void saveDistributionAccept(DistributionAccept distributionAccept,
			Connection con) throws SQLException {
		DAO_DistributionAccept dao_DistributionAccept = (DAO_DistributionAccept) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),DAO_DistributionAccept.class);
		Statement stmt = con.createStatement();
		log.debug("��������ȷ�ϵ�SQL��" + dao_DistributionAccept.saveDistributionAccept(distributionAccept));
		stmt.execute(dao_DistributionAccept.saveDistributionAccept(distributionAccept));
		if (stmt != null) {
			stmt.close();
		}
	}
	
	/**
	 * ��������ȷ�ϵ�
	 * 
	 * @param id ����ȷ�ϵ����
	 * @param userid �û�ID
	 * @param con	���Ӷ���
	 * @throws SQLException	SQL�쳣
	 */
	public void transactDistributionAccept(int id,int userid,Connection con) throws SQLException {
		DAO_DistributionAccept dao_DistributionAccept = (DAO_DistributionAccept) DAOFactoryAdapter
			.getInstance(DataBaseType.getDataBaseType(con),DAO_DistributionAccept.class);
		Statement stmt = con.createStatement();
		log.debug("��������ȷ�ϵ�SQL��" + dao_DistributionAccept.transactDistributionAccept(id,userid));
		stmt.execute(dao_DistributionAccept.transactDistributionAccept(id,userid));
		if (stmt != null) {
			stmt.close();
		}
	}
	
	/**
	 * ͨ����Ų������ȷ�ϵ�
	 * 
	 * @param id	����ȷ�ϵ����
	 * @param con	���Ӷ���
	 * @return
	 * @throws SQLException	SQL�쳣
	 */
	public DistributionAccept getDistributionAcceptById(int id,Connection con)
		throws SQLException, ParseException {
		DistributionAccept distributionAccept = new DistributionAccept();
		DAO_DistributionAccept dao_DistributionAccept = (DAO_DistributionAccept) DAOFactoryAdapter
			.getInstance(DataBaseType.getDataBaseType(con),DAO_DistributionAccept.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ����Ų������ȷ�ϵ���sql��" + dao_DistributionAccept.getDistributionAcceptById(id));
		ResultSet rs = stmt.executeQuery(dao_DistributionAccept.getDistributionAcceptById(id));
		while(rs.next()){
			distributionAccept.setId(rs.getInt("int_id"));
			distributionAccept.setDisDocId(rs.getInt("Int_DisDocId"));
			distributionAccept.setState(rs.getInt("Int_State"));
			distributionAccept.setResponseUID(rs.getInt("Int_responseUID"));
			distributionAccept.setRequestDate(rs.getString("Dat_requestDate") == null ? null
					: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			.parse(rs.getString("Dat_requestDate")));
			distributionAccept.setResponseDate(rs.getString("Dat_responseDate") == null ? null
					: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			.parse(rs.getString("Dat_responseDate")));
			distributionAccept.setMateriel(rs.getString("Str_materiel"));
		}
		if (stmt != null) {
			stmt.close();
		}
		return distributionAccept;
	}
	
	/**
	 * ͨ������ָʾ����Ӧ������Ԫ��ѯ����ȷ�ϵ�
	 * 
	 * @param responseProUnit ����ָʾ����Ӧ������Ԫ 
	 * @param con  ���Ӷ���
	 * @return
	 * @throws SQLException SQL�쳣
	 */
	public List<DistributionAccept> getDistributionAcceptsByresponseProUnit(int responseProUnit,Connection con)
		throws SQLException, ParseException {
		List<DistributionAccept> disAccept_list = new ArrayList<DistributionAccept>();
		DAO_DistributionAccept dao_DistributionAccept = (DAO_DistributionAccept) DAOFactoryAdapter
			.getInstance(DataBaseType.getDataBaseType(con),DAO_DistributionAccept.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ������ָʾ����Ӧ������Ԫ��ѯ����ȷ�ϵ���sql��" + dao_DistributionAccept.getDistributionAcceptsByresponseProUnit(responseProUnit));
		ResultSet rs = stmt.executeQuery(dao_DistributionAccept.getDistributionAcceptsByresponseProUnit(responseProUnit));
		while(rs.next()){
			DistributionAccept distributionAccept = new DistributionAccept();
			distributionAccept.setId(rs.getInt("int_id"));
			distributionAccept.setDisDocId(rs.getInt("Int_DisDocId"));
			distributionAccept.setState(rs.getInt("Int_State"));
			distributionAccept.setResponseUID(rs.getInt("Int_responseUID"));
			distributionAccept.setRequestDate(rs.getString("Dat_requestDate") == null ? null
					: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			.parse(rs.getString("Dat_requestDate")));
			distributionAccept.setResponseDate(rs.getString("Dat_responseDate") == null ? null
					: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			.parse(rs.getString("Dat_responseDate")));
			distributionAccept.setMateriel(rs.getString("Str_materiel"));
			disAccept_list.add(distributionAccept);
		}
		if (stmt != null) {
			stmt.close();
		}
		return disAccept_list;
	}
}
