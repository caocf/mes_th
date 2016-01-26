package com.qm.mes.pm.service.exceptionrecord;

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
import com.qm.mes.pm.bean.ExceptionRecord;
import com.qm.mes.pm.dao.DAO_Device;
import com.qm.mes.pm.factory.ExceptionRecordFactory;
import com.qm.mes.system.dao.DAOFactoryAdapter;
/**
 * �����쳣��¼
 * 
 * @author Xujia
 * 
 */
public class UpdateExceptionRecord extends AdapterService {
	private final Log log = LogFactory.getLog(UpdateExceptionRecord.class);
	/**
	 * �������
	 */
	private Connection con = null;
	/**
	 *��������Ԫ 
	 */
	private String int_produnitid = null;
	/**
	 *������ 
	 */
	private String str_description = null;	
	/**
	 * �쳣ԭ��
	 */
	private String int_exceptioncause = null;
	/**
	 *���豸 
	 */
	private String int_device = null;
	/**
	 * ���豸����
	 */
	private String devicetype = null;
	/**
	 *����� 
	 */
	private String int_id=null;
	private int devicetypeid=0;
	private int deviceid=0;
	/**
	 * ���û���
	 */
	private String userid = null;
	/**
	 * �豸����
	 */
	private String int_exceptiontype = null;
	Statement stmt = null;	
	
	ExceptionRecordFactory factory = new ExceptionRecordFactory();
	//��־
	
	@Override
	public boolean checkParameter(IMessage message, String processid) {
		devicetypeid=0;
		deviceid=0;		
		final Log log = LogFactory.getLog(UpdateExceptionRecord.class);
		con = (Connection) message.getOtherParameter("con");
		int_produnitid = message.getUserParameterValue("int_produnitid");
		int_exceptioncause= message.getUserParameterValue("int_exceptioncause");		
		str_description = message.getUserParameterValue("str_description");
		int_device = message.getUserParameterValue("int_device");
		devicetype= message.getUserParameterValue("devicetype");
		//devicetypeid=devicetype=="0"?0:(Integer.parseInt(devicetype));
		userid = message.getUserParameterValue("userid");
		int_exceptiontype = message.getUserParameterValue("int_exceptiontype");
		int_id=message.getUserParameterValue("int_id");
		int_device = int_device==null?"":int_device;
		devicetype = devicetype==null?"":devicetype;
		
		//���log��Ϣ
	    String debug=" ������Ԫ ��" + int_produnitid + "��"+ "������"+str_description+ ";"
		+"�豸�ţ�"+int_device;
	    log.debug("�����쳣��¼�û��ύ�Ĳ���: " + debug);

		if (int_produnitid == null || str_description == null 	|| int_exceptioncause == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
					processid, new java.util.Date(), null));
			log.fatal("������Ԫ���������쳣ԭ������Ϊ�ղ������˳�����");
			return false;
		}
		return true;
	}

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {
			try {
				
				
				stmt = con.createStatement();	
				 DAO_Device dao_device = (DAO_Device) DAOFactoryAdapter.getInstance(
							DataBaseType.getDataBaseType(con), DAO_Device.class);
				 String sql=dao_device.getTypeidByname(devicetype);					
					Statement stat1=con.createStatement();
					log.debug("��ѯtypeid��"+sql);
					ResultSet type = stat1.executeQuery(sql);					
					while (type.next())
					{	devicetypeid=type.getInt(1);						
					}				
					log.info("*�豸���ͺ�*:"+devicetypeid);
					
				String sql2=dao_device.getDeviceidBytype(int_device, devicetypeid);
				Statement stat2=con.createStatement();
				log.debug("��ѯdeviceid��"+sql2);
				ResultSet device = stat2.executeQuery(sql2);				
				while (device.next())
				{	deviceid=device.getInt(1);				
				}
				log.info("*�豸��*:"+deviceid);
				
				ExceptionRecord er = new ExceptionRecord();					
				er.setProduceUnitId(new Integer(int_produnitid));
				er.setDescription(str_description);
				er.setExceptionCauseId(new Integer(int_exceptioncause));	
				er.setExceptionTypeId(new Integer(int_exceptiontype));
				er.setUserId(new Integer(userid));
				er.setDevicetypeid(devicetypeid);
				er.setDeviceid(new Integer(deviceid));
				er.setId(new Integer(int_id));
				factory.updateExceptionRecord(er, con);
				log.info("�����쳣��¼����ɹ���");
				
				
				
			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), processid, new Date(), sqle));
				log.error("���ݿ��쳣���жϷ���");
				return ExecuteResult.fail;
			}
			finally {
				if (stmt != null)
			stmt.close();
				if (con != null)
			con.close();
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
		int_produnitid = null;
		int_exceptioncause = null;		
		str_description = null;
		con = null;
		int_device = null;
		devicetype = null;
		userid = null;
		
	}

}
