package th.tg.factory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import th.tg.bean.AssemblySearch;
import th.tg.bean.KisAssembly;
import th.tg.bean.KisWelding;
import th.tg.bean.Part;
import th.tg.bean.WeldingSearch;
import th.tg.bean.Welding_Stat;
import th.tg.dao.DAO_WeldingSearch;

public class WeldingSearchFactory {

	//��־
	private final Log log = LogFactory.getLog(WeldingSearchFactory.class);
	
	/**
	 * ͨ����ʼ����ʱ���ѯ��װ��Ϣ
	 * 
	 * @param startTime		��ʼʱ��
	 * @param endTime		����ʱ��
	 * @param con			���Ӷ���
	 * @throws SQLException	SQL�쳣
	 */
	public List<WeldingSearch> getWeldingByTimeCarType(String startTime,String endTime,Connection con)
			throws SQLException{
		List<WeldingSearch> list = new ArrayList<WeldingSearch>();
		DAO_WeldingSearch dao = new DAO_WeldingSearch();
		Statement stmt = con.createStatement();
		log.debug("ͨ����ʼ����ʱ���ѯ��װ��ϢSQL:"+dao.getWeldingByTimeCarType(startTime, endTime));
		ResultSet rs = stmt.executeQuery(dao.getWeldingByTimeCarType(startTime, endTime));
		while(rs.next()){
			WeldingSearch ws = new WeldingSearch();
			ws.setSequence(rs.getString("cSEQNo"));
			ws.setKin(rs.getString("cCarNo"));
			ws.setPart_no(rs.getString("cQADNo"));
			ws.setNum(rs.getInt("iTFASSNum"));
			list.add(ws);
		}
		if(stmt != null){
			stmt.close();
			stmt = null;
		}
		return list;
	}
	
	/**
	 * ��ѯ��������
	 * 
	 * @param con	���Ӷ���
	 * @return	count	����
	 * @throws SQLException	SQL�쳣
	 */
	public int getCount(Connection con)throws SQLException{
		int count = 0;
		DAO_WeldingSearch dao = new DAO_WeldingSearch();
		Statement stmt = con.createStatement();
		log.debug("��ѯ��������:"+dao.getCount());
		ResultSet rs = stmt.executeQuery(dao.getCount());
		if(rs.next()){
			count = rs.getInt("count");
		}
		if(stmt != null){
			stmt.close();
			stmt = null;
		}
		return count;
	}
	
	/**
	 * ͨ��ʱ����ѯ�ܳ�
	 * 
	 * @param startTime	��ʼʱ��
	 * @param endTime	����ʱ��
	 * @param carType	��������
	 * @param con		���Ӷ���
	 * @return
	 * @throws SQLException	SQL�쳣
	 */
	public List<Part> getPartsByStartTimeEndTime(String startTime,String endTime,String carType,int searchsetid,Connection con)throws SQLException{
		List<Part> list = new ArrayList<Part>();
		DAO_WeldingSearch dao = new DAO_WeldingSearch();
		Statement stmt = con.createStatement();
		log.debug(":"+dao.getPartsByStartTimeEndTime(startTime,endTime,carType,searchsetid));
		ResultSet rs = stmt.executeQuery(dao.getPartsByStartTimeEndTime(startTime,endTime,carType,searchsetid));
		while(rs.next()){
			Part part = new Part();
			part.setId(rs.getInt("ITFASSNameId"));
			part.setName(rs.getString("name"));
			list.add(part);
		}
		if(stmt != null){
			stmt.close();
			stmt = null;
		}
		return list;
	}
	
