<%@ page language="java" import="java.sql.*" contentType="text/html;charset=gb2312"%>
<%@page import="java.util.*" %> 
<%@page import="mes.framework.*" %>
<%@ page import="mes.framework.dao.*" %>  

<html><!-- InstanceBegin template="/Templates/��������1.dwt.jsp" codeOutsideHTMLIsLocked="true" --> 
<!-- InstanceBeginEditable name="������Ӷ���" -->
<jsp:useBean id="Conn" scope="page" class="common.Conn_MES"/>
<!-- InstanceEndEditable -->
 
<%	String nextpage="_manage.jsp";
	StringBuffer result = new StringBuffer("<script>");
%>
<!-- InstanceBeginEditable name="��ò�������֤" -->
<%	nextpage="role_manage.jsp";
	String roleno=request.getParameter("roleno");
	String intpage="";
	intpage=request.getParameter("intPage");
	String info = request.getParameter("info");
		info = info==null?"":info;
	if(roleno==null||roleno.trim().equals("")){
	out.println("<script>alert(\"����Ϊ��\");window.location.href='"+nextpage+"?page="+intpage+"&info="+info+"&eid="+roleno+"';</script>");
		return;
	}
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
		//л�����޸� id=1 ʱ�䣺2009 2��25�� Ŀ�ģ����û�ʹ�øý�ɫʱ������ɾ��
		  IDAO_UserManager daor = DAOFactory_UserManager
		.getInstance(DataBaseType.getDataBaseType(con));
       Statement stmt = con.createStatement();
       ResultSet rs= stmt.executeQuery(daor.getSQl_QueryUserByrole(Integer.parseInt(roleno)));
		if(rs.next()==true)
		{
		out.println("<script>alert(\"���û�ʹ�øý�ɫ������ɾ��\");window.location.href='"+nextpage+"?page="+intpage+"&info="+info+"&eid="+roleno+"';</script>");
		}
		else
		{
		
		IServiceBus bus = ServiceBusFactory.getInstance();
		IMessage message = MessageFactory.createInstance();
		String processid_run = "";
		//�����û����� %>	
	<!-- InstanceBeginEditable name="�����û�����������Ҫ���е�����id" -->
	<%
		processid_run = "24";
		message.setUserParameter("userid", userid);
		message.setUserParameter("roleno", roleno);
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
		result.append("window.location.href='"+nextpage+"?page="+ intpage+"&info="+info+"&eid="+roleno+"'; ");
		result.append("</script>");
		out.println(result.toString());
		}
		rs.close();
		stmt.close();
		//л���� ����; id=1
		
	}catch(Exception e){	
		if(con!=null)con.close();//�ͷ���Դ
		throw e;
	}
%>

<!-- InstanceEnd --></html>