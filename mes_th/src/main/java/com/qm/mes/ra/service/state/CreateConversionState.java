package com.qm.mes.ra.service.state;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.AdapterService;
import com.qm.mes.framework.ExecuteResult;
import com.qm.mes.framework.IMessage;
import com.qm.mes.framework.ServiceException;
import com.qm.mes.framework.ServiceExceptionType;
import com.qm.mes.ra.bean.ConversionState;
import com.qm.mes.ra.dao.DAO_ConversionState;
import com.qm.mes.ra.dao.DAO_ConversionStateForOracle;
import com.qm.mes.ra.factory.StateManager;
/**
 * ���ָ��״̬��Ϣ
 * 
 * @author xujia
 * 
 */
public class CreateConversionState extends AdapterService {
	/**
	 * �������
	 */
	private Connection con = null;
	/**
	 *  ԭ״̬
	 */
	private String fromstate = null;
	/**
	 * ��ת״̬
	 */
	private String tostate= null;
	/**
	 * ת������
	 */
	private String string_desc=null;
	StateManager Manager = new StateManager();
	Statement stmt = null;
	DAO_ConversionState dao2=new DAO_ConversionStateForOracle();	
  	private ResultSet rs1 = null;
  	private String sql1="";
	private final Log log = LogFactory.getLog(CreateConversionState.class);
	
	@Override
	public boolean checkParameter(IMessage message, String processid) {
		//����message�������Ĳ���
		try{
			//��ȡ����	
			con = (Connection) message.getOtherParameter("con"); 
			stmt = con.createStatement();   //��ʼ��
			fromstate = message.getUserParameterValue("fromstate");
			tostate = message.getUserParameterValue("tostate");
			string_desc = message.getUserParameterValue("string_desc");
			sql1=dao2.getAllConversionState();
			//	���log��Ϣ
			String debug="״̬����fromstate:" + fromstate 
			+ " ��ʽ����tostate:"+tostate+ " ������string_desc="+string_desc+ "\n";
			log.info("���״̬ת���Ĳ���: " + debug);
			// У���Ƿ���Ϣ�ظ�
			rs1=stmt.executeQuery(sql1);
			while(rs1.next())	    
			{  
				if((rs1.getInt(2)==Integer.parseInt(fromstate)) && (rs1.getInt(3)==Integer.parseInt(tostate)))
				{  
					message.addServiceException(new ServiceException(
							ServiceExceptionType.PARAMETERLOST, "����ת�����ظ��������¶���", this.getId(),
							processid, new java.util.Date(), null));
					return false;
				}
			}
			rs1.close();	   
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {
			try {   //ͬ״̬����ת��
				if ((Integer.parseInt(fromstate)) == (Integer.parseInt(tostate)) ) {
						message.addServiceException(new ServiceException(
								ServiceExceptionType.UNKNOWN, "��ͬ״̬֮�䲻��ת����������ѡ��",
								this.getId(), processid, new Date(), null));
						log.fatal("��ͬ״̬֮�䲻��ת��");
						return ExecuteResult.fail;
				} else {
					//���ù�����ִ�д���
					ConversionState state = new ConversionState();
					state.setFromstate(Integer.parseInt(fromstate));
					state.setTostate(Integer.parseInt(tostate));
					state.setDesc(string_desc);
					Manager.createConversionState(state, con);
					log.debug( "��Ӵ��������ɹ�!");
				}
			}catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), processid, new Date(), sqle));
				log.error("���״̬�������ʱ,���ݿ��쳣"	+ sqle.toString());
				return ExecuteResult.fail;
			} 
		} catch (Exception e) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
					processid, new java.util.Date(), e));
			log.error( "���״̬�������ʱ,δ֪�쳣" + e.toString());
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
	}

	@Override
	public void relesase() throws SQLException {
		fromstate = null;
		tostate = null;
		string_desc = null;
	}
}


