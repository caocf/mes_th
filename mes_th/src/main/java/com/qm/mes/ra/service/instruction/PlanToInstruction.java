package com.qm.mes.ra.service.instruction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.qm.mes.framework.AdapterService;
import com.qm.mes.framework.DataBaseType;
import com.qm.mes.framework.ExecuteResult;
import com.qm.mes.framework.IMessage;
import com.qm.mes.framework.ServiceException;
import com.qm.mes.framework.ServiceExceptionType;
import com.qm.mes.framework.dao.DAOFactory_UserManager;
import com.qm.mes.framework.dao.IDAO_UserManager;
import com.qm.mes.os.bean.Plan;
import com.qm.mes.os.factory.PlanFactory;
import com.qm.mes.ra.bean.Instruction;
import com.qm.mes.ra.bean.ProduceUnit;
import com.qm.mes.ra.bean.Version;
import com.qm.mes.ra.factory.InstructionFactory;
import com.qm.mes.ra.factory.InstructionHistoryFactory;
import com.qm.mes.ra.factory.InstructionVersionFactory;
import com.qm.mes.ra.factory.ProduceUnitFactory;

/**�ƻ�����ָ��
 * @author л����
 *
 */
public class PlanToInstruction extends AdapterService {
	/**
	 * �������
	 */
	private Connection con=null;
	private  Statement stmt=null;
	/**
	 * ������Ԫ��
	 */
	private String produnitid =null;
	/**
	 * ��������
	 */
	private String overtime=null;
	/**
	 * ���
	 */
	private String workOrder=null;
	/**
	 * �û����
	 */
	private String userid=null;
	
	private PlanFactory planfactory=new PlanFactory();
	private InstructionHistoryFactory historyfactory=new InstructionHistoryFactory();
	private InstructionVersionFactory versionfactory=new InstructionVersionFactory();
	
