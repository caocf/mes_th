/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.qm.mes.ra.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.qm.mes.ra.bean.Instruction;

/**
 * 
 * @author YuanPeng
 */
public class DAO_InstructionForSqlserver extends DAO_InstructionForOracle {

	/**
	 * Ԭ�� ��ָ����и��²�����sql���
	 * 
	 * @param instruction
	 * @return ����T_RA_INSTRUCTION�и�ID�ļ�¼
	 */
	public String updateInstruction(Instruction instruction) {
		String a = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
				.format(instruction.getProduceDate());
		String sql = "update T_RA_INSTRUCTION set Int_produnitid = "
				+ instruction.getProdunitid()
				+ " , Dat_produceDate = '"
				+ a
				+ "' , Str_versionCode = '"
				+ instruction.getVersionCode()
				+ "' , Int_instructOrder = "
				+ instruction.getInstructionOrder()
				+ " , Dat_planDate = "
				+ (instruction.getPlanDate() == null
						|| instruction.getPlanDate().toString().equals("null") ? "''"
						: "convert(datetime,'" + instruction.getPlanDate()
								+ "',20)")
				+ " , Int_planOrder = "
				+ instruction.getPlanOrder()
				+ " , Str_produceType = '"
				+ instruction.getProduceType()
				+ "' , Str_produceName = '"
				+ instruction.getProduceName()
				+ "' , Str_produceMarker = '"
				+ instruction.getProduceMarker()
				+ "' , Str_workOrder = '"
				+ instruction.getWorkOrder()
				+ "' , Str_workTeam = '"
				+ instruction.getWorkTeam()
				+ "' , Tim_planBegin = "
				+ (instruction.getPlanBegin() == null
						|| instruction.getPlanBegin().toString().equals("null") ? "''"
						: "convert(datetime,'" + instruction.getPlanBegin()
								+ "',20)")
				+ " , Tim_planFinish = "
				+ (instruction.getPlanFinish() == null
						|| instruction.getPlanFinish().toString()
								.equals("null") ? "''" : "convert(datetime,'"
						+ instruction.getPlanBegin() + "',20)")
				+ " , Int_count = " + instruction.getCount()
				+ " , Int_instructStateID = "
				+ instruction.getInstructStateID() + " , Int_issuance = "
				+ instruction.getIssuance() + " , Int_StaError = "
				+ instruction.getStaError() + " , Int_delete = "
				+ instruction.getDelete() + " where int_id = "
				+ instruction.getId();
		return sql;

	}

	/**
	 * Ԭ�� ���ұȸ�˳���С��˳���ָ��
	 * 
	 * @param ProduceUnitID
	 *            ������Ԫ
	 * @param str_date
	 *            ��������
	 * @param Int_instructOrder
	 *            ָ��˳��
	 * @return �ȸ�˳���С��˳���ָ��
	 */
	public String getByOrderMinus(int ProduceUnitID, String str_date,String workOrder,
			int Int_instructOrder) {
		String sql = "select * from T_RA_INSTRUCTION where int_instructorder <="
				+ Int_instructOrder
				+ " and Int_produnitid = "
				+ ProduceUnitID
				+ " and str_workOrder='"+workOrder+"' and convert(datetime,Dat_produceDate,20)=convert(datetime,'"
				+ str_date
				+ "',20) and int_delete<>1 order by int_instructorder desc";
		return sql;
	}

	/**
	 * Ԭ�� ���ұȸ�˳��Ŵ��˳���ָ��
	 * 
	 * @param ProduceUnitID
	 *            ������Ԫ
	 * @param str_date
	 *            ��������
	 * @param Int_instructOrder
	 *            ָ��˳��
	 * @return �ȸ�˳���С��˳���ָ��
	 */
	public String getByOrderPlus(int ProduceUnitID, String str_date,String workOrder,
			int Int_instructOrder) {
		String sql = "select * from T_RA_INSTRUCTION where int_instructorder >="
				+ Int_instructOrder
				+ " and Int_produnitid = "
				+ ProduceUnitID
				+ " and str_workorder ='"
				+ workOrder
				+ "' and convert(datetime,Dat_produceDate,20)=convert(datetime,'"
				+ str_date
				+ "',20) and int_delete<>1 order by int_instructorder";
		return sql;
	}

