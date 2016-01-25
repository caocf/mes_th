package mes.pm.factory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import mes.framework.DataBaseType;
import mes.pm.bean.ExceptionRecord;
import mes.pm.dao.DAO_ExceptionRecord;
import mes.system.dao.DAOFactoryAdapter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * �쳣��¼������
 * @author Xujia
 *
 */
public class ExceptionRecordFactory {
//	������־�ļ�
	private final Log log = LogFactory.getLog(ExceptionRecordFactory.class);
	
	 //ͨ���豸name��ȡ������
	public List<String> getdevicetypeBydevicename(String devicename,Connection con)throws SQLException{
        DAO_ExceptionRecord dao = (DAO_ExceptionRecord)DAOFactoryAdapter.getInstance(
                DataBaseType.getDataBaseType(con),DAO_ExceptionRecord.class);      
        Statement stmt = con.createStatement();        
        log.debug("ͨ���豸name��ȡ������: "+dao.getdevicetypeBydevicename(devicename));	
        ResultSet rs=stmt.executeQuery(dao.getdevicetypeBydevicename(devicename));
        List<String> list=new ArrayList<String>();      
        while(rs.next()){
        	if(list.contains(rs.getString("str_statename")))
        	    continue;
              list.add(rs.getString("str_statename"));          
        }       
        log.debug("�豸������: "+list);	
        if(stmt!=null){
     	   stmt.close();     	
        }
       if(con!=null){
    	   con=null;
       }
        return list;
	}
	
	/**  ���
	 * �ϱ��쳣
	 * @param ExceptionRecord
	 * @param con
	 * @throws SQLException
	 */
	public void createExceptionRecord(ExceptionRecord exRecord,Connection con) throws SQLException{
		DAO_ExceptionRecord dao = (DAO_ExceptionRecord)DAOFactoryAdapter.getInstance(
                DataBaseType.getDataBaseType(con),DAO_ExceptionRecord.class);   
		Statement stmt = con.createStatement();
		log.debug("�ϱ��쳣: "+dao.saveExceptionRecord(exRecord));
		stmt.execute(dao.saveExceptionRecord(exRecord));		
		if(stmt!=null){
			stmt.close();
			stmt=null;
		}
	}
	 /**  ���
	 * �ر��쳣
	 * @param ExceptionType
     * @param con  	
     *                 
	 */
	public void colseExceptionRecord(ExceptionRecord exRecord, Connection con)
			throws SQLException {
		DAO_ExceptionRecord dao = (DAO_ExceptionRecord)DAOFactoryAdapter.getInstance(
                DataBaseType.getDataBaseType(con),DAO_ExceptionRecord.class);   
		Statement stmt = con.createStatement();
		log.debug("�ر��쳣 "+dao.colseExceptionRecord(exRecord));
		stmt.execute(dao.colseExceptionRecord(exRecord));       
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}
	}
	/**
	 * ͨ��ID��ѯ�쳣��¼	   
	 * @param id
	 *            �豸��
	 * @param con
	 *            ���Ӷ���
	 * @return ͨ��ID��ѯ����ָ�����
	 * @throws java.sql.SQLException
	 */
	public ExceptionRecord getExceptionRecordById(int id, Connection con)
			throws SQLException, ParseException {
		ExceptionRecord d = new ExceptionRecord();
		DAO_ExceptionRecord dao = (DAO_ExceptionRecord)DAOFactoryAdapter.getInstance(
                DataBaseType.getDataBaseType(con),DAO_ExceptionRecord.class);   
		Statement stmt = con.createStatement();
		log.debug("ͨ��ID��ѯ�쳣��¼SQL��" + dao.getExceptionRecordById(id));
		ResultSet rs = stmt.executeQuery(dao.getExceptionRecordById(id));
		if (rs.next()) {
		    d.setDescription(rs.getString("Str_description"));
		    d.setDeviceid(rs.getInt("int_deviceid"));
			d.setDevicetypeid(rs.getInt("int_devicetypeid"));
			d.setClose(rs.getString("dat_close") == null ? null
							: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
									.parse(rs.getString("dat_close")));
			d.setCloseuser(rs.getInt("int_closeuserid"));
			d.setExceptionCauseId(rs.getInt("int_exceptioncause"));
			d.setExceptionTypeId(rs.getInt("int_exceptiontype"));
			d.setId(rs.getInt("int_id"));
			d.setProduceUnitId(rs.getInt("int_produceunit"));
			d.setRediscription(rs.getString("str_rediscription"));
			d.setStart(rs.getString("dat_start") == null ? null
					: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			          .parse(rs.getString("dat_start")));
			d.setState(rs.getInt("int_state"));
			d.setUserId(rs.getInt("int_userid"));
		}
		if (stmt != null)
			stmt.close();
		return d;
	}
	 /**  ���
	 * �����쳣��¼
	 * @param ExceptionType
     * @param con  	
     *                 
	 */
	public void updateExceptionRecord(ExceptionRecord exRecord, Connection con)
			throws SQLException {
		DAO_ExceptionRecord dao = (DAO_ExceptionRecord)DAOFactoryAdapter.getInstance(
                DataBaseType.getDataBaseType(con),DAO_ExceptionRecord.class);   
		Statement stmt = con.createStatement();
		log.debug("�����쳣��¼: "+dao.updateExceptionRecord(exRecord));
		stmt.execute(dao.updateExceptionRecord(exRecord));       
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}
	}
	

}
