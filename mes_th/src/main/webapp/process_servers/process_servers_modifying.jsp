<%@ page language="java" import="java.sql.*" contentType="text/html;charset=gb2312"%>
<jsp:useBean id="Conn" scope="page" class="com.qm.th.helpers.Conn_MES"/>
<%@page import="java.util.*" %>
<%@page import="com.qm.mes.framework.*" %>

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
	String eid = null;
	eid=request.getParameter("eid");
	String process_info = request.getParameter("process_info");
	process_info = process_info==null?"":new String(process_info);
	if(processid==null||processid.trim().equals("")||sid==null||sid.trim().equals("")||serverid==null||serverid.trim().equals("")||aliasname==null||aliasname.trim().equals("")||actid==null|actid.trim().equals(""))
	{
%>
<script>
<!-- 
alert("����Ϊ��");window.location.href='process_servers_modify.jsp';
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
		
		//out.println(con);
		//if(con!=null)con.close();
		//if(true)return;
		
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
		er=bus.doProcess("8", message);
		
		//�ͷ���Դ
		if(con!=null)con.close();
		
		//��ִ�н���Ĵ���
		if(er==ExecuteResult.sucess)
		{
%>
<script>
<!-- 
alert("�����ɹ���");window.location.href='process_servers_view.jsp?page=<%=intpage%>&eid=<%=eid%>&process_info=<%=process_info%>';
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
alert("����ʧ�ܣ�ԭ�������������Ա��ϵ��");
window.location.href='process_servers_view.jsp?processid=<%=processid%>&page=<%=intpage%>&eid=<%=eid%>&process_info=<%=process_info%>';
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
alert("����ʧ�ܣ�<%=se.getDescr().replaceAll("\n","")%>");
window.location.href='process_servers_view.jsp?processid=<%=processid%>&page=<%=intpage%>&eid=<%=eid%>&process_info=<%=process_info%>';
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
alert("�����쳣��<%=e.toString().replaceAll("\n","")%>");
window.location.href='process_servers_view.jsp?processid=<%=processid%>&page=<%=intpage%>&eid=<%=eid%>';
-->
</script>	
<%
		throw e;
	}
%>
		
