package com.qm.mes.ra.service.instruction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.AdapterService;
import com.qm.mes.framework.ExecuteResult;
import com.qm.mes.framework.IMessage;
import com.qm.mes.framework.ServiceException;
import com.qm.mes.framework.ServiceExceptionType;
import com.qm.mes.ra.bean.Instruction;
import com.qm.mes.ra.factory.InstructionCacheFactory;
import com.qm.mes.ra.factory.InstructionFactory;

/**
 * ɾ����ҵָ����ʱ��
 *
 * @author YuanPeng
 */
public class TruncateInstructionCache extends AdapterService {
    /**
     * ���ݿ����Ӷ���
     */
    private Connection con;
    /**
     * ָ����ʱ��������
     */
    InstructionCacheFactory instructionCacheFactory = null;
    /**
     * ָ��̶���
     */
    InstructionFactory instructionFactory = null;
    /**
     * ������ָ�ʼ˳���
     */
    int UnlockStartOrder;
    /**
     * ָ����󼯺�
     */
    List<Instruction> list = new ArrayList<Instruction>();
    /**
     * ������Ԫ��
     */
    int ProduceUnitID;
    /**
     * ��������
     */
    String str_date = null;
    /**
     * ���
     */
    String workOrder = null;
	/**
	 * ��־
	 */
	private final Log log = LogFactory.getLog(TruncateInstructionCache.class);
    
	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
		ProduceUnitID = Integer.parseInt(message.getUserParameterValue("str_ProduceUnitID"));
		str_date = message.getUserParameterValue("str_date");
		workOrder = message.getUserParameterValue("workOrder");
        if(message.getUserParameterValue("str_UnlockStartOrder")==null||message.getUserParameterValue("str_UnlockStartOrder").equals("null"))
        	return false;
        UnlockStartOrder = Integer.parseInt(message.getUserParameterValue("str_UnlockStartOrder"));
    	log.debug("������Ԫ��"+ProduceUnitID+"���������ڣ�"+str_date+"����Σ�"+workOrder+"����������˳��ţ�"+UnlockStartOrder);
        instructionCacheFactory = new InstructionCacheFactory();
        try {
            //�����ʱ���м�¼����Ϊ0��η���FALSE
            if (instructionCacheFactory.getInstructionCacheCount(con) == 0) {
                message.addServiceException(new ServiceException(ServiceExceptionType.DATABASEERROR, 
                        "��ʱ����û������", this.getId(), processid, new Date(), null));
                log.fatal("��ʱ����û������");
                return false;
            }
        } catch (Exception e) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
					processid, new java.util.Date(), e));
			log.error("δ֪�쳣");
			return false;
		}
        return true;
	}

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {
			try {
				instructionCacheFactory = new InstructionCacheFactory();
				instructionFactory = new InstructionFactory();
                //ɾ����������Ԫ��������
                instructionCacheFactory.DeleteInstructionCacheByProdUnitIdproducedateWorkorder(ProduceUnitID,str_date,workOrder, con);
                log.info("ɾ����������Ԫ�ð����ʱָ��");
                //��ѯ��Int_instructOrder��Ķ���
                list = instructionFactory.OrderPlus(ProduceUnitID,str_date,workOrder,UnlockStartOrder, con);
                log.info("��ѯδ����ָ�����");
                for(Instruction instruction_i:list)
                	//ͨ��ID�޸�ָ��ȡ���༭״̬
                	instructionFactory.uneditInstructionById(instruction_i.getId(), con);
                log.info("��δ����ָ�����༭״̬");
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
		try{
			con = null;
		}catch(Exception e){
			if(con!=null)
				con = null;
			e.printStackTrace();
		}
	}

}
