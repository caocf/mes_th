package mes.os.service.plan;

import mes.framework.AdapterService;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import java.util.*;
import java.sql.*;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import mes.os.factory.*;
import mes.os.bean.*;
/**���ܣ����ɱ༭�ռ�
 * @author л����
 *
 */
public class ProducePlanSpace extends AdapterService {

	/**
	 * log ��־
	 */
	private final Log log = LogFactory.getLog(ProducePlanSpace.class);

	/**
	 * con �������ݿ�
	 */
	private Connection con=null;
	
	/**
	 * produnitid // ������Ԫ
	 */
	private String produnitid =null;
	
	/**
	 * planfactory // �ƻ�����
	 */
	private PlanFactory planfactory=new PlanFactory();
	
	/**
	 * overtime // ��������
	 */
	private String overtime=null;
	
	/**
	 * workOrder // ���
	 */
	private String workOrder=null;

	public boolean checkParameter(IMessage message, String processid) {
		con=(Connection)message.getOtherParameter("con");
		produnitid=message.getUserParameterValue("int_id");
		overtime=message.getUserParameterValue("overtime");
		workOrder=message.getUserParameterValue("workOrder");
		//���log��Ϣ
	    String debug="produnitid:" +produnitid 
		+ " overtime:"+overtime+ " " +
		"workOrder:"+workOrder+ "\n";
	    log.info("���״̬ת���Ĳ���: " + debug);
		if (produnitid== null) {
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
			List<Plan>  listplan=new ArrayList<Plan>();
			listplan=planfactory.getPlanbyProdateProidWorder(overtime, Integer.parseInt(produnitid), workOrder, con);
			// �������������ں�������Ԫ��εļƻ���copy�����µļƻ�
			 Iterator<Plan> iter=listplan.iterator();
			// �����汾��Ϊtemp�ı༭�ƻ�
		      while(iter.hasNext()){
		    	  Plan plan=(Plan)iter.next();
		    	  plan.setUpload(0);
		    	  plan.setOrderFormId(" ");
		    	  plan.setVersioncode("temp");//admin Ϊ�汾��
		    	  planfactory.savePlan(plan, con);
		      }
		      log.debug( "�ƻ�����ɹ�!");
	    }
	    catch (SQLException sqle) {
		message.addServiceException(new ServiceException(
				ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
						.getId(), processid, new Date(), sqle));
		return ExecuteResult.fail;
	    }
	     catch (Exception e) {
	    message.addServiceException(new ServiceException(
			ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
			processid, new java.util.Date(), e));
	      return ExecuteResult.fail;
	    }
	    return ExecuteResult.sucess;
	   }

    public void relesase() throws SQLException {
        con = null;
        produnitid =null;
    	overtime=null;
        workOrder=null;
      
   

     }
	
	
	
}
