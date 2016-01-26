
<%@ page language="java" import="java.sql.*"
	contentType="text/html;charset=gb2312"%>
<jsp:directive.page import="java.util.List" />
<jsp:directive.page import="java.util.ArrayList" />
<jsp:directive.page import="com.qm.mes.beans.ProcessService" />
<%@page import="com.qm.mes.framework.*"%>
<%@page import="com.qm.mes.framework.dao.*"%>
<jsp:useBean id="Conn" scope="page" class="com.qm.th.helper.Conn_MES" />
<%@taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<%@page import="com.qm.mes.ra.util.*"%>

<%
	response.setHeader("progma","no-cache");
	response.setHeader("Cache-Control","no-cache");
    
	String process_info="";	
    String eid = null;
	eid=request.getParameter("eid");
	System.out.println(eid);
    Connection conn=null;
    Statement stmt=null;
    ResultSet rs=null;
    String sql="";
    List<ProcessService> list=new ArrayList<ProcessService>();
    ArrayList<String> colist = new ArrayList<String>(3);
   int PageSize; //һҳ��ʾ�ļ�¼��
		int RowCount; //��¼����
		int PageCount=0; //��ҳ��
		int intPage; //����ʾҳ��
	process_info=request.getParameter("process_info");
	if (process_info==null)
		process_info="";

    try
    {		//��ȡ����
	   	conn=Conn.getConn();
		stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		
		String strPage;
		int i=0;
		PageSize=10;
		//��ô���ʾ��ĳһҳ��
		strPage=request.getParameter("page");				
		IDAO_Core sqlDao = DAOFactory_Core.getInstance(DataBaseType.getDataBaseType(conn));	
		if(strPage==null|| strPage.equals(""))
		{    intPage=1;
		}
		else
		{    intPage=Integer.parseInt(strPage);
		    if(intPage<1) intPage =1;
		} 
	
		//String sql="select a.nprocessid,a.nsid,b.cprocessname,c.cservername from process_servers a,process_statement b,server_statement c where a.nprocessid=b.nprocessid and a.nserverid=c.nserverid "+sqlWhere+" order by a.nprocessid,a.nsid";
		//String sql="select nprocessid,nsid ,nserverid,nactid from process_servers order by nprocessid,nsid";
		
		if(process_info==null||process_info.equals("")){
			sql=sqlDao.getSQL_AllProcessServerInfo();	
			sql = "select * from (" + sql +") a";	
		}
		else{				
				
			//ѡ��Ҫ��ѯ���ֶ�			
			colist.add("nprocessid".toLowerCase());
			colist.add("cprocessname".toLowerCase());
			colist.add("cservername".toLowerCase());			
			process_info=process_info.trim();			  
			sql=sqlDao.getSQL_AllProcessServerInfo();
			//ģ����ѯ		
		   LinkQuery link=new LinkQuery();		    
		   StringBuffer wh= link.linkquery(process_info,colist,sql,conn);
		   sql = "select * from (" + sql +") a where " + wh.toString(); 
			// sql = dao.getStateByName(info);
		     
		}			
		sql=sql+" order by nprocessid,nsid";
		
        
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
				ProcessService prs=new ProcessService();
				prs.setProcessid(rs.getString(1));
				prs.setSid(rs.getString(2));
				prs.setProcessname(rs.getString(3));
				prs.setServername(rs.getString(4));
				list.add(prs);
			    i++;
	   		    rs.next();
	      }
	}
	%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="../cssfile/style.css">
		<meta http-equiv="Content-Language" content="zh-cn" />
		<script>
