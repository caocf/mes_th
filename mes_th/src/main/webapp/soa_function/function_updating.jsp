<%@ page language="java" import="java.sql.*" contentType="text/html;charset=gb2312"%>
<%@page import="java.util.*,java.util.regex.Pattern" %> 
<%@page import="mes.framework.*" %>  

<html><!-- InstanceBegin template="file:///E|/webapps/mes/framework/Templates/��������1.dwt.jsp" codeOutsideHTMLIsLocked="true" --> 
<!-- InstanceBeginEditable name="������Ӷ���" -->
<jsp:useBean id="Conn" scope="page" class="com.qm.mes.th.helper.Conn_MES"/>
<!-- InstanceEndEditable -->
 
<%	String nextpage="_manage.jsp";
	StringBuffer result = new StringBuffer("<script>");
%>
<!-- InstanceBeginEditable name="��ò�������֤" -->
<%	String functionid=request.getParameter("functionid");
	String functionname=request.getParameter("functionname");
	String nodetype=request.getParameter("nodetype");
	String url= request.getParameter("url");
	String safemarkcode = request.getParameter("safemarkcode");
	String note = request.getParameter("note");
	String state = request.getParameter("state");
	String rank = request.getParameter("rank");
	String flo_Order = request.getParameter("flo_Order");
	String upnodeid = request.getParameter("upnodeid");
	String userid=(String )session.getAttribute("userid");
	nextpage="function_manage.jsp";
	String intpage="";
	intpage=request.getParameter("intpage");
	String info = request.getParameter("info");
		info = info==null?"":info;
	
	if(flo_Order==null||flo_Order.equals("")){
		flo_Order="0";
	}
	if((nodetype!=null)&&(!nodetype.equals("3")))
		url="";
	if((nodetype!=null)&&(nodetype.equals("2")||nodetype.equals("1"))){
		state="";
		rank="1";//��Ҷ�ڵ�ʱ��ΪӦ�ü�
	}
	if((nodetype!=null)&&(!nodetype.equals("3")))
		safemarkcode="";
	/*System.out.print("behind");
	System.out.print("a"+functionid);
	System.out.println(functionname);
	System.out.println(nodetype);
	System.out.println(url);
	System.out.println(safemarkcode);
	System.out.println(note);
	System.out.println(state);
	System.out.println(rank);
	System.out.println("b"+userid);
    System.out.print("end");*/
	
	if(functionid==null||functionid.trim().equals("")||functionname==null||functionname.trim().equals("")||nodetype==null||nodetype.trim().equals("")||rank==null||rank.trim().equals("")||userid==null||userid.trim().equals("")||upnodeid==null||upnodeid.trim().equals(""))
{
%>
<script type="text/javascript">
	alert("����Ϊ��");window.location.href="function_manage.jsp?page=<%=intpage%>&eid=<%=functionid%>&info=<%=info%>";		
</script>
<%
	return;
	}
	//String str="\d*\.\d{2}|\d*";
	boolean b = Pattern.compile("^[\\d]{1,5}(\\.[\\d]{1,3}$|$)").matcher(flo_Order).find();
	if(!b){
	%>
<script type="text/javascript">
	alert("����˳���ӦΪFloat����������,��2,12.5!");window.location.href="function_update.jsp?id=<%=functionid%>&info=<%=info%>";	
</script>

 	<!-- InstanceEndEditable -->
 	<%
 	}	
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
		processid_run = "25";
		message.setUserParameter("functionid", functionid);
		message.setUserParameter("functionname", functionname);
		message.setUserParameter("nodetype", nodetype);
		message.setUserParameter("url", url);
		message.setUserParameter("safemarkcode", safemarkcode);
		message.setUserParameter("note", note);
		message.setUserParameter("flo_Order", flo_Order);
		message.setUserParameter("upnodeid", upnodeid);
		message.setUserParameter("state", state);
		message.setUserParameter("rank", rank);
		message.setUserParameter("userid", userid);
	%><!-- InstanceEndEditable -->
	<%	message.setOtherParameter("con", con);
		er=bus.doProcess(processid_run, message);
		if(con!=null)con.close();//�ͷ���Դ
		if(er==ExecuteResult.sucess)//��ִ�н���Ĵ���
			result.append("alert(\"�����ɹ���\");");
		else{
			list=message.getServiceException();
			if(list==null||list.size()==0){
				result.append("alert(\"����ʧ�ܣ�ԭ�������������Ա��ϵ��\");");
			}else{
				se=(ServiceException)list.get(list.size()-1);
				result.append("alert(\"����ʧ�ܣ�"+se.getDescr().replaceAll("\n","")+"\");");
			}
		}
		result.append("window.location.href='"+nextpage+"?page="+ intpage+"&info="+info+"&eid="+request.getParameter("functionid")+"'; ");
		result.append("</script>");
		out.println(result.toString());
	}catch(Exception e){	
		if(con!=null)con.close();//�ͷ���Դ
		throw e;
	}
%>

<!-- InstanceEnd --></html>