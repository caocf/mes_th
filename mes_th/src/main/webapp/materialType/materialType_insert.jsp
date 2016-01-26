<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*,com.qm.mes.system.elements.*" errorPage="" %>
<html>
<%@taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<%@page import="com.qm.mes.framework.*"%>
<%@page import="com.qm.mes.system.dao.*" %>
<%@page import="com.qm.mes.system.factory.*" %>
<%	response.setHeader("Pragma","No-cache");  
   	response.setHeader("Cache-Control","no-cache");  
  	response.setDateHeader("Expires", 0); %>
<jsp:useBean id="Conn" scope="page" class="com.qm.th.helper.Conn_MES"/>
<% Connection con=null;
    Statement stmt=null;
    ResultSet rs=null;
    try{
    con = Conn.getConn();
    stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
    IMaterialTypeFactory factory;
    factory = (IMaterialTypeFactory) FactoryAdapter.getFactoryInstance(IMaterialTypeFactory.class.getName());
    IDAO_MaterialType dao = (IDAO_MaterialType) DAOFactoryAdapter.getInstance(DataBaseType.getDataBaseType(con),IDAO_MaterialType.class);
    String sqlType = dao.getSQL_queryElementAll();
    java.util.HashMap<Comparable,String> map = new java.util.HashMap<Comparable,String>();
    map.put("","-1");
	rs=stmt.executeQuery(sqlType);
    while(rs.next()){ 
	   IMaterialType materialtype = factory.createElement();
       materialtype.setId(rs.getInt(3));
	   materialtype.setName(rs.getString(2));
	   map.put(materialtype.getName(),String.valueOf(materialtype.getId()));
    }
%>
 
<head>
<link rel="stylesheet" type="text/css" href="../cssfile/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>��������</title>
<script type="text/javascript" src="../JarResource/META-INF/tag/taglib_common.js"></script>
</head>
<div class="title">
		�����������
</div>
<body>

<div align="center"><br>
<form  name="form1"  action="materialType_adding.jsp" method="get" onSubmit="return checkinput()">
    <table class="tbnoborder">
      <tr>
        <td width="119">������������</td>
        <td width="266">
        <mes:inputbox name="element_name" size="36" id="element_name" maxlength="40" reSourceURL="../JarResource/" colorstyle="box_black" />
        </td>
      </tr>
	  <tr>
        <td width="119">���������ͣ�</td>
        <td width="266">
         <mes:selectbox colorstyle="box_black" id="selectbox1" name="parent_id" map="<%=map%>" reSourceURL="../JarResource/" size="36" maxlength="30" readonly="false" selectSize="6" value=""/>
        </td>
      </tr>
	  <tr>
        <td width="119">������Ϣ��</td>
        <td width="266">
        <mes:inputbox name="description" size="36" id="description" reSourceURL="../JarResource" colorstyle="box_black"/>
        </td>
      </tr>
    </table>
    <br>
    <table width="384" class="tbnoborder">
      <tr>
        <td class="tdnoborder" width="123" ><span class="btn2_mouseout" onMouseOver="this.className='btn2_mouseover'"
	 onMouseOut="this.className='btn2_mouseout'" >
          <input class="btn2"  type="submit" name="Submit" value="�ύ"/>
        </span></td>
        <td  class="tdnoborder" width="126" ><span class="btn2_mouseout" onMouseOver="this.className='btn2_mouseover'"
	 onMouseOut="this.className='btn2_mouseout'" >
          <input class="btn2"  type="reset" name="button"  onClick="resetPara()" value="����"/>
        </span></td>
        <td class="tdnoborder" width="119" ><span class="btn2_mouseout" onMouseOver="this.className='btn2_mouseover'"
	 onMouseOut="this.className='btn2_mouseout'" >
          <input class="btn2"  type="button" name="button2"  onClick="window.location.href='materialType_view.jsp'" value="����"/>
        </span></td>
      </tr>
    </table>
	</form>
</div>
</body>

<%
    //�ͷ���Դ
	if(rs!=null)rs.close();
	if(stmt!=null)stmt.close();
	if(con!=null)con.close();
	}catch(Exception e)
	{
		if(rs!=null)rs.close();
		if(stmt!=null)stmt.close();
		if(con!=null)con .close();
		throw e;
	}
 %>
<script type="text/javascript">
function checkinput(){
var re = /^[a-zA-Z0-9\u4e00-\u9fa5]+$/;
if(form1.element_name.value=="")
	{
		alert("����Ϊ��");
		return false;
	}else if(!re.test(document.getElementById("element_name").value))
    {
	    alert("����������Ӧ����ĸ�����ֺͺ������!");
	    return false;
	}else
		return true;

}
function resetPara(){
	document.getElementsByName("element_name")[0].focus();
}
</script>
</html>