package mes.system.testcase;

import java.sql.Connection;

import junit.framework.Assert;
import mes.framework.DataBaseType;
import mes.system.dao.DAOFactoryAdapter;
import mes.system.dao.IDAO_Material;
import mes.system.dao.IDAO_TechnicsProcedure;
import mes.system.elements.IMaterial;
import mes.system.factory.FactoryAdapter;
import mes.system.factory.IMaterialFactory;
import mes.system.factory.IMaterialTypeFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestFactoryAdapter extends MyTestCase {

	@SuppressWarnings("unused")
	private Connection con;

	@Before
	public void setUp() throws Exception {
		con = super.getConnection();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDAOFactory() {
		Object obj = DAOFactoryAdapter.getInstance(DataBaseType.SQLSERVER,
				IDAO_Material.class);
		Assert.assertNotNull(obj);
		Assert.assertEquals(true, obj instanceof IDAO_Material);
		System.out.println((IDAO_Material) obj);

		obj = DAOFactoryAdapter.getInstance(DataBaseType.ORACLE,
				IDAO_Material.class);
		Assert.assertNotNull(obj);
		Assert.assertEquals(true, obj instanceof IDAO_Material);
		System.out.println(obj);

		obj = DAOFactoryAdapter.getInstance(DataBaseType.SQLSERVER,
				IDAO_TechnicsProcedure.class);
		Assert.assertNotNull(obj);
		Assert.assertEquals(true, obj instanceof IDAO_TechnicsProcedure);
		System.out.println(obj);
		obj = DAOFactoryAdapter.getInstance(DataBaseType.ORACLE,
				IDAO_TechnicsProcedure.class);
		Assert.assertNotNull(obj);
		Assert.assertEquals(true, obj instanceof IDAO_TechnicsProcedure);
		System.out.println(obj);
	}

	/**
	 * ���Լ��ع�����
	 */
	@Test
	public void testGetFactoryInstance() {
		// Ĭ��map��0
		Assert.assertEquals(FactoryAdapter.getFactoryMap().size(), 0);
		// ������ǿ���ʵ�����������
		Assert.assertNotNull(FactoryAdapter
				.getFactoryInstance(IMaterialFactory.class.getName()));
		// ���غ�map��Ԫ����Ϊ1
		Assert.assertEquals(FactoryAdapter.getFactoryMap().size(), 1);
		// ������ǲ��ܱ�ʵ����������ģ�Ӧ����nullֵ
		Assert.assertNull(FactoryAdapter.getFactoryInstance(IMaterial.class
				.getName()));
		// ���ڼ���ʧ������map��Ԫ������Ϊ1
		Assert.assertEquals(FactoryAdapter.getFactoryMap().size(), 1);
		// ������ܼ��ز���ʵ����
		Assert.assertNotNull(FactoryAdapter
				.getFactoryInstance(IMaterialTypeFactory.class.getName()));
		// ���سɹ���map��Ԫ������1
		Assert.assertEquals(FactoryAdapter.getFactoryMap().size(), 2);
		// ����һ���Ѿ�������map�е�����
		Assert.assertNotNull(FactoryAdapter
				.getFactoryInstance(IMaterialFactory.class.getName()));
		// ����û���µ�Ԫ�ؽ�����map��Ԫ����������
		Assert.assertEquals(FactoryAdapter.getFactoryMap().size(), 2);
	}

}
