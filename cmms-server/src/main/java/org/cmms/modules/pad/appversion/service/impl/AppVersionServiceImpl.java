package org.cmms.modules.pad.appversion.service.impl;

import org.cmms.modules.pad.appversion.entity.AppVersion;
import org.cmms.modules.pad.appversion.mapper.AppVersionMapper;
import org.cmms.modules.pad.appversion.service.IAppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: App版本更新
 * @Author: jeecg-boot
 * @Date:   2020-07-25
 * @Version: V1.0
 */
@Service
public class AppVersionServiceImpl extends ServiceImpl<AppVersionMapper, AppVersion> implements IAppVersionService {
    @Autowired
    AppVersionMapper appVersionMapper;

    public AppVersion queryLatestVersion() {
        return appVersionMapper.queryLatestVersion();
    }
}
