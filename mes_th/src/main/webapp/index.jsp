<%@ page language="java" contentType="text/html;charset=GB2312"%>
<%@taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<jsp:directive.page import="java.util.Calendar" />
<html>
	<head>
		<title>MESϵͳ--����Ա��¼</title>
		<link rel="stylesheet" type="text/css" href="cssfile/style.css">
		<script>
<!-- 
function checkinput(thisForm)
{
	if (thisForm.name.value==""){alert("�������û���");thisForm.name.focus();return false;}
	if (thisForm.pass.value==""){alert("�������û�����");thisForm.pass.focus();return false;}	
	return true;
}
-->
</script>
		<script type="text/javascript" src="calendar.js"></script>
		<script type="text/javascript" src="JarResource/META-INF/tag/taglib_common.js"></script>
		<script language="javascript">
   var CalendarWebControl = new atCalendarControl();
  </script>

	</head>
	<body topmargin="0" leftmargin="0" rightmargin=0 bottommargin=0
		onLoad="document.frm1.name.focus();" background="" bgcolor="#FAFAFA"
		scroll="NO">
		<%
			Calendar c = Calendar.getInstance();
			int yy = c.get(Calendar.YEAR);
			int mm = c.get(Calendar.MONTH) + 1;
			int dd = c.get(Calendar.DAY_OF_MONTH);
			String dateStr = String.valueOf(yy);
			if (mm < 10)
				dateStr = dateStr + "-" + "0" + String.valueOf(mm);
			else
				dateStr = dateStr + "-" + String.valueOf(mm);

			if (dd < 10)
				dateStr = dateStr + "-" + "0" + String.valueOf(dd);
			else
				dateStr = dateStr + "-" + String.valueOf(dd);
		%>
		<div id="head" class="head">
			<div id="head1"></div>
			<div id="head4"></div>
			<div id="head2"></div>
		</div>
		<form action="log.jsp" name="frm1" onSubmit="return checkinput(this)" method="get">
			<div id="logonwindow" class="logonwindow" align="center">
				<div id="first" class="first">
					��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;¼
				</div>
				<div id="second" class="second">
					��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
					<input class="box1" name="dateInput" type="text" id="dateInput"
						size="25" maxlength="15" readonly value=<%=dateStr%>
						onclick="CalendarWebControl.show(this,this.value);"
						onMouseOver="this.className='box2'"
						onFocus="this.className='box2'" onMouseOut="this.className='box1'"
						onBlur="this.className='box1'" />
				</div>
				<div id="third" class="third">
					�û���&nbsp;&nbsp;&nbsp;&nbsp;
					<input class="box1" type="text" name="name" value="" size="25"
						onMouseOver="this.className='box2'"
						onFocus="this.className='box2'" onMouseOut="this.className='box1'"
						onBlur="this.className='box1'">
				</div>
				<div id="fourth" class="fourth">
					��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
					<input class="box1" type="password" name="pass" size="25"
						maxlength="15" onMouseOver="this.className='box2'"
						onFocus="this.className='box2'" onMouseOut="this.className='box1'"
						onBlur="this.className='box1'">
				</div>
				<div id="fifth" class="fifth">
				<!-- 
					<span class="btn_mouseout"
						onMouseOver="this.className='btn_mouseover'"
						onmouseout="this.className='btn_mouseout'">
						<input class="btn1" type=submit value="�� ¼" name=submit>
					</span>
					 -->
					 <mes:button id="button1" reSourceURL="JarResource/" submit="true" value="�� ¼"/>
					 &nbsp;&nbsp;
					<!--  
					<span class="btn_mouseout"
						onMouseOver="this.className='btn_mouseover'"
						onmouseout="this.className='btn_mouseout'">
						<input class="btn1" type=reset value="�� ��">
					</span>
					 -->
					 <mes:button id="button2" reSourceURL="JarResource/"submit="false" onclick="resetPara()" value="�� ��"/>
					 &nbsp;&nbsp;
					 <!-- 
					<span class="btn_mouseout"
						onMouseOver="this.className='btn_mouseover'"
						onmouseout="this.className='btn_mouseout'">
						<input	class="btn1" type=reset value="�� ��"
							onclick="window.parent.opener=null;window.close();"
							onblur="document.all.dateInput.focus()" class="btn1">
					</span>
					 -->
					 <mes:button id="button3" reSourceURL="JarResource/" onclick="close()" value="�� ��"/>
				</div>
			</div>
		</form>

		<div id="remark" class="remark">
			[����] ӦΪ��ǰҪִ�еĹ�����������Ӧ������
			<br>
			���磺1��2���賿��¼ϵͳ
			<br>
			��ʱҪִ�еĹ���������1��1��ҹ������
			<br>
			��¼����ӦѡΪ1��1��
		</div>
		<div id="indexfoot" class="foot">
		</div>
	</body>
</html>
<script type="text/javascript">
function resetPara()
{
	document.getElementsByName("name")[0].value="";
	document.getElementsByName("pass")[0].value="";
	document.getElementsByName("name")[0].focus();

}
function close()
{
    window.parent.opener=null;
    window.close();
    document.all.dateInput.focus();
}
</script>