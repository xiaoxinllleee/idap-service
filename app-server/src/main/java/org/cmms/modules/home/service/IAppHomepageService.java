package org.cmms.modules.home.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.home.entity.AppHomepage;

import java.util.List;

/**
 * @Description: APP主页配置
 * @Author: jeecg-boot
 * @Date:   2022-02-25
 * @Version: V1.0
 */
public interface IAppHomepageService extends IService<AppHomepage> {

    List<AppHomepage> getByUserName(String username);
    List<AppHomepage> getFootByUserName(String username);
}
