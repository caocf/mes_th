<%@page language="java" import="java.sql.*"
	contentType="text/html;charset=gb2312"%>
<jsp:directive.page import="java.util.List" />
<jsp:directive.page import="java.util.ArrayList" />
<jsp:directive.page import="mes.beans.Users" />
<jsp:useBean id="Conn" scope="page" class="com.qm.mes.th.helper.Conn_MES" />
<%@page import="mes.framework.dao.*"%>
<%@page import="mes.framework.*"%>
<%@taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<jsp:include flush="true" page="security.jsp" />
<%@page import="mes.ra.util.*"%>
<%
 response.setHeader("progma","no-cache");
 response.setHeader("Cache-Control","no-cache");
	int PageSize; //һҳ��ʾ�ļ�¼��
	int RowCount; //��¼����
	int PageCount; //��ҳ��
	int intPage; //����ʾҳ��
	String strPage;
	int i=0;
	String eid = null;
	eid=request.getParameter("eid");
	PageSize=10;
	//��ô���ʾ��ĳһҳ��
	strPage=request.getParameter("page");
	ArrayList<String> colist = new ArrayList<String>(2);
	if(strPage==null|| strPage.equals(""))
	{    intPage=1;
	}
	else
	{    intPage=Integer.parseInt(strPage);
	    if(intPage<1) intPage =1;
	} 
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
	IDAO_UserManager sqlDao = DAOFactory_UserManager.getInstance(DataBaseType.getDataBaseType(conn));
	stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	//���ڷ��ص�select����еĲ��������������� where roleno='?' ���ԣ���֮�����ò���ʱ��Ҫͨ��setInt������������setString
	//String tmp_sql = sqlDao.getSQL_QueryRoleForRoleNo("?").replace("'?'","?");
	//stmt2 = conn.prepareStatement(tmp_sql);
	String sql="";
	//��ѯ��
	 if(info==null||info.equals("")){
			sql=sqlDao.getSQL_AllUserRoleNameNoForRank();
			 sql = "select * from (" + sql +") a";
		}
		else{					
			//ѡ��Ҫ��ѯ���ֶ�			
			colist.add("nusrno".toLowerCase());
			colist.add("cusrname".toLowerCase());					
			info=info.trim();			  
			sql=sqlDao.getSQL_AllUserRoleNameNoForRank();
			//ģ����ѯ		
		   LinkQuery link=new LinkQuery();		    
		   StringBuffer wh= link.linkquery(info,colist,sql,conn);
		   sql = "select * from (" + sql +") a where " + wh.toString(); 		  	     
		}	 	
		sql=sql+ " order by nusrno";
	List<Users>list=new ArrayList<Users>();	
	rs=stmt.executeQuery(sql);
	rs.last();
	 
	//����ܼ�¼��
	RowCount=rs.getRow();
	
	//��ҳ��
	PageCount = (RowCount+PageSize-1) / PageSize;
	if(intPage>PageCount) intPage = PageCount;
	if(PageCount>0)
	{
	    //����¼ָ�붨λ����һ��ʾλ��
	    rs.absolute((intPage-1)*PageSize + 1);
	    i = 0;
	    while(i<PageSize && !rs.isAfterLast())
	    {
			Users u=new Users();
			u.setUserno(rs.getString(1));
			u.setUsername(rs.getString(2));
			u.setUser_enabled(rs.getString(3));
			list.add(u);
			i++;
			rs.next();
		}
	}
%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="../cssfile/style.css">
		<meta http-equiv="Content-Language" content="zh-cn" />
		<style>
.head2{	height:22px;color:ffff00;
}
</style>

	</head>
	<body background="../images/background.jpg">
		<!-- ����ͨ�ýű� -->
		<script type="text/javascript"
			src="../JarResource/META-INF/tag/taglib_common.js"></script>
		<div align="center">
			<strong>�û�����</strong>
		</div>
		<br>
		<div align="center">
			<mes:table reSourceURL="../JarResource/" id="table1">
				<mes:thead>
					<mes:tr>
						<td width="83">
							�û�����
						</td>
						<td width="380">
							�û�����
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
				<mes:tbody iterator="<%=list.iterator() %>" type="mes.beans.Users"
					varName="users">
					<mes:tr id="tr${users.userno}">
						<mes:td>${users.userno }</mes:td>
						<mes:td>${users.username }</mes:td>
						<mes:td>
							<a href="user_look.jsp?id=${users.userno }&intPage=<%=intPage %>&info=<%=info%>">�鿴</a>
						</mes:td>
						<mes:td>
							<a
								href="user_update.jsp?id=${users.userno }&intPage=<%=intPage %>&info=<%=info%>">����</a>
						</mes:td>
						<mes:td>
							<%		
		Users ima = (Users)pageContext.getAttribute("users");
		if(ima.getUser_enabled().equals("1"))
		{
%>
							<a href="#" title="�����ɾ��������¼��"
								onClick="javascript:if(confirm('���Ҫɾ��������¼��')) window.location.href='user_delete.jsp?id=${users.userno }&intPage=<%=intPage %>&info=<%=info%>';return false;">ɾ��</a>
							<%
		}
		else
		{
			out.write("--");
		}
%>
						</mes:td>

					</mes:tr>
				</mes:tbody>
				<mes:tfoot>
					<mes:tr>
						<mes:td colspan="100%" align="center">

							<form name="form" style="margin: 0px" method="post"
								onSubmit="return checkinput(this)" action="user_view.jsp">
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
		out.println("<a href=\"user_view.jsp?page=1&info="+info+"\">��һҳ</a>");	
		out.println("<a href=\"user_view.jsp?page="+(intPage-1)+"&info="+info+"\">��һҳ</a>");		
		}%>
								<%if(intPage<PageCount){
		out.println("<a href=\"user_view.jsp?page="+(intPage+1)+"&info="+info+"\">��һҳ</a>");
		out.println("<a href=\"user_view.jsp?page="+(PageCount)+"&info="+info+"\">���һҳ</a>");
		}%>

								<a href='user_add.jsp?intPage=<%=intPage %>&info=<%=info%>'>������¼�¼��</a>
							</form>
						</mes:td>
					</mes:tr>
				</mes:tfoot>
			</mes:table>
			<form name="form1" method="get" style="margin: 0px"
				action="user_view.jsp">
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
		</div>
	</body>
</html>
