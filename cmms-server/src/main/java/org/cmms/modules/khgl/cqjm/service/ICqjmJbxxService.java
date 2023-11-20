package org.cmms.modules.khgl.cqjm.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.cqjm.entity.*;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 城区居民功能包
 * @Author: jeecg-boot
 * @Date:   2020-02-20
 * @Version: V1.0
 */
public interface ICqjmJbxxService extends IService<CqjmJbxx> {

    /**
     * 城区居民 基本信息删除
     * @param id
     * @param dabh
     * @param zjhm
     */
    void deleteByIdAndDabhAndZjhm(@Param("id") String id,@Param("dabh") String dabh,@Param("zjhm") String zjhm);

    /**
     * 一对多添加保存
     * @param jbxx
     * @param zcxxList
     * @param ywhxgywList
     * @param fjxxList
     */
    void saveMain(CqjmJbxx jbxx, List<CqjmZcxx> zcxxList, List<CqjmYwhywwlxx> ywhxgywList, List<CqjmZcfzqk> zcfzqkList, List<CqjmFjxx> fjxxList);

    /**
     * 一对多修改保存
     * @param jbxx
     * @param zcxxList
     * @param ywhxgywList
     * @param fjxxList
     */
    void updateMain(CqjmJbxx jbxx, List<CqjmZcxx> zcxxList, List<CqjmYwhywwlxx> ywhxgywList, List<CqjmZcfzqk> zcfzqkList, List<CqjmFjxx> fjxxList);

    /**
     * 一对多删除
     * @param zjhm
     */
    void deleteMain(String zjhm);

    /**
     * 一对多批量删除
     * @param idList
     */
    void deleteBatchMain(Collection<? extends Serializable> idList);

}
