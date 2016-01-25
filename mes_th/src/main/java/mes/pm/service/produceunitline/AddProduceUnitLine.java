package mes.pm.service.produceunitline;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import mes.framework.AdapterService;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import mes.pm.bean.ProLineItem;
import mes.pm.bean.ProduceUnitLine;
import mes.pm.factory.ProduceUnitLineFactory;
import mes.util.SerializeAdapter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AddProduceUnitLine extends AdapterService {
	/**
	 * �������
	 */
	private Connection con = null;
	/**
	 * ������Ԫ�������ú�
	 */
	private int int_id ;
	/**
	 * ������Ԫ����������
	 */
	private String name = null;
	/**
	 * ������Ϣ
	 */
	private String description = null;
	/**
	 * String��������Ԫ��������Map
	 */
	private String str_attr_val = null;
	/**
	 * ������Ԫ��������Map
	 */
	private HashMap<?, ?> attr_val = new HashMap<String, String>();
	/**
	 * ����ת������
	 */
	private SerializeAdapter sa = new SerializeAdapter();
	/**
	 * ������Ԫ������������
	 */
	private int attr_count = 0;
	/**
	 * ��־
	 */
	private final Log log = LogFactory.getLog(AddProduceUnitLine.class);
	/**
	 * ������Ԫ����������������
	 */
	int count=0;
	/**
	 * ������Ԫ�������ù���
	 */
	ProduceUnitLineFactory factory = new ProduceUnitLineFactory();
	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
		name = message.getUserParameterValue("name");
		description = message.getUserParameterValue("description");
		str_attr_val = message.getUserParameterValue("str_attr_val");
		try {
			attr_val = (HashMap<?,?>)sa.toObject(str_attr_val);
			attr_count = Integer.parseInt(message.getUserParameterValue("attr_count"));
			count = factory.getProduceUnitLineCountByName(name, con);
		} catch (Exception e) {
			log.error("AddProduceUnitLine Check���쳣��"+e.toString());
			e.printStackTrace();
			return false;
		}
		
		if(count>0){
			message.addServiceException(new ServiceException(
				ServiceExceptionType.PARAMETERERROR, "������Ԫ�������������Ѿ����ڣ����������룡", this.getId(),
				processid, new java.util.Date(), null));
			log.fatal("������Ԫ�������������Ѿ�����");
			return false;
		}
		//���log��Ϣ
	    String debug="���ƣ�" + name + "��������Ϣ��"+description;
	    log.debug("���������Ԫ��������ʱ�û��ύ�Ĳ���: " + debug);
	    
		return true;
	}

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {
			try {
				ProduceUnitLine assembleDoc = new ProduceUnitLine();
				assembleDoc.setName(name);
				assembleDoc.setDescription(description);
				factory.saveProduceUnitLine(assembleDoc, con);
				log.info("���������Ԫ�������÷���ɹ���");
				int_id = factory.getProduceUnitLineIdByName(name, con);
				for(int i=1;i<=attr_count;i++){
					ProLineItem proLineItem = new ProLineItem();
					log.debug("��"+i+"��������Ԫ��������---˳��ţ�"+attr_val.get("int_itemorder"+i)+
							"��������Ԫ�ţ�"+attr_val.get("int_itemprodUnitId"+i));
					proLineItem.setLineId(int_id);
					proLineItem.setOrder(Integer.parseInt(attr_val.get("int_itemorder"+i).toString()));
					proLineItem.setProduceUnitId(Integer.parseInt(attr_val.get("int_itemprodUnitId"+i).toString()));
					factory.saveProLineItem(proLineItem,con);
				}
				log.info("���������Ԫ�������ݳɹ�");
			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), processid, new Date(), sqle));
				log.error("���ݿ��쳣���жϷ���"+sqle.toString());
				return ExecuteResult.fail;
			}
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
