package org.cmms.modules.jgywsj.dktjsj.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.jgywsj.dktjsj.entity.Dktjsj;
import org.cmms.modules.jgywsj.dktjsj.entity.TbTjfxDktjsj;
import org.cmms.modules.jgywsj.dktjsj.mapper.DktjsjMapper;
import org.cmms.modules.jgywsj.dktjsj.service.IDktjsjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;

/**
 * @Description: 贷款统计数据
 * @Author: jeecg-boot
 * @Date:   2021-05-14
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class DktjsjServiceImpl extends ServiceImpl<DktjsjMapper, TbTjfxDktjsj> implements IDktjsjService {
    @Autowired
    private DktjsjMapper dktjsjMapper;
    @Override
    public List<Date> getLatestTjrq(String zzbz) {
        return dktjsjMapper.getLatestTjrq(zzbz);
    }

    @Override
    public Dktjsj queryDkxxByTjrqAndZzbz(String tjrq, String zzbz) {
        return dktjsjMapper.queryDkxxByTjrqAndZzbz(tjrq, zzbz);
    }
}
