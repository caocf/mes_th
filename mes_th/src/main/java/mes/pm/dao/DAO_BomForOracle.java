package mes.pm.dao;

public class DAO_BomForOracle implements DAO_Bom {
	
	/**
	 * ͨ��BOM��Ų�ѯBOM����
	 * 
	 * @param id	BOM���
	 * @return	BOM����
	 */
	public String getBomById(int id){
		String sql = "select * from t_pm_Bom where int_id="+id;
		return sql;
	}
	
	/**
	 * ͨ�������ʾ��ѯBOM�����б�
	 * 
	 * @param component �����ʾ
	 * @return  BOM�����б�
	 */
	public String getBomsBycomponent(String component){
		String sql = "select * from t_pm_Bom where Str_component="+component;
		return sql;
	}
	
	/**
	 *  ͨ���ϼ���ű�ʾ��ѯBOM����
	 * 
	 * @param parentid �ϼ���ű�ʾ
	 * @return BOM����
	 */
	public String getBomsByParentId(int parentid){
		String sql ="select * from t_pm_Bom where Int_parentid="+parentid;
		return sql;
	}
	
	/**
	 * 	�����ѯ����Bom
	 * 
	 * @return  �����ѯ����Bom�б�  
	 */
	public String getAllBomsByDESC(){
		String sql = "select * from t_pm_bom order by int_id desc";
		return sql;
	}
	
	/**
	 * ͨ�������ʾ��ѯ�¼�BOM����
	 * 
	 * @param Component	�����ʾ
	 * @return ͨ�������ʾ��ѯ�¼�BOM�����б�
	 */
	public String getBomByComponent(String Component){ 
	String sql = "Select * From T_PM_BOM Start With STR_COMPONENT='"+Component+"' Connect By Prior INT_ID=INT_PARENTID order by STR_COMPONENT";
		return sql;
	}
	
	/**
	 * ��ѯ����BOM�����ʶ�������ʶ����
	 * 
	 * @return ��ѯ����BOM�����ʶ�������ʶ�����б�
	 */
	public String getBomsGroupByComponent(){
		String sql = "Select STR_COMPONENT,min(STR_DISCRIPTION) as STR_DISCRIPTION From T_PM_BOM group by STR_COMPONENT order by STR_COMPONENT";
		return sql;
	}
	
	
}
