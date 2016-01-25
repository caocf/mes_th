package mes.pm.dao;

import mes.pm.bean.Device;
import mes.pm.bean.DeviceType;
/**
 * @author Xujia
 *
 */
public class DAO_DeviceForOracle implements DAO_Device {
	
	/**
	 * �����豸��sql���
	 * @param device
	 * @return  
	 */
	public String saveDevice(Device device){
		 String sql = "insert into t_pm_device(int_id,str_devicename,str_devicecode,str_description) "
				+ "values(seq_PM_DEVICE.nextval,'"
				+ device.getName()
				+"','"+device.getCode()+"','"+device.getDescription()+"')";	   
		return sql;
	}	
	/**
	 * �����豸���͵�sql���
	 * @param device
	 * @return   
	 */
	public String saveDeviceType(DeviceType deviceType){
		 String sql = "insert into t_pm_devicetype(int_id,str_statename) "
				+ "values(seq_PM_DEVICETYPE.nextval,'"
				+deviceType.getName()+"')";	   
		return sql;
	}	
	/**
	 * �����豸-���͵�sql���
	 * @param d
	 * @param t
	 * @return  
	 */
	public String saveDevice_Type(int d,int t){
		 String sql = "insert into t_pm_device_type(int_id,int_device,int_devicetype) "
				+ "values(seq_PM_DEVICE_TYPE.nextval,"
				+d+","+t+")";	   
		return sql;
	}		
	/**
	 * �����豸-������Ԫ��sql���
	 * @param d
	 * @param u
	 * @return
	 */
	public String saveDevice_Unit(int d,int u){
		 String sql = "insert into t_pm_device_unit(int_id,int_device,int_unit) "
				+ "values(seq_PM_DEVICE_UNIT.nextval,"
				+d+","+u+")";	   
		return sql;
	}		
	/**
	 * ͨ����Ų���豸��sql���
	 * @param id
	 * @return
	 */
	public String getDeviceById(int id){
		String sql = "select * from t_pm_device"
			+ " where int_id = " + id + "";
	return sql;
	}
	/**
	 * ͨ����Ų���豸���͵�sql���
	 * @param id
	 * @return
	 */
	public String getDeviceTypeById(int id){
		String sql = "select * from t_pm_devicetype"
			+ " where int_id = " + id + "";
	return sql;
	}
	/**
	 * ͨ����Ų���豸-���͵�sql���
	 * @param id
	 * @return
	 */
	public String getDevice_TypeById(int id){
		String sql = "select * from t_pm_device_type"
			+ " where int_device = " + id + "";
	return sql;
	}
	/**
	 * ͨ����Ų���豸-��Ԫ��sql���
	 * @param id
	 * @return
	 */
	public String getDevice_UnitById(int id){
		String sql = "select * from t_pm_device_unit"
			+ " where int_device = " + id + "";
	return sql;
	}
	/**
	 * ͨ�����ɾ���豸��sql���
	 * @param id
	 * @return
	 */
	public String delDeviceById(int id){
		String sql = "delete from  t_pm_device where int_id=" + id;
		return sql;
	}
	/**
	 * ͨ�����ɾ���豸���͵�sql���
	 * @param id
	 * @return
	 */
    public	String delDeviceTypeById(int id){
		String sql = "delete from  t_pm_devicetype where int_id=" + id;
		return sql;
	}
	/**
	 * ͨ�����ɾ���豸-���͵�sql���  ������
	 * @param id
	 * @return
	 */
	public String delDevice_TypeById(int id){
		String sql = "delete from  t_pm_device_type where int_id=" + id;
		return sql;
	}
	/**
	 * ͨ�����ɾ���豸-��Ԫ��sql���
	 * @param id
	 * @return
	 */
	public String delDevice_UnitById(int id){
		String sql = "delete from  t_pm_device_unit where int_id=" + id;
		return sql;
	}
	/**
	 * ͨ���豸��ɾ���豸-���͵�sql���
	 * @param id
	 * @return
	 */
	public String delDevice_TypeByDid(int deviceid){
		String sql="delete from t_pm_device_type where int_device="+deviceid;
		return sql;
	}
	/**
	 * ͨ���豸��ɾ���豸-��Ԫ��sql���
	 * @param id
	 * @return
	 */
	public String delDevice_UnitByDid(int deviceid){ 
		String sql="delete from t_pm_device_unit where int_device="+deviceid;
		return sql;
	}
	/**
	 * �����豸��sql���
	 * @param device
	 * @return  
	 */
	public String updateDevice(Device device){  
		String sql = "update t_pm_device set str_devicename ='"
			+device.getName() + "' , str_devicecode = '"+ device.getCode()
			 + "' , str_description = '"+ device.getDescription()+ "' where int_id = "
			+ device.getId();
	return sql;
	}
	/**
	 * �����豸���͵�sql���
	 * @param device
	 * @return 
	 */
	public String updateDeviceType(DeviceType deviceType){  
		String sql = "update t_pm_devicetype set str_statename ='"
			+deviceType.getName() +  "' where int_id = "
			+ deviceType.getId();
	return sql;
	}
	/**
	 * �����豸-���͵�sql���
	 * @param d
	 * @param t
	 * @return t_pm_device_type  int_id,int_device,int_devicetype
	 */
	public String updateDevice_Type(int type,int id){  
		String sql = "update t_pm_device_type set int_devicetype ="
			+type +  " where int_id = "
			+ id;
	return sql;
	}
	/**
	 * �����豸-��Ԫ��sql���
	 * @param d
	 * @param t
	 * @return
	 */
	public String updateDevice_Unit(int unit,int id){  
		String sql = "update t_pm_device_unit set int_unit ="
			+unit +  " where int_id = "
			+ id;
	return sql;
	}   
	/**
	 * ��ѯ��ȫ���豸��sql���
	 * @return
	 */
	public String getAllDevice (){		
		
		String sql = "select *"
			+ " from t_pm_device";	
	return sql;
	}
	/**
	 * ��ѯ��ȫ���豸���͵�sql���
	 * @return
	 */
	public String getAllDeviceType (){
		String sql = "select *"
			+ " from t_pm_devicetype";
		return sql;}

