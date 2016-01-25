package mes.pm.service.assembledoc;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import mes.framework.AdapterService;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import mes.pm.bean.AssDocItem;
import mes.pm.bean.AssembleDoc;
import mes.pm.factory.AssembleDocFactory;
import mes.util.SerializeAdapter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ���װ��ָʾ��
 * 
 * @author Ypeng
 * 
 */
public class AddAssembleDoc extends AdapterService {
	/**
	 * �������
	 */
	private Connection con = null;
	/**
	 * װ��ָʾ����
	 */
	private int int_id ;
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
	 * ��־
	 */
	private final Log log = LogFactory.getLog(AddAssembleDoc.class);
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
		name = message.getUserParameterValue("name");
		materiel = message.getUserParameterValue("materiel");
		description = message.getUserParameterValue("description");
		str_attr_val = message.getUserParameterValue("str_attr_val");
		try {
			attr_val = (HashMap<?,?>)sa.toObject(str_attr_val);
			userid = Integer.parseInt(message.getUserParameterValue("userid"));
			attr_count = Integer.parseInt(message.getUserParameterValue("attr_count"));
			count = factory.getAssembleDocCountByName(name, con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(count>0){
			message.addServiceException(new ServiceException(
				ServiceExceptionType.PARAMETERERROR, "װ��ָʾ�������Ѿ����ڣ����������룡", this.getId(),
				processid, new java.util.Date(), null));
			log.fatal("װ��ָʾ�������Ѿ�����");
			return false;
		}
		//���log��Ϣ
	    String debug="�����û�ID��"+userid+"�����ƣ�" + name + "��"+ "�������ͱ�ʾ��"+materiel+ ";"
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
				assembleDoc.setCreateUID(userid);
				assembleDoc.setName(name);
				assembleDoc.setMateriel(materiel);
				assembleDoc.setDescription(description);
				factory.saveAssembleDoc(assembleDoc, con);
				log.info("���װ��ָʾ������ɹ���");
				int_id = factory.getAssembleDocIdByName(name, con);
				for(int i=1;i<=attr_count;i++){
					AssDocItem assDocItem = new AssDocItem();
					log.debug("��"+i+"��װ��ָʾ��---���֣�"+attr_val.get("str_itemname"+i)+
							"���Ӽ���ʾ��"+attr_val.get("str_itemcode"+i)+"��������"+attr_val.get("str_itemdes"+i));
					assDocItem.setAssDocId(int_id);
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
