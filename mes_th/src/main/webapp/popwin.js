/**
 * ����Ի���
 */
var Dialog = function(win, date){
		// Document����
		var doc = win.document;
		// ��ͼ
		var view = doc.createElement("div");
		
		// Window����
		view.win = win;
		// Ψһ��ʶ
		view.id = "divMsg";
		// ��ʽ
		view.style.cssText = "BORDER-RIGHT:#455690 1px solid;BORDER-TOP:#a6b4cf 1px solid;Z-INDEX:99999;LEFT:0px;VISIBILITY:hidden;BORDER-LEFT:#a6b4cf 1px solid;WIDTH:180px;BORDER-BOTTOM:#455690 1px solid;POSITION:absolute;TOP:0px;HEIGHT:116px;BACKGROUND-COLOR:#c9d3f3";
		// ����
		view.innerHTML = "<TABLE style=\"BORDER-TOP:#ffffff 1px solid;BORDER-LEFT:#ffffff 1px solid\" cellSpacing=0 cellPadding=0 width=\"100%\" bgColor=#cfdef4 border=0>"
	                   + "<TBODY>"
	                   + "<TR>"
	                   + "<TD style=\"FONT-SIZE:12px;COLOR:#0f2c8c\" width=30 height=24></TD>"
	                   + "<TD style=\"FONT-WEIGHT:normal;FONT-SIZE:12px;COLOR:#1f336b;PADDING-TOP:4px;PADDING-left:4px\" vAlign=center width=\"100%\"> ����Ϣ��ʾ��</TD>"
	                   + "<TD style=\"PADDING-TOP:2px;PADDING-right:2px\" vAlign=center align=right width=19>"
                       + "<span id=\"closeButton\" title=�ر� style=\"CURSOR:hand;color:red;font-size:12px;font-weight:bold;margin-right:4px;\">��</span>"
                       + "</TD>"
                       + "</TR>"
                       + "<TR>"
                       + "<TD style=\"PADDING-RIGHT: 1px;PADDING-BOTTOM: 1px\" colSpan=3 height=90>"
                       + "<DIV style=\"BORDER-RIGHT:#b9c9ef 1px solid;PADDING-RIGHT:13px;BORDER-TOP:#728eb8 1px solid;PADDING-LEFT:13px;FONT-SIZE:12px;PADDING-BOTTOM:13px;BORDER-LEFT:#728eb8 1px solid;WIDTH:100%;COLOR:#1f336b;PADDING-TOP:18px;BORDER-BOTTOM:#b9c9ef 1px solid;HEIGHT:100%\">"
                       + "<FONT COLOR=#000000>"
                       +  (date ? date : showDate())
                       +"</FONT><BR><BR>"
                       + "<DIV align=center style=\"word-break:break-all\">"
                       + "<FONT COLOR=\"#FF0000\" size=\"3pt\">��������KIN��</FONT><BR><BR>"
                       + "<DIV STYLE=\"WIDTH:100%;TEXT-ALIGN:RIGHT;OVERFLOW:HIDDEN;\"><A HREF=\"/mes_th/th/productdata/container.jsp\" onclick=\"javascript:top.confirmed=true;\">�鿴����</A></DIV>"
                       + "</DIV>"
                       + "</DIV>"
                       + "</TD>"
                       + "</TR>"
                       + "</TBODY>"
                       + "</TABLE>";
        // ���¼�
        attachEvent(view);
        
        // ���ض���
		return view;
};

/**
 * ���¼�
 */
