<%@ page contentType="text/html;charset=GBK" language="java" pageEncoding="GBK"%>
<%@ page import="com.qm.mes.th.helper.Conn_MES"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.SQLException"%>

<% 
     /** ����ID */
     String id = request.getParameter("intId");
     /** ҳ�� */
     String page_num = request.getParameter("page");
     /** ��� */
     String part = request.getParameter("part");
     /** ת����� */
     String convertPart = request.getParameter("convertPart");
     /** ��ע */
     String remark = request.getParameter("remark");

     Connection conn = null;
     PreparedStatement stmt = null;
     
     try{
    	 conn = new Conn_MES().getConn();
    	 stmt = conn.prepareStatement("UPDATE SPECPART SET cPart=?, cConvertPart=?, cRemark=? WHERE ID=?");
    	 stmt.setString(1, part);
    	 stmt.setString(2, convertPart);
    	 stmt.setString(3, remark);
    	 stmt.setString(4, id);
    	 
    	 stmt.executeUpdate();
     }catch(Exception e){
    	 e.printStackTrace();
     }finally{
    	 if(stmt != null){
    		 try{
    			 stmt.close();
    		 }catch(SQLException e){
    			 e.printStackTrace();
    		 }finally{
    			 stmt = null;
    		 }
    	 }
    	 if(conn != null){
    		 try{
    			 conn.close();
    		 }catch(SQLException e){
    			 e.printStackTrace();
    		 }finally{
    			 conn = null;
    		 }
    	 }
     }
     response.sendRedirect("spec_manage.jsp?page=" + page_num + "&eid=" + id);
%>