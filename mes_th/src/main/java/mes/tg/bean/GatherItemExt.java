/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mes.tg.bean;

/**
 *
 * @author YuanPeng
 */
public class GatherItemExt {
    private int id;//���
    private int gatherId;//�ɼ���Id
    private int order;//�ɼ�˳���
    private String name;//�������ƣ�

    public GatherItemExt(){
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the gatherId
     */
    public int getGatherId() {
        return gatherId;
    }

    /**
     * @param gatherId the gatherId to set
     */
    public void setGatherId(int gatherId) {
        this.gatherId = gatherId;
    }

    /**
     * @return the order
     */
    public int getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
