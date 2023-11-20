package org.cmms.modules.tjfx.pjsxwcqkmxb.xzcwcqkmxb.service.impl;

import org.cmms.modules.tjfx.pjsxwcqkmxb.xzcwcqkmxb.entity.PjsxwcqkCmx;
import org.cmms.modules.tjfx.pjsxwcqkmxb.xzcwcqkmxb.mapper.PjsxwcqkCmxMapper;
import org.cmms.modules.tjfx.pjsxwcqkmxb.xzcwcqkmxb.service.IPjsxwcqkCmxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

/**
 * @Description: 行政村完成情况明细表
 * @Author: jeecg-boot
 * @Date:   2020-03-17
 * @Version: V1.0
 */
@Service
public class PjsxwcqkCmxServiceImpl extends ServiceImpl<PjsxwcqkCmxMapper, PjsxwcqkCmx> implements IPjsxwcqkCmxService {

    @Autowired
    private PjsxwcqkCmxMapper mapper;

    @Override
    public void InitDataToXzc(Map<String, String> sql) {
        mapper.InitDataToXzc(sql);
    }
}
