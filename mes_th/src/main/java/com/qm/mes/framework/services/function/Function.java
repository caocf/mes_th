package com.qm.mes.framework.services.function;
public class Function 
{
	//����id
	private String functionid;
	//������
	private String functionname;
	//�ڵ�����
	private String nodeType;
	//�ļ�URL
	private String url;
	//��ȫ���ʱ��
	private String safemark;
	//��
	private String level;
	//���ڵ�
	private String upfunctionid;
	//״̬
	private String state;
	//����
	private String rank;
	//��ע
	private String note;
	
	public Function(){
		
	}
	
	public String getFunctionID()
	{
		return this.functionid;
	}
	public String getFunctionName()
	{
		return this.functionname;
	}
	public String getNodeType()
	{
		return this.nodeType;
	}
	public String getURL()
	{
		return this.url;
	}
	public String getUpFunctionID()
	{
		return this.upfunctionid;
	}
	public String getSafeMark()
	{
		return this.safemark;
	}
	public String getLevel()
	{
		return this.level;
	}
	public String getState()
	{
		return this.state;
	}
	public String getNote()
	{
		return this.note;
	}
	public String getRank()
	{
		return this.rank;
	}
	//=======================================

	public void setFunctionID(String functionid)
	{
		this.functionid=functionid;
	}
	public void setFunctionName(String functionname)
	{
		this.functionname=functionname;
	}
	public void setNodeType(String nodetype)
	{
		this.nodeType=nodetype;
	}
	public void setURL(String url)
	{
		this.url=url;
	}
	public void setUpFunctionID(String upfunctionid)
	{
		this.upfunctionid=upfunctionid;
	}
	public void setLevel(String level)
	{
		this.level=level;
	}
	public void setState(String state)
	{
		this.state=state;
	}
	public void setSafeMark(String s)
	{
		this.safemark=s;
	}
	public void setRank(String s)
	{
		this.rank=s;
	}
	public void setNote(String s)
	{
		this.note=s;
	}
}