package com.qm.mes.pm.dao;

import com.qm.mes.pm.bean.Device;
import com.qm.mes.pm.bean.DeviceType;

/**
 * @author Xujia
 *
 */
public interface DAO_Device {
	
	/**
	 * �����豸��sql���
	 * @param device
	 * @return
	 */
	String saveDevice(Device device);
	/**
	 * �����豸���͵�sql���
	 * @param device
	 * @return
	 */
	String saveDeviceType(DeviceType deviceType);
	/**
	 * �����豸-���͵�sql���
	 * @param d
	 * @param t
	 * @return
	 */
	String saveDevice_Type(int d,int t);	
	/**
	 * �����豸-������Ԫ��sql���
	 * @param d
	 * @param u
	 * @return
	 */
	String saveDevice_Unit(int d,int u);
	/**
	 * ͨ����Ų���豸��sql���
	 * @param id
	 * @return
	 */
	String getDeviceById(int id);
	/**
	 * ͨ����Ų���豸���͵�sql���
	 * @param id
	 * @return
	 */
	String getDeviceTypeById(int id);
	/**
	 * ͨ����Ų���豸-���͵�sql���
	 * @param id
	 * @return
	 */
	String getDevice_TypeById(int id);
	/**
	 * ͨ����Ų���豸-��Ԫ��sql���
	 * @param id
	 * @return
	 */
	String getDevice_UnitById(int id);
	/**
	 * ͨ�����ɾ���豸��sql���
	 * @param id
	 * @return
	 */
	String delDeviceById(int id);
	/**
	 * ͨ�����ɾ���豸���͵�sql���
	 * @param id
	 * @return
	 */
	String delDeviceTypeById(int id);
	/**
	 * ͨ�����ɾ���豸-���͵�sql���
	 * @param id
	 * @return
	 */
	String delDevice_TypeById(int id);
	/**
	 * ͨ�����ɾ���豸-��Ԫ��sql���
	 * @param id
	 * @return
	 */
	String delDevice_UnitById(int id);
	/**
	 * �����豸��sql���
	 * @param device
	 * @return
	 */
	String updateDevice(Device device);
	/**
	 * �����豸���͵�sql���
	 * @param device
	 * @return
	 */
	String updateDeviceType(DeviceType deviceType);	
	/**
	 * �����豸-���͵�sql���
	 * @param d
	 * @param t
	 * @return
	 */
	String updateDevice_Type(int type,int id);	
	/**
	 * �����豸-��Ԫ��sql���
	 * @param d
	 * @param t
	 * @return
	 */
	String updateDevice_Unit(int unit,int id);   
	/**
	 * ��ѯ��ȫ���豸��sql���
	 * @return
	 */
	String getAllDevice();
	/**
	 * ��ѯ��ȫ���豸���͵�sql���
	 * @return
	 */
	String getAllDeviceType();
	/**
	 * ��֤�豸�Ƿ�����
	 *  ���
	 * @param id
	 * @return ˳��Ÿ���
	 */
	String checkDeviceTypeById(int id);
	/**
	 * ͨ���豸��Ų���豸��sql���
	 * @param name
	 * @return
	 */
	String getDeviceByCode(String code);
	
	/**
	 * @param id
	 * @return
	 */
	String getTypeidByid(int id);
	
	/**
	 * @param id
	 * @return
	 */
	String getUnitidByid(int id);
	
	/** ����unit device����
	 * @param id
	 * @return
	 */
	String getUnit_Device(int id);
	/**
	 * ����type device����
	 * @param id
	 * @return
	 */
	String getType_Device(int id);
	
	/**
	 * ����������id
	 * @param name
	 * @return
	 */
	String getTypeidByname(String name);
	
	/**
	 * @param name
	 * @param id
	 * @return
	 */
	String getDeviceidBytype(String name,int id);
	/**
	 * ���ExceptionRecord�����Ƿ����豸����
	 *  ���
	 * @param id
	 * @return ��������
	 */
	String checkDTypeInrecordById(int id);
	/**
	 * ���ExceptionRecord�����Ƿ����豸��
	 *  ���
	 * @param id
	 * @return ��������
	 */
	String checkDeviceInrecordById(int id);
	/**
	 * ͨ���豸��ɾ���豸-���͵�sql���
	 * @param id
	 * @return
	 */
	String delDevice_TypeByDid(int deviceid);
	/**
	 * ͨ���豸��ɾ���豸-��Ԫ��sql���
	 * @param id
	 * @return
	 */
	String delDevice_UnitByDid(int deviceid); 
}
