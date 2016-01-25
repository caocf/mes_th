package mes.framework;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

class DefProcess extends DefElement implements IProcess {

	private List<IProcessItem> pis = new Vector<IProcessItem>();

	private final Log log = LogFactory.getLog(DefProcess.class);

	public ExecuteResult doProcess(IMessage message) {
		List<IProcessItem> pros = new ArrayList<IProcessItem>(5);
		IServiceBus bus = ServiceBusFactory.getInstance();
		log.debug("�������̣�" + getId() + "��" + this.getNameSpace() + "."
				+ this.getName());
		String preStr = "\t";
		for (IProcessItem pi : pis) {
			ExecuteResult result = doProcessItem(bus, pi, message, true, preStr
					+ "����");

			// ���������н�����쳣������Ը�����Դ����̴˷������õ��쳣����ʽ����
			log.debug(preStr + "�������н����" + result);
			if (result == ExecuteResult.fail) {
				switch (pi.getExceptionDisposeType()) {
				case rollback:
					log.debug(preStr + "�����ع������̿�ʼ�������С�");
					rollback(pros, bus, message, preStr);
				case exit:
					log.debug(preStr + "���������˳���");
					return result;
				case ignore:
					log.debug(preStr + "�������ԣ����̼������С�");
					break;
				}
			}
			pros.add(pi);
		}
		log.debug("����������ϡ�");
		return ExecuteResult.sucess;
	}

	private void rollback(List<IProcessItem> pros, IServiceBus bus,
			IMessage message, String preStr) {
		log.debug(preStr + "��ʼ���̻ع�");
		for (int i = pros.size() - 1; i >= 0; i--) {
			log.debug(preStr
					+ "����ع����н����"
					+ doProcessItem(bus, pros.get(i), message, false, preStr
							+ preStr + "�ع�"));
		}
		log.debug(preStr + "�������̻ع�");
	}

	private ExecuteResult doProcessItem(IServiceBus bus, IProcessItem pi,
			IMessage message, boolean isdo, String preStr) {
		log.debug(preStr + "������Ŀ��" + pi.getServiceName() + "������ID��"
				+ pi.getServicdId() + "��ִ��˳��" + pi.getSort() + "���쳣����ʽ��"
				+ pi.getExceptionDisposeType());
		ExecuteResult result = ExecuteResult.fail;
		IMessage message2 = null;
		try {
			// �����������Ϣ����
			message2 = MessageAdapterFactory.getMessage(this.getId(), pi
					.getServiceName(), message);
			if (isdo)
				result = bus.doService(pi.getServicdId(), this.getId(),
						message2);
			else {
				IService ser = ServiceFactory.getInstance(pi.getServicdId());
				if (ser != null)
					result = ser.undoService(message2, getId());
			}
		} catch (Exception e) {
			log.fatal("���̣�" + this.getId() + "����" + this.getNameSpace() + "."
					+ this.getName() + "���ķ���:" + pi.getServicdId() + ","
					+ pi.getServiceName() + "���ǳ�����������ע�⣺����Ӧ�׳��쳣��\n" + "�쳣��Ϣ��"
					+ e.getMessage());
			result = ExecuteResult.fail;
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, "�����׳�δ֪�쳣",
					pi.getServicdId(), this.getId(), new Date(), e));
		} finally {
			// ������ɹ�����������������ͷŶ�ԭ��Ϣ��������ã��ͷ���Դ
			if (message2 != null && message2 instanceof IMessageAdapter) {
				((IMessageAdapter) message2).setSource(null);
			}
		}
		return result;
	}

	public void addProcessItem(IProcessItem pi) {
		pis.add(pi);
	}

}
