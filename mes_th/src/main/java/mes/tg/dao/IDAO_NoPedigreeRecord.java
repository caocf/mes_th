package mes.tg.dao;
import mes.tg.bean.NoPedigreeRecord;
/**
 * @author л����
 * 
 */
public interface IDAO_NoPedigreeRecord {

	/**
	 * ����NoPedigreeRecord noPedigreeRecord ʵ�����
	 * 
	 * noPedigreeRecord.int_gatherrecord_id
	 * noPedigreeRecord.str_value
	 * noPedigreeRecord.str_gatheritemExtName
	 * @return
	 */
   public	String saveNoPedigreeRecord (NoPedigreeRecord noPedigreeRecord);
   /**
    * @param int id 
    * ͨ��id��ѯ������ϵ��¼�Ķ�Ӧ�������ֶ�ֵ
    * @return 
    */
  
   public String getNoPedigreeRecordById(int id);
   
   /**
    * 
    * ��ѯ������ϵ��¼�������ֶ�ֵ
    * @return 
    */
   public  String getAllNoPedigreeRecord ();
   

   /**
    * @param  materielvalue
    * ͨ�������ϵ�ֵ����ѯ�����йصķ���ϵ��¼��
    * @return 
    */
   public String getNoPedigreeRecordByMaterielValue(String materielvalue);

   
	/**
	 * ����NoPedigreeRecord noPedigreeRecord ʵ�����
	 * 
	 * noPedigreeRecord.int_id
	 * noPedigreeRecord.str_value
	 * noPedigreeRecord.str_gatheritemExtName
	 * @return
	 */
   
   
   public String updateNoPedigreeRecord(NoPedigreeRecord np );
   
   
   
   /**
	 * ͨ�������¼���id����ѯ�����йصķ���ϵ��¼
	 * @param id
	 * 
	 * @return
	 */
  
   public String getNoPedigreeRecordBygatherid(int id);
   
}
