/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mes.ra.dao;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import mes.ra.bean.Instruction;

/**
 *
 * @author YuanPeng
 */
public class DAO_InstructionForOracle implements DAO_Instruction{

	 /**
     * ͨ����Ų��ָ���sql���
     *Ԭ��
     * @param id
     * @return T_RA_INSTRUCTION�и�ID�������ֶ�
     */
    public String getInstructionById(int id) {
        String sql = "select * from T_RA_INSTRUCTION where int_id = " + id;
        return sql;
    }

    /**Ԭ��
     * ��ָ����и��²�����sql���
     *
     * @param instruction
     * @return ����T_RA_INSTRUCTION�и�ID�ļ�¼
     */
    public String updateInstruction(Instruction instruction) {
        String a = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(instruction.getProduceDate());
        String sql = "update T_RA_INSTRUCTION set Int_produnitid = " + instruction.getProdunitid()
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
                + " , Int_delete = " + instruction.getDelete()
				+ " where int_id = " + instruction.getId();
        return sql;

    }

    /**Ԭ��
     * ���ұȸ�˳���С��˳���ָ��
     * 
     *@param ProduceUnitID
     *		������Ԫ
     *@param str_date
     *		��������
     * @param Int_instructOrder
     *                      ָ��˳��
     * @return �ȸ�˳���С��˳���ָ��
     */
    public String getByOrderMinus(int ProduceUnitID, String str_date,String workOrder,int Int_instructOrder){
        String sql = "select * from T_RA_INSTRUCTION where int_instructorder <=" + Int_instructOrder + " and str_workOrder='"+workOrder+"' Int_produnitid = " + ProduceUnitID + 
	" and to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date + "' and int_delete<>1 order by int_instructorder desc";
        return sql;
    }

    /**Ԭ��
     * ���ұȸ�˳��Ŵ��˳���ָ��
     *
     *@param ProduceUnitID
     *		������Ԫ
     *@param str_date
     *		��������
     * @param Int_instructOrder
     *                      ָ��˳��
     * @return �ȸ�˳���С��˳���ָ��
     */
    public String getByOrderPlus(int ProduceUnitID, String str_date,String workOrder,int Int_instructOrder){
        String sql = "select * from T_RA_INSTRUCTION where int_instructorder >=" + Int_instructOrder + " and Int_produnitid = " + ProduceUnitID + 
	" and str_workorder = '" + workOrder + 
	"'  and to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date + "' and int_delete<>1 order by int_instructorder";
        return sql;
    }

    /**
	    * ͨ��������Ԫ�š��������ڡ�״̬�Ų�ѯ��ҵָ��
	    *Ԭ��
	    * @param Int_produnitid
	    *            ������Ԫ��
	    * @param str_date
	    *            ��������
	    * @param stateid
	    *            ״̬��
	    * @return ͨ��������Ԫ�š��������ڡ�״̬�Ų�ѯ����ҵָ��
	    */
	   public  String getInstructionByProUnitProDateStateOrder(int Int_produnitid,String str_date,String workOrder,int stateid){
	 	  String sql="select * from t_ra_instruction where Int_produnitid = "+Int_produnitid + 
	 	  " and to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date+"' and int_instructstateid = " +
	 	  stateid + " and str_workorder = '" +
	 	  workOrder + "' and int_delete<>1 order by int_instructorder";
	 	  return sql;
	   }

    /**Ԭ��
     * ͨ����ҵָ����Ÿ���Int_editΪ1
     *
     * @param id
     *      ��ҵָ�����
     * @return ͨ����ҵָ����Ÿ���Int_editΪ1
     */
    public String editInstructionById(int id) {
        String sql = "update t_ra_Instruction set Int_edit = 1 where Int_Id = " + id;
    return sql;
    }

