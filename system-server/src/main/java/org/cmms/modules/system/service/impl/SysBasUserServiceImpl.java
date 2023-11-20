package org.cmms.modules.system.service.impl;

import org.cmms.modules.system.entity.SysBasUser;
import org.cmms.modules.system.mapper.SysBasUserMapper;
import org.cmms.modules.system.service.ISysBasUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 用户信息
 * @Author: jeecg-boot
 * @Date:   2020-07-22
 * @Version: V1.0
 */
@Service
public class SysBasUserServiceImpl extends ServiceImpl<SysBasUserMapper, SysBasUser> implements ISysBasUserService {

    @Override
    public SysBasUser getByUserId(String userId) {
        return baseMapper.getByUserId(userId);
    }

    @Override
    public void updatePassword(String userid, String password) {
        baseMapper.updatePassword(userid,password);
    }
}
