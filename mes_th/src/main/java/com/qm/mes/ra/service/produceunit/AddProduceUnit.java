package com.qm.mes.ra.service.produceunit;

import java.sql.*;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.AdapterService;
import com.qm.mes.framework.ExecuteResult;
import com.qm.mes.framework.IMessage;
import com.qm.mes.framework.ServiceException;
import com.qm.mes.framework.ServiceExceptionType;
import com.qm.mes.ra.bean.*;
import com.qm.mes.ra.factory.*;

/**���������Ԫ
 * @author л����
 *
 */
public class AddProduceUnit extends  AdapterService{ 
	private final Log log = LogFactory.getLog(AddProduceUnit.class);
	private Connection con=null;
	/**
	 * ������Ԫ����
	 */
	private String Str_name=null;
	/**
	 * ������Ԫ���
	 */
	private String Str_code=null;
	/**
	 * ����̨�ݸ���
	 */
	private String Int_instCount=null; 
	/**
	 * δ����ָ��״̬
	 */
	private String Int_instructStateID=null;
    /**
     * �Ƿ���Ҫ��֤�ƻ���Ϣ
     */
    private String Int_planIncorporate=null;
	private String int_materielruleid=null;

	public boolean checkParameter(IMessage message, String processid) {
		con=(Connection)message.getOtherParameter("con");
		Str_name=message.getUserParameterValue("Str_name");
		Str_code=message.getUserParameterValue("Str_code");
		Int_instructStateID=message.getUserParameterValue("Int_instructStateID");
		Int_planIncorporate=message.getUserParameterValue("Int_planIncorporate");
		Int_instCount=message.getUserParameterValue("Int_instCount");
		int_materielruleid=message.getUserParameterValue("int_materielruleid");
        //���log��Ϣ
	    String debug="״̬����Int_instructStateID:" +Int_instructStateID 
		+ " Int_planIncorporate:"+Int_planIncorporate+ " Int_instCount:"+Int_instCount+ "\n";
	    log.info("���״̬ת���Ĳ���: " + debug);
		if (Str_name == null) {
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
		
			ProduceUnit produceUnit=new ProduceUnit();
			produceUnit.setStr_name(Str_name);
			produceUnit.setStr_code(Str_code);
			produceUnit.setInt_instructStateID(Integer.parseInt(Int_instructStateID));
			produceUnit.setInt_planIncorporate(Integer.parseInt(Int_planIncorporate));
			produceUnit.setInt_instCount(Integer.parseInt(Int_instCount));
			produceUnit.setInt_Type(1);
			produceUnit.setInt_delete(0);
			produceUnit.setInt_materielRuleid(Integer.parseInt(int_materielruleid));
			ProduceUnitFactory factory=new ProduceUnitFactory();
			factory.saveProduceUnit(produceUnit, con);
			log.debug( "������ĵ�Ԫ�����ɹ�!");
		}
		catch (SQLException sqle) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
					.getId(), processid, new Date(), sqle));
			log.error("���浥Ԫ����ʱ,���ݿ��쳣"	+ sqle.toString());
			return ExecuteResult.fail;
		}
		catch (Exception e) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
					processid, new java.util.Date(), e));
			log.error( "���浥Ԫ����ʱ,δ֪�쳣" + e.toString());
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
	}
	public void relesase() throws SQLException {
	    con = null;
	    Str_name=null;
	    Str_code=null;
	    Int_instructStateID=null;
	    Int_planIncorporate=null;
	    Int_instCount=null;
    }
}
