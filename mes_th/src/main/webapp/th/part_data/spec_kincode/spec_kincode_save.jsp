<%@ page contentType="text/html;charset=GBK" language="java" pageEncoding="GBK"%>
<%@ page import="com.qm.th.helper.Conn_MES"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="java.text.SimpleDateFormat"%>

<% 
       /** ҳ�� */
       String page_num = request.getParameter("page");
       /** KIN�� */
       String ccarno = request.getParameter("ccarno");
       /** ��װ����ʱ�� */
       String dwbegin = request.getParameter("dwbegin");
       /** ��װ˳��� */
       String remark = request.getParameter("remark");
       
       Connection conn = null;
       PreparedStatement stmt = null;

       try{
           // ����һ��������
           String strSql = "INSERT INTO SPECIALKIN (CCARNO, DTODATE, CREMARK) VALUES (?, ?, ?)";
           // ���ݿ�����
           conn = new Conn_MES().getConn();
           stmt = conn.prepareStatement(strSql);
           stmt.setString(1, ccarno);
           stmt.setTimestamp(2, new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dwbegin).getTime()));
           stmt.setString(3, remark);
           
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