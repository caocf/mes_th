<%@ page language="java" import="java.sql.*"
	contentType="text/html;charset=gb2312"%>
<jsp:directive.page import="java.util.List" />
<jsp:directive.page import="java.util.ArrayList" />
<jsp:directive.page import="mes.beans.Process" />
<jsp:useBean id="Conn" scope="page" class="common.Conn_MES" />
<%@page import="mes.framework.*"%>
<%@page import="mes.framework.dao.*"%>
<%@taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<%@page import="mes.ra.util.*"%>

<%
	response.setHeader("progma","no-cache");
    response.setHeader("Cache-Control","no-cache");
    
    Connection conn=null;
    Statement stmt=null;
    ResultSet rs=null;
    String eid = null;
    List<Process> list = new ArrayList<Process>();
	eid=request.getParameter("eid");
    String process_info="";
	process_info=request.getParameter("process_info");
	if(process_info==null)
	process_info="";
	ArrayList<String> colist = new ArrayList<String>(2);
	Process tmp = null;	    
	int PageCount=0; //总页数
		
    try
    {	//获取连接
	   	conn=Conn.getConn();
		stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		int PageSize=10; //一页显示的记录数
		int RowCount=0; //记录总数
		int intPage=1; //待显示页码
		String strPage;
		int i=0;
		String sql=null;
		PageSize=10;
		//获得待显示的某一页数
		strPage=request.getParameter("page");				
		if(strPage==null|| strPage.equals(""))
		{	    intPage=1;		     
		}
		else
		{	  intPage=Integer.parseInt(strPage);
		    if(intPage<1) intPage =1;
		} 			
	  //查询用	
	   IDAO_Core dao = DAOFactory_Core.getInstance(DataBaseType.getDataBaseType(conn));	    	    
		if(process_info==null||process_info.equals("")){
			sql = dao.getSQL_QueryAllProcess();	
			sql = "select * from (" + sql +") a";		
		}
		else{				
				
			//选择要查询的字段			
			colist.add("nprocessid".toLowerCase());
			colist.add("cprocessname".toLowerCase());			
			process_info=process_info.trim();			  
			sql = dao.getSQL_QueryAllProcess();
			//模糊查询		
		   LinkQuery link=new LinkQuery();		    
		   StringBuffer wh= link.linkquery(process_info,colist,sql,conn);
		   sql = "select * from (" + sql +") a where " + wh.toString(); 
			// sql = dao.getStateByName(info);		     
		}			
		sql=sql+" order by nprocessid asc";
	   rs=stmt.executeQuery(sql);
		rs.last();
		//获得总记录数
		RowCount=rs.getRow();
		
		//总页数
		PageCount = (RowCount+PageSize-1) / PageSize;
		if(intPage>PageCount) intPage = PageCount;
		if(PageCount>0)
		{		
		    //将记录指针定位到下一显示位置
		    rs.absolute((intPage-1)*PageSize + 1);
		    i = 0;
		    while(i<PageSize && !rs.isAfterLast())
		    {	tmp = new Process();	
				tmp.setId(rs.getString("NPROCESSID"));
		    	tmp.setName(rs.getString("CPROCESSNAME"));
		    	tmp.setNamespaceid(rs.getString(4)==null?"null":rs.getString(4));
		    	list.add(tmp);
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
		&nbsp;
		<script type="text/javascript"
			src="../JarResource/META-INF/tag/taglib_common.js"></script>

		<div align="center">
			<strong>流程定义浏览</strong>
		</div>
		<br>
		<div align="center">
			<mes:table reSourceURL="../JarResource/" id="table1">
				<mes:thead>

					<mes:tr>
						<td width="83">
							流程号
						</td>
						<td width="380">
							流程名
						</td>
						<td width="60">
							查看
						</td>
						<td width="60">
							更改
						</td>
						<td width="60">
							删除
						</td>

					</mes:tr>
				</mes:thead>
				<mes:tbody iterator="<%=list.iterator()%>" type="mes.beans.Process"
					varName="process">
					<mes:tr id="tr${process.id}">

						<mes:td>${process.id }</mes:td>
						<mes:td>${process.name }</mes:td>
						<mes:td>
							<a
								href='process_statement_look.jsp?processid=${process.id }&nnamespaceid=${process.namespaceid }&intPage=<%=intPage %>&process_info=<%=process_info%>'>查看</a>
						</mes:td>
						<mes:td>
							<a
								href='process_statement_modify.jsp?processid=${process.id }&nnamespaceid=${process.namespaceid }&intPage=<%=intPage %>&process_info=<%=process_info%>'>更改</a>
						</mes:td>
						<mes:td>
							<a href="#" title="点击将删除这条记录！"
								onClick="javascript:if(confirm('真的要删除这条记录吗？')) window.location.href='process_statement_deleting.jsp?processid=${process.id }&intPage=<%=intPage %>&process_info=<%=process_info%>';return false;">删除</a>
						</mes:td>


					</mes:tr>
				</mes:tbody>
				<mes:tfoot>
					<mes:tr>
						<mes:td colspan="100%" align="center">
							<form name="form" style="margin: 0px" method="post"
								onSubmit="return checkinput(this)"
								action="process_statement_view.jsp">
								<input type="hidden" value="<%=process_info%>" name="process_info">
								共
								<%=RowCount%>
								条 第
								<%=intPage%>
								页 共
								<%=PageCount%>
								页
								<%if(PageCount>1){
		out.println("跳转到第");
		out.print("<input size=\"1\"  name=\"page\" value=");%>
								<%=intPage%>
								<%out.println(">");
		out.println("页");
		}%>
								<%if(intPage>1){    
		out.println("<a href=\"process_statement_view.jsp?page=1&process_info="+process_info+"\">第一页</a>");	
		out.println("<a href=\"process_statement_view.jsp?page="+(intPage-1)+"&process_info="+process_info+"\">上一页</a>");		
		}%>
								<%if(intPage<PageCount){
		out.println("<a href=\"process_statement_view.jsp?page="+(intPage+1)+"&process_info="+process_info+"\">下一页</a>");
		out.println("<a href=\"process_statement_view.jsp?page="+(PageCount)+"&process_info="+process_info+"\">最后一页</a>");
		}%>

								<a href='process_statement_input.jsp?intPage=<%=intPage %>&process_info=<%=process_info%>'>【添加新记录】</a>
							</form>
						</mes:td>
					</mes:tr>
				</mes:tfoot>
			</mes:table>

			<form name="form1" method="post" style="margin: 0px"
				action="process_statement_view.jsp">
				<table class="tbnoborder">
					<tr>
					   <td class="tbnoborder">
							关键字查询：&nbsp;&nbsp;
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
								value="查询" />
						</td>						
					</tr>
				</table>
			</form>
			<div style="color:ff0000;font-size:9pt">
				参数为空的时候查询所有信息，查询条件以"," 分开。
			</div>
		</div>

	</body>

	<%
		//释放资源
	
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
			alert('代码只允许输入数字！');
			thisform.process_info.value="";
			thisform.process_info.focus();
			return false;
		}
		if(<%=PageCount%>>1)
		if(!re.test(document.getElementsByName("page")[0].value ))
    {
	    alert("跳转页数应由正整数组成!");
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
