package org.cmms.modules.yxdygl.sjyxdygl.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.yxdygl.sjyxdygl.entity.EjyxdyglReuse;
import org.cmms.modules.yxdygl.sjyxdygl.entity.Sjyxdygl;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.yxdygl.yxdyfjxx.entity.Yxdyfjxx;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Description: 三级营销单元管理
 * @Author: Penghr
 * @Date:   2020-07-18
 * @Version: V1.0
 */
public interface ISjyxdyglService extends IService<Sjyxdygl> {

    List<EjyxdyglReuse> QuerySszhByYjyxdybh(@Param("ejyxdybh") String ejyxdybh);

    Sjyxdygl queryDataByDybh(Map<String,String> sql);

    String queryDybhBySsyxdy(String ssyxdy);

    List<Sjyxdygl> queryDataByUser(String username);

    /**
     * 添加一对多
     *
     */
    public void saveMain(Sjyxdygl ejyxdygl,List<Yxdyfjxx> yxdyfjxxList) ;

    /**
     * 修改一对多
     *
     */
    public void updateMain(Sjyxdygl ejyxdygl,List<Yxdyfjxx> yxdyfjxxList);

    /**
     * 删除一对多
     */
    public void delMain (String id);

    /**
     * 批量删除一对多
     */
    public void delBatchMain (Collection<? extends Serializable> idList);
}
