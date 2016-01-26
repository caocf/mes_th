package com.qm.mes.tg.service.getherRecord;


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
import com.qm.mes.tg.bean.PedigreeRecord;
import com.qm.mes.tg.factory.RecordFactory;

/**
 * ����:л����
 * ���²ɼ��������ϵ�ֵ
 * @param GatherRecord
 * @param con
 * @throws SQLException
 * gatherRecord_editing.jsp ҳ���õ�
 */
public class Service_UpDatePedigreeRecord extends AdapterService{
	private Connection con = null;
	//��ϵ��¼���id
	private String PedigreeRecord_id;
	//������Ҫ�޸ĵ�ֵ
	private String value;

	
	String origid;
	String origvalue;
	String cause; 
	String userid;
	//��־
	private final Log log = LogFactory.getLog(Service_UpDatePedigreeRecord.class);
	
	public boolean checkParameter(IMessage message, String processid) {
		
		con = (Connection) message.getOtherParameter("con");
		PedigreeRecord_id = message.getUserParameterValue("PedigreeRecord_id");
		value = message.getUserParameterValue("value");
		 origid= message.getUserParameterValue("origid");
	     origvalue=message.getUserParameterValue("origvalue");
	     cause=message.getUserParameterValue("cause");
	     userid=message.getUserParameterValue("userid");
		  if(cause==null||cause.equals(""))
		  {
			  cause="��ԭ��";
		  }
		
		 if(value==null||value.equals(""))
		 {
			 value = message.getUserParameterValue("origvalue");
		 }
	
		if (PedigreeRecord_id == null || value == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
					processid, new java.util.Date(), null));
			log.fatal("������ϵ��¼--��ϵ��¼�š�����ֵ���в���Ϊ��");
			return false;
		}
		return true;
	}
	
	public ExecuteResult doAdapterService(IMessage message, String processid)
	throws SQLException, Exception {
try {
	try {
		PedigreeRecord PedigreeRecord = new PedigreeRecord ();
		PedigreeRecord .setId(new Integer(PedigreeRecord_id));
		PedigreeRecord.setMaterielValue(value);
		log.debug("��ϵ��¼�ţ�"+PedigreeRecord_id+"������ֵ��"+value);
		RecordFactory factory = new RecordFactory();
		factory.upDatePedigreeRecord(PedigreeRecord , con);
		log.info("������ϵ��¼�ɹ�");
		factory.savePEDIGREEHISTORY(Integer.parseInt(origid), origvalue, cause, userid, con);
		log.info("������ϵ��ʷ��¼�ɹ�");
	} catch (SQLException sqle) {
		message.addServiceException(new ServiceException(
				ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
						.getId(), processid, new Date(), sqle));
		log.info("���ݿ��쳣");
		return ExecuteResult.fail;
	}
} catch (Exception e) {
	message.addServiceException(new ServiceException(
			ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
			processid, new java.util.Date(), e));
	log.info("δ֪�쳣");
	return ExecuteResult.fail;
}
return ExecuteResult.sucess;
}
	
	
	
	
	public void relesase() throws SQLException {
		con = null;
		PedigreeRecord_id=null;
	    value=null;

	}
}
