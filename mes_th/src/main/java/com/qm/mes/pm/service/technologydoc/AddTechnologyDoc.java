package com.qm.mes.pm.service.technologydoc;

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
import com.qm.mes.pm.bean.TechDocItem;
import com.qm.mes.pm.bean.TechItemFile;
import com.qm.mes.pm.bean.TechnologyDoc;
import com.qm.mes.pm.factory.TechnologyDocFactory;
import com.qm.mes.util.SerializeAdapter;

/**
 * ��ӹ��ղ���˵����
 * 
 * @author Ypeng
 * 
 */
public class AddTechnologyDoc extends AdapterService {
	/**
	 * �������
	 */
	private Connection con = null;
	/**
	 * ���ղ���˵�����
	 */
	private int int_id ;
	/**
	 * ���ղ���˵������
	 */
	private String name = null;
	/**
	 * ��Ʒ����ʾ
	 */
	private String materiel = null;
	/**
	 * ������Ϣ
	 */
	private String description = null;
	/**
	 * String�͹��ղ�����Map
	 */
	private String str_attr_val = null;
	/**
	 * ���ղ�����Map
	 */
	private HashMap<?, ?> attr_val = new HashMap<String, String>();
	/**
	 * ����ת������
	 */
	private SerializeAdapter sa = new SerializeAdapter();
	/**
	 * ���ղ���������
	 */
	private int attr_count = 0;
	/**
	 * �û�ID
	 */
	private int userid = 0;
	/**
	 * ��־
	 */
	private final Log log = LogFactory.getLog(AddTechnologyDoc.class);
	/**
	 * ���ղ���˵������������
	 */
	int name_count=0;
	/**
	 * ��Ʒ����ʾ����
	 */
	int materiel_count=0;
	/**
	 * ���ղ���˵���鹤��
	 */
	TechnologyDocFactory factory = new TechnologyDocFactory();
	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
		name = message.getUserParameterValue("name");
		materiel = message.getUserParameterValue("materiel");
		description = message.getUserParameterValue("description");
		str_attr_val = message.getUserParameterValue("str_attr_val");
		try {
			attr_val = (HashMap<?,?>)sa.toObject(str_attr_val);
			userid = Integer.parseInt(message.getUserParameterValue("userid"));
			attr_count = Integer.parseInt(message.getUserParameterValue("attr_count"));
			name_count = factory.getTechnologyDocCountByName(name, con);
			materiel_count = factory.getTechnologyDocCountByMateriel(materiel,con);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("UpdateTechnologyDoc��check�з����쳣���£�"+e.toString());
			return false;
		} 
		if(name_count>0){
			message.addServiceException(new ServiceException(
				ServiceExceptionType.PARAMETERERROR, "���ղ���˵���������Ѿ����ڣ����������룡", this.getId(),
				processid, new java.util.Date(), null));
			log.fatal("���ղ���˵���������Ѿ�����");
			return false;
		}
		if(materiel_count>0){
			message.addServiceException(new ServiceException(
				ServiceExceptionType.PARAMETERERROR, "��Ʒ����ʾ�Ѿ����ڣ����������룡", this.getId(),
				processid, new java.util.Date(), null));
			log.fatal("��Ʒ����ʾ�Ѿ�����");
			return false;
		}
		//���log��Ϣ
	    String debug="�޸��û�ID��"+userid+"�����ƣ�" + name + "��"
		+ "������Ϣ��"+description;
	    log.debug("��ӹ��ղ�����ʱ�û��ύ�Ĳ���: " + debug);
	    
		return true;
	}
	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {
				TechnologyDoc technologyDoc = new TechnologyDoc();
				technologyDoc.setCreateUID(userid);
				technologyDoc.setName(name);
				technologyDoc.setMateriel(materiel);
				technologyDoc.setDescription(description);
				factory.saveTechnologyDoc(technologyDoc, con);
				log.info("��ӹ��ղ���˵����ɹ���");
				int_id = factory.getTechnologyDocIdByName(name, con);
				for(int i=1;i<=attr_count;i++){
					TechDocItem techDocItem = new TechDocItem();
					log.debug("��"+i+"�����ղ�����---���֣�"+attr_val.get("int_itemprodUnitId"+i)+
							"���Ӽ���ʾ��"+attr_val.get("str_itemcontent"+i));
					techDocItem.setTechDocId(int_id);
					techDocItem.setProduceUnitId(Integer.parseInt(attr_val.get("int_itemprodUnitId"+i).toString()));
					techDocItem.setContent(attr_val.get("str_itemcontent"+i).toString());
					factory.saveTechDocItem(techDocItem,con);
					if(attr_val.get("file"+i)!=null){
						TechItemFile techItemFile = new TechItemFile();
						techItemFile.setTechDocItemId(factory.getTechDocItemMaxId(con));
						techItemFile.setPathName(attr_val.get("file"+i).toString());
						factory.saveTechItemFile(techItemFile, con);
					}
				}
				log.info("��ӹ��ղ�����ɹ�");
			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), processid, new Date(), sqle));
				log.error("���ݿ��쳣���жϷ���"+sqle.toString());
				return ExecuteResult.fail;
			} catch (Exception e) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
					processid, new java.util.Date(), e));
			log.error("δ֪�쳣���жϷ���"+e.toString());
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
	}

	@Override
	public void relesase() throws SQLException {
		con = null;

	}

}
