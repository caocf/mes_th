package com.qm.th.tg.dao;

/**
 * �ɶ���DAO
 * 
 * @author YuanPeng
 *
 */
public class DAO_ChengduCar {

	/**
	 * ���տ�ʼ����ɾ���ɶ��ݴ�
	 * 
	 * @param startDate	��ʼ����
	 * @return
	 */
	public String deleteChengduJettaByStartDate(String startDate){
		String sql = "delete from cardata where substring(cCarNo,6,1)='0'" +
			" and substring(ccarno,5,1)='7'" +
			" and convert(varchar(200),dwbegin,23)<=convert(varchar(200),'" + 
			startDate + "',23)";
		return sql;
	}
	
	/**
	 * ���տ�ʼ���ڲ�ѯ�ɶ��ݴ�
	 * 
	 * @param startDate	��ʼ����
	 * @return
	 */
	public String getChengduJettaByStartDate(String startDate){
		String sql = "select cCarNo,cVinCode,dWBegin from cardata" +
				" where substring(cCarNo,6,1)='0'" +
				" and substring(ccarno,5,1)='7'" +
				" and convert(varchar(200),dwbegin,23)<=convert(varchar(200),'" + 
			startDate + "',23) order by dWBegin,cSEQNo";
		return sql;
	}
	
}
