package mes.os.dao;

import mes.os.bean.UpLoadRecord;

/**
 * 
 * @author л����
 *
 */
public class DAO_UploadRecordForSqlserver extends  DAO_UploadRecordForOracle{
	
	/* (non-Javadoc)
	 * ����������ҵ�ƻ���¼��  л����
	 * @see mes.os.dao.DAO_UploadRecordForOracle#SaveUploadRecord(mes.os.bean.UpLoadRecord)
	 */
	public String SaveUploadRecord(UpLoadRecord uploadrecord){
		String sql="insert into t_os_upLoadRecord values("
		  +"sysdate,'"
		  +uploadrecord.getUserName()+"','"
		  +uploadrecord.getVersionCode()+"',"
		  +uploadrecord.getUpload()+")";
			
		return sql;
	}
}
