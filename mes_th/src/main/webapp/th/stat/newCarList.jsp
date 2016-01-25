<%@ page contentType="text/html;charset=GBK" language="java" pageEncoding="GBK"%>
<%@ taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<%@ page import="common.Conn_MES"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="th.bs.bean.NewCarData"%>
<%@ page import="mes.os.util.LinkQuery"%>

<% 
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%    
	  /** ��ҳ�治���� */
	  response.setHeader("Pragma", "No-cache");
	  response.setHeader("Cache-Control", "no-cache");
	  response.setDateHeader("Expires", 0);

	  /** BOM��Ϣ�б� */
      List<NewCarData> list = new ArrayList<NewCarData>();
		
	  /** ��ѯ�ؼ��� */
	  String info = request.getParameter("info") != null ? request.getParameter("info") : "";
		
	  /** ��ô���ʾ��ĳһҳ�� */
	  String strPage = request.getParameter("page"); 
		
	  int PageSize = 30; //һҳ��ʾ�ļ�¼��
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
          String strSql = "select b.ctfass, DBO.TRIMSUFFIX(cfilename) cfilename, ctype,";
                 strSql += " convert(varchar(20), usedtime, 120) usedtime, b.cRemark";
                 strSql += " from (select *  from fit_newcar where Usedtime is not null) a ";
                 strSql += " inner join ( select ctfass, cqadno, cremark from partData";
                 strSql += " group by ctfass, cqadno, cremark ) b on a.CQadno = b.cQADNo";
          
          // ������ģ����ѯ
          if(!info.equals("")){
              ArrayList<String> colist = new ArrayList<String>(5);
              colist.add("ctfass".toLowerCase());
              
              info = info.trim();
              LinkQuery link = new LinkQuery();
              
              String condition = link.linkquery(info, colist, strSql, conn).toString();
              strSql = "SELECT * FROM (" + strSql + ") A WHERE " + condition;
          }
          strSql += " order by usedtime desc";
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
	        	 NewCarData pd = new NewCarData(); 
	             pd.setCtfass(rs.getString("ctfass"));
	             pd.setCfilename(rs.getString("cfilename"));
	             pd.setCremark(rs.getString("cRemark"));
	             pd.setCtype(rs.getString("ctype"));
	             pd.setUsedtime(rs.getString("usedtime"));
	             
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
        <link rel="stylesheet" type="text/css" href="<%=basePath%>cssfile/style.css">
        <script type="text/javascript" src="<%=basePath%>JarResource/META-INF/tag/taglib_common.js"></script>
    </head>
    <body>
        <div class="title"><strong><!-- InstanceBeginEditable name="����2" -->�³���ƥ���¼��ѯ<!-- InstanceEndEditable --></strong></div>
        <br>
        <div align="center">
            <!-- InstanceBeginEditable name="����" -->
            <form  name="form1" method="get" style="margin: 0px" action="newCarList.jsp">
                <table class="tbnoborder">
                    <tr>
                        <td class="tbnoborder">�������Ų�ѯ:</td>
                        <td class="tdnoborder">
                            <input type=text value="<%=info%>" name="info" id="info" class="box1" size=20 onMouseOver="this.className='box3'" 
                                   onFocus="this.className='box3'" onMouseOut="this.className='box1'" onBlur="this.className='box1'"></td>
                        <td class="tdnoborder">
                            <mes:button id="s1" reSourceURL="../../../JarResource/" submit="true" value="��ѯ"/>
                        </td>
                    </tr>
                </table>
            </form>
            <!-- InstanceEndEditable -->
            <mes:table reSourceURL="../../JarResource/" id="qulity_states">
                <mes:thead>
                    <mes:tr>
                        <mes:td width="30">���</mes:td>
                        <mes:td width="120">��������</mes:td>
                        <mes:td width="120">������</mes:td>
                        <mes:td width="60">��������</mes:td>
                        <mes:td width="160">����ʱ��</mes:td>
                        <mes:td width="200">�������</mes:td>
                    </mes:tr>
                </mes:thead>
                <mes:tbody iterator="<%=list.iterator()%>" type="th.bs.bean.NewCarData" varName="pd">
                    <mes:tr>
                        <mes:td><%=serialNumber++ + (intPage - 1)*PageSize%></mes:td>
                        <mes:td>${pd.ctfass}</mes:td>
                        <mes:td>${pd.cfilename}</mes:td>
                        <mes:td>${pd.ctype}</mes:td>
                        <mes:td>${pd.usedtime}</mes:td>
                        <mes:td>${pd.cremark}</mes:td>
                    </mes:tr>
                </mes:tbody>
                <mes:tfoot>
                   <mes:tr>
                     <mes:td colspan="100%" align="center">
                         <form  name="form" style="margin: 0px"  method="post" onSubmit="return checkinput(this)" action="newCarList.jsp">
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
    </script>
</html>