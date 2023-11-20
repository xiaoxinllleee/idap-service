package org.cmms.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.system.entity.SysDic;

/**
 * <p>
 * 系统字典配置表 服务类
 * </p>
 *
 * @Author scott
 * @since 2018-12-19
 */
public interface ISysDicService extends IService<SysDic> {
    public SysDic queryByCode(String code);

}
