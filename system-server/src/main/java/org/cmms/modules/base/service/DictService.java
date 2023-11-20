package org.cmms.modules.base.service;

import org.cmms.base.XbootBaseService;
import org.cmms.modules.base.entity.Dict;

import java.util.List;

/**
 * 字典接口
 * @author Exrick
 */
public interface DictService extends XbootBaseService<Dict,String> {

    /**
     * 排序获取全部
     * @return
     */
    List<Dict> findAllOrderBySortOrder();

    /**
     * 通过type获取
     * @param type
     * @return
     */
    Dict findByType(String type);

    /**
     * 模糊搜索
     * @param key
     * @return
     */
    List<Dict> findByTitleOrTypeLike(String key);
}