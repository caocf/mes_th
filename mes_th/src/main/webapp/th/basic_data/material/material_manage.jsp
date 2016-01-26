<%@ page contentType="text/html;charset=GBK" language="java" pageEncoding="GBK"%>
<%@ taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<%@ page import="com.qm.th.helper.Conn_MES"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.qm.th.bs.bean.PartList"%>
<%@ page import="com.qm.mes.os.util.LinkQuery"%>

<% 
	 /** ��ҳ�治���� */
	 response.setHeader("Pragma", "No-cache");
	 response.setHeader("Cache-Control", "no-cache");
	 response.setDateHeader("Expires", 0);
	
	 /** BOM��Ϣ�б� */
	 List<PartList> list = new ArrayList<PartList>();
	  
	 /** �޸���ĿID */
	 String eid = request.getParameter("eid");
	  
	 /** ��ѯ�ؼ��� */
	 String info = request.getParameter("info") != null ? request.getParameter("info") : "";
	  
	 /** ��ô���ʾ��ĳһҳ�� */
	 String strPage = request.getParameter("page"); 
	  
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
     PreparedStatement  stmt = null;
     ResultSet rs = null;

     try{
         // �������ݿ�����
         conn = new Conn_MES().getConn();
         // ������ѯ���
         String strSql = new StringBuilder()
         .append(" SELECT A.ID, A.CPARTNO, CASE WHEN A.CPARTTYPE = '00001' THEN '����' ELSE '�Ӽ�' END AS CPARTTYPE,")
         .append(" ISNULL(B.CTFASSNAME, '') AS CTFASSNAME, A.CREMARK")
         .append(" FROM PARTLIST A LEFT OUTER JOIN TFASSNAME B ON A.ITFASSNAMEID = B.ID").toString();
         // ������ģ����ѯ
         if(!info.equals("")){
             ArrayList<String> colist = new ArrayList<String>(5);
             colist.add("CPARTNO".toLowerCase());
             colist.add("CPARTTYPE".toLowerCase());
             colist.add("TFASSNAME".toLowerCase());
             
             info = info.trim();
             LinkQuery link = new LinkQuery();
             
             String condition = link.linkquery(info, colist, strSql, conn).toString();
             strSql = "SELECT * FROM (" + strSql.toString() + ") A WHERE " + condition;
         }
         stmt = conn.prepareStatement(strSql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
         rs = stmt.executeQuery();
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
                PartList pl = new PartList(); 
                pl.setId(rs.getInt("ID"));// ����
                pl.setCpartno(rs.getString("CPARTNO"));// �����
                pl.setCparttype(rs.getString("CPARTTYPE"));// �������
                pl.setCtfassname(rs.getString("CTFASSNAME"));// ��������
                pl.setRemark(rs.getString("CREMARK"));// ��ע
                
                list.add(pl);
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
        <title>����ά��</title>
        <link rel="stylesheet" type="text/css" href="../../../cssfile/style.css">
        <script type="text/javascript" src="../../../JarResource/META-INF/tag/taglib_common.js"></script>
    </head>
    <body>
        <div class="title"><strong><!-- InstanceBeginEditable name="����2" -->����ά��<!-- InstanceEndEditable --></strong></div>
        <br>
        <div align="center">
            <mes:table reSourceURL="../../../JarResource/" id="qulity_states">
                <mes:thead>
                    <mes:tr>
                        <mes:td width="30">���</mes:td>
                        <mes:td width="160">�������</mes:td>
                        <mes:td width="160">����ܳ�����</mes:td>
                        <mes:td width="120">������</mes:td>
                        <mes:td width="30">����</mes:td>
                        <mes:td width="30">ɾ��</mes:td>
                    </mes:tr>
                </mes:thead>
                <mes:tbody iterator="<%=list.iterator()%>" type="th.bs.bean.PartList" varName="pl">
                    <mes:tr id = "tr${pl.id}">
                        <mes:td><%=serialNumber++ + (intPage - 1)*PageSize%></mes:td>
                        <mes:td>${pl.cpartno}</mes:td>
                        <mes:td>${pl.ctfassname}</mes:td>
                        <mes:td>${pl.cparttype}</mes:td>
                        <mes:td>
                            <a href="javascript:window.location.href='material_modify.jsp?id=${pl.id}&page=<%=intPage%>'">����</a>
                        </mes:td>
                        <mes:td>
                            <a href="javascript:if(confirm('���Ҫɾ��������¼��')){ window.location.href='material_del.jsp?page=<%=intPage%>&intId=${pl.id}'; }">ɾ��</a>
                        </mes:td>
                    </mes:tr>
                </mes:tbody>
                <mes:tfoot>
                   <mes:tr>
                     <mes:td colspan="100%" align="center">
                         <form  name="form" style="margin: 0px"  method="post" onSubmit="return checkinput(this)" action="material_manage.jsp">
                               <input type="hidden" value="<%=info%>" name="info" >   
                                   ��<%=RowCount%>��  ��<%=intPage%>ҳ   ��<%=PageCount%>ҳ  
                                <%
                                    if(PageCount > 1){
                                        out.println("��ת����");
                                        out.print("<input size=\"2\"  name=\"page\" value=");%><%=intPage%><%out.println(">");
                                        out.println("ҳ");
                                    }
                                   
                                    if(intPage > 1){    
                                        out.println("<a href=\"?page=1&info=" + info + "\">��һҳ</a>");   
                                        out.println("<a href=\"?page=" + (intPage-1)+"&info=" + info + "\">��һҳ</a>");     
                                    }
                                    
                                    if(intPage<PageCount){
                                        out.println("<a href=\"?page=" + (intPage+1)+"&info=" + info + "\">��һҳ</a>");
                                        out.println("<a href=\"?page=" + (PageCount)+"&info=" + info + "\">���һҳ</a>");
                                    }
                                %>
                                <a href="material_maintenance.jsp?intPage=<%=intPage%>">������¼�¼��</a>
                           </form>
                      </mes:td>
                   </mes:tr>  
                </mes:tfoot> 
            </mes:table>
            <!-- InstanceBeginEditable name="����" -->
            <form  name="form1" method="get" style="margin: 0px" action="material_manage.jsp">
                <table class="tbnoborder">
                    <tr>
                        <td class="tbnoborder">�ؼ��ֲ�ѯ:</td>
                        <td class="tdnoborder">
                            <input type=text value="<%=info%>" name="info" id="info" class="box1" size=20 onMouseOver="this.className='box3'" 
                                   onFocus="this.className='box3'" onMouseOut="this.className='box1'" onBlur="this.className='box1'"></td>
                        <td class="tdnoborder">
                            <mes:button id="s1" reSourceURL="../../../JarResource/" submit="true" value="��ѯ"/>
                        </td>
                    </tr>
                </table>
            </form>
            <div class="div_normal">
                    <div class="div_normal">
                        �ؼ���Ϊ��<a class="div_red">������� ����ܳ����� ������</a><br>
                        ����Ϊ�յ�ʱ���ѯ������Ϣ����ѯ������<a class="div_red">"," </a>�ֿ���<br>
                    </div>
            </div>
            <!-- InstanceEndEditable -->
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
    </script>
</html>