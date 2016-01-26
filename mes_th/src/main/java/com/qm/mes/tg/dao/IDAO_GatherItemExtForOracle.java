/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.qm.mes.tg.dao;

import com.qm.mes.tg.bean.GatherItemExt;

/**
 * ����ORACLE�����ݿ����
 *
 * @author YuanPeng
 */
public class IDAO_GatherItemExtForOracle implements IDAO_GatherItemExt {

    /**
     * ����GatherItemExt
     * seq_tg_GatherItemExt.nextvalΪseq_tg_GatherItemExt������һֵ֮��
     * @param gatherItemExt
     *                  �ɼ��������Զ���
     * @return sql
     *          ��������GatherItemExt��SQL���
     */
    public String saveGatherItemExt(GatherItemExt gatherItemExt) {
        String sql = "insert into t_tg_Gatheritemext(INT_ID,INT_GATHER_ID,INT_ORDER,STR_NAME) "
				+ "values(seq_tg_GatherItemExt.nextval"+","
                + gatherItemExt.getGatherId()
                + ","
                + gatherItemExt.getOrder() 
                + ",'"
                + gatherItemExt.getName()
                +"')";
        return sql;
    }

    /**
     * ͨ��ID��ѯGatherItemExt
     *
     * @param id
     *          ���
     * @return sql
     *          ͨ��ID��ѯGatherItemExt��SQL���
     */
    public String getGatherItemExtById(int id) {
        String sql = "select INT_ID,INT_GATHER_ID,INT_ORDER,STR_NAME from t_tg_gatheritemext where INT_ID = "
                + id + "order by INT_ID";
        return sql;
    }

    /**
     * ��ѯ���һ���ɼ���ID
     *
     * @return
     */
    public String getMaxGatherId(){
        String sql="select max(int_id) from T_TG_GATHER";
        return sql;
    }

    /**
     * ͨ��IDɾ��GatherItemExt
     *
     * @param id
     *          ���
     * @return sql
     *          ͨ��IDɾ��GatherItemExt��SQL���
     */
    public String delGatherItemExtById(int id) {
        String sql = "delete from t_tg_gatheritemext where INT_ID = " + id;
        return sql;
    }

    /**
     * ͨ���ɼ���IDɾ����Ӧ����չ����
     *
     * @param gather_id
     * @return
     */
    public String delGatherItemExtByGatherId(int gather_id) {
        String sql = "delete from t_tg_gatheritemext where INT_GATHER_ID = " + gather_id;
        return sql;
    }

    /**
     * ����GatherItemExt
     *
     * @param gatherItemExt
     *                  �ɼ��������Զ���
     * @return sql
     *          ����GatherItemExt��SQL���
     */
    public String updateGatherItemExt(GatherItemExt gatherItemExt) {
        String sql = "update t_tg_gatheritemext set INT_GATHER_ID = " + gatherItemExt.getGatherId()
                + ",INT_ORDER = " + gatherItemExt.getOrder()
                + ",STR_NAME = '" +gatherItemExt.getName()
                + "' where INT_ID = " + gatherItemExt.getId();
        return sql;
    }

    /**
     * ��ѯ����GatherItemExt
     *
     * @return sql
     *          ��ѯ����GatherItemExt��SQL���
     */
    public String getAllGatherItemExt() {
        String sql = "select INT_ID,INT_GATHER_ID,INT_ORDER,STR_NAME from t_tg_gatheritemext ";
        return sql;
    }

    /**
     * ͨ��name��ѯGatherItemExt
     *
     * @param name
     *          ����
     * @return sql
     *          ͨ��name��ѯGatherItemExt��SQL���
     */
    public String getGatherItemExtByName(String name) {
        String sql = "select INT_ID,INT_GATHER_ID,INT_ORDER,STR_NAME from t_tg_gatheritemext where STR_NAME = '"
                + name + "' order by STR_NAME";
        return sql;
    }

    /**
     * ͨ��GatherItemExt��ѯGatherItemExt
     *
     * @param int_gather_id
     * @return sql
     */
    public String getGatherItemExtByGatherId(int int_gather_id){
        String sql = "select INT_ID,INT_GATHER_ID,INT_ORDER,STR_NAME from t_tg_gatheritemext where INT_GATHER_ID = "
                + int_gather_id + " order by INT_ORDER";
        
        return sql;
    }
}
