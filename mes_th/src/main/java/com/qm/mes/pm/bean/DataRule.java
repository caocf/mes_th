package com.qm.mes.pm.bean;

import java.util.List;
/**
 * ʵ��Bean�������ݹ����ʵ����Ϣ
 * @author Xujia
 *
 */
public class DataRule {

	/**
	 * �����
	 */
	private int id; 
	/**
	 * ��������
	 */
	private String name; 
	/**
	 * ������
	 */
	private String code; 
	/**
	 * ����Ĳ�����Ϣ
	 */
	private List<DataRuleArg> args;  
	/**
	 *   ����ʽ
	 */
	private String rule;  
	/**
	 * ��ǰ����Ĺ�������
	 */
	private String description;
	
	
	/**
	 * @return args
	 */
	public List<DataRuleArg> getArgs() {
		return args;
	}
	/**
	 * @param args Ҫ���õ� args
	 */
	public void setArgs(List<DataRuleArg> args) {
		this.args = args;
	}
	/**
	 * @return code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code Ҫ���õ� code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description Ҫ���õ� description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id Ҫ���õ� id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name Ҫ���õ� name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return rule
	 */
	public String getRule() {
		return rule;
	}
	/**
	 * @param rule Ҫ���õ� rule
	 */
	public void setRule(String rule) {
		this.rule = rule;
	}
	
	

}
