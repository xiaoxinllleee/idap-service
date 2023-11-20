package org.cmms.modules.khgl.cqjm.service.impl;

import org.cmms.modules.khgl.cqjm.entity.CqjmZcfzqk;
import org.cmms.modules.khgl.cqjm.mapper.CqjmZcfzqkMapper;
import org.cmms.modules.khgl.cqjm.service.ICqjmZcfzqkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 城区居民资产负债情况
 * @Author: jeecg-boot
 * @Date:   2020-02-24
 * @Version: V1.0
 */
@Service
public class CqjmZcfzqkServiceImpl extends ServiceImpl<CqjmZcfzqkMapper, CqjmZcfzqk> implements ICqjmZcfzqkService {

    @Autowired
    private CqjmZcfzqkMapper mapper;

    @Override
    public List<CqjmZcfzqk> selectByZjhm(String zjhm) {
        return mapper.selectByZjhm(zjhm);
    }

    @Override
    public void deleteZcfzqkByZjhm(String zjhm) {
        mapper.deleteZcfzqkByZjhm(zjhm);
    }
}
