package mes.os.service.workschedle;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import mes.framework.AdapterService;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import mes.os.factory.*;

/**ɾ������ʱ�̱�
 * @author ������
 *
 */

public class DeleteWorkSchedle extends AdapterService {
	 
	/**
	 * log ��־
	 */
	private final Log log = LogFactory.getLog(DeleteWorkSchedle.class);
	/**
	 *con ���ݿ�����
	 */
		private Connection con=null;
		/**
		 *int_id ����ʱ�̱�id
		 */
		private String int_id=null;
		public boolean checkParameter(IMessage message, String processid) {
				con = (Connection) message.getOtherParameter("con");
				int_id=message.getUserParameterValue("int_id");
				/**
				  * ���log��Ϣ
				  */
			    String debug="int_id:" +int_id +"\n";
			    log.info("���״̬ת���Ĳ���: " + debug);
				if (int_id== null) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
							processid, new java.util.Date(), null));
					return false;
				}
				return true;
		 }
		 
		public ExecuteResult doAdapterService(IMessage message, String processid)
		throws SQLException, Exception {
			try {
				try {
					WorkSchedleFactory factory=new WorkSchedleFactory();
					factory.deleteWorkSchedleById(Integer.parseInt(int_id), con);
					log.debug( "ɾ������ʱ�̱�ɹ�!");
				}catch(SQLException sqle){
					message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
						.getId(), processid, new Date(), sqle));
					return ExecuteResult.fail;
				}
			}catch(Exception e){
				message.addServiceException(new ServiceException(
						ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
						processid, new java.util.Date(), e));
				return ExecuteResult.fail;
			}
			return ExecuteResult.sucess;
	}
		 
		 @Override
		 public void relesase() throws SQLException {
			 con = null;
		     int_id=null;
		   	
		 }
		 
		 
		 
		 
	}

	
	
	
	
	


