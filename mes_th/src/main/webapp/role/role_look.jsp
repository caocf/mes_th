<%@ page language="java" import="java.sql.*" contentType="text/html;charset=gb2312"%>
<%@page import="java.util.*" %>
<%@page import="com.qm.mes.framework.*" %>
<%@taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<html><!-- InstanceBegin template="/Templates/�鿴����1.dwt.jsp" codeOutsideHTMLIsLocked="true" --> 
 <!-- InstanceBeginEditable name="�������" -->
<%@page import="com.qm.mes.util.tree.*"%>
  <%@page import="com.qm.mes.framework.dao.*,com.qm.mes.framework.DataBaseType"%>
 <jsp:useBean id="Conn" scope="page" class="com.qm.th.helper.Conn_MES"/><!-- InstanceEndEditable -->
 <!-- InstanceBeginEditable name="��ò�������֤" --><%
 	String roleno = request.getParameter("roleno");
 	String intpage="";
	intpage=request.getParameter("intPage");
	String info = request.getParameter("info");
		info = info==null?"":info;
	if(roleno==null){
		out.println("<script>alert(\"����Ϊ��\"); window.location.href=\"role_manage.jsp\";</script>");
		return;
	} %> <!-- InstanceEndEditable -->
	<%String userid=(String)session.getAttribute("userid");
	Map<Comparable,String> map = new HashMap<Comparable,String>();
	Connection conn=null;
  	try{//��ȡ����zzzzz
		conn=Conn.getConn();
		Statement stmt = conn.createStatement();
		String sql="";
		%><!-- InstanceBeginEditable name="����SQL���" --><%
//	sql="select * from data_role r LEFT OUTER JOIN data_user u on r.nlastupdateuser=u.nusrno where r.nroleno="+roleno;
	sql = DAOFactory_UserManager.getInstance(DataBaseType.getDataBaseType(conn)).getSQL_QueryRoleAndUser(roleno);
%><!-- InstanceEndEditable --><%
		ResultSet rs=stmt.executeQuery(sql);
		if(rs.next()){
		%><!-- InstanceBeginEditable name="��ò�ѯ���" --><%
			map.put("roleno",rs.getString("nroleno"));
			map.put("rolename",rs.getString("crolename"));
			map.put("rank",rs.getString("crank"));
			map.put("welcomepage",rs.getString("cwelcomepage"));
			map.put("note",rs.getString("cnote"));
			map.put("username",rs.getString("CUSRNAME"));
		%><!-- InstanceEndEditable --><%
		}else{
			out.println("<script>alert(\"û�в�ѯ�����\");window.location.href='service_manage.jsp';</script>");
			if(conn!=null)conn.close();
			return;
		}stmt.close();
	   Connection con = null;
	   BuildTree bct=null;
	   try{
		  con = Conn.getConn();
		  bct = new BuildTree( con, userid,"look",roleno);
	   }catch(SQLException sqle){
		  System.out.println(sqle);
	   }finally{
		  if(con!=null)con.close();
	   }%>
	<head>
		<link rel="stylesheet" type="text/css" href="../cssfile/style.css">
		<script type="text/javascript" src="../JarResource/META-INF/tag/taglib_common.js"></script>
<!-- InstanceBeginEditable name="����" -->
		<title>�鿴��ɫ������Ϣ</title>
<!-- InstanceEndEditable -->
	</head>
	<body>
	<div class="title"><!-- InstanceBeginEditable name="����2" -->�鿴��ɫ������Ϣ<!-- InstanceEndEditable --></div>
	<br>
	<div align="center"><!-- InstanceBeginEditable name="����1" -->
    <table class="tbnoborder" width="468" border="1">
      <tr>
        <td width="145" align="center">��ɫ�ţ�</td>
        <td width="311"><%=map.get("roleno")%>
        </td>
      </tr>
<tr>
        <td width="145" align="center">��ɫ����</td>
        <td width="311"><%=map.get("rolename")%>
      </td>
      </tr>
<tr>
        <td width="145" align="center">����</td>
        <td width="311"><%=map.get("rank").equals("1")?"Ӧ�ü�":"������"%>
      </td>
      </tr>
<tr>
        <td width="145" align="center">Ȩ�ޣ�</td>
        <td width="311" style="padding:20px 20px 20px 50px ">
			<%=bct.getHtmlCode()%>
      </td>
      </tr>
<tr>
        <td width="145" align="center">��ҳ��</td>
        <td width="311"><%=map.get("welcomepage")%>
      </td>
      </tr>
<tr>
        <td width="145" align="center">������</td>
        <td width="311"><%=map.get("note")%>
      </td>
      </tr>
	   <%if(((String)session.getAttribute("user_rolerank")).equals("0")){%>
	   <tr>
			<td width="119" align="center">���ά�����û�����</td>
			<td width="266">
				<%=map.get("username")%>
			</td>
      </tr><%}%>
    </table>
    <table class="tbnoborder">
      <tr>
        <td  class="tdnoborder" colspan="2"  >
        <mes:button id="button1" reSourceURL="../JarResource/"  value="�� ��"  onclick="func()"/>
        </td>
      </tr>
    </table>
	<!-- InstanceEndEditable --></div>
</body><!-- InstanceEnd -->
<script>
		<!--
			function func(){
				var pageIndex = <%=intpage%>;
				var int_id=<%=roleno%>;		
				window.location.href = 'role_manage.jsp?page='+ pageIndex+'&eid='+int_id+ '&info=<%=info%>';							
			}
		-->
	</script>
</html>
<%	//�ͷ���Դ
	}catch(Exception e)
	{
		throw e;
	}finally{
		if(conn != null)conn.close();
	}
%>









