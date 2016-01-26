<%@ page language="java" import="java.sql.*"
	contentType="text/html;charset=gb2312"%>
<jsp:directive.page import="java.util.List" />
<jsp:directive.page import="java.util.ArrayList" />
<jsp:directive.page import="com.qm.mes.beans.ServicePara" />
<jsp:useBean id="Conn" scope="page" class="com.qm.th.helpers.Conn_MES" />
<%@ page import="com.qm.mes.framework.dao.*"%>
<%@ page import="com.qm.mes.framework.DataBaseType"%>
<%@taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<%@page import="com.qm.mes.ra.util.*"%>
<%
		response.setHeader("Pragma","No-cache");  
		response.setHeader("Cache-Control","no-cache");  
		response.setDateHeader("Expires", 0);
%>
<%      	
		int PageSize; //һҳ��ʾ�ļ�¼��
		int RowCount; //��¼����
		int PageCount=0; //��ҳ��
		int intPage; //����ʾҳ��
		String strPage;
		int i=0;
		PageSize=10;
		String eid = null;
		ArrayList<String> colist = new ArrayList<String>(3);
	    eid=request.getParameter("eid");
		//��ô���ʾ��ĳһҳ��		
		strPage=request.getParameter("page");
		if(strPage==null|| strPage.equals(""))
		{	    intPage=1;
		}
		else
		{    intPage=Integer.parseInt(strPage);
		    if(intPage<1) intPage =1;
		} 
		String info="";
	    info=request.getParameter("info");
	    if(info==null)
			info="";
		
		Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
		String sql = "";

    try
    {
		//��ȡ����
	   	conn=Conn.getConn();
		stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		IDAO_Core dao = DAOFactory_Core.getInstance(DataBaseType.getDataBaseType(conn));
		
		//�ؼ��ֲ�ѯ��
		if(info==null||info.equals("")){
			sql = dao.getSQL_AllServiceParas();
			sql = "select * from (" + sql +") a";
		}
		else{				
				
			//ѡ��Ҫ��ѯ���ֶ�			
			colist.add("nserverid".toLowerCase());
			colist.add("cSERVERNAME".toLowerCase());	
			colist.add("CPARAMETER".toLowerCase());			
			info=info.trim();			  
			sql = dao.getSQL_AllServiceParas();;	
			//ģ����ѯ		
		   LinkQuery link=new LinkQuery();		    
		   StringBuffer wh= link.linkquery(info,colist,sql,conn);
		   sql = "select * from (" + sql +") a where " + wh.toString(); 
			// sql = dao.getStateByName(info);		     
		}			       
		sql=sql+"  order by nserverid";
        List<ServicePara> list=new ArrayList<ServicePara>();
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
				ServicePara sp=new ServicePara();
				sp.setServiceid(rs.getString(1));
				sp.setServicename(rs.getString(2));
				sp.setParaname(rs.getString(3));
				sp.setParatype(rs.getString(4));
				list.add(sp);
			i++;
	   		rs.next();
	      }
	}
	%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="../cssfile/style.css">
		<meta http-equiv="Content-Language" content="zh-cn">
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
			<strong>�����������</strong>
		</div>
		<br>
		<div align="center">
			<mes:table reSourceURL="../JarResource/" id="table1">
				<mes:thead>
					<mes:tr>
						<td width="83">
							�����
						</td>
						<td width="180">
							������
						</td>
						<td width="180">
							������
						</td>
						<td width="80">
							��������
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
				<mes:tbody iterator="<%=list.iterator() %>"
					type="mes.beans.ServicePara" varName="spara">
					<mes:tr id="tr${spara.serviceid}-${spara.paraname}">
						<mes:td>${spara.serviceid}</mes:td>
						<mes:td>${spara.servicename}</mes:td>
						<mes:td>${spara.paraname}</mes:td>
						<mes:td>${spara.paratype}</mes:td>
						<mes:td>
							<a
								href='servicePara_look.jsp?serviceid=${spara.serviceid }&eid=${spara.serviceid}-${spara.paraname}&paratype=${spara.paratype}&paraname=${spara.paraname}&servicename=${spara.servicename}&intPage=<%=intPage %>&info=<%=info%>'>�鿴</a>
						</mes:td>
						<mes:td>
							<a
								href='servicePara_update.jsp?serviceid=${spara.serviceid }&eid=${spara.serviceid}-${spara.paraname}&setparatype=${spara.paratype}&setparaname=${spara.paraname }&servicename=${spara.servicename}&intPage=<%=intPage %>&info=<%=info%>'>����</a>
						</mes:td>
						<mes:td>
							<a href="#"
								onclick="javascript:if(confirm('���Ҫɾ��������¼��?')) window.location.href='servicePara_deleting.jsp?serviceid=${spara.serviceid }&eid=${spara.serviceid}-${spara.paraname}&setparatype=${spara.paratype}&setparaname=${spara.paraname }&intPage=<%=intPage %>&info=<%=info%>';return false;">ɾ��</a>
						</mes:td>
					</mes:tr>
				</mes:tbody>
				<mes:tfoot>
					<mes:tr>
						<mes:td colspan="100%" align="center">
							<form name="form" style="margin: 0px" method="post"
								onSubmit="return checkinput(this)"
								action="servicePara_manage.jsp">
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
		out.println("<a href=\"servicePara_manage.jsp?page=1&info="+info+"\">��һҳ</a>");	
		out.println("<a href=\"servicePara_manage.jsp?page="+(intPage-1)+"&info="+info+"\">��һҳ</a>");		
		}%>
								<%if(intPage<PageCount){
		out.println("<a href=\"servicePara_manage.jsp?page="+(intPage+1)+"&info="+info+"\">��һҳ</a>");
		out.println("<a href=\"servicePara_manage.jsp?page="+(PageCount)+"&info="+info+"\">���һҳ</a>");
		}%>

								<a href='servicePara_add.jsp?intPage=<%=intPage %>&eid=${spara.serviceid}-${spara.paraname}&info=<%=info%>'>������¼�¼��</a>
							</form>
						</mes:td>
					</mes:tr>
				</mes:tfoot>
			</mes:table>
			<form name="form1" method="post" style="margin: 0px"
				action="servicePara_manage.jsp">
				<table class="tbnoborder">
					<tr>
					   <td class="tbnoborder">
							�ؼ��ֲ�ѯ��&nbsp;&nbsp;
						</td>						
						<td class="tdnoborder">
							<input type=text value="<%=info %>" name="info" id="info"
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
				�ؼ���Ϊ������š���������������
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
	<script>
	function checkinput(thisform){
		var i=0;
		var re =  /^[0-9]+$/;
		var qm;
		var mm = document.getElementsByName("method");
		for(i=0;i<mm.length;i++)
			if(mm[i].checked==true)
				qm = mm[i].value;
				
		if((qm=="ByParas")&&(isNaN(thisform.info.value)==true)){
			if( (paras.indexOf("\\") != -1)||(paras.indexOf("\'") != -1))
		{
			alert("�ı����������˷Ƿ��ַ�������������");
			thisform.info.value="";
			thisform.info.focus();
		}
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
</html>
