package org.cmms.modules.yxdygl.pqqxgl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.yxdygl.pqqxgl.entity.YxdyglPqqxgl;
import org.cmms.modules.yxdygl.pqqxgl.entity.YxdyglPqqxglTree;

import java.util.List;

/**
 * @Description: 片区权限管理
 * @Author: jeecg-boot
 * @Date:   2021-11-17
 * @Version: V1.0
 */
public interface IYxdyglPqqxglService extends IService<YxdyglPqqxgl> {

    IPage<YxdyglPqqxglTree> getTreeList(IPage page, YxdyglPqqxgl yxdyglPqqxgl);

    //获取已经有主客户经理的网格
    List<String> getMenuIdsByZkhjl();

    public List<String> getMenuIdsByKhjlgh(String yggh);
}
