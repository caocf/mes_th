package mes.pm.dao;

import mes.pm.bean.AssDocItem;
import mes.pm.bean.AssembleDoc;

public class DAO_AssembleDocForOracle implements DAO_AssembleDoc {

	/**
	 * ����װ��ָʾ����sql���
	 * 
	 * @param assembleDoc װ��ָʾ������
	 * @param CreateUID �����û�ID
	 * @return
	 */
	public String saveAssembleDoc(AssembleDoc assembleDoc){
		String sql = "insert into t_pm_AssembleDoc(int_id,Str_Name,Int_delete,Str_materiel," +
				"Str_description,Dat_createDate,Dat_upDate,Int_CreateUID,Int_UpdateUID) " +
				"values(seq_pm_ASSEMBLEDOC.nextval,'"+assembleDoc.getName()+"',0,'"+
				assembleDoc.getMateriel()+"','"+assembleDoc.getDescription()+"',sysdate,sysdate,"
				+assembleDoc.getCreateUID()+","+assembleDoc.getCreateUID()+")";
		return sql;
	}
	
	/**
	 * ͨ����Ų��װ��ָʾ����sql���
	 * 
	 * @param id װ��ָʾ�����
	 * @return ͨ����Ų��װ��ָʾ������
	 */
	public String getAssembleDocById(int id){
		String sql = "select * from t_pm_AssembleDoc where int_id ="+id;
		return sql;
	}
	
	/**
	 * ͨ�����ɾ��װ��ָʾ����sql���
	 * 
	 * @param id װ��ָʾ�����
	 * @return
	 */
	public String delAssembleDocById(int id){
		String sql = "update t_pm_AssembleDoc set int_delete=1 where int_id ="+id;
		return sql;
	}
	
	/**
	 * ͨ�����ϱ�ʾ���װ��ָʾ����sql���
	 * 
	 * @param materiel ���ϱ�ʾ 
	 * @return ͨ�����ϱ�ʾ���װ��ָʾ������
	 */
	public String getAssembleDocByMateriel(String materiel){
		String sql = "select * from t_pm_AssembleDoc where Str_materiel ='"+materiel+"' and int_delete<>1";
		return sql;
	}
	
	/**
	 * �����ѯ����װ��ָʾ��
	 * 
	 * @return �����ѯ����װ��ָʾ�����б�
	 */
	public String getAllAssembleDocsByDESC(){
		String sql = "select tpm.*,to_char(tpm.DAT_CREATEDATE,'yyyy-mm-dd hh24:mi:ss') as createdate,"+
			"to_char(tpm.DAT_UPDATE,'yyyy-mm-dd hh24:mi:ss') as updatedate,"+
			"(select cusrname from data_user where tpm.int_createuid = nusrno ) createname,"+
			"(select cusrname from data_user where tpm.int_updateuid = nusrno ) updatename "+ 
			"from t_pm_assembledoc tpm "+
			"where tpm.int_delete=0 order by int_id desc";
		System.out.println(sql);
		return sql;
	}
	
	/**
	 * �����ѯ����װ��ָʾ��
	 * 
	 * @return �����ѯ����װ��ָʾ�����б�
	 */
	public String getAllAssembleDocs(){
		String sql = "select tpm.*,to_char(tpm.DAT_CREATEDATE,'yyyy-mm-dd hh24:mi:ss') as createdate,"+
			"to_char(tpm.DAT_UPDATE,'yyyy-mm-dd hh24:mi:ss') as updatedate,"+
			"(select cusrname from data_user where tpm.int_createuid = nusrno ) createname,"+
			"(select cusrname from data_user where tpm.int_updateuid = nusrno ) updatename "+ 
			"from t_pm_assembledoc tpm "+
			"where tpm.int_delete=0 order by int_id asc";
		return sql;
	}
	
	/**
	 * ͨ��װ��ָʾ������ѯ���
	 * 
	 * @param name
	 * @return ͨ��װ��ָʾ������ѯ�����
	 */
	public String getAssembleDocIdByName(String name){
		String sql = "select int_id from t_pm_assembledoc where str_name='"+name+"' and int_delete<>1";
		return sql;
	}
	
	/**
	 * ͨ��װ��ָʾ������ѯװ��ָʾ������
	 * 
	 * @param name װ��ָʾ���� 
	 * @return װ��ָʾ������
	 */
	public String getAssembleDocCountByName(String name){
		String sql = "select count(*) from t_pm_assembledoc where str_name='"+name+"' and int_delete<>1";
		return sql;
	}
	
	/**
	 * ����װ��ָʾ��
	 * 
	 * @param assembleDoc װ��ָʾ������
	 * @return 
	 */
	public String updateAssembleDoc(AssembleDoc assembleDoc){
		String sql = "update t_pm_assembledoc set str_name='"+assembleDoc.getName()+"', Str_materiel='"+
		assembleDoc.getMateriel()+"', Str_description='"+assembleDoc.getDescription()+"', Dat_upDate=sysdate"+
		", Int_UpdateUID= "+assembleDoc.getUpdateUID()+" where int_id="+assembleDoc.getId();
		return sql;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * ����װ��ָʾ����
	 * 
	 * @param assDocItem װ��ָʾ������
	 * @return
	 */
	public String saveAssDocItem(AssDocItem assDocItem){
		String sql = "insert into t_pm_AssDocItem(int_id,Int_AssDocId,Str_Name,Str_code,Str_description)" +
				"values(seq_pm_ASSDOCITEM.nextval,"+assDocItem.getAssDocId()+",'"+assDocItem.getName()+"','"+
				assDocItem.getCode()+"','"+assDocItem.getDescription()+"')";
		return sql;		
	}
	
	/**
	 * ͨ��װ��ָʾ���Ų��װ��ָʾ���б��sql���
	 * 
	 * @param id װ��ָʾ����
	 * @return ͨ��װ��ָʾ���Ų��װ��ָʾ���б�
	 */
	public String getAssDocItemByAssembleDocId(int id){
		String sql = "select * from t_pm_AssDocItem where Int_AssDocId ="+id;
		return sql;
	}
	
	/**
	 * ͨ��װ��ָʾ����ɾ��װ��ָʾ���sql���
	 * 
	 * @param id װ��ָʾ�����
	 * @return
	 */
	public String delAssDocItemByAssembleDocId(int id){
		String sql = "delete from t_pm_AssDocItem where Int_AssDocId ="+id;
		return sql;
	}

}
