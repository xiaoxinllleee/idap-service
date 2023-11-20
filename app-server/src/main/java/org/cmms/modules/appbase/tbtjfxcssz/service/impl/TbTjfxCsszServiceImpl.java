package org.cmms.modules.appbase.tbtjfxcssz.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.checkerframework.checker.units.qual.A;
import org.cmms.modules.appbase.tbtjfxcssz.entity.TbTjfxCssz;
import org.cmms.modules.appbase.tbtjfxcssz.mapper.TbTjfxCsszMapper;
import org.cmms.modules.appbase.tbtjfxcssz.service.ITbTjfxCsszService;
import org.cmms.modules.utils.LogsUtil;
import org.hibernate.hql.spi.id.cte.AbstractCteValuesListBulkIdHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Description: 参数设置
 * @Author: jeecg-boot
 * @Date:   2021-05-20
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class TbTjfxCsszServiceImpl extends ServiceImpl<TbTjfxCsszMapper, TbTjfxCssz> implements ITbTjfxCsszService {

    @Autowired
    TbTjfxCsszMapper tbTjfxCsszMapper;
    @Override
    public String getValue(String key) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("csbm",key);
        List<TbTjfxCssz> list = tbTjfxCsszMapper.selectList(queryWrapper);
        if (CollUtil.isNotEmpty(list))
            return list.get(0).getCsz();
        return null;
    }

    @Override
    public Date getTheMaxDate() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("CSBM","P00001");
        List<TbTjfxCssz> list = tbTjfxCsszMapper.selectList(queryWrapper);
        if(list!=null && list.size()>0) {
            try {
                TbTjfxCssz cs = list.get(0);
                String csz = cs.getCsz();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                return sdf.parse(csz);
            } catch (ParseException e) {
                e.printStackTrace();
                log.error(LogsUtil.getStackTrace(e));
            }
        }
        HashMap hashMap = new HashMap();
        //红黑树的特性

        return null;
    }
}
