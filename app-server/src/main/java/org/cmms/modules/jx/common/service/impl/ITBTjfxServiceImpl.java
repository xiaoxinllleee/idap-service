package org.cmms.modules.jx.common.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.modules.appbase.tbtjfxcssz.service.ITbTjfxCsszService;
import org.cmms.modules.jx.common.entity.TbTjfxQhdksj;
import org.cmms.modules.jx.common.mapper.TbTjfxQhdksjMapper;
import org.cmms.modules.jx.common.service.ITBTjfxService;
import org.cmms.modules.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description //TODO
 * @Date 2020/11/8 9:36
 * @Author huangwb
 **/
@Service
@DS("eweb")
public class ITBTjfxServiceImpl implements ITBTjfxService {
    @Autowired
    private TbTjfxQhdksjMapper tbTjfxQhdksjMapper;
    @Autowired
    @Lazy
    private ITbTjfxCsszService iTbTjfxCsszService;

    @Override
    public TbTjfxQhdksj getBankWideLoans() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("tjrq", DateUtil.stringToDate(iTbTjfxCsszService.getValue("P00001"), "yyyyMMdd"));
        List<TbTjfxQhdksj> list = tbTjfxQhdksjMapper.selectList(queryWrapper);
        if (CollUtil.isNotEmpty(list))
            return list.get(0);
        return null;
    }


}
