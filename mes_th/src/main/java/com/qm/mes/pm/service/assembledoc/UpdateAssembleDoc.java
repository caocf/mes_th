package com.qm.mes.pm.service.assembledoc;

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
import com.qm.mes.pm.bean.AssDocItem;
import com.qm.mes.pm.bean.AssembleDoc;
import com.qm.mes.pm.factory.AssembleDocFactory;
import com.qm.mes.util.SerializeAdapter;

/**
 * ����װ��ָʾ��
 * 
 * @author Ypeng
 * 
 */
public class UpdateAssembleDoc extends AdapterService {
	/**
	 * �������
	 */
	private Connection con = null;
	/**
	 * װ��ָʾ����
	 */
	private String int_id ;
	/**
	 * װ��ָʾ����
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
	 * String��װ��ָʾ��Map
	 */
	private String str_attr_val = null;
	/**
	 * װ��ָʾ��Map
	 */
	private HashMap<?, ?> attr_val = new HashMap<String, String>();
	/**
	 * ����ת������
	 */
	private SerializeAdapter sa = new SerializeAdapter();
	/**
	 * װ��ָʾ������ 
	 */
	private int attr_count = 0;
	/**
	 * �û�ID
	 */
	private int userid = 0;
	/**
	 * װ��ָʾ�����Ƿ񱻸ı�
	 */
	private String change = null;
	/**
	 * ��־
	 */
	private final Log log = LogFactory.getLog(UpdateAssembleDoc.class);
	/**
	 * װ��ָʾ����������
	 */
	int count=0;
	/**
	 * װ��ָʾ������
	 */
	AssembleDocFactory factory = new AssembleDocFactory();
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
			attr_val = (HashMap<?,?>)sa.toObject(str_attr_val);
			userid = Integer.parseInt(message.getUserParameterValue("userid"));
			attr_count = Integer.parseInt(message.getUserParameterValue("attr_count"));
			count = factory.getAssembleDocCountByName(name, con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(change.equals("false"))
			count--;
		if(count>0){
			message.addServiceException(new ServiceException(
				ServiceExceptionType.PARAMETERERROR, "װ��ָʾ�������Ѿ����ڣ����������룡", this.getId(),
				processid, new java.util.Date(), null));
			log.fatal("װ��ָʾ�������Ѿ�����");
			return false;
		}
		//���log��Ϣ
	    String debug="�޸��û�ID��"+userid+"�����ƣ�" + name + "��"+ "�������ͱ�ʾ��"+materiel+ ";"
		+ "������Ϣ��"+description;
	    log.debug("���װ��ָʾ��ʱ�û��ύ�Ĳ���: " + debug);
	    
		return true;
	}

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {
			try {
				AssembleDoc assembleDoc = new AssembleDoc();
				assembleDoc.setId(Integer.parseInt(int_id));
				assembleDoc.setUpdateUID(userid);
				assembleDoc.setName(name);
				assembleDoc.setMateriel(materiel);
				assembleDoc.setDescription(description);
				factory.updateAssembleDoc(assembleDoc, con);
				log.info("����װ��ָʾ���ɹ���");
				factory.delAssDocItemByAssembleDocId(Integer.parseInt(int_id), con);
				log.info("ɾ��װ��ָʾ��ɹ�");
				for(int i=1;i<=attr_count;i++){
					AssDocItem assDocItem = new AssDocItem();
					log.debug("��"+i+"��װ��ָʾ��---���֣�"+attr_val.get("str_itemname"+i)+
							"���Ӽ���ʾ��"+attr_val.get("str_itemcode"+i)+"��������"+attr_val.get("str_itemdes"+i));
					assDocItem.setAssDocId(Integer.parseInt(int_id));
					assDocItem.setName(attr_val.get("str_itemname"+i).toString());
					assDocItem.setCode(attr_val.get("str_itemcode"+i).toString());
					assDocItem.setDescription(attr_val.get("str_itemdes"+i).toString());
					factory.saveAssDocItem(assDocItem,con);
				}
				log.info("���װ��ָʾ��ɹ�");
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
