package com.qm.mes.util.tree;

import com.qm.mes.util.tree.IDAO_UserManage;

public class ADAO_UserManage implements IDAO_UserManage {
	// ����û����Ƿ����
	public String getSQL_ExistUser(String username) {
		return "select count(*) from data_user where cusrname='" + username
				+ "'";
	}

	// �����û�״̬ �Ƿ���ͣ��
	public String getSQL_UserState(String userid) {
		return "select cstate from data_user where nusrno=" + userid + "";
	}

	// ����û���������(md5���ܺ������)�Ƿ���ȷ
	public String getSQL_UserProof(String username, String password) {
		return "select count(*) from data_user where cusrname='" + username
				+ "' and cpassword='" + password + "'";
	}

	// �����û�������ȡ���Ӧ����ʽ��
	public String getSQL_CssFile(String userid) {
		return "select ccssfile from interface_user where nusrno=" + userid
				+ "";
	}

	// �����û�������ȡ���ɫ��
	public String getSQL_RoleNo(String userid) {
		return "select nroleno from data_user where nusrno=" + userid + "";
	}

	// ���ݽ�ɫ�ţ���ȡ��ɫ�ļ���
	public String getSQL_Rank(String roleno) {
		return "select crank from data_role where nroleno=" + roleno + "";
	}

	// ��ȡϵͳ����ֵ
	public String getSQL_ParameterValue(String key) {
		return "select cvalue from system_parameter where CKEY='" + key + "'";
	}

	// ���ݽ�ɫ�ţ���ȡ��ӭҳ
	public String getSQL_WelcomePage(String roleno) {
		return "select CWELCOMEPAGE from data_role where NROLENO='" + roleno
				+ "'";
	}

	// ���ݽ�ɫ�Ż�ȡȨ�޴�
	public String getSQL_PowerString(String roleno) {
		return "select CPOWERSTRING from data_role where NROLENO='" + roleno
				+ "'";
	}

	// ���ݹ��ܺŻ�ȡ�䰲ȫ��ʶ
	public String getSQL_SafeMark(String functionid) {
		return "select CSAFEMARK from data_function where NFUNCTIONID="
				+ functionid + "";
	}

	// ��ȡ������Ϣ
	public String getSQL_FuncitonInfo(String functionid) {
		return "select nfunctionid,cfunctionname,curl,nupfunctionid,crank,cnodetype,cstate,csafemark,cnote,cenabled,flo_order from data_function where nfunctionid="
				+ functionid + "";
	}

	// ���ݹ��ܺŻ�ȡ���ӽڵ��Ҷ,��������˳�����ʾ
	public String getSQL_AllSubFunctionID(String functionid) {
		return "select nfunctionid from data_function where nupfunctionid="
				+ functionid + "  order by flo_order";
	}

	// �����û�����ȡ�û���
	public String getSQL_userID(String username) {
		return "select nusrno from data_user where cusrname='" + username + "'";
	}

	// �����û��ţ���ȡ���û�������Ϣ
	public String getSQL_UserInfo_UserID(String userid) {
		return "select NUSRNO,CUSRNAME,CPASSWORD,NROLENO,NEMPLOYEEID,CSTATE,NLASTUPDATEUSER,DLASTUPDATETIME,CNOTE,CENABLED  from data_user where nusrno="
				+ userid + "";
	}

	// ==============================
	public String getSQL_updateUserInterface(String userid, String color) {
		return "update interface_user set CCSSFILE='" + color
				+ "' where NUSRNO=" + userid + "";
	}

}
