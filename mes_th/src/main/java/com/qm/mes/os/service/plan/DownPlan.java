package com.qm.mes.os.service.plan;


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
import com.qm.mes.os.factory.PlanFactory;


/**���ܣ�����������ҵ�ƻ�
 * @author л����
 *
 */
public class DownPlan extends AdapterService {
	 
	/**
	 * log ��־
	 */
	private final Log log = LogFactory.getLog(DownPlan.class);
   
    /**
     * con  //���ݿ����Ӷ���
     */
    Connection con = null;
  
    /**
     * downid ���뱻ѡ�мƻ����ڣ����������ƻ���id
     */
    private  int downid=0;
    //Ҫ������id��˳���
    /**
     * downorder  ����������˳���
     */
    private int downorder=0;
       
    /**
     * selectid (��ǰѡ�е�id)  ���������ƻ�id
     */
    private int selectid=0;
 
    /**
     * selectorder (��ǰѡ�мƻ�˳���) ����������˳���
     */
    private int selectorder=0;
 

	 public boolean checkParameter(IMessage message, String processid) {
	        con = (Connection) message.getOtherParameter("con");
	        downid=Integer.parseInt(message.getUserParameterValue("downid"));
	        downorder=Integer.parseInt(message.getUserParameterValue("downorder"));
	        selectid=Integer.parseInt(message.getUserParameterValue("selectid"));
	        selectorder=Integer.parseInt(message.getUserParameterValue("selectorder"));
	      //���log��Ϣ
		    String debug="downid:" +downid 
			+ " downorder:"+downorder+ " " +
			"selectid:"+selectid+ " " +
			"selectorder:"+selectorder+ "\n";
		    log.info("���״̬ת���Ĳ���: " + debug);
	        if(downid==0||downorder==0||selectid==0||selectorder==0){
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
					// ���Ƽƻ�
					PlanFactory factory=new PlanFactory();
					factory.down_or_upPlanOrderbyplanid(selectid, downorder, con);
					factory.down_or_upPlanOrderbyplanid(downid, selectorder, con);
					 log.debug( "���Ƴɹ�!");
	                
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
	        downid=0;
	        downorder=0;
	        selectid=0;
	        selectorder=0;
	    }
}
