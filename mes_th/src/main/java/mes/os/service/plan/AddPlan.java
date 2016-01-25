package mes.os.service.plan;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import mes.framework.AdapterService;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import mes.os.factory.PlanFactory;
import mes.os.bean.*;
/**
 * ���һ���ƻ�
 * �ں���checkParameter�мƻ�˳��Ų���Ϊ�ա�
 * @author л����
 * 
 */
public class AddPlan extends AdapterService {
	
	/**
	 * log ��־
	 */
	private final Log log = LogFactory.getLog(AddPlan.class);
   
    /**
     * con  //���ݿ����Ӷ���
     */
    Connection con = null;
   
    /**
     * workOrder  //�ƻ�˳���
     */
    private String workOrder=null;
   
    /**
     * produceDate  // ��������
     */ 
    private String produceDate=null;
   
    /**
     * produnitid  // ������Ԫid
     */
    private String produnitid=null;
   
    /**
     * workTeam  // ����
     */
    private String workTeam=null;
   
    /**
     * planOrder  // �ƻ�˳���
     */
    private String planOrder=null;
    
    /**
     * planDate // �ƻ�����
     */
    private String planDate=null;
    
	/**
	 * description // ����
	 */
	private String description=null;
	
	/**
	 * planGroupId // �ƻ����κ�
	 */
	private String planGroupId=null;
	
	/**
	 * produceType // ��Ʒ����ʶ
	 */ 
	private String produceType=null;
	
	/**
	 * produceName // ��Ʒ�� 
	 */
	private String produceName=null;
	
	/**
	 * count // ����
	 */
	private String count=null;
	
	/**
	 * produceMarker // ��Ʒ��ʶ
	 */ 
	private String produceMarker=null;
	
 
	 public boolean checkParameter(IMessage message, String processid) {
			
	        con = (Connection) message.getOtherParameter("con");
	        workOrder=message.getUserParameterValue("workOrder").trim();
	        produceDate=message.getUserParameterValue("produceDate").trim();
	        produnitid=message.getUserParameterValue("produnitid").trim();
	        workTeam=message.getUserParameterValue("workTeam").trim();
	        planOrder=message.getUserParameterValue("planOrder").trim();
	        planDate=message.getUserParameterValue("planDate").trim();
	        description=message.getUserParameterValue("description").trim();
			planGroupId=message.getUserParameterValue("planGroupId").trim();
			produceType=message.getUserParameterValue("produceType").trim();
			produceName=message.getUserParameterValue("produceName").trim();
			count=message.getUserParameterValue("count").trim();
			produceMarker=message.getUserParameterValue("produceMarker").trim();
			//���log��Ϣ
		    String debug="workOrder:" +workOrder 
			+ " produceDate:"+produceDate+ " " +
			"produnitid:"+produnitid+ " " +
			"workTeam:"+workTeam+ " " +
			"planOrder:"+planOrder+ " " +
			"planDate:"+planDate+ " " +
			"description:"+description+ " " +
			"planGroupId:"+planGroupId+ " " +
			"produceType:"+produceType+ " " +
			"produceName:"+produceName+ " " +
			"count:"+count+ " " +
			"produceMarker:"+produceMarker+ "\n";
		    log.info("���״̬ת���Ĳ���: " + debug);
	        if(planOrder==null){
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
					// �����ƻ�
					 PlanFactory factory=new PlanFactory();
					 Plan plan =new Plan();
				
					 plan.setPlanDate((new SimpleDateFormat("yyyy-MM-dd")).parse(planDate));
		             plan.setProduceDate((new SimpleDateFormat("yyyy-MM-dd")).parse(produceDate));
		             plan.setOrderFormId("");
		             plan.setPlanGroupId(Integer.parseInt(planGroupId));
		             plan.setProduceType(produceType);
		             plan.setProduceName(produceName);
		             plan.setProduceMarker(produceMarker);
		             plan.setProdunitid(Integer.parseInt(produnitid));
		             plan.setWorkTeam(workTeam);
		             plan.setWorkOrder(workOrder);
		             plan.setAmount(Integer.parseInt(count));
		             plan.setVersioncode("temp");
		             plan.setUpload(0);
		             plan.setPlanOrder(Integer.parseInt(planOrder));
		             plan.setDescription(description);
		             factory.savePlan(plan, con);
		             log.debug( "��Ӽƻ��ɹ�!");
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
		     produceDate=null;
		     produnitid=null;
		     planOrder=null;
		     planDate=null;
		     description=null;
			 planGroupId=null;
			 produceType=null;
			 produceName=null;
			 count=null;
		     produceMarker=null;
	    }

}
