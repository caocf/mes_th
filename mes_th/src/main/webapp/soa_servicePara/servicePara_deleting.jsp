<%@ page language="java" import="java.sql.*" contentType="text/html;charset=gb2312"%>
<jsp:useBean id="Conn" scope="page" class="common.Conn"/>
<%@page import="java.util.*" %>
<%@page import="mes.framework.*" %>

<%
	/*
	 * ҵ����������������̷�����Ϣ��У���������Ƿ��ظ���������Ӧ�������
	 */
	 
	//��ȡ�������
	String serviceid=request.getParameter("serviceid");
	String setparaname = request.getParameter("setparaname");
	String setparatype = request.getParameter("setparatype");
	String eid = request.getParameter("eid");
    String intpage="";
	intpage=request.getParameter("intPage");
	String info = request.getParameter("info");
		info = info==null?"":info;
	if(serviceid==null||serviceid.trim().equals("")||setparaname==null||setparaname.trim().equals("")||setparatype==null||setparatype.trim().equals(""))
	{
%>
<script>
<!-- 
alert("����Ϊ��");window.location.href='servicePara_manage.jsp?page=<%=intpage%>&info=<%=info%>&eid=<%=eid%>';
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
		message.setUserParameter("serviceid", serviceid);
		message.setUserParameter("paratype", setparatype);
		message.setUserParameter("paraname", setparaname);
		message.setOtherParameter("con", con);
		
		
		//����soa�е�"ɾ�����������Ϣ"���� ���̺�Ϊ13
		//todo
		er=bus.doProcess("13", message);
		//�ͷ���Դ
		if(con!=null)con.close();
		
		//��ִ�н���Ĵ���
		if(er==ExecuteResult.sucess)
		{
		
%>
<script>
<!-- 
alert("�����ɹ���");window.location.href='servicePara_manage.jsp?page=<%=intpage%>&info=<%=info%>';
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
alert("����ʧ�ܣ�ԭ�������������Ա��ϵ��");window.location.href='servicePara_manage.jsp?page=<%=intpage%>&info=<%=info%>&eid=<%=eid%>';
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
window.location.href="servicePara_manage.jsp?page=<%=intpage%>&info=<%=info%>&eid=<%=eid%>";
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
alert("�����쳣��<%=e.toString()%>");window.location.href='servicePara_manage.jsp?page=<%=intpage%>&info=<%=info%>&eid=<%=eid%>';
-->
</script>	
<%
	throw e;
	}
%>
		

