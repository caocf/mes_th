<%@ page language="java" import="java.sql.*" contentType="text/html;charset=gb2312"%>
<jsp:useBean id="Conn" scope="page" class="com.qm.mes.th.helper.Conn_MES"/>
<%@page import="java.util.List"%>
<%@ page import="mes.framework.*" %>
<%
	/* 
	 * ʱ�䣺2007-06-25
	 * ���ߣ�����
	 * ҵ��������ɾ����������Ϣ
	 */
	 
	//��ȡ�������
	String processid=request.getParameter("processid");
	String eid=request.getParameter("eid");
	String aliasname=request.getParameter("aliasname");
	String parametername=request.getParameter("parameter");
	String intpage="";
	intpage=request.getParameter("intPage");
	String info = request.getParameter("info");
		info = info==null?"":info;
	//String eid=request.getParameter("eid");
	
	if(processid==null||processid.trim().equals("")||aliasname==null||aliasname.trim().equals("")||parametername==null||parametername.trim().equals(""))
	{
%>
<script>
<!-- 
alert("����Ϊ��");window.location.href='adapter_manage.jsp?page=<%=intpage%>&info=<%=info%>&eid=<%=eid%>';
 -->
</script>
<%
		return;
	}

	
	
	try{
		//int int_processid=
		Integer.parseInt(processid);
	}
	catch(Exception e_tran2)
	{
%>
<script>
<!-- 
alert("���̺Ų�����������");window.location.href='adapter_deleting.jsp';
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
		
		message.setUserParameter("processid", processid);
		message.setUserParameter("i_serveralias", aliasname);
		message.setUserParameter("i_parameter",parametername);
		
		message.setOtherParameter("con", con);
		
		//����soa�е����� ��ʱ��Ϊ2
		//todo
		er=bus.doProcess("2", message);
		
		//�ͷ���Դ
		if(con!=null)con.close();
		
		//��ִ�н���Ĵ���
		if(er==ExecuteResult.sucess)
		{
%>
<script>
<!-- 
alert("�����ɹ���");window.location.href='adapter_manage.jsp?page=<%=intpage%>&info=<%=info%>';
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
alert("����ʧ�ܣ�ԭ�������������Ա��ϵ��");window.location.href='adapter_manage.jsp?page=<%=intpage%>&info=<%=info%>&eid=<%=eid%>';
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
alert("����ʧ�ܣ�<%=se.getDescr()%>");window.location.href='adapter_manage.jsp?page=<%=intpage%>&info=<%=info%>&eid=<%=eid%>';
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
alert("�����쳣��<%=e.toString()%>");window.location.href='adapter_manage.jsp?page=<%=intpage%>&info=<%=info%>&eid=<%=eid%>';
-->
</script>
<%
		throw e;
	}
%>
