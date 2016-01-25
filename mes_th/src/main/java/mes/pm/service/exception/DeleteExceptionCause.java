package mes.pm.service.exception;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import mes.framework.AdapterService;
import mes.framework.DataBaseType;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import mes.pm.dao.DAO_Exception;
import mes.pm.factory.ExceptionFactory;
import mes.system.dao.DAOFactoryAdapter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * ɾ���쳣����
 * 
 * @author Xujia
 * 
 */
public class DeleteExceptionCause  extends AdapterService  {
	/**
	 * �������
	 */
	private Connection con = null;
	/**
	 * ����id
	 */
	private String int_id = null;
	private final Log log = LogFactory.getLog(DeleteExceptionType.class);
	Statement stmt = null;
	ResultSet rs = null;
	ResultSet rs1=null;
	//������ǰ
	ExceptionFactory factory = new ExceptionFactory();		

	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
		int_id = message.getUserParameterValue("int_id");
	
		if (int_id == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
					processid, new java.util.Date(), null));
			
			return false;
		}
		return true;
	}

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid) throws SQLException, Exception {
        try {
			try {	
				DAO_Exception dao = (DAO_Exception) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_Exception.class);
		 stmt = con.createStatement();
		 rs = stmt.executeQuery(dao.checkExceptionCauseById(Integer.parseInt(int_id)));
		 log.info("���ExceptionRecord�����Ƿ����쳣ԭ��ŵ�sql:"+ dao.checkExceptionCauseById(Integer.parseInt(int_id)));
		 int count = 0;
		 if (rs.next()) {
			count = rs.getInt(1);
		  }
		 rs1=stmt.executeQuery(dao.getExceptionCauseById(Integer.parseInt(int_id)));
		 log.info("���Ƿ����ã�"+dao.getExceptionCauseById(Integer.parseInt(int_id)));
		 int state=0;
		 if(rs1.next())
		 { state=rs1.getInt("int_state");
		 }
		 log.info("������Ϣ��"+state);
		 if ((Integer.parseInt(int_id))==21 ||(Integer.parseInt(int_id))==6)  {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.UNKNOWN, "����/�豸ԭ����ɾ��!",
						this.getId(), processid, new Date(), null));
				log.fatal("����/�豸ԭ����ɾ��");
				return ExecuteResult.fail;
			} else if(count > 0 || state==1){
				message.addServiceException(new ServiceException(
						ServiceExceptionType.UNKNOWN, "���쳣ԭ�����쳣��¼�����û��������ã�����ɾ��!",
						this.getId(), processid, new Date(), null));
				log.fatal("���쳣ԭ�����쳣��¼�����û������ã�����ɾ��");
				return ExecuteResult.fail;
			}else {		
			
				factory.delExceptionCauseById(new Integer(int_id), con);
				log.debug("���������ɹ�!");
			}	
			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), processid, new Date(), sqle));
				log.error("ɾ���쳣ԭ��ʱ,δ֪�쳣" + sqle.toString());
				return ExecuteResult.fail;
			}
		} catch (Exception e) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
					processid, new java.util.Date(), e));
			log.error("ɾ���쳣ԭ��ʱ,δ֪�쳣" + e.toString());
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
    }

	@Override
	public void relesase() throws SQLException {
		int_id = null;
		con = null;

	}

}