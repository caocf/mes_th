<%@ page language="java" import="java.sql.*" contentType="text/html;charset=gb2312"%>
<jsp:useBean id="Conn_MES" scope="page" class="common.Conn_MES"/>
<%@page import="common.*"%>
<%
	 /* 
	 * ʱ�䣺2007-07-5
	 * ���ߣ�����
	 * ҵ�������������û�����
	 */
	 
	String userid=(String)session.getAttribute("userid");
	if(userid==null) 
	{
		out.write("���ʱ��ܾ���");
		return;
	}
	//��ȡ�������
	String username=(String)session.getAttribute("username");
	String password=request.getParameter("txt_password");
	String newpassword=request.getParameter("txt_newpassword");
	
	newpassword=Security.clearSingleQuotationMarksFlaw(newpassword);
	//System.out.println(newpassword);
	
	Connection con=null;
	Statement stmt=null;
	
	if(newpassword==null||newpassword.trim().equals(""))
	{
%>
<script>
<!-- 
alert("����Ϊ��");window.location.href='changepassword.jsp';
 -->
</script>
<%
		return;
	}
	
	try
	{
		//��ȡ��Դ
		con=Conn_MES.getConn();
		stmt = con.createStatement();
		MD5 md5=new MD5();
		String dataPassword = "";
		
		String msql = "select cpassword from data_user where cusrname='"+username+"'";
		ResultSet rs = stmt.executeQuery(msql);
		if(rs.next()){
			dataPassword = rs.getString(1);
		}
		if(!md5.getMD5ofStr(password).equals(dataPassword)){
%>
<script>
<!-- 
alert("ԭ�������벻��ȷ");window.location.href='changepassword.jsp';
 -->
</script>
<%
		return;
	}	
		String sql = "update data_user set CPASSWORD='" + md5.getMD5ofStr(newpassword) + "' where NUSRNO=" + userid+"";
		stmt.executeUpdate(sql);
%>
<script>
<!-- 
alert("�����ɹ���");window.location.href='body.jsp';
 -->
</script>
<%
		
	}
	catch(Exception e)
	{	
%>
<script>
<!--  
alert("�����쳣��<%=e.toString()%>");window.location.href='changepassword.jsp';
-->
</script>	
<%
		throw e;
	}finally{
		//�ͷ���Դ
		if(stmt!=null)stmt.close();
		if(con!=null)con.close();
	}
%>
		