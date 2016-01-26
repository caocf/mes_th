<%@ page language="java" contentType="text/html;charset=gb2312"%>
<%@ page import="com.qm.mes.util.tree.DataServer_UserManage"%>
<%@ page import="com.qm.mes.util.tree.BussinessProcess_UserManage"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.SQLException"%>

<jsp:useBean id="Conn_MES" scope="page" class="com.qm.th.helper.Conn_MES"/>
<% 
	String userid = (String)session.getAttribute("userid");
	if(userid == null){
		out.write("���ʱ��ܾ���");
		return;
	}
	
	String cssFile = (String)session.getAttribute("file");
 	if(cssFile == null || cssFile.trim().equals("")){ 
 		cssFile="blue";
 	}
	
	Connection con = null;
	DataServer_UserManage ds = null;
	BussinessProcess_UserManage bp = null;
	
	try{
		con = Conn_MES.getConn();
		ds = new DataServer_UserManage(con);
		String color = request.getParameter("color");
		if(color != null){
			session.setAttribute("file",color);
			cssFile = color;
			bp = new BussinessProcess_UserManage(con);
			bp.updateUserInterface(userid,color);
		}
		String roleno = request.getParameter("roleno");
		String rolename = request.getParameter("rolename");
		String url = ds.getWelcomePage(roleno);
%>

<html>
<head>
	<title>mes-web���</title>
	<link rel="stylesheet" type="text/css" href="cssfile/<%=cssFile%>.css">
	<script language="javascript" type="text/javascript" src="popwin.js"></script>
	<script>
		<!--
			/** AJAX���� */
			var XHR;
			 
			/**
			 * XMLHttpRequest���캯��
			 */
			function createXMLHttpRequest( ) {
		        var request = false;
		        if (window.XMLHttpRequest) {
	                if (typeof XMLHttpRequest != 'undefined'){
                        try {
                            request = new XMLHttpRequest( );
                        } catch (e) {
                            request = false;
                        }
	                }
		        } else if (window.ActiveXObject) {
	                try {
                    	request = new ActiveXObject('Msxml2.XMLHTTP');
	                } catch(e) {
	                    try {
                            request = new ActiveXObject('Microsoft.XMLHTTP');
	                    } catch (e) {
                            request = false;
	                    }
	                }
		        }
		        return request;
			}
			// ����XMLHttpRequest����
			XHR = createXMLHttpRequest( );
			
			/**
			 * ��ʼ��
			 *
			 * @param frame Frame����
			 */
			function initial(frame){
				// �������
				var win = frame.contentWindow;
				// Document����
			    var doc = win.document;
				// ����Ի���
				var dialog;
				// ǰһ������ֵ
			    var prers = "";
				
				// ���ع�����
	   	 		if(doc.body.clientWidth == doc.body.offsetWidth){ doc.body.scroll = "no"; }
			
				if (XHR) {
			        var sendRequest = function(){
			        	  // ��������
			        	  var fun = arguments.callee;
			        	  // ʱ��� 
			        	  var timestamp = new Date().getTime();
			        	  // ����GET��ʽ�ύ����·��Ϊbom_dialog.jsp
					      XHR.open("GET", "SpecialKinWarning?timestamp=" + timestamp + "&reqType=0", true);
					      
					      /**
					       * ����������
					       */
					      XHR.onreadystatechange = function(){
				              if(XHR.readyState == 4){
			                      if(XHR.status == 200){
				                      try{
			                      	  	  var rs = XHR.responseText;
				                   	      var nodes = doc.body.childNodes;
			                   		      if("" != rs && rs != null){
			                   		          var preid = eval( "(" + rs + ")")["id"];
			                   		          
			                   		          // У���Ƿ�Ϊ���ļ�,��������ļ�����ȡ��ȷ��״̬
			                   		          if(top.recent != preid){top.confirmed = false;}
			                   		          if(prers < preid){
			                   		          	  // �ļ���δȷ�ϣ���ʾ��Ϣ
				                   		      	  if(!top.confirmed){
					                   		      	  /** �رվɶԻ��� */
					                   		      	  if(dialog) { dialog.close(); }
						                   		      // ����һ���Ի���
							                   		  dialog = new Dialog(win);
													  // ��Ӵ��嵽ҳ��
													  doc.body.appendChild(dialog);
													  // ��ʾ����
													  dialog.viewMsg();
													  
													  // ʱ���
					                   		      	  top.recent = preid;
												  }
					                   		 	  // �ж�ҳ���Ƿ�Ϊ��׼ҳ��
			                   	      			  if(doc.getElementsByTagName("body").length > 0){
						                   		 	  // ���ñ�ʶ
					                   		      	  prers = preid;
				                   		      	  }
											  }
										  }
										  // �ӳ��ط�
										  setTimeout(function(){fun();}, 5 * 60 * 1000);
								      }catch(e){}
			                      }
				              }
					      };
					      XHR.send(null);
			        }();
				}
			}
		-->
	</script>
</head>
<frameset rows="0,*,20" cols="*" id="main" border="0" >
	<frame name="title" scrolling="no" noresize  src="head.jsp?roleno=<%=roleno%>&rolename=<%=rolename%>"  marginwidth="0" marginheight="0" > 
  	<frameset cols="230,10,*" id="left" border="0" >
		<frame NAME="ProductionMenu" ID="ProductionMenu" src="menu.jsp?roleno=<%=roleno%>" marginwidth="0" marginheight="0"  class="frameborder">
		<frame NAME="middle" ID="middle"  scrolling="no" noresize="noresize" src="middle.jsp" marginwidth="0" marginheight="0">
    	<frame NAME="ProductionShow" ID="ProductionShow" src=<%=url%> class="frameborder" onload="initial(this)">
	</frameset>
	<frame name="foot" id="foot" scrolling="no" noresize src="foot.jsp" marginwidth="0" marginheight="0">
</frameset>
<% 
	}catch(Exception e){
		throw e;
	}finally{
		if(con != null){
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				con = null;
			}
		}
	}
%>