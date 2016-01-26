package com.qm.mes.os.factory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.text.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.DataBaseType;
import com.qm.mes.os.bean.*;
import com.qm.mes.os.dao.*;
import com.qm.mes.system.dao.DAOFactoryAdapter;
public class WorkSchedleFactory {
	private final Log log = LogFactory.getLog(WorkSchedleFactory.class);
	
	/**��ӹ���ʱ�̱� 
	 * @param workschedle
	 * @param con
	 * @throws SQLException
	 * * author : ������
	 */
	public void saveWorkSchedle(WorkSchedle workschedle,Connection con)throws SQLException{
        DAO_WorkSchedle dao_workschedle = (DAO_WorkSchedle)DAOFactoryAdapter.getInstance(
                DataBaseType.getDataBaseType(con),DAO_WorkSchedle.class);
        Statement stmt = con.createStatement();
        log.debug("��ӹ���ʱ�̱�"+dao_workschedle.saveWorkSchedle(workschedle) );
        stmt.executeUpdate(dao_workschedle.saveWorkSchedle(workschedle));
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
	public void deleteWorkSchedleById(int id, Connection con) throws SQLException {
		DAO_WorkSchedle dao_workschedle = (DAO_WorkSchedle) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con),DAO_WorkSchedle.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ�����ɾ������ʱ�̱�"+dao_workschedle.deleteWorkSchedleById(id));
		stmt.execute(dao_workschedle.deleteWorkSchedleById(id));
		if (stmt != null) {
			stmt.close();
		}
		
		
	}
	
	/** ������
	 * ͨ����Ż�ù���ʱ�̱�
	 * @param id
	 * @param con
	 * @throws SQLException 
	 */
	public WorkSchedle getWorkSchedlebyId(int int_id,Connection con) throws SQLException{
    	DAO_WorkSchedle dao_workschedle = (DAO_WorkSchedle) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_WorkSchedle.class);
		Statement stmt = con.createStatement();
		WorkSchedle workschedle=new WorkSchedle();
		log.debug("ͨ����Ż�ù���ʱ�̱�"+dao_workschedle.getWorkSchedleById(int_id) );
		ResultSet rs=stmt.executeQuery(dao_workschedle.getWorkSchedleById(int_id));
		while(rs.next()){
			workschedle.setId(rs.getInt("INT_ID"));
			workschedle.setProdunitid(rs.getInt("INT_PRODUNITID"));
			workschedle.setWorkOrder(rs.getString("STR_WORKORDER"));
			workschedle.setWorkSchedle(rs.getString("STR_WORKSCHEDLE"));
			workschedle.setAdvanceTime(rs.getString("STR_ADVANCETIME"));
		}
		if(stmt!=null){
			stmt.close();
		}
		
