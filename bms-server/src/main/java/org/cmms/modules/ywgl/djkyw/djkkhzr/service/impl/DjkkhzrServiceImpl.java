package org.cmms.modules.ywgl.djkyw.djkkhzr.service.impl;

import org.cmms.modules.ywgl.djkyw.djkkhzr.entity.Djkkhzr;
import org.cmms.modules.ywgl.djkyw.djkkhzr.mapper.DjkkhzrMapper;
import org.cmms.modules.ywgl.djkyw.djkkhzr.service.IDjkkhzrService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 贷记卡考核责任
 * @Author: jeecg-boot
 * @Date:   2021-12-06
 * @Version: V1.0
 */
@Service
public class DjkkhzrServiceImpl extends ServiceImpl<DjkkhzrMapper, Djkkhzr> implements IDjkkhzrService {

    @Override
    public void CallPkgUpdateDkzrr(String tjrq) {
        baseMapper.CallPkgUpdateDkzrr(tjrq);
    }
}
