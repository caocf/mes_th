<%@ page language="java" import="java.sql.*" contentType="text/html;charset=gb2312"%>
<jsp:useBean id="Conn" scope="page" class="com.qm.mes.th.helper.Conn_MES"/>
<%@ page import="mes.framework.dao.*"%>
<%@ page import="mes.framework.DataBaseType"%>
<%@taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="../cssfile/style.css">
<script type="text/javascript" src="../JarResource/META-INF/tag/taglib_common.js"></script>
</head>
<body>
<div class="title">�鿴������Ϣ</div>
<br>
<br>
<%  String intpage=request.getParameter("intPage");;
	String id="";
	String info = request.getParameter("info");
		info = info==null?"":info;
	if((request.getParameter("id")==null))
	{
%>
	<script>alert("û�д˹��ܣ�");window.history.go(-1);</script>
<% 
		return;
	}
	else
	{
 		id=request.getParameter("id");
	}
	
	String user_rolerank="";
	user_rolerank=(String)session.getAttribute("user_rolerank");
	
	Connection conn=null;
	Statement stmt=null;
	ResultSet rs=null;
	
	try{
	conn=Conn.getConn();
	stmt=conn.createStatement();
	
	String upfunctionid="";
	String functionid="";
	String functionname="";
	String state="";
	String nodetype="";
	String url="";
	String safemarkcode="";
	//String lastupdateuser="";
	String lastupdatetime="";
	String note="";
	String upfunctionname="";
	String rank="";
	String lastupdateusername="";
	String flo_Order = "";

	//String sql="select a.nupfunctionid,a.nfunctionid,a.cfunctionname,a.cstate,a.cnodetype,a.curl,a.csafemark,a.nlastupdateuser,to_char(a.dlastupdatetime,'yyyy-mm-dd HH24:MI:ss'),a.cnote,a.crank,b.cusrname";
		      // sql+=" from data_function a inner join data_user b on a.nlastupdateuser=b.NUSRNO";
			   //sql+=" where a.nfunctionid='"+id+"' and to_number(a.crank)>="+user_rolerank+"";
			   
	String sql = "";
	IDAO_UserManager dao = DAOFactory_UserManager.getInstance(DataBaseType.getDataBaseType(conn));
	sql=dao.getSQL_QueryFunctionForFunctionIdAndRank(Integer.parseInt(id),user_rolerank);
    rs=stmt.executeQuery(sql);
	System.out.println(sql);
	if(rs.next())
	{
		upfunctionid=rs.getString(1);
		functionid=rs.getString(2);
		functionname=rs.getString(3);
		state=rs.getString(4);
		nodetype=rs.getString(5);
		url=rs.getString(6);
		safemarkcode=rs.getString(7);
		//lastupdateuser=rs.getString(8);
		lastupdatetime=rs.getString(9);
		note=rs.getString(10);
		if(note==null)note="����˵��";
		rank=rs.getString(11);
		lastupdateusername= rs.getString("cusrname");
		flo_Order=rs.getString("flo_order");
	}
	else
	{
		if(rs!=null)rs.close();
		if(stmt!=null)stmt.close();
		if(conn!=null)conn.close();

%>
	<script>alert("û�д˹��ܻ���Ȩ���ʴ���Ϣ!");window.location.href='function_manage.jsp';</script>
<%
		return;
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
%>
<div align="center">
  <table width="400" class="tbnoborder" border="1">
    <tr>
        <td width="126" height="34">���ܴ��룺</td>
      <td width="262"><%=functionid%></td>
    </tr>
    <tr>
        <td>�������ƣ�</td>
        <td><%=functionname%></td>
    </tr>
    <tr>  
    	<td>�ڵ����ͣ�</td>
        <td>
<% 
	if(nodetype.equals("1"))out.write("��");
	if(nodetype.equals("2"))out.write("�ڵ�");
	if(nodetype.equals("3"))out.write("Ҷ");
%>
		</td>
    </tr>
    <tr>
    	<td>���ڵ㣺</td>
        <td><%=upfunctionname%></td>
    </tr>
    <tr>
    	<td>����URL��</td>
        <td><%=url%></td>
    </tr>
    <tr>
    	<td>״̬��</td>
        <td>
<%
	if(state.equals("1"))
		out.write("������");
	else 
		out.write("�ѽ���");
%>
        </td>
    </tr>
    <tr>
		<td>��ȫ���ʱ��</td>
        <td>
<%
	if(nodetype.equals("3"))
		out.write(safemarkcode);
	else 
		out.write("��");
%>
		</td>	
   </tr>  
   <tr>
   		<td>ά������Ա</td>
        <td ><%=lastupdateusername%></td>
   </tr>  
   <tr>
   		<td >ά��ʱ��</td>
        <td ><%=lastupdatetime%></td>
   </tr>  
   <tr>		
   		<td >����</td>
        <td ><% if(rank.equals("0")) out.write("������"); else if(rank.equals("1")) out.write("Ӧ�ü�");%></td>	
   </tr>
   <tr>		
   		<td >��ע</td>
        <td ><%=note%></td>	
   </tr>  
   <tr>
   		<td>����˳���</td>
   		<td><%=flo_Order %></td>
   </tr>
</table>
<table class="tbnoborder">   
   <tr>
      <td  class="tdnoborder" >
	 <mes:button id="B1" reSourceURL="../JarResource/" onclick="func()" value="�� ��"/>
	  </td>
    </tr>
  </table>
</div>
</body>
<script>
		<!--
			function func(){
				var pageIndex = <%=intpage%>;
				var int_id=<%=id%>;		
				window.location.href = 'function_manage.jsp?page='+ pageIndex+'&eid='+int_id+ '&info=<%=info%>';			
			}
		-->
	</script>
</html>
<%

	}catch(Exception e)
	{
		throw e;
	}finally{
		//�ͷ���Դ
		if(rs!=null)rs.close();
		if(stmt!=null)stmt.close();
		if(conn!=null)conn.close();
	}
%>