	/**
	 * ͨ��ʱ����ѯ�ܳ�
	 * 
	 * @param startTime	��ʼʱ��
	 * @param endTime	����ʱ��
	 * @param carType	��������
	 * @param con		���Ӷ���
	 * @return
	 * @throws SQLException	SQL�쳣
	 */
	public List<Part> getPartsByStartTimeEndTime_B8Q5(String startTime,String endTime,String carType,String B8_Q5,Connection con)throws SQLException{
		List<Part> list = new ArrayList<Part>();
		DAO_WeldingSearch dao = new DAO_WeldingSearch();
		Statement stmt = con.createStatement();
		log.debug(":"+dao.getPartsByStartTimeEndTime_B8Q5(startTime,endTime,carType,B8_Q5));
		ResultSet rs = stmt.executeQuery(dao.getPartsByStartTimeEndTime_B8Q5(startTime,endTime,carType,B8_Q5));
		while(rs.next()){
			Part part = new Part();
			part.setId(rs.getInt("ITFASSNameId"));
			part.setName(rs.getString("name"));
			list.add(part);
		}
		if(stmt != null){
			stmt.close();
			stmt = null;
		}
		return list;
	}
	
	/**
	 * ��������ѯ
	 * 
	 * @param carType	��������
	 * @param con		���Ӷ���
	 * @return
	 * @throws SQLException	SQL�쳣
	 */
	public List<Part> getParts(String carType,int searchsetid,Connection con)throws SQLException{
		List<Part> list = new ArrayList<Part>();
		DAO_WeldingSearch dao = new DAO_WeldingSearch();
		Statement stmt = con.createStatement();
		log.debug(":"+dao.getParts(carType,searchsetid));
		ResultSet rs = stmt.executeQuery(dao.getParts(carType,searchsetid));
		while(rs.next()){
			Part part = new Part();
			part.setId(rs.getInt("ITFASSNameId"));
			part.setName(rs.getString("name"));
			list.add(part);
		}
		if(stmt != null){
			stmt.close();
			stmt = null;
		}
		return list;
	}
	
	/**
	 * ��������ѯ
	 * 
	 * @param carType	��������
	 * @param con		���Ӷ���
	 * @return
	 * @throws SQLException	SQL�쳣
	 */
	public List<Part> getParts_B8Q5(String carType,String B8_Q5,Connection con)throws SQLException{
		List<Part> list = new ArrayList<Part>();
		DAO_WeldingSearch dao = new DAO_WeldingSearch();
		Statement stmt = con.createStatement();
		log.debug(":"+dao.getParts_B8Q5(carType,B8_Q5));
		ResultSet rs = stmt.executeQuery(dao.getParts_B8Q5(carType,B8_Q5));
		while(rs.next()){
			Part part = new Part();
			part.setId(rs.getInt("ITFASSNameId"));
			part.setName(rs.getString("name"));
			list.add(part);
		}
		if(stmt != null){
			stmt.close();
			stmt = null;
		}
		return list;
	}
	
	/**
	 * ͨ����ʼʱ�䡢������ѯ�ܳ�
	 * 
	 * @param startOrder	��ʼ˳���
	 * @param endOrder		����˳���
	 * @param carType	��������
	 * @param con			���Ӷ���
	 * @return
	 * @throws SQLException	SQL�쳣
	 */
	public List<Part> getPartsByStartOrderEndOrder(String startOrder,String endOrder,String carType,int searchsetid,Connection con)throws SQLException{
		List<Part> list = new ArrayList<Part>();
		DAO_WeldingSearch dao = new DAO_WeldingSearch();
		Statement stmt = con.createStatement();
		log.debug(":"+dao.getPartsByStartOrderEndOrder(startOrder,endOrder,carType,searchsetid));
		ResultSet rs = stmt.executeQuery(dao.getPartsByStartOrderEndOrder(startOrder,endOrder,carType,searchsetid));
		while(rs.next()){
			Part part = new Part();
			part.setId(rs.getInt("ITFASSNameId"));
			part.setName(rs.getString("name"));
			list.add(part);
		}
		if(stmt != null){
			stmt.close();
			stmt = null;
		}
		return list;
	}

	
	/**
	 * ͨ����ʼʱ�䡢������ѯ�ܳ�
	 * 
	 * @param startOrder	��ʼ˳���
	 * @param endOrder		����˳���
	 * @param carType	��������
	 * @param con			���Ӷ���
	 * @return
	 * @throws SQLException	SQL�쳣
	 */
	public List<Part> getPartsByStartOrderEndOrder_B8Q5(String startOrder,String endOrder,String carType,String B8_Q5,Connection con)throws SQLException{
		List<Part> list = new ArrayList<Part>();
		DAO_WeldingSearch dao = new DAO_WeldingSearch();
		Statement stmt = con.createStatement();
		log.debug(":"+dao.getPartsByStartOrderEndOrder_B8Q5(startOrder,endOrder,carType,B8_Q5));
		ResultSet rs = stmt.executeQuery(dao.getPartsByStartOrderEndOrder_B8Q5(startOrder,endOrder,carType,B8_Q5));
		while(rs.next()){
			Part part = new Part();
			part.setId(rs.getInt("ITFASSNameId"));
			part.setName(rs.getString("name"));
			list.add(part);
		}
		if(stmt != null){
			stmt.close();
			stmt = null;
		}
		return list;
	}
	
