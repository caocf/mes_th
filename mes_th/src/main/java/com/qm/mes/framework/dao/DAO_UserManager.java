package com.qm.mes.framework.dao;

import java.util.List;
import java.util.Vector;

abstract class DAO_UserManager implements IDAO_UserManager {

	// ɾ������
	public String getSQL_DeleteFunction(int functionid) {
		return "delete from data_function where nfunctionid=" + functionid + "";
	}

	// ��ȡȨ�޴�����
	public String getSQL_QueryPowerStringLength() {
		return "select max(length(cPOWERSTRING)) as PowerStringSize from data_role";
	}

	public String getSQL_QueryFunctionForUpfunctionid(int functionid) {
		return "select nfunctionid,cnodetype from data_function where nupfunctionid="
				+ functionid + "";
	}

	// ��ȡ�ڵ�����
	public String getSQL_QueryNodeTypeForFunctionId(int functionid) {
		return "select cnodetype from data_function where nfunctionid="
				+ functionid + "";
	}

	// ��ѯ�����б�
	public String getSQL_QueryAllFunctionsForRank(String userrolerank,
			String functioninfo, String method) {
		String sql = "";
		if (functioninfo != null && (!functioninfo.equals(""))) {
			// �����ܺŲ�ѯ
			if (method.equals("ById"))
				sql = "SELECT nfunctionid,CFUNCTIONNAME,cnodetype,cstate,cenabled FROM data_function where crank>='"
						+ userrolerank
						+ "' and nfunctionid ='"
						+ functioninfo
						+ "'";
			// ����������ѯ
			if (method.equals("ByName"))
				sql = "SELECT nfunctionid,CFUNCTIONNAME,cnodetype,cstate,cenabled FROM data_function where cfunctionname like '%"
						+ functioninfo + "%' order by nfunctionid";
		} else
			sql = "SELECT nfunctionid,CFUNCTIONNAME,cnodetype,cstate,cenabled FROM data_function where crank>='"
					+ userrolerank + "' order by nfunctionid";

		return sql;
	}

