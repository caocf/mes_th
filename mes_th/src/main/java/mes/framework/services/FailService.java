package mes.framework.services;

import mes.framework.DefService;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.IService;

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
