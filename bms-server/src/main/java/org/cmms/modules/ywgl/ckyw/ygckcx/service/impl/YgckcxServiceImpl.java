package org.cmms.modules.ywgl.ckyw.ygckcx.service.impl;

import org.cmms.modules.ywgl.ckyw.ygckcx.entity.Ygckcx;
import org.cmms.modules.ywgl.ckyw.ygckcx.mapper.YgckcxMapper;
import org.cmms.modules.ywgl.ckyw.ygckcx.service.IYgckcxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 员工存款查询
 * @Author: jeecg-boot
 * @Date:   2021-10-09
 * @Version: V1.0
 */
@Service
public class YgckcxServiceImpl extends ServiceImpl<YgckcxMapper, Ygckcx> implements IYgckcxService {

    @Override
    public void pYgckcx(String tjyf) {
        baseMapper.pYgckcx(tjyf);
    }
}
