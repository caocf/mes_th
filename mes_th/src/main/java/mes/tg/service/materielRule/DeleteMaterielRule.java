package mes.tg.service.materielRule;

import java.sql.Connection;
import java.sql.ResultSet;
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
import mes.tg.dao.IDAO_MaterielRule;
import mes.tg.factory.MaterielRuleFactory;
import mes.tg.factory.RecordFactory;
import mes.ra.factory.ProduceUnitFactory;


/**
 * ɾ�����ϱ�ʶ����
 * 
 * @author lida
 * 
 */
public class DeleteMaterielRule extends AdapterService {
	// �������
	private Connection con = null;
	// ���ϱ�ʶ����id
	private String int_id = null;

	Statement stmt = null;
	ResultSet rs = null;
	//��־
	private final Log log = LogFactory.getLog(DeleteMaterielRule.class);

	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
		int_id = message.getUserParameterValue("int_id");
		//���log��Ϣ
	    String debug="���ϱ�ʶ����ţ�" + int_id;
	    log.debug("ɾ�����ϱ�ʶ����ʱ�û��ύ�Ĳ���: " + debug);
		if (int_id == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
					processid, new java.util.Date(), null));
			log.fatal("���ϱ�ʶ�����Ϊ�գ��˳�����");
			return false;
		}
		return true;
	}

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {
			try {
				IDAO_MaterielRule dao = (IDAO_MaterielRule) DAOFactoryAdapter
						.getInstance(DataBaseType.getDataBaseType(con),
								IDAO_MaterielRule.class);
				stmt = con.createStatement();
				rs = stmt.executeQuery(dao
						.checkGather(Integer.parseInt(int_id)));
				int gather_count = 0;
				if (rs.next()) {
					gather_count = rs.getInt(1);
				}
				rs = stmt.executeQuery(dao.checkGatherItem(Integer
						.parseInt(int_id)));
				int gatherItem_count = 0;
				if (rs.next()) {
					gatherItem_count = rs.getInt(1);
				}
				RecordFactory RecordFactory = new RecordFactory();
				MaterielRuleFactory materielRuleFactory = new MaterielRuleFactory();
				String MaterielRuleName = materielRuleFactory.findMaterielRule(Integer.parseInt(int_id),con).getName();
				int count_MaterielRuleName = RecordFactory.countByMaterielRuleName(MaterielRuleName,con);
				int count_GatherRecord = RecordFactory.countGatherReByMaterielRuleName(MaterielRuleName,con);
				ProduceUnitFactory produceUnitFactory = new ProduceUnitFactory();
				int count_ProduceUnit = produceUnitFactory.countProduceUnitByMateritelRuleID(Integer.parseInt(int_id),con);
				// �ڲɼ���gather�����й��������ϱ�ʶ����ŵ�����
				if (gather_count > 0) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.UNKNOWN, "�����ϱ�ʶ���ڲɼ�������Ӧ�ã�������ɾ��!\n����ά���ɼ�������ɾ���ٲ�����",
							this.getId(), processid, new Date(), null));
					log.fatal("�����ϱ�ʶ���ڲɼ�������Ӧ�ã�������ɾ��");
					return ExecuteResult.fail;
				}
				//�ɼ�������gatherItem�����д����ϱ�ʶ����ŵ�Ӧ��
				else if (gatherItem_count > 0) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.UNKNOWN, "�����ϱ�ʶ���ڲɼ�����������Ӧ�ã�������ɾ��!\n����ά���ɼ�����������ɾ���ٲ�����",
							this.getId(), processid, new Date(), null));
					log.fatal("�����ϱ�ʶ���ڲɼ�����������Ӧ�ã�������ɾ��");
					return ExecuteResult.fail;
				}
				//��ϵ�������ϱ�ʶ���������������ϵ���д������ϱ�ʶ������ʱ������ɾ��
				else if(count_MaterielRuleName > 0){
					message.addServiceException(new ServiceException(
							ServiceExceptionType.UNKNOWN, "��ϵ�д��ڴ����ϱ�ʶ��������������ɾ��!",
							this.getId(), processid, new Date(), null));
					log.fatal("��ϵ�д��ڴ����ϱ�ʶ��������������ɾ��");
					return ExecuteResult.fail;
				}
				//�жϹ����¼�����Ƿ��е�ǰ���ϱ�ʶ������
				else if(count_GatherRecord > 0){
					message.addServiceException(new ServiceException(
							ServiceExceptionType.UNKNOWN, "�����¼�д��ڴ����ϱ�ʶ��������������ɾ��!",
							this.getId(), processid, new Date(), null));
					log.fatal("�����¼�д��ڴ����ϱ�ʶ��������������ɾ��");
					return ExecuteResult.fail;
				}
				//�ж�������Ԫ���Ƿ��е�ǰ���ϱ�ʶ�����
				else if(count_ProduceUnit > 0){
					message.addServiceException(new ServiceException(
							ServiceExceptionType.UNKNOWN, "������Ԫ�д��ڴ����ϱ�ʶ��������������ɾ��!",
							this.getId(), processid, new Date(), null));
					log.fatal("������Ԫ�д��ڴ����ϱ�ʶ��������������ɾ��");
					return ExecuteResult.fail;
				}
				else {
					MaterielRuleFactory factory = new MaterielRuleFactory();
					factory.deleteMaterielRule(Integer.parseInt(int_id), con);
					log.info("ɾ�����ϱ�ʶ�������ɹ���");
				}
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
		} finally {
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
