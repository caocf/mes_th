<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@page import="th.tg.factory.SearchSetFactory"%>
<html><!-- InstanceBegin template="/Templates/new_�ύ.dwt.jsp" codeOutsideHTMLIsLocked="false" -->
<!-- InstanceBeginEditable name="�����" -->
<%@page import="th.tg.bean.*,java.util.*,mes.framework.dao.*,mes.system.dao.*" %>
<%@page import="org.apache.commons.logging.Log,org.apache.commons.logging.LogFactory"%>
<%@taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<!-- InstanceEndEditable -->
<%	response.setHeader("Pragma","No-cache");  
   	response.setHeader("Cache-Control","no-cache");  
  	response.setDateHeader("Expires", 0); %>
<!-- ����Ϊ�޸��ύ����ʱ�� -->
<!-- InstanceBeginEditable name="������Ӷ���" -->
<jsp:useBean id="Conn" scope="page" class="common.Conn_MES"/>
 <%
 	String info = request.getParameter("info");
		info = info==null?"":new String(info);
 	String int_id = request.getParameter("int_id");
 	String intpage=request.getParameter("intPage");
 	System.out.println("searchset_update ҳ����"+intpage);
	if(int_id==null){
		out.println("<script>alert(\"����Ϊ��\"); window.location.href=\"searchset_manage.jsp\";</script>");
		return;
	} 
    Connection con=null;
    try{
	    con = Conn.getConn();
		SearchSetFactory factory = new SearchSetFactory();
		SearchSet ss = factory.getSearchSetById(Integer.parseInt(int_id),con);
		final  Log log = LogFactory.getLog("searchset_update.jsp");
		
		
 %>
 <!-- InstanceEndEditable -->
 
<head>
<link rel="stylesheet" type="text/css" href="../cssfile/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<!-- InstanceBeginEditable name="����" -->
<title>�޸Ĳ�ѯ������Ϣ</title>
<!-- InstanceEndEditable -->
<!-- InstanceBeginEditable name="head" -->
<script type="text/javascript" src="../JarResource/META-INF/tag/taglib_common.js"></script>
<!-- InstanceEndEditable -->
</head>
<div class="title">
<!-- InstanceBeginEditable name="������" -->
		�޸Ĳ�ѯ������Ϣ
<!-- InstanceEndEditable -->
</div>
<body bgcolor="#FFFFFF">
<div align="center">
<!-- InstanceBeginEditable name="��" -->
<form  name="form1"  action="searchset_updating.jsp" method="get"  onSubmit="return checkinput()">
    <table class="tbnoborder" border="1">
      <tr>
        <td width="100" >��ѯ���úţ�</td>
        <td width="80" colspan="1">
		<%=int_id %>
		</td>
	</tr>
	<tr>
        <td width="100">��ѯ��������</td>
        <td width="80">
		<mes:inputbox id="csearchname" name="csearchname" value="<%=ss.getCsearchName() %>" size="20" maxlength="25" reSourceURL="../JarResource/" colorstyle="box_black" />
		</td>
   	</tr>
	<tr>
		<td>����װ��</td>
        <td>
		<select id="cWA" name="cWA">
		<%if(ss.getCwa().equals("A")) {%>
			<option value="W">��װ</option>
			<option value="A" selected="selected">��װ</option>
		<%}else{%>
			<option value="W" selected="selected">��װ</option>
			<option value="A">��װ</option>
		<%}%>
		</select>
		</td>
	  </tr>
	  <tr>
        <td>�������룺</td>
        <td>
		<mes:inputbox id="cFactory" name="cFactory" value="<%=ss.getCfactory() %>" size="20" maxlength="25" reSourceURL="../JarResource/" colorstyle="box_black" />
		</td>
		</tr>
	  <tr>
        <td width="100">�������ƣ�</td>
        <td width="80">
		<mes:inputbox id="cDscFactory" name="cDscFactory" value="<%=ss.getCdscFactory() %>" size="20" maxlength="25" reSourceURL="../JarResource/" colorstyle="box_black" />
		</td>
   	  </tr>
	  <tr>
        <td width="100">���ʹ��룺</td>
        <td width="80">
		<mes:inputbox id="cCarType" name="cCarType" value="<%=ss.getCcarType() %>" size="20" maxlength="25" reSourceURL="../JarResource/" colorstyle="box_black" />
		</td>
   	  </tr>
	  <tr>
        <td width="100">�������ƣ�</td>
        <td width="80">
		<mes:inputbox id="cDscCarType" name="cDscCarType" value="<%=ss.getCdscCarType() %>" size="20" maxlength="25" reSourceURL="../JarResource/" colorstyle="box_black" />
		</td>
   	  </tr>
	  <tr>
        <td width="100">������</td>
        <td width="80">
		<mes:inputbox id="cRemark" name="cRemark" value="<%=ss.getCremark() %>" size="20" maxlength="25" reSourceURL="../JarResource/" colorstyle="box_black" />
		</td>
   	  </tr>
    </table>
		<div id="divnamecheck"></div>
		
    <br>
    <table width="384" class="tbnoborder">
    <input type = "hidden" name="intpage" value="<%=intpage%>">
      <tr>
        <td class="tdnoborder" width="123" >
		<mes:button id="button1" reSourceURL="../JarResource/" submit="true" value="�ύ"/>
		</td>
        <td  class="tdnoborder" width="126" >
		<mes:button id="button2" reSourceURL="../JarResource/" submit="false" value="����"/>
		</td>
        <td class="tdnoborder" width="119" >
		<mes:button id="button3" reSourceURL="../JarResource/" onclick="func()" value="����"/>
		</td>
      </tr>
    </table>
     <input type="hidden" name="int_id" value="<%=int_id %>"/>
	<input type = "hidden" name="info" value="<%=info%>">

	</form>
	<!-- InstanceEndEditable -->
 </div>
</body>
<!-- InstanceBeginEditable name="������֤" -->

<script type="text/javascript">
var namecheck="true";//�����ظ��ж�
function checkinput(){

var re =  /^[0-9]+$/;
	
}

function func(){
	window.location.href="searchset_manage.jsp?page=<%=intpage%>&info=<%=info%>";
}
</script>
<!-- InstanceEndEditable -->
<!-- InstanceEnd --></html>

<%
		//�ͷ���Դ

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null)
				con.close();
		}
	%>	
