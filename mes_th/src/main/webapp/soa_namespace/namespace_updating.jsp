<%@ page language="java" import="java.sql.*" contentType="text/html;charset=gb2312"%>
<jsp:useBean id="Conn" scope="page" class="com.qm.mes.th.helper.Conn_MES"/>
<%@page import="java.util.*" %>
<%@page import="mes.framework.*" %>

<%
	/*
	 * ҵ�������������������Ϣ��У���������Ƿ��ظ���������Ӧ�������
	 */
	 
	//��ȡ�������
	String id = request.getParameter("id");
	String namespace = request.getParameter("namespace");
	String desc=request.getParameter("desc");
    String intpage="";
	intpage=request.getParameter("intpage");
	String info = request.getParameter("info");
		info = info==null?"":info;
	if(namespace==null||namespace.trim().equals("")||desc==null||desc.trim().equals("")||id==null||id.trim().equals(""))
	{
%>
<script>
<!-- 
alert("����Ϊ��");window.location.href='namespace_manage.jsp?page=<%=intpage%>&eid=<%=id%>&info=<%=info%>';
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
		message.setUserParameter("id",id);
		message.setUserParameter("namespace",namespace);
		message.setUserParameter("desc", desc);
	    message.setOtherParameter("con", con);
		
		//����soa��"���������ռ�"������ ���̺�Ϊ36
		//todo
		er=bus.doProcess("36", message);
		
		//�ͷ���Դ
		if(con!=null)con.close();
		
		//��ִ�н���Ĵ���
		if(er==ExecuteResult.sucess)
		{
%>
<script>
	alert("�����ɹ���");window.location.href='namespace_manage.jsp?page=<%=intpage%>&eid=<%=id%>&info=<%=info%>';
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
 	alert("����ʧ�ܣ�ԭ�������������Ա��ϵ��");window.location.href='namespace_manage.jsp?page=<%=intpage%>&eid=<%=id%>&info=<%=info%>';
 </script>
<%
			}
			else
			{
				se=(ServiceException)list.get(list.size()-1);
%>
<script>
 	alert("����ʧ�ܣ�<%=se.getDescr()%>");window.location.href='namespace_manage.jsp?page=<%=intpage%>&eid=<%=id%>&info=<%=info%>';
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
	alert("�����쳣��<%=e.toString()%>");window.location.href='namespace_manage.jsp?page=<%=intpage%>&eid=<%=id%>&info=<%=info%>';
</script>	
<%   
	throw e;
	}
%>
		