package com.qm.mes.tg.factory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.DataBaseType;
import com.qm.mes.system.dao.DAOFactoryAdapter;
import com.qm.mes.tg.bean.GatherRecord;
import com.qm.mes.tg.bean.MaterielRule;
import com.qm.mes.tg.bean.PedigreeRecord;
import com.qm.mes.tg.dao.IDAO_Record;

public class RecordFactory {
	
	//��־
	private final Log log = LogFactory.getLog(RecordFactory.class);
	
	/**
	 * �ɼ����ݱ���
	 * @param gr �����¼����
	 * @param prs �ɼ��������б���һλ�������ϵ�ֵ�����������������ϵ�ֵ
	 * @param mrs ��֤�����б���һλ�������ϵ���֤�������������������ϵ���֤����
	 * @param con 
	 * @throws SQLException 
	 */
	public synchronized void saveRecord(GatherRecord gr,List<String> prs,List<MaterielRule> mrs,Connection con) throws SQLException{
		IDAO_Record dao_record = (IDAO_Record) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), IDAO_Record.class);
		
		Statement stmt = con.createStatement();
		//gr.setMaterielValue(prs.get(0));
		//gr.setMaterielValue(mrs.get(0).getName());
		//��ѯ�ǹ�����Ϣ�Ƿ����	
		//by cp
		
