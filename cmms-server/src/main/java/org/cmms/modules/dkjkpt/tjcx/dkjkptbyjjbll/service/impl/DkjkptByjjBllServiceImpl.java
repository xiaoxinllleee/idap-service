package org.cmms.modules.dkjkpt.tjcx.dkjkptbyjjbll.service.impl;

import org.cmms.modules.dkjkpt.tjcx.dkjkptbyjjbll.entity.DkjkptByjjBll;
import org.cmms.modules.dkjkpt.tjcx.dkjkptbyjjbll.mapper.DkjkptByjjBllMapper;
import org.cmms.modules.dkjkpt.tjcx.dkjkptbyjjbll.service.IDkjkptByjjBllService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 本月将进关注
 * @Author: jeecg-boot
 * @Date:   2023-08-31
 * @Version: V1.0
 */
@Service
public class DkjkptByjjBllServiceImpl extends ServiceImpl<DkjkptByjjBllMapper, DkjkptByjjBll> implements IDkjkptByjjBllService {

    @Override
    public void extract(String tjyf) {
        baseMapper.extract(tjyf);
    }
}
