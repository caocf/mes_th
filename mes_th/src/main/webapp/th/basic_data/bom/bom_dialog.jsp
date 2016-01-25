<%@ page contentType="text/html;charset=GBK" language="java" pageEncoding="GBK"%>
<%@ page import="com.qm.mes.th.helper.Conn_MES"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="mes.os.util.LinkQuery"%>

<% 
    //��ҳ�治����
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
    
    /** ������� (��/��) */
    String parttype = request.getParameter("parttype");
    /** ��ѯ�ؼ��� */
	String info = request.getParameter("info") != null ? request.getParameter("info") : "";
    
    int PageSize = 6;// һҳ��ʾ�ļ�¼��
    int RowCount = 0;// ��¼����
    int PageCount = 0;// ��ҳ��
    int intPage;// ����ʾҳ��
    int count = 1;// ���
    
    // ��ô���ʾ��ĳһҳ��
    String strPage = request.getParameter("page");  
    if(strPage == null || !strPage.matches("^\\d*$")){
        intPage = 1;
    }else{
        intPage = Integer.parseInt(strPage);
        if(intPage < 1) { intPage = 1; }
    }
%>

<html>
    <head>
        <title>��/�Ӽ���Ϣ</title>
        <link rel="stylesheet" type="text/css" href="../../../cssfile/style.css">
        <script type="text/javascript" src="../../../JarResource/META-INF/tag/taglib_common.js"></script>
    </head>
    <body>
    	<div align="center">
	        <table class="th_table" border=0 cellspacing=0 cellpadding=0>
	            <thead>
		            <tr>
		                <td width=30>���</td>
		                <td width=120>�����</td>
		                <td width=130>�������</td>
		                <td width=120>��ע</td>
		            </tr>
	            </thead>
	            <tbody>
	            <% 
	                 Connection conn = null;
	                 Statement stmt = null;
	                 ResultSet rs = null;
	                 
	                 try{
	                     conn = new Conn_MES().getConn();
	                     String strSql = "SELECT CPARTNO, CTFASSTYPE, CREMARK FROM PARTLIST WHERE CPARTTYPE = '" + parttype + "'";
	                     // ������ģ����ѯ
	                     if(!"".equals(info.trim())){
	                         ArrayList<String> colist = new ArrayList<String>(5);
	                         colist.add("CPARTNO".toLowerCase());
	                         
	                         info = info.trim();
	                         LinkQuery link = new LinkQuery();
	                         
	                         String condition = link.linkquery(info, colist, strSql, conn).toString();
	                         strSql = "SELECT * FROM (" + strSql + ") A WHERE " + condition;
	                     }
	                     strSql += " ORDER BY CPARTNO";
	                     stmt = conn.createStatement(
	                                 ResultSet.TYPE_SCROLL_INSENSITIVE, 
	                                 ResultSet.CONCUR_READ_ONLY);
	                     rs = stmt.executeQuery(strSql);
	                     rs.last();// �α�������β
	                     RowCount = rs.getRow();//����ܼ�¼��
	                     PageCount = (RowCount + PageSize - 1) / PageSize;//��ҳ��
							
	                     if(intPage > PageCount) { intPage = PageCount; }// �Ƿ��Ѿ��������һҳ
	                     if(PageCount>0){
	                           //����¼ָ�붨λ����һ��ʾλ��
	                           rs.absolute((intPage-1) * PageSize + 1);
	                           while(count <= PageSize && !rs.isAfterLast()){
	                                 out.println("<tr onmouseover=\"mousehover(this)\" onmouseout=\"mouseout(this)\" onclick=\"mouseclick(this)\" ondblclick=\"mousedblclick(this)\">");
	                                     /** ��� */
	                                     out.println("<td>");
	                                         out.println((intPage - 1) * PageSize + count++);
	                                     out.println("</td>");
	                                     
	                                     /** ����� */
	                                     out.println("<td>");
	                                         out.println(rs.getString("CPARTNO"));
	                                     out.println("</td>");
	                                     
	                                     /** ������� */
	                                     out.println("<td>");
	                                         out.println(rs.getString("CTFASSTYPE"));
	                                     out.println("</td>");
	                                     
	                                     /** ��ע */
	                                     out.println("<td>");
	                                         out.println(rs.getString("CREMARK"));
	                                     out.println("</td>");
	                                 out.println("</tr>");
	                                 //���α������ƶ�һλ
	                                 rs.next();
	                           }
	                     }
	                 }catch(Exception e){
	                     e.printStackTrace();
	                 }finally{
	                     // resource release
	                     if(rs != null){
	                         try{
	                             rs.close();
	                         }catch(SQLException e){
	                             e.printStackTrace();
	                         }finally{
	                             rs = null;
	                         }
	                     }
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
	                 // ���������㣬�Կհײ���
	                 if(count < PageSize){
	                     // colspan:�������   28:���и߶�
	                     out.println("<tr><td colspan=4 height=" + ((PageSize - count + 1) * 30) + ">&nbsp</td></tr>");
	                 }
	            %>
	            </tbody>
	            <tfoot>
		            <tr>
			            <td colspan="100%" align="center">
			                <form style="margin:0px" onSubmit="return checkinput(this)">
				                 ��<%=RowCount%>��  ��<%=intPage%>ҳ  ��<%=PageCount%>ҳ  
				                 <input type="hidden" name="parttype" value="<%=parttype%>">
				                 <%
				                     if(PageCount > 1){
					                     out.println("��ת����");
					                     out.print("<input size=\"3\" name=\"page\" value=");%><%=intPage%><%out.println(">");
					                     out.println("ҳ");
					                     out.println("<img src=\"images/page-first.gif\" onclick=\"chagePage(1)\"/>");   
					                     out.println("<img src=\"images/page-prev.gif\"  onclick=\"chagePage(" + (intPage - 1) + ")\"/>");     
					                     out.println("<img src=\"images/page-next.gif\" onclick=\"chagePage(" + (intPage + 1) + ")\"/>");
					                     out.println("<img src=\"images/page-last.gif\" onclick=\"chagePage(" + PageCount + ")\"/>");
				                     }
				                 %>
			                </form>
			            </td>
		            </tr>
	            </tfoot>
	        </table>
	        <!-- InstanceBeginEditable name="����" -->
            <form name="form1" method="get" style="margin: 0px" onsubmit="return search(this)">
                <table class="tbnoborder">
                    <tr>
                        <td class="tbnoborder">������Ų�ѯ:</td>
                        <td class="tdnoborder">
                            <input type=text value="<%=info%>" name="info" id="info" class="box1" size=20 onMouseOver="this.className='box3'" 
                                   onFocus="this.className='box3'" onMouseOut="this.className='box1'" onBlur="this.className='box1'"></td>
                    </tr>
                </table>
            </form>
            <!-- InstanceEndEditable -->
        </div>
    <body>
</html>