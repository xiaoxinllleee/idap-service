package org.cmms.modules.gzap.gzrl.service.impl;

import org.cmms.modules.gzap.gzrl.mapper.GzrlMapper;
import org.cmms.modules.gzap.gzrl.service.IGzrlService;
import org.cmms.modules.tjfx.zhcdksjmx.entity.Zhcksjmx;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description: 支行存款数据明细
 * @Author: cmms
 * @Date:   2019-09-17
 * @Version: V1.0
 */
@Service
public class GzrlServiceImpl extends ServiceImpl<GzrlMapper, Zhcksjmx> implements IGzrlService {
    @Resource
    private GzrlMapper gzrlMapper;

    @Override
    public List<Map> getgzrlxx(String dx) {
        return gzrlMapper.getgzrlxx(dx);
    }
    

    
}
