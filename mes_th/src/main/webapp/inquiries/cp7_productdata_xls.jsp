<%@ page contentType="text/html; charset=gb2312" language="java" %>
<%@ page import="java.sql.*" %>
<%@page import="mes.tg.dao.*,mes.tg.factory.*,mes.tg.bean.*" %>
<%@ page import ="mes.framework.DataBaseType"%>
<%@ page import ="th.tg.util.*"%>
<%@ page import = "mes.system.dao.DAOFactoryAdapter"%>
<%@page	import="org.apache.commons.logging.Log,org.apache.commons.logging.LogFactory"%>
<jsp:useBean id="Conn" scope="page" class="com.qm.mes.th.helper.Conn_MES"/>
<%	response.setHeader("Pragma","No-cache");  
   	response.setHeader("Cache-Control","no-cache");  
  	response.setDateHeader("Expires", 0); %>
<%
	response.reset();   
	response.setContentType("application/x-msdownload");
	final  Log log = LogFactory.getLog("cp7_productdata_xls.jsp");
	
	//���������ǿ���������Ҫ���ļ���.xls
	//excel�ļ�����
	String type = request.getParameter("type");
	String name = "";
		name = "���ڳ��������Ϣͳ��";
	//sql���
	String sql=request.getParameter("sql"); 
	log.debug(name + "sql��" + sql);
  
	response.setHeader("Content-Disposition",   "attachment;   filename="+new String((name+"_���ݱ�.xls").getBytes(),"iso8859-1"));			
	Connection con = null;
	try {
	    con = Conn.getConn();
		ResultSet rs=null;
		
		if(sql!=null){
	rs=con.createStatement().executeQuery(sql);
		}
		ServletOutputStream os=response.getOutputStream();
        ProductDataExport.createExcelFile(rs,os,type,con);
	} catch (Exception e){
		e.printStackTrace();
	}finally{
	    if(con!=null)
		con.close();
		out.clear();
        out =  pageContext.pushBody();
	}
%>
