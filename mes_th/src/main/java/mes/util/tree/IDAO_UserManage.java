package mes.util.tree;

public interface IDAO_UserManage {
	//����û����Ƿ����
	public String getSQL_ExistUser(String username);
	
	//�����û�״̬ �Ƿ���ͣ��
	public String getSQL_UserState(String userid);
	
	//����û����������Ƿ���ȷ
	public String getSQL_UserProof(String username,String password) ;
	
	//�����û�������ȡ���Ӧ����ʽ��
	public String getSQL_CssFile(String userid);
	
	//�����û�������ȡ���ɫ��
	public String getSQL_RoleNo(String userid);
	
	//���ݽ�ɫ�ţ���ȡ��ɫ�ļ���
	public String getSQL_Rank(String roleno);
	
	//��ȡϵͳ����ֵ
	public String getSQL_ParameterValue(String key);
	
	//���ݽ�ɫ�ţ���ȡ��ӭҳ
	public String getSQL_WelcomePage(String roleno);
	
	//���ݽ�ɫ�Ż�ȡȨ�޴�
	public String getSQL_PowerString(String roleno);
		
	//���ݹ��ܺŻ�ȡ�䰲ȫ��ʶ
	public String getSQL_SafeMark(String functionid);
	
	//��ȡ������Ϣ
	public String getSQL_FuncitonInfo(String functionid);
	
	//���ݹ��ܺŻ�ȡ���ӽڵ��Ҷ
	public String getSQL_AllSubFunctionID(String functionid);
	
	//�����û�����ȡ�û���
	public String getSQL_userID(String username);
	
	//�����û��ţ���ȡ���û�������Ϣ
	public String getSQL_UserInfo_UserID(String userid);
	
	
	//===========================================================
	//�����û�ҳ��������
	public String getSQL_updateUserInterface(String userid,String color);

}
