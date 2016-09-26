package com.qm.mes.th.assembly.newprint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.qm.mes.th.assembly.IReportDataSetBuilder;
import com.qm.mes.th.assembly.entities.ReportBaseInfo;
import com.qm.mes.th.assembly.entities.RequestParam;

import th.pz.bean.JConfigure;
import th.pz.bean.PrintSet;

/**
 * ���ݼ���������
 * 
 * @author Ajaxfan
 */
class DataSetBuilder implements IReportDataSetBuilder {
	private Connection conn;
	private PrintSet printSet;
	private ReportBaseInfo reportBaseInfo;
	private List<JConfigure> list;
	private String queryExpression;
	private RequestParam requestParam;

	/** ϵͳ��־���� */
	private Logger logger = Logger.getLogger(DataSetBuilder.class);

	/**
	 * 
	 * @param conn
	 * @param printSet
	 * @param reportBaseInfo
	 */
	public DataSetBuilder(Connection conn, PrintSet printSet, ReportBaseInfo reportBaseInfo,
	        RequestParam requestParam) {
		this.conn = conn;
		this.printSet = printSet;
		this.reportBaseInfo = reportBaseInfo;
		this.requestParam = requestParam;

		list = new ArrayList<JConfigure>();
	}

	/**
	 * ��ѯ���ʽ
	 */
	public void buildQueryExpression() {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT top ");
		sb.append(printSet.getNTFASSCount());
		sb.append(" c.cSEQNo_A, c.cVinCode, c.cCarType, ISNULL(cQADNo, '') cQADNo, sc.ITFASSNameId, sc.iTFASSNum, c.cCarNo, ks.ccode");
		sb.append(" FROM carData c LEFT JOIN carData_D sc");
		sb.append(" ON c.ccarno = sc.icarid AND itfassnameid = ");
		sb.append(reportBaseInfo.getTfassId());
		sb.append(" LEFT JOIN TFASSName t ON sc.itfassnameid = t.id ");
		sb.append(" LEFT JOIN kinset ks ON substring(c.ccarno,6,1) = ks.csubkin");
		sb.append(" WHERE ((dabegin = '");
		sb.append(reportBaseInfo.getDabegin());
		sb.append("' AND c.cSEQNo_A>'");
		sb.append(reportBaseInfo.getDaseqa());
		sb.append("') or (dabegin> '");
		sb.append(reportBaseInfo.getDabegin());
		sb.append("'))");

		if (printSet.getCCarType() != null && !printSet.getCCarType().equals("")) {
			String carType = "'" + printSet.getCCarType() + "'";
			carType = carType.replace(",", "','");
			sb.append(" AND substring(c.ccarno,6,1) in (" + carType + ")");
		}

		if (printSet.getCFactory() != null && !printSet.getCFactory().equals("")) {
			String factoryNo = "'" + printSet.getCFactory() + "'";
			factoryNo = factoryNo.replace(",", "','");
			sb.append(" AND (subString(c.cSEQNo_A,1,2) in(" + factoryNo + ")) ");
		}
		if (printSet.getCVinRule() != null && !"".equals(printSet.getCVinRule().trim())) {
			String cvinRule = "'" + printSet.getCVinRule() + "'";
			cvinRule = cvinRule.replace(",", "','");
			sb.append(" and (subString(c.cVinCode,7,2) in(" + cvinRule + ")) ");
		}
		sb.append(" ORDER BY c.dabegin, c.cSEQNo_A");

		logger.debug(sb.toString());

		queryExpression = sb.toString();
	}

	/**
	 * �������ݼ���
	 */
	public void buildBusinessDataSet() {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.prepareStatement(queryExpression);
			rs = stmt.executeQuery();

			// ��ǰ��ӡ��ҳ��
			int pageNumber = Integer.valueOf(requestParam.getJs());
			// û��ӡһ��������Ӧ�õݼ�
			int count = printSet.getNPerTimeCount() - (--pageNumber) * printSet.getNTFASSCount();
			count = Math.min(count, printSet.getNTFASSCount());

			// ������ݼ���
			for (int i = 1; i <= count; i++) {
				JConfigure obj = new JConfigure(i);
				obj.setChassisNumber(reportBaseInfo.getChassisNumber());
				obj.setPrintSetId(printSet.getIPrintGroupId());
				obj.setJs(reportBaseInfo.getCarno());

				if (rs.next()) {
					obj.setCQADNo(rs.getString("cQADNo"));// ��������
					obj.setCSEQNo_A(rs.getString("cSEQNo_A"));// ��װ˳���
					obj.setCVinCode(setLastVin(rs.getString("cVinCode")));// VIN��
					obj.setCCarNo(rs.getString("cCarNo"));// kin��
					obj.setCCarType(rs.getString("ccode"));// ����
					obj.setTfassId(rs.getInt("ITFASSNameId"));
				}
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					rs = null;
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					stmt = null;
				}
			}
		}
	}

	/**
	 * ��������Vin��
	 * 
	 * @param obj
	 * @return
	 */
	private String setLastVin(String curVin) {
		// ���ݲ����ʱ��VIN����Ϊ��
		if (curVin == null || curVin.trim().length() == 0) {
			return null;
		}
		// ��VIN7,8λ���з��࣬�洢��Ӧ��VIN��Ϣ
		reportBaseInfo.putVinMap2CarType(curVin.substring(6, 8), curVin);
		// ����Ӧ��VIN��
		printSet.setCLastVin(curVin);

		return curVin;
	}

	/*
	 * ���ݼ���
	 */
	public List<JConfigure> getDataSet() {
		return list;
	}
}
