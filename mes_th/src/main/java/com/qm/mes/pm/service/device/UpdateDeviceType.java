package com.qm.mes.pm.service.device;

import java.sql.Connection;
import java.sql.SQLException;
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
import com.qm.mes.pm.service.datarule.AddDataRule;
/**
 * �����豸����
 * @author Xujia
 *
 */
public class UpdateDeviceType extends AdapterService {
	/**
	 * �������
	 */
	private Connection con = null;
	/**
	 * �豸������    
	 */
	private String str_name = null;
	/**
	 * ���
	 */
	private String int_id=null;
	DeviceFactory factory = new DeviceFactory();
	//��־
	private final Log log = LogFactory.getLog(AddDataRule.class);
	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
		str_name = message.getUserParameterValue("string_statename");
		int_id= message.getUserParameterValue("int_id");
		
		//���log��Ϣ
	    String debug="�豸����" + str_name;
	    log.debug("�����豸��Ϣ�û��ύ�Ĳ���: " + debug);

		if (str_name == null ) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
					processid, new java.util.Date(), null));
			log.fatal("�豸������Ϊ�ղ������˳�����");
			return false;
		}

		return true;
	}

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {
			try {
				DeviceType dt = new DeviceType();								
				dt.setName(str_name);
				dt.setId(new Integer(int_id));
				factory.updateDeviceType(dt, con);
				log.info("�����豸���ͳɹ���");	
				
								
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
		str_name = null;	
		con = null;

	}

}