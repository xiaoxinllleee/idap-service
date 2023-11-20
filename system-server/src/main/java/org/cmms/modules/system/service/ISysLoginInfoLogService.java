package org.cmms.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.system.entity.SysLoginInfoLog;

import java.util.Date;

/**
 * @Description: 登录信息日志表
 * @Author: jeecg-boot
 * @Date:   2023-06-25
 * @Version: V1.0
 */
public interface ISysLoginInfoLogService extends IService<SysLoginInfoLog> {

    void addLoginInfoLog(String username,String realname,String lx);
    void addLogoutInfoLog(String username, String lx, Date exit);
}
