package com.qm.mes.tg.service.gather;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.AdapterService;
import com.qm.mes.framework.DataBaseType;
import com.qm.mes.framework.ExecuteResult;
import com.qm.mes.framework.IMessage;
import com.qm.mes.framework.ServiceException;
import com.qm.mes.framework.ServiceExceptionType;
import com.qm.mes.system.dao.DAOFactoryAdapter;
import com.qm.mes.tg.dao.IDAO_Gather;
import com.qm.mes.tg.factory.GatherFactory;

/**
 * ɾ���ɼ���
 * 
 * @author lida
 * 
 */
public class DeleteGather extends AdapterService {
	// �������
	private Connection con = null;
	// �ɼ����
	private String int_id = null;
	//��־
	private final Log log = LogFactory.getLog(DeleteGather.class);

	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
		int_id = message.getUserParameterValue("int_id");
		//���log��Ϣ
	    String debug="ɾ���ɼ���ţ�" + int_id;
	    log.debug("ɾ���ɼ���ʱ�û��ύ�Ĳ���: " + debug);
		if (int_id == null) {
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
				GatherFactory factory = new GatherFactory();
				factory.delGatherById(new Integer(int_id), con);
				log.info("ɾ���ɼ������ɹ���");
				IDAO_Gather dao = (IDAO_Gather) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						IDAO_Gather.class);
				Statement stmt = con.createStatement();
				log.debug("ɾ��qualitys: "+dao.delGather_Q(Integer.parseInt(int_id)));
				stmt.execute(dao.delGather_Q(Integer.parseInt(int_id)));
				
			if(stmt!=null){
				stmt.close();
				stmt=null;
			}
				
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
		int_id = null;
		con = null;

	}
}
