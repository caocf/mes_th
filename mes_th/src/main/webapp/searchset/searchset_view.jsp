<%@ page language="java" import="java.sql.*" contentType="text/html;charset=gb2312"%>
<%@page import="mes.framework.*,mes.system.dao.*,mes.framework.dao.*" %>
<%@page import="th.tg.bean.*,java.util.*,th.tg.dao.*" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="th.tg.factory.*" %>
<%@taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<%@page import="org.apache.commons.logging.Log,org.apache.commons.logging.LogFactory"%>
<html><!-- InstanceBegin template="/Templates/new_�鿴.dwt.jsp" codeOutsideHTMLIsLocked="true" -->
 
 <!-- InstanceBeginEditable name="�������" -->
 <jsp:useBean id="Conn" scope="page" class="com.qm.mes.th.helper.Conn_MES"/>
 <!-- InstanceEndEditable -->
 <!-- InstanceBeginEditable name="��ò�������֤" -->	 
 <%
 	String info = request.getParameter("info");
		info = info==null?"":new String(info);
 	String int_id = request.getParameter("int_id");
 	
 	 String intpage=request.getParameter("intPage");
	final  Log log = LogFactory.getLog("searchset_view.jsp");
    int int_int_id = new Integer(int_id);
	if(int_id==null){
		out.println("<script>alert(\"����Ϊ��\"); window.location.href=\"searchset_manage.jsp\";</script>");
		return;
	} %> <!-- InstanceEndEditable -->
	<%
	Connection conn=null;
  	try{//��ȡ����
		conn=Conn.getConn();
		%>
		<!-- InstanceBeginEditable name="����SQL���" -->
		<%
		SearchSetFactory factory = new SearchSetFactory();
		SearchSet ss = factory.getSearchSetById(Integer.parseInt(int_id),conn);
		%>
		<!-- InstanceEndEditable -->
	<head>
		<link rel="stylesheet" type="text/css" href="../cssfile/style.css">
	<!-- InstanceBeginEditable name="doctitle" -->
		<title>��ѯ������Ϣ</title>
	<!-- InstanceEndEditable -->
	    <!-- InstanceBeginEditable name="head" -->
		<script type="text/javascript" src="../JarResource/META-INF/tag/taglib_common.js"></script>
		<!-- InstanceEndEditable -->
	</head>
	<body>
	<div class="title"><!-- InstanceBeginEditable name="����" -->�鿴��ѯ������Ϣ<!-- InstanceEndEditable --></div>
	<br>
	<div align="center"><!-- InstanceBeginEditable name="����1" -->
    <table class="tbnoborder" border="1">
      <tr>
        <td width="135">��ѯ���ú� -����</td>
        <td width="172">
        <input readonly size="27" type="text" value="<%=int_id%> - <%=ss.getCsearchName()==null?"":ss.getCsearchName() %>" class="box1" onMouseOver="this.className='box3'" onFocus="this.className='box3'" onMouseOut="this.className='box1'" onBlur="this.className='box1'">
        </td>
        <td width="120">����װ��</td>
        <td width="150">
        <input readonly size="27" type="text" value="<%=ss.getCwa()==null?"":ss.getCwa().equals("A")?"��װ":"��װ" %>" class="box1" onMouseOver="this.className='box3'" onFocus="this.className='box3'" onMouseOut="this.className='box1'" onBlur="this.className='box1'">
        </td>
     </tr>
     <tr>
        <td width="120">�������룺</td>
        <td width="172">
        <input readonly size="27" type="text" value="<%=ss.getCfactory() %>" class="box1" onMouseOver="this.className='box3'" onFocus="this.className='box3'" onMouseOut="this.className='box1'" onBlur="this.className='box1'">
        </td>
        <td width="135">�������ƣ�</td>
        <td width="172">
        <input readonly size="27" type="text" value="<%=ss.getCdscFactory() %>" class="box1" onMouseOver="this.className='box3'" onFocus="this.className='box3'" onMouseOut="this.className='box1'" onBlur="this.className='box1'">
        </td>
     </tr>
     <tr>
        <td width="120">���ʹ��룺</td>
        <td width="172">
        <input readonly size="27" type="text" value="<%=ss.getCcarType() %>" class="box1" onMouseOver="this.className='box3'" onFocus="this.className='box3'" onMouseOut="this.className='box1'" onBlur="this.className='box1'">
        </td>
        <td width="135">�������ƣ�</td>
        <td width="172">
        <input readonly size="27" type="text" value="<%=ss.getCdscCarType() %>" class="box1" onMouseOver="this.className='box3'" onFocus="this.className='box3'" onMouseOut="this.className='box1'" onBlur="this.className='box1'">
        </td>
     </tr>
     <tr>
        <td width="135">��ע��</td>
        <td width="172">
        <input readonly size="27" type="text" value="<%=ss.getCremark()==null?"":ss.getCremark() %>" class="box1" onMouseOver="this.className='box3'" onFocus="this.className='box3'" onMouseOut="this.className='box1'" onBlur="this.className='box1'">
        </td>
     </tr>
    </table>
    <table class="tbnoborder">
      <tr>
        <td  class="tdnoborder" colspan="2"  >
          <mes:button id="button1" reSourceURL="../JarResource/" onclick="func()" value="�� ��"/>
		</td>
      </tr>
    </table><!-- InstanceEndEditable -->
	</div>
</body><!-- InstanceEnd -->

<script>
<!--
	function func(){
		var pageIndex = <%=intpage%>;
		var int_id=<%=int_id%>;		
		window.location.href = 'searchset_manage.jsp?page='+ pageIndex+'&eid='+int_id+'&info=<%=info%>';	
	}

		-->
	</script>
	</html>
<%
	//�ͷ���Դ
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if (conn != null)
			conn.close();
	}
%>









