package com.qm.mes.framework.dao;

import java.util.List;

public interface IDAO_UserManager {

	/**
	 * ���¹���(���ڵ�ΪҶ�ڵ�ʱ)
	 * 
	 * @param functionid
	 *            ���ܺ�
	 * @param functionname
	 *            ������
	 * @param url
	 *            �ļ�url
	 * @param state
	 *            ״̬
	 * @param note
	 *            ��ע
	 * @param safemarkcode
	 *            ��ȫ���
	 * @param userid
	 *            �û�id
	 * @param rank
	 *            ����
	 * @param flo_Order
	 *            ����˳��
	 * @param nupfunctionid
	 *            �����ܺ�
	 * @return
	 */
	String getSQL_UpdateFunctionWhenLeaf(int functionid, String functionname,
			String url, String state, String note, String safemarkcode,
			int userid, String rank, Float flo_Order, int upfunctionid);

	/**
	 * ���¹���(���ڵ��Ҷ�ڵ�ʱ)
	 * 
	 * @param functionid
	 *            ���ܺ�
	 * @param functionname
	 *            ������
	 * @param note
	 *            ��ע
	 * @param userid
	 *            �û�id
	 * @param rank
	 *            ����
	 * @param flo_Order
	 *            ����˳��
	 * @param nupfunctionid
	 *            �����ܺ�
	 */
	String getSQL_UpdateFunctionWhenNotLeaf(int functionid,
			String functionname, String note, int userid, String rank,
			Float flo_Order, int upfunctionid);

	/**
	 * ɾ������
	 * 
	 * @param functionid
	 *            ����id
	 * @return
	 */
	String getSQL_DeleteFunction(int functionid);

	/**
	 * ��ӹ���
	 * 
	 * @param functionid
	 *            ���ܺ�
	 * @param functionname
	 *            ������
	 * @param nodetype
	 *            �ڵ�����
	 * @param url
	 *            �ļ�url
	 * @param upfunctionid
	 *            �����ܺ�
	 * @param rank
	 *            ����
	 * @param state
	 *            ״̬
	 * @param userid
	 *            �û�ID
	 * @param safemarkcode
	 *            ���ʰ�ȫ���
	 * @param note
	 *            ��ע
	 * @param enable
	 *            ϵͳ��ʶ
	 * @return
	 */
	String getSQL_InsertFunction(int functionid, String functionname,
			String nodetype, String url, int upfunctionid, String rank,
			String state, int userid, String safemarkcode, String note,
			String enable, float flo_Order);

	/**
	 * ���ɹ��ܺ�
	 * 
	 * @return
	 */
	String getSQL_QueryNextFuntionId();

	/**
	 * ��ȡϵͳ��Ȩ�޴�����
	 * 
	 * @return ���صĽ����ֻ��һ�к�һ���ֶ�������PowerStringSize ������Int
	 */
	String getSQL_QueryPowerStringLength();

	/**
	 * ����Ȩ�޴�
	 * 
	 * @param appendString
	 *            ��ӵ��ַ���
	 * @return
	 */
	String getSQL_UpdatePowerStringWhenAdd(String appendString);

	/**
	 * ɾ������ʱ����Ȩ�޴�
	 * 
	 * @param userid
	 *            �û�ID
	 * @param node
	 *            ��ע
	 * @return
	 */
	String getSQL_UpdatePowerStringWhenDelete(int userid, String node);

	/**
	 * ��ȡ���ܺźͽڵ�����
	 * 
	 * @param functionid
	 *            ���ڵ�Ĺ��ܺ�
	 * @return 1��nfunctionid ���ܺ�<br>
	 *         2��cnodetype �ڵ�����
	 */
	String getSQL_QueryFunctionForUpfunctionid(int functionid);

	/**
	 * ��ȡ�ڵ�����
	 * 
	 * @param functionid
	 *            ���ܺ�
	 * @return cnodetype �ڵ�����
	 */
	String getSQL_QueryNodeTypeForFunctionId(int functionid);

	/**
	 * ��ѯ�����б�
	 * 
	 * @param userrolerank
	 * @param functioninfo
	 * @param method
	 *            �����ܺŲ�ѯ�򰴹�������ѯ
	 * @return ��������������ֶ�<br>
	 *         1��nfunctionid ���ܺ�<br>
	 *         2��CFUNCTIONNAME ������<br>
	 *         3��cnodetype �ڵ�����<br>
	 *         4��cstate ״̬<br>
	 *         5��cenabled ϵͳ��ʶ
	 */
	String getSQL_QueryAllFunctionsForRank(String userrolerank,
			String functioninfo, String method);

