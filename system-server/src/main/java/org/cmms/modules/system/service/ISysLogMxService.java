package org.cmms.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.system.entity.SysLogMx;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * @Description: 系统日志明细表
 * @Author: jeecg-boot
 * @Date:   2023-05-25
 * @Version: V1.0
 */
public interface ISysLogMxService extends IService<SysLogMx> {

    void addLoginMxLog(String userid,String username,String lx);
    void addLogoutMxLog(String userid,String lx,Date exit);
}
