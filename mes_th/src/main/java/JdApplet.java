import java.applet.Applet;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * ���õ���ӡ
 * 
 * @author Ajaxfan
 */
public class JdApplet extends Applet {
    /***/
	private static final long serialVersionUID = 3007717280460840364L;

	// �ظ���ӡ�������ڣ��ܺŴ�ӡprint_data�е����ݣ�
    // ��ڲ�����rq ���ڣ�ch�����ţ�js���ܺţ�ls����ӡ��������ӡҳ����groupid ���id
    public void ppr(String rq, String ch, String js, String path, String groupid) {
        String urlbase = path + "/rePrint?";

        try {
            URL url = new URL(getCodeBase(), urlbase + "rq=" + rq + "&ch=" + ch + "&js=" + js + "&groupid=" + groupid);

            printAction((JasperPrint) JRLoader.loadObject(url));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "��ӡִ��ʧ�ܣ�������Ϣ��鿴������־");
            e.printStackTrace();
        }
    }

    // ��ʷ��ӡ �����ڡ��ܺţ���cardata�ж����ݣ�Ȼ��д��print_data��
    // ��ڲ�����rq ���ڣ�ch�����ţ�js���ܺţ�ls����ӡ��������ӡҳ����groupid ���id
    public void ppHistory(String rq, String ch, String js, String path, String groupid) {
        String urlbase = path + "/historyPrint?";

        try {
            URL url = new URL(getCodeBase(), urlbase + "rq=" + rq + "&ch=" + ch + "&js=" + js + "&groupid=" + groupid);

            printAction((JasperPrint) JRLoader.loadObject(url));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "��ӡִ��ʧ�ܣ�������Ϣ��鿴������־");
            e.printStackTrace();
        }
    }

    // �ظ���ӡ���û���ӡ�������ϵ������ʱ���ӵڶ���֮��ִ�д˳ǳ���
    // ��ڲ�����rq ���ڣ�ch�����ţ�js���ܺţ�ls����ӡ��������ӡҳ����groupid ���id
    public void ppm(String rq, String ch, String ls, String path, String groupid) {
        String urlbase = path + "/mjprint?";
        ch = ch.trim();

        try {
            for (int i = 0, temp = 1; i < Integer.valueOf(ls); i++, temp++) {
                URL url = new URL(getCodeBase(),
                        urlbase + "rq=" + rq + "&ch=" + ch + "&js=" + temp + "&groupid=" + groupid);

                printAction((JasperPrint) JRLoader.loadObject(url));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "��ӡִ��ʧ�ܣ�������Ϣ��鿴������־");
            e.printStackTrace();

        }
    }

    // ��ڲ�����rq ���ڣ�ch�����ţ�js���ܺţ�ls����ӡ��������ӡҳ����groupid ���id
    public void pp(String rq, String ch, String ls, String path, String groupid, String pages) {
        String urlbase = path + "/jprint?";
        ch = ch.trim();

        List<JasperPrint> jaspers = new ArrayList<JasperPrint>();

        try {
            // �����ݱ�������
            for (int i = 0, temp = 1; i < Integer.valueOf(ls); i++, temp++) {
                // ����ģ������·��
                URL url = new URL(getCodeBase(), urlbase + "rq=" + rq + "&ch=" + ch + "&js=" + temp + "&groupid="
                        + groupid + "&isContinu=" + true);
                // �������
                jaspers.addAll((List<JasperPrint>) JRLoader.loadObject(url));
            }

            // ��ӡ��ݣ��������Լ��ٺ����ݿ�Ľ���
            for (int j = 0; j < Integer.valueOf(pages); j++) {
                for (JasperPrint jasper : jaspers) {
                    printAction(jasper);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "��ӡִ��ʧ�ܣ�������Ϣ��鿴������־");
            e.printStackTrace();
        }
    }

    /**
     * �����ӡ
     * 
     * @param jasperPrint
     *            �������
     */
    private void printAction(JasperPrint jasperPrint) throws Exception {
        JasperPrintManager.printReport(jasperPrint, false);
    }
}
