package com.qm.mes.ra.factory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.DataBaseType;
import com.qm.mes.ra.bean.ConversionState;
import com.qm.mes.ra.bean.State;
import com.qm.mes.ra.dao.DAO_ConversionState;
import com.qm.mes.ra.dao.DAO_State;
import com.qm.mes.system.dao.DAOFactoryAdapter;


public class StateManager {
	private final Log log = LogFactory.getLog(StateManager.class);
	/**
	 * ��ѯȫ��״̬��Ϣ
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public List<State> getAllState(Connection con) throws SQLException {
		List<State> list = new ArrayList<State>();
		DAO_State dao_State = (DAO_State) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_State.class);
		Statement stmt = con.createStatement();
		log.debug("��ѯȫ��״̬��Ϣ"+dao_State.getAllState());
		ResultSet rs = stmt.executeQuery(dao_State.getAllState());
		while (rs.next()) {
			State s = new State();
			s.setId(rs.getInt("int_id"));
			s.setStateName(rs.getString("str_statename"));
			s.setStyle(rs.getString("str_style"));
			s.setDelete(rs.getInt("int_delete"));
			list.add(s);
		}
		if (stmt != null) {
			stmt.close();
		} 
		
		return list;
	}
	/**
	 * ��ѯȫ��״̬ת����Ϣ
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public List<State> getAllConversionState(Connection con) throws SQLException {
		List<State> list = new ArrayList<State>();
		DAO_ConversionState  dao_State = (DAO_ConversionState) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_State.class);
		Statement stmt = con.createStatement();
		log.debug("��ѯȫ��״̬ת����Ϣ"+ dao_State.getAllConversionState());
		ResultSet rs = stmt.executeQuery( dao_State.getAllConversionState());
		while (rs.next()) {
			State s = new State();
			s.setId(rs.getInt("int_id"));
			s.setStateName(rs.getString("str_statename"));
			s.setStyle(rs.getString("str_style"));
			s.setDelete(rs.getInt("int_delete"));
			list.add(s);
		}
		if (stmt != null) {
			stmt.close();
		} 
		
		return list;
	}
	/**
	 * ͨ����Ų�ѯState
	 * @param id
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public State getStateById(int id, Connection con) throws SQLException {
		State s = null;
		DAO_State dao_State = (DAO_State) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_State.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ����Ų�ѯState"+ dao_State.getStateById(id));
		ResultSet rs = stmt.executeQuery(dao_State.getStateById(id));
		while (rs.next()) {
			s = new State();
			s.setId(rs.getInt("int_id"));
			s.setStateName(rs.getString("str_statename"));
			s.setStyle(rs.getString("str_style"));
			s.setDelete(rs.getInt("int_delete"));	
			s.setStyledesc(rs.getString("str_styledesc"));

			}
		if (stmt != null) {
			stmt.close();
		}
		return s;
	}
	
	/**
	 * ͨ��״̬���Ʒ���״̬��Ϣ
	 * @param id
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public State getStateByName(String name, Connection con)
			throws SQLException {
		State s = null;
		DAO_State dao_State = (DAO_State) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_State.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ��״̬���Ʒ���״̬��Ϣ"+ dao_State.getStateByName(name));
		ResultSet rs = stmt.executeQuery(dao_State.getStateByName(name));
		while (rs.next()) {
			s = new State();
			s.setId(rs.getInt("int_id"));
			s.setStateName(rs.getString("str_statename"));
			s.setStyle(rs.getString("str_style"));
			s.setDelete(rs.getInt("int_delete"));
			s.setStyledesc(rs.getString("str_styledesc"));
			if (stmt != null) {
				stmt.close();
			}
		}
		return s;
	}

	/**
	 * ͨ�����ɾ��State
	 * @param id
	 * @param con
	 * @throws SQLException
	 */
	public void delStateById(int id, Connection con) throws SQLException {
		DAO_State dao_State = (DAO_State) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_State.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ�����ɾ��State"+ dao_State.delStateById(id));
		stmt.execute(dao_State.delStateById(id));
		if (stmt != null) {
			stmt.close();
		}
	}
	
	/**
	 * ͨ�����ɾ��ConversionState
	 * @param id
	 * @param con
	 * @throws SQLException
	 */
	public void delConversionStateById(int id, Connection con) throws SQLException {
		DAO_ConversionState dao_ConversioinState = (DAO_ConversionState) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_ConversionState.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ�����ɾ��ConversionState"+ dao_ConversioinState.delConversionStateById(id));
		stmt.execute(dao_ConversioinState.delConversionStateById(id));
		
		if (stmt != null) {
			stmt.close();
		}
	}

	/**
	 * ����state
	 * @param state
	 * @param con
	 * @throws SQLException
	 */
	public void createState(State state, Connection con) throws SQLException {
		DAO_State dao_State = (DAO_State) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_State.class);
		Statement stmt = con.createStatement();
		log.debug("����state"+ dao_State.saveState(state));
		stmt.execute(dao_State.saveState(state));
		if (stmt != null) {
			stmt.close();
		}
	}
	/**
	 * ����conversionstate
	 * @param conversionstate
	 * @param con
	 * @throws SQLException
	 */
	public void createConversionState(ConversionState ConversionState, Connection con) throws SQLException {
		

		DAO_ConversionState dao_ConversionState = (DAO_ConversionState) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_ConversionState.class);
		Statement stmt = con.createStatement();
		log.debug("����conversionstate"+ dao_ConversionState.saveConversionState(ConversionState));
		stmt.execute(dao_ConversionState.saveConversionState(ConversionState));
		
		if (stmt != null) {
			stmt.close();
		}
	}
	 /**
	 * ����State����
	 * @param state
     * @param con
     *          ���ݿ����Ӷ���
	 * @throws SQLException
     *                  SQL�쳣
	 */
	public void updateState(State state, Connection con)
			throws SQLException {
		DAO_State dao_State = (DAO_State) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_State.class);
		Statement stmt = con.createStatement();
		log.debug("����State����"+ dao_State.updateState(state));
		stmt.execute(dao_State.updateState(state));
     
		if (stmt != null) {
			stmt.close();
		}
	}
	/**
	 * ����ConversionState����
	 *
	 * @param conversionstate
     * @param con
     *          ���ݿ����Ӷ���
	 * @throws SQLException
     *                  SQL�쳣
	 */
	public void updateConversionState(ConversionState ConversionState, Connection con)
			throws SQLException {
		DAO_ConversionState dao_Conversionstate = (DAO_ConversionState) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_ConversionState.class);
		Statement stmt = con.createStatement();
		log.debug("����ConversionState����"+ dao_Conversionstate.updateConversionState(ConversionState));
		stmt.execute(dao_Conversionstate.updateConversionState(ConversionState));
      
		if (stmt != null) {
			stmt.close();
		}
	}

}
