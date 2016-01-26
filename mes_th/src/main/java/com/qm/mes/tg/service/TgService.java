package com.qm.mes.tg.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.qm.mes.tg.bean.GatherRecord;
import com.qm.mes.tg.bean.MaterielRule;
import com.qm.mes.tg.factory.MaterielRuleFactory;
import com.qm.mes.tg.factory.RecordFactory;

public class TgService extends AbstractTgService {

	TgService(int userId, int gatherId) {
		super(userId, gatherId);
	}

	/**
	 * inΪ�ɼ��������б��洢���ݣ��ڴ˹����н�����洢��������һ�������б� ����б���Ϊ��˵������ɹ���������Խ�������Ϣ����������������
	 */
	public List<String> savaTgRecord(List<String> in) {
		try {
			MaterielRuleFactory imrdao = null;
			//���Connection����ʱ��
			Connection con = null;
			// ȡ����֤����
			List<MaterielRule> mrs;
			mrs = imrdao.getListByGid(gatherId, con);
			if (mrs.size() == 0) {
				this.saveErrorMessage("��������֤����");
				return getErrorList();
			}
			if (validate(in, mrs)) {
				RecordFactory irdao = null;
				GatherRecord gr = new GatherRecord();
				gr.setGatherId(gatherId);
				gr.setUserId(userId);
				irdao.saveRecord(gr, in, mrs, con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getErrorList();
	}
}
