package th.util.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * �쳣������ʾ
 * 
 * @author GaoHF
 */
public class HintProductDataError extends HttpServlet {
	/** SerialCode */
	private static final long serialVersionUID = 1L;
	/** ���һ���쳣�ļ�ʱ�� */
	private volatile long lasted;
	/** ���5���쳣���� */
	private static File[] arr = new File[5];
	/** Ŀ¼ */
	private static final String PATH = AccessRegedit.getPath(IProperty.LOCAL_FAILURE_DIR_PATH);
	/** ���ڸ�ʽ */
	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// �ļ�ͷ����
		response.setContentType("text/html;charset=GBK");
		// ���������
		PrintWriter out = response.getWriter();
		// �ļ�����·��
		lastErrorFile(new File(PATH + new SimpleDateFormat("/yyyy/MM/dd").format(new Date())));
		// �������� 0: ��ʾ��  1:�ļ��б�
		String requestType = request.getParameter("reqType");

		// �쳣�ļ���ѯ
		if("1".equals(requestType)){
			out.println("{");
			for(int i = 0; i < arr.length; i++){
				File f = arr[i];
				if(f != null){
					if(i > 0){out.print(",");}

					out.print("\"");
					out.print(f.getName());
					out.print("\":\"");
					out.print(df.format(new Date(f.lastModified())));
					out.print("\"");
				}
			}
			out.println("}");
		}
		// �쳣��Ϣ��ʾ
		else if("0".equals(requestType)){
			if (lasted > 0) {
				String time = df.format(new Date(lasted));
				out.println("{date:\"" + time + "\"}");
			}
		}
		lasted = 0;
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * ������һ���ļ�����������
	 * 
	 * @param dir
	 */
	private synchronized void lastErrorFile(File dir) {
		if (dir.exists()) {
			for (File f : dir.listFiles()) {
				if (f.isDirectory()) {
					lastErrorFile(f);
					continue;
				}
				// �쳣�ļ�����ʱ��
				long modifyTime = f.lastModified();
				// �������쳣�ļ�ʱ��
				lasted = Math.max(lasted, modifyTime);
				// �齨�ļ�����
				swap(f);
			}
		}
	}
	
	/**
	 * �����󼸸��쳣����
	 * 
	 * @param file
	 */
	private synchronized void swap(File file) {
		// �ļ�����
		for (int i = 0; i < arr.length; i++) {
			// ȡ��һ���ļ�����
			File f = arr[i];
			// ǰһ���ļ��޸�ʱ��
			long pre = f != null ? f.lastModified() : 0;
			// ��ǰ�ļ��޸�ʱ��
			long no = file.lastModified();
			
			// �����ļ��������
			if (f == null || pre <= no) {
				arr[i] = file;
				break;
			}
		}
	}
}
