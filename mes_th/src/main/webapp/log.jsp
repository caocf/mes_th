<%@ page language="java" import="java.sql.*" contentType="text/html;charset=gb2312"%>
<jsp:useBean id="Conn_MES" scope="page" class="com.qm.th.helper.Conn_MES"/>
<%@page import="com.qm.mes.util.tree.*"%> 
<%@page import="com.qm.th.helper.StringHelper"%> 

<%
	//��ȡ����
	//��ȡ�û�������
	String username="",password="",userid="";
	username = request.getParameter("name").trim();
	password = request.getParameter("pass").trim();
	
	//��ȡ��¼����
	String logindate=request.getParameter("dateInput");
	if(username==null||password==null||logindate==null||username.trim().equals("")||password.trim().equals("")||logindate.trim().equals(""))
	{
%>
<script language="JavaScript">
<!-- 
     alert("����Ϊ��!");window.location.href='index.jsp';
 -->
</script>
<%
		return;
	}
	
	
	//��ȡ����
	Connection con=null;
	DataServer_UserManage ds=null;
	AccessCtrl ac=null;
	
	try
	{
		if(Conn_MES==null)
		{
%>
<script language="JavaScript">
<!-- 
     alert("����ʧЧ��");window.location.href='index.jsp';
 -->
</script>
<%
			return;
		}

        username=StringHelper.clearSingleQuotationMarksFlaw(username);
        password=StringHelper.clearSingleQuotationMarksFlaw(password);
		
		con=Conn_MES.getConn();
		ds = new DataServer_UserManage (con);
		ac=new AccessCtrl(con);
		
		//��֤�û�
		if(ac.userProof(username,password))
		{
			userid=ds.getUserID(username);
			session.setAttribute("userid",userid);
			session.setAttribute("username",username);
			session.setAttribute("logindate",logindate);
			
			//��ȡ���û�����ʽ
			String cssfile=ds.getCssFile(userid);
			session.setAttribute("file",cssfile);
			//�ֽ��������
			String year=logindate.substring(0,4);
			String month=logindate.substring(4,6);
			String day=logindate.substring(6);
			
			session.setMaxInactiveInterval(-1);
			
			String url="log4.jsp?year="+year+"&month="+month+"&day="+day;
			
			
			
			if(ds.getParameterValue("fullscreen").equals("1"))
			{
%>

<script language="JavaScript">
<!--
    window.open('<%=url%>','','scrollbars=0,fullscreen=yes');
    window.opener=null;
    window.close();
 -->
</script>
<%
			}
			else
			{
				response.sendRedirect(url);
			}
		}
		else
		{
			String message=ac.getMessage().replaceAll("\"","'").replaceAll("\n","");
%>
<script language="JavaScript">
<!-- 
     alert("<%=message%>");window.location.href='index.jsp';
 -->
</script>
<%
			return;
		}

	}catch(Exception e)
	{
		throw e;
	}finally{
		if(con!=null)con.close();
	}
%>
