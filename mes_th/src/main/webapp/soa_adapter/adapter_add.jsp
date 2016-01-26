<%@ page language="java" import="java.sql.*" contentType="text/html;charset=gb2312"%>
<jsp:useBean id="Conn" scope="page" class="com.qm.th.helper.Conn_MES" />
<%@taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<%@ page import="com.qm.mes.framework.*" %>
<%@ page import="com.qm.mes.framework.dao.IDAO_Core" %>
<%@ page import="com.qm.mes.framework.dao.DAOFactory_Core" %>
<html>
<%  
	
	/* 
	 * ʱ�䣺2007-06-25
	 * ���ߣ�����
	 * ҵ�������������������Ϣ
	 */
	Connection conn=null;
	Statement stmt=null;
	ResultSet rs=null;
	String sql="";
	IDAO_Core dao=null;
	String intpage=request.getParameter("intPage");	 
	String info = request.getParameter("info");
		info = info==null?"":info;    
	//String eid=request.getParameter("eid");  
	try
	{
		//��ȡ����
   		conn=Conn.getConn();
   		dao=DAOFactory_Core.getInstance(DataBaseType.getDataBaseType(conn));
   		stmt=conn.createStatement();
   		
   		sql=dao.getSQL_QueryAllProcessInfos();
		String t_processid="";
		String t_processname="";
		rs=stmt.executeQuery(sql);
		java.util.HashMap<Comparable,String> map = new java.util.HashMap<Comparable,String>();
		while(rs.next())
		{
			t_processid=rs.getString(1);
			t_processname=rs.getString(2);
			map.put(t_processname,t_processid);
		}
		
		String sourceid="";
		String sourcedesc="";
		sql=dao.getSQL_QueryAllSourceInfos();
		rs=stmt.executeQuery(sql);
		java.util.HashMap<Comparable,String> map1 = new java.util.HashMap<Comparable,String>();
		while(rs.next())
		{
			sourceid=rs.getString(1);
			sourcedesc=rs.getString(2);
			map1.put(sourcedesc,sourceid);
		}
%>
<head>
<link rel="stylesheet" type="text/css" href="../cssfile/style.css">
<script type="text/javascript" src="../JarResource/META-INF/tag/taglib_common.js"></script>
</head>
<div class="title">�����������Ϣ</div>
<body>
<div align="center">
<form method="post" action="adapter_adding.jsp">
<table class="tbnoborder" width="468">
<tr>
<td>����</td>
<td>
<mes:selectbox colorstyle="box_black" id="selectbox1" name="processid" map="<%=map%>" reSourceURL="../JarResource/" size="36" maxlength="30" readonly="false" selectSize="6" value=""/>
</td>
</tr>

<tr>
<td>�������</td>
<td>
<mes:inputbox id="i_serveralias" name="i_serveralias" size="36" maxlength="30" reSourceURL="../JarResource/" colorstyle="box_black" />
</td>
</tr>

<tr>
<td>����</td>
<td><mes:inputbox id="i_parameter" name="i_parameter"  size="36" reSourceURL="../JarResource/" colorstyle="box_black" />
</td>
</tr>

<tr>
<td>������Դ</td>
<td>
<mes:selectbox onchanged="change()" colorstyle="box_black" id="selectbox2" name="source" map="<%=map1%>" reSourceURL="../JarResource/" size="36" maxlength="30" readonly="false" selectSize="2" value=""/>
</td>
</tr>

<tr>
<td>����������</td>
<td><mes:inputbox id="o_serveralias" name="o_serveralias" size="36" reSourceURL="../JarResource/" colorstyle="box_black" readonly="true"/>
</td>
</tr>

<tr>
<td>�������</td>
<td><mes:inputbox id="o_parameter" name="o_parameter" size="36" reSourceURL="../JarResource/" colorstyle="box_black" />
</td>
</tr>
</table>
<br>
<table class="tbnoborder">
<input type = "hidden" name="intpage" value="<%=intpage%>">
<tr>
<td  width="100" class="tdnoborder" >
<!-- 
<span class="btn2_mouseout" onMouseOver="this.className='btn2_mouseover'" onMouseOut="this.className='btn2_mouseout'">
<input type="submit" class="btn2" value="�ύ">
</span>
 -->
<mes:button id="button1" reSourceURL="../JarResource/" submit="true" value="�ύ"/>
</td>
<td width="100" class="tdnoborder" >
<!-- 
<span class="btn2_mouseout" onMouseOver="this.className='btn2_mouseover'" onMouseOut="this.className='btn2_mouseout'" >
<input class="btn2"  type="reset" value="����">
</span>
 -->
<mes:button id="button2" reSourceURL="../JarResource/" submit="false" onclick="resetPara()" value="����"/>
</td>
<td width="100" class="tdnoborder"  >&nbsp;<!-- 
<span class="btn2_mouseout" onMouseOver="this.className='btn2_mouseover'" onMouseOut="this.className='btn2_mouseout'" >
<input type="button" class="btn2" value="����" onclick="javascript:window.location.href='adapter_manage.jsp'">
</span>
 -->
<mes:button id="button3" reSourceURL="../JarResource/" onclick = "func()"  value="����"/>
</td>
</tr>
</table>
</form>
</div>
</body>

<script type="text/javascript">
function resetPara()
{
	document.getElementsByName("processid")[0].value="";
	document.getElementsByName("i_serveralias")[0].value="";
	document.getElementsByName("i_parameter")[0].value="";
	document.getElementsByName("source")[0].value="";
	document.getElementsByName("o_parameter")[0].value="";
	document.getElementsByName("processid")[0].focus();

}
function change(){
	var o = document.getElementById('o_serveralias');
	var val = document.getElementById('selectbox2_input').value;
	
	if(val=='user'){
		o.value='';
		o.readOnly=true;
	}else{
		o.readOnly=false;
	}//alert(val+','+o.readOnly);
}
function func(){
		var pageIndex = <%=intpage%>;
		window.location.href = "adapter_manage.jsp?page=" + pageIndex+ '&info=<%=info%>';
			}

</script>
</html>
<%
	}
	catch(Exception e)
	{
		throw e;
	}
	finally
	{
		//�ͷ���Դ
		if(rs!=null)rs.close();
		if(stmt!=null)stmt.close();
		if(conn!=null)conn.close();
	}
%>