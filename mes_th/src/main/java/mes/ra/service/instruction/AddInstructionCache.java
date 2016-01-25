package mes.ra.service.instruction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
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
import mes.os.factory.WorkSchedleFactory;
import mes.ra.bean.Instruction;
import mes.ra.factory.InstructionCacheFactory;
import mes.util.SerializeAdapter;

/**
 * �����ҵָ����ʱ������
 *
 * @author YuanPeng
 */
public class AddInstructionCache extends AdapterService {
    /**
     * ���ݿ����Ӷ���
     */
    private Connection con;
    /**
     * ��ҵָ�����
     */
    private Instruction instruction ;
	SerializeAdapter sa = new SerializeAdapter();
	/**
	 * ��־
	 */
	private final Log log = LogFactory.getLog(AddInstructionCache.class);
	 /**
	 * sqlplanall �ƻ�Ԥ�������
	 */
	PreparedStatement sqlplanall=null;
	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
            try{
            try{
                //��str_instructionת����Instruction����
                instruction = (Instruction) sa.toObject(message.getUserParameterValue("str_instruction"));
            } catch (IOException ex) {
                Logger.getLogger(UpdateInstruction.class.getName()).log(Level.SEVERE, null, ex);
            }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UpdateInstruction.class.getName()).log(Level.SEVERE, null, ex);
            }
		return true;
	}

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {
			try {
				//����ʱ�̱�
				WorkSchedleFactory workschedlefactory=new WorkSchedleFactory();
				InstructionCacheFactory instructionCacheFactory = new InstructionCacheFactory();
				//������صļƻ���Ϣ
				String  sqlplanstring="select p.*,to_char(p.Dat_produceDate,'yyyy-MM-dd') as producedate from t_os_plan p where str_versioncode in(select max(str_versioncode) from t_os_plan  where int_upload=1  and int_produnitid=? group by int_produnitid,Dat_produceDate,str_workOrder"
				            +") and  str_producemarker=? ";
				sqlplanall =con.prepareStatement(sqlplanstring);
				sqlplanall.setInt(1,instruction.getProdunitid());
				sqlplanall.setString(2,instruction.getProduceMarker());
				ResultSet rsprepare= sqlplanall.executeQuery();
				if(rsprepare.next()){
					//���Ƿ���ʵ���� 				 
					long locked= workschedlefactory.getworkschedleadtime(rsprepare.getInt("int_produnitid"),rsprepare.getString("producedate"),rsprepare.getString("str_workorder"),con);
					if(locked==0){
						//�������ͬ�������ϲ�����ʵ������ȫ��صļƻ���Ϣ
			            instruction.setPlanDate(rsprepare.getDate("dat_planDate")); 
				        instruction.setPlanOrder(rsprepare.getInt("int_planOrder"));
			        }
				}
                instructionCacheFactory.saveInstructionCache(instruction, con);
                log.info("������ʱָ��ɹ�");
			}catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), processid, new Date(), sqle));
				log.error("���ݿ��쳣");
				return ExecuteResult.fail;
			}
		}catch (Exception e) {
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
		/**
	     * ��ҵָ�����
	     */
		instruction = null;
		con = null;
		sqlplanall.close();
	}

}
