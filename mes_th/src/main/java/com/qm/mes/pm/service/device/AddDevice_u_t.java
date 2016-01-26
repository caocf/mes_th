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
import com.qm.mes.pm.dao.DAO_Device;
import com.qm.mes.pm.factory.DeviceFactory;
import com.qm.mes.system.dao.DAOFactoryAdapter;
/**
 * ����豸��������Ԫ/���͵Ĺ�ϵ
 * @author Xujia
 *
 */
public class AddDevice_u_t extends AdapterService {//�������ݿ����
    Connection con = null;
    /**
     * ������Ԫ��
     */
    private String unit_info  =null ;
    /**
     * �豸���ͺ�
     */
    private String device_info = null;
    /**
     * ���ݹ���Id
     */
    private String int_id =null ;
	/**
	 * ��־
	 */
	private final Log log = LogFactory.getLog(AddDevice_u_t.class);
	
	DeviceFactory factory = new DeviceFactory();

    
    
    @Override
    public boolean checkParameter(IMessage message, String processid) {
    	String debug = "";
		con = (Connection) message.getOtherParameter("con");        
		int_id = message.getOutputParameterValue("int_id");
		//int_produnitid = message.getUserParameterValue("int_produnitid");
		//int_devicetype = message.getUserParameterValue("int_devicetype");
		device_info = message.getUserParameterValue("device_info");
		unit_info = message.getUserParameterValue("unit_info");
		
		//���log��Ϣ
	    debug="�豸�ţ�" + int_id + "��"+ "������Ԫ�ţ�"+unit_info+ "��"+ "�豸���ͺ�" +  "��"+device_info;
	
	    log.debug("����쳣����ʱ�û��ύ�Ĳ���: " + debug);

		if (unit_info == null || device_info == null || int_id == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
					processid, new java.util.Date(), null));
			log.fatal("�豸�š�������Ԫ�š��豸���ͺ�����Ϊ�ղ������˳�����");
			return false;
		}

		return true;
	}

    @Override
    public ExecuteResult doAdapterService(IMessage message, String processid){
        try {
			try {                				
                
				DAO_Device dao = (DAO_Device) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_Device.class);
				Statement stmt = con.createStatement();
				String[] units = unit_info.split(","); // ��Ԫ��ֳ���
				String[] devices = device_info.split(","); // �豸��ֳ���
				//���������Ԫ���豸�Ĺ�ϵ
				for (int j = 0; j < units.length; j++) {
				log.debug("����Device_unit: "+dao.saveDevice_Unit((Integer.parseInt(int_id)),(Integer.parseInt(units[j]))));
				stmt.execute(dao.saveDevice_Unit((Integer.parseInt(int_id)),(Integer.parseInt(units[j]))));	
				}
				for (int k = 0; k <devices.length; k++) {
				log.debug("����Device_type: "+dao.saveDevice_Type((Integer.parseInt(int_id)),(Integer.parseInt(devices[k]))));
				stmt.execute(dao.saveDevice_Type((Integer.parseInt(int_id)),(Integer.parseInt(devices[k]))));
				}
				if(stmt!=null){
					stmt.close();
					stmt=null;
				}
               
			}catch (SQLException sqle) {
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
    	device_info = null;
    	unit_info = null;		
    	int_id = null;
		con = null;

	}
    

}