	/**
	 * ͨ����ʼʱ�䡢������ѯ�ܳ�
	 * 
	 * @param startTime		��ʼʱ��
	 * @param num			����
	 * @param carType	��������
	 * @param con			���Ӷ���
	 * @return
	 * @throws SQLException	SQL�쳣
	 */
	public List<Part> getPartsByStartTimeNum(String startTime,String num,String carType,int searchsetid,Connection con)throws SQLException{
		List<Part> list = new ArrayList<Part>();
		DAO_WeldingSearch dao = new DAO_WeldingSearch();
		Statement stmt = con.createStatement();
		log.debug(":"+dao.getPartsByStartTimeNum(startTime,num,carType,searchsetid));
		ResultSet rs = stmt.executeQuery(dao.getPartsByStartTimeNum(startTime,num,carType,searchsetid));
		while(rs.next()){
			Part part = new Part();
			part.setId(rs.getInt("ITFASSNameId"));
			part.setName(rs.getString("name"));
			list.add(part);
		}
		if(stmt != null){
			stmt.close();
			stmt = null;
		}
		return list;
	}
	
	/**
	 * ͨ����ʼʱ�䡢������ѯ�ܳ�
	 * 
	 * @param startTime		��ʼʱ��
	 * @param num			����
	 * @param carType	��������
	 * @param con			���Ӷ���
	 * @return
	 * @throws SQLException	SQL�쳣
	 */
	public List<Part> getPartsByStartTimeNum_B8Q5(String startTime,String num,String carType,String B8_Q5,Connection con)throws SQLException{
		List<Part> list = new ArrayList<Part>();
		DAO_WeldingSearch dao = new DAO_WeldingSearch();
		Statement stmt = con.createStatement();
		log.debug(":"+dao.getPartsByStartTimeNum_B8Q5(startTime,num,carType,B8_Q5));
		ResultSet rs = stmt.executeQuery(dao.getPartsByStartTimeNum_B8Q5(startTime,num,carType,B8_Q5));
		while(rs.next()){
			Part part = new Part();
			part.setId(rs.getInt("ITFASSNameId"));
			part.setName(rs.getString("name"));
			list.add(part);
		}
		if(stmt != null){
			stmt.close();
			stmt = null;
		}
		return list;
	}
	
	/**
	 * ͨ��ʱ����ѯͳ������
	 * 
	 * @param startTime	��ʼʱ��
	 * @param endTime	����ʱ��
	 * @param carType	��������
	 * @param con		���Ӷ���
	 * @return
	 * @throws SQLException	SQL�쳣
	 */
	public List<Welding_Stat> getStatByStartTimeEndTime(String startTime,String endTime,String carType,int searchsetid,Connection con)
		throws SQLException{
		List<Welding_Stat> list = new ArrayList<Welding_Stat>();
		DAO_WeldingSearch dao = new DAO_WeldingSearch();
		Statement stmt = con.createStatement();
		log.debug("ͨ��ʱ����ѯͳ������SQL:"+dao.getStatByStartTimeEndTime(startTime,endTime,carType,searchsetid));
		ResultSet rs = stmt.executeQuery(dao.getStatByStartTimeEndTime(startTime,endTime,carType,searchsetid));
		while(rs.next()){
			Welding_Stat ws = new Welding_Stat();
			ws.setNo(rs.getString("max_no"));
			ws.setNum(rs.getInt("sum_num"));
			list.add(ws);
		}
		if(stmt != null){
			stmt.close();
			stmt = null;
		}
		return list;
	}
	
