<%@ page contentType="text/html;charset=GBK" language="java" pageEncoding="GBK"%>
<%@ taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<%@ page import="com.qm.th.helper.Conn_MES"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.qm.th.bs.bean.PartData"%>
<%@ page import="com.qm.mes.os.util.LinkQuery"%>

<%    
	  /** ��ҳ�治���� */
	  response.setHeader("Pragma", "No-cache");
	  response.setHeader("Cache-Control", "no-cache");
	  response.setDateHeader("Expires", 0);

	  /** BOM��Ϣ�б� */
      List<PartData> list = new ArrayList<PartData>();
		
	  /** �޸���ĿID */
	  String eid = request.getParameter("eid");
		
	  /** ��ѯ�ؼ��� */
	  String info = request.getParameter("info") != null ? request.getParameter("info") : "";
	  
	  System.out.println(info);
		
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
      PreparedStatement stmt = null;
      ResultSet rs = null;
      
      try{
          // �������ݿ�����
          conn = new Conn_MES().getConn();
          // ������ѯ���
          String strSql = "SELECT ID, CTFASS, CVWMAINPART, NVWSUBPARTTYPE, CVWSUBPART, NVWSUBPARTQUAN, CQADNO, CISTEMPCAR, CREMARK FROM PARTDATA";
          // ������ģ����ѯ
          if(!info.equals("")){
              ArrayList<String> colist = new ArrayList<String>(5);
              colist.add("CTFASS".toLowerCase());
              colist.add("CVWMAINPART".toLowerCase());
              colist.add("CVWSUBPART".toLowerCase());
              colist.add("CQADNO".toLowerCase());
              
              info = info.trim();
              LinkQuery link = new LinkQuery();
              
              String condition = link.linkquery(info, colist, strSql, conn).toString();
              strSql = "SELECT * FROM (" + strSql + ") A WHERE " + condition;
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
	             PartData pd = new PartData(); 
	             pd.setId(rs.getInt("ID"));// ����
	             pd.setCtfass(rs.getString("CTFASS"));// ��������
	             pd.setCvwmainpart(rs.getString("CVWMAINPART"));// �����������
	             pd.setNvwsubparttype(rs.getInt("NVWSUBPARTTYPE"));// ���������������
	             pd.setCvwsubpart(rs.getString("CVWSUBPART"));// �����������
	             pd.setNvwsubpartquan(rs.getInt("NVWSUBPARTQUAN"));// �������������
	             pd.setCqadno(rs.getString("CQADNO"));// ������
	             pd.setCistempcar(rs.getString("CISTEMPCAR"));// ���ȳ���
	             pd.setCremark(rs.getString("CREMARK"));// ��ע
	             
	             list.add(pd);
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
        <title>BOM��Ϣά��</title>
        <link rel="stylesheet" type="text/css" href="../../../cssfile/style.css">
        <script type="text/javascript" src="../../../JarResource/META-INF/tag/taglib_common.js"></script>
    </head>
    <body>
        <div class="title"><strong><!-- InstanceBeginEditable name="����2" -->BOM��Ϣά��<!-- InstanceEndEditable --></strong></div>
        <br>
        <div align="center">
            <mes:table reSourceURL="../../../JarResource/" id="qulity_states">
                <mes:thead>
                    <mes:tr>
                        <mes:td width="30">���</mes:td>
                        <mes:td width="160">��������</mes:td>
                        <mes:td width="160">�����������</mes:td>
                        <mes:td width="120">���������������</mes:td>
                        <mes:td width="160">�����������</mes:td>
                        <mes:td width="100">�������������</mes:td>
                        <mes:td width="120">������</mes:td>
                        <mes:td width="60">���ȳ���</mes:td>
                        <mes:td width="30">����</mes:td>
                        <mes:td width="30">ɾ��</mes:td>
                    </mes:tr>
                </mes:thead>
                <mes:tbody iterator="<%=list.iterator()%>" type="th.bs.bean.PartData" varName="pd">
                    <mes:tr id = "tr${pd.id}">
                        <mes:td><%=serialNumber++ + (intPage - 1)*PageSize%></mes:td>
                        <mes:td>${pd.ctfass}</mes:td>
                        <mes:td>${pd.cvwmainpart}</mes:td>
                        <mes:td>${pd.nvwsubparttype}</mes:td>
                        <mes:td>${pd.cvwsubpart}</mes:td>
                        <mes:td>${pd.nvwsubpartquan}</mes:td>
                        <mes:td>${pd.cqadno}</mes:td>
                        <mes:td>${pd.cistempcar}</mes:td>
                        <mes:td>
                            <a href="javascript:window.location.href='bom_modify.jsp?intId=${pd.id}&strPage=<%=intPage%>'">����</a>
                        </mes:td>
                        <mes:td>
                            <a href="javascript:if(confirm('���Ҫɾ��������¼��')){ window.location.href='bom_del.jsp?page=<%=intPage%>&intId=${pd.id}'; }">ɾ��</a>
                        </mes:td>
                    </mes:tr>
                </mes:tbody>
                <mes:tfoot>
                   <mes:tr>
                     <mes:td colspan="100%" align="center">
                         <form  name="form" style="margin: 0px"  method="post" onSubmit="return checkinput(this)" action="bom_manage.jsp">
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
                                <a href="bom_maintenance.jsp?intPage=<%=intPage%>">������¼�¼��</a>
                           </form>
                      </mes:td>
                   </mes:tr>  
                </mes:tfoot> 
            </mes:table>
            <!-- InstanceBeginEditable name="����" -->
            <form  name="form1" method="get" style="margin: 0px" action="bom_manage.jsp">
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
                        �ؼ���Ϊ��<a class="div_red">�������� ����������� ����������� ������</a><br>
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