<%@ page language="java" contentType="text/html;charset=gb2312"%>

<%

	String userid=(String)session.getAttribute("userid");
	if(userid==null) 
	{
		out.write("���ʱ��ܾ���");
		return;
	}
	
%>
<html>
<head>
<link  rel="stylesheet" type="text/css"  href="cssfile/style.css">
</head>
<body onLoad="frm1.txt_password.focus()">
<div class="title">��������</div>
<div align="center">
<form name=frm1 action="cpwdconfirm.jsp" method="post" onSubmit="return test()" >
  <table width="335">
    <tr>
      <td width="107">ԭ���룺</td>
      <td width="229"><input type=password name="txt_password" maxlength="30" class="box1" onMouseOver="this.className='box3'" onFocus="this.className='box3'" onMouseOut="this.className='box1'" onBlur="this.className='box1'"></td></tr>
    
    <tr><td>�����룺</td><td><input type=password name="txt_newpassword" maxlength="30" class="box1" onMouseOver="this.className='box3'" onFocus="this.className='box3'" onMouseOut="this.className='box1'" onBlur="this.className='box1'"></td></tr>
   
    <tr><td>ȷ�����룺</td><td><input type=password name="txt_checkpassword" maxlength="30" class="box1" onMouseOver="this.className='box3'" onFocus="this.className='box3'" onMouseOut="this.className='box1'" onBlur="this.className='box1'"></td></tr>
    
  </table>
  <br>
  <table id="tbnoborder">
  <tr>
  	<td width="70" class="btnInTd" id="tdnoborder">
  <span class="btn2_mouseout" onMouseOver="this.className='btn2_mouseover'"
onMouseOut ="this.className='btn2_mouseout'">
<input class="btn2" name="btn_submit" type=submit value="��������" >
</span>
	</td>
	<td width="87" class="btnInTd" id="tdnoborder">
  <span class="btn2_mouseout" onMouseOver="this.className='btn2_mouseover'"
onMouseOut ="this.className='btn2_mouseout'">
<input class="btn2" name="btn_reset" type=reset value="����" >
</span>
  </td>
  <td width="68" class="btnInTd" id="tdnoborder">
  <span class="btn2_mouseout" onMouseOver="this.className='btn2_mouseover'"
onMouseOut ="this.className='btn2_mouseout'">
<input class="btn2" name="btn_close" type="button" onClick="window.location.href='body.jsp'" value="�ر�" >
</span>
  </td>
</tr>
</table>
</form>
</div>
<script language="javascript">
<!--
  function test(){
    var password=frm1.txt_password.value;
    var newpassword=frm1.txt_newpassword.value;
    var checkpassword=frm1.txt_checkpassword.value;

    if(password.length==0){
		alert("����д����!");
		frm1.txt_password.focus();
        return false;
    }
    if(newpassword.length==0){
		alert("����д������!");
		frm1.txt_newpassword.focus();
		return false;
    }
    if(checkpassword.length==0)
    {
        alert("����дȷ������!");
		frm1.txt_checkpassword.focus();
		return false;
    }
    if(newpassword!=checkpassword)
    {
    	alert("��������ȷ�����벻��!");
		frm1.txt_newpassword.value="";
		frm1.txt_checkpassword.value="";
		frm1.txt_newpassword.focus();
		return false;
    }
    if (thisform.password.value.indexOf(" ")>=0||thisform.newpassword.value.indexOf(" ")>=0||thisform.checkpassword.value.indexOf(" ")>=0)
   	{
    	alert("�����в������пո�");
		thisform.password.focus();
		return false;
   	}
    //frm1.submit();
    return true;
  }
//-->
</script>
</body>
</html>