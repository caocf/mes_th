package com.qm.mes.framework.services;

import com.qm.mes.framework.DefService;
import com.qm.mes.framework.ExecuteResult;
import com.qm.mes.framework.IMessage;
import com.qm.mes.framework.IService;

/**
 * ����һ������ʧ��״̬�����ͣ����ڲ������̻ع��ġ�
 * @author �Ź���
 * 2007-12-18
 */
public class FailService extends DefService implements IService {

	public ExecuteResult doService(IMessage message, String processid) {
		return ExecuteResult.fail;
	}
}
