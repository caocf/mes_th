<%@ page language="java" import="java.sql.*" contentType="text/html;charset=gb2312"%>
<%@page import="th.tg.dao.*,th.tg.factory.*" %>
<%@page import="th.tg.bean.*,mes.ra.util.*" %>
<%@page import="java.util.*,java.text.SimpleDateFormat" %>
<%@page	import="mes.framework.*,mes.framework.dao.*,mes.system.dao.*"%>

<%@taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<html><!-- InstanceBegin template="/Templates/new_view.dwt.jsp" codeOutsideHTMLIsLocked="true" -->

<!-- InstanceBeginEditable name="�������" -->
<jsp:directive.page import="java.util.List"/>
<jsp:directive.page import="java.util.ArrayList"/>
<jsp:useBean id="Conn" scope="page" class="com.qm.mes.th.helper.Conn_MES"/>
<!-- InstanceEndEditable -->
<!-- InstanceBeginEditable name="��ù���" -->
<%@page import="org.apache.commons.logging.Log,org.apache.commons.logging.LogFactory"%>
<%
    String info="";
	info=request.getParameter("info");
	System.out.println("info:"+info);
	if (info==null)
		info="";
%>
<!-- InstanceEndEditable -->
<%   String eid =null;
    eid= request.getParameter("eid");
    final  Log log = LogFactory.getLog("searchset_manage.jsp");
	response.setHeader("progma","no-cache");
	response.setHeader("Cache-Control","no-cache");
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;  
    
    int PageSize; //һҳ��ʾ�ļ�¼��
	int RowCount; //��¼����
	int PageCount=0; //��ҳ��
	int intPage; //����ʾҳ��
	String strPage;
	int i=0;
	PageSize=10;
	//��ô���ʾ��ĳһҳ��
	strPage=request.getParameter("page");	
	if(strPage==null)
	{
	    intPage=1;
	}
	else
	{
	    intPage=Integer.parseInt(strPage);
	    if(intPage<1) intPage =1;
	} 
	try{
	    //��ȡ����
	    conn=Conn.getConn();
		stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		String sql=null;
	%>
	<!-- InstanceBeginEditable name="����SQL���" -->
		<%
		 DAO_SearchSet dao = new DAO_SearchSet();
			
		if(info.equals("")||info==null){
			sql = dao.getAllSearchSets();
			sql = "select * from (" + sql +") a ";
		}else{
		 	
		   ArrayList<String> colist = new ArrayList<String>(5);
			colist.add("id".toLowerCase());
			colist.add("cSearchName".toLowerCase());
			colist.add("cWA".toLowerCase());
			colist.add("cFactory".toLowerCase());
			colist.add("cDscFactory".toLowerCase());
			colist.add("cCarType".toLowerCase());
			colist.add("cDscCarType".toLowerCase());
			colist.add("cRemark".toLowerCase());
			
		    info=info.trim();
		  sql = dao.getAllSearchSets();
		    LinkQuery link=new LinkQuery();
		    
		   StringBuffer wh= link.linkquery(info,colist,sql,conn);
		   sql = "select * from (" + sql +") a where " + wh.toString(); 
		}
		  sql=sql+"order by id asc";
			log.info("��ѯȫ����ѯ���ö��� ��sql���:  " + sql);
		List<SearchSet> list_SearchSet = new ArrayList<SearchSet>();
	%>
	<!-- InstanceEndEditable -->
	<%
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
		    {%>
			<!-- InstanceBeginEditable name="�����������" -->
			<%	
			  SearchSet ss = new SearchSet();
			ss.setId(rs.getInt("id"));
			ss.setCsearchName(rs.getString("cSearchName"));
			ss.setCwa(rs.getString("cWA"));
			ss.setCfactory(rs.getString("cFactory"));
			ss.setCdscFactory(rs.getString("cDscFactory"));
			ss.setCcarType(rs.getString("cCarType"));
			ss.setCdscCarType(rs.getString("cDscCarType"));
			ss.setCremark(rs.getString("cRemark"));
			list_SearchSet.add(ss);
			%>
				<!-- InstanceEndEditable -->
			<%
				i++;
		   		rs.next();
	      }
	}
	%>				
<head>
<link rel="stylesheet" type="text/css" href="../cssfile/style.css">
<!-- InstanceBeginEditable name="����" -->
		<title>��ѯ����</title>
<!-- InstanceEndEditable -->
<meta http-equiv="Content-Language" content="zh-cn">
<style>
.head2{	height:22px;color:ffff00;
}
</style>
<!-- InstanceBeginEditable name="head" -->
<!-- InstanceEndEditable -->
</head>

