package com.qm.mes.framework;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Map;

import com.qm.mes.framework.dao.DAOFactory_Core;
import com.qm.mes.framework.dao.IDAO_Core;
import com.qm.th.helper.Conn_MES;

/**
 * ���񹤳�
 * 
 * @author �Ź��� 2007-6-21
 */
public final class ServiceFactory {
	/**
	 * �����
	 */
	private static Map<String, IService> servicemap = new Hashtable<String, IService>();

	/**
	 * ����ָ��serviceid�ĵķ���
	 * 
	 * @param serviceid
	 *            ����ı�ʶ
	 * @return ��û�ж�Ӧ�����򷵻�null
	 */
	public static IService getInstance(String serviceid) {
		return getInstance(serviceid, true);

	}

	private static IService getInstance(String serviceid, boolean b) {
		if (b) {
			IService s = servicemap.get(serviceid);
			if (s != null)
				return s;
		}

		Connection con = null;
		try {
			con =  (new Conn_MES()).getConn();
			IDAO_Core daoservice = DAOFactory_Core.getInstance(DataBaseType
					.getDataBaseType(con));
			return loadService(
					daoservice.getSQL_QueryServiceForServiceid(Integer
							.valueOf(serviceid)), con);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return null;
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException sqle) {
			}
		}
	}

	private static IService loadService(String sql, Connection con)
			throws SQLException {
		Statement st = con.createStatement();
		ResultSet set = st.executeQuery(sql);
		IService service = null;
		while (set.next()) {
			String classname = set.getString("CCLASSNAME");
			service = loadObject(classname);
			if (service == null)
				continue;
			service.setClassName(classname);
			service.setDescr(set.getString("CDESCRIPTION"));
			service.setName(set.getString("CSERVERNAME"));
			service.setNameSpace(set.getString("NNAMESPACEID"));
			service.setId(set.getString("NSERVERID"));
			servicemap.put(service.getId(), service);
		}
		return service;

	}

	/**
	 * Ӧ���ṩ�����Ӽ����������ݿ��еķ���
	 * 
	 * @param con
	 *            ���ݿ�����
	 * @throws SQLException
	 *             �˷������ܻ��ܳ�SQLException�쳣
	 */
	static void loadAllService(Connection con) throws SQLException {
		con =  (new Conn_MES()).getConn();
		IDAO_Core daoservice = DAOFactory_Core.getInstance(DataBaseType
				.getDataBaseType(con));
		loadService(daoservice.getSQL_QueryAllServices(null,null), con);
		
	}

	/**
	 * ������·���е��࣬�������ʵ��
	 * 
	 * @param cname
	 *            ����������
	 * @return ��Ķ������������ͨ��һ���޲εĹ��캯������ʵ������
	 */
	private static IService loadObject(String cname) {
		try {
			return (IService) Class.forName(cname).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
