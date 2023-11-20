package org.cmms.modules.tjfx.pjsxwcqkmxb.qhwcqkmxb.service.impl;

import org.cmms.modules.tjfx.pjsxwcqkmxb.qhwcqkmxb.entity.PjsxwcqkQh;
import org.cmms.modules.tjfx.pjsxwcqkmxb.qhwcqkmxb.mapper.PjsxwcqkQhMapper;
import org.cmms.modules.tjfx.pjsxwcqkmxb.qhwcqkmxb.service.IPjsxwcqkQhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

/**
 * @Description: 全行完成情况明细表
 * @Author: jeecg-boot
 * @Date:   2020-03-16
 * @Version: V1.0
 */
@Service
public class PjsxwcqkQhServiceImpl extends ServiceImpl<PjsxwcqkQhMapper, PjsxwcqkQh> implements IPjsxwcqkQhService {

    @Autowired
    private PjsxwcqkQhMapper mapper;

    @Override
    public void InitDataToQh(Map<String, String> sql) {
        mapper.InitDataToQh(sql);
    }
}
