package com.qm.mes.pm.service.exceptionrecord;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.AdapterService;
import com.qm.mes.framework.ExecuteResult;
import com.qm.mes.framework.IMessage;
import com.qm.mes.framework.ServiceException;
import com.qm.mes.framework.ServiceExceptionType;
import com.qm.mes.pm.bean.ExceptionRecord;
import com.qm.mes.pm.factory.ExceptionRecordFactory;
/**
 * �ر��쳣��¼
 * 
 * @author Xujia
 * 
 */
public class CloseExceptionRecord extends AdapterService {

	/**
	 * �������
	 */
	private Connection con = null;
	/**
	 * �ر�����
	 */
	private String str_rediscription = null;	
	/**
	 * ���
	 */
	private String int_id=null;
	/**
	 * �û���
	 */
	private String userid = null;
	//������ǰ
	ExceptionRecordFactory factory = new ExceptionRecordFactory();
    //��־
	private final Log log = LogFactory.getLog(CloseExceptionRecord.class);
	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
		str_rediscription = message.getUserParameterValue("str_rediscription");		
		int_id=message.getUserParameterValue("int_id");
		userid = message.getUserParameterValue("userid");
		
		//���log��Ϣ
	    String debug="�ر�������" + str_rediscription + "��"+ "�û��ţ�"+userid;
	    log.debug("�ر��쳣ʱ�û��ύ�Ĳ���: " + debug);

		if (str_rediscription == null || userid == null ) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
					processid, new java.util.Date(), null));
			log.fatal("�ر��������û�������Ϊ�ղ������˳�����");
			return false;
		}

		return true;
	}

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {
			    
			ExceptionRecord er = new ExceptionRecord();	
			er.setId(Integer.parseInt(int_id));
			er.setRediscription(str_rediscription);
			er.setCloseuser(new Integer(userid));	
			factory.colseExceptionRecord(er, con);
				log.debug( "�ر��쳣��¼�ɹ�");
				
		} catch (Exception e) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
					processid, new java.util.Date(), e));
			log.error( "���״̬����ʱ,δ֪�쳣" + e.toString());
			
			
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
	}

	@Override
	public void relesase() throws SQLException {
		str_rediscription = null;
		userid = null;
		int_id=null;
		con = null;

	}

}

