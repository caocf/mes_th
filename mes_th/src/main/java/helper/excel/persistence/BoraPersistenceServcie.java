package helper.excel.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import common.Conn_MES;
import helper.excel.entities.FATHBean;
import helper.excel.inters.IDataPersistenceService;

/**
 * Bora����
 * 
 * @author Ajaxfan
 */
public final class BoraPersistenceServcie extends AbstractPersistence implements IDataPersistenceService<FATHBean> {
	/**
	 * 
	 */
	@Override
	public int storeData(List<FATHBean> list) throws Exception {
		int count = 0;

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = new Conn_MES().getConn();
			conn.setAutoCommit(false);
			
			stmt = conn.prepareStatement(toSql());
			
			// ���ݱ������
			for (FATHBean bean : list) {
				stmt.setString(1, bean.getChassi());
				stmt.setObject(2, toDateTime(bean.getCp5adate(), bean.getCp5atime()));
				stmt.setString(3, getSeq(bean.getSeq()));
				stmt.setString(4, bean.getKnr().replaceAll("-", ""));

				stmt.addBatch();
			}
			count = stmt.executeBatch().length;
			conn.commit();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} finally {
					stmt = null;
				}
			}
			if (conn != null) {
				try {
					conn.setAutoCommit(true);
					conn.close();
				} finally {
					conn = null;
				}
			}
		}
		return count;
	}

	@Override
	protected String getPrefix() {
		return "02";
	}
}
