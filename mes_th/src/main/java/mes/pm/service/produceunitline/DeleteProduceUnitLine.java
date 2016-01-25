package mes.pm.service.produceunitline;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import mes.framework.AdapterService;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import mes.pm.factory.ProduceUnitLineFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ɾ��������Ԫ��������
 *
 * @author YuanPeng
 */
public class DeleteProduceUnitLine extends AdapterService {
    /**
     * ���ݿ����Ӷ���
     */
    Connection con = null;
    /**
     * ������Ԫ�������ú�
     */
    private String int_id = null;
	/**
	 * ��־
	 */
	private final Log log = LogFactory.getLog(DeleteProduceUnitLine.class);
	/**
	 * ������Ԫ�������ù���
	 */
	ProduceUnitLineFactory proUnitLineFactory = new ProduceUnitLineFactory();
    /**
     * ��������
     *
     * @throws java.sql.SQLException
     */
    @Override
    public void relesase() throws SQLException {
        con = null;
        int_id = null;
    }

    /**
     * ������
     *
     * @param message
     *              ʹ��IMessage�����������
     * @param processid
     *              ����ID
     * @return booleanֵ
     *              ����booleanֵ����ʾ�ɹ����
     */
    @Override
    public boolean checkParameter(IMessage message, String processid) {
        con = (Connection) message.getOtherParameter("con");
        int_id = message.getUserParameterValue("int_id");
        if (int_id == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
					processid, new java.util.Date(), null));
			return false;
		}

        return true;
    }

    /**
     * ִ�з���
     *
     * @param message
     *              ʹ��IMessage�����������
     * @param processid
     *              ����ID
     * @return ExecuteResult
     *                  ִ�н��
     * @throws java.sql.SQLException
     *                          �׳�SQL�쳣
     * @throws java.lang.Exception
     *                          �׳������쳣
     */
    @Override
    public ExecuteResult doAdapterService(IMessage message, String processid) throws SQLException, Exception {
        try {
			try {
				//���߼�ɾ��������Ԫ�������ã���δ��������Ԫ�������ݽ���ɾ��
				proUnitLineFactory.delProduceUnitLineById(Integer.parseInt(int_id), con);
				log.info("ɾ��������Ԫ�������óɹ���");
			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), processid, new Date(), sqle));
				log.error("���ݿ��쳣");
				return ExecuteResult.fail;
			}
		} catch (Exception e) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
					processid, new java.util.Date(), e));
			log.error("δ֪�쳣");
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
    }
}
