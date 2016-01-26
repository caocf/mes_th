package com.qm.mes.framework;

/**
 * ����ķ��񣬴˷���ʵ���˷���ӿ��е����ͨ�õķ�����<br>
 * �����û�������Ĵ�������<br>
 * <font color="red"><b>����̳�</b></font>
 * 
 * @author �Ź��� 2007-6-21
 */
public abstract class DefService extends DefElement implements IService {

	private String className = "";

	public String getClassName() {
		return className;
	}

	public void setClassName(String classname) {
		this.className = classname;
	}

	@SuppressWarnings("deprecation")
	public ExecuteResult redoService(IMessage message, String processid) {
		this.undoService(message, processid);
		this.doService(message, processid);
		return ExecuteResult.sucess;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see zgl.realtool.mes.framework.IService#undoService(zgl.realtool.mes.framework.IMessage,
	 *      java.lang.String)
	 */
	public ExecuteResult undoService(IMessage message, String processid) {
		return ExecuteResult.fail;
	}

}
