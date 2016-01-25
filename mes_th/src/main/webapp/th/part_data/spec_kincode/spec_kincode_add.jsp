<%@ page contentType="text/html;charset=GBK" language="java" pageEncoding="GBK"%>
<%@ taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<%@ page import="common.Conn_MES"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException" %>

<% 
     /** ҳ�� */
     String page_num = request.getParameter("page");
     /** ���ID�� */
     String maxId = "1";

     Connection conn = null;
     Statement stmt = null;
     ResultSet rs = null;
     
     try{
         conn = new Conn_MES().getConn();
         stmt = conn.createStatement();
         rs = stmt.executeQuery("SELECT (ISNULL(MAX(ID), 0) + 1) as maxID FROM SPECIALKIN");
         
         if(rs.next()){
             maxId = rs.getString("maxID");
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
        <title>����KIN��ά��</title>
        <meta http-equiv=content-type content="text/html;charset=GBK">
        <link rel="stylesheet" type="text/css" href="../../../cssfile/th_style.css">
        <link rel="stylesheet" type="text/css" href="../../../cssfile/style.css">
        <script type="text/javascript" src="../../../JarResource/META-INF/tag/taglib_common.js"></script>
        <script type="text/javascript" src="Dialog.js"></script>
    </head>
    <body>
         <div align="center">
            <div class="title"><strong><!-- InstanceBeginEditable name="����" -->����KIN��ά��<!-- InstanceEndEditable --></strong></div>
            <form action="spec_kincode_save.jsp" onsubmit="return checkForm(this)">
                <input type="hidden" name="page" value="<%=page_num%>">
                <table border=1 cellspacing=0 cellpadding=0 style="font-family: ����, Arial;font-size: 12px;">
                    <tr>
                        <td>ID</td>
                        <td>
                            <mes:inputbox colorstyle="" id="primaryID" name="primaryID" reSourceURL="../../../JarResource" readonly="true" value="<%=maxId%>"/>
                        </td>
                        <td width=50 align="right">KIN��</td>
                        <td>
                            <mes:inputbox colorstyle="box_red" id="ccarno" name="ccarno" reSourceURL="../../../JarResource" maxlength="9"/>
                        </td>
                    </tr>
                    <tr>
                        <td>��Ч����</td>
                        <td>
                            <mes:calendar id="dwbegin" name="dwbegin" reSourceURL="../../../JarResource" haveTime="true"/>
                        </td>
                        <td width=50 align="right">��ע</td>
                        <td>
                            <mes:inputbox colorstyle="box_red" id="remark" name="remark" reSourceURL="../../../JarResource"/>
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
                    if(checkEmpty(fobj)){
                        return checkNum(fobj) && checkDate(fobj);
                    }
                    return false;
                }
                
                /**
                 * У��¼��KIN�Ÿ�ʽ
                 */
                function checkNum(fobj){
                	// �ƶ����ݸ�ʽ�� 9λ����
                	var regex = /[0-9]{9}$/;
                	// �ı������
                	var txt = fobj["ccarno"];
                	// ¼������ֵ
                	var val = txt.value;
                	// ����¼���ʶ��
                	var isCorrect = false;
                	
                	// У��¼������
                	if((isCorrect = regex.test(val)) == false){
                		alert("KIN��¼�����������9λ���ַ�");
                		txt.select();
                		txt.focus();
                	}
                	return isCorrect;
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
                 * ������������ʽ
                 */
                function checkDate(f){
                    /** ��������ڸ�ʽ */
                    var regexp = /(^((19){1}|(20){1}))\d{2}-(([0-1]{1}[0-9]{1}))-([0-3]{1}[0-9]{1}) (([0-2]{1}[0-9]{1}):([0-5]{1}[0-9]{1}):([0-5]{1}[0-9]{1})$)/;
                    /** ���ڿؼ� */
                    var in_date = f["dwbegin"];
                    /** �Ƿ���Ϲ��� */
                    var isLegal = regexp.test(in_date.value);
                    
                    if(!isLegal){
                        alert("�������ڸ�ʽ����ȷ!");
                        in_date.focus();
                    }
                    return isLegal;
                }
                
                /**
                 * ����
                 */
                function backward(){
                	var page = "<%=page_num%>";
                	var url = "spec_kincode_search.jsp?page=" + page;
                	window.location.href = url;
                }
        -->
    </script>
</html>