package org.cmms.modules.xdgl.nsb.service.impl;

import org.cmms.modules.xdgl.nsb.entity.CamsNhpjsxNsb;
import org.cmms.modules.xdgl.nsb.mapper.CamsNhpjsxNsbMapper;
import org.cmms.modules.xdgl.nsb.service.ICamsNhpjsxNsbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 浏阳农户评级授信农户年审表
 * @Author: jeecg-boot
 * @Date:   2022-10-12
 * @Version: V1.0
 */
@Service
public class CamsNhpjsxNsbServiceImpl extends ServiceImpl<CamsNhpjsxNsbMapper, CamsNhpjsxNsb> implements ICamsNhpjsxNsbService {

    @Override
    public List<String> getAllByErrorType(String sszh,String errorType) {
        return baseMapper.getAllByErrorType(sszh,errorType);
    }

    @Override
    public void updateByDao(CamsNhpjsxNsb camsNhpjsxNsb) {
        baseMapper.updateByDao(camsNhpjsxNsb);
    }

    @Override
    public void tq() {
        baseMapper.tq();
    }
}
