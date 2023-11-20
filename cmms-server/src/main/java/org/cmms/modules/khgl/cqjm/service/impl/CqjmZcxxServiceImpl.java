package org.cmms.modules.khgl.cqjm.service.impl;

import org.cmms.modules.khgl.cqjm.entity.CqjmZcxx;
import org.cmms.modules.khgl.cqjm.mapper.CqjmZcxxMapper;
import org.cmms.modules.khgl.cqjm.service.ICqjmZcxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 城区居民资产信息
 * @Author: jeecg-boot
 * @Date:   2020-02-22
 * @Version: V1.0
 */
@Service
public class CqjmZcxxServiceImpl extends ServiceImpl<CqjmZcxxMapper, CqjmZcxx> implements ICqjmZcxxService {

    @Autowired
    private CqjmZcxxMapper mapper;

    @Override
    public void deleteZcxxByZjhm(String zjhm) {
        mapper.deleteZcxxByZjhm(zjhm);
    }

}
