/**
 * 
 */
package mes.system.services.material;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import mes.framework.AdapterService;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import mes.system.elements.IMaterial;
import mes.system.factory.FactoryAdapter;
import mes.system.factory.IMaterialFactory;

/**
 * @author ������
 * 
 */
public class Service_AddMaterial extends AdapterService {
	// �������
	private Connection con = null;

	// ����������ù���
	private IMaterialFactory factory;

	// ������,��Ԫ�ر���
	private String element_name = null;

	// �������ͺ�
	private String type_id = null;

	// ����������
	private String character = null;

	// ���ϱ�ʶ��
	private String identify = null;

	// ������Ϣ
	private String description = null;

	// �����û�
	private String userid = null;

	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
		element_name = message.getUserParameterValue("element_name");
		type_id = message.getUserParameterValue("type_id");
		character = message.getUserParameterValue("character").trim();
		identify = message.getUserParameterValue("identify").trim();
		description = message.getUserParameterValue("description");
		userid = message.getUserParameterValue("userid");
		System.out.println("���У� " + "element_name = " + element_name + "  "
				+ "  description = " + description + "  type_id = " + type_id
				+ "  character =" + character + "        identify ="
				+ identify);
		if (element_name == null || userid == null || type_id == null
				|| character == null || identify == null) {
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
				factory = (IMaterialFactory) FactoryAdapter
						.getFactoryInstance(IMaterialFactory.class.getName());
				stmt = con.createStatement();
				IMaterial type = factory.createElement();
				String[] charac = character.split(":");
				String[] iden = identify.split(":");
				type.setName(message.getUserParameterValue("element_name"));
				type.setMaterialTypeId(new Integer(message
						.getUserParameterValue("type_id")));
				for (String ch : charac) {
					if(ch!=null&&!ch.equals(""))
						type.addCharacterId(new Integer(ch));
				}
				for (String ide : iden) {
					if(ide!=null && !ide.equals(""))
						type.addIdentifyId(new Integer(ide));
				}
				type.setDescription(message
						.getUserParameterValue("description"));
				type.setUpdateUserId(new Integer(message
						.getUserParameterValue("userid")));
				stmt.close();
				factory.save(type, con);
			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), processid, new Date(), null));
				sqle.printStackTrace();
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
		element_name = null;
		type_id = null;
		character = null;
		identify = null;
		description = null;
		userid = null;
		con = null;

	}

}
