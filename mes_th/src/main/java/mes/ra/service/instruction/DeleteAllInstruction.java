package mes.ra.service.instruction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import mes.framework.AdapterService;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import mes.ra.factory.InstructionFactory;

/**ɾ��ָ�������ָ��
 * @author ������
 *
 */
public class DeleteAllInstruction extends AdapterService {
	 /**
     * ���ݿ����Ӷ���
     */
	private Connection con=null;
	 /**
     * ������Ԫ��
     */
	private String produnitid=null;
	 /**
     * ��������
     */
	private String str_date=null;
	 /**
     * ���
     */
	private String workOrder=null;
	
	public boolean checkParameter(IMessage message, String processid) {
			con = (Connection) message.getOtherParameter("con");
			produnitid=message.getUserParameterValue("info");
			str_date=message.getUserParameterValue("str_date");
			workOrder=message.getUserParameterValue("workOrder");
			if (produnitid== null || str_date== null || workOrder== null) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
						processid, new java.util.Date(), null));
				return false;
			}
			return true;
	 }
	 
	public ExecuteResult doAdapterService(IMessage message, String processid)
	throws SQLException, Exception {
		try {
			try {
				InstructionFactory factory=new InstructionFactory();
				// ͨ��������Ԫ����Σ�����ɾ��ָ��
				factory.deleteAllInstructionByProdunitidDateWorkorder(Integer.parseInt(produnitid),str_date,workOrder,con);
	 
			}catch(SQLException sqle){
				message.addServiceException(new ServiceException(
					ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
					.getId(), processid, new Date(), sqle));
				return ExecuteResult.fail;
			}
		}catch(Exception e){
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
					processid, new java.util.Date(), e));
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
}
	 
	 @Override
	 public void relesase() throws SQLException {
		 con = null;
		 produnitid=null;
		 str_date=null;
		 workOrder=null;
	   	
	 }
	 
	 
	 
	 
}








