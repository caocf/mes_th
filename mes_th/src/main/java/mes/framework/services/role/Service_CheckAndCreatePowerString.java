package mes.framework.services.role;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import mes.framework.AdapterService;
import mes.framework.DataBaseType;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.IProcess;
import mes.framework.ProcessFactory;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import mes.framework.dao.DAOFactory_UserManager;
import mes.framework.dao.IDAO_UserManager;

public class Service_CheckAndCreatePowerString extends AdapterService {
	private Connection con = null;

	private String rank = null;

	private String source_ps = null;

	private final Log log = LogFactory
			.getLog(Service_CheckAndCreatePowerString.class);

	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
		rank = message.getUserParameterValue("rank");
		source_ps = message.getUserParameterValue("functionids").trim(); // .trim()�Ǻ�ӵ�
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		String debug_CACPS = "rank = " + rank + "\n" + "source_ps = "
				+ source_ps + "\n";
		log.debug(processInfo
				+ ",Service_CheckAndCreatePowerString��,���Ȩ�޼�Ȩ�޹��� "
				+ debug_CACPS);

		if (rank == null || con == null || source_ps == null)
			return false;
		return true;
	}

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		// ��ȡ����������
		IProcess process = ProcessFactory.getInstance(processid);
		String processInfo = process.getNameSpace() + "." + process.getName();
		String[] fids = source_ps.split(":");
		log.debug(processInfo + ",���ضϵ�Ȩ�޹��ܴ�: fids = " + fids);
		IDAO_UserManager daor = DAOFactory_UserManager.getInstance(DataBaseType
				.getDataBaseType(con));

		Map<String, String> map = getVFunctionID(daor);
		StringBuffer powerstring = new StringBuffer();
		if (rank.equals("1"))
			// ��Ŀ���ɫΪӦ�ü���Ҫ��֤���ܴ���û�п������Ĺ���
			for (String fid : fids)
				if (map.get(fid) != null && !map.get(fid).equals(rank)) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.PARAMETERLOST, "�����빦�ܲ�ƥ�䣡"
									+ fid + "���ܼ���Ϊ������,���������룡", this.getId(),
							processid, new java.util.Date(), null));
					log.error(processInfo + ",�û����빦���뼶��ƥ��" + fid + "���ܼ���Ϊ������");
					return ExecuteResult.fail;
				}
		ArrayList<String> flist = new ArrayList<String>();
		for (String fid : fids) {
			flist.add(fid);
		}
		for (String fid : map.keySet()) {
			if (flist.contains(fid))
				powerstring.append("1");
			else
				powerstring.append("0");
		}

		message.setOutputParameter("powerstring", powerstring.toString());
		return ExecuteResult.sucess;
	}

	private Map<String, String> getVFunctionID(IDAO_UserManager dao)
			throws SQLException {
		Map<String, String> map = new TreeMap<String, String>(
				new Comparator<String>() {
					public int compare(String o1, String o2) {
						if (o1.equals("")) { // ��if�����Ǻ�ӵ�
							log.error("o1Ϊ��ʱ�Ա�û������");
							return -1;
						}
						try {
							int v1 = Integer.valueOf(o1);
							int v2 = Integer.valueOf(o2);
							if (v1 == v2)
								return 0;
							if (v1 > v2)
								return 1;
							else
								return -1;
						} catch (Exception e) {
							log.fatal("�Ա�����ʱ�����쳣��" + e.toString());
							e.printStackTrace();
						}
						return 1;
					}
				});
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		log.debug("��ѯϵͳ�еĴ��ڹ��ܣ����չ��ܺ������sql���: sql = "
				+ dao.getSQL_QueryAllFunction());
		ResultSet rs = stmt.executeQuery(dao.getSQL_QueryAllFunction());
		while (rs.next()) {
			map.put(rs.getString("nfunctionid"), rs.getString("crank"));
		}
		rs = stmt.executeQuery(dao.getSQL_QueryPowerStringLength());
		int n = 0;
		if (rs.next())
			n = rs.getInt(1);
		for (int i = 1; i <= n; i++)
			if (map.get(String.valueOf(i)) == null)
				map.put(String.valueOf(i), "0"); // �����ǵ�λ����չ���Ժ󣬶������λ����null��������Ҫ��0
		stmt.close();
		return map;
	}

	@Override
	public void relesase() throws SQLException {
		con = null;
		rank = null;
		source_ps = null;
	}

}