	/**
	 * ͨ��������Ԫ�š��������ڡ�״̬�Ų�ѯ��ҵָ�� Ԭ��
	 * 
	 * @param Int_produnitid
	 *            ������Ԫ��
	 * @param str_date
	 *            ��������
	 * @param stateid
	 *            ״̬��
	 * @return ͨ��������Ԫ�š��������ڡ�״̬�Ų�ѯ����ҵָ��
	 */
	public String getInstructionByProUnitProDateState(int Int_produnitid,
			String str_date,String  workOrder ,int stateid) {
		String sql = "select * from t_ra_instruction where Int_produnitid = "
				+ Int_produnitid + " and convert(datetime,Dat_produceDate,20)="
				+ "convert(datetime,'" + str_date + "',20)"
				+ " and int_instructstateid = " + stateid
				+ " and str_wrokorder = '" + workOrder
				+ "' and int_delete<>1 order by int_instructorder";
		return sql;
	}

	/**
	 * ����ָ�� Ԭ��
	 * 
	 * @param instruction
	 * @return
	 */
	public String saveInstruction(Instruction instruction) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				

		String sql = null;
		String sql1 = "insert into T_RA_INSTRUCTION(Int_produnitid,"
				+ "Dat_produceDate,Str_versionCode,Int_instructOrder,Dat_planDate"
				+ ",Int_planOrder,Str_produceType,Str_produceName,Str_produceMarker,"
				+ "Str_workOrder,Str_workTeam,Tim_planBegin,Tim_planFinish,Int_count,"
				+ "Int_instructStateID,Int_issuance,Int_StaError,Int_delete,Int_Edit) "
				+ "values("
				+ instruction.getProdunitid()
				+ ","
				+ "convert(datetime,'"
				+ sdf.format(instruction.getProduceDate())
				+ "',20)"
				+ ","
				+ (instruction.getVersionCode() == null
						|| instruction.getVersionCode().equals("null") ? "''"
						: ("'" + instruction.getVersionCode().toString()) + "'")
				+ ","
				+ instruction.getInstructionOrder()
				+ ","
				+ (instruction.getPlanDate() == null
						|| instruction.getPlanDate().toString().equals("null") ? "''"
						: "convert(datetime,'"
								+ sdf.format(instruction.getPlanDate())
								+ "',20)")
				+ ","
				+ instruction.getPlanOrder()
				+ ","
				+ (instruction.getProduceType() == null
						|| instruction.getProduceType().equals("null") ? ""
						: ("'" + instruction.getProduceType())) + "'" + ",";
		String sql3 = (instruction.getProduceName() == null
				|| instruction.getProduceName().equals("null") ? "" : ("'"
				+ instruction.getProduceName() + "'"))
				+ ","
				+ (instruction.getProduceMarker() == null
						|| instruction.getProduceMarker().equals("null") ? "''"
						: ("'" + instruction.getProduceMarker() + "'"))
				+ ","
				+ (instruction.getWorkOrder() == null
						|| instruction.getWorkOrder().equals("null") ? ""
						: ("'" + instruction.getWorkOrder() + "'"))
				+ ","
				+ (instruction.getWorkTeam() == null
						|| instruction.getWorkTeam().equals("null") ? "" : ("'"
						+ instruction.getWorkTeam() + "'"))
				+ ","
				+ (instruction.getPlanBegin() == null
						|| instruction.getPlanBegin().toString().equals("null") ? null
						: "convert(datetime,'"
								+ sdf.format(instruction.getPlanBegin()).toString()
								+ "',20)") + ",";
		String sql4 = (instruction.getPlanFinish() == null
				|| instruction.getPlanFinish().toString().equals("null") ? "''"
				: "convert(datetime,'" + sdf.format(instruction.getPlanFinish()).toString()
						+ "',20)")
				+ ","
				+ instruction.getCount()
				+ ","
				+ instruction.getInstructStateID()
				+ ","
				+ instruction.getIssuance()
				+ ","
				+ instruction.getStaError()
				+ ","
				+ instruction.getDelete()
				+ ","
				+ instruction.getEdit()
				+ ")";
		sql = sql1 + sql3 + sql4;
		return sql;
	}

	/**
	 * ͨ��������Ԫ�š��������ڡ�˳��Ŵ����׸��Ǳ༭��������int_delete�ֶ�ֵΪ1 Ԭ��
	 * 
	 * @param ProduceUnitID
	 * @param str_date
	 * @param UnlockStartOrder
	 * @return
	 */
	public String DeleteInstructionByProUnitDateworkOrderUnlockOrder(int ProduceUnitID,
			String str_date,String workOrder, int UnlockStartOrder) {
		String sql = "update t_ra_Instruction set Int_delete = 1 where Int_produnitid = "
				+ ProduceUnitID
				+ " and str_workOrder = '"
				+ workOrder
				+ "' and convert(datetime,Dat_produceDate,20)=convert(datetime,'"
				+ str_date
				+ "',20) and Int_instructOrder >= "
				+ UnlockStartOrder;
		return sql;
	}

	/**
	 * ͨ��������Ԫ�š��������ڲ�ѯ��¼���� Ԭ��
	 * 
	 * @param ProduceUnitID
	 *            ������Ԫ��
	 * @param str_date
	 *            ��������
	 * @return ����ͨ��������Ԫ�š��������ڲ�ѯ��¼������
	 */
	public String getCountByProUnitDateOrder(int ProduceUnitID, String str_date,String str_workOrder) {
		String sql = "select count(*) from t_ra_instruction where Int_produnitid="
				+ ProduceUnitID
				+ " and convert(datetime,Dat_produceDate,20)=convert(datetime,'"
				+ str_date + "',20) and str_workOrder='"
				+ str_workOrder + "'and int_delete <> 1";
		return sql;
	}

	/**
	 * ͨ��������Ԫ�ź��������ڲ�ѯδ��ɾ����ָ��
	 * 
	 * @param produnitid
	 *            ������Ԫ��
	 * @param str_date
	 *            ��������
	 * @return ָ���
	 */
	public String getInstructionsByProdunitDateOrder(int produnitid, String str_date,String str_workorder) {
		String sql = "select * from t_ra_instruction where convert(datetime,Dat_produceDate,20)=convert(datetime,'"
				+ str_date
				+ "',20) and int_produnitid="
				+ produnitid
				+ " and str_workorder="
				+ str_workorder
				+ "  and int_delete<>1 order by Int_instructOrder";
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
	public String getCountByProUnitDateMarker(int produnitid, String str_date,String workOrder,
			String marker) {
		String sql = "select count(*) from t_ra_instruction where convert(datetime,Dat_produceDate,20)=convert(datetime,'"
				+ str_date
				+ "',20) and int_produnitid="
				+ produnitid
				+ " and str_workorder='"
				+ workOrder
				+ "' and Str_produceMarker='" + marker + "' and int_delete<>1";
		return sql;
	}

	/**
	 * ͨ��������Ԫ�š��������ڲ�ѯ��¼ Ԭ��
	 * 
	 * @param ProduceUnitID
	 *            ������Ԫ��
	 * @param str_date
	 *            ����
	 * @param List
	 *            <Integer> list_state ״̬����
	 * @return ����ͨ��������Ԫ�š��������ڲ�ѯ��¼
	 */
	public String getCountByProUnitDateState(int ProduceUnitID,
			List<Integer> list_state) {
		String str = "";
		Iterator<Integer> iter = list_state.iterator();
		if (list_state.size() > 0) {
			str += list_state.get(0);
		} else {
			str += "-1";
		}
		while (iter.hasNext()) {
			int n = Integer.parseInt(iter.next().toString());
			str += ',';
			str += n;
		}
		
		String sql = "select * from t_ra_instruction  where Int_produnitid="
				+ ProduceUnitID			
				+" and datediff(day,convert(datetime,getdate(),20),convert(datetime,Dat_produceDate,20))=0"
				+ " and Int_instructStateID in(" + str
				+ ") and int_delete <> 1 and INT_ISSUANCE = 1";
		
		return sql;
	}

	/**
	 * �޸�ָ��״̬ л����
	 * 
	 * @param structStateID
	 *            ״̬id
	 * @param staError
	 *            ״̬��ת���쳣��ʶ
	 * @param producemarker
	 *            ��Ʒ��ʶ int_produnitid ������Ԫ��id
	 * 
	 * @return �����ڸ�������Ԫ���Ӧ��ָ��
	 */
	public String updateInstructState(int structStateID, int staError,
			String producemarker, int produnitid, String str_date) {
		String sql = "update t_ra_Instruction set INT_INSTRUCTSTATEID="
				+ structStateID
				+ ", INT_STAERROR="
				+ staError
				+ " where convert(datetime,Dat_produceDate,20)=convert(datetime,'"
				+ str_date + "',20) and STR_PRODUCEMARKER='" + producemarker
				+ "' and int_produnitid=" + produnitid;

		return sql;
	}

	/**
	 * �鿴����ָ��״̬ л����
	 * 
	 * @param producemarker
	 *            ��Ʒ��ʶ
	 * @return �������Ӧ��ָ��
	 */
	public String checkstr_produceMarker(String str_produceMarker,
			int produnitid, String str_date) {
		String sql = "select ra.Int_instructstateid from t_ra_instruction ra where str_produceMarker='"
				+ str_produceMarker
				+ "' and convert(datetime,Dat_produceDate,20)=convert(datetime,'"
				+ str_date
				+ "',20) and ra.int_delete=0 and int_Edit<>1 and ra.int_issuance=1 and  ra.int_produnitid="
				+ produnitid;

		return sql;
	}

	/**
	 * ָ��״̬�����쳣ʱҪ������ֵ����� л����
	 * 
	 * @param producemarker
	 *            ��Ʒ��ʶ
	 * @param userId�û�id
	 * @param Int_instructionStateID״̬id
	 * @param produceUnit������Ԫ
	 * @return �������Ӧ��ָ��
	 */
	public String savet_ra_Instructionexception(int Int_instructionStateID,
			int userId, String produceMarker, int INT_GATHERID) {

		String sql = "insert into t_ra_Instructionexception values("
				+ INT_GATHERID + ",convert(varchar,getdate(),20),"
				+ Int_instructionStateID + ",'" + produceMarker + "'," + userId
				+ ")";

		return sql;
	}

	/**
	 * �鿴��Ʒ��ʶ���ҳ�������������ֵ��ָ����п��Ƿ���ڡ� л����
	 * 
	 * @param producemarker
	 *            ��Ʒ��ʶ
	 * @param produnitid
	 *            ������Ԫ��
	 * @param str_date
	 *            ��������
	 * @return �������Ӧ��ָ��
	 */

	public String checkmaterielValue(String producemarker, int produnitid,
			String str_date) {
		String sql = "select Str_producemarker  from  t_ra_instruction ra  where   ra.int_issuance=1 and ra.int_produnitid="
				+ produnitid
				+ ""
				+ " and convert(datetime,Dat_produceDate,20)=convert(datetime,'"
				+ str_date
				+ "',20) and ra.int_delete=0 and Str_producemarker='"
				+ producemarker + "' order by int_instructorder  ";

		return sql;
	}

	/**
	 * ��������ʱ���������Ԫ�鿴��ص�ָ� л����
	 * 
	 * @param int_produnitid,String
	 *            str_date,workOrder
	 * @return �������Ӧ��ָ��
	 */

	public String getInstructionByProduceUnitProduceDateWorkorder(int int_produnitid,
			String str_date,String workOrder) {

		String sql = "select * from t_ra_instruction where int_produnitid="
				+ int_produnitid
				+ " and str_workorder='"
				+ workOrder
				+ "' and convert(datetime,Dat_produceDate,20)=convert(datetime,'"
				+ str_date
				+ "',20) and int_delete=0 and int_Edit<>1  order by int_instructorder asc";

		return sql;
	}
	/**
	 * ��������ʱ���������Ԫ�鿴��ص�ָ� л����
	 * 
	 * @param int_produnitid,String
	 *            str_date
	 * @return �������Ӧ��ָ��
	 */

	public String getInstructionByProduceUnitProduceDate(int int_produnitid,
			String str_date) {

		String sql = "select * from t_ra_instruction where int_produnitid="
				+ int_produnitid
				+ " and  convert(datetime,Dat_produceDate,20)=convert(datetime,'"
				+ str_date
				+ "',20) and int_delete=0 and int_Edit<>1  order by int_instructorder asc";

		return sql;
	}

	/**
	 * �˶��Ƿ���Խ��а汾���� л����
	 * 
	 * @param int_produnitid
	 *            ������Ԫ��
	 * @param str_date
	 *            ��������
	 * 
	 */
	public String checksaveVersion(int int_produnitid, String str_date,String workOrder) {
		String sql = "select * from t_ra_instruction where  int_delete=0  and int_produnitid="
				+ int_produnitid
				+ "  and str_workorder='"
				+ workOrder
				+ "' and  convert(datetime,Dat_produceDate,20)=convert(datetime,'"
				+ str_date
				+ "',20) and (int_instructstateid<>(select int_instructStateid from t_ra_produceunit where int_id="
				+ int_produnitid + " and int_delete=0)or int_Edit=1)  ";
		return sql;
	}

	/**
	 * ��������ʱ���������Ԫ�ͻָ�״̬�鿴��ص�ָ����а汾�ָ� л����
	 * 
	 * @param int_produnitid
	 *            ������Ԫ��
	 * @param str_date
	 *            ��������
	 * @param int_stateid
	 *            ״̬��
	 * 
	 */
	public String comebackVersion(int int_produnitid, String str_date,
			int int_stateid) {
		String sql = "select * from t_ra_instruction where int_produnitid="
				+ int_produnitid
				+ " and convert(datetime,Dat_produceDate,20)=convert(datetime,'"
				+ str_date + "',20)" + " and int_instructstateid<>"
				+ int_stateid + " and int_delete=0 and int_Edit<>1 ";

		return sql;
	}

	/**
	 * ��������ʱ���������Ԫɾ��ָ�� л����
	 * 
	 * @param int_produnitid
	 *            ������Ԫ��
	 * @param str_date
	 *            ��������
	 * 
	 */
	public String deleteInstructionByProduceUnitProduceDateWorkorder(int int_produnitid,
			String str_date,String workOrder) {
		String sql = "update t_ra_instruction set int_delete=1 where int_delete=0 and int_edit<>1 and int_produnitid="
				+ int_produnitid
				+ " and str_workorder="
				+ workOrder
				+ " and convert(datetime,Dat_produceDate,20)=convert(datetime,'"
				+ str_date + "',20)";

		return sql;
	}

	/**
	 * ͨ���ɼ���id��ϵͳʱ���ѯ��ص�ָ� л����
	 * 
	 * 
	 * 
	 */
	public String getGatherwork(int gatherid) {
		Calendar calendar=Calendar.getInstance();
	     calendar.setTime(new Date());
	     int hour=calendar.get(Calendar.HOUR_OF_DAY);
	     String year=String.valueOf(calendar.get(Calendar.YEAR));
	     String month=String.valueOf(calendar.get(Calendar.MONDAY)+1);
	      if(calendar.get(Calendar.MONDAY)+1<10){
	      	month="0"+month;
	      }
	     
	     String day=null;
	     String str_date=null;
	     if(hour>=8)
	     {
	  	   day=String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
	  	   if(calendar.get(Calendar.DAY_OF_MONTH)<10){
	  		   day="0"+day;
	  	   }
	  	   str_date=year+"-"+month+"-"+day;
	     }
	     else
	     { 
	  	   day=String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)-1);
	  	   str_date=year+"-"+month+"-"+day;
	     }
	     
	     String sql="select ra.*  from t_ra_instruction ra  , t_tg_gather g ,t_os_workSchedle ws where  ws.int_produnitid=g.int_produnitid and g.int_produnitid=ra.int_produnitid and ws.str_workorder=ra.str_workOrder and ra.int_issuance=1 and g.int_id="+gatherid+""
		 +" and convert(datetime,Dat_produceDate,20)=convert(datetime,'"
				+ str_date
				+ "',20)"
				+ " and ra.int_delete=0 and int_Edit<>1 and ra.Int_instructStateID in(select int_fromstate from t_ra_gatherstaterule gsr,t_ra_stateconversion sv where  gsr.int_gatherid="+gatherid+"" +
		 		" and gsr.int_stateconversionid=sv.int_id)  order by  ws.str_workschedle,   int_instructorder    " ;

		return sql;
		
	}

	/**
	 * ����ָ������˳��� ����ָ��׷�� л����
	 */
	public String getInstructionMaxOrder(int int_produnitid, String str_date,String workOrder) {
		String sql = "select max(int_instructorder) from t_ra_instruction where  int_delete=0  and int_produnitid="
				+ int_produnitid
				+ " and str_workorder="
				+ workOrder
				+ " and convert(datetime,Dat_produceDate,20)=convert(datetime,'"
				+ str_date + "',20)";

		return sql;
	}

	/**
	  * ͨ���ӿ�ʼʱ�䵽����ʱ�估������Ԫ��ѯ��ص�ָ��
	  * л����
	  */
	public String getInstructionByStartAndEndProduceunitOrder(int produceid,String starttime,String endtime,String workorder){
		String sql = "select * from t_ra_instruction where int_delete=0 and int_produnitid="
				+ produceid
				+ " and  str_workorder='"+workorder+"' and "
				+ "convert(datetime,Dat_produceDate,20)>="
				+ "convert(datetime,'"
				+ starttime
				+ "',20)"
				+ " and convert(datetime,Dat_produceDate,20)<= "
				+ "convert(datetime,'" + endtime + "',20)";
		
		return sql;
	}

	/**
	 * �鿴������Ԫ���������ڵ�������ֵ ����ָ���ʱ�ж� л����
	 */
	public String getStr_produceMarkerbyproduceUnitandproduceDate(
			int produceid, String str_date) {
		String sql = "select * from t_ra_instruction where  int_delete=0  and int_produnitid="
				+ produceid
				+ " and convert(datetime,Dat_produceDate,20)=convert(datetime,'"
				+ str_date + "',20)";
		return sql;
	}

	/**
	 * л���� ������Ԫ��ʱ�� ͨ�������ϵ�ֵ�鿴ָ���Ƿ��ڱ༭״̬��
	 */
	public String checkinstructionedit(String producemarker, int produnitid,
			String str_date) {
		String sql = "select * from t_ra_instruction ra where str_produceMarker='"
				+ producemarker
				+ "'and ra.int_delete=0  and  ra.int_produnitid="
				+ produnitid
				+ "  and convert(datetime,Dat_produceDate,20)=convert(datetime,'"
				+ str_date + "',20)";

		return sql;
	}

	/**
	 * ��ѯ��ǰ������Ԫ���ǵ�ǰ�������ڵ�ָ�����
	 * 
	 * @param produnitid
	 * @param str_date
	 * @return
	 */
	public String getInstructionsByProdunitOtherProdDate(int produnitid,
			String str_date) {
		String sql = "select * from t_ra_instruction ra where ra.int_delete=0  and  ra.int_produnitid="
				+ produnitid
				+ "  and convert(datetime,Dat_produceDate,20)<>convert(datetime,'"
				+ str_date + "',20)";
		return sql;
	}
	 public String export(String int_id ,String overtime,String endtime,String workOrder){
		 
		 String sql="select INT_PRODUNITID,STR_WORKTEAM,STR_WORKORDER,DAT_PRODUCEDATE,INT_INSTRUCTORDER," +
		 		"STR_PRODUCETYPE,STR_PRODUCENAME,STR_PRODUCEMARKER,INT_COUNT,TIM_PLANBEGIN,TIM_PLANFINISH," +
		 		"DAT_PLANDATE,INT_PLANORDER,STR_VERSIONCODE,INT_STAERROR from t_ra_instruction " +
		 		"where int_delete=0 and int_produnitid="+Integer.parseInt(int_id)+"" 		 		
		 		+" and datediff(day,convert(datetime,'"+overtime+"',20),convert(datetime,Dat_produceDate,20))<=0"
		 		+" and datediff(day,convert(datetime,'"+endtime+"',20),convert(datetime,Dat_produceDate,20))>=0"
		 		+" and str_workorder='"+workOrder+"' order by Dat_produceDate desc,Int_instructOrder asc";
		 
		 
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
		 
		 String sql = "select * fromt_ra_instruction where int_delete=0 and int_edit<>1 and int_produnitid="
				+ int_produnitid
				+ " and str_workorder="
				+ workOrder
				+ " and convert(datetime,Dat_produceDate,20)=convert(datetime,'"
				+ str_date + "',20)";

		return sql;
		 
		 
	 }
	 
}