<body background="../images/background.jpg">
<!-- ����ͨ�ýű� -->
<script type="text/javascript" src="../JarResource/META-INF/tag/taglib_common.js"></script>

<div class="title"><strong><!-- InstanceBeginEditable name="����2" -->��ѯ���ù���<!-- InstanceEndEditable --></strong></div>
	<br>
	<div align="center"><!-- InstanceBeginEditable name="����1" -->
<mes:table reSourceURL="../JarResource/" id="table1">
	<mes:thead>

   <mes:tr>  
		<td width="30">���</td>
		<td width="150">��ѯ��������</td>
		<td width="36">����װ</td>
		<td width="58">��������</td>
		<td width="80">��������</td>
		<td width="60">���ʹ���</td>
		<td width="80">��������</td>
		<td width="80">��ע</td>
		<td width="36">�鿴</td>
		<td width="36">����</td>
		<td width="36">ɾ��</td>
				
   </mes:tr>
</mes:thead>
<mes:tbody iterator="<%=list_SearchSet.iterator()%>" type="th.tg.bean.SearchSet" varName="ss">
<mes:tr id = "tr${ss.id}"> 
<%
      //SearchSet ss = (SearchSet)pageContext.getAttribute("ss");
   %>
    
		<mes:td>${ss.id}</mes:td>
		<mes:td>${ss.csearchName}</mes:td>
		<mes:td>${ss.cwa}</mes:td>
		<mes:td>${ss.cfactory}</mes:td>
		<mes:td>${ss.cdscFactory}</mes:td>
		<mes:td>${ss.ccarType}</mes:td>
		<mes:td>${ss.cdscCarType}</mes:td>
		<mes:td>${ss.cremark}</mes:td>
		<mes:td ><a href='searchset_view.jsp?int_id=${ss.id }&intPage=<%=intPage %>&info=<%=info%>'>�鿴</a></mes:td>
		<mes:td ><a href='searchset_update.jsp?int_id=${ss.id }&intPage=<%=intPage %>&info=<%=info%>'>����</a></mes:td>
		<mes:td ><a href="#" title="�����ɾ��������¼��" onClick="javascript:if(confirm('���Ҫɾ��������¼��')) window.location.href='searchset_deleting.jsp?int_id=${ss.id }&intPage=<%=intPage %>&info=<%=info%>';return false;">ɾ��</a></mes:td>


  </mes:tr>
  </mes:tbody>
<mes:tfoot>
   <mes:tr>
     <mes:td colspan="100%" align="center">
     <form  name="form" style="margin: 0px"  method="post" onSubmit="return checkinput(this)" action="searchset_manage.jsp">
	   <input type="hidden" value="<%=info%>" name="info" >	  
	  
		��<%=RowCount%>��  ��<%=intPage%>ҳ   ��<%=PageCount%>ҳ 	
		<%if(PageCount>1){
		out.println("��ת����");
		out.print("<input size=\"1\"  name=\"page\" value=");%><%=intPage%><%out.println(">");
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

       <a href='searchset_insert.jsp?intPage=<%=intPage %>&info=<%=info%>'>������¼�¼��</a>
       </form>
	  </mes:td>
   </mes:tr>  
   </mes:tfoot>  
</mes:table>
<!-- InstanceEndEditable -->
<!-- InstanceBeginEditable name="����" -->
<form  name="form1" method="get" style="margin: 0px"  action="searchset_manage.jsp">
<table class="tbnoborder">
<tr>
<td class="tbnoborder">�ؼ��ֲ�ѯ:</td>
<td class="tdnoborder">
<input type=text value="<%=info %>" name="info" id="info" class="box1" size=20 onMouseOver="this.className='box3'" 
onFocus="this.className='box3'" onMouseOut="this.className='box1'" onBlur="this.className='box1'"></td>
<td class="tdnoborder">
<mes:button id="s1" reSourceURL="../JarResource/" submit="true" value="��ѯ"/>
</td>
</tr>
</table>
</form>
<div class="div_normal">
	�ؼ���Ϊ��<a class="div_red">11<a class="div_red">"," </a>�ֿ���<br>
<!-- InstanceEndEditable -->
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
<!-- InstanceBeginEditable name="script" -->
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
    {   alert("��תҳ��Ӧ�����������!");
	    document.getElementsByName("page")[0].value="";
	    return false;		
		}return true;
	}
	function selecttr(){
		var eid = '<%=eid==null?"-1":eid%>';
		
		if(eid!=-1){
			document.getElementById('tr'+eid).click();
		}		
	}selecttr();
</script>
<!-- InstanceEndEditable -->
<!-- InstanceEnd --></html>