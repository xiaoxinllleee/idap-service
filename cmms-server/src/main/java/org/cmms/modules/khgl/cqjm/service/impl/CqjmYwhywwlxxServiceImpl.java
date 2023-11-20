package org.cmms.modules.khgl.cqjm.service.impl;

import org.cmms.modules.khgl.cqjm.entity.CqjmYwhywwlxx;
import org.cmms.modules.khgl.cqjm.mapper.CqjmYwhywwlxxMapper;
import org.cmms.modules.khgl.cqjm.service.ICqjmYwhywwlxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 城区居民与我行业务往来信息
 * @Author: jeecg-boot
 * @Date:   2020-04-02
 * @Version: V1.0
 */
@Service
public class CqjmYwhywwlxxServiceImpl extends ServiceImpl<CqjmYwhywwlxxMapper, CqjmYwhywwlxx> implements ICqjmYwhywwlxxService {

    @Autowired
    private CqjmYwhywwlxxMapper mapper;

    @Override
    public void deleteYwwlxxByZjhm(String zjhm) {
        mapper.deleteYwwlxxByZjhm(zjhm);
    }
}
