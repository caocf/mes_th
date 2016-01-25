package mes.ra.service.instruction;

import java.io.IOException;
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
import mes.util.SerializeAdapter;

/**
 * ɾ����ҵָ��
 *
 * @author YuanPeng
 */
public class DeleteInstruction extends AdapterService {
    /**
     * ���ݿ����Ӷ���
     */
    Connection con = null;
    /**
     * ��ҵָ�����
     */
    String int_id = null;
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
	SerializeAdapter sa = new SerializeAdapter();
	/**
	 * ��־
	 */
	private final Log log = LogFactory.getLog(DeleteInstruction.class);
	
    /**
     * ��������
     *
     * @throws java.sql.SQLException
     */
    @Override
    public void relesase() throws SQLException {
        con = null;
        int_id = null;
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
    
    public boolean checkParameter(IMessage message, String processid) {
        con = (Connection) message.getOtherParameter("con");
        ProduceUnitID = Integer.parseInt(message.getUserParameterValue("str_ProduceUnitID"));
		str_date = message.getUserParameterValue("str_date");
		workOrder = message.getUserParameterValue("workOrder");
        //��ȡ���鳤��
        array_length = Integer.parseInt(message.getUserParameterValue("arr_length"));
        int_array = new int[array_length];
        
        try {
            //��str_instructionת����int��������
            int_array = (int[]) sa.toObject(message.getUserParameterValue("str_array"));
        } catch (IOException ex) {
            Logger.getLogger(IssuanceInstruction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IssuanceInstruction.class.getName()).log(Level.SEVERE, null, ex);
        }
        String debug = "������Ԫ�ţ�"+ProduceUnitID+"����Σ�"+workOrder+"����������Ϊ��"+str_date;
        for(int i=0;i<array_length;i++){
        	if(i!=array_length)debug+="��";
        	debug+="��"+i+"��˳��ţ�"+int_array[i];
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
				InstructionCacheFactory instructionCacheFactory = new InstructionCacheFactory();
				int flag=0;
				int j=0;
				for(int i=1;i<int_array.length;i++){
					for(j=int_array.length-1;j>=i;j--){
						if(int_array[j]>int_array[j-1]) { 
							flag = int_array[j-1]; 
							int_array[j-1] = int_array[j]; 
							int_array[j] = flag; 
						}
					}
				}
				for(int k : int_array){
					// ͨ��������Ԫ�š��������ڡ�ָ��˳���ɾ����ʱָ��
                    instructionCacheFactory.delInstructionCacheByProduceUnitDateWorkorderOrder(ProduceUnitID,str_date,workOrder,k, con);
                    log.info("ɾ����ʱָ��ɹ�");
                    //����ָ��˳��:���������ڡ�������Ԫ�����ڸ�ָ��˳��ŵ�ָ��˳��ż�1
                    instructionCacheFactory.MinusInstructionOrder(ProduceUnitID, str_date,workOrder, k, con);
                    log.info("������ʱָ��˳��ɹ�");
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
