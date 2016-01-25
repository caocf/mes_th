<%@ page contentType="text/html;charset=GBK" language="java" pageEncoding="GBK"%>
<%@ taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<%@ page import="com.qm.mes.th.helper.Conn_MES"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>

<% 
	 /** ����ID */
	 String id = request.getParameter("intId");
	 /** ҳ�� */
	 String page_num = request.getParameter("page");
	 /** ��� */
	 String part = "";
	 /** ת����� */
	 String convertPart = "";
	 /** ��ע */
	 String remark = "";

     Connection conn = null;
     Statement stmt = null;
     ResultSet rs = null;
     
     try{
         conn = new Conn_MES().getConn();
         stmt = conn.createStatement();
         rs = stmt.executeQuery("SELECT CPART, CCONVERTPART, CREMARK FROM SPECPART WHERE ID = " + id);
         
         if(rs.next()){
        	 part = rs.getString("CPART");
        	 convertPart = rs.getString("CCONVERTPART");
        	 remark = rs.getString("CREMARK");
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
%>

<html>
    <head>
        <title>���⴦����ά��</title>
        <meta http-equiv=content-type content="text/html;charset=GBK">
        <link rel="stylesheet" type="text/css" href="../../../cssfile/style.css">
        <link rel="stylesheet" type="text/css" href="../../../cssfile/th_style.css">
        <script type="text/javascript" src="../../../JarResource/META-INF/tag/taglib_common.js"></script>
        <script type="text/javascript" src="Dialog.js"></script>
    </head>
    <body>
         <div align="center">
            <div class="title"><strong><!-- InstanceBeginEditable name="����" -->���⴦����ά��<!-- InstanceEndEditable --></strong></div>
            <form action="spec_update.jsp" onsubmit="return checkForm(this)">
                <input type="hidden" name="intId" value="<%=id%>">
                <input type="hidden" name="page" value="<%=page_num%>">
            
                <table border=0 cellspacing=0 cellpadding=0 class="th_table">
                    <tr>
                        <td>ID</td>
                        <td>
                            <mes:inputbox colorstyle="" id="primaryID" name="primaryID" reSourceURL="../../../JarResource" readonly="true" value="<%=id%>"/>
                        </td>
                        <td>���</td>
                        <td>
                            <mes:inputbox colorstyle="box_red" id="part" name="part" reSourceURL="../../../JarResource" value="<%=part%>"/>
                        </td>
                    </tr>
                    <tr>
                        <td>ת�����</td>
                        <td>
                            <mes:inputbox colorstyle="box_red" id="convertPart" name="convertPart" reSourceURL="../../../JarResource" value="<%=convertPart%>"/>
                        </td>
                        <td>��ע</td>
                        <td>
                            <mes:inputbox colorstyle="box_red" id="remark" name="remark" reSourceURL="../../../JarResource" value="<%=remark%>"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan = 4 align="center">
                            <mes:button id="c_save" reSourceURL="../../../JarResource" value="�ύ" submit="true"/>
                            <mes:button id="c_clean" reSourceURL="../../../JarResource" value="���" submit="false"/>
                            <mes:button id="c_back" reSourceURL="../../../JarResource" value="����" onclick="backward()"/>
                        </td>
                    </tr>
                </table>
            </form>
         </div>
    </body>
    <script>
        <!--
                /**
                 *  У��FormԪ��
                 *
                 *  @param fobj ������
                 */
                function checkForm(fobj){
                    return checkEmpty(fobj);
                }
                
                /**
                 * ����ı�����Ϊ��
                 *
                 * @return false:�����쳣  true:���� 
                 */
                function checkEmpty(f){
                    var inputs = f.getElementsByTagName("input");
                    for(var i=0; i<inputs.length; i++){
                        var ipt = inputs[i];
                        if(ipt["type"] == "text"){
                            if(!ipt["value"]){
                                alert("�ı�����Ϊ��!");
                                ipt.focus();
                                return false;
                            }
                        }
                    }
                    return true;
                }
                
                /**
                 * ����
                 */
                function backward(){
                	var eid = "<%=id%>";
                	var page = "<%=page_num%>";
                	var url = "spec_manage.jsp?eid=" + eid + "&page=" + page;
                	window.location.href = url;
                }
        -->
    </script>
</html>