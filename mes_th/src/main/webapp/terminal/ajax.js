var XHR;
/**
 * XMLHttpRequest���캯��
 */
function createXMLHttpRequest() {
	var request = false;
	if (window.XMLHttpRequest) {
		if (typeof XMLHttpRequest != 'undefined') {
			try {
				request = new XMLHttpRequest();
			} catch (e) {
				request = false;
			}
		}
	} else if (window.ActiveXObject) {
		try {
			o
			request = new ActiveXObject('Msxml2.XMLHTTP');
		} catch (e) {
			try {
				request = new ActiveXObject('Microsoft.XMLHTTP');
			} catch (e) {
				request = false;
			}
		}
	}
	return request;
}
XHR = createXMLHttpRequest();// ����XMLHttpRequest����

