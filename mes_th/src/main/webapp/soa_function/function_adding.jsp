<%@ page language="java" import="java.sql.*"
	contentType="text/html;charset=gb2312"%>
<%@page import="java.util.*,java.util.regex.Pattern"%>
<%@page import="com.qm.mes.framework.*"%>
<jsp:useBean id="Conn" scope="page" class="com.qm.th.helpers.Conn_MES" />

<%
	 /*
	 * ҵ�������������������Ϣ��У���������Ƿ��ظ���������Ӧ�������
	 */
	//��ȡ�������
	String functionname = request.getParameter("functionname");
    String intpage="";
	intpage=request.getParameter("intpage");
	String rank = request.getParameter("rank");
	String nodetype = request.getParameter("nodetype");
	String url = request.getParameter("url");
	String upnodeid = request.getParameter("upnodeid");
	String state = request.getParameter("state");
	String safemarkcode = request.getParameter("safemarkcode");
	String note = request.getParameter("note");
	String enable = "1";
	String userid = (String) session.getAttribute("userid");
	String flo_Order = request.getParameter("flo_Order");
	if(flo_Order==null||flo_Order.equals(""))
		flo_Order="0";

	if ((nodetype != null) && (nodetype.equals("2")))//�ڵ�
	{
		url = "";
		state = "1";
		safemarkcode = "";
		rank = "1";
	}

	/*System.out.println("begin");
	System.out.println("functionname:"+functionname);
	System.out.println("rank:"+rank);
	System.out.println("nodetype:"+nodetype);
	System.out.println("url:"+url);
	System.out.println("upnodeid:"+upnodeid);
	System.out.println("state:"+state);
	System.out.println("safemarkcode:"+safemarkcode);
	System.out.println("note:"+note);
	System.out.println(enable);
	System.out.println("d:"+userid);
	System.out.println("end");*/

	if (functionname == null || functionname.trim().equals("")
			|| nodetype == null || nodetype.trim().equals("")
			|| upnodeid == null || upnodeid.trim().equals("")
			|| note == null
			|| rank == null || url == null || safemarkcode == null
			|| state == null || rank == null || note == null) {
%>
<script type="text/javascript">
	alert("����Ϊ��");window.location.href="function_manage.jsp";
</script>
<%
	return;
	}
	//String str="\d*\.\d{2}|\d*";
	boolean b = Pattern.compile("^[\\d]{1,5}(\\.[\\d]{1,3}$|$)").matcher(flo_Order).find();
	if(!b){
	%>
<script type="text/javascript">
	alert("����˳���ӦΪFloat����������,��2,12.5!");window.location.href="function_manage.jsp?page=<%=intpage%>';
</script>

 	<%
 	}
	Connection con = null;
	ExecuteResult er = null;
	ServiceException se = null;
	//	String questiondesc="";

	List<?> list = null;
	try {
		//��ȡ��Դ
		con = Conn.getConn();

		IServiceBus bus = ServiceBusFactory.getInstance();
		IMessage message = MessageFactory.createInstance();
		//�����û�����
		message.setOtherParameter("con", con);
		message.setUserParameter("functionname", functionname);
		message.setUserParameter("nodetype", nodetype);
		message.setUserParameter("url", url);
		message.setUserParameter("upfunctionid", upnodeid);
		message.setUserParameter("safemarkcode", safemarkcode);
		message.setUserParameter("note", note);
		message.setUserParameter("state", state);
		message.setUserParameter("rank", rank);
		message.setUserParameter("userid", userid);
		message.setUserParameter("enable", enable);
		message.setUserParameter("flo_Order",flo_Order);

		//����soa�е�"ɾ������"���� ���̺�Ϊ29
		//todo
		er = bus.doProcess("29", message);
		//�ͷ���Դ
		if (con != null)
			con.close();

		//��ִ�н���Ĵ���
		if (er == ExecuteResult.sucess) {
%>
<script>
<!-- 
alert("�����ɹ���");window.location.href='function_manage.jsp?page=<%=intpage%>';
 -->
</script>
<%
			} else {
			list = message.getServiceException();
			if (list == null || list.size() == 0) {
%>
<script>
<!-- 
alert("����ʧ�ܣ�ԭ�������������Ա��ϵ��");window.location.href='function_manage.jsp?page=<%=intpage%>';
 -->
</script>
<%
		} else {
		se = (ServiceException) list.get(list.size() - 1);
%>
<script>
<!-- 
alert("����ʧ�ܣ�<%=se.getSource()%>");
window.location.href="function_manage.jsp?page=<%=intpage%>';
 -->
</script>
<%
		}
		}

	} catch (Exception e) {
		//�ͷ���Դ
		if (con != null)
			con.close();
%>
<script>
<!--  
alert("�����쳣��<%=e.toString()%>");window.location.href='function_manage.jsp?page=<%=intpage%>';
-->
</script>
<%
	throw e;
	}
%>





