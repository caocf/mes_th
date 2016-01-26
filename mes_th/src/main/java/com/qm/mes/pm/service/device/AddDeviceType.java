package com.qm.mes.pm.service.device;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.AdapterService;
import com.qm.mes.framework.ExecuteResult;
import com.qm.mes.framework.IMessage;
import com.qm.mes.framework.ServiceException;
import com.qm.mes.framework.ServiceExceptionType;
import com.qm.mes.pm.bean.DeviceType;
import com.qm.mes.pm.factory.DeviceFactory;
/**
 * ����豸����
 * 
 * @author xujia
 * 
 */

public class AddDeviceType  extends AdapterService {
	/**
	 * �������
	 */
	private Connection con = null;
	/**
	 * �豸��
	 */
	private String str_name = null;
	
	Statement stmt = null;
	ResultSet rs = null;
	DeviceFactory factory = new DeviceFactory();

	private final Log log = LogFactory.getLog(AddDeviceType.class);
	@Override
	public boolean checkParameter(IMessage message, String processid) {
	
		con = (Connection) message.getOtherParameter("con");
		str_name = message.getUserParameterValue("str_name").trim();		
		
	
		//���log��Ϣ
	    String debug="�豸��������str_name��" + str_name	+ "\n";
	    log.info("����豸���͵Ĳ���: " + debug);
	    
		if (str_name == null  ) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
					processid, new java.util.Date(), null));
			return false;
		}
		return true;
	}
	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {
			try {					
						
				DeviceType dt=new DeviceType();
				dt.setName(str_name);
				factory.createDeviceType(dt, con);
				
				log.debug( "����豸���ͳɹ�!");
			
		}catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), processid, new Date(), sqle));
				log.error("����豸���Ͳ���ʱ,���ݿ��쳣"	+ sqle.toString());
				return ExecuteResult.fail;
			} 
		} catch (Exception e) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
					processid, new java.util.Date(), e));
			log.fatal( "����豸���Ͳ���ʱ,δ֪�쳣" + e.toString());
			return ExecuteResult.fail;
			}
		return ExecuteResult.sucess;
	}
	@Override
	public void relesase() throws SQLException {
		str_name = null;		
		con = null;
	}
}