function checkform()
{
	if(isNaN(searchform.processid.value))
	{
		alert("����Ĳ������֣����������֣�")
		return false
	}
	if(searchform.processid.value=="")
	{
		alert("����������!")
		return false
	}
	return true
	
}
</script>
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
			<strong>���̷������</strong>
		</div>
		<br>
		<div align="center">
			<mes:table reSourceURL="../JarResource/" id="table1">
				<mes:thead>
					<mes:tr>
						<td width="42">
							���̺�
						</td>
						<td width="61">
							�������
						</td>
						<td width="150">
							������
						</td>
						<td width="300">
							������
						</td>
						<td width="40">
							�鿴
						</td>
						<td width="40">
							����
						</td>
						<td width="42">
							ɾ��
						</td>
					</mes:tr>
				</mes:thead>
				<mes:tbody iterator="<%=list.iterator() %>"
					type="mes.bean.ProcessService" varName="processService">
					<mes:tr id="tr${processService.processid}-${processService.sid}">
						<mes:td>${processService.processid}</mes:td>
						<mes:td>${processService.sid}</mes:td>
						<mes:td>${processService.processname}</mes:td>
						<mes:td>${processService.servername}</mes:td>
						<mes:td>
							<a
								href='process_servers_look.jsp?processid=${processService.processid }&eid=${processService.processid}-${processService.sid}&sid=${processService.sid }&intPage=<%=intPage %>&process_info=<%=process_info%>'>�鿴</a>
						</mes:td>
						<mes:td>
							<a
								href='process_servers_modify.jsp?processid=${processService.processid }&eid=${processService.processid}-${processService.sid}&sid=${processService.sid }&intPage=<%=intPage %>&process_info=<%=process_info%>'>����</a>
						</mes:td>
						<mes:td>
							<a href="#" title="�����ɾ��������¼��"
								onClick="javascript:if(confirm('���Ҫɾ��������¼��')) window.location.href='process_servers_deleting.jsp?processid=${processService.processid }&eid=${processService.processid}-${processService.sid}&sid=${processService.sid }&intPage=<%=intPage %>&process_info=<%=process_info%>';return false;">ɾ��</a>
						</mes:td>
					</mes:tr>
				</mes:tbody>
				<mes:tfoot>
					<mes:tr>
						<mes:td colspan="100%" align="center">
							<form name="form" style="margin: 0px" method="post"
								onSubmit="return checkinput(this)"
								action="process_servers_view.jsp">
								<input type="hidden" value="<%=process_info%>" name="process_info">
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
		out.println("<a href=\"process_servers_view.jsp?page=1&process_info="+process_info+"\">��һҳ</a>");	
		out.println("<a href=\"process_servers_view.jsp?page="+(intPage-1)+"&process_info="+process_info+"\">��һҳ</a>");		
		}%>
								<%if(intPage<PageCount){
		out.println("<a href=\"process_servers_view.jsp?page="+(intPage+1)+"&process_info="+process_info+"\">��һҳ</a>");
		out.println("<a href=\"process_servers_view.jsp?page="+(PageCount)+"&process_info="+process_info+"\">���һҳ</a>");
		}%>

								<a href='process_servers_input.jsp?intPage=<%=intPage %>&eid=${processService.processid}-${processService.sid}&process_info=<%=process_info%>'>������¼�¼��</a>
							</form>
						</mes:td>
					</mes:tr>
				</mes:tfoot>
			</mes:table>
			<form name="form1" method="post" style="margin: 0px"
				action="process_servers_view.jsp">
				<table class="tbnoborder">
					<tr>
					   <td class="tbnoborder">
							�ؼ��ֲ�ѯ��&nbsp;&nbsp;
						</td>						
						<td class="tdnoborder">
							<input type=text value="<%=process_info %>" name="process_info" id="process_info"
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
				�ؼ���Ϊ�����̺š���������������
			</div>
			<div style="color:ff0000;font-size:9pt">
				����Ϊ�յ�ʱ���ѯ������Ϣ����ѯ������"," �ֿ���
			</div>
		</div>

	</body>

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
	<script type="text/javascript">
function checkinput(thisform){
        var re =  /^[0-9]+$/;
		var i=0;
		var qm;
		var mm = document.getElementsByName("method");
		for(i=0;i<mm.length;i++)
			if(mm[i].checked==true)
				qm = mm[i].value;
				
		if((qm=="ById")&&(isNaN(thisform.process_info.value)==true)){
			alert('����ֻ�����������֣�');
			thisform.process_info.value="";
			thisform.process_info.focus();
			return false;
		}
		if(<%=PageCount%>>1)
		if(!re.test(document.getElementsByName("page")[0].value ))
    {	    alert("��תҳ��Ӧ�����������!");
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
</html>
