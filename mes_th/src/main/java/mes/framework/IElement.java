package mes.framework;

/**
 * �����ӿ�
 * 
 * @author �Ź��� 2007-6-21
 */
interface IElement {
	public String getId();

	public String getName();

	public String getNameSpace();

	public String getDescr();

	void setDescr(String descr);

	void setName(String name);

	void setNameSpace(String ns);

	void setId(String id);

}