    /**Ԭ��
     * ͨ����ҵָ����Ÿ���Int_editΪ0
     *
     * @param id
     *      ��ҵָ�����
     * @return ͨ����ҵָ����Ÿ���Int_editΪ0
     */
    public String uneditInstructionById(int id) {
        String sql = "update t_ra_Instruction set Int_edit = 0 where Int_Id = " + id;
    return sql;
    }

/**
* ����ָ��
* Ԭ��
*
* @param instruction
* @return
*/
public String saveInstruction(Instruction instruction) {
   String sql = null;
   String sql1 = "insert into T_RA_INSTRUCTION(int_id,Int_produnitid," +
           "Dat_produceDate,Str_versionCode,Int_instructOrder,Dat_planDate" +
           ",Int_planOrder,Str_produceType,Str_produceName,Str_produceMarker," +
           "Str_workOrder,Str_workTeam,Tim_planBegin,Tim_planFinish,Int_count," +
           "Int_instructStateID,Int_issuance,Int_StaError,Int_delete,Int_Edit) "
			+ "values(seq_RA_INSTRUCTION.nextval,"
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
           ?"''":("to_date('"+(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(instruction.getPlanBegin())+
           "','yyyy-mm-dd hh24:mi:ss')")) + ",";
   String sql4 =
			(instruction.getPlanFinish()==null||instruction.getPlanFinish().toString().equals("null")
           ?"''":("to_date('"+(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(instruction.getPlanFinish())+
           "','yyyy-mm-dd hh24:mi:ss')")) + ","
			+ instruction.getCount() + ","
			+ instruction.getInstructStateID() + ","
           + instruction.getIssuance() + ","
           + instruction.getStaError() + ","
           + instruction.getDelete() + ","
           + instruction.getEdit()
			+ ")";
   sql = sql1 + sql3 + sql4;
   return sql;
}
/**
* ͨ��������Ԫ�š��������ڡ�˳��Ŵ����׸��Ǳ༭��������int_delete�ֶ�ֵΪ1
* Ԭ��
* @param ProduceUnitID
* @param str_date
* @param UnlockStartOrder
* @return
*/
public String DeleteInstructionByProUnitDateworkOrderUnlockOrder(int ProduceUnitID, String str_date,String workOrder, int UnlockStartOrder){
	String sql = "update t_ra_Instruction set Int_delete = 1 where Int_produnitid = " + ProduceUnitID + 
	" and str_workOrder = '" + workOrder + 
	"' and to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date + "' and Int_instructOrder >= " + UnlockStartOrder;
	return sql;
}

/**
* ͨ��������Ԫ�š��������ڲ�ѯ��¼����
* Ԭ��
* @param ProduceUnitID
* 			������Ԫ��
* @param str_date
* 			��������
* @return ����ͨ��������Ԫ�š��������ڲ�ѯ��¼������
*/
public String getCountByProUnitDateOrder(int ProduceUnitID, String str_date,String str_workOrder){
	String sql = "select count(*) from t_ra_instruction where Int_produnitid=" + ProduceUnitID +" and to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date+"' and str_workOrder='" + str_workOrder +"'and int_delete <> 1";
	return sql;	  
}

/**
* ��ָ��ID��ָ�����Ϊ�ѷ���״̬
* 
* @param id
* 		��ҵָ�����
* @return
*/
public String issuanceInstruction(int id ){
	String sql = "update t_ra_instruction set INT_ISSUANCE = 1 where int_id = " + id;
	return sql;
}
	
/**
 * ͨ��������Ԫ�ź��������ڲ�ѯδ��ɾ����ָ��
 * 
 * @param produnitid
 * 			������Ԫ��
 * @param str_date
 * 			��������
 * @return ָ���
 */
	public String getInstructionsByProdunitDateOrder(int  produnitid,String str_date,String str_workorder){
		String sql = "select * from t_ra_instruction where to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date+"' and int_produnitid="+produnitid +" and str_workorder='"+str_workorder+"' and int_delete<>1 order by Int_instructOrder";
		return sql;
	}
	
	/**
	 * �жϸ�������Ԫ���������ڡ���Ʒ��ʶ��ָ������
	 * 
	 * @param produnitid
	 * @param str_date
	 * @param marker
	 * @return
	 */
	public String getCountByProUnitDateWorkorderMarker(int  produnitid,String str_date,String workOrder,String marker){
		String sql = "select count(*) from t_ra_instruction where to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date+"' and int_produnitid="+produnitid +" and str_workorder='"+workOrder +"' and Str_produceMarker='"+marker+"' and int_delete<>1";
		return sql;
	}
	
	/**
	* ͨ��������Ԫ�š��������ڲ�ѯ��¼
	* Ԭ��
	* @param ProduceUnitID
	* 			������Ԫ��
	* @param str_date
	* 			����
	* @param List<Integer> list_state ״̬����
	* @return ����ͨ��������Ԫ�š��������ڲ�ѯ��¼
	*/
	public String getCountByProUnitDateState(int ProduceUnitID,List<Integer> list_state){
		String str = "";
		Iterator<Integer> iter=list_state.iterator();
		if(list_state.size()>0){
			str+=list_state.get(0);
		}
		else{str+="-1";}
		while(iter.hasNext()){
			int n = Integer.parseInt(iter.next().toString());
			str+=',';
			str+=n;
		}
		
		String sql = "select * from t_ra_instruction  where Int_produnitid=" + ProduceUnitID +
          
           " and Int_instructStateID in("+ str +") and int_delete <> 1 and INT_ISSUANCE = 1";
		
		return sql;	  
	}
    
    
    
    
    
    
    /**
     * �޸�ָ��״̬
     * л����
     * @param structStateID ״̬id
     *  @param staError ״̬��ת���쳣��ʶ
     *  @param producemarker ��Ʒ��ʶ
     *  int_produnitid ������Ԫ��id
     *  
     * @return  �����ڸ�������Ԫ���Ӧ��ָ��
     */
    public String updateInstructState(int structStateID,int staError,String producemarker,int produnitid){
    	String sql="update t_ra_Instruction set INT_INSTRUCTSTATEID="+structStateID
    	+", INT_STAERROR="+staError
    	+" where   STR_PRODUCEMARKER='"+producemarker+"' and int_produnitid="+produnitid;
    	
    	return sql;
    }
    /**
     * �鿴����ָ��״̬
     * л����
     * 
     *  @param producemarker ��Ʒ��ʶ
     * @return  �������Ӧ��ָ��
     */
   public String  checkstr_produceMarker(String str_produceMarker,int  produnitid){
	   String sql="select ra.Int_instructstateid from t_ra_instruction ra where str_produceMarker='"+str_produceMarker+"'  and ra.int_delete=0 and int_Edit<>1 and ra.int_issuance=1 and  ra.int_produnitid="+produnitid;
	  
	  
	   return sql;
   }
   /**
    * ָ��״̬�����쳣ʱҪ������ֵ�����
    * л����
    * 
    *  @param producemarker ��Ʒ��ʶ
    *   @param userId�û�id
    *    @param Int_instructionStateID״̬id
    *     @param produceUnit������Ԫ
    * @return  �������Ӧ��ָ��
    */
   public String savet_ra_Instructionexception(int Int_instructionStateID,int userId,String produceMarker,int INT_GATHERID){
	   String sql="insert into t_ra_Instructionexception values(seq_RA_INSTRUCTIONEXCEPTION.nextval,"+INT_GATHERID+",sysdate,"+Int_instructionStateID+""
	       +",'"+produceMarker+"',"+userId+")";
	   
	   
	   return sql;
   }
   /**
    * �鿴��Ʒ��ʶ���ҳ�������������ֵ��ָ����п��Ƿ���ڡ�
    * л����
    * 
    *  @param producemarker ��Ʒ��ʶ
    *  @param produnitid ������Ԫ��
    *  @param str_date ��������
    * @return  �������Ӧ��ָ��
    */
   
   public  String checkmaterielValue(String producemarker){
	   String sql="select Str_producemarker  from  t_ra_instruction ra  where   ra.int_issuance=1 "
	   +"  and ra.int_delete=0 and Str_producemarker='"+producemarker+"' order by int_instructorder  ";
	
	   return sql;
   }
   /**
    * ��������ʱ���������Ԫ�Ͱ�β鿴��ص�ָ�
    * л����
    * 
    *  @param  int_produnitid,String str_date
    * @return  �������Ӧ��ָ��
    */
   
   
  public  String getInstructionByProduceUnitProduceDateWorkorder(int int_produnitid,String str_date,String workOrder){
		
	  String sql="select * from t_ra_instruction where int_produnitid="+int_produnitid+" and to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date+"' and str_workorder='"+workOrder+"' and int_delete=0 order by int_instructorder asc";
	 
	  return sql;
  }
  /**
   * ��������ʱ���������Ԫ�鿴��ص�ָ�
   * л����
   * 
   *  @param  int_produnitid,String str_date
   * @return  �������Ӧ��ָ��
   */
  public  String getInstructionByProduceUnitProduceDate(int int_produnitid,String str_date){
		
	  String sql="select * from t_ra_instruction where int_produnitid="+int_produnitid+" and to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date+"' and int_delete=0 and int_Edit<>1  order by int_instructorder asc";
	 
	  return sql;
  }
  /**
   * ����ָ��汾
   * л����
   * 
   *  @param  instuction	ָ�����
   * @param str_versioncode �汾��
   */
  
 public String updateInstructionVersioncode(Instruction instruction,String str_versioncode){
	 String sql="update t_ra_instruction set str_versioncode='"+str_versioncode+"' where int_id="+instruction.getId();
	 return sql;
 }
 /**
  * �˶��Ƿ���Խ��а汾����
  * л����
  * 
  *  @param  int_produnitid ������Ԫ��
  *  @param str_date ��������
  *
  */
  public  String checksaveVersion(int int_produnitid,String str_date,String workOrder){
	
	  String sql="select * from t_ra_instruction where  int_delete=0 and str_workOrder='"+workOrder+"'  and int_produnitid="+int_produnitid+" and to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date+"' and (int_instructstateid<>(select int_instructStateid from t_ra_produceunit where int_id="+int_produnitid+" and int_delete=0)or int_Edit=1)  ";
	 
	  return sql;
  }
  /**
   * ��������ʱ���������Ԫ�ͻָ�״̬�鿴��ص�ָ����а汾�ָ�
   * л����
   * 
   *  @param  int_produnitid ������Ԫ��
  *  @param str_date ��������
  *  @param int_stateid ״̬��
   *
   */
 public  String comebackVersion(int  int_produnitid,String str_date,int int_stateid){
	 String sql="select * from t_ra_instruction where int_produnitid="+int_produnitid+" and to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date+"' and int_instructstateid<>"+int_stateid+" and int_delete=0 and int_Edit<>1 ";
	
	 return sql;
 }
 /**
  * ��������ʱ���������Ԫɾ��ָ��
  * л����
  * 
  *  @param  int_produnitid ������Ԫ��
  *  @param str_date ��������
  *
  */
 public String deleteInstructionByProduceUnitProduceDateWorkorder(int int_produnitid,String str_date,String workOrder){
	 String sql="update t_ra_instruction set int_delete=1 where int_delete=0 and int_edit<>1 and int_produnitid="+int_produnitid+" and str_workorder='"+workOrder+"' and to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date+"'";
	
	 return sql;
 }


 /**
  * ͨ���ɼ���id��ϵͳʱ���ѯ��ص�ָ�
  * л����
  */
 public String getGatherwork(int gatherid){
	 String sql="select ra.*  from t_ra_instruction ra  , t_tg_gather g ,t_os_workSchedle ws where  ws.int_produnitid=g.int_produnitid and g.int_produnitid=ra.int_produnitid and ws.str_workorder=ra.str_workOrder and ra.int_issuance=1 and g.int_id="+gatherid+""
	 +"  and ra.int_delete=0 and int_Edit<>1 and ra.Int_instructStateID in(select int_fromstate from t_ra_gatherstaterule gsr,t_ra_stateconversion sv where  gsr.int_gatherid="+gatherid+"" +
	 " and gsr.int_stateconversionid=sv.int_id)  order by ra.Dat_produceDate, ws.str_workschedle,   ra.int_instructorder    "
	 
	 ;
	 System.out.println(sql);
	 return sql;
 }
 /**
  * ����ָ������˳���
  *  ����ָ��׷��
  * л����
  */
 public String getInstructionMaxOrder(int int_produnitid,String str_date,String workOrder){
	  String sql="select max(int_instructorder) from t_ra_instruction where  int_delete=0  and int_produnitid="+int_produnitid+" and str_workorder='"+workOrder+"' and to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date+"'  ";
	
	  return sql;
 }
 /**
  * ͨ���ӿ�ʼʱ�䵽����ʱ�估������Ԫ��ѯ��ص�ָ��
  * л����
  */
public String getInstructionByStartAndEndProduceunitOrder(int produceid,String starttime,String endtime,String workorder){
	String sql="select * from t_ra_instruction where int_delete=0 and int_produnitid="+produceid+" and str_workorder='"+workorder+"' and "
	+"to_date(Dat_produceDate)>=to_date('"
				+ starttime
				+ "','yyyy-MM-DD') and to_date(Dat_produceDate)<=to_date('"
				+ endtime + "','yyyy-MM-DD') order by Dat_produceDate desc,Int_instructOrder asc";

	return sql;
   }
/**
 * �鿴������Ԫ���������ڵ�������ֵ
 * ����ָ���ʱ�ж�
 * л����
 */
public  String getStr_produceMarkerbyproduceUnitandproduceDate(int produceid,String str_date){
	  String sql="select * from t_ra_instruction where  int_delete=0  and int_produnitid="+produceid+" and to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date+"'  ";
	return sql;
  }
/**
 *  л����
 *  �˶������ϱ�ʶ����
 *   ָ���ʱ��
 */
 public String checkinstructionmateriel(int produnitid){
   String sql="select m.str_validateclass  from t_tg_gather g,t_tg_materielrule m where m.int_id=g.int_materielruleid and g.int_produnitid="+produnitid;
   return sql;
 }
 /**
  * л����
  * ������Ԫ��ʱ��
  * ͨ�������ϵ�ֵ�鿴ָ���Ƿ��ڱ༭״̬��
  */
 public String checkinstructionedit(String producemarker,int produnitid,String str_date){
	 String sql="select * from t_ra_instruction ra where str_produceMarker='"+producemarker+"'and ra.int_delete=0  and  ra.int_produnitid="+produnitid+"  and to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date+"' ";
	
	 return sql;
 }
 
 /**
  * �鿴������Ԫ���������ڵ�������ֵ
  * ����ָ���ʱ�ж�
  * 
  */
 public  String getInstructionsbyproduceUnit(int produceid){
 	  String sql="select * from t_ra_instruction where  int_delete=0  and int_produnitid="+produceid;
 	return sql;
   }
 
 /**
  * ��ѯ��ǰ������Ԫ���ǵ�ǰ�������ڵ�ָ�����
  * 
  * @param produnitid
  * @param str_date
  * @return
  */
 public String getInstructionsByProdunitOtherProdDate(int produnitid,String str_date){
	 String sql="select * from t_ra_instruction ra where ra.int_delete=0  and  ra.int_produnitid="+produnitid+"  and to_char(Dat_produceDate,'yyyy-MM-dd')<>'"+str_date+"' ";
	 return sql;
 }
 /**
  * л����˶��Ƿ����ɾ��������Ԫ��
  */
public String getallInstruction(){
	  String sql="select * from t_ra_instruction ra where ra.int_delete=0";
		 return sql;
}
 
/**
 * ����ָ��������Ԫ�������ϵ�ָ�� л����
 */
 public String getInstructionbymaterile(String materile,int produceid){
 
	  String sql="select * from t_ra_instruction  where str_produceMarker='"+materile+"' and int_produnitid="+produceid+"  and int_delete=0 ";
		
	  return sql;
 }
 /**
  * ���
  * �������
  * Ϊ�˼����������ݿ�
  */
 public String export(int produceid,String starttime,String endtime,String workorder){
	 //String sql="select INT_PRODUNITID,STR_WORKTEAM,STR_WORKORDER,DAT_PRODUCEDATE,INT_INSTRUCTORDER,STR_PRODUCETYPE,STR_PRODUCENAME,STR_PRODUCEMARKER,INT_COUNT,TIM_PLANBEGIN,TIM_PLANFINISH,DAT_PLANDATE,INT_PLANORDER,STR_VERSIONCODE,INT_STAERROR from t_ra_instruction where int_delete=0 and int_produnitid="+Integer.parseInt(int_id)+" and to_date(Dat_produceDate)>=to_date('"+overtime+"','yyyy-MM-DD') and to_date(Dat_produceDate)<=to_date('"+endtime+"','yyyy-MM-DD') and str_workorder='"+workOrder+"' order by Dat_produceDate,Int_instructOrder asc";
	 String sql="select INT_PRODUNITID,STR_WORKTEAM,STR_WORKORDER,DAT_PRODUCEDATE,INT_INSTRUCTORDER,STR_PRODUCETYPE,STR_PRODUCENAME,STR_PRODUCEMARKER,INT_COUNT,TIM_PLANBEGIN,TIM_PLANFINISH,DAT_PLANDATE,INT_PLANORDER,STR_VERSIONCODE,INT_STAERROR from t_ra_instruction where int_delete=0 and int_produnitid="+produceid+" and str_workorder='"+workorder+"' and "
		+"to_date(Dat_produceDate)>=to_date('"
					+ starttime
					+ "','yyyy-MM-DD') and to_date(Dat_produceDate)<=to_date('"
					+ endtime + "','yyyy-MM-DD') order by Dat_produceDate desc,Int_instructOrder asc";
	 return sql;
 }
 
 /**
  * л����˶��Ƿ����ɾ��������Ԫ��
  */
 public String checkproducedelete(int produceid,String date){
	 String sql="select * from t_ra_instruction where  int_delete=0  and  int_produnitid="+produceid+"  and to_char(Dat_produceDate,'yyyy-MM-dd')='"+date+"'";
	
	 return sql;
 }
 
 
 /**
  * �˶�ָ������Ƿ���ָ������ɾ��
  * ������
  * ����������Ԫ���������ڼ����
  *  @param  instuction
  *
  */
 public String checkAllInstructionByProdunitidDateWorkorder(int int_produnitid,String str_date,String workOrder){
	 
	 String sql="select * from t_ra_instruction where int_delete=0 and int_edit<>1 and int_produnitid="+int_produnitid+" and str_workorder='"+workOrder+"' and to_char(Dat_produceDate,'yyyy-MM-dd')='"+str_date+"'";
		
	 return sql;
	 
	 
 }
 
 
 
 }
 