	/**
	 * �鿴����������Ϣ
	 * 
	 * @param functionid
	 *            ���ܺ�
	 * @param user_rolerank
	 *            �û�����
	 * @return ��������������ֶ�<br>
	 *         1��nupfunctionid �����ܺ�<br>
	 *         2��nfunctionid ���ܺ�<br>
	 *         3��cfunctionname ������<br>
	 *         4��cstate ״̬<br>
	 *         5��cnodetype �ڵ�����<br>
	 *         6��curl �ļ�url<br>
	 *         7��csafemark ���ʰ�ȫ���<br>
	 *         8��nlastupdateuser �û�ID<br>
	 *         9��dlastupdatetime ά��ʱ��<br>
	 *         10��cnote ��ע<br>
	 *         11��crank ����<br>
	 *         12��cusrname �û���<br>
	 *         13��flo_Order ����˳���
	 */
	String getSQL_QueryFunctionForFunctionIdAndRank(int functionid,
			String user_rolerank);

	/**
	 * ��ӹ���ʱ��ȡ��һ���ڵ�
	 * 
	 * @return ��������������ֶ�<br>
	 *         1��nfunctionid���ܺ�<br>
	 *         2��cfunctionname������
	 */
	String getSQL_QueryLastNodeForNodeType();

	/**
	 * ɾ����ɫ<br>
	 * ��������SQL��䣺<br>
	 * 1�����ս�ɫ��ɾ����ɫ��Ϣ�� <br>
	 * 2������ɾ����ɫ����Ӱ����û���Ϣ�������û���ɫ����Ϊ1��
	 * 
	 * @param roleno
	 *            ��ɫ��
	 * @param userid
	 *            �û��� ���������¼�¼
	 * @return ����һ��SQL���
	 */
	List<String> getSQL_DeleteRole(String roleno, String userid);

	/**
	 * �����ɫ��Ϣ
	 * 
	 * @param roleno
	 *            ��ɫ��
	 * @param rolename
	 *            ��ɫ��
	 * @param rank
	 *            ��ɫ����
	 * @param powerstring
	 *            Ȩ�޴�
	 * @param welcomepage
	 *            ��ӭҳ��
	 * @param userid
	 *            ���һ�θ����û���
	 * @param note
	 *            ����
	 * @return ����һ�������ɫ��SQL
	 */
	String getSQL_InsertRole(String roleno, String rolename, String rank,
			String powerstring, String welcomepage, String userid, String note);

	/**
	 * ��ѯϵͳ�еĴ��ڹ��ܣ����չ��ܺ�����
	 * 
	 * @return ��ѯ�Ľ�����ϰ��������ֶΣ�<br>
	 *         1��NFUNCTIONID�������ܺ�<br>
	 *         2��CFUNCTIONNAME������������<br>
	 *         3��CURL�����ļ�URL<br>
	 *         4��NUPFUNCTIONID���������ܺ�<br>
	 *         5��CRANK��������<br>
	 *         6��CNODETYPE�����ڵ�����<br>
	 *         7��CSTATE����״̬<br>
	 *         8��NLASTUPDATEUSER����ά��Ա<br>
	 *         9��DLASTUPDATETIME����ά��ʱ��<br>
	 *         10��CSAFEMARK�������ʰ�ȫ���<br>
	 *         11��CNOTE������ע<br>
	 *         12��CENABLED����ϵͳ��ʶ �Ƿ�ɱ༭
	 */
	String getSQL_QueryAllFunction();

	/**
	 * ��ѯ��һ�������ڲ�������Ľ�ɫ��
	 * 
	 * @return ���صĽ������ֻ��һ�У�����ֻ��һ���ֶ���������no�� ������Int
	 */
	String getSQL_QueryNextRoleNO();

	/**
	 * ���ݽ�ɫ����ͽ�ɫ�Ż��ɫ����ѯ��ɫ�����ս�ɫ������<br>
	 * 
	 * @param rank
	 *            <br>
	 *            ��ɫ���𡪡������뼶����򼶱�Ų�����ˡ� <br>
	 * @param roleno
	 *            <br>
	 *            ��ɫ�š����������ɫ�����ɫ�Ų�������� <br>
	 * @return ���صĽ�����а��������ֶΣ�<br>
	 *         1��NROLENO������ɫ����<br>
	 *         2��CROLENAME������ɫ����<br>
	 *         3��CENABLED--ϵͳ��ʶ �Ƿ�ɱ༭<br>
	 * @author ������ <br>
	 */
	String getSQL_QueryRole(String userrolerank, String roleinfo, String method);

