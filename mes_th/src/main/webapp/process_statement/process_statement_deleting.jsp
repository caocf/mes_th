<%@ page language="java" import="java.sql.*" contentType="text/html;charset=gb2312"%>
<jsp:useBean id="Conn" scope="page" class="com.qm.th.helper.Conn_MES"/>
<%@page import="java.util.*" %>
<%@page import="com.qm.mes.framework.*" %>
<%@ page import="com.qm.mes.framework.dao.*" %>
<%
	/*
	 * ҵ����������������̷�����Ϣ��У���������Ƿ��ظ���������Ӧ�������
	 */
	 
	//��ȡ�������
	String processid=request.getParameter("processid");
    String intpage="";
	intpage=request.getParameter("intPage");
	String process_info = request.getParameter("process_info");
	process_info = process_info==null?"":new String(process_info);
	if(processid==null||processid.trim().equals(""))
	
	{
%>
<script>
<!-- 
alert("����Ϊ��");window.location.href='process_statement_view.jsp?page=<%=intpage%>&process_info=<%=process_info%>&eid=<%=processid%>';
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
		//л�����޸� 2009 2��26�� Ŀ�ģ���ɾ������ʱ�ж��Ƿ��з��� id=1
		IDAO_Core idao_core=DAOFactory_Core.getInstance(DataBaseType.getDataBaseType(con));
		 Statement stmt = con.createStatement();
       ResultSet rs= stmt.executeQuery(idao_core.getSQl_QueryProcessandserverbynprocessid(Integer.parseInt(processid)));
		if(rs.next()==true)
		{
		out.println("<script>alert(\"�������з���ʹ�ã�����ɾ��\");window.location.href='process_statement_view.jsp?page="+intpage+"&process_info="+process_info+"&eid="+processid+"';</script>");
		}
		else{
		IServiceBus bus = ServiceBusFactory.getInstance();
		IMessage message = MessageFactory.createInstance();
		//�����û�����
		message.setUserParameter("processid", processid);
		message.setOtherParameter("con", con);
		
		//����soa�е����� ��ʱ��Ϊ1 
		//todo
		er=bus.doProcess("22", message);
		
		//�ͷ���Դ
		if(con!=null)con.close();
		
		//��ִ�н���Ĵ���
		if(er==ExecuteResult.sucess)
		{
%>
<script>
<!-- 
alert("�����ɹ���");window.location.href='process_statement_view.jsp?page=<%=intpage%>&process_info=<%=process_info%>';
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
alert("����ʧ�ܣ�ԭ�������������Ա��ϵ��");window.location.href='process_statement_view.jsp?page=<%=intpage%>&process_info=<%=process_info%>&eid=<%=processid%>';
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
alert("����ʧ�ܣ�<%=se.getDescr().replaceAll("\n","")%>");window.location.href='process_statement_view.jsp?page=<%=intpage%>&process_info=<%=process_info%>&eid=<%=processid%>';
 -->
</script>
<%
			}
		}
		}
		rs.close();
		stmt.close();
	//л���� ����; id=1
	}
	catch(Exception e)
	{	
		//�ͷ���Դ
		if(con!=null)con.close();
		
%>
<script>
<!--  
alert("�����쳣��<%=e.toString().replaceAll("\n","")%>");window.location.href='process_statement_view.jsp?page=<%=intpage%>&process_info=<%=process_info%>&eid=<%=processid%>';
-->
</script>	
<%
	throw e;
	}
%>
		

