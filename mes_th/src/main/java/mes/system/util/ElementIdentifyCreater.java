package mes.system.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ������������ڸ�������µĿ��õ�Ԫ�ر��IDֵ
 * @author �Ź��� 2008-3-4
 * @deprecated ��Ϊ����������Կ��ƣ����������ݿ��Լ��ķ�����������ֵ
 */
public final class ElementIdentifyCreater {
	public static int getNewElementIdentify(Connection con) throws SQLException {
		Statement st = con.createStatement();
		ResultSet set = st
				.executeQuery("select nvl(max(id),0)+1 as new_id from element");
		set.next();
		return set.getInt(1);
	}
}
