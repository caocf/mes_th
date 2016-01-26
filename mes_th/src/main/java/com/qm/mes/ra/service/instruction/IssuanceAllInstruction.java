package com.qm.mes.ra.service.instruction;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.AdapterService;
import com.qm.mes.framework.ExecuteResult;
import com.qm.mes.framework.IMessage;
import com.qm.mes.framework.ServiceException;
import com.qm.mes.framework.ServiceExceptionType;
import com.qm.mes.ra.factory.InstructionCacheFactory;
/**
 * ��ҵָ��ȫ������
 *
 * @author YuanPeng
 */
public class IssuanceAllInstruction extends AdapterService {
    /**
     * ���ݿ����Ӷ���
     */
    private Connection con;
	/**
	 * ������Ԫ��
	 */
	int ProduceUnitID;
	/**
	 * �ַ���������
	 */
	String str_date = null;
	/**
	 * �ַ����Ͱ��
	 */
	String workOrder = null;
	/**
	 * ��־
	 */
	private final Log log = LogFactory.getLog(IssuanceAllInstruction.class);

	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
		ProduceUnitID = Integer.parseInt(message
				.getUserParameterValue("str_ProduceUnitID"));
		str_date = message.getUserParameterValue("str_date");
		workOrder = message.getUserParameterValue("workOrder");
		log.debug("������Ԫ�ţ�"+ProduceUnitID+"����Σ�"+workOrder+"���������ڣ�"+str_date);
        return true;
	}

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {
			
			InstructionCacheFactory instructionCacheFactory = new InstructionCacheFactory();
			//������������Ԫ���������ڵ�����ָ��
            instructionCacheFactory.IssuanceAllByProduceUnitDateWorkorder(ProduceUnitID,str_date,workOrder, con);
			log.info("��������ָ��ɹ�");
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
	}

}