	/**
	 * ͨ��ʱ����ѯͳ������
	 * 
	 * @param startTime	��ʼʱ��
	 * @param endTime	����ʱ��
	 * @param carType	��������
	 * @param con		���Ӷ���
	 * @return
	 * @throws SQLException	SQL�쳣
	 */
	public List<Welding_Stat> getStatByStartTimeEndTime_B8Q5(String startTime,String endTime,String carType,String B8_Q5,Connection con)
		throws SQLException{
		List<Welding_Stat> list = new ArrayList<Welding_Stat>();
		DAO_WeldingSearch dao = new DAO_WeldingSearch();
		Statement stmt = con.createStatement();
		log.debug("ͨ��ʱ����ѯͳ������SQL:"+dao.getStatByStartTimeEndTime_B8Q5(startTime,endTime,carType,B8_Q5));
		ResultSet rs = stmt.executeQuery(dao.getStatByStartTimeEndTime_B8Q5(startTime,endTime,carType,B8_Q5));
		while(rs.next()){
			Welding_Stat ws = new Welding_Stat();
			ws.setNo(rs.getString("max_no"));
			ws.setNum(rs.getInt("sum_num"));
			list.add(ws);
		}
		if(stmt != null){
			stmt.close();
			stmt = null;
		}
		return list;
	}
	
	/**
	 * ������ͳ��
	 * 
	 * @param carType	��������
	 * @param con		���Ӷ���
	 * @return
	 * @throws SQLException	SQL�쳣
	 */
	public List<Welding_Stat> getStat(String carType,int searchsetid,Connection con)
		throws SQLException{
		List<Welding_Stat> list = new ArrayList<Welding_Stat>();
		DAO_WeldingSearch dao = new DAO_WeldingSearch();
		Statement stmt = con.createStatement();
		log.debug("������ͳ��SQL:"+dao.getStat(carType,searchsetid));
		ResultSet rs = stmt.executeQuery(dao.getStat(carType,searchsetid));
		while(rs.next()){
			Welding_Stat ws = new Welding_Stat();
			ws.setNo(rs.getString("max_no"));
			ws.setNum(rs.getInt("sum_num"));
			list.add(ws);
		}
		if(stmt != null){
			stmt.close();
			stmt = null;
		}
		return list;
	}
	
	/**
	 * ������ͳ��
	 * 
	 * @param carType	��������
	 * @param con		���Ӷ���
	 * @return
	 * @throws SQLException	SQL�쳣
	 */
	public List<Welding_Stat> getStat_B8Q5(String carType,String B8_Q5,Connection con)
		throws SQLException{
		List<Welding_Stat> list = new ArrayList<Welding_Stat>();
		DAO_WeldingSearch dao = new DAO_WeldingSearch();
		Statement stmt = con.createStatement();
		log.debug("������ͳ��SQL:"+dao.getStat_B8Q5(carType,B8_Q5));
		ResultSet rs = stmt.executeQuery(dao.getStat_B8Q5(carType,B8_Q5));
		while(rs.next()){
			Welding_Stat ws = new Welding_Stat();
			ws.setNo(rs.getString("max_no"));
			ws.setNum(rs.getInt("sum_num"));
			list.add(ws);
		}
		if(stmt != null){
			stmt.close();
			stmt = null;
		}
		return list;
	}
	
