package com.qm.mes.pm.factory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.DataBaseType;
import com.qm.mes.pm.bean.Device;
import com.qm.mes.pm.bean.DeviceType;
import com.qm.mes.pm.dao.DAO_Device;
import com.qm.mes.system.dao.DAOFactoryAdapter;

/**
 * �豸������
 * @author Xujia
 *
 */
public class DeviceFactory {
//	������־�ļ�
	private final Log log = LogFactory.getLog(DeviceFactory.class);
	
	/** ���
	 * ͨ�����ɾ��DeviceType
	 * @param id
	 * @param con
	 * @throws SQLException 
	 */
	public void delDeviceTypeById(int id, Connection con) throws SQLException {
		DAO_Device dao = (DAO_Device) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_Device.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ�����ɾ��DeviceType: "+dao.delDeviceTypeById(id));	
		stmt.execute(dao.delDeviceTypeById(id));
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}
	}
	/** ���
	 * ͨ�����ɾ��Device
	 * @param id
	 * @param con
	 * @throws SQLException 
	 */
	public void delDeviceById(int id, Connection con) throws SQLException {
		DAO_Device dao = (DAO_Device) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_Device.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ�����ɾ��Device: "+dao.delDeviceById(id));	
		stmt.execute(dao.delDeviceById(id));
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}
	}
	
	/** ���
	 * ͨ���豸��ɾ���豸-��Ԫ
	 * @param deviceid
	 * @param con
	 * @throws SQLException 
	 */
	public void delDevice_UnitByDid(int deviceid, Connection con) throws SQLException {
		DAO_Device dao = (DAO_Device) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_Device.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ�����ɾ��Device_unit: "+dao.delDevice_UnitByDid(deviceid));	
		stmt.execute(dao.delDevice_UnitByDid(deviceid));
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}
	}	
	
	/** ���
	 * ͨ���豸��ɾ���豸-����
	 * @param deviceid
	 * @param con
	 * @throws SQLException 
	 */
	public void delDevice_TypeByDid(int deviceid, Connection con) throws SQLException {
		DAO_Device dao = (DAO_Device) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_Device.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ�����ɾ��Device_type: "+dao.delDevice_TypeByDid(deviceid));	
		stmt.execute(dao.delDevice_TypeByDid(deviceid));
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}
	}	
	
	/** ���
	 * ͨ�����ɾ��Device_Type
	 * @param id
	 * @param con
	 * @throws SQLException 
	 */
	public void delDevice_TypeById(int id, Connection con) throws SQLException {
		int int_id=0;
		ResultSet rs = null;
		DAO_Device dao = (DAO_Device) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_Device.class);
		Statement stmt = con.createStatement();		
		log.debug("ͨ����Ų�id: "+dao.getTypeidByid(id));	
		rs = stmt.executeQuery(dao.getTypeidByid(id));
		while(rs.next())
		{
	    int_id=rs.getInt(1);
	    log.info("ȡ��idΪ��"+int_id);
		}
		log.debug("ɾ��device_type����Ϣ: "+dao.delDevice_TypeById(int_id));	
		if (int_id!=0)
		{stmt.execute(dao.delDevice_TypeById(int_id));}
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}
	}
	/** ���
	 * ͨ�����ɾ��Device_Unit
	 * @param id
	 * @param con
	 * @throws SQLException 
	 */
	public void delDevice_UnitById(int id, Connection con) throws SQLException {
		int int_id1=0;
		ResultSet rs = null;
		DAO_Device dao = (DAO_Device) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_Device.class);
		Statement stmt = con.createStatement();		
		log.debug("ͨ����Ų�id: "+dao.getUnitidByid(id));	
		rs = stmt.executeQuery(dao.getUnitidByid(id));
		while(rs.next())
		{
	    int_id1=rs.getInt(1);
	    log.info("ȡ��idΪ��"+int_id1);
		}
		log.debug("ɾ��device_unit����Ϣ: "+dao.delDevice_UnitById(int_id1));	
		if (int_id1!=0)
		stmt.execute(dao.delDevice_UnitById(int_id1));
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}
	}
	
	/**  ���
	 * ����DeviceType
	 * @param DeviceType
	 * @param con
	 * @throws SQLException
	 */
	public void createDeviceType(DeviceType deviceType,Connection con) throws SQLException{
		DAO_Device dao = (DAO_Device) DAOFactoryAdapter
		.getInstance(DataBaseType.getDataBaseType(con),
				DAO_Device.class);
		Statement stmt = con.createStatement();
		log.debug("����DeviceType: "+dao.saveDeviceType(deviceType));
		stmt.execute(dao.saveDeviceType(deviceType));		
		if(stmt!=null){
			stmt.close();
			stmt=null;
		}
	}
	/**  ���
	 * ����Device
	 * @param Device
	 * @param con
	 * @throws SQLException
	 */
	public void createDevice(Device device,Connection con) throws SQLException{
		DAO_Device dao = (DAO_Device) DAOFactoryAdapter
		.getInstance(DataBaseType.getDataBaseType(con),
				DAO_Device.class);
		Statement stmt = con.createStatement();
		log.debug("����Device: "+dao.saveDevice(device));
		stmt.execute(dao.saveDevice(device));		
		if(stmt!=null){
			stmt.close();
			stmt=null;
		}
	}
	/**
	 *  ͨ���豸��Ų���豸
	 * @param id
	 * @param con
	 * @return
	 * @throws SQLException 
	 */
	public Device getDeviceByCode(String str_code,Connection con) throws SQLException{
		Device d = null;
		DAO_Device dao = (DAO_Device) DAOFactoryAdapter
		.getInstance(DataBaseType.getDataBaseType(con),
				DAO_Device.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ���豸��Ų���豸��sql��䣺"+dao.getDeviceByCode(str_code));
		ResultSet rs = stmt.executeQuery(dao.getDeviceByCode(str_code));
		log.debug("ͨ���豸��Ż�ȡ�豸�б�---");
		while(rs.next()){
			d = new Device();
			d.setId(rs.getInt("int_id"));
			d.setName(rs.getString("str_devicename"));
			d.setCode(rs.getString("str_devicecode"));			
			d.setDescription(rs.getString("str_description"));
			log.debug("�豸�ţ�"+rs.getInt("int_id")+"���豸��"+rs.getString("str_devicename")+"���豸���룺"
					+rs.getString("str_devicecode")+"��������"
					+rs.getString("str_description"));
		}
		if(rs!=null){
			rs.close();
			rs=null;
		}
		if(stmt!=null){
			stmt.close();
			stmt=null;
		}
		return d;
	}

	/**
	 * ͨ��ID��ѯ�豸
	 * 
	 * @param id
	 *            �豸��
	 * @param con
	 *            ���Ӷ���
	 * @return ͨ��ID��ѯ����ָ�����
	 * @throws java.sql.SQLException
	 */
	public Device getDeviceById(int id, Connection con)
			throws SQLException, ParseException {
		Device d = new Device();
		DAO_Device dao = (DAO_Device) DAOFactoryAdapter
		.getInstance(DataBaseType.getDataBaseType(con),
				DAO_Device.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ��ID��ѯ�豸SQL��" + dao.getDeviceById(id));
		ResultSet rs = stmt.executeQuery(dao.getDeviceById(id));
		if (rs.next()) {
			d.setId(rs.getInt("int_id"));
			d.setName(rs.getString("str_devicename"));
			d.setCode(rs.getString("str_devicecode"));
			d.setDescription(rs.getString("str_description"));			
		}
		if (stmt != null)
			stmt.close();
		return d;
	}
	
	/**
	 * ͨ��ID��ѯ�豸����
	 * 
	 * @param id
	 *            �豸��
	 * @param con
	 *            ���Ӷ���
	 * @return ͨ��ID��ѯ����ָ�����
	 * @throws java.sql.SQLException
	 */
	public DeviceType getDeviceTypeById(int id, Connection con)
			throws SQLException, ParseException {
		DeviceType d = new DeviceType();
		DAO_Device dao = (DAO_Device) DAOFactoryAdapter
		.getInstance(DataBaseType.getDataBaseType(con),
				DAO_Device.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ��ID��ѯ�豸����SQL��" + dao.getDeviceTypeById(id));
		ResultSet rs = stmt.executeQuery(dao.getDeviceTypeById(id));
		if (rs.next()) {
			d.setId(rs.getInt("int_id"));
			d.setName(rs.getString("str_statename"));
				
		}
		if (stmt != null)
			stmt.close();
		return d;
	}
	
	/**
	 * ͨ��ID��ѯid
	 * 
	 * @param id
	 *            �豸��
	 * @param con
	 *            ���Ӷ���
	 * @return ͨ��ID��ѯ����ָ�����
	 * @throws java.sql.SQLException
	 */
	public int getUnit_Device(int id, Connection con)
			throws SQLException, ParseException {
		int unitid=0;
		DAO_Device dao = (DAO_Device) DAOFactoryAdapter
		.getInstance(DataBaseType.getDataBaseType(con),
				DAO_Device.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ��ID��ѯ������Ԫ�ţ�" + dao.getUnit_Device(id));
		ResultSet rs = stmt.executeQuery( dao.getUnit_Device(id));
		if (rs.next()) {
			unitid=rs.getInt("id");			
		}
		log.info("��ԪidΪ��"+unitid);
		if (stmt != null)
			stmt.close();
		return unitid;
	}
	/**
	 * ͨ��ID��ѯ������Ԫ��
	 * 
	 * @param id
	 *            �豸��
	 * @param con
	 *            ���Ӷ���
	 * @return ͨ��ID��ѯ����ָ�����
	 * @throws java.sql.SQLException
	 */
	public String getUnitid_Device(int id, Connection con)
			throws SQLException, ParseException {
		String unitid="";
		DAO_Device dao = (DAO_Device) DAOFactoryAdapter
		.getInstance(DataBaseType.getDataBaseType(con),
				DAO_Device.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ��ID��ѯ������Ԫ�ţ�" + dao.getUnit_Device(id));
		ResultSet rs = stmt.executeQuery( dao.getUnit_Device(id));
	   while (rs.next()) {
			unitid+=rs.getString("int_unit")+",";		
		}
		log.info("��Ԫ��Ϊ��"+unitid);
		if (stmt != null)
			stmt.close();
		return unitid;
	}
	/**
	 * ͨ��ID��ѯ�豸��
	 * 
	 * @param id
	 *            �豸��
	 * @param con
	 *            ���Ӷ���
	 * @return ͨ��ID��ѯ����ָ�����
	 * @throws java.sql.SQLException
	 */
	public String getTypeid_Device(int id, Connection con)
			throws SQLException, ParseException {
		String typeid="";
		DAO_Device dao = (DAO_Device) DAOFactoryAdapter
		.getInstance(DataBaseType.getDataBaseType(con),
				DAO_Device.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ��ID��ѯ������Ԫ�ţ�" + dao.getType_Device(id));
		ResultSet rs = stmt.executeQuery(dao.getType_Device(id));
		while(rs.next()) {
			typeid+=rs.getString("int_devicetype")+",";			
		}
		log.info("���ͺ�Ϊ��"+typeid);
		if (stmt != null)
			stmt.close();
		return typeid;
	}
	/**
	 * ͨ��ID��ѯid
	 * 
	 * @param id
	 *            �豸��
	 * @param con
	 *            ���Ӷ���
	 * @return ͨ��ID��ѯ����ָ�����
	 * @throws java.sql.SQLException
	 */
	public int getType_Device(int id, Connection con)
			throws SQLException, ParseException {
		int typeid=0;
		DAO_Device dao = (DAO_Device) DAOFactoryAdapter
		.getInstance(DataBaseType.getDataBaseType(con),
				DAO_Device.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ��ID��ѯ������Ԫ�ţ�" + dao.getType_Device(id));
		ResultSet rs = stmt.executeQuery(dao.getType_Device(id));
		if (rs.next()) {
			typeid=rs.getInt("id");			
		}
		log.info("����idΪ��"+typeid);
		if (stmt != null)
			stmt.close();
		return typeid;
	}
	 /**  ���
	 * ����Device����
	 *
	 * @param Device
     * @param con
     *          ���ݿ����Ӷ���
	 * @throws SQLException
     *                  SQL�쳣
	 */
	public void updateDevice(Device device, Connection con)
			throws SQLException {
		DAO_Device dao = (DAO_Device) DAOFactoryAdapter
		.getInstance(DataBaseType.getDataBaseType(con),
				DAO_Device.class);
		Statement stmt = con.createStatement();
		log.debug("����Device���� "+dao.updateDevice(device));
		stmt.execute(dao.updateDevice(device));       
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}
	}
	/**  ���
	 * ����Device_Type����
	 *
	 * @param Device_Type
     * @param con
     *          ���ݿ����Ӷ���
	 * @throws SQLException
     *                  SQL�쳣
	 */
	public void updateDevice_Type(int type,int id, Connection con)
			throws SQLException {
		DAO_Device dao = (DAO_Device) DAOFactoryAdapter
		.getInstance(DataBaseType.getDataBaseType(con),
				DAO_Device.class);
		Statement stmt = con.createStatement();
		log.debug("����Device_Type���� "+dao.updateDevice_Type(type, id));
		stmt.execute(dao.updateDevice_Type(type, id));       
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}
	}
	/**  ���
	 * ����Device_Unit����
	 *
	 * @param Device_Unit
     * @param con
     *          ���ݿ����Ӷ���
	 * @throws SQLException
     *                  SQL�쳣
	 */
	public void updateDevice_Unit(int unit,int id, Connection con)
			throws SQLException {
		DAO_Device dao = (DAO_Device) DAOFactoryAdapter
		.getInstance(DataBaseType.getDataBaseType(con),
				DAO_Device.class);
		Statement stmt = con.createStatement();
		log.debug("����Device_Unit���� "+dao.updateDevice_Unit(unit, id));
		stmt.execute(dao.updateDevice_Unit(unit, id));       
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}
	}
	/**  ���
	 * ����DeviceType����
	 *
	 * @param DeviceType
     * @param con
     *          ���ݿ����Ӷ���
	 * @throws SQLException
     *                  SQL�쳣
	 */
	public void updateDeviceType(DeviceType devicetype, Connection con)
			throws SQLException {
		DAO_Device dao = (DAO_Device) DAOFactoryAdapter
		.getInstance(DataBaseType.getDataBaseType(con),
				DAO_Device.class);
		Statement stmt = con.createStatement();
		log.debug("����Devicetype���� "+dao.updateDeviceType(devicetype));
		stmt.execute(dao.updateDeviceType(devicetype));       
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}
	}

}
