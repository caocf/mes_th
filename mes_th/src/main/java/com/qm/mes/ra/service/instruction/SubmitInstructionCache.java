package com.qm.mes.ra.service.instruction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Iterator;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.AdapterService;
import com.qm.mes.framework.DataBaseType;
import com.qm.mes.framework.ExecuteResult;
import com.qm.mes.framework.IMessage;
import com.qm.mes.framework.ServiceException;
import com.qm.mes.framework.ServiceExceptionType;
import com.qm.mes.framework.dao.DAOFactory_UserManager;
import com.qm.mes.framework.dao.IDAO_UserManager;
import com.qm.mes.ra.bean.Instruction;
import com.qm.mes.ra.bean.ProduceUnit;
import com.qm.mes.ra.bean.Version;
import com.qm.mes.ra.factory.InstructionCacheFactory;
import com.qm.mes.ra.factory.InstructionFactory;
import com.qm.mes.ra.factory.InstructionHistoryFactory;
import com.qm.mes.ra.factory.InstructionVersionFactory;
import com.qm.mes.ra.factory.ProduceUnitFactory;

/**
 * �ύ��ҵָ����ʱ��
 *
 * @author YuanPeng
 */

public class SubmitInstructionCache extends AdapterService { 
    /**
     * ���ݿ����Ӷ���
     */
    Connection con = null;
    /**
     * ��������Ԫ��������������ʱ���ж�ȡ��ָ����󼯺�
     */
    private List<Instruction> list =new ArrayList<Instruction>();
    /**
     * ָ���
     */
    private InstructionFactory instructionFactory = null;
    /**
     * ��ʱָ���
     */
    private InstructionCacheFactory instructionCacheFactory = null;

    /**
     * ������Ԫ��
     */
    private int ProduceUnitID ;
    /**
     * �ַ���������
     */
    private String str_date = null;
    /**
     * �Ƿ񱣴�汾
     */
    private String str_saveversion = null;
    /**
     * ���������׸�˳���
     */
    private int UnlockStartOrder;
    /**
     * �汾����
     */
    private Version version = new Version();
    /**
     * �汾��
     */
    private String str_versioncode = null;
    /**
     * �û�ID
     */
    private int userId ;
    /**
     * �û���
     */
    private String username = null;
    /**
     *���
     */
    private String workOrder=null;
	/**
	 * ��־
	 */
	private final Log log = LogFactory.getLog(SubmitInstructionCache.class);
    
    /**
     * ��������
     *
     * @throws java.sql.SQLException
     */
    @Override
    public void relesase() throws SQLException {
        con = null;
    }

