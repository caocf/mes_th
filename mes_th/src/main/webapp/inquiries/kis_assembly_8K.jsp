<%@ page language="java" import="java.sql.*" contentType="text/html;charset=gb2312"%>
<%@page import="th.tg.dao.*,th.tg.factory.*" %>
<%@page import="th.tg.bean.*,mes.ra.util.*" %>
<%@page import="java.util.*,java.text.SimpleDateFormat" %>
<%@page	import="mes.framework.*,mes.framework.dao.*,mes.system.dao.*"%>
<script language="JavaScript" type="text/javascript" src="../My97DatePicker/WdatePicker.js"></script>
<%@taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<html><!-- InstanceBegin template="/Templates/new_view.dwt.jsp" codeOutsideHTMLIsLocked="true" -->

<!-- InstanceBeginEditable name="�������" -->
<jsp:directive.page import="java.util.List"/>
<jsp:directive.page import="java.util.ArrayList"/>
<jsp:useBean id="Conn" scope="page" class="com.qm.mes.th.helper.Conn_MES"/>
<script language="JavaScript" type="text/javascript" src="../My97DatePicker/WdatePicker.js"></script>
<!-- InstanceEndEditable -->
<!-- InstanceBeginEditable name="��ù���" -->
<%@page import="org.apache.commons.logging.Log,org.apache.commons.logging.LogFactory"%>
<%  
    final  Log log = LogFactory.getLog("kis_assembly_3c.jsp");
	response.setHeader("progma","no-cache");
	response.setHeader("Cache-Control","no-cache");
    Connection con = null;
    WeldingSearchFactory factory_ws = new WeldingSearchFactory();//��װ��ѯ����
    List<KisAssembly> list_ka = new ArrayList<KisAssembly>();//KIS��װ��ѯ����
    SearchSetFactory factory_ss = new SearchSetFactory();//��ѯ���ù���
    SearchSet ss = new SearchSet();//��ѯ���ö���
    int searchsetid = 0;//��ѯ�������
    String auto = "";
    String start_Time = "";
    String sql = "";


