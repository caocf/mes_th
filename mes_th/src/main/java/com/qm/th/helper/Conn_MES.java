package com.qm.th.helper;

import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * ���ݿ����ӳ�
 * 
 * @author Administrator
 */
public class Conn_MES {
	/** Tomcat JNDI���������� */
	private static final String JNDI_EXPRESS = "java:comp/env/mes_th";

	/** ����Դ���� */
	private static DataSource DS;

	/**
	 * ͨ��JNDIʵ��������Դ����
	 */
	static {
		try {
			DS = (DataSource) new InitialContext().lookup(JNDI_EXPRESS);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �������ݿ�����
	 * 
	 * @return
	 */
	public java.sql.Connection getConn() {
		try {
			return DS.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}