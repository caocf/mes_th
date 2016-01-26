package com.qm.mes.tg.service;

import java.util.ArrayList;
import java.util.List;

import com.qm.mes.tg.bean.MaterielRule;
import com.qm.mes.tg.util.IMaterielValidate;

public abstract class AbstractTgService implements ITgService {
	List<String> errorList = new ArrayList<String>();// �����б�
	List<String> validateList = new ArrayList<String>();// ��֤��Ϣ�Ĵ����б�
	int userId;
	int gatherId;

	/**
	 * ���û����ɼ�����òɼ��¼���
	 * 
	 * @param userId
	 *            �û�id
	 * @param gatherId
	 *            �ɼ������
	 */
	AbstractTgService(int userId, int gatherId) {
		this.userId = userId;
		this.gatherId = gatherId;
	}

	public List<String> getErrorList() {
		errorList.addAll(validateList);
		return errorList;
	}

	/**
	 * �洢������Ϣ��errorList
	 */
	public void saveErrorMessage(String message) {
		if (message != null) {
			errorList.add(message);
		}
	}

	/**
	 * ������֤����
	 */
	public boolean validate(List<String> in, List<MaterielRule> validates) {
		boolean result = true;
		if (in == null || in.size() == 0) {
			validateList.add("�����������ȷ");
			return false;
		}
		if (validates == null || validates.size() == 0) {
			validateList.add("��֤��������ȷ");
			return false;
		}
		if (in.size() != validates.size()) {
			validateList.add("��������֤��ƥ��");
			return false;
		}
		for (int i = 0; i < validates.size(); i++) {
			MaterielRule mr = validates.get(i);
			IMaterielValidate mv = null;
			try {
				mv = (IMaterielValidate) Class.forName(mr.getValidate())
						.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			if (!mv.validate(in.get(i))) {
				if (result)
					result = false;
				validateList.add(mr.getName() + ":" + in.get(i) + "  û��ͨ����֤");
			}
		}

		return result;
	}
}
