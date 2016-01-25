package mes.pm.service.exception;

import java.sql.Connection;
import java.sql.SQLException;

import mes.framework.AdapterService;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import mes.pm.bean.ExceptionType;
import mes.pm.factory.ExceptionFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * �����쳣����
 * 
 * @author Xujia
 * 
 */

public class UpdateExceptionType extends AdapterService {
	/**
	 * ������� 
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
	/**
	 * ��� 
	 */
	private String int_id=null;
	/**
	 * ������ǰ
	 */
	ExceptionFactory factory = new ExceptionFactory();		
   //��־
	private final Log log = LogFactory.getLog(UpdateExceptionType.class);
	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
		str_name = message.getUserParameterValue("str_name");
		int_state= message.getUserParameterValue("int_state");
		//int_sysdefault = message.getUserParameterValue("int_sysdefault");
		int_id=message.getUserParameterValue("int_id");
		
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
			    
			ExceptionType et = new ExceptionType();	
			et.setId(Integer.parseInt(int_id));
			et.setName(str_name);
			et.setState(new Integer(int_state));
			//et.setSysdefault(new Integer(int_sysdefault));					
			factory.updateExceptionType(et, con);
				log.debug( "�����쳣���ͳɹ�");
				
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
		str_name = null;
		int_state = null;
		//int_sysdefault = null;
		int_id=null;
		con = null;

	}

}
