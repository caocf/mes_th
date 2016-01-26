<%@ page contentType="text/html;charset=GBK" language="java" pageEncoding="GBK"%>
<%@ page import="com.qm.th.helper.Conn_MES"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.SQLException"%>

<% 
	/** ����ID */
	String id = request.getParameter("intId");
	/** ҳ�� */
	String page_num = request.getParameter("page");
	/** �������� */
	String tfass = request.getParameter("partdata_ctfass");
	/** ����������� */
	String vwmainpart = request.getParameter("primary");
	/** ����������������� */
	String vwsubparttype = request.getParameter("partdata_nvwsubparttype");
	/** ����������� */
	String vwsubpart = request.getParameter("sub");
	/** ������������� */
	String vwsubpartquan = request.getParameter("partdata_nvwsubpartquan");
	/** ������ */
	String qadno = request.getParameter("plan_no");
	/** �Ƿ���ȳ��� */
	String istempcar = request.getParameter("tempcar");
	/** ��ע */
	String remark = request.getParameter("remark");

	/** ����BOM�� */
	StringBuilder strSql = new StringBuilder();
	strSql.append(" UPDATE PARTDATA SET cTFASS=?, ");
	strSql.append(" cVWMainPart=?, nVWSubPartType=?,");
	strSql.append(" cVWSubPart=?, nVWSubPartQuan=?, ");
	strSql.append(" cQADNo=?, cIsTempCar=?, cRemark=?");
	strSql.append(" WHERE ID = ?");
	
    Connection conn = null;
    PreparedStatement stmt = null;
    
    try{
        conn = new Conn_MES().getConn();
        stmt = conn.prepareStatement(strSql.toString());
        stmt.setString(1, tfass);
        stmt.setString(2, vwmainpart);
        stmt.setString(3, vwsubparttype);
        stmt.setString(4, vwsubpart);
        stmt.setString(5, vwsubpartquan);
        stmt.setString(6, qadno);
        stmt.setString(7, istempcar == null ? "F" : "T");
        stmt.setString(8, remark);
        stmt.setString(9, id);
        
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
    response.sendRedirect("bom_manage.jsp?eid=" + id + "&page=" + page_num);
%>
