<%@ page language="java" import="java.util.*" pageEncoding="GB2312"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.sql.*"%>
<%@taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>
<jsp:useBean id="Conn" scope="page" class="com.qm.mes.th.helper.Conn_MES" />
<%

	/** ��ҳ�治���� */
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);

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
    <link rel="stylesheet" type="text/css" href="../../cssfile/css.css">
    <link rel="stylesheet" type="text/css" href="../../cssfile/th_style.css">
	<script type="text/javascript" src="../../JarResource/META-INF/tag/taglib_common.js"></script>		 
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
     Statement cseq_stmt=null;
     ResultSet cseq_rs=null;
     
     String vinSql="";
     String fassidSql="";
     String sql = "";
     String sqlInIn="";
     String strSql="";
     String groupId="0";
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
     String jsls=""; //javascript��ӡ������
     String fassid=""; //����ܳ�id
     String vinCode=""; //vin��
     String tempVin=""; //��ʱvin �Ƚ��Ƿ�����
     String vinType=""; //vin78λ
     String IsContinu="1"; //����
     String abegin="";
     String cseq_strSql="";
     String lastVin=""; //�������
     


     int maxPage=0;
     int perTimeCount=0;
     int tFassCount=0;
     int pages=0;
     int partCount=0;
     int printCount=0; //��ӡ���� ��ͬ��javascript ls
     int testCount=0; //һ�δ�ӡ���ܼ�¼����
     int newVinLst=0; //��vin����λ
     int oldVinLst=0; //��vin����λ
     int PerTimeRow=0; //ÿ�δ�ӡ���������һҳ6�У���ҳ����12�С�

     
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
      <font size="+1" >�������ӡ��</font> 
      <label> <mes:calendar id="rq" name="rq" reSourceURL="JarResource/" showDescripbe="false" haveTime="false" value="<%=jspRq%>"/>
      </label> 
      <input type="button" value="ȷ��" onclick="getPrtDate()"/> 
      </div>
      <form id="form1" name="form1" method="post" action="">
     <table width="1000" border="1" align="center" height="97">
     <tr>	
    	 <td>groupid</td>
		<td width="40%"> 
		����</td>
    	<td width="28%">��������ά��</td>
    	<td width="7%">��ǰ�ܺ�</td>
     	<td width="7%"><strong><font size="2" face="����" color="#ff0000">�ѽ���</font></strong></td>
     	<td width="8%">��ӡ��׼</td>
     	<td width="10%">�Զ���ӡ </td>
     	<td width="10%">�ύ</td>
     </tr>
 <%
 	String openApp = "";
 	try
 	{
 		con = Conn.getConn();
     	stmt = con.createStatement();
     	//sql="select cCarTypeDesc,ctfassname,ibigno,nperTimeCount,nTfassCount,iPrintGroupId from printset";
     	sql="select distinct iPrintGroupId from printSet";
     	rs=stmt.executeQuery(sql);
		while (rs.next())
		{
			//��С�������
		     int minPartCount=9999;
			//groupid�ţ�
			groupId=rs.getString(1);
			/*
			//��ʼ����
			//System.out.println("bigno");
			bigNo=rs.getInt(3);
			if (bigNo==0)
			{
				bigNo++;
			}
			*/
			//�ѽ��ճ�����
			//sqlCarCount="select count(*)  from cardata a,cardata_d b where a.cCarNo=b.icarid and b.itfassnameid=4";
		
			try
			{
				
				//stmtCarCount=con.createStatement();
				//rsCarCount=stmtCarCount.executeQuery(sqlCarCount);
				
				strSql="select cDescrip,cCarTypeDesc,ctfassname,nPerTimeCount,nTFASSCount,id,cfactory,cCarType,cPrintRadio,cAuto,cSEQNo_A,npage,cLastVin from printset where iPrintGroupid="+groupId+" order by id";
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
					//seq_A=rsIn.getString(11).trim();				
					pages=rsIn.getInt(12);
					lastVin=rsIn.getString(13).trim();
					//System.out.println(printSetId);
					if(lastVin==null||lastVin.equals(""))
					{
						System.out.println("jdhzServletPrint.lastVin����������");
						throw new Exception("jdhzServletPrint.lastVin����������");
					}
					else
					{
						lastVin=lastVin.trim();
					}
					
					//ȡʱ�� dabegin��˳��� seq_A
					//cseq_strSql="select dabegin from cardata where cseqno_a='"+seq_A+"' and convert(varchar(10),dabegin,20)='"+jspRq+"'";
					cseq_strSql="select dabegin,cseqno_a from cardata where cvincode='"+lastVin+"'";
					out.write("************:"+printSetId);
					out.write("<br />");
					//out.write("ȡ��ʱ��"+cseq_strSql);
					//out.write("<br />");
					try
					{
						cseq_stmt=con.createStatement();
						cseq_rs=cseq_stmt.executeQuery(cseq_strSql);
						if(cseq_rs.next())
						{
							abegin=cseq_rs.getString(1);
							seq_A=cseq_rs.getString(2).trim();
						}
						else
						{
							//abegin=jspRq;
							System.out.println("MES_TH �ļ���printPHD.jsp����cardata����û�д�VIN :"+lastVin);
						}
					}
					catch(Exception ecseq)
					{
						System.out.println("ȡdabegin ����"+ecseq.toString());
					}
					finally
					{
						if(cseq_rs!=null)
							cseq_rs.close();
						if(cseq_stmt!=null)
							cseq_stmt.close();
					}
					//ȡfassid����ܳ�
					fassidSql="select id from tfassname where ctfassname='"+tFassName+"'";
					//out.write("ȡ���ܳ�ID"+cseq_strSql);
					//out.write("<br />");
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
					
			
					//hmvin���vin��
					HashMap<String,String> hmVin=new HashMap<String,String>(); //�� vintype��vin
					vinSql="select ctype,clastvin  from printSetVin where printid='"+printSetId+"'" ;	
					//out.write("ȡ��VIN��:"+vinSql);
					//out.write("<br />");
					//System.out.println(vinSql);	
					try
					{
						stmtInIn=con.createStatement();
						rsInIn=stmtInIn.executeQuery(vinSql);				
						while(rsInIn.next())
						{
							if(rsInIn.getString(1)!=null &&!rsInIn.getString(1).trim().equals(""))
							{
								//System.out.println("cType is :"+rsInIn.getString(1)+"end");
								hmVin.put(rsInIn.getString(1).trim(),rsInIn.getString(2).trim());
							}
						}
					}
					catch(Exception eInIn)
					{
						System.out.println("eInIn ȡ���vin�� ����"+eInIn.toString());
						throw eInIn;
					}
					finally
					{
						if(rsInIn!=null)
							rsInIn.close();
						if(stmtInIn!=null)
							stmtInIn.close();
					}			
					//end hmvin���vin��
					
					//ȡ�ô�ӡ����
					if(printRadio.trim().equals("1"))
					{
						printCount=1;
					}
					else if(printRadio.trim().equals("2"))
					{
						printCount=2;
					}
					else if(printRadio.trim().equals("3"))
					{
						printCount=4;
					}
					PerTimeRow=tFassCount*printCount;
					//end��ӡ����
								
					//vin���������
					//sqlWhere=" select top "+tFassCount+" c.cSEQNo_A,c.cVinCode,c.cCarType,cQADNo,sc.ITFASSNameId,sc.iTFASSNum,c.cCarNo from carData c,carData_D sc,TFASSName t";
					//sqlWhere=" select count(*) from carData c,carData_D sc,TFASSName t";	
					//sqlWhere=sqlWhere+" where c.ccarno=sc.iCarId  and t.cTFASSName= '"+tFassName+"'   and sc.ITFASSNameId = t.id and convert(varchar(10),c.dABegin,20) >= '"+jspRq+"' and c.cSEQNo_A>'"+seq_A+"'"; 					
					testCount=printCount*tFassCount;	
					sqlWhere=" select top "+testCount+"  c.cSEQNo_A,c.cVinCode,c.cCarType,cQADNo,sc.ITFASSNameId,sc.iTFASSNum,c.cCarNo  from carData c left outer join carData_D sc" +
					" on c.ccarno=sc.icarid and itfassnameid="+fassid+" left join TFASSName t on sc.itfassnameid=t.id where ((dabegin = '"+abegin+"' and c.cSEQNo_A>'"+seq_A+"')"+
					" or (dabegin > '"+abegin+"'))"; 
					if(carType!=null&&!carType.equals(""))
					{
						carType="'"+carType+"'";
						carType=carType.replace(",", "','");
						//sqlWhere=sqlWhere+" and (subString(c.cSEQNo_A,1,2) in("+carType+")) ";
						sqlWhere=sqlWhere+"and substring(c.ccarno,6,1) in ("+carType+")";
					}				
					if(factoryNo!=null&&!factoryNo.equals(""))
					{
						factoryNo="'"+factoryNo+"'";
						factoryNo=factoryNo.replace(",", "','");
						sqlWhere=sqlWhere+" and (subString(c.cSEQNo_A,1,2) in("+factoryNo+")) ";
					}
					sqlWhere=sqlWhere + " order by c.dabegin,c.cSEQNo_A";

					//out.write("�ܲ�ѯ:"+sqlWhere);
					//out.write("<br />");
					System.out.println(sqlWhere);
					//System.out.println(hmVin.toString());

					try
					{
						stmtInIn=con.createStatement();
						rsInIn=stmtInIn.executeQuery(sqlWhere);		
						//System.out.println(printSetId);	
						IsContinu="1";
						while(rsInIn.next())
						{
							//System.out.println("begin");				
							vinCode=rsInIn.getString(2).trim();
							vinType=vinCode.substring(6,8);
							tempVin=hmVin.get(vinType);
							if(tempVin==null||tempVin.trim().equals(""))
							{
								hmVin.put(vinType,vinCode);
							}
							else
							{
								//System.out.println("vin"+vinCode);
								//System.out.println("vinType:"+vinType);
								//System.out.println(hmVin.toString());
								//System.out.println("oldvin"+tempVin);
								oldVinLst=Integer.valueOf(tempVin.substring(11)); //vin����λ
								newVinLst=Integer.valueOf(vinCode.substring(11)); //cardata��vin��6λ
								oldVinLst++;
								//���������
								if(newVinLst!=oldVinLst)
								{
									System.out.println(tFassName);
									System.out.println(newVinLst+"����"+oldVinLst);
									System.out.println(vinCode+":"+tempVin);
									System.out.println(printSetId+":"+tFassName);
									IsContinu="0";
									break;
								}
								else
								{
									hmVin.put(vinType,vinCode);
								}
							}
							//partCount=rsInIn.getRow();
						}
						
					}
					catch(Exception eInIn)
					{
						System.out.println("eInIn vin�����жϳ���"+eInIn.toString());
						throw eInIn;
					}
					finally
					{
						if(rsInIn!=null)
							rsInIn.close();
						if(stmtInIn!=null)
							stmtInIn.close();
					}
					//ȡ�ѽ�������
					sqlWhere=" select count(*)  from carData c left outer join carData_D sc" +
					" on c.ccarno=sc.icarid and itfassnameid="+fassid+" left join TFASSName t on sc.itfassnameid=t.id where ((dabegin = '"+abegin+"' and c.cSEQNo_A>'"+seq_A+"')"+
					" or (dabegin > '"+abegin+"'))"; 
					//�ڶ��δ��� carType
					if(carType!=null&&!carType.equals(""))
					{
						//carType="'"+carType+"'";
						//carType=carType.replace(",", "','");
						//sqlWhere=sqlWhere+" and (subString(c.cSEQNo_A,1,2) in("+carType+")) ";
						sqlWhere=sqlWhere+"and substring(c.ccarno,6,1) in ("+carType+")";
					}
					//�ڶ��δ���factoryNo				
					if(factoryNo!=null&&!factoryNo.equals(""))
					{
						//factoryNo="'"+factoryNo+"'";
						//factoryNo=factoryNo.replace(",", "','");
						sqlWhere=sqlWhere+" and (subString(c.cSEQNo_A,1,2) in("+factoryNo+")) ";
					}
					//sqlWhere=sqlWhere + " order by c.cSEQNo_A";
					//System.out.println(sqlWhere);
					out.write("ȡ�ѽ�������:"+sqlWhere);
					out.write("<br />");
					try
					{
						stmtInIn=con.createStatement();
						rsInIn=stmtInIn.executeQuery(sqlWhere);				
						if(rsInIn.next())
						{						
							partCount=rsInIn.getInt(1);
						}				
					}
					catch(Exception eInIn)
					{
						System.out.println("eInIn �ѽ�����������"+eInIn.toString());
						throw eInIn;
					}
					finally
					{
						if(rsInIn!=null)
							rsInIn.close();
						if(stmtInIn!=null)
							stmtInIn.close();
					}				
					//��ͬgroupid��ͬid�� ȡ��С�����������ʾ��
					if(minPartCount>partCount)
						minPartCount=partCount;				
					//end ȡ�ѽ������ݣ�
					
					/*
					//20100605������ ��ȡ������
					//���� �˴���д��whileѭ����������Դ�˷�
						sqlInIn="select max(ibigNo) from print_Data where cremark='"+jspRq+"' and iPrintGroupId="+printSetId; 
						try
						{
							stmtInIn=con.createStatement();
							rsInIn=stmtInIn.executeQuery(sqlInIn);
							if(rsInIn.next())
							{
								ch=rsInIn.getString(1);
								
							}
						}
						catch(Exception eInIn)
						{
							System.out.println("eInIn ����"+eInIn.toString());
							throw eInIn;
						}
						finally
						{
							if(rsInIn!=null)
								rsInIn.close();
							if(stmtInIn!=null)
								stmtInIn.close();
						}
						end20100605������ ��ȡ������
					*/
					//�ܺ�
					//sqlInIn="select max(iCarNo) from print_Data where cremark='"+jspRq+"' and iPrintGroupId="+printSetId+" and iBigNo="+ch;
					sqlInIn="select max(iCarNo) from print_Data where cremark='"+jspRq+"' and iPrintGroupId="+printSetId;

					//out.write("�ܺ�:"+sqlInIn);
					//out.write("<br />");
					try
					{
						stmtInIn=con.createStatement();
						rsInIn=stmtInIn.executeQuery(sqlInIn);
						if(rsInIn.next())
						{
							js=rsInIn.getString(1);
						}
						if(js==null||js.equals(""))
						{
							js="0";
						}
					}
					catch(Exception eInIn)
					{
						System.out.println("eInIn ����"+eInIn.toString());
						throw eInIn;
					}
					finally
					{
						if(rsInIn!=null)
							rsInIn.close();
						if(stmtInIn!=null)
							stmtInIn.close();
					}
					/*20100605��������ȡ������
					// ������==������ʱ ���ż�1�� ������
					maxPage=perTimeCount/tFassCount;	
					//System.out.println("333333333333"+ch);
					//System.out.println(maxPage);	
					if(ch==null||ch.equals(""))
					{
						ch="1";
					}
					else if(maxPage<=Integer.valueOf(js))
				     {
				     	ch=String.valueOf(Integer.valueOf(ch)+1);
				     }
				    // System.out.println("44444444"+ch);	
				     
				   	  else if(ch.equals("0"))
				      {
				     		ch="1";
				  	   }
				     
				    end ȡ������*/
				     
				   //�ɴ�ӡ���������
				   			
				}
				
				//�Զ���ӡ
				//System.out.println(groupId+":"+minPartCount+"print:"+tFassCount*printCount);
				if(auto.equals("1")&&minPartCount>=tFassCount*printCount)
				{
					//System.out.println("ss");
					//openApp +="openApp("+groupId+");";
					openApp +="openApp("+groupId+","+printRadio+","+ch+","+pages+","+minPartCount+","+PerTimeRow+","+IsContinu+");";
					
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
			out.write("<td>"+groupId+"</td>");
			//out.write("<td>"+descript+"</td>");
			out.write("<td>"+descript+"</td>");
			//out.write("<td>"+tFassName+"</td>");		
			//System.out.println("aaaaaaaaaaaaaaaa"+ch);
			//out.write("<td >��ʷ����<mes:calendar id='oldrq"+groupId+"' name='rq"+groupId+"' reSourceURL='JarResource/' showDescripbe='false' haveTime='false' value='2010-06-05'/>");
			out.write("<td>");
			out.write("��ʷ�ܺ�<input type='text' name='oldjh"+groupId+"' id='oldjh"+groupId+"' size='3' value='0'/>");
			out.write("<input type='button' value='��ʷ��ӡ' onclick='reprint("+groupId+")'/>");
			out.write("<input type='button' value='���´�ӡ' onclick='printOld("+groupId+")'/></td>");
			out.write("<td>"+js+"</td>");
			out.write("<td  ><label ><strong><font color='#ff0000' size='3' face='����'>"+minPartCount+"</strong></label></td>");
			out.write("<td>");
			if(printRadio.trim().equals("1"))
				out.write(tFassCount+"����");
			if(printRadio.trim().equals("2"))
				out.write(tFassCount*2+"����");	
			if(printRadio.trim().equals("3"))
				out.write(perTimeCount+"����");	
			out.write("</td>");
			out.write("<td><input type='checkbox' name='checkBox"+groupId+"'   id='checkBox"+groupId+"'  disabled='true'");
			if(auto.equals("1"))
				out.write("checked");
			out.println("/> �Զ���ӡ</td>");			
			out.write("<td><input type='button' name='button"+groupId+"' id='button"+groupId+"' value='�ύ' onclick='openApp("+groupId+","+printRadio+","+ch+","+pages+","+minPartCount+","+PerTimeRow+","+IsContinu+")'/></label></td>");
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
  <!--  
    <tr>
      <td width="134"><label>
        <input type="radio" name="ls"  value="2" checked="checked" onclick="setls(2)"/>
      12����</label></td>
      <td width="111"><label>
        <input type="radio" name="ls" value="4" onclick="setls(4)"/>
      24����</label></td>
      <td width="102"><label>
        <input type="radio" name="ls" value="8"  onclick="setls(8)" />
      48����</label></td>
    </tr>
    <tr>
      <td align="center">����������</td>
      
      <td colspan="2"><label>
        <input type="text" name="rq" id="rq"  value="<%=jspRq %>"/>
      </label></td>
    </tr>
    <tr>
      <td align="center">���복��</td>
      <td colspan="2"><label>
        <input type="text" name="ch" id="ch" value="<%=temp %>" />
      </label></td>
    </tr>
    <tr>
      <td align="center"><label>
        <input type="button" name="button" id="button" value="�ύ" onclick="openApp()"/>
      </label></td>
      <td colspan="2" align="center"><label>
        <input type="reset" name="button2" id="button2" value="����" />
      </label></td>
    </tr>

  -->
    </table>
   <div id="d">
  <APPLET ID="JrPrt" name = "app" codebase="th/pzt" CODE = "JdApplet"  
  	ARCHIVE = "jasperreports-3.1.4-applet.jar,jcommon-1.0.10.jar,jasperreports-2.0.5.jar"
  	 WIDTH = "0" HEIGHT = "0" MAYSCRIPT>
  <PARAM NAME = "type" VALUE="application/x-java-applet;version=1.2.2"/>
  <PARAM NAME = "scriptable" VALUE="true"/>
  <PARAM NAME = "REPORT_URL" VALUE =""/>

  </APPLET>
  </div>
</form>
</body>
 <script language="javascript"> 
var ls = 1;
function setls(xx){
	ls=xx;
	//alert(ls);
}
//function test(s){
/*
    var rq=document.getElementById("rq").value;
    var ch=document.getElementById("ch").value;
    document.app.pp(rq,ch,s);
*/
//}
function getPrtDate()
{
	var prtDate=document.getElementById("rq").value;
	window.location="printPHD.jsp?rq="+prtDate;
}
//�ظ���ӡ�����ܺţ����� ��ӡprint_data������
function reprint(groupId)
{
 	var rq=document.getElementById("rq").value;
 	
	var jsOldCh="oldch"+groupId;
	
	//var jsch=document.getElementById(jsOldCh).value;
	var jsch="1";
	var jsOldJh="oldjh"+groupId;
	var jsjh=document.getElementById(jsOldJh).value;
	var printPath="<%=basePath%>"+"servlets";
	if(jsch==0 ||jsjh==0)
	{
		alert("������ܺ�");
		return false;
	}

	document.app.ppr(rq,jsch,jsjh,printPath,groupId); 
}
function printOld(groupId)
{
	
 	var rq=document.getElementById("rq").value;
 	
	var jsOldCh="oldch"+groupId;
	
	//var jsch=document.getElementById(jsOldCh).value;
	var jsch="1";
	var jsOldJh="oldjh"+groupId;
	var jsjh=document.getElementById(jsOldJh).value;
	var printPath="<%=basePath%>"+"servlets";
	if(jsch==0 ||jsjh==0)
	{
		alert("������ܺ�");
		return false;
	}

	document.app.ppHistory(rq,jsch,jsjh,printPath,groupId); 

}


function openApp(groupid,jPrintRadio,ch,pages,minPartCount,PerTimeRow,IsContinu) 
{ 

    //var ls=document.getElementById("ls").value; 
    //alert(groupid);
   	
    var rq=document.getElementById("rq").value;
    //alert(rq);
    //return false;
    //var ch=document.getElementById("ch").value;
    //alert(ch+"aa");
    var prtDate;
    var printPath;
    var prtMsg;
    var vinContinu;
  
    prtDate="<%=jspRq%>";
    printPath="<%=basePath%>"+"servlets";
     if(jPrintRadio=="1")
   		ls=1;
  	 else if(jPrintRadio=="2")
   		ls=2;
  	 else if(jPrintRadio=="3")
  	 	ls=4;
	//ls=document.getElementById("radio").value;
	
	//������
	if(IsContinu=="0")
	{
		vinContinu=window.confirm("vin���������Ƿ��ӡ");
		if(vinContinu==true)
		{		
			//��ʾ���������Ƿ��ӡ
			if(minPartCount<PerTimeRow)
			{
				prtMsg=window.confirm("���ݲ����Ƿ��ӡ��");
				if(prtMsg==true)
				{
				   // document.app.pp(rq,ch,1,printPath,groupid);  
				   document.app.pp(rq,ch,ls,printPath,groupid,vinContinu);  
				    for(var mm=0;mm<pages-1;mm++)
				    { 
				    	document.app.ppm(rq,ch,ls,printPath,groupid); 
				    }
				   	window.location.reload();
				   		//window.location="printPHD.jsp?rq=1";
		   		}
		   	}
		   	else //��������
		   	{
		   			//document.app.pp(rq,ch,ls,printPath,groupid);  
		   			document.app.pp(rq,ch,ls,printPath,groupid,vinContinu); 
				    for(var mm=0;mm<pages-1;mm++)
				    { 
				    	document.app.ppm(rq,ch,ls,printPath,groupid); 
				    }
				   	window.location.reload();
				   		//window.location="printPHD.jsp?rq=1";
		   	}
		 }
   	}
   	//����
   	else
   	{
   			//��ʾ���������Ƿ��ӡ
			if(minPartCount<PerTimeRow)
			{
				prtMsg=window.confirm("���ݲ����Ƿ��ӡ��");
				if(prtMsg==true)
				{
				   // document.app.pp(rq,ch,1,printPath,groupid);  
				   document.app.pp(rq,ch,ls,printPath,groupid,vinContinu);  
				    for(var mm=0;mm<pages-1;mm++)
				    { 
				    	document.app.ppm(rq,ch,1,printPath,groupid); 
				    }
				   	window.location.reload();
				   		//window.location="printPHD.jsp?rq=1";
		   		}
		   	}
		   	else //��������
		   	{
		   			//document.app.pp(rq,ch,ls,printPath,groupid);  
		   			document.app.pp(rq,ch,ls,printPath,groupid,vinContinu); 
				    for(var mm=0;mm<pages-1;mm++)
				    { 
				    	document.app.ppm(rq,ch,ls,printPath,groupid); 
				    }
				    window.location.reload();
				   	//window.location="printPHD.jsp?rq=1";
		   	}
   	}
    /*
    //�����������С��
    if(rq<prtDate)
    {
    	alert("��������"+rq+"С��ϵͳ����"+prtDate+"Ӧ�ô��ڵ��ڴ�����");
    	return false;
    }
    else if(rq=prtDate)
    {
    //ls ��ӡҳ��
    	alert("print");
    	document.app.pp(rq,ch,ls,1);   
    	window.location.reload();
    }
    //�������ڴ���ϵͳ���ڣ������Զ���Ϊ1��
    else
    {
    	ch=1;
    	document.app.pp(rq,ch,ls,1);   
    	window.location.reload();
    }
    */

}
<%=openApp%>
function reflush(){
	window.location.href="printPHD.jsp";
}
setTimeout(reflush,180000);

</script> 
</html>
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          