	/**
	 * ͨ��˳��Ų��ѯͳ������
	 * 
	 * @param startOrder	��ʼ˳���
	 * @param endOrder	����˳���
	 * @param carType	��������
	 * @param con		���Ӷ���
	 * @return
	 * @throws SQLException	SQL�쳣
	 */
	public List<Welding_Stat> getStatByStartOrderEndOrder(String startOrder,String endOrder,String carType,int searchsetid,Connection con)
		throws SQLException{
		List<Welding_Stat> list = new ArrayList<Welding_Stat>();
		DAO_WeldingSearch dao = new DAO_WeldingSearch();
		Statement stmt = con.createStatement();
		log.debug("ͨ��˳��Ų��ѯͳ������SQL:"+dao.getStatByStartOrderEndOrder(startOrder,endOrder,carType,searchsetid));
		ResultSet rs = stmt.executeQuery(dao.getStatByStartOrderEndOrder(startOrder,endOrder,carType,searchsetid));
		while(rs.next()){
			Welding_Stat ws = new Welding_Stat();
			ws.setNo(rs.getString("max_no"));
			ws.setNum(rs.getInt("sum_num"));
			list.add(ws);
		}
		if(stmt != null){
			stmt.close();
			stmt = null;
		}
		return list;
	}
	
	/**
	 * ͨ��˳��Ų��ѯͳ������
	 * 
	 * @param startOrder	��ʼ˳���
	 * @param endOrder	����˳���
	 * @param carType	��������
	 * @param con		���Ӷ���
	 * @return
	 * @throws SQLException	SQL�쳣
	 */
	public List<Welding_Stat> getStatByStartOrderEndOrder_B8Q5(String startOrder,String endOrder,String carType,String B8_Q5,Connection con)
		throws SQLException{
		List<Welding_Stat> list = new ArrayList<Welding_Stat>();
		DAO_WeldingSearch dao = new DAO_WeldingSearch();
		Statement stmt = con.createStatement();
		log.debug("ͨ��˳��Ų��ѯͳ������SQL:"+dao.getStatByStartOrderEndOrder_B8Q5(startOrder,endOrder,carType,B8_Q5));
		ResultSet rs = stmt.executeQuery(dao.getStatByStartOrderEndOrder_B8Q5(startOrder,endOrder,carType,B8_Q5));
		while(rs.next()){
			Welding_Stat ws = new Welding_Stat();
			ws.setNo(rs.getString("max_no"));
			ws.setNum(rs.getInt("sum_num"));
			list.add(ws);
		}
		if(stmt != null){
			stmt.close();
			stmt = null;
		}
		return list;
	}
	
	/**
	 * ͨ����ʼʱ�䡢������ѯͳ������
	 * 
	 * @param startTime	��ʼʱ��
	 * @param num		����
	 * @param carType	��������
	 * @param con		���Ӷ���
	 * @return
	 * @throws SQLException	SQL�쳣
	 */
	public List<Welding_Stat> getStatByStartTimeNum(String startTime,String num,String carType,int searchsetid,Connection con)
		throws SQLException{
		List<Welding_Stat> list = new ArrayList<Welding_Stat>();
		DAO_WeldingSearch dao = new DAO_WeldingSearch();
		Statement stmt = con.createStatement();
		log.debug("ͨ����ʼʱ�䡢������ѯͳ������SQL:"+dao.getStatByStartTimeNum(startTime,num,carType,searchsetid));
		ResultSet rs = stmt.executeQuery(dao.getStatByStartTimeNum(startTime,num,carType,searchsetid));
		while(rs.next()){
			Welding_Stat ws = new Welding_Stat();
			ws.setNo(rs.getString("max_no"));
			ws.setNum(rs.getInt("sum_num"));
			list.add(ws);
		}
		if(stmt != null){
			stmt.close();
			stmt = null;
		}
		return list;
	}
	
