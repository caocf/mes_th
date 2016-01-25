package mes.tg.service.gatheritem;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import mes.framework.AdapterService;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import mes.tg.bean.GatherItem;
import mes.tg.factory.GatherFactory;
import mes.tg.factory.GatherItemFactory;
import mes.util.SerializeAdapter;

/**
 * ��Ӳɼ�������
 * 
 * @author lida
 * 
 */
public class AddGatherItem extends AdapterService {

	// �������
	private Connection con = null;

	// �ɼ����
	private String int_gatherid = null;

	// ���Ը���
	private String attr_count = null;

	// �����ϱ�ʶ�����
	private String int_materielruleid = null;

	// ���Դ浽map��(˳���,���ϱ�ʶ��),��map����ת����String����
	HashMap<String, String> attr_val = new HashMap<String, String>();
	SerializeAdapter sa = new SerializeAdapter();
	//��־
	private final Log log = LogFactory.getLog(AddGatherItem.class);

	@SuppressWarnings("unchecked")
	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
		int_gatherid = message.getOutputParameterValue("int_gatherid");
		attr_count = message.getUserParameterValue("attr_count");
		int_materielruleid = message
				.getUserParameterValue("int_materielruleid");
		try {
			//������ת��ΪMap<˳���><���ϱ�ʶ��>����
			attr_val = (HashMap<String, String>) sa.toObject(message
					.getUserParameterValue("str_attr_val"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//���log��Ϣ
	    String debug="�ɼ���ţ�" + int_gatherid + "��"
		+ "���ϱ�ʶ����ţ�"+int_materielruleid+ ";"+"�ɼ������Ը�����"+attr_count;
	    if(Integer.parseInt(attr_count)!=0)debug+=";�ɼ������ԣ�\n";
	    for(int j=1;j<=Integer.parseInt(attr_count);j++){
	    	debug+="�ɼ�������˳��ţ�"+attr_val.get("int_order"+j)+";";
	    	debug+="�ɼ����������Ϲ���"+attr_val.get("int_materialruleid"+j);
	    	if(j!=Integer.parseInt(attr_count))debug+=";\n";
	    }
	    log.debug("��Ӳɼ���ʱ�û��ύ�Ĳ���: " + debug);
	    
		if (int_gatherid == null || attr_count == null || attr_val == null
				|| int_materielruleid == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
					processid, new java.util.Date(), null));
			log.fatal("�ɼ���š��ɼ��������������ɼ������ԡ����ϱ�ʶ������Ϊ�ղ������˳�����");
			return false;
		}

		return true;
	}

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {
			try {
				GatherItemFactory factory = new GatherItemFactory();
				GatherFactory factory1 = new GatherFactory();
				//ѭ��ȡ��Mapֵ�������ɼ�������
				for (int i = 1; i <= Integer.parseInt(attr_count); i++) {
					GatherItem gatherItem = new GatherItem();
					gatherItem.setGatherId(new Integer(int_gatherid));
					gatherItem.setOrder(new Integer(attr_val.get("int_order"
							+ i)));
					gatherItem.setMaterielruleId(new Integer(attr_val
							.get("int_materialruleid" + i)));
					int fcount = 0;
					fcount = factory1.checkGatherItemByMaterialId(Integer
							.parseInt(int_gatherid), new Integer(attr_val
							.get("int_materialruleid" + i)), con);
					if (fcount > 0) {
						message.addServiceException(new ServiceException(
								ServiceExceptionType.UNKNOWN,
								"������Ԫ�иòɼ��������Ϲ������������Ϲ����!", this.getId(),
								processid, new Date(), null));
						log.fatal("������Ԫ�иòɼ��������Ϲ������������Ϲ���š�");
						continue;
					} else {
						int count = 0;
						count = factory.checkGatherItemByOrder(Integer
								.parseInt(int_gatherid), new Integer(attr_val
								.get("int_order" + i)), con);
						int count1 = 0;
						count1 = factory.checkGatherItemBySubMaterialId(Integer
								.parseInt(int_gatherid), new Integer(attr_val
								.get("int_materialruleid" + i)), con);
						if (count > 0) {
							message.addServiceException(new ServiceException(
									ServiceExceptionType.UNKNOWN,
									"������Ԫ�иòɼ����Ѿ����ڴ�˳�������!", this.getId(),
									processid, new Date(), null));
							log.fatal("������Ԫ�иòɼ����Ѿ����ڴ�˳������ݡ�");
							return ExecuteResult.fail;
						} else if (count1 > 0) {
							message.addServiceException(new ServiceException(
									ServiceExceptionType.UNKNOWN,
									"������Ԫ�иòɼ����Ѿ����ڴ����Ϲ��������!", this.getId(),
									processid, new Date(), null));
							log.fatal("������Ԫ�иòɼ����Ѿ����ڴ����Ϲ�������ݡ�");
							return ExecuteResult.fail;
						} else {
							factory.saveGatherItem(gatherItem, con);
							log.info("��Ӳɼ������ɹ���");
						}
					}
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
		}
		return ExecuteResult.sucess;
	}

	@Override
	public void relesase() throws SQLException {
		int_gatherid = null;
		attr_count = null;
		attr_val = null;
		con = null;

	}

}
