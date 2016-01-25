package mes.util.tree;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class DataServer_UserManage {
	private Connection conn = null;
	private IDAO_UserManage dao = null;

	public DataServer_UserManage(Connection conn) throws Exception {
		this.conn = conn;
		dao = DAOFactory_UserManage.getInstance(conn);
	}

	// ����û����Ƿ����
	public boolean getExistUser(String username) throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			if (username == null || username.trim().equals(""))
				throw new Exception("ʵ��Ϊ�գ�");
			sql = dao.getSQL_ExistUser(username);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
			}
			if (count <= 0) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			System.out
					.println("DataServer_UserManage��getExistUser(String username)�����׳��쳣"
							+ e);
			throw e;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
	}

	public String getUserID(String username) throws Exception {
		String userid = "";
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			if (username == null || username.trim().equals(""))
				throw new Exception("ʵ��Ϊ�գ�");

			sql = dao.getSQL_userID(username);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				userid = rs.getString(1);
			}
			return userid;
		} catch (Exception e) {
			System.out
					.println("DataServer_UserManage��getUserID(String username)�����׳��쳣"
							+ e);
			throw e;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
	}

	// �����û�״̬ �Ƿ���ͣ��
	public String getUserState(String userid) throws Exception {
		String state = "";
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			if (userid == null || userid.trim().equals(""))
				throw new Exception("ʵ��Ϊ�գ�");

			sql = dao.getSQL_UserState(userid);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				state = rs.getString(1);
			}
			return state;
		} catch (Exception e) {
			System.out
					.println("DataServer_UserManage��getUserState(String userid)�����׳��쳣"
							+ e);
			throw e;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
	}

	// ����û���������(md5���ܺ������)�Ƿ���ȷ
	public boolean userProof(String username, String password) throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			if (username == null || username.trim().equals("")
					|| password == null || password.trim().equals(""))
				throw new Exception("ʵ��Ϊ�գ�");
			sql = dao.getSQL_UserProof(username, password);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
			}
			if (count <= 0) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			System.out
					.println("DataServer_UserManage��userProof(String username,String password)�����׳��쳣"
							+ e);
			throw e;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
	}

	// �����û�������ȡ���Ӧ����ʽ��
	public String getCssFile(String userid) throws Exception {
		String cssfile = "";
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			if (userid == null || userid.trim().equals(""))
				throw new Exception("ʵ��Ϊ�գ�");

			sql = dao.getSQL_CssFile(userid);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				cssfile = rs.getString(1);
			}
			return cssfile;
		} catch (Exception e) {
			System.out
					.println("DataServer_UserManage��getCssFile(String userid)�����׳��쳣"
							+ e);
			throw e;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
	}

	// �����û�������ȡ���ɫ��
	public String getRoleNo(String userid) throws Exception {
		String roleno = "";
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			if (userid == null || userid.trim().equals(""))
				throw new Exception("ʵ��Ϊ�գ�");

			sql = dao.getSQL_RoleNo(userid);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				roleno = rs.getString(1);
			}
			return roleno;
		} catch (Exception e) {
			System.out
					.println("DataServer_UserManage��getRoleNo(String userid)�����׳��쳣"
							+ e);
			throw e;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
	}

	// ���ݽ�ɫ�ţ���ȡ��ɫ�ļ���
	public String getRank(String roleno) throws Exception {
		String rank = "";
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			if (roleno == null || roleno.trim().equals(""))
				throw new Exception("ʵ��Ϊ�գ�");

			sql = dao.getSQL_Rank(roleno);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				rank = rs.getString(1);
			}
			return rank;
		} catch (Exception e) {
			System.out
					.println("DataServer_UserManage��getRank(String roleno)�����׳��쳣"
							+ e);
			throw e;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
	}

	// ��ȡϵͳ����ֵ
	public String getParameterValue(String key) throws Exception {
		String value = "";
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			if (key == null || key.trim().equals(""))
				throw new Exception("ʵ��Ϊ�գ�");

			sql = dao.getSQL_ParameterValue(key);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				value = rs.getString(1);
			}
			return value;
		} catch (Exception e) {
			System.out
					.println("DataServer_UserManage��getParameterValue(String key)�����׳��쳣"
							+ e);
			throw e;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
	}

	
	// ���ݽ�ɫ�ţ���ȡ��ӭҳ
	public String getWelcomePage(String roleno) throws Exception {
		
		String welcomepage = "";
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			if (roleno == null || roleno.trim().equals(""))
				throw new Exception("ʵ��Ϊ�գ�");

			sql = dao.getSQL_WelcomePage(roleno);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				welcomepage = rs.getString(1);
			}
			return welcomepage;
		} catch (Exception e) {
			System.out
					.println("DataServer_UserManage��getWelcomePage(String roleno)�����׳��쳣"
							+ e);
			throw e;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
	}

	// ���ݽ�ɫ�Ż�ȡȨ�޴�
	public String getPowerString(String roleno) throws Exception {
		String powerstring = "";
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			if (roleno == null || roleno.trim().equals(""))
				throw new Exception("ʵ��Ϊ�գ�");

			sql = dao.getSQL_PowerString(roleno);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				powerstring = rs.getString(1);
			}
			return powerstring;
		} catch (Exception e) {
			System.out
					.println("DataServer_UserManage��getPowerString(String roleno)�����׳��쳣"
							+ e);
			throw e;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
	}

	// ����Ȩ�޴���ȡ��ȫ��ʶ����
	public Vector<String> getVSafeMark(String powerstring) throws Exception {
		Vector<String> v_safemark = new Vector<String>();
		String safemark = "";
		try {
			for (int i = 1; i < powerstring.length(); i++) {
				if (powerstring.substring(i - 1, i).equals("1")) {
					safemark = getSafeMark(String.valueOf(i));
					if (safemark != null && !safemark.trim().equals(""))
						v_safemark.addElement(safemark);
				}
			}
			return v_safemark;
		} catch (Exception e) {
			System.out
					.println("DataServer_UserManage��getVSafeMark(String powerstring)�����׳��쳣"
							+ e);
			throw e;
		}
	}

	// ���ݹ��ܺŻ�ȡ�䰲ȫ��ʶ
	public String getSafeMark(String functionid) throws Exception {
		String safemark = "";
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			if (functionid == null || functionid.trim().equals(""))
				throw new Exception("ʵ��Ϊ�գ�");

			sql = dao.getSQL_SafeMark(functionid);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				safemark = rs.getString(1);
			}
			return safemark;
		} catch (Exception e) {
			System.out
					.println("DataServer_UserManage��getSafeMark(String functionid)�����׳��쳣"
							+ e);
			throw e;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
	}

	// ��ȡ������Ϣ
	public Function getFuncitonInfo(String functionid) throws Exception {
		Function f = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			if (functionid == null || functionid.trim().equals(""))
				throw new Exception("ʵ��Ϊ�գ�");

			sql = dao.getSQL_FuncitonInfo(functionid);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				String functionname = rs.getString(2);
				String url = rs.getString(3);
				String upfunctionid = rs.getString(4);
				String rank = rs.getString(5);
				String nodetype = rs.getString(6);
				String state = rs.getString(7);
				String safemark = rs.getString(8);
				String note = rs.getString(9);
				Float flo_Order = rs.getFloat(11);
				f = new Function();
				f.setFunctionID(functionid);
				f.setFunctionName(functionname);
				f.setNodeType(nodetype);
				f.setNote(note);
				f.setRank(rank);
				f.setSafeMark(safemark);
				f.setState(state);
				f.setUpFunctionID(upfunctionid);
				f.setURL(url);
				f.setFlo_Order(flo_Order);
			}

			return f;
		} catch (Exception e) {
			System.out
					.println("DataServer_UserManage��getFuncitonInfo(String functionid)�����׳��쳣"
							+ e);
			throw e;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
	}

	// ���ݹ��ܺŻ�ȡ���ӽڵ��Ҷ
	public Vector<Function> getAllSubVFunction(String functionid)
			throws Exception {
		Vector<Function> v_function = new Vector<Function>();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			sql = dao.getSQL_AllSubFunctionID(functionid);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			String sub_functionid = "";
			while (rs.next()) {
				sub_functionid = rs.getString(1);
				v_function.addElement(getFuncitonInfo(sub_functionid));
			}
			if (rs != null)
				rs.close();
			return v_function;
		} catch (Exception e) {
			System.out
					.println("DataServer_UserManage��getAllSubVFunctions(String functionid)�����׳��쳣"
							+ e);
			throw e;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
	}

}
