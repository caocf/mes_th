/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.qm.mes.tg.dao;

import com.qm.mes.tg.bean.GatherItemExt;

/**
 *����SQLSERVER�����ݿ����
 *
 * @author YuanPeng
 */
public class IDAO_GatherItemExtForSqlserver extends IDAO_GatherItemExtForOracle{

    /**
     * ����saveGatherItemExt
     *
     * @param gatherItemExt
     *                  �ɼ��������Զ���
     * @return sql
     *          ����gatherItemExt��sql���
     */
    public String saveGatherItemExt(GatherItemExt gatherItemExt) {
        String sql = "insert into t_tg_Gatheritemext(INT_GATHER_ID,INT_ORDER,STR_NAME) "
				+ "values("
                + gatherItemExt.getGatherId()
                + ","
                + gatherItemExt.getOrder()
                + ","
                + gatherItemExt.getName()
                +")";
        return sql;
    }

}
