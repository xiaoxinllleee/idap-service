package org.cmms.modules.pad.nhxxgl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.pad.nhxxgl.entity.YxglKhhfxxb;
import org.cmms.modules.pad.nhxxgl.mapper.YxglKhhfxxbMapper;
import org.cmms.modules.pad.nhxxgl.service.IYxglKhhfxxbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 客户回访信息表
 * @Author: jeecg-boot
 * @Date:   2020-02-28
 * @Version: V1.0
 */
@Service
public class YxglKhhfxxbServiceImpl extends ServiceImpl<YxglKhhfxxbMapper, YxglKhhfxxb> implements IYxglKhhfxxbService {

    @Autowired
    private YxglKhhfxxbMapper mapper;

    @Override
    public List<YxglKhhfxxb> queryHfxxByZjhm(String zjhm) {
        return mapper.queryHfxxByZjhm(zjhm);
    }
}
