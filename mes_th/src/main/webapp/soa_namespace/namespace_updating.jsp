<%@ page language="java" import="java.sql.*" contentType="text/html;charset=gb2312"%>
<jsp:useBean id="Conn" scope="page" class="common.Conn_MES"/>
<%@page import="java.util.*" %>
<%@page import="mes.framework.*" %>

<%
	/*
	 * 业务描述：添加适配器信息，校验适配器是否重复配置由相应服务完成
	 */
	 
	//获取请求参数
	String id = request.getParameter("id");
	String namespace = request.getParameter("namespace");
	String desc=request.getParameter("desc");
    String intpage="";
	intpage=request.getParameter("intpage");
	String info = request.getParameter("info");
		info = info==null?"":info;
	if(namespace==null||namespace.trim().equals("")||desc==null||desc.trim().equals("")||id==null||id.trim().equals(""))
	{
%>
<script>
<!-- 
alert("参数为空");window.location.href='namespace_manage.jsp?page=<%=intpage%>&eid=<%=id%>&info=<%=info%>';
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
		//获取资源
		con=Conn.getConn();
		
		IServiceBus bus = ServiceBusFactory.getInstance();
		IMessage message = MessageFactory.createInstance();
		//设置用户参数
		message.setUserParameter("id",id);
		message.setUserParameter("namespace",namespace);
		message.setUserParameter("desc", desc);
	    message.setOtherParameter("con", con);
		
		//调用soa中"更新命名空间"的流程 流程号为36
		//todo
		er=bus.doProcess("36", message);
		
		//释放资源
		if(con!=null)con.close();
		
		//对执行结果的处理
		if(er==ExecuteResult.sucess)
		{
%>
<script>
	alert("操作成功！");window.location.href='namespace_manage.jsp?page=<%=intpage%>&eid=<%=id%>&info=<%=info%>';
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
 	alert("操作失败！原因不明！请与管理员联系！");window.location.href='namespace_manage.jsp?page=<%=intpage%>&eid=<%=id%>&info=<%=info%>';
 </script>
<%
			}
			else
			{
				se=(ServiceException)list.get(list.size()-1);
%>
<script>
 	alert("操作失败！<%=se.getDescr()%>");window.location.href='namespace_manage.jsp?page=<%=intpage%>&eid=<%=id%>&info=<%=info%>';
</script>
<%
			}
		}
		
	}
	catch(Exception e)
	{	
		//释放资源
		if(con!=null)con.close();
		
%>
<script>  
	alert("发生异常！<%=e.toString()%>");window.location.href='namespace_manage.jsp?page=<%=intpage%>&eid=<%=id%>&info=<%=info%>';
</script>	
<%   
	throw e;
	}
%>
		