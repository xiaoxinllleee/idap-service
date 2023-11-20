package org.cmms.modules.tjfx.zfsjtj.zfyxtj.nh.gr.service.impl;

import org.cmms.modules.tjfx.zfsjtj.zfyxtj.nh.gr.entity.KhjlZfyxtj;
import org.cmms.modules.tjfx.zfsjtj.zfyxtj.nh.gr.mapper.KhjlZfyxtjMapper;
import org.cmms.modules.tjfx.zfsjtj.zfyxtj.nh.gr.service.IKhjlZfyxtjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 客户经理走访营销统计
 * @Author: jeecg-boot
 * @Date:   2022-05-23
 * @Version: V1.0
 */
@Service
public class KhjlZfyxtjServiceImpl extends ServiceImpl<KhjlZfyxtjMapper, KhjlZfyxtj> implements IKhjlZfyxtjService {
    @Autowired
    private KhjlZfyxtjMapper khjlZfyxtjMapper;

    @Override
    public void init(String tjrq) {
        khjlZfyxtjMapper.init(tjrq);
    }

    @Override
    public void batchInit(String beginDate, String endDate) {
        khjlZfyxtjMapper.batchInit(beginDate, endDate);
    }


    @Override
    public void syxyInit(String zdrkrq){
        khjlZfyxtjMapper.syxyInit(zdrkrq);
    }
}