	/**
	 * ��ѯ��ɫ����ɫ��Ӧ���û���Ϣ
	 * 
	 * @param roleno
	 *            ��ɫ��
	 * @return ���صĽ��������һ�У����������ֶΣ�<br>
	 *         NROLENO���� ��ɫ����<br>
	 *         CROLENAME���� ��ɫ����<br>
	 *         CRANK����Ȩ�޼���<br>
	 *         CPOWERSTRING����Ȩ�޴�<br>
	 *         CWELCOMEPAGE������ӭҳ��<br>
	 *         NLASTUPDATEUSER ����ά��Ա<br>
	 *         DLASTUPDATETIME ����ά��ʱ��<br>
	 *         CNOTE������ע<br>
	 *         CENABLED����ϵͳ��ʶ �Ƿ�ɱ༭<br>
	 *         <br>
	 *         CUSRNAME������½�ʺ�<br>
	 *         NEMPLOYEEID����Ա��ID<br>
	 *         CSTATE����״̬<br>
	 *         NLASTUPDATEUSER ����ά��Ա<br>
	 *         DLASTUPDATETIME ����ά��ʱ��<br>
	 */
	String getSQL_QueryRoleAndUser(String roleno);

	/**
	 * ���½�ɫ�����ݽ�ɫ�Ÿ���������ɫ��Ϣ
	 * 
	 * @param roleno
	 *            ��ɫ��
	 * @param rolename
	 *            ��ɫ��
	 * @param rank
	 *            ����
	 * @param powerstring
	 *            Ȩ�޴�
	 * @param welcomepage
	 *            ��ӭҳ��
	 * @param userid
	 *            ���һ��ά�����û���
	 * @param note
	 *            ����
	 * @return ���ظ��½�ɫ��Ϣ��SQL���
	 */
	String getSQL_UpdateRole(String roleno, String rolename, String rank,
			String powerstring, String welcomepage, String userid, String note);

	/**
	 * �����û������û����л�ȡ��¼��
	 * 
	 * @param username
	 *            �û���
	 * @return
	 */
	public String getSQL_QueryCountUserName(String username);

	/**
	 * �����û��Ŵ��û����л�ȡ�û�״̬
	 * 
	 * @param userid
	 *            �û���
	 * @return ��������������ֶ� cstate
	 * @deprecated ����ʹ��getSQL_QueryUserInfoForUserID����
	 */
	public String getSQL_QueryUserStateForUserID(String userid);

	/**
	 * �����û�����������û����л�ȡ��¼��
	 * 
	 * @param username
	 *            �û���
	 * @param password
	 *            ����(md5���ܺ������)
	 * @return
	 */
	public String getSQL_QueryCountUserNamePassword(String username,
			String password);

	/**
	 * �����û��ţ���ȡ���Ӧ����ʽ��
	 * 
	 * @param userid
	 *            �û���
	 * @return ��������������ֶ� ccssfile
	 */
	public String getSQL_QueryCssFileForUserID(String userid);

	/**
	 * �����û��ţ���ȡ���ɫ��
	 * 
	 * @param userid
	 *            �û���
	 * @return ��������������ֶ� nroleno
	 * @deprecated ����ʹ��getSQL_QueryUserInfoForUserID����
	 */
	public String getSQL_QueryRoleNoForUserID(String userid);

	/**
	 * ���ݽ�ɫ�ţ���ȡ��ɫ�ļ���
	 * 
	 * @param roleno
	 *            ��ɫ��
	 * @return ��������������ֶ� crank
	 * @deprecated ����ʹ��getSQL_QueryRoleForRoleNo����
	 */
	public String getSQL_QueryRankForRoleNo(String roleno);

	/**
	 * ��ȡϵͳ����ֵ
	 * 
	 * @param key
	 *            ����
	 * @return ��������������ֶ� cvalue
	 */
	public String getSQL_QueryParameterValueForKey(String key);

	/**
	 * ���ݽ�ɫ�ţ���ȡ��ӭҳ
	 * 
	 * @param roleno
	 *            ��ɫ��
	 * @return ��������������ֶ� CWELCOMEPAGE
	 * @deprecated ����ʹ��getSQL_QueryRoleForRoleNo����
	 */
	public String getSQL_QueryWelcomePageForRoleNo(String roleno);

