package org.cmms.modules.home.service.impl;

import org.cmms.modules.home.entity.AppHomepage;
import org.cmms.modules.home.mapper.AppHomepageMapper;
import org.cmms.modules.home.service.IAppHomepageService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: APP主页配置
 * @Author: jeecg-boot
 * @Date:   2022-02-25
 * @Version: V1.0
 */
@Service
public class AppHomepageServiceImpl extends ServiceImpl<AppHomepageMapper, AppHomepage> implements IAppHomepageService {

    @Override
    public List<AppHomepage> getByUserName(String username) {
        return baseMapper.getByUserName(username);
    }

    @Override
    public List<AppHomepage> getFootByUserName(String username) {
        return baseMapper.getFootByUserName(username);
    }
}
