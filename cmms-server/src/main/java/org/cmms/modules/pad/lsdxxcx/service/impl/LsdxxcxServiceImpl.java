package org.cmms.modules.pad.lsdxxcx.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.pad.lsdxxcx.entity.Lsdxxcx;
import org.cmms.modules.pad.lsdxxcx.mapper.LsdxxcxMapper;
import org.cmms.modules.pad.lsdxxcx.service.ILsdxxcxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 流水贷信息查询
 * @Author: jeecg-boot
 * @Date:   2023-05-24
 * @Version: V1.0
 */
@Service
public class LsdxxcxServiceImpl extends ServiceImpl<LsdxxcxMapper, Lsdxxcx> implements ILsdxxcxService {

    @Override
    public IPage<Lsdxxcx> getMaxData(Page page, String khmc, String zjhm) {
        return baseMapper.getMaxData(page, khmc, zjhm);
    }
}
