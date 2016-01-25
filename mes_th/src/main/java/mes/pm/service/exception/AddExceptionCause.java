package mes.pm.service.exception;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import mes.framework.AdapterService;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import mes.pm.bean.ExceptionCause;
import mes.pm.factory.ExceptionFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ����쳣ԭ��
 * 
 * @author Xujia
 * 
 */

public class AddExceptionCause extends AdapterService{
	/**
	 * �������
	 */
	private Connection con = null;
	/**
	 * �쳣ԭ����
	 */
	private String str_name = null;
	/**
	 *   ״̬
	 */
	private String int_state = null;
	ExceptionFactory factory = new ExceptionFactory();
	
	
	//��־
	private final Log log = LogFactory.getLog(AddExceptionCause.class);
	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
		str_name = message.getUserParameterValue("str_name");
		int_state= message.getUserParameterValue("int_state");
		
		
		//���log��Ϣ
	    String debug="�쳣��������" + str_name + "��"+ "״̬��"+int_state;
	
	    log.debug("����쳣����ʱ�û��ύ�Ĳ���: " + debug);

		if (str_name == null || int_state == null ) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
					processid, new java.util.Date(), null));
			log.fatal("�쳣ԭ������״̬����Ϊ�ղ������˳�����");
			return false;
		}

		return true;
	}

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {
			try {
				ExceptionCause ec = new ExceptionCause();							
				ec.setName(str_name);
				ec.setState(new Integer(int_state));	
				factory.createExceptionCause(ec, con);
				log.info("����쳣ԭ�����ɹ���");
				
				
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
		con = null;

	}

}
