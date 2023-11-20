package org.cmms.modules.khpjsx.pjsxsjx.service.impl;

import org.cmms.modules.khpjsx.pjsxsjx.entity.PjsxSjxxx;
import org.cmms.modules.khpjsx.pjsxsjx.mapper.PjsxSjxxxMapper;
import org.cmms.modules.khpjsx.pjsxsjx.service.IPjsxSjxxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

/**
 * @Description: 评级授信数据项
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
@Service
public class PjsxSjxxxServiceImpl extends ServiceImpl<PjsxSjxxxMapper, PjsxSjxxx> implements IPjsxSjxxxService {

    @Autowired
    private PjsxSjxxxMapper mapper;

    @Override
    public PjsxSjxxx querySjxid(Map<String, String> sql) {
        return mapper.querySjxid(sql);
    }
}
