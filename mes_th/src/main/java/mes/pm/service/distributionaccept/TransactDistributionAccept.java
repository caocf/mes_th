package mes.pm.service.distributionaccept;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.SQLException;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import mes.framework.AdapterService;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import mes.pm.factory.DistributionAcceptFactory;
import mes.util.SerializeAdapter;

/**
 * ��������ȷ�ϵ�
 *
 * @author YuanPeng
 * ����   TransactDisAccept
 */
public class TransactDistributionAccept extends AdapterService {
    /**
     * ���ݿ����Ӷ���
     */
    private Connection con;
    /**
     * �Ѳ������ݱ�ɴ������ݵļĴ���
     */
	private SerializeAdapter sa = null;
    /**
     * ��������
     */
    int int_array[] ;
    /**
     * ѡ�е����鳤��
     */
    private int array_length ;
    /**
     * ��Ӧ�û�
     */
    private int userid;
	//��־
	private final Log log = LogFactory.getLog(TransactDistributionAccept.class);

	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
        sa = new SerializeAdapter();
                //��ȡ���鳤��
                array_length = Integer.parseInt(message.getUserParameterValue("arr_length"));
                int_array = new int[array_length];
                userid = Integer.parseInt(message.getUserParameterValue("userid"));
        try {
            //���ַ���ת����������������
            int_array = (int[]) sa.toObject(message.getUserParameterValue("str_array"));
            
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        for(int i =0 ;i<array_length;i++){
        	String debug = "";
        	if(i!=0||i!=array_length)debug+="��";
        	debug+="��"+i+"�����Ϊ��"+int_array[i];
        	log.debug(debug);
        }
        return true;
	}

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {
			try {
				DistributionAcceptFactory factory = new DistributionAcceptFactory();
                for(int j:int_array){
                	factory.transactDistributionAccept(j,userid, con);
                	log.info("���Ϊ:"+j+"��ȷ�ϵ��ѱ��������");
                }
                log.info("ͨ����Ŵ�������ȷ�ϵ��ɹ�");
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

	@Override
	public void relesase() throws SQLException {
		con = null;
	}

}

