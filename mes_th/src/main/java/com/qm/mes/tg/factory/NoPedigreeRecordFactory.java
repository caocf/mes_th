package com.qm.mes.tg.factory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.DataBaseType;
import com.qm.mes.system.dao.DAOFactoryAdapter;
import com.qm.mes.tg.bean.NoPedigreeRecord;
import com.qm.mes.tg.dao.IDAO_NoPedigreeRecord;

public class NoPedigreeRecordFactory {

	//��־
	private final Log log = LogFactory.getLog(NoPedigreeRecordFactory.class);
	
	/**
	 * ����NoPedigreeRecord
	 * @param NoPedigreeRecord
	 * @param con
	 * @throws SQLException
	 */
	public void saveNoPedigreeRecord (NoPedigreeRecord npgr,Connection con) 
	throws SQLException {
		IDAO_NoPedigreeRecord dao__NoPedigreeRecord  = (IDAO_NoPedigreeRecord ) DAOFactoryAdapter
		.getInstance(DataBaseType.getDataBaseType(con),
				IDAO_NoPedigreeRecord.class);
      Statement stmt = con.createStatement();
      log.debug("��������ϵ��¼��SQL��䣺"+dao__NoPedigreeRecord.saveNoPedigreeRecord(npgr));
     stmt.execute(dao__NoPedigreeRecord.saveNoPedigreeRecord(npgr));
    if (stmt != null) {
	stmt.close();
	stmt = null;
    }
	}
	
	/**
	 * �õ�NoPedigreeRecord
	 * @param ͨ��id
	 * @param con
	 * @throws SQLException
	 */
	public NoPedigreeRecord getNoPedigreeRecordById(int id,Connection con) 
	throws SQLException{
		IDAO_NoPedigreeRecord dao__NoPedigreeRecord  = (IDAO_NoPedigreeRecord ) DAOFactoryAdapter
		.getInstance(DataBaseType.getDataBaseType(con),
				IDAO_NoPedigreeRecord.class);
      Statement stmt = con.createStatement();
      log.debug("ͨ����Ų�ѯ����ϵ��¼��SQL��䣺"+dao__NoPedigreeRecord.getNoPedigreeRecordById(id));
ResultSet rs = stmt.executeQuery(dao__NoPedigreeRecord.getNoPedigreeRecordById(id));
NoPedigreeRecord gi = null;
	log.debug("ͨ����Ų�ѯ����ϵ��¼--");
if (rs.next()) {
	gi = new  NoPedigreeRecord();
	gi.setId(rs.getInt("int_id"));
	gi.setGatherRecordId(rs.getInt("int_gatherrecord_id"));
	gi.setValue(rs.getString("str_value"));
	gi.setGatheritemextname(rs.getString("str_gatheritemextname"));
	log.debug("����ϵ��¼�ţ�"+rs.getInt("int_id")+"�������¼�ţ�"+rs.getInt("int_gatherrecord_id")+
			"������ϵ��չ����ֵ��"+rs.getString("str_value")+"���ɼ�����չ��������"+rs.getString("str_gatheritemextname"));
   }
if(stmt!=null){
	stmt.close();
	stmt=null;
}
return gi;
	}
	/**
	 * �õ���ϵ��ѯ����������ֵ
	 * @param ͨ��������ֵ materielvalue
	 * @param con
	 * @throws SQLException
	 * ��productRecord.jsp ҳ���õ�
	 */
	public  String  getNoPedigreeRecordStrnameandvalue(String materielvalue,Connection con) 
	throws SQLException{
         IDAO_NoPedigreeRecord dao__NoPedigreeRecord  = (IDAO_NoPedigreeRecord ) DAOFactoryAdapter
		.getInstance(DataBaseType.getDataBaseType(con),
				IDAO_NoPedigreeRecord.class);
      Statement stmt = con.createStatement();
      log.debug("ͨ������ֵ��ѯ����ϵ��¼��������ֵ��SQL��䣺"+dao__NoPedigreeRecord.getNoPedigreeRecordByMaterielValue(materielvalue));
      ResultSet rs = stmt.executeQuery(dao__NoPedigreeRecord.getNoPedigreeRecordByMaterielValue(materielvalue));
      String output1="";
      log.debug("ͨ������ֵ��ѯ����ϵ��¼��������ֵ---");
      while(rs.next()){
 
      output1+=rs.getString("str_gatheritemextname");
      output1+=":";
      output1+=rs.getString("str_value"); 
      output1+="  ";
      log.debug("�ɼ�����չ��������"+rs.getString("str_gatheritemextname")+";����ϵ��չ����ֵ:"+rs.getString("str_value"));
     }
     if(rs!=null){
       rs.close();
       rs=null;
       }
    if(stmt!=null){
    stmt.close();
    stmt=null;
      }
     return output1;
   }
	
