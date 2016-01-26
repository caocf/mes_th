package com.qm.mes.ra.service.WorkTO;

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
import com.qm.mes.ra.bean.WorkTO;
import com.qm.mes.ra.factory.WorkTOFactory;

/**��Ӱ�ι���
 * @author ������
 *
 */
public class AddWorkTO extends AdapterService{
	private Connection con=null;
	/**
	  * ������Ԫ
	  */
	private String str_produceunit=null;
	
	/**
	  * ���
	  */
	private String str_workOrder=null;
	
	private final Log log = LogFactory.getLog(AddWorkTO.class);
	
	public boolean checkParameter(IMessage message, String processid) {
			
			con = (Connection) message.getOtherParameter("con");
			str_produceunit=message.getUserParameterValue("str_produceunit").trim();
			
			str_workOrder=message.getUserParameterValue("str_workOrder").trim();
			
			/**
			  * ���log��Ϣ
			  */
		    String debug="������Ԫ����" + str_produceunit + "��"
			+ "��Σ�"+str_workOrder;
		    
		    log.debug("��Ӱ�μ�¼ʱ�û��ύ�Ĳ���: " + debug);
			
			if (str_produceunit== null) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
						processid, new java.util.Date(), null));
				log.fatal("������Ԫ�����������Ϊ�ղ������˳�����");
				return false;
			}
			return true;	
	     }
	
	public ExecuteResult doAdapterService(IMessage message, String processid)
	throws SQLException, Exception {
		try {
			WorkTO workto=new WorkTO();
			workto.setProdunitid(Integer.parseInt(str_produceunit));
			
			workto.setWorkOrder(str_workOrder);     
				
			WorkTOFactory f=new WorkTOFactory();
			f.saveWorkTO(workto, con);
			log.info("��Ӱ�μ�¼�ɹ���");
			
		 }
	    catch (SQLException sqle) {
		message.addServiceException(new ServiceException(
				ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
						.getId(), processid, new Date(), sqle));
		log.error("���ݿ��쳣���жϷ���");
		return ExecuteResult.fail;
	    }
	     catch (Exception e) {
	    message.addServiceException(new ServiceException(
			ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
			processid, new java.util.Date(), e));
	    log.error("δ֪�쳣���жϷ���");
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
	    
    }
	
	
	
	
}
