<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK" language="java"%>
<%@taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<html>
    <head>
        <title>����KIN����������</title>
        <link rel="stylesheet" type="text/css" href="../../../cssfile/style.css">
        <script type="text/javascript" src="../../../JarResource/META-INF/tag/taglib_common.js"></script>
        <style>
            .mac_table td{
                border-right: 1px solid #C1DAD7;
                border-bottom: 1px solid #C1DAD7;
                border-top: 1px solid #C1DAD7;
                border-left: 1px solid #C1DAD7;
                background: #fff; //
                font-size: 11px;
                padding: 6px 6px 6px 12px;
                color: #4f6b72;
                font-family: ����, Arial;
                font-size: 12px;
            }
        </style>
    </head>
    <body>
        <div class="title"><strong><!-- InstanceBeginEditable name="����2" -->����KIN����������<!-- InstanceEndEditable --></strong></div>
        <div align="center">
            <div id="tip" style="height:20px;text-align:center;font:13px;color:red;"></div>
            <%
                   Object obj = session.getAttribute("mes_msg");
                   if(obj != null){
                       String msg = (String)obj;
                       out.println("<script>var tip_obj=document.getElementById(\"tip\");tip_obj.innerHTML=\"" + msg + "\"</script>");
                   } else {
                	   response.sendRedirect("./spec_kincode_search.jsp");
                   }
                   session.removeAttribute("mes_msg");
            %>
            <form name="form1" id="form1" action="upload.jsp" enctype="multipart/form-data" method="post" onsubmit="return check(this)">
                <table cellspacing=0 cellpadding=0 border=0 class="mac_table">
                    <tr>
                        <td>
                                �ϴ��ļ�: 
                        </td>
                        <td>
                            <input type="file" name="ofile1" size="15"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan=2 align="center">
                            <mes:button id="qulityMsg_modified" reSourceURL="../../../JarResource" value="�ϴ�" submit="true"/>&nbsp;
                            <mes:button id="qulityMsg_reset" reSourceURL="../../../JarResource" value="����" submit="false"/>
                        </td>
                    </tr>
                </table>
            </form>
            <div class="div_normal">
                    ע���ļ�ӦΪExcel��ʽ. <a  href="./specialKin.xls">����ģ���ļ�</a><br>
            </div>
        </div>
    </body>
    <script type="text/javascript" language="javascript">
       <!--
           /*
            * form��У��
            *
            * @param obj ������
            * @author gaohf
            * @date 2010-06-10
            */
           function check(form_obj){
               var upload_obj = form_obj["ofile1"];
               var cur_value = upload_obj.value;
               var regex = /.*\.xls$/i;// �ж��ļ������Ƿ�Ϊexcel
               var tip = document.getElementById("tip");
               
               var isExcel = cur_value.match(regex) != null;
               if(!isExcel){
                    tip.innerHTML = "�ļ���ʽ����ȷ";
               }
               return isExcel;
           }
       -->
    </script>
</html>