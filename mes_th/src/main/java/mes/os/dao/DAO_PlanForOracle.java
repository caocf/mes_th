package mes.os.dao;

import java.text.SimpleDateFormat;

import mes.os.bean.Plan;
/**
 * 
 * @author л���� 2009-05-15
 *
 */

public class DAO_PlanForOracle implements DAO_Plan{
/** 
* ������ҵ�ƻ�  л����
*/
	 public  String savePlan(Plan plan){
		 String plandate=plan.getPlanDate()==null||plan.getPlanDate().toString().equals("null")?"":(""+(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(plan.getPlanDate())+"");
		 String producedate=plan.getProduceDate()==null||plan.getProduceDate().toString().equals("null")?"":(""+(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(plan.getProduceDate())+"");
		// System.out.println("11111111111111111111111111111111111"); 
		 String sql=" insert into t_os_plan values(SEQ_os_plan.nextval,"
			 +"to_date('"+plandate+"','yyyy-mm-dd hh24:mi:ss')" + "," 
			 +"to_date('"+producedate+"','yyyy-mm-dd hh24:mi:ss')" + ",'" 
			 +plan.getOrderFormId()+"',"
			 +plan.getPlanGroupId()+",'"
			 +plan.getProduceType()+"','"
			 +plan.getProduceName()+"','"
			 +plan.getProduceMarker()+"',"
			 +plan.getProdunitid()+",'"
			 +plan.getWorkTeam()+"','"
			 +plan.getWorkOrder()+"',"
			 +plan.getAmount()+",'"
			 +plan.getVersioncode()+"',"
			 +plan.getUpload()+","
			 +plan.getPlanOrder()+",'" 
			 + plan.getDescription()+ "','" 
			 +   " '" +")";
			//System.out.println(sql); 
		 return sql;
	 }

	 /** 
	  * ����������Ԫ���������ڣ�����Ͱ�β�����ҵ�ƻ� ���İ汾��Ϣ л����
	  */
	public String getPlanbyProdateProidWorder(String produceDate,int produnitid,String workOrder ){
		
		String sql="select * from t_os_plan where str_versioncode=(select str_versioncode  from  t_os_plan where  int_id=(select max(int_id) from t_os_plan " 
				+"where to_char(dat_produceDate,'yyyy-MM-dd')='"+produceDate+"'"
		+" and int_produnitid="+produnitid+" and str_workOrder='"+workOrder+"'  "  +"))order by int_planOrder asc";
		
		return sql;
		
	}
	  /** 
	   * ͨ���汾�ŵõ���ҵ�ƻ�  л����
	   */
	public String getPlanbyversioncord(String versioncode){
		String sql="select * from t_os_plan where str_versionCode='"+versioncode+"' order by int_planOrder desc";
		
		return sql;
	}
	   /** 
	    * ɾ��ָ���汾�ŵ���ҵ�ƻ� л����
	    */
	public  String deletePlanbyversioncode(String versioncode){
		String sql="delete from t_os_plan where str_versionCode='"+versioncode+"' ";
		return sql;
		
	}
	    /** 
	     * ͨ���汾int_id  �õ��汾�ƻ���Ϣ ���ռƻ�˳����������� л����
	     */
	public   String getPlanbyversionid(int int_id){
		String sql="select * from t_os_plan where str_versionCode=(select str_versionCode from t_os_planversion where int_id="+int_id+") order by int_planOrder asc";
		
		return sql;
	}
	     /** 
	      * ��ѯһ��ʱ���ڵ���ҵ�ƻ�
	      */
	public  String getplanbybystarttimeandendtimeproduceunitoverendtime(int produnitid,String workOrder ,String overtime,String endtime){
		String sql="select * from t_os_plan  where str_versioncode in(select max(str_versioncode) from( "
		         + " select * from t_os_plan where str_versioncode!='temp' and int_produnitid="+produnitid+"  and str_workOrder='"+workOrder+"' and "
		         +"to_date(Dat_produceDate)>=to_date('"+overtime+"','yyyy-MM-DD') and to_date(Dat_produceDate)<=to_date('"+endtime+"','yyyy-MM-DD')"
		         +" ) group by str_workOrder ,dat_producedate ,int_produnitid "
		         +") order by dat_producedate asc";
		
		return sql;
	
	}
	      /** 
	       * �޸�ָ��idֵ�ļƻ�  л����
	       */
	public   String updatePlanbyid(Plan plan){
		 String plandate=plan.getPlanDate()==null||plan.getPlanDate().toString().equals("null")?"":(""+(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(plan.getPlanDate())+"");
		String sql="update t_os_plan set dat_planDate=to_date('"+plandate+"','yyyy-mm-dd hh24:mi:ss') ," 
		        +"int_planorder="+plan.getPlanOrder()+","
				+"str_description='"+plan.getDescription()+"',"
				+"int_planGroupId="+plan.getPlanGroupId()+","
				+"str_produceType='"+plan.getProduceType()+"',"
				+"str_produceName='"+plan.getProduceName()+"',"
				+"str_produceMarker='"+plan.getProduceMarker()+"',"
				+"str_workTeam='"+plan.getWorkTeam()+"',"
				+"int_count="+plan.getAmount()+" where int_id="+plan.getId()+"";
		return sql;
	}
	       /** 
	        * �õ�ָ��id�ļƻ�   л����
	        */
	public   String getplanbyid(int id ){
		String sql="select * from t_os_plan where int_id="+id+"";
		return sql;
	}
	        /** 
	        *ɾ��������Ԫ���������ڣ�����Ͱ��  ˳��ŵ���ҵ�ƻ�
	        */ 
	public   String deleteplanbyPlanOrder(String produceDate,int produnitid,String workOrder,int planorder){
		String sql="delete  from t_os_plan where int_planOrder="+planorder+" and str_versioncode='temp' " 
			+" and  to_char(dat_produceDate,'yyyy-MM-dd')='"+produceDate+"'"
	+" and int_produnitid="+produnitid+" and str_workOrder='"+workOrder+"' ";
	
	return sql;
	  }
	
	        /** 
	        * ɾ���ƻ�ʱ���õ���ָ��˳�򷽷�  л����
	        */ 
	 public String updatePlanOrder(String produceDate,int produnitid,String workOrder,int planorder){
			String sql="update t_os_plan  set int_planOrder=int_planOrder-1 where int_planOrder>"+planorder+" and str_versioncode='temp' " 
			+" and to_char(dat_produceDate,'yyyy-MM-dd')='"+produceDate+"'"
	+" and int_produnitid="+produnitid+"  and str_workOrder='"+workOrder+"'  ";
	
	return sql;
	 }
	 
	 
	/** (non-Javadoc)
	 * ���ƻ������� �ƻ�˳��ͨ���ƻ���id  �ͻ�����˳���л����
	 * @see mes.os.dao.DAO_Plan#down_or_upPlanOrderbyplanid(int, int)
	 */
	public   String down_or_upPlanOrderbyplanid(int id,int nextorder){
		String sql="update t_os_plan set int_planOrder="+nextorder+" where int_id="+id+"";
		return sql;
	}
	
	
	
	  
	/* (non-Javadoc)
	 * �����༭�ƻ� л����
	 * @see mes.os.dao.DAO_Plan#disfrockplan(java.lang.String, int, java.lang.String)
	 */
	public  String disfrockplan(String produceDate,int produnitid,String workOrder){
		 String sql="delete from  t_os_plan   where  str_versioncode='temp'" 
			+" and to_char(dat_produceDate,'yyyy-MM-dd')='"+produceDate+"'"
	+" and int_produnitid="+produnitid+" and str_workOrder='"+workOrder+"' ";
	
	return sql;
	}

	  
	/* (non-Javadoc)
	 * �ύ�༭�ƻ� л����
	 * @see mes.os.dao.DAO_Plan#submitplan(java.lang.String, int, java.lang.String, java.lang.String)
	 */
	public  String submitplan(String produceDate,int produnitid,String workOrder,String versioncode){
		 String sql="update   t_os_plan set str_versioncode='"+versioncode+"'  where  str_versioncode='temp' " 
				+" and to_char(dat_produceDate,'yyyy-MM-dd')='"+produceDate+"'"
		+" and int_produnitid="+produnitid+"  and str_workOrder='"+workOrder+"' ";
		
		return sql;
	}
	  
	 /* (non-Javadoc)
	 * ��ѯ�ύ�İ汾�� л����
	 * @see mes.os.dao.DAO_Plan#getversioncodewhensubmit(java.lang.String, int, java.lang.String)
	 */
	public String getversioncodewhensubmit(String produceDate,int produnitid,String workOrder){
		String sql=" select str_versioncode  from  t_os_plan where  int_id=(select max(int_id) from t_os_plan "
					+" where to_char(dat_produceDate,'yyyy-MM-dd')='"+produceDate+"' "
			+" and int_produnitid="+produnitid+"  and str_workOrder='"+workOrder+"' and str_versioncode!='temp')";
		return sql;
	 }

	 
	 /* (non-Javadoc)
	 * ������ҵʱֻ�ܿ������İ汾���Ұ汾�Ų�Ϊtemp л����
	 * @see mes.os.dao.DAO_Plan#getmaxPlanexcepttemp(java.lang.String, int, java.lang.String)
	 */
	public  String getmaxPlanexcepttemp(String produceDate,int produnitid,String workOrder){
		 String sql="select * from t_os_plan where str_versioncode=(select str_versioncode  from  t_os_plan where  int_id=(select max(int_id) from t_os_plan " 
				+"where to_char(dat_produceDate,'yyyy-MM-dd')='"+produceDate+"'"
		+" and int_produnitid="+produnitid+" and str_workOrder='"+workOrder+"' and str_versioncode!='temp' "  +"))order by int_planOrder asc";
		
		return sql;
	 }
	 
	/* (non-Javadoc)
	 * ������ҵ�ƻ� л����
	 * @see mes.os.dao.DAO_Plan#uploadplan(java.lang.String, int, java.lang.String)
	 */
	public  String uploadplan(String produceDate,int produnitid,String workOrder){
		 String sql="update t_os_plan set int_upload=1 where str_versioncode=(select str_versioncode  from  t_os_plan where  int_id=(select max(int_id) from t_os_plan " 
				+"where to_char(dat_produceDate,'yyyy-MM-dd')='"+produceDate+"'"
		+" and int_produnitid="+produnitid+"  and str_workOrder='"+workOrder+"' and str_versioncode!='temp' "  +"))";
		
		return sql;
	}
	 
   /* (non-Javadoc)
 * ȡ����ҵ�ƻ����� л����
 * @see mes.os.dao.DAO_Plan#canceluploadplan(java.lang.String, int, java.lang.String)
 */
public  String  canceluploadplan(String produceDate,int produnitid,String workOrder){
    	 String sql="update t_os_plan set int_upload=0 where str_versioncode=(select str_versioncode  from  t_os_plan where  int_id=(select max(int_id) from t_os_plan " 
				+"where to_char(dat_produceDate,'yyyy-MM-dd')='"+produceDate+"'"
		+" and int_produnitid="+produnitid+"  and str_workOrder='"+workOrder+"' and str_versioncode!='temp' "  +"))";
		
		return sql;
    }
	
 
  /* (non-Javadoc)
 * ��ѯ��ͬ��������������Ԫ��ε����汾��Ϣ л����
 * @see mes.os.dao.DAO_Plan#geteverydaymaxversioncodeplan()
 */
public  String geteverydaymaxversioncodeplan(){
	  String sql="select * from t_os_plan where str_versioncode in(select max(str_versioncode) from t_os_plan where str_versioncode!='temp' group by str_workOrder ,dat_producedate )";
	  
	  return sql;
  }
  
  /* (non-Javadoc)
 * �滻ʱ���˶Ա�������ݵ���ҵ�ƻ� 
 * @see mes.os.dao.DAO_Plan#geteverydaymaxversioncodeplanexceptnow(java.lang.String, int, java.lang.String)
 */
public String geteverydaymaxversioncodeplanexceptnow(String produceDate,int produnitid,String workOrder){
	  String sql="select * from t_os_plan where str_versioncode in(select max(str_versioncode) from t_os_plan  where str_versioncode!='temp'  and  str_versioncode!=("
		  +"select max(str_versioncode) from t_os_plan where str_versioncode=(select str_versioncode  from  t_os_plan where  int_id=(select max(int_id) from t_os_plan where to_char(dat_produceDate,'yyyy-MM-dd')='"+produceDate+"' and int_produnitid="+produnitid+"  and str_workOrder='"+workOrder+"' and str_versioncode!='temp' ))"
		  +") group by str_workOrder ,dat_producedate )";
	 
	  return sql;
	  
	  
  }
  
  /* (non-Javadoc)
 * ��ѯ��ͬ��������������Ԫ�����ε����汾��Ϣ ���Ƿ���Ҫ���ҵ�������ֵ л����
 * @see mes.os.dao.DAO_Plan#getmaterielfromplan(java.lang.String)
 */
public  String getmaterielfromeditplan(String produceDate,int produnitid,String workOrder,String materiel){
	  String sql="select * from t_os_plan where str_versioncode='temp'" 
			+" and to_char(dat_produceDate,'yyyy-MM-dd')='"+produceDate+"'"
			+" and int_produnitid="+produnitid+"  and str_workOrder='"+workOrder+"' and str_producemarker='"+materiel+"' order by int_planOrder asc";
			
	 
	 return sql;
 }
  
  /* (non-Javadoc)
 * �༭ҳ����Ҽ�¼ �汾����temp  л����
 * @see mes.os.dao.DAO_Plan#getplancontainstemp(java.lang.String, int, java.lang.String)
 */
public  String getplancontainstemp(String produceDate,int produnitid,String workOrder){
	  String sql="select * from t_os_plan where str_versioncode='temp'" 
	+" and to_char(dat_produceDate,'yyyy-MM-dd')='"+produceDate+"'"
	+" and int_produnitid="+produnitid+"  and str_workOrder='"+workOrder+"' order by int_planOrder asc";
	
	return sql;
  }
  
 
 /* (non-Javadoc)
 * �˶��Ƿ����order�ļƻ�˳��� л����
 * @see mes.os.dao.DAO_Plan#checkplanorder(java.lang.String, int, java.lang.String, int)
 */
public  String checkplanorder(String produceDate,int produnitid,String workOrder,int order){
	 String sql="select * from  t_os_plan where  str_versioncode='temp' and int_planOrder="+order+" " 
		+" and to_char(dat_produceDate,'yyyy-MM-dd')='"+produceDate+"'"
        +" and int_produnitid="+produnitid+"  and str_workOrder='"+workOrder+"' ";

     return sql;
 }
  

  
 /**ͨ��id��ѯ������Ԫ������ڼ�鹤��ʱ�̱�ɾ������
	 * author : ������
	 */
 public String getAllPlanByProdunitidOrder(int Produnitid,String Order){
	 String sql="select int_id from  t_os_plan where int_produnitid="+Produnitid+"  and str_workOrder='"+Order+"' ";
	 return sql;

	 
 }
 
 /* (non-Javadoc)
 *�ƻ�����ָ��ʱ û�����ļƻ�����Ա��������л����
 * @see mes.os.dao.DAO_Plan#getmaxPlanexcepttempupload(java.lang.String, int, java.lang.String)
 */
public String getmaxPlanexcepttempupload(String produceDate,int produnitid,String workOrder){
	 String sql="select * from t_os_plan where str_versioncode=(select max(str_versioncode) from t_os_plan" 
			+" where to_char(dat_produceDate,'yyyy-MM-dd')='"+produceDate+"'"
	+" and int_produnitid="+produnitid+" and str_workOrder='"+workOrder+"' and str_versioncode!='temp' and int_upload=1 " +")  order by int_planOrder asc";
    return sql;
	}

/**
 * @author л����
 * �ڼƻ�����ʱ����������֤�����������汾��������Ԫ�������Ƿ��ظ���
 *
 */
  public String getplanbymaterielproid_upload_andmaxversion(String materiel,int produnitid){
	  String sql="select * from t_os_plan where str_versioncode in (select max(str_versioncode) from t_os_plan group by str_workOrder ,dat_producedate,int_produnitid ) and int_upload=1 and int_produnitid="+produnitid+" and str_producemarker='"+materiel+"'";
	  return sql;
  }
 }



