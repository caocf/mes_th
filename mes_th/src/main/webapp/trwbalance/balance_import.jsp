<%@ page contentType="text/html; charset=GBK" language="java" import="java.sql.*" errorPage="" %>
<html>
<!-- InstanceBeginEditable name="�����" -->
<%@taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<%@page	import="org.apache.commons.logging.Log,org.apache.commons.logging.LogFactory"%>
<jsp:useBean id="Conn" scope="page" class="com.qm.th.helpers.Conn_MES"/>
<!-- InstanceEndEditable -->
<!-- ����Ϊ�޸��ύ����ʱ�� -->
<!-- InstanceBeginEditable name="������Ӷ���" -->
<%

%>
<!-- InstanceEndEditable -->
<!-- ���� -->
<style> 
	.cal_descr{ display:'none'}
</style>
<head>
<link rel="stylesheet" type="text/css" href="../cssfile/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<!-- InstanceBeginEditable name="����" -->
<title>��ĩ���㵼��</title>
<!-- InstanceEndEditable -->
<script type="text/javascript" src="../JarResource/META-INF/tag/taglib_common.js"></script>
</head>
<div class="title">
<!-- InstanceBeginEditable name="������" -->
		��ĩ���㵼��
<!-- InstanceEndEditable -->
</div>
<body>
<div align="center">
<!-- InstanceBeginEditable name="��" -->
<form  name="form1" enctype="multipart/form-data"
 action="balance_importing.jsp" method="post" onSubmit="return checkinput()">
    <table class="tbnoborder"  align="center">
      <tr>
	    <td>
			ѡ�����ļ���<input id="inputxls" name="inputxls" type="file">
		</td>
      </tr>
      <tr>
	    <td>
			ѡ�����ļ���<input id="inputxls" name="inputxls" type="file">
		</td>
      </tr>
      <tr>
	    <td>
			ѡ�����ļ���<input id="inputxls" name="inputxls" type="file">
		</td>
      </tr>
      <tr>
	    <td>
			ѡ�����ļ���<input id="inputxls" name="inputxls" type="file">
		</td>
      </tr>
      <tr>
	    <td>
			ѡ�����ļ���<input id="inputxls" name="inputxls" type="file">
		</td>
      </tr>
	  <tr><td >&nbsp;</td></tr>
	  <tr>
	  	<td class="tdnoborder">
			<input type="submit" id="button1"  value="�ϴ�"/>&nbsp;&nbsp;
		</td>
      </tr>
   <!-- 
      <tr>
      <td> </td>
      	<td align="right">
      	  <img width="50" src="../images/plus.bmp" onclick="">
      	  <img width="50" src="../images/minus.bmp" onclick="">
      	</td>
      </tr> -->
    </table>
    <br/>
	<div align="center" style="color:ff0000;font-size:9pt">ѡ�����ļ�����ļ�������Ϊ�ա�</div>
</form>
  <!-- InstanceEndEditable -->
 </div>
</body>
<!-- InstanceBeginEditable name="������֤" -->
<script type="text/javascript">
function checkinput(){
var file = form1.inputxls;
if(file.value=='')
	{
		alert("��ѡ���ϴ��ļ�");
		file.focus();
		return false;
	}if(file.value.substring(file.value.length-4).toLowerCase()!=".txt"){
		alert("��ѡ��txt�ļ�");
		file.focus();
		return false;
	}else{
		return true;
	}
}
</script>
<!-- InstanceEndEditable -->
<!-- InstanceEnd --></html>