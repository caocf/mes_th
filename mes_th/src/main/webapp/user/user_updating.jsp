<%@ page language="java" import="java.sql.*"
	contentType="text/html;charset=gb2312"%>
<jsp:useBean id="Conn" scope="page" class="com.qm.mes.th.helper.Conn_MES" />
<%@page import="common.*"%>
<%@page import="java.util.*"%>
<%@page import="mes.framework.*"%>
<%@ include file="security.jsp"%>

<%
	 /*
	 * ҵ����������������̷�����Ϣ��У���������Ƿ��ظ���������Ӧ�������
	 */

	MD5 m = new MD5();

	//���԰�ȫҳ������,�����û����û���id
	String lastupdateuser = userid;
	//�����û���ʱ�䣺��service���趨��
	String lastupdatetime = "1";
	String enabled = "1";
    String intpage="";
	intpage=request.getParameter("intpage");
	String eid=request.getParameter("eid");
	//��ȡ�������
	String usrno = request.getParameter("usrno");
	String usrname = request.getParameter("usrname");
	String password = request.getParameter("password");
	String employeeid = request.getParameter("employeeid");
	if (employeeid == null)
		employeeid = "";
	String state = request.getParameter("state");
	String note = request.getParameter("note");
	String default_roleno = request.getParameter("default_roleno");
	String old_default_roleno = request
			.getParameter("old_default_roleno");
	String haveRoleno = request.getParameter("haveRoleNo");
	String[] roleno = request.getParameterValues("roleno");
	String info = request.getParameter("info");
		info = info==null?"":info;

	if (roleno == null)
		roleno = new String[] { "" };

	if (usrno == null || usrno.trim().equals("") || usrname == null
			|| usrname.trim().equals("") || password == null
			|| password.trim().equals("") || employeeid == null
			|| employeeid.trim().equals("") || state == null
			|| state.trim().equals("") || note == null
			|| note.trim().equals("") || default_roleno == null
			|| default_roleno.equals("") ||  roleno == null
			|| haveRoleno == null || haveRoleno.trim().equals("")) {
			
%>
<script>
<!-- 

alert("����Ϊ��");window.location.href='user_view.jsp?page=<%=intpage%>&eid=<%=eid%>&usrno=<%=usrno%>&info=<%=info%>';
 -->
</script>
<%
	return;
	}

	Connection con = null;
	String roleid = "";
	String str_default = "false";
	for (String i : roleno) {
		roleid = roleid + ":" + i;
		if(default_roleno.equals(i)){
		    str_default = "true";
		}
	}
	if(str_default.equals("false")){
%>
<script>
<!-- 
alert("Ĭ�Ͻ�ɫ������ѡ�н�ɫ!!");window.location.href='user_view.jsp?page=<%=intpage%>&eid=<%=eid%>&usrno=<%=usrno%>&info=<%=info%>';
 -->
</script>
<%
	    return;
	}
	roleno = null;

	ExecuteResult er = null;
	ServiceException se = null;
	//	String questiondesc="";

	if (!password.equals("********"))
		password = m.getMD5ofStr(password);

	List list = null;
	try {
		//��ȡ��Դ
		con = Conn.getConn();

		IServiceBus bus = ServiceBusFactory.getInstance();
		IMessage message = MessageFactory.createInstance();
		//�����û�����
		String processid_run = "28";
		message.setUserParameter("usrno", usrno);
		message.setUserParameter("usrname", usrname);
		message.setUserParameter("password", password);
		message.setUserParameter("employeeid", employeeid);
		message.setUserParameter("state", state);
		message.setUserParameter("note", note);
		message.setUserParameter("default_roleno", default_roleno);
		message.setUserParameter("old_default_roleno",
		old_default_roleno);
		message.setUserParameter("roleno", roleid);
		message.setUserParameter("oldRoleno", haveRoleno);

		message.setUserParameter("lastupdateuser", lastupdateuser); //���� ��ʱΪ1
		message.setUserParameter("lastupdatetime", lastupdatetime); //���� ȡ��oracle������
		message.setUserParameter("enabled", enabled);//����

		message.setOtherParameter("con", con);

		//����soa�е����� ��ʱ��Ϊ1 
		//todo
		er = bus.doProcess(processid_run, message);

		//�ͷ���Դ
		if (con != null)
			con.close();

		//��ִ�н���Ĵ���
		if (er == ExecuteResult.sucess) {
%>
<script>
<!-- 
alert("�����ɹ���");window.location.href='user_view.jsp?page=<%=intpage%>&eid=<%=eid%>&usrno=<%=usrno%>&info=<%=info%>';
 -->
</script>
<%
			} else {
			list = message.getServiceException();
			if (list == null || list.size() == 0) {
%>
<script>
<!-- 
alert("����ʧ�ܣ�ԭ�������������Ա��ϵ��");window.location.href='user_view.jsp?page=<%=intpage%>&eid=<%=eid%>&usrno=<%=usrno%>&info=<%=info%>';
 -->
</script>
<%
		} else {
		se = (ServiceException) list.get(list.size() - 1);
%>
<script>
<!-- 
alert("����ʧ�ܣ�<%=se.getDescr().replaceAll("\n", "")%>");window.location.href='user_view.jsp?page=<%=intpage%>&eid=<%=eid%>&usrno=<%=usrno%>&info=<%=info%>';
 -->
</script>
<%
		}
		}

	} catch (Exception e) {
		//�ͷ���Դ
		if (con != null)
			con.close();
%>
<script>
<!--  
alert("�����쳣��<%=e.toString().replaceAll("\n", "")%>");window.location.href='user_view.jsp?page=<%=intpage%>&eid=<%=eid%>&usrno=<%=usrno%>&info=<%=info%>';
-->
</script>
<%
	throw e;
	}
%>
