<%@ page language="java" import="java.sql.*" contentType="text/html;charset=gb2312"%>
<jsp:useBean id="Conn" scope="page" class="com.qm.th.helpers.Conn_MES"/>
<%@page import="java.util.*" %>
<%@page import="com.qm.mes.framework.*" %>

<%
	/*
	 * ҵ����������������̷�����Ϣ��У���������Ƿ��ظ���������Ӧ�������
	 */
	 
	//��ȡ�������
	String processid=request.getParameter("processid");
	String sid=request.getParameter("sid");
	String eid=request.getParameter("eid");
    String intpage="";
	intpage=request.getParameter("intPage");
    String process_info = request.getParameter("process_info");
	process_info = process_info==null?"":new String(process_info);
	
	if(processid==null||processid.trim().equals("")||sid==null||sid.trim().equals(""))
	{
%>
<script>
<!-- 
alert("����Ϊ��");window.location.href='process_servers_view.jsp?page=<%=intpage%>';
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
	
		message.setOtherParameter("con", con);
		
		//����soa�е����� ��ʱ��Ϊ1 
		//todo
		er=bus.doProcess("9", message);
		
		//�ͷ���Դ
		if(con!=null)con.close();
		
		//��ִ�н���Ĵ���
		if(er==ExecuteResult.sucess)
		{
%>
<script>
<!-- 
alert("�����ɹ���");window.location.href='process_servers_view.jsp?page=<%=intpage%>&process_info=<%=process_info%>';
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
alert("����ʧ�ܣ�ԭ�������������Ա��ϵ��");window.location.href='process_servers_view.jsp?page=<%=intpage%>&process_info=<%=process_info%>&eid=<%=eid%>';
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
alert("����ʧ�ܣ�<%=se.getDescr().replaceAll("\n","")%>");window.location.href='process_servers_view.jsp?page=<%=intpage%>&process_info=<%=process_info%>&eid=<%=eid%>';
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
alert("�����쳣��<%=e.toString().replaceAll("\n","")%>");window.location.href='process_servers_view.jsp?page=<%=intpage%>&process_info=<%=process_info%>&eid=<%=eid%>';
-->
</script>	
<%
	throw e;
	}
%>
		
