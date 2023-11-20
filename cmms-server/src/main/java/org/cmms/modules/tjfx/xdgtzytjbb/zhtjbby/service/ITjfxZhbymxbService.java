package org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.entity.TjfxZhbymxb;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

/**
 * @Description: 支行表一明细表
 * @Author: jeecg-boot
 * @Date:   2020-02-11
 * @Version: V1.0
 */
public interface ITjfxZhbymxbService extends IService<TjfxZhbymxb> {
    public List<TjfxZhbymxb> queryTableDictTextByKey(Date ksrq, Date jsrq, String xzc, String zkhjl, String khlx , String code, String zdmc,String qfbs);

    public List<TjfxZhbymxb>querykhzfmx(Date ksrq, Date jsrq,  String xzc,  String zkhjl, String khlx, String code, String zdmc,String qfbs);

    public List<TjfxZhbymxb>queryljkhzfmx(Date ksrq, Date jsrq,String xzc, String zkhjl,  String khlx, String code, String zdmc,String qfbs);

    public List<TjfxZhbymxb>querypjsxjdmx(Date ksrq,  Date jsrq,  String xzc,  String zkhjl,String code, String zdmc,String qfbs);

    public List<TjfxZhbymxb>queryljpjsxjdmx(Date ksrq,  Date jsrq, String xzc,  String zkhjl,String code, String zdmc,String qfbs);

    public List<TjfxZhbymxb>querypjsxjdyxjemx( Date ksrq,  Date jsrq, String xzc,  String zkhjl,String code, String zdmc,String qfbs);

    public List<TjfxZhbymxb>queryljpjsxjdyxjemx(Date ksrq,  Date jsrq, String xzc,  String zkhjl,String code, String zdmc,String qfbs);
}
