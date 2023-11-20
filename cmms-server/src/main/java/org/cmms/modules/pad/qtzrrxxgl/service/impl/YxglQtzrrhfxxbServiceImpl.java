package org.cmms.modules.pad.qtzrrxxgl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.pad.qtzrrxxgl.entity.YxglQtzrrhfxxb;
import org.cmms.modules.pad.qtzrrxxgl.mapper.YxglQtzrrhfxxbMapper;
import org.cmms.modules.pad.qtzrrxxgl.service.IYxglQtzrrhfxxbService;
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
public class YxglQtzrrhfxxbServiceImpl extends ServiceImpl<YxglQtzrrhfxxbMapper, YxglQtzrrhfxxb> implements IYxglQtzrrhfxxbService {

    @Autowired
    private YxglQtzrrhfxxbMapper mapper;

    @Override
    public List<YxglQtzrrhfxxb> queryHfxxByZjhm(String zjhm) {
        return mapper.queryHfxxByZjhm(zjhm);
    }
}
