package org.cmms.modules.tjfx.pjsxwcqkmxb.zhwcqkmxb.service.impl;

import org.cmms.modules.tjfx.pjsxwcqkmxb.zhwcqkmxb.entity.PjsxwcqkZh;
import org.cmms.modules.tjfx.pjsxwcqkmxb.zhwcqkmxb.mapper.PjsxwcqkZhMapper;
import org.cmms.modules.tjfx.pjsxwcqkmxb.zhwcqkmxb.service.IPjsxwcqkZhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

/**
 * @Description: 支行完成情况明细表
 * @Author: jeecg-boot
 * @Date:   2020-03-16
 * @Version: V1.0
 */
@Service
public class PjsxwcqkZhServiceImpl extends ServiceImpl<PjsxwcqkZhMapper, PjsxwcqkZh> implements IPjsxwcqkZhService {

    @Autowired
    private PjsxwcqkZhMapper mapper;

    @Override
    public void InitDataToZh(Map<String, String> sql) {
        mapper.InitDataToZh(sql);
    }
}
