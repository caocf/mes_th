<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.sql.*"%>
<%@taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<jsp:useBean id="Conn" scope="page" class="com.qm.th.helpers.Conn_MES" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String jspRq=request.getParameter("rq"); //ȡǰһҳ�����ڣ�û�и��õİ취��

//System.out.println(jspRq);


%>

<% 
                    Calendar ca = Calendar.getInstance();
					java.util.Date da = ca.getTime();
					SimpleDateFormat formt = new SimpleDateFormat("yyyy-MM-dd");
					String sj = formt.format(da);
					
					if(jspRq==null||jspRq.equals(""))
					{
						jspRq=sj;
					}
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta http-equiv=content-type content="text/html;charset=GBK">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <link rel="stylesheet" type="text/css" href="../../../cssfile/css.css">
    <link rel="stylesheet" type="text/css" href="../../cssfile/th_style.css">
	<script type="text/javascript" src="../../../JarResource/META-INF/tag/taglib_common.js"></script>		 
    <base href="<%=basePath%>">
    <title>�������ӡ</title>	
  </head>

 
  <%

     Connection con = null;
     Statement stmtInIn=null;
     Statement stmt = null;
     ResultSet rs = null;
     ResultSet rsInIn=null;
     Statement stmtIn=null;
     ResultSet rsIn=null;
     Statement fassidStmt=null;
     ResultSet fassidRs=null;
     Statement vinStmt=null; //ȡ���vin��
     ResultSet vinRs=null;
     
     String vinSql="";
     String fassidSql="";
     String sql = "";
     String sqlInIn="";
     String strSql="";
     String printId="0";
     String carTypeDesc="";
     String carType="";
     String descript="";
     String tFassName="";
     String printSetId="";
     String ch="1"; //���� ���Ѿ�ȡ��û���ô���Ϊ�����޸��Ա�������
     String js="";
     String sqlWhere="";
     String factoryNo="";
     String seq_A="";
     String auto="";
     String printRadio="";
     String fassid=""; //����ܳ�id
     String vinCode=""; //vin��
     String tempVin=""; //��ʱvin �Ƚ��Ƿ�����
     String vinType=""; //vin78λ
     String IsContinu="1"; //����
     


     int perTimeCount=0;
     int tFassCount=0;
     int pages=0;
     int partCount=0;
     int printCount=0; //��ӡ���� ��ͬ��javascript ls
     int testCount=0; //һ�δ�ӡ���ܼ�¼����
     int newVinLst=0; //��vin����λ
     int oldVinLst=0; //��vin����λ

     
     int temp =0;
     sql = "select max(iBigNo) tt  from print_data  where iPrintGroupId = '1' and convert(varchar(10),dPrintTime,20)='"+jspRq+"'";
     try{
    	 con = Conn.getConn();
    	 stmt = con.createStatement();
     	rs = stmt.executeQuery(sql);
    	 if(rs.next())
     		 temp = rs.getInt("tt")+1;
    	 else
     		 temp =1;
 	 }catch(Exception e){
   		 e.printStackTrace();
 	 }finally{
  
    if(stmt!=null)
    	stmt.close();
    if(rs!=null)
    	rs.close();
    if(con!=null) 
   		con.close();
  
  }
  
   %>
  
  <body>

      <div align="center">
      <font size="+1" >�������ѯ</font> 
      <label> <mes:calendar id="rq" name="rq" reSourceURL="JarResource/" showDescripbe="false" haveTime="false" value="<%=jspRq%>"/>
      </label> 

      </div>
      <form id="form1" name="form1" method="post" action="">
     <table width="500" border="1" align="center" height="97">
     <tr>	
    	 <td width="5%">groupid</td>
		<td width="40%"> ����</td>
		<td width="40%"> ����</td>
    	<td width="20%">��ѯ</td>

     </tr>
 <%
 	try
 	{
 		con = Conn.getConn();
     	stmt = con.createStatement();
     	//sql="select cCarTypeDesc,ctfassname,ibigno,nperTimeCount,nTfassCount,iPrintGroupId from printset";
     	sql="select id from printSet order by id";
     	rs=stmt.executeQuery(sql);
		while (rs.next())
		{
			//��С�������
		     int minPartCount=9999;
			//groupid�ţ�
			printId=rs.getString(1);
			//�ѽ��ճ�����
			//sqlCarCount="select count(*)  from cardata a,cardata_d b where a.cCarNo=b.icarid and b.itfassnameid=4";	
			try
			{
				
				//stmtCarCount=con.createStatement();
				//rsCarCount=stmtCarCount.executeQuery(sqlCarCount);
				
				strSql="select cDescrip,cCarTypeDesc,ctfassname,nPerTimeCount,nTFASSCount,id,cfactory,cCarType,cPrintRadio,cAuto,cSEQNo_A,npage from printset where id="+printId+" order by id";
				stmtIn=con.createStatement();
				rsIn=stmtIn.executeQuery(strSql);
				while(rsIn.next())
				{
					
					//carCount=rs.getString(1);
					descript=rsIn.getString(1);
					carTypeDesc=rsIn.getString(2);
					tFassName=rsIn.getString(3);
					perTimeCount=rsIn.getInt(4); //��ӡ����
					tFassCount=rsIn.getInt(5);	
					printSetId=	rsIn.getString(6);
					factoryNo=rsIn.getString(7);
					carType=rsIn.getString(8);
					printRadio=rsIn.getString(9).trim();
					auto=rsIn.getString(10).trim();
					seq_A=rsIn.getString(11).trim();
					pages=rsIn.getInt(12);
					//System.out.println(printSetId);
					
					//ȡfassid����ܳ�
					fassidSql="select id from tfassname where ctfassname='"+tFassName+"'";
					try
					{
						fassidStmt=con.createStatement();
						fassidRs=fassidStmt.executeQuery(fassidSql);
						if(fassidRs.next())
						{
							fassid=fassidRs.getString(1);
						}
						else
						{
							System.out.println("ȡfassid����ܳ�id����printPHD.jsp");
						}
					}
					catch(Exception eGetTfassid)
					{
						System.out.println("ȡfassid����ܳ�id����printPHD.jsp"+eGetTfassid.toString());
					}	
					finally
					{
						if(fassidRs!=null)
							fassidRs.close();
						if(fassidStmt!=null)
							fassidStmt.close();
					}
					//end ȥfassid����ܳ�id
				   //�ɴ�ӡ���������
				   			
				}
				
			}
			catch(Exception eGetCarCount)
			{
				System.out.print("eGetCarCount:"+eGetCarCount.toString());
				throw eGetCarCount;
			}
			finally
			{
				if(rsIn!=null)
					rsIn.close();
				if(stmtIn!=null)
					stmtIn.close();				
			}
			out.write("<tr>");	
			out.write("<td>"+printId+"</td>");
			//out.write("<td>"+descript+"</td>");
			out.write("<td>"+carTypeDesc+"</td>");
			out.write("<td><input type='text' id='text"+printId+"' name='text"+printId+"' value='0' </td>");		
			//out.write("<td >��ʷ����<mes:calendar id='oldrq"+groupId+"' name='rq"+groupId+"' reSourceURL='JarResource/' showDescripbe='false' haveTime='false' value='2010-06-05'/>");	
			out.write("<td><input type='button' name='button"+printId+"' id='button"+printId+"' value='�ύ' onclick='printSearch("+printId+")'/></label></td>");
			//out.write("<input type='button' name='historyPrint' id='historyPrint' value='��ʷ��ӡ' /></td>");
			//out.write("<td>"+carCount+"/<input type='radio' id='radio1'>"+rs.getInt(4)+"<input type='radio' id='radio1' checked>"+rs.getInt(5)+"</td>");
			out.write("</tr>");
			

		}//end while printid;
    }
    catch(Exception e2)
    {
    	e2.printStackTrace();
 	}
  	finally
  	{ 
   	 if(stmt!=null)
   	 stmt.close();
   	 if(rs!=null)
   	 rs.close();
   	 if(con!=null) 
  	  con.close();
  	}
  %>
    </table>

</form>
</body>
 <script language="javascript"> 
function printSearch(printId)
{
	
	var text='text'+printId;
	//alert(text);
	var cjs=document.getElementById(text).value;
	var rq=document.getElementById("rq").value;
	//alert(cjs);
	window.location="searchView.jsp?rq="+rq+"&cjs="+cjs+"&id="+printId;
}
function reflush(){
	window.location.href="printPHD.jsp";
}
//setTimeout(reflush,10000);

</script> 
</html>
