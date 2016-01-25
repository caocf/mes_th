package mes.os.service.plan;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import mes.framework.AdapterService;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import mes.os.factory.*;

import mes.util.SerializeAdapter;

/**���ܣ�ɾ���ƻ�
 * @author л����
 *
 */
public class DeletePlan extends AdapterService {
	 
	/**
	 * log ��־
	 */
	private final Log log = LogFactory.getLog(DeletePlan.class);
   
    /**
     * con  //���ݿ����Ӷ���
     */
    Connection con = null;
    
    /**
     * int_id //��ҵָ����� 
     */
    String int_id = null;
  
	/**
	 *  sa �����������
	 */
	SerializeAdapter sa = new SerializeAdapter();
    
    /**
     * int_array[] //��������
     */
    int int_array[] ;
   
    /**
     * array_length  //ѡ�е����鳤��
     */
    int array_length ;
 
   /**
     * workOrder // ���
    */
    private String workOrder=null;
   
    /**
     * overTime  // ����ʱ��
     */
    private String overTime=null;
   
    /**
     * produceUnit  // ������Ԫ��id
     */
    private String produceUnit=null;
  
    /**
     * all   // ҳ�����Լƻ��ĸ���
     */
    private int all=0;
    
	 public boolean checkParameter(IMessage message, String processid) {
	        con = (Connection) message.getOtherParameter("con");
	        workOrder=message.getUserParameterValue("workOrder");
	        overTime=message.getUserParameterValue("overTime");
	        produceUnit=message.getUserParameterValue("produceUnit");
		    all=Integer.parseInt(message.getUserParameterValue("all"));
	                //��ȡ���鳤��
	                array_length = Integer.parseInt(message.getUserParameterValue("arr_length"));
                    int_array = new int[array_length];
	               //���log��Ϣ
	    		    String debug="workOrder:" +workOrder 
	    			+ " overTime:"+overTime+ " " +
	    			"produceUnit:"+produceUnit+ " " +
	    			"all:"+all+ " " +
	    			"array_length:"+array_length+ "\n";
	    		    log.info("���״̬ת���Ĳ���: " + debug);
	        try {
	            //��str_instructionת����Instruction����
	            int_array = (int[]) sa.toObject(message.getUserParameterValue("str_array"));
	        } catch (IOException ex) {
	           ex.getStackTrace();
	        } catch (ClassNotFoundException ex) {
	        	ex.getMessage();
	            
	        }
	        

	        return true;
	    }
	 public ExecuteResult doAdapterService(IMessage message, String processid) throws SQLException, Exception {
	        try {
				try {
					// �ƻ�����
					PlanFactory factory=new PlanFactory();
					int flag=0;
					int j=0;
					// ���մӴ�С����
					for(int i=1;i<int_array.length;i++){
					 for(j=int_array.length-1;j>=i;j--)
						  { if(int_array[j]>int_array[j-1]) 
						  { flag = int_array[j-1]; 
						  int_array[j-1] = int_array[j]; 
						  int_array[j] = flag; }
					  }
					}
					
		         for(int k : int_array){
						//ɾ����ʱ�ƻ�planorder=k
						factory.deleteplanbyPlanOrder(overTime, Integer.parseInt(produceUnit),  workOrder, k, con);
					     
						//�����ƻ�˳��allΪ����༭����ĸ�����ȫɾ��ʱ��all��0ʱ�Ͳ���˳��
						all--;
						if(all>0){
	                    factory.updatePlanOrder(overTime, Integer.parseInt(produceUnit), workOrder, k, con);
	              
						}
	                }
				} catch (SQLException sqle) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
									.getId(), processid, new Date(), sqle));
					return ExecuteResult.fail;
				}
			} catch (Exception e) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
						processid, new java.util.Date(), e));
				return ExecuteResult.fail;
			}
			return ExecuteResult.sucess;
			
			
			}
	 
	 
	 public void relesase() throws SQLException {
	        con = null;
	        int_id = null;
	        workOrder=null;
	        overTime=null;
	        produceUnit=null;
	     
	    }
}
