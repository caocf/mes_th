/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.qm.mes.tg.dao;

import com.qm.mes.tg.bean.GatherItemExt;
/**
 * �־ò�ӿ���
 *
 * @author YuanPeng
 */
public interface IDAO_GatherItemExt {

    /**
     * ����GatherItemExt����
     *
     * @param gatherItemExt
     *                      ʵ�����
     *
     * @return
     */
    String saveGatherItemExt(GatherItemExt gatherItemExt);

    /**
     * ͨ����Ų��GatherItemExt����
     *
     * @param id
     *           ��ţ�Ψһ��
     * @return int_id
     *                  ���<br>
     *          int_gatherId
     *                  �ɼ���ID<br>
     *          int_order
     *                  �ɼ���˳���<br>
     *          str_name
     *                  ��չ������<br>
     */
    String getGatherItemExtById(int id);

    /**
     * ��ѯ���һ���ɼ���ID
     *
     * @param id
     *           ��ţ�Ψһ��
     * @return int_id
     *                  ���<br>
     *          int_gatherId
     *                  �ɼ���ID<br>
     *          int_order
     *                  �ɼ���˳���<br>
     *          str_name
     *                  ��չ������<br>
     */
    String getMaxGatherId();

    /**
     * ͨ�����ɾ��GatherItemExt����
     *
     * @param id
     *          ��ţ�Ψһ��
     * @return  
     */
    String delGatherItemExtById(int id);

    /**
     * ͨ���ɼ���IDɾ����Ӧ����չ����
     * 
     * @param gather_id
     *              �ɼ���ID
     * @return
     */
    String delGatherItemExtByGatherId(int gather_id);

    /**
     * �޸�GatherItemExt����
     *
     * @param gatherItemExt
     *                  �ɼ�����չ���Զ���
     * @return 
     */
    String updateGatherItemExt (GatherItemExt gatherItemExt);

    /**
     * ��ѯ��ȫ��GatherItemExt����
     *
     * @return List<GatherItemExt>
     *                  �ɼ�����չ��������
     */
    String getAllGatherItemExt ();

    /**
     *ͨ�����ֲ��GatherItemExt����
     *
     * @param name
     *           ����
     * @return List<GatherItemExt>
     *                  �ɼ�����չ��������
     */
    String getGatherItemExtByName(String name);

    /**
     * ͨ���ɼ�����Ų��GatherItemExt����
     * 
     * @param int_gather_id
     * @return int_id
     *                  ���<br>
     *          int_gatherId
     *                  �ɼ���ID<br>
     *          int_order
     *                  �ɼ���˳���<br>
     *          str_name
     *                  ��չ������<br>
     */
    String getGatherItemExtByGatherId(int int_gather_id);
}
