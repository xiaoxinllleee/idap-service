package org.cmms.modules.khgl.ckdkkh.service.impl;

import org.cmms.modules.khgl.ckdkkh.entity.ClDkkh;
import org.cmms.modules.khgl.ckdkkh.mapper.ClDkkhMapper;
import org.cmms.modules.khgl.ckdkkh.service.IClDkkhService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 存量贷款客户
 * @Author: jeecg-boot
 * @Date:   2022-09-26
 * @Version: V1.0
 */
@Service
public class ClDkkhServiceImpl extends ServiceImpl<ClDkkhMapper, ClDkkh> implements IClDkkhService {

    @Override
    public void pClDkkh() {
        baseMapper.pClDkkh();
    }
}
