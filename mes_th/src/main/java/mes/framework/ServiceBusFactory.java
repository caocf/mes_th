package mes.framework;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import common.Conn;

/**
 * �������߹������˹���Ӧ�õ���ģʽ
 * 
 * @author �Ź��� 2007-6-21
 */
public final class ServiceBusFactory {
	/**
	 * �������߶���
	 */
	private static IServiceBus esb = null;

	/**
	 * Ĭ�ϵķ��������࣬�����ֹ��������ʣ����������ڲ��ࡣ
	 * 
	 * @author �Ź��� 2007-6-21
	 */
	private static class DefServiceBus implements IServiceBus {

		/**
		 * ͨ����������ݿ����ӳ�ʼ��ϵͳ����
		 * 
		 * @param con
		 *            ���ݿ�����
		 * @throws SQLException
		 */
		public DefServiceBus(Connection con) throws SQLException {
			// �������еķ���
			// ServiceFactory.loadAllService(con);
			// �������е�����
			ProcessFactory.loadAllProcess(con);
			// �������е���Ϣ������
			MessageAdapterFactory.loadAllMessageAdapter(con);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see zgl.realtool.mes.framework.IServiceBus#doService(java.lang.String,
		 *      java.lang.String, zgl.realtool.mes.framework.IMessage)
		 */
		public ExecuteResult doService(String serviceid, String processid,
				IMessage message) {
			ExecuteResult result = ExecuteResult.fail;
			// ��ö�Ӧ�ķ���
			IService s = ServiceFactory.getInstance(serviceid);
			if (s == null) {
				// �����񲻴��ڣ��򷵻�����ʧ�ܣ�����¼�쳣��Ϣ��
				message.addServiceException(new ServiceException(
						ServiceExceptionType.SERVICELOST, "���񲻴���", serviceid,
						processid, new Date(), null));
				return ExecuteResult.fail;
			}
			// ������ܵ���message���Ѿ���װ���������������ˣ���������ֱ�ӵ��ü��ɡ�
			result = s.doService(message, processid);
			return result;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see zgl.realtool.mes.framework.IServiceBus#doProcess(java.lang.String,
		 *      zgl.realtool.mes.framework.IMessage)
		 */
		public synchronized ExecuteResult doProcess(String processid, IMessage message) {
			IProcess process = ProcessFactory.getInstance(processid);
			if (process == null) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.UNKNOWN, "���̲�����", "-1", processid,
						new Date(), null));
				return ExecuteResult.fail;
			}
			ExecuteResult result = process.doProcess(message);
			return result;
		}

	}

	public static IServiceBus getInstance() {
		if (esb != null)
			return esb;
		// TODO ϵͳ�û��������������Ӻ��û�ʹ�õ�������ͨ����ͬ�û���¼�����ݿ�����ӡ�
		try {
			//���������ļ�
			//PropertyConfigurator.configure("properties.lcf");
			Connection con =  (new Conn()).getConn();
			esb = new DefServiceBus(con);
			con.close();
			return esb;
		} catch (SQLException e) {
			e.printStackTrace();
			// ��ʼ������ʧ�ܣ�ϵͳ�޷���������
			esb = null;
			return esb;
		}
	}
}
