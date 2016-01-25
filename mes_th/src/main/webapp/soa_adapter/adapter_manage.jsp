<%@ page language="java" import="java.sql.*"
	contentType="text/html;charset=gb2312"%>
<jsp:directive.page import="java.util.List" />
<jsp:directive.page import="java.util.ArrayList" />
<jsp:directive.page import="mes.beans.Adapter" />
<jsp:useBean id="Conn" scope="page" class="com.qm.mes.th.helper.Conn_MES" />
<%@ page import="mes.framework.*"%>
<%@ page import="mes.framework.forjsp.soa.*"%>
<%@ page import="mes.framework.dao.IDAO_Core"%>
<%@ page import="mes.framework.dao.DAOFactory_Core"%>
<%@taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<%@page import="mes.ra.util.*"%>
<%	response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
<%
	/* 
	 * ʱ�䣺2007-7-2
	 * ���ߣ�������
	 * ҵ��������������������ҳ��
	 */

    response.setHeader("progma","no-cache");
	response.setHeader("Cache-Control","no-cache");
    Connection conn=null;
    Statement stmt=null;
    ResultSet rs=null;
    IDAO_Core dao=null;
    DataServer_SOA ds=null;
    String eid = null;
	eid=request.getParameter("eid");
	ArrayList<String> colist = new ArrayList<String>(3);
	int PageCount=0; //��ҳ��
    try
    {
		//��ȡ����
	   	conn=Conn.getConn();
	   	dao=DAOFactory_Core.getInstance(DataBaseType.getDataBaseType(conn));;
	   	ds=new DataServer_SOA(conn);
		stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		int PageSize; //һҳ��ʾ�ļ�¼��
		int RowCount; //��¼����
		int intPage=0; //����ʾҳ��
		String strPage;	
		int i=0;
		PageSize=10;
		//��ô���ʾ��ĳһҳ��
		strPage=request.getParameter("page");
		if(strPage==null|| strPage.equals(""))
		{    intPage=1;
		}
		else
		{    intPage=Integer.parseInt(strPage);
		    if(intPage<1) intPage =1;
		} 
		String info=request.getParameter("info");
		if(info==null)
		     info="";
		String sql="";
		//�ؼ��ֲ�ѯ��
		if(info==null||info.equals("")){
				sql=dao. getSQL_AllAdapterInfos();
				sql = "select * from (" + sql +") a ";
		}
		else{					
			//ѡ��Ҫ��ѯ���ֶ�			
			colist.add("ciparameter".toLowerCase());
			colist.add("cprocessname".toLowerCase());	
			colist.add("cservername".toLowerCase());				
			info=info.trim();			  
				sql=dao. getSQL_AllAdapterInfos();
			//ģ����ѯ		
		   LinkQuery link=new LinkQuery();		    
		   StringBuffer wh= link.linkquery(info,colist,sql,conn);
		   sql = "select * from (" + sql +") a where " + wh.toString(); 
			// sql = dao.getStateByName(info);		     
		}			   
	    sql=sql+" order by nprocessid,cialiasname";
		List<Adapter> list=new ArrayList<Adapter>();
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
				Adapter adapter=new Adapter();
				adapter.setProcessid(rs.getString(1));
				adapter.setServeraliasname(rs.getString(2));
				adapter.setParametername(rs.getString(3));
				
				adapter.setProcessname(ds.getProcessName_ProcessID(adapter.getProcessid()));
				if(adapter.getProcessname()==null)adapter.setProcessname("");
				adapter.setServerid(ds.getServerID_Processid_ServerAlias(adapter.getProcessid(),adapter.getServeraliasname()));
				if(adapter.getServerid()==null)adapter.setServerid("");
				if(adapter.getServerid()!=null&&!adapter.getServerid().trim().equals("")) adapter.setServername(ds.getServerName_ServerID(adapter.getServerid()));
				if(adapter.getServername()==null)adapter.setServername("");
				list.add(adapter);
			i++;
	   		rs.next();
	      }
	}
	%>
