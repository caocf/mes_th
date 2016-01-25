package th.tg.factory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.sql.ResultSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import th.tg.bean.ChengduCar;
import th.tg.dao.DAO_ChengduCar;

/**
 * �ɶ�������
 * 
 * @author YuanPeng
 *
 */
public class ChengduCarFactory {

	//��־
	private final Log log = LogFactory.getLog(ChengduCarFactory.class);
	
	/**
	 * ���տ�ʼ����ɾ���ɶ��ݴ�
	 * 
	 * @param startDate
	 * @param con
	 * @throws SQLException
	 */
	public void deleteChengduJettaByStartDate(String startDate,Connection con)
	throws SQLException{
		DAO_ChengduCar dao = new DAO_ChengduCar();
		Statement stmt = con.createStatement();
		log.debug("���տ�ʼ����ɾ���ɶ��ݴ�:"+dao.deleteChengduJettaByStartDate(startDate));
		stmt.execute(dao.deleteChengduJettaByStartDate(startDate));
		if(stmt != null){
			stmt.close();
			stmt = null;
		}
	}
	/**
	 * ���տ�ʼ���ڲ�ѯ�ɶ��ݴ�
	 * 
	 * @param startDate
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	public List<ChengduCar> getChengduJettaByStartDate(String startDate,Connection con)throws SQLException{
		List<ChengduCar> list = new ArrayList<ChengduCar>();
		DAO_ChengduCar dao = new DAO_ChengduCar();
		Statement stmt = con.createStatement();
		log.debug("���տ�ʼ���ڲ�ѯ�ɶ��ݴ�:"+dao.getChengduJettaByStartDate(startDate));
		ResultSet rs = stmt.executeQuery(dao.getChengduJettaByStartDate(startDate));
		while(rs.next()){
			ChengduCar cdc = new ChengduCar();
			cdc.setCCarNo(rs.getString("cCarNo"));
			cdc.setCVinCode(rs.getString("cVinCode"));
			cdc.setDWBegin(rs.getString("dWBegin"));
			list.add(cdc);
		}
		if(stmt != null){
			stmt.close();
			stmt = null;
		}
		return list;
	}
}
