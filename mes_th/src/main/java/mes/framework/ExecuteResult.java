package mes.framework;

/**
 * ���н����״̬��
 * 
 * @author �Ź��� 2007-6-21
 */
public enum ExecuteResult {
	/**
	 * �ɹ�
	 */
	sucess,
	/**
	 * ʧ��
	 */
	fail;

	public String toString() {
		switch (this) {
		case sucess:
			return "�ɹ�";
		case fail:
			return "ʧ��";
		}
		return "�ɹ�";
	}
}