function attachEvent(obj){
	/** ����λ�õ���ر��� */
	var divTop, divLeft, divWidth, divHeight, docHeight, docWidth, objTimer, i = 0;
	/** Document���� */
	var docu = obj.win.document;
	/** ��ʱ�� */
	var objTimer;
	
	/**
	 * ��ʾ��Ϣ
	 */
	obj.viewMsg = function(){
	   try{
	     divTop = parseInt(docu.getElementById("divMsg").style.top, 10);// div��x����
	     divLeft = parseInt(docu.getElementById("divMsg").style.left, 10);// div��y����
	     divHeight = parseInt(docu.getElementById("divMsg").offsetHeight, 10);// div�ĸ߶�
	     divWidth = parseInt(docu.getElementById("divMsg").offsetWidth, 10);// div�Ŀ��
	     docWidth = docu.body.clientWidth;// ��ȡ������
	     docHeight = docu.body.clientHeight;// ���ô���߶�
	     docu.getElementById("divMsg").style.top = parseInt(document.body.scrollTop, 10) + docHeight + 10;// ����div��Y����
	     docu.getElementById("divMsg").style.left = parseInt(docu.body.scrollLeft, 10) + docWidth - divWidth;// ����div��X����
	     docu.getElementById("divMsg").style.visibility = "visible";// ����div��ʾ
	     
	     // ���ö�ʱ��
	     objTimer = this.win.setInterval(function(){
	     	obj.moveDiv();
	     }, 10);
	   }catch(e){}
	}
	/**
	 * ��������div�ߴ�
	 */
	obj.resizeDiv = function(){
	   try{
	     divHeight = parseInt(docu.getElementById("divMsg").offsetHeight, 10);// ����div�߶�
	     divWidth = parseInt(docu.getElementById("divMsg").offsetWidth, 10);// ����div���
	     docWidth = docu.body.clientWidth;// ��ȡ������
	     docHeight = docu.body.clientHeight;// ���ô���߶�
	     docu.getElementById("divMsg").style.top = docHeight - divHeight + parseInt(docu.body.scrollTop, 10);// ����div��y����
	     docu.getElementById("divMsg").style.left = docWidth - divWidth + parseInt(docu.body.scrollLeft, 10);// ����div��x����
	   }catch(e){}
	}
	
	/**
	 * �ƶ�����
	 */
	obj.moveDiv = function(){
      try{
	     if (parseInt(docu.getElementById("divMsg").style.top,10) <= (docHeight - divHeight + parseInt(docu.body.scrollTop, 10))){
	       this.win.clearInterval(objTimer);
	       objTimer = this.win.setInterval(function(){
	       		obj.resizeDiv();
	       }, 1);// ����div��λ�úʹ�С
	     }
	     divTop = parseInt(docu.getElementById("divMsg").style.top, 10);// ��ȡy����
	     docu.getElementById("divMsg").style.top = divTop - 1;// ����div��Y����
	   }catch(e){}
	}
	/**
	 * �ر��Ӵ�
	 * */
	obj.close = function(){
		// �����ʱ��
		if(objTimer) obj.win.clearInterval(objTimer);
		var nodes = docu.body.childNodes;
		for(var i = 0; i < nodes.length; i++){
			var node = nodes[i];
			if(node == obj){
				// �Ƴ��Ƴ��ڵ�
				docu.body.removeChild(obj);
			}
		}
	}
	
	/**
	 * �رմ���
	 */
	var closeDialog = function(){
		// �رհ�ť
		var closeButton = obj.getElementsByTagName("span")[0];
		// ��ȷ��
		top.recognize = true;
	   	// �ر��¼�
	   	closeButton.onclick = obj.close;
	}();
	// ���ݴ���߶ȺͿ�ȣ��ı����Ϣ��ʾ��ĸ߶ȺͿ��
	obj.win.onresize = obj.resizeDiv;
	// ���ִ���ʱ�������κδ���
	obj.win.onerror = function(){};
}

/**
 * ��ʾ����
 */
function showDate(){
	var digit = 10;
	var date = new Date();
	var year = date.getYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	var hour = date.getHours();
	var minute= date.getMinutes();
	var second = date.getSeconds();
	
	return  year 
	       + "-"  
	       + (month > digit ? month : "0" + month)
	       + "-" 
	       + (day > digit ? day : "0" + day)
	       + " " 
	       + (hour > digit ? hour : "0" + hour)
	       + ":" 
	       + (minute > digit ? minute : "0" + minute)
	       + ":" 
	       + (second > digit ? second : "0" + second);
}
