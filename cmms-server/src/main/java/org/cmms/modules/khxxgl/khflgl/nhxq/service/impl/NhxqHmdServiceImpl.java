package org.cmms.modules.khxxgl.khflgl.nhxq.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.KhxxglHnkd;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.NhxqHmd;
import org.cmms.modules.khxxgl.khflgl.nhxq.mapper.NhxqHmdMapper;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqHmdService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 农户信息
 * @Author: jeecg-boot
 * @Date:   2021-12-01
 * @Version: V1.0
 */
@Service
public class NhxqHmdServiceImpl extends ServiceImpl<NhxqHmdMapper, NhxqHmd> implements INhxqHmdService {
    @Override
    public void init(){
        baseMapper.init();
    }
    @Override
    public List<NhxqHmd> getHByZjhm(String zjhm) {
        return baseMapper.getHByZjhm(zjhm);
    }

    @Override
    public List<NhxqHmd> getByHnkd(KhxxglHnkd khxxglHnkd) {
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        return baseMapper.getByHnkd(khxxglHnkd,loginUser.getWorkNo());
    }
}
