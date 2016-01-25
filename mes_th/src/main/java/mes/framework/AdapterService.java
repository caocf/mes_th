package mes.framework;

import java.sql.SQLException;
import java.util.Date;

/**
 * ����������<br>
 * �̳д˷�����û����Խ�һ���������ҵ��Ĵ��룬������Ϊ�쳣�Ĳ���д����Ĵ��롣 <br>
 * <font color="red"><b>����̳�</b></font>
 * 
 * @author �Ź��� 2007-6-28
 */
public abstract class AdapterService extends DefService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see zgl.realtool.mes.framework.IService#doService(zgl.realtool.mes.framework.IMessage,
	 *      java.lang.String)
	 */
	public final ExecuteResult doService(IMessage message, String processid) {

		return doWork(message, processid, true);
	}

	@Override
	public final ExecuteResult undoService(IMessage message, String processid) {
		return doWork(message, processid, false);
	}

	/**
	 * �ع�����<br>
	 * ����������п����ʵ��ļ��������Ĳ����쳣�Ĵ��롣 <br>
	 * <font color="red"><b>������ϣ��ʵ�ֻع����������Ǵ˷�����</b></font>
	 * 
	 * @param message
	 * @param processid
	 * @return ���ط������н��
	 * @throws SQLException
	 * @throws Exception
	 */
	public ExecuteResult undoAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		return ExecuteResult.fail;
	}

	private ExecuteResult doWork(IMessage message, String processid,
			boolean isdo) {
		ExecuteResult result = ExecuteResult.fail;
		try {
			if (!checkParameter(message, processid)) {
				message
						.addServiceException(new ServiceException(
								ServiceExceptionType.PARAMETERLOST, "ȱ���������",
								this.getId(), processid, new java.util.Date(),
								null));
				return result;
			}
			if (isdo)
				return doAdapterService(message, processid);
			else
				return undoAdapterService(message, processid);

		} // ����SQL�쳣
		catch (SQLException sqle) {
			sqle.printStackTrace();
			// ��¼�쳣��Ϣ
			message.addServiceException(new ServiceException(
					ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣",
					this.getId(), processid, new Date(), null));

		}// ����δ֪һ��
		catch (Exception e) {
			e.printStackTrace();
			// ��¼�쳣��Ϣ
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, "δ֪�쳣��" + e.toString(), this
							.getId(), processid, new java.util.Date(), e));

		} finally {
			try {
				relesase();
			} catch (Exception e) {
				e.printStackTrace();
				message.addServiceException(new ServiceException(
						ServiceExceptionType.UNKNOWN, "�ͷ���Դʱ�����쳣��"
								+ e.toString(), this.getId(), processid,
						new java.util.Date(), e));
			}
		}
		return result;
	}

	/**
	 * �ͷ���Դ<br>
	 * ���۷������������쳣���أ�������ô˷���
	 * 
	 * @throws SQLException
	 */
	public abstract void relesase() throws SQLException;

	/**
	 * ��֤����<br>
	 * ����������false���Զ����ɡ�ȱ�ٲ��������쳣��Ϣ
	 * 
	 * @param message
	 * @param processid
	 * @return
	 */
	public abstract boolean checkParameter(IMessage message, String processid);

	/**
	 * ִ�з���<br>
	 * ����������п����ʵ��ļ��������Ĳ����쳣�Ĵ��롣
	 * 
	 * @param message
	 * @param processid
	 * @return ���ط������н��
	 * @throws SQLException
	 * @throws Exception
	 */
	public abstract ExecuteResult doAdapterService(IMessage message,
			String processid) throws SQLException, Exception;
}
