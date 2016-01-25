package mes.ra.service.produceunit;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import mes.framework.AdapterService;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import mes.ra.factory.ProduceUnitFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class AddUnitChild extends AdapterService {

    private Connection conn = null;

    // ��������ԪID
    private String intId = null;

    // ��������Ԫ
    private String c_unit = null;

    // ��־
    private final Log log = LogFactory.getLog(AddUnitChild.class);

    @Override
    public boolean checkParameter(IMessage message, String processid) {

            conn = (Connection) message.getOtherParameter("con");
            intId = message.getUserParameterValue("intId");
            c_unit = message.getUserParameterValue("c_unit");

            log.info("��������ԪID: " + intId + ";��������Ԫ��" + c_unit);

            if (intId == null || c_unit == null) {
                    message.addServiceException(new ServiceException(ServiceExceptionType.PARAMETERLOST, "�������Ϊ��",
                                    this.getId(), processid, new java.util.Date(), null));

                    log.fatal("�����������Ԫ��-��������Ԫid����������Ԫ��Ϊ�ղ���");
                    return false;
            }
            return true;
    }

    @Override
    public ExecuteResult doAdapterService(IMessage message, String processid) throws SQLException, Exception {
            try {

                    new ProduceUnitFactory().delCunit(intId, conn);
                    new ProduceUnitFactory().addCunit(intId, c_unit, conn);
                    log.info("�����������Ԫ�ɹ�");

                    return ExecuteResult.sucess;
            } catch (Exception e) {
                    message.addServiceException(new ServiceException(ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣",
                                    this.getId(), processid, new Date(), null));

                    log.error("���ݿ��쳣");
                    e.printStackTrace();
                    return ExecuteResult.fail;
            }
    }

    /*
     * ��Դ����
     */
    @Override
    public void relesase() throws SQLException {
            intId = null;
            c_unit = null;
            conn = null;
    }
}
