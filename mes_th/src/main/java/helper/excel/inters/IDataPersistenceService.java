package helper.excel.inters;

import java.util.List;

import helper.excel.entities.FATHBean;

/**
 * ���ݳ־û��ӿ�
 * 
 * @author Ajaxfan
 */
public interface IDataPersistenceService {
	/**
	 * ���ݴ洢
	 * 
	 * @param list
	 * @return
	 */
	public int storeData(List<FATHBean> list) throws Exception;
}
