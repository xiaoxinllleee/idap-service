package org.cmms.modules.tjfx.xdgtzytjbb.zhtjbbe.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbbe.entity.TjfxZhbemxb;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 支行表二明细表
 * @Author: jeecg-boot
 * @Date:   2020-02-14
 * @Version: V1.0
 */
public interface ITjfxZhbemxbService extends IService<TjfxZhbemxb> {

    public List<Map<String,Object>> csqueryckyemx ( Date tjyf,  String zdmc,  String xzc,  String code,  String zkhjl );

    public List<TjfxZhbemxb>queryckyemx( Date tjyf,  String zdmc, String xzc,  String code, String zkhjl,String qfbs);

    public List<TjfxZhbemxb>queryclhsmx( Date tjyf,  String zdmc, String xzc, String code, String zkhjl, String code2,String qfbs);

    public List<TjfxZhbemxb>queryzlhsmx( Date tjyf,  String zdmc, String xzc, String code, String zkhjl,String code2,String qfbs);


    public List<TjfxZhbemxb>querycscqckyemx( Date tjyf,  String zdmc, String xzc,String qfbs);

    public List<TjfxZhbemxb>querycscqclhsmx( Date tjyf,  String zdmc, String xzc, String code,String qfbs);

    public List<TjfxZhbemxb>querycscqzlhsmx( Date tjyf,  String zdmc, String xzc, String code,String qfbs);

}
