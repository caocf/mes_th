package helper.excel.inters;

import java.util.List;

import helper.excel.entities.FATHBean;

/**
 * ���ݳ־û��ӿ�
 * 
 * @author Ajaxfan
 */
public interface IDataPersistenceService<T> {
	/**
	 * ���ݴ洢
	 * 
	 * @param list
	 * @return
	 */
	public int storeData(List<T> list) throws Exception;
}
