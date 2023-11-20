package org.cmms.modules.yxdygl.sjyxdygl.service.impl;

import org.cmms.modules.yxdygl.sjyxdygl.entity.VSjyxdygl;
import org.cmms.modules.yxdygl.sjyxdygl.mapper.VSjyxdyglMapper;
import org.cmms.modules.yxdygl.sjyxdygl.service.IVSjyxdyglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 三级营销单元管理
 * @Author: Penghr
 * @Date:   2020-07-18
 * @Version: V1.0
 */
@Service
public class VSjyxdyglServiceImpl extends ServiceImpl<VSjyxdyglMapper, VSjyxdygl> implements IVSjyxdyglService {

    @Autowired
    private VSjyxdyglMapper mapper;

    @Override
    public String querySszhBySjyxdybh(String dybh) {
        return mapper.querySszhBySjyxdybh(dybh);
    }
}
