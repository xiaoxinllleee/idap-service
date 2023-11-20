package org.cmms.modules.system.service;

import org.cmms.modules.system.entity.DpJdgl;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.system.model.DpJdglTreeModel;
import org.cmms.modules.system.model.HrBasOrganizationTreeModel;

import java.util.List;

/**
 * @Description: 绩效自动任务节点管理
 * @Author: jeecg-boot
 * @Date:   2021-01-07
 * @Version: V1.0
 */
public interface IDpJdglService extends IService<DpJdgl> {
    List<DpJdglTreeModel> queryTreeList();

    List<DpJdglTreeModel> searhBy(String keyWord);

}
