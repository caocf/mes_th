<%@ page language="java" import="java.sql.*" contentType="text/html;charset=gb2312"%>
<jsp:useBean id="Conn" scope="page" class="com.qm.th.helpers.Conn_MES"/>
<%@page import="java.util.*" %>
<%@page import="com.qm.mes.framework.*" %>

<%
	/*
	 * ҵ�������������������Ϣ��У���������Ƿ��ظ���������Ӧ�������
	 */
	 
	//��ȡ�������
	String serviceid = request.getParameter("serviceid");
	String servicename=request.getParameter("servicename");
	String classname=request.getParameter("classname");
	String servicedesc=request.getParameter("servicedesc");
	String namespace=request.getParameter("namespace");
	String intpage="";
	intpage=request.getParameter("intpage");
    String info = request.getParameter("info");
		info = info==null?"":info;
	if(serviceid==null||serviceid.trim().equals("")||servicename==null||servicename.trim().equals("")||classname==null||classname.trim().equals("")||servicedesc==null||servicedesc.trim().equals("")||namespace==null||namespace.trim().equals(""))
	{
%>
<script>
<!-- 
alert("����Ϊ��");window.location.href='service_manage.jsp?page=<%=intpage%>&eid=<%=serviceid%>&info=<%=info%>';
 -->
</script>
<%
		return;
	}
	
	Connection con=null;
	ExecuteResult er=null;
	ServiceException se=null;
//	String questiondesc="";
	
	List list=null;
	try
	{
		//��ȡ��Դ
		con=Conn.getConn();
		
		IServiceBus bus = ServiceBusFactory.getInstance();
		IMessage message = MessageFactory.createInstance();
		//�����û�����
		message.setUserParameter("serviceid",serviceid);
		message.setUserParameter("servicename", servicename);
		message.setUserParameter("classname",classname);
		message.setUserParameter("servicedesc", servicedesc);
		message.setUserParameter("namespace", namespace);
		message.setOtherParameter("con", con);
		
		//����soa��"���·�����"������ ���̺�Ϊ7
		//todo
		er=bus.doProcess("7", message);
		
		//�ͷ���Դ
		if(con!=null)con.close();
		
		//��ִ�н���Ĵ���
		if(er==ExecuteResult.sucess)
		{
%>
<script>
	alert("�����ɹ���");window.location.href='service_manage.jsp?page=<%=intpage%>&eid=<%=serviceid%>&info=<%=info%>';
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
 	alert("����ʧ�ܣ�ԭ�������������Ա��ϵ��");window.location.href='service_manage.jsp?page=<%=intpage%>&eid=<%=serviceid%>&info=<%=info%>';
 </script>
<%
			}
			else
			{
				se=(ServiceException)list.get(list.size()-1);
%>
<script>
 	alert("����ʧ�ܣ�<%=se.getDescr()%>");window.location.href='service_manage.jsp?page=<%=intpage%>&eid=<%=serviceid%>&info=<%=info%>';
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
	alert("�����쳣��<%=e.toString()%>");window.location.href='service_manage.jsp?page=<%=intpage%>&eid=<%=serviceid%>&info=<%=info%>';
</script>	
<%   
	throw e;
	}
%>
		