package mes.pm.service.datarule;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import mes.framework.AdapterService;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import mes.pm.bean.DataRule;
import mes.pm.factory.DataRuleFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * ������ݹ���
 * 
 * @author Xujia
 * 
 */
public class AddDataRule  extends AdapterService {
	
	/**
	 *  �������
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
	 * ���ݹ��򹤳���
	 */
	DataRuleFactory factory = new DataRuleFactory();
	/**
	 * ��־ 
	 */
	private final Log log = LogFactory.getLog(AddDataRule.class);
	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
		str_name = message.getUserParameterValue("str_name");
		str_code= message.getUserParameterValue("str_code");
		string_rule = message.getUserParameterValue("rule");
		str_description = message.getUserParameterValue("str_description");
		
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
				factory.createDataRule(datarule, con);
				log.info("������ݹ������ɹ���");
				DataRule d = new DataRule();
				d = factory.getDataRuleByName(str_name, con);
				message.setOutputParameter("int_dataruleid", String.valueOf(d
						.getId()));
				log.info("���ݹ����int_dataruleidΪ��"+d.getId()+" ");
				
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

