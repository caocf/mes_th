package com.qm.mes.pm.service.device;

import java.sql.Connection;
import java.sql.ResultSet;
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
 * ɾ���豸��Ϣ
 * 
 * @author xujia
 * 
 */

public class DeleteDevice  extends AdapterService {
	/**
	 * �������
	 */
	private Connection con = null;
	/**
	 *   ����id
	 */
	private String int_id = null;
	DeviceFactory factory = new DeviceFactory();
	Statement stmt = null;
	ResultSet rs = null;
	
	private final Log log = LogFactory.getLog(DeleteDevice.class);

	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
		int_id = message.getUserParameterValue("int_id");
	
		if (int_id == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
					processid, new java.util.Date(), null));
			return false;
		}
		return true;
	}

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid) throws SQLException, Exception {
		try {
			try {			
				DAO_Device dao = (DAO_Device) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_Device.class);
		      stmt = con.createStatement();	
		     // ������쳣��¼���Ƿ��õ�
		     rs = stmt.executeQuery(dao.checkDeviceInrecordById(Integer
				.parseInt(int_id)));
		      int count3 = 0;
		      if (rs.next()) {
			count3 = rs.getInt(1);
		       }
		
		      //���õ�ʱ������ɾ��
				if (count3 > 0 ) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.UNKNOWN, "���豸���ã�����ɾ��!",
							this.getId(), processid, new Date(), null));
					log.fatal("���豸���ã�����ɾ��");
					return ExecuteResult.fail;
				} else {
				factory.delDeviceById((new Integer(int_id)), con);							
				log.debug("ɾ���豸�ɹ�!");
     		    //ɾ����4�豸�й���������				
				factory.delDevice_TypeByDid((new Integer(int_id)), con);
				factory.delDevice_UnitByDid((new Integer(int_id)), con);
				log.debug("ɾ���豸������ɹ���");
				
			}} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), processid, new Date(), sqle));
				log.error("ɾ���豸ʱ,δ֪�쳣" + sqle.toString());
				return ExecuteResult.fail;
			}
		} catch (Exception e) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
					processid, new java.util.Date(), e));
			log.error("ɾ���豸ʱ,δ֪�쳣" + e.toString());
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
    }

	@Override
	public void relesase() throws SQLException {
		int_id = null;
		con = null;

	}

}
