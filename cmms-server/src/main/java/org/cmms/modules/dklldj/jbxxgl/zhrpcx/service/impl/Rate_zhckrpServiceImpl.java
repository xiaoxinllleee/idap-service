package org.cmms.modules.dklldj.jbxxgl.zhrpcx.service.impl;

import org.cmms.modules.dklldj.jbxxgl.khxxgl.mapper.Rate_khjbxxbMapper;
import org.cmms.modules.dklldj.jbxxgl.zhrpcx.entity.Rate_zhckrp;
import org.cmms.modules.dklldj.jbxxgl.zhrpcx.mapper.Rate_zhckrpMapper;
import org.cmms.modules.dklldj.jbxxgl.zhrpcx.service.IRate_zhckrpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.transaction.Transactional;
import java.util.Map;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-03-28
 * @Version: V1.0
 */
@Service
public class Rate_zhckrpServiceImpl extends ServiceImpl<Rate_zhckrpMapper, Rate_zhckrp> implements IRate_zhckrpService {
    @Autowired
    private Rate_zhckrpMapper nhjbxxMapper;


    @Override
    @Transactional
    public void extract(Map<String,String> tjyf) {
        nhjbxxMapper.extract(tjyf);
    }
}
