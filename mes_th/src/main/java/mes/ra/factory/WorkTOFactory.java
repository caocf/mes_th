package mes.ra.factory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mes.framework.DataBaseType;
import mes.ra.bean.*;
import mes.ra.dao.*;
import mes.system.dao.DAOFactoryAdapter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class WorkTOFactory {
	//��־
	private final Log log = LogFactory.getLog(WorkTOFactory.class);
	
	/**
	 * ��Ӱ����μ�¼
	 * ������
	 * @param workto
	 * @param con
	 * @throws SQLException
	 */
	public void saveWorkTO(WorkTO workto,Connection con)throws SQLException{
        DAO_WorkTO dao_workto = (DAO_WorkTO)DAOFactoryAdapter.getInstance(
                DataBaseType.getDataBaseType(con),DAO_WorkTO.class);
        Statement stmt = con.createStatement();
        log.debug("��Ӱ����μ�¼SQL��䣺"+dao_workto.saveWorkTO(workto));
        stmt.executeUpdate(dao_workto.saveWorkTO(workto));
        if(stmt!=null){
     	   stmt.close();
        }
    }
	
	/** ������
	 * ͨ����Ż�ð����μ�¼
	 * @param id
	 * @param con
	 * @throws SQLException 
	 */
	public WorkTO getWorkTObyId(int int_id,Connection con) throws SQLException{
    	DAO_WorkTO dao_workto = (DAO_WorkTO) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con),DAO_WorkTO.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ����Ż�ð�μ�¼SQL��䣺"+dao_workto.getWorkTOById(int_id));
		WorkTO workto=new WorkTO();
		ResultSet rs=stmt.executeQuery(dao_workto.getWorkTOById(int_id));
		log.debug("ͨ����Ż�ð�μ�¼�б�---");
		while(rs.next()){
			workto.setId(rs.getInt(1));
			workto.setProdunitid(rs.getInt(2));
			workto.setWorkOrder(rs.getString(3));
			log.debug("��ţ�"+rs.getInt(1)+"��������Ԫ��"+rs.getInt(2)+"����Σ�"+rs.getString(3));	
		}
		if(stmt!=null){
			stmt.close();
		}
		return workto;
    }
	
	/**
	 * ������
	 * �޸Ĺ���ʱ�̱�
	 * @param workto
	 * @param con
	 * @throws SQLException
	 */
	public void  updateWorkTO(WorkTO workto,Connection con) throws SQLException{
	  	DAO_WorkTO dao_workto = (DAO_WorkTO) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con),DAO_WorkTO.class);
		Statement stmt = con.createStatement();
		log.debug("�޸Ĺ���ʱ�̱�SQL��䣺"+dao_workto.updateWorkTO(workto));
		stmt.executeUpdate(dao_workto.updateWorkTO(workto));
		if(stmt!=null){
			stmt.close();
		}
	  }
	
	
	/** ������
	 * ͨ�����ɾ������ʱ�̱�
	 * @param id
	 * @param con
	 * @throws SQLException 
	 */
	public void deleteWorkTOById(int id, Connection con) throws SQLException {
		DAO_WorkTO dao_workto = (DAO_WorkTO) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con),DAO_WorkTO.class);
		Statement stmt = con.createStatement();
		stmt.execute(dao_workto.deleteWorkTOById(id));
		log.debug("ͨ��ͨ�����ɾ������ʱ�̱�SQL��䣺"+dao_workto.deleteWorkTOById(id));
		
		if (stmt != null) {
			stmt.close();
		}
	}	
    /**
     * ��ѯ���е�������Ԫ�ţ���֤����ʱ������Ԫ�����飬���Ψһ ������
     * @param con
     * @return
     * @throws SQLException
     */
    public List<String> getProdunitid(Connection con) throws SQLException{
    	DAO_WorkTO dao_workto = (DAO_WorkTO) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_WorkTO .class);
		Statement stmt = con.createStatement();
		log.debug("��ѯ���е�������Ԫ��SQL��䣺"+dao_workto.getprodunitid());
		ResultSet rs=stmt.executeQuery(dao_workto.getprodunitid());
		List<String>  id=new ArrayList<String>();
		while(rs.next()){
			String produnitid=rs.getString("int_produnitid").trim();
			log.debug("������Ԫ�ţ�"+rs.getString("int_produnitid"));
			id.add(produnitid);
		}
		if(stmt!=null){
			stmt.close();
		}
    	return id;
    }

    /**
     * * ��ѯ���еİ�Σ���֤����ʱ������Ԫ�����飬���Ψһ
	 * author ������
     * @param con
     * @return
     * @throws SQLException
     */
    public List<String> getWorkOrder(Connection con) throws SQLException{
    	DAO_WorkTO dao_workto = (DAO_WorkTO) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con),DAO_WorkTO .class);
		Statement stmt = con.createStatement();
		log.debug("��ѯ���еİ��SQL��䣺"+dao_workto.getworkOrder());
		ResultSet rs=stmt.executeQuery(dao_workto.getworkOrder());
		List<String>  order=new ArrayList<String>();
		while(rs.next()){
			String workorder=rs.getString("str_workOrder").trim();
			log.debug("��Σ�"+rs.getString("str_workOrder"));
			order.add(workorder);
		}
		if(stmt!=null){
			stmt.close();
		}
    	return order;
    }
    /**
     * * ��ȥ��ID��ѯ��ù���ʱ�̱���������Ԫ�����ڸ����ж�
	 * author : ������
     * @param int_id
     * @param con
     * @return
     * @throws SQLException
     */
    public List<String> getProdunitidById(int int_id,Connection con) throws SQLException{
    	DAO_WorkTO dao_workto = (DAO_WorkTO) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_WorkTO .class);
		Statement stmt = con.createStatement();
		log.debug("��ȥ��ID��ѯ��ù���ʱ�̱���������Ԫ��SQL��䣺"+dao_workto.getprodunitidById(int_id));
		ResultSet rs=stmt.executeQuery(dao_workto.getprodunitidById(int_id));
		List<String>  id=new ArrayList<String>();
		while(rs.next()){
			String produnitid=rs.getString("int_produnitid").trim();
			log.debug("������Ԫ�ţ�"+rs.getString("int_produnitid"));
			id.add(produnitid);
		}
		if(stmt!=null){
			stmt.close();
		}
    	return id;
    }
    /**
     * ��ȥ��ID��ѯ��ù���ʱ�̱��а�����ڸ����ж�
	 * author : ������
     * @param int_id
     * @param con
     * @return
     * @throws SQLException
     */
    public List<String> getWorkOrderById(int int_id,Connection con) throws SQLException{
    	DAO_WorkTO dao_workto = (DAO_WorkTO) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con),DAO_WorkTO.class);
		Statement stmt = con.createStatement();
		log.debug("��ȥ��ID��ѯ��ù���ʱ�̱��а��SQL��䣺"+dao_workto.getworkOrderById(int_id));
		ResultSet rs=stmt.executeQuery(dao_workto.getworkOrderById(int_id));
		List<String>  order=new ArrayList<String>();
		while(rs.next()){
			String workorder=rs.getString("str_workOrder").trim();
			log.debug("��Σ�"+rs.getString("str_workOrder"));
			order.add(workorder);
		}
		if(stmt!=null){
			stmt.close();
		}
    	return order;
    }
	/**ͨ��������Ԫ��id����ȡ�����Ϣ л����
	 * @param id
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public List<String> getworkOrderByprodunitid(int id,Connection con)throws SQLException{
		DAO_WorkTO dao_workto = (DAO_WorkTO)DAOFactoryAdapter.getInstance(
                DataBaseType.getDataBaseType(con),DAO_WorkTO.class);
       Statement stmt = con.createStatement();
       log.debug("ͨ��������Ԫ��id����ȡ���SQL��䣺"+dao_workto.getworkOrderByprodunitid(id));
       ResultSet rs=stmt.executeQuery(dao_workto.getworkOrderByprodunitid(id));
        List<String> list=new ArrayList<String>();
      
        while(rs.next()){
        	if(list.contains(rs.getString("str_workOrder")))
        	    continue;
        	
        	log.debug("��Σ�"+rs.getString("str_workOrder"));
        	list.add(rs.getString("str_workOrder"));    
        } 
       if(stmt!=null){
     	   stmt.close();
        }
        return list;
	}
   
    
   
	
	
}
