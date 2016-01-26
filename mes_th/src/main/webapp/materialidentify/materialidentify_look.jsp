<%@ page language="java" import="java.sql.*" contentType="text/html;charset=gb2312"%>
<%@page import="com.qm.mes.system.factory.*" %>
<%@page import="com.qm.mes.system.elements.*" %>
<html>
 <jsp:useBean id="Conn" scope="page" class="com.qm.th.helper.Conn_MES"/>
 <%
 	String element_name = request.getParameter("element_name");
	if(element_name==null){
		out.println("<script>alert(\"����Ϊ��\"); window.location.href=\"materialidentify_view.jsp\";</script>");
		return;
	} %>
	<%
	Connection conn=null;
	Statement stmt=null;
  	try{//��ȡ����
		conn=Conn.getConn();
		stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		IMaterialidentifyFactory factory;
		factory = (IMaterialidentifyFactory) FactoryAdapter.getFactoryInstance(IMaterialidentifyFactory.class.getName());
		%>
		<%
		IMaterialidentify midentify=factory.queryElement(new Integer(element_name),conn);
        %>
		  <%
		if(midentify==null){
			out.println("<script>alert(\"û�в�ѯ�����\");window.location.href='materialidentify_view.jsp';</script>");
		}
		%>
	<head>
		<link rel="stylesheet" type="text/css" href="../cssfile/style.css">
		<title>����������Ϣ</title>
	</head>
	<body>
	<div class="title">�鿴����������Ϣ</div>
	<br>
	<div align="center">
    <table class="tbnoborder">
      <tr>
        <td width="180">Ԫ�غ� - ���ϱ�ʶ����</td>
        <td width="266"><input readonly size="50" type="text" value="<%=midentify.getId()%> - <%=midentify.getName() %>" class="box1" onMouseOver="this.className='box3'" onFocus="this.className='box3'" onMouseOut="this.className='box1'" onBlur="this.className='box1'">
        </td>
      </tr>
       <tr>
        <td>������Ϣ��</td>
        <td><input readonly size="50" type="text" value="<%=midentify.getDescription()==null?"":midentify.getDescription() %>" class="box1" onMouseOver="this.className='box3'" onFocus="this.className='box3'" onMouseOut="this.className='box1'" onBlur="this.className='box1'">
        </td>
      </tr>
       <tr>
        <td>�޸�ʱ�䣺</td>
        <td><input readonly size="50" type="text" value="<%=midentify.getUpdateDateTime() %>" class="box1" onMouseOver="this.className='box3'" onFocus="this.className='box3'" onMouseOut="this.className='box1'" onBlur="this.className='box1'">
        </td>
      </tr>
       <tr>
        <td>�����û��ţ�</td>
        <td><input readonly size="50" type="text" value="<%=midentify.getUpdateUserId() %>" class="box1" onMouseOver="this.className='box3'" onFocus="this.className='box3'" onMouseOut="this.className='box1'" onBlur="this.className='box1'">
        </td>
      </tr>
       <tr>
        <td>���ϱ�ʶ���ȣ�</td>
        <td><input readonly size="50" type="text" value="<%=midentify.getCodeLength() %>" class="box1" onMouseOver="this.className='box3'" onFocus="this.className='box3'" onMouseOut="this.className='box1'" onBlur="this.className='box1'">
        </td>
      </tr>
      <tr>
        <td>�汾�ţ�</td>
        <td><input readonly size="50" type="text" value="<%=midentify.getVersion() %>" class="box1" onMouseOver="this.className='box3'" onFocus="this.className='box3'" onMouseOut="this.className='box1'" onBlur="this.className='box1'">
        </td>
      </tr>
    </table>
    <table class="tbnoborder">
      <tr>
        <td  class="tdnoborder" colspan="2"  ><span class="btn2_mouseout" onMouseOver="this.className='btn2_mouseover'"
	onMouseOut ="this.className='btn2_mouseout'">
          <input class="btn2" type="button"  value="�� ��"  onClick="history.go(-1)">
        </span></td>
      </tr>
    </table>
	</div>
</body>
</html>
<%	//�ͷ���Դ
	if(stmt!=null)stmt.close();
	if(conn!=null)conn.close();
	}catch(Exception e)
	{
		if(conn!=null)conn.close();
		throw e;
	}
%>









