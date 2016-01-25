/**
 * 
 */
package mes.system.services.materialidentify;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import mes.framework.AdapterService;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import mes.system.elements.IMaterialidentify;
import mes.system.factory.FactoryAdapter;
import mes.system.factory.IMaterialidentifyFactory;

/**
 * @author ������
 * 
 */
public class Service_UpdateMaterialidentify extends AdapterService {
	// �������
	private Connection con = null;

	// ������ϱ�ʶ���ù���
	private IMaterialidentifyFactory factory;

	// ���ϱ�ʶ��,��Ԫ�ر���
	private String element_name = null;

	// ���ϱ�ʶ����
	private String codelength = null;

	// ������Ϣ
	private String description = null;

	// �����û�
	private String userid = null;

	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
		element_name = message.getUserParameterValue("element_name");
		codelength = message.getUserParameterValue("codelength");
		description = message.getUserParameterValue("description");
		userid = message.getUserParameterValue("userid");
		System.out.println("���У� " + "element_name = " + element_name + "  "
				+ "codelength = " + codelength + "  description = "
				+ description);
		if (element_name == null || codelength == null || userid == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
					processid, new java.util.Date(), null));
			return false;
		}
		return true;
	}

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {
			Statement stmt = null;
			try {
				factory = (IMaterialidentifyFactory) FactoryAdapter
						.getFactoryInstance(IMaterialidentifyFactory.class
								.getName());
				stmt = con.createStatement();
				IMaterialidentify identify = factory.queryElement(message
						.getUserParameterValue("element_name"), con);
				identify.setCodeLength(new Integer(message
						.getUserParameterValue("codelength")));
				identify.setUpdateUserId(new Integer(message
						.getUserParameterValue("userid")));
				identify.setDescription(message
						.getUserParameterValue("description"));
				stmt.close();
				factory.update(identify, con);
			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), processid, new Date(), null));
				return ExecuteResult.fail;
			} finally {
				if (stmt != null)
					stmt.close();
			}
		} catch (Exception e) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
					processid, new java.util.Date(), e));
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
	}

	@Override
	public void relesase() throws SQLException {
		codelength = null;
		element_name = null;
		description = null;
		userid = null;
		con = null;
	}

}
