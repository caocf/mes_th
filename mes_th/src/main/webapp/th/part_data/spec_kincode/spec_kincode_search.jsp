<%@ page contentType="text/html;charset=GBK" language="java" pageEncoding="GBK"%>
<%@ taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<%@ page import="common.Conn_MES"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="th.ps.bean.CarData"%>
<%@ page import="mes.os.util.LinkQuery"%>

<%    
      /** ��ҳ�治���� */
      response.setHeader("Pragma", "No-cache");
      response.setHeader("Cache-Control", "no-cache");
      response.setDateHeader("Expires", 0);

      /** BOM��Ϣ�б� */
      List<CarData> list = new ArrayList<CarData>();
        
      /** �޸���ĿID */
      String eid = request.getParameter("eid");
        
      /** ��ѯ�ؼ��� */
      String info = request.getParameter("info") != null ? request.getParameter("info") : "";
      
      int serialNumber = 1;
      
      Connection conn = null;
      PreparedStatement stmt = null;
      ResultSet rs = null;
      
      try{
          // ������ѯ���
    	  StringBuilder strSql = new StringBuilder();
    	  strSql.append("SELECT b.id, b.ccarno, b.cenabled, b.cremark,");
    	  strSql.append("            CONVERT(CHAR, A.DWBEGIN, 120) AS DWBEGIN,");
    	  strSql.append("            CONVERT(CHAR, A.DABEGIN, 120) AS DABEGIN");
    	  strSql.append(" FROM specialkin b LEFT JOIN cardata a ON a.ccarno like b.ccarno + '%'");

          // �������ݿ�����
          conn = new Conn_MES().getConn();
          // ������ģ����ѯ
          if(!info.equals("")){
              ArrayList<String> colist = new ArrayList<String>(5);
              colist.add("ccarno");
              colist.add("cremark");
              
              info = info.trim();
              LinkQuery link = new LinkQuery();
              
              String condition = link.linkquery(info, colist, strSql.toString(), conn).toString();
              String subQuery = strSql.toString();
              strSql.setLength(0);
              strSql.append("SELECT * FROM (")
            		.append(subQuery)
              		.append(") A WHERE ")
              		.append(condition);
          }
    	  strSql.append(" ORDER BY cenabled desc");
    	  
          stmt = conn.prepareStatement(strSql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
          rs = stmt.executeQuery();
          
          while(rs.next()){
                 CarData cd = new CarData(); 
                 cd.setId(rs.getInt("id"));// ����
                 cd.setCcarno(rs.getString("ccarno"));// KIN��
                 cd.setDwbegin(rs.getString("dwbegin"));// ��װ����ʱ��
                 cd.setDabegin(rs.getString("dabegin"));// ��װ����ʱ��
                 cd.setCenabled(rs.getString("cenabled"));// �Ƿ���
                 cd.setCremark(rs.getString("cremark"));// ��ע
                 
                 list.add(cd);
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
        <title>����KIN�Ų�ѯ</title>
        <link rel="stylesheet" type="text/css" href="../../../cssfile/style.css">
        <script type="text/javascript" src="../../../JarResource/META-INF/tag/taglib_common.js"></script>
    </head>
    <body>
        <div class="title"><strong><!-- InstanceBeginEditable name="����2" -->����KIN�Ų�ѯ<!-- InstanceEndEditable --></strong></div>
        <br>
        <div align="center">
            <mes:table reSourceURL="../../../JarResource/" id="qulity_states">
                <mes:thead>
                    <mes:tr>
                        <mes:td width="30">���</mes:td>
                        <mes:td width="90">KIN</mes:td>
                        <mes:td width="160">��װ����ʱ��</mes:td>
                        <mes:td width="160">��װ����ʱ��</mes:td>
                        <mes:td width="60">�Ƿ���</mes:td>
                        <mes:td width="120">��ע</mes:td>
                        <mes:td width="30">����</mes:td>
                        <mes:td width="30">ɾ��</mes:td>
                    </mes:tr>
                </mes:thead>
                <mes:tbody iterator="<%=list.iterator()%>" type="th.ps.bean.CarData" varName="cd">
                    <mes:tr id = "tr${cd.id}">
                        <mes:td><%=serialNumber++%></mes:td>
                        <mes:td>${cd.ccarno}</mes:td>
                        <mes:td>${cd.dwbegin}</mes:td>
                        <mes:td>${cd.dabegin}</mes:td>
                        <mes:td align="center">${cd.cenabled == "1" ? "<span style='color:green;'>��</span>" : "<span style='color:gray;'>��</span>"}</mes:td>
                        <mes:td>${cd.cremark}</mes:td>
                        <mes:td>
                            <a href="javascript:window.location.href='spec_kincode_modify.jsp?intId=${cd.id}'">����</a>
                        </mes:td>
                        <mes:td>
                            <a href="javascript:if(confirm('���Ҫɾ��������¼��')){ window.location.href='spec_kincode_del.jsp?kinCode=${cd.ccarno}'; }">ɾ��</a>
                        </mes:td>
                    </mes:tr>
                </mes:tbody>
                <mes:tfoot>
                   <mes:tr>
                     <mes:td colspan="100%" align="center">
                         <form  name="form" style="margin: 0px"  method="get" onSubmit="return checkinput(this)" action="spec_kincode_search.jsp">
                              <a href="spec_kincode_add.jsp">������¼�¼��</a>
                         </form>
                      </mes:td>
                   </mes:tr>  
                </mes:tfoot> 
            </mes:table>
            <!-- InstanceBeginEditable name="����" -->
            <form  name="form1" method="get" style="margin: 0px" action="spec_kincode_search.jsp">
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
                        �ؼ���Ϊ��<a class="div_red">KIN ��װ˳���</a><br>
                        ����Ϊ�յ�ʱ���ѯ������Ϣ����ѯ������<a class="div_red">"," </a>�ֿ���<br>
                    </div>
            </div>
            <!-- InstanceEndEditable -->
        </div>
    </body>
    <script type="text/javascript">
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