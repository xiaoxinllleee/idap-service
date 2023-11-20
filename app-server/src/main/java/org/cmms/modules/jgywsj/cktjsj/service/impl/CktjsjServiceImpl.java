package org.cmms.modules.jgywsj.cktjsj.service.impl;

import org.cmms.modules.jgywsj.cktjsj.entity.TbTjfxCktjsj;
import org.cmms.modules.jgywsj.cktjsj.mapper.CktjsjMapper;
import org.cmms.modules.jgywsj.cktjsj.service.ICktjsjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;

/**
 * @Description: 存款统计数据
 * @Author: jeecg-boot
 * @Date:   2021-05-28
 * @Version: V1.0
 */
@Service
public class CktjsjServiceImpl extends ServiceImpl<CktjsjMapper, TbTjfxCktjsj> implements ICktjsjService {
    @Autowired
    private CktjsjMapper cktjsjMapper;
    @Override
    public List<Date> getLatestTjrq(String zzbz) {
        return cktjsjMapper.getLatestTjrq(zzbz);
    }

    @Override
    public TbTjfxCktjsj queryCktjsjByTjrqAndZzbz(String tjrq, String zzbz) {
        return cktjsjMapper.queryCktjsjByTjrqAndZzbz(tjrq, zzbz);
    }
}
