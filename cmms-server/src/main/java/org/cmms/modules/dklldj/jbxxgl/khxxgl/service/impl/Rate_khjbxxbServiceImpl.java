package org.cmms.modules.dklldj.jbxxgl.khxxgl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.dklldj.jbxxgl.khxxgl.entity.Rate_khjbxxb;
import org.cmms.modules.dklldj.jbxxgl.khxxgl.mapper.Rate_khjbxxbMapper;
import org.cmms.modules.dklldj.jbxxgl.khxxgl.service.IRate_khjbxxbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-03-04
 * @Version: V1.0
 */
@Service
public class Rate_khjbxxbServiceImpl extends ServiceImpl<Rate_khjbxxbMapper, Rate_khjbxxb> implements IRate_khjbxxbService {
    @Autowired
    private Rate_khjbxxbMapper nhjbxxMapper;


    @Override
    @Transactional
    public void extract() {
        nhjbxxMapper.extract();
    }

    @Override
    public Rate_khjbxxb getNameByZjhm(String zjhm) {
        Rate_khjbxxb rate_khjbxxb = nhjbxxMapper.selectById(zjhm);
        if (rate_khjbxxb != null && rate_khjbxxb.getKhmc() != null) {
            return rate_khjbxxb;
        } else {
            return null;
        }
    }
}
