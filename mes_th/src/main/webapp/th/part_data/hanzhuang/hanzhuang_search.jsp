<%@ page contentType="text/html;charset=GBK" language="java" pageEncoding="GBk"%>
<%@ taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<%@ page import="com.qm.th.helper.Conn_MES"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.qm.th.ps.bean.CarData"%>

<% 
     /** ��ҳ�治���� */
     response.setHeader("Pragma", "No-cache");
     response.setHeader("Cache-Control", "no-cache");
     response.setDateHeader("Expires", 0);
     
     /** �������� */
     String rad = request.getParameter("rad");
     
     /** ��ʼ���� */
     String startDate = request.getParameter("startdate");
     
     /** �������� */
     String endDate = request.getParameter("enddate");
     
     /** KIN�� */
     String kincode = request.getParameter("kincode");
    
     /** BOM��Ϣ�б� */
     List<CarData> list = new ArrayList<CarData>();
      
     /** �޸���ĿID */
     String eid = request.getParameter("eid");
      
     /** ��ѯ�ؼ��� */
     String info = request.getParameter("info") != null ? request.getParameter("info") : "";
      
     /** ��ô���ʾ��ĳһҳ�� */
     String strPage = request.getParameter("page"); 

     rad = rad == null ? "" : rad;
     startDate = startDate == null ? "" : startDate;
     endDate = endDate == null ? "" : endDate;
     kincode = kincode == null ? "" : kincode;
     
     int PageSize = 10; //һҳ��ʾ�ļ�¼��
     int RowCount = 0; //��¼����
     int PageCount = 0; //��ҳ��
     int intPage; //����ʾҳ��
     int record_count = 0;
     int serialNumber = 1;
      
     if(strPage == null){
        intPage = 1;
     }else{
        intPage = Integer.parseInt(strPage);
        if(intPage < 1) { intPage = 1; }
     }

     Connection conn = null;
     Statement  stmt = null;
     ResultSet rs = null;

     try{
         // �������ݿ�����
         conn = new Conn_MES().getConn();
         StringBuilder strSql = new StringBuilder("SELECT ID, CCARNO, CSEQNO, WUPTIME, DWBEGIN FROM CARDATA");
         
         if(rad != null && !"".equals(rad.trim())){
             if("row_time".equalsIgnoreCase(rad)){
                 if(startDate != null && !"".equals(startDate)){
                     strSql.append(" where datediff(minute, dWBegin, '");
                     strSql.append(startDate);
                     strSql.append("') < 0");
                 }
                 if(endDate != null && !"".equals(endDate)){
                     if(strSql.indexOf("where") != -1){
                         strSql.append(" and ");
                     }else{
                         strSql.append(" where ");
                     }
                     strSql.append(" datediff(minute, dWBegin, '");
                     strSql.append(endDate);
                     strSql.append("') > 0");
                 }
             }else if("row_kin".equals(rad)){
                 if(kincode!= null && !"".equals(kincode)){
	                 strSql.append(" WHERE CCARNO like '");
	                 strSql.append(kincode);
	                 strSql.append("%'");
                 }
             }
         }
         stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
         rs = stmt.executeQuery(strSql.toString());
         rs.last();// �α�������β
         RowCount = rs.getRow();//����ܼ�¼��
         PageCount = (RowCount + PageSize - 1) / PageSize;//��ҳ��
         
         if(intPage > PageCount){ 
             intPage = PageCount;
         }
         if(PageCount > 0){
             //����¼ָ�붨λ����һ��ʾλ��
             rs.absolute((intPage - 1) * PageSize + 1);
             while(record_count < PageSize && !rs.isAfterLast()){
                CarData ca = new CarData(); 
                ca.setId(rs.getInt("ID"));// ����
                ca.setCcarno(rs.getString("CCARNO"));// KIN��
                ca.setCseqno(rs.getString("CSEQNO"));// ��װ˳���
                ca.setWuptime(rs.getInt("WUPTIME"));// ��װ���ߴ���
                ca.setDwbegin(rs.getString("DWBEGIN"));// ��װ����ʱ��
                
                list.add(ca);
                record_count++;
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
%>
<html>
    <head>
        <title>��װ�ظ����߲�ѯ</title>
        <meta http-equiv=content-type content="text/html;charset=GBK">
        <link rel="stylesheet" type="text/css" href="../../../cssfile/style.css">
        <link rel="stylesheet" type="text/css" href="../../../cssfile/th_style.css">
        <script type="text/javascript" src="../../../JarResource/META-INF/tag/taglib_common.js"></script>
        <script type="text/javascript" src="js/core.js"></script>
    </head>
    <body>
        <div align="center">
            <div class="title"><strong><!-- InstanceBeginEditable name="����" -->��װ�ظ����߲�ѯ<!-- InstanceEndEditable --></strong></div>
            <form onsubmit="return check(this)">
                <table style="font-size:10pt;">
                    <tr>
                        <td>
                            <input type="radio" name="rad" value="row_time" checked>
                        </td>
                        <td>
                            ��ʼʱ��:
                        </td>
                        <td>
                             <mes:calendar id="startdate" name="startdate" reSourceURL="../../../JarResource/" showDescripbe="false" haveTime="true" value="<%=startDate%>"/>
                        </td>
                        <td></td>
                        <td>
                            ����ʱ��:
                        </td>
                        <td>
                             <mes:calendar id="enddate" name="enddate" reSourceURL="../../../JarResource/" showDescripbe="false" haveTime="true" value="<%=endDate%>"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="radio" name="rad" value="row_kin">
                        </td>
                        <td>
                            KIN:
                        </td>
                        <td colspan="3">
                             <mes:inputbox colorstyle="red" id="kincode" name="kincode" reSourceURL="../../../JarResource/" size="30" value="<%=kincode%>"/>
                        </td>
                        <td>
                             <mes:button id="c_submit" reSourceURL="../../../JarResource/" value="��ѯ" submit="true"/>
                        </td>
                    </tr>
                </table>
            </form>
            <hr width="550px" />
            <br>
            <mes:table reSourceURL="../../../JarResource/" id="qulity_states">
                <mes:thead>
                    <mes:tr>
                        <mes:td width="30">���</mes:td>
                        <mes:td width="160">KIN��</mes:td>
                        <mes:td width="160">��װ˳���</mes:td>
                        <mes:td width="120">��װ���ߴ���</mes:td>
                        <mes:td width="160">��װ����ʱ��</mes:td>
                    </mes:tr>
                </mes:thead>
                <mes:tbody iterator="<%=list.iterator()%>" type="th.ps.bean.CarData" varName="ca">
                    <mes:tr id = "tr${ca.id}">
                        <mes:td><%=serialNumber++ + (intPage - 1)*PageSize%></mes:td>
                        <mes:td>${ca.ccarno}</mes:td>
                        <mes:td>${ca.cseqno}</mes:td>
                        <mes:td>${ca.wuptime}</mes:td>
                        <mes:td>${ca.dwbegin}</mes:td>
                    </mes:tr>
                </mes:tbody>
                <mes:tfoot>
                   <mes:tr>
                     <mes:td colspan="100%" align="center">
                         <form id="tform" name="form" style="margin: 0px"  method="post" onSubmit="return checkinput(this)" action="hanzhuang_search.jsp">
                               <input type="hidden" value="<%=info%>" name="info" >
                               <input type="hidden" value="<%=startDate%>" name="startdate" >
                               <input type="hidden" value="<%=endDate%>" name="enddate" >
                               <input type="hidden" value="<%=kincode%>" name="kincode" >
                               <input type="hidden" value="<%=rad%>" name="rad" >
                               
                                   ��<%=RowCount%>��  ��<%=intPage%>ҳ   ��<%=PageCount%>ҳ  
                                <%
                                    if(PageCount > 1){
                                        out.println("��ת����");
                                        out.print("<input size=\"2\"  name=\"page\" value=");%><%=intPage%><%out.println(">");
                                        out.println("ҳ");
                                    }
                                    if(intPage > 1){    
                                        out.println("<a href=\"firstPage\" onclick=\"return turnPage(1)\">��һҳ</a>");   
                                        out.println("<a href=\"prePage\" onclick=\"return turnPage(" + (intPage-1) + ")\">��һҳ</a>");     
                                    }
                                    if(intPage<PageCount){
                                        out.println("<a href=\"nextPage\" onclick=\"return turnPage(" + (intPage + 1) + ")\">��һҳ</a>");
                                        out.println("<a href=\"lastpage\" onclick=\"return turnPage(" + (PageCount) + ")\">���һҳ</a>");
                                    }
                                %>
                           </form>
                      </mes:td>
                   </mes:tr>  
                </mes:tfoot> 
            </mes:table>
        </div>
    </body>
   <script type="text/javascript">
       /**
        * ��У��
        */
       function checkinput(thisform){
            var re =  /^[0-9]+$/;
            var i = 0;
            var qm;
            var mm = document.getElementsByName("method");
            for(i = 0; i < mm.length; i++){
                if(mm[i].checked == true){
                    qm = mm[i].value;
                }
            }   
            if((qm == "ById") && (isNaN(thisform.process_info.value) == true)){
                alert('����ֻ�����������֣�');
                thisform.process_info.value = "";
                thisform.process_info.focus();
                return false;
            }
            if(<%=PageCount%> > 1)
            if(!re.test(document.getElementsByName("page")[0].value )){   
                alert("��תҳ��Ӧ�����������!");
                document.getElementsByName("page")[0].value = "";
                return false;       
            }
            return true;
       }
       
       /**
        * ��ѡ����
        */
       function selecttr(){
           var eid = '<%=eid==null?"-1":eid%>';
           if(eid!=-1 && document.getElementById('tr'+eid)!=null){
                document.getElementById('tr'+eid).click();
           }       
       }
       selecttr();
       
       /**
        *  ��ҳ����
        */
       function turnPage(pageNumber){
            /** form���� */
            var obj = document.getElementById("tform");
            
            if(obj){
                var url = obj["action"];
                obj["action"] = url + "?page=" + pageNumber;
                obj.submit();
            }
            return false;
       }
       
       /**
        * Ĭ��ѡ�� radioButton
        */
       var selRadio = function(){
            // radio button ��������
            var rads = document.getElementsByName("rad");
            // ѡ�е�radio button��
            var selName = "<%=rad%>";
            
            // ��������ѡ����Ӧ��radio button
            for(var i=0; i<rads.length; i++){
                var rad = rads[i];
                if(selName && rad["value"] == selName){
                    rad["checked"] = "checked";
                    break;
                }
            }
       }();
       
       /**
        * У���
        */
       function check(formObj){
            /** ��ʼ���� */
            var startdate = document.getElementById("startdate");
            
            // У�鿪ʼ���ڸ�ʽ
            if(checkDate(startdate)){
                // ��������
                var enddate = document.getElementById("enddate");
                // У���������
                return checkDate(enddate);
            }
            return false;
       }
       
       /**
        * ������������ʽ
        */
       function checkDate(cal){
           /** ��������ڸ�ʽ */
           var regexp = /(^((19){1}|(20){1}))\d{2}-(([0-1]{1}[0-9]{1}))-([0-3]{1}[0-9]{1}) (([0-2]{1}[0-9]{1}):([0-5]{1}[0-9]{1}):([0-5]{1}[0-9]{1})$)/;
           /** �����Ƿ�Ϸ� */
           var isLegal = true;
           
           // ������ı���Ϊ�գ�����У��
           if(cal.value){
	           /** �Ƿ���Ϲ��� */
	           isLegal = regexp.test(cal.value);
	           
	           if(!isLegal){
	               alert("�������ڸ�ʽ����ȷ!");
	               cal.value = "";
	               cal.focus();
	           }
           }
           return isLegal;
       }
    </script>
</html>