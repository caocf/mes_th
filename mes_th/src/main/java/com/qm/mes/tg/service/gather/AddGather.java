package com.qm.mes.tg.service.gather;

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
import com.qm.mes.tg.bean.Gather;
import com.qm.mes.tg.factory.GatherFactory;

/**
 * ��Ӳɼ���
 * 
 * @author lida
 * 
 */
public class AddGather extends AdapterService {
	// �������
	private Connection con = null;
	// �ɼ�����
	private String str_name = null;
	// ������Ϣ
	private String str_desc = null;
	// ������Ԫ��
	private String int_produnitid = null;
	// �����ϱ�ʶ�����
	private String int_materielruleid = null;
	//��־
	private final Log log = LogFactory.getLog(AddGather.class);
	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
		str_name = message.getUserParameterValue("str_name");
		str_desc = message.getUserParameterValue("str_desc");
		int_produnitid = message.getUserParameterValue("int_produnitid");
		int_materielruleid = message.getUserParameterValue("int_materielruleid");

		//���log��Ϣ
	    String debug="�ɼ�������" + str_name + "��"+ "������Ԫ�ţ�"+int_produnitid+ ";"
		+ "���ϱ�ʶ����ţ�"+int_materielruleid+ ";"+"�ɼ���������"+str_desc;
	    log.debug("��Ӳɼ���ʱ�û��ύ�Ĳ���: " + debug);

		if (str_name == null || str_desc == null || int_produnitid == null
				|| int_materielruleid == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
					processid, new java.util.Date(), null));
			log.fatal("�ɼ�������������Ԫ�š����ϱ�ʶ����š��ɼ�����������Ϊ�ղ������˳�����");
			return false;
		}

		return true;
	}

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {
			try {
				Gather gather = new Gather();
				//л�������
				gather.setName(str_name);
				gather.setDesc(str_desc);
				gather.setProdunitId(new Integer(int_produnitid));
				gather.setMaterielruleId(new Integer(int_materielruleid));
				GatherFactory factory = new GatherFactory();
				factory.saveGather(gather, con);
				log.info("��Ӳɼ������ɹ���");
				Gather g = new Gather();
				g = factory.getGatherByName(str_name, con);
				message.setOutputParameter("int_gatherid", String.valueOf(g
						.getId()));
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
		str_desc = null;
		int_produnitid = null;
		int_materielruleid = null;
		con = null;

	}

}
