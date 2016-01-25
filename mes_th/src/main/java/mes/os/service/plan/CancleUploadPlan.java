package mes.os.service.plan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import mes.framework.AdapterService;
import mes.framework.DataBaseType;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import mes.framework.dao.DAOFactory_UserManager;
import mes.framework.dao.IDAO_UserManager;
import mes.os.bean.UpLoadRecord;
import mes.os.factory.PlanFactory;
import mes.os.factory.UploadFactory;

/**���ܣ�ȡ��������ҵ�ƻ�
 * ��checkParameter��������Ԫid����Ϊ��
 * @author л����
 *
 */
public class CancleUploadPlan extends AdapterService {
	
	/**
	 * log ��־
	 */
	private final Log log = LogFactory.getLog(CancleUploadPlan.class);

	/**
	 * con �������ݿ�
	 */
	private Connection con=null;
	
	/**
	 * stmt �����ݿⷢ��sql���
	 */
	private Statement stmt=null;
	
	/**
	 * produnitid // ������Ԫid
	 */
	private String produnitid =null;
	
	/**
	 * planfactory // �ƻ�����
	 */
	private PlanFactory planfactory=new PlanFactory();
	
	/**
	 * overtime // ��������
	 */
	private String overtime=null;
	
	/**
	 * workOrder // ���
	 */
	private String workOrder=null;
	
	/**
	 * userid // �û���id
	 */
	private String userid=null;

	/**
	 * versioncode 	// �汾��
	 */
	private String versioncode=null;
	
	public boolean checkParameter(IMessage message, String processid) {
		con=(Connection)message.getOtherParameter("con");
		produnitid=message.getUserParameterValue("int_id");
		overtime=message.getUserParameterValue("overtime");
		workOrder=message.getUserParameterValue("workOrder");
		userid=message.getUserParameterValue("userid");
		versioncode=message.getUserParameterValue("versioncode");
		//���log��Ϣ
	    String debug="produnitid:" +produnitid 
		+ " overtime:"+overtime+ " " +
		"workOrder:"+workOrder+ " " +
		"userid:"+userid+ " " +
		"versioncode:"+versioncode+ "\n";
	    log.info("���״̬ת���Ĳ���: " + debug);
		if (produnitid== null) {
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
			
			// ͨ���û���id�õ��û���
		    IDAO_UserManager dao_UserManager = DAOFactory_UserManager.getInstance(DataBaseType.getDataBaseType(con));
   	        String sql = dao_UserManager.getSQL_SelectUserById(Integer.parseInt(userid));
   	        stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
   	        ResultSet rs = stmt.executeQuery(sql);
   	        String user="";
   	        if(rs.next())
   	        	user = rs.getString("CUSRNAME");
   	  // ��������
   	         UpLoadRecord uploadrecord=new UpLoadRecord();
   	         uploadrecord.setUserName(user);
   	         uploadrecord.setUpload(0);
   	         uploadrecord.setVersionCode(versioncode);
   	         UploadFactory uploadfactory=new UploadFactory();
   	         uploadfactory.SaveUploadRecord(uploadrecord, con);
   	   // ȡ�������ƻ�
			planfactory.canceluploadplan(overtime, Integer.parseInt(produnitid),  workOrder, con);
			log.debug( "ȡ�������ƻ��ɹ�!");
	    }
	    catch (SQLException sqle) {
		message.addServiceException(new ServiceException(
				ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
						.getId(), processid, new Date(), sqle));
		return ExecuteResult.fail;
	    }
	     catch (Exception e) {
	    message.addServiceException(new ServiceException(
			ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
			processid, new java.util.Date(), e));
	      return ExecuteResult.fail;
	    }
	    return ExecuteResult.sucess;
	   }

    public void relesase() throws SQLException {
        con = null;
        produnitid =null;
    	overtime=null;
        workOrder=null;
        userid=null;
        versioncode=null;
        if(stmt!=null)stmt.close();
   
     }
}
