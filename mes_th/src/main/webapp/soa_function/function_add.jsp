<%@ page language="java" import="java.sql.*" contentType="text/html;charset=gb2312"%>
<jsp:useBean id="Conn" scope="page" class="com.qm.th.helpers.Conn_MES"/>
<%@ page import="com.qm.mes.framework.dao.*"%>
<%@ page import="com.qm.mes.framework.DataBaseType"%>
<%@taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<%
	Connection con=null;
	Statement stmt=null;
	ResultSet rs = null; 
	String intpage=request.getParameter("intPage");	
	String info = request.getParameter("info");
		info = info==null?"":info;    
	try{
	
	con=Conn.getConn();
	stmt=con.createStatement();
//	String user_rolerank=user_rolerank=(String)session.getAttribute("user_rolerank");
%>


<html>
<head>
<link rel="stylesheet" type="text/css"  href="../cssfile/style.css">
<script type="text/javascript" src="../JarResource/META-INF/tag/taglib_common.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<script>
<!--
function check(thisform)
{  
	
  	if (thisform.functionname.value=='')
   	{
    	alert("�����빦�����ƣ�");
		thisform.functionname.focus();
		return false;
   	} 
   	if(thisform.nodetype.value==3)
   	{
   		if (thisform.url.value=='')
   		{
	    	alert("������URL��");
			thisform.url.focus();
			return false;
		}
		
		if(thisform.state.value==2)
		{
			alert("��ѡ��״̬!");
			thisform.state.focus();
			return false;
		}
		
		if(thisform.safemarkcode.value=='')
		{
			alert("�����밲ȫ���ʱ��!");
			thisform.safemarkcode.focus();
			return false;
		}
  	} 
  	if(thisform.nodetype.value=='')
  	{
  		alert("��ѡ��ڵ����ͣ�");
		thisform.url.focus();
		return false;
  	}	
  	
  	if(thisform.upnodeid.value==0)
  	{
  		alert("��ѡ����һ���ڵ㣡");
		thisform.upnodeid.focus();
		return false;
	}
	if(thisform.flo_Order.value.match(/^[\d]{1,5}(\.[\d]{1,3}$|$)/)==null){
		alert("����˳���ӦΪFloat����������,��2,12.5!");
	    return false;
	}

  	return true;
}	

function editurl(thisform)
{
	if(thisform.nodetype.value==3)
	{	
		thisform.url.value='';
		thisform.url.disabled=false;
		thisform.safemarkcode.value='';
		thisform.safemarkcode.disabled=false;
		thisform.state.value=1;
		thisform.state.disabled=false;
		thisform.rank.disabled=false;
	}
	else
	{
		thisform.url.value='��';
		thisform.url.disabled=true;
		thisform.safemarkcode.value='��';
		thisform.safemarkcode.disabled=true;
		thisform.state.value=2;
		thisform.state.disabled=true;
		thisform.rank.disabled=true
		thisform.rank.value="1";
		

	}
}
-->	
</script>
</head>
<body onLoad="form1.functionname.focus()">
<div class="title">����¼�¼</div>
<br>
<div align="center">
<form name=form1 method=get action="function_adding.jsp" >
  <table class="tbnoborder" border="1">
	<tr>
		<td>
		�������ƣ�
		</td>
        <td>
		<input type="text" name="functionname" size="50"  maxlength="30" class="box1" onMouseOver="this.className='box3'" onFocus="this.className='box3'" onMouseOut="this.className='box1'" onBlur="this.className='box1'">
		</td>     
    </tr>
    <tr>
		<td>
		����
		</td>
        <td >
		<span class="boxOut"><span class="boxIn">
			<select name="rank" disabled>
				<option value=1 selected>Ӧ�ü�</option>
                 <option value=2>������</option>
			</select>
		</span></span>
		</td>     
    </tr>
        <tr>
    	<td >
		�ڵ�����
		</td>
		<td >
		<span class="boxOut"><span class="boxIn">
		<select name="nodetype"  onBlur="editurl(form1)"  >
		<option value=2 selected>�ڵ�</option>
		<option value=3>Ҷ</option>
		</select>
		</span></span>
		</td>     
    </tr>
    <tr>
    	<td>
		URL��
		</td>
        <td >
		<input type="text" name="url" size="50" disabled value="��" maxlength="100" class="box1" onMouseOver="this.className='box3'" onFocus="this.className='box3'" onMouseOut="this.className='box1'" onBlur="this.className='box1'">
		</td>     
    </tr>

	<tr>
		<td >
		��һ���ڵ�
		</td>
		<td >
		<span class="boxOut"><span class="boxIn">
		<select name="upnodeid">
	<%  
	String sql= "";
	//sql="select nfunctionid,cfunctionname from data_function where cnodetype='2' or cnodetype='1' order by nfunctionid" ;
	IDAO_UserManager dao = DAOFactory_UserManager.getInstance(DataBaseType.getDataBaseType(con));
	sql=dao.getSQL_QueryLastNodeForNodeType();
	rs=stmt.executeQuery(sql);
	
	while(rs.next())
	{
		out.write("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
	}
%>
		</select>
		</span></span>
		</td>     
	</tr>
    <tr>
    	<td>
		״̬
		</td>
	    <td>
		<span class="boxOut"><span class="boxIn">
		<select name="state" disabled>
		<option value='1' >����</option>
		<option value='0'>����</option>
		</select>
		</span></span>
		</td>	
    </tr>
    <tr>
    	<td>
		��ȫ���ʱ��
		</td>
		<td>
		<input type="text" name="safemarkcode" maxlength="30" value="��" disabled class="box1" onMouseOver="this.className='box3'" onFocus="this.className='box3'" onMouseOut="this.className='box1'" onBlur="this.className='box1'">
		</td>
    </tr>
    <tr>
        <td>
		��ע
		</td>
		<td>
		<input type="text" name="note" maxlength="100" class="box1" onMouseOver="this.className='box3'" onFocus="this.className='box3'" onMouseOut="this.className='box1'" onBlur="this.className='box1'">
		</td>
    </tr>
     <tr>
        <td>
		����˳��
		</td>
		<td>
		<input type="text" name="flo_Order" maxlength="100" class="box1" onMouseOver="this.className='box3'" onFocus="this.className='box3'" onMouseOut="this.className='box1'" onBlur="this.className='box1'">
		</td>
    </tr>
</table>
<br>
<table  class="tbnoborder">
<input type = "hidden" name="intpage" value="<%=intpage%>">
    <tr>
		<td width="100" class="tdnoborder" >
   			<mes:button id="button1" reSourceURL="../JarResource/" submit="true" value="���" onclick="return check(form1)"/>
   		</td>
   
		<td width="100" class="tdnoborder" >
        	<mes:button id="button2" reSourceURL="../JarResource/" submit="false" onclick="resetPara()" value="����"/>
		</td>

		<td  width="100" class="tdnoborder" >
			<mes:button id="button3" reSourceURL="../JarResource/" value="����" onclick = "func()" />
		</td>
    </tr>
  </table>
</form>
</div>
</body>

<script type="text/javascript">
function resetPara()
{
	document.getElementsByName("functionname")[0].value="";
	document.getElementsByName("note")[0].value="";
	document.getElementsByName("flo_Order")[0].value="";
	document.getElementsByName("functionname")[0].focus();

}
function func(){
		var pageIndex = <%=intpage%>;
		window.location.href = "function_manage.jsp?page=" + pageIndex+ '&info=<%=info%>';
			}

</script>
</html>
<%
		//�ͷ���Դ	
		}catch(Exception e)
		{		
			e.printStackTrace();
		}finally {			
			if(stmt!=null)stmt.close();
			if(con!=null)con .close();
			}		
	%>






