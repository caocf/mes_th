package mes.os.service.mpsplan;

import mes.framework.AdapterService;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import mes.os.bean.*;
import mes.os.factory.*;

import java.text.*;
import java.sql.*;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**���ܣ��޸����ƻ�
 * @author л����
 *
 */
public class UpdateMPSPlan  extends AdapterService{
	
	/**
	 * log ��־
	 */
	private final Log log = LogFactory.getLog(UpdateMPSPlan.class);
     /**
	 * con 	// ��ȡ����
	 */
	private  Connection con=null;
	/**
	 * int_id  // ���ƻ�id
	 */
	private String int_id=null;
	
	/**
	 * startDate // �ƻ�����
	 */
	private String startDate=null;
	
	/**
	 * mpsUnit // mps��λ
	 */
	private String mpsUnit=null;
	
	/**
	 * materielName // ������
	 */
	private String materielName=null;
	
	/**
	 * planAmount // �ƻ�����
	 */
	private String planAmount=null;
	
	/**
	 * intendStorage // Ԥ�ƿ��
	 */
	private String intendStorage=null;
	/**
	 * planType // �ƻ�������
	 */
	private String planType=null;
	/**
	 * version // �汾
	 */
	private String version=null;
	/**
	 * contractcode // ��ͬ��
	 */
	private String contractcode=null;
	public boolean checkParameter(IMessage message, String processid) {
		con=(Connection)message.getOtherParameter("con");
		int_id=message.getUserParameterValue("int_id");
		startDate=message.getUserParameterValue("startDate");
		mpsUnit=message.getUserParameterValue("mpsUnit");
		materielName=message.getUserParameterValue("materielName");
		planAmount=message.getUserParameterValue("planAmount");
		intendStorage=message.getUserParameterValue("intendStorage");
		planType=message.getUserParameterValue("planType");
		version=message.getUserParameterValue("version");
		contractcode=message.getUserParameterValue("contractcode");
		
		//���log��Ϣ
	    String debug="int_id:" +int_id 
		+ " startDate:"+startDate+ " " +
		"mpsUnit:"+mpsUnit+ " " +
		"materielName:"+materielName+ " " +
		"planAmount:"+planAmount+ " " +
		"intendStorage:"+intendStorage+ " " +
		"planType:"+planType+ " " +
		"version:"+version+ " " +
		"contractcode:"+contractcode+ "\n";
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
			 // �������ƻ�ͨ�����ƻ�id
			 MPSPlan mpsplan=new MPSPlan();
			 mpsplan.setId((Integer.parseInt(int_id)));
             mpsplan.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(startDate));
             mpsplan.setMpsUnit(mpsUnit);
             mpsplan.setMaterielName(materielName);
             mpsplan.setPlanAmount(Integer.parseInt(planAmount));
             mpsplan.setIntendStorage(Integer.parseInt(intendStorage));
             mpsplan.setPlanType(planType);
             mpsplan.setVersion(version);
             mpsplan.setContractCode(contractcode);
             
			 MPSPlanFactory factory=new MPSPlanFactory ();
			 factory.updateMPSPlanbyid(mpsplan, con);
			 log.debug( "�������ƻ��ɹ�!");
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
    	  startDate=null;
    	  mpsUnit=null;
    	  materielName=null;
    	  planAmount=null;
    	  intendStorage=null;
    	  planType=null;
    	  version=null;
          contractcode=null;
   


     }

}
