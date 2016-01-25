package mes.pm.service.datarule;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.MissingResourceException;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import mes.framework.AdapterService;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import mes.pm.factory.DataRuleFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ���ݹ���У��
 * @author Xujia
 * 
 */
public class TestDataRule extends AdapterService {
	/**
	 * ����ʽ
	 */
	private String rule = null;
	/**
	 * int_id
	 */
	private String int_id = null;
	/**
	 * ����
	 */
	private String args = null;
	/**
	 *  ��������
	 */
	private String count = null;
	/**
	 * ����
	 */
	private String name = null;

	BigDecimal result1 = new BigDecimal("0");

	DataRuleFactory factory = new DataRuleFactory();

	// ��־
	private final Log log = LogFactory.getLog(TestDataRule.class);

	@Override
	public boolean checkParameter(IMessage message, String processid) {
		// con = (Connection) message.getOtherParameter("con");
		args = message.getUserParameterValue("args"); // �������
		rule = message.getUserParameterValue("rule"); // ����ʽ
		int_id = message.getUserParameterValue("int_id");
		count = message.getUserParameterValue("count"); // ��������
		name = message.getUserParameterValue("name"); // ������

		// ���log��Ϣ
		String debug = "����ʽ��" + rule + " ����Ϊ��" + args + " ��������Ϊ��" + count
				+ " nameΪ" + name + " ���Ϊ��" + int_id;
		log.debug("������ݹ���ʱ�û��ύ�Ĳ���: " + debug);

		String[] newargs = args.split(";"); // ��ֳ���
		String func = ""; // ������
		Double[] tt = new Double[Integer.parseInt(count)]; // ��������
		for (int j = 0; j < newargs.length; j++) {

			int size = newargs[j].lastIndexOf(","); // ��ֳɱ�������������ʽ
			String start = newargs[j].substring(0, size).trim(); // ȡ������
			String end = newargs[j].substring(size + 1, newargs[j].length())
					.trim(); // ȡ����
			func = func + start + ",";
			tt[j] = Double.parseDouble(end);
			log.info("start:" + start + " end:" + end + " func:" + func
					+ "tt[j]=" + tt[j]);
			// rule=rule.replace(start,end); //����ʽ�еĲ�����ʵ����������滻
		}
		func = func.substring(0, func.length() - 1);
		log.info("fun:" + func);
		// log.info("���չ�ʽΪ��"+"\n"+"\n"+rule+"\n");
		Object obj = null;
		String script = "function " + name + "(" + func + ")" + "{ " + rule
				+ "}";
		log.info("script: " + script);
		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("JavaScript");

		try {
			engine.eval(script);
			Invocable inv = (Invocable) engine;
			// String [] tt = {"3","1"};
			obj = inv.invokeFunction(name, tt);					
			// System.out.println("2***");
		} catch (ScriptException e) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, "�ű��쳣:������"
							+ e.getLineNumber() + "�У���" + e.getColumnNumber()
							+ "�С��쳣ԭ��Ϣ��" + e.toString(), this.getId(),
					processid, new java.util.Date(), e));
			log.error("�ű��쳣���жϷ���");
			return false;
		} 
		catch(MissingResourceException a){
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, "���������͹���ƥ���쳣�������������"
							+ a.getKey()
							+ "��", this.getId(),
					processid, new java.util.Date(), a));
			log.error("�����͹���ƥ���쳣���жϷ���");
			return false;
		}catch (NoSuchMethodException b) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, "ȱ������/�����쳣��", this.getId(),
					processid, new java.util.Date(), b));
			log.error("ȱ������/�����쳣���жϷ���");
			return false;
		}

		message.setOutputParameter("result", String.valueOf(obj));

		return true;
	}

	
	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {

			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, "��ʽΪ��" + rule, this.getId(),
					processid, new Date(), null));

		} catch (Exception e) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
					processid, new java.util.Date(), e));
			log.error("δ֪�쳣���жϷ���");
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
	}

	@Override
	public void relesase() throws SQLException {

		int_id = null;
		args = null;
		count = null;
		rule = null;
		// con = null;

	}

}
