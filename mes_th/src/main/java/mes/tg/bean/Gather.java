package mes.tg.bean;

public class Gather {
	private int id;//���
	private String name;//�ɼ�������
	private String desc;//�ɼ�������
	private int produnitId;//������Ԫid��ϵͳ�ⲿȡ��
	private int materielruleId;//��������֤����
	//����״̬������֤
	private int startgo;
	//ǿ������״̬������֤
	private int  compel;
	
	public int getCompel(){
		return compel;
	}
	
	public void setCompel(int compel){
		this.compel=compel;
	}
	
	public int getStartgo(){
		return startgo;
		
	}
	public void setStartgo(int startgo){
		 this.startgo=startgo;
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getProdunitId() {
		return produnitId;
	}
	public void setProdunitId(int produnitId) {
		this.produnitId = produnitId;
	}
	public int getMaterielruleId() {
		return materielruleId;
	}
	public void setMaterielruleId(int materielruleId) {
		this.materielruleId = materielruleId;
	}
}
