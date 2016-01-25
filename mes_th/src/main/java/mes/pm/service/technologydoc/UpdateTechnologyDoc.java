package mes.pm.service.technologydoc;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import mes.framework.AdapterService;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import mes.pm.bean.TechDocItem;
import mes.pm.bean.TechItemFile;
import mes.pm.bean.TechnologyDoc;
import mes.pm.factory.TechnologyDocFactory;
import mes.util.SerializeAdapter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UpdateTechnologyDoc extends AdapterService {
	/**
	 * �������
	 */
	private Connection con = null;
	/**
	 * ���ղ���˵�����
	 */
	private String int_id ;
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
	 * String�͹��ղ����Map
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
	 * ������Ԫ�����������Ƿ񱻸ı�
	 */
	private String change = null;
	/**
	 * ��Ʒ����ʾ�Ƿ񱻸ı�
	 */
	private String change_materiel = null;
	/**
	 * ��־
	 */
	private final Log log = LogFactory.getLog(UpdateTechnologyDoc.class);
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
		int_id = message.getUserParameterValue("int_id");
		name = message.getUserParameterValue("name");
		materiel = message.getUserParameterValue("materiel");
		description = message.getUserParameterValue("description");
		str_attr_val = message.getUserParameterValue("str_attr_val");
		change = message.getUserParameterValue("change");
		change_materiel = message.getUserParameterValue("change_materiel");
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
		//������update��δ��������count-1
		if(change.equals("false"))
			name_count--;
		if(name_count>0){
			message.addServiceException(new ServiceException(
				ServiceExceptionType.PARAMETERERROR, "���ղ���˵���������Ѿ����ڣ����������룡", this.getId(),
				processid, new java.util.Date(), null));
			log.fatal("���ղ���˵���������Ѿ�����");
			return false;
		}
		//��Ʒ����ʾ��update��δ��������count-1
		if(change_materiel.equals("false"))
			materiel_count--;
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
			try {
				TechnologyDoc technologyDoc = new TechnologyDoc();
				technologyDoc.setId(Integer.parseInt(int_id));
				technologyDoc.setUpdateUID(userid);
				technologyDoc.setName(name);
				technologyDoc.setMateriel(materiel);
				technologyDoc.setDescription(description);
				factory.updateTechnologyDoc(technologyDoc, con);
				log.info("���¹��ղ���˵����ɹ���");
				factory.delTechDocItemByTechnologyDocId(Integer.parseInt(int_id), con);
				log.info("ɾ�����ղ�����ɹ�");
				for(int i=1;i<=attr_count;i++){
					int techItemId = 0;
					TechDocItem techDocItem = new TechDocItem();
					log.debug("��"+i+"�����ղ�����---" +"��ţ�"+attr_val.get("techitemid"+i)+
							"���֣�"+attr_val.get("int_itemprodUnitId"+i)+
							"���Ӽ�������"+attr_val.get("str_itemcontent"+i));
					
					techDocItem.setId(attr_val.get("techitemid"+i)==null||attr_val.get("techitemid"+i).equals("")?0:Integer.parseInt(attr_val.get("techitemid"+i).toString()));
					techDocItem.setTechDocId(Integer.parseInt(int_id));
					techDocItem.setProduceUnitId(Integer.parseInt(attr_val.get("int_itemprodUnitId"+i).toString()));
					techDocItem.setContent(attr_val.get("str_itemcontent"+i)==null?"":attr_val.get("str_itemcontent"+i).toString());
					factory.saveTechDocItem(techDocItem,con);
					log.debug(attr_val.get("file"+i));
					log.debug(attr_val.get("changefile"+i));
					//�жϸù��ղ������Ƿ�����ļ�
					if(attr_val.get("file"+i)!=null&&!attr_val.get("file"+i).equals("")){
						log.info("��"+i+"�����ղ��������ļ�");
						//��øո���ӵĹ��ղ��������
						techItemId = techDocItem.getId();
						//�ж��ļ����������Ƿ�Ϊɾ��
						if(attr_val.get("changefile"+i)!=null&&attr_val.get("changefile"+i).equals("del")){
							//ɾ����������ļ�
							factory.delTechItemFile(techItemId, con);
							log.info("ɾ�����ղ������ļ��ɹ�");
						}
						TechItemFile techItemFile = new TechItemFile();
						techItemFile.setTechDocItemId(factory.getTechDocItemMaxId(con));
						techItemFile.setPathName(attr_val.get("file"+i).toString());
						factory.saveTechItemFile(techItemFile, con);
						log.info("�������ղ������ļ��ɹ�");
					}
					if(attr_val.get("changefile"+i)!=null&&attr_val.get("changefile"+i).equals("")){
							factory.updateTechItemId(techDocItem.getId(),con);
					}
				}
				log.info("��ӹ��ղ�����ɹ�");
			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), processid, new Date(), sqle));
				log.error("UpdateTechnologyDoc��do�з������ݿ��쳣���£�"+sqle.toString());
				return ExecuteResult.fail;
			}
		} catch (Exception e) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
					processid, new java.util.Date(), e));
			log.error("UpdateTechnologyDoc��do�з����쳣����:"+e.toString());
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
	}

	@Override
	public void relesase() throws SQLException {
		con = null;

	}

}