    //���������Ψһ��ô�Ͳ���List�б�
	/**
	 * �õ�NoPedigreeRecord�б�����
	 * @param ͨ��������ֵ materielvalue
	 * @param con
	 * @throws SQLException
	 * ��gatherRecord_edit.jsp �õ�
	 */
	public  List<NoPedigreeRecord> getNoPedigreeRecordBygatherid(int id,Connection con) 
	throws SQLException{
		List<NoPedigreeRecord> list = new ArrayList<NoPedigreeRecord>();
		IDAO_NoPedigreeRecord dao__NoPedigreeRecord  = (IDAO_NoPedigreeRecord ) DAOFactoryAdapter
		.getInstance(DataBaseType.getDataBaseType(con),
				IDAO_NoPedigreeRecord.class);
      Statement stmt = con.createStatement();
      log.debug("ͨ����ŵõ�����ϵ��¼�б�"+dao__NoPedigreeRecord.getNoPedigreeRecordBygatherid(id));
      ResultSet rs = stmt.executeQuery(dao__NoPedigreeRecord.getNoPedigreeRecordBygatherid(id));
      NoPedigreeRecord gi = null;
      log.debug("ͨ����ŵõ�����ϵ��¼---");
     while(rs.next()) {
	gi = new  NoPedigreeRecord();
	gi.setId(rs.getInt("int_id"));
	gi.setGatherRecordId(rs.getInt("int_gatherrecord_id"));
	gi.setValue(rs.getString("str_value"));
	gi.setGatheritemextname(rs.getString("str_gatheritemextname"));
	log.debug("����ϵ��¼�ţ�"+rs.getInt("int_id")+"�������¼�ţ�"+rs.getInt("int_gatherrecord_id")+"�ɼ�����չ��������"
			+rs.getString("str_gatheritemextname")+";����ϵ��չ����ֵ:"+rs.getString("str_value"));
	list.add(gi);
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
	public  List<NoPedigreeRecord> getNoPedigreeRecordByMaterielValue(String materilvalue,Connection con) 
	throws SQLException{
		List<NoPedigreeRecord> list = new ArrayList<NoPedigreeRecord>();
		IDAO_NoPedigreeRecord dao__NoPedigreeRecord  = (IDAO_NoPedigreeRecord ) DAOFactoryAdapter
		.getInstance(DataBaseType.getDataBaseType(con),
				IDAO_NoPedigreeRecord.class);
      Statement stmt = con.createStatement();
      log.debug("ͨ������ֵ��ѯ����ϵ��¼SQL��䣺"+dao__NoPedigreeRecord.getNoPedigreeRecordByMaterielValue(materilvalue));
  ResultSet rs = stmt.executeQuery(dao__NoPedigreeRecord.getNoPedigreeRecordByMaterielValue(materilvalue));
   NoPedigreeRecord gi = null;
   log.debug("ͨ������ֵ��ѯ����ϵ��¼�б�---");
     while(rs.next()) {
	gi = new  NoPedigreeRecord();
	gi.setId(rs.getInt("int_id"));
	gi.setGatherRecordId(rs.getInt("int_gatherrecord_id"));
	gi.setValue(rs.getString("str_value"));
	gi.setGatheritemextname(rs.getString("str_gatheritemextname"));
	log.debug("����ϵ��¼�ţ�"+rs.getInt("int_id")+"�������¼�ţ�"+rs.getInt("int_gatherrecord_id")+"�ɼ�����չ��������"
			+rs.getString("str_gatheritemextname")+";����ϵ��չ����ֵ:"+rs.getString("str_value"));
	list.add(gi);
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
	
	
	/**
	 * ���·���ϵ��¼
	 * @param NoPedigreeRecord
	 * @param con
	 * @throws SQLException
	 * ��gatherRecord_updating.jsp ҳ���õ�
	 * gatherRecord_editing.jsp ҳ���õ�
	 */

	 public void updateNoPedigreeRecord(NoPedigreeRecord npgr,Connection con)throws SQLException {
				IDAO_NoPedigreeRecord dao__NoPedigreeRecord  = (IDAO_NoPedigreeRecord ) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						IDAO_NoPedigreeRecord.class);
		      Statement stmt = con.createStatement();
		      log.debug("���·���ϵ��¼SQL��䣺"+dao__NoPedigreeRecord.updateNoPedigreeRecord(npgr));
		    stmt.execute(dao__NoPedigreeRecord.updateNoPedigreeRecord(npgr));
		    if (stmt != null) {
			stmt.close();
			stmt = null;
		    }
	 }
	
}


