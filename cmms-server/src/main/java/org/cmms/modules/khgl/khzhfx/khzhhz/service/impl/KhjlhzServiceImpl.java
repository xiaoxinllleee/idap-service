package org.cmms.modules.khgl.khzhfx.khzhhz.service.impl;

import org.cmms.modules.khgl.khzhfx.khzhhz.entity.Khjlhz;
import org.cmms.modules.khgl.khzhfx.khzhhz.mapper.KhjlhzMapper;
import org.cmms.modules.khgl.khzhfx.khzhhz.service.IKhjlhzService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 客户转化客户经理汇总
 * @Author: jeecg-boot
 * @Date:   2023-04-26
 * @Version: V1.0
 */
@Service
public class KhjlhzServiceImpl extends ServiceImpl<KhjlhzMapper, Khjlhz> implements IKhjlhzService {

    @Override
    public void extract(String ksrq, String jsrq) {
        baseMapper.extract(ksrq,jsrq);
    }
}
