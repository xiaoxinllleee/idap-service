package org.cmms.modules.jylrhs.csgl.zbk.service.impl;

import org.cmms.modules.jylrhs.csgl.zbk.entity.JylrhsZbk;
import org.cmms.modules.jylrhs.csgl.zbk.mapper.JylrhsZbkMapper;
import org.cmms.modules.jylrhs.csgl.zbk.service.IJylrhsZbkService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 经营利润核算（指标库）
 * @Author: jeecg-boot
 * @Date:   2023-06-06
 * @Version: V1.0
 */
@Service
public class JylrhsZbkServiceImpl extends ServiceImpl<JylrhsZbkMapper, JylrhsZbk> implements IJylrhsZbkService {

    @Override
    public Long duplicateCheckCount(String id) {
        return baseMapper.duplicateCheckCount(id);
    }
}
