package org.cmms.modules.tjbb.ckywfx.dqhzrpcx.service.impl;

import org.cmms.modules.tjbb.ckywfx.dqhzrpcx.entity.Dqhzrpcx;
import org.cmms.modules.tjbb.ckywfx.dqhzrpcx.mapper.DqhzrpcxMapper;
import org.cmms.modules.tjbb.ckywfx.dqhzrpcx.service.IDqhzrpcxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 定期汇总日平查询
 * @Author: jeecg-boot
 * @Date:   2021-08-20
 * @Version: V1.0
 */
@Service
public class DqhzrpcxServiceImpl extends ServiceImpl<DqhzrpcxMapper, Dqhzrpcx> implements IDqhzrpcxService {

    @Override
    public void pDqhzrpcx(String tjyf) {
        baseMapper.pDqhzrpcx(tjyf);
    }
}
