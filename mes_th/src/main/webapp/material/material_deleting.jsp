<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@page import="java.util.*" %> 
<%@page import="com.qm.mes.framework.*" %>  
<jsp:useBean id="Conn" scope="page" class="com.qm.th.helpers.Conn_MES"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>ɾ������</title>

</head>
<body>
	<% 
	//��ȡ�������
	String element_name = request.getParameter("element_name");
	if(element_name==null||element_name.trim().equals(""))
	{
%>
<script>
<!-- 
alert("����Ϊ��");window.location.href='material_view.jsp';
 -->
</script>
<%
		return;
	}	
	
	Connection con=null;
	ExecuteResult er=null;
	ServiceException se=null;
	
	List list=null;
	try
	{
		//��ȡ��Դ
		con=Conn.getConn();
		
		IServiceBus bus = ServiceBusFactory.getInstance();
		IMessage message = MessageFactory.createInstance();
%>
<%
		//�����û�����
		message.setUserParameter("element_name", element_name);
		message.setOtherParameter("con", con);
		//����soa�е����� ��ʱ��Ϊ1 
		//todo "58"�����̶��������"�����������"
		er=bus.doProcess("58", message);
%>
<%
		//�ͷ���Դ
		if(con!=null)con.close();
		
		//��ִ�н���Ĵ���
		if(er==ExecuteResult.sucess)
		{
%>
<script>
<!-- 
alert("�����ɹ���");window.location.href='material_view.jsp';
 -->
</script>
<%
		}
		else
		{
			list=message.getServiceException();
			if(list==null||list.size()==0)
			{
%>
<script>
<!-- 
alert("����ʧ�ܣ�ԭ�������������Ա��ϵ��");window.location.href='material_view.jsp';
 -->
</script>
<%
			}
			else
			{
				se=(ServiceException)list.get(list.size()-1);
%>
<script>
<!-- 
alert('����ʧ�ܣ�<%=se.getDescr().replaceAll("\n","")%>');window.location.href='material_view.jsp';
 -->
</script>
<%
			}
		}
		
	}
	catch(Exception e)
	{	
		//�ͷ���Դ
		if(con!=null)con.close();
		
%>
<script>
<!--  
alert("�����쳣��<%=e.toString().replaceAll("\n","")%>");window.location.href='material_view.jsp';
-->
</script>	
<%
	throw e;
	}
%>
</body>
</html>
