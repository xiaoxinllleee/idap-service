package org.cmms.modules.khxxgl.khflgl.shxq.service.impl;

import org.cmms.modules.khxxgl.khflgl.shxq.entity.Shxq;
import org.cmms.modules.khxxgl.khflgl.shxq.mapper.ShxqMapper;
import org.cmms.modules.khxxgl.khflgl.shxq.service.IShxqService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 商户户采集信息
 * @Author: jeecg-boot
 * @Date:   2021-11-29
 * @Version: V1.0
 */
@Service
public class ShxqServiceImpl extends ServiceImpl<ShxqMapper, Shxq> implements IShxqService {
    @Override
    public void init(){
        baseMapper.init();
    }

    @Override
    public List<Shxq> selectByShxx(String hhbm) {
        return baseMapper.selectByShxx(hhbm);
    }

    @Override
    public Integer getEzfskmBySjjyzZjhm(String zjhm) {
        return baseMapper.getEzfskmBySjjyzZjhm(zjhm);
    }
}
