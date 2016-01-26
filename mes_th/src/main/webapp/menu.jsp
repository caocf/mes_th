<%@ page language="java" import="java.sql.*" contentType="text/html;charset=gb2312"%>
<jsp:useBean id="Conn_MES" scope="page" class="com.qm.th.helpers.Conn_MES"/>
<%@page import="com.qm.mes.util.tree.*"%>

<%
	String userid=(String)session.getAttribute("userid");
	if(userid==null) 
	{
		out.write("���ʱ��ܾ���");
		return;
	}
	
	String cssFile =(String)session.getAttribute("file");
 	if(cssFile==null||cssFile.trim().equals("")) 
 		cssFile="blue";
	
	Connection con=null;
	DataServer_UserManage ds=null;
%>
<html>
<link href="cssfile/<%=cssFile%>.css" rel="stylesheet" type="text/css">
<head>

<TITLE>ϵͳ���ܲ˵�</TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
</head>
<style>
.treeicon{background-color:#E8F3FD};
</style>
<body bgcolor="#E8F3FD" style="padding:0px;margin:0px"> 
<link rel="stylesheet" type="text/css" href="tree.css">
<script language="javascript" src="tree.js" ></script>
<div id = "div_tree" style="padding:5px;font-size:10pt;"></div>
<%
	try
	{
		//�������
		con=Conn_MES.getConn();
		ds = new DataServer_UserManage(con);
		String roleno="";
		//roleno=ds.getRoleNo(userid);
		roleno=request.getParameter("roleno");
		String powerstring=ds.getPowerString(roleno);
		//BuildTree������������
		BuildTree bft = new BuildTree(con,powerstring);
		
		Function root=ds.getFuncitonInfo("1");
%>
<script type="text/javascript">
<!--
var t = mes.taglib.tree('div_tree');
//�����Ƿ�������=============start (��tree.js��Ҳ������)
t.iml.add("plus","collapse_top");
t.iml.add("plus","collapse_end");
t.iml.add("plus","collapse");

t.iml.add("minus","expand_top");
t.iml.add("minus","expand");
t.iml.add("minus","expand_end");

t.iml.add("blank","branch_end");
t.iml.add("blank","branch");
//�����Ƿ�������=============end

//---- ���ƹ���������ر�ʱ��ͼƬ��ʽ start----
t.iml.add("book1_open","open2");
t.iml.add("book1_close","close2");
//---- ���ƹ���������ر�ʱ��ͼƬ��ʽ end----

//link��sun�������й̶��ģ�����ͼƬ��
t.iml.add('html','link');
t.iml.add("sun","sun");
//�����м��null�����ӵ�ַ���е�ַ�ĵ������ʾ���ӵ�ַ�����ݣ�û�����ǳ�����
var node<%=root.getFunctionID()%> = t.addNode('<%=root.getFunctionName()%>',null,'open2','close2');

<%=bft.expand(root) %>
//ProductionShow����ʾ�����洰�ڵ���
t.target = "ProductionShow";
-->
</script>
<%	
	}catch(Exception e)
	{
		System.out.println(e);
		throw e;
	}
	finally
	{
		if(con!=null)con.close();
	}
%>
</body>
</html>