/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.qm.mes.tg.service.gatheritemextends;

import java.sql.Connection;
import java.util.Date;
import java.sql.SQLException;
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

import java.io.IOException;

/**
 *��Ӳɼ���������
 *
 * @author YuanPeng
 */
public class AddGatherItemExt extends AdapterService{
    //�������ݿ����
    Connection con = null;
    //�ɼ���Id
    private String int_gatherId =null ;
    //��չ���Ը���
    private String gie_count = null;
    //�ɼ�����չ����˳���
    private String order = null;
    //�ɼ�����չ������
    private String str_name = null;
	//��־
	private final Log log = LogFactory.getLog(AddGatherItemExt.class);
	
    // ��չ���Դ浽map��(˳���,��չ������),��map����ת����String����
	HashMap<String, String> gie_val = new HashMap<String, String>();
	SerializeAdapter sa = new SerializeAdapter();

    
    @Override
    public void relesase() throws SQLException {
    con = null;
    int_gatherId =null ;
    gie_count = null;
    
    }

    @Override
    public boolean checkParameter(IMessage message, String processid) {
    	String debug = "";
		con = (Connection) message.getOtherParameter("con");
        //ͨ�����ֲ�ͬ�Ĵ��ݲ�����ʽ���ж������ַ�ʽ����
        if(message.getUserParameterValue("int_gatherId") == null){
            int_gatherId = message.getOutputParameterValue("int_gatherId");
        }
        else
            int_gatherId = message.getUserParameterValue("int_gatherId");
		gie_count = message.getUserParameterValue("gie_count");
        if(message.getUserParameterValue("str_gie_val") != null){
	        try {
	        	//������ת��Ϊmap(˳���,��չ������)
				gie_val = (HashMap<String, String>) sa.toObject(message
						.getUserParameterValue("str_gie_val"));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			//���log��Ϣ
		    debug ="�ɼ���ţ�" + int_gatherId + "��"+"�ɼ�����չ���Ը�����"+gie_count;
		    if(Integer.parseInt(gie_count)!=0)debug+=";�ɼ�����չ���ԣ�\n";
		    for(int j=1;j<=Integer.parseInt(gie_count);j++){
		    	debug+="�ɼ�������˳��ţ�"+gie_val.get("int" + j + "_gieorder")+";";
		    	debug+="�ɼ�����������"+gie_val.get("str" + j + "_name");
		    	if(j!=1)debug+=";\n";
		    	if(j!=Integer.parseInt(gie_count))debug+="\n";
		    }
        }
        if(message.getUserParameterValue("kind") != "batch"){
			order = message.getUserParameterValue("int_order");
			str_name = message.getUserParameterValue("str_name");
			debug = "�ɼ���ţ�" + int_gatherId + "��"+"�ɼ�����չ���Ը�����"+gie_count+
			"���ɼ�����չ����˳��ţ�"+order+"���ɼ�����չ��������"+str_name;
		}
      
	    log.debug("��Ӳɼ�����չ����ʱ�û��ύ�Ĳ���: " + debug);

		if (int_gatherId == null || gie_count == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
					processid, new java.util.Date(), null));
			log.fatal("�ɼ���š��ɼ�������������Ϊ�ղ������˳�����");
			return false;
		}

		return true;
    }

    @Override
    public ExecuteResult doAdapterService(IMessage message, String processid){
        try {
			try {
                //kind==batch����Ϊ������
                if(message.getUserParameterValue("kind") == "batch"){
                    for (int i = 1; i <= Integer.parseInt(gie_count); i++) {
                    GatherItemExtFactory gatherItemExtFactory = new GatherItemExtFactory();
                    GatherItemExt gatherItemExt = new GatherItemExt();
                    gatherItemExt.setGatherId(new Integer(int_gatherId));
                    gatherItemExt.setOrder(new Integer(gie_val.get("int" + i + "_gieorder")));
                    gatherItemExt.setName(gie_val.get("str" + i + "_name"));
                    gatherItemExtFactory.saveGatherItemExt(gatherItemExt, con);
                    }
					log.info("������Ӳɼ�����չ���Է���ɹ���");
                }
                else{
                	//������Ӳɼ�����չ����
                    GatherItemExtFactory gatherItemExtFactory = new GatherItemExtFactory();
                    GatherItemExt gatherItemExt = new GatherItemExt();
					gatherItemExt.setGatherId(new Integer(int_gatherId));
                    gatherItemExt.setOrder(new Integer(order));
                    gatherItemExt.setName(str_name);
                    gatherItemExtFactory.saveGatherItemExt(gatherItemExt, con);
					log.info("������Ӳɼ�����չ���Է���ɹ���");
                }
			}catch (SQLException sqle) {
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
    

}
