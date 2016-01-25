package mes.ra.factory;
/**
 * author л����
 */
import java.sql.*;
import java.util.*;
import mes.framework.DataBaseType;
import mes.ra.bean.*;
import mes.ra.dao.DAO_ProduceUnit ;
import mes.system.dao.DAOFactoryAdapter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProduceUnitFactory {
	private final Log log = LogFactory.getLog(ProduceUnitFactory.class);
	/**����������Ԫ
	 *  author л����
	 * @param produceUnit
	 * @param con
	 * @throws SQLException
	 */
	public void saveProduceUnit(ProduceUnit produceUnit,Connection con) throws SQLException{
		DAO_ProduceUnit dao_produceunit = (DAO_ProduceUnit) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_ProduceUnit .class);
		Statement stmt = con.createStatement();
		log.debug("����������Ԫ"+dao_produceunit.saveProduceUnit(produceUnit));
		stmt.executeUpdate(dao_produceunit.saveProduceUnit(produceUnit));
		if(stmt!=null)
		{
			stmt.close();
		}
		
	}
	/**
	 * ��ѯ���е�������Ԫ������֤����ʱ������Ԫ��Ψһ
	 * л����
	 * @param con
	 * @return
	 * @throws SQLException
	 */
    public List<String> getProduceUnitName(Connection con) throws SQLException{
    	DAO_ProduceUnit dao_produceunit = (DAO_ProduceUnit) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_ProduceUnit .class);
		Statement stmt = con.createStatement();
		log.debug("��ѯ���е�������Ԫ������֤����ʱ������Ԫ��Ψһ"+dao_produceunit.getAllProduceUnit());
		ResultSet rs=stmt.executeQuery(dao_produceunit.getAllProduceUnit());
		List<String>  des=new ArrayList<String>();
		while(rs.next()){
			String name=rs.getString("Str_name").trim();
			des.add(name);
		}
		if(stmt!=null){
			stmt.close();
		}
    	return des;
    }
    /**л����
     * ͨ��ID�ŵõ�������Ԫ��Ϣ
     * @param int_id
     * @param con
     * @return
     * @throws SQLException
     */
  
    public ProduceUnit getProduceUnitbyId(int int_id,Connection con) throws SQLException{
    	DAO_ProduceUnit dao_produceunit = (DAO_ProduceUnit) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_ProduceUnit .class);
		Statement stmt = con.createStatement();
		ProduceUnit produceunit=new ProduceUnit();
		log.debug("�õ�id�ŵ�������Ԫ����Ϣ "+dao_produceunit.getProduceUnitById(int_id));
		ResultSet rs=stmt.executeQuery(dao_produceunit.getProduceUnitById(int_id));
		while(rs.next()){
			   produceunit.setInt_id(rs.getInt("int_id"));
               produceunit.setStr_name(rs.getString("str_name"));
               produceunit.setStr_code(rs.getString("STR_CODE"));
               produceunit.setInt_instructStateID(rs.getInt("INT_INSTRUCTSTATEID"));
               produceunit.setInt_planIncorporate(rs.getInt("INT_PLANINCORPORATE"));
               produceunit.setInt_instCount(rs.getInt("INT_INSTCOUNT"));
               produceunit.setInt_Type(rs.getInt("INT_TYPE"));
               produceunit.setInt_delete(rs.getInt("INT_DELETE"));
               produceunit.setInt_materielRuleid(rs.getInt("INT_MATERIELRULEID"));
		}
		if(stmt!=null){
			stmt.close();
		}
		return produceunit;
    }
    /**
     *  л����
     * ����������Ԫ��Ϣ
     * @param produceunit
     * @param con
     * @throws SQLException
     */
     
  public void  updateProduceUnit(ProduceUnit produceunit,Connection con) throws SQLException{
  	DAO_ProduceUnit dao_produceunit = (DAO_ProduceUnit) DAOFactoryAdapter.getInstance(
			DataBaseType.getDataBaseType(con), DAO_ProduceUnit .class);
	Statement stmt = con.createStatement();
	log.debug("����������Ԫ��Ϣ "+dao_produceunit.updateProduceUnit(produceunit));
	stmt.executeUpdate(dao_produceunit.updateProduceUnit(produceunit));
	if(stmt!=null){
		stmt.close();
	}
  }
  /**
   * * ͨ��idɾ��ָ����������Ԫ
   * �߼�ɾ��
   * л����
   * @param id
   * @param con
   * @throws SQLException
   */
   
  public   void  deleteProduceUnitById(int id,Connection con) throws SQLException{
	  	DAO_ProduceUnit dao_produceunit = (DAO_ProduceUnit) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_ProduceUnit .class);
		Statement stmt = con.createStatement();
		log.debug("ͨ��idɾ��ָ����������Ԫ "+dao_produceunit.deleteProduceUnitById(id));
		stmt.executeUpdate(dao_produceunit.deleteProduceUnitById(id));
		if(stmt!=null){
			stmt.close();
		}
	  }
  /**
   * л����
	 �õ����е�������Ԫ����Ϣ
   * @param con
   * @return
   * @throws SQLException
   */
	 
	public List<ProduceUnit> getAllProduceUnit(Connection con) throws SQLException{
	  	DAO_ProduceUnit dao_produceunit = (DAO_ProduceUnit) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_ProduceUnit .class);
		Statement stmt = con.createStatement();
		log.debug("�õ����е�������Ԫ����Ϣ"+dao_produceunit.getAllProduceUnit());
		List<ProduceUnit>  list=new ArrayList<ProduceUnit>();
		ResultSet rs=stmt.executeQuery(dao_produceunit.getAllProduceUnit());
		while(rs.next()){
			ProduceUnit produceunit=new ProduceUnit();
			produceunit.setInt_id(rs.getInt("INT_ID"));
            produceunit.setStr_name(rs.getString("STR_NAME"));
            produceunit.setStr_code(rs.getString("STR_CODE"));
            produceunit.setInt_instructStateID(rs.getInt("INT_INSTRUCTSTATEID"));
            produceunit.setInt_planIncorporate(rs.getInt("INT_PLANINCORPORATE"));
            produceunit.setInt_instCount(rs.getInt("INT_INSTCOUNT"));
            produceunit.setInt_Type(rs.getInt("INT_TYPE"));
            produceunit.setInt_delete(rs.getInt("INT_DELETE"));
            produceunit.setInt_materielRuleid(rs.getInt("INT_MATERIELRULEID"));
            list.add(produceunit);
            
		}
		if(stmt!=null){
			stmt.close();
		}
		return list;
	}
	/**
	 *  �˶��Ƿ����������Ԫ���ŵ�������Ԫ
	 * @param name
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	
	public boolean checkProduceUnitByName(String name,Connection con) throws SQLException{
    	DAO_ProduceUnit dao_produceunit = (DAO_ProduceUnit) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_ProduceUnit .class);
		Statement stmt = con.createStatement();
		log.debug("�˶��Ƿ����������Ԫ���ŵ�������Ԫ"+dao_produceunit.getProduceUnitByName(name));
		ResultSet rs=stmt.executeQuery(dao_produceunit.getProduceUnitByName(name));
		boolean f=false;
		
		if(rs.next()){
			f=true;	
		}
		if(stmt!=null){
			stmt.close();
		}
	
		return f;
		
	}
	 /**
	  * * ͨ��������Ԫ��id�鵽������Ԫ�ĳ�ʼָ��״̬
	   * л����
	  * @param id
	  * @param con
	  * @return
	  * @throws SQLException
	  */
	   
	public int   getInstructionstateIdByproduceunitid(int id,Connection con) throws SQLException{
    	DAO_ProduceUnit dao_produceunit = (DAO_ProduceUnit) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_ProduceUnit .class);
		Statement stmt = con.createStatement();
		int i=0;
		log.debug("ͨ��������Ԫ��id�鵽������Ԫ�ĳ�ʼָ��״̬"+dao_produceunit.getInstructionstateIdByproduceunitid(id));
		ResultSet rs=stmt.executeQuery(dao_produceunit.getInstructionstateIdByproduceunitid(id));
		if(rs.next()){
			i=rs.getInt(1);
		}
		if(stmt!=null){
			stmt.close();
		}
		return i;
	  }
	
	/**
	 * ͨ�����ϱ�ʶ����Ų�ѯ������Ԫ����
	 * @param materielurleid ���ϱ�ʶ�����
	 * @param con
	 * @return ������Ԫ����
	 */

	  public int countProduceUnitByMateritelRuleID(int materielurleid,Connection con){
		  Statement stmt = null;
		  ResultSet rs = null;
		  int count=0;
		  try{
			  DAO_ProduceUnit dao_produceunit = (DAO_ProduceUnit) DAOFactoryAdapter.getInstance(
					DataBaseType.getDataBaseType(con), DAO_ProduceUnit .class);
			stmt = con.createStatement();
			log.debug("ͨ�����ϱ�ʶ����Ų�ѯ������Ԫ����"+dao_produceunit.countProduceUnitByMateritelRuleID(materielurleid));
			rs=stmt.executeQuery(dao_produceunit.countProduceUnitByMateritelRuleID(materielurleid));
			if(rs.next()){
				count=rs.getInt(1);
			}
			
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
					if(stmt!=null){
						stmt.close();
					}
				}catch(SQLException sqle){sqle.printStackTrace();}
			}
			return count;
	  }
	
	/**
	 * * л����
	 * ͨ��������Ԫ���õ�������Ԫ����Ϣ
	 * @param name
	 * @param con
	 * @return
	 * @throws SQLException
	 */

	public ProduceUnit getProduceUnitByName(String name,Connection con) throws SQLException{
	  	DAO_ProduceUnit dao_produceunit = (DAO_ProduceUnit) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_ProduceUnit .class);
		Statement stmt = con.createStatement();
		log.debug("ͨ��������Ԫ���õ�������Ԫ����Ϣ"+dao_produceunit.getProduceUnitByName(name));
		ResultSet rs=stmt.executeQuery(dao_produceunit.getProduceUnitByName(name));
		ProduceUnit produceunit=new ProduceUnit();
	while(rs.next()){
		
	 produceunit.setInt_id(rs.getInt("INT_ID"));
     produceunit.setStr_name(rs.getString("STR_NAME"));
     produceunit.setStr_code(rs.getString("STR_CODE"));
     produceunit.setInt_instructStateID(rs.getInt("INT_INSTRUCTSTATEID"));
     produceunit.setInt_planIncorporate(rs.getInt("INT_PLANINCORPORATE"));
     produceunit.setInt_instCount(rs.getInt("INT_INSTCOUNT"));
     produceunit.setInt_Type(rs.getInt("INT_TYPE"));
     produceunit.setInt_delete(rs.getInt("INT_DELETE"));
     produceunit.setInt_materielRuleid(rs.getInt("INT_MATERIELRULEID"));
		}
	if(stmt!=null){
		stmt.close();
	}
		return produceunit;
	}
	
	//---------------------------------------------------------������Ŀ���---------------------------------------------------------
	
	 /**
     * ɾ����¼
     * 
     * @param intId
     * @param conn
     * @throws SQLException
     */
    public void delCunit(String intId, Connection conn) throws SQLException {
    	 DAO_ProduceUnit dao = (DAO_ProduceUnit)DAOFactoryAdapter.getInstance(
                 DataBaseType.getDataBaseType(conn), DAO_ProduceUnit.class);

            Statement stmt = conn.createStatement();
            String strSql = dao.delCunitByid(Integer.parseInt(intId));
            log.debug("ɾ����������Ԫ: " + strSql);
            stmt.execute(strSql);

            if (stmt != null) {
                    stmt.close();
                    stmt = null;
            }
    }

    /**
     * ��Ӽ�¼
     * 
     * @param ID
     * @param conn
     * @throws SQLException
     */
    public void addCunit(String intId, String Cunit, Connection conn) throws SQLException {
    	 DAO_ProduceUnit dao = (DAO_ProduceUnit)DAOFactoryAdapter.getInstance(
                 DataBaseType.getDataBaseType(conn), DAO_ProduceUnit.class);

            Statement stmt = conn.createStatement();
            conn.setAutoCommit(false);
            java.util.StringTokenizer tok = new java.util.StringTokenizer(Cunit, ",");

            while (tok.hasMoreElements()) {  // ������������
                    String strSql = dao.addCunit(Integer.parseInt(intId), (String) tok.nextElement());
                    stmt.addBatch(strSql);
            }
            if (stmt.executeBatch().length > 0)
                    conn.commit();

            if (stmt != null) {
                    stmt.close();
                    stmt = null;
            }
    }
}
