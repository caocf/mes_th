<%@ page language="java" import="java.sql.*" contentType="text/html;charset=gb2312"%>
<%@page import="com.qm.th.tg.dao.*,com.qm.th.tg.factory.*" %>
<%@page import="com.qm.th.tg.bean.*,com.qm.mes.ra.util.*" %>
<%@page import="java.util.*,java.text.SimpleDateFormat" %>
<%@page	import="com.qm.mes.framework.*,com.qm.mes.framework.dao.*,com.qm.mes.system.dao.*"%>

<%@taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<html><!-- InstanceBegin template="/Templates/new_view.dwt.jsp" codeOutsideHTMLIsLocked="true" -->

<!-- InstanceBeginEditable name="�������" -->
<jsp:directive.page import="java.util.List"/>
<jsp:directive.page import="java.util.ArrayList"/>
<jsp:useBean id="Conn" scope="page" class="com.qm.th.helpers.Conn_MES"/>
<script language="JavaScript" type="text/javascript" src="../My97DatePicker/WdatePicker.js"></script>
<!-- InstanceEndEditable -->
<!-- InstanceBeginEditable name="��ù���" -->
<%@page import="org.apache.commons.logging.Log,org.apache.commons.logging.LogFactory"%>
<%  
    final  Log log = LogFactory.getLog("history_carselect.jsp");
	response.setHeader("progma","no-cache");
	response.setHeader("Cache-Control","no-cache");
    Connection con = null;
    WeldingSearchFactory factory_ws = new WeldingSearchFactory();//��ѯ����
    List<Part> list_as = new ArrayList<Part>();//��ѯ����
    List<AssemblySearch> list_ws = new ArrayList<AssemblySearch>();//������Ϣ
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    String condition_One = null;//����һ
    String condition_Two = null;//������
    int radio_value = 0;//��ѡ��ťֵ
	String sql_temp1 = "";
	String sql_as = "";
	try{
	    //��ȡ����
	    con=Conn.getConn();
	    radio_value = request.getParameter("radio1")==null?1:Integer.parseInt(request.getParameter("radio1"));
	    log.debug("RADIOѡ�"+radio_value);
		String sql=null;
		DAO_WeldingSearch dao = new DAO_WeldingSearch();
	    switch(radio_value){
	    	case 1:
	    	condition_One = request.getParameter("kin")==null?"n":request.getParameter("kin");
			sql_temp1 = "ccarno='"+ condition_One +"' ";
	    	break;
	    	case 2:
	    	condition_One = request.getParameter("vin")==""?"n":request.getParameter("vin");
	    	sql_temp1 = "cvincode='"+ condition_One +"' ";
	    	break;
	    }
		list_as = factory_ws.getHistorypart(sql_temp1,con);
		list_ws = factory_ws.getHistorycar(sql_temp1,con);
	%>				
<head>
<link rel="stylesheet" type="text/css" href="../cssfile/style.css">
<!-- InstanceBeginEditable name="����" -->
		<title>������ѯ</title>
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

<div class="title"><strong><!-- InstanceBeginEditable name="����2" -->������ѯ<!-- InstanceEndEditable --></strong></div>
	<br>
	<div align="center"><!-- InstanceBeginEditable name="����1" -->
	
	  <form action="history_carselect.jsp">
	  <table>
	  <tr><td>
	  	<input type="radio" name="radio1" value="1">KIN
	 	<input id="kin" name="kin" size="10" maxlength="10" type="text" value="<%=radio_value!=1||condition_One.equals("n")?"":condition_One%>"/>
  	  </td></tr>
  	  <tr><td>
	  	<input type="radio" name="radio1" value="2">VIN
	  	<input name="vin" size="17" maxlength="17" value="<%=radio_value!=2||condition_One.equals("n")?"":condition_One%>">
	  	<mes:button id="s1" reSourceURL="../JarResource/" submit="true" value="��ѯ" />
	  </td></tr>
  	</table>
  </form>
  	</div>
  	<div align="center">
  	<style>
  	td{border-width:1pt;border-style :solid}
  	</style>
  	<style>
  	tr{border-width:1pt;border-style :solid}
  	</style>
  	<p></p><p></p>
  <table border="0" cellspacing="0" cellpadding="0" width="1090">
  	<tr>
  	<td align="center" width="90">��װ˳���</td>
  	<td align="center" width="90">��װ˳���</td>
  	<td align="center" width="150">VIN</td>
  	<td align="center" width="90">������</td>
  	<td align="center" width="150">��װ����</td>
  	<td align="center" width="150">��װ����</td>
  	<td align="center" width="150">CP6����</td>
  	<td align="center" width="110">��װ��������</td>
  	<td align="center" width="110">��װ��������</td>
  	</tr>
  	<%for(int i=0;i<list_ws.size();i++){%>
  	  <tr>
  	    <td align="center"><%=list_ws.get(i).getSeq_W()==null?"-":list_ws.get(i).getSeq_W()%></td>
  	  	<td align="center"><%=list_ws.get(i).getSeq()==null?"-":list_ws.get(i).getSeq()%></td>
  	  	<td align="center"><%=list_ws.get(i).getVin()==null?"-":list_ws.get(i).getVin()%></td>
  	    <td align="center"><%=list_ws.get(i).getKin()==null?"-":list_ws.get(i).getKin()%></td>
  	    <td align="center"><%=list_ws.get(i).getDWBegin()==null?"-":sdf.format(sdf.parse(list_ws.get(i).getDWBegin()))%></td>
  	    <td align="center"><%=list_ws.get(i).getDABegin()==null?"-":sdf.format(sdf.parse(list_ws.get(i).getDABegin()))%></td>
  	    <td align="center"><%=list_ws.get(i).getDCp6Begin()==null?"-":sdf.format(sdf.parse(list_ws.get(i).getDCp6Begin()))%></td>
  	    <td align="center"><%=list_ws.get(i).getCfilename_w()==null?"-":list_ws.get(i).getCfilename_w()%></td>
  	    <td align="center"><%=list_ws.get(i).getCfilename_a()==null?"-":list_ws.get(i).getCfilename_a()%></td>
  	  </tr>
    <%} %>
  </table>
  <table border="0" cellspacing="0" cellpadding="0" width="390">
  	<tr>
  	<td align="center" width="130">�ܳɺ�</td>
  	<td align="center" width="180">�ܳ�����</td>
  	<td align="center" width="80">�ܳ�����</td>
  	</tr>
  	<%for(int i=0;i<list_as.size();i++){%>
  	  <tr>
  	  	<td align="center"><%=list_as.get(i).getCode()==null?"-":list_as.get(i).getCode()%></td>
  	  	<td align="center"><%=list_as.get(i).getName()==null?"-":list_as.get(i).getName()%></td>
  	    <td align="center"><%=list_as.get(i).getNum()%></td>
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
function checkinput(thisform){
        var re =  /^[0-9]+$/;
		var i=0;
		var qm;
		var mm = document.getElementsByName("method");
		
}
function check_radio(){
	document.getElementsByName("radio1")[<%=radio_value-1%>].checked = true;
}check_radio();
</script>
<!-- InstanceEndEditable -->
<!-- InstanceEnd --></html>