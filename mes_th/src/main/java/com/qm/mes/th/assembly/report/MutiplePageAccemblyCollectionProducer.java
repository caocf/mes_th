package com.qm.mes.th.assembly.report;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.LazyDynaBean;

import com.qm.mes.th.assembly.IReportCollectionProducer;
import com.qm.mes.th.assembly.IReportOrder;
import com.qm.mes.th.assembly.helper.JasperTemplateLoader;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import th.pz.bean.JConfigure;

/**
 * �����򵥱���
 * 
 * @author Ajaxfan
 */
class MutiplePageAccemblyCollectionProducer implements IReportCollectionProducer {
	/**
	 * ��ӡ�������������
	 * 
	 * @param orderList
	 * @return
	 */
	@Override
	public List<JasperPrint> product(List<IReportOrder> orderList) {
		List<JasperPrint> list = new ArrayList<JasperPrint>();

		try {
			for (IReportOrder order : orderList) {
				list.add(createMainJasperPrint(order));
				list.addAll(createTraceJasperPrints(order));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public JasperPrint createMainJasperPrint(IReportOrder order) throws Exception {
		// �������
		Map<String, Object> parameters = new HashMap<String, Object>();

		// ����datasource;
		parameters.put("js", String.valueOf(order.getReportBaseInfo().getCarno()));
		parameters.put("zrq", order.getRequestParam().getRequestDate());
		parameters.put("ch", order.getReportBaseInfo().getCarno());
		parameters.put("tm", order.getReportBaseInfo().getChassisNumber());
		parameters.put("mc", order.getPrintSet().getCTFASSName());
		parameters.put("id", order.getPrintSet().getId());
		parameters.put("SUBREPORT_DIR", JasperTemplateLoader.BASE_PATH);

		// ���ݲ��
		parameters.put("dataSource", getSubList(order.getDatas(), 0, 8));
		parameters.put("dataSource1", getSubList(order.getDatas(), 8, 16));
		parameters.put("dataSource2", getSubList(order.getDatas(), 16, 24));
		parameters.put("totalDatasource", createTotalDataList(order.getDatas()));

		return createJasperPrints(order.getPrintSet().getCPrintMD(), parameters);
	}

	/**
	 * �������ݻ���
	 * 
	 * @param datas
	 * @return
	 */
	private Object createTotalDataList(List<JConfigure> datas) {
		Map<String, Integer> map = new TreeMap<String, Integer>();

		// ���ݷ������
		for (JConfigure obj : datas) {
			// ��¼������
			int count = 0;

			// ���������Ϣ���ڣ����¼��������
			if (map.containsKey(obj.getCQADNo())) {
				count = map.get(obj.getCQADNo());
			}
			map.put(obj.getCQADNo(), ++count);
		}
		// ͳ����Ϣ�б�
		List<DynaBean> list = new ArrayList<DynaBean>();

		for (String key : map.keySet()) {
			DynaBean bean = new LazyDynaBean();
			bean.set("itemTotal", key + ":  " + map.get(key));

			list.add(bean);
		}
		return list;
	}

	/**
	 * ׷�ݱ�
	 * 
	 * @param reportBaseInfo
	 * @param printSet
	 * @param requestParam
	 * @param dataset
	 * @return
	 * @throws Exception
	 */
	private List<JasperPrint> createTraceJasperPrints(IReportOrder order) throws Exception {
		// Ҫ��ӡ�ı�����
		List<JasperPrint> list = new ArrayList<JasperPrint>();

		// ֻ�������ض������������ܴ�ӡ׷�ݵ�
		if ("1".equals(order.getPrintSet().getCCode())) {
			// �������
			Map<String, Object> parameters = new HashMap<String, Object>();

			parameters.put("js", String.valueOf(order.getReportBaseInfo().getCarno()));
			parameters.put("zrq", order.getRequestParam().getRequestDate());
			parameters.put("mc", order.getPrintSet().getCTFASSName());
			parameters.put("SUBREPORT_DIR", JasperTemplateLoader.BASE_PATH);

			for (int i = 0, n = 0; i < 2; i++) {// ׷�ݵ�ÿ�����һ�����ŵ���
				for (int j = 1; j <= 2; j++) {// ÿ�����Ӵ�ӡ��������
					parameters.put("dataSource" + j, getSubList(order.getDatas(), n * 6, (++n) * 6));
				}
				JasperPrint print = null;
				if (order.getPrintSet().getCPrintMD().contains("ca3")) {
					print = createJasperPrints("new_qnfxpzs_ca3.jasper", parameters);
				} else {// ��Ϊ׷�ݵ���һ�������������ֿ����ŵ��Ӵ�ӡ��������Ҫ�����ݲ�֣��������ɴ�ӡ����
					print = createJasperPrints("new_qnfxpzs.jasper", parameters);
				}
				// ׷�ݵ������ظ���ӡ
				print.setProperty("repeat", "false");

				list.add(print);
			}
		}
		return list;
	}

	/**
	 * ��ȡ�����ݼ���
	 * 
	 * @param configures
	 * @param percent
	 *            �ڼ���
	 * @param all
	 *            �ܷ���
	 * @return
	 */
	private List<JConfigure> getSubList(List<JConfigure> configures, int start, int end) {
		int size = configures.size();// ���ݼ��ϵĴ�С
		end = Math.min(size, end);// �зּ��ϵĽ�������

		if (size >= start) {
			return configures.subList(start, end);
		}
		return Collections.emptyList();
	}

	/**
	 * @param conn
	 * @param printSet
	 * @param reportBaseInfo
	 * @param dataset
	 * @param requestParam
	 * @return
	 */
	private JasperPrint createJasperPrints(String printMd, Map<String, Object> parameters) throws Exception {
		return JasperFillManager.fillReport((JasperReport) JRLoader.loadObject(JasperTemplateLoader.load(printMd)),
		        parameters, new JREmptyDataSource());
	}
}
