package org.cmms.modules.khxxgl.khflgl.nhxq.service.impl;

import org.cmms.modules.khxxgl.khflgl.nhxq.entity.NhJzyx;
import org.cmms.modules.khxxgl.khflgl.nhxq.mapper.NhJzyxMapper;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhJzyxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 农户精准营销试图
 * @Author: jeecg-boot
 * @Date:   2023-05-19
 * @Version: V1.0
 */
@Service
public class NhJzyxServiceImpl extends ServiceImpl<NhJzyxMapper, NhJzyx> implements INhJzyxService {

    @Override
    public void jzyxInit(String nhid) {
        baseMapper.jzyxInit(nhid);
    }
}
