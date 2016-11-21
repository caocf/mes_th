<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

	// ȡǰһҳ������
	String jspRq = request.getParameter("rq"); 
	
	// �ж��Ƿ����Ԥ�����ڣ����������ֱ��ȡԤ��ֵ������ȡ��ǰ����
	if(jspRq == null || jspRq.trim().equals("")) {
		jspRq = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
	}
	// ���ò�����ҳ������������
	request.setAttribute("rq", jspRq);
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <base href="<%=basePath%>">
        <title>�������ӡ</title>	
        <meta http-equiv=content-type content="text/html;charset=GBK">
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">    
        <link rel="stylesheet" type="text/css" href="<%=basePath%>cssfile/css.css">
        <link rel="stylesheet" type="text/css" href="<%=basePath%>cssfile/th_style.css">
	
        <!-- Mes Framework Library -->
        <script type="text/javascript" src="<%=basePath%>JarResource/META-INF/tag/taglib_common.js"></script>
    </head>

    <!-- ���õ���ӡ���߼������� -->
    <body>
        <!-- Top Region (Title & Date Selector) -->
        <div align="center">
            <font size="6" >�������ӡ</font> 
            <label> 
                <mes:calendar id="rq" name="rq" reSourceURL="JarResource/" 
      			   showDescripbe="false" haveTime="false" 
      			   value="<%=jspRq%>" onchange="getPrtDate()"/>
            </label> 
        </div>
		
        <!-- ���õ���ӡ�� -->
        <form id="form1" name="form1" method="post" action="">
            <table width="1000" border="1" align="center" height="97">
                <tr>	
                    <td width="3%">���</td>
                    <td width="24%">����</td>
                    <td width="24%">��������ά��</td>
                    <td width="6%">��ǰ�ܺ�</td>
                    <td width="6%"><strong class="accepted">�ѽ���</strong></td>
                    <td width="10%">���ʱ��</td>
                    <td width="7%">��ӡ��׼</td>
                    <td width="10%">�Զ���ӡ </td>
                    <td width="6%" align="center">�ύ</td>
                </tr>
            
                <!-- ����ҵ���߼����������ݵĽ��� -->
                <jsp:include page="PrintLogic.jsp"></jsp:include>
            </table>
        </form>
		
        <!-- ����Applet�����û����õ��Ĵ�ӡ -->
        <div id="d">
            <APPLET ID="JrPrt" name = "app" codebase="th/pzt" CODE = "JdApplet"  
        	        ARCHIVE = "jasperreports-5.0.1.jar,commons-logging-1.0.4.jar,log4j-1.2.14.jar,barbecue-1.5-beta1.jar,commons-collections-3.2.1.jar,commons-digester-2.1.jar"
        	        WIDTH = "0" HEIGHT = "0" MAYSCRIPT>
                <PARAM NAME = "type" VALUE="application/x-java-applet;version=1.2.2"/>
                <PARAM NAME = "scriptable" VALUE="true"/>
            </APPLET>
        </div>

        <!-- ��վ�ĸ�Ŀ¼ -->
        <input type="hidden" value="<%=basePath%>" id="basePath"/>

        <!-- Load Javascript -->
        <script type="text/javascript" src="<%=basePath%>/js/Print-PHD.js"></script>
        <script type="text/javascript"><%=request.getAttribute("openApp")%></script>
    </body>
</html>