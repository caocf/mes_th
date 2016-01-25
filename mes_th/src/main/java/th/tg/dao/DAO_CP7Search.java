package th.tg.dao;

/**
 * QAD�ܳɲ�ѯDAO
 * 
 * @author YuanPeng
 *
 */
public class DAO_CP7Search {

	/**
	 * ͨ��ʱ���ͳ��QAD�����ܳ���Ϣ
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public String getCarDataStatByStartTimeEndTime(String startTime,String endTime){
		String sql = "select max(cTfass) cTfass," +
			"max(cCarType) cCarType,sum(nNum) nNum from cp7_cardata " +
			"where dCP7Date>=convert(varchar(100),'" + startTime + "',23) " +
			"and dCP7Date<=convert(varchar(100),'" + endTime + "',23) " +
			"group by cTfass order by cTfass";
		return sql;
	}
	
	/**
	 * ͨ��ʱ����ѯ���ڳ��������Ϣ
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public String getProductDataByStartTimeEndTime(String startTime,String endTime){
		String sql = "select convert(varchar(100),dCP7Date,23) cp7date,cCarNo,cVinCode,cpartno,cpartname,nNum " +
			"from cp7_productdata " +
			"where dCP7Date>=convert(varchar(100),'" + startTime + "',23) " +
			"and dCP7Date<=convert(varchar(100),'" + endTime + "',23) " +
			"order by dCP7Date";
		return sql;
	}
	
	/**
	 * ͨ��ʱ���ͳ�ƴ��ڳ��������Ϣ
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public String getProductDataStatByStartTimeEndTime(String startTime,String endTime){
		String sql = "select max(cpartno) cpartno," +
			"max(cpartname) cpartname,sum(nNum) nNum from cp7_productdata " +
			"where dCP7Date>=convert(varchar(100),'" + startTime + "',23) " +
			"and dCP7Date<=convert(varchar(100),'" + endTime + "',23) " +
			"group by cpartno order by cpartno";
		return sql;
	}
	
	/**
	 * ͨ��ʱ���ͳ��δƥ��ɢ����Ϣ
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public String getErrStatByStartTimeEndTime(String startTime,String endTime){
		String sql = "select max(cpartno) cpartno," +
			"max(cpartname) cpartname,sum(nNum) nNum from cp7_err " +
			"where dCP7Date>=convert(varchar(100),'" + startTime + "',23) " +
			"and dCP7Date<=convert(varchar(100),'" + endTime + "',23) " +
			"group by cpartno order by cpartno";
		return sql;
	}
}
