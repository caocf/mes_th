/** ��ǰ��ʾ��Ϣ���� */
var curbtn;
/** ������������  */
var dialogname = "fore";
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
 * �رնԻ���
 */
function closeDialog(){
	// ǰ�ô���
    var d = document.getElementById(dialogname);
    // ���ش���
    d.style.visibility = "hidden";
}

/**
 * ��ʾ�Ի���
 */
function showDialog(obj,  page, info){
	// ҳ��
    var pageNum = page ? page : 1;
    // �����ؼ���
    var searchItem = info ? info : "";
	// ��꽹�Ӱ�ť�ƿ�
    obj.blur();
    
    if (XHR) {
	      // ����GET��ʽ�ύ����·��Ϊbom_dialog.jsp
	      XHR.open("GET", "bom_dialog.jsp?parttype=" + obj["alt"] + "&page=" + pageNum + "&info=" + searchItem, true);
	       
	      /**
	       * ����������
	       */
	      XHR.onreadystatechange = function(){
	            // ǰ�ô��ڶ���
	            var d = document.getElementById(dialogname);
	            // ҳ����
	            var b = document.body;
	            // ���ڸ߶�
	            d.style.height = b.clientHeight;
	            // ���ڿ��
	            d.style.width = b.clientWidth;
	            // ��ʾ����
	            d.style.visibility = "visible";
	            // ��������
	            d.style.paddingTop = b.clientHeight / 5;
	            
	            if(XHR.readyState == 4){
	                   if(XHR.status == 200){
	                   	     // ��ǰ�����ť
	                   	     curbtn = obj;
	                   	     // ����������ʾ����
	                         var container = document.getElementById("subwin");
	                         // �����ʾ����
	                         container.innerHTML = XHR.responseText;
	                         
	                         return true;
	                   }
	                   closeDialog();
	           }
	      };
	      XHR.send(null);
   }
}

/**
 * �������ô�С
 */
function resizeDialog(){
	// ǰ�ô������
    var d = document.getElementById(dialogname);
    // ҳ����
    var b = document.body;
    // ��ǰ�����Ƿ�ɼ�
    if(d.style.visibility == "visible"){
    	// ����߶�
        d.style.height = b.clientHeight;
        // ������
        d.style.width = b.clientWidth;
        // ��������
        d.style.paddingTop = b.clientHeight / 5;
    }
}

/**
 *  �����ͣ
 */
function mousehover(trobj){
	if(document.body["clickedObj"] != trobj){
	   trobj.className = "mousehover";
	}
}
   
/**
 *  ����ƿ�
 */
function mouseout(trobj){
	if(document.body["clickedObj"] != trobj){
       trobj.className = "";
    }
}
   
/**
 *  ��굥��
 */
function mouseclick(trobj){
	   //  ֮ǰ��ѡ�еĶ���
       var presel = document.body["clickedObj"];
       if(presel){
       	    presel.className = "";
       }
       // ����ѡ�ж�������
       document.body["clickedObj"] = trobj;
       // ����ѡ��״̬
       trobj.className = "selected";
}
   
/**
 *  ���˫��
 */
function mousedblclick(trobj){
	   // ��굥��
	   mouseclick(trobj);
	   // �ύ;
	   apply(trobj);
}

/**
 * �ύ
 */
function apply(trobj){
	   var trobj = trobj ? trobj : document.body["clickedObj"];
       // �ı�������
       var inpname = curbtn["alt"] == '00001' ? "primary" : "sub";
	   // Ҫ�����ı���
	   var userinp = document.getElementById(inpname);
	   
	   if(trobj && trobj.cells.length > 1){
	        // ��ֵѡ�����ݵ��ı���
	        userinp.value = trobj.cells[1].innerHTML;
	        // �رնԻ���
	        closeDialog();
	   }
}

/**
 * ��У��
 * 
 * @param thisform ������
 */
function checkinput(thisform){
	// ���������ʽ
    var regexp =  /^[0-9]+$/;
    // Ŀ��ҳ��
    var value = thisform["page"].value;
    
    // �ж������ʽ
    if(!regexp.test(value)){
    	window.alert("�����ҳ������,����������!");
	    thisform["page"].value = "1";
	    return false;       
	}
	// ��ҳ
	turnPage(value);
	// ���Ա������ύ
	return false;
}

/**
 * ҳ����ת
 * 
 * @param page ҳ��
 */
function chagePage(page){
	// ҳ��
    var npage = page ? page : 1;
    // ��ҳ
    turnPage(npage);
}

/**
 * ��ҳ
 * 
 * @param npage ҳ��
 */
function turnPage(npage){
	var searchItem = document.getElementById("info").value;
	// ��ʾ����
	showDialog(curbtn, npage, searchItem);
} 

function search(formObj){
	  // ��ʾ����
	  showDialog(curbtn, 1, formObj["info"].value);
	  // ˢ�±��
	  return false;
}