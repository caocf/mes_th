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
import mes.os.factory.PlanFactory;
import mes.ra.bean.ProduceUnit;
import mes.ra.factory.*;
import mes.os.factory.*;
import mes.os.bean.*;
/**���ܣ��ύ�༭�ļƻ�
 * @author л����
 *
 */
public class SubmitPlan extends AdapterService {

	/**
	 * log ��־
	 */
	private final Log log = LogFactory.getLog(SubmitPlan.class);
   
    /**
     * con  //���ݿ����Ӷ���
     */
    Connection con = null;

    /**
    * workOrder // ���
    */
    private String workOrder=null;

    /**
    * overTime // ��������
    */
    private String overTime=null;
   
    /**
     * produceUnit  // ������Ԫid
     */
    private String produceUnit=null;
    
     /**
     * saveversion // �Ƿ����ɰ汾��ʶ trueΪ��
     */
    private String saveversion=null;
   
    /**
     * user // �û�id
     */
    private String user=null;
   
    /**
     * stmt �����ݿⷢ��sql���
     */
    private Statement stmt=null;
	

	 public boolean checkParameter(IMessage message, String processid) {
			
	        con = (Connection) message.getOtherParameter("con");
	        workOrder=message.getUserParameterValue("workOrder");
	        overTime=message.getUserParameterValue("overTime");
	        produceUnit=message.getUserParameterValue("produceUnit");
	     
	        saveversion=message.getUserParameterValue("saveversion");
	        user=message.getUserParameterValue("user");
	      //���log��Ϣ
		    String debug="workOrder:" +workOrder 
			+ " overTime:"+overTime+ " " +
			"produceUnit:"+produceUnit+ " " +
			"saveversion:"+saveversion+ " " +
			"user:"+user+ "\n";
		    log.info("���״̬ת���Ĳ���: " + debug);
	        if(produceUnit==null){
	        	   message.addServiceException(new ServiceException(
	   					ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
	   					processid, new java.util.Date(), null));
	   			return false;
	           }
	        

	        return true;
	    }
	 public ExecuteResult doAdapterService(IMessage message, String processid) throws SQLException, Exception {
	        try {
				try {
					// �汾�����λֵ
					String first="01";
					// �õ��û�����
					int  userId = Integer.parseInt(user);
			        IDAO_UserManager dao_UserManager = DAOFactory_UserManager.getInstance(DataBaseType.getDataBaseType(con));
			        String sql = dao_UserManager.getSQL_SelectUserById(userId);
			        stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			        ResultSet rs = stmt.executeQuery(sql);
			        
			        if(rs.next())
			        	user = rs.getString("CUSRNAME");
			        else
			        	user = null;

					PlanFactory factory=new PlanFactory();
					VersionFactory versionfactory=new VersionFactory();
				     // �õ��汾�ź���λ
				String code=factory.getversioncodewhensubmit(overTime, Integer.parseInt(produceUnit), workOrder, con);
				if(!code.equals("")&&code!=null)
				   {
						    int leng=code.length();
					    	//ʮλ
			  	            String code1=code.substring(leng-2,leng-1);
					        //	��λ
					    	String code2=code.substring(leng-1,leng);
					        int gewei=Integer.parseInt(code2)+1;
					    	if(gewei<=9){
					    		first=code1+String.valueOf(gewei);
					    	}
					    	else{
					    	  int shiwei=Integer.parseInt(code1)+1;
					    	   first=String.valueOf(shiwei)+"0";
					    		
					    	}
					    	
					    	
					    	
			       }
				 
				       ProduceUnitFactory unitfactory=new ProduceUnitFactory();
					   ProduceUnit produceunit=new ProduceUnit();
					      // ͨ��������Ԫid��ȡ������Ԫ��ͬʱ���ɰ汾��
			           produceunit=unitfactory.getProduceUnitbyId(Integer.parseInt(produceUnit), con);
					     // ������Ԫ��
			           String str_name=produceunit.getStr_name();
					       
					   String [] date=new String [3];
					   date =overTime.split("-");
						     // ��������ȥ����-��
				       String datestring= date[0]+date[1]+date[2];
				         // �õ��汾��
				       String versioncode=datestring+str_name+workOrder+first;
				     // ����û��������°汾�Ļ�
				       if(saveversion.equals("true")){
				    	// �����°汾���汾��Ϣ
						factory.submitplan(overTime, Integer.parseInt(produceUnit),  workOrder, versioncode, con);
					    Version version=new Version();
					    version.setUser(user);
					    version.setVersionCode(versioncode);
					    versionfactory.saveVersion(version, con);
					    message.setOutputParameter("saveversion", versioncode);
					}// ����
					else{
						// ��ɾ���滻�İ汾����
						factory.deletePlanbyversioncode(code, con);
						// �ڰ��ύ��������Ϊ��һ���汾������
						factory.submitplan(overTime, Integer.parseInt(produceUnit),  workOrder, code, con);
						 message.setOutputParameter("saveversion", code);
					}
					  log.debug( "�ύ�ɹ�!");
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
		   workOrder=null;
		   overTime=null;
		   produceUnit=null;
		   if(stmt!=null){
		     stmt.close();
		     }
		  
	    }
}
