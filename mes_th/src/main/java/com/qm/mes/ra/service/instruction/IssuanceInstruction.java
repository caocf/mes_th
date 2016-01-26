package com.qm.mes.ra.service.instruction;

import java.sql.Connection;
import java.sql.SQLException;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.AdapterService;
import com.qm.mes.framework.ExecuteResult;
import com.qm.mes.framework.IMessage;
import com.qm.mes.framework.ServiceException;
import com.qm.mes.framework.ServiceExceptionType;
import com.qm.mes.ra.factory.InstructionCacheFactory;
import com.qm.mes.util.SerializeAdapter;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ��ҵָ���
 *
 * @author YuanPeng
 */
public class IssuanceInstruction extends AdapterService {
    /**
     * ���ݿ����Ӷ���
     */
    private Connection con;
	/**
	 * �Ѳ������ݱ�ɴ������ݵļĴ���
	 */
	private SerializeAdapter sa = null;
    /**
     * ��������
     */
    int int_array[] ;
    /**
     * ѡ�е����鳤��
     */
    int array_length ; 
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
	private final Log log = LogFactory.getLog(IssuanceInstruction.class);

	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
		ProduceUnitID = Integer.parseInt(message
				.getUserParameterValue("str_ProduceUnitID"));
		str_date = message.getUserParameterValue("str_date");
		workOrder = message.getUserParameterValue("workOrder");
		log.debug("������Ԫ��"+ProduceUnitID+"���������ڣ�"+str_date+"����Σ�"+workOrder+"��˳����б�--\n");
        sa = new SerializeAdapter();
        //��ȡ���鳤��
        array_length = Integer.parseInt(message.getUserParameterValue("arr_length"));
        int_array = new int[array_length];
        try {
            //���ַ���ת����������������
            int_array = (int[]) sa.toObject(message.getUserParameterValue("str_array"));
            
        } catch (IOException ex) {
            Logger.getLogger(IssuanceInstruction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IssuanceInstruction.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i =0 ;i<array_length;i++){
        	String debug = "";
        	if(i!=0||i!=array_length)debug+="��";
        	debug+="��"+i+"��˳���Ϊ��"+int_array[i];
        	log.debug(debug);
        }
        return true;
	}

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {
			try {
				InstructionCacheFactory instructionCacheFactory = new InstructionCacheFactory();
                for(int j:int_array){
                	//ָ�����ͨ��������Ԫ���������ڣ���Σ�ָ��˳���
                    instructionCacheFactory.IssuanceByProduceUnitDateWorkorderOrder(ProduceUnitID,str_date,workOrder,j, con);
                }
                log.info("ͨ��������Ԫ���������ڡ���Ρ�˳��ŷ�����ʱָ��ɹ�");
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
	}

}

