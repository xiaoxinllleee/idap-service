package org.cmms.modules.yxdygl.yjyxdygl.service;

import org.cmms.modules.yxdygl.yjyxdygl.entity.Yjyxdygl;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.yxdygl.yxdyfjxx.entity.Yxdyfjxx;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Description: 一级营销单元管理
 * @Author: Penghr
 * @Date:   2020-07-17
 * @Version: V1.0
 */
public interface IYjyxdyglService extends IService<Yjyxdygl> {

    Yjyxdygl queryDataByDybh(Map<String,String> sql);

    List<Yjyxdygl> queryDataByUser(String username);

    /**
     * 添加一对多
     *
     */
    public void saveMain(Yjyxdygl yxdyglYjyxdygl,List<Yxdyfjxx> yxdyglYxdyfjxxList) ;

    /**
     * 修改一对多
     *
     */
    public void updateMain(Yjyxdygl yxdyglYjyxdygl,List<Yxdyfjxx> yxdyglYxdyfjxxList);

    /**
     * 删除一对多
     */
    public void delMain (String id);

    /**
     * 批量删除一对多
     */
    public void delBatchMain (Collection<? extends Serializable> idList);
}
