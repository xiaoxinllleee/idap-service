package org.cmms.modules.pad.shxxgl.service.impl;

import org.cmms.modules.pad.shxxgl.entity.CamsZcsxShpjsxxx;
import org.cmms.modules.pad.shxxgl.mapper.CamsZcsxShpjsxxxMapper;
import org.cmms.modules.pad.shxxgl.service.ICamsZcsxShpjsxxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 商户评级授信信息
 * @Author: jeecg-boot
 * @Date:   2020-08-29
 * @Version: V1.0
 */
@Service
public class CamsZcsxShpjsxxxServiceImpl extends ServiceImpl<CamsZcsxShpjsxxxMapper, CamsZcsxShpjsxxx> implements ICamsZcsxShpjsxxxService {
    @Autowired
    private CamsZcsxShpjsxxxMapper mapper;

    @Override
    public CamsZcsxShpjsxxx getByShid(String shid) {
        return mapper.getByShid(shid);
    }
}
