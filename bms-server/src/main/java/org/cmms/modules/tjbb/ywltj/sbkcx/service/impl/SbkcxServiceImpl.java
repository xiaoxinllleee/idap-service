package org.cmms.modules.tjbb.ywltj.sbkcx.service.impl;

import org.cmms.modules.tjbb.ywltj.sbkcx.entity.Sbkcx;
import org.cmms.modules.tjbb.ywltj.sbkcx.mapper.SbkcxMapper;
import org.cmms.modules.tjbb.ywltj.sbkcx.service.ISbkcxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 社保卡查询
 * @Author: jeecg-boot
 * @Date:   2021-08-25
 * @Version: V1.0
 */
@Service
public class SbkcxServiceImpl extends ServiceImpl<SbkcxMapper, Sbkcx> implements ISbkcxService {

    @Override
    public void pSbkcx(String tjyf) {
        baseMapper.pSbkcx(tjyf);
    }
}
