/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.qm.mes.ra.service.instruction;

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
import com.qm.mes.ra.bean.Instruction;
import com.qm.mes.ra.bean.ProduceUnit;
import com.qm.mes.ra.factory.InstructionCacheFactory;
import com.qm.mes.ra.factory.InstructionFactory;
import com.qm.mes.ra.factory.ProduceUnitFactory;

import java.util.List;

/**
 * ������ҵָ����ʱ��
 *
 * @author YuanPeng
 */
public class CreateInstructionCache extends AdapterService {
    /**
     * ���ݿ����Ӷ���
     */
    Connection con = null;
    /**
     * ��������Ԫ����̨��
     */
    int LockNum;
    /**
     * ָ����ʱ����
     */
    InstructionCacheFactory instructionCacheFactory = null;
    /**
     * ָ���
     */
    InstructionFactory instructionFactory = null;
    /**
     * ������Ԫ����
     */
    ProduceUnitFactory unitFactory = new ProduceUnitFactory();
    boolean check;
    int sum ;
    /**
     * ������ԪID
     */
    int ProduckUnitID;
    /**
     * ����
     */
    String str_date = null;
    /**
     * ���
     */
    String workOrder=null;
    /**
     * δ��������ָ��ID����
     */
    List<Integer> list = null;
    /**
     * ������Ԫ����
     */
    ProduceUnit produceunit = null;
    /**
     * ��������Ԫ�Ŀɱ༭״̬��
     */
    int stateid =0;
	/**
	 * ��־
	 */
	private final Log log = LogFactory.getLog(CreateInstructionCache.class);

    /**
     * ��������
     *
     * @throws java.sql.SQLException
     */
    @Override
    public void relesase() throws SQLException {
        con = null;
    }

    /**
     * ������
     *
     * @param message
     *              ʹ��IMessage�����������
     * @param processid
     *              ����ID
     * @return booleanֵ
     *              ����booleanֵ����ʾ�ɹ����
     */
    @Override
    public boolean checkParameter(IMessage message, String processid){
        try {
        	produceunit = new ProduceUnit();
            con = (Connection) message.getOtherParameter("con");
            ProduckUnitID = Integer.parseInt(message.getUserParameterValue("str_ProduceUnitID"));
            str_date = message.getUserParameterValue("str_date");
            workOrder = message.getUserParameterValue("workOrder");
            instructionCacheFactory = new InstructionCacheFactory();
            produceunit = unitFactory.getProduceUnitbyId(ProduckUnitID, con);
            stateid = produceunit.getInt_instructStateID();
            LockNum = produceunit.getInt_instCount();
            log.debug("������Ԫ�ţ�"+ProduckUnitID+"���������ڣ�"+str_date+"����Σ�"+workOrder+"��������Ԫδ����״̬��"+
            		stateid+"��������Ԫ����̨�ݣ�"+LockNum);
            if(produceunit.getInt_delete()==1){
            	message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "��������Ԫ�ѱ�ɾ��", this
								.getId(), processid, new Date(), null));
            	log.fatal("��������Ԫ�ѱ�ɾ��");
            }
        } catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), processid, new Date(), sqle));
				log.fatal("���ݿ��쳣");
				return false;
			}
        return true;
    }

    /**
     * ִ�з���
     *
     * @param message
     *              ʹ��IMessage�����������
     * @param processid
     *              ����ID
     * @return ExecuteResult
     *                  ִ�н��
     * @throws java.sql.SQLException
     *                          �׳�SQL�쳣
     * @throws java.lang.Exception
     *                          �׳������쳣
     */
    @Override
    public ExecuteResult doAdapterService(IMessage message, String processid) throws SQLException, Exception {
        try {
			try {
                instructionFactory = new InstructionFactory();
				instructionCacheFactory = new InstructionCacheFactory();
                //ȡ�÷�������ָ���
                list = instructionFactory.getInstructionsByUnlock(ProduckUnitID,str_date,workOrder,stateid, LockNum, con);
                if(list.size() == 0){
                    message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "�����������ݲ�����", this
								.getId(), processid, new Date(), null));
                    log.fatal("δ��������ָ�����");
				return ExecuteResult.fail;
                }
                for(int j:list){
                    //����������ָ��ϴ�����ʱ������ָ���������ڱ༭״̬
                	Instruction instruction = new Instruction();
                    instruction = instructionFactory.getInstructionById(j, con);
                    log.info("ͨ��ָ��Ų�ѯָ�����ɹ�");
                    instructionCacheFactory.saveInstructionCache(instruction, con);
                    log.info("ͨ��ָ��Ŵ���ָ��ɹ�");
                    instructionFactory.editInstructionById(j, con);
                    log.info("ͨ��ָ��Ž�ָ����Ϊ�༭״̬�ɹ�");
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
}
