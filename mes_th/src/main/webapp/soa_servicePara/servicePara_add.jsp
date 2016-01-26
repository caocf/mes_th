<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<jsp:useBean id="Conn" scope="page" class="com.qm.th.helper.Conn_MES"/>
<%@taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<%@ page import="com.qm.mes.framework.dao.*"%>
<%@ page import="com.qm.mes.framework.DataBaseType"%>
<%    
	response.setHeader("Pragma","No-cache");  
   	response.setHeader("Cache-Control","no-cache");  
  	response.setDateHeader("Expires", 0); 
    String intpage=request.getParameter("intPage");
    String info = request.getParameter("info");
		info = info==null?"":info;	   
    Connection cn = null;
	Statement stmt= null;
	ResultSet rs= null;
	int serviceid=0;
	String servicename="";
	String sql="";
	String output = "";
	
	try{	
    	cn = Conn.getConn();
		if(cn==null)
			out.println("���ݿ�����ʧ��.");
		else
		{
			stmt=cn.createStatement();
			IDAO_Core dao = DAOFactory_Core.getInstance(DataBaseType.getDataBaseType(cn));
			sql=dao.getSQL_QueryAllServices("","");
			rs=stmt.executeQuery(sql);
			 java.util.HashMap<Comparable,String> map = new java.util.HashMap<Comparable,String>();
			while(rs.next())
			{
				serviceid=rs.getInt(1);
				servicename=rs.getString(2);
				map.put(servicename,String.valueOf(serviceid));
				output += "<option value=\""+serviceid+"\">"+serviceid+":"+ servicename+"</option>";
				
			}
%>



<html>
<head>
<link rel="stylesheet" type="text/css" href="../cssfile/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>�����������</title>
<script type="text/javascript" src="../JarResource/META-INF/tag/taglib_common.js"></script>
</head>
<body>
<div class="title">��ӷ��������Ϣ</div>
<br>
<div align="center">
<form id="form1" name="form1" method="post" action="servicePara_adding.jsp" onSubmit="return checkinput()">
  <table width="700" class="tdnoborder">
    <tr>
      <td width="133">������:</td>
      <td width="210">
      <mes:selectbox colorstyle="box_black" id="selectbox1" name="serviceid" map="<%=map%>" reSourceURL="../JarResource/" size="36" maxlength="30" readonly="false" selectSize="6" value=""/>
      </td>
      <td width="166"><a href="javascript:addParas();">��Ӳ���</a></td>
      <td width="168"><a href="javascript:deleteParas();">ɾ������</a></td>
    </tr>
    <tr>
      <td colspan="100%"><div id="parameters" ></div></td>
    </tr>
	</table>
	<br>
	<table width="320" class="tbnoborder">
	<input type = "hidden" name="intpage" value="<%=intpage%>">
    <tr>
      <td class="tdnoborder"  colspan="2">
      <!-- 
      <span class="btn2_mouseout" onMouseOver="this.className='btn2_mouseover'" onMouseOut="this.className='btn2_mouseout'">
        <input class="btn2" type="submit" name="Submit" value="ȷ��" />
      </span>
      -->
      <mes:button id="button1" reSourceURL="../JarResource/" submit="true" value="ȷ��"/>
        </td>
      <td class="tdnoborder"  colspan="2">
      <!-- 
      <span class="btn2_mouseout" onMouseOver="this.className='btn2_mouseover'" onMouseOut="this.className='btn2_mouseout'">
        <input class="btn2" type="button" name="button"  onclick="resetPara()" value="����" />
      </span>
      -->
      <mes:button id="button2" reSourceURL="../JarResource/" submit="false" onclick="resetPara()" value="����"/>
      </td>
	  <td width="101" colspan="2" class ="tdnoborder">
	  <!-- 
	  <span class="btn2_mouseout" onMouseOver="this.className='btn2_mouseover'"	 onMouseOut="this.className='btn2_mouseout'">
        <input class="btn2" type="button" name="button"  onclick="window.location.href='servicePara_manage.jsp'" value="����" />
      </span>
      -->
      <mes:button id="button3" reSourceURL="../JarResource/" onclick = "func()" value="����"/>
      </td>
    </tr>
  </table>
  <input type="hidden" name="paracount"/>
</form>
</div>
</body>

<script type="text/javascript">
var para_count=0;
function addParas(){
	para_count++;
	document.getElementById("parameters").innerHTML+='<table width="100%" align=center class="tdnoborder"><tr><td>��������<input type="text"  name="para'+para_count+'" class="box1" onMouseOver="this.className=\'box3\'" onFocus="this.className=\'box3\'" onMouseOut="this.className=\'box1\'" onBlur="this.className=\'box1\'"/></td><td>&nbsp;�������ͣ�<span class="boxOut"><span class="boxIn"><select name="para'+para_count+'_type" ><option value="I" selected>����</option><option value="O">���</option></select></span></span>&nbsp;&nbsp;</td><td>����������<input type="textaera" name="para'+para_count+'_desc"  class="box1" onMouseOver="this.className=\'box3\'" onFocus="this.className=\'box3\'" onMouseOut="this.className=\'box1\'" onBlur="this.className=\'box1\'"/></td></tr>';
	document.getElementsByName("paracount")[0].value=para_count;
}

function deleteParas(){
	if(para_count==0)alert("����������Ϊ��");
	else
	{
		var q = document.getElementById("parameters");
		q.removeChild(q.children(q.children.length-1));
		para_count--;
		document.getElementsByName("paracount")[0].value=para_count;
	}
}

function checkinput(){
  if(para_count==0)
  {
  	alert("����Ӳ���");
	return false;
  }
  else
  {
		var i = 0 , j = 0;
		for(i=1;i<=para_count;i++){
			var v = document.getElementsByName("para"+i)[0].value;
			if(v=="")
			{
				alert("����Ϊ��");
				return false;
			}
			else{
				var t = document.getElementsByName("para"+i+"_type")[0].value;
				for(j=i+1;j<=para_count;j++){
					if((v==document.getElementsByName("para"+j)[0].value)&&(t==document.getElementsByName("para"+j+"_type")[0].value))
					{
					   alert("���������");
						return false;
					}
				}
			}
			
		}
 	}
}

function resetPara()
{
	var i;
	for(i=1;i<=para_count;i++)
	{
		document.getElementsByName("para"+i)[0].value="";
		document.getElementsByName("para"+i+"_type")[0].value="I";
		document.getElementsByName("para"+i+"_desc")[0].value="";
	}
	document.getElementsByName("serviceid")[0].focus();
}

function func(){
		var pageIndex = <%=intpage%>;
		window.location.href = "servicePara_manage.jsp?page=" + pageIndex+ '&info=<%=info%>';
			}
</script>
</html>
<%
	}
	}catch(Exception e)
	{
		out.println("��ѯʧ��"+e);
		throw e;
	}finally{
		//�ͷ���Դ
		if(rs!=null)rs.close();
		if(stmt!=null)stmt.close();
		if(cn!=null)cn.close();
	}
%>