package com.qm.mes.ra.service.instruction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.sql.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.AdapterService;
import com.qm.mes.framework.ExecuteResult;
import com.qm.mes.framework.IMessage;
import com.qm.mes.framework.ServiceException;
import com.qm.mes.framework.ServiceExceptionType;
import com.qm.mes.ra.dao.*;
import com.qm.mes.ra.factory.*;
/**����ָ��״̬
 * author : л����
 */
public class UpdateInstructState  extends AdapterService{
	private Connection con = null;
	/**
	 * ����״̬����
	 */
	private String startstate=null;
	/**
	 * ǿ������״̬����
	 */
	private String powerstate=null;
	/**
	 * �����ϵ�ֵҲ����ָ����еĲ�Ʒ��ʶ
	 */
	private String str_produceMarker=null;
	/**
	 * �û���id
	 */
	private String  userId=null;
	/**
	 * �ɼ����id
	 */
	private String INT_GATHERID=null;
    /**
     * ������Ԫ��id
     */
    private String produnitid=null;
	/**
	 * ��־
	 */
	private final Log log = LogFactory.getLog(UpdateInstructState.class);
	
	@Override
	public boolean checkParameter(IMessage message, String processid) {
		
		con = (Connection) message.getOtherParameter("con");
		startstate= message.getUserParameterValue("startstate");
		powerstate=message.getUserParameterValue("powerstate");
		produnitid=message.getUserParameterValue("produnitid");
		str_produceMarker=message.getUserParameterValue("str_produceMarker");
		userId=message.getUserParameterValue("userId");
		INT_GATHERID=message.getUserParameterValue("gatherId");
		log.debug("�Ƿ�����״̬����"+startstate+"���Ƿ�ǿ������״̬����"+powerstate+"��������Ԫ�ţ�"+produnitid+
				 "����Ʒ��ʶ��"+str_produceMarker+"���û���"+userId+"���ɼ���ţ�"+INT_GATHERID);
		if (str_produceMarker==null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
					processid, new java.util.Date(), null));
			log.fatal("��Ʒ��ʶΪ��");
			return false;
		}
		

		return true;
	}

	public ExecuteResult doAdapterService(IMessage message, String processid)
	throws SQLException, Exception {
try {
	try {
		Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		//��startstate=0 ʲô������
		//��Ϊҳ���ж��˷���ǿ��ת��������һ����������ϵ�
		if(Integer.parseInt(startstate)==1){
			InstructionFactory factory=new InstructionFactory();
		    //�鿴��ǰָ���������״̬id
		    int Int_instrucsttateid= factory.checkstr_produceMarker(str_produceMarker,Integer.parseInt(produnitid), con);
		    DAO_State state=new DAO_StateForOracle();
		    //ͨ���ɼ����id����ѯ��ɼ�����йص�״̬��id��
	        String statesql=state.getstateIdbygatherid(new Integer(INT_GATHERID));
	        log.debug("ͨ���ɼ���Ų�ѯ״̬��SQL��䣺"+statesql);
		    ResultSet stateConversionrs= stmt.executeQuery(statesql);
		    int m=0;
		    //����Ĭ�ϵ�ִ��
		    while(stateConversionrs.next()){
		    	if(stateConversionrs.getInt("defaultexcute")==1)
				{          
		    		//�����ϵ�ǰ��״̬����ת��ǰ�ĳɹ�
					if(Int_instrucsttateid==stateConversionrs.getInt("int_fromstate"))
					{
						m++;
						factory.updateInstructState(stateConversionrs.getInt("int_tostate"), 0, str_produceMarker,Integer.parseInt(produnitid), con);
						log.info("����ָ��״̬�ɹ�");
					 }
		         }
		    }
			//���Ĭ�ϵĲ�������������
		    if(m==0){
		    	stateConversionrs.beforeFirst();
		    	while(stateConversionrs.next()){ 
		    		if(Int_instrucsttateid==stateConversionrs.getInt("int_fromstate"))
		    		{
		    			m++;
		    			if(m==1){
		    				factory.updateInstructState(stateConversionrs.getInt("int_tostate"), 0, str_produceMarker, Integer.parseInt(produnitid),con);
		    				log.info("����ָ��״̬�ɹ�");
		    			}
					}   
		        }
		    	//�����û�з��ϵľͰ�Ĭ�ϵ�ת��ͬʱ��¼�쳣
		    	if(m==0){
		    		stateConversionrs.beforeFirst();
		    		while(stateConversionrs.next()){ 
		    			if(stateConversionrs.getInt("defaultexcute")==1)
						{
		    				//����ָ��״̬ͬʱ��¼�쳣
				    		factory.updateInstructState(stateConversionrs.getInt("int_tostate"), 1, str_produceMarker,Integer.parseInt(produnitid), con);
				    		log.info("����ָ��״̬�ɹ�");
				    		//ͬʱ���쳣��ָ��ŵ�ָ���쳣����
				    		factory.savet_ra_Instructionexception(Int_instrucsttateid, Integer.parseInt(userId), str_produceMarker, Integer.parseInt(INT_GATHERID), con);
				    		log.info("����ָ���쳣�ɹ�");
				    	}   
		    		}
		    	}
		    }
		    if(stmt!=null){
		    	stmt.close();
		    }
		}

	} catch (SQLException sqle) {
		message.addServiceException(new ServiceException(
				ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
						.getId(), processid, new Date(), sqle));
		log.error("���ݿ��쳣");
		return ExecuteResult.fail;
	}
} catch (Exception e) {
	message.addServiceException(new ServiceException(
			ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
			processid, new java.util.Date(), e));
	log.error("δ֪�쳣");
	return ExecuteResult.fail;
}
return ExecuteResult.sucess;
}

@Override
public void relesase() throws SQLException {
	 con = null;
	 startstate=null;
	 powerstate=null;

	
}


}