	public String getSQL_QueryLastNodeForNodeType() {
		return "select nfunctionid,cfunctionname from data_function where cnodetype='2' or cnodetype='1' order by nfunctionid";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mes.framework.services.role.IDAORole#getSQL_DeleteRole(java.lang.String,
	 *      java.lang.String)
	 */
	public List<String> getSQL_DeleteRole(String roleno, String userid) {
		List<String> v = new Vector<String>();
		String sql = "delete from data_role where NROLENO=" + roleno;
		v.add(sql);
		return v;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mes.framework.services.role.IDAORole#getSQL_QueryAllFunction()
	 */
	public String getSQL_QueryAllFunction() {
		return "select NFUNCTIONID,CFUNCTIONNAME,CURL,NUPFUNCTIONID,CRANK"
				+ ",CNODETYPE,CSTATE,NLASTUPDATEUSER,DLASTUPDATETIME,"
				+ "CSAFEMARK,CNOTE,CENABLED from data_function order by nfunctionid";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mes.framework.services.role.IDAORole#getSQL_QueryRole(java.lang.String,
	 *      java.lang.String)
	 */
	public String getSQL_QueryRole(String userrolerank, String roleinfo,
			String method) {

		String sql = "";
		if (roleinfo != null && (!roleinfo.equals(""))) {
			// ����ɫ�Ų�ѯ
			if (method.equals("ById"))
				sql = "SELECT nroleno,crolename,CENABLED FROM data_role where crank>='"
						+ userrolerank + "' and nroleno ='" + roleinfo + "'";
			// ����ɫ����ѯ
			if (method.equals("ByName"))
				sql = "SELECT nroleno,crolename,CENABLED FROM data_role where crolename like '%"
						+ roleinfo + "%' order by nroleno";
		} else
			sql = "SELECT nroleno,crolename,CENABLED FROM data_role where crank>='"
					+ userrolerank + "' order by nroleno";
		return sql;
	}

	// ��ѯ�û�
	public String getSQL_QueryAllUserRoleNameNoForRank(String user_rolerank,
			String userinfo, String method) {
		String sql = "";
		if (userinfo != null && (!userinfo.equals(""))) {
			// ���û������ѯ
			if (method.equals("ById"))
				sql = "SELECT nusrno,cusrname,cenabled FROM data_user where nusrno='"
						+ userinfo + "' order by nusrno";
			// ���û����Ʋ�ѯ
			if (method.equals("ByName"))
				sql = "SELECT nusrno,cusrname,cenabled FROM data_user where cusrname='"
						+ userinfo + "' order by nusrno";
		} else
			sql = "SELECT nusrno,cusrname,cenabled FROM data_user order by nusrno";
		return sql;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mes.framework.services.role.IDAORole#getSQL_QueryRoleAndUser(java.lang.String)
	 */
	public String getSQL_QueryRoleAndUser(String roleno) {
		return "select * from data_role r LEFT OUTER JOIN data_user u on r.nlastupdateuser=u.nusrno where r.nroleno="
				+ roleno;
	}

	public String getSQL_QueryCountUserName(String username) {
		return "select count(*) from data_user where cusrname='" + username
				+ "'";
	}

	public String getSQL_QueryUserStateForUserID(String userid) {
		return "select cstate from data_user where nusrno=" + userid + "";
	}

	// ����û���������(md5���ܺ������)�Ƿ���ȷ
	public String getSQL_QueryCountUserNamePassword(String username,
			String password) {
		return "select count(*) from data_user where cusrname='" + username
				+ "' and cpassword='" + password + "'";
	}

	// �����û�������ȡ���Ӧ����ʽ��
	public String getSQL_QueryCssFileForUserID(String userid) {
		return "select ccssfile from interface_user where nusrno=" + userid
				+ "";
	}

	// �����û�������ȡ���ɫ��
	public String getSQL_QueryRoleNoForUserID(String userid) {
		return "select nroleno from data_user where nusrno=" + userid + "";
	}

	// ���ݽ�ɫ�ţ���ȡ��ɫ�ļ���
	public String getSQL_QueryRankForRoleNo(String roleno) {
		return "select crank from data_role where nroleno=" + roleno + "";
	}

	// ��ȡϵͳ����ֵ
	public String getSQL_QueryParameterValueForKey(String key) {
		return "select cvalue from system_parameter where CKEY='" + key + "'";
	}

	// ���ݽ�ɫ�ţ���ȡ��ӭҳ
	public String getSQL_QueryWelcomePageForRoleNo(String roleno) {
		return "select CWELCOMEPAGE from data_role where NROLENO='" + roleno
				+ "'";
	}

	// ���ݽ�ɫ�Ż�ȡȨ�޴�
	public String getSQL_QueryPowerStringForRoleNo(String roleno) {
		return "select CPOWERSTRING from data_role where NROLENO='" + roleno
				+ "'";
	}

	public String getSQL_QueryRoleForRoleNo(String roleno) {
		return "select NROLENO,CROLENAME,CRANK,CPOWERSTRING,CWELCOMEPAGE,"
				+ "NLASTUPDATEUSER,DLASTUPDATETIME,CNOTE,CENABLED "
				+ "from data_role where NROLENO='" + roleno + "'";
	}

	public String getSQL_QuerySafeMarkForFunctionID(String functionid) {
		return "select CSAFEMARK from data_function where NFUNCTIONID="
				+ functionid + "";
	}

	// ��ȡ������Ϣ
	public String getSQL_QueryFuncitonInfoForFunctionID(String functionid) {
		return "select nfunctionid,cfunctionname,curl,nupfunctionid,crank,cnodetype,cstate,csafemark,cnote,cenabled,NLASTUPDATEUSER,flo_order from data_function where nfunctionid="
				+ functionid + "";
	}

	// ���ݹ��ܺŻ�ȡ���ӽڵ��Ҷ
	public String getSQL_QueryAllSubFunctionIDsForFunctionID(String functionid) {
		return "select nfunctionid from data_function where nupfunctionid="
				+ functionid + "";
	}

	// �����û�����ȡ�û���
	public String getSQL_QueryUserIDForUserName(String username) {
		return "select nusrno from data_user where cusrname='" + username + "'";
	}

	// �����û��ţ���ȡ���û�������Ϣ
	public String getSQL_QueryUserInfoForUserID(String userid) {
		return "select NUSRNO,CUSRNAME,CPASSWORD,NROLENO,NEMPLOYEEID,"
				+ "CSTATE,NLASTUPDATEUSER,DLASTUPDATETIME,CNOTE,CENABLED "
				+ " from data_user where nusrno=" + userid + "";
	}

	public String getSQL_UpdateUserInterface(String userid, String color) {
		return "update interface_user set CCSSFILE='" + color
				+ "' where NUSRNO=" + userid + "";
	}

	public String getSQL_DeleteUser(String usrno) {
		return "delete from data_user where nusrno=" + usrno;
	}

	public String getSQL_QueryCountForUserNo(String usrno) {
		return "select count(*) from data_user where nusrno=" + usrno;
	}

	public String getSQL_QueryCountForUserName(String usrname) {
		return "select count(*) from data_user where cusrname='" + usrname
				+ "'";
	}

	public String getSQL_insertCss(String usrno, String cssfile) {
		return "insert into interface_user values(" + usrno + ",'" + cssfile
				+ "')";
	}

	public String getSQL_DeleteCss(String usrno) {
		return "delete interface_user where nusrno=" + usrno;
	}

	public String getSQL_QueryMaxusrno() {
		return "select max(nusrno) from data_user";
	}

	// TODO ��ɫ-�û� ��Զ�ı� data_user_role
	public String getSQL_DeleteDataUserRole(int usrno) {
		return "delete from DATA_USER_ROLE where NUSRNO=" + usrno + "";
	}

	public String getSQL_selectDataUserRole(int usrno) {
		return "select nusrno,nroleno,cdefault from data_user_role where nusrno="
				+ usrno + "";
	}
	
	public String getSQL_selectDataUserRole_Roleno(int roleno){
		return "select nusrno,nroleno,cdefault from data_user_role where NROLENO="+roleno+"";
	}
	
	public String getSQL_selectHaveRole(int usrno) {
		return "select d.NUSRNO,d.NROLENO,d.CDEFAULT,r.CROLENAME from DATA_USER_ROLE d,DATA_ROLE r"
				+ " where d.NROLENO=r.NROLENO and d.NUSRNO=" + usrno + "";
	}

	public String getSQL_DeleteUser_Roleno(int roleno) {
		return "delete from data_user where nusrno in ("
				+ "select nusrno from DATA_USER_ROLE  where NUSRNO in ("
				+ "select NUSRNO from DATA_USER_ROLE where NROLENO=" + roleno
				+ ") group by NUSRNO having count(NUSRNO)=1)";
	}
	
	public String getSQL_DeleteDataUserRole_Roleno(int roleno){
		return "delete from DATA_USER_ROLE where NROLENO="+roleno+"";
	}
	//л������� ��ɾ����ɫʱ��ѯ�Ƿ����û�������������ɾ
	 public String getSQl_QueryUserByrole(int nroleno){
		String sql="select du.nusrno  from data_user  du,data_role  dr ,data_user_role dur  " +
				" where dr. nroleno=dur.nroleno and dur.nusrno=du.nusrno and dr.nroleno="+nroleno;
		return sql;
	 }
	 
	//Ԭ����� ͨ��ID��ѯ�û�����
	 public String getSQL_SelectUserById(int id){
		 String sql = "select * from data_user where NUSRNO = " + id;
		 return sql;
	 }
	 
	//Ԭ����� ��ѯ�����û����е�����
	 public String getAllUsers(){
		 String sql = "select * from data_user";
		 return sql;
	 }
	 //�����ӣ����ڲ�ѯ������Ϣ
	 public String getSQL_AllFunctionsForRank(String userrolerank){
	 String sql = "SELECT nfunctionid,CFUNCTIONNAME,cnodetype,cstate,cenabled FROM data_function where crank>='"
			+ userrolerank + "' ";
	 return sql;
	 }
    //�����ӣ����ڲ�ѯuser��Ϣ
	 public String getSQL_AllUserRoleNameNoForRank(){
		 String sql = "SELECT nusrno,cusrname,cenabled FROM data_user ";
		 return sql;
	 }
   //�����ӣ����ڲ�ѯrole��Ϣ
	 public String getSQL_AllRoleNameNoForRank(String user_rolerank){
		String sql = "SELECT nroleno,crolename,CENABLED FROM data_role where crank>='"
				+ user_rolerank + "'";
	return sql;
	 }
}
