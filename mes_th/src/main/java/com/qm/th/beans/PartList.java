package com.qm.th.beans;

/**
 * PartList��
 * 
 * @author Gaohf
 * @date 2010-4-19
 */
public class PartList {
    /** ���� */
    private Integer id;

    /** ����� */
    private String cpartno;

    /** ������ */
    private String cparttype;

    /** ����ܳ��� */
    private String ctfassname;

    /** ��ע */
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpartno() {
        return cpartno;
    }

    public void setCpartno(String cpartno) {
        this.cpartno = cpartno;
    }

    public String getCtfassname() {
        return ctfassname;
    }

    public void setCtfassname(String ctfassname) {
        this.ctfassname = ctfassname;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCparttype() {
        return cparttype;
    }

    public void setCparttype(String cparttype) {
        this.cparttype = cparttype;
    }
}
