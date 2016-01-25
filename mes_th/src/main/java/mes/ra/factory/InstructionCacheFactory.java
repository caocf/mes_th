package mes.ra.factory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.List;
import java.util.ArrayList;
import mes.system.dao.DAOFactoryAdapter;
import mes.framework.DataBaseType;
import java.text.SimpleDateFormat;

import mes.ra.bean.Instruction;
import mes.ra.dao.DAO_Instruction_cache;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author YuanPeng
 */
public class InstructionCacheFactory {
	private final Log log = LogFactory.getLog(InstructionCacheFactory.class);
	/**
	 * ����ָ��
	 * 
	 * @param instruction
	 *            ָ�����
	 * @param con
	 *            ���Ӷ���
	 * @throws java.sql.SQLException
	 */
	public void saveInstructionCache(Instruction instruction, Connection con)
			throws SQLException {
		DAO_Instruction_cache dao_Instruction_cache = (DAO_Instruction_cache) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_Instruction_cache.class);
		Statement stmt = con.createStatement();
		log.debug("����ָ��"+dao_Instruction_cache.saveInstructionCache(instruction));
		stmt.execute(dao_Instruction_cache.saveInstructionCache(instruction));
		if (stmt != null) {
			stmt.close();
		}
	}

	/**
	 * ͨ��ID��ѯָ��
	 * 
	 * @param id
	 *            ָ�����к�
	 * @param con
	 *            ���Ӷ���
	 * @return ͨ��ID��ѯ����ָ�����
	 * @throws java.sql.SQLException
	 */
	public Instruction getInstructionCacheById(int id, Connection con)
			throws SQLException, ParseException {
		Instruction instruction = new Instruction();
		DAO_Instruction_cache dao_Instruction_cache = (DAO_Instruction_cache) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_Instruction_cache.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ��ID��ѯָ��"+dao_Instruction_cache.getInstructionCacheById(id));
		ResultSet rs = stmt.executeQuery(dao_Instruction_cache
				.getInstructionCacheById(id));
		if (rs.next()) {
			instruction.setId(rs.getInt("INT_ID"));
			instruction.setProdunitid(rs.getInt("INT_PRODUNITID"));
			instruction.setProduceDate(rs.getDate("DAT_PRODUCEDATE"));
			instruction.setVersionCode(rs.getString("STR_VERSIONCODE"));
			instruction.setInstructionOrder(rs.getInt("INT_INSTRUCTORDER"));
			instruction.setPlanDate(rs.getString("DAT_PLANDATE") == null ? null
					: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs
							.getString("DAT_PLANDATE")));
			instruction.setPlanOrder(rs.getInt("INT_PLANORDER"));
			instruction.setProduceType(rs.getString("STR_PRODUCETYPE"));
			instruction.setProduceName(rs.getString("STR_PRODUCENAME"));
			instruction.setProduceMarker(rs.getString("STR_PRODUCEMARKER"));
			instruction.setWorkOrder(rs.getString("STR_WORKORDER"));
			instruction.setWorkTeam(rs.getString("STR_WORKTEAM"));
			instruction.setPlanBegin(rs.getString("TIM_PLANBEGIN") == null ? null
					: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs
							.getString("TIM_PLANBEGIN")));
			instruction.setPlanFinish(rs.getString("TIM_PLANFINISH") == null ? null
					: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs
							.getString("TIM_PLANFINISH")));
			instruction.setCount(rs.getInt("INT_COUNT"));
			instruction.setInstructStateID(rs.getInt("INT_INSTRUCTSTATEID"));
			instruction.setIssuance(rs.getInt("INT_ISSUANCE"));
			instruction.setStaError(rs.getInt("INT_STAERROR"));

		}
		if (stmt != null)
			stmt.close();
		return instruction;

	}

	/**
	 * ͨ��������Ԫ�š��������ڡ�ָ��˳���ɾ����ָ��
	 * 
	 * @param Int_produnitid
	 *            ָ�����к�
	 * @param str_date
	 *  		��������
	 * @param order
	 * 		ָ��˳���
	 * @param con
	 *            ���Ӷ���
	 * @throws java.sql.SQLException
	 */
	public void delInstructionCacheByProduceUnitDateWorkorderOrder(int Int_produnitid, String str_date,String workOrder,int order, Connection con)
			throws SQLException {
		DAO_Instruction_cache dao_Instruction_cache = (DAO_Instruction_cache) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_Instruction_cache.class);
		Statement stmt = con.createStatement();
		log.debug("ͨ��������Ԫ�š��������ڡ���Ρ�ָ��˳���ɾ����ָ��"+dao_Instruction_cache.delInstructionCacheByProduceUnitDateWorkorderOrder(Int_produnitid,str_date,workOrder,order));
		stmt.execute(dao_Instruction_cache.delInstructionCacheByProduceUnitDateWorkorderOrder(Int_produnitid,str_date,workOrder,order));
		if (stmt != null) {
			stmt.close();
		}
	}

	/**
	 * ����ָ�����
	 * 
	 * @param instruction
	 *            ָ�����
	 * @param con
	 *            ���Ӷ���
	 * @throws java.sql.SQLException
	 */
	public void updateInstructionCache(Instruction instruction, Connection con)
			throws SQLException {
		DAO_Instruction_cache dao_Instruction_cache = (DAO_Instruction_cache) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_Instruction_cache.class);
		Statement stmt = con.createStatement();
		log.debug("����ָ�����"+dao_Instruction_cache.updateInstructionCache(instruction));
		stmt.execute(dao_Instruction_cache.updateInstructionCache(instruction));
		if (stmt != null) {
			stmt.close();
		}
	}

	/**
	 * ��ѯ��¼������
	 * 
	 * @param con
	 *            ���Ӷ���
	 * @return ָ���¼������
	 * @throws java.sql.SQLException
	 */
	public int getInstructionCacheCount(Connection con) throws SQLException {
		int count;
		DAO_Instruction_cache dao_Instruction_cache = (DAO_Instruction_cache) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_Instruction_cache.class);
		Statement stmt = con.createStatement();
		log.debug("��ѯ��¼������"+dao_Instruction_cache.getInstructionCacheCount());
		ResultSet rs = stmt.executeQuery(dao_Instruction_cache
				.getInstructionCacheCount());
		rs.next();
		count = rs.getInt(1);
		if (stmt != null) {
			stmt.close();
		}
		return count;
	}

	/**
	 * ��ѯ����ָ���¼��
	 * 
	 * @param con
	 *            ���Ӷ���
	 * @return ����ָ����󼯺�
	 * @throws java.sql.SQLException
	 */
	public List<Instruction> getAllInstructionCache(Connection con)
			throws SQLException, ParseException {
		Instruction instruction = null;
		List<Instruction> list = new ArrayList<Instruction>();
		DAO_Instruction_cache dao_Instruction_cache = (DAO_Instruction_cache) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_Instruction_cache.class);
		Statement stmt = con.createStatement();
		log.debug("��ѯ����ָ���¼��"+dao_Instruction_cache.getInstructionCacheCount());
		ResultSet rs = stmt.executeQuery(dao_Instruction_cache
				.getAllInstructionCache());
		while (rs.next()) {
			instruction.setId(rs.getInt("INT_ID"));
			instruction.setProdunitid(rs.getInt("INT_PRODUNITID"));
			instruction.setProduceDate(rs.getDate("DAT_PRODUCEDATE"));
			instruction.setVersionCode(rs.getString("STR_VERSIONCODE"));
			instruction.setInstructionOrder(rs.getInt("INT_INSTRUCTORDER"));
			instruction.setPlanDate(rs.getString("DAT_PLANDATE") == null ? null
					: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs
							.getString("DAT_PLANDATE")));
			instruction.setPlanOrder(rs.getInt("INT_PLANORDER"));
			instruction.setProduceType(rs.getString("STR_PRODUCETYPE"));
			instruction.setProduceName(rs.getString("STR_PRODUCENAME"));
			instruction.setProduceMarker(rs.getString("STR_PRODUCEMARKER"));
			instruction.setWorkOrder(rs.getString("STR_WORKORDER"));
			instruction.setWorkTeam(rs.getString("STR_WORKTEAM"));
			instruction.setPlanBegin(rs.getString("TIM_PLANBEGIN") == null ? null
					: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs
							.getString("TIM_PLANBEGIN")));
			instruction.setPlanFinish(rs.getString("TIM_PLANFINISH") == null ? null
					: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs
							.getString("TIM_PLANFINISH")));
			instruction.setCount(rs.getInt("INT_COUNT"));
			instruction.setInstructStateID(rs.getInt("INT_INSTRUCTSTATEID"));
			instruction.setIssuance(rs.getInt("INT_ISSUANCE"));
			instruction.setStaError(rs.getInt("INT_STAERROR"));

			list.add(instruction);
		}
		if (stmt != null) {
			stmt.close();
		}
		return list;
	}

	/**
	 * ���ָ��˳����������Ƿ�Ϊ��һ��
	 * 
	 * @param order
	 *            ָ��˳���
	 * @param con
	 *            ���Ӷ���
	 * @return boolean ˳����Ƿ����ڵ�һ��
	 * @throws java.sql.SQLException
	 * 
	 */
	public boolean checkFirst(int Int_produnitid, String str_date,String workOrder, int order,
			Connection con) throws SQLException {
		DAO_Instruction_cache dao_Instruction_cache = (DAO_Instruction_cache) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_Instruction_cache.class);
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		log.debug("���ָ��˳����������Ƿ�Ϊ��һ��"+dao_Instruction_cache.getInstructionCacheByProduceUnitProduceDateOrder(Int_produnitid,
						str_date,workOrder));
		ResultSet rs = stmt.executeQuery(dao_Instruction_cache
				.getInstructionCacheByProduceUnitProduceDateOrder(Int_produnitid,
						str_date,workOrder));
		if (rs.next() && rs.getInt(5) == order)
			return true;
		if (stmt != null) {
			stmt.close();
		}
		return false;
	}

	/**
	 * ���ָ��˳����������Ƿ�Ϊ���һ��
	 * 
	 * @param order
	 *            ָ��˳���
	 * @param con
	 *            ���Ӷ���
	 * @return boolean ˳����Ƿ��������һ��
	 * @throws java.sql.SQLException
	 * 
	 */
	public boolean checkLast(int Int_produnitid, String str_date,String workOrder, int order,
			Connection con) throws SQLException {
		DAO_Instruction_cache dao_Instruction_cache = (DAO_Instruction_cache) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_Instruction_cache.class);
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		log.debug("��ѯ����ָ���¼��"+dao_Instruction_cache.getInstructionCacheByProduceUnitProduceDateOrder(Int_produnitid,str_date,workOrder));
		ResultSet rs = stmt.executeQuery(dao_Instruction_cache
				.getInstructionCacheByProduceUnitProduceDateOrder(Int_produnitid,
						str_date,workOrder));
		while (rs.isLast() && rs.getInt(5) == order)
			return true;
		if (stmt != null) {
			stmt.close();
		}
		return false;
	}

	/**
	 * ��ѯ����ָ�������˳�������
	 * 
	 * @param con
	 *            ���Ӷ���
	 * @return ָ����󼯺�
	 * @throws java.sql.SQLException
	 */
	public List<Instruction> getAllInstructionCacheByOrder(Connection con)
			throws SQLException, ParseException {
		Instruction instruction = null;
		List<Instruction> list = new ArrayList<Instruction>();
		DAO_Instruction_cache dao_Instruction_cache = (DAO_Instruction_cache) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_Instruction_cache.class);
		Statement stmt = con.createStatement();
		log.debug("��ѯ����ָ�������˳�������"+dao_Instruction_cache.getAllInstructionCache());
		ResultSet rs = stmt.executeQuery(dao_Instruction_cache
				.getAllInstructionCache());
		while (rs.next()) {
			instruction.setId(rs.getInt("INT_ID"));
			instruction.setProdunitid(rs.getInt("INT_PRODUNITID"));
			instruction.setProduceDate(rs.getDate("DAT_PRODUCEDATE"));
			instruction.setVersionCode(rs.getString("STR_VERSIONCODE"));
			instruction.setInstructionOrder(rs.getInt("INT_INSTRUCTORDER"));
			instruction.setPlanDate(rs.getString("DAT_PLANDATE") == null ? null
					: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs
							.getString("DAT_PLANDATE")));
			instruction.setPlanOrder(rs.getInt("INT_PLANORDER"));
			instruction.setProduceType(rs.getString("STR_PRODUCETYPE"));
			instruction.setProduceName(rs.getString("STR_PRODUCENAME"));
			instruction.setProduceMarker(rs.getString("STR_PRODUCEMARKER"));
			instruction.setWorkOrder(rs.getString("STR_WORKORDER"));
			instruction.setWorkTeam(rs.getString("STR_WORKTEAM"));
			instruction.setPlanBegin(rs.getString("TIM_PLANBEGIN") == null ? null
					: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs
							.getString("TIM_PLANBEGIN")));
			instruction.setPlanFinish(rs.getString("TIM_PLANFINISH") == null ? null
					: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs
							.getString("TIM_PLANFINISH")));
			instruction.setCount(rs.getInt("INT_COUNT"));
			instruction.setInstructStateID(rs.getInt("INT_INSTRUCTSTATEID"));
			instruction.setIssuance(rs.getInt("INT_ISSUANCE"));
			instruction.setStaError(rs.getInt("INT_STAERROR"));

			list.add(instruction);
		}
		if (stmt != null) {
			stmt.close();
		}
		return list;
	}


	/**
	 * ��ѯ��Int_instructOrderС�Ķ���
	 * 
	 * @param Int_instructOrder
	 *            ָ��˳���
	 * @param con
	 *            ���Ӷ���
	 * @throws java.sql.SQLException
	 *             return Instruction ���ر�Int_instructOrderС�Ķ���
	 */
	public List<Instruction> OrderMinus(int Int_produnitid, String str_date,String workOrder,int Int_instructOrder, Connection con)
			throws SQLException, ParseException {
		List<Instruction> list = new ArrayList<Instruction>();
		DAO_Instruction_cache dao_Instruction = (DAO_Instruction_cache) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_Instruction_cache.class);
		Instruction instruction = new Instruction();
		Statement stmt = con.createStatement();
		log.debug("��ѯ��Int_instructOrderС�Ķ���"+dao_Instruction.getByOrderMinus(Int_produnitid,str_date,workOrder,Int_instructOrder));
		ResultSet rs = stmt.executeQuery(dao_Instruction
				.getByOrderMinus(Int_produnitid,str_date,workOrder,Int_instructOrder));
		if (rs.next()) {
			instruction.setId(rs.getInt("INT_ID"));
			instruction.setProdunitid(rs.getInt("INT_PRODUNITID"));
			instruction.setProduceDate(rs.getDate("DAT_PRODUCEDATE"));
			instruction.setVersionCode(rs.getString("STR_VERSIONCODE"));
			instruction.setInstructionOrder(rs.getInt("INT_INSTRUCTORDER"));
			instruction.setPlanDate(rs.getString("DAT_PLANDATE") == null ? null
					: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs
							.getString("DAT_PLANDATE")));
			instruction.setPlanOrder(rs.getInt("INT_PLANORDER"));
			instruction.setProduceType(rs.getString("STR_PRODUCETYPE"));
			instruction.setProduceName(rs.getString("STR_PRODUCENAME"));
			instruction.setProduceMarker(rs.getString("STR_PRODUCEMARKER"));
			instruction.setWorkOrder(rs.getString("STR_WORKORDER"));
			instruction.setWorkTeam(rs.getString("STR_WORKTEAM"));
			instruction.setPlanBegin(rs.getString("TIM_PLANBEGIN") == null ? null
					: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs
							.getString("TIM_PLANBEGIN")));
			instruction.setPlanFinish(rs.getString("TIM_PLANFINISH") == null ? null
					: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs
							.getString("TIM_PLANFINISH")));
			instruction.setCount(rs.getInt("INT_COUNT"));
			instruction.setInstructStateID(rs.getInt("INT_INSTRUCTSTATEID"));
			instruction.setIssuance(rs.getInt("INT_ISSUANCE"));
			instruction.setStaError(rs.getInt("INT_STAERROR"));

			list.add(instruction);
		}
		if (stmt != null) {
			stmt.close();
		}
		return list;
	}

	/**
	 * ��ѯ��Int_instructOrder��Ķ���
	 * 
	 * @param Int_instructOrder
	 *            ָ��˳���
	 * @param con
	 *            ���Ӷ���
	 * @throws java.sql.SQLException
	 *             return Instruction ���ر�Int_instructOrder��Ķ���
	 */
	public List<Instruction> OrderPlus(int Int_produnitid, String str_date,String workOrder,int Int_instructOrder, Connection con)
			throws SQLException, ParseException {
		List<Instruction> list = new ArrayList<Instruction>();
		DAO_Instruction_cache dao_Instruction = (DAO_Instruction_cache) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_Instruction_cache.class);
		Instruction instruction = new Instruction();
		Statement stmt = con.createStatement();
		log.debug("��ѯ��Int_instructOrder��Ķ���"+dao_Instruction
				.getByOrderPlus(Int_produnitid,str_date,workOrder,Int_instructOrder));
		ResultSet rs = stmt.executeQuery(dao_Instruction
				.getByOrderPlus(Int_produnitid,str_date,workOrder,Int_instructOrder));
		if (rs.next()) {
			instruction.setId(rs.getInt("INT_ID"));
			instruction.setProdunitid(rs.getInt("INT_PRODUNITID"));
			instruction.setProduceDate(rs.getDate("DAT_PRODUCEDATE"));
			instruction.setVersionCode(rs.getString("STR_VERSIONCODE"));
			instruction.setInstructionOrder(rs.getInt("INT_INSTRUCTORDER"));
			instruction.setPlanDate(rs.getString("DAT_PLANDATE") == null ? null
					: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs
							.getString("DAT_PLANDATE")));
			instruction.setPlanOrder(rs.getInt("INT_PLANORDER"));
			instruction.setProduceType(rs.getString("STR_PRODUCETYPE"));
			instruction.setProduceName(rs.getString("STR_PRODUCENAME"));
			instruction.setProduceMarker(rs.getString("STR_PRODUCEMARKER"));
			instruction.setWorkOrder(rs.getString("STR_WORKORDER"));
			instruction.setWorkTeam(rs.getString("STR_WORKTEAM"));
			instruction.setPlanBegin(rs.getString("TIM_PLANBEGIN") == null ? null
					: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs
							.getString("TIM_PLANBEGIN")));
			instruction.setPlanFinish(rs.getString("TIM_PLANFINISH") == null ? null
					: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs
							.getString("TIM_PLANFINISH")));
			instruction.setCount(rs.getInt("INT_COUNT"));
			instruction.setInstructStateID(rs.getInt("INT_INSTRUCTSTATEID"));
			instruction.setIssuance(rs.getInt("INT_ISSUANCE"));
			instruction.setStaError(rs.getInt("INT_STAERROR"));

			list.add(instruction);
		}
		if (stmt != null) {
			stmt.close();
		}
		return list;
	}

	/**
	 * ָ���
	 * Ԭ��
	 * @param Int_produnitid
	 *            ������Ԫ��
	 * @param str_date
	 * 			��������
	 * @param Int_instructOrder
	 * 			��ҵָ��˳���
	 * @param con
	 *            ���Ӷ���
	 * @throws java.sql.SQLException
	 */
	public void IssuanceByProduceUnitDateWorkorderOrder(int Int_produnitid, String str_date,String workOrder,
			int Int_instructOrder, Connection con) throws SQLException {
		DAO_Instruction_cache dao_Instruction_cache = (DAO_Instruction_cache) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_Instruction_cache.class);
		Statement stmt = con.createStatement();
		log.debug("��ѯ��Int_instructOrder��Ķ���"+dao_Instruction_cache
				.IssuanceByProduceUnitDateWorkorderOrder(Int_produnitid,str_date,workOrder,Int_instructOrder));
		stmt.execute(dao_Instruction_cache
				.IssuanceByProduceUnitDateWorkorderOrder(Int_produnitid,str_date,workOrder,Int_instructOrder));
		if (stmt != null) {
			stmt.close();
		}
	}

	/**
	 * ��������ʱ���������Ԫ�鿴��ص�ָ� л����
	 * 
	 * @param Str_produceUnit,String
	 *            str_date ������Ԫ ��������
	 * @return �������Ӧ��ָ��
	 */
	public List<Instruction> getInstructionCacheByProduceUnitProduceDateOrder(
			int Int_produnitid, String str_date, String workOrder,Connection con)
			throws SQLException, ParseException {
		List<Instruction> list = new ArrayList<Instruction>();
		DAO_Instruction_cache dao_Instruction_cache = (DAO_Instruction_cache) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_Instruction_cache.class);
		Statement stmt = con.createStatement();
		log.debug("��������ʱ���������Ԫ�鿴��ص�ָ��"+dao_Instruction_cache
				.getInstructionCacheByProduceUnitProduceDateOrder(Int_produnitid,
						str_date,workOrder));
		ResultSet rs = stmt.executeQuery(dao_Instruction_cache
				.getInstructionCacheByProduceUnitProduceDateOrder(Int_produnitid,
						str_date,workOrder));
		while (rs.next()) {
			Instruction instruction = new Instruction();
			instruction.setId(rs.getInt("INT_ID"));
			instruction.setProdunitid(rs.getInt("INT_PRODUNITID"));
			instruction.setProduceDate(rs.getString("DAT_PRODUCEDATE") == null ? null
					: (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs
							.getString("DAT_PRODUCEDATE"))));
			instruction.setVersionCode(rs.getString("STR_VERSIONCODE"));
			instruction.setInstructionOrder(rs.getInt("INT_INSTRUCTORDER"));
			instruction.setPlanDate(rs.getString("DAT_PLANDATE") == null ? null
					: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs
							.getString("DAT_PLANDATE")));
			instruction.setPlanOrder(rs.getInt("INT_PLANORDER"));
			instruction.setProduceType(rs.getString("STR_PRODUCETYPE"));
			instruction.setProduceName(rs.getString("STR_PRODUCENAME"));
			instruction.setProduceMarker(rs.getString("STR_PRODUCEMARKER"));
			instruction.setWorkOrder(rs.getString("STR_WORKORDER"));
			instruction.setWorkTeam(rs.getString("STR_WORKTEAM"));
			instruction.setPlanBegin(rs.getString("TIM_PLANBEGIN") == null ? null
					: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs
							.getString("TIM_PLANBEGIN")));
			instruction.setPlanFinish(rs.getString("TIM_PLANFINISH") == null ? null
					: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs
							.getString("TIM_PLANFINISH")));
			instruction.setCount(rs.getInt("INT_COUNT"));
			instruction.setInstructStateID(rs.getInt("INT_INSTRUCTSTATEID"));
			instruction.setIssuance(rs.getInt("INT_ISSUANCE"));
			instruction.setStaError(rs.getInt("INT_STAERROR"));
			list.add(instruction);
		}
		if (stmt != null) {
			stmt.close();
		}
		return list;
	}

	/**
	 * ɾ����ʱ������������
	 * 
	 * @param con
	 *            ���Ӷ���
	 * @throws java.sql.SQLException
	 */
	public void TruncateInstructionCache(Connection con) throws SQLException {
		DAO_Instruction_cache dao_Instruction_cache = (DAO_Instruction_cache) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_Instruction_cache.class);
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			log.debug("ɾ����ʱ������������"+dao_Instruction_cache.DeleteInstructionCache());
			stmt.execute(dao_Instruction_cache.DeleteInstructionCache());

		} catch (Exception e) {

		} finally {
			if (stmt != null)
				stmt.close();
		}
	}

	/**
	 * ͨ��������Ԫ�š��������ڲ�ѯ��¼����
	 * 
	 * @param ProduceUnitID
	 *            ������Ԫ��
	 * @param str_date
	 *            ��������
	 * @return ����ͨ��������Ԫ�š��������ڲ�ѯ��¼������
	 */
	public int getCountByProUnitDateOrder(int ProduceUnitID, String str_date,String workOrder,
			Connection con) {
		int count = 0;
		try {
			
			DAO_Instruction_cache dao_Instruction_cache = (DAO_Instruction_cache) DAOFactoryAdapter
					.getInstance(DataBaseType.getDataBaseType(con),
							DAO_Instruction_cache.class);
			Statement stmt = con.createStatement();
			log.debug("ͨ��������Ԫ�š��������ڲ�ѯ��¼����"+dao_Instruction_cache
					.getCountByProUnitDateOrder(ProduceUnitID, str_date,workOrder));
			ResultSet rs = stmt.executeQuery(dao_Instruction_cache
					.getCountByProUnitDateOrder(ProduceUnitID, str_date,workOrder));
			if (rs.next()) {
				count = rs.getInt(1);
			}
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * ɾ����ʱ���и�������Ԫ��������
	 * 
	 * @param ProduceUnitID
	 *            ������Ԫ��
	 * @param con
	 *            ���Ӷ���
	 * @throws java.sql.SQLException
	 */
	public void DeleteInstructionCacheByProdUnitIdproducedateWorkorder(int ProduceUnitID,String str_date,String workOrder,
			Connection con) throws SQLException {
		DAO_Instruction_cache dao_Instruction_cache = (DAO_Instruction_cache) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),
						DAO_Instruction_cache.class);
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			log.debug("ɾ����ʱ���и�������Ԫ�ð����������"+dao_Instruction_cache
					.DeleteInstructionCacheByProdUnitIdproducedateWorkorder(ProduceUnitID,str_date,workOrder));
			stmt.executeUpdate(dao_Instruction_cache.DeleteInstructionCacheByProdUnitIdproducedateWorkorder(ProduceUnitID,str_date,workOrder));
		} catch (Exception e) {
              e.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}
	}
	
	/**
     * ͨ��������Ԫ�š��������ڡ���Ρ�ָ��˳��Ų�ѯָ��
     * 
     * @param ProduceUnitID
     * 			������Ԫ��
     * @param str_date
     * 			��������
     * * @param wrokOrder
     * 			���
     * @param Int_instructOrder
     * 				ָ��˳���
     * @return ��ҵָ�����
     */
	public Instruction getInstructionByProdUnitDateOrder(int ProduceUnitID,String str_date,String workOrder,int Int_instructOrder,Connection con){
		Instruction instruction = new Instruction();
		try{	
			DAO_Instruction_cache dao_Instruction = (DAO_Instruction_cache) DAOFactoryAdapter
				.getInstance(DataBaseType.getDataBaseType(con),DAO_Instruction_cache.class);
			Statement stmt = con.createStatement();
			log.debug("ͨ��������Ԫ�š��������ڡ���Ρ�ָ��˳��Ų�ѯָ��"+dao_Instruction
					.getInstructionByProdUnitDateWorkorderOrder(ProduceUnitID,str_date,workOrder,Int_instructOrder));
			ResultSet rs = stmt.executeQuery(dao_Instruction
					.getInstructionByProdUnitDateWorkorderOrder(ProduceUnitID,str_date,workOrder,Int_instructOrder));
			if (rs.next()) {
				instruction.setId(rs.getInt("INT_ID"));
				instruction.setProdunitid(rs.getInt("INT_PRODUNITID"));
				instruction.setProduceDate(rs.getDate("DAT_PRODUCEDATE"));
				instruction.setVersionCode(rs.getString("STR_VERSIONCODE"));
				instruction.setInstructionOrder(rs.getInt("INT_INSTRUCTORDER"));
				instruction.setPlanDate(rs.getString("DAT_PLANDATE") == null ? null
						: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs
								.getString("DAT_PLANDATE")));
				instruction.setPlanOrder(rs.getInt("INT_PLANORDER"));
				instruction.setProduceType(rs.getString("STR_PRODUCETYPE"));
				instruction.setProduceName(rs.getString("STR_PRODUCENAME"));
				instruction.setProduceMarker(rs.getString("STR_PRODUCEMARKER"));
				instruction.setWorkOrder(rs.getString("STR_WORKORDER"));
				instruction.setWorkTeam(rs.getString("STR_WORKTEAM"));
				instruction.setPlanBegin(rs.getString("TIM_PLANBEGIN") == null ? null
						: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs
								.getString("TIM_PLANBEGIN")));
				instruction.setPlanFinish(rs.getString("TIM_PLANFINISH") == null ? null
						: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs
								.getString("TIM_PLANFINISH")));
				instruction.setCount(rs.getInt("INT_COUNT"));
				instruction.setInstructStateID(rs.getInt("INT_INSTRUCTSTATEID"));
				instruction.setIssuance(rs.getInt("INT_ISSUANCE"));
				instruction.setStaError(rs.getInt("INT_STAERROR"));
			}
			if (stmt != null) {
				stmt.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
			return instruction;
	}
	
		/**
	    * ���������ڡ�������Ԫ�����ڸ�ָ��˳��ŵ�ָ��˳��ż�1
	    * 
	    *  @param  ProduceUnitID
	    *  		������Ԫ��
	    *  @param  str_date
	    *  		�������ں�
	    *  @param  order
	    *  		ָ��˳���
	    * @return  ����sql
	    */
	public void MinusInstructionOrder(int ProduceUnitID,String str_date,String workOrder,int order,Connection con){
		try{
		DAO_Instruction_cache dao_Instruction_cache = (DAO_Instruction_cache)DAOFactoryAdapter.getInstance(
	               DataBaseType.getDataBaseType(con),DAO_Instruction_cache.class);
			Statement stmt = con.createStatement();
			log.debug("���������ڡ�������Ԫ����Ρ����ڸ�ָ��˳��ŵ�ָ��˳��ż�1"+dao_Instruction_cache.MinusInstructionOrder(ProduceUnitID,str_date,workOrder,order));
	       stmt.execute(dao_Instruction_cache.MinusInstructionOrder(ProduceUnitID,str_date,workOrder,order));
	       	if (stmt != null) {
				stmt.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
     * ������������Ԫ���������ڵ�����ָ��
     * 
     * @param Int_produnitid
     * @param str_date
     * @return
     */
    public void IssuanceAllByProduceUnitDateWorkorder(int Int_produnitid, String str_date,String workOrder,Connection con){
    	Statement stmt = null;
    	try{
	    	DAO_Instruction_cache dao_Instruction_cache = (DAO_Instruction_cache) DAOFactoryAdapter
			.getInstance(DataBaseType.getDataBaseType(con),
					DAO_Instruction_cache.class);
	    	stmt = con.createStatement();
	    	log.debug("������������Ԫ���������ڡ���ε�����ָ��"+dao_Instruction_cache
					.IssuanceAllByProduceUnitDateWorkorder(Int_produnitid,str_date,workOrder));
			stmt.execute(dao_Instruction_cache
					.IssuanceAllByProduceUnitDateWorkorder(Int_produnitid,str_date,workOrder));
			if (stmt != null) {
				stmt.close();
			}
    	}catch(Exception e){e.printStackTrace();}finally{
    		try{
    			if (stmt != null) stmt.close();
    		}catch(SQLException sqle){sqle.printStackTrace();}
    	}
    }
    
    /**
     * ����ָ������˳���
	   ����ָ��׷��
     * @param int_produnitid
     * @param str_date
     * @param wrokOrder
     * @param con
     * @return
     * @throws SQLException
     * @throws ParseException
     */

	   public int getInstructionMaxOrder(int int_produnitid,String str_date,String wrokOrder,Connection con)throws SQLException ,ParseException{
	      
	      
		   DAO_Instruction_cache dao_Instruction_cache = (DAO_Instruction_cache)DAOFactoryAdapter.getInstance(
	               DataBaseType.getDataBaseType(con),DAO_Instruction_cache.class);
	    
			Statement stmt = con.createStatement();
			log.debug("����ָ������˳���"+dao_Instruction_cache
					.getInstructionMaxOrder(int_produnitid,str_date,wrokOrder));
			ResultSet rs = stmt.executeQuery(dao_Instruction_cache
					.getInstructionMaxOrder(int_produnitid,str_date,wrokOrder));
			 int n=0;
			if(rs.next()){
				n=rs.getInt(1);
				
			}
			if (stmt != null) {
				stmt.close();
			}
				return n;
			
	   }
	   
	   /**
	     * ͨ��������Ԫ�š��������ڡ���Ʒ��ʶ��ѯ��¼����
	     *  Ԭ��
	     * @param ProduceUnitID
	     * 			������Ԫ��
	     * @param str_date
	     * 			��������
	     * @param marker
	     * 			��Ʒ��ʶ
	     * @return ����ͨ��������Ԫ�š��������ڡ���β�ѯ��¼������
	     */
	    public int getCountByProUnitDateWorkorderMarker(int ProduceUnitID, String str_date,String workOrder,String marker, Connection con){
	    	int count = 0;
	    	try {
	    	DAO_Instruction_cache dao_Instruction = (DAO_Instruction_cache)DAOFactoryAdapter.getInstance(
				DataBaseType.getDataBaseType(con),DAO_Instruction_cache.class);
			
	 		Statement stmt = con.createStatement();
	 		log.debug("ͨ��������Ԫ�š��������ڡ���Ρ���Ʒ��ʶ��ѯ��¼����"+dao_Instruction.getCountByProUnitDateWorkorderMarker(ProduceUnitID, str_date,workOrder,marker));
	 		ResultSet rs  = stmt.executeQuery(dao_Instruction.getCountByProUnitDateWorkorderMarker(ProduceUnitID, str_date,workOrder,marker));
	         if(rs.next()){
	             count = rs.getInt(1);
	         }
	         if (stmt != null) {
	  			stmt.close();
	  		}
	    	} catch (SQLException e) {
				e.printStackTrace();
			}
	         return count;
	    }
	    
	    /**
	     * ͨ��������Ԫ���������ڡ���Ʒ��ʶ��ѯָ�����
	     * 
	     * @param ProduceUnitID
	     * @param str_date
	     * @param marker
	     * @param con
	     * @return
	     */
	    public Instruction getInstructionByProUnitDateMarker(int ProduceUnitID,String str_date,String workOrder,String marker,Connection con){
			Instruction instruction = new Instruction();
			try{	
				DAO_Instruction_cache dao_Instruction = (DAO_Instruction_cache) DAOFactoryAdapter
					.getInstance(DataBaseType.getDataBaseType(con),DAO_Instruction_cache.class);
				Statement stmt = con.createStatement();
				log.debug("ͨ��������Ԫ���������ڡ���Ρ���Ʒ��ʶ��ѯָ�����"+dao_Instruction
						.getInstructionByProUnitDateWorkorderMarker(ProduceUnitID,str_date,workOrder,marker));
				ResultSet rs = stmt.executeQuery(dao_Instruction
						.getInstructionByProUnitDateWorkorderMarker(ProduceUnitID,str_date,workOrder,marker));
				if (rs.next()) {

					instruction.setId(rs.getInt("INT_ID"));
					instruction.setProdunitid(rs.getInt("INT_PRODUNITID"));
					instruction.setProduceDate(rs.getDate("DAT_PRODUCEDATE"));
					instruction.setVersionCode(rs.getString("STR_VERSIONCODE"));
					instruction.setInstructionOrder(rs.getInt("INT_INSTRUCTORDER"));
					instruction.setPlanDate(rs.getString("DAT_PLANDATE") == null ? null
							: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs
									.getString("DAT_PLANDATE")));
					instruction.setPlanOrder(rs.getInt("INT_PLANORDER"));
					instruction.setProduceType(rs.getString("STR_PRODUCETYPE"));
					instruction.setProduceName(rs.getString("STR_PRODUCENAME"));
					instruction.setProduceMarker(rs.getString("STR_PRODUCEMARKER"));
					instruction.setWorkOrder(rs.getString("STR_WORKORDER"));
					instruction.setWorkTeam(rs.getString("STR_WORKTEAM"));
					instruction.setPlanBegin(rs.getString("TIM_PLANBEGIN") == null ? null
							: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs
									.getString("TIM_PLANBEGIN")));
					instruction.setPlanFinish(rs.getString("TIM_PLANFINISH") == null ? null
							: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs
									.getString("TIM_PLANFINISH")));
					instruction.setCount(rs.getInt("INT_COUNT"));
					instruction.setInstructStateID(rs.getInt("INT_INSTRUCTSTATEID"));
					instruction.setIssuance(rs.getInt("INT_ISSUANCE"));
					instruction.setStaError(rs.getInt("INT_STAERROR"));
				}
				if (stmt != null) {
					stmt.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
				return instruction;
		}
	
	
	/**
	    * ����ָ������
	    * ���
	    * @param num,order
	    * @param con
	    *          ���Ӷ���
	    * @throws java.sql.SQLException
	    */
	   public void updateInstructionCacheNum(int num ,int order,Connection con,String str_date,int ProduceUnitID,String workOrder)	throws SQLException {
			DAO_Instruction_cache dao_Instruction_cache = (DAO_Instruction_cache)DAOFactoryAdapter.getInstance(
	               DataBaseType.getDataBaseType(con),DAO_Instruction_cache.class);
			Statement stmt = con.createStatement();
			log.debug("����ָ������"+dao_Instruction_cache.updateInstructionCacheNum(num,order,str_date,ProduceUnitID,workOrder));
	       stmt.execute(dao_Instruction_cache.updateInstructionCacheNum(num,order,str_date,ProduceUnitID,workOrder));
	       	if (stmt != null) {
				stmt.close();
			}
	   }
	   /**
	    * ����ɾ��ָ��
	    * ���
	    * @param order
	    * @param con
	    *          ���Ӷ���
	    * @throws java.sql.SQLException
	    */
	   public void deleteInstructionCacheByOrder(int order,Connection con,String str_date,int ProduceUnitID,String workOrder)throws SQLException {
			DAO_Instruction_cache dao_Instruction_cache = (DAO_Instruction_cache)DAOFactoryAdapter.getInstance(
	               DataBaseType.getDataBaseType(con),DAO_Instruction_cache.class);
			Statement stmt = con.createStatement();
			log.debug("����ɾ��ָ��"+dao_Instruction_cache.deleteInstructionCacheByOrder(order,str_date,ProduceUnitID,workOrder));
	       stmt.execute(dao_Instruction_cache.deleteInstructionCacheByOrder(order,str_date,ProduceUnitID,workOrder));
	    	if (stmt != null) {
				stmt.close();
			}
	   }
	   /**
	    * ����ָ��
	    * ���
	    * @param order
	    * @param con
	    *          ���Ӷ���
	    * @throws java.sql.SQLException
	    */
	   public void updateInstructionCacheOrder(int order,Connection con,String str_date,int ProduceUnitID,String workOrder)	throws SQLException {
			DAO_Instruction_cache dao_Instruction_cache = (DAO_Instruction_cache)DAOFactoryAdapter.getInstance(
	               DataBaseType.getDataBaseType(con),DAO_Instruction_cache.class);
			Statement stmt = con.createStatement();
			log.debug("����ָ��"+dao_Instruction_cache.updateInstructionCacheOrder(order,str_date,ProduceUnitID,workOrder));
	       stmt.execute(dao_Instruction_cache.updateInstructionCacheOrder(order,str_date,ProduceUnitID,workOrder));
	      
			if (stmt != null) {
				stmt.close();
			}
	   }
	   /**
	    * �������Ҫ���ָ��
	    * ���
	    *
	    *  @param  order,order1,count,str
	    * @return  ����sql
	    */
	   public void insertInstructionCache(int order1,String str,int count,int order,Connection con)	throws SQLException {
			DAO_Instruction_cache dao_Instruction_cache = (DAO_Instruction_cache)DAOFactoryAdapter.getInstance(
	               DataBaseType.getDataBaseType(con),DAO_Instruction_cache.class);
			Statement stmt = con.createStatement();
			log.debug("�������Ҫ���ָ��"+dao_Instruction_cache.insertInstructionCache(order1,str,count,order));
	       stmt.execute(dao_Instruction_cache.insertInstructionCache(order1,str,count,order));
	     
			if (stmt != null) {
				stmt.close();
			}
	   }
	   /***
        *  л�����ѯ�༭�ռ����������ϵ�ֵ
        */
       public Instruction getInstructionbymateriel(String materiel,int int_produnitid,Connection con){
			Instruction instruction = new Instruction();
			try{	
				DAO_Instruction_cache dao_Instruction = (DAO_Instruction_cache) DAOFactoryAdapter
					.getInstance(DataBaseType.getDataBaseType(con),DAO_Instruction_cache.class);
				Statement stmt = con.createStatement();
				log.debug("ͨ��������Ԫ���������ڡ���Ρ���Ʒ��ʶ��ѯָ�����"+dao_Instruction.getInstructionbymateriel(materiel,int_produnitid));
						
				ResultSet rs = stmt.executeQuery(dao_Instruction.getInstructionbymateriel(materiel,int_produnitid));
				if (rs.next()) {

					instruction.setId(rs.getInt("INT_ID"));
					instruction.setProdunitid(rs.getInt("INT_PRODUNITID"));
					instruction.setProduceDate(rs.getDate("DAT_PRODUCEDATE"));
					instruction.setVersionCode(rs.getString("STR_VERSIONCODE"));
					instruction.setInstructionOrder(rs.getInt("INT_INSTRUCTORDER"));
					instruction.setPlanDate(rs.getString("DAT_PLANDATE") == null ? null
							: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs
									.getString("DAT_PLANDATE")));
					instruction.setPlanOrder(rs.getInt("INT_PLANORDER"));
					instruction.setProduceType(rs.getString("STR_PRODUCETYPE"));
					instruction.setProduceName(rs.getString("STR_PRODUCENAME"));
					instruction.setProduceMarker(rs.getString("STR_PRODUCEMARKER"));
					instruction.setWorkOrder(rs.getString("STR_WORKORDER"));
					instruction.setWorkTeam(rs.getString("STR_WORKTEAM"));
					instruction.setPlanBegin(rs.getString("TIM_PLANBEGIN") == null ? null
							: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs
									.getString("TIM_PLANBEGIN")));
					instruction.setPlanFinish(rs.getString("TIM_PLANFINISH") == null ? null
							: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs
									.getString("TIM_PLANFINISH")));
					instruction.setCount(rs.getInt("INT_COUNT"));
					instruction.setInstructStateID(rs.getInt("INT_INSTRUCTSTATEID"));
					instruction.setIssuance(rs.getInt("INT_ISSUANCE"));
					instruction.setStaError(rs.getInt("INT_STAERROR"));
				}
				if (stmt != null) {
					stmt.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
				return instruction;
    	   
       }
}
