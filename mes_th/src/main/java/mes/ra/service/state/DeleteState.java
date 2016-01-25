package mes.ra.service.state;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import mes.framework.AdapterService;
import mes.framework.DataBaseType;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import mes.ra.factory.StateFactory;
import mes.system.dao.DAOFactoryAdapter;
import mes.ra.dao.DAO_State;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ɾ��ָ��״̬
 * 
 * @author xujia
 * 
 */
public class DeleteState extends AdapterService {
	/**
	 * �������
	 */
	private Connection con = null;
	/**
	 * ����id
	 */
	private String int_id = null;
    StateFactory factory = new StateFactory();
	Statement stmt = null;
	ResultSet rs = null;
	private final Log log = LogFactory.getLog(DeleteState.class);

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

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid) throws SQLException, Exception {
        try {
			try {
				DAO_State dao = (DAO_State) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_State.class);
				stmt = con.createStatement();
				rs = stmt.executeQuery(dao
				.checkStateById1(Integer.parseInt(int_id)));
				int state_count1 = 0;
				if (rs.next()) {
					state_count1 = rs.getInt(1);
				}
			rs = stmt.executeQuery(dao.checkStateById2(Integer
				.parseInt(int_id)));
			int state_count2 = 0;
			if (rs.next()) {
				state_count2 = rs.getInt(1);
			}

			rs = stmt.executeQuery(dao.checkProduceUnitById(Integer
				.parseInt(int_id)));
			int state_count3 = 0;
			if (rs.next()) {
				state_count3 = rs.getInt(1);
			}

			// ��conversionstate�õ�stateʱ������ɾ��
			if (state_count1 > 0 || state_count2 > 0 || state_count3 > 0) {
				message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, "��״̬����״̬ת����������Ԫ�����ã�����ɾ��!",
					this.getId(), processid, new Date(), null));
			log.fatal("״̬����״̬ת����������Ԫ�����ã�����ɾ��");
			return ExecuteResult.fail;
			} else {
				factory.delStateById(new Integer(int_id), con);
			} 
		}catch (SQLException sqle) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
							.getId(), processid, new Date(), sqle));
			log.error("ɾ��״̬ʱ,δ֪�쳣" + sqle.toString());
			return ExecuteResult.fail;
		}
        } catch (Exception e) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
					processid, new java.util.Date(), e));
			log.error("ɾ��״̬ʱ,δ֪�쳣" + e.toString());
			return ExecuteResult.fail;
		}finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
		return ExecuteResult.sucess;
    }

	@Override
	public void relesase() throws SQLException {
		int_id = null;
		con = null;

	}

}
