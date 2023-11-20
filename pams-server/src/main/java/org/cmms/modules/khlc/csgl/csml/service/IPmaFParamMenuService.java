package org.cmms.modules.khlc.csgl.csml.service;

import org.cmms.modules.khlc.csgl.csml.entity.PmaFParamMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khlc.csgl.csml.model.PmaFParamMenuTreeModel;
import org.cmms.modules.khlc.khfagl.model.SchemeMenulTreeModel;

import java.util.List;

/**
 * @Description: 参数目录
 * @Author: jeecg-boot
 * @Date:   2021-03-23
 * @Version: V1.0
 */
public interface IPmaFParamMenuService extends IService<PmaFParamMenu> {
    List<PmaFParamMenuTreeModel> queryTreeList();
}
