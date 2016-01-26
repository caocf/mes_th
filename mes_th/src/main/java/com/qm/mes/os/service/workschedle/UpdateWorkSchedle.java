package com.qm.mes.os.service.workschedle;

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
import com.qm.mes.os.bean.WorkSchedle;
import com.qm.mes.os.factory.WorkSchedleFactory;

/**���Ĺ���ʱ�̱�
 * @author baobao
 *
 */
public class UpdateWorkSchedle extends AdapterService{
	private final Log log = LogFactory.getLog(UpdateWorkSchedle.class);
		
	private Connection con=null;
	private String int_id=null;
	/**
	  * ������Ԫ ��-��
	  */
	private String str_produceunit1=null;
	/**
	  * ������Ԫ ��
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
	  * ����ʱ�� Сʱ������
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
	  * ��ǰ�� �죺Сʱ:����
	  */
	private String str_advanceTime=null;
	
	public boolean checkParameter(IMessage message, String processid) {
		con=(Connection)message.getOtherParameter("con");
		int_id=message.getUserParameterValue("int_id").trim();
		str_produceunit1=message.getUserParameterValue("str_produceunit").trim();
		String pp[];
		pp = str_produceunit1.split("-");
		str_produceunit=pp[0];
		str_workOrder=message.getUserParameterValue("str_workOrder").trim();
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
	    String debug="int_id:" +int_id 
		+ " str_produceunit1:"+str_produceunit1+ " " +
		"str_produceunit:"+str_produceunit+ " " +
		"str_workOrder:"+str_workOrder+ " " +
		"str_workSchedleH:"+str_workSchedleH+ " " +
		"str_workSchedleM:"+str_workSchedleM+ " " +
		"str_workSchedle:"+str_workSchedle+ " " +
		"str_advanceTimeD:"+str_advanceTimeD+ " " +
		"str_advanceTimeH:"+str_advanceTimeH+ " " +
		"str_advanceTimeM:"+str_advanceTimeM+ " " +
		"str_advanceTime:"+str_advanceTime+ "\n";
	    log.info("���״̬ת���Ĳ���: " + debug);
		if (str_produceunit == null) {
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
				WorkSchedle workschedle=new WorkSchedle();
				workschedle.setId(Integer.parseInt(int_id));
				workschedle.setProdunitid(Integer.parseInt(str_produceunit));
				workschedle.setWorkOrder(str_workOrder);
				workschedle.setWorkSchedle(str_workSchedle);
				workschedle.setAdvanceTime(str_advanceTime);
				WorkSchedleFactory factory=new WorkSchedleFactory();
				factory.updateWorkSchedle(workschedle, con);
				log.debug( "ɾ������ʱ�̱�ɹ�!");
				}catch (SQLException sqle) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
							.getId(), processid, new Date(), sqle));
					return ExecuteResult.fail;
				}
					
		}catch (Exception e) {
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
		 /**
		  * ������Ԫ
		  */
		str_produceunit1=null;
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
