package com.qm.mes.ra.service.produceunit;

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
import com.qm.mes.ra.factory.ProduceUnitFactory;

/**ɾ��������Ԫ
 * @author л����
 *
 */
public class DeleteProduceUnit extends AdapterService {
	private final Log log = LogFactory.getLog(DeleteProduceUnit.class);
	private Connection con=null;
	/**
	 * �汾��
	 */
	private String int_id=null;
	public boolean checkParameter(IMessage message, String processid) {
			con = (Connection) message.getOtherParameter("con");
			int_id=message.getUserParameterValue("int_id");
           //���log��Ϣ
		    String debug="int_id:" +int_id + "\n";
		    log.info("���״̬ת���Ĳ���: " + debug);
			if (int_id== null) {
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
				ProduceUnitFactory factory=new ProduceUnitFactory();
				factory.deleteProduceUnitById(Integer.parseInt(int_id), con);
				log.debug( "���ɾ����Ԫ�����ɹ�!");
				
				new ProduceUnitFactory().delCunit(int_id, con);
				log.debug("ɾ����������Ԫ�ɹ���");
			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
						.getId(), processid, new Date(), sqle));
				log.error("ɾ����Ԫ����ʱ,���ݿ��쳣"	+ sqle.toString());
				return ExecuteResult.fail;
			}
		} catch (Exception e) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
					processid, new java.util.Date(), e));
			log.error( "ɾ����Ԫ����ʱ,δ֪�쳣" + e.toString());
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
	}
	 
	 @Override
	 public void relesase() throws SQLException {
		 con = null;
	     int_id=null;
	   }
	 
	 
}
