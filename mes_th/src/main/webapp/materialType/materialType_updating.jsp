<%@ page contentType="text/html; charset=gb2312" language="java"
	import="java.sql.*,java.util.*,mes.framework.*,java.util.regex.Pattern" errorPage=""%>
<jsp:useBean id="Conn" scope="page" class="com.qm.mes.th.helper.Conn_MES"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>�ޱ����ĵ�</title>
	</head>
	<%
	/*
	 * �������ͣ� �޸�����������Ϣ
	 */
	String userid=(String)session.getAttribute("userid");	
	 
	//��ȡ�������
	String element_name = request.getParameter("element_name");
	String parent_id = request.getParameter("parent_id");
	String description = request.getParameter("description");
	userid = userid==null?"-1":userid;
	parent_id = parent_id==null||parent_id.equals("")?"-1":parent_id;
	//description = description==null||description.equals("")?"":description;
	if(element_name==null||element_name.trim().equals("")||userid==null||userid.trim().equals(""))
	{
%>
<script>
<!-- 
alert("����Ϊ��");window.location.href='materialType_view.jsp';
 -->
</script>
<%
		return;
	}	
		String a[]=new String[element_name.length()];
	int sum=0;
	for(int i=0;i<element_name.length();i++){
		int j = i+1;
		a[i]=element_name.substring(i,j );
		boolean hanzi=Pattern.compile("[\u4e00-\u9fa5]").matcher(a[i]).find();
		boolean shuzi = Pattern.compile("[a-zA-Z_0-9]").matcher(a[i]).find();
		if(true==hanzi){
			sum = sum+2;
		}else if(true==shuzi){
			sum = sum+1;
		}
	}
		if(sum>40)
	{
%>
<script>
<!-- 
alert("����ֵ���40���ַ�,ÿ����ռ2���ַ�");window.location.href='materialType_view.jsp';
 -->
</script>
<%
		return;
	}
	Connection con=null;
	ExecuteResult er=null;
	ServiceException se=null;
	
	List list=null;
	try
	{
		//��ȡ��Դ
		con=Conn.getConn();
		
		IServiceBus bus = ServiceBusFactory.getInstance();
		IMessage message = MessageFactory.createInstance();
		//�����û�����
		message.setUserParameter("element_name", element_name);
		message.setUserParameter("parent_id", parent_id);
		message.setUserParameter("description",description);
		message.setUserParameter("userid",userid);
		message.setOtherParameter("con", con);
		
		//����soa�е����� ��ʱ��Ϊ1 
		//todo "41"�����̶��������"�޸���������"
		er=bus.doProcess("41", message);
		//�ͷ���Դ
		if(con!=null)con.close();
		
		//��ִ�н���Ĵ���
		if(er==ExecuteResult.sucess)
		{
%>
<script>
<!-- 
alert("�����ɹ���");window.location.href='materialType_view.jsp';
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
alert("����ʧ�ܣ�ԭ�������������Ա��ϵ��");window.location.href='materialType_view.jsp';
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
alert('����ʧ�ܣ�<%=se.getDescr().replaceAll("\n","")%>');window.location.href='materialType_view.jsp';
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
alert('�����쳣��<%=e.toString().replaceAll("\n","")%>');window.location.href='materialType_view.jsp';
-->
</script>	
<%
	throw e;
	}
	%>
	<body>
	</body>
</html>
