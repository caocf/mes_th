package com.qm.mes.tg.factory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.DataBaseType;
import com.qm.mes.system.dao.DAOFactoryAdapter;
import com.qm.mes.tg.bean.MaterielRule;
import com.qm.mes.tg.dao.IDAO_MaterielRule;

public class MaterielRuleFactory {
	
	//��־
	private final Log log = LogFactory.getLog(MaterielRuleFactory.class);

	/**
	 * ͨ���ɼ�����ţ�����òɼ������ϵ�ȫ����֤���������Ϲ����ڵ�һλ�������Ϲ�����������
	 * 
	 * @param id
	 *            �ɼ������
	 * @return �����б�
	 * @throws SQLException
	 */
	public List<MaterielRule> getListByGid(int id, Connection con)
			throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			IDAO_MaterielRule dao_materielRule = (IDAO_MaterielRule) DAOFactoryAdapter
					.getInstance(DataBaseType.getDataBaseType(con),
							IDAO_MaterielRule.class);
			stmt = con.createStatement();
			List<MaterielRule> list = new ArrayList<MaterielRule>();
			log.debug("ͨ����Ų�ѯ�����ϱ�ʶ����SQL��䣺"+dao_materielRule.gerMainRuleByGid(id));
			// ��ѯ�����ϵ���֤����
			rs = stmt.executeQuery(dao_materielRule.gerMainRuleByGid(id));
			if (rs.next()) {
				MaterielRule mr = new MaterielRule();
				mr.setId(rs.getInt("int_id"));
				mr.setName(rs.getString("str_name"));
				mr.setValidate(rs.getString("str_validateclass"));
				log.debug("��������֤����--��������֤����ţ�"+mr.getId()+"����������֤��������"+mr.getName()
						+"����������֤�����ַ���:"+mr.getValidate());
				list.add(mr);
			} else {
				log.fatal("��������֤���򲻴���");
				
				// �����������֤���򲻴��ڽ��׳��쳣
				throw new SQLException("��������֤���򲻴���");
			}
			log.debug("ͨ����Ų�ѯ�����ϱ�ʶ����SQL��䣺"+dao_materielRule.getAttributeByGid(id));
			// ��ѯ�����ϵ���֤����
			rs = stmt.executeQuery(dao_materielRule.getAttributeByGid(id));
			log.debug("�����ϵ���֤����--");
			while (rs.next()) {
				MaterielRule mr = new MaterielRule();
				mr.setId(rs.getInt("int_id"));
				mr.setName(rs.getString("str_name"));
				mr.setValidate(rs.getString("str_validateclass"));
				log.debug("�����ϵ���֤����ţ�"+rs.getInt("int_id")+"�������ϵ���֤��������"+
						rs.getString("str_name")+"�������ϵ���֤�����ַ�����"+rs.getString("str_validateclass"));
				list.add(mr);
			}
			return list;
		} catch (SQLException e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
		}

	}

	/**
	 * ����materielRule
	 * 
	 * @param materielRule
	 * @param con
	 * @throws SQLException
	 */
	public void saveMaterielRule(MaterielRule materielRule, Connection con)
			throws SQLException {
		IDAO_MaterielRule dao = (IDAO_MaterielRule) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						IDAO_MaterielRule.class);
		Statement stmt = con.createStatement();
		log.debug("�������ϱ�ʶ����SQL��䣺"+dao.saveMainRule(materielRule));
		stmt.execute(dao.saveMainRule(materielRule));
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}
	}

	/**
	 * �޸�materielRule
	 * 
	 * @param materielRule
	 * @param con
	 * @throws SQLException
	 */
	public void updateMaterielRule(MaterielRule materielRule, Connection con)
			throws SQLException {
		IDAO_MaterielRule dao = (IDAO_MaterielRule) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						IDAO_MaterielRule.class);
		Statement stmt = con.createStatement();
		log.debug("�޸����ϱ�ʶ����SQL��䣺"+dao.updateMainRule(materielRule));
		stmt.execute(dao.updateMainRule(materielRule));
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}
	}

	/**
	 * ɾ��materielRule
	 * 
	 * @param id
	 * @param con
	 * @throws SQLException
	 */
	public void deleteMaterielRule(int id, Connection con) throws SQLException {
		IDAO_MaterielRule dao = (IDAO_MaterielRule) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						IDAO_MaterielRule.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ�����ϱ�ʶ�����ɾ�����ϱ�ʶ����SQL��䣺"+dao.deleteMainRule(id));
		stmt.execute(dao.deleteMainRule(id));
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}
	}

	/**
	 * �鿴�������ϱ�ʶ����Ĺ���
	 * 
	 * @param id
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public MaterielRule findMaterielRule(int id, Connection con)
			throws SQLException {
		IDAO_MaterielRule dao = (IDAO_MaterielRule) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						IDAO_MaterielRule.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ�����ϱ�ʶ����Ų�ѯ���ϱ�ʶ����SQL��䣺"+dao.findMainRule(id));
		ResultSet rs = stmt.executeQuery(dao.findMainRule(id));
		MaterielRule materielRule =  null;
		if(rs.next()){
			materielRule = new MaterielRule();
			materielRule.setId(rs.getInt("int_id"));
			materielRule.setName(rs.getString("str_name"));
			materielRule.setValidate(rs.getString("str_validateclass"));
			materielRule.setDesc(rs.getString("str_desc"));
			log.debug("���ϵ���֤����ţ�"+rs.getInt("int_id")+"�����ϵ���֤��������"+rs.getString("str_name")+
					"�����ϵ���֤�����ַ�����"+rs.getString("str_validateclass")+"�����ϱ�ʶ����������"+rs.getString("str_desc"));
			
		}
		if(rs!=null){
			rs.close();
			rs=null;
		}
		if(stmt!=null){
			stmt.close();
			stmt=null;
		}
		return materielRule;
	}

	/**
	 * ��ѯ�������ϱ�ʶ����Ĺ���
	 * 
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public List<MaterielRule> selectMaterielRule(Connection con)
			throws SQLException {
		IDAO_MaterielRule dao = (IDAO_MaterielRule) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						IDAO_MaterielRule.class);
		List<MaterielRule> list = new ArrayList<MaterielRule>();
		Statement stmt = con.createStatement();
		log.debug("��ѯ�������ϱ�ʶ����SQL���:"+dao.selectMainRule());
		ResultSet rs = stmt.executeQuery(dao.selectMainRule());
		log.debug("��ѯ�������ϱ�ʶ�����б�---");
		while (rs.next()) {
			MaterielRule m = new MaterielRule();
			m.setId(rs.getInt("int_id"));
			m.setName(rs.getString("str_name"));
			m.setValidate(rs.getString("str_validateclass"));
			log.debug("���ϵ���֤����ţ�"+rs.getInt("int_id")+"�����ϵ���֤��������"+rs.getString("str_name")+
					"�����ϵ���֤�����ַ�����"+rs.getString("str_validateclass"));
			list.add(m);
		}
		if(stmt!=null){
			stmt.close();
			stmt=null;
		}
		return list;
	}
	
	/**
	 * �鿴�������ϱ�ʶ����Ĺ���
	 * 
	 * @param id
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	//��û�õ�
	public MaterielRule findMaterielRuleByName(String name, Connection con)
			throws SQLException {
		IDAO_MaterielRule dao = (IDAO_MaterielRule) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						IDAO_MaterielRule.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ�����ϱ�ʶ��������ѯ���ϱ�ʶ����SQL���:"+dao.findMainRuleByName(name));
		ResultSet rs = stmt.executeQuery(dao.findMainRuleByName(name));
		MaterielRule materielRule =  null;
		if(rs.next()){
			materielRule = new MaterielRule();
			materielRule.setId(rs.getInt("int_id"));
			materielRule.setName(rs.getString("str_name"));
			materielRule.setValidate(rs.getString("str_validateclass"));
			log.debug("���ϵ���֤����ţ�"+rs.getInt("int_id")+"�����ϵ���֤��������"+rs.getString("str_name")+
					"�����ϵ���֤�����ַ�����"+rs.getString("str_validateclass"));
		}
		if(rs!=null){
			rs.close();
			rs=null;
		}
		if(stmt!=null){
			stmt.close();
			stmt=null;
		}
		return materielRule;
	}
}
