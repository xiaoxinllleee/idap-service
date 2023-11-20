package org.cmms.modules.tjfx.xdgtzytjbb.tjfxzhpjsxwcqkmxb.service.impl;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.xdgtzytjbb.tjfxzhpjsxwcqkmxb.entity.TjfxPjsxwcqkmxb;
import org.cmms.modules.tjfx.xdgtzytjbb.tjfxzhpjsxwcqkmxb.mapper.TjfxPjsxwcqkmxbMapper;
import org.cmms.modules.tjfx.xdgtzytjbb.tjfxzhpjsxwcqkmxb.service.ITjfxPjsxwcqkmxbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;

/**
 * @Description: 评级授信完成情况明表
 * @Author: jeecg-boot
 * @Date:   2020-02-17
 * @Version: V1.0
 */
@Service
public class TjfxPjsxwcqkmxbServiceImpl extends ServiceImpl<TjfxPjsxwcqkmxbMapper, TjfxPjsxwcqkmxb> implements ITjfxPjsxwcqkmxbService {

    @Autowired
    TjfxPjsxwcqkmxbMapper tjfxPjsxwcqkmxbMapper;


    @Override
    public List<TjfxPjsxwcqkmxb> querysjzfhs ( Date ksrq,  Date jsrq , String xzc, String qfbs,String code){
        return tjfxPjsxwcqkmxbMapper.querysjzfhs(ksrq, jsrq, xzc, qfbs, code);
    }

    @Override
    public List<TjfxPjsxwcqkmxb> querysjpjhs ( Date ksrq, Date jsrq , String xzc, String qfbs){
        return tjfxPjsxwcqkmxbMapper.querysjpjhs(ksrq, jsrq, xzc, qfbs);
    }

}
