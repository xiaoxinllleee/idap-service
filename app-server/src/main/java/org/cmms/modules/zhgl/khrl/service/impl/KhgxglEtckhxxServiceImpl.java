package org.cmms.modules.zhgl.khrl.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.Data;
import org.cmms.modules.zhgl.khrl.entity.KhgxglEtckhxx;
import org.cmms.modules.zhgl.khrl.mapper.KhgxglEtckhxxMapper;
import org.cmms.modules.zhgl.khrl.service.IKhgxglEtckhxxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: ETC客户信息
 * @Author: jeecg-boot
 * @Date:   2022-03-21
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class KhgxglEtckhxxServiceImpl extends ServiceImpl<KhgxglEtckhxxMapper, KhgxglEtckhxx> implements IKhgxglEtckhxxService {

    @Override
    public List<KhgxglEtckhxx> getEtcListByKhmc(String khmc, String jgdm) {
        return baseMapper.getEtcListByKhmc(khmc, jgdm);
    }
}
