package mes.pm.bean;

import java.util.List;
import mes.pm.bean.ProLineItem;
/**
 * ������Ԫ��������
 * 
 * @author YuanPeng
 *
 */
public class ProduceUnitLine {
	/**
	 * ���
	 */
	private int id;
	/**
	 * ����
	 */
	private String name;
	/**
	 * ����
	 */
	private String Description;
	/**
	 * װ���Ӽ�����
	 */
	private List<ProLineItem> items; 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public List<ProLineItem> getItems() {
		return items;
	}
	public void setItems(List<ProLineItem> items) {
		this.items = items;
	}
}
