package org.cmms.modules.pad.shxxgl.service.impl;

import org.cmms.modules.pad.shxxgl.entity.CamsZcsxShcjxx;
import org.cmms.modules.pad.shxxgl.mapper.CamsZcsxShcjxxMapper;
import org.cmms.modules.pad.shxxgl.service.ICamsZcsxShcjxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 商户采集信息
 * @Author: jeecg-boot
 * @Date:   2020-08-29
 * @Version: V1.0
 */
@Service
public class CamsZcsxShcjxxServiceImpl extends ServiceImpl<CamsZcsxShcjxxMapper, CamsZcsxShcjxx> implements ICamsZcsxShcjxxService {
    @Autowired
    private CamsZcsxShcjxxMapper mapper;

    @Override
    public CamsZcsxShcjxx getByShid(String shid) {
        return mapper.getByShid(shid);
    }
}
