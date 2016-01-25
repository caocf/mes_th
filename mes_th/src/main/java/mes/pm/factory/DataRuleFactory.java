package mes.pm.factory;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import java.util.ArrayList;
import java.util.List;

import mes.framework.DataBaseType;
import mes.pm.bean.DataRule;
import mes.pm.bean.DataRuleArg;
import mes.pm.dao.DAO_DataRule;
import mes.system.dao.DAOFactoryAdapter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * ���ݹ��򹤳���
 * @author Xujia
 *
 */
public class DataRuleFactory {
	//������־�ļ�
	private final Log log = LogFactory.getLog(DataRuleFactory.class);
	/**  ���
	 * ����DataRule
	 * @param DataRule
	 * @param con
	 * @throws SQLException
	 */
	public void createDataRule(DataRule dataRule,Connection con) throws SQLException{
		DAO_DataRule dao_DataRule = (DAO_DataRule) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_DataRule.class);
		Statement stmt = con.createStatement();
		log.debug("����datarule "+dao_DataRule.saveDataRule(dataRule));
		stmt.execute(dao_DataRule.saveDataRule(dataRule));		
		if(stmt!=null){
			stmt.close();
			stmt=null;
		}
	}
	/**  ���
	 * ����DataRuleArgs
	 * @param DataRuleArgs
	 * @param con
	 * @throws SQLException
	 */
	public void createDataRuleArgs(DataRuleArg dataRuleArg,Connection con) throws SQLException{
		DAO_DataRule dao_DataRule = (DAO_DataRule) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_DataRule.class);
		Statement stmt = con.createStatement();
		log.debug("����dataruleargs "+dao_DataRule.saveDataRuleArg(dataRuleArg));		
		stmt.execute(dao_DataRule.saveDataRuleArg(dataRuleArg));		
		if(stmt!=null){
			stmt.close();
			stmt=null;
		}
	}
	/**  ���
	 * ��ѯȫ�����ݹ�����Ϣ
	 * @param con
	 * @return
	 * @throws SQLException 
	 */
	public List<DataRule> getAllDataRule(Connection con) throws SQLException{
		List<DataRule> list = new ArrayList<DataRule>();
		DAO_DataRule dao_DataRule = (DAO_DataRule) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_DataRule.class);
		Statement stmt = con.createStatement();
		log.debug("��ѯ���ݹ�����Ϣ "+dao_DataRule.getAllDataRule());
		ResultSet rs = stmt.executeQuery(dao_DataRule.getAllDataRule());
		while(rs.next()){
			DataRule r = new DataRule();			
		     r.setId(rs.getInt("int_id"));
		     r.setName(rs.getString("str_name"));
		     r.setCode(rs.getString("str_code"));
		     r.setRule(rs.getString("string_rule"));
		     r.setDescription(rs.getString("str_description"));	
			list.add(r);
		}
		if(rs!=null){
			rs.close();
			rs=null;
		}
		if(stmt!=null){
			stmt.close();
			stmt=null;
		}
		return list;
	}
	
	
	
	/** ���
	 * ͨ�����ɾ��DataRule
	 * @param id
	 * @param con
	 * @throws SQLException 
	 */
	public void delDataRuleById(int id, Connection con) throws SQLException {
		DAO_DataRule dao_DataRule = (DAO_DataRule) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_DataRule.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ�����ɾ�����ݹ��� "+dao_DataRule.delDataRuleById(id));	
		stmt.execute(dao_DataRule.delDataRuleById(id));
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}
	}

	/** ���
	 * ͨ�����ɾ��DataRule����
	 * @param id
	 * @param con
	 * @throws SQLException 
	 */
	public void delDataRuleArgsById(int id, Connection con) throws SQLException {
		DAO_DataRule dao_DataRule = (DAO_DataRule) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_DataRule.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ�����ɾ�����ݹ��� "+dao_DataRule.delDataRuleArgsById(id));	
		stmt.execute(dao_DataRule.delDataRuleArgsById(id));
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}
	}
	
	 /**  ���
	 * ����DataRule����
	 *
	 * @param DataRule
     * @param con
     *          ���ݿ����Ӷ���
	 * @throws SQLException
     *                  SQL�쳣
	 */
	public void updateDataRule(DataRule dataRule, Connection con)
			throws SQLException {
		DAO_DataRule dao_DataRule = (DAO_DataRule) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_DataRule.class);
		Statement stmt = con.createStatement();
		log.debug("����DataRule���� "+dao_DataRule.updateDataRule(dataRule));
		stmt.execute(dao_DataRule.updateDataRule(dataRule));       
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}
	}
	/**
	 * ͨ�����ݹ�������ѯDataRule
	 * @param id
	 * @param con
	 * @return
	 * @throws SQLException 
	 */
	public DataRule getDataRuleByName(String str_name,Connection con) throws SQLException{
		DataRule d = null;
		DAO_DataRule dao_DataRule = (DAO_DataRule) DAOFactoryAdapter
		.getInstance(DataBaseType.getDataBaseType(con),
				DAO_DataRule.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ�����ݹ�������ȡ���ݹ���SQL��䣺"+dao_DataRule.getDataRuleByName(str_name));
		ResultSet rs = stmt.executeQuery(dao_DataRule.getDataRuleByName(str_name));
		log.debug("ͨ�����ݹ�������ȡ���ݹ����б�---");
		while(rs.next()){
			d = new DataRule();
			d.setId(rs.getInt("int_id"));
			d.setName(rs.getString("str_name"));
			d.setCode(rs.getString("str_code"));
			d.setRule(rs.getString("string_rule"));
			d.setDescription(rs.getString("str_description"));
			log.debug("���ݹ���ţ�"+rs.getInt("int_id")+"�����ݹ�����"+rs.getString("str_name")+"�����ݹ�����룺"
					+rs.getString("str_code")+"����ʽ��"+rs.getString("string_rule")+"��������"
					+rs.getString("str_description"));
		}
		if(rs!=null){
			rs.close();
			rs=null;
		}
		if(stmt!=null){
			stmt.close();
			stmt=null;
		}
		return d;
	}
	
	/**
	 * ͨ��ID��ѯָ��
	 * 
	 * @param id
	 *            ָ�����к�
	 * @param con
	 *            ���Ӷ���
	 * @return ͨ��ID��ѯ����ָ�����
	 * @throws java.sql.SQLException
	 */
	public DataRule getDataRuleById(int id, Connection con)
			throws SQLException, ParseException {
		DataRule dr = new DataRule();
		DAO_DataRule dao_DataRule = (DAO_DataRule) DAOFactoryAdapter
		.getInstance(DataBaseType.getDataBaseType(con),
				DAO_DataRule.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ��ID��ѯװ��ָʾ��SQL��" + dao_DataRule.getDataRuleById(id));
		ResultSet rs = stmt
				.executeQuery(dao_DataRule.getDataRuleById(id));
		if (rs.next()) {
			dr.setId(rs.getInt("int_id"));
			dr.setName(rs.getString("str_name"));
			dr.setCode(rs.getString("str_code"));
			dr.setDescription(rs.getString("str_description"));
			dr.setRule(rs.getString("string_rule"));
		}
		if (stmt != null)
			stmt.close();
		return dr;
	}
	
	/**
	 * ͨ������Ų�ѯ�������
	 * 
	 * @param id ���򵥺�
	 * @param con ���Ӷ���
	 * @return ��������б�
	 * @throws SQLException SQL�쳣
	 */
	public List<DataRuleArg> getDataRuleArgByDataRuleId(int id,Connection con)throws SQLException{
		List<DataRuleArg> list_DataRuleArg = new ArrayList<DataRuleArg>();
		DAO_DataRule dao_DataRule = (DAO_DataRule) DAOFactoryAdapter
		.getInstance(DataBaseType.getDataBaseType(con),
				DAO_DataRule.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ������Ų�ѯ��������б�SQL��" + dao_DataRule.getDataRuleArgsById(id));
		ResultSet rs = stmt.executeQuery(dao_DataRule.getDataRuleArgsById(id));
		while (rs.next()) {
			DataRuleArg dataRuleArg = new DataRuleArg();
			dataRuleArg.setId(rs.getInt("int_id"));
			dataRuleArg.setName(rs.getString("str_argname"));
			dataRuleArg.setDescription(rs.getString("str_description"));
			dataRuleArg.setInt_dataruleid(rs.getInt("int_dataruleid"));			
			list_DataRuleArg.add(dataRuleArg);
		}
		if (stmt != null)
			stmt.close();
		return list_DataRuleArg;
	}

}
