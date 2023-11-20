package org.cmms.modules.yxdygl.yxdyglmain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.yxdygl.yxdyglmain.entity.YxdyglMenu;

import java.util.List;

/**
 * @Description: 营销单元菜单
 * @Author: jeecg-boot
 * @Date:   2021-11-16
 * @Version: V1.0
 */
public interface IYxdyglMenuService extends IService<YxdyglMenu> {
    List<YxdyglMenu> listTree(String khjl);
}
