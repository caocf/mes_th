package th.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import th.report.entities.RequestParam;

/**
 * @author Administrator
 */
public class JdhzServletPrint extends HttpServlet {
	/** ���к� */
	private static final long serialVersionUID = 1L;
	/** ���ڸ�ʽ������ */
	private SimpleDateFormat date_fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/** ϵͳ��־���� */
	private Logger logger = Logger.getLogger(JdhzServletPrint.class);

	/**
	 * ���ݴ���
	 */
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// ������Ӧͷ
		response.setContentType("application/octet-stream");
		
		// �ӿͻ��˽��յ����������
		RequestParam requestParam = new RequestParam();
		requestParam.setRequestDate(request.getParameter("rq"));// ��������
		requestParam.setChassisNumber(request.getParameter("ch"));// ���̺�
		requestParam.setGroupId(request.getParameter("groupid"));// ��ӡ���
	}
}
