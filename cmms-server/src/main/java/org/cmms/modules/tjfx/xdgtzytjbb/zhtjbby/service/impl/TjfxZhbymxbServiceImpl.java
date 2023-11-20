package org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.service.impl;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.entity.TjfxZhbymxb;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.mapper.TjfxZhbymxbMapper;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.service.ITjfxZhbymxbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;

/**
 * @Description: 支行表一明细表
 * @Author: jeecg-boot
 * @Date:   2020-02-11
 * @Version: V1.0
 */
@Service
public class TjfxZhbymxbServiceImpl extends ServiceImpl<TjfxZhbymxbMapper, TjfxZhbymxb> implements ITjfxZhbymxbService {
    @Autowired
    TjfxZhbymxbMapper tjfxZhbymxbMapper;

    @Override
    public List<TjfxZhbymxb> queryTableDictTextByKey(Date ksrq, Date jsrq, String xzc, String zkhjl, String khlx,String code, String zdmc,String qfbs) {
        return tjfxZhbymxbMapper.queryTableDictTextByKey(ksrq, jsrq, xzc, zkhjl, khlx,code,zdmc,qfbs);
    }
    @Override
    public List<TjfxZhbymxb>querykhzfmx( Date ksrq,  Date jsrq,  String xzc, String zkhjl,String khlx,String code, String zdmc,String qfbs) {
        return tjfxZhbymxbMapper.querykhzfmx(ksrq,jsrq,xzc,zkhjl,khlx,code,zdmc,qfbs);
    }
    @Override
    public List<TjfxZhbymxb>queryljkhzfmx(Date ksrq,  Date jsrq, String xzc,  String zkhjl,  String khlx,String code, String zdmc,String qfbs) {
            return tjfxZhbymxbMapper.queryljkhzfmx(ksrq,jsrq,xzc,zkhjl,khlx,code,zdmc,qfbs);

    }
    @Override
    public List<TjfxZhbymxb>querypjsxjdmx(@Param("ksrq") Date ksrq, @Param("jsrq") Date jsrq, @Param("xzc") String xzc, @Param("zkhjl") String zkhjl,String code, String zdmc,String qfbs) {
        return tjfxZhbymxbMapper.querypjsxjdmx(ksrq,jsrq,xzc,zkhjl,code,zdmc,qfbs);
    }

    @Override
    public List<TjfxZhbymxb>queryljpjsxjdmx(@Param("ksrq") Date ksrq, @Param("jsrq") Date jsrq,@Param("xzc") String xzc, @Param("zkhjl") String zkhjl,String code, String zdmc,String qfbs) {
        return tjfxZhbymxbMapper.queryljpjsxjdmx(ksrq,jsrq,xzc,zkhjl,code,zdmc,qfbs);
    }

    @Override
    public List<TjfxZhbymxb>querypjsxjdyxjemx(@Param("ksrq") Date ksrq, @Param("jsrq") Date jsrq, @Param("xzc") String xzc, @Param("zkhjl") String zkhjl,String code, String zdmc,String qfbs) {
        return tjfxZhbymxbMapper.querypjsxjdyxjemx(ksrq,jsrq,xzc,zkhjl,code,zdmc,qfbs);
    }

    public List<TjfxZhbymxb>queryljpjsxjdyxjemx(@Param("ksrq") Date ksrq, @Param("jsrq") Date jsrq,@Param("xzc") String xzc, @Param("zkhjl") String zkhjl,String code, String zdmc,String qfbs) {
        return tjfxZhbymxbMapper.queryljpjsxjdyxjemx(ksrq,jsrq,xzc,zkhjl,code,zdmc,qfbs);
    }
}
