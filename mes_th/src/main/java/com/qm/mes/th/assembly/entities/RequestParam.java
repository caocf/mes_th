package com.qm.mes.th.assembly.entities;

/**
 * �����������
 * 
 * @author Ajaxfan
 */
public class RequestParam {
    /** ��� */
    private String groupId;
    /** ���� */
    private String requestDate;
    /** ���̺� */
    private String chassisNumber;
    /** ���Ӻ� */
    private String js;

    public String getJs() {
        return js;
    }

    public void setJs(String js) {
        this.js = js;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }
}
