package org.cmms.modules.jgywsj.jgkmsj.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.jgywsj.jgkmsj.entity.JgkmsjCk;
import org.cmms.modules.jgywsj.jgkmsj.entity.TbTjfxJgkmsj;
import org.cmms.modules.jgywsj.jgkmsj.mapper.JgkmsjMapper;
import org.cmms.modules.jgywsj.jgkmsj.service.IJgkmsjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;

/**
 * @Description: 机构科目数据
 * @Author: jeecg-boot
 * @Date:   2021-05-14
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class JgkmsjServiceImpl extends ServiceImpl<JgkmsjMapper, TbTjfxJgkmsj> implements IJgkmsjService {
    @Autowired
    private JgkmsjMapper jgkmsjMapper;
    @Override
    public JgkmsjCk queryCkxxByTjrqAndZzbz(String tjrq, String zzbz) {
        return jgkmsjMapper.queryCkxxByTjrqAndZzbz(tjrq, zzbz);
    }

    @Override
    public List<Date> getLatestTjrq(String zzbz) {
        return jgkmsjMapper.getLatestTjrq(zzbz);
    }
}
