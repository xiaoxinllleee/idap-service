package org.cmms.modules.dklldj.lldjgl.glzhgl.service.impl;

import org.cmms.modules.dklldj.jbxxgl.glrxxgl.mapper.Rate_khglrxxbMapper;
import org.cmms.modules.dklldj.lldjgl.glzhgl.entity.rateKhzhglxxb;
import org.cmms.modules.dklldj.lldjgl.glzhgl.mapper.rateKhzhglxxbMapper;
import org.cmms.modules.dklldj.lldjgl.glzhgl.service.IrateKhzhglxxbService;
import org.cmms.modules.khgl.dkkh.entity.CbsBormBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-03-30
 * @Version: V1.0
 */
@Service
public class rateKhzhglxxbServiceImpl extends ServiceImpl<rateKhzhglxxbMapper, rateKhzhglxxb> implements IrateKhzhglxxbService {
    @Autowired
    private rateKhzhglxxbMapper nhjbxxMapper;

    @Override
    @Transactional
    public void extract(String zjhm) {
        nhjbxxMapper.extract(zjhm);
    }
}
