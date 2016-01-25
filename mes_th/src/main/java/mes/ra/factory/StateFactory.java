package mes.ra.factory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mes.framework.DataBaseType;
import mes.system.dao.DAOFactoryAdapter;
import mes.ra.bean.State;
import mes.ra.dao.DAO_State;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StateFactory {
	private final Log log = LogFactory.getLog(StateFactory.class);
	/**  ���
	 * ��ѯȫ��״̬��Ϣ
	 * @param con
	 * @return
	 * @throws SQLException 
	 */
	public List<State> getAllState(Connection con) throws SQLException{
		List<State> list = new ArrayList<State>();
		DAO_State dao_State = (DAO_State) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_State.class);
		Statement stmt = con.createStatement();
		log.debug("��ѯȫ��״̬��Ϣ"+dao_State.getAllState());
		ResultSet rs = stmt.executeQuery(dao_State.getAllState());
		while(rs.next()){
			State s = new State();
			s.setId(rs.getInt("int_id"));
			s.setStateName(rs.getString("str_statename"));
			s.setStyle(rs.getString("str_style"));
			s.setDelete(rs.getInt("int_delete"));
			list.add(s);
		}
		if(stmt!=null){
			stmt.close();
		}
		return list;
	}
	
	/** ���
	 * ͨ�����ɾ��State
	 * @param id
	 * @param con
	 * @throws SQLException 
	 */
	public void delStateById(int id, Connection con) throws SQLException {
		DAO_State dao_State = (DAO_State) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_State.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ�����ɾ��State"+dao_State.delStateById(id));	
		stmt.execute(dao_State.delStateById(id));
		if (stmt != null) {
			stmt.close();
		}
	}
	
	/**  ���
	 * ����state
	 * @param state
	 * @param con
	 * @throws SQLException
	 */
	public void createState(State state,Connection con) throws SQLException{
		DAO_State dao_State = (DAO_State) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_State.class);
		Statement stmt = con.createStatement();
		log.debug("����state"+dao_State.saveState(state));
		stmt.execute(dao_State.saveState(state));		
		if(stmt!=null){
			stmt.close();
		}
	}
	
	 /**  ���
	 * ����State����
	 *
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
		log.debug("����State����"+dao_State.updateState(state));
		stmt.execute(dao_State.updateState(state));
       
		if (stmt != null) {
			stmt.close();
		}
	}
	
	/**  ���
	 * ���״̬�Ƿ��Ѿ�����
	 * @param gatherid
	 
	 * @throws SQLException
	 */
	public int checkState1(int id, 
			Connection con) throws SQLException {
		DAO_State dao_State = (DAO_State) DAOFactoryAdapter
		.getInstance(DataBaseType.getDataBaseType(con),
				DAO_State.class);
		Statement stmt = con.createStatement();
		log.debug("���״̬�Ƿ��Ѿ�����"+dao_State.checkStateById1(id));
		int count = 0;
		ResultSet rs = stmt.executeQuery(dao_State
				.checkStateById1(id));
		if (rs.next()) {
			count = rs.getInt(1);
		}
		if (stmt != null) {
			stmt.close();
		}
		return count;
	}
	/**  ���
	 * ���״̬�Ƿ��Ѿ�����
	 * @param gatherid
	 
	 * @throws SQLException
	 */
	public int checkState2(int id, 
			Connection con) throws SQLException {
		DAO_State dao_State = (DAO_State) DAOFactoryAdapter
		.getInstance(DataBaseType.getDataBaseType(con),
				DAO_State.class);
		Statement stmt = con.createStatement();
		log.debug("���״̬�Ƿ��Ѿ�����"+dao_State.checkStateById2(id));
		int count = 0;
		ResultSet rs = stmt.executeQuery(dao_State
				.checkStateById2(id));
		if (rs.next()) {
			count = rs.getInt(1);
		}
		if (stmt != null) {
			stmt.close();
		}
		return count;
	}
	
	/**  ���
	 * ��֤״̬����Ϊname��״̬�Ƿ��Ѿ�����
	 * @param name
	 
	 * @throws SQLException
	 */
	public int checkState(String name, 
			Connection con) throws SQLException {
		DAO_State dao_State = (DAO_State) DAOFactoryAdapter
		.getInstance(DataBaseType.getDataBaseType(con),
				DAO_State.class);
		Statement stmt = con.createStatement();
		int count = 0;
		log.debug("��֤״̬����Ϊname��״̬�Ƿ��Ѿ�����"+dao_State	.checkStateByName(name));
		ResultSet rs = stmt.executeQuery(dao_State
				.checkStateByName(name));
		if (rs.next()) {
			count = rs.getInt(1);
		}
		if (stmt != null) {
			stmt.close();
		}
		return count;
	}


	/**
	 * ͨ���ɼ����id�鿴Ĭ��״̬װ����ϵ��
	 * л����
	 * @param int_gatherid
	 * @param con
	 * @return
	 * @throws SQLException
	 */
    public int getconversionidBy(int int_gatherid,Connection con) throws SQLException{
    	DAO_State dao_State = (DAO_State) DAOFactoryAdapter
		.getInstance(DataBaseType.getDataBaseType(con),
				DAO_State.class);
    	int n=0;
    	Statement stmt = con.createStatement();
    	log.debug("ͨ���ɼ����id�鿴Ĭ��״̬װ����ϵ��"+dao_State.getconversionidBy(int_gatherid));
    	ResultSet rs = stmt.executeQuery(dao_State.getconversionidBy(int_gatherid));
    	if(rs.next())
    		n=rs.getInt(1);
		if(stmt!=null){
			stmt.close();
		}
    	return n;
    		}
    
	/**
	 * ͨ��״̬id
	 * �鿴״̬��
	 * л����
	 * @param id
	 * @param con
	 * @return
	 * @throws SQLException
	 */
    public String getStateById(int id,Connection con) throws SQLException{
    	DAO_State dao_State = (DAO_State) DAOFactoryAdapter
		.getInstance(DataBaseType.getDataBaseType(con),
				DAO_State.class);	
    	Statement stmt = con.createStatement();
    	log.debug("ͨ��״̬id�鿴״̬��"+dao_State.getStateById(id));
    	ResultSet rs=stmt.executeQuery(dao_State.getStateById(id));
    	String name=null;
    	if(rs.next())
    		 name=rs.getString("str_statename");
		if(stmt!=null){
			stmt.close();
		}
    	return name;
    }
	
    
    /**
     * ͨ��״̬ID�Ų�ѯ״̬����
     * 
     * @param id
     * 		״̬���
     * @param con
     * 		���Ӷ���
     * @return ״̬����
     */
    public State getStateById2(int id ,Connection con){
    	State state = new State();
    	try{
	    	DAO_State dao_State = (DAO_State) DAOFactoryAdapter
			.getInstance(DataBaseType.getDataBaseType(con),
					DAO_State.class);	
	    	Statement stmt = con.createStatement();
	    	log.debug("ͨ��״̬ID�Ų�ѯ״̬����"+dao_State.getStateById(id));
	    	ResultSet rs=stmt.executeQuery(dao_State.getStateById(id));
	    	if(rs.next()){
	    		state.setId(rs.getInt("int_id"));
	    		state.setStateName(rs.getString("str_statename"));
	    		state.setStyle(rs.getString("str_style"));
	    		state.setDelete(rs.getInt("int_delete"));
	    	}
			if(stmt!=null){
				stmt.close();
			}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return state;
    }
    }
