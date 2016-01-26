/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.qm.mes.tg.service.gatheritemextends;

import java.sql.Connection;
import java.sql.SQLException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.AdapterService;
import com.qm.mes.framework.ExecuteResult;
import com.qm.mes.framework.IMessage;
import com.qm.mes.framework.ServiceException;
import com.qm.mes.framework.ServiceExceptionType;
import com.qm.mes.tg.bean.GatherItemExt;
import com.qm.mes.tg.factory.GatherItemExtFactory;
import com.qm.mes.util.SerializeAdapter;

/**
 * �ɼ������Ը���
 *
 * @author YuanPeng
 */
public class UpdateGatherItemExt extends AdapterService {
    //���ݿ����Ӷ���
    private Connection con;
    // ���Ը���
	private String gie_count = null;
    //�ɼ������Ժ�
    private int int_id;
    //�ɼ���Id
    private String int_gatherId;
    //�ɼ�˳���
    private String int_order;
    //��������
    private String str_name;
	//��־
	private final Log log = LogFactory.getLog(UpdateGatherItemExt.class);
	
    // ��չ���Դ浽map��(˳���,��չ������),��map����ת����String����
	HashMap<String, String> gie_val = new HashMap<String, String>();
	SerializeAdapter sa = new SerializeAdapter();
	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
        int_gatherId = message.getUserParameterValue("int_gatherId");
        //kind==batch����Ϊ������
        if(message.getUserParameterValue("kind") == "batch"){
        //��չ��������
        gie_count = message.getUserParameterValue("gie_count");
        try {
        	//������ת��ΪMap
			gie_val = (HashMap<String, String>) sa.toObject(message
					.getUserParameterValue("str_gie_val"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//���log��Ϣ
	    String debug="�ɼ���ţ�" + int_gatherId + "��"+"�ɼ�����չ���Ը�����"+gie_count;
	    if(Integer.parseInt(gie_count)!=0)debug+=";�ɼ�����չ���ԣ�\n";
	    for(int j=1;j<=Integer.parseInt(gie_count);j++){
	    	debug+="�ɼ�������˳��ţ�"+gie_val.get("int" + j + "_gieorder")+";";
	    	debug+="�ɼ�����������"+gie_val.get("str" + j + "_name");
	    	if(j!=1)debug+=";\n";
	    	if(j!=Integer.parseInt(gie_count))debug+="\n";
	    }
	    log.debug("���²ɼ�����չ����ʱ�û��ύ�Ĳ���: " + debug);
        }
        //����Ϊ��һ��
        else{
        	//������²ɼ�����չ����
            int_id = new Integer(message.getUserParameterValue("int_id"));
            str_name = message.getUserParameterValue("str_name");
            int_order = message.getUserParameterValue("int_order");
          //���log��Ϣ
    	    String debug="�ɼ�����չ���Ժţ�" + int_id + "��"+ "�ɼ�����չ��������"+str_name+ ";"
    		+ "�ɼ�����չ����˳��ţ�"+int_order;
    	    log.debug("���²ɼ�����չ����ʱ�û��ύ�Ĳ���: " + debug);
        }
		if (int_gatherId == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
					processid, new java.util.Date(), null));
			log.fatal("�ɼ����Ϊ�գ��˳�����");
			return false;
		}
		return true;

	}

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {
			try {

				GatherItemExtFactory gatherItemExtFactory = new GatherItemExtFactory();
                if(message.getUserParameterValue("kind") == "batch"){
                	//��������--��֮ǰ�ɼ�����չ����ɾ�������´���
                    gatherItemExtFactory.delGatherItemExtByGatherId(new Integer(int_gatherId), con);
                    for(int i = 1; i <= Integer.parseInt(gie_count); i++){
                    GatherItemExt gatherItemExt = new GatherItemExt();
                    gatherItemExt.setName(gie_val.get("str_name" + i));
                    gatherItemExt.setGatherId(new Integer(int_gatherId));
                    gatherItemExt.setOrder(new Integer(gie_val.get("int_gieorder" + i)));
                    gatherItemExtFactory.saveGatherItemExt(gatherItemExt, con);
                    }
    				log.info("���²ɼ�����չ���Է���ɹ���");
                }
                else{
                	//������²ɼ�����չ����
                    GatherItemExt gatherItemExt = new GatherItemExt();
                    gatherItemExt.setId(new Integer(int_id));
                    gatherItemExt.setGatherId(new Integer(int_gatherId));
                    gatherItemExt.setOrder(new Integer(int_order));
                    gatherItemExt.setName(str_name);
                    gatherItemExtFactory.updateGatherItemExt(gatherItemExt, con);
    				log.info("���²ɼ�����չ���Է���ɹ���");
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
//		int_id = null;
		str_name = null;
		int_gatherId = null;
		int_order = null;
		con = null;

	}

}
