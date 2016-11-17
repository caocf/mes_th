<%@ page contentType="text/html;charset=GBK" language="java" pageEncoding="GBK"%>
<%@ page import="common.Conn_MES"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.SQLException"%>

<% 
	/** ҳ�� */
    String page_num = request.getParameter("page");
	/** ����ID */
	String id = request.getParameter("intId");
	/** KIN�� */
    String ccarno = request.getParameter("ccarno");
    /** ��װ����ʱ�� */
    String cenabled = request.getParameter("cenabled");
    /** ��װ˳��� */
    String remark = request.getParameter("remark");
	
	Connection conn = null;
	PreparedStatement stmt = null;
 
	try{
		conn = new Conn_MES().getConn();
		stmt = conn.prepareStatement("UPDATE SPECIALKIN SET cenabled=?, CREMARK=? WHERE CCARNO=?");
		stmt.setString(1, cenabled == null ? "0" : "1");
		stmt.setString(2, remark);
		stmt.setString(3, ccarno);
		
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
	response.sendRedirect("spec_kincode_search.jsp?page=" + page_num + "&eid=" + id);
%>