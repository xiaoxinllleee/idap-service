package org.cmms.modules.yxdygl.ejyxdygl.service;

import org.cmms.modules.yxdygl.ejyxdygl.entity.Ejyxdygl;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.yxdygl.yxdyfjxx.entity.Yxdyfjxx;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Description: 二级营销单元管理
 * @Author: Penghr
 * @Date:   2020-07-17
 * @Version: V1.0
 */
public interface IEjyxdyglService extends IService<Ejyxdygl> {

    Ejyxdygl queryDataByDybh(Map<String,String> sql);

    List<Ejyxdygl> queryDataByUser(String username);

    String queryDybhBySsyxdy(String ssyxdy);

    /**
     * 添加一对多
     *
     */
    public void saveMain(Ejyxdygl ejyxdygl,List<Yxdyfjxx> yxdyfjxxList) ;

    /**
     * 修改一对多
     *
     */
    public void updateMain(Ejyxdygl ejyxdygl,List<Yxdyfjxx> yxdyfjxxList);

    /**
     * 删除一对多
     */
    public void delMain (String id);

    /**
     * 批量删除一对多
     */
    public void delBatchMain (Collection<? extends Serializable> idList);
}
