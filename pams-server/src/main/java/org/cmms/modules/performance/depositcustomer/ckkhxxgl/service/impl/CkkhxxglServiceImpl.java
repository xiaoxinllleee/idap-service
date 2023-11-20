package org.cmms.modules.performance.depositcustomer.ckkhxxgl.service.impl;

import org.cmms.modules.performance.depositcustomer.ckkhxxgl.entity.Ckkhxxgl;
import org.cmms.modules.performance.depositcustomer.ckkhxxgl.mapper.CkkhxxglMapper;
import org.cmms.modules.performance.depositcustomer.ckkhxxgl.service.ICkkhxxglService;
import org.cmms.modules.performance.depositcustomer.ckkhxxgl.vo.Clckkhxx;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 存款客户信息管理
 * @Author: jeecg-boot
 * @Date:   2023-02-28
 * @Version: V1.0
 */
@Service
public class CkkhxxglServiceImpl extends ServiceImpl<CkkhxxglMapper, Ckkhxxgl> implements ICkkhxxglService {
    @Override
    public void init() {
        baseMapper.init();
    }

    @Override
    public void rlckkhxx(String jgdm) {
        baseMapper.rlckkhxx(jgdm);
    }

}