	/**
	 * ���ݽ�ɫ�Ż�ȡȨ�޴�
	 * 
	 * @param roleno
	 *            ��ɫ��
	 * @return ��������������ֶ� CPOWERSTRING
	 * @deprecated ����ʹ��getSQL_QueryRoleForRoleNo����
	 */
	public String getSQL_QueryPowerStringForRoleNo(String roleno);

	/**
	 * ���ݹ��ܺŻ�ȡ�䰲ȫ��ʶ
	 * 
	 * @param functionid
	 *            ���ܺ�
	 * @return ��������������ֶ� CSAFEMARK
	 * @deprecated ����ʹ��getSQL_QueryFuncitonInfoForFunctionID����
	 */
	// public String getSQL_QuerySafeMarkForFunctionID(String functionid);
	/**
	 * ��ȡ������Ϣ
	 * 
	 * @param functionid
	 *            ���ܺ�
	 * @return ��������������ֶ�<br>
	 *         1��nfunctionid ���ܺ�<br>
	 *         2��cfunctionname ������<br>
	 *         3��curl URL<br>
	 *         4��nupfunctionid �����ܺ�<br>
	 *         5��crank ����<br>
	 *         6��cnodetype �ڵ�����<br>
	 *         7��cstate ״̬<br>
	 *         8��csafemark ��ȫ��ʶ<br>
	 *         9��cnote ��ע<br>
	 *         10��cenabled �Ƿ��ɾ��<br>
	 *         11��NLASTUPDATEUSER ����޸���<br>
	 *         12��flo_order ����˳���
	 */
	public String getSQL_QueryFuncitonInfoForFunctionID(String functionid);

	/**
	 * ���ݹ��ܺŻ�ȡ���ӽڵ��Ҷ
	 * 
	 * @param functionid
	 * @return ��������������ֶ� nfunctionid
	 */
	public String getSQL_QueryAllSubFunctionIDsForFunctionID(String functionid);

	/**
	 * �����û�����ȡ�û���
	 * 
	 * @param username
	 *            �û���
	 * @return ��������������ֶ� nusrno
	 */
	public String getSQL_QueryUserIDForUserName(String username);

	/**
	 * �����û��ţ���ȡ���û�������Ϣ
	 * 
	 * @param userid
	 *            �û���
	 * @return ��������������ֶ�<br>
	 *         1��NUSRNO �û���<br>
	 *         2��CUSRNAME �û���<br>
	 *         3��CPASSWORD ����<br>
	 *         4��NROLENO ��ɫ��<br>
	 *         5��NEMPLOYEEID Ա����<br>
	 *         6��CSTATE ״̬<br>
	 *         7��NLASTUPDATEUSER ����޸���<br>
	 *         8��DLASTUPDATETIME ����޸�ʱ��<br>
	 *         9��CNOTE ��ע<br>
	 *         10��CENABLED �Ƿ��ɾ��
	 */
	public String getSQL_QueryUserInfoForUserID(String userid);

	/**
	 * �����û�ҳ��������
	 * 
	 * @param userid
	 * @param color
	 * @return
	 */
	public String getSQL_UpdateUserInterface(String userid, String color);

	/**
	 * ���ݽ�ɫ�Ų�ѯ��ɫ��Ϣ
	 * 
	 * @param roleno
	 *            ��ɫ��
	 * @return ����һ����ɫ��Ϣ�����������ֶΣ� NROLENO ��ɫ����<br>
	 *         1��CROLENAME ��ɫ����<br>
	 *         2��CRANK Ȩ�޼���<br>
	 *         3��CPOWERSTRING Ȩ�޴�<br>
	 *         4��CWELCOMEPAGE ��ӭҳ��<br>
	 *         5��NLASTUPDATEUSER ά��Ա<br>
	 *         6��DLASTUPDATETIME ά��ʱ��<br>
	 *         7��CNOTE ��ע<br>
	 *         8��CENABLED ϵͳ��ʶ �Ƿ�ɱ༭<br>
	 */
	String getSQL_QueryRoleForRoleNo(String roleno);

