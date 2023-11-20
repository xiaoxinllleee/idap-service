package org.cmms.modules.khxxgl.khflgl.nhxq.service.impl;

import org.cmms.modules.khxxgl.khflgl.nhxq.entity.PjsxHmd;
import org.cmms.modules.khxxgl.khflgl.nhxq.mapper.PjsxHmdMapper;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.IPjsxHmdService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 评级授信黑名单
 * @Author: jeecg-boot
 * @Date:   2023-11-15
 * @Version: V1.0
 */
@Service
public class PjsxHmdServiceImpl extends ServiceImpl<PjsxHmdMapper, PjsxHmd> implements IPjsxHmdService {

    @Override
    public void updateStatus(String id, String status) {
        baseMapper.updateStatus(id, status);

    }
}
