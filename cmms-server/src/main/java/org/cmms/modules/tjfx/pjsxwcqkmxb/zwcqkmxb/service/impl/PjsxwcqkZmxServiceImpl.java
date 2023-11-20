package org.cmms.modules.tjfx.pjsxwcqkmxb.zwcqkmxb.service.impl;

import org.cmms.modules.tjfx.pjsxwcqkmxb.zwcqkmxb.entity.PjsxwcqkZmx;
import org.cmms.modules.tjfx.pjsxwcqkmxb.zwcqkmxb.mapper.PjsxwcqkZmxMapper;
import org.cmms.modules.tjfx.pjsxwcqkmxb.zwcqkmxb.service.IPjsxwcqkZmxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

/**
 * @Description: 组完成情况明细表
 * @Author: jeecg-boot
 * @Date:   2020-03-17
 * @Version: V1.0
 */
@Service
public class PjsxwcqkZmxServiceImpl extends ServiceImpl<PjsxwcqkZmxMapper, PjsxwcqkZmx> implements IPjsxwcqkZmxService {

    @Autowired
    private PjsxwcqkZmxMapper mapper;

    @Override
    public void InitDataToXzz(Map<String, String> sql) {
        mapper.InitDataToXzz(sql);
    }
}
