package com.qm.mes.ra.factory;
import java.sql.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.DataBaseType;
import com.qm.mes.ra.dao.DAO_State;
import com.qm.mes.system.dao.DAOFactoryAdapter;
/**
 * author : л����
 */
public class GatherStateRuleFactory {
	private final Log log = LogFactory.getLog(GatherStateRuleFactory.class);
	/**
	 * �����ɼ���Ĺ���
	 * л����
	 * @param int_gatherid  �ɼ����id
	 * @param Stateconversionid
	 * @param defaultgo
	 * @param con
	 * @throws SQLException
	 */
	public void saveGatherStateRule(int int_gatherid,int Stateconversionid,int defaultgo,Connection con)throws SQLException {
		DAO_State dao_State = (DAO_State) DAOFactoryAdapter
		.getInstance(DataBaseType.getDataBaseType(con),
				DAO_State.class);
		Statement stmt = con.createStatement();
		log.debug("�����ɼ���״̬����"+dao_State.saveGatherStateRule(int_gatherid, Stateconversionid, defaultgo));
		stmt.execute(dao_State.saveGatherStateRule(int_gatherid, Stateconversionid, defaultgo));

		if (stmt != null) {
			stmt.close();
		}
	}
	/**
	 * ��ɾ���ɼ���Ĺ���
	 * �ڲɼ����޸�ҳ�浱�û�Ҫ�޸Ĳɼ������ʱӦ��ɾ����ǰ�Ĺ����ڽ��д����µĹ���
	 * @param int_gatherid �ɼ����id
	 * @param con
	 * @throws SQLException
	 */
	public void delgatherstaterule(int int_gatherid ,Connection con) throws SQLException {
		DAO_State dao_State = (DAO_State) DAOFactoryAdapter
		.getInstance(DataBaseType.getDataBaseType(con),
				DAO_State.class);
		Statement stmt = con.createStatement();
		log.debug("��ɾ���ɼ���Ĺ���"+dao_State.delgatherstaterule(int_gatherid));
		stmt.executeUpdate(dao_State.delgatherstaterule(int_gatherid));
		if(stmt!=null){
			stmt.close();
		}
		
	}

}
