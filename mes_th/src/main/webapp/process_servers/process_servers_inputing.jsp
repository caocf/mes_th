<%@ page language="java" import="java.sql.*" contentType="text/html;charset=gb2312"%>
<jsp:useBean id="Conn" scope="page" class="com.qm.mes.th.helper.Conn_MES"/>
<%@page import="java.util.*" %>
<%@page import="mes.framework.*" %>

<%
	/*
	 * ҵ����������������̷�����Ϣ��У���������Ƿ��ظ���������Ӧ�������
	 */
	 
	//��ȡ�������
	String processid=request.getParameter("tfNPROCESSID");
	String sid=request.getParameter("tfNSID");
	String serverid=request.getParameter("tfCSERVERID");
	String aliasname=request.getParameter("tfCALIASNAME");
	String actid=request.getParameter("tfNACTID");
	String intpage="";
	intpage=request.getParameter("intpage");
	if(processid==null||processid.trim().equals("")||sid==null||sid.trim().equals("")||serverid==null||serverid.trim().equals("")||aliasname==null||aliasname.trim().equals("")||actid==null|actid.trim().equals(""))
	{
%>
<script>
<!-- 
alert("����Ϊ��");window.location.href='process_servers_input.jsp';
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
		message.setUserParameter("processid", processid);
		message.setUserParameter("sid", sid);
		message.setUserParameter("serverid",serverid);
		message.setUserParameter("aliasname", aliasname);
		//if(o_serveralias==null)o_serveralias="";
		message.setUserParameter("actid", actid);

		
		message.setOtherParameter("con", con);
		
		//����soa�е����� ��ʱ��Ϊ1 
		//todo
		er=bus.doProcess("5", message);
		
		//�ͷ���Դ
		if(con!=null)con.close();
		
		//��ִ�н���Ĵ���
		if(er==ExecuteResult.sucess)
		{
%>
<script>
<!-- 
alert("�����ɹ���");window.location.href='process_servers_view.jsp?page=<%=intpage%>';
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
alert("����ʧ�ܣ�ԭ�������������Ա��ϵ��");window.location.href='process_servers_view.jsp?page=<%=intpage%>';
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
alert("����ʧ�ܣ�<%=se.getDescr().replaceAll("\n","")%>");window.location.href='process_servers_view.jsp?page=<%=intpage%>';
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
alert("�����쳣��<%=e.toString().replaceAll("\n","")%>");window.location.href='process_servers_view.jsp?page=<%=intpage%>';
-->
</script>	
<%
	throw e;
	}
%>
		