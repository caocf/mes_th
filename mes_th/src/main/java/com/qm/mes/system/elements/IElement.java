package com.qm.mes.system.elements;

import java.util.Date;

public interface IElement {

	public abstract int getId();

	/**
	 * ����Ԫ��id����Ψһ��ʾһ��Ԫ��<br>
	 * <center><font color=ff0000 ><b>ע���������һ��Ҫ���á�</b></font></center>
	 * 
	 * @param id
	 *            Ԫ��id
	 */
	public abstract void setId(int id);

	public abstract String getName();

	/**
	 * ����Ԫ��id����Ψһ��ʾһ��Ԫ��<br>
	 * <center><font color=ff0000 ><b>ע���������һ��Ҫ���á�</b></font></center>
	 * 
	 * @param name
	 *            Ԫ��name
	 */
	public abstract void setName(String name);

	public abstract String getDescription();

	public abstract void setDescription(String desc);

	public abstract boolean isDiscard();

	public abstract void setDiscard(boolean discard);

	public abstract void setUpdateDateTime(Date date);

	public abstract Date getUpdateDateTime();

	public abstract int getUpdateUserId();

	public abstract void setUpdateUserId(int id);

	int getVersion();

	void setVersion(int version);
}
