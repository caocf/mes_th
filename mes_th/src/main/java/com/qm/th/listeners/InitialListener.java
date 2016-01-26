package com.qm.th.listeners;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.qm.th.helpers.Conn_MES;


public class InitialListener implements ServletContextListener {
	/** �û���ʱĿ¼ */
	private String _user_tmp_dir = System.getProperty("java.io.tmpdir");

	/** �ļ����� */
	public static final String FILE_NAME = "mes.txt";

	/**
	 * ��������ʼ��
	 */
	public void contextInitialized(ServletContextEvent evt) {
		/**
		 * ÿ����ִ��һ�����ݲ���
		 */
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				checkData();
			}
		}, 10 * 1000, 60 * 1000);
	}

	/**
	 * ����Ƿ�����³���
	 */
	private synchronized void checkData() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = new Conn_MES().getConn();
			stmt = conn.prepareStatement(querySql());
			rs = stmt.executeQuery();

			if (rs.next()) {
				wirteFileToLocal();
				updateTbl();
			}
		} catch (Exception e) {

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					rs = null;
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					stmt = null;
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					conn = null;
				}
			}
		}
	}

	/**
	 * д��һ�������ļ��������Ĳ���ͨ���ļ����ж��Ƿ����³�
	 */
	private void wirteFileToLocal() {
		try {
			new File(_user_tmp_dir + File.separator + FILE_NAME).createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ���±�
	 */
	private void updateTbl() {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = new Conn_MES().getConn();
			stmt = conn.prepareStatement("INSERT INTO fit_broadcast_tbl (brodcastTime) VALUES (getDate())");

			stmt.executeUpdate();
		} catch (Exception e) {

		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					stmt = null;
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					conn = null;
				}
			}
		}
	}

	/**
	 * @return ��ѯ���
	 */
	private String querySql() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM fit_newcar");
		sb.append(" WHERE usedTime is not null and not exists (");
		sb.append(" SELECT brodcastTime FROM fit_broadcast_tbl");
		sb.append(" WHERE convert(varchar(20),usedTime, 120) <");
		sb.append(" CONVERT(varchar(20),brodcastTime, 120))");

		return sb.toString();
	}

	public void contextDestroyed(ServletContextEvent evt) {
	}
}