	/**
	 * �����û���Ϣ��
	 * 
	 * @param usrno
	 *            �û�ID
	 * @param usrname
	 *            ��½�ʺ�
	 * @param password
	 *            ��½����
	 * @param roleno
	 *            ��ɫ��
	 * @param employeeid
	 *            Ա��ID
	 * @param state
	 *            ״̬ 0 ͣ�� 1����
	 * @param lastupdateuser
	 *            ά��Ա
	 * @param lastupdatetime
	 *            ά��ʱ��
	 * @param note
	 *            ��ע
	 * @param roleno
	 *            ������ɫ
	 * @param enabled
	 *            ϵͳ��ʶ �Ƿ�ɱ༭ 0���ɱ༭(ɾ��)1�ɱ༭
	 * @return
	 */
	String getSQL_InsertUser(String usrno, String usrname, String password,
			String roleno, String employeeid, String state,
			String lastupdateuser, String lastupdatetime, String note,
			String enabled);

	/**
	 * �����û�rolenoɾ���û��� <br>
	 * ԭ��:�����û�idɾ���û���
	 * 
	 * @param usrno
	 *            �û���
	 * @return
	 */
	String getSQL_DeleteUser(String usrno);

	/**
	 * ���ݽ�ɫ��,ɾ����ɫʱ,���û������ڸý�ɫɾ���û�
	 * 
	 * @param roleno
	 *            ��ɫ��
	 * @return
	 */
	String getSQL_DeleteUser_Roleno(int roleno);

	/**
	 * ���ݽ�ɫ��,ɾ��DataUserRole���д��ڸý�ɫ��
	 * 
	 * @param roleno
	 *            ��ɫ��
	 * @return
	 */
	String getSQL_DeleteDataUserRole_Roleno(int roleno);

	/**
	 * ���ݽ�ɫ��ɾ���û�-��ɫ��Ӧ��
	 * 
	 * @param usrno
	 *            �û���
	 * @return
	 */
	String getSQL_DeleteDataUserRole(int usrno);

	/**
	 * �����û����ɫ��ϵ�����Ϣ
	 * 
	 * @param usrno
	 *            �û���<br>
	 * @param roleno
	 *            ��ɫ��<br>
	 * @param cdefault
	 *            �Ƿ�ΪĬ�Ͻ�ɫ:0 ��ѡ��ɫ 1 Ĭ�Ͻ�ɫ<br>
	 * @return
	 */
	String getSQL_insertDataUserRole(int usrno, int roleno, String cdefault);

	/**
	 * ��ѯ�û��ŵ����н�ɫ
	 * 
	 * @param usrno
	 *            �û���
	 * @return usrno �û��� <br>
	 *         roleno ��ɫ��<br>
	 *         cdefault Ĭ�Ͻ�ɫ 0: ��ѡ��ɫ 1: Ĭ�Ͻ�ɫ<br>
	 *         rolename ��ɫ��
	 */
	String getSQL_selectDataUserRole(int usrno);

	/**
	 * ��ѯDataUserRole���������Ϣ
	 * 
	 * @param roleno
	 * @return usrno �û��� <br>
	 *         roleno ��ɫ��<br>
	 *         cdefault Ĭ�Ͻ�ɫ 0: ��ѡ��ɫ 1: Ĭ�Ͻ�ɫ<br>
	 */
	String getSQL_selectDataUserRole_Roleno(int roleno);

	/**
	 * �����û��Ų�ѯ���еĽ�ɫ��
	 * 
	 * @param usrno
	 *            �û���
	 * @return usrno �û���<br>
	 *         roleno ��ɫ��<br>
	 *         cdefault Ĭ�Ͻ�ɫ 0: ��ѡ��ɫ 1: Ĭ�Ͻ�ɫ
	 */
	String getSQL_selectHaveRole(int usrno);

	// update include password
	/**
	 * �����û�id�������û���Ϣ���������롣
	 * 
	 * @param usrno
	 *            �û�id
	 * @param usrname
	 *            ��½�ʺ�
	 * @param password
	 *            ��½����
	 * @param employeeid
	 *            Ա��ID
	 * @param state
	 *            ״̬ 0 ͣ�� 1����
	 * @param lastupdateuser
	 *            ά��Ա
	 * @param lastupdatetime
	 *            ά��ʱ��
	 * @param note
	 *            ��ע
	 * @param enabled
	 *            ϵͳ��ʶ �Ƿ�ɱ༭ 0���ɱ༭(ɾ��)1�ɱ༭
	 * @return
	 */
	String getSQL_UpdateUser(String usrno, String usrname, String password,
			String employeeid, String state, String lastupdateuser,
			String lastupdatetime, String note, String enabled);

