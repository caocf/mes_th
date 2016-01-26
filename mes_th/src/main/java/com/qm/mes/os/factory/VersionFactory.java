package com.qm.mes.os.factory;

import java.sql.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.DataBaseType;
import com.qm.mes.os.bean.Version;
import com.qm.mes.os.dao.DAO_Version;
import com.qm.mes.system.dao.DAOFactoryAdapter;
/**
 * 
 * @author л����
 *
 */
public class VersionFactory {
	private final Log log = LogFactory.getLog(VersionFactory.class);
	/**�����汾 л����
	 * @param version
	 * @param con
	 * @throws SQLException
	 */
	public void  saveVersion(Version version ,Connection con)throws SQLException{
		 DAO_Version dao_version = (DAO_Version) DAOFactoryAdapter.getInstance(
					DataBaseType.getDataBaseType(con), DAO_Version.class);
			Statement stmt = con.createStatement();
			log.debug("�����汾"+dao_version.saveVersion(version));
			stmt.executeUpdate(dao_version.saveVersion(version));
			if(stmt!=null){
				stmt.close();
			}
			
		
	}
	   /**�õ��汾��Ϣͨ���汾id��  л����
	 * @param int_id
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public Version getVersionbyid(int int_id,Connection con)throws SQLException{
		DAO_Version dao_version = (DAO_Version) DAOFactoryAdapter.getInstance(
						DataBaseType.getDataBaseType(con), DAO_Version.class);
		Statement stmt = con.createStatement();
		log.debug("�õ��汾��Ϣͨ���汾id��"+dao_version.getVersionbyid(int_id));
		ResultSet rs=stmt.executeQuery(dao_version.getVersionbyid(int_id));
		Version version=new Version();
		while(rs.next()){
			version.setId(rs.getInt("int_id"));
			version.setVersionDatime(rs.getDate("dat_versionDatime"));
			version.setUser(rs.getString("str_user"));
			version.setVersionCode(rs.getString("str_versioncode"));
			version.setDescription(rs.getString("str_description"));
		}
		if(stmt!=null){
			stmt.close();
		}
		
		return version;
	 }
	    
	   /**ɾ���汾��ָ���İ汾��Ϣ  л����
	 * @param versioncode
	 * @param con
	 * @throws SQLException
	 */
	public	void deleteVersionbyversioncode(String versioncode,Connection con)throws SQLException{
		DAO_Version dao_version = (DAO_Version) DAOFactoryAdapter.getInstance(
						DataBaseType.getDataBaseType(con), DAO_Version.class);
		Statement stmt = con.createStatement();
		log.debug("ɾ���汾��ָ���İ汾��Ϣ"+dao_version.deleteVersionbyversioncode(versioncode));
		stmt.executeUpdate(dao_version.deleteVersionbyversioncode(versioncode));
		if(stmt!=null){
			stmt.close();
		}
		
	}
	   /**ɾ���汾���¼ͨ���汾��int_id  л����
	 * @param int_id
	 * @param con
	 * @throws SQLException
	 */
	public	void deleteversionbyid(int int_id,Connection con)throws SQLException{
		DAO_Version dao_version = (DAO_Version) DAOFactoryAdapter.getInstance(
						DataBaseType.getDataBaseType(con), DAO_Version.class);
		Statement stmt = con.createStatement();
		log.debug("ɾ���汾���¼ͨ���汾��int_id"+dao_version.deleteversionbyid(int_id));
		stmt.executeUpdate(dao_version.deleteversionbyid(int_id));
		if(stmt!=null){
			stmt.close();
		}
		
	}
	   
	   /**�ж��Ƿ����ɾ���汾 ͨ���汾��id л����
	 * @param id
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public 	boolean  checkdeleteversionbyid(int id ,Connection con)throws SQLException{
		DAO_Version dao_version = (DAO_Version) DAOFactoryAdapter.getInstance(
						DataBaseType.getDataBaseType(con), DAO_Version.class);
		Statement stmt = con.createStatement();
		boolean f=false;
		log.debug("�ж��Ƿ����ɾ���汾 ͨ���汾��id"+dao_version.checkdeleteversionbyid(id));
		ResultSet rs=stmt.executeQuery(dao_version.checkdeleteversionbyid(id));
		if(rs.next()){
            f=true;
        }
		if(stmt!=null){
			stmt.close();
		}
		
		return f;
	}
	/**
	 * @author ���ݰ汾�Ÿİ汾��ע��Ϣ
	 *
	 */
	public void upversiondescriptionbyvcode(String versioncode,String description,Connection con)throws SQLException{
		DAO_Version dao_version = (DAO_Version) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_Version.class);
          Statement stmt = con.createStatement();
          stmt.executeUpdate(dao_version.upversiondescriptionbyvcode(versioncode, description));
          if(stmt!=null){
  			stmt.close();
  		}
	}
	    
	/**
	 *  л���� ���ݰ汾�ŵõ��ƻ���Ϣ
	 */
	public  Version getversionbyversioncord(String versioncode,Connection con)throws SQLException{
		DAO_Version dao_version = (DAO_Version) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_Version.class);
          Statement stmt = con.createStatement();
          ResultSet rs=null;
          rs=stmt.executeQuery(dao_version.getPlanbyversioncord(versioncode));
          Version version=new Version();
          if(rs.next()){
        	  version.setId(rs.getInt("int_id"));
        	  version.setUser(rs.getString("str_user"));
        	  version.setVersionCode(rs.getString("str_versioncode"));
        	  version.setVersionDatime(rs.getDate("dat_versionDatime"));
        	  version.setDescription(rs.getString("str_description"));
        	 // System.out.println("gggggggggggggggg:"+rs.getString("str_description"));
          }
          if(stmt!=null){
    			stmt.close();
    		}
          return version;
	}
}