		return workschedle;
    }
	/**������
	 * �޸Ĺ���ʱ�̱�
	 * @param workschedle
	 * @param con
	 * @throws SQLException
	 */
	public void  updateWorkSchedle(WorkSchedle workschedle,Connection con) throws SQLException{
	  	DAO_WorkSchedle dao_workschedle = (DAO_WorkSchedle) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_WorkSchedle.class);
		Statement stmt = con.createStatement();
		log.debug("�޸Ĺ���ʱ�̱�"+dao_workschedle.updateWorkSchedle(workschedle));
		stmt.executeUpdate(dao_workschedle.updateWorkSchedle(workschedle));
		if(stmt!=null){
			stmt.close();
		}
		
	  }
	
    /**��ѯ���е�������Ԫ�ţ���֤����ʱ������Ԫ�����飬���Ψһ
	 * author ������
     * @param con
     * @return
     * @throws SQLException
     */
    public List<String> getProdunitid(Connection con) throws SQLException{
    	DAO_WorkSchedle dao_workschedle = (DAO_WorkSchedle) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_WorkSchedle .class);
		Statement stmt = con.createStatement();
		log.debug("��ѯ���е�������Ԫ�ţ���֤����ʱ������Ԫ�����飬���Ψһ"+dao_workschedle.getprodunitid());
		ResultSet rs=stmt.executeQuery(dao_workschedle.getprodunitid());
		
		List<String>  id=new ArrayList<String>();
		while(rs.next()){
			String produnitid=rs.getString("int_produnitid").trim();
			id.add(produnitid);
		}
		if(stmt!=null){
			stmt.close();
		}
		
    	return id;
    	
    }
    
    /**��ѯ���еİ�Σ���֤����ʱ������Ԫ�����飬���Ψһ
	 * author ������
     * @param con
     * @return
     * @throws SQLException
     */
    public List<String> getWorkOrder(Connection con) throws SQLException{
    	DAO_WorkSchedle dao_workschedle = (DAO_WorkSchedle) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con),DAO_WorkSchedle .class);
		Statement stmt = con.createStatement();
		log.debug("��ѯ���еİ�Σ���֤����ʱ������Ԫ�����飬���Ψһ"+dao_workschedle.getworkOrder());
		ResultSet rs=stmt.executeQuery(dao_workschedle.getworkOrder());
		List<String>  order=new ArrayList<String>();
		while(rs.next()){
			String workorder=rs.getString("str_workOrder").trim();
			order.add(workorder);
		}
		if(stmt!=null){
			stmt.close();
		}
		
    	return order;
    }
    
    /**��ȥ��ID��ѯ��ù���ʱ�̱���������Ԫ�����ڸ����ж�
	 * author : ������
     * @param int_id
     * @param con
     * @return
     * @throws SQLException
     */
    public List<String> getProdunitidById(int int_id,Connection con) throws SQLException{
    	DAO_WorkSchedle dao_workschedle = (DAO_WorkSchedle) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_WorkSchedle .class);
		Statement stmt = con.createStatement();
		log.debug("��ȥ��ID��ѯ��ù���ʱ�̱���������Ԫ�����ڸ����ж�"+dao_workschedle.getprodunitidById(int_id));
		ResultSet rs=stmt.executeQuery(dao_workschedle.getprodunitidById(int_id));
		
		List<String>  id=new ArrayList<String>();
		while(rs.next()){
			String produnitid=rs.getString("int_produnitid").trim();
			id.add(produnitid);
		}
		if(stmt!=null){
			stmt.close();
			
		}
		
    	return id;
    }
    
    /**��ȥ��ID��ѯ��ù���ʱ�̱��а�����ڸ����ж�
	 * author : ������
     * @param int_id
     * @param con
     * @return
     * @throws SQLException
     */
    public List<String> getWorkOrderById(int int_id,Connection con) throws SQLException{
    	DAO_WorkSchedle dao_workschedle = (DAO_WorkSchedle) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_WorkSchedle .class);
		Statement stmt = con.createStatement();
		log.debug("��ȥ��ID��ѯ��ù���ʱ�̱��а�����ڸ����ж�"+dao_workschedle.getworkOrderById(int_id));
		ResultSet rs=stmt.executeQuery(dao_workschedle.getworkOrderById(int_id));
		
		List<String>  order=new ArrayList<String>();
		while(rs.next()){
			String workorder=rs.getString("str_workOrder").trim();
			order.add(workorder);
		}
		if(stmt!=null){
			stmt.close();
		}
		
    	return order;
    }
    
	/**ͨ��ID��ѯ������Ԫ�������ɾ������ʱ�̱����ж�
	 * author : ������
	 * @param id
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public WorkSchedle getProdunitidOrderById(int id,Connection con)throws SQLException{
		DAO_WorkSchedle dao_workschedle = (DAO_WorkSchedle) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_WorkSchedle.class);
		Statement stmt = con.createStatement();
		WorkSchedle workschedle=new WorkSchedle();
		log.debug("ͨ��ID��ѯ������Ԫ�������ɾ������ʱ�̱����ж�"+dao_workschedle.getProdunitidOrderById(id));
		ResultSet rs=stmt.executeQuery(dao_workschedle.getProdunitidOrderById(id));
		
		while(rs.next()){
			workschedle.setProdunitid(rs.getInt("INT_PRODUNITID"));
			workschedle.setWorkOrder(rs.getString("STR_WORKORDER"));
		}
		if(stmt!=null){
			stmt.close();
		}
		
		return workschedle;
    }

	/**ͨ��������Ԫ��id����ȡ�����Ϣ л����
	 * @param id
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public List<String> getworkOrderByprodunitid(int id,Connection con)throws SQLException{
        DAO_WorkSchedle dao_workschedle = (DAO_WorkSchedle)DAOFactoryAdapter.getInstance(
                DataBaseType.getDataBaseType(con),DAO_WorkSchedle.class);
        Statement stmt = con.createStatement();
        log.debug("ͨ��������Ԫ��id����ȡ�����Ϣ"+dao_workschedle.getworkOrderByprodunitid(id));
        ResultSet rs=stmt.executeQuery(dao_workschedle.getworkOrderByprodunitid(id));
        
        List<String> list=new ArrayList<String>();
      
        while(rs.next()){
        	if(list.contains(rs.getString("str_workOrder")))
        	    continue;
              list.add(rs.getString("str_workOrder"));
        }
        if(stmt!=null){
     	   stmt.close();
        }
        
        return list;
	}
	
	/**ͨ��������Ԫ�������ѯ����ʱ�����ǰ��  л����
	 * @param produnitid
	 * @param dat_producedate
	 * @param workorder
	 * @param con
	 * @return
	 * @throws SQLException
	 * @throws ParseException
	 */
	public long getworkschedleadtime(int produnitid,String dat_producedate,String workorder,Connection con)throws SQLException,ParseException{
        DAO_WorkSchedle dao_workschedle = (DAO_WorkSchedle)DAOFactoryAdapter.getInstance(
                DataBaseType.getDataBaseType(con),DAO_WorkSchedle.class);
        Statement stmt = con.createStatement();
        //log.debug("ͨ��������Ԫ�������ѯ����ʱ�����ǰ��"+dao_workschedle.getworkschedleadtime(produnitid, workorder));
        ResultSet rs=stmt.executeQuery(dao_workschedle.getworkschedleadtime(produnitid, workorder));
        String[] a=new String[2];
        String[] b=new String[3];
        String workSchedle="";
        String advanceTime="";
      
        if(rs.next()){
        	workSchedle=rs.getString("str_workSchedle");
        	advanceTime=rs.getString("str_advanceTime");
	    }
        Calendar calendar=Calendar.getInstance();
        //��ȡ����ʱ��
        calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dat_producedate));
        a=workSchedle.split(":");
        b=advanceTime.split(":");
        long timedate= calendar.getTimeInMillis()+(Integer.parseInt(a[0])*60+Integer.parseInt(a[1]))*60*1000;
        int  hour=Integer.parseInt(b[0])*24+Integer.parseInt(b[1]);
        int minunit=Integer.parseInt(b[2]);
        long timeschedle=hour*60*60*1000+minunit*60*1000;
        //��ǰʱ��
        calendar.setTime(new java.util.Date());
        long nowtime=calendar.getTimeInMillis();
        // ������ʱ��-����ʱ�̱�С��ϵͳʱ��Ļ���ʾ�Ѿ�����ʵ������
        long t= timedate-timeschedle;
        if(t<=nowtime){
        	t=0;
        }
        if(stmt!=null){
      	   stmt.close();
        }
       
	    return t; 
	}

	/**�˶��Ƿ����������Ԫ��εĹ���ʱ�̱�  л����
	 * @param produnitid
	 * @param workorder
	 * @param con
	 * @return
	 * @throws SQLException
	 * @throws ParseException
	 */
	public boolean checkworkOrderProduce(int produnitid,String workorder,Connection con)throws SQLException,ParseException{
        DAO_WorkSchedle dao_workschedle = (DAO_WorkSchedle)DAOFactoryAdapter.getInstance(
                DataBaseType.getDataBaseType(con),DAO_WorkSchedle.class);
        Statement stmt = con.createStatement();
        log.debug("�˶��Ƿ����������Ԫ��εĹ���ʱ�̱�"+dao_workschedle.getworkschedleadtime(produnitid,  workorder));
        ResultSet rs=stmt.executeQuery(dao_workschedle.getworkschedleadtime(produnitid,  workorder));
        boolean f=false;
	    if(rs.next()){
	    	f=true;
	    }
	    if(stmt!=null){
	    	stmt.close();
	    }
	   
	    return f;
	
	}
	
	/**ͨ��������Ԫ��β�ѯ����ʱ�̱�����ɾ��������ж�
	 * author : ������
	 * @param Produnitid
	 * @param Order
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public boolean getSchedleByProdunitidOrder(int Produnitid,String Order,Connection con)throws SQLException{
		DAO_WorkSchedle dao_workschedle = (DAO_WorkSchedle) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_WorkSchedle.class);
		Statement stmt = con.createStatement();
		boolean f=false;
		log.debug("ͨ��������Ԫ��β�ѯ����ʱ�̱�����ɾ��������ж�"+dao_workschedle.getSchedleByProdunitidOrder(Produnitid, Order));
		ResultSet rs=stmt.executeQuery(dao_workschedle.getSchedleByProdunitidOrder(Produnitid, Order));
		if(rs.next()){
		     f=true;
			}
		if(stmt!=null){
			stmt.close();
		}
		
		return f;
    }
	
	/**ͨ��������Ԫ��ѯ����ʱ��
	 * author : ������
	 * @param str_produceunit
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public List<String> getWorkSchedelByProuunitid(int str_produceunit,Connection con) throws SQLException{
    	DAO_WorkSchedle dao_workschedle = (DAO_WorkSchedle) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_WorkSchedle .class);
		Statement stmt = con.createStatement();
		log.debug("ͨ��������Ԫ��ѯ����ʱ��"+dao_workschedle.getWorkSchedelByProuunitid(str_produceunit));
		ResultSet rs=stmt.executeQuery(dao_workschedle.getWorkSchedelByProuunitid(str_produceunit));
		List<String> sch=new ArrayList<String>();
		while(rs.next()){
			String workschedel=rs.getString("str_workschedle");
			sch.add(workschedel);
		}
		if(stmt!=null){
			stmt.close();
		}
		
    	return sch;
    	
    }
	
	/**����IDͨ��������Ԫ��ѯ����ʱ��
	 * author : ������
	 * @param str_produceunit
	 * @param int_id
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public List<String> getWorkSchedelByProuunitidAndID(int str_produceunit,int int_id,Connection con) throws SQLException{
    	DAO_WorkSchedle dao_workschedle = (DAO_WorkSchedle) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_WorkSchedle .class);
		Statement stmt = con.createStatement();
		log.debug("����IDͨ��������Ԫ��ѯ����ʱ��"+dao_workschedle.getWorkSchedelByProuunitidAndID(str_produceunit,int_id));
		ResultSet rs=stmt.executeQuery(dao_workschedle.getWorkSchedelByProuunitidAndID(str_produceunit,int_id));
		List<String> sch=new ArrayList<String>();
		while(rs.next()){
			String workschedel=rs.getString("str_workschedle");
			sch.add(workschedel);
		}
		if(stmt!=null){
			stmt.close();
		}
		
    	return sch;
    	
    }
	
	
}
