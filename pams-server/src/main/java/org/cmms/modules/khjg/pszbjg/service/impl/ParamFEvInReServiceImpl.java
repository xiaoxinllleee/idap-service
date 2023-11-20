package org.cmms.modules.khjg.pszbjg.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.khjg.pszbjg.entity.GzQueryVo;
import org.cmms.modules.khjg.pszbjg.entity.ParamFEvInRe;
import org.cmms.modules.khjg.pszbjg.mapper.ParamFEvInReMapper;
import org.cmms.modules.khjg.pszbjg.service.IParamFEvInReService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 派生指标结果
 * @Author: jeecg-boot
 * @Date:   2021-05-11
 * @Version: V1.0
 */
@Service
public class ParamFEvInReServiceImpl extends ServiceImpl<ParamFEvInReMapper, ParamFEvInRe> implements IParamFEvInReService {

    @Override
    public IPage<ParamFEvInRe> getGzjg(Page page, GzQueryVo gzQueryVo) {
        return baseMapper.getgz(page,gzQueryVo);
    }

    @Override
    public IPage<ParamFEvInRe> getgzbyfa(Page page, GzQueryVo gzQueryVo) {
        return baseMapper.getgzbyfa(page,gzQueryVo);
    }

    @Override
    public IPage<ParamFEvInRe> getgzbyjg(Page page, GzQueryVo gzQueryVo) {
        return baseMapper.getgzbyjg(page,gzQueryVo);
    }
}
