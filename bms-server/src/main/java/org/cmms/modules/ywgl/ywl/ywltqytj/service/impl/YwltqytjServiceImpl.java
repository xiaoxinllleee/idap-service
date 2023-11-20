package org.cmms.modules.ywgl.ywl.ywltqytj.service.impl;

import org.cmms.modules.ywgl.ywl.ywltqytj.entity.Ywltqytj;
import org.cmms.modules.ywgl.ywl.ywltqytj.mapper.YwltqytjMapper;
import org.cmms.modules.ywgl.ywl.ywltqytj.service.IYwltqytjService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;

/**
 * @Description: 业务量提取与统计
 * @Author: jeecg-boot
 * @Date:   2021-09-29
 * @Version: V1.0
 */
@Service
public class YwltqytjServiceImpl extends ServiceImpl<YwltqytjMapper, Ywltqytj> implements IYwltqytjService {
    @Override
    public void pYwltqytj(String tjyf, String zzbz) {
        baseMapper.pYwltqytj(tjyf, zzbz);
    }

    @Override
    public String getMaxFpid() {
        return baseMapper.getMaxFpid();
    }

    @Override
    public String getAssessParamValue(String paramcode) {
        return baseMapper.getAssessParamValue(paramcode);
    }

    @Override
    public String conversionConfigInfo(Date beginOfMonth) {
        return baseMapper.conversionConfigInfo(beginOfMonth);
    }
}
