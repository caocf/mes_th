/**
 * 
 */
package com.qm.mes.beans;

/**
 * @author lida
 * 
 */
public class ProcessService {
	// ���̺�
	private String processid = null;

	// ���к�
	private String sid = null;

	// ������
	private String processname = null;

	// ������
	private String servername = null;

	public String getProcessid() {
		return processid;
	}

	public void setProcessid(String processid) {
		this.processid = processid;
	}

	public String getProcessname() {
		return processname;
	}

	public void setProcessname(String processname) {
		this.processname = processname;
	}

	public String getServername() {
		return servername;
	}

	public void setServername(String servername) {
		this.servername = servername;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}
}
