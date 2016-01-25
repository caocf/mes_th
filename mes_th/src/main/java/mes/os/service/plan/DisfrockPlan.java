package mes.os.service.plan;

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

import mes.os.factory.PlanFactory;

/**���ܣ������༭
 * @author л����
 *
 */
public class DisfrockPlan extends AdapterService {

	/**
	 * log ��־
	 */
	private final Log log = LogFactory.getLog(DisfrockPlan.class);
   
    /**
     * con  //���ݿ����Ӷ���
     */
    Connection con = null;
   /**
   * workOrder  // ���
  */
   private String workOrder=null;
   
   /**
   * overTime // ��������
   */
   private String overTime=null;
   
   /**
   * produceUnit // ������Ԫid
   */
  private String produceUnit=null;
  
   

	 public boolean checkParameter(IMessage message, String processid) {
			
	        con = (Connection) message.getOtherParameter("con");
	        workOrder=message.getUserParameterValue("workOrder");
	        overTime=message.getUserParameterValue("overTime");
	        produceUnit=message.getUserParameterValue("produceUnit");
	      //���log��Ϣ
		    String debug="workOrder:" +workOrder 
			+ " overTime:"+overTime+ " " +
			"produceUnit:"+produceUnit+ "\n";
		    log.info("���״̬ת���Ĳ���: " + debug);

	        if(produceUnit==null){
	        	   message.addServiceException(new ServiceException(
	   					ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
	   					processid, new java.util.Date(), null));
	   			return false;
	           }
	        

	        return true;
	    }
	 public ExecuteResult doAdapterService(IMessage message, String processid) throws SQLException, Exception {
	        try {
				try {
					// �����ƻ��༭
					PlanFactory factory=new PlanFactory();
					factory.disfrockplan(overTime, Integer.parseInt(produceUnit),  workOrder, con);
					
		          
					 log.debug( "�����ɹ�!");
				} catch (SQLException sqle) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
									.getId(), processid, new Date(), sqle));
					return ExecuteResult.fail;
				}
			} catch (Exception e) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
						processid, new java.util.Date(), e));
				return ExecuteResult.fail;
			}
			return ExecuteResult.sucess;
			
			
			}
	 
	 
	 public void relesase() throws SQLException {
		   con = null;
		   workOrder=null;
		   overTime=null;
		   produceUnit=null;
		 
		  
	    }
}