	/**
	 * ͨ����ʼʱ�䡢������ѯͳ������
	 * 
	 * @param startTime	��ʼʱ��
	 * @param num		����
	 * @param carType	��������
	 * @param con		���Ӷ���
	 * @return
	 * @throws SQLException	SQL�쳣
	 */
	public List<Welding_Stat> getStatByStartTimeNum_B8Q5(String startTime,String num,String carType,String B8_Q5,Connection con)
		throws SQLException{
		List<Welding_Stat> list = new ArrayList<Welding_Stat>();
		DAO_WeldingSearch dao = new DAO_WeldingSearch();
		Statement stmt = con.createStatement();
		log.debug("ͨ����ʼʱ�䡢������ѯͳ������SQL:"+dao.getStatByStartTimeNum_B8Q5(startTime,num,carType,B8_Q5));
		ResultSet rs = stmt.executeQuery(dao.getStatByStartTimeNum_B8Q5(startTime,num,carType,B8_Q5));
		while(rs.next()){
			Welding_Stat ws = new Welding_Stat();
			ws.setNo(rs.getString("max_no"));
			ws.setNum(rs.getInt("sum_num"));
			list.add(ws);
		}
		if(stmt != null){
			stmt.close();
			stmt = null;
		}
		return list;
	}
	
	/**
	 * ͨ��SQL������ѯ��װ��ѯ
	 * 
	 * @param sql_temp1	SQL����
	 * @param con		���Ӷ���
	 * @return
	 * @throws SQLException	SQL�쳣
	 */
	public List<AssemblySearch> getACarsByCondition(String sql_temp1,Connection con)
		throws SQLException{
		List<AssemblySearch> list = new ArrayList<AssemblySearch>();
		DAO_WeldingSearch dao = new DAO_WeldingSearch();
		Statement stmt = con.createStatement();
		log.debug("ͨ��SQL������ѯ��װ��ѯSQL:"+dao.getACarsByCondition(sql_temp1));
		ResultSet rs = stmt.executeQuery(dao.getACarsByCondition(sql_temp1));
		while(rs.next()){
			AssemblySearch as = new AssemblySearch();
			as.setSeq(rs.getString("cSEQNo_A"));
			as.setVin(rs.getString("cVinCode"));
			as.setKin(rs.getString("cCarNo"));
			as.setDWBegin(rs.getString("dWBegin"));
			as.setDABegin(rs.getString("dABegin"));
			as.setDCp6Begin(rs.getString("dCp6Begin"));
			list.add(as);
		}
		if(stmt != null){
			stmt.close();
			stmt = null;
		}
		return list;
	}
	
	/**
	 * ͨ��SQL������ѯ��װͳ��
	 * 
	 * @param sql_temp1	SQL����
	 * @param con	���Ӷ���
	 * @return
	 * @throws SQLException	SQL�쳣
	 */
	public List<Welding_Stat> getAStatByCondition(String sql_temp1,Connection con)
		throws SQLException{
		List<Welding_Stat> list = new ArrayList<Welding_Stat>();
		DAO_WeldingSearch dao = new DAO_WeldingSearch();
		Statement stmt = con.createStatement();
		log.debug("ͨ��SQL������ѯ��װͳ��SQL:"+dao.getAStatByCondition(sql_temp1));
		ResultSet rs = stmt.executeQuery(dao.getAStatByCondition(sql_temp1));
		while(rs.next()){
			Welding_Stat ws = new Welding_Stat();
			ws.setNo(rs.getString("seq"));
			ws.setNum(rs.getInt("num"));
			list.add(ws);
		}
		if(stmt != null){
			stmt.close();
			stmt = null;
		}
		return list;
	}
	
	/**
	 * ͨ�����͡�����KIS��װ��ѯ
	 * 
	 * @param carType	����
	 * @param con		���Ӷ���
	 * @throws SQLException	SQL�쳣
	 */
	public List<KisAssembly> getKISAssemblyByCartype(String carType,String factory,String sql_temp,Connection con)throws SQLException{
		List<KisAssembly> list = new ArrayList<KisAssembly>();
		DAO_WeldingSearch dao = new DAO_WeldingSearch();
		Statement stmt = con.createStatement();
		log.debug("ͨ�����͡�����KIS��װ��ѯSQL:"+dao.getKISAssemblyByCartype(carType,factory,sql_temp));
		ResultSet rs = stmt.executeQuery(dao.getKISAssemblyByCartype(carType,factory,sql_temp));
		while(rs.next()){
			KisAssembly ka = new KisAssembly();
			ka.setSeq(rs.getString("cSEQNo_A"));
			ka.setCartype(rs.getString("cartype"));
			ka.setVin(rs.getString("cVinCode"));
			ka.setKin(rs.getString("cCarNo"));
			ka.setDABegin(rs.getString("dABegin"));
			ka.setDCp6Begin(rs.getString("dCp6Begin"));
			list.add(ka);
		}
		if(stmt != null){
			stmt.close();
			stmt = null;
		}
		return list;
	}
	
