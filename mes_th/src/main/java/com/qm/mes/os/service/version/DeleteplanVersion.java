package com.qm.mes.os.service.version;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.AdapterService;
import com.qm.mes.framework.ExecuteResult;
import com.qm.mes.framework.IMessage;
import com.qm.mes.framework.ServiceException;
import com.qm.mes.framework.ServiceExceptionType;
import com.qm.mes.os.bean.*;
import com.qm.mes.os.factory.*;
/**���ܣ�ɾ���ƻ��汾
 * @author л����
 *
 */
public class DeleteplanVersion extends AdapterService{
	
	/**
	 * log //��־
	 */
	private final Log log = LogFactory.getLog(DeleteplanVersion.class);
	
	/**
	 * con //���ݿ�����
	 */
	private Connection con=null;
	
	/**
	 * int_id//�汾��id
	 */
	private String int_id=null;
	 
	/**
	 * versionfactory  //�汾����
	 */
	private  VersionFactory versionfactory=new VersionFactory();
	 
	/**
	 * planfactory  //�ƻ�����
	 */
	private  PlanFactory planfactory=new PlanFactory();
	 public boolean checkParameter(IMessage message, String processid) {
		
		      con = (Connection) message.getOtherParameter("con");
			  int_id=message.getUserParameterValue("int_id");
			 
			  String debug="int_id:" +int_id+"\n";
		      log.info("���״̬ת���Ĳ���: " + debug);
			
		   if(int_id==null){
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
			 
			 Version version=new Version();
			  //ͨ���汾��idֵ �õ��汾�ŵ��ƻ�����ɾ���ƻ���Ϣ
			 version =versionfactory.getVersionbyid(Integer.parseInt(int_id), con);
			 String str_versioncode= version.getVersionCode();
			 planfactory.deletePlanbyversioncode(str_versioncode, con);
			 versionfactory.deleteversionbyid(Integer.parseInt(int_id), con);
			 log.debug( "ɾ���ƻ��汾�ɹ�!");
			 
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
	 
	 @Override
	   public void relesase() throws SQLException {
	   	    con = null;
	        int_id=null;
	    
	   	
	   }
}
