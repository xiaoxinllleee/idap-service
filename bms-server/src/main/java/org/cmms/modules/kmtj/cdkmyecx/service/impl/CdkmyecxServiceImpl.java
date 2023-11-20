package org.cmms.modules.kmtj.cdkmyecx.service.impl;

import org.cmms.modules.kmtj.cdkmyecx.entity.Cdkmyecx;
import org.cmms.modules.kmtj.cdkmyecx.mapper.CdkmyecxMapper;
import org.cmms.modules.kmtj.cdkmyecx.service.ICdkmyecxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 存贷科目余额查询
 * @Author: jeecg-boot
 * @Date:   2023-03-24
 * @Version: V1.0
 */
@Service
public class CdkmyecxServiceImpl extends ServiceImpl<CdkmyecxMapper, Cdkmyecx> implements ICdkmyecxService {

    @Override
    public void pYgmrscs(String tjyf) {
        baseMapper.pYgmrscs(tjyf);
    }
}
