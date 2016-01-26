<%@ page language="java" import="java.sql.*"
	contentType="text/html;charset=gb2312"%>
<html>
	<!-- InstanceBegin template="/Templates/����ҳ��ģ��1.dwt.jsp" codeOutsideHTMLIsLocked="true" -->
	<!-- InstanceBeginEditable name="������Ӷ���" -->
	<jsp:directive.page import="java.util.List" />
	<jsp:directive.page import="java.util.ArrayList" />
	<jsp:directive.page import="com.qm.mes.beans.Role" />
	<jsp:useBean id="Conn" scope="page" class="com.qm.th.helper.Conn_MES" />
	<%@page import="com.qm.mes.framework.dao.*,com.qm.mes.framework.DataBaseType"%>
	<%@taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
	<%@page import="com.qm.mes.ra.util.*"%>
	<!-- InstanceEndEditable -->
	<%	response.setHeader("progma","no-cache");
	response.setHeader("Cache-Control","no-cache");%>
	<!-- InstanceBeginEditable name="����SQL���" -->
	<% String eid = null;//ҳ���ѡ����
	eid=request.getParameter("eid");
	ArrayList<String> colist = new ArrayList<String>(2);
    String user_rolerank="";
	user_rolerank=(String)session.getAttribute("user_rolerank");	
	if(user_rolerank==null)
		user_rolerank="";
	
	String info="";
	info=request.getParameter("info");
	if(info==null)
		info="";		
	Connection conn=null;
	Statement stmt=null;
	ResultSet rs=null;
	try{
    conn=Conn.getConn();
	stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	String enabled="";
	String sql="";    
	IDAO_UserManager dao = DAOFactory_UserManager.getInstance(DataBaseType.getDataBaseType(conn));
	//��ѯ
	if(info==null||info.equals("")){
			sql= dao.getSQL_AllRoleNameNoForRank(user_rolerank);
			sql = "select * from (" + sql +") a";
		}
		else{					
			//ѡ��Ҫ��ѯ���ֶ�			
			colist.add("nroleno".toLowerCase());
			colist.add("crolename".toLowerCase());					
			info=info.trim();			  
			sql= dao.getSQL_AllRoleNameNoForRank(user_rolerank);
			//ģ����ѯ		
		   LinkQuery link=new LinkQuery();		    
		   StringBuffer wh= link.linkquery(info,colist,sql,conn);
		   sql = "select * from (" + sql +") a where " + wh.toString(); 		  	     
		}	 	
		sql=sql+" order by nroleno";
	
	List<Role> list=new ArrayList<Role>();
	rs=stmt.executeQuery(sql);
	rs.last();
    
	int PageSize; //һҳ��ʾ�ļ�¼��
	int RowCount; //��¼����
	int PageCount; //��ҳ��
	int intPage; //����ʾҳ��
	String strPage;
	int i=0;
	PageSize=10;
	//��ô���ʾ��ĳһҳ��
	strPage=request.getParameter("page");
	if(strPage==null)
		intPage=1;
	else{
		intPage=Integer.parseInt(strPage);
		if(intPage<1) intPage =1;
	} 
   		%>
	<!-- InstanceEndEditable -->
	<%
		//����ܼ�¼��
		RowCount=rs.getRow();
		//��ҳ��
		PageCount = (RowCount+PageSize-1) / PageSize;
		if(intPage>PageCount) intPage = PageCount;

		if(PageCount>0){
		    rs.absolute((intPage-1)*PageSize + 1);//����¼ָ�붨λ����һ��ʾλ��
		    i = 0;
		    while(i<PageSize && !rs.isAfterLast()){%>
	<!-- InstanceBeginEditable name="�����������" -->
	<%
				Role role=new Role();
				role.setRole_no(rs.getString(1));
				role.setRole_name(rs.getString(2));
				role.setCenabled(rs.getString(3));
				enabled=role.getCenabled();
				list.add(role);
			    i++;
			    rs.next();
			}
		}
		%>
	<head>
		<link rel="stylesheet" type="text/css" href="../cssfile/style.css">
		<meta http-equiv="Content-Language" content="zh-cn" />
		<style>
.head2{	height:22px;color:ffff00;
}
</style>
		<!-- InstanceBeginEditable name="����1" -->
		<title>��ɫ����</title>
		<!-- InstanceEndEditable -->
	</head>
	<body background="../images/background.jpg">
		<!-- ����ͨ�ýű� -->
		<script type="text/javascript"
			src="../JarResource/META-INF/tag/taglib_common.js"></script>
		<div align="center">
			<strong>��ɫ����</strong>
		</div>
		<br>
		<div align="center">
			<mes:table reSourceURL="../JarResource/" id="table1">
				<!-- InstanceBeginEditable name="��ͷ" -->
				<mes:thead>
					<mes:tr>
						<td width="83">
							��ɫ��
						</td>
						<td width="380">
							��ɫ��
						</td>
						<td width="60">
							�鿴
						</td>
						<td width="60">
							����
						</td>
						<td width="60">
							ɾ��
						</td>
					</mes:tr>
				</mes:thead>
				<mes:tbody iterator="<%=list.iterator() %>" type="mes.beans.Role"
					varName="role">
					<mes:tr id="tr${role.role_no}">
						<%if(list.equals("")){%>
						<mes:td colspan="100%" align="center">�޲�ѯ���</mes:td>
						<%}else{
			  %>
						<!-- InstanceEndEditable   output.toString() -->

						<mes:td>${role.role_no}</mes:td>
						<mes:td>${role.role_name}</mes:td>
						<mes:td>
							<a
								href='role_look.jsp?roleno=${role.role_no }&intPage=<%=intPage %>&info=<%=info%>'>�鿴</a>
						</mes:td>
						<mes:td>
							<a
								href='role_update.jsp?roleno=${role.role_no }&intPage=<%=intPage %>&info=<%=info%>'>����</a>
						</mes:td>
						<mes:td>
							<% if(enabled.equals("1")){%>
							<a href="#" title="�����ɾ��������¼��"
								onClick="javascript:if(confirm('���Ҫɾ��������¼��?')) window.location.href='role_deleteing.jsp?roleno=${role.role_no }&intPage=<%=intPage %>&info=<%=info%>';return false;">ɾ��</a>
							<%}else{
					out.write("-");
				}
		       %>
						</mes:td>
						<%} %>

					</mes:tr>
				</mes:tbody>
				<mes:tfoot>
					<mes:tr>
						<mes:td colspan="100%" align="center">
							<form name="form" style="margin: 0px" method="get"
								onSubmit="return checkinput(this)" action="role_manage.jsp">
								<input type="hidden" value="<%=info%>" name="info">

								��
								<%=RowCount%>
								�� ��
								<%=intPage%>
								ҳ ��
								<%=PageCount%>
								ҳ
								<%if(PageCount>1){
		 out.println("��ת����");
		 out.print("<input size=\"1\"  name=\"page\" value=");%>
								<%=intPage%>
								<%out.println(">");
		 out.println("ҳ");
		 }%>
								<%if(intPage>1){    
			out.println("<a href=\"#\" onclick=\"location='?page=1&info="+info+"'\">��һҳ</a>");	
			out.println("<a href=\"#\" onclick=\"location='?page="+(intPage-1)+"&info="+info+"'\">��һҳ</a>");		
		}%>
								<%if(intPage<PageCount){
			out.println("<a href=\"#\" onclick=\"location='?page="+(intPage+1)+"&info="+info+"'\">��һҳ</a>");
			out.println("<a href=\"#\" onclick=\"location='?page="+PageCount+"&info="+info+"'\">���һҳ</a>");
		}%>
								<!-- InstanceBeginEditable name="����¼�¼" -->
								<a href='role_add.jsp?intPage=<%=intPage %>&info=<%=info%>'>������¼�¼��</a>
								<!-- InstanceEndEditable -->
							</form>
						</mes:td>
					</mes:tr>
				</mes:tfoot>
			</mes:table>
			<!-- InstanceBeginEditable name="����" -->
			<form name="form1" method="post" style="margin: 0px"
				action="role_manage.jsp">
				<table class="tbnoborder">
					<tr>
						<td class="tbnoborder">
							�ؼ��ֲ�ѯ��&nbsp;&nbsp;
						</td>
						<td class="tdnoborder">
							<input type=text value="<%=info%>" name="info" id="info"
								class="box1" size=10 onMouseOver="this.className='box3'"
								onFocus="this.className='box3'"
								onMouseOut="this.className='box1'"
								onBlur="this.className='box1'">
						</td>
						<td class="tdnoborder">
							<mes:button id="s1" reSourceURL="../JarResource/" submit="true"
								value="��ѯ" />
						</td>
					</tr>
				</table>
			</form>
			<div style="color:ff0000;font-size:9pt">
				����Ϊ�յ�ʱ���ѯ������Ϣ����ѯ������"," �ֿ���
			</div>		
			<script>
	function checkinput(thisform){
		var re =  /^[0-9]+$/;
		var i=0;
		var qm;
		var mm = document.getElementsByName("method");
		for(i=0;i<mm.length;i++)
			if(mm[i].checked==true)
				qm = mm[i].value;
				
		if((qm=="ById")&&(isNaN(thisform.info.value)==true)){
			alert('����ֻ�����������֣�');
			thisform.info.value="";
			thisform.info.focus();
			return false;
		}
		if(<%=PageCount%>>1)
		if(!re.test(document.getElementsByName("page")[0].value ))
     {  alert("��תҳ��Ӧ�����������!");
	    document.getElementsByName("page")[0].value="";
	    return false;
    }	
		 return true;	    
	}
	function selecttr(){
		var eid = '<%=eid==null?"-1":eid%>';		
		if(eid!=-1){
			document.getElementById('tr'+eid).click();
		}		
	}selecttr();
</script>

			<!-- InstanceEndEditable -->
		</div>
	</body>
	<!-- InstanceEnd -->
</html>
<%
		//�ͷ���Դ
	
		}catch(Exception e)
		{		
			e.printStackTrace();
		}finally {			
			if(stmt!=null)stmt.close();
			if(conn!=null)conn .close();
			}
		
	%>