	// ȡ���û�id�Ƿ��ظ�
	/**
	 * �����û�id���Ҽ�¼����
	 * 
	 * @param usrno
	 * @return
	 */
	String getSQL_QueryCountForUserNo(String usrno);

	/**
	 * �����û������Ҽ�¼����
	 * 
	 * @param usrname
	 * @return
	 * 
	 */
	String getSQL_QueryCountForUserName(String usrname);

	// update no password;
	/**
	 * �����û�id �����û���Ϣ������������
	 * 
	 * @param usrno
	 *            �û�id
	 * @param usrname
	 *            ��½�ʺ�
	 * @param employeeid
	 *            Ա��ID
	 * @param state
	 *            ״̬
	 * @param lastupdateuser
	 *            ά��Ա
	 * @param lastupdatetime
	 *            ά��ʱ��
	 * @param note
	 *            ��ע
	 * @param enabled
	 *            ϵͳ��ʶ �Ƿ�ɱ༭
	 * @return
	 */
	String getSQL_UpdateUser(String usrno, String usrname, String employeeid,
			String state, String lastupdateuser, String lastupdatetime,
			String note, String enabled);

	// ȡ���usrno��
	/**
	 * ��������usrno
	 * 
	 * @return
	 */
	String getSQL_QueryMaxusrno();

	/**
	 * ����css��ʽ��
	 * 
	 * @param usrno
	 * @param cssfile
	 * @return
	 */
	String getSQL_insertCss(String usrno, String cssfile);

	/**
	 * �����û�idɾ��css��ʽ
	 * 
	 * @param usrno
	 * @return
	 */
	String getSQL_DeleteCss(String usrno);

	/**
	 * ����Ȩ�޼�����ҽ�ɫ����ͽ�ɫ����
	 * 
	 * @param user_rolerank
	 *            Ȩ�޼���
	 * @return NROLENO ��ɫ����<br>
	 *         CROLENAME ��ɫ����
	 */
	public String getSQl_QueryRoleForRank(String user_rolerank);

	/**
	 * ���ݽ�ɫ������û�������û����Ʋ�ѯ�û��������û���������
	 * 
	 * @param user_rolerank
	 *            Ȩ����Ϣ-�����뼶����򼶱�Ų������ <br>
	 * @param userinfo
	 *            Ϊ�û�������û����� <br>
	 * @param method
	 *            Ϊ�������ַ���(�û������ѯ���û����Ʋ�ѯ) <br>
	 * @return 1��nusrno �û�ID<br>
	 *         2��cusrname ��½�ʺ�<br>
	 *         3��cenabled ϵͳ��ʶ<br>
	 * @author ������ <br>
	 */
	public String getSQL_QueryAllUserRoleNameNoForRank(String user_rolerank,
			String userinfo, String method);

	/**
	 * ����Ȩ�޼�������û���ɫ��Ϣ
	 * 
	 * @param id
	 * @param user_rolerank
	 *            Ȩ�޼���
	 * @return 1��nusrno �û�id<br>
	 *         2��cusrname ��½�ʺ�<br>
	 *         3��nemployeeid Ա��ID<br>
	 *         4��nroleno ��ɫ����<br>
	 *         5��cstate ״̬<br>
	 *         6��nlastupdateuser ά��Ա<br>
	 *         7��dlastupdatetime ά��ʱ��<br>
	 *         8��cnote ��ע
	 */
	public String getSQL_QueryUserRoleInfoForRank(String id,
			String user_rolerank);
    public String getSQl_QueryUserByrole(int nroleno);
    
  	/**
  	 * ͨ���û�ID��ѯ�û�����
  	 * 
  	 * @param id
  	 * @return �û�����
  	 * @author Ԭ�� <br>
  	 */
	public String getSQL_SelectUserById(int id);
	
	/**
  	 * ��ѯ�����û�����
  	 * 
  	 * @return �û�����
  	 * @author Ԭ�� <br>
  	 */
	 public String getAllUsers();
	/**
  	 * �鹦����Ϣ
  	 * 
  	 * @param userrolerank
  	 * @return sql
  	 * @author ��� <br>
  	 */
	public String getSQL_AllFunctionsForRank(String userrolerank);
	/**
  	 * ��user��Ϣ  	
  	 * @return sql
  	 * @author ��� <br>
  	 */
	public String getSQL_AllUserRoleNameNoForRank();
	/**
  	 * ��role��Ϣ  	
  	 * @return sql
  	 * @author ��� <br>
  	 */
	public String getSQL_AllRoleNameNoForRank(String user_rolerank);
}
