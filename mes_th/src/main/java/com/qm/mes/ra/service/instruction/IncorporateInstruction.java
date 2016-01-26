package com.qm.mes.ra.service.instruction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qm.mes.framework.AdapterService;
import com.qm.mes.framework.DataBaseType;
import com.qm.mes.framework.ExecuteResult;
import com.qm.mes.framework.IMessage;
import com.qm.mes.framework.ServiceException;
import com.qm.mes.framework.ServiceExceptionType;
import com.qm.mes.ra.bean.*;
import com.qm.mes.ra.dao.DAO_Instruction_cache;
import com.qm.mes.ra.factory.*;
import com.qm.mes.system.dao.DAOFactoryAdapter;
/** �ϲ�ָ��
 * @author XuJia
 */
public class IncorporateInstruction extends AdapterService { 
	/**
	 * �������
	 */
	private Connection con = null;
	/** 
	 * �ϲ�����
	 */
	private int sum;
	/** 
	 * �ϲ�˳���
	 */
	private int arr[] = null;
	/** 
	 * ��������
	 */
	private String str_date=null;
	/** 
	 * ���
	 */
	private String workOrder=null;
	/** 
	 * ������Ԫ��
	 */
	private String ProduceUnitID=null;
	
	ResultSet rs[];
	ResultSet rs1 = null;
	Statement stmt = null;
	Statement stmt1 = null;	
	InstructionCacheFactory instructionCacheFactory =null;		
	ProduceUnitFactory produceunitfactory=null;
	/** 
	 * ������Ԫ����
	 */
	ProduceUnit produceunit=null;
	int num = 0;
	int i;
	int number;
	/**
	 * ��־
	 */
	private final Log log = LogFactory.getLog(IncorporateInstruction.class);

