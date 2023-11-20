package org.cmms.modules.tjbb.ckywfx.ckkhcdknrptj.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.tjbb.ckywfx.ckkhcdknrptj.entity.Ckkhcdknrptj;
import org.cmms.modules.tjbb.ckywfx.ckkhcdknrptj.mapper.CkkhcdknrptjMapper;
import org.cmms.modules.tjbb.ckywfx.ckkhcdknrptj.service.ICkkhcdknrptjService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 存款客户存贷款年日平统计
 * @Author: jeecg-boot
 * @Date:   2021-08-19
 * @Version: V1.0
 */
@Service
public class CkkhcdknrptjServiceImpl extends ServiceImpl<CkkhcdknrptjMapper, Ckkhcdknrptj> implements ICkkhcdknrptjService {

    @Override
    public void pCkkhcdknrptj(String tjyf) {
        baseMapper.pCkkhcdknrptj(tjyf);
    }
}
