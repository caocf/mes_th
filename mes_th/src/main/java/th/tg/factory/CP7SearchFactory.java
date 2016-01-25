package th.tg.factory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import th.tg.bean.CP7CarData;
import th.tg.dao.DAO_CP7Search;

/**
 * QAD�����ܳɲ�ѯ����
 * 
 * @author YuanPeng
 *
 */
public class CP7SearchFactory {
	//��־
	private final Log log = LogFactory.getLog(CP7SearchFactory.class);

	/**
	 * ͨ��ʱ���ͳ��QAD�����ܳ���Ϣ
	 * 
	 * @param startTime		��ʼʱ��
	 * @param endTime		����ʱ��
	 * @param con			���Ӷ���
	 * @return	List<CP7CarData>
	 * @throws SQLException	SQL�쳣
	 */
	public List<CP7CarData> getCarDataStatByStartTimeEndTime(String startTime,String endTime,Connection con)
			throws SQLException{
		List<CP7CarData> list = new ArrayList<CP7CarData>();
		DAO_CP7Search dao = new DAO_CP7Search();
		Statement stmt = con.createStatement();
		log.debug("ͨ��ʱ���ͳ��QAD�����ܳ���ϢSQL:"+dao.getCarDataStatByStartTimeEndTime(startTime, endTime));
		ResultSet rs = stmt.executeQuery(dao.getCarDataStatByStartTimeEndTime(startTime, endTime));
		while(rs.next()){
			CP7CarData cp7 = new CP7CarData();
			cp7.setPart_no(rs.getString("cTfass"));
			cp7.setCartype(rs.getString("cCarType"));
			cp7.setNum(rs.getFloat("nNum"));
			list.add(cp7);
		}
		if(stmt!=null){
			stmt.close();
			stmt = null;
		}
		return list;
	}
	
	/**
	 * ͨ��ʱ���ͳ�ƴ��ڳ��������Ϣ
	 * 
	 * @param startTime		��ʼʱ��
	 * @param endTime		����ʱ��
	 * @param con			���Ӷ���
	 * @return	List<CP7CarData>
	 * @throws SQLException	SQL�쳣
	 */
	public List<CP7CarData> getProductDataStatByStartTimeEndTime(String startTime,String endTime,Connection con)
			throws SQLException{
		List<CP7CarData> list = new ArrayList<CP7CarData>();
		DAO_CP7Search dao = new DAO_CP7Search();
		Statement stmt = con.createStatement();
		log.debug("ͨ��ʱ���ͳ�ƴ��ڳ��������ϢSQL:"+dao.getProductDataStatByStartTimeEndTime(startTime, endTime));
		ResultSet rs = stmt.executeQuery(dao.getProductDataStatByStartTimeEndTime(startTime, endTime));
		while(rs.next()){
			CP7CarData cp7 = new CP7CarData();
			cp7.setPart_no(rs.getString("cpartno"));
			cp7.setPart_name(rs.getString("cpartname"));
			cp7.setNum(rs.getFloat("nNum"));
			list.add(cp7);
		}
		if(stmt!=null){
			stmt.close();
			stmt = null;
		}
		return list;
	}
	
	/**
	 * ͨ��ʱ���ͳ��δƥ��ɢ����Ϣ
	 * 
	 * @param startTime		��ʼʱ��
	 * @param endTime		����ʱ��
	 * @param con			���Ӷ���
	 * @return	List<CP7CarData>
	 * @throws SQLException	SQL�쳣
	 */
	public List<CP7CarData> getErrStatByStartTimeEndTime(String startTime,String endTime,Connection con)
			throws SQLException{
		List<CP7CarData> list = new ArrayList<CP7CarData>();
		DAO_CP7Search dao = new DAO_CP7Search();
		Statement stmt = con.createStatement();
		log.debug("ͨ��ʱ���ͳ��δƥ��ɢ����ϢSQL:"+dao.getErrStatByStartTimeEndTime(startTime, endTime));
		ResultSet rs = stmt.executeQuery(dao.getErrStatByStartTimeEndTime(startTime, endTime));
		while(rs.next()){
			CP7CarData cp7 = new CP7CarData();
			cp7.setPart_no(rs.getString("cpartno"));
			cp7.setPart_name(rs.getString("cpartname"));
			cp7.setNum(rs.getFloat("nNum"));
			list.add(cp7);
		}
		if(stmt!=null){
			stmt.close();
			stmt = null;
		}
		return list;
	}
}
