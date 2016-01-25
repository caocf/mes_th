package mes.tg.dao;
//�����˶��ڲɼ���¼����ز���	
import mes.tg.bean.GatherRecord;

/**
 * @author chenpeng
 * 
 */
public interface IDAO_Record {

	/**
	 * ����GatherRecord gr ʵ�����
	 * 
	 * @param gr.int_gatherid
	 *            �ɼ���id <br>
	 * @param gr.int_userid
	 *            �û�id <br>
	 * @param gr.str_materielvalue
	 *            �����ϱ�ʶֵ <br>
	 * @param gr.str_materielname
	 *            �����ϱ�ʶ������ <br>
	 * @param gr.dat_date
	 *            ����ʱ��
	 * @return
	 */
	String saveGatherRecord(GatherRecord gr);

	/**
	 * ͨ��GartherRecord grʵ����󣬲�ѯ��GatherRecord�����к�
	 * 
	 * @param gr.getGatherId()
	 *            �ɼ���id <br>
	 * @param gr.getMaterielValue()
	 *            �����ϱ�ʶֵ <br>
	 * 
	 * @return int_id �����¼id<br>
	 */
	String getGatherRecordId(GatherRecord gr);

	/**
	 * ������ϵ�ļ�¼��
	 * 
	 * @param grecordId
	 *            �����¼����� <br>
	 * @param materielValue
	 *            �����ϱ�ʶֵ <br>
	 * @param materielName
	 *            �����ϱ�ʶ������ <br>
	 * @return ������ϵ��sql���
	 */
	String savePedigreeRecord(int grecordId, String materielValue,
			String materielName);

	/**
	 * create by cp ͨ����Ϣ��id(INT_GATHERID) ȡ��������Ϣ
	 * 
	 * @param grId :
	 *            �ɼ���id
	 * @return 1��int_id �����¼��id <br>
	 *         2��str_name �ɼ����� <br>
	 *         3��str_materielvalue �����ϱ�ʶֵ <br>
	 *         4��INT_USERID �û�id <br>
	 * 
	 * STR_MATERIELNAME �����ϱ�ʶ������
	 */

	String getGatherRecordByGatherId(int grId);

	/**
	 * ͨ�������¼id,ȡ�ù����¼����Ϣ
	 * 
	 * @param id
	 *            �����¼id
	 * @return 1��STR_MATERIELVALUE �����ϱ�ʶֵ <br>
	 *         2��STR_MATERIELNAME �����ϱ�ʶ������<br>
	 *         3��int_gatherid �ɼ���id <br>
	 *         4��int_userid �û�id <br>
	 *         5��strDate DAT_DATE�ı���,�����¼ʱ�� <br>
	 *         6��STR_VALIDATECLASS ���ϱ�ʶ������֤�ַ��� <br>
	 *         7��strdesc ���ϱ�ʶ������֤�ַ�����������Ϣ
	 */
	String getGatherRecordById(int id);

	/**
	 * ͨ�������¼id,ȡ����ϵ����Ϣ
	 * 
	 * @param gatherrecordid
	 *            �����¼id
	 * @return 1��INT_ID �����¼id <br>
	 *         2��STR_MATERIELVALUE �����ϱ�ʶֵ <br>
	 *         3��STR_MATERIELNAME �����ϱ�ʶ������ <br>
	 *         4��STR_VALIDATECLASS ���ϱ�ʶ������֤�ַ��� <br>
	 *         5��strdesc ���ϱ�ʶ������֤�ַ�����������Ϣ
	 */
	String getPedigreeRecordBygrid(int gatherrecordid);
	/**
	 *  �޸�������ֵ
	 * л���� 
	 * @param gatherrecordid
	 *            �����¼id
	 * @param   �����ϵ�ֵ      
	 * 
	 */
	
    String upDateGatherRecord(int id,String mavalue);
 
	/**
	 *  �޸���ϵ��¼
	 * л���� 
	 * @param gatherrecordid
	 *           ��ϵ�ļ�¼id
	 * @param   �����ϵ�ֵ      
	 * 
	 */
    String upDatePedigreeRecord(int id,String str_materiervalue);
    
    /**
	 *   �޸���ϵ��ʷ��¼
	 * л���� 
	 * @param gatherrecordid
	 *           ԭ����¼id
	 * @param   ԭ����¼��ֵ
	 *  @param  ��ӵ�ԭ��
	 * @param  �û���id
	 */
    String savePEDIGREEHISTORY(int id,String value,String cause,String userid);
    
    String countBygatherId(int id);
   
    /**
     * ��ѯ���ϱ�ʶ����������ϵ���е�����
     * 
     * @author YuanPeng
     * @param MaterielRuleName ���ϱ�ʶ������
     * @return ����
     */
    public String countByMaterielRuleName(String MaterielRuleName);

    /**
     * ��ѯ���ϱ�ʶ�������ڹ����¼���е�����
     * 
     * @author YuanPeng
     * @param MaterielRuleName ���ϱ�ʶ������
     * @return ����
     */
    public String countGatherReByMaterielRuleName(String MaterielRuleName);
    
    /**
     * 	ͨ��ID��ѯ��ϵ����
     * @author YuanPeng
     * @param id
     * @return	��ϵ����
     */
    public String getPedigreeRecordById(int id);
}
