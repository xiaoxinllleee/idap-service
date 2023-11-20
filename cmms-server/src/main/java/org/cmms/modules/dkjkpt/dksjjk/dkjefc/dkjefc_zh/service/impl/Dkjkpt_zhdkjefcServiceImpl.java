package org.cmms.modules.dkjkpt.dksjjk.dkjefc.dkjefc_zh.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.dksjjk.dkjefc.dkjefc_zh.entity.Dkjkpt_zhdkjefc;
import org.cmms.modules.dkjkpt.dksjjk.dkjefc.dkjefc_zh.mapper.Dkjkpt_zhdkjefcMapper;
import org.cmms.modules.dkjkpt.dksjjk.dkjefc.dkjefc_zh.service.IDkjkpt_zhdkjefcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

/**
 * @Description: 贷款金额分成_支行
 * @Author: jeecg-boot
 * @Date:   2020-11-06
 * @Version: V1.0
 */
@Service
public class Dkjkpt_zhdkjefcServiceImpl extends ServiceImpl<Dkjkpt_zhdkjefcMapper, Dkjkpt_zhdkjefc> implements IDkjkpt_zhdkjefcService {

    @Autowired
    private Dkjkpt_zhdkjefcMapper dkjkpt_zhdkjefcMapper;
    @Override
    public void InitDataToQh(Map<String, String> sql) {
        dkjkpt_zhdkjefcMapper.InitDataToQh(sql);
    }
}
