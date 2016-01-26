package com.qm.mes.pm.service.distributionaccept;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.AdapterService;
import com.qm.mes.framework.ExecuteResult;
import com.qm.mes.framework.IMessage;
import com.qm.mes.framework.ServiceException;
import com.qm.mes.framework.ServiceExceptionType;
import com.qm.mes.pm.bean.DistributionAccept;
import com.qm.mes.pm.bean.DistributionDoc;
import com.qm.mes.pm.factory.DistributionAcceptFactory;
import com.qm.mes.pm.factory.DistributionDocFactory;
import com.qm.mes.ra.factory.InstructionFactory;

/**
 * �������ȷ�ϵ�
 * 
 * @author Ypeng
 * ����   AddDistriAccept
 */
public class AddDistributionAccept extends AdapterService {
	/**
	 * �������
	 */
	private Connection con = null;
	/**
	 * ������Ԫ��
	 */
	private int produnitid;
	/**
	 * ����ֵ
	 */
	private String materielValue = null;
	/**
	 * ��������
	 */
	private String materiel = null;
	/**
	 * ����ָʾ���б�
	 */
	List<DistributionDoc> list_DistributionDoc = new ArrayList<DistributionDoc>();
	/**
	 * ��־
	 */
	private final Log log = LogFactory.getLog(AddDistributionAccept.class);
	/**
	 * ����ȷ�ϵ�����
	 */
	DistributionAcceptFactory factory = new DistributionAcceptFactory();
	/**
	 * ָ���
	 */
	InstructionFactory instructionFactory = new InstructionFactory();
	/**
	 * ����ָʾ������
	 */
	DistributionDocFactory distributionDocFactory = new DistributionDocFactory();
	
	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
		produnitid = Integer.parseInt(message.getUserParameterValue("produnitid"));
		materielValue = message.getUserParameterValue("materielValue");
		try {
			materiel = instructionFactory.getInstructionbymaterile(materielValue,produnitid, con).getProduceType();
			list_DistributionDoc=distributionDocFactory.getDistributionDocsByRequestProUnitId(produnitid,con);
		
		
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {
			try {
				for(DistributionDoc dd:list_DistributionDoc){
					if(dd.getMaterielType().equals(materiel)){
						DistributionAccept distributionAccept = new DistributionAccept();
						distributionAccept.setDisDocId(dd.getId());
						distributionAccept.setState(0);
						distributionAccept.setMateriel(materiel);
						factory.saveDistributionAccept(distributionAccept, con);
						log.debug("����ָʾ���ţ�"+dd.getId()+"��״̬�ţ�"+0+"���������ͣ�"+materiel);
					}
					
				}
				log.info("�������ȷ�ϵ�����ɹ���");
			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), processid, new Date(), sqle));
				log.error("���ݿ��쳣���жϷ���");
				return ExecuteResult.fail;
			}
		} catch (Exception e) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
					processid, new java.util.Date(), e));
			log.error("δ֪�쳣���жϷ���");
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
	}

	@Override
	public void relesase() throws SQLException {
		con = null;

	}

}
