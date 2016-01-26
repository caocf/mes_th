/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.qm.mes.ra.dao;

import java.text.SimpleDateFormat;

import com.qm.mes.ra.bean.Instruction;

/**
 *
 * @author YuanPeng
 */
public class DAO_Instruction_cacheForOracle implements DAO_Instruction_cache{
	 /**
	    * ����ָ���sql���
	    *
	    * @param instruction
	    * @return
	    */
    public String saveInstructionCache(Instruction instruction) {
        String sql = null;

        String sql1 = "insert into t_ra_Instruction_cache(int_id,Int_produnitid," +
                "Dat_produceDate,Str_versionCode,Int_instructOrder,Dat_planDate" +
                ",Int_planOrder,Str_produceType,Str_produceName,Str_produceMarker," +
                "Str_workOrder,Str_workTeam,Tim_planBegin,Tim_planFinish,Int_count," +
                "Int_instructStateID,Int_issuance,Int_StaError) "
				+ "values(seq_RA_INSTRUCTION_CACHE.nextval,"
				+ instruction.getProdunitid() + ","
				+ ("to_date('"+(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(instruction.getProduceDate())+
                "','yyyy-mm-dd hh24:mi:ss')") + "," +
				(instruction.getVersionCode()==null||instruction.getVersionCode().equals("null")?"''":("'"+instruction.getVersionCode().toString()) + "'") + ","
				+ instruction.getInstructionOrder() + ","
				+(instruction.getPlanDate()==null||instruction.getPlanDate().toString().equals("null")
                ?"''":"to_date('"+(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(instruction.getPlanDate())+
                "','yyyy-mm-dd hh24:mi:ss')") + ","
				+ instruction.getPlanOrder() + ","
				+ (instruction.getProduceType()==null||instruction.getProduceType().equals("null")?"":("'"+instruction.getProduceType()))+"'" + ",";
       String sql3 =
				(instruction.getProduceName()==null||instruction.getProduceName().equals("null")?"":("'"+instruction.getProduceName()+"'") )+ ","
				+ (instruction.getProduceMarker()==null||instruction.getProduceMarker().equals("null")?"''":("'"+instruction.getProduceMarker()+"'")) + ","
				+ (instruction.getWorkOrder()==null||instruction.getWorkOrder().equals("null")?"":("'"+instruction.getWorkOrder()+"'") )+ ","
				+ (instruction.getWorkTeam()==null||instruction.getWorkTeam().equals("null")?"":("'"+instruction.getWorkTeam()+"'") )+ ","
				+ (instruction.getPlanBegin()==null||instruction.getPlanBegin().toString().equals("null")
                ?null:("to_date('"+(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(instruction.getPlanBegin())+
                "','yyyy-mm-dd hh24:mi:ss')")) + ",";
        String sql4 =
				(instruction.getPlanFinish()==null||instruction.getPlanFinish().toString().equals("null")
                ?"''":("to_date('"+(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(instruction.getPlanFinish())+
                "','yyyy-mm-dd hh24:mi:ss')")) + ","
				+ instruction.getCount() + ","
				+ instruction.getInstructStateID() + ","
                + instruction.getIssuance() + ","
                + instruction.getStaError()
				+ ")";
        sql = sql1 + sql3 + sql4;
        return sql;
    }
    /**
     * ͨ����Ų��ָ���sql���
     *
     * @param id
     * @return T_RA_INSTRUCTION�и�ID�������ֶ�
     */
    public String getInstructionCacheById(int id) {
        String sql = "select * from t_ra_Instruction_cache where int_id = " + id;
        return sql;
    }
    /**
     * ͨ�����ɾ��ָ���sql���
     *
     *@param Int_produnitid
     *@param str_date
     *@param workOrder
     *@param order
     *@return ɾ��T_RA_INSTRUCTION�и�order�ļ�¼
     */
    public String delInstructionCacheByProduceUnitDateWorkorderOrder(int Int_produnitid, String str_date,String workOrder,int order) {
       String sql = "delete from t_ra_Instruction_cache where Int_produnitid="+Int_produnitid+" and to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date+"' and str_workorder='"+workOrder+"' and Int_instructOrder = " + order;
        return sql;
    }
    /**
     * ��ָ����и��²�����sql���
     *
     * @param instruction
     * @return ����T_RA_INSTRUCTION�и�ID�ļ�¼
     */
    public String updateInstructionCache(Instruction instruction) {
        String a = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(instruction.getProduceDate());
        String sql = "update t_ra_Instruction_cache set Int_produnitid = " + instruction.getProdunitid()
				+ " , Dat_produceDate = " +"to_date('"+a+"','yyyy-mm-dd hh24:mi:ss')"
				+ " , Str_versionCode = '" + instruction.getVersionCode()
				+ "' , Int_instructOrder = " + instruction.getInstructionOrder()
                + " , Dat_planDate = " +(instruction.getPlanDate()==null||instruction.getPlanDate().toString().equals("null")?"''":("to_date('"+(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(instruction.getPlanDate())+"','yyyy-mm-dd hh24:mi:ss')"))
				+ " , Int_planOrder = " + instruction.getPlanOrder()
				+ " , Str_produceType = '" + instruction.getProduceType()
                + "' , Str_produceName = '" + instruction.getProduceName()
				+ "' , Str_produceMarker = '" + instruction.getProduceMarker()
				+ "' , Str_workOrder = '" + instruction.getWorkOrder()
                + "' , Str_workTeam = '" + instruction.getWorkTeam()
				+ "' , Tim_planBegin = " + (instruction.getPlanBegin()==null||instruction.getPlanBegin().toString().equals("null")?"''":("to_date('"+(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(instruction.getPlanBegin())+"','yyyy-mm-dd hh24:mi:ss')"))
				+ " , Tim_planFinish = " + (instruction.getPlanFinish()==null||instruction.getPlanFinish().toString().equals("null")?"''":("to_date('"+(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(instruction.getPlanFinish())+"','yyyy-mm-dd hh24:mi:ss')"))
                + " , Int_count = " + instruction.getCount()
				+ " , Int_instructStateID = " + instruction.getInstructStateID()
                + " , Int_issuance = " + instruction.getIssuance()
                + " , Int_StaError = " + instruction.getStaError()
				+ " where int_id = " + instruction.getId();
        return sql;
    }
    /**
     * ��ѯT_RA_INSTRUCTION�м�¼����
     * @return T_RA_INSTRUCTION�м�¼����
     */
    public String getInstructionCacheCount() {
        String sql = "select count(*) from t_ra_Instruction_cache";
        return sql;
    }
    /**
     * ��ѯT_RA_INSTRUCTION�����м�¼
     *
     * @return ����T_RA_INSTRUCTION�����м�¼
     */
    public String getAllInstructionCache() {
        String sql = "select * from t_ra_Instruction_cache order by int_id";
        return sql;
    }
    /**
     * ���ҵ�ǰ���ں�������Ԫ�ıȸ�˳���С��˳���ָ��
     * 
     * @param Int_produnitid
     * 			������ԪID
     * @param str_date
     * 			��������
     * @param workOrder
     * 			���
     * @param Int_instructOrder
     * 			��ҵָ��˳���
     * @return ��ҵָ�����
     */
    public String getByOrderMinus(int Int_produnitid, String str_date,String workOrder,int Int_instructOrder) {
        String sql = "select * from t_ra_Instruction_cache where Int_produnitid="+Int_produnitid+" and to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date+"' and str_workorder='"+workOrder+"' and int_instructorder <" + Int_instructOrder + " order by int_instructorder desc";
        return sql;
    }
    /**
     * ���ҵ�ǰ���ں�������Ԫ�ıȸ�˳��Ŵ��˳���ָ��
     * 
     * @param Int_produnitid
     * 			������ԪID
     * @param str_date
     * 			��������
     * @param workOrder
     * 			���
     * @param Int_instructOrder
     * 			��ҵָ��˳���
     * @return ��ҵָ�����
     */
    public String getByOrderPlus(int Int_produnitid, String str_date,String workOrder,int Int_instructOrder){
        String sql = "select * from t_ra_Instruction_cache where Int_produnitid="+Int_produnitid+" and to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date+"' and str_workOrder='"+workOrder+"' and int_instructorder >" + Int_instructOrder + " order by int_instructorder";
        return sql;
    }
    /**
     * ͨ��������Ԫ�š��������ڡ�ָ��˳��ŷ���ָ���sql���
     *
     * @param Int_produnitid
     * 			������Ԫ��
     * @param str_date
     * 		��������
     * @param workOrder
     * 			���
     * @param order
     * 		ָ��˳���
     * @return ����T_RA_INSTRUCTION�и�������Ԫ�š��������ڡ�ָ��˳��ŵļ�¼
     */
    public String IssuanceByProduceUnitDateWorkorderOrder(int Int_produnitid, String str_date,String workOrder,int order) {
        String sql = "update t_ra_Instruction_cache set Int_issuance = 1 where Int_produnitid="+Int_produnitid+" and to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date+"' and str_workorder='"+workOrder+"'  and Int_instructOrder = " + order;
        return sql;
    }
    /**
     * ��������ʱ���������Ԫ��β鿴��ص�ָ�
     *  @param  Str_produceUnit,String str_date,workOrder
     * @return  �������Ӧ��ָ��
     */
    public String getInstructionCacheByProduceUnitProduceDateOrder(int Int_produnitid, String str_date,String workOrder) {
        String sql="select * from t_ra_Instruction_cache where Int_produnitid="+Int_produnitid+" and to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date+"' and str_workorder='"+workOrder+"' order by int_instructorder";
	  return sql;
    }
    /**
     * ��ѯT_RA_INSTRUCTION�����м�¼��˳������
     *
     * @return ����T_RA_INSTRUCTION�����м�¼��˳������
     */
    public String getAllInstructionCacheByOrder() {
        String sql = "select * from t_ra_Instruction_cache order by Int_instructOrder";
        return sql;
    }
    /**
     * ɾ��ָ����ʱ������������
     *
     * @return
     */
    public String DeleteInstructionCache(){
        String sql = "delete from T_RA_INSTRUCTION_CACHE";
        return sql;
    }

    /**
     * ͨ��������Ԫ�š��������ڲ�ѯ��¼����
     * 
     * @param ProduceUnitID
     * 			������Ԫ��
     * @param str_date
     * 			��������
     * @param workOrder
     * 			���
     * @return ����ͨ��������Ԫ�š��������ڲ�ѯ��¼������
     */
    public String getCountByProUnitDateOrder(int ProduceUnitID, String str_date,String workOrder){
    	String sql = "select count(*) from t_ra_instruction_cache where Int_produnitid=" + ProduceUnitID +" and str_workorder='" + workOrder +"'and to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date+"'";
    	return sql;
    }
    
    /**
     * ɾ����������Ԫ������ʱָ��
     * 
     * @param ProduceUnitID
     * 			������Ԫ��
     * @return 
     */
    public String DeleteInstructionCacheByProdUnitIdproducedateWorkorder(int ProduceUnitID,String str_date,String workOrder ){
        String sql = "delete from T_RA_INSTRUCTION_CACHE where Int_produnitid=" + ProduceUnitID+ " and to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date+"' and str_workorder='"+workOrder+"'";
        return sql;
    }
    
    /**
     * ͨ��������Ԫ�š��������ڡ�ָ��˳��Ų�ѯָ��
     * 
     * @param ProduceUnitID
     * 			������Ԫ��
     * @param str_date
     * 			��������
     * @param workOrder
     * 			���
     * @param Int_instructOrder
     * 				ָ��˳���
     * @return ��ҵָ�����
     */
    public String getInstructionByProdUnitDateWorkorderOrder(int ProduceUnitID,String str_date,String workOrder,int Int_instructOrder){
    	String sql = "select * from t_ra_Instruction_cache where Int_produnitid="+ProduceUnitID+" and to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date+"' and str_workorder='"+workOrder+"' and Int_instructOrder = " + Int_instructOrder;
    	return sql;
    }
    
    /**
     * ���������ڡ�������Ԫ�����ڸ�ָ��˳��ŵ�ָ��˳��ż�1
     * 
     *  @param  ProduceUnitID
     *  		������Ԫ��
     *  @param  str_date
     *  		�������ں�
     *  @param  workOrder
     *  		���
     *  @param  order
     *  		ָ��˳���
     * @return  ����sql
     */
     public  String MinusInstructionOrder(int ProduceUnitID,String str_date,String workOrder,int order) {
     String sql="update t_ra_instruction_cache set int_instructorder=int_instructorder-1 where int_instructorder>"+order+" and to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date+"'"+"  and str_workorder='"+workOrder+"' and int_produnitid="+ProduceUnitID ;
     return sql;
 }
     
     /**
      * ������������Ԫ���������ڵ�����ָ��
      * 
      * @param Int_produnitid
      * @param str_date
      * @param workOrder
      * @return
      */
     public String IssuanceAllByProduceUnitDateWorkorder(int Int_produnitid, String str_date,String workOrder) {
         String sql = "update t_ra_Instruction_cache set Int_issuance = 1 where Int_produnitid="+Int_produnitid+" and to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date+"' and str_workorder='"+workOrder+"'";
         return sql;
     }
     
     /**
      * ��ѯ��ʱ�����˳���
      * 
      * @param int_produnitid
      * @param str_date
      * @param workOrder
      * @return
      */
     public String getInstructionMaxOrder(int int_produnitid,String str_date,String workOrder){
    	 String sql = "select max(int_instructorder) from t_ra_Instruction_cache where int_produnitid="+int_produnitid+"  and str_workOrder='"+workOrder+"' and to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date+"'  ";
    	 return sql;
     }
     
     /**
 	 * �жϸ�������Ԫ���������ڡ���Ʒ��ʶ��ָ������
 	 * 
 	 * @param produnitid
 	 * @param str_date
 	 * @param workOrder
 	 * @param marker
 	 * @return
 	 */
 	public String getCountByProUnitDateWorkorderMarker(int  produnitid,String str_date,String workOrder,String marker){
 		String sql = "select count(*) from t_ra_instruction_cache where to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date+"' and int_produnitid="+produnitid +" and str_workOrder='"+workOrder +"' and Str_produceMarker='"+marker+"'";
 		return sql;
 	}
     
 	/**
 	 * ��ѯ��������Ԫ���������ڡ���Ʒ��ʶ��ָ��
 	 * 
 	 * @param produnitid
 	 * @param str_date
 	 * @param workOrder
 	 * @param marker
 	 * @return
 	 */
 	public String getInstructionByProUnitDateWorkorderMarker(int  produnitid,String str_date,String workOrder,String marker){
 		String sql = "select * from t_ra_instruction_cache where to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date+"' and int_produnitid="+produnitid +" and str_workorder='"+workOrder +"' and Str_produceMarker='"+marker+"'";
 		return sql;
 	}
     
 	/**
     * ���ָ��ָ��Ŷ�Ӧ��id
     * ���
     *  @param  int_order,str_date,ProduceUnitID,workOrder
     * @return  ����sql
     */
      public  String selectInstructionCacheid(int int_order,String str_date,int ProduceUnitID,String workOrder){
    	  String sql="select int_id from t_ra_instruction_cache where int_instructorder="+int_order+" and str_workorder='"+workOrder+"' and to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date+"'"+"and int_produnitid="+ProduceUnitID;
       return sql;
      }

     /**
      * ���ָ��ָ��ŵ�����
      * ���
      *
      *  @param  int_order,str_date,ProduceUnitID,workOrder
      * @return  ����sql
      */
      public  String selectInstructionCacheNum(int int_order,String str_date,int ProduceUnitID,String workOrder){
    	  String sql="select int_count from t_ra_instruction_cache where int_instructorder="+int_order +"  and str_workorder='"+workOrder +"' and to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date+"'"+"and int_produnitid="+ProduceUnitID;
    	  return sql;
      }
     /**
       * ����ָ��ָ��ŵ�����
       * ���
       *
       *  @param  int_count,int_order
       *   @return  ����sql
       */
       public  String updateInstructionCacheNum(int int_count,int int_order,String str_date,int ProduceUnitID,String workOrder){
    	   String sql="update t_ra_instruction_cache set int_count="+int_count+" where Int_instructOrder ="+int_order +" and to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date+"'"+" and str_workorder ='"+workOrder +"' and int_produnitid="+ProduceUnitID ;
        return sql;
       }
       /**
        * ͨ�����ɾ��ָ���sql���
        * ���
        * @param order
        * @return ɾ��T_RA_INSTRUCTION�и�order�ļ�¼������ɾ����
        */
       public String deleteInstructionCacheByOrder(int order,String str_date,int ProduceUnitID,String workOrder){
    	   String sql= "delete from t_ra_instruction_cache where int_instructOrder = "+ order+" and to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date+"'"+"  and str_workorder = '"+ workOrder+"'  and int_produnitid="+ProduceUnitID ;
        return sql;
    	     }
       /**
        * ����ָ��ָ���
        * ���
         *  @param  order
        * @return  ����sql
        */
        public  String updateInstructionCacheOrder(int order,String str_date,int ProduceUnitID,String workOrder) {
        String sql="update t_ra_instruction_cache set int_instructorder=int_instructorder+1 where int_instructorder="+order+"  and str_workOrder='"+workOrder+"' and to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date+"'"+"and int_produnitid="+ProduceUnitID ;
        return sql;
    }
        /**
         * �������Ҫ���ָ��
         * ���
         *  @param  order,order1,count,str
         * @return  ����sql
         */
         public  String insertInstructionCache(int order1,String str,int count,int order) {
        	 String sql="insert into t_ra_instruction_cache(int_id,int_produnitid,dat_producedate,str_versioncode,int_instructorder,dat_plandate,int_planorder,str_producetype,str_producename,str_producemarker,str_workorder,str_workteam ,tim_planbegin,tim_planfinish,int_count,int_instructstateid,int_issuance,int_staerror) select seq_RA_INSTRUCTION.nextval,int_produnitid,dat_producedate,str_versioncode,"+order1+",dat_plandate,int_planorder,str_producetype,str_producename,'"+str+"',str_workorder,str_workteam ,tim_planbegin,tim_planfinish,"+count+",int_instructstateid,int_issuance,int_staerror from t_ra_instruction_cache where Int_instructOrder ="+order;
    		return sql;
    	}
         
         /**
          * ͨ��������Ԫ��ѯ��Ʒ��ʶ
          * 
          * @param ProduceUnitID
          * @return
          */
         public String selectproducemarker(int ProduceUnitID){
         	String sql = "select str_producemarker from t_ra_instruction_cache where int_produnitid = "+ProduceUnitID;
         	return sql;
         	
         }
         /***
          *  л�����ѯ�༭�ռ����������ϵ�ֵ
          */
         public String getInstructionbymateriel(String materiel,int int_produnitid){
        	 String sql = "select * from t_ra_instruction_cache where str_producemarker ='"+materiel+"'and int_produnitid="+int_produnitid+" ";
          	return sql; 
        	 
         }
         


}
