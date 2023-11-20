package org.cmms.modules.system.service;

import org.cmms.modules.system.entity.SysBasUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 用户信息
 * @Author: jeecg-boot
 * @Date:   2020-07-22
 * @Version: V1.0
 */
public interface ISysBasUserService extends IService<SysBasUser> {
    SysBasUser getByUserId(String userId);

    void updatePassword(String userid,String password);
}
