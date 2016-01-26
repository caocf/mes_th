<%@ page contentType="text/html;charset=GBK" language="java" pageEncoding="GBK"%>
<%@ page import="com.qm.th.helper.Conn_MES"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.SQLException"%>

<% 
    /** KIN�� */
    String kinCode = request.getParameter("kinCode");
	/** ҳ�� */
	String page_num = request.getParameter("page");
    
    Connection conn = null;
    PreparedStatement stmt = null;
    
    try{
        // ɾ�����
        String strSql = "DELETE FROM SPECIALKIN WHERE CCARNO = ?";
        // �������ݿ�����
        conn = new Conn_MES().getConn();
        stmt = conn.prepareStatement(strSql);
        stmt.setString(1, kinCode);
        
        stmt.execute();
    }catch(Exception e){
        e.printStackTrace();
    }finally{
        // resource release
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
    response.sendRedirect("spec_kincode_search.jsp?page=" + page_num);
%>