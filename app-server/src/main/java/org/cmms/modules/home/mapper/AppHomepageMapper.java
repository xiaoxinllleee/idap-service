package org.cmms.modules.home.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.home.entity.AppHomepage;

/**
 * @Description: APP主页配置
 * @Author: jeecg-boot
 * @Date:   2022-02-25
 * @Version: V1.0
 */
public interface AppHomepageMapper extends BaseMapper<AppHomepage> {

    List<AppHomepage> getByUserName(String usernmae);
    List<AppHomepage> getFootByUserName(String usernmae);
}
