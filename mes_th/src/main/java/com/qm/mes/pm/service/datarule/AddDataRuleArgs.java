package com.qm.mes.pm.service.datarule;

import java.io.IOException;
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
import com.qm.mes.pm.bean.DataRuleArg;
import com.qm.mes.pm.factory.DataRuleFactory;
import com.qm.mes.util.SerializeAdapter;

/**
 * ������ݹ������
 * 
 * @author Xujia
 * 
 */
public class AddDataRuleArgs extends AdapterService {//�������ݿ����
    Connection con = null;
    /**
     * ���ݹ���Id
     */
    private String int_dataruleid =null ;
    /**
     * ��������
     */
    private String count=null;
	/**
	 * ��־
	 */
	private final Log log = LogFactory.getLog(AddDataRuleArgs.class);
	DataRuleFactory dataruleFactory = new DataRuleFactory();
	
    // ������Ϣ�浽map��(������,����),��map����ת����String����
	HashMap<String, String> args = new HashMap<String, String>();
	SerializeAdapter sa = new SerializeAdapter();

    
    @Override
    public void relesase() throws SQLException {
    con = null;
    
    
    }

    @Override
    public boolean checkParameter(IMessage message, String processid) {
    	String debug = "";
		con = (Connection) message.getOtherParameter("con");
        //ͨ�����ֲ�ͬ�Ĵ��ݲ�����ʽ���ж������ַ�ʽ����
		int_dataruleid = message.getOutputParameterValue("int_dataruleid");
		count = message.getUserParameterValue("count");
        if(message.getUserParameterValue("argss") != null){
	        try {
	        	//������ת��Ϊmap(˳���,��չ������)
				args = (HashMap<String, String>) sa.toObject(message
						.getUserParameterValue("argss"));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			//���log��Ϣ
		    debug ="���ݹ���Id��" + int_dataruleid + "��"+"����������"+count;
		    if(Integer.parseInt(count)!=0)debug+=";������Ϣ��\n";
		    for(int j=1;j<=Integer.parseInt(count);j++){
		    	debug+="��������"+args.get("str_argsname"+j)+";";
		    	debug+="������Ϣ��"+args.get("str_argsdescription"+j);
		    	if(j!=1)debug+=";\n";
		    	if(j!=Integer.parseInt(count))debug+="\n";
		    }
        }
        
      
	    log.debug("������ݹ���ʱ�û��ύ�Ĳ���: " + debug);

		if (args == null ||count == null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
					processid, new java.util.Date(), null));
			log.fatal("���ݹ������ơ�������Ϊ�ղ������˳�����");
			return false;
		}

		return true;
    }

    @Override
    public ExecuteResult doAdapterService(IMessage message, String processid){
        try {
			try {                				
                
                    for (int i = 1; i <= Integer.parseInt(count); i++) {                    
                    DataRuleArg dataruleArg = new DataRuleArg();                   
                    dataruleArg.setInt_dataruleid(new Integer(int_dataruleid));               
                    dataruleArg.setName(args.get("str_argsname"+i));
                    dataruleArg.setDescription(args.get("str_argsdescription"+i));
                    dataruleFactory.createDataRuleArgs(dataruleArg, con);
                    }
					log.info("������Ӳ����ɹ���");
                
               
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
