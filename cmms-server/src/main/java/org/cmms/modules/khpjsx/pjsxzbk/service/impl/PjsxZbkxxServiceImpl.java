package org.cmms.modules.khpjsx.pjsxzbk.service.impl;

import org.cmms.modules.khpjsx.pjsxzbk.entity.PjsxZbkxx;
import org.cmms.modules.khpjsx.pjsxzbk.mapper.PjsxZbkxxMapper;
import org.cmms.modules.khpjsx.pjsxzbk.service.IPjsxZbkxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

/**
 * @Description: 评级授信指标库
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
@Service
public class PjsxZbkxxServiceImpl extends ServiceImpl<PjsxZbkxxMapper, PjsxZbkxx> implements IPjsxZbkxxService {

    @Autowired
    private PjsxZbkxxMapper mapper;

    @Override
    public PjsxZbkxx queryZbid(Map<String, String> sql) {
        return mapper.queryZbid(sql);
    }
}
