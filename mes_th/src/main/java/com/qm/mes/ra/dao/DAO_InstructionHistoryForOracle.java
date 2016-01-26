package com.qm.mes.ra.dao;

import java.text.SimpleDateFormat;

import com.qm.mes.ra.bean.Instruction;

public class DAO_InstructionHistoryForOracle implements DAO_InstructionHistory{
	//ʵ����DAO_InstructionHistory�ӿ�,���ṩһ�����oracle��sql��伯
	/**
	 * 
	 * ����ָ��汾���ݵ�sql���
	 * л����
	*/
	public String saveVersionHistory(Instruction instruction,String str_versioncode){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String a=instruction.getProduceDate()==null||instruction.getProduceDate().toString().equals("null")?"":(""+sdf.format(instruction.getProduceDate())+"");
		  String b=instruction.getPlanDate()==null||instruction.getPlanDate().toString().equals("null")?"":(""+sdf.format(instruction.getPlanDate())+"");
		  
		  
		  String c=instruction.getPlanBegin()==null||instruction.getPlanBegin().toString().equals("null")?"":(""+sdf.format(instruction.getPlanBegin())+"");
		
		  String d=instruction.getPlanFinish()==null||instruction.getPlanFinish().toString().equals("null")?"":(""+sdf.format(instruction.getPlanFinish())+"");
		  String sql = "insert into t_ra_InstructionHistory "
			+ "values(seq_RA_INSTRUCTIONHistory.nextval,"
			+ instruction.getProdunitid() + ","
			
			+"to_date('"+
			a
			+"','yyyy-mm-dd hh24:mi:ss')"+ ",'"
			+  str_versioncode + "',"
			+ instruction.getInstructionOrder() + ","
			+"to_date('"+
			b
			+"','yyyy-mm-dd hh24:mi:ss')"+ ","
			+ instruction.getPlanOrder() + ",'"
			+ instruction.getProduceType() + "','"
			+ instruction.getProduceName() + "','"
			+ instruction.getProduceMarker() + "','"
			+ instruction.getWorkOrder() + "','"
			+ instruction.getWorkTeam() + "',"
			+"to_date('"+
			c
			+"','yyyy-mm-dd hh24:mi:ss')"+ ","
			
			+"to_date('"+
			d
			+"','yyyy-mm-dd hh24:mi:ss')"+ ","
			+ instruction.getCount()+ ","
			+ instruction.getInstructStateID() 
			+")";
		
			return sql;
	}
	
	/**
	 * 
	 * ͨ���汾�Ų��ָ��汾���ݵ�sql���
	 * л����
	*/
	public String getVersionHistory(String versionCode){
		//
		String sql = "select to_char(Dat_produceDate,'yyyy-MM-dd')as produceDate  from t_ra_InstructionHistory "
			+ "where Str_versionCode = '" + versionCode + "'";
		
	    return sql;
	}
	
	/**
	 * 
	 * ͨ���汾��ɾ����Ӧ�İ汾���ݵ�sql���
	 * л����
	*/
	public String delVersionHistory(String versionCode){
		//
		String sql = "delete from t_ra_InstructionHistory where Str_versionCode ='"+versionCode+"'";
       
		return sql;
	}
	/**
	 * 
	 * ͨ��ָ��汾��ŵõ�ָ��汾��¼��
	 * л����
	*/
	public 	String getInstructionHistory(int int_id){
		String sql="select rh.int_id,rh.int_produnitid,rh.Dat_produceDate,rh.Str_versionCode," +
				"rh.Int_instructOrder,rh.Dat_planDate,rh.Int_planOrder,rh.Str_produceType," +
				"rh.Str_produceName,rh.Str_produceMarker,rh.Str_workOrder,rh.Str_workTeam,rh.Tim_planBegin,rh.Tim_planFinish," +
				"rh.Int_count,rh.Int_instructStateID from t_ra_instructionhistory rh,t_ra_instructionversion rv where rh.Str_versioncode=rv.Str_versioncode "
			+" and rh.int_produnitid=rv.int_produnitid and rv.int_id="+int_id+"  order by rh.Int_instructOrder asc";
		
		return sql;
	}
	

   /**
	 * 
	 * ͨ��������Ԫ���������ڲ�ѯָ��汾��¼��
	 * л����
	*/
  public String getInstructionhistorybyproduceunitdate(int int_produnitid,String Dat_produceDate){
	  String sql="select * from t_ra_instructionhistory where int_produnitid="+int_produnitid+" and to_char(Dat_produceDate,'yyyy-MM-dd')='"+Dat_produceDate+"'";
	 
	  return sql;
  }
	 /**
	  * �������İ汾��
	  * л����
	  */
	
	 public    String checkcodebyproduceunitanddateWorkorder(int int_produnitid,String str_date,String workOrder){
		  String sql="select * from t_ra_instructionhistory where int_produnitid="+int_produnitid+" and to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date+"' and str_workOrder='"+workOrder+"' order by int_id desc ";
		 
		  return sql;
	 }
	   /**
	    * л����
      * 
      * ͨ���汾�ŵ�ָ����ʷ���в������ݵļ���
      */
    public    String getInstructionbyversioncode(String versioncode){
    	String sql="select * from t_ra_instructionhistory where str_versioncode='"+versioncode+"'";
    	return sql;
    }
}
