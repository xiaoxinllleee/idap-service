package org.cmms.modules.khgxgl.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.khgxgl.entity.KhgxglDkkhxxgl;
import org.cmms.modules.khgxgl.mapper.KhgxglDkkhxxglMapper;
import org.cmms.modules.khgxgl.service.IKhgxglDkkhxxglService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 贷款客户信息管理
 * @Author: jeecg-boot
 * @Date:   2022-03-10
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class KhgxglDkkhxxglServiceImpl extends ServiceImpl<KhgxglDkkhxxglMapper, KhgxglDkkhxxgl> implements IKhgxglDkkhxxglService {

    @Override
    public String getAllCpxxByZjhm(String zjhm) {
        return baseMapper.getAllCpxxByZjhm(zjhm);
    }

    @Override
    public String getAllCpxxByHth(String hth) {
        return baseMapper.getAllCpxxByHth(hth);
    }
}
