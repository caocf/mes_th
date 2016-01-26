/**
 * 
 */
package com.qm.mes.system.services.material;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.qm.mes.framework.AdapterService;
import com.qm.mes.framework.ExecuteResult;
import com.qm.mes.framework.IMessage;
import com.qm.mes.framework.ServiceException;
import com.qm.mes.framework.ServiceExceptionType;
import com.qm.mes.system.elements.IMaterial;
import com.qm.mes.system.factory.FactoryAdapter;
import com.qm.mes.system.factory.IMaterialFactory;

/**
 * @author ������
 * 
 */
public class Service_UpdateMaterial extends AdapterService {
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

	// ԭ������������
	private String charachave = null;

	// ԭ�����ϱ�ʶ��
	private String identhave = null;

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
		charachave = message.getUserParameterValue("charachave").trim();
		identhave = message.getUserParameterValue("identhave").trim();
		description = message.getUserParameterValue("description");
		userid = message.getUserParameterValue("userid");
		System.out.println("���У� " + "element_name = " + element_name + "  "
				+ "  description = " + description + "  type_id = " + type_id
				+ "  character =" + character + "        identify =" + identify
				+ "   charachave = " + charachave + "   identhave = "
				+ identhave);
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
				String[] charac = character.split(":");
				String[] iden = identify.split(":");
				String[] ch_have = charachave.split(":");
				String[] id_have = identhave.split(":");
				IMaterial type = factory.queryElement(message
						.getUserParameterValue("element_name"), con);
				if (ch_have != null) {
					for (String str_ch : ch_have) {
						if (str_ch != null && !str_ch.equals("")) {
							type.removeCharacterId(new Integer(str_ch));
							System.out.println("str_ch = " + str_ch);
						}
					}
				}
				if (id_have != null) {
					for (String str_id : id_have) {
						if (str_id != null && !str_id.equals("")) {
							type.removeIdentifyId(new Integer(str_id));
							System.out.println("str_id = " + str_id);
						}
					}
				}
				for (String ch : charac) {
					if (ch != null && !ch.equals("")) {
						type.addCharacterId(new Integer(ch));
					}
				}
				for (String ide : iden) {
					if (ide != null && !ide.equals("")) {
						type.addIdentifyId(new Integer(ide));
					}
				}
				type.setMaterialTypeId(new Integer(message
						.getUserParameterValue("type_id")));
				type.setDescription(message
						.getUserParameterValue("description"));
				type.setUpdateUserId(new Integer(message
						.getUserParameterValue("userid")));
				stmt.close();
				factory.update(type, con);
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
		element_name = null;
		type_id = null;
		description = null;
		userid = null;
		con = null;

	}

}
