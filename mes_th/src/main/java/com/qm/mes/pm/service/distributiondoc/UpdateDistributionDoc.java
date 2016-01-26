package com.qm.mes.pm.service.distributiondoc;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.AdapterService;
import com.qm.mes.framework.ExecuteResult;
import com.qm.mes.framework.IMessage;
import com.qm.mes.framework.ServiceException;
import com.qm.mes.framework.ServiceExceptionType;
import com.qm.mes.pm.bean.DistriItem;
import com.qm.mes.pm.bean.DistributionDoc;
import com.qm.mes.pm.factory.DistributionDocFactory;
import com.qm.mes.util.SerializeAdapter;

/**
 * ��������ָʾ��
 * 
 * @author Ypeng
 * UpDistributionDoc
 */
public class UpdateDistributionDoc extends AdapterService {
	/**
	 * �������
	 */
	private Connection con = null;
	/**
	 * ����ָʾ����
	 */
	private String int_id ;
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
	 * String������ָʾ��Map
	 */
	private String str_attr_val = null;
	/**
	 * ����ָʾ��Map
	 */
	private HashMap<?, ?> attr_val = new HashMap<String, String>();
	/**
	 * ����ת������
	 */
	private SerializeAdapter sa = new SerializeAdapter();
	/**
	 * ����ָʾ������
	 */
	private int attr_count = 0;
	/**
	 * �û�ID
	 */
	private int userid = 0;
	/**
	 * ����ָʾ�����Ƿ񱻸ı�
	 */
	private String change = null;
	/**
	 * ��־
	 */
	private final Log log = LogFactory.getLog(UpdateDistributionDoc.class);
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
		con = (Connection) message.getOtherParameter("con");
		int_id = message.getUserParameterValue("int_id");
		name = message.getUserParameterValue("name");
		materiel = message.getUserParameterValue("materiel");
		description = message.getUserParameterValue("description");
		str_attr_val = message.getUserParameterValue("str_attr_val");
		change = message.getUserParameterValue("change");
		try {
			requestProUnit = Integer.parseInt(message.getUserParameterValue("requestProUnit"));
			responseProUnit = Integer.parseInt(message.getUserParameterValue("responseProUnit"));
			targetProUnit = Integer.parseInt(message.getUserParameterValue("targetProUnit"));
			attr_val = (HashMap<?,?>)sa.toObject(str_attr_val);
			userid = Integer.parseInt(message.getUserParameterValue("userid"));
			attr_count = Integer.parseInt(message.getUserParameterValue("attr_count"));
			count = factory.getDistributionDocCountByName(name, con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(change.equals("false"))
			count--;
		if(count>0){
			message.addServiceException(new ServiceException(
				ServiceExceptionType.PARAMETERERROR, "����ָʾ�������Ѿ����ڣ����������룡", this.getId(),
				processid, new java.util.Date(), null));
			log.fatal("����ָʾ�������Ѿ�����");
			return false;
		}
		//���log��Ϣ
	    String debug="�޸��û�ID��"+userid+"�����ƣ�" + name + "��"+ "�������ͱ�ʾ��"+materiel+ ";"
		+ "������Ϣ��"+description;
	    log.debug("�������������ʱ�û��ύ�Ĳ���: " + debug);
	    
		return true;
	}

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {
			try {
				DistributionDoc distributionDoc = new DistributionDoc();
				distributionDoc.setId(Integer.parseInt(int_id));
				distributionDoc.setUpdateUID(userid);
				distributionDoc.setName(name);
				distributionDoc.setMaterielType(materiel);
				distributionDoc.setDescription(description);
				distributionDoc.setRequest_proUnit(requestProUnit);
				distributionDoc.setResponse_proUnit(responseProUnit);
				distributionDoc.setTarget_proUnit(targetProUnit);
				factory.updateDistributionDoc(distributionDoc, con);
				log.info("��������ָʾ���ɹ���");
				factory.delDistriItemByDistributionDocId(Integer.parseInt(int_id), con);
				log.info("ɾ������������ɹ�");
				for(int i=1;i<=attr_count;i++){
					DistriItem distriItem = new DistriItem();
					log.debug("��"+i+"������������---���֣�"+attr_val.get("str_itemname"+i)+
							"���������ͱ�ʾ��"+attr_val.get("str_itemmatitem"+i)+
							"������������"+attr_val.get("int_itemcount"+i)+"��������"+attr_val.get("str_itemdes"+i));
					distriItem.setDistributionDocId(Integer.parseInt(int_id));
					distriItem.setName(attr_val.get("str_itemname"+i).toString());
					distriItem.setMatitem(attr_val.get("str_itemmatitem"+i).toString());
					distriItem.setCount(Integer.parseInt(attr_val.get("int_itemcount"+i).toString()));
					distriItem.setDescription(attr_val.get("str_itemdes"+i).toString());
					factory.saveDistriItem(distriItem,con);
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