<html>
	<head>
		<style>
.head2{	height:22px;color:ffff00;}
</style>
		<link rel="stylesheet" type="text/css" href="../cssfile/style.css">

		<meta http-equiv="Content-Language" content="zh-cn" />

	</head>
	<body background="../images/background.jpg">
		<!-- ����ͨ�ýű� -->
		<script type="text/javascript"
			src="../JarResource/META-INF/tag/taglib_common.js"></script>
		<div align="center">
			<strong>����������</strong>
		</div>
		<br>
		<div align="center">
			<mes:table reSourceURL="../JarResource/" id="table1">
				<mes:thead>
					<mes:tr>
						<td width="140">
							���̺�-��
						</td>
						<td width="400">
							�����-����������
						</td>
						<td width="120">
							����Ĳ�����
						</td>
						<td width="53">
							�鿴
						</td>
						<td width="53">
							����
						</td>
						<td width="53">
							ɾ��
						</td>
					</mes:tr>
				</mes:thead>
				<mes:tbody iterator="<%=list.iterator() %>" type="mes.beans.Adapter"
					varName="adapter">
					<mes:tr id="tr${adapter.processid}-${adapter.serverid}-${adapter.parametername}">

						<mes:td>${adapter.processid}-${adapter.processname}</mes:td>
						<mes:td>${adapter.serverid}-${adapter.servername}(${adapter.serveraliasname})</mes:td>
						<mes:td>${adapter.parametername}</mes:td>
						<mes:td>
							<a
								href='adapter_look.jsp?processid=${adapter.processid}&eid=${adapter.processid}-${adapter.serverid}-${adapter.parametername}&intPage=<%=intPage %>&info=<%=info%>'>�鿴</a>
						</mes:td>
						<mes:td>
							<a
								href="adapter_update.jsp?processid=${adapter.processid}&aliasname=${adapter.serveraliasname}&parameter=${adapter.parametername}&eid=${adapter.processid}-${adapter.serverid}-${adapter.parametername}&intPage=<%=intPage %>&info=<%=info%>">����</a>
						</mes:td>
						<mes:td>
							<a href="#" title="�����ɾ��������¼��"
								onClick="javascript:if(confirm('���Ҫɾ��������¼��')) window.location.href='adapter_deleting.jsp?processid=${adapter.processid}&eid=${adapter.processid}-${adapter.serverid}-${adapter.parametername}&aliasname=${adapter.serveraliasname}&parameter=${adapter.parametername}&intPage=<%=intPage %>&info=<%=info%>';return false;">ɾ��</a>
						</mes:td>
					</mes:tr>
				</mes:tbody>
				<mes:tfoot>
					<mes:tr>
						<mes:td colspan="100%" align="center">
							<form name="form" style="margin: 0px" method="post"
								onSubmit="return checkinput(this)" action="adapter_manage.jsp">
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
		out.println("<a href=\"?page=1&info="+info+"\">��һҳ</a>");	
		out.println("<a href=\"?page="+(intPage-1)+"&info="+info+"\">��һҳ</a>");		
		}%>
								<%if(intPage<PageCount){
		out.println("<a href=\"?page="+(intPage+1)+"&info="+info+"\">��һҳ</a>");
		out.println("<a href=\"?page="+(PageCount)+"&info="+info+"\">���һҳ</a>");
		}%>

								<a href='adapter_add.jsp?intPage=<%=intPage %>&eid=${adapter.processid}-${adapter.serverid}-${adapter.parametername}&info=<%=info%>'>������¼�¼��</a>
							</form>
						</mes:td>
					</mes:tr>
				</mes:tfoot>
			</mes:table>
           <form  name="form1" method="post"  style="margin: 0px" action="adapter_manage.jsp">
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
				�ؼ��֣�����������������������
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
		if(<%=PageCount%>>1)
		if(!re.test(document.getElementsByName("page")[0].value ))
     {
	    alert("��תҳ��Ӧ�����������!");
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
