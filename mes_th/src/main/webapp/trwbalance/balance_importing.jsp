<%@ page language="java" import="java.sql.Connection,java.util.*" contentType="text/html;charset=GBK"%>
<%@page	import="org.apache.commons.logging.Log,org.apache.commons.logging.LogFactory"%>
<%@ page import="org.apache.commons.fileupload.*,java.io.*" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%@page import="java.sql.CallableStatement"%>
<%@page import="java.sql.Statement"%>

<jsp:useBean id="Conn" scope="page" class="com.qm.th.helper.Conn_MES"/>
<%
    final  Log log = LogFactory.getLog("balance_importing.jsp");
	Connection con = null;
	try{
		con = Conn.getConn();
		// �����ļ������������������� FileItem ����
		DiskFileItemFactory factory = new DiskFileItemFactory(); 
		// �����ļ��Ļ���·��
		String tempdir =this.getServletContext().getRealPath("/upload/temp");    
		java.io.File d = new java.io.File(application.getRealPath("/trwbalance/update"));
		if(!d.exists()){
			d.mkdirs();
		}
		factory.setSizeThreshold(100*1024*1024); // �������ֻ�������ڴ��д洢������,��λ:�ֽ�
		factory.setRepository(d); // ����һ���ļ���С����getSizeThreshold()��ֵʱ���ݴ����Ӳ�̵�Ŀ¼(Ĭ�Ͽ��Բ�������)

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// ���������û��ϴ��ļ���С,��λ:�ֽ�
		upload.setSizeMax(100*1024*1024);
		//�ϴ��ļ�,�����������еı��ֶΣ�������ͨ�ֶκ��ļ��ֶ�
		List  items = upload.parseRequest(request); 
		//�����ÿ���ֶν��д�������ͨ�ֶκ��ļ��ֶ�
		Iterator it = items.iterator();
		while(it.hasNext()){
			FileItem fileItem = (FileItem) it.next();
			//�������ͨ�ֶ�
			if(fileItem.isFormField()){  //����ͨ���ֶ�
				System.out.println(fileItem.getFieldName() + "   " + fileItem.getName() + "   " + new String(fileItem.getString().getBytes("iso8859-1"), "gbk"));
				fileItem.getFieldName();//�õ��ֶ�name���Ե�ֵ
				fileItem.getName();//�õ�file�ֶε��ļ���ȫ·�������������file�ֶΣ�Ϊnull
				fileItem.getString();//�õ����ֶε�ֵ,Ĭ�ϵı����ʽ
				fileItem.getString("GB2312");//ָ�������ʽ
			}else{//�ļ��ֶ�
				System.out.println(fileItem.getFieldName() + "   " +
				fileItem.getName() + "   " +//�õ�file�ֶε��ļ���ȫ·����
				fileItem.isInMemory() + "    " +//�����ж�FileItem������װ�����������Ǵ洢���ڴ��У����Ǵ洢����ʱ�ļ��У�����洢���ڴ����򷵻�true�����򷵻�false
				fileItem.getContentType() + "   " +//�ļ�����
				fileItem.getSize());          //�ļ���С

				//�����ļ�����ʵ���ǰѻ����������д��Ŀ��·����
				if(fileItem.getName()!=null && fileItem.getSize()!=0){
					File fullFile = new File(fileItem.getName());
					File newFile = new File(application.getRealPath("/trwbalance/update") + "/" + fullFile.getName());
					fileItem.write(newFile);
					System.out.println("�ϴ��ļ�·����"+application.getRealPath("/trwbalance/update")+ "/" + fullFile.getName());
					CallableStatement cstmt = con.prepareCall("{ call sp_cp7_inserttxt(?)}");
					cstmt.setString(1, application.getRealPath("/trwbalance/update") + "/" + fullFile.getName());
					cstmt.execute();
					/*
					String sql = "DELETE FROM cp7 WHERE (cvincode IN (SELECT DISTINCT cvincode FROM VIEW_cp7_tempdata))"
						+" insert into cp7 select * from VIEW_cp7_tempdata WHERE CPARTNO<>'3C0 400 035'"
						+" insert into cp7 (CCARNO,CVINCODE,DAENDTIME,CPARTNO,CPARTNAME,NUM)select CCARNO,CVINCODE,DAENDTIME,'3C0 400 035 L',CPARTNAME,NUM from VIEW_cp7_tempdata WHERE CPARTNO='3C0 400 035'"
						+" insert into cp7 (CCARNO,CVINCODE,DAENDTIME,CPARTNO,CPARTNAME,NUM)select CCARNO,CVINCODE,DAENDTIME,'3C0 400 035 R',CPARTNAME,NUM from VIEW_cp7_tempdata WHERE CPARTNO='3C0 400 035'"
						+" DELETE FROM cp7_productdata WHERE (cvincode IN (SELECT DISTINCT dbo.cp7.cvincode FROM dbo.cp7))"
						+" DELETE FROM cp7_cardata WHERE (cvincode IN (SELECT DISTINCT dbo.cp7.cvincode FROM dbo.cp7))"
						+" DELETE FROM cp7_err WHERE (cvincode IN (SELECT DISTINCT dbo.cp7.cvincode FROM dbo.cp7))"
						+" insert into cp7_productdata select *,num AS nusednum from cp7";
					Statement stmt = con.createStatement();
					log.debug("������ѯ����"+sql);
					stmt.execute(sql);
					*/
					cstmt = con.prepareCall("{ call sp_cp7_getvin}");
					cstmt.execute();
					/*
					if(stmt != null){
						stmt.close();
						stmt = null;
					}
					*/
					out.println("<script>alert(\"�������\");</script>");
				}else{
				System.out.println("�ļ�û��ѡ�� �� �ļ�����Ϊ��");
				out.println("<script>alert(\"�ļ�û��ѡ����ļ�����Ϊ��\");</script>");
				}
			}
		}
	}catch(Exception e){
		e.printStackTrace();
		log.error(e.toString());
	}finally{
		if(con!=null){
			con.close();
			con = null;
		}
	}

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=gb2 312"/> 
  </head>
  
  <body><br><br></body>
  <script type="text/javascript">
  function back(){
  	window.location.href = "balance_import.jsp";
  }back();
  
  </script>
</html>
