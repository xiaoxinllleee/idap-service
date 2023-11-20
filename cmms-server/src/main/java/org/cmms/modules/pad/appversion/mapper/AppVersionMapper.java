package org.cmms.modules.pad.appversion.mapper;

import org.cmms.modules.pad.appversion.entity.AppVersion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * @Description: App版本更新
 * @Author: jeecg-boot
 * @Date:   2020-07-25
 * @Version: V1.0
 */
@Component
public interface AppVersionMapper extends BaseMapper<AppVersion> {
    public AppVersion queryLatestVersion();
}
