package mes.system.testcase;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import junit.framework.Assert;
import mes.system.elements.IMaterialType;
import mes.system.factory.FactoryAdapter;
import mes.system.factory.IMaterialTypeFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestIMaterialTypeFactory extends MyTestCase {

	private IMaterialTypeFactory factory;

	private Connection con;

	@Before
	public void setUp() throws Exception {
		factory = (IMaterialTypeFactory) FactoryAdapter
				.getFactoryInstance(IMaterialTypeFactory.class.getName());
		con = super.getConnection();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateElement() {

	}

	@Test
	public void testSaveIMaterialTypeConnection() {
		IMaterialType newType = factory.createElement();
		newType.setName("������������3");
		newType.setDescription("������������3��������Ϣ");
		newType.setParentId(-1);
		newType.setUpdateDateTime(new Date());
		newType.setUpdateUserId(-11);
		newType.setVersion(0);
		try {
			factory.save(newType, con);
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.fail("�������ʱ������쳣");
		}
	}

	@Test
	public void testUpdateElementIMaterialTypeConnection() {
		IMaterialType type;
		try {
			type = factory.queryElement("������������3", con);
			System.out.println(type);
			System.out.println();
			type.setDescription(type.getDescription() + "�Ѹ���");
			type.setParentId(-3);
			type.setDescription("asdfjkasldfj");
			System.out.println(type);
//			factory.updateElement(type, con);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
