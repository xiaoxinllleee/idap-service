package org.cmms.modules.pad.shxxgl.service.impl;

import org.cmms.modules.pad.shxxgl.entity.CamsJbxxShzllb;
import org.cmms.modules.pad.shxxgl.mapper.CamsJbxxShzllbMapper;
import org.cmms.modules.pad.shxxgl.service.ICamsJbxxShzllbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 商户资料列表
 * @Author: jeecg-boot
 * @Date:   2020-08-29
 * @Version: V1.0
 */
@Service
public class CamsJbxxShzllbServiceImpl extends ServiceImpl<CamsJbxxShzllbMapper, CamsJbxxShzllb> implements ICamsJbxxShzllbService {
    @Autowired
    private CamsJbxxShzllbMapper mapper;

    @Override
    public List<CamsJbxxShzllb> getByShid(String shid) {
        return mapper.getByShid(shid);
    }
}
