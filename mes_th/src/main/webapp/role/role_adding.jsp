<%@ page language="java" import="java.sql.*" contentType="text/html;charset=gb2312"%>
<%@page import="java.util.*" %> 
<%@page import="com.qm.mes.framework.*" %>  

<html><!-- InstanceBegin template="/Templates/��������1.dwt.jsp" codeOutsideHTMLIsLocked="true" --> 
<!-- InstanceBeginEditable name="������Ӷ���" -->
		<jsp:useBean id="Conn" scope="page" class="com.qm.th.helpers.Conn_MES"/>
		<!-- InstanceEndEditable -->
 
<%	String nextpage="_manage.jsp";
	StringBuffer result = new StringBuffer("<script>");
%>
<!-- InstanceBeginEditable name="��ò�������֤" -->
<%
	String[] chs = request.getParameterValues("chk");
   String intpage="";
	intpage=request.getParameter("intpage");
	String rolename=request.getParameter("rolename");
	String rank=request.getParameter("rank");
	String welcomepage=request.getParameter("welcomepage");
	String note=request.getParameter("note");
	
	if(chs==null)
		chs=new String[]{""};
	
	if(	rolename==null||
		rank==null||
		welcomepage==null||
		note==null){
		out.println("<script>alert(\"����Ϊ��\");window.location.href='role_manage.jsp';</script>");
		return;
	}
	String str_ch = "";
	for(String ch:chs)
		str_ch = str_ch + ":" + ch;
	chs=null;

%>
<!-- InstanceEndEditable --><%
	String userid = (String)session.getAttribute("userid");
	
	Connection con=null;
	ExecuteResult er=null;
	ServiceException se=null;
//	String questiondesc="";
	List list=null;
	try	{
		//��ȡ��Դ
		con=Conn.getConn();
		IServiceBus bus = ServiceBusFactory.getInstance();
		IMessage message = MessageFactory.createInstance();
		String processid_run = "";
		//�����û����� %>	
	<!-- InstanceBeginEditable name="�����û�����������Ҫ���е�����id" -->
	<%
		nextpage="role_manage.jsp";
		processid_run = "14";
		message.setUserParameter("userid", userid);
		message.setUserParameter("rolename", rolename);
		message.setUserParameter("rank", rank);
		message.setUserParameter("welcomepage", welcomepage);
		message.setUserParameter("note", note);
		message.setUserParameter("functionids", str_ch);
		message.setOtherParameter("con", con);
	%><!-- InstanceEndEditable -->
	<%	message.setOtherParameter("con", con);
		er=bus.doProcess(processid_run, message);
		if(con!=null)con.close();//�ͷ���Դ
		if(er==ExecuteResult.sucess){//��ִ�н���Ĵ���
			result.append("alert(\"�����ɹ���\");");
			%>
		<!-- InstanceBeginEditable name="ִ�гɹ�" --><%
	%><!-- InstanceEndEditable -->	
			<%
		}else{
			list=message.getServiceException();
			if(list==null||list.size()==0){
				result.append("alert(\"����ʧ�ܣ�ԭ�������������Ա��ϵ��\");");
			}else{
				se=(ServiceException)list.get(list.size()-1);
				result.append("alert(\"����ʧ�ܣ�"+se.getDescr().replaceAll("\n","")+"\");");
			}
		}
		result.append("window.location.href='"+nextpage+"?page="+ intpage+"'; ");
		result.append("</script>");
		out.println(result.toString());
	}catch(Exception e){	
		if(con!=null)con.close();//�ͷ���Դ
		throw e;
	}
%>

<!-- InstanceEnd --></html>