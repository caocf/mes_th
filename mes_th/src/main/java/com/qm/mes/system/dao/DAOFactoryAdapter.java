package com.qm.mes.system.dao;

import com.qm.mes.framework.DataBaseType;

public final class DAOFactoryAdapter {
	public static final IDAO_Material getInstance(DataBaseType dataBaseType) {
		if (dataBaseType == null)
			return null;
		switch (dataBaseType) {
		case SQLSERVER:
			return new IDAO_MaterialForSqlserver();
		case ORACLE:
			return new IDAO_MaterialForOracle();
		}
		return null;
	}

	/**
	 * �������ݿ����ͷ��ؽӿڵĶ���<br>
	 * ����<br>
	 * 1��Ŀ������ӿ���ͬһ������<br>
	 * 2��Ŀ������=�ӿ���+ForSqlserver/ForOracle���������ݿ�����ѡ��ʹ�ã�
	 * @param dataBaseType ���ݿ����ͣ�Ŀǰ��Sqlserver��Oracle
	 * @param interfaceClass Ŀ��ӿ�����
	 * @return ���سɹ����ɵĶ����������쳣����null��
	 */
	@SuppressWarnings("unchecked")
	public static final Object getInstance(DataBaseType dataBaseType,
			Class interfaceClass) {
		//��û��ָ������Ҫ�Ľӿ����ͼ����ݿ�������ֱ�ӷ���null
		if (dataBaseType == null || interfaceClass == null)
			return null;
		String className = interfaceClass.getName();
		switch (dataBaseType) {
		case SQLSERVER:
			className += "ForSqlserver";
			break;
		case ORACLE:
			className += "ForOracle";
			break;
		}
		try {
			Object object = Class.forName(className).newInstance();
			if (interfaceClass.isInstance(object))
				return object;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
