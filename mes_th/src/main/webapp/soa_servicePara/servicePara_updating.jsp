<%@ page language="java" import="java.sql.*" contentType="text/html;charset=gb2312"%>
<jsp:useBean id="Conn" scope="page" class="common.Conn_MES"/>
<%@page import="java.util.*" %>
<%@page import="mes.framework.*" %>

<%
	/*
	 * ҵ�������������������Ϣ��У���������Ƿ��ظ���������Ӧ�������
	 */
	 
	//��ȡ�������
	String serviceid = request.getParameter("serviceid");
	String paraname = request.getParameter("paraname");
	String setparaname = request.getParameter("setparaname");
	String setparatype = request.getParameter("setparatype");
	String paratype = request.getParameter("paratype");
	String paradesc = request.getParameter("paradesc");
    String intpage="";
	intpage=request.getParameter("intpage");
	String eid = null;
	eid=request.getParameter("eid");
	String info = request.getParameter("info");
		info = info==null?"":info;
	
	if(serviceid==null||serviceid.trim().equals("")||paraname==null||paraname.trim().equals("")||setparaname==null||setparaname==""||setparatype==null||setparatype.trim().equals("")||paratype==null||paratype.trim().equals("")||paradesc==null||paradesc.trim().equals(""))
	{
%>
<script>
	alert("����Ϊ��");window.location.href='servicePara_manage.jsp?page=<%=intpage%>&eid=<%=eid%>&info=<%=info%>';
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
		message.setUserParameter("paratype",paratype);
		message.setUserParameter("setparatype", setparatype);
		message.setUserParameter("paraname", paraname);
		message.setUserParameter("setparaname",setparaname);
        message.setUserParameter("paradesc", paradesc);
		message.setOtherParameter("con", con);
		
		//����soa��"���·������"������ ���̺�Ϊ12
		//todo
		er=bus.doProcess("12", message);
		
		//�ͷ���Դ
		if(con!=null)con.close();
		
		//��ִ�н���Ĵ���
		if(er==ExecuteResult.sucess)
		{
%>
<script>
	alert("�����ɹ���");window.location.href='servicePara_manage.jsp?page=<%=intpage%>&eid=<%=eid%>&info=<%=info%>';
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
 	alert("����ʧ�ܣ�ԭ�������������Ա��ϵ��");window.location.href='servicePara_manage.jsp?page=<%=intpage%>&eid=<%=eid%>&info=<%=info%>';;
 </script>
<%
			}
			else
			{
				se=(ServiceException)list.get(list.size()-1);
%>
<script>
 	alert("����ʧ�ܣ�<%=se.getDescr()%>");window.location.href='servicePara_manage.jsp?page=<%=intpage%>&eid=<%=eid%>&info=<%=info%>';
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
	alert("�����쳣��<%=e.toString()%>");window.location.href='servicePara_manage.jsp?page=<%=intpage%>&eid=<%=eid%>&info=<%=info%>';
</script>	
<%   
	throw e;
	}
%>
		