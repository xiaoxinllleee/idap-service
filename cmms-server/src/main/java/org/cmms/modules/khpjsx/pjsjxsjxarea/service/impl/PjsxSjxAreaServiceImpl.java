package org.cmms.modules.khpjsx.pjsjxsjxarea.service.impl;

import org.cmms.modules.khpjsx.pjsjxsjxarea.entity.PjsxSjxArea;
import org.cmms.modules.khpjsx.pjsjxsjxarea.mapper.PjsxSjxAreaMapper;
import org.cmms.modules.khpjsx.pjsjxsjxarea.service.IPjsxSjxAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

/**
 * @Description: 区域数据项
 * @Author: jeecg-boot
 * @Date:   2020-01-17
 * @Version: V1.0
 */
@Service
public class PjsxSjxAreaServiceImpl extends ServiceImpl<PjsxSjxAreaMapper, PjsxSjxArea> implements IPjsxSjxAreaService {

    @Autowired
    private PjsxSjxAreaMapper mapper;

    @Override
    public PjsxSjxArea querySjxid(Map<String, String> sql) {
        return mapper.querySjxid(sql);
    }
}
