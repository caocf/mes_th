package th.servlet;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Conn_MES;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import th.pz.bean.JConfigure;

/**
 * @author Administrator
 */
public class JdhzServletPrint extends HttpServlet {
	/** ���к� */
	private static final long serialVersionUID = 1L;
	/** ���ڸ�ʽ������ */
	SimpleDateFormat date_fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * ���ݴ���
	 */
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// ������Ӧͷ
		response.setContentType("application/octet-stream");

		String rq = request.getParameter("rq");// ��������
		String ch = request.getParameter("ch");// ���̺�
		String groupId = request.getParameter("groupid");// ��ӡ���
		String insert_sql = "";
		String update_sql = ""; // ����printSetvin
		String dh = ""; // ����
		String dysj = ""; // ��ӡʱ��
		String vincode = ""; // vin��
		String sxh = ""; // ˳���
		String kinh = ""; // kin��
		String tFass = ""; // ��������
		String tFassName = ""; // ����ܳ�����
		String carType = ""; // ���ʹ���
		String cvinRule = "";
		String seq_A = ""; // ��װ˳���
		String mesSeq = ""; // �����˳���
		String factoryNo = ""; // �����ţ�
		String printMd = ""; // ��ӡģ��
		String sqlWhere = ""; // where
		String pageNo = "0"; // print_data.cpageNo����
		String printSetCode = "";// printSet.cCode
		String printTitle = ""; // ���õ�ͷ
		String lastVin = ""; // ���vin��
		String tempVinLst = ""; //
		String abegin = "";
		String vinType = "";
		String tempVin = "";

		/*
		 * ������Ŷ������ ��λ��+��λ��+��λ��+printSet���е�groupId(��λ��+��λ��ˮ�� ��12λ
		 */
		int index = 0;
		int cjs = 1; // ������
		int zcid = 0; // ����ܳ�����id
		int tFassCount = 0; // ÿҳ��ӡ����
		int oldVinLst = 0;
		int newVinLst = 0;
		int lenTempVin = 0;
		int printDsCount = 0; // ��ͬgroupid�ļ�¼��

		List<List<JConfigure>> superlist = new ArrayList<List<JConfigure>>();
		Map<String, Object> parameters = new HashMap<String, Object>();

		Connection con = null;

		PreparedStatement printSet_stmt = null;
		Statement stmt = null;
		Statement insert_stmt = null;
		PreparedStatement insert_c = null;
		PreparedStatement v_stmt = null;
		Statement ps_stmt = null;
		PreparedStatement cseq_stmt = null;
		ResultSet rs = null;
		ResultSet rs_c = null;
		ResultSet v_rs = null;
		ResultSet cseq_rs = null;
		ResultSet printSet_rs = null;

