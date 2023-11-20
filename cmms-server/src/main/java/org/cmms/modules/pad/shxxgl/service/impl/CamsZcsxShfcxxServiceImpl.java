package org.cmms.modules.pad.shxxgl.service.impl;

import org.cmms.modules.pad.shxxgl.entity.CamsZcsxShfcxx;
import org.cmms.modules.pad.shxxgl.mapper.CamsZcsxShfcxxMapper;
import org.cmms.modules.pad.shxxgl.service.ICamsZcsxShfcxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 商户房产信息
 * @Author: jeecg-boot
 * @Date:   2020-08-29
 * @Version: V1.0
 */
@Service
public class CamsZcsxShfcxxServiceImpl extends ServiceImpl<CamsZcsxShfcxxMapper, CamsZcsxShfcxx> implements ICamsZcsxShfcxxService {
    @Autowired
    private CamsZcsxShfcxxMapper mapper;

    @Override
    public List<CamsZcsxShfcxx> getByShid(String shid) {
        return mapper.getByShid(shid);
    }
}
