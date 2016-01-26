<%@ page contentType="text/html;charset=GBK" language="java" pageEncoding="GBK"%>
<%@ taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.qm.th.helper.Conn_MES"%>
<%@ page import="com.qm.th.bs.bean.SpecKin"%>

<% 
	/** ��ҳ�治���� */
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	
	/** �޸���ĿID */
    String eid = request.getParameter("eid");
	/** ��ô���ʾ��ĳһҳ�� */
    String strPage = request.getParameter("page");
	
    int PageSize = 5; //һҳ��ʾ�ļ�¼��
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
	
	List<SpecKin> list = new ArrayList<SpecKin>();
	
	try{
		StringBuilder strSql  = new StringBuilder();
		strSql.append(" SELECT A.ID, A.CVINCODE, A.CCARNO, A.DWBEGIN, A.DABEGIN ");
		strSql.append(" FROM CARDATA A INNER JOIN SPECIALKIN B ");
		strSql.append(" ON A.CCARNO = B.CCARNO AND DATEDIFF(DAY, B.DTODATE, GETDATE()) < 0 ");
		
		conn = new Conn_MES().getConn();
		stmt = conn.prepareStatement(strSql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
            	SpecKin sk = new SpecKin();
            	sk.setId(rs.getString("ID"));
    			sk.setKin(rs.getString("CCARNO"));
    			sk.setVin(rs.getString("CVINCODE"));
    			sk.setDwbegin(rs.getString("DWBEGIN"));
    			sk.setDabegin(rs.getString("DABEGIN"));
    			
    			list.add(sk);
               record_count++;
               rs.next();
            }
        }
	}catch(Exception e){
		e.printStackTrace();
	}finally{
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
        <title>����KIN����ʾ</title>
        <meta http-equiv=content-type content="text/html;charset=GBK">
        <link rel="stylesheet" type="text/css" href="../../cssfile/style.css">
        <link rel="stylesheet" type="text/css" href="../../cssfile/th_style.css">
        <script type="text/javascript" src="../../JarResource/META-INF/tag/taglib_common.js"></script>
    </head>
    <body>
        <div align="center">
            <div class="title"><strong><!-- InstanceBeginEditable name="����" -->����KIN����ʾ<!-- InstanceEndEditable --></strong></div>
            <mes:table reSourceURL="../../JarResource/" id="spec_states">
                <mes:thead>
                    <mes:tr>
                        <mes:td width="30">���</mes:td>
                        <mes:td width="120">KIN��</mes:td>
                        <mes:td width="120">VIN��</mes:td>
                        <mes:td width="160">��װ����ʱ��</mes:td>
                        <mes:td width="160">��װ����ʱ��</mes:td>
                    </mes:tr>
                </mes:thead>
                <mes:tbody iterator="<%=list.iterator()%>" type="th.bs.bean.SpecKin" varName="sk">
                    <mes:tr id = "tr${sk.id}">
                        <mes:td><%=serialNumber++ + (intPage - 1)*PageSize%></mes:td>
                        <mes:td>${sk.kin}</mes:td>
                        <mes:td>${sk.vin}</mes:td>
                        <mes:td>${sk.dwbegin}</mes:td>
                        <mes:td>${sk.dabegin}</mes:td>
                    </mes:tr>
                </mes:tbody>
                <mes:tfoot>
                   <mes:tr>
                     <mes:td colspan="100%" align="center">
                         <form id="tform" name="form" style="margin: 0px"  method="post" onSubmit="return checkinput(this)" action="specKin.jsp">
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
           if(eid !=- 1 && document.getElementById('tr'+eid) != null){
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
        * ˢ��ҳ��
        */
       var refresh = function(){
       		setInterval(function(){
       			var pagenumger = "<%=intPage%>";
       			var eid = '<%=eid==null?"-1":eid%>';
       			window.location.href = "specKin.jsp?page=" + pagenumger + "&eid=" + eid;
       		}, 10 * 1000);
       }();
    </script>
</html>