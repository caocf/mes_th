package mes.tg.service.materielRule;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import mes.framework.AdapterService;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;

import mes.tg.bean.MaterielRule;
import mes.tg.factory.MaterielRuleFactory;

/**
 * ������ϱ�ʶ����
 * 
 * @author lida
 * 
 */
public class AddMaterielRule extends AdapterService {
	// �������
	private Connection con = null;
	// ���ϱ�ʶ������
	private String str_name = null;
	// ��֤�ַ���
	private String str_validateclass = null;
	// ��֤�ַ�����������Ϣ
	private String desc = null;
	//��־
	private final Log log = LogFactory.getLog(AddMaterielRule.class);

	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
		str_name = message.getUserParameterValue("str_name");
		str_validateclass = message.getUserParameterValue("str_validateclass");
		desc = message.getUserParameterValue("desc");
		//���log��Ϣ
	    String debug="���ϱ�ʶ��������" + str_name + "����֤�ַ�����"+str_validateclass+
		";��֤�ַ�����������Ϣ��"+desc;
	    log.debug("������ϱ�ʶ����ʱ�û��ύ�Ĳ���: " + debug);
		if (str_name == null || str_validateclass == null || desc == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
					processid, new java.util.Date(), null));
			log.fatal("���ϱ�ʶ����������֤�ַ�������֤�ַ�����������Ϣ����Ϊ�ղ������˳�����");
			return false;
		}

		return true;
	}

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {
			try {
				MaterielRuleFactory factory = new MaterielRuleFactory();
				MaterielRule materielRule = new MaterielRule();
				materielRule.setName(str_name);
				materielRule.setValidate(str_validateclass);
				materielRule.setDesc(desc);
				factory.saveMaterielRule(materielRule, con);
				log.info("������ϱ�ʶ�������ɹ���");
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
		str_validateclass = null;
		con = null;

	}

}
