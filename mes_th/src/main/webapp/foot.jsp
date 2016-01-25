<%@ page language="java" contentType="text/html;charset=gb2312"%>
<% 
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<style>
			.alert_msg {
				overflow:hidden;
				font-style:italic;
				color:#da5a1b;
				font-weight:bolder;
				float:right;
				margin-right: -120px;
				background: URL('images/footbar.gif') no-repeat;
				width:120px;
				padding-left:10px;
			}
		</style>
		<script language="javascript" src="<%=basePath%>js/jquery-1.11.0.min.js"></script>
	</head>
	<body bottommargin="0" leftmargin="0" rightmargin="0" topmargin="0">
		<div>
			<a id="msg" class="alert_msg" href="<%=basePath%>th/stat/newCarList.jsp" target="_blank" onfocus="this.blur()">�����³���</a>
		</div>
	</body>
	<script>
		$(document).ready(function(){
			 /**
			  * ����ѯ�ķ�ʽ�����̨���ݣ�֪����̨���ݱ�������Ϊֹ
			  */
			(function(){
				var selobj = arguments.callee;
				/**
				 * ��ȡ���µ�����
				 */
				$.ajax({
				   	type: "POST",
				   	cache: false,
				   	url: "<%=basePath%>NewCarDataServlet?t=" + new Date(),
				   	timeout: 60 * 1000,
				   	success: function(msg){
				   	    // ��ʾ������
						var obj = $('.alert_msg');
						// ��ʾ��ʾ���Ķ����¼�
						obj.animate({marginRight: '0px'}, 2 * 1000);
						
						/**
						 * ����ʾ���¼�
						 */
						obj.bind('click', function(){
						    // �ύһ������֪ͨ������Ѿ����û�ȷ�Ϲ��ˣ�����ɾ���ļ���
							$.ajax({
								type: "GET", 
								url: "<%=basePath%>NewCarDataServlet?t=" + new Date(),
								success: function(msg){
									setTimeout(function(){selobj()}, 5 * 1000);
								}
							});
							// ����ʾ��һ��ҳ��
							obj.animate({marginRight: '-120px'}, 2 * 1000);
							// ȡ���󶨵�Click�¼�
							obj.unbind('click');
						});
					},
					// ������������������ύһ������
					error: function(msg){
						selobj();
					}
				});
			})();
		});
	</script>
</html>