Statement stmt = null;
ResultSet rs = null;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	try{
	    //��ȡ����
	    con=Conn.getConn();
	    stmt = con.createStatement();
	    if(request.getParameter("auto")==null||request.getParameter("auto").equals(""))
	    	auto = "true";
	    else
	    	auto = request.getParameter("auto");
	    start_Time = request.getParameter("d11");
	    log.debug("auto����Ϊ��"+auto);
	    searchsetid = Integer.parseInt(request.getParameter("searchsetid"));
	    ss = factory_ss.getSearchSetById(searchsetid,con);
		String sql_temp = null;
		DAO_WeldingSearch dao = new DAO_WeldingSearch();

		sql = "select c.cSEQNo_A,SUBSTRING(c.cVinCode,7,2) cartype,c.cVinCode,c.cCarNo," + 
			"c.dABegin,c.dCp6Begin,a.cQADNo aa,b.cQADNo bb,d.cQADNo dd " + "from carData c " +
			"left join carData_D p on p.iCarId=c.cCarNo " +
			"left join (select a.cQADNo,a.iCarId from carData_D a where a.ITFASSNameId=2) a on a.iCarId=c.cCarNo " +
			"left join (select b.cQADNo,b.iCarId from carData_D b where b.ITFASSNameId=4) b on b.iCarId=c.cCarNo " +
			"left join (select d.cQADNo,d.iCarId from carData_D d where d.ITFASSNameId=8) d on d.iCarId=c.cCarNo " +
			"where 1=1 and substring(cSEQNo_A,1,2)='" + ss.getCfactory() + "' " +
			"and substring(cCarNo,6,1) in(" + ss.getCcarType() + ") ";

		if(start_Time==null||start_Time.equals("null")||start_Time.equals("")){
			sql_temp = " and DateDiff(hour,dABegin,getdate())<24 ";
		}else{
			sql_temp = " and dABegin>=convert(varchar(100),'" + start_Time + "',20) ";
		}
//		list_ka = factory_ws.getKISAssemblyByCartype(ss.getCcarType(),ss.getCfactory(),sql_temp,con);


		sql += sql_temp;
		sql += "group by cSEQNo_A,cVinCode,cCarNo,dABegin,dCp6Begin,a.cQADNo,b.cQADNo,d.cQADNo";
		sql += " order by dABegin desc,cSEQNo_A desc";
		rs = stmt.executeQuery(sql);		
		
	%>				
<head>
<link rel="stylesheet" type="text/css" href="../cssfile/style.css">
<!-- InstanceBeginEditable name="����" -->
		<title>��ѯ����</title>
<!-- InstanceEndEditable -->
<meta http-equiv="Content-Language" content="zh-cn">
<style>
.head2{	height:22px;color:ffff00;
}
</style>
<!-- InstanceBeginEditable name="head" -->
<!-- InstanceEndEditable -->
</head>

<body background="../images/background.jpg">
<!-- ����ͨ�ýű� -->
<script type="text/javascript" src="../JarResource/META-INF/tag/taglib_common.js"></script>

<div class="title"><strong><!-- InstanceBeginEditable name="����2" --><%=ss.getCdscCarType()+ss.getCdscFactory()%>��װFIS��ѯ<!-- InstanceEndEditable --></strong></div>
  	<div align="center">
  	<style>
  	td{border-width:1pt;border-style :solid}
  	</style>
  	<style>
  	tr{border-width:1pt;border-style :solid}
  	</style>
  	<p></p><p></p>
  	<div align="center">
  	<%if(auto.equals("true")) {%>
  		<input type="button" name="noauto" value="�л����ֶ�" onclick="unautoreflush()">
  	<%}else{ %>
  		<input type="button" name="auto" value="�л����Զ�" onclick="reflush()">
  		<input type="button" name="reflush" value="ˢ��" onclick="unautoreflush()">
  	<%} %>
  		<input type="hidden">
  	</div>
  	<div align="center">
  		
  		<input id="d11" name="d11" type="text" value="<%=start_Time==null||start_Time.equals("")?"":start_Time%>"/>
	  <img onclick="WdatePicker({el:'d11',dateFmt:'yyyy-MM-dd HH:mm:ss'})" src="../My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
	  <input type="button" name="select_btn" value="��ѯ" onclick="select_btn()">
  	</div>
  <table border="0" cellspacing="0" cellpadding="0" id="table1">
  	<tr>
  	<td align="center" width="80">˳���</td>
  	<td align="center" width="200">VIN</td>
  	<td align="center" width="150">	������</td>
  	<td align="center" width="150">	����</td> 
  	<td align="center" width="150">	��ת���</td>
  	<td align="center" width="150">	����ʱ��</td> 
 	</tr>
  	<%while(rs.next()){%>
  	  <tr>
	    <%if(rs.getString(1)!=null && rs.getString(1).substring(2).equals("1999")){%>
		<td align="center" style="background-color: red"><font size="72"><%=rs.getString(1)==null?"-":rs.getString(1)%></font></td>
	    <%}else if(rs.getString(1)!=null && rs.getString(1).substring(2).equals("0001")){%>
		<td align="center" style="background-color: #00FF00"><%=rs.getString(1)==null?"-":rs.getString(1)%></td>
	    <%}else{%>
  	  	<td align="center"><%=rs.getString(1)==null?"-":rs.getString(1)%></td>
	    <%}%>
  
  	  	<td align="center"><%=rs.getString(3)==null?"-":rs.getString(3)%></td>
	  	<td align="left"><%=rs.getString(7)==null?"-":rs.getString(7)%></td>
	  	<td align="left"><%=rs.getString(8)==null?"-":rs.getString(8)%></td>
	  	<td align="left"><%=rs.getString(9)==null?"-":rs.getString(9)%></td>	  	
                <td align="left"><%=rs.getString(5)==null?"-":rs.getString(5)%></td>
  	  </tr>
    <%} %>
  </table>
  </div>
</body>

<%
		//�ͷ���Դ
	
		}catch(Exception e)
		{		
			e.printStackTrace();
		}finally {			
			if(con!=null)con .close();
			}
		
	%>	
<!-- InstanceBeginEditable name="script" -->
<script type="text/javascript">
var auto = <%=auto%>;
function checkinput(thisform){
        var re =  /^[0-9]+$/;
		var i=0;
		var qm;
		var mm = document.getElementsByName("method");
		
}

//�Զ�ˢ��
function reflush(){
	window.location.href="kis_assembly_3c.jsp?auto=true&searchsetid=<%=searchsetid%>&d11=" + document.getElementsByName("d11")[0].value;
}
//���request.getParameter("auto")==true����ҳ���Զ�ˢ��
if(auto==true) {
	setTimeout(reflush,30000);
}
//�ֶ�ˢ��
function unautoreflush(){
	window.location.href="kis_assembly_3c.jsp?auto=false&searchsetid=<%=searchsetid%>&d11=" + document.getElementsByName("d11")[0].value
}
//�ύ��ť
function select_btn(){
	if(auto==true){
		reflush();
	}else{
		unautoreflush();
	}
}


</script>
<!-- InstanceEndEditable -->
<!-- InstanceEnd --></html>