	public boolean checkParameter(IMessage message, String processid) {
		con=(Connection)message.getOtherParameter("con");
		produnitid=message.getUserParameterValue("produceided");
		overtime=message.getUserParameterValue("overtime");
		workOrder=message.getUserParameterValue("workOrder");
		userid=message.getUserParameterValue("userid");
	
		
		if (produnitid== null) {
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
			//�Ӽƻ�����ָ������滻ֱ��ɾ��ָ����� ������Ԫ����κ��������ڵ�ָ�
			InstructionFactory instructionfactory=new InstructionFactory();
			 //Ԥ�������
			PreparedStatement sqlinstruction=null;
			//��������ʱ���������Ԫɾ��ָ��
			instructionfactory.deleteInstructionByProduceUnitProduceDateWorkorder(Integer.parseInt(produnitid), overtime,workOrder, con);
			ProduceUnitFactory producefactory=new  ProduceUnitFactory();
			//ͨ��������Ԫ��id�鵽������Ԫ�ĳ�ʼָ��״̬
			int instructstateid= producefactory.getInstructionstateIdByproduceunitid(Integer.parseInt(produnitid), con);//��ȡ������Ԫ��״̬
			List<Plan> planlist=new ArrayList<Plan>();
			String first="01"; 
			String second=null;
			String str_versioncodeinstruction=null;
			String str_versioncode=null;
			//��ȡ��ʷ���� �汾�� �������İ汾��
			String code= historyfactory.checkcodebyproduceunitanddateWorkorder(Integer.parseInt(produnitid), overtime,workOrder, con);
			if(!code.equals("")){
				int leng=code.length();
				//ʮλ
		  	    String code1=code.substring(leng-2,leng-1);
				//��λ
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
			//��ȡ�û�����
			String username=null;
      		int  userId = Integer.parseInt(userid);
   	        IDAO_UserManager dao_UserManager = DAOFactory_UserManager.getInstance(DataBaseType.getDataBaseType(con));
   	        String sql = dao_UserManager.getSQL_SelectUserById(userId);
   	        stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
   	        ResultSet rs = stmt.executeQuery(sql);
   	        if(rs.next()){
   	        	username = rs.getString("CUSRNAME");
   	        }
   	        else{
   	        	username = null;
   	        }
 
			ProduceUnit produceunit=new ProduceUnit();
		    // ͨ��������Ԫid��ȡ������Ԫ��ͬʱ���ɰ汾��
            produceunit=producefactory.getProduceUnitbyId(Integer.parseInt(produnitid), con);
		    String produce_name=produceunit.getStr_name();
		    String [] date=new String [3];
		    date =overtime.split("-");
	        String producedate= date[0]+date[1]+date[2];
	        str_versioncode=producedate+produce_name+workOrder+first;//��ǰָ����еİ汾��
	        str_versioncodeinstruction=producedate+produce_name+workOrder+second;//Ҫ���ɵİ汾��
			planlist=planfactory.getmaxPlanexcepttempupload(overtime, Integer.parseInt(produnitid), workOrder, con);
		    Iterator<Plan> iter=planlist.iterator();
		    String sqlinstructionstring="select st.*,to_char(st.dat_producedate,'yyyy-MM-dd') as producedate from t_ra_instruction st  where str_produceMarker=? and int_produnitid="+Integer.parseInt(produnitid)+" and int_delete=0 ";
		    sqlinstruction=con.prepareStatement(sqlinstructionstring);
		    int i=0;
		    while(iter.hasNext()){
		    	Plan plan=(Plan)iter.next();
		    	sqlinstruction.setString(1, plan.getProduceMarker());
				rs=sqlinstruction.executeQuery();
				if(rs.next())//���ָ������ɴ������ϵ�ָ����ȫָ�����Ϣ��
				{
					 i++;
					 message.setOutputParameter("yes"+i,produceunit.getStr_name()+" "+rs.getString("producedate")+" "+rs.getString("str_workOrder")+" "+rs.getString("str_versioncode")+" "+"�汾��ָ�����Ѿ�ʹ���˴�������ֵ:"+plan.getProduceMarker());
					 Instruction instruction=new Instruction();
					 instruction.setId(rs.getInt(1));
					 instruction.setProdunitid(rs.getInt(2));
					 instruction.setProduceDate(rs.getDate(3));
					 instruction.setVersionCode(rs.getString(4));
					 instruction.setInstructionOrder(rs.getInt(5));
					 instruction.setPlanDate(plan.getPlanDate());
					 instruction.setPlanOrder(plan.getPlanOrder());
					 instruction.setProduceType(rs.getString(8));
					 instruction.setProduceName(rs.getString(9));
					 instruction.setProduceMarker(rs.getString(10));
					 instruction.setWorkOrder(rs.getString(11));
					 instruction.setWorkTeam(rs.getString(12));
					 instruction.setPlanBegin(rs.getString(13)==null?null:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString(13)));
					 instruction.setPlanFinish(rs.getString(14)==null?null:new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString(14)));
					 instruction.setCount(rs.getInt(15));
					 instruction.setInstructStateID(rs.getInt(16));
					 instruction.setIssuance(rs.getInt(17));
					 instruction.setStaError(rs.getInt(18));
					 instruction.setDelete(rs.getInt(19));
					 instruction.setEdit(rs.getInt(20));
					 instructionfactory.updateInstruction(instruction, con);
					 continue;
				}//���û�����ռƻ�����ָ��
		    	Instruction instruction=new Instruction();
		    	instruction.setProdunitid(Integer.parseInt(produnitid));//������Ԫ
		    	instruction.setProduceDate(new SimpleDateFormat("yyyy-MM-dd").parse(overtime));//��������
		    	instruction.setVersionCode(str_versioncodeinstruction);//�汾��
		    	instruction.setInstructionOrder(plan.getPlanOrder());//ָ��˳���
		    	instruction.setPlanDate(plan.getPlanDate());//�ƻ�����
		    	instruction.setPlanOrder(plan.getPlanOrder());//�ƻ�˳���
		    	instruction.setProduceType(plan.getProduceType());//��Ʒ����ʶ
		    	instruction.setProduceName(plan.getProduceName());//��Ʒ����
		    	instruction.setProduceMarker(plan.getProduceMarker());//��Ʒ��ʶ
		    	instruction.setWorkOrder(workOrder);//���
		    	instruction.setWorkTeam(plan.getWorkTeam());//����
		    	instruction.setPlanBegin(null);
		    	instruction.setPlanFinish(null);
		    	instruction.setCount(plan.getAmount());//����
		    	instruction.setInstructStateID(instructstateid);//ָ��״̬
		    	instruction.setIssuance(0);//������ʶ
		    	instruction.setStaError(0);//�쳣
		    	instruction.setDelete(0);//ɾ����ʶ
		    	instruction.setEdit(0);//�༭��ʶ
		    	instructionfactory.saveInstruction(instruction, con);//����ָ��
		    	historyfactory.saveInstruction(instruction, str_versioncode, con);//����ָ����ʷ
		    }
		     message.setOutputParameter("num", String.valueOf(i));
		    //���ɰ汾
		    if(planlist.size()!=i){
		    	Version version=new Version();
		    	version.setInt_delete(0);//ɾ����ʶ
		    	version.setProdunitid(Integer.parseInt(produnitid));//������Ԫ 
		    	version.setVersionCode(str_versioncode);//�汾��
		    	version.setUser(username);
		    	versionfactory.saveVersion(version, con);
		    }
	    }
	    catch (SQLException sqle) {
		message.addServiceException(new ServiceException(
				ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
						.getId(), processid, new Date(), sqle));
		return ExecuteResult.fail;
	    }
	     catch (Exception e) {
	    message.addServiceException(new ServiceException(
			ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
			processid, new java.util.Date(), e));
	      return ExecuteResult.fail;
	    }
	    return ExecuteResult.sucess;
	   }

    public void relesase() throws SQLException {
        con = null;
        produnitid =null;
    	overtime=null;
        workOrder=null;
      if(stmt!=null){
    	  stmt.close();
      }
      

     }
	
	
	

}
