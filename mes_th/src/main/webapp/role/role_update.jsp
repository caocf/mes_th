<%@ page language="java" import="java.sql.*" contentType="text/html;charset=gb2312"%>
<%@page import="java.util.*" %>
<%@taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<html><!-- InstanceBegin template="/Templates/�鿴����1.dwt.jsp" codeOutsideHTMLIsLocked="true" --> 
 <!-- InstanceBeginEditable name="�������" -->
<%@page import="com.qm.mes.util.tree.*"%>
 <jsp:useBean id="Conn" scope="page" class="com.qm.th.helpers.Conn_MES"/>
 
  <%@page import="com.qm.mes.framework.dao.*,com.qm.mes.framework.DataBaseType"%>
  <!-- InstanceEndEditable -->
 <!-- InstanceBeginEditable name="��ò�������֤" --><%
 	String roleno = request.getParameter("roleno");
 	String intpage=request.getParameter("intPage");
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
		  bct = new BuildTree( con, userid,"update",roleno);
	    }catch(SQLException sqle){
		  System.out.println(sqle);
    	}finally{
		  if(con!=null)con.close();
	    }
	    String no = map.get("roleno");
	    java.util.HashMap<Comparable,String> map1 = new java.util.HashMap<Comparable,String>();
		map1.put("������","0");
		map1.put("Ӧ�ü�","1");
		String rolerank = map.get("rank").equals("0")?"������":"Ӧ�ü�";
	    %>
	<head>
		<link rel="stylesheet" type="text/css" href="../cssfile/style.css">
<!-- InstanceBeginEditable name="����" -->
		<title>���½�ɫ������Ϣ</title>
<!-- InstanceEndEditable -->
<script type="text/javascript" src="../JarResource/META-INF/tag/taglib_common.js"></script>
	</head>
	<body>
	<div class="title"><!-- InstanceBeginEditable name="����2" -->���½�ɫ������Ϣ<!-- InstanceEndEditable --></div>
	<br>
	<div align="center"><!-- InstanceBeginEditable name="����1" -->
	
	<form id="form1" action="role_updating.jsp" method="get" onSubmit="return checkinput();">
    <table width="468" class="tbnoborder" border="1">
    <input type = "hidden" name="info" value="<%=info%>">
		<tr>
        <td align="center" width="145">��ɫ�ţ�</td>
        <td width="311">
        <mes:inputbox readonly="true" size="36" id="roleno" name="roleno" reSourceURL="../JarResource/" colorstyle="box_black"  value="<%=no%>"/>
        </td>
      </tr>
	<tr>
        <td align="center" width="145">��ɫ����</td>
        <td width="311">
        <mes:inputbox name="rolename" id="rolename"  size="36" maxlength="30" reSourceURL="../JarResource/" colorstyle="box_black"/>
      </td>
      </tr>
	  <%if(((String)session.getAttribute("user_rolerank")).equals("1")){%>
	 	 <input type="hidden" name="rank" value="1"/>
	  <%}else{%>
	<tr>
        <td align="center" width="145">����</td>
        <td width="311">
        <mes:selectbox colorstyle="box_black" id="selectbox1" name="rank" map="<%=map1%>" reSourceURL="../JarResource/" size="36" maxlength="30" readonly="false" selectSize="2" value="<%=rolerank %>"/>
      </td></tr><%}%>
	<tr>
        <td align="center" width="145">Ȩ�ޣ�</td>
        <td width="311" style="padding:20px 20px 20px 50px ">
			<%=bct.getHtmlCode()%>
      </td>
      </tr>
	<tr>
        <td align="center" width="145">��ҳ��</td>
        <td width="311"><input name="welcomepage" size="48" type="text"  class="box1" onMouseOver="this.className='box3'" onFocus="this.className='box3'" onMouseOut="this.className='box1'" onBlur="this.className='box1'" maxlength="100"onkeyup="this.value=this.value.replace(/\b[\d]*/,'');"  onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/\b[\d]*/,''))">
      </td>
      </tr>
	<tr>
        <td align="center" width="145">������</td>
        <td width="311">
        <mes:inputbox name="note" id="note" size="36"  maxlength="100" reSourceURL="../JarResource/" colorstyle="box_black" />
      </td>
      </tr>
    </table>
    <table class="tbnoborder">
    <input type = "hidden" name="intpage" value="<%=intpage%>">
      <tr>
	 	 <td width="111"  class="tdnoborder">
			<mes:button id="button1" reSourceURL="../JarResource/" submit="true" value="�ύ"/>
		</td>
		<td width="109"  class="tdnoborder">
      		<mes:button id="button2" reSourceURL="../JarResource/" submit="false"  onclick="resetPara()" value="����"/>
	  	</td>
        <td  class="tdnoborder" colspan="2"  >
        <mes:button id="button3" reSourceURL="../JarResource/"  value="�� ��"  onclick="func()"/>
        </td>
      </tr>
    </table>
	</form>
<script type="text/javascript">
var re = /^[a-zA-Z0-9\u4e00-\u9fa5]+$/; 
function checkinput(){
	if(!re.test(document.getElementById("rolename").value))
    {
	    alert("��ɫ��Ӧ����ĸ�����ֺͺ������!");
	    return false;
	}
	if(!re.test(document.getElementById("note").value))
	{
		alert("����Ӧ����ĸ�����ֺͺ������!");
		return false;
	}
	var rolename = document.getElementsByName("rolename")[0];
	var welcomepage = document.getElementsByName("welcomepage")[0];
	var note = document.getElementsByName("note")[0];
	if(rolename.value==""||welcomepage.value==""||note.value=="")	{
		alert("ȱ�ٲ���!");
		return false;
	}
	else
		return true;
}

function resetPara(){
//alert('1');
	var rolename = document.getElementsByName("rolename")[0];
	var rank = document.getElementsByName("rank")[0];
	var welcomepage = document.getElementsByName("welcomepage")[0];
	var note = document.getElementsByName("note")[0];
	
	rolename.value="<%=map.get("rolename")%>"
	rank.value="<%=map.get("rank")%>"
	welcomepage.value="<%=map.get("welcomepage")%>"
	note.value="<%=map.get("note")%>"
	rank.selectedIndex = "<%=map.get("rank")%>";

	rolename.focus();
	
}resetPara();
function func(){
	var pageIndex = <%=intpage%>;
	var int_id=<%=roleno%>;		
	window.location.href = 'role_manage.jsp?page='+ pageIndex+'&eid='+int_id+ '&info=<%=info%>';
	}
</script>
	<!-- InstanceEndEditable --></div>
</body><!-- InstanceEnd --></html>
<%	
	}catch(Exception e)
	{
		throw e;
	}finally{
		//�ͷ���Դ
		if(conn!=null)conn.close();
	}
%>









