<%@ page language="java" import="java.sql.*" contentType="text/html;charset=gb2312"%>
<jsp:useBean id="Conn" scope="page" class="common.Conn_MES"/>
<%@ page import="mes.framework.dao.*"%>
<%@ page import="mes.framework.DataBaseType"%>
<%@taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<html><%
	String id=request.getParameter("id");
	String intpage=request.getParameter("intPage");
	String info = request.getParameter("info");
		info = info==null?"":info;
	if(id==null||id.trim().equals(""))
	{
%>
<script type="text/javascript">
	alert("����Ϊ��");window.location.href="function_manage.jsp";
	
</script>
<%
    return;
}
    String functionid="";
	String functionname="";
	String nodetype="";
	String url="";
	String upfunctionid="";
	String safemarkcode="";
	String note="";
	String state="";
	String upfunctionname="";
	String flo_Order = "";
	String rank="";
	
	Connection con=null;
	Statement stmt=null;
	ResultSet rs=null;
	
	String user_rolerank="";
	user_rolerank=(String)session.getAttribute("user_rolerank");

    try{
		con=Conn.getConn();
		stmt=con.createStatement();
		rs=null;
		String sql="";
		//String sql="select nfunctionid,cfunctionname,curl,cnodetype,nupfunctionid,csafemark,cnote,cstate,crank from data_function where nfunctionid='"+id+"'";
	
		IDAO_UserManager dao = DAOFactory_UserManager.getInstance(DataBaseType.getDataBaseType(con));
	    sql=dao.getSQL_QueryFunctionForFunctionIdAndRank(Integer.parseInt(id),user_rolerank);
		rs=stmt.executeQuery(sql);
		if(rs.next())
		{
			upfunctionid=rs.getString(1);
			functionid=rs.getString(2);
			functionname=rs.getString(3);
			state=rs.getString(4);
			nodetype=rs.getString(5);
			url=rs.getString(6);
			safemarkcode=rs.getString(7);
			note=rs.getString(10);
			flo_Order=rs.getString(13);
			
		    if(functionid==null)functionid="";
			if(functionname==null)functionname="";
			if(url==null)url="";
			if(nodetype==null)nodetype="";
			if(upfunctionid==null)upfunctionid="";
			if(safemarkcode==null)safemarkcode="";
			if(state==null)state="";
			if(note==null)note="";
			if(flo_Order==null)flo_Order="";
			rank=rs.getString("crank");
			
		}
		if(nodetype.equals("1"))
		{
			upfunctionname="��";
		}
		else
		{
			rs=stmt.executeQuery("select cfunctionname from data_function where nfunctionid='"+upfunctionid+"'");	
			if(rs.next())upfunctionname=rs.getString(1);
		}
		
		 
	//String sql= "";
	//sql="select nfunctionid,cfunctionname from data_function where cnodetype='2' or cnodetype='1' order by nfunctionid" ;
	//IDAO_UserManager dao = DAOFactory_UserManager.getInstance(DataBaseType.getDataBaseType(con));
	sql=dao.getSQL_QueryLastNodeForNodeType();
	rs=stmt.executeQuery(sql);
	java.util.HashMap<Comparable,String> map = new java.util.HashMap<Comparable,String>();
	       
	while(rs.next())
	{
		 map.put(rs.getString(2),rs.getString(1));
		//out.write("<option value="+rs.getString(1)+">"+rs.getString(2)+"</option>");
	}

	
%>

<head>

<link rel="stylesheet" type="text/css" href="../cssfile/style.css">
<script type="text/javascript" src="../JarResource/META-INF/tag/taglib_common.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<script>
<!--
function check(thisform)
{  

  	if(thisform.functionname.value=='')
   	{
    	alert("�����빦�����ƣ�");
		thisform.functionname.focus();
		return false;
   	}
<%
if(nodetype.equals("3"))
{
%> 

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
<%
}
%>	
if(thisform.flo_Order.value.match(/^[\d]{1,5}(\.[\d]{1,3}$|$)/)==null){
		alert("����˳���ӦΪFloat����������,��2,12.5!");
	    return false;
	}
  	return true;
}	

-->
</script>
</head>


<body onLoad="form1.functionname.focus()">
<div class="title"><br>���¹�����Ϣ</div>
<div align="center">
<form name=form1 method=get action="function_updating.jsp" onSubmit="return check(form1)">
  <table class="tbnoborder" border="1">
      <tr>
        <td>
		���ܴ���<font color="#ff0000">*</font>��		</td>
        <td><%=functionid%><input type="hidden" name="functionid" value="<%=functionid%>"></td>
	</tr>
	<tr>
		<td>
		�������ƣ�</td>
        <td>
		<input type="text" name="functionname" size="50" value="<%=functionname%>" 
maxlength="15" class="box1" type=text  onMouseOver="this.className='box3'" onFocus="this.className='box3'" onMouseOut="this.className='box1'" onBlur="this.className='box1'"></td>     
    </tr>
	<tr>
		<td>����</td> 
        <td>
		<span class="boxOut"><span class="boxIn">
		<!--<select name="rank">
		<option value=1 <%//if(nodetype.equals("2")) out.println("disable");%>>Ӧ�ü�</option>
        <option value=0>������</option>
        </select>	-->	
	<%
		if(nodetype.equals("3")) 
	    {
		 	out.println(" <select name='rank'>");
		 	out.println("<option value=1 "+("1".equals(rank)?"selected":"")+" >Ӧ�ü�</option>");
			out.println("<option value=0 "+("0".equals(rank)?"selected":"")+" >������</option>");
		 	out.println("</select>");
		 	
		 	    
        }
	  	else
	    {
			out.println(" <select name='rank'  disabled='disabled'>");
		 	out.println("<option value=1>Ӧ�ü�</option>");
			out.println("</select>");	     
		}
		%>
       </td>   
    </tr>
	 <tr>
    	<td>
		URL</td>   
        <td>
		<input type="text"  maxlength="100" name="url" size="50" class="box1" type=text  onMouseOver="this.className='box3'" onFocus="this.className='box3'" onMouseOut="this.className='box1'" onBlur="this.className='box1'"
<%
	if(nodetype.equals("3") )
		out.write("value="+url);
	else
		out.write(" disabled value='��' ");
%> >	</td>   
    </tr>
    <tr>
    	<td>
    	�ڵ����ͣ�    	</td>
        <td ><input type=hidden name="nodetype" value=<%=nodetype%>>
         <input type = "hidden" name="info" value="<%=info%>">
<% 
	if(nodetype.equals("1"))out.write("��");
	if(nodetype.equals("2"))out.write("�ڵ�");
	if(nodetype.equals("3"))out.write("Ҷ");
%>		</td>
    <tr>
    <tr>
    	<td>
    	���ڵ�:    	</td>
    	<td>
    	 <mes:selectbox colorstyle="box_black" id="upnodeid" name="upnodeid" map="<%=map%>" reSourceURL="../JarResource/" size="36" maxlength="30" readonly="false" selectSize="6" value="<%=upfunctionname%>"/>
    	</td>
    </tr>
	<TR>
		<td>״̬</td>
		<td><span class="boxOut"><span class="boxIn">
		<select name=state <% if(nodetype.equals("2")||nodetype.equals("1")) out.write("disabled"); %>>
		<!--<option value='2' <%//if(nodetype.equals("2")||nodetype.equals("1")) out.write("selected");%>>��</option>-->
		<option value='1' <%if(nodetype.equals("3")&&state.equals("1")) out.write("selected");  %>>����</option>
		<option value='0'<% if(nodetype.equals("3")&&state.equals("0")) out.write("selected");  %>>����</option>
		</select></span></span></td>
	  </TR>
    <tr>
    	<td>��ȫ���</td>
    	<td><input type="text" name="safemarkcode" maxlength="20" <%if(nodetype.equals("3")) out.write

("value='"+safemarkcode+"'");else out.write("value='��' disabled");%> class="box1" type=text  onMouseOver="this.className='box3'" onFocus="this.className='box3'" onMouseOut="this.className='box1'" onBlur="this.className='box1'"></td>
    </tr>
    <tr>
    	<td>��ע</td>
    	<td><input type="text" name="note" maxlength="100" value="<%=note%>" class="box1" type=text  onMouseOver="this.className='box3'" onFocus="this.className='box3'" onMouseOut="this.className='box1'" onBlur="this.className='box1'"></td>
    </tr>
      <tr>
        <td>
		����˳��
		</td>
		<td>
		<input type="text" name="flo_Order" maxlength="100" class="box1" value="<%=flo_Order %>" onMouseOver="this.className='box3'" onFocus="this.className='box3'" onMouseOut="this.className='box1'" onBlur="this.className='box1'">
		</td>
    </tr>
</table>
<br>
<table class="tbnoborder">
<input type = "hidden" name="intpage" value="<%=intpage%>">
<tr>
    <td width="100" class="tdnoborder">
<mes:button id="button1" reSourceURL="../JarResource/" submit="true" value="����"/>
</td>
<td width="100" class="tdnoborder">	 
<mes:button id="button2" reSourceURL="../JarResource/" submit="false" onclick="resetPara()" value="����"/>
</td>
<td width="100" class="tdnoborder">	 
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
	document.getElementsByName("functionname")[0].focus();
}

function func(){
	var pageIndex = <%=intpage%>;
	var int_id=<%=id%>;		
	window.location.href = 'function_manage.jsp?page='+ pageIndex+'&eid='+int_id+ '&info=<%=info%>';
	}
</script>
</html>

<%
	}catch(Exception e){
		throw e;
	}finally{
		//�ͷ���Դ
		if(rs!=null)rs.close();
		if(stmt!=null)stmt.close();
		if(con!=null)con.close();
	}
 %>
	
