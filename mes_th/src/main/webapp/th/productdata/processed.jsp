<%@ page contentType="text/html;charset=GBK" language="java" pageEncoding="GBK"%>
<%@ taglib uri="http://www.faw-qm.com.cn/mes" prefix="mes"%>

<% 
	/** ��ҳ�治���� */
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	
	String path = request.getContextPath();
%>

<html>
    <head>
        <title>�Ѵ����Ĳ�ѯ</title>
        <meta http-equiv=content-type content="text/html;charset=GBK">
        <link rel="stylesheet" type="text/css" href="../../cssfile/style.css">
        <link rel="stylesheet" type="text/css" href="../../cssfile/th_style.css">
        <script type="text/javascript" src="../../JarResource/META-INF/tag/taglib_common.js"></script>
    </head>
    <body>
    	 <div align="center">
            <div class="title"><strong><!-- InstanceBeginEditable name="����" -->�Ѵ����Ĳ�ѯ<!-- InstanceEndEditable --></strong></div>
            <br/>
            <table cellspacing=0 cellpadding=0 border=1 id="tbl" class="th_table" width="300px">
            	<thead>
            		<tr>
            			<td colspan="2" align="center" style="font-size:25px;">���´�����</td>
            		</tr>
	            	<tr>
	            		<td width="150" align="center" style="font-size:16px;">
		            		�ļ���
		            	</td>
		            	<td width="150" align="center" style="font-size:16px;">
		            		�ļ�����ʱ��
		            	</td>
	            	</tr>
            	</thead>
            	<tbody>
            	</tbody>
            </table>
            <div style="border:solid 1px red;position:relative;top:30px;">
	         	<span>���ݿ�����״̬:</span>
	         	<span id="conn_state">������</span>
         	</div>
         </div>
    </body>
    <script>
    	<!--	
    			/** ajax���� */
   				var HttpXmlReq = false;
   				// ����ajax����
                var createHTTPXMLReq = function(){
	                // FireFox�����
	                if(window.XMLHttpRequest){
	                      HttpXmlReq = new  XMLHttpRequest();
	                }
	                // IE�����
	                else if(window.ActiveXObject){
	                      try{
	                          HttpXmlReq = new ActiveXObject("Msxml2.XMLHTTP");
	                      }catch(e){
	                          try{
	                              HttpXmlReq = new ActiveXObject("Microsoft.XMLHTTP");
	                          }catch(e){
	                               HttpXmlReq=false;
	                          }
	                      }
	                 }
                }();
                /**
                 *  ˢ�����ݽ����
                 */
    			var func = function(){
    				// ������  
    				var tbl = document.getElementById("tbl");
    				// �������
    				var tbody = tbl.getElementsByTagName("tbody")[0];
    				// �����м���
    				var rows = tbody.rows;
    				
    				var innerFunc = function(){
    					// ��������
    					var oneself = arguments.callee;
    					// ָ�Ʊ�ʾ(��ֹ�ظ��ύ)
    					var fingerprint = new Date();
    					// ��������״̬��ʾ����
    					var connText = document.getElementById("conn_state");
    					// ����ʽ��·��
    					HttpXmlReq.open("GET", "<%=path%>/ProcessedServlet?date" + fingerprint, true);
    					// �ص���������
	                    HttpXmlReq.onreadystatechange = function(){
	                    	if(HttpXmlReq.readystate == 4){
	                            if(HttpXmlReq.status == 200){
	                            	// ������������ݼ���
	                                var arr = eval("{" + HttpXmlReq.responseText + "}");
	                                // ���ݿ�����״̬
		                            connText.innerHTML = arr ? "������" : "�ѶϿ�";
		                            // ���ݿ������쳣��ˢ�±��
		                            if(!arr) return;
	                                // ���±��
	                                for(var i = arr.length - 1; i > -1; i--){
	                                	// ��񳤶�Ӧ�ÿ�����10��
	                                	if(rows.length == 5){
	                                		// ���ߵ�һ��
	                                		tbody.removeChild(rows[arr.length - 1]);
	                                	}
	                                	var subarr = arr[i];
	                                	// ����һ������
		                                var tr = document.createElement("tr");
	                                	for(var j = 0; j < subarr.length; j++){
		                                	// ����һ������
		                                	var td = document.createElement("td");
		                                	// ���뷽ʽ
		                                	td.align = "center";
							td.style.cssText = "word-break:break-all";
		                                	// ��Ԫ������
		                                	td.innerHTML = subarr[j];
		                                	// ��ӵ�Ԫ��
		                                	tr.appendChild(td);
	                                	}
	                                	// ������
		                                tbody.insertBefore(tr, tbody.firstChild);
	                                }
	                            }
		                    }
	                    }
	                    // ��������
	                    HttpXmlReq.send(null);
	                    // ��ʱ3���ڽ���ˢ��
	                    setTimeout(function(){
	                    	oneself();
	                    }, 30 * 1000);
    				}();
    			}();
    	-->
    </script>
</html>