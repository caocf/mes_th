<%@ page language="java" import="java.sql.*"
	contentType="text/html;charset=gb2312"%>
<%@page import="com.qm.th.tg.dao.*,com.qm.th.tg.factory.*"%>
<%@page import="com.qm.th.tg.bean.*,com.qm.mes.ra.util.*"%>
<%@page import="java.util.*,java.text.SimpleDateFormat"%>
<%@page import="com.qm.mes.framework.*,com.qm.mes.framework.dao.*,com.qm.mes.system.dao.*"%>

<%@taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<html>
	<!-- InstanceBegin template="/Templates/new_view.dwt.jsp" codeOutsideHTMLIsLocked="true" -->

	<!-- InstanceBeginEditable name="�������" -->
	<jsp:directive.page import="java.util.List" />
	<jsp:directive.page import="java.util.ArrayList" />
	<jsp:useBean id="Conn" scope="page" class="com.qm.th.helper.Conn_MES" />
	<script language="JavaScript" type="text/javascript"
		src="../My97DatePicker/WdatePicker.js"></script>
	<!-- InstanceEndEditable -->
	<!-- InstanceBeginEditable name="��ù���" -->
	<%@page
		import="org.apache.commons.logging.Log,org.apache.commons.logging.LogFactory"%>
	<%
		final Log log = LogFactory.getLog("history_assembly.jsp");
		response.setHeader("progma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		Connection con = null;
		WeldingSearchFactory factory_ws = new WeldingSearchFactory();//��װ��ѯ����
		List<AssemblySearch> list_as = new ArrayList<AssemblySearch>();//��װ��ѯ����
		List<Welding_Stat> list_ws = new ArrayList<Welding_Stat>();//��װͳ�Ƽ���
		SearchSetFactory factory_ss = new SearchSetFactory();//��ѯ���ù���
		SearchSet ss = new SearchSet();//��ѯ���ö���
		String condition_One = null;//����һ
		String condition_Two = null;//������
		String condition_Three = null;//������
		int radio_value = 0;//��ѡ��ťֵ
		int searchsetid = 0;//��ѯ�������
		String sql_temp1 = "";
		String sql_temp2 = "";
		String sql_as = "";
		String sql_ws = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			//��ȡ����
			con = Conn.getConn();
			searchsetid = Integer.parseInt(request
					.getParameter("searchsetid"));
			ss = factory_ss.getSearchSetById(searchsetid, con);
			radio_value = request.getParameter("radio1") == null ? 1
					: Integer.parseInt(request.getParameter("radio1"));
			log.debug("RADIOѡ�" + radio_value);
			String sql = null;
			DAO_WeldingSearch dao = new DAO_WeldingSearch();
			switch (radio_value) {
			case 1:
				condition_One = request.getParameter("d11") == null ? "n"
						: request.getParameter("d11");
				sql_temp1 = " where (dabegin>(select max(dabegin) dabegin from history_cardata where cseqno_a='"
						+ condition_One
						+ "') "
						+ " or (dabegin=(select max(dabegin) dabegin from history_cardata where cseqno_a='"
						+ condition_One
						+ "')) "
						+ "and cseqno_a='"
						+ condition_One + "')";

				break;
			case 2:
				condition_One = request.getParameter("startvin") == "" ? "n"
						: request.getParameter("startvin");
				condition_Two = request.getParameter("endvin") == "" ? "n"
						: request.getParameter("endvin");
				condition_Three = request.getParameter("cartype") == "" ? "n"
						: request.getParameter("cartype");
				sql_temp1 = " where substring(cVinCode,12,6)>='"
						+ condition_One
						+ "' and substring(cVinCode,12,6)<='"
						+ condition_Two + "'"
						+ " and substring(cVinCode,7,2)='"
						+ condition_Three + "' and dABegin is not null ";
				break;
			}
			/*if(radio_value==1&&request.getParameter("d11")==null||
			radio_value==1&&request.getParameter("d11").equals("")||
			radio_value==2&&request.getParameter("startvin")==null&&request.getParameter("endvin")==null||
			radio_value==2&&request.getParameter("startvin").equals("")&&request.getParameter("endvin").equals("")){
				sql_temp1 = " where dABegin is not null and DateDiff(day,dABegin,getdate())<40";
				
			}*/
			sql_temp1 = sql_temp1 + " and substring(cCarNo,6,1) in ("
					+ ss.getCcarType() + ") ";
			sql_temp1 = sql_temp1 + " and substring(cSEQNo_A,1,2)="
					+ ss.getCfactory() + " ";
			//sql = dao.getACarsByCondition(sql_temp1);

			if (radio_value == 1 && request.getParameter("d11") == null
					|| radio_value == 1
					&& request.getParameter("d11").equals("")
					|| radio_value == 2
					&& request.getParameter("startvin") == null
					&& request.getParameter("endvin") == null
					|| radio_value == 2
					&& request.getParameter("startvin").equals("")
					&& request.getParameter("endvin").equals("")) {

			} else {
				list_as = factory_ws.getHistoryACarsByCondition(sql_temp1,
						con);
				sql_as = dao.getHistoryACarsByCondition(sql_temp1);
				list_ws = factory_ws.getHistoryAStatByCondition(sql_temp1,
						con);
				sql_ws = dao.getHistoryAStatByCondition(sql_temp1);
			}
	%>
	<head>
		<link rel="stylesheet" type="text/css" href="../cssfile/style.css">
		<!-- InstanceBeginEditable name="����" -->
		<title>��ѯ����</title>
		<!-- InstanceEndEditable -->
		<meta http-equiv="Content-Language" content="zh-cn">
		<style>
.head2 {
	height: 22px;
	color: ffff00;
}
</style>
		<!-- InstanceBeginEditable name="head" -->
		<!-- InstanceEndEditable -->
	</head>

	<body background="../images/background.jpg">
		<!-- ����ͨ�ýű� -->
		<script type="text/javascript"
			src="../JarResource/META-INF/tag/taglib_common.js"></script>

		<div class="title">
			<strong>
				<!-- InstanceBeginEditable name="����2" --><%=ss.getCsearchName()%><!-- InstanceEndEditable -->
			</strong>
		</div>
		<br>
		<div align="center">
			<!-- InstanceBeginEditable name="����1" -->

			<form action="history_assembly.jsp">
				<table>
					<tr>
						<td>
							<input type="radio" name="radio1" value="1">
							��ʼ˳���
							<input id="d11" name="d11" size="6" maxlength="6" type="text"
								value="<%=radio_value != 1
								|| condition_One.equals("n") ? ""
								: condition_One%>" />
						</td>
					</tr>
					<tr>
						<td>
							<input type="radio" name="radio1" value="2">
							��ʼ���̺�(����λ)
							<input name="startvin" size="6" maxlength="6"
								value="<%=radio_value != 2
								|| condition_One.equals("n") ? ""
								: condition_One%>">
							--�������̺�(����λ)
							<input name="endvin" size="6" maxlength="6"
								value="<%=radio_value != 2
								|| condition_Two.equals("n") ? ""
								: condition_Two%>">
							���ͣ�
							<input name="cartype" size="2" maxlength="2"
								value="<%=radio_value != 2
								|| condition_Three.equals("n") ? ""
								: condition_Three%>">
							<mes:button id="s1" reSourceURL="../JarResource/" submit="true"
								value="��ѯ" />
						</td>
					</tr>
				</table>
				<input type="hidden" name="searchsetid" value="<%=searchsetid%>">
				<mes:button id="s2" reSourceURL="../JarResource/" value="������ѯ"
					onclick="xls_select()" />
				<mes:button id="s3" reSourceURL="../JarResource/" value="����ͳ��"
					onclick="xls_stat()" />
			</form>
		</div>
		<div align="center">
			<style>
td {
	border-width: 1pt;
	border-style: solid
}
</style>
			<style>
tr {
	border-width: 1pt;
	border-style: solid
}
</style>
			<p></p>
			<p></p>
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="center" width="60">
						˳���
					</td>
					<td align="center" width="150">
						VIN
					</td>
					<td align="center" width="90">
						������
					</td>
					<td align="center" width="160">
						��װ����
					</td>
					<td align="center" width="160">
						��װ����
					</td>
					<td align="center" width="160">
						CP6����
					</td>
				</tr>
				<%
					for (int i = 0; i < list_as.size(); i++) {
				%>
				<tr>
					<td align="center"><%=list_as.get(i).getSeq() == null ? "-"
							: list_as.get(i).getSeq()%></td>
					<td align="center"><%=list_as.get(i).getVin() == null ? "-"
							: list_as.get(i).getVin()%></td>
					<td align="center"><%=list_as.get(i).getKin() == null ? "-"
							: list_as.get(i).getKin()%></td>
					<td align="center"><%=list_as.get(i).getDWBegin() == null ? "-"
							: sdf
									.format(sdf.parse(list_as.get(i)
											.getDWBegin()))%></td>
					<td align="center"><%=list_as.get(i).getDABegin() == null ? "-"
							: sdf
									.format(sdf.parse(list_as.get(i)
											.getDABegin()))%></td>
					<td align="center"><%=list_as.get(i).getDCp6Begin() == null ? "-"
							: sdf.format(sdf.parse(list_as.get(i)
									.getDCp6Begin()))%></td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
		<br>
		<div align="center">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="center">
						�����
					</td>
					<td align="center">
						����
					</td>
				</tr>
				<%
					for (int i = 0; i < list_ws.size(); i++) {
				%>
				<tr>
					<td align="center"><%=list_ws.get(i).getNo()%></td>
					<td align="center"><%=list_ws.get(i).getNum()%></td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
	</body>

	<%
		//�ͷ���Դ

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null)
				con.close();
		}
	%>
	<!-- InstanceBeginEditable name="script" -->
	<script type="text/javascript">
function checkinput(thisform){
        var re =  /^[0-9]+$/;
		var i=0;
		var qm;
		var mm = document.getElementsByName("method");
		
}
function check_radio(){
	document.getElementsByName("radio1")[<%=radio_value - 1%>].checked = true;
}check_radio();
function xls_select(){
	window.location.href="assembly_select_xls.jsp?sql=<%=sql_as%>&downloadname=��װ��ѯ";
}
function xls_stat(){
	window.location.href="assembly_select_xls.jsp?sql=<%=sql_ws%>&downloadname=��װͳ��";
}
</script>
	<!-- InstanceEndEditable -->
	<!-- InstanceEnd -->
</html>