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
import com.qm.mes.pm.bean.Device;
import com.qm.mes.pm.factory.DeviceFactory;

/**
 * ����豸��Ϣ 
 * @author xujia
 * 
 */
public class AddDevice  extends AdapterService {
	/**
	 * �������
	 */
	private Connection con = null;
	/**
	 * �豸�� 
	 */
	private String str_name = null;
	/**
	 * �豸��
	 */
	private String str_code = null;	
	/**
	 *  �豸����
	 */
	private String str_description = null;
	DeviceFactory factory = new DeviceFactory();
	//��־
	private final Log log = LogFactory.getLog(AddDevice.class);
	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
		str_name = message.getUserParameterValue("str_name");
		str_code= message.getUserParameterValue("str_code");		
		str_description = message.getUserParameterValue("str_description");
		
		//���log��Ϣ
	    String debug="�豸����" + str_name + "��"+ "�豸��ţ�"+str_code+ ";"
		+"�豸������"+str_description;
	    log.debug("����豸ʱ�û��ύ�Ĳ���: " + debug);

		if (str_name == null || str_code == null 	|| str_description == null) {
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
				Device d = new Device();					
				d.setName(str_name);
				d.setCode(str_code);
				d.setDescription(str_description);				
				factory.createDevice(d, con);
				log.info("����豸��Ϣ����ɹ���");
				Device dd = new Device();
				//�õ���Ӻ����Ŵ�����������
				dd = factory.getDeviceByCode(str_code, con);
				message.setOutputParameter("int_id", String.valueOf(dd
						.getId()));
				log.info("�豸���int_idΪ��"+dd.getId()+" ");
				
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
		str_code = null;		
		str_description = null;
		con = null;

	}

}