	@Override
	public boolean checkParameter(IMessage message, String processid) {
		try {			
			con = (Connection) message.getOtherParameter("con");
			stmt = con.createStatement();   //��ʼ��
			String a = message.getUserParameterValue("arr");
			String[] strArray = a.split(",");							
			str_date=message.getUserParameterValue("str_date");
			workOrder=message.getUserParameterValue("workOrder");
			ProduceUnitID=message.getUserParameterValue("ProduceUnitID");		
            sum=strArray.length;
            // ���log��Ϣ
			String debug = "������sum=" + sum + "\n" + "˳���ֵ��arr=" + arr + "\n";
			log.debug("������̷���ʱ�û��ύ�Ĳ���: " + debug);
			
			DAO_Instruction_cache dao1 = (DAO_Instruction_cache) DAOFactoryAdapter
					.getInstance(DataBaseType.getDataBaseType(con),
							DAO_Instruction_cache.class);
			log.debug("ͨ��������Ԫ�š��������ڡ���Ρ�ָ��˳��Ų�ѯָ��SQL��䣺"+dao1.getInstructionByProdUnitDateWorkorderOrder(Integer.parseInt(ProduceUnitID),str_date,workOrder,Integer.parseInt(strArray[0])));
			// ͨ��������Ԫ�š��������ڡ�ָ��˳��Ų�ѯָ��
			ResultSet rs0 = stmt.executeQuery(dao1.getInstructionByProdUnitDateWorkorderOrder(Integer.parseInt(ProduceUnitID),str_date,workOrder,Integer.parseInt(strArray[0])));
         	
			String producetype="";
			String workteam="";
			String workorder="";
			int planorder=0;
			String plandate="";
			boolean boolean1=true;
			
			//ȡ��Ԫ���Ƿ������ƻ���֤
			produceunitfactory = new ProduceUnitFactory();
			con = (Connection) message.getOtherParameter("con");
			//ͨ��ID�ŵõ�������Ԫ��Ϣ
			produceunit=produceunitfactory.getProduceUnitbyId(Integer.parseInt(ProduceUnitID), con);
			log.info("ͨ��������Ԫ�Ų�ѯ������Ԫ�ɹ�");
			int unit=produceunit.getInt_planIncorporate();
			//ȡ�ȶ�ֵ
			while (rs0.next()) {
				producetype = rs0.getString("str_producetype");
				workteam = rs0.getString("str_workteam");
				num = rs0.getInt("int_count");
				workorder = rs0.getString("str_workorder");
				plandate=rs0.getString("dat_plandate");
				planorder=rs0.getInt("int_planorder");	
				log.debug("�������ͣ�"+producetype+"�����飺"+workteam+"������:"+num+"����Σ�"+workorder+"���ƻ����ڣ�"+plandate+"���ƻ�˳��ţ�"+planorder);
			}
			
			rs = new ResultSet[strArray.length]; //���峤��
			
			//ѭ��ѡҪ�Ƚϵ�ֵ
			for (i = 1; i < strArray.length; i++) {
				rs[i] = stmt.executeQuery(dao1.getInstructionByProdUnitDateWorkorderOrder(Integer.parseInt(ProduceUnitID),str_date,workOrder,Integer.parseInt(strArray[i])));
			    if (rs[i].next()) {
					String producetype1 = rs[i].getString("str_producetype");
					String workteam1 = rs[i].getString("str_workteam");
					String workorder1=rs[i].getString("str_workorder");
					int planorder1=rs[i].getInt("int_planorder");
					String plandate1=rs[i].getString("dat_plandate");
					boolean boolean2=false;
					if(plandate== null&&plandate1==null){
					    boolean2=true;}
					else if(plandate==null||plandate1==null)
					    {boolean2=false;}
					else{
					    boolean2=(plandate.equals(plandate1));
					}
					if (unit==0||(boolean2&&(planorder==planorder1)))
					{
						//У��
						if ( producetype.equals(producetype1) && workteam.equals(workteam1)&& workorder.equals(workorder1)) 
						{
							// �����ۼ�						
							num += rs[i].getInt("int_count");						
							continue;
						} else
				        	{message.addServiceException(new ServiceException(
									ServiceExceptionType.PARAMETERLOST, "������𡢰��顢��δ��ڲ�ͬ�����ܺϲ�",
									this.getId(), processid, new java.util.Date(),
									null));
				        	log.fatal("������𡢰��顢��δ��ڲ�ͬ�����ܺϲ�");
				        	boolean1=false;
				        	break;
						}
					}					 
					else {
						message.addServiceException(new ServiceException(
								ServiceExceptionType.PARAMETERLOST, "�ƻ����ں�˳��ͬ",
								this.getId(), processid, new java.util.Date(),
								null));
						log.fatal("�ƻ����ں�˳��ͬ");
					    boolean1=false; 					   
					    break;
					}
			    }  //for���� 
					
			} 
			
			return boolean1;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
	}

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {
			try {
		        	
				String a = message.getUserParameterValue("arr");
				String[] strArray = a.split(",");	
				// ��arry�������У�����Ŀ��ָ�
				 int[] int_arr = new int[strArray.length-1];
				 for(int i=0;i<strArray.length-1;i++){
				        int_arr[i] = Integer.parseInt(strArray[i+1]);
				    }
				int flag=0;
				int j=0;
				for(i=1;i<int_arr.length;i++){
					for(j=int_arr.length-1;j>=i;j--){
						if(int_arr[j]>int_arr[j-1]) {
							flag = int_arr[j-1]; 
							int_arr[j-1] = int_arr[j]; 
							int_arr[j] = flag; 
						}
					}
				}
				
                //���ۼӵ����ָ��µ���һ��������
				instructionCacheFactory = new InstructionCacheFactory();
				con = (Connection) message.getOtherParameter("con");
				//����ָ������
				instructionCacheFactory.updateInstructionCacheNum(num,(Integer.parseInt(strArray[0])), con,str_date,(Integer.parseInt(ProduceUnitID)),workOrder);
				log.info("������ʱָ��ɹ�");
				
				
				for(int k=0;k<int_arr.length;k++){
					//ɾ��
					instructionCacheFactory.deleteInstructionCacheByOrder(int_arr[k], con,str_date,(Integer.parseInt(ProduceUnitID)),workOrder);
					log.info("ɾ����ʱָ��ɹ�");
					//��˳���
					instructionCacheFactory.MinusInstructionOrder(Integer.parseInt(ProduceUnitID), str_date,workOrder, int_arr[k], con);
					log.info("��ʱָ�����ɹ�");
				}								
				return ExecuteResult.sucess;

			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), processid, new Date(), sqle));
				log.error("���ݿ��쳣");
				return ExecuteResult.fail;
			}
		} catch (Exception e) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
					processid, new java.util.Date(), e));
			log.error("δ֪����");
			return ExecuteResult.fail;
		} finally {
			if (rs1 != null)
				rs1.close();
			if (stmt != null)
				stmt.close();
		}
		
	}

	@Override
	public void relesase() throws SQLException {
		arr = null;
		con = null;

	}

}
