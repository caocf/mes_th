package mes.os.dao;
import mes.os.bean.*;
/**
 * 
 * @author л���� 2009-05-15
 *
 */

public interface DAO_Version {
	/**
	 * �õ����еİ汾��Ϣ  л����
	 */
	String getAllVersions();
	
	/**
	 * �����汾 л����
	 */
	String saveVersion(Version version);

	/**
	 * �õ��汾��Ϣͨ���汾id�� л����
	 */
	String getVersionbyid(int int_id);
	
	/**
	 * ɾ���汾��ָ���İ汾��Ϣ  л����
	 */
	String deleteVersionbyversioncode(String versioncode);
	
	/**
	 * ɾ���汾���¼ͨ���汾��int_id 
	 */
	String deleteversionbyid(int int_id);
	   
	/**
	 *�ж��Ƿ����ɾ���汾 ͨ���汾��id л����
	 */
	String checkdeleteversionbyid(int id );
	/**
	 * @author ���ݰ汾�Ÿİ汾��ע��Ϣ
	 *
	 */
	String upversiondescriptionbyvcode(String versioncode,String description);
	/**
	 *  л���� ���ݰ汾�ŵõ��ƻ���Ϣ
	 */
	public String getPlanbyversioncord(String versioncode);
}
