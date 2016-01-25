package th.bs.bean;

/**
 * BOM��
 * 
 * @author Gaohf
 * @date 2010-4-19
 */
public class PartData {
    /** ���� */
    private Integer id;

    /** �������� */
    private String ctfass;

    /** ����������� */
    private String cvwmainpart;

    /** ��������������� */
    private Integer nvwsubparttype;

    /** ����������� */
    private String cvwsubpart;

    /** ������������� */
    private Integer nvwsubpartquan;

    /** ������ */
    private String cqadno;

    /** ���ȳ��� */
    private String cistempcar;

    /** ��ע */
    private String cremark;

    public String getCtfass() {
        return ctfass;
    }

    public void setCtfass(String ctfass) {
        this.ctfass = ctfass;
    }

    public String getCvwmainpart() {
        return cvwmainpart;
    }

    public void setCvwmainpart(String cvwmainpart) {
        this.cvwmainpart = cvwmainpart;
    }

    public Integer getNvwsubparttype() {
        return nvwsubparttype;
    }

    public void setNvwsubparttype(Integer nvwsubparttype) {
        this.nvwsubparttype = nvwsubparttype;
    }

    public String getCvwsubpart() {
        return cvwsubpart;
    }

    public void setCvwsubpart(String cvwsubpart) {
        this.cvwsubpart = cvwsubpart;
    }

    public Integer getNvwsubpartquan() {
        return nvwsubpartquan;
    }

    public void setNvwsubpartquan(Integer nvwsubpartquan) {
        this.nvwsubpartquan = nvwsubpartquan;
    }

    public String getCqadno() {
        return cqadno;
    }

    public void setCqadno(String cqadno) {
        this.cqadno = cqadno;
    }

    public String getCistempcar() {
        return cistempcar;
    }

    public void setCistempcar(String cistempcar) {
        String isTemp = "T".equalsIgnoreCase(cistempcar) ? "��" : "��";
        this.cistempcar = isTemp;
    }

    public String getCremark() {
        return cremark;
    }

    public void setCremark(String cremark) {
        this.cremark = cremark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}