package mes.ra.service.instruction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.logging.Level;
import java.util.logging.Logger;
import mes.framework.AdapterService;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import mes.ra.factory.InstructionCacheFactory;
import mes.ra.bean.Instruction;

/**
 * ��ҵָ������
 * 
 * @author YuanPeng
 */
public class UpInstruction extends AdapterService { 
	/**
	 * ���ݿ����Ӷ���
	 */
	Connection con = null; 
	/**
	 * ��ҵָ��˳���
	 */
	int order =0;
	/**
	 * ������Ԫ��
	 */
	int ProduceUnitID; 
	/**
	 * �ַ�������������
	 */
	String str_date = null; 
	/**
	 * �ַ����Ͱ��
	 */
	String workOrder = null;
	Instruction instruction = new Instruction();
	Instruction instruction_new = new Instruction();
	InstructionCacheFactory instructionCacheFactory = new InstructionCacheFactory();
	int temp;
	boolean check;
	int sum; 
	/**
	 * ��־
	 */
	private final Log log = LogFactory.getLog(UpInstruction.class);
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
	 *            ʹ��IMessage�����������
	 * @param processid
	 *            ����ID
	 * @return booleanֵ ����booleanֵ����ʾ�ɹ����
	 */
	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
		order = Integer.parseInt(message.getUserParameterValue("order"));
		ProduceUnitID = Integer.parseInt(message
				.getUserParameterValue("str_ProduceUnitID"));
		str_date = message.getUserParameterValue("str_date");
		workOrder = message.getUserParameterValue("workOrder");
		log.debug("ָ��˳��ţ�"+order+"��������Ԫ�ţ�"+ProduceUnitID+"����Σ�"+workOrder+"���������ڣ�"+str_date);
		if (order == 0) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
					processid, new java.util.Date(), null));
			log.fatal("˳���Ϊ��");
			return false;
		}
		try {
				//ͨ��������Ԫ�š��������ڡ���Ρ�ָ��˳��Ų�ѯָ��
				instruction = instructionCacheFactory
						.getInstructionByProdUnitDateOrder(ProduceUnitID,str_date,workOrder,order, con);
				log.info("ͨ��������Ԫ�š��������ڡ���Ρ�˳��Ų�ѯ��ʱָ��ɹ�");
				if (instruction.getDelete() == 1) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.PARAMETERLOST,
							"��ָ���Ѿ�Ϊɾ��״̬��", this.getId(), processid,
							new java.util.Date(), null));
					log.fatal("��ָ���Ѿ�Ϊɾ��״̬");
					return false;
				}
				// ���ָ��˳����������Ƿ�Ϊ��һ��
				check = instructionCacheFactory.checkFirst(ProduceUnitID,
						str_date, workOrder,instruction.getId(), con);
				if (check == true) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.UNKNOWN, "�ü�¼Ϊ��һ�����޷�����", this
									.getId(), processid, new Date(), null));
					log.fatal("�ü�¼Ϊ��һ�����޷�����");
					return false;
				}
		} catch (SQLException ex) {
			Logger.getLogger(DeleteInstruction.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		return true;
	}

	/**
	 * ִ�з���
	 * 
	 * @param message
	 *            ʹ��IMessage�����������
	 * @param processid
	 *            ����ID
	 * @return ExecuteResult ִ�н��
	 * @throws java.sql.SQLException
	 *             �׳�SQL�쳣
	 * @throws java.lang.Exception
	 *             �׳������쳣
	 */
	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {
			try {
		
				InstructionCacheFactory factory = new InstructionCacheFactory();
				//��ѯ��Int_instructOrderС�Ķ���
				instruction_new = factory.OrderMinus(ProduceUnitID,str_date,workOrder,
						instruction.getInstructionOrder(), con).get(0);
				log.info("��ѯ��һ˳���ָ��ɹ�");
				temp = instruction_new.getInstructionOrder();
				instruction_new.setInstructionOrder(instruction.getInstructionOrder());
				instruction.setInstructionOrder(temp);
				factory.updateInstructionCache(instruction_new, con);
				factory.updateInstructionCache(instruction, con);
				log.info("���Ƴɹ�");
			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), processid, new Date(), sqle));
				log.error("���ݿ�����쳣");
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
