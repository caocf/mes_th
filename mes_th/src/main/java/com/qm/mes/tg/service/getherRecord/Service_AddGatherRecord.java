/*
 * create by : chenpeng
 * date:20080721
 * description: about gatherRecord 
 */
package com.qm.mes.tg.service.getherRecord;

import java.sql.Connection;
import java.sql.SQLException;
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
import com.qm.mes.tg.bean.GatherRecord;
import com.qm.mes.tg.bean.MaterielRule;
import com.qm.mes.tg.factory.MaterielRuleFactory;
import com.qm.mes.tg.factory.RecordFactory;

public class Service_AddGatherRecord extends AdapterService {
	private Connection con = null;
	// ���
	private String id;
	// �ɼ������
	private String gatherId = null;
	// ����������
	private String materielName = null;
	// ������ֵ
	private String materielValue = null;
	// �û�id
	private String userId = null;
	
	private String strPrs = null;
	
	// �ɼ��������б���һλ�������ϵ�ֵ�����������������ϵ�ֵ
	private List<String> prs = new ArrayList<String>();
	//��־
	private final Log log = LogFactory.getLog(Service_AddGatherRecord.class);

	// ��";"������Stringת��ΪList;
	public List<String> strToList(String prsStr) {
		List<String> inPrs = new ArrayList<String>();
		while (prsStr.indexOf(";") != -1) {
			inPrs.add(prsStr.substring(0, prsStr.indexOf(";")));
			prsStr = prsStr.substring((prsStr.indexOf(";") + 1));
		}
		inPrs.add(prsStr);
		return inPrs;
	}

	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
		id = message.getUserParameterValue("id");
		gatherId = message.getUserParameterValue("gatherId");
		materielName = message.getUserParameterValue("materielName").trim();
		materielValue = message.getUserParameterValue("materielValue").trim();
		userId = message.getUserParameterValue("userId");
		strPrs = message.getUserParameterValue("strPrs");
		
		if (id == null || gatherId == null || materielName == null
				|| materielValue == null || userId == null || strPrs == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
					processid, new java.util.Date(), null));
			log.fatal("��ӹ����¼��-��¼�š��ɼ���š�������������ֵ���û���š�������������ֵ����Ϊ�ղ���");
			return false;
		}
		return true;
	}

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {
			RecordFactory recordFactory = new RecordFactory();
			// ������֤���򹤳�
			MaterielRuleFactory materielRuleFactroy = new MaterielRuleFactory();

			// ��֤�����б���һλ�������ϵ���֤�������������������ϵ���֤����
			List<MaterielRule> mrs = new ArrayList<MaterielRule>();

			GatherRecord gr = new GatherRecord(); // �����¼
			gr.setId(Integer.valueOf(id));
			gr.setGatherId(Integer.valueOf(gatherId));
			gr.setMaterielName(materielName);
			gr.setMaterielValue(materielValue);
			gr.setUserId(Integer.valueOf(userId));
			// String������ת��Ϊlist��
			prs = this.strToList(strPrs);
			mrs = materielRuleFactroy.getListByGid(Integer.valueOf(gatherId),
					con);
			log.debug("�����¼�ţ�"+id+"���ɼ���ţ�"+gatherId+"����������"+materielName+"������ֵ��"+materielValue+
					"���û���ţ�"+userId+"��������������"+strPrs+"����֤�����б�"+mrs);
			recordFactory.saveRecord(gr, prs, mrs, con);
			log.info("��ӹ����¼�ɹ�");
			return ExecuteResult.sucess;
		} catch (Exception e) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣",
					this.getId(), processid, new Date(), null));
			log.error("���ݿ��쳣");
			e.printStackTrace();
			return ExecuteResult.fail;
		}
	}

	@Override
	public void relesase() throws SQLException {
		id = null;// ���
		gatherId = null;// �ɼ������
		materielName = null;// ����������
		materielValue = null;// ������ֵ
		userId = null;// �û�id
		con = null;

	}
}
