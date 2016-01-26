package com.qm.mes.pm.service.device;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.AdapterService;
import com.qm.mes.framework.DataBaseType;
import com.qm.mes.framework.ExecuteResult;
import com.qm.mes.framework.IMessage;
import com.qm.mes.framework.ServiceException;
import com.qm.mes.framework.ServiceExceptionType;
import com.qm.mes.pm.bean.Device;
import com.qm.mes.pm.dao.DAO_Device;
import com.qm.mes.pm.factory.DeviceFactory;
import com.qm.mes.pm.service.datarule.AddDataRule;
import com.qm.mes.system.dao.DAOFactoryAdapter;
/**
 * �����豸
 * @author xujia
 * 
 */

public class UpdateDevice extends AdapterService {
	/**
	 * �������
	 */
	private Connection con = null;
	/**
	 * �豸�� 
	 */
	private String str_devicename = null;
	/**
	 * �豸���
	 */
	private String str_devicecode = null;
	/**
	 * �豸����
	 */
	private String str_description = null;
	/**
	 *  ������Ԫ��Ϣ
	 */
	private String unit_info=null;
	/**
	 *  �豸��Ϣ
	 */
	private String device_info=null;
	/**
	 *  ���
	 */ 
	private String int_id=null;
	DeviceFactory factory = new DeviceFactory();
	//��־
	private final Log log = LogFactory.getLog(AddDataRule.class);
	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
		str_devicename = message.getUserParameterValue("str_name");
		str_devicecode= message.getUserParameterValue("str_code");
		int_id= message.getUserParameterValue("int_id");
		str_description = message.getUserParameterValue("str_description");
		//int_produnitid = message.getUserParameterValue("int_produnitid");
		//int_devicetype = message.getUserParameterValue("int_devicetype");
		device_info = message.getUserParameterValue("device_info");
		unit_info = message.getUserParameterValue("unit_info");
		
		//���log��Ϣ
	    String debug="�豸����" + str_devicename + "��"+ "�豸��ţ�"+str_devicecode+ ";"
		+"������Ԫ�ţ�" + unit_info + "��"+ "�豸���ͣ�"+device_info+"�豸������"+str_description;
	    log.debug("�����豸��Ϣ�û��ύ�Ĳ���: " + debug);

		if (str_devicename == null || str_devicecode == null 	|| str_description == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
					processid, new java.util.Date(), null));
			log.fatal("�豸�����豸��š��豸��������Ϊ�ղ������˳�����");
			return false;
		}

		return true;
	}

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {
			try {
				String[] units = unit_info.split(","); // ��Ԫ��ֳ���
				String[] devices = device_info.split(","); // �豸��ֳ���		
				//�����豸��Ϣ
				Device d = new Device();								
				d.setName(str_devicename);
				d.setCode(str_devicecode);
				d.setDescription(str_description);	
				d.setId(new Integer(int_id));
				factory.updateDevice(d, con);
				log.info("�����豸��Ϣ�ɹ���");	
				
				//����������Ԫ��Ϣ
				/* ��������ʱ��������
				 * int unitid=factory.getUnit_Device(new Integer(int_id), con);
				factory.updateDevice_Unit( new Integer(int_produnitid),unitid, con);
			       ���������δ���  */
				DAO_Device dao = (DAO_Device) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_Device.class);
				Statement stmt = con.createStatement();
				factory.delDevice_UnitByDid((new Integer(int_id)), con);  //��ɾ��						
				for (int j = 0; j < units.length; j++) {    //���������Ԫ���豸�Ĺ�ϵ
				    log.debug("���Device_unit: "+dao.saveDevice_Unit((Integer.parseInt(int_id)),(Integer.parseInt(units[j]))));
				    stmt.execute(dao.saveDevice_Unit((Integer.parseInt(int_id)),(Integer.parseInt(units[j]))));	
				}
				log.info("����device_unit�ɹ�");
				//�����豸������Ϣ
				factory.delDevice_TypeByDid((new Integer(int_id)), con); //��ɾ��
				for (int k = 0; k <devices.length; k++) {   //����豸���ͺ��豸�Ĺ�ϵ
					log.debug("���Device_type: "+dao.saveDevice_Type((Integer.parseInt(int_id)),(Integer.parseInt(devices[k]))));
					stmt.execute(dao.saveDevice_Type((Integer.parseInt(int_id)),(Integer.parseInt(devices[k]))));
					}
				log.info("����device_type�ɹ�");
								
			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), processid, new Date(), sqle));
				log.error("���ݿ��쳣���жϷ���");
				return ExecuteResult.fail;
			}
		} catch (Exception e) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
					processid, new java.util.Date(), e));
			log.error("δ֪�쳣���жϷ���");
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
	}

	@Override
	public void relesase() throws SQLException {
		str_devicename = null;
		str_devicecode = null;
		unit_info = null;
		device_info = null;		
		str_description = null;
		con = null;

	}

}

