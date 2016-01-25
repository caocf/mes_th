package mes.ra.service.version;

import mes.framework.AdapterService;
import mes.framework.IMessage;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import mes.framework.ExecuteResult;
import java.util.*;
import java.sql.*;
import java.util.ArrayList;
import mes.ra.factory.*;
import mes.os.factory.*;
import mes.ra.bean.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**�汾�ָ�
 * @author л����
 *
 */

public class Comeback extends AdapterService {
	private Connection con=null;
	/**
	 * ������Ԫid  ��
	 */
	private String Str_produceUnit=null ;
	/**
	 * ��������
	 */
	private String str_date=null;
	/**
	 * �汾��
	 */
	private String versioncode=null;
	/**
	 * ��� 
	 */
	private String workOrder=null;
	 /**
	 * sqlinstruction ָ��Ԥ�������
	 */
	PreparedStatement sqlinstruction=null;
	 /**
	 * sqlplanall �ƻ�Ԥ�������
	 */
	PreparedStatement sqlplanall=null;
	private final Log log = LogFactory.getLog(Comeback.class);
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
		Str_produceUnit=message.getUserParameterValue("Str_produceUnit");
		str_date=message.getUserParameterValue("str_date");
	    versioncode=message.getUserParameterValue("versioncode");
	    workOrder=message.getUserParameterValue("workOrder");
	    //���log��Ϣ
		String debug="Str_produceUnit:" + Str_produceUnit 
			+ "versioncode:"+versioncode+ "str_date"+str_date+ "\n";
		    log.info("���״̬ת���Ĳ���: " + debug);
		if (str_date== null||Str_produceUnit==null) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
					processid, new java.util.Date(), null));
			return false;
		}
		return true;
	}

	public ExecuteResult doAdapterService(IMessage message, String processid)
		throws SQLException, Exception {
	try {
		try {
			//Ԥ�������
			ResultSet rs=null;
			List<Instruction> list_instruction = new ArrayList<Instruction>();
			//ɾ��ָ�������������Ԫ������������ͬ��ָ��
			InstructionFactory instructionfactory=new InstructionFactory();
			ProduceUnitFactory produceunitfactory=new ProduceUnitFactory();
			WorkSchedleFactory workschedlefactory=new WorkSchedleFactory();
			ProduceUnit produceuint=new ProduceUnit();
			produceuint=produceunitfactory.getProduceUnitbyId(Integer.parseInt(Str_produceUnit), con);
			InstructionHistoryFactory instructionhistoryfactory=new InstructionHistoryFactory();
			String str_versioncode="";
			String first=null;
			list_instruction=instructionfactory.getInstructionByProduceUnitProduceDateWorkorder(Integer.parseInt(Str_produceUnit), str_date,workOrder, con);
			if(list_instruction.size()!=0){
				Instruction instruction1=(Instruction)list_instruction.get(0);
				str_versioncode=instruction1.getVersionCode();
			}
			else{
				str_versioncode=instructionhistoryfactory.checkcodebyproduceunitanddateWorkorder(Integer.parseInt(Str_produceUnit), str_date, workOrder, con);
				if (!str_versioncode.equals("") && str_versioncode != null) {
					int leng = str_versioncode.length();
					// ʮλ
					String code1 = str_versioncode.substring(leng - 2,
							leng - 1);
					// ��λ
					String code2 = str_versioncode.substring(leng - 1, leng);
					int gewei = Integer.parseInt(code2) + 1;
					if (gewei <= 9) {
						first = code1 + String.valueOf(gewei);
					} else {
						int shiwei = Integer.parseInt(code1) + 1;
						first = String.valueOf(shiwei) + "0";
					}
				}
				String[] name = new String[3];
				name = str_date.split("-");
				String namess = name[0] + name[1] + name[2];
				str_versioncode = namess + produceuint.getStr_name() + workOrder + first;
			}
			 
			list_instruction.clear();

			//ѭ����list�е�ָ����ӵ�ָ�����
			list_instruction=instructionhistoryfactory.getInstructionbyversioncode(versioncode, con);
			String s="(";
			for(int i=0;i<list_instruction.size();i++){
				if(i==list_instruction.size()-1){
					s=s+"?";
				}
				else 
					s=s+"?,";
			}
			s=s+")";
			
			String sql="select * from t_ra_instruction where  int_delete=0   and str_producemarker in"+s+" and str_versioncode in(select "
					+"max(str_versioncode) from t_ra_instruction where int_produnitid="+Integer.parseInt(Str_produceUnit)+"   group by  int_produnitid ,dat_producedate,str_workOrder) and str_versioncode"
					+"<>'"+str_versioncode+"' ";
			sqlinstruction=con.prepareStatement(sql);
			
			for(int i=0;i<list_instruction.size();i++){
				Instruction instruction=(Instruction)list_instruction.get(i);
				sqlinstruction.setString(i+1, instruction.getProduceMarker());
			}
			rs=sqlinstruction.executeQuery();
			if(rs.next()){
				message.addServiceException(new ServiceException(
						ServiceExceptionType.PARAMETERLOST, "������ֵ���ظ����ܽ��а汾�ָ�", this.getId(),
						processid, new java.util.Date(), null));
				return ExecuteResult.fail;
			}
			else{
				instructionfactory.deleteInstructionByProduceUnitProduceDateWorkorder(Integer.parseInt(Str_produceUnit), str_date, workOrder,con);
				String  sqlplanstring="select p.*,to_char(p.Dat_produceDate,'yyyy-MM-dd') as producedate from t_os_plan p where str_versioncode in(select max(str_versioncode) from t_os_plan  where int_upload=1  and int_produnitid="+Integer.parseInt(Str_produceUnit)+" group by int_produnitid,Dat_produceDate,str_workOrder "
		            +") and  str_producemarker=? ";
				
				sqlplanall =con.prepareStatement(sqlplanstring);
				for(int i=0;i<list_instruction.size();i++){
					Instruction instruction=(Instruction)list_instruction.get(i);
					//����ƻ���ϢΪ�� ����������ֵ���ƻ����Ƿ������汾����ʵ�����ļƻ�������ء�
					if(instruction.getPlanOrder()==0||instruction.getPlanDate()==null){
						sqlplanall.setString(1,instruction.getProduceMarker());
						ResultSet rsprepare= sqlplanall.executeQuery();
						if(rsprepare.next()){
							//���Ƿ���ʵ���� 				 
							long locked= workschedlefactory.getworkschedleadtime(rsprepare.getInt("int_produnitid"),rsprepare.getString("producedate"),rsprepare.getString("str_workorder"),con);
							if(locked==0){
								//�������ͬ�������ϲ�����ʵ������ȫ��صļƻ���Ϣ
								instruction.setPlanDate(rsprepare.getDate("dat_planDate")); 
								instruction.setPlanOrder(rsprepare.getInt("int_planOrder"));
							}
						}
					}
					instruction.setVersionCode(str_versioncode);
					instructionfactory.saveInstruction(instruction, con);
				}
			}
		} catch (SQLException sqle) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
							.getId(), processid, new java.util.Date(), sqle));
			log.error("�汾�ָ�ʱ���ݿ��쳣"	+ sqle.toString());
			return ExecuteResult.fail;
		}
	} catch (Exception e) {
		message.addServiceException(new ServiceException(
				ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
				processid, new java.util.Date(), e));
		log.error("�汾�ָ�ʱ���ݿ�δ֪�쳣"	+ e.toString());
		return ExecuteResult.fail;
	}
	return ExecuteResult.sucess;
	}

	public void relesase() throws SQLException {
	   	     con = null;
	   	      //������Ԫ
	   		 Str_produceUnit=null ;
	   		 //��������
	   		 str_date=null;
	   		 workOrder=null;
	   		 if(sqlinstruction!=null)
	   		 sqlinstruction.close();
	   		 if(sqlplanall!=null)
	         sqlplanall.close();
	   }
}
