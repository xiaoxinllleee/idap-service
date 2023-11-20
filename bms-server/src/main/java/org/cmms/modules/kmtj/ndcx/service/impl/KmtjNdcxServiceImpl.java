package org.cmms.modules.kmtj.ndcx.service.impl;

import org.cmms.modules.kmtj.ndcx.entity.KmtjNdcx;
import org.cmms.modules.kmtj.ndcx.mapper.KmtjNdcxMapper;
import org.cmms.modules.kmtj.ndcx.service.IKmtjNdcxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 年度查询
 * @Author: jeecg-boot
 * @Date:   2023-03-24
 * @Version: V1.0
 */
@Service
public class KmtjNdcxServiceImpl extends ServiceImpl<KmtjNdcxMapper, KmtjNdcx> implements IKmtjNdcxService {

    @Override
    public void pYgmrscs(String tjyf) {
        baseMapper.pYgmrscs(tjyf);
    }
}
