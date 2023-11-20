package org.cmms.modules.zhgl.khrl.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.zhgl.khrl.entity.KhgxglSjyhkhxx;
import org.cmms.modules.zhgl.khrl.mapper.KhgxglSjyhkhxxMapper;
import org.cmms.modules.zhgl.khrl.service.IKhgxglSjyhkhxxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 手机银行客户信息
 * @Author: jeecg-boot
 * @Date:   2022-03-19
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class KhgxglSjyhkhxxServiceImpl extends ServiceImpl<KhgxglSjyhkhxxMapper, KhgxglSjyhkhxx> implements IKhgxglSjyhkhxxService {

    @Override
    public List<KhgxglSjyhkhxx> getListBykhmc(String khmc, String jgdm) {

        return baseMapper.getListBykhmc(khmc, jgdm);
    }
}
