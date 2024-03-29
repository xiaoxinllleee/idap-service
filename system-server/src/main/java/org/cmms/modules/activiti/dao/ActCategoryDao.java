package org.cmms.modules.activiti.dao;

import org.cmms.base.XbootBaseDao;
import org.cmms.modules.activiti.entity.ActCategory;

import java.util.List;

/**
 * 流程分类数据处理层
 * @author Exrick
 */
public interface ActCategoryDao extends XbootBaseDao<ActCategory,String> {

    /**
     * 通过父id获取
     * @param parentId
     * @return
     */
    List<ActCategory> findByParentIdOrderBySortOrder(String parentId);

    /**
     * 通过名称模糊搜索
     * @param title
     * @return
     */
    List<ActCategory> findByTitleLikeOrderBySortOrder(String title);
}