	/**
	 * ��֤�豸�Ƿ�����
	 *  ���
	 * @param id
	 * @return ˳��Ÿ���
	 */
	public String checkDeviceTypeById(int id) {
		String sql = "select count(*) from  t_pm_devicetype a, t_pm_device_type b"
			+ " where a.int_id=" + id
			+ " and a.int_id = b.int_devicetype ";
	return sql;
	}
	/**
	 * ���ExceptionRecord�����Ƿ����豸����
	 *  ���
	 * @param id
	 * @return ��������
	 */
	public String checkDTypeInrecordById(int id){
		String sql = "select count(*) from  t_pm_devicetype a, t_pm_exceptionrecord b"
			+ " where a.int_id=" + id
			+ " and a.int_id = b.int_devicetypeid";
	    return sql;
	}
	/**
	 * ���ExceptionRecord�����Ƿ����豸��
	 *  ���
	 * @param id
	 * @return ��������
	 */
	public String checkDeviceInrecordById(int id){
		String sql = "select count(*) from  t_pm_device a, t_pm_exceptionrecord b"
			+ " where a.int_id=" + id
			+ " and a.int_id = b.int_deviceid";
	    return sql;
	}
	/**
	 * ͨ���豸��Ų���豸��sql���
	 * @param name
	 * @return
	 */
	public String getDeviceByCode(String code){
		String sql = "select * from t_pm_device"
			+ " where str_devicecode='" + code +"'order by int_id";	
	return sql;		
	}
	/**
	 * @param id
	 * @return
	 */
	public String getTypeidByid(int id){
		String sql="select * from t_pm_device_type "
	        + " where int_device=" + id +" order by int_id";	
	     return sql;
	}
	
	/**
	 * @param id
	 * @return
	 */
	public String getUnitidByid(int id){
	String sql="select * from t_pm_device_unit "
        + " where int_device=" + id +" order by int_id";	
     return sql;
}
	/** ����unit device����
	 * @param id
	 * @return
	 */
	public String getUnit_Device(int id){
		String sql="select d.int_id,t.int_id as id,t.int_unit from t_pm_device_unit t,t_pm_device d"
       +" where t.int_device=d.int_id and d.int_id="+id;
		return sql;
	}
	/**
	 * ����type device����
	 * @param id
	 * @return
	 */
   public String getType_Device(int id){
	   String sql="select d.int_id,t.int_id as id,t.int_devicetype from t_pm_device_type t,t_pm_device d"
	       +" where t.int_device=d.int_id and d.int_id="+id;
			return sql;
	   
   }
   /**
	 * ����������id
	 * @param name
	 * @return
	 */
	public String getTypeidByname(String name){
		String sql="select int_id from t_pm_devicetype where str_statename='"+name+"'";
		return  sql;
	}
	/**
	 * @param name
	 * @param id
	 * @return
	 */
	public String getDeviceidBytype(String name,int id){
	 String sql="select d.int_id,d.str_devicename,t.int_id as id,t.int_devicetype"
	 +" from t_pm_device_type t,t_pm_device d"
	 +" where t.int_device=d.int_id  and d.str_devicename='"+name+"' and t.int_devicetype="+id;
	 return sql;
	 
}
}