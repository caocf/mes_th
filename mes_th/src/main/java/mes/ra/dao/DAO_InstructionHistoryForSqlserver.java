package mes.ra.dao;

import java.text.SimpleDateFormat;


import mes.ra.bean.Instruction;

public class DAO_InstructionHistoryForSqlserver extends DAO_InstructionHistoryForOracle{
	/**
	 * 
	 * ����ָ��汾���ݵ�sql���
	 * л����
	*/
	public String saveVersionHistory(Instruction instruction,String str_versioncode){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String a=instruction.getProduceDate()==null||instruction.getProduceDate().toString().equals("")?"''":"convert(datetime,'"+ sdf.format(instruction.getProduceDate())+ "',20)";
		  String b=instruction.getPlanDate()==null||instruction.getPlanDate().toString().equals("")?"''":"convert(datetime,'"+ sdf.format(instruction.getPlanDate())+ "',20)";
		  
		  
		  String c=instruction.getPlanBegin()==null||instruction.getPlanBegin().toString().equals("")?"''":"convert(datetime,'"+ sdf.format(instruction.getPlanBegin())+ "',20)";
		
		  String d=instruction.getPlanFinish()==null||instruction.getPlanFinish().toString().equals("")?"''":"convert(datetime,'"+ sdf.format(instruction.getPlanFinish())+ "',20)";
		  String sql = "insert into t_ra_InstructionHistory "
			+ "values("
			+ instruction.getProdunitid() + ","
			
			+a+ ",'"
			+  str_versioncode + "',"
			+ instruction.getInstructionOrder() + ","
			+b+ ","
			
			+ instruction.getPlanOrder() + ",'"
			+ instruction.getProduceType() + "','"
			+ instruction.getProduceName() + "','"
			+ instruction.getProduceMarker() + "','"
			+ instruction.getWorkOrder() + "','"
			+ instruction.getWorkTeam() + "',"
		+
			c
			+","
			
			+d+ ","
			+ instruction.getCount()+ ","
			+ instruction.getInstructStateID() 
			+")";
		
			return sql;
	}

   /**
	 * 
	 * ͨ��������Ԫ���������ڲ�ѯָ��汾��¼��
	 * л����
	*/
  public String getInstructionhistorybyproduceunitdate(int int_produnitid,String Dat_produceDate){
	  String sql="select * from t_ra_instructionhistory where int_produnitid="+int_produnitid+" and Dat_produceDate=convert(datetime,'"+ Dat_produceDate+ "',20)";
	 
	  return sql;
  }
	 /**
	  * �������İ汾��
	  * л����
	  */
	
	 public    String checkcodebyproduceunitanddateWorkorder(int int_produnitid,String str_date,String workOrder){
		  String sql="select * from t_ra_instructionhistory where int_produnitid="+int_produnitid+" and str_workorder='"+workOrder+"' and Dat_produceDate=convert(datetime,'"+ str_date+ "',20) order by int_id desc ";
		 
		  return sql;
	 }
	
}
