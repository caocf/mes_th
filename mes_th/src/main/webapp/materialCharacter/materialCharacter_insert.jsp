<%@ page contentType="text/html; charset=gb2312" language="java" import="" errorPage="" %>
<html>
<%@taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<%	response.setHeader("Pragma","No-cache");  
   	response.setHeader("Cache-Control","no-cache");  
  	response.setDateHeader("Expires", 0); %>
<head>
<link rel="stylesheet" type="text/css" href="../cssfile/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>�����������</title>
<script type="text/javascript" src="../JarResource/META-INF/tag/taglib_common.js"></script>
</head>
<div class="title">
		�����������
</div>
<body>
<div align="center">
<form  name="form1"  action="materialCharacter_adding.jsp" method="get" onSubmit="return checkinput()">
    <table class="tbnoborder">
      <tr>
        <td width="119">������������</td>
        <td width="266">
        <mes:inputbox name="element_name" size="36" id="element_name" maxlength="40" reSourceURL="../JarResource/" colorstyle="box_black" /></td>
      </tr>
	  <tr>
        <td width="119">������Ϣ��</td>
        <td width="266">
        <mes:inputbox name="description" size="36" id="description" reSourceURL="../JarResource/" colorstyle="box_black" /></td>
      </tr>
    </table>
    <br>
    <table width="384" class="tbnoborder">
      <tr>
        <td class="tdnoborder" width="123" ><span class="btn2_mouseout" onMouseOver="this.className='btn2_mouseover'"
	 onMouseOut="this.className='btn2_mouseout'" >
          <input class="btn2"  type="submit" name="Submit" value="�ύ"/>
        </span></td>
        <td  class="tdnoborder" width="126" ><span class="btn2_mouseout" onMouseOver="this.className='btn2_mouseover'"
	 onMouseOut="this.className='btn2_mouseout'" >
          <input class="btn2"  type="reset" name="button"  onClick="resetPara()" value="����"/>
        </span></td>
        <td class="tdnoborder" width="119" ><span class="btn2_mouseout" onMouseOver="this.className='btn2_mouseover'"
	 onMouseOut="this.className='btn2_mouseout'" >
          <input class="btn2"  type="button" name="button2"  onClick="window.location.href='materialCharacter_view.jsp'" value="����"/>
        </span></td>
      </tr>
    </table>
	</form>
 </div>
</body>
<script type="text/javascript">
function checkinput(){
var re = /^[a-zA-Z0-9\u4e00-\u9fa5]+$/;
if(form1.element_name.value=="")
	{
		alert("����Ϊ��");
		return false;
	}else if(!re.test(document.getElementById("element_name").value))
    {
	    alert("����������Ӧ����ĸ�����ֺͺ������!");
	    return false;
	}else
		return true;

}
function resetPara(){
	document.getElementsByName("element_name")[0].focus();
}
</script>
</html>