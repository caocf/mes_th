package com.qm.th.hints;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.regex.Pattern;

import ca.beq.util.win32.registry.RegistryKey;
import ca.beq.util.win32.registry.RootKey;

/**
 * ͨ����ȡע�����ȡ��Ϣ
 * 
 * @author GaoHF
 */
final class AccessRegedit {
	/** ����ƥ��Ŀ¼�ֶε����� */
	private static final Pattern pattern = Pattern.compile("[a-zA-Z]{1}:\\\\.*");
	/** ����Ŀ¼ */
	private static final String SERVICE_ROOT = "SYSTEM\\CurrentControlSet\\Services";
	/** �������� */
	private static final String SERVICE_NAME = "FATHService";

	/**
	 * ��ȡ�ļ���·��
	 * 
	 * @return
	 */
	static String getPath(String name) {
		return loadProperty().getProperty(name);
	}
	
	/**
	 * װ����Դ�ļ�
	 */
	private static Properties loadProperty() {
		// ��Դ�ļ�
		Properties properties = new Properties();
		// �ļ���
		FileInputStream in = null;
		
		try{
			// ���̸�Ŀ¼
			String base = getBasePath();
			// ��Դ�ļ�����
			File p = getPropertiesFile(new File(base));
			
			// װ����Դ�ļ���properties��
			if(p != null){
				in = new FileInputStream(p);
				properties.load(in);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			// ��Դ����
			if(in != null){
				try{
					
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					in = null;
				}
			}
		}
		return properties;
	}

	/**
	 * �����ļ�Ŀ¼
	 * 
	 * @return
	 */
	private static File getPropertiesFile(File dir) {
		// ����Ŀ¼
		for (File f : dir.listFiles()) {
			// ������Ŀ¼����������±���
			if (f.isDirectory()) {
				return getPropertiesFile(f);
			}
			// �ҵ�ָ����properties�ļ��� ��������
			if ("configuration.properties".equalsIgnoreCase(f.getName())) {
				return f;
			}
		}
		return null;
	}

	/**
	 * ���̸�Ŀ¼
	 * 
	 * @return
	 */
	private static String getBasePath() {
		// ��ȡע���
		RegistryKey r = new RegistryKey(RootKey.HKEY_LOCAL_MACHINE,
				SERVICE_ROOT + File.separator + SERVICE_NAME + File.separator
						+ "Parameters");
		// �������
		String result = "";
		// ͨ�������ȡָ���ִ�
		java.util.regex.Matcher matcher = pattern.matcher(r.getValue(
				"AppDir").toString());
		// ��ý�ȡ�ı�
		if (matcher.find()) {
			result = matcher.group();
		}
		return result;
	}
}