	/**
	 * ͨ������KIS��װ��ѯ
	 * 
	 * @param carType	����
	 * @param con		���Ӷ���
	 * @throws SQLException	SQL�쳣
	 */
	public List<KisWelding> getKISWeldingByCartype(String carType,Connection con)throws SQLException{
		List<KisWelding> list = new ArrayList<KisWelding>();
		DAO_WeldingSearch dao = new DAO_WeldingSearch();
		Statement stmt = con.createStatement();
		log.debug("ͨ������KIS��װ��ѯSQL:"+dao.getKISWeldingByCartype(carType));
		ResultSet rs = stmt.executeQuery(dao.getKISWeldingByCartype(carType));
		while(rs.next()){
			KisWelding kw = new KisWelding();
			kw.setSeq(rs.getString("cSEQNo"));
			kw.setVin(rs.getString("cVinCode"));
			kw.setKin(rs.getString("cCarNo"));
			kw.setDWBegin(rs.getString("dWBegin"));
			list.add(kw);
		}
		if(stmt != null){
			stmt.close();
			stmt = null;
		}
		return list;
	}
	
	/**
	 * ͨ��������ѯ���
	 * 
	 * @param sql_temp1
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public List<Part> getpart(String sql_temp1,Connection con)throws SQLException{
		List<Part> list = new ArrayList<Part>();
		DAO_WeldingSearch dao = new DAO_WeldingSearch();
		Statement stmt = con.createStatement();
		log.debug("ͨ��������ѯ���SQL:"+dao.getpart(sql_temp1));
		ResultSet rs = stmt.executeQuery(dao.getpart(sql_temp1));
		
		while(rs.next()){
			Part part = new Part();
			part.setCode(rs.getString("cqadno"));
			part.setName(rs.getString("ctfassname"));
			part.setPageno(rs.getString("pageno"));
			part.setTraceone(rs.getString("traceone"));
			part.setTracetwo(rs.getString("tracetwo"));
			part.setProddate(rs.getString("recorddate"));
			
			list.add(part);
		}
		
		if(stmt != null){
			stmt.close();
			stmt = null;
		}
		return list;
	}
	
	/**
	 * ͨ��������ѯ����
	 * 
	 * @param sql_temp1
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public List<AssemblySearch> getcar(String sql_temp1,Connection con)throws SQLException{
		List<AssemblySearch> list = new ArrayList<AssemblySearch>();
		DAO_WeldingSearch dao = new DAO_WeldingSearch();
		Statement stmt = con.createStatement();
		log.debug("ͨ��������ѯ����SQL:"+dao.getcar(sql_temp1));
		ResultSet rs = stmt.executeQuery(dao.getcar(sql_temp1));
		while(rs.next()){
			AssemblySearch as = new AssemblySearch();
			as.setSeq_W(rs.getString("cSEQNo"));
			as.setSeq(rs.getString("cSEQNo_A"));
			as.setKin(rs.getString("cCarNo"));
			as.setVin(rs.getString("cVinCode"));
			as.setDWBegin(rs.getString("dWBegin"));
			as.setDABegin(rs.getString("dABegin"));
			as.setDCp6Begin(rs.getString("dCp6Begin"));
			as.setCfilename_w(rs.getString("cfilename_w"));
			as.setCfilename_a(rs.getString("cfilename_a"));
			list.add(as);
		}
		if(stmt != null){
			stmt.close();
			stmt = null;
		}
		return list;
	}
}
