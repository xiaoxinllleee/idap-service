package org.cmms.modules.dklldj.tjfxgl.qylldb.service.impl;

import org.cmms.modules.dklldj.tjfxgl.qylldb.entity.Dhdkmx;
import org.cmms.modules.dklldj.tjfxgl.qylldb.mapper.DhdkmxMapper;
import org.cmms.modules.dklldj.tjfxgl.qylldb.service.IDhdkmxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;

/**
 * @Description: 单户贷款明细
 * @Author: Penghr
 * @Date:   2020-03-28
 * @Version: V1.0
 */
@Service
public class DhdkmxServiceImpl extends ServiceImpl<DhdkmxMapper, Dhdkmx> implements IDhdkmxService {

    @Autowired
    private DhdkmxMapper mapper;

    @Override
    public List<Dhdkmx> queryDhdkmx(Date tjyf, String jgdm, String zjhm, String ywbh) {
        return mapper.queryDhdkmx(tjyf, jgdm, zjhm, ywbh);
    }
}
