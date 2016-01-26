package com.qm.mes.pm.service.datarule;

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
import com.qm.mes.pm.bean.DataRule;
import com.qm.mes.pm.bean.DataRuleArg;
import com.qm.mes.pm.factory.DataRuleFactory;
import com.qm.mes.util.SerializeAdapter;
/**
 * �������ݹ���
 * @author Xujia
 *
 */
public class UpdateDataRule  extends AdapterService {
	/**
	 * �������
	 */
	private Connection con = null;
	/**
	 *  ���ݹ�����   
	 */
	private String str_name = null;
	/**
	 *  ������
	 */
	private String str_code = null;
	/**
	 * ����ʽ
	 */
	private String string_rule = null;
	/**
	 * ��ʽ����
	 */
	private String str_description = null;
	/**
	 * int_id
	 */
	private String int_id=null;
    /**
     * ��������
     */
    private String count=null;
    HashMap<String, String> args = new HashMap<String, String>();
	SerializeAdapter sa = new SerializeAdapter();
	DataRuleFactory factory = new DataRuleFactory();
	//��־
	private final Log log = LogFactory.getLog(AddDataRule.class);
	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
		str_name = message.getUserParameterValue("str_name");
		str_code= message.getUserParameterValue("str_code");
		int_id= message.getUserParameterValue("int_id");
		string_rule = message.getUserParameterValue("string_rule");
		str_description = message.getUserParameterValue("str_description");
		count = message.getUserParameterValue("count");
		 // ������Ϣ�浽map��(������,����),��map����ת����String����
		

		//���log��Ϣ
	    String debug="���ݹ�������" + str_name + "��"+ "�����ţ�"+str_code+ ";"
		+ "����ʽ��"+string_rule+ ";"+"�ɼ���������ʽ������"+str_description;
	    log.debug("������ݹ���ʱ�û��ύ�Ĳ���: " + debug);

		if (str_name == null || str_code == null || string_rule== null
				|| str_description == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
					processid, new java.util.Date(), null));
			log.fatal("���ݹ������������š�����ʽ����ʽ��������Ϊ�ղ������˳�����");
			return false;
		}
		
	    if(message.getUserParameterValue("argss") != null){
	        try {
	        	//������ת��Ϊmap(˳���,��չ������)
				args = (HashMap<String, String>) sa.toObject(message
						.getUserParameterValue("argss"));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			//���log��Ϣ
		    debug ="���ݹ���Id��" + int_id + "��"+"����������"+count;
		    if(Integer.parseInt(count)!=0)debug+=";������Ϣ��\n";
		    for(int j=1;j<=Integer.parseInt(count);j++){
		    	debug+="��������"+args.get("str_argsname"+j)+";";
		    	debug+="������Ϣ��"+args.get("str_argsdescription"+j);
		    	if(j!=1)debug+=";\n";
		    	if(j!=Integer.parseInt(count))debug+="\n";
		    }
		    log.debug("������ݹ���ʱ�û��ύ�Ĳ���: " + debug);

			if (args == null ||count == null) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
						processid, new java.util.Date(), null));
				log.fatal("���ݹ������ơ�������Ϊ�ղ������˳�����");
				return false;
			}

        }
		return true;
	}

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {
			try {
				DataRule datarule = new DataRule();								
				datarule.setName(str_name);
				datarule.setCode(str_code);
				datarule.setRule(string_rule);
				datarule.setDescription(str_description);	
				datarule.setId(new Integer(int_id));
				factory.updateDataRule(datarule, con);
				log.info("�������ݹ������ɹ���");		
//				��ɾ��ԭ�в�����Ϣ
				factory.delDataRuleArgsById(new Integer(int_id), con);
			    log.debug("ɾ�������ɹ�!");
			    //��������Ӳ���
                for (int i = 1; i <= Integer.parseInt(count); i++) {                    
                DataRuleArg dataruleArg = new DataRuleArg();                   
                dataruleArg.setInt_dataruleid(new Integer(int_id));               
                dataruleArg.setName(args.get("str_argsname"+i));
                dataruleArg.setDescription(args.get("str_argsdescription"+i));
                factory.createDataRuleArgs(dataruleArg, con);
                }
				log.info("������Ӳ�������ɹ���");				
			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), processid, new Date(), sqle));
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

	@Override
	public void relesase() throws SQLException {
		str_name = null;
		str_code = null;
		string_rule = null;
		str_description = null;
		con = null;

	}

}
