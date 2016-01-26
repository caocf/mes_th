package com.qm.mes.tg.service.gatheritem;

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
import com.qm.mes.tg.factory.GatherItemFactory;

/**
 * ɾ���ɼ�������
 * 
 * @author lida
 * 
 */
public class DeleteGatherItem extends AdapterService {
	// �������
	private Connection con = null;
	// �ɼ������Ժ�
	private String int_gatherid = null;
	//��־
	private final Log log = LogFactory.getLog(DeleteGatherItem.class);

	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
		int_gatherid = message.getUserParameterValue("int_gatherid");
		//���log��Ϣ
	    String debug="�ɼ���ţ�" + int_gatherid;
	    log.debug("��Ӳɼ���ʱ�û��ύ�Ĳ���: " + debug);
		if (int_gatherid == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
					processid, new java.util.Date(), null));
			log.fatal("�ɼ����Ϊ�գ��˳�����");
			return false;
		}

		return true;
	}

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {
			try {
				GatherItemFactory factory = new GatherItemFactory();
				factory.delGatherItemByGid(new Integer(int_gatherid), con);
				log.info("��Ӳɼ������ɹ���");
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
		int_gatherid = null;
		con = null;

	}

}
