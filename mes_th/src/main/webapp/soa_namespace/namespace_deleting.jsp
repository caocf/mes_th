<%@ page language="java" import="java.sql.*" contentType="text/html;charset=gb2312"%>
<jsp:useBean id="Conn" scope="page" class="common.Conn_MES"/>
<%@page import="java.util.*" %>
<%@page import="mes.framework.*" %>

<%
	/*
	 * ҵ����������������̷�����Ϣ��У���������Ƿ��ظ���������Ӧ�������
	 */
	 
	//��ȡ�������
	String id=request.getParameter("id");
	String intpage="";
	intpage=request.getParameter("intPage");
	String info = request.getParameter("info");
		info = info==null?"":info;
	if(id==null||id.trim().equals(""))
	{
%>
<script>
<!-- 
alert("����Ϊ��");window.location.href='namespace_manage.jsp?page=<%=intpage%>&info=<%=info%>&eid=<%=id%>';
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
		message.setUserParameter("id", id);
		message.setOtherParameter("con", con);
		
		
		//����soa�е�"ɾ�������ռ�"���� ���̺�Ϊ37
		//todo
		er=bus.doProcess("37", message);
		//�ͷ���Դ
		if(con!=null)con.close();
		
		//��ִ�н���Ĵ���
		if(er==ExecuteResult.sucess)
		{
		
%>
<script>
<!-- 
alert("�����ɹ���");window.location.href='namespace_manage.jsp?page=<%=intpage%>&info=<%=info%>';
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
alert("����ʧ�ܣ�ԭ�������������Ա��ϵ��");window.location.href='namespace_manage.jsp?page=<%=intpage%>&info=<%=info%>&eid=<%=id%>';
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
alert("����ʧ�ܣ�<%=se.getDescr()%>");
window.location.href="namespace_manage.jsp?page=<%=intpage%>&info=<%=info%>&eid=<%=id%>";
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
alert("�����쳣��<%=e.toString()%>");window.location.href='namespace_manage.jsp?page=<%=intpage%>&info=<%=info%>&eid=<%=id%>';
-->
</script>	
<%
	throw e;
	}
%>
		


