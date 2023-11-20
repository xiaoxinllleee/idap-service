package org.cmms.modules.pad.appversion.service;

import org.cmms.modules.pad.appversion.entity.AppVersion;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: App版本更新
 * @Author: jeecg-boot
 * @Date:   2020-07-25
 * @Version: V1.0
 */
public interface IAppVersionService extends IService<AppVersion> {
    public AppVersion queryLatestVersion();
}
