package mes.tg.bean;

public class MaterielRule {
	private int id;// ���
	private String name;// ��֤����
	private String validate;// ���Ϲ������֤�ַ���
	private String desc;// ���Ϲ�����֤�ַ�����������Ϣ

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}
}
