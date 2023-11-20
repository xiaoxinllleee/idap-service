package org.cmms.modules.kmtj.jdcx.service.impl;

import org.cmms.modules.kmtj.jdcx.entity.KmtjJdcx;
import org.cmms.modules.kmtj.jdcx.mapper.KmtjJdcxMapper;
import org.cmms.modules.kmtj.jdcx.service.IKmtjJdcxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 季度查询
 * @Author: jeecg-boot
 * @Date:   2023-03-24
 * @Version: V1.0
 */
@Service
public class KmtjJdcxServiceImpl extends ServiceImpl<KmtjJdcxMapper, KmtjJdcx> implements IKmtjJdcxService {
    @Override
    public void pYgmrscs(String tjyf) {
        baseMapper.pYgmrscs(tjyf);
    }
}
