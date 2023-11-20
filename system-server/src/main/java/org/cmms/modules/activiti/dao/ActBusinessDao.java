package org.cmms.modules.activiti.dao;

import org.apache.ibatis.annotations.Select;
import org.cmms.base.XbootBaseDao;
import org.cmms.modules.activiti.entity.ActBusiness;

import java.util.List;

/**
 * 申请业务数据处理层
 * @author Exrick
 */
public interface ActBusinessDao extends XbootBaseDao<ActBusiness,String> {

    /**
     * 通过流程定义id获取
     * @param procDefId
     * @return
     */
    List<ActBusiness> findByProcDefId(String procDefId);

    /**
     * 通过标题获取
     * @param title
     * @return
     */
    List<ActBusiness> findByTitleLike(String title);

    /**
     * 通过tableId获取
     * @param tableId
     * @return
     */
    List<ActBusiness> findByTableId(String tableId);

    void deleteByTableId(String tableId);
}