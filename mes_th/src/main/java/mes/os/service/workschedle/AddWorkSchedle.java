package mes.os.service.workschedle;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import mes.os.bean.*;
import mes.os.factory.*;
import mes.framework.AdapterService;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;


/**��ӹ���ʱ�̱�
 * @author ������
 *
 */
public class AddWorkSchedle extends AdapterService {
	private final Log log = LogFactory.getLog(AddWorkSchedle.class);
	private Connection con=null;
	/**
	  * ������Ԫ
	  */
	private String str_produceunit=null;
	/**
	  * ���
	  */
	private String str_workOrder=null;
	/**
	  * ����ʱ�� Сʱ
	  */
	private String str_workSchedleH=null;
	/**
	  * ����ʱ�� ����
	  */
	private String str_workSchedleM=null;
	/**
	  * ����ʱ�� Сʱ:����
	  */
	private String str_workSchedle=null;
	/**
	  * ��ǰ�� ��
	  */
	private String str_advanceTimeD=null;
	/**
	  * ��ǰ�� Сʱ
	  */
	private String str_advanceTimeH=null;
	/**
	  * ��ǰ�� ����
	  */
	private String str_advanceTimeM=null;
	/**
	  * ��ǰ�� �죺Сʱ������
	  */
	private String str_advanceTime=null;
	
	public boolean checkParameter(IMessage message, String processid) {
			
			con = (Connection) message.getOtherParameter("con");
			str_produceunit=message.getUserParameterValue("str_produceunit").trim();
			str_workOrder=message.getUserParameterValue("workOrder").trim();
			str_workSchedleH=message.getUserParameterValue("str_workSchedleH").trim();
			str_workSchedleM=message.getUserParameterValue("str_workSchedleM").trim();
			str_workSchedle=str_workSchedleH+":"+str_workSchedleM;
			str_advanceTimeD=message.getUserParameterValue("str_advanceTimeD").trim();
			str_advanceTimeH=message.getUserParameterValue("str_advanceTimeH").trim();
			str_advanceTimeM=message.getUserParameterValue("str_advanceTimeM").trim();
			str_advanceTime=str_advanceTimeD+":"+str_advanceTimeH+":"+str_advanceTimeM;
			/**
			  * ���log��Ϣ
			  */
		    String debug="str_produceunit:" +str_produceunit 
			+ " str_workOrder:"+str_workOrder+ " " +
			"str_workSchedleH:"+str_workSchedleH+ " " +
			"str_workSchedleM:"+str_workSchedleM+ " " +
			"str_workSchedle:"+str_workSchedle+ " " +
			"str_advanceTimeD:"+str_advanceTimeD+ " " +
			"str_advanceTimeH:"+str_advanceTimeH+ " " +
			"str_advanceTimeM:"+str_advanceTimeM+ " " +
			"str_advanceTime:"+str_advanceTime+ "\n";
		    log.info("���״̬ת���Ĳ���: " + debug);
			if (str_produceunit== null) {
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
			
			WorkSchedle workschedle=new WorkSchedle();
			workschedle.setProdunitid(Integer.parseInt(str_produceunit));
			workschedle.setWorkOrder(str_workOrder);     
			workschedle.setWorkSchedle(str_workSchedle);
			workschedle.setAdvanceTime(str_advanceTime);	
			WorkSchedleFactory f=new WorkSchedleFactory();
			f.saveWorkSchedle(workschedle, con);
			log.debug( "��ӹ���ʱ�̱�ɹ�!");
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
	    /**
		  * ������Ԫ
		  */
	    str_produceunit=null;
	    /**
		  * ���
		  */
	    str_workOrder=null;
	    /**
		  * ����ʱ�� Сʱ
		  */
	    str_workSchedleH=null;
	    /**
		  * ����ʱ�� ����
		  */
	    str_workSchedleM=null;
	    /**
		  * ����ʱ�� Сʱ������
		  */
	    str_workSchedle=null;
	    /**
		  * ��ǰ�� ��
		  */
	    str_advanceTimeD=null;
	    /**
		  * ��ǰ�� Сʱ
		  */
	    str_advanceTimeH=null;
	    /**
		  * ��ǰ�� ����
		  */
	    str_advanceTimeM=null;
	    /**
		  * ��ǰ�� �죺Сʱ������
		  */
	    str_advanceTime=null;
    }
	
	
	
	
}
