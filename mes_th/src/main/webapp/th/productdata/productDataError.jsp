<%@ page contentType="text/html;charset=GBK" language="java" pageEncoding="GBK"%>

<% 
	/** ��ҳ�治���� */
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	
	String path = request.getContextPath();
%>
<html>
	<head>
		<title>�쳣������Ϣ</title>
        <meta http-equiv=content-type content="text/html;charset=GBK">
        <link rel="stylesheet" type="text/css" href="../../cssfile/style.css">
        <link rel="stylesheet" type="text/css" href="../../cssfile/th_style.css">
        <script type="text/javascript" src="../../JarResource/META-INF/tag/taglib_common.js"></script>
	</head>
	<body>
		<div align="center">
           <div class="title"><font color=red>�쳣���Ĳ�ѯ</font></div>
           (�±��г�������ļ����쳣�ļ�)
           <br/>
			<table id="tbl" border=1 width="300px" class="th_table">
			    <thead>
		                <tr>
				    <td align=center style="font-size:16px;" width="150px">�ļ���</td>
				    <td align=center style="font-size:16px;" width="150px">�ļ��޸�ʱ��</td>
				</tr>
			    </thead>
			    <tbody>		
			    </tbody>
			</table>
		</div>
	</body>
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
			function initial(){
			    // ������ 
				var tbl = document.getElementById("tbl");
				// �����
				var tbody = tbl.getElementsByTagName("tbody")[0];
				
				if (XHR) {
			        var sendRequest = function(){
			        	  // ��������
			        	  var fun = arguments.callee;
			        	  // ʱ��� 
			        	  var timestamp = new Date().getTime();
			        	  // ����GET��ʽ�ύ����·��Ϊbom_dialog.jsp
					      XHR.open("GET", "<%=path%>/HintProductDataError?timestamp=" + timestamp + "&reqType=1", true);
					      
					      /**
					       * ����������
					       */
					      XHR.onreadystatechange = function(){
				              if(XHR.readyState == 4){
			                      if(XHR.status == 200){
				                      var result = XHR.responseText;
				                      if("" != result && result != null){ 
				                      	  // ����json����
				                          var jsonObj = eval("(" + result + ")");
				                          while(tbody.hasChildNodes()){
				                          	tbody.removeChild(tbody.lastChild);
				                          }
				                          
				                          // ����json��Ϣ
				                          for(var property in jsonObj){
				                              // ����һ���ж���
				                              var tr = document.createElement("tr");
				                              // ������һ���ж��� (�ļ���)
				                              var col_name = document.createElement("td");
				                              // �����ڶ����ж��� (�ļ�����ʱ��)
				                          	  var col_date = document.createElement("td");
				                          	
				                          	  // ����ļ�������
				                          	  col_name.innerHTML = property;
				                          	  // ����ļ�����������
				                          	  col_date.innerHTML = jsonObj[property];
								  col_name.style.cssText = "word-break:break-all";
				                          	  
				                          	  // ���´����ĵ�Ԫ����ӵ��ж�����
				                          	  tr.appendChild(col_name);
				                          	  tr.appendChild(col_date);
				                          	  
				                          	  // ��������
				                              tbody.appendChild(tr);
				                              // ѭ��ִ��
				                          }
				                      }
			                      }
				                  setTimeout(function(){fun()}, 3000);
				              }
					      };
					      XHR.send(null);
			        }();
				}
			}
			initial();
		-->
	</script>
</html>