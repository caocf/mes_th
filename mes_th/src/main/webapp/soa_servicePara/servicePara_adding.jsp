<%@ page language="java" import="java.sql.*" contentType="text/html;charset=gb2312"%>
<jsp:useBean id="Conn" scope="page" class="com.qm.th.helper.Conn_MES"/>
<%@page import="java.util.*" %>
<%@page import="com.qm.mes.framework.*" %>
<%
	/*
	 * ҵ�������������������Ϣ��У���������Ƿ��ظ���������Ӧ�������
	 */
	 
	//��ȡ�������
	String serviceid=request.getParameter("serviceid");
    String paracount =request.getParameter("paracount");
    String intpage="";
	intpage=request.getParameter("intpage");
	if(serviceid==null||serviceid==""||paracount==null||paracount=="")
	{
%>
<script type="text/javascript">
	alert("����Ϊ��");window.location.href='servicePara_manage.jsp?page=<%=intpage%>';
</script>		

<%
	   return;
	}
	int count =Integer.parseInt(paracount);
	Connection con=null;
	ExecuteResult er=null;
	ServiceException se=null;
//	String questiondesc="";
	boolean success_sign=true;
    List list=null;
	try
	{
	    
	    //��ȡ��Դ
		con=Conn.getConn();
		con.setAutoCommit(false);
		
		IServiceBus bus = ServiceBusFactory.getInstance();
		IMessage message = MessageFactory.createInstance();
		//�����û�����
		message.setUserParameter("serviceid", serviceid);
		
		int i=1;
		//ʹ��ѭ������ִ�ж�Ρ���ӷ���������ķ���
		for(i=1;i<=count;i++)
		{
		
			message.setUserParameter("paraname", request.getParameter("para"+i));
			message.setUserParameter("paratype", request.getParameter("para"+i+"_type"));
			message.setUserParameter("paradesc", request.getParameter("para"+i+"_desc"));
			message.setOtherParameter("con", con);
			
			//����soa����ӷ������������ �����Ϊ6 
			//todo
			er=bus.doProcess("6", message);
			if(er!=ExecuteResult.sucess)
			{
				success_sign=false;
				break;
				
			}
		}
		if(success_sign)
		{
			con.commit();
			con.setAutoCommit(true);
		}else
		{
			con.rollback();
			con.setAutoCommit(true);
		}
		//�ͷ���Դ
		if(con!=null)con.close();
		
		
		//��ִ�н���Ĵ���
		if(er==ExecuteResult.sucess)
		{
%>
<script>
<!-- 
alert("�����ɹ���");window.location.href='servicePara_manage.jsp?page=<%=intpage%>';
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
alert("����ʧ�ܣ�ԭ�������������Ա��ϵ��");window.location.href='servicePara_manage.jsp?page=<%=intpage%>';
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
alert("����ʧ�ܣ�<%=se.getDescr()%>");window.location.href='servicePara_manage.jsp?page=<%=intpage%>';
 -->
</script>
<%
			}
		}
		
	}
	catch(Exception e)
	{	
		con.rollback();
		con.setAutoCommit(true);
		//�ͷ���Դ
		if(con!=null)con.close();
		
%>
<script>
<!--  
alert("�����쳣��<%=e.toString()%>");window.location.href='servicePara_manage.jsp?page=<%=intpage%>';
-->
</script>	
<%
	throw e;
	}
		

		
%>
		