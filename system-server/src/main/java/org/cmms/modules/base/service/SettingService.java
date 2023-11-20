package org.cmms.modules.base.service;

import org.cmms.modules.base.entity.Setting;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;


/**
 * 配置接口
 * @author Exrick
 */
@CacheConfig(cacheNames = "setting")
public interface SettingService {

    /**
     * 通过id获取
     * @param id
     * @return
     */
    Setting get(String id);

    /**
     * 修改
     * @param setting
     * @return
     */
    Setting saveOrUpdate(Setting setting);
}