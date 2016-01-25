package mes.pm.service.distributiondoc;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import mes.framework.AdapterService;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import mes.pm.bean.DistriItem;
import mes.pm.bean.DistributionDoc;
import mes.pm.factory.DistributionDocFactory;
import mes.util.SerializeAdapter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AddDistributionDoc extends AdapterService {
	/**
	 * �������
	 */
	private Connection con = null;
	/**
	 * ����ָʾ����
	 */
	private int int_id ;
	/**
	 * ����ָʾ����
	 */
	private String name = null;
	/**
	 * �������ͱ�ʾ
	 */
	private String materiel = null;
	/**
	 * ������Ϣ
	 */
	private String description = null;
	/**
	 * ����������Ԫ
	 */
	private int requestProUnit ;
	/**
	 * ��Ӧ������Ԫ
	 */
	private int responseProUnit;
	/**
	 * ��������������Ԫ
	 */
	private int targetProUnit;
	/**
	 * String������������Map
	 */
	private String str_attr_val = null;
	/**
	 * ����������Map
	 */
	private HashMap<?, ?> attr_val = new HashMap<String, String>();
	/**
	 * ����ת������
	 */
	private SerializeAdapter sa = new SerializeAdapter();
	/**
	 * ��������������
	 */
	private int attr_count = 0;
	/**
	 * �û�ID
	 */
	private int userid = 0;
	/**
	 * ��־
	 */
	private final Log log = LogFactory.getLog(AddDistributionDoc.class);
	/**
	 * ����ָʾ����������
	 */
	int count=0;
	/**
	 * ����ָʾ������
	 */
	DistributionDocFactory factory = new DistributionDocFactory();
	@Override
	public boolean checkParameter(IMessage message, String processid) {
		//���ղ���
		con = (Connection) message.getOtherParameter("con");
		name = message.getUserParameterValue("name");
		materiel = message.getUserParameterValue("materiel");
		description = message.getUserParameterValue("description");
		requestProUnit = Integer.parseInt(message.getUserParameterValue("requestProUnit"));
		responseProUnit = Integer.parseInt(message.getUserParameterValue("responseProUnit"));
		targetProUnit = Integer.parseInt(message.getUserParameterValue("targetProUnit"));
		str_attr_val = message.getUserParameterValue("str_attr_val");
		try {
			//ת��Ϊ����������Map
			attr_val = (HashMap<?,?>)sa.toObject(str_attr_val);
			userid = Integer.parseInt(message.getUserParameterValue("userid"));
			attr_count = Integer.parseInt(message.getUserParameterValue("attr_count"));
			count = factory.getDistributionDocCountByName(name, con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//�ж������Ƿ��Ѿ�����
		if(count>0){
			message.addServiceException(new ServiceException(
				ServiceExceptionType.PARAMETERERROR, "����ָʾ�������Ѿ����ڣ����������룡", this.getId(),
				processid, new java.util.Date(), null));
			log.fatal("����ָʾ�������Ѿ�����");
			return false;
		}
		//���log��Ϣ
	    String debug="�����û�ID��"+userid+"�����ƣ�" + name + "��"+ "�������ͱ�ʾ��"+materiel+ ";"
		+ "������Ϣ��"+description;
	    log.debug("�������ָʾ��ʱ�û��ύ�Ĳ���: " + debug);
	    
		return true;
	}

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {
			try {
				DistributionDoc distributionDoc = new DistributionDoc();
				distributionDoc.setCreateUID(userid);
				distributionDoc.setName(name);
				distributionDoc.setMaterielType(materiel);
				distributionDoc.setDescription(description);
				distributionDoc.setRequest_proUnit(requestProUnit);
				distributionDoc.setResponse_proUnit(responseProUnit);
				distributionDoc.setTarget_proUnit(targetProUnit);
				factory.saveDistributionDoc(distributionDoc, con);
				log.info("�������ָʾ������ɹ���");
				//ͨ�����ֲ�ѯ���
				int_id = factory.getDistributionDocIdByName(name, con);
				//ѭ����������ָʾ��
				for(int i=1;i<=attr_count;i++){
					DistriItem DistriItem = new DistriItem();
					log.debug("��"+i+"������������---���֣�"+attr_val.get("str_itemname"+i)+
							"�����ϱ�ʾ��"+attr_val.get("str_itemmatitem"+i)+
							"������������"+attr_val.get("int_itemcount"+i)+
							"��������"+attr_val.get("str_itemdes"+i));
					DistriItem.setDistributionDocId(int_id);
					DistriItem.setName(attr_val.get("str_itemname"+i).toString());
					DistriItem.setCount(Integer.parseInt(attr_val.get("int_itemcount"+i).toString()));
					DistriItem.setMatitem(attr_val.get("str_itemmatitem"+i).toString());
					DistriItem.setDescription(attr_val.get("str_itemdes"+i).toString());
					factory.saveDistriItem(DistriItem,con);
				}
				log.info("�������������ɹ�");
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
		con = null;

	}

}
