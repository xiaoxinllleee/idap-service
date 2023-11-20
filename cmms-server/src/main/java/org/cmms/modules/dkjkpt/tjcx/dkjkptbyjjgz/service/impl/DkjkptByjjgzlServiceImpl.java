package org.cmms.modules.dkjkpt.tjcx.dkjkptbyjjgz.service.impl;

import org.cmms.modules.dkjkpt.tjcx.dkjkptbyjjgz.entity.DkjkptByjjgzl;
import org.cmms.modules.dkjkpt.tjcx.dkjkptbyjjgz.mapper.DkjkptByjjgzlMapper;
import org.cmms.modules.dkjkpt.tjcx.dkjkptbyjjgz.service.IDkjkptByjjgzlService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 本月将进关注
 * @Author: jeecg-boot
 * @Date:   2023-08-31
 * @Version: V1.0
 */
@Service
public class DkjkptByjjgzlServiceImpl extends ServiceImpl<DkjkptByjjgzlMapper, DkjkptByjjgzl> implements IDkjkptByjjgzlService {

    @Override
    public void extract(String tjyf) {
        baseMapper.extract(tjyf);
    }
}
