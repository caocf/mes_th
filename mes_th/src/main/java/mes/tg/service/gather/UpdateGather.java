package mes.tg.service.gather;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import mes.framework.AdapterService;
import mes.framework.DataBaseType;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import mes.system.dao.DAOFactoryAdapter;
import mes.tg.bean.Gather;
import mes.tg.dao.IDAO_Gather;
import mes.tg.factory.GatherFactory;

/**
 * �޸Ĳɼ���
 * 
 * @author lida
 * 
 */
public class UpdateGather extends AdapterService {
	// �������
	private Connection con = null;
	// �ɼ����
	private String int_id = null;
	// �ɼ�����
	private String str_name = null;
	// ������Ϣ
	private String str_desc = null;
	// ������Ԫ��
	private String int_produnitid = null;
	// ���ϱ�ʶ��
	private String int_materielruleid = null;
	//����״̬���
	private String quality_status = null;
	//��־
	private final Log log = LogFactory.getLog(UpdateGather.class);
	
	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
		int_id = message.getUserParameterValue("int_id");
		str_name = message.getUserParameterValue("str_name");
		str_desc = message.getUserParameterValue("str_desc");
		int_produnitid = message.getUserParameterValue("int_produnitid");
		int_materielruleid = message.getUserParameterValue("int_materielruleid");
		quality_status = message.getUserParameterValue("quality_check");
		//���log��Ϣ
	    String debug="�ɼ���ID��"+int_id+"�ɼ�������" + str_name + "��"+ "������Ԫ�ţ�"+int_produnitid+ ";"
		+ "���ϱ�ʶ����ţ�"+int_materielruleid+ ";"+"�ɼ���������"+str_desc+ "������״̬��" +  "��"+quality_status;
	    log.debug("���²ɼ���ʱ�û��ύ�Ĳ���: " + debug);
		
		if (str_name == null || str_desc == null || int_produnitid == null
				|| int_materielruleid == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
					processid, new java.util.Date(), null));
			log.fatal("�ɼ�������������Ԫ�š����ϱ�ʶ����š��ɼ�����������Ϊ�ղ������˳�����");
			return false;
		}
	
		return true;
	}

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {
			try {
				GatherFactory factory = new GatherFactory();
				Gather gather = new Gather();
				gather.setId(new Integer(int_id));
				gather.setName(str_name);
				gather.setDesc(str_desc);
				gather.setProdunitId(new Integer(int_produnitid));
				gather.setMaterielruleId(new Integer(int_materielruleid));
				factory.updateGather(gather, con);
				log.info("���²ɼ������ɹ���");
				
				IDAO_Gather dao = (IDAO_Gather) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						IDAO_Gather.class);
				Statement stmt = con.createStatement();
				String[] qualitys = quality_status.split(","); // ������ֳ���
				// ��ɾ���ɼ����������ϵ
				log.debug("ɾ��qualitys:"+dao.delGather_Q(Integer.parseInt(int_id)));
				stmt.execute(dao.delGather_Q(Integer.parseInt(int_id)));
				log.info("ɾ���ɼ����������ϵ�ɹ���");
				
                // ��Ӳɼ���������Ĺ�ϵ
				for (int j = 0; j < qualitys.length; j++) {
				log.debug("����qualitys: "+dao.saveGather_Q((Integer.parseInt(int_id)),(Integer.parseInt(qualitys[j])),j+1));
				stmt.execute(dao.saveGather_Q((Integer.parseInt(int_id)),(Integer.parseInt(qualitys[j])),j+1));
				}
				log.info("��Ӳɼ���������Ĺ�ϵ�ɹ���");
				
			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), processid, new Date(), sqle));
				log.error("���ݿ��쳣���жϷ���");
				return ExecuteResult.fail;
			}
		} catch (Exception e) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
					processid, new java.util.Date(), e));
			log.error("δ֪�쳣���жϷ���");
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
	}

	@Override
	public void relesase() throws SQLException {
		int_id = null;
		str_name = null;
		str_desc = null;
		int_produnitid = null;
		int_materielruleid = null;
		con = null;

	}

}