//		System.out.println(dao_record.getGatherRecordId(gr));
//		System.out.println(gr.getMaterielValue());
		ResultSet rs=null;
		
		/*
		ResultSet rs = stmt.executeQuery(dao_record.getGatherRecordId(gr));
		//������ڹ�����Ϣ���׳��쳣,�������
		if(rs.next()){
			if(rs!=null){
				rs.close();
				rs=null;
			}
			if(stmt!=null){
				stmt.close();
				stmt=null;
			}
			throw new SQLException("�����Ѵ���");
		}
		end by cp*/
		boolean isReleaseLock = false;//�����ʶ
		try {
			//����Ƿ����������������û���������������񲢽���ʶ��Ϊtrue
			if (con.getAutoCommit()) { 
				isReleaseLock = true;
				con.setAutoCommit(false);
			}
			//��¼������Ϣ
			log.debug("������¼������ϢSQL��䣺"+dao_record.saveGatherRecord(gr));
			stmt.execute(dao_record.saveGatherRecord(gr));
			//ȡ�øոմ��������Ϣ��id
			log.debug("ͨ�������¼����ȡ�ù����¼��SQL��䣺"+dao_record.getGatherRecordId(gr));
			rs = stmt.executeQuery(dao_record.getGatherRecordId(gr));
			rs.next();
			int id = rs.getInt("int_id");
			//����ϵ��¼������ϵ����
			for(int i=1;i<prs.size();i++){
				String[] i_v = prs.get(i).split(":");
				log.debug("����ϵ��¼������ϵ����SQL��䣺"+dao_record.savePedigreeRecord(id,i_v[1], mrs.get(Integer.parseInt(i_v[0]) + 1).getName()));
				stmt.execute(dao_record.savePedigreeRecord(id,i_v[1], mrs.get(Integer.parseInt(i_v[0]) + 1).getName()));
			}
			//��������Ƿ�����������������������ã����ύ
			if(isReleaseLock){
				con.commit();
			}
		}catch (SQLException e) {
			con.rollback();
			throw e;
		} finally {
			if(rs!=null){
				rs.close();
				rs=null;
			}
			if(stmt!=null){
				stmt.close();
				rs=null;
			}
			//��������Ƿ���������
			if (isReleaseLock){
				con.setAutoCommit(true);
			}
		}
	}
	/**
	 * ����:л����
	 * ���²ɼ��������ϵ�ֵ
	 * @param GatherRecord
	 * @param con
	 * @throws SQLException
	 * ��gatherRecord_updating.jsp ҳ���õ�
	 * gatherRecord_editing.jsp ҳ���õ�
	 */
	public synchronized void upDateRecord(GatherRecord gr,Connection con)  throws SQLException{
		Statement stmt = null;
		try{
		IDAO_Record dao_update = (IDAO_Record) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), IDAO_Record.class);
		stmt = con.createStatement();
		log.debug("���²ɼ���������ֵSQL��䣺"+dao_update.upDateGatherRecord(gr.getId(), gr.getMaterielValue()));
		stmt.execute(dao_update.upDateGatherRecord(gr.getId(), gr.getMaterielValue()));		
		}
		catch(SQLException e){
			throw e;
		}finally{
			if(stmt!=null){
				stmt.close();
				stmt=null;
			}
		}
	}
	/**
	 * ����:л����
	 * ���²ɼ�����ϵ�����ϵ�ֵ
	 * @param GatherRecord
	 * @param con
	 * @throws SQLException
	 * ��gatherRecord_updating.jsp ҳ���õ�
	 * gatherRecord_editing.jsp ҳ���õ�
	 */
	public synchronized void upDatePedigreeRecord(PedigreeRecord pr,Connection con) throws SQLException{
		try{
		IDAO_Record dao_updatePedigreeRecord = (IDAO_Record) DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con), IDAO_Record.class);
		Statement stmt = con.createStatement();
		log.debug("���²ɼ�����ϵ������ֵSQL��䣺"+dao_updatePedigreeRecord.upDatePedigreeRecord(pr.getId(), pr.getMaterielValue()));
		stmt.execute(dao_updatePedigreeRecord.upDatePedigreeRecord(pr.getId(), pr.getMaterielValue()));
		if(stmt!=null){
			stmt.close();
			stmt=null;
		}
		}
		catch(SQLException e){
			throw e;
		}
	}
	  
    /**
	 *   �޸���ϵ��ʷ��¼
	 * л���� 
	 * @param gatherrecordid
	 *           ԭ����¼id
	 * @param   ԭ����¼��ֵ
	 *  @param  ��ӵ�ԭ��
	 * @param  �û���id
	 * @param con
	 *  @throws SQLException
	 *  
	 */
	
	public synchronized void  savePEDIGREEHISTORY(int origid,String origvalue,String cause,String userid,Connection con) throws SQLException{
		try{
			IDAO_Record dao_savePEDIGREEHISTORY = (IDAO_Record) DAOFactoryAdapter.getInstance(
					DataBaseType.getDataBaseType(con), IDAO_Record.class);
			Statement stmt = con.createStatement();
			log.debug("�޸���ϵ��ʷ��¼SQL��䣺"+dao_savePEDIGREEHISTORY.savePEDIGREEHISTORY(origid, origvalue, cause, userid));
			stmt.execute(dao_savePEDIGREEHISTORY.savePEDIGREEHISTORY(origid, origvalue, cause, userid));
			if(stmt!=null){
				stmt.close();
				stmt=null;
			}	
		}
			catch(SQLException e){
				throw e;
			}
	}
	
	/**
     * ��ѯ���ϱ�ʶ����������ϵ���е�����
     * 
     * @author YuanPeng
     * @param MaterielRuleName ���ϱ�ʶ������
     * @return ����
     */
    public synchronized int countByMaterielRuleName(String MaterielRuleName , Connection con){
    	int count = 0;
    	try{
			IDAO_Record dao_record = (IDAO_Record) DAOFactoryAdapter.getInstance(
					DataBaseType.getDataBaseType(con), IDAO_Record.class);
			Statement stmt = con.createStatement();
			log.debug("��ѯ���ϱ�ʶ����������ϵ���е�����SQL��䣺"+dao_record.countByMaterielRuleName(MaterielRuleName));
			ResultSet rs = stmt.executeQuery(dao_record.countByMaterielRuleName(MaterielRuleName));
			if(rs.next()){
				count = rs.getInt(1);
				log.debug("��ѯ���ϱ�ʶ����������ϵ���е�����Ϊ��"+count);
			}
			if(stmt!=null){
				stmt.close();
				stmt=null;
			}
    	}
			catch(Exception e){
				e.printStackTrace();
			}
			return count;
    }
    
    /**
     * ��ѯ���ϱ�ʶ�������ڹ����¼���е�����
     * 
     * @author YuanPeng
     * @param MaterielRuleName ���ϱ�ʶ������
     * @return ����
     */
    public synchronized int countGatherReByMaterielRuleName(String MaterielRuleName , Connection con){
    	int count = 0;
    	try{
			IDAO_Record dao_record = (IDAO_Record) DAOFactoryAdapter.getInstance(
					DataBaseType.getDataBaseType(con), IDAO_Record.class);
			Statement stmt = con.createStatement();
			log.debug("��ѯ���ϱ�ʶ�������ڹ����¼���е�����"+dao_record.countGatherReByMaterielRuleName(MaterielRuleName));
			ResultSet rs = stmt.executeQuery(dao_record.countGatherReByMaterielRuleName(MaterielRuleName));
			if(rs.next()){
				count = rs.getInt(1);
				log.debug("��ѯ���ϱ�ʶ�������ڹ����¼���е�����Ϊ��"+count);
			}
			if(stmt!=null){
				stmt.close();
				stmt=null;
			}
    	}
			catch(Exception e){
				e.printStackTrace();
			}
			return count;
    }
    
    /**
     * 	ͨ��ID��ѯ��ϵ����
     * @author YuanPeng
     * @param id
     * @return	��ϵ����
     */
    public PedigreeRecord getPedigreeRecordById(int id,Connection con){
    	PedigreeRecord pedigreeRecord = null;
    	Statement stmt = null;
    	try{
    		pedigreeRecord = new PedigreeRecord();
			IDAO_Record dao_record = (IDAO_Record) DAOFactoryAdapter.getInstance(
					DataBaseType.getDataBaseType(con), IDAO_Record.class);
			stmt = con.createStatement();
			log.debug("ͨ�������¼�Ų�ѯ�����¼����SQL���:"+dao_record.getPedigreeRecordById(id));
			ResultSet rs = stmt.executeQuery(dao_record.getPedigreeRecordById(id));
			if(rs.next()){
				pedigreeRecord.setId(rs.getInt("INT_ID"));
				pedigreeRecord.setGatherRecordId(rs.getInt("INT_GATHERRECORDID"));
				pedigreeRecord.setMaterielValue(rs.getString("STR_MATERIELVALUE"));
				pedigreeRecord.setMaterielName(rs.getString("STR_MATERIELNAME"));
				log.debug("��ϵ�ţ�"+rs.getInt("INT_ID")+"�������¼�ţ�"+rs.getInt("INT_GATHERRECORDID")+"������ֵ��"+rs.getString("STR_MATERIELVALUE")+"����������"+rs.getString("STR_MATERIELNAME"));
			}
			
    	}
			catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
					if(stmt!=null)stmt.close();
				}catch(SQLException sqle){
					sqle.printStackTrace();}
			}
			return pedigreeRecord;
    }
	
}
