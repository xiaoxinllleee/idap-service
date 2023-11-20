package org.cmms.modules.appbase.checkupdate.service.impl;

import org.cmms.modules.appbase.checkupdate.entity.AppVersionInfo;
import org.cmms.modules.appbase.checkupdate.mapper.AppVersionInfoMapper;
import org.cmms.modules.appbase.checkupdate.service.IAppVersionInfoService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: APP版本信息
 * @Author: jeecg-boot
 * @Date:   2022-03-28
 * @Version: V1.0
 */
@Service
public class AppVersionInfoServiceImpl extends ServiceImpl<AppVersionInfoMapper, AppVersionInfo> implements IAppVersionInfoService {

}
