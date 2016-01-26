package com.qm.mes.system.services.materialtype;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.qm.mes.framework.AdapterService;
import com.qm.mes.framework.ExecuteResult;
import com.qm.mes.framework.IMessage;
import com.qm.mes.framework.ServiceException;
import com.qm.mes.framework.ServiceExceptionType;
import com.qm.mes.system.elements.IMaterialType;
import com.qm.mes.system.factory.FactoryAdapter;
import com.qm.mes.system.factory.IMaterialTypeFactory;

import junit.framework.Assert;

/**
 * @author ������
 * 
 */

public class Service_UpdateMaterialType extends AdapterService {
	// �������
	private Connection con = null;

	// ��������������ù���
	private IMaterialTypeFactory factory;

	// ����������,��Ԫ�ر���
	private String element_name = null;

	// �����Ϻ�
	private String parent_id = null;

	// ������Ϣ
	private String description = null;

	// �����û�
	private String userid = null;

	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
		element_name = message.getUserParameterValue("element_name");
		parent_id = message.getUserParameterValue("parent_id");
		description = message.getUserParameterValue("description");
		userid = message.getUserParameterValue("userid");
		System.out.println("���У� " + "element_name = " + element_name + "  " + "parent_id = " + parent_id
				+ "  description = " + description);
		if (element_name == null || userid == null) {
			message.addServiceException(new ServiceException(ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
					processid, new java.util.Date(), null));
			return false;
		}

		return true;
	}

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid) throws SQLException, Exception {
		try {
			Statement stmt = null;
			try {
				factory = (IMaterialTypeFactory) FactoryAdapter
						.getFactoryInstance(IMaterialTypeFactory.class.getName());
				stmt = con.createStatement();
				// �����·�ʽ����ֱ�ӻ��element_name��ֵ IMaterialType newType =
				// factory.createElement();
				IMaterialType newType = factory.queryElement(message.getUserParameterValue("element_name"), con);
				newType.setParentId(new Integer(message.getUserParameterValue("parent_id")));
				newType.setDescription(message.getUserParameterValue("description"));
				newType.setUpdateUserId(new Integer(message.getUserParameterValue("userid")));
				stmt.close();
				factory.update(newType, con);
			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣",
						this.getId(), processid, new Date(), null));
				Assert.fail("�������ʱ������쳣");
				return ExecuteResult.fail;
			} finally {
				if (stmt != null)
					stmt.close();
			}
		} catch (Exception e) {
			message.addServiceException(new ServiceException(ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
					processid, new java.util.Date(), e));
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
	}

	@Override
	public void relesase() throws SQLException {
		element_name = null;
		parent_id = null;
		userid = null;
		description = null;
		con = null;
	}

}
