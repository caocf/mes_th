package com.qm.mes.pm.service.exception;

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
import com.qm.mes.pm.bean.ExceptionType;
import com.qm.mes.pm.factory.ExceptionFactory;

/**
 * ����쳣����
 * 
 * @author Xujia
 * 
 */

public class AddExceptionType extends AdapterService{
	/**
	 *  �������
	 */
	private Connection con = null;
	/**
	 * �쳣������  
	 */
	private String str_name = null;
	/**
	 * ״̬
	 */
	private String int_state = null;	
	
	ExceptionFactory factory = new ExceptionFactory();
	
	//��־
	private final Log log = LogFactory.getLog(AddExceptionType.class);
	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
		str_name = message.getUserParameterValue("str_name");
		int_state= message.getUserParameterValue("int_state");
		//int_sysdefault = message.getUserParameterValue("int_sysdefault");		
		
		//���log��Ϣ
	    String debug="�쳣��������" + str_name + "��"+ "״̬��"+int_state;
	    log.debug("����쳣����ʱ�û��ύ�Ĳ���: " + debug);

		if (str_name == null || int_state == null ) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
					processid, new java.util.Date(), null));
			log.fatal("�쳣��������״̬��ϵͳ��ʾ����Ϊ�ղ������˳�����");
			return false;
		}

		return true;
	}

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {
			try {
				ExceptionType et = new ExceptionType();							
				et.setName(str_name);
				et.setState(new Integer(int_state));
				//et.setSysdefault(new Integer(int_sysdefault));				
				factory.createExceptionType(et, con);
				log.info("����쳣���ͷ���ɹ���");
				
				
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
		int_state = null;
		//int_sysdefault = null;		
		con = null;

	}

}
