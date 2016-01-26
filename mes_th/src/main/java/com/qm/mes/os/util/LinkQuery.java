package com.qm.mes.os.util;

import java.util.*;
import java.sql.*;

/*
 * л���� 2009-5-5
 * 
 * */
public class LinkQuery {

	/**
	 * sql������ɺ���
	 * 
	 * @param info
	 *            ������������ֵ�����<font color="red"><B>","</B></font>�ָ<font color="red">�������ֵ֮��Ӧ��<B>���롱</B>����</font>
	 * @param colist
	 *            ������˵��ֶ���
	 * @param sql
	 *            ԭsql���
	 * @param con
	 * @return ����Ӧ����ģ������������sql���
	 * @throws SQLException
	 */
	public StringBuffer linkquery(String info, ArrayList<String> colist,
			String sql, Connection con) throws SQLException {
		//��ѯԴsql������ṹʹ�õĶ���
		Statement stmt = null;
		//��ϸ���ֵ�ù���������������ʹ�õĶ���
		StringBuffer where = new StringBuffer("1=1");
		try {
			stmt = con.createStatement();
			// ֵ����
			String[] vals = info.split(",");
			
			for(int i=0;i<vals.length;i++)
				vals[i] = vals[i].trim();
			
			// �õ�ԭ����������
			ResultSet rs = stmt.executeQuery("select * from (" + sql + ") a where 1=2");
			ResultSetMetaData meta = rs.getMetaData();
			String colname = null;
			// ����ÿһ������ֵ���������ֵ֮��Ӧ�á��롱����
			for (String v : vals) {
				// ����wh��Ӧ��or�����������������Ӧ������ֵ
				String wh = "( 1=2 ";
				for (int c = 1; c <= meta.getColumnCount(); c++) {
					// ����ֶ���
					colname = meta.getColumnName(c);
					// ���ֶ���������������㣬��ִ����һ��ѭ��
					if (colist.contains(colname.toLowerCase()) == false)
						continue;
					// ��Ϲ���������whֵ���磺(1=2 or username like '%zz%' or password
					// like '%zz%'
					wh = wh + " or " + colname + " like '%" + v + "%' ";
				}
				// ��������ţ�whֵ���磺(1=2 or username like '%zz%' or password like
				// '%zz%' )
				wh = wh + ")";
				// ����Ϻõĵ�ֵ�����������뵽�������������
				// whereֵ���磺1=1 and ( 1=2 or INT_ID like '%����%')and( 1=2 or
				// INT_ID like '%17%')
				where.append(" and " + wh);
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return where;
	}
}