		try {
			con = new Conn_MES().getConn();// �����µ����ݿ�����
			con.setAutoCommit(false);// ��������

			try {
				// ��ѯ��ӡ���ڴ�ӡ��Ŀ��������
				printSet_stmt = con.prepareStatement("SELECT COUNT(*) FROM printSet WHERE iPrintGroupId = ?");
				printSet_stmt.setString(1, groupId);
				printSet_rs = printSet_stmt.executeQuery();

				if (printSet_rs.next()) {
					printDsCount = printSet_rs.getInt(1);
				}
			} finally {
				if (printSet_rs != null) {
					try {
						printSet_rs.close();
					} finally {
						printSet_rs = null;
					}
				}
				if (printSet_stmt != null) {
					try {
						printSet_stmt.close();
					} finally {
						printSet_stmt = null;
					}
				}
			}
			// ����һ�����飬������Ŵ�ӡ��Ŀ��ID
			String printId[] = new String[printDsCount];

			// �������еĴ�ӡ��Ŀ
			printSet_stmt = con.prepareStatement("SELECT id, cCode FROM printSet WHERE iPrintGroupId = ? ORDER BY id");
			printSet_stmt.setString(1, groupId);
			printSet_rs = printSet_stmt.executeQuery();

			int iiPrintId = 0;

			while (printSet_rs.next()) {
				// �ظ�ѭ�� ��Դ�˷� �����ж� �����̣�������ͬһ�ŵ����ϵĴ�ӡ
				printId[iiPrintId] = printSet_rs.getString("id");
				printSetCode = printSet_rs.getString("cCode");
				superlist.add(new ArrayList<JConfigure>());

				// ���˴�ӡ�����������
				v_stmt = con.prepareStatement("SELECT * FROM printSet WHERE id = ?");
				v_stmt.setString(1, printId[iiPrintId]);
				v_rs = v_stmt.executeQuery();

				if (v_rs.next()) {
					tFassName = v_rs.getString("ctfassname");// �����
					carType = v_rs.getString("cCarType");// ����
					tFassCount = v_rs.getInt("ntfassCount");// ��ӡ����
					mesSeq = v_rs.getString("iMesSeq");// δ����
					factoryNo = v_rs.getString("cFactory");// ����
					printMd = v_rs.getString("cPrintMd");// ģ��
					printTitle = v_rs.getString("ccartypedesc");// ����
					lastVin = v_rs.getString("clastvin");// ���VIN
					cvinRule = v_rs.getString("cVinRule");// VIN����

					try {
						cseq_stmt = con.prepareStatement("SELECT dabegin, cseqno_a FROM cardata WHERE cvincode = ?");
						cseq_stmt.setString(1, lastVin);
						cseq_rs = cseq_stmt.executeQuery();

						if (cseq_rs.next()) {
							abegin = cseq_rs.getString("dabegin");
							seq_A = cseq_rs.getString("cseqno_a");
						}
					} finally {
						if (cseq_rs != null) {
							try {
								cseq_rs.close();
							} finally {
								cseq_rs = null;
							}
						}
						if (cseq_stmt != null) {
							try {
								cseq_stmt.close();
							} finally {
								cseq_stmt = null;
							}
						}
					}

					// ����
					try {
						insert_c = con.prepareStatement(
								"SELECT max(iCarNo) FROM print_data WHERE iPrintGroupId= ? AND cremark = ?");
						insert_c.setString(1, printId[iiPrintId]);
						insert_c.setString(2, rq);
						rs_c = insert_c.executeQuery();

						if (rs_c.next()) {
							cjs = rs_c.getInt(1) + 1;
						} else {
							cjs = 1;
						}
						// end����
					} finally {
						if (rs_c != null) {
							try {
								rs_c.close();
							} finally {
								rs_c = null;
							}
						}
						if (insert_c != null) {
							try {
								insert_c.close();
							} finally {
								insert_c = null;
							}
						}
					}

					// ȡ����ܳ�id
					try {
						insert_c = con.prepareStatement("SELECT id FROM tfassname WHERE ctfassname = ?");
						insert_c.setString(1, tFassName);
						rs_c = insert_c.executeQuery();

						if (rs_c.next()) {
							zcid = rs_c.getInt(1);
						}
					} finally {
						if (rs_c != null) {
							try {
								rs_c.close();
							} finally {
								rs_c = null;
							}
						}
						if (insert_c != null) {
							try {
								insert_c.close();
							} finally {
								insert_c = null;
							}
						}
					}

					// ȡ��󵥺� print_data.cpageno
					try {
						insert_c = con.prepareStatement(
								"SELECT MAX(cpageNo) FROM print_data WHERE iPrintGroupId = ? AND cremark = ?");
						insert_c.setString(1, printId[iiPrintId]);
						insert_c.setString(2, rq);
						rs_c = insert_c.executeQuery();

						if (rs_c.next()) {
							pageNo = rs_c.getString(1);
							if (pageNo == null || pageNo.equals("")) {
								pageNo = "0";
							} else {
								pageNo = pageNo.substring(8, 12);
								pageNo = String.valueOf(Integer.valueOf(pageNo));
							}
						} else {
							pageNo = "0";
						}
					} finally {
						if (rs_c != null) {
							try {
								rs_c.close();
							} finally {
								rs_c = null;
							}
						}
						if (insert_c != null) {
							try {
								insert_c.close();
							} finally {
								insert_c = null;
							}
						}
					}
					// �Զ������������ 2��2��2��2groupid4��ˮ��
					dh = rq.substring(2, 4);
					dh = dh + rq.substring(5, 7);
					dh = dh + rq.substring(8, 10);
					String tempPrintId = printId[iiPrintId];
					int printLength = tempPrintId.length();

					for (int ii = 0; ii < (2 - printLength); ii++) {
						tempPrintId = "0" + tempPrintId;
					}
					dh = dh + tempPrintId;

					// pirnt_data����û�е�������
					String tempSeq = mesSeq;
					if (pageNo.equals("0")) {
						dh = dh + "0001";
						mesSeq = "1";
					} else {
						tempSeq = pageNo;
						tempSeq = String.valueOf(Integer.valueOf(tempSeq) + 1);
						mesSeq = tempSeq;
						int tempLength = tempSeq.length();
						for (int ii = 0; ii < (4 - tempLength); ii++) {
							tempSeq = "0" + tempSeq;
						}
						dh = dh + tempSeq;
					}

					// ȡprintsetvin������
					HashMap<String, String> hmVin = new HashMap<String, String>();
					try {
						insert_c = con.prepareStatement("SELECT ctype, clastvin FROM printSetVin WHERE printid = ?");
						insert_c.setString(1, printId[iiPrintId]);
						rs_c = insert_c.executeQuery();

						while (rs_c.next()) {
							hmVin.put(rs_c.getString("ctype"), rs_c.getString("clastvin"));
						}
					} finally {
						if (rs_c != null) {
							try {
								rs_c.close();
							} finally {
								rs_c = null;
							}
						}
						if (insert_c != null) {
							try {
								insert_c.close();
							} finally {
								insert_c = null;
							}
						}
					}
					sqlWhere = " select top " + tFassCount
							+ " c.cSEQNo_A,c.cVinCode,c.cCarType,cQADNo,sc.ITFASSNameId,sc.iTFASSNum,c.cCarNo,ks.ccode from carData c left outer join carData_D sc"
							+ " on c.ccarno = sc.icarid and itfassnameid = " + zcid
							+ " left join TFASSName t on sc.itfassnameid = t.id left join kinset ks on substring(c.ccarno,6,1)=ks.csubkin "
							+ " where ((dabegin = '" + abegin + "' and c.cSEQNo_A>'" + seq_A + "')" + " or (dabegin> '"
							+ abegin + "'))";

					if (carType != null && !carType.equals("")) {
						carType = "'" + carType + "'";
						carType = carType.replace(",", "','");
						sqlWhere = sqlWhere + "and substring(c.ccarno,6,1) in (" + carType + ")";
					}

					if (factoryNo != null && !factoryNo.equals("")) {
						factoryNo = "'" + factoryNo + "'";
						factoryNo = factoryNo.replace(",", "','");
						sqlWhere = sqlWhere + " and (subString(c.cSEQNo_A,1,2) in(" + factoryNo + ")) ";
					}
					if (cvinRule != null && !"".equals(cvinRule.trim())) {
						cvinRule = "'" + cvinRule + "'";
						cvinRule = cvinRule.replace(",", "','");
						sqlWhere = sqlWhere + " and (subString(c.cVinCode,7,2) in(" + cvinRule + ")) ";
					}
					sqlWhere = sqlWhere + " ORDER BY c.cSEQNo_A, c.dabegin";

					// Ҫ��ӡ������
					try {
						insert_stmt = con.createStatement();
						stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
						rs = stmt.executeQuery(sqlWhere);

						index = 0;
						while (rs.next()) {
							vincode = rs.getString("cVinCode");// vin
							sxh = rs.getString("cSEQNo_A");// ˳���
							zcid = rs.getInt("ITFASSNameId");// �ܳ�id
							dysj = date_fmt.format(GregorianCalendar.getInstance().getTime());// ��ӡʱ��
							kinh = rs.getString("cCarNo");// kin��
							tFass = rs.getString("cQADNo");// ��������

							// end �ж�vin�Ƿ� �Ѿ���ӡ, �ж�vin�Ƿ�����
							vinType = vincode.substring(6, 8);
							tempVin = hmVin.get(vinType);

							// ��vinΪ�յ�����£�ִ��������߼�
							if (tempVin == null || "".equals(tempVin)) {
								hmVin.put(vinType, vincode);// �洢vin
								update_sql = "insert into  printsetvin (printid,clastvin,ctype) values('"
										+ printId[iiPrintId] + "','" + vincode + "', '" + vincode.substring(6, 8)
										+ "')";
								insert_stmt.executeUpdate(update_sql);

								/** ********��ӡ����*************** */
								JConfigure jc = new JConfigure();
								jc.setCQADNo(rs.getString("cQADNo"));
								jc.setCSEQNo_A(rs.getString("cSEQNo_A"));
								jc.setCVinCode(rs.getString("cVinCode"));
								jc.setCCarNo(rs.getString("cCarNo"));
								// ����
								if (printSetCode.equals("1") && tFassName.trim().equals("������")) {
									jc.setCCarType("FXP");
								} else if (printSetCode.equals("1") && tFassName.trim().equals("��ȫ����")) {
									jc.setCCarType("AQQN");
								} else {
									jc.setCCarType(rs.getString("ccode").trim());
								}
								// ��0��ʼ�� ��Ӧ���ݿ�iNum
								jc.setIndex(++index);
								// ���һ������ӡ������
								superlist.get(iiPrintId).add(jc);

								// ��ӡ�����
								insert_sql = " insert into print_data(iPrintGroupId,cPageNo,dPrintTime,iCarNo,inum,cVinCode,cSEQNo,cTFAss,ITFASSNameId,iBigNo,cRemark,ckinno) "
										+ " values('" + printId[iiPrintId] + "','" + dh + "','" + dysj + "'," + cjs
										+ "," + index + ",'" + vincode + "','" + sxh + "','" + tFass + "','" + zcid
										+ "','" + ch + "','" + rq + "','" + kinh + "')";
								ps_stmt = con.createStatement();
								try {
									ps_stmt.executeUpdate(insert_sql);
								} finally {
									if (ps_stmt != null) {
										try {
											ps_stmt.close();
										} finally {
											ps_stmt = null;
										}
									}
								}
							}
							// ��֮ǰ��Vin��¼���ڵ������
							else {
								oldVinLst = Integer.valueOf(tempVin.substring(11)); // vin����λ
								newVinLst = Integer.valueOf(vincode.substring(11)); // cardata��vin��6λ

								// ��ʼֵΪ0
								if (oldVinLst == 0) {
									oldVinLst = newVinLst;
								} else {
									oldVinLst = oldVinLst + 1;
								}

								// Vin�����������
								if (newVinLst != oldVinLst) {
									while (newVinLst > oldVinLst && index < tFassCount) {
										/** ********��ӡ����*************** */
										JConfigure jc = new JConfigure();
										jc.setCQADNo("");
										jc.setCSEQNo_A("");
										jc.setCVinCode("");
										jc.setCCarNo("");
										jc.setCCarType("");
										jc.setIndex(++index);
										superlist.get(iiPrintId).add(jc);

										// ��ӡ�����
										insert_sql = " insert into print_data(iPrintGroupId,cPageNo,dPrintTime,iCarNo,inum,cVinCode,cSEQNo,cTFAss,ITFASSNameId,iBigNo,cRemark,ckinno) "
												+ " values('" + printId[iiPrintId] + "','" + dh + "','" + dysj + "',"
												+ cjs + "," + index + ",' ',' ',' ',' ','" + ch + "','" + rq + "',' ')";
										ps_stmt = con.createStatement();
										try {
											ps_stmt.executeUpdate(insert_sql);
										} finally {
											if (ps_stmt != null) {
												try {
													ps_stmt.close();
												} finally {
													ps_stmt = null;
												}
											}
										}
										/** ********��ӡ���ý��� ********* */
										/**
										 * ********printsetvin****************
										 */
										tempVinLst = String.valueOf(oldVinLst);
										lenTempVin = tempVinLst.length();
										for (int kk = 0; kk < (6 - lenTempVin); kk++) {
											tempVinLst = "0" + tempVinLst;
										}
										tempVin = tempVin.substring(0, 11) + tempVinLst;
										hmVin.put(tempVin.substring(6, 8), tempVin);

										// ���vin���ʱ
										// ����printsetvin
										update_sql = "update printsetvin set clastvin='" + tempVin + "' where printid='"
												+ printId[iiPrintId] + "' and ctype='" + tempVin.substring(6, 8) + "'";
										ps_stmt = con.createStatement();
										try {
											ps_stmt.executeUpdate(update_sql);
										} finally {
											if (ps_stmt != null) {
												try {
													ps_stmt.close();
												} finally {
													ps_stmt = null;
												}
											}
										}
										/**
										 * ***************printsetvinEnd********
										 * *****
										 */
										oldVinLst = oldVinLst + 1;

									}
									// �����ǰ�����Ų���ˣ���ҳ�Ĵ�ӡ��������ֱ������ѭ��
									if (++index > tFassCount) {
										break;
									}

									/** ** VIN������ӡ���ÿ�ʼ *** */
									JConfigure jc = new JConfigure();
									jc.setCQADNo(rs.getString("cQADNo"));
									jc.setCSEQNo_A(rs.getString("cSEQNo_A"));
									jc.setCVinCode(rs.getString("cVinCode"));
									jc.setCCarNo(rs.getString("cCarNo"));

									// ����
									if (printSetCode.equals("1") && tFassName.trim().equals("������")) {
										jc.setCCarType("FXP");
									} else if (printSetCode.equals("1") && tFassName.trim().equals("��ȫ����")) {
										jc.setCCarType("AQQN");
									} else {
										jc.setCCarType(rs.getString("ccode").trim());
									}
									jc.setIndex(index);
									superlist.get(iiPrintId).add(jc);

									// ��ӡ�����
									insert_sql = " insert into print_data(iPrintGroupId,cPageNo,dPrintTime,iCarNo,inum,cVinCode,cSEQNo,cTFAss,ITFASSNameId,iBigNo,cRemark,ckinno) "
											+ " values('" + printId[iiPrintId] + "','" + dh + "','" + dysj + "'," + cjs
											+ "," + index + ",'" + vincode + "','" + sxh + "','" + tFass + "','" + zcid
											+ "','" + ch + "','" + rq + "','" + kinh + "')";
									ps_stmt = con.createStatement();
									try {
										ps_stmt.executeUpdate(insert_sql);
									} finally {
										if (ps_stmt != null) {
											try {
												ps_stmt.close();
											} finally {
												ps_stmt = null;
											}
										}
									}
									/** ************��ӡ���ý���************* */

									/** ********printsetvin**************** */
									tempVinLst = String.valueOf(oldVinLst);
									lenTempVin = tempVinLst.length();
									for (int kk = 0; kk < (6 - lenTempVin); kk++) {
										tempVinLst = "0" + tempVinLst;
									}
									tempVin = tempVin.substring(0, 11) + tempVinLst;
									hmVin.put(tempVin.substring(6, 8), tempVin);

									// ���vin���ʱ, ����printsetvin
									update_sql = "update printsetvin set clastvin='" + tempVin + "' where printid='"
											+ printId[iiPrintId] + "' and ctype='" + tempVin.substring(6, 8) + "'";
									ps_stmt = con.createStatement();
									try {
										ps_stmt.executeUpdate(update_sql);
									} finally {
										if (ps_stmt != null) {
											try {
												ps_stmt.close();
											} finally {
												ps_stmt = null;
											}
										}
									}
									/**
									 * ***************printsetvinEnd************
									 * *
									 */
								} else {
									hmVin.put(vincode.substring(6, 8), vincode);

									// ����printsetvin
									update_sql = "update printsetvin set clastvin='" + vincode + "' where printid='"
											+ printId[iiPrintId] + "' and ctype='" + vincode.substring(6, 8) + "'";
									ps_stmt = con.createStatement();
									try {
										ps_stmt.executeUpdate(update_sql);
									} catch (Exception eUpdatePrintSet) {
										System.out.println("print_data����" + eUpdatePrintSet.toString());
									} finally {
										if (ps_stmt != null)
											ps_stmt.close();
									}
									/** ***��ӡ���ÿ�ʼ*** */
									JConfigure jc = new JConfigure();
									jc.setCQADNo(rs.getString("cQADNo"));
									jc.setCSEQNo_A(rs.getString("cSEQNo_A"));
									jc.setCVinCode(rs.getString("cVinCode"));
									jc.setCCarNo(rs.getString("cCarNo"));

									if (printSetCode.equals("1") && tFassName.trim().equals("������")) {
										jc.setCCarType("FXP");
									} else if (printSetCode.equals("1") && tFassName.trim().equals("��ȫ����")) {
										jc.setCCarType("AQQN");
									} else {
										jc.setCCarType(rs.getString("ccode").trim());
									}
									// ��0��ʼ�� ��Ӧ���ݿ�iNum
									index++;
									jc.setIndex(index);
									// list.add(jc);
									superlist.get(iiPrintId).add(jc);

									// ��ӡ�����
									insert_sql = " insert into print_data(iPrintGroupId,cPageNo,dPrintTime,iCarNo,inum,cVinCode,cSEQNo,cTFAss,ITFASSNameId,iBigNo,cRemark,ckinno) "
											+ " values('" + printId[iiPrintId] + "','" + dh + "','" + dysj + "'," + cjs
											+ "," + index + ",'" + vincode + "','" + sxh + "','" + tFass + "','" + zcid
											+ "','" + ch + "','" + rq + "','" + kinh + "')";
									ps_stmt = con.createStatement();
									try {
										ps_stmt.executeUpdate(insert_sql);
									} catch (Exception eUpdatePrintSet) {
										System.out.println("print_data����" + eUpdatePrintSet.toString());
									} finally {
										if (ps_stmt != null)
											ps_stmt.close();
									}
									/** ************��ӡ���ý���************* */
								}
							}

							// ���ϵͳ���г���һҳ�����Ŀ�vin ��ϵͳֻ��ӡһҳ�հס�
							if (index == tFassCount) {
								ps_stmt = con.createStatement();
								String psu_sql = "update printSet set cLastVin='" + vincode + "',cSEQNo_A='" + sxh
										+ "',cKinno='" + kinh + "', iMESseq=" + mesSeq + ",iBigNo=" + ch
										+ ",dPrintDate='" + dysj + "' ,iCarNo='" + cjs + "',cremark='" + dh
										+ "'  where id=" + printId[iiPrintId];
								try {
									ps_stmt.executeUpdate(psu_sql);
								} finally {
									if (ps_stmt != null) {
										try {
											ps_stmt.close();
										} finally {
											ps_stmt = null;
										}
									}
								}
							}
							// �������㣬����һ��
							else {
								ps_stmt = con.createStatement();
								String psu_sql = "update printSet set cLastVin='" + vincode + "',cSEQNo_A='" + sxh
										+ "',cKinno='" + kinh + "', iMESseq=" + mesSeq + ",iBigNo=" + ch
										+ ",dPrintDate='" + dysj + "' ,iCarNo='" + cjs + "',cremark='" + dh
										+ "'  where id=" + printId[iiPrintId];
								try {
									ps_stmt.executeUpdate(psu_sql);
								} finally {
									if (ps_stmt != null) {
										try {
											ps_stmt.close();
										} finally {
											ps_stmt = null;
										}
									}
								}
							}
						} // end while sqlwhere
						if (index == 0) {
							// ��ӡ�����
							insert_sql = " insert into print_data(iPrintGroupId,cPageNo,dPrintTime,iCarNo,inum,cVinCode,cSEQNo,cTFAss,ITFASSNameId,iBigNo,cRemark,ckinno) "
									+ " values('" + printId[iiPrintId] + "','" + dh + "','" + dysj + "'," + cjs + ","
									+ 1 + ",' ',' ',' ',' ','" + ch + "','" + rq + "',' ')";
							ps_stmt = con.createStatement();
							try {
								ps_stmt.executeUpdate(insert_sql);
							} catch (Exception eUpdatePrintSet) {
								System.out.println("print_data����" + eUpdatePrintSet.toString());
							} finally {
								if (ps_stmt != null)
									ps_stmt.close();
							}

							ps_stmt = con.createStatement();
							String psu_sql = "update printSet set iCarNo='" + cjs + "',cremark='" + dh + "'  where id="
									+ printId[iiPrintId];
							try {
								ps_stmt.executeUpdate(psu_sql);
							} finally {
								if (ps_stmt != null) {
									try {
										ps_stmt.close();
									} finally {
										ps_stmt = null;
									}
								}
							}
						}
					} catch (Exception eGetJc) {
						System.out.println(eGetJc.toString());
						throw eGetJc;
					} finally {
						if (rs != null)
							rs.close();
						if (stmt != null)
							stmt.close();
					}
				}
				iiPrintId++;
			} // end while of printId[]
			iiPrintId--;

			ServletContext context = this.getServletConfig().getServletContext();
			JasperPrint jasperPrint = null;
			String temp = printMd; // ��ӡģ����PrintSet���� ����洢��

			if (iiPrintId == 0) {
				File reportFile = new File(context.getRealPath(temp));
				File reportFile1 = new File(context.getRealPath("ireport"));

				// ����datasource;
				parameters.put("REPORT_CONNECTION", con);
				parameters.put("js", String.valueOf(cjs));
				parameters.put("zrq", rq);
				parameters.put("ch", ch);
				parameters.put("tm", dh);
				parameters.put("mc", printTitle);
				parameters.put("id", Integer.valueOf(printId[iiPrintId]));
				parameters.put("x_sub", reportFile1.getPath() + "\\");
				parameters.put("xdir", reportFile1.getPath() + "\\");

				JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getPath());

				if (superlist.get(iiPrintId).size() > 0) {
					jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
							new JRBeanCollectionDataSource(superlist.get(iiPrintId)));
				} else {
					jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
				}
			}
			// ������ ��ȫ���һ��
			else if (printSetCode.equals("1")) {
				// �����̡���ȫ�������ݼ���
				List<JConfigure> fxpqnList = new ArrayList<JConfigure>();
				// ��֯���ݼ���
				for (int iitFassCount = 0; iitFassCount < superlist.get(0).size(); iitFassCount++) {
					for (int iifxp = 0; iifxp <= iiPrintId; iifxp++) {
						fxpqnList.add(superlist.get(iifxp).get(iitFassCount));
					}
				}
				File reportFile = new File(context.getRealPath(temp));
				File reportFile1 = new File(context.getRealPath("ireport"));
				parameters.put("REPORT_CONNECTION", con);
				parameters.put("js", String.valueOf(cjs));
				parameters.put("zrq", rq);
				parameters.put("ch", ch);
				parameters.put("mc", printTitle);
				parameters.put("id", Integer.valueOf(printId[iiPrintId]));// ��ӡ��ID
				parameters.put("x_sub", reportFile1.getPath() + "\\");// ��Ŀ¼
				parameters.put("xdir", reportFile1.getPath() + "\\");// �ӱ���·��
				parameters.put("tm", dh);// ���̺�

				JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getPath());

				if (fxpqnList.size() > 0) {
					jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
							new JRBeanCollectionDataSource(fxpqnList));
				} else {
					jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
				}
			} else {
				try {
					printSet_stmt = con.prepareStatement("SELECT * FROM printSet WHERE iPrintGroupId = ? ORDER BY id");
					printSet_stmt.setString(1, groupId);
					printSet_rs = printSet_stmt.executeQuery();

					for (int iiList = 0; printSet_rs.next();) {
						parameters.put(printSet_rs.getString("cCode"),
								new JRBeanCollectionDataSource(superlist.get(iiList++)));
						parameters.put("mc" + String.valueOf(iiList), printSet_rs.getString("ccartypedesc"));
						parameters.put("id" + String.valueOf(iiList), printSet_rs.getInt("id"));
						parameters.put("tm" + String.valueOf(iiList), printSet_rs.getString("cremark").trim());
					}
				} finally {
					if (printSet_rs != null) {
						try {
							printSet_rs.close();
						} finally {
							printSet_rs = null;
						}
					}
					if (printSet_stmt != null) {
						try {
							printSet_stmt.close();
						} finally {
							printSet_stmt = null;
						}
					}
				}
				File reportFile = new File(context.getRealPath(temp));
				File reportFile1 = new File(context.getRealPath("ireport"));

				parameters.put("REPORT_CONNECTION", con);
				parameters.put("js", String.valueOf(cjs));
				parameters.put("zrq", rq);
				parameters.put("ch", ch);
				parameters.put("SUBREPORT_DIR", reportFile1.getPath() + "\\");

				JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getPath());
				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con);
			}
			ServletOutputStream ouputStream = response.getOutputStream();

			ObjectOutputStream oos = new ObjectOutputStream(ouputStream);
			oos.writeObject(jasperPrint);

			oos.flush();
			oos.close();

			ouputStream.flush();
			ouputStream.close();

			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.setAutoCommit(false);
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
