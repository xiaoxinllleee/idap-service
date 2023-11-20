package org.cmms.modules.ywgl.ywl.ywlhzcx.service.impl;

import org.cmms.modules.ywgl.ywl.ywlhzcx.entity.Ywlhzcx;
import org.cmms.modules.ywgl.ywl.ywlhzcx.mapper.YwlhzcxMapper;
import org.cmms.modules.ywgl.ywl.ywlhzcx.service.IYwlhzcxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 业务量汇总查询
 * @Author: jeecg-boot
 * @Date:   2021-12-07
 * @Version: V1.0
 */
@Service
public class YwlhzcxServiceImpl extends ServiceImpl<YwlhzcxMapper, Ywlhzcx> implements IYwlhzcxService {

    @Override
    public void pYwlhzcx(String tjyf) {
        baseMapper.pYwlhzcx(tjyf);
    }
}
