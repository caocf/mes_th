<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<html><!-- InstanceBegin template="/Templates/new_�ύ.dwt.jsp" codeOutsideHTMLIsLocked="false" -->
<!-- InstanceBeginEditable name="�����" -->
<%@page import="th.tg.dao.*" %>
<%@ page import="th.tg.bean.*" %>
<jsp:directive.page import="mes.framework.*,mes.system.dao.*"/>
<%@page import="org.apache.commons.logging.Log,org.apache.commons.logging.LogFactory"%>
<%@taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<!-- InstanceEndEditable -->
<%	response.setHeader("Pragma","No-cache");  
   	response.setHeader("Cache-Control","no-cache");  
  	response.setDateHeader("Expires", 0); %>
<!-- ����Ϊ�޸��ύ����ʱ�� -->
<!-- InstanceBeginEditable name="������Ӷ���" -->
<jsp:useBean id="Conn" scope="page" class="com.qm.mes.th.helper.Conn_MES"/>
<%  String intpage=request.getParameter("intPage");	
String info = request.getParameter("info");
		info = info==null?"":new String(info);   
    Connection con=null;
    Statement stmt=null;
    ResultSet rs=null;
    try{
		final  Log log = LogFactory.getLog("searchset_insert.jsp");
	
 %>
 <!-- InstanceEndEditable -->
<!-- ���� -->
 
<head>
<link rel="stylesheet" type="text/css" href="../cssfile/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<!-- InstanceBeginEditable name="����" -->
<title>��Ӳ�ѯ����</title>
<!-- InstanceEndEditable -->
<!-- InstanceBeginEditable name="head" -->
<script type="text/javascript" src="../JarResource/META-INF/tag/taglib_common.js"></script>
<!-- InstanceEndEditable -->
</head>
<div class="title">
<!-- InstanceBeginEditable name="������" -->
		��Ӳ�ѯ����
<!-- InstanceEndEditable -->
</div>
<body bgcolor="#FFFFFF">
<div align="center">
<!-- InstanceBeginEditable name="��" -->
<form  name="form1"  action="searchset_adding.jsp" method="get" onSubmit="return checkinput();">
    <table class="tbnoborder" border="1">
      <tr>
        <td width="120">��ѯ�������ƣ�</td>
        <td width="220">
		<mes:inputbox id="cSearchname" name="cSearchname" size="27" maxlength="25" reSourceURL="../JarResource/" colorstyle="box_black" />
		</td>
      </tr>
      <tr>
        <td width="120">����װ��</td>
        <td width="220">
		<select id="cWA" name="cWA">
			<option value="W">��װ</option>
			<option value="A">��װ</option>
		</select>
		</td>
      </tr>
	  <tr>
        <td>�������룺</td>
        <td>
       	<mes:inputbox id="cFactory" name="cFactory" size="27" maxlength="25" reSourceURL="../JarResource/" colorstyle="box_black" />
		</td>
      </tr>
	  <tr>
        <td>�������ƣ�</td>
        <td>
       	<mes:inputbox id="cDscFactory" name="cDscFactory" size="27" maxlength="25" reSourceURL="../JarResource/" colorstyle="box_black" />
		</td>
      </tr>
	  <tr>
        <td>���ʹ��룺</td>
        <td>
       	<mes:inputbox id="cCarType" name="cCarType" size="27" maxlength="25" reSourceURL="../JarResource/" colorstyle="box_black" />
		</td>
      </tr>
	  <tr>
        <td>�������ƣ�</td>
        <td>
       	<mes:inputbox id="cDscCarType" name="cDscCarType" size="27" maxlength="25" reSourceURL="../JarResource/" colorstyle="box_black" />
		</td>
      </tr>
	  <tr>
        <td>��ע��</td>
        <td>
       	<mes:inputbox id="cRemark" name="cRemark" size="27" maxlength="25" reSourceURL="../JarResource/" colorstyle="box_black" />
		</td>
      </tr>
    </table>
    <div id="divnamecheck"></div><br>
    <table width="384" class="tbnoborder">
    <input type = "hidden" name="intpage" value="<%=intpage%>">
    <input type = "hidden" name="info" value="<%=info%>">
      <tr>
        <td class="tdnoborder" width="123" >
		 <mes:button id="button1" reSourceURL="../JarResource/" submit="true" value="�ύ"/>
		</td>
        <td  class="tdnoborder" width="126" >
		 <mes:button id="button2" reSourceURL="../JarResource/" submit="false" value="����"/>
		</td>
        <td class="tdnoborder" width="119" >
		 <mes:button id="button3" reSourceURL="../JarResource/" onclick = "func()"  value="����"/>
		</td>
      </tr>
    </table>
	</form>
	<!-- InstanceEndEditable -->
 </div>
</body>
<!-- InstanceBeginEditable name="������֤" -->

<script type="text/javascript">
var namecheck="true";//�����ظ��ж�
function checkinput(){
  var re =  /^[0-9]+$/;
  return true;
}
//����
function func(){
	window.location.href="searchset_manage.jsp";
}

</script>
<!-- InstanceEndEditable -->
<!-- InstanceEnd --></html>

<%
    //�ͷ���Դ
	}catch(Exception e)
	{
		throw e;
	}finally{
		//�ͷ���Դ
		if(rs!=null)rs.close();
		if(stmt!=null)stmt.close();
		if(con!=null)con .close();
	}
 %>