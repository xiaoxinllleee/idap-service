package org.cmms.modules.tjbb.ywltj.zzsbywl.service.impl;

import org.cmms.modules.tjbb.ywltj.zzsbywl.entity.Zzsbywl;
import org.cmms.modules.tjbb.ywltj.zzsbywl.mapper.ZzsbywlMapper;
import org.cmms.modules.tjbb.ywltj.zzsbywl.service.IZzsbywlService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 自助设备业务量
 * @Author: jeecg-boot
 * @Date:   2021-08-24
 * @Version: V1.0
 */
@Service
public class ZzsbywlServiceImpl extends ServiceImpl<ZzsbywlMapper, Zzsbywl> implements IZzsbywlService {

    @Override
    public void pZzsbywl(String tjwd, String tjyf) {
        baseMapper.pZzsbywl(tjwd, tjyf);
    }
}
