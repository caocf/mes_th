package mes.ra.service.WorkTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import mes.framework.AdapterService;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import mes.ra.bean.WorkTO;
import mes.ra.factory.WorkTOFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**���°�ι���
 * @author baobao
 *
 */
public class UpdateWorkTO extends AdapterService{
	private Connection con=null;
	private String int_id=null;
	/**
	  * ������Ԫ
	  */
	private String str_produceunit=null;
	/**
	  * ���
	  */
	private String str_workOrder=null;
	
	private final Log log = LogFactory.getLog(UpdateWorkTO.class);
	
	public boolean checkParameter(IMessage message, String processid) {
		con=(Connection)message.getOtherParameter("con");
		int_id=message.getUserParameterValue("int_id").trim();
		str_produceunit=message.getUserParameterValue("str_produceunit").trim();
		
		str_workOrder=message.getUserParameterValue("str_workOrder").trim();
		/**
		  *���log��Ϣ
		  */
	    String debug="��ţ�"+int_id+"������Ԫ����" + str_produceunit + "��"
		+ "��Σ�"+str_workOrder;
	  
	    log.debug("���°����μ�¼ʱ�û��ύ�Ĳ���: " + debug);
		
		if (str_produceunit == null) { 
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
					processid, new java.util.Date(), null));
			log.fatal("��š�������Ԫ�����������Ϊ�ղ������˳�����");
			return false;
		}
		return true;
	}
	
	public ExecuteResult doAdapterService(IMessage message, String processid)
	throws SQLException, Exception {
		try {	
			try {	
				WorkTO workto=new WorkTO();
				workto.setId(Integer.parseInt(int_id));
				workto.setProdunitid(Integer.parseInt(str_produceunit));
				
				workto.setWorkOrder(str_workOrder);
				WorkTOFactory factory=new WorkTOFactory();
				factory.updateWorkTO(workto, con);
				log.info("���°����μ�¼����ɹ���");
				}catch (SQLException sqle) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
							.getId(), processid, new Date(), sqle));
					log.error("���ݿ��쳣���жϷ���");
					return ExecuteResult.fail;
				}
					
		}catch (Exception e) {
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
		int_id=null;
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
