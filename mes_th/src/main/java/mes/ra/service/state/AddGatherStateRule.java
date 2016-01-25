package mes.ra.service.state;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import mes.ra.factory.*;
import mes.framework.AdapterService;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**��Ӳɼ���״̬����
 * author : л����
 */
public class AddGatherStateRule extends AdapterService{
	/**
	 *  �ɼ����id
	 */
	private String int_gatherid;
	/**
	 * ����
	 */
	private Connection con = null;
	/**
	 * �����Ĺ������
	 */
	private  String sum;
	/**
	 * Ĭ�ϵ�ִ�й���״̬
	 */
	private String go;
	private String int_stateconversionid;
	private final Log log = LogFactory.getLog(AddGatherStateRule.class);

	public boolean checkParameter(IMessage message, String processid) {
		int_gatherid=message.getOutputParameterValue("int_gatherid");
		con=(Connection) message.getOtherParameter("con");
		sum=message.getUserParameterValue("sumnumber");
        go=message.getUserParameterValue("go");
        if(sum==null){
        	sum="0";
        }
		if (int_gatherid == null) {
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
			GatherStateRuleFactory factory=new GatherStateRuleFactory();
			factory.delgatherstaterule(Integer.parseInt(int_gatherid), con);			
			if(go!=null){
				factory.saveGatherStateRule(Integer.parseInt(int_gatherid), Integer.parseInt(go), 1, con);
			}
			for(int i=1;i<=Integer.parseInt(sum);i++){
				int_stateconversionid=message.getUserParameterValue("go"+i);   
				factory.saveGatherStateRule(Integer.parseInt(int_gatherid), Integer.parseInt(int_stateconversionid), 0, con);
				log.debug("���������ɹ�!");
			}
		}
		catch (SQLException sqle) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
					.getId(), processid, new Date(), sqle));
			log.error("ɾ��״̬ʱ,δ֪�쳣" + sqle.toString());
			return ExecuteResult.fail;
		}
		catch (Exception e) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
					processid, new java.util.Date(), e));
			log.error("ɾ��״̬ʱ,δ֪�쳣" + e.toString());
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
	}
	public void relesase() throws SQLException {
	    con = null;
	    int_gatherid=null;
	    int_stateconversionid=null;
	    sum=null;
    }
}
