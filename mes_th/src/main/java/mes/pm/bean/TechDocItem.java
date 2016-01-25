package mes.pm.bean;

import java.awt.Image;



/**
 * ʵ��Bean���ڹ���˵�����������Ϣ
 * 
 * @author YuanPeng
 *
 */
public class TechDocItem {
	/**
	 * ���
	 */
	private int id; 
	/**
	 * ���ղ���˵�����
	 */
	private int TechDocId;
	/**
	 * ������ղ��������
	 */
	private int code;
	/**
	 * ������Ԫ��
	 */
	private int produceUnitId; 
	/**
	 * ������Ԫ��
	 */
	private String prodUnitName;
	/**
	 * ��ص�Ԫ�ľ��幤�ղ�������
	 */
	private String Content; 
	/**
	 * ���ղ������ļ�
	 */
	private TechItemFile techItemFile = null;
	/**
	 * �ļ�·��
	 */
	private Image itemImage = null;
	
	private String filePathName = null;
	
	public String getFilePathName() {
		return filePathName;
	}
	public void setFilePathName(String filePathName) {
		this.filePathName = filePathName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProduceUnitId() {
		return produceUnitId;
	}
	public void setProduceUnitId(int produceUnitId) {
		this.produceUnitId = produceUnitId;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public int getTechDocId() {
		return TechDocId;
	}
	public void setTechDocId(int TechDocId) {
		this.TechDocId = TechDocId;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getProdUnitName() {
		return prodUnitName;
	}
	public void setProdUnitName(String prodUnitName) {
		this.prodUnitName = prodUnitName;
	}
	public TechItemFile getTechItemFile() {
		return techItemFile;
	}
	public void setTechItemFile(TechItemFile techItemFile) {
		this.techItemFile = techItemFile;
	}
	public Image getItemImage() {
		return itemImage;
	}
	public void setItemImage(Image itemImage) {
		this.itemImage = itemImage;
	}
}
