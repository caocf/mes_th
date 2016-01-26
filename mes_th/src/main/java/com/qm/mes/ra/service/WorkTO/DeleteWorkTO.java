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
import com.qm.mes.ra.factory.WorkTOFactory;

/**ɾ����ι���
 * @author ������
 *
 */
public class DeleteWorkTO extends AdapterService{
	private Connection con=null;
	private String int_id=null;
	/**
	  * ��־
	  */
	private final Log log = LogFactory.getLog(DeleteWorkTO.class);
	
	public boolean checkParameter(IMessage message, String processid) {
			con = (Connection) message.getOtherParameter("con");
			int_id=message.getUserParameterValue("int_id");
			String debug="ɾ����ţ�" + int_id;
		    log.debug("ɾ�������μ�¼ʱ�û��ύ�Ĳ���: " + debug);
			if (int_id== null) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
						processid, new java.util.Date(), null));
				log.fatal("���Ϊ�գ��˳�����");
				return false;
			}
			return true;
	 }
	 
	public ExecuteResult doAdapterService(IMessage message, String processid)
	throws SQLException, Exception {
		try {
			try {
				WorkTOFactory factory=new WorkTOFactory();
				factory.deleteWorkTOById(Integer.parseInt(int_id), con);
				log.info("��Ӱ����μ�¼����ɹ���");
	 
			}catch(SQLException sqle){
				message.addServiceException(new ServiceException(
					ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
					.getId(), processid, new Date(), sqle));
				log.error("���ݿ��쳣���жϷ���");
				return ExecuteResult.fail;
			}
		}catch(Exception e){
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
					processid, new java.util.Date(), e));
			log.error("δ֪�쳣���жϷ���");
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
}
	 
	 @Override
	 public void relesase() throws SQLException {
		 con = null;
	     int_id=null;
	   	
	 }
	 
	 
	 
	 
}








