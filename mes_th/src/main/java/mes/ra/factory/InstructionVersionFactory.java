package mes.ra.factory;
/**
 * author : л����
 */
import java.sql.*;


import mes.ra.bean.*;

import mes.framework.DataBaseType;
import mes.ra.dao.DAO_InstructionVersion ;
import mes.system.dao.DAOFactoryAdapter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class InstructionVersionFactory {
	private final Log log = LogFactory.getLog(InstructionVersionFactory.class);
	
	/**ͨ���汾�Ų��ָ��汾
	 * author : л����
	 */
	public Version getversionById(int int_id,Connection con)throws SQLException{
        DAO_InstructionVersion dao_Instructionversion = (DAO_InstructionVersion)DAOFactoryAdapter.getInstance(
                DataBaseType.getDataBaseType(con),DAO_InstructionVersion .class);
        Statement stmt = con.createStatement();
        log.debug("ͨ���汾�Ų��ָ��汾"+dao_Instructionversion.getVersionByid(int_id));
     ResultSet rs= stmt.executeQuery(dao_Instructionversion.getVersionByid(int_id));
       Version version=new Version();
       if(rs.next()){
    	   version.setId(rs.getInt("int_id"));
    	   version.setVersionDatime(rs.getDate("Datime_versionDatime"));
    	   version.setUser(rs.getString("Str_user"));
    	   version.setProdunitid(rs.getInt("int_produnitid"));
    	   version.setVersionCode(rs.getString("Str_versionCode"));
    	   version.setDescription(rs.getString("str_description"));
       }
       if(stmt!=null){
    	   stmt.close();
    	   stmt=null;
       }
       return version;
    }
     
	/**����ָ��汾
	 * author : л����
	 */
	public void saveVersion(Version version,Connection con)throws SQLException{
          DAO_InstructionVersion dao_Instructionversion = (DAO_InstructionVersion)DAOFactoryAdapter.getInstance(
                  DataBaseType.getDataBaseType(con),DAO_InstructionVersion .class);
          Statement stmt = con.createStatement();
          log.debug("����ָ��汾"+dao_Instructionversion.saveVersion(version));
          stmt.executeUpdate(dao_Instructionversion.saveVersion(version));
          if(stmt!=null){
       	   stmt.close();
       	   stmt=null;
          }
          
      }
	  
	/**ɾ��ָ��汾
	 * author : л����
	 */
	public void delVersion(int int_id,Connection con)throws SQLException{
        DAO_InstructionVersion dao_Instructionversion = (DAO_InstructionVersion)DAOFactoryAdapter.getInstance(
                DataBaseType.getDataBaseType(con),DAO_InstructionVersion .class);
        Statement stmt = con.createStatement();
        log.debug("ɾ��ָ��汾"+dao_Instructionversion.delVersionById(int_id));
        stmt.executeUpdate(dao_Instructionversion.delVersionById(int_id));
        if(stmt!=null){
        	   stmt.close();
        	   stmt=null;
           } 
	}
	
	/**���ݰ汾�Ÿİ汾��ע��Ϣ
	 * @author�� ������
	 *
	 */
	public void upversiondescriptionbyvcode(String versioncode,String description,Connection con)throws SQLException{
		DAO_InstructionVersion dao_version = (DAO_InstructionVersion) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), DAO_InstructionVersion.class);
          Statement stmt = con.createStatement();
          stmt.executeUpdate(dao_version.upversiondescriptionbyvcode(versioncode, description));
          if(stmt!=null){
  			stmt.close();
  		}
	}
	
}

