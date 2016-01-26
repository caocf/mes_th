package com.qm.mes.tg.service.noPedigreeRecord;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.AdapterService;
import com.qm.mes.framework.ExecuteResult;
import com.qm.mes.framework.IMessage;
import com.qm.mes.framework.ServiceException;
import com.qm.mes.framework.ServiceExceptionType;
import com.qm.mes.tg.bean.NoPedigreeRecord;
import com.qm.mes.tg.factory.NoPedigreeRecordFactory;
import com.qm.mes.util.SerializeAdapter;
/**
 * ����:л����
 * ���²ɼ��������ϵ�ֵ
 * @param GatherRecord
 * @param con
 * @throws SQLException
 * ��gatherRecord_updating.jsp ҳ���õ�
 * gatherRecord_editing.jsp ҳ���õ�
 */
public class UpDateNoPedigreeRecord extends AdapterService{
	private Connection con = null;
	// �����¼���id
	private String int_gatherrecord_id = null;
	// ����ϵ�������ֵ
	private String str_value = null;
	// ����ϵ���е�������
	private String str_gatherextname= null;
	//���Եĸ���
	private String attr_count = null;
	HashMap<String, String> attr_val = new HashMap<String, String>();
	SerializeAdapter sa = new SerializeAdapter();
	//��־
	private final Log log = LogFactory.getLog(UpDateNoPedigreeRecord.class);
	
	public boolean checkParameter(IMessage message, String processid) {

		con = (Connection) message.getOtherParameter("con");
		int_gatherrecord_id =message.getUserParameterValue("gatherrecordid");
		
		attr_count =message.getUserParameterValue("attr_count");
	
		try {
			attr_val=(HashMap<String, String>)sa.toObject(message.getUserParameterValue("str_attr_val2"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//���log��Ϣ
	    String debug="�����¼�ţ�" + int_gatherrecord_id + "��"+"�ɼ������Ը�����"+attr_count;
	    if(Integer.parseInt(attr_count)!=0)debug+=";�ɼ������ԣ�\n";
	    for(int j=1;j<=Integer.parseInt(attr_count);j++){
	    	debug+="����ϵ��¼ֵ��"+attr_val.get("str_value"+ j)+";";
	    	debug+="�ɼ�����չ��������"+attr_val.get("str_name"+ j);
	    	if(j!=Integer.parseInt(attr_count))debug+=";\n";
	    }
	    log.debug("��ӷ���ϵ��¼ʱ�û��ύ�Ĳ���: " + debug);
	
		
		if (attr_count==null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
					processid, new java.util.Date(), null));
			log.fatal("�����¼�š�����ϵ��¼��������Ϊ�ղ������˳�����");
			return false;
		}
		

		return true;
	}
	
	public ExecuteResult doAdapterService(IMessage message, String processid)
	throws SQLException, Exception {
try {
	try {
		for (int i = 1; i <= Integer.parseInt(attr_count); i++) {
		
			 NoPedigreeRecord  nopedigreerecord = new  NoPedigreeRecord();
			 nopedigreerecord.setId(new Integer(attr_val.get("int_"+i)));
			 nopedigreerecord.setValue(attr_val.get("str_value"+ i));
			 nopedigreerecord.setGatheritemextname(attr_val.get("str_name"+ i));
		     NoPedigreeRecordFactory factory = new  NoPedigreeRecordFactory();
		  if(nopedigreerecord.getValue()!=null&&!nopedigreerecord.getValue().equals("")){
		     factory.updateNoPedigreeRecord( nopedigreerecord , con);
		  }
			log.info("���·���ϵ��¼����ɹ���");
		}
	} catch (SQLException sqle) {
		message.addServiceException(new ServiceException(
				ServiceExceptionType.DATABASEERROR, "xie���ݿ�����쳣", this.getId(), processid, new Date(), sqle));
		log.error("���ݿ��쳣���жϷ���");
		return ExecuteResult.fail;
	}
} catch (Exception e) {
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
	    int_gatherrecord_id = null;
	    str_value = null;
	    str_gatherextname = null;
		
	}
}
