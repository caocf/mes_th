package com.qm.mes.os.service.plan;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.AdapterService;
import com.qm.mes.framework.ExecuteResult;
import com.qm.mes.framework.IMessage;
import com.qm.mes.framework.ServiceException;
import com.qm.mes.framework.ServiceExceptionType;
import com.qm.mes.os.bean.*;
import com.qm.mes.os.factory.*;
/**����:�޸ı༭ҳ��ļƻ�
 * @author л����
 *
 */
public class UpdatePlan extends AdapterService{

	/**
	 * log ��־
	 */
	private final Log log = LogFactory.getLog(UpdatePlan.class);
	
	/**
	 * con ���ݿ�����
	 */
	private  Connection con=null;
	 
	/**
	 * int_id  //�ƻ���id
	 */
	private String int_id=null;
	 
	/**
	 * description  //������Ϣ
	 */
	private String description=null;
	 
	/**
	 * planGroupId  //�ƻ����κ�
	 */
	private String planGroupId=null;
	 
	/**
	 * produceType  //��Ʒ����ʶ
	 */
	private String produceType=null;
	
	/**
	 * produceName   //��Ʒ����
	 */
	private String produceName=null;
	 
	/**
	 * count  //����
	 */
	private String count=null;
	 
	/**
	 * planDate  //�ƻ�����
	 */
	private String planDate=null;
	  
	/**
	 * produceMarker //��Ʒ��ʶ
	 */
	private String produceMarker=null;
	  
	/**
	 * planOrder //�ƻ�˳���
	 */
	private String planOrder=null;
	 
	/**
	 * workTeam  //����
	 */
	private String workTeam=null;
	public boolean checkParameter(IMessage message, String processid) {
		con=(Connection)message.getOtherParameter("con");
		int_id=message.getUserParameterValue("int_id").trim();
		description=message.getUserParameterValue("description").trim();
		planGroupId=message.getUserParameterValue("planGroupId").trim();
		produceType=message.getUserParameterValue("produceType").trim();
		produceName=message.getUserParameterValue("produceName").trim();
		count=message.getUserParameterValue("count").trim();
		planDate=message.getUserParameterValue("planDate").trim();
		produceMarker=message.getUserParameterValue("produceMarker").trim();
		planOrder=message.getUserParameterValue("planOrder").trim();
		workTeam=message.getUserParameterValue("workTeam").trim();
		//���log��Ϣ
	    String debug="int_id:" +int_id 
		+ " description:"+description+ " " +
		"planGroupId:"+planGroupId+ " " +
		"produceType:"+produceType+ " " +
		"produceName:"+produceName+ " " +
		"count:"+count+ " " +
		"planDate:"+planDate+ " " +
		"produceMarker:"+produceMarker+ " " +
		"planOrder:"+planOrder+ " " +
		"workTeam:"+workTeam+ "\n";
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
			  //���¼ƻ�
			 Plan plan=new Plan();
			 
			 plan.setPlanDate((new SimpleDateFormat("yyyy-MM-dd")).parse(planDate));
			 plan.setPlanOrder(Integer.parseInt(planOrder));
             plan.setOrderFormId("");
             plan.setPlanGroupId(Integer.parseInt(planGroupId));
             plan.setProduceType(produceType);
             plan.setProduceName(produceName);
             plan.setAmount(Integer.parseInt(count));
             plan.setId(Integer.parseInt(int_id));
             plan.setDescription(description);
             plan.setProduceMarker(produceMarker);
             plan.setWorkTeam(workTeam);
			
             PlanFactory planfactory=new PlanFactory();
			 planfactory.updatePlanbyid(plan, con);
			log.debug( "���¼ƻ��ɹ�!");
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
	        int_id=null;
	        description=null;
	      	planGroupId=null;
	        produceType=null;
	      	produceName=null;
	      	count=null;
	      	planDate=null;
	     }
}
