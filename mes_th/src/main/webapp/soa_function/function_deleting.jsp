<%@ page language="java" import="java.sql.*" contentType="text/html;charset=gb2312"%>
<jsp:useBean id="Conn" scope="page" class="com.qm.th.helper.Conn_MES"/>
<%@page import="java.util.*" %>
<%@page import="com.qm.mes.framework.*" %>

<%
	/*
	 * ҵ����������������̷�����Ϣ��У���������Ƿ��ظ���������Ӧ�������
	 */
	 
	//��ȡ�������
	String functionid=request.getParameter("id");
    String userid=(String )session.getAttribute("userid");
	String intpage="";
	intpage=request.getParameter("intPage");	
	String info = request.getParameter("info");
		info = info==null?"":info;
    if(functionid==null||functionid.trim().equals(""))
	{
%>
<script>
<!-- 
alert("����Ϊ��");window.location.href='function_manage.jsp?page=<%=intpage%>&info=<%=info%>&eid=<%=functionid%>';
 -->
</script>
<%
		return;
	}	
	
	Connection con=null;
	ExecuteResult er=null;
	ServiceException se=null;
	//String questiondesc="";
	
	List list=null;
	try
	{
		//��ȡ��Դ
		con=Conn.getConn();
		
		IServiceBus bus = ServiceBusFactory.getInstance();
		IMessage message = MessageFactory.createInstance();
		//�����û�����
		message.setUserParameter("functionid", functionid);
		message.setUserParameter("userid", userid);
		message.setOtherParameter("con", con);
		
		
		//����soa�е�"ɾ������"���� ���̺�Ϊ27
		//todo
		er=bus.doProcess("27", message);
		//�ͷ���Դ
		if(con!=null)con.close();
		
		//��ִ�н���Ĵ���
		if(er==ExecuteResult.sucess)
		{
		
%>
<script>
<!-- 
alert("�����ɹ���");window.location.href='function_manage.jsp?page=<%=intpage%>&info=<%=info%>';
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
alert("����ʧ�ܣ�ԭ�������������Ա��ϵ��");window.location.href='function_manage.jsp?page=<%=intpage%>&info=<%=info%>&eid=<%=functionid%>';
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
window.location.href="function_manage.jsp?page=<%=intpage%>&info=<%=info%>&eid=<%=functionid%>";
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
alert("�����쳣��<%=e.toString()%>");window.location.href='function_manage.jsp?page=<%=intpage%>&info=<%=info%>&eid=<%=functionid%>';
-->
</script>	
<%
	throw e;
	}
%>
		

