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
	String info = request.getParameter("info");
		info = info==null?"":info;

	//��ȡ�������
    String intpage="";
	intpage=request.getParameter("intpage");
	String usrno = request.getParameter("usrno");
	String usrname = request.getParameter("usrname");
	String password = request.getParameter("password");
	String employeeid = request.getParameter("employeeid");
	String default_roleno = request.getParameter("default_roleno");
	
	System.out.println(intpage + " ********** " + usrno);
	
	
	if (employeeid == null)
		employeeid = "";
	String state = request.getParameter("state");
	String note = request.getParameter("note");
	if (note == null || note.equals(""))
		note = "��ע";
	String[] roleno = request.getParameterValues("roleno");
	
	Connection con = null;
	ExecuteResult er = null;
	ServiceException se = null;
	password = m.getMD5ofStr(password);
	List list = null;
	try {
		//��ȡ��Դ
		con = Conn.getConn();
		String roleid = "";
		String str_default = "false";
		
		if(roleno == null){
%>
<script>
<!-- 
alert("������ѡ��һ��������ɫ!!");window.location.href='user_add.jsp';
-->
</script>
<%
	    	return;
	    }
	       
	     
	  
		for (String i : roleno) {
		
		    roleid = roleid + ":" + i;
		    if(default_roleno.equals(i)){
		    	
		        str_default = "true";
		    }
        }
 		if(str_default.equals("false")){
 			roleno = null;
%>
<script>
<!-- 
alert("Ĭ�Ͻ�ɫ������ѡ�н�ɫ!!");window.location.href='user_add.jsp';
-->
</script>
<%
	    	return;
	     }
	       
	       
	       
	    if (roleno == null)
		roleno = new String[]{};
		
		if (usrno == null || usrno.trim().equals("") || usrname == null
			|| usrname.trim().equals("") || password == null
			|| password.trim().equals("") || employeeid == null
			|| employeeid.trim().equals("") || state == null
			|| state.trim().equals("") || note == null
			|| note.trim().equals("") || roleno == null || roleno.equals("")
			|| default_roleno.trim().equals("")
			|| default_roleno == null){
	
%>
<script>
<!-- 
    alert("����Ϊ��!!");window.location.href='user_add.jsp';
-->
</script>
<%
	return;
	}
	       
	       
	    roleno = null;
		IServiceBus bus = ServiceBusFactory.getInstance();
		IMessage message = MessageFactory.createInstance();
		//�����û�����
		String processid_run = "23";
		message.setUserParameter("usrno", usrno);
		message.setUserParameter("usrname", usrname);
		message.setUserParameter("password", password);
		message.setUserParameter("employeeid", employeeid);
		message.setUserParameter("state", state);
		message.setUserParameter("note", note);
		message.setUserParameter("roleno", roleid);
		message.setUserParameter("default_roleno",default_roleno);
		message.setUserParameter("lastupdateuser", lastupdateuser); //���� ��ʱΪ1
		message.setUserParameter("lastupdatetime", lastupdatetime); //���� ȡ��oracle������
		message.setUserParameter("enabled", enabled);//����
		message.setOtherParameter("con", con);

		//����soa�е����� ��ʱ��Ϊ1 
		//todo
		er = bus.doProcess(processid_run, message);


		//��ִ�н���Ĵ���
		if (er == ExecuteResult.sucess) {
	
%>
<script>
<!-- 
	alert("�����ɹ���");
	var pageIndex = <%=intpage%>;
	window.location.href = "user_view.jsp?page=" + pageIndex+'&info=<%=info%>';
 -->
</script>
<%
			} else {
			list = message.getServiceException();
			if (list == null || list.size() == 0) {
%>
<script>
<!-- 
alert("����ʧ�ܣ�ԭ�������������Ա��ϵ��");window.location.href='user_add.jsp?intPage=<%=intpage%>&info=<%=info%>';
 -->
</script>
<%
		} else {
		se = (ServiceException) list.get(list.size() - 1);
%>
<script>
<!-- 
alert("����ʧ�ܣ�<%=se.getDescr().replaceAll("\n", "")%>");window.location.href='user_add.jsp?intPage=<%=intpage%>&info=<%=info%>';
 -->
</script>
<%
		}
		}
	} catch (Exception e) {
		//�ͷ���Դ
		
%>
<script>
<!--  
alert("�����쳣��<%=e.toString().replaceAll("\n", "")%>");window.location.href='user_add.jsp?page=<%=intpage%>&info=<%=info%>';
-->
</script>
<%
	 e.printStackTrace();
	}
	 finally {
	  try{ 
	  if (con != null)
			con.close();
	  }
	 catch (Exception e) {
		e.printStackTrace();
	} 
	}
%>

