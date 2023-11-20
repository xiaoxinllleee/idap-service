package org.cmms.modules.khlc.khfagl.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.khlc.khfagl.entity.PmaAScheme;
import org.cmms.modules.khlc.khfagl.mapper.PmaASchemeMapper;
import org.cmms.modules.khlc.khfagl.service.IPmaASchemeService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 考核方案基础信息表
 * @Author: jeecg-boot
 * @Date:   2021-01-29
 * @Version: V1.0
 */
@Service

public class PmaASchemeServiceImpl extends ServiceImpl<PmaASchemeMapper, PmaAScheme> implements IPmaASchemeService {

    @Override
    public IPage<PmaAScheme> getSchenmeByJdId(Page<PmaAScheme> page, String jdid, String famc) {
        return baseMapper.getSchenmeByJdId(page, jdid,famc);

    }
}
