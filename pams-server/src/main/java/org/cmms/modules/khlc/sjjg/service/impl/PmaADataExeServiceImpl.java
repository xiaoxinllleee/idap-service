package org.cmms.modules.khlc.sjjg.service.impl;

import org.cmms.modules.khlc.sjjg.entity.PmaADataExe;
import org.cmms.modules.khlc.sjjg.mapper.PmaADataExeMapper;
import org.cmms.modules.khlc.sjjg.service.IPmaADataExeService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 数据加工功能
 * @Author: jeecg-boot
 * @Date:   2023-03-08
 * @Version: V1.0
 */
@Service
public class PmaADataExeServiceImpl extends ServiceImpl<PmaADataExeMapper, PmaADataExe> implements IPmaADataExeService {

    @Override
    @Transactional
    public void extract(String spname,String sjrq,String rwid) {
        baseMapper.extract(spname,sjrq,rwid);
    }
}
