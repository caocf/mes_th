/**
 *�����ڲ�ѯ����
 */
function getPrtDate() {
	window.location = "printPHD.jsp?rq=" + document.getElementById("rq").value;
}

/** 
 * �ظ���ӡ�����ܺţ����� ��ӡprint_data������
 */
function reprint(groupId) { 
 	innerPrint("document.app.ppr", groupId);
}

/**
 * ���ݲ���
 */
function printOld(groupId) {
 	innerPrint("document.app.ppHistory", groupId);
}

/**
 * �ڲ���ӡ����
 */
function innerPrint(methodExp, groupId) {
	var rq = document.getElementById("rq").value;
	var jsch = "1";
	var jsOldJh = "oldjh" + groupId;
	var jsjh = document.getElementById(jsOldJh).value;
	var printPath = document.getElementById("basepath").value + "servlets";
	
	if(jsjh == 0) {
		alert("������ܺ�");
		return false;
	}

	// ���÷���
	eval(methodExp + "('" + rq + "','" + jsch + "','" + jsjh + "','" + printPath + "','" + groupId + "')");
}

/**
 * ���õ���ӡ
 *
 * @param groupid        ��ӡ������Id
 * @param jPrintRadio    ��ӡ����
 * @param ch             �����ı�����Ϊ�˼����Զ�����
 * @param pages          ��ӡҳ��
 * @param minPartCount   ��С��������
 * @param PerTimeRow     ÿ�е�������
 * @param IsContinu      Vin�Ƿ�����
 */
function openApp(groupid, jPrintRadio, ch, pages, minPartCount, PerTimeRow, IsContinu) {
    var rq = document.getElementById("rq").value;// ��ǰ�Ĳ�ѯʱ��
    var printPath;// ��ӡ�ļ�·��
    var ls = 1;// ��ӡ���ݵı���

    // ��ӡ���������·��
    printPath = document.getElementById("basepath").value + "servlets";

    // ���ô�ӡ����
    switch(parseInt(jPrintRadio)) {
    	case 2: ls = 2; break;
    	case 3: ls = 4; break;
    }

	// ���Vin������������Ҫ���û�ȷ�ϴ�ӡ����
	if(IsContinu === "0" && !window.confirm("vin���������Ƿ��ӡ")) { return; }

	//��ʾ���������Ƿ��ӡ
	if(minPartCount < PerTimeRow && !window.confirm("���ݲ����Ƿ��ӡ��")) { return; }

   	// ��ӡ���õ� (ֻҪ�ܹ���ӡ������ΪVin�������ģ��������һ������Ϊtrue)
	document.app.pp(rq, ch, ls, printPath, groupid, pages);

	// ҳ������
	window.location.reload();
}

// ����ҳ����Զ�ˢ�¼�ʱ��(ÿ������ˢ��һ��)
setTimeout(function(){
	window.location.href = "printPHD.jsp";
}, 3 * 60 * 1000);