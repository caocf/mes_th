<%@ page language="java" import="java.sql.*"
	contentType="text/html;charset=gb2312"%>
<jsp:useBean id="Conn" scope="page" class="common.Conn" />
<%@page import="java.util.List"%>
<%@ page import="mes.framework.*"%>
<%
	 /* 
	 * ʱ�䣺2007-06-25
	 * ���ߣ�����
	 * ҵ�������������������Ϣ��У���������Ƿ��ظ���������Ӧ�������
	 */

	//��ȡ�������
	String processid = request.getParameter("processid");
	String i_serveralias = request.getParameter("i_serveralias");
	String i_parameter = request.getParameter("i_parameter");
	String source = request.getParameter("source");
	String o_serveralias = request.getParameter("o_serveralias");
	String o_parameter = request.getParameter("o_parameter");
    String intpage="";
	intpage=request.getParameter("intpage");
	if (processid == null || processid.trim().equals("")
			|| i_serveralias == null || i_serveralias.trim().equals("")
			|| source == null || source.trim().equals("")
			|| i_parameter == null || i_parameter.trim().equals("")
			|| o_parameter == null | o_parameter.trim().equals("")) {
%>
<script>
<!-- 
alert("����Ϊ��");window.location.href='adapter_add.jsp';
 -->
</script>
<%
	return;
	}
	//������ԴΪ2�������������Է���
	if (source.trim().equals("2")
			&& (o_serveralias == null || o_serveralias.trim()
			.equals(""))) {
%>
<script>
<!-- 
alert("����Ϊ��");window.location.href='adapter_add.jsp';
 -->
</script>
<%
	return;
	}

	try {
		//		int int_source=Integer.parseInt(source);
		Integer.parseInt(source);
	} catch (Exception e_tran) {
%>
<script>
<!-- 
alert("������Դ������������");window.location.href='adapter_add.jsp';
 -->
</script>
<%
	return;
	}

	try {
		//int int_processid=
		Integer.parseInt(processid);
	} catch (Exception e_tran2) {
%>
<script>
<!-- 
alert("���̺Ų�����������");window.location.href='adapter_add.jsp';
 -->
</script>
<%
	return;
	}

	Connection con = null;
	ExecuteResult er = null;
	ServiceException se = null;
	//	String questiondesc="";

	List list = null;
	try {
		//��ȡ��Դ
		con = Conn.getConn();

		IServiceBus bus = ServiceBusFactory.getInstance();
		IMessage message = MessageFactory.createInstance();
		//�����û�����
		message.setUserParameter("processid", processid);
		message.setUserParameter("i_serveralias", i_serveralias);
		message.setUserParameter("i_parameter", i_parameter);
		message.setUserParameter("source", source);
		if (o_serveralias == null)
			o_serveralias = "";
		message.setUserParameter("o_serveralias", o_serveralias);
		message.setUserParameter("o_parameter", o_parameter);

		message.setOtherParameter("con", con);

		//����soa�е����� ��ʱ��Ϊ1 
		//todo
		er = bus.doProcess("1", message);

		//�ͷ���Դ
		if (con != null)
			con.close();

		//��ִ�н���Ĵ���
		if (er == ExecuteResult.sucess) {
%>
<script>
<!-- 
alert("�����ɹ���");window.location.href='adapter_manage.jsp?page=<%=intpage%>';
 -->
</script>
<%
			} else {
			list = message.getServiceException();
			if (list == null || list.size() == 0) {
%>
<script>
<!-- 
alert("����ʧ�ܣ�ԭ�������������Ա��ϵ��");window.location.href='adapter_manage.jsp?page=<%=intpage%>';
 -->
</script>
<%
		} else {
		se = (ServiceException) list.get(list.size() - 1);
%>
<script>
<!-- 
alert("����ʧ�ܣ�<%=se.getDescr()%>");window.location.href='adapter_manage.jsp?page=<%=intpage%>';
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
alert("�����쳣��<%=e.toString()%>");window.location.href='adapter_manage.jsp?page=<%=intpage%>';
-->
</script>
<%
	throw e;
	}
%>
