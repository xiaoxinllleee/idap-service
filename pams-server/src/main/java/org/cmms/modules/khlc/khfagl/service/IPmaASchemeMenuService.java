package org.cmms.modules.khlc.khfagl.service;

import org.cmms.modules.khlc.khfagl.entity.PmaASchemeMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khlc.khfagl.model.SchemeMenulTreeModel;

import java.util.List;

/**
 * @Description: 考核方案目录
 * @Author: jeecg-boot
 * @Date:   2021-01-29
 * @Version: V1.0
 */
public interface IPmaASchemeMenuService extends IService<PmaASchemeMenu> {
    List<SchemeMenulTreeModel> queryTreeList();

}