    /**
     * ������
     *
     * @param message
     *              ʹ��IMessage�����������
     * @param processid
     *              ����ID
     * @return booleanֵ
     *              ����booleanֵ����ʾ�ɹ����
     */
    @Override
    public boolean checkParameter(IMessage message, String processid) {
    	try {
	    	con = (Connection) message.getOtherParameter("con");
	        ProduceUnitID = Integer.parseInt(message.getUserParameterValue("str_ProduceUnitID"));
	        workOrder=message.getUserParameterValue("workOrder");
	        str_date = message.getUserParameterValue("str_date");
	        str_saveversion = message.getUserParameterValue("saveversion");
	        UnlockStartOrder = Integer.parseInt(message.getUserParameterValue("str_UnlockStartOrder"));
	        userId = Integer.parseInt(message.getUserParameterValue("userid"));
	        log.debug("������Ԫ�ţ�"+ProduceUnitID+"���������ڣ�"+str_date+"���Ƿ񱣴�汾��"+str_saveversion+
	        		"����������˳��ţ�"+UnlockStartOrder+"���û��ţ�"+userId);
	        IDAO_UserManager dao_UserManager = DAOFactory_UserManager.getInstance(DataBaseType.getDataBaseType(con));
	        String sql = dao_UserManager.getSQL_SelectUserById(userId);
	        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	        log.debug("ͨ���û��Ų�ѯ�û�����SQL��䣺"+sql);
	        ResultSet rs = stmt.executeQuery(sql);
	        if(rs.next()){
	        	username = rs.getString("CUSRNAME");
	        	log.debug("�û�����"+username);
	        }
	        else{
	        	username = null;
	        	log.debug("�û���Ϊ��");
	        }
	        try {
				version.setVersionDatime((new SimpleDateFormat("yyyy-MM-dd").parse(str_date)));
				log.debug("�汾���ڣ�"+version.getVersionDatime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
	    } catch (Exception e) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
					processid, new java.util.Date(), e));
			log.error("δ֪�쳣");
			return false;
		}
        return true;
    }

    /**
     * ִ�з���
     *
     * @param message
     *              ʹ��IMessage�����������
     * @param processid
     *              ����ID
     * @return ExecuteResult
     *                  ִ�н��
     * @throws java.sql.SQLException
     *                          �׳�SQL�쳣
     * @throws java.lang.Exception
     *                          �׳������쳣
     */
    @Override
    public ExecuteResult doAdapterService(IMessage message, String processid) throws SQLException, Exception {
        try {
			try {
				
				
				
				
				
				instructionFactory = new InstructionFactory();
				instructionCacheFactory = new InstructionCacheFactory();
				//ͨ��������Ԫ�š��������ڡ�˳��Ŵ����׸��Ǳ༭��������int_delete�ֶ�ֵΪ1
				instructionFactory.DeleteInstructionByProUnitDateworkOrderUnlockOrder(ProduceUnitID,str_date,workOrder,UnlockStartOrder,con);
				log.info("ɾ����������Ԫ���������ڡ�δ����˳���ָ��ɹ�");
				//��������ʱ���������Ԫ�鿴��ص�ָ� 
				list = instructionCacheFactory.getInstructionCacheByProduceUnitProduceDateOrder(ProduceUnitID, str_date,workOrder, con);
				
				log.info("��ѯ��������Ԫ���������ڵ���ʱָ��ɹ�");
                for(Instruction in:list){
                	instructionFactory.saveInstruction(in, con);
                	
                }
                log.info("����δ����ָ��ɹ�");
                //ɾ����ʱ���и�������Ԫ��������
                instructionCacheFactory.DeleteInstructionCacheByProdUnitIdproducedateWorkorder(ProduceUnitID,str_date,workOrder,con);
               
                if(str_saveversion.equals("true")){
                	String code=null;
                	String first="01";
                	String second=null;
                	InstructionHistoryFactory historyfactory=new InstructionHistoryFactory();
                	// �������İ汾��
                	code = historyfactory.checkcodebyproduceunitanddateWorkorder(ProduceUnitID,str_date,workOrder, con);
                		if(!code.equals("")){
                			int leng=code.length();
                			//ʮλ
                			String code1=code.substring(leng-2,leng-1);
                			//	��λ
                			String code2=code.substring(leng-1,leng);
                			int gewei=Integer.parseInt(code2)+1;
                			if(gewei<=9){
                				first=code1+String.valueOf(gewei);
                			}
                			else{
                				int shiwei=Integer.parseInt(code1)+1;
                			    first=String.valueOf(shiwei)+"0";
                			}
                		}
                		if(Integer.parseInt(first)+1<=9){
                			second="0"+String.valueOf((Integer.parseInt(first)+1));
                		}
                		else{
                			second=String.valueOf((Integer.parseInt(first)+1));
                		}
                		ProduceUnitFactory producefactory=new  ProduceUnitFactory();
                		ProduceUnit produceunit=new ProduceUnit();
                		List<Instruction> list = new ArrayList<Instruction>();
                		List<Instruction> list2 = new ArrayList<Instruction>();
                		Version version=new Version();
                		//�õ�������Ԫid��������Ԫ��Ϊ�����汾��׼��
                		produceunit=producefactory.getProduceUnitbyId(ProduceUnitID, con);
                		String str_name=produceunit.getStr_name();
                		//Ŀ����Ϊ�˷����ʱ����20090403
                		String [] u1=new String [3];
                		u1 =str_date.split("-");
                		String ss= u1[0]+u1[1]+u1[2];
                		String s =ss+str_name+workOrder+first;
                		String str_versioncode1=ss+str_name+workOrder+second;
                		message.setOutputParameter("saveversion", s);
                		//���ɰ汾��
                		str_versioncode=s;
                		version.setInt_delete(0);
                		version.setVersionCode(str_versioncode);
                		version.setProdunitid(ProduceUnitID);
                		version.setUser(username);
                		InstructionVersionFactory factory=new  InstructionVersionFactory();
                		InstructionFactory instructionfactory=new InstructionFactory ();
                		factory.saveVersion(version, con);
                		//��������ʱ���������Ԫ�鿴��ص�ָ��
                		list2=instructionfactory.getInstructionByProduceUnitProduceDateWorkorder(ProduceUnitID,str_date,workOrder, con);
                		Iterator<Instruction> iter2=list2.iterator();
                		//����ָ��汾
                		while(iter2.hasNext())
                		{ 
                			Instruction instruction=(Instruction)iter2.next();
                			//����ָ��汾,�������µİ汾ʱ����,����ָ����еİ汾�ֶ�
                			instructionfactory.updateInstructionVersioncode(instruction, str_versioncode1, con);
                			    	  
                		}
                		//��������ʱ���������Ԫ�鿴��ص�ָ��
                		list=instructionfactory.getInstructionByProduceUnitProduceDateWorkorder(ProduceUnitID,str_date, workOrder,con);
                		InstructionHistoryFactory history=new InstructionHistoryFactory();
                		Iterator<Instruction> iter=list.iterator();
                		//����ָ��汾��ʷ��
                		while(iter.hasNext())
                		{ 
                			Instruction instruction=(Instruction)iter.next();
                			history.saveInstruction(instruction,str_versioncode, con);		   
                		}
					}
                else
                	   message.setOutputParameter("saveversion", "no");
			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), processid, new Date(), sqle));
				return ExecuteResult.fail;
			}
		} catch (Exception e) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
					processid, new java.util.Date(), e));
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
    }
}
