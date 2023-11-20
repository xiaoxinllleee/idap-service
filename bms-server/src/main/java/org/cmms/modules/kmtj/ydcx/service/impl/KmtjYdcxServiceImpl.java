package org.cmms.modules.kmtj.ydcx.service.impl;

import org.cmms.modules.kmtj.ydcx.entity.KmtjYdcx;
import org.cmms.modules.kmtj.ydcx.mapper.KmtjYdcxMapper;
import org.cmms.modules.kmtj.ydcx.service.IKmtjYdcxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 月度查询
 * @Author: jeecg-boot
 * @Date:   2023-03-24
 * @Version: V1.0
 */
@Service
public class KmtjYdcxServiceImpl extends ServiceImpl<KmtjYdcxMapper, KmtjYdcx> implements IKmtjYdcxService {

    @Override
    public void pYgmrscs(String tjyf) {
        baseMapper.pYgmrscs(tjyf);
    }
}
