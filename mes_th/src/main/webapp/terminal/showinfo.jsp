<%@ page contentType="text/html;charset=GBK" pageEncoding="GBK" language="java"%>
<%session.setAttribute("maxIndentify", "");%>
<html>
	<head>
		<meta http-equiv=content-type content="text/html;charset=GBK">
		<script type="text/javascript" language="javascript" src="ajax.js"></script>
		<link rel="stylesheet" type="text/css" href="../cssfile/th_style.css">
	</head>
	<body bgColor="#000000" scroll="no"> 
		<table border="0" cellpadding="0" cellspacing="0" class="th_table" id="th_thead">
			<thead>
				<tr>
					<td width="180" alt="name" align="center">�����״̬</td>
					<td width="180" alt="code" align="center">״̬����</td>
					<td width="180" alt="id" align="center">�������</td>
					<td width="180" alt="car"  align="center">����</td>
					<td width="180" alt="doorno" align="center">�����ź�</td>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
		<div id="mydiv">
			<table border="0" cellpadding="0" cellspacing="0" class="th_table" id="th_tbody">
				<tbody>
				</tbody>
			</table>
		</div>
	</body>
	<script>
		<!--
			/**
			 * ������Ϣ
			 *
			 * @param requestID  ����ID
			 */
			var callServer = function(requestID){
				/** �������� */
				var oneself = arguments.callee;
				/** ͷ��Ϣ */
				var tbl_head = document.getElementById("th_thead");
				/** ��ͷ */
				var th = tbl_head.getElementsByTagName("thead")[0];
				/** ������Ϣ */
				var tbl_body = document.getElementById("th_tbody");
				/** ����� */
				var tbody = tbl_body.getElementsByTagName("tbody")[0];
				/** ͷ��Ԫ�񼯺� */
				var headers = th.rows[0].cells;
				/** ʱ��� */
				var timestamp = new Date().getTime();
				/** �����ַ */
				var url = "scrolling.jsp?date=" + timestamp + "&id=" + requestID;
				
				// ����ʽ
    			XHR.open("GET", url, true);
    			
    			// �ص���������
                XHR.onreadystatechange = function(){
                	if(XHR.readystate == 4){
                        if(XHR.status == 200){
                        	 /** �������� */
                		     var feedback = XHR.responseText;
                		     /** ʵ�����ص����� */
                		     var arr = eval(feedback);
                		     for(var i = 0; i < arr.length; i++){
                		         var jsonobj = arr[i];
                		         var trobj = document.createElement("tr");
                		         
                		         for(var j = 0; j < headers.length; j++){
                		         	 var tdobj = document.createElement("td");
                		         	 var tobj = headers[j];
                		         	 var pro = tobj.alt;
                		         	 var val = jsonobj[pro];
									
								     tdobj.align = "center";
								     tdobj.width = tobj.width;
								     tdobj.height = 50;
                		     	 	 tdobj.innerHTML = val ? val : "-----";
 	               		     	 	 trobj.appendChild(tdobj);
 	               		     	 	 
 	               		     	 	 /** �������� */
 	               		     	 	 if(pro == "id"){requestID = Math.max(requestID, val);}
                		         }
                		      
                		     	 tbody.appendChild(trobj);
                		     }
                		     while(tbody.hasChildNodes()){
								var node = tbody.firstChild;
								var height = node.offsetHeight;
								if(scrollElem.scrollTop <= 2 * height){break;}
								tbody.removeChild(node);
								scrollElem.scrollTop -= height;
							 }
                		     /**
                		      * ��ʱѭ������
                		      */
                		     setTimeout(function(){
                		     	oneself(arr.length ? requestID : 0);
                		     // ������ʱ��	
                		     }, 5 * 1000);
                		     
                		      /**
                		      * ��ʱˢ��
                		      */
                		     setTimeout(function(){
                		     	location.reload();
                		     // ������ʱ��	
                		     }, 150 * 1000);
                        }
                    }
                }
                // ��������
	            XHR.send(null);
			}(0);
			
			// �����ߴ�
			marque(1004, 700, "icefable1", "box1left")
		    var scrollElem;
		    var stopscroll;
		    var marqueesHeight;
		    
			//Ϊ�������¼�
			function marque(width, height, marqueName, marqueCName){
				try{
				    // ����Ļ�ĸ߶�
			    	marqueesHeight = height;
			    	// ֹͣ�Զ�������ʶ
			  		stopscroll = false;
					// �ɹ���Ԫ��
			  		scrollElem = document.getElementById("mydiv");
			  		// ���ù���Ԫ����ʽ
			  		with(scrollElem){
						style.width = width;
						style.height = marqueesHeight;
						style.overflow = "hidden";
						noWrap = true;
			  		}
			  		// ��������¼� --- �����������ڵ�Ԫ����ʱ��ֹͣ�Զ�����
			  		scrollElem.onmouseover = new Function('stopscroll = true');
			  		// ����ƿ��¼� --- ����ƿ������Զ���������
			  		scrollElem.onmouseout  = new Function('stopscroll = false');
			  		// ��ʼ������Ԫ��
			  		init_srolltext();
				}catch(e) {}
			}
			
			/**
			 * �������ĳ�ʼ��
			 */
			function init_srolltext(){
			    scrollElem.scrollTop = 0;
			    // ��������
			    setInterval("scrollUp()", 18);
			}
			
			/**
			 * ���Ϲ����ķ���
			 */
			function scrollUp(){
		  		if(stopscroll) {return;}
		  		scrollElem.scrollTop += 1;
			}
			
			/**
			 * ���ñ���С
			 window.onresize = function(){
				var screenHeight = document.body.clientHeight;
				var scrollHeight = document.body.scrollHeight;
				scrollElem.style.height = screenHeight - scrollHeight;
			}
			 */
			
		-->
	</script